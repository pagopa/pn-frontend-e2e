package it.pn.frontend.e2e.presentation.core.binder;

import it.pn.frontend.e2e.presentation.core.adapter.IPresentationApiAdapter;
import it.pn.frontend.e2e.presentation.core.binder.invocation_handler.context.BaseInvocationContext;
import it.pn.frontend.e2e.presentation.core.capability.dispatcher.ICapabilityDispatcher;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@RequiredArgsConstructor
public abstract class AbstractPresentationBinder<ApiAdapter extends IPresentationApiAdapter, Context extends BaseInvocationContext>
        implements IPresentationBinder {

    protected final ApiAdapter adapter;
    protected final ICapabilityDispatcher<Context> dispatcher;


    @Override
    @SuppressWarnings("unchecked")
    public <T> T bind(Class<T> type) {
        if (!type.isInterface())
            throw new IllegalArgumentException(type.getName() + " is not an interface.");

        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                createInvocationHandler(type)
        );
    }

    /**
     * Hook method: implemented by domain-specific binders (web, mobile, mock).
     */
    protected abstract InvocationHandler createInvocationHandler(Class<?> boundType);
}

