package it.pn.frontend.e2e.framework.core.capability.dispatcher;

import it.pn.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.pn.frontend.e2e.framework.core.model.Location;
import it.pn.frontend.e2e.framework.core.model.Selector;
import it.pn.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class CapabilityDispatcher<S extends Selector, L extends Location, E extends AbstractPresentationElement<S,L>> implements ICapabilityDispatcher<S,L,E> {
    private final List<ICapabilityHandler<S, L, E>> handlers;

    @Override
    public Optional<E> dispatch(Method method) {
        ICapabilityHandler<S, L, E> capabilityHandler =
                        handlers
                        .stream()
                        .filter(h -> h.canHandle(method))
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalStateException("No handler for " + method.getDeclaringClass().getSimpleName())
                        );

        S selector = getSelector(method);
        L location = getLocation(method);

        return capabilityHandler.handle(selector, location);
    }

    /**
     * Hook method – implemented by domain-specific subclasses (web, mobile, mock).
     */
    protected abstract S getSelector(Method method);

    /**
     * Hook method – implemented by domain-specific subclasses (web, mobile, mock).
     */
    protected abstract L getLocation(Method method);
}
