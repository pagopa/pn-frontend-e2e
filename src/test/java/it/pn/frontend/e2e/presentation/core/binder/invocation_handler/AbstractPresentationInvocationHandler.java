package it.pn.frontend.e2e.presentation.core.binder.invocation_handler;

import it.pn.frontend.e2e.presentation.core.adapter.IPresentationApiAdapter;
import it.pn.frontend.e2e.presentation.core.binder.invocation_handler.context.BaseInvocationContext;
import it.pn.frontend.e2e.presentation.core.capability.dispatcher.ICapabilityDispatcher;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public abstract class AbstractPresentationInvocationHandler<ApiAdapter extends IPresentationApiAdapter, Context extends BaseInvocationContext>
        implements InvocationHandler {

    protected final ICapabilityDispatcher<Context> dispatcher;
    protected final ApiAdapter adapter;
    protected final Class<?> boundType;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.isDefault())
            return InvocationHandler.invokeDefault(proxy, method, args);

        if (method.getDeclaringClass() == Object.class)
            return method.invoke(this, args);

        Context ctx = createContext(proxy, method, args);

        return dispatcher.dispatch(ctx);
    }

    /**
     * Hook method – implemented by domain-specific subclasses (web, mobile, mock).
     */
    protected abstract Context createContext(
            Object proxy,
            Method method,
            Object[] args
    );
}
