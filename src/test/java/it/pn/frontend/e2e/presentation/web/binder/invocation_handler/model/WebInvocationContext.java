package it.pn.frontend.e2e.presentation.web.binder.invocation_handler.model;

import it.pn.frontend.e2e.presentation.core.adapter.IPresentationApiAdapter;
import it.pn.frontend.e2e.presentation.core.binder.invocation_handler.model.InvocationContext;
import lombok.Getter;

import java.lang.reflect.Method;

@Getter
public class WebInvocationContext extends InvocationContext {

    protected final String selector;

    public WebInvocationContext(Object proxy, Method method, Object[] args, IPresentationApiAdapter adapter, Class<?> boundType, String selector) {
        super(proxy, method, args, adapter, boundType);
        this.selector = selector;
    }

}
