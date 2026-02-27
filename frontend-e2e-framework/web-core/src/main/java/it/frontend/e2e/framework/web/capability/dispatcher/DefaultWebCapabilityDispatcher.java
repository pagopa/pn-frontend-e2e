package it.frontend.e2e.framework.web.capability.dispatcher;

import it.frontend.e2e.framework.core.capability.dispatcher.DefaultCapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;

import java.util.List;

public class DefaultWebCapabilityDispatcher extends DefaultCapabilityDispatcher {
    public DefaultWebCapabilityDispatcher(List<ICapabilityHandler> handlers) {
        super(handlers);
    }
}
