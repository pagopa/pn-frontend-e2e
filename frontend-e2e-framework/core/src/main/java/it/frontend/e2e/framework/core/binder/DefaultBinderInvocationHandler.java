package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.annotation.Selector;
import it.frontend.e2e.framework.core.capability.Capability;
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
        this.dispatcher = dispatcher;
        this.ctx = BindContext.root();
    }

    public DefaultBinderInvocationHandler(ICapabilityDispatcher dispatcher, BindContext ctx) {
        this.dispatcher = dispatcher;
        this.ctx = ctx;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.isDefault())
            return InvocationHandler.invokeDefault(proxy, method, args);

        if (method.getDeclaringClass() == Object.class) {
            return switch (method.getName()) {
                case "equals" -> proxy == args[0];
                case "hashCode" -> System.identityHashCode(proxy);
                case "toString" -> proxy.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(proxy));
                default -> method.invoke(this, args);
            };
        }

        Class<?> rt = method.getReturnType();

        // RICORSIONE: se il return type è un DomainElement o è una Capability -> nuovo proxy dello stesso framework
        if (DomainElement.class.isAssignableFrom(rt) || Capability.class.isAssignableFrom(rt)) {
            String childSel = resolveSelector(method);
            String fullSel = compose(ctx.selector(), childSel);

            logger.logDebug("Binding recursive element: " + rt.getSimpleName() +
                    " | From: " + method.getDeclaringClass().getSimpleName() +
                    " | Selector: " + fullSel);

            return Proxy.newProxyInstance(
                    rt.getClassLoader(),
                    new Class<?>[]{rt},
                    new DefaultBinderInvocationHandler(dispatcher, new BindContext(fullSel))
            );
        }

        // Gestione dei metodi delle capability
        logger.logDebug("Dispatching capability method: " + method.getName() + " | Selector: " + ctx.selector());
        return dispatcher.dispatch(method, args, ctx.selector());
    }

    public static String compose(String parent, String child) {
        if (parent == null || parent.isBlank()) return child;

        // "assoluto"
        if (child.startsWith("//") || child.startsWith("(//") || child.startsWith(".//"))
            return child;

        if (child.startsWith("/"))
            return parent + child;

        return parent + "/" + child;
    }

    private static String resolveSelector(Method method) {
        Selector onMethod = method.getAnnotation(Selector.class);
        if (onMethod != null) return onMethod.value();

        // fallback: selector sul return type
        Selector onType = method.getReturnType().getAnnotation(Selector.class);
        if (onType != null) return onType.value();

        return "";
    }

}
