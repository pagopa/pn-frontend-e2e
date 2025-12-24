package it.pn.frontend.e2e.framework.core.binder;

import it.pn.frontend.e2e.framework.core.adapter.IPresentationApiAdapter;
import it.pn.frontend.e2e.framework.core.binder.invocation_handler.context.BaseInvocationContext;
import it.pn.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public abstract class AbstractPresentationBinder<ApiAdapter extends IPresentationApiAdapter, Context extends BaseInvocationContext>
        implements IPresentationBinder {

    protected final ApiAdapter adapter;
    protected final ICapabilityDispatcher<Context> dispatcher;

    protected AbstractPresentationBinder(ApiAdapter adapter, ICapabilityDispatcher<Context> dispatcher) {
        this.adapter = adapter;
        this.dispatcher = dispatcher;
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> T bind(Class<T> type) {
        if (!type.isInterface())
            throw new IllegalArgumentException(type.getName() + " is not an interface.");

        Class<?> resolved = resolveGeneratedType(type);

        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{resolved},
                createInvocationHandler(type)
        );
    }

    private Class<?> resolveGeneratedType(Class<?> requested) {
        String genName = requested.getName() + "$Gen";
        try {
            return Class.forName(genName, false, requested.getClassLoader());
        } catch (ClassNotFoundException ignored) {
            return requested;
        }
    }

    /**
     * Hook method: implemented by domain-specific binders (web, mobile, mock).
     */
    protected abstract InvocationHandler createInvocationHandler(Class<?> boundType);
}

