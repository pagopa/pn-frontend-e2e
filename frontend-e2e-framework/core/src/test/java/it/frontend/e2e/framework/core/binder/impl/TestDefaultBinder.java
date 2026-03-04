package it.frontend.e2e.framework.core.binder.impl;

import it.frontend.e2e.framework.core.binder.DefaultBinder;
import it.frontend.e2e.framework.core.capability.dispatcher.impl.TestDefaultCapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;

import java.util.List;

public class TestDefaultBinder extends DefaultBinder {

    public TestDefaultBinder(List<ICapabilityHandler> handlers) {
        super(handlers);
    }

    public TestDefaultBinder() {
        super(TestDefaultCapabilityDispatcher.defaultHandlers);
    }
}
