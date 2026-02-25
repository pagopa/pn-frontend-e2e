package it.pn.frontend.e2e.framework.core.binder.invocation_handler.context;

import it.pn.frontend.e2e.framework.core.adapter.model.selector.Selector;
import it.pn.frontend.e2e.framework.core.adapter.model.selector.SelectorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

@Getter
@RequiredArgsConstructor
public class BaseInvocationContext {
    private final Method method;
    private final Selector<? extends SelectorType> selector;
}
