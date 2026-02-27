package it.frontend.e2e.framework.web.capability.dispatcher;

import it.frontend.e2e.framework.core.capability.dispatcher.AbstractCapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;

import java.util.List;

public class WebCapabilityDispatcher extends AbstractCapabilityDispatcher {
    public WebCapabilityDispatcher(List<ICapabilityHandler> handlers) {
        super(handlers);
    }
}
