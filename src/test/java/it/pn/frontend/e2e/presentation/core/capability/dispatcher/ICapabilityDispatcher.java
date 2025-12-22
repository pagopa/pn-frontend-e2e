package it.pn.frontend.e2e.presentation.core.capability.dispatcher;

import it.pn.frontend.e2e.presentation.core.binder.invocation_handler.context.BaseInvocationContext;
import it.pn.frontend.e2e.presentation.core.capability.handler.ICapabilityHandler;

import java.util.List;

public interface ICapabilityDispatcher<Context extends BaseInvocationContext> {
    Object dispatch(Context ctx);
    void setHandlers(List<ICapabilityHandler<?>> newHandlers);
}
