package it.pn.frontend.e2e.presentation.core.capability.dispatcher;

import it.pn.frontend.e2e.presentation.core.binder.invocation_handler.model.InvocationContext;
import it.pn.frontend.e2e.presentation.core.capability.handler.ICapabilityHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class CapabilityDispatcher implements ICapabilityDispatcher<InvocationContext> {
    private final List<ICapabilityHandler<?>> handlers;

    public Object dispatch(InvocationContext ctx) {

        for(ICapabilityHandler<?> handler : handlers) {
            if(handler.support(ctx.getMethod(), ctx.getBoundType()))
                return invokedTyped(handler, ctx);
        }

        throw new UnsupportedOperationException("Nessun handler per " + ctx.getMethod().getName());
    }

    public void setHandlers(List<ICapabilityHandler<?>> newHandlers) {
        handlers.clear();
        handlers.addAll(newHandlers);
    }

    @SuppressWarnings("unchecked")
    private <R> R invokedTyped(ICapabilityHandler<?> handler, InvocationContext ctx) {

        R result = (R) handler.handle(ctx);

        Class<?> expected = ctx.getMethod().getReturnType();
        if(result != null && !expected.isInstance(result))
            throw new ClassCastException(
                    "Handler " + handler.getClass().getSimpleName()
                    + " ha restituito " + result.getClass().getSimpleName()
                    + " ma il metodo richiede " + expected.getSimpleName()
            );

        return result;
    }
}
