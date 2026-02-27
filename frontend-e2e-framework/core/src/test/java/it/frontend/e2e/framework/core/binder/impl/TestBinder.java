package it.frontend.e2e.framework.core.binder.impl;

import it.frontend.e2e.framework.core.capability.core.Gettable;
import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.dispatcher.impl.TestCapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;
import it.frontend.e2e.framework.core.capability.handler.TestCapabilityHandler;
import it.frontend.e2e.framework.core.model.TestElement;
import it.frontend.e2e.framework.core.model.TestLocation;
import it.frontend.e2e.framework.core.model.TestSelector;
import it.frontend.e2e.framework.core.binder.AbstractBinder;

import java.lang.reflect.InvocationHandler;
import java.util.List;

public class TestBinder extends AbstractBinder<TestSelector, TestLocation, TestElement> {

    private List<ICapabilityHandler> getHandlers() {
        return List.of(
                new TestCapabilityHandler()
        );
    }

    @Override
    protected <T extends Gettable<TestSelector, TestLocation, TestElement>> InvocationHandler getInvocationHandler(Class<T> boundType) {
        ICapabilityDispatcher dispatcher = new TestCapabilityDispatcher(getHandlers());
        return new TestInvocationHandler(dispatcher);
    }
}
