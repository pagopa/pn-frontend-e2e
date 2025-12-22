package it.pn.frontend.e2e.presentation.core.capability.handler;

import it.pn.frontend.e2e.presentation.core.binder.invocation_handler.model.InvocationContext;

import java.lang.reflect.Method;

public interface ICapabilityHandler<R> {
    boolean support(Method method, Class<?> boundType);
    R handle(InvocationContext ctx);
}
