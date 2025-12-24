package it.pn.frontend.e2e.framework.web.binder.invocation_handler.context;

import it.pn.frontend.e2e.framework.core.adapter.IPresentationApiAdapter;
import it.pn.frontend.e2e.framework.core.binder.invocation_handler.context.BaseInvocationContext;
import lombok.Getter;

import java.lang.reflect.Method;


@Getter
public class WebInvocationContext extends BaseInvocationContext {
    protected final String selector;

    public WebInvocationContext(Object proxy, Method method, Object[] args, IPresentationApiAdapter adapter, Class<?> boundType, String selector) {
        super(proxy, method, args, adapter, boundType);
        this.selector = selector;
    }
}
