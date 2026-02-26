package it.frontend.e2e.framework.core.presentation_binder;

import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class AbstractBinderInvocationHandler implements InvocationHandler {

    protected final ICapabilityDispatcher dispatcher;

    protected AbstractBinderInvocationHandler(ICapabilityDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.isDefault())
            return InvocationHandler.invokeDefault(proxy, method, args);

        if (method.getDeclaringClass() == Object.class)
            return method.invoke(this, args);

        return dispatcher.dispatch(method);
    }
}
