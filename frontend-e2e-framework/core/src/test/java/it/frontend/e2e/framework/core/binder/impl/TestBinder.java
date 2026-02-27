package it.frontend.e2e.framework.core.binder.impl;

import it.frontend.e2e.framework.core.binder.AbstractBinder;
import it.frontend.e2e.framework.core.model.TestElement;
import it.frontend.e2e.framework.core.model.TestLocation;
import it.frontend.e2e.framework.core.model.TestSelector;

import java.lang.reflect.InvocationHandler;

public class TestBinder extends AbstractBinder<TestSelector, TestLocation, TestElement> {

    public TestBinder(InvocationHandler handler) {
        super(handler);
    }
}
