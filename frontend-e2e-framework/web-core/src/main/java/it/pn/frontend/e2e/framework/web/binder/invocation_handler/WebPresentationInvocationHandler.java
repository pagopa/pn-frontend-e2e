package it.pn.frontend.e2e.framework.web.binder.invocation_handler;

import it.pn.frontend.e2e.framework.core.binder.invocation_handler.AbstractPresentationInvocationHandler;
import it.pn.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.pn.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.pn.frontend.e2e.framework.web.binder.invocation_handler.context.WebInvocationContext;

import java.lang.reflect.Method;

public class WebPresentationInvocationHandler extends AbstractPresentationInvocationHandler<WebInvocationContext> {

    private final String selector;

    public WebPresentationInvocationHandler(ICapabilityDispatcher dispatcher, IWebPresentationApiAdapter adapter, Class<?> boundType, String selector) {
        super(dispatcher, adapter, boundType);
        this.selector = selector;
    }

    @Override
    protected WebInvocationContext createContext(Object proxy, Method method, Object[] args) {
        return new WebInvocationContext(proxy, method, args, adapter, boundType, selector);
    }
}

