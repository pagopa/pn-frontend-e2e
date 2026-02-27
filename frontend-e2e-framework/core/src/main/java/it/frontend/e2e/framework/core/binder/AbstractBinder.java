package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.core.capability.core.Gettable;
import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public abstract class AbstractBinder<
        S extends Selector,
        L extends Location,
        E extends AbstractPresentationElement<S,L>
        > implements IBinder<S, L, E> {

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Gettable<S,L,E>> T bind(Class<T> type) {
        if (!type.isInterface())
            throw new IllegalArgumentException(type.getName() + " is not an interface.");

        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                getInvocationHandler(type)
        );
    }

    /**
     * Hook method: implemented by domain-specific binders (web, mobile, mock).
     */
    protected abstract <T extends Gettable<S,L,E>> InvocationHandler getInvocationHandler(Class<T> boundType);
}

