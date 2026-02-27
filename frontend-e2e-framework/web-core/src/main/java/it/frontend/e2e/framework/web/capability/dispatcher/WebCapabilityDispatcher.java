package it.frontend.e2e.framework.web.capability.dispatcher;

import it.frontend.e2e.framework.core.capability.dispatcher.CapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;

import java.util.List;

public class WebCapabilityDispatcher extends CapabilityDispatcher {
    public WebCapabilityDispatcher(List<ICapabilityHandler> handlers) {
        super(handlers);
    }
}
