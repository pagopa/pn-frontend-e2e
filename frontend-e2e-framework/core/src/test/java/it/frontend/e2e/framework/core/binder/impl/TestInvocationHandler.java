package it.frontend.e2e.framework.core.binder.impl;

import it.frontend.e2e.framework.core.binder.BinderInvocationHandler;
import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;

public class TestInvocationHandler extends BinderInvocationHandler {
    public TestInvocationHandler(ICapabilityDispatcher dispatcher) {
        super(dispatcher);
    }
}
