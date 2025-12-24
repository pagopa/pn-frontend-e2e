package it.pn.frontend.e2e.framework.web.binder;

import it.pn.frontend.e2e.framework.core.binder.AbstractPresentationBinder;
import it.pn.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.pn.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.pn.frontend.e2e.framework.web.binder.invocation_handler.WebPresentationInvocationHandler;
import it.pn.frontend.e2e.framework.web.binder.invocation_handler.context.WebInvocationContext;

import java.lang.reflect.InvocationHandler;

public class WebPresentationBinder extends AbstractPresentationBinder<IWebPresentationApiAdapter, WebInvocationContext> {

    public WebPresentationBinder(IWebPresentationApiAdapter adapter, ICapabilityDispatcher<WebInvocationContext> dispatcher) {
        super(adapter, dispatcher);
    }

    @Override
    protected InvocationHandler createInvocationHandler(Class<?> boundType) {
        return new WebPresentationInvocationHandler(
                dispatcher,
                adapter,
                boundType,
                ""
        );
    }
}
