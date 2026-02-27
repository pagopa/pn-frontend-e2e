package it.frontend.e2e.framework.core.capability.dispatcher.impl;

import it.frontend.e2e.framework.core.capability.dispatcher.DefaultCapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;
import it.frontend.e2e.framework.core.capability.handler.TestCapabilityHandler;

import java.util.List;

public class TestDefaultCapabilityDispatcher extends DefaultCapabilityDispatcher {

    public static final List<ICapabilityHandler> defaultHandlers = List.of(
            new TestCapabilityHandler()
    );

    public TestDefaultCapabilityDispatcher(List<ICapabilityHandler> handlers) {
        super(handlers);
    }

    public TestDefaultCapabilityDispatcher() {
        super(defaultHandlers);
    }

}