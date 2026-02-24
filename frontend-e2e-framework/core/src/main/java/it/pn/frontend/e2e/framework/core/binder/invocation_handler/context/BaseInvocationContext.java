package it.pn.frontend.e2e.framework.core.binder.invocation_handler.context;

import it.pn.frontend.e2e.framework.core.adapter.IPresentationApiAdapter;
import lombok.Getter;

import java.lang.reflect.Method;

@Getter
public abstract class BaseInvocationContext {
    private final Object proxy;
    private final Method method;
    private final Object[] args;
    private final IPresentationApiAdapter adapter;
    private final Class<?> boundType;

    protected BaseInvocationContext(Object proxy, Method method, Object[] args, IPresentationApiAdapter adapter, Class<?> boundType) {
        this.proxy = proxy;
        this.method = method;
        this.args = args;
        this.adapter = adapter;
        this.boundType = boundType;
    }
}
