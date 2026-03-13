package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.core.capability.Capability;
import it.frontend.e2e.framework.core.capability.dispatcher.DefaultCapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;

import java.lang.reflect.Proxy;

public class DefaultBinder implements IBinder {

    private final ICapabilityDispatcher dispatcher;

    public DefaultBinder() {
        this.dispatcher = new DefaultCapabilityDispatcher();
    }

    public DefaultBinder(ICapabilityDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Capability> T bind(Class<T> type) {
        if (!type.isInterface())
            throw new IllegalArgumentException(type.getName() + " is not an interface.");

        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                new DefaultBinderInvocationHandler(this.dispatcher)
        );
    }
}

