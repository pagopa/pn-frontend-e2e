package it.frontend.e2e.framework.core.capability.dispatcher.impl;

import it.frontend.e2e.framework.core.capability.dispatcher.CapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;
import it.frontend.e2e.framework.core.capability.handler.TestCapabilityHandler;

import java.util.List;

public class TestCapabilityDispatcher extends CapabilityDispatcher {

    public static final List<ICapabilityHandler> defaultHandlers = List.of(
            new TestCapabilityHandler()
    );

    public TestCapabilityDispatcher(List<ICapabilityHandler> handlers) {
        super(handlers);
    }

    public TestCapabilityDispatcher() {
        super(defaultHandlers);
    }

}