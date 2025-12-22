package it.pn.frontend.e2e.presentation.core.binder.invocation_handler;

import it.pn.frontend.e2e.presentation.core.adapter.IPresentationApiAdapter;
import it.pn.frontend.e2e.presentation.core.binder.invocation_handler.model.InvocationContext;
import it.pn.frontend.e2e.presentation.core.capability.dispatcher.CapabilityDispatcher;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class PresentationInvocationHandler<ApiAdapter extends IPresentationApiAdapter> implements InvocationHandler {
    protected final CapabilityDispatcher dispatcher;
    protected final ApiAdapter adapter;
    protected final Class<?> boundType;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(method.isDefault())
            return InvocationHandler.invokeDefault(proxy, method, args);

        if(method.getDeclaringClass() == Object.class)
            return method.invoke(this, args);

        InvocationContext ctx = createInvocationContext(proxy, method, args);

        return dispatcher.dispatch(ctx);
    }

    protected InvocationContext createInvocationContext(Object proxy, Method method, Object[] args) {
        return new InvocationContext(
                proxy, method, args, adapter, boundType
        );
    }
}
