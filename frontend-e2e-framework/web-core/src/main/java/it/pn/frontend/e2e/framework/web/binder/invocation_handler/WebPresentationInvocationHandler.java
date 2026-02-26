package it.pn.frontend.e2e.framework.web.binder.invocation_handler;

import it.pn.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.pn.frontend.e2e.framework.core.presentation_binder.AbstractBinderInvocationHandler;
import it.pn.frontend.e2e.framework.web.adapter.model.WebPresentationElement;
import it.pn.frontend.e2e.framework.web.adapter.model.locator.WebLocation;
import it.pn.frontend.e2e.framework.web.adapter.model.selector.WebSelector;

public class WebPresentationInvocationHandler extends AbstractBinderInvocationHandler<WebSelector, WebLocation, WebPresentationElement> {

    protected WebPresentationInvocationHandler(ICapabilityDispatcher<WebSelector, WebLocation, WebPresentationElement> dispatcher) {
        super(dispatcher);
    }
}

