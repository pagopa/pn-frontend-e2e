package it.pn.frontend.e2e.presentation.web.binder;

import it.pn.frontend.e2e.presentation.core.binder.AbstractPresentationBinder;
import it.pn.frontend.e2e.presentation.core.capability.dispatcher.ICapabilityDispatcher;
import it.pn.frontend.e2e.presentation.web.adapter.IWebPresentationApiAdapter;
import it.pn.frontend.e2e.presentation.web.binder.invocation_handler.WebPresentationInvocationHandler;
import it.pn.frontend.e2e.presentation.web.binder.invocation_handler.context.WebInvocationContext;

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
