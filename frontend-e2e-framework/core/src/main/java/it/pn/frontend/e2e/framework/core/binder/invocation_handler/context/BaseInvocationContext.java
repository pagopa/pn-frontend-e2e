package it.pn.frontend.e2e.framework.core.binder.invocation_handler.context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

@Getter
@RequiredArgsConstructor
public class BaseInvocationContext {
    private final Method method;
}
