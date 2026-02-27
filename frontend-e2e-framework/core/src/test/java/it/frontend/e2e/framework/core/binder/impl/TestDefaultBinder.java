package it.frontend.e2e.framework.core.binder.impl;

import it.frontend.e2e.framework.core.binder.DefaultBinder;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;
import it.frontend.e2e.framework.core.capability.dispatcher.impl.TestDefaultCapabilityDispatcher;
import it.frontend.e2e.framework.core.model.TestElement;
import it.frontend.e2e.framework.core.model.TestLocation;
import it.frontend.e2e.framework.core.model.TestSelector;

import java.util.List;

public class TestDefaultBinder extends DefaultBinder<TestSelector, TestLocation, TestElement> {

    public TestDefaultBinder(List<ICapabilityHandler> handlers) {
        super(handlers);
    }

    public TestDefaultBinder() {
        super(TestDefaultCapabilityDispatcher.defaultHandlers);
    }
}
