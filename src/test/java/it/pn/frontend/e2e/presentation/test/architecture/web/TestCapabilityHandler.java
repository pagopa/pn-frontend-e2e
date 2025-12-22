package it.pn.frontend.e2e.presentation.test.architecture.web;


import it.pn.frontend.e2e.presentation.core.capability.handler.ICapabilityHandler;
import it.pn.frontend.e2e.presentation.web.binder.invocation_handler.context.WebInvocationContext;

import java.lang.reflect.Method;

public class TestCapabilityHandler implements ICapabilityHandler<WebInvocationContext> {

    public Method interceptedMethod;
    public Object[] interceptedArgs;

    @Override
    public boolean canHandle(WebInvocationContext context) {
        return context.getMethod().getName().equals("sayHello");
    }

    @Override
    public Object handle(WebInvocationContext ctx) {
        this.interceptedMethod = ctx.getMethod();
        this.interceptedArgs = ctx.getArgs();
        return "Hello " + ctx.getArgs()[0];
    }
}

