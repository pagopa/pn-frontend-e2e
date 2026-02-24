package it.pn.frontend.e2e.framework.core.binder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public abstract class AbstractPresentationBinder implements IPresentationBinder {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T bind(Class<T> type) {
        if (!type.isInterface())
            throw new IllegalArgumentException(type.getName() + " is not an interface.");

        Class<?> resolved = resolveClass(type);

        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{resolved},
                createInvocationHandler(type)
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
    protected abstract InvocationHandler createInvocationHandler(Class<?> boundType);
}

