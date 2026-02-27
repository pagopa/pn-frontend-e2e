package it.frontend.e2e.framework.core.capability.dispatcher.impl;

import it.frontend.e2e.framework.core.capability.dispatcher.AbstractCapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;

import java.util.List;

public class TestCapabilityDispatcher extends AbstractCapabilityDispatcher {

    public TestCapabilityDispatcher(List<ICapabilityHandler> handlers) {
        super(handlers);
    }
}