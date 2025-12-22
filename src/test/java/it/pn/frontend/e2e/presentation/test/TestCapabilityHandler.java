package it.pn.frontend.e2e.presentation.test;

import it.pn.frontend.e2e.presentation.core.binder.invocation_handler.model.InvocationContext;
import it.pn.frontend.e2e.presentation.core.capability.handler.ICapabilityHandler;

import java.lang.reflect.Method;

class TestCapabilityHandler implements ICapabilityHandler<String> {

    Method interceptedMethod;
    Object[] interceptedArgs;

    @Override
    public boolean support(Method method, Class<?> boundType) {
        return method.getName().equals("sayHello");
    }

    @Override
    public String handle(InvocationContext ctx) {
        this.interceptedMethod = ctx.getMethod();
        this.interceptedArgs = ctx.getArgs();
        return "Hello " + ctx.getArgs()[0];
    }
}

