package it.pn.frontend.e2e.presentation.core.binder.invocation_handler.context;

import it.pn.frontend.e2e.presentation.core.adapter.IPresentationApiAdapter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

@RequiredArgsConstructor
@Getter
public abstract class BaseInvocationContext {
    private final Object proxy;
    private final Method method;
    private final Object[] args;
    private final IPresentationApiAdapter adapter;
    private final Class<?> boundType;
}
