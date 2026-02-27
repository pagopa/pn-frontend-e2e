package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.core.capability.core.Gettable;
import it.frontend.e2e.framework.core.capability.dispatcher.DefaultCapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;
import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;
import lombok.Getter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;


public class DefaultBinder<
        S extends Selector,
        L extends Location,
        E extends AbstractPresentationElement<S,L>
        > implements IBinder<S, L, E> {

    @Getter
    private final List<ICapabilityHandler> handlers;
    private final InvocationHandler invocationHandler;

    public DefaultBinder(List<ICapabilityHandler> handlers) {
        this.handlers = handlers;

        ICapabilityDispatcher dispatcher = new DefaultCapabilityDispatcher(handlers);
        this.invocationHandler = new DefaultBinderInvocationHandler(dispatcher);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Gettable<S,L,E>> T bind(Class<T> type) {
        if (!type.isInterface())
            throw new IllegalArgumentException(type.getName() + " is not an interface.");

        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                invocationHandler
        );
    }
}

