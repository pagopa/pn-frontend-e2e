package it.pn.frontend.e2e.presentation.core.binder;

import it.pn.frontend.e2e.presentation.core.adapter.IPresentationApiAdapter;
import it.pn.frontend.e2e.presentation.core.binder.invocation_handler.PresentationInvocationHandler;
import it.pn.frontend.e2e.presentation.core.capability.dispatcher.CapabilityDispatcher;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Proxy;

@Getter
@Setter
@RequiredArgsConstructor
public class PresentationBinder<ApiAdapter extends IPresentationApiAdapter> implements IPresentationBinder {

    protected final ApiAdapter adapter;
    protected final CapabilityDispatcher dispatcher;

    @Override
    @SuppressWarnings("unchecked")
    public <T> T bind(Class<T> type) {
        if(!type.isInterface())
            throw new IllegalArgumentException(type.getName() + " is not an interface. Interface is required.");

        return (T) createProxy(type);
    }

    protected <T> Object createProxy(Class<T> type) {
        return Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                new PresentationInvocationHandler<>(
                        dispatcher,
                        adapter,
                        type
                )
        );
    }
}
