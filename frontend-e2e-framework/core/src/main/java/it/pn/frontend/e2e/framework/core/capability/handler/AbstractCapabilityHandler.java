package it.pn.frontend.e2e.framework.core.capability.handler;

import it.pn.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.pn.frontend.e2e.framework.core.model.Location;
import it.pn.frontend.e2e.framework.core.model.Selector;
import it.pn.frontend.e2e.framework.core.capability.Capability;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public abstract class AbstractCapabilityHandler<S extends Selector, L extends Location, E extends AbstractPresentationElement<S,L>> implements ICapabilityHandler<S,L,E> {

    private final Class<? extends Capability<S,L,E>> capabilityClass;

    @Override
    public boolean canHandle(Method method) {
        return method.getDeclaringClass().equals(capabilityClass);
    }
}
