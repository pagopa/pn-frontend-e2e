package it.pn.frontend.e2e.presentation.core.binder.invocation_handler.model;

import it.pn.frontend.e2e.presentation.core.adapter.IPresentationApiAdapter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

@RequiredArgsConstructor
@Getter
public class InvocationContext{
    protected final Object proxy;
    protected final Method method;
    protected final Object[] args;
    protected final IPresentationApiAdapter adapter;
    protected final Class<?> boundType;
}
