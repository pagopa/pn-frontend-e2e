package it.pn.frontend.e2e.framework.core.binder.invocation_handler.context;

import lombok.Getter;

import java.lang.reflect.Method;

@Getter
public abstract class BaseInvocationContext {
    private final Method method;

    protected BaseInvocationContext(Method method) {
        this.method = method;
    }
}
