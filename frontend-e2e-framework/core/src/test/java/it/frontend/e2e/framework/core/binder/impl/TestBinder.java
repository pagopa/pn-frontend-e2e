package it.frontend.e2e.framework.core.binder.impl;

import it.frontend.e2e.framework.core.binder.Binder;
import it.frontend.e2e.framework.core.binder.BinderInvocationHandler;
import it.frontend.e2e.framework.core.model.TestElement;
import it.frontend.e2e.framework.core.model.TestLocation;
import it.frontend.e2e.framework.core.model.TestSelector;

import java.lang.reflect.InvocationHandler;

public class TestBinder extends Binder<TestSelector, TestLocation, TestElement> {

    public TestBinder(BinderInvocationHandler handler) {
        super(handler);
    }
}
