package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.annotation.selector.XPath;
import it.frontend.e2e.framework.core.binder.context.BindContext;
import it.frontend.e2e.framework.core.capability.Capability;
import it.frontend.e2e.framework.core.capability.context.CapabilityScope;
import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.frontend.e2e.framework.core.logging.ILogger;
import it.frontend.e2e.framework.core.logging.Slf4jLogger;
import it.frontend.e2e.framework.core.model.DomainElement;

import java.lang.reflect.*;
import java.util.Optional;

public class DefaultBinderInvocationHandler implements InvocationHandler {

    protected final ICapabilityDispatcher dispatcher;
    private final BindContext ctx;
    private final boolean optionalBestEffort;
    private final ILogger logger = new Slf4jLogger();

    public DefaultBinderInvocationHandler(ICapabilityDispatcher dispatcher) {
        this(dispatcher, BindContext.root(), false);
    }

    public DefaultBinderInvocationHandler(ICapabilityDispatcher dispatcher, BindContext ctx) {
        this(dispatcher, ctx, false);
    }

    private DefaultBinderInvocationHandler(ICapabilityDispatcher dispatcher, BindContext ctx, boolean optionalBestEffort) {
        this.dispatcher = dispatcher;
        this.ctx = ctx;
        this.optionalBestEffort = optionalBestEffort;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getDeclaringClass() == Object.class) {
            return switch (method.getName()) {
                case "equals" -> proxy == args[0];
                case "hashCode" -> System.identityHashCode(proxy);
                case "toString" -> proxy.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(proxy));
                default -> throw new UnsupportedOperationException("Object method not supported: " + method.getName());
            };
        }

        try {
            if (method.isDefault()) return handleDefaultMethod(proxy, method, args);
            if (isOptionalReturn(method)) return bindOptional(method, args);

            Class<?> rt = method.getReturnType();

            if (isBindableType(rt)) return bindRecursive(method, rt, optionalBestEffort);
            return resolveCapabilityMethod(method, args, ctx);
        } catch (RuntimeException ex) {
            if (!optionalBestEffort) {
                throw ex;
            }
            logger.logDebug("Best-effort Optional invocation failed for method: " + method.getName() + " -> fallback");
            return fallbackValue(method.getReturnType());
        }
    }

    protected Object handleDefaultMethod(Object proxy, Method method, Object[] args) throws Throwable {
        return InvocationHandler.invokeDefault(proxy, method, args);
    }

    protected InvocationHandler getInvocationHandlerFor(Method method, Class<?> returnType, BindContext bindContext) {
        return new DefaultBinderInvocationHandler(this.dispatcher, bindContext, optionalBestEffort);
    }

    protected <T> T resolveCapabilityMethod(Method method, Object[] args, BindContext bindContext ) {
        logger.logDebug("Dispatching capability method: " + method.getName() + " | " + bindContext.toString());
        return dispatcher.dispatch(method, args, bindContext.getScope());
    }

    protected String compose(String parent, String child) {
        if (parent == null || parent.isBlank()) return child;
        if (child == null || child.isBlank()) return parent;

        parent = parent.trim();
        child = child.trim();

        if (child.startsWith("./")) child = child.substring(2);

        if (parent.endsWith("/")) return parent + child;
        return parent + "/" + child;
    }

    private static String resolveXPath(Method method, Class<?> returnType) {
        XPath onMethod = method.getAnnotation(XPath.class);
        if (onMethod != null) return onMethod.value();

        XPath onType = returnType.getAnnotation(XPath.class);
        if (onType != null) return onType.value();

        return "";
    }

    private boolean isOptionalReturn(Method method) {
        return Optional.class.equals(method.getReturnType());
    }

    private boolean isBindableType(Class<?> type) {
        return DomainElement.class.isAssignableFrom(type) || Capability.class.isAssignableFrom(type);
    }

    private Object bindRecursive(Method method, Class<?> returnType, boolean optionalBestEffort) {
        String childSel = resolveXPath(method, returnType);
        String fullSel = compose(ctx.getScope().selector(), childSel);
        CapabilityScope scope = new CapabilityScope(fullSel, ctx.getScope().location());

        logger.logInfo("Binding recursive element: " + returnType.getSimpleName() +
                " | From: " + method.getDeclaringClass().getSimpleName() +
                " | Selector: " + fullSel);

        return Proxy.newProxyInstance(
                returnType.getClassLoader(),
                new Class<?>[]{returnType},
                new DefaultBinderInvocationHandler(this.dispatcher, new BindContext(scope), optionalBestEffort)
        );
    }

    private Optional<?> bindOptional(Method method, Object[] args) {
        Type optionalArgType = extractOptionalType(method);
        Class<?> innerType = resolveClass(optionalArgType);

        if (innerType == null || !isBindableType(innerType)) {
            try {
                Object value = resolveCapabilityMethod(method, args, ctx);
                if (value instanceof Optional<?> optionalValue) {
                    return optionalValue;
                }
                return Optional.ofNullable(value);
            } catch (RuntimeException ex) {
                logger.logDebug("Best-effort Optional capability resolution failed for method: " + method.getName() + " -> empty");
                return Optional.empty();
            }
        }

        try {
            Object bound = bindRecursive(method, innerType, true);
            return Optional.ofNullable(bound);
        } catch (RuntimeException ex) {
            logger.logDebug("Best-effort Optional bind failed for method: " + method.getName() + " -> empty");
            return Optional.empty();
        }
    }

    private Object fallbackValue(Class<?> returnType) {
        if (Void.TYPE.equals(returnType)) return null;
        if (Boolean.TYPE.equals(returnType)) return false;
        if (Byte.TYPE.equals(returnType)) return (byte) 0;
        if (Short.TYPE.equals(returnType)) return (short) 0;
        if (Integer.TYPE.equals(returnType)) return 0;
        if (Long.TYPE.equals(returnType)) return 0L;
        if (Float.TYPE.equals(returnType)) return 0f;
        if (Double.TYPE.equals(returnType)) return 0d;
        if (Character.TYPE.equals(returnType)) return '\0';
        if (Optional.class.equals(returnType)) return Optional.empty();
        return null;
    }

    private Type extractOptionalType(Method method) {
        Type generic = method.getGenericReturnType();
        if (!(generic instanceof ParameterizedType pt)) {
            throw new IllegalStateException("Optional senza tipo parametrico: " + method);
        }
        return pt.getActualTypeArguments()[0];
    }

    private Class<?> resolveClass(Type type) {
        if (type instanceof Class<?> c) {
            return c;
        }

        if (type instanceof ParameterizedType pt && pt.getRawType() instanceof Class<?> raw) {
            return raw;
        }

        if (type instanceof WildcardType wt) {
            for (Type upper : wt.getUpperBounds()) {
                Class<?> resolved = resolveClass(upper);
                if (resolved != null && !Object.class.equals(resolved)) {
                    return resolved;
                }
            }
            return null;
        }

        if (type instanceof TypeVariable<?> tv) {
            for (Type bound : tv.getBounds()) {
                Class<?> resolved = resolveClass(bound);
                if (resolved != null && !Object.class.equals(resolved)) {
                    return resolved;
                }
            }
            return null;
        }

        return null;
    }
}
