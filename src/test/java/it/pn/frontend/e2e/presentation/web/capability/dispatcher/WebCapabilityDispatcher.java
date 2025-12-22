package it.pn.frontend.e2e.presentation.web.capability.dispatcher;

import it.pn.frontend.e2e.presentation.core.capability.dispatcher.CapabilityDispatcher;
import it.pn.frontend.e2e.presentation.core.capability.dispatcher.ICapabilityDispatcher;
import it.pn.frontend.e2e.presentation.core.capability.handler.ICapabilityHandler;
import it.pn.frontend.e2e.presentation.web.binder.invocation_handler.model.WebInvocationContext;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WebCapabilityDispatcher implements ICapabilityDispatcher<WebInvocationContext> {
    private final CapabilityDispatcher dispatcher;

    @Override
    public Object dispatch(WebInvocationContext ctx) {
        return dispatcher.dispatch(ctx);
    }

    @Override
    public void setHandlers(List<ICapabilityHandler<?>> newHandlers) {
        dispatcher.setHandlers(newHandlers);
    }
}
