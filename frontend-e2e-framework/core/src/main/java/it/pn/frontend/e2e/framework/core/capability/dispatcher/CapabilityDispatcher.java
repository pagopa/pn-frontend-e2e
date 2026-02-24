package it.pn.frontend.e2e.framework.core.capability.dispatcher;

import it.pn.frontend.e2e.framework.core.binder.invocation_handler.context.BaseInvocationContext;
import it.pn.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class CapabilityDispatcher<Context extends BaseInvocationContext> implements ICapabilityDispatcher<Context> {
    private final List<ICapabilityHandler<Context>> handlers;

    @Override
    public Object dispatch(Context ctx) {
        return handlers.stream()
                .filter(h -> h.canHandle(ctx))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("No handler for " + ctx.getClass().getSimpleName())
                )
                .handle(ctx);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setHandlers(List<ICapabilityHandler<?>> newHandlers) {
        handlers.clear();
        handlers.addAll(
                newHandlers.stream()
                        .map(h -> (ICapabilityHandler<Context>) h)
                        .toList()
        );
    }
}
