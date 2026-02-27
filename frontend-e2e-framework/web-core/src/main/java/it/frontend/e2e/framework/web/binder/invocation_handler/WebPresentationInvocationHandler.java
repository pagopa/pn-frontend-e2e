package it.frontend.e2e.framework.web.binder.invocation_handler;

import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.frontend.e2e.framework.core.presentation_binder.AbstractBinderInvocationHandler;
import it.frontend.e2e.framework.web.adapter.model.WebPresentationElement;
import it.frontend.e2e.framework.web.adapter.model.locator.WebLocation;
import it.frontend.e2e.framework.web.adapter.model.selector.WebSelector;

public class WebPresentationInvocationHandler extends AbstractBinderInvocationHandler {

    protected WebPresentationInvocationHandler(ICapabilityDispatcher dispatcher) {
        super(dispatcher);
    }
}

