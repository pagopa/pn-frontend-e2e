package it.pn.frontend.e2e.framework.core.binder.invocation_handler.context;

import it.pn.frontend.e2e.framework.core.adapter.IPresentationApiAdapter;
import java.lang.reflect.Method;

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

    public IPresentationApiAdapter getAdapter() {
        return adapter;
    }

    public Object[] getArgs() {
        return args;
    }

    public Class<?> getBoundType() {
        return boundType;
    }

    public Method getMethod() {
        return method;
    }

    public Object getProxy() {
        return proxy;
    }
}
