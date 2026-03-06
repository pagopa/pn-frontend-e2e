package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.core.capability.Capability;
import it.frontend.e2e.framework.core.capability.dispatcher.DefaultCapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;
import lombok.Getter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;


public class DefaultBinder implements IBinder {

    @Getter
    private final List<ICapabilityHandler> handlers;
    private final InvocationHandler invocationHandler;

    public DefaultBinder(List<ICapabilityHandler> handlers) {
        this.handlers = handlers;

        ICapabilityDispatcher dispatcher = new DefaultCapabilityDispatcher(handlers);
        this.invocationHandler = new DefaultBinderInvocationHandler(dispatcher);
    }

    public DefaultBinder(InvocationHandler invocationHandler, List<ICapabilityHandler> handlers) {
        this.handlers = handlers;
        this.invocationHandler = invocationHandler;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Capability> T bind(Class<T> type) {
        if (!type.isInterface())
            throw new IllegalArgumentException(type.getName() + " is not an interface.");

        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                invocationHandler
        );
    }
}

