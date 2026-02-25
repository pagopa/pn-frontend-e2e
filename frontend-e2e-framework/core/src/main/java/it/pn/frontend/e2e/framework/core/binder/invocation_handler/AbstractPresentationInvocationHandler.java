package it.pn.frontend.e2e.framework.core.binder.invocation_handler;

import it.pn.frontend.e2e.framework.annotation.Selector;
import it.pn.frontend.e2e.framework.core.binder.invocation_handler.context.BaseInvocationContext;
import it.pn.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class AbstractPresentationInvocationHandler<Context extends BaseInvocationContext> implements InvocationHandler {

    protected final ICapabilityDispatcher<Context> dispatcher;

    protected AbstractPresentationInvocationHandler(ICapabilityDispatcher<Context> dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.isDefault())
            return InvocationHandler.invokeDefault(proxy, method, args);

        if (method.getDeclaringClass() == Object.class)
            return method.invoke(this, args);

        String selectorValue = resolveSelector(method);
        Context ctx = createContext(proxy, method, args, selectorValue);

        return dispatcher.dispatch(ctx);
    }

    private String resolveSelector(Method method) {

        // Metodo
        if (method.isAnnotationPresent(Selector.class)) {
            return method.getAnnotation(Selector.class).value();
        }

        // Interfaccia dichiarata
        Class<?> declaringClass = method.getDeclaringClass();
        if (declaringClass.isAnnotationPresent(Selector.class)) {
            return declaringClass.getAnnotation(Selector.class).value();
        }

        return null;
    }

    /**
     * Hook method – implemented by domain-specific subclasses (web, mobile, mock).
     */
    protected abstract Context createContext(
            Object proxy,
            Method method,
            Object[] args,
            String selectorValue
    );
}
