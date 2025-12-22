package it.pn.frontend.e2e.presentation.core.capability.handler;

import it.pn.frontend.e2e.presentation.core.binder.invocation_handler.context.BaseInvocationContext;

public interface ICapabilityHandler<Context extends BaseInvocationContext> {
    boolean canHandle(Context context);
    Object handle(Context context);
}
