package it.pn.frontend.e2e.presentation.web.binder.invocation_handler;

import it.pn.frontend.e2e.presentation.core.binder.invocation_handler.PresentationInvocationHandler;
import it.pn.frontend.e2e.presentation.core.binder.invocation_handler.model.InvocationContext;
import it.pn.frontend.e2e.presentation.core.capability.dispatcher.CapabilityDispatcher;
import it.pn.frontend.e2e.presentation.web.adapter.IWebPresentationApiAdapter;
import it.pn.frontend.e2e.presentation.web.binder.invocation_handler.model.WebInvocationContext;

import java.lang.reflect.Method;

public class WebPresentationInvocationHandler extends PresentationInvocationHandler<IWebPresentationApiAdapter> {
    private final String selector;

    public WebPresentationInvocationHandler(CapabilityDispatcher dispatcher, IWebPresentationApiAdapter adapter, Class<?> boundType, String selector) {
        super(dispatcher, adapter, boundType);
        this.selector = selector;
    }

    @Override
    protected InvocationContext createInvocationContext(Object proxy, Method method, Object[] args) {
        return new WebInvocationContext(proxy, method, args, adapter, boundType, selector);
    }
}
