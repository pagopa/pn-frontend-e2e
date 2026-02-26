package it.frontend.e2e.framework.core.presentation_binder;

import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public abstract class AbstractBinder<S extends Selector, L extends Location> implements IBinder<S, L> {

    @Override
    @SuppressWarnings("unchecked")
    public <T extends AbstractPresentationElement<S,L>> T bind(Class<T> type) {
        if (!type.isInterface())
            throw new IllegalArgumentException(type.getName() + " is not an interface.");

        Class<?> resolved = resolveClass(type);

        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{resolved},
                getInvocationHandler(type)
        );
    }

    private Class<?> resolveClass(Class<?> requested) {
        String className = requested.getName();

        try {
            return Class.forName(className, false, requested.getClassLoader());
        } catch (ClassNotFoundException ignored) {
            return requested;
        }
    }

    /**
     * Hook method: implemented by domain-specific binders (web, mobile, mock).
     */
    protected abstract InvocationHandler getInvocationHandler(Class<?> boundType);
}

