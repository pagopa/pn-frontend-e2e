package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.annotation.selector.XPath;
import it.frontend.e2e.framework.core.binder.context.BindContext;
import it.frontend.e2e.framework.core.capability.Capability;
import it.frontend.e2e.framework.core.capability.context.CapabilityScope;
import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.frontend.e2e.framework.core.logging.ILogger;
import it.frontend.e2e.framework.core.logging.Slf4jLogger;
import it.frontend.e2e.framework.core.model.DomainElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DefaultBinderInvocationHandler implements InvocationHandler {

    protected final ICapabilityDispatcher dispatcher;
    private final BindContext ctx;
    private final ILogger logger = new Slf4jLogger();

    public DefaultBinderInvocationHandler(ICapabilityDispatcher dispatcher) {
        this(dispatcher, BindContext.root());
    }

    public DefaultBinderInvocationHandler(ICapabilityDispatcher dispatcher, BindContext ctx) {
        this.dispatcher = dispatcher;
        this.ctx = ctx;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.isDefault())
            return handleDefaultMethod(proxy, method, args);

        if (method.getDeclaringClass() == Object.class) {
            return switch (method.getName()) {
                case "equals"   -> proxy == args[0];
                case "hashCode" -> System.identityHashCode(proxy);
                case "toString" -> proxy.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(proxy));
                default -> throw new UnsupportedOperationException("Object method not supported: " + method.getName());
            };
        }

        Class<?> rt = method.getReturnType();

        // RICORSIONE: se il return type è un DomainElement o una Capability -> nuovo proxy dello stesso framework
        if (DomainElement.class.isAssignableFrom(rt) || Capability.class.isAssignableFrom(rt)) {
            String childSel = resolveXPath(method);
            String fullSel  = compose(ctx.getScope().selector(), childSel);
            CapabilityScope scope = new CapabilityScope(fullSel, ctx.getScope().location());

            logger.logInfo("Binding recursive element: " + rt.getSimpleName() +
                    " | From: " + method.getDeclaringClass().getSimpleName() +
                    " | Selector: " + fullSel);

            return Proxy.newProxyInstance(
                    rt.getClassLoader(),
                    new Class<?>[]{rt},
                    getInvocationHandlerFor(method, rt, new BindContext(scope))
            );
        }

        // Gestione dei metodi delle capability
        return resolveCapabilityMethod(method, args, ctx);
    }

    protected Object handleDefaultMethod(Object proxy, Method method, Object[] args) throws Throwable {
        return InvocationHandler.invokeDefault(proxy, method, args);
    }

    protected InvocationHandler getInvocationHandlerFor(Method method, Class<?> returnType, BindContext bindContext) {
        return new DefaultBinderInvocationHandler(this.dispatcher, bindContext);
    }

    protected <T> T resolveCapabilityMethod(Method method, Object[] args, BindContext bindContext ) {
        logger.logDebug("Dispatching capability method: " + method.getName() + " | " + bindContext.toString());
        return dispatcher.dispatch(method, args, bindContext.getScope());
    }

    protected String compose(String parent, String child) {
        if (parent == null || parent.isBlank()) return child;
        if (child  == null || child.isBlank())  return parent;

        parent = parent.trim();
        child  = child.trim();

        if (child.startsWith("./")) child = child.substring(2);

        if (parent.endsWith("/")) return parent + child;
        return parent + "/" + child;
    }

    private static String resolveXPath(Method method) {
        XPath onMethod = method.getAnnotation(XPath.class);
        if (onMethod != null) return onMethod.value();

        XPath onType = method.getReturnType().getAnnotation(XPath.class);
        if (onType != null) return onType.value();

        return "";
    }
}
