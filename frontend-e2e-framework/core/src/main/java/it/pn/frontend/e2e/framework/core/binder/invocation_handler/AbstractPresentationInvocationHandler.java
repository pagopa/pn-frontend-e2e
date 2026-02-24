package it.pn.frontend.e2e.framework.core.binder.invocation_handler;

import it.pn.frontend.e2e.framework.core.binder.invocation_handler.context.BaseInvocationContext;
import it.pn.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import lombok.RequiredArgsConstructor;

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

        Context ctx = createContext(proxy, method, args);

        return dispatcher.dispatch(ctx);
    }

    /**
     * Hook method – implemented by domain-specific subclasses (web, mobile, mock).
     */
    protected abstract Context createContext(
            Object proxy,
            Method method,
            Object[] args
    );
}
