package it.frontend.e2e.framework.core.capability.handler;

import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;
import it.frontend.e2e.framework.core.capability.Capability;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

public abstract class AbstractCapabilityHandler<
        S extends Selector,
        L extends Location,
        E extends AbstractPresentationElement<S,L>,
        C extends Capability<S, L, E>
        > implements ICapabilityHandler {

    protected final C capabilityClass;

    protected AbstractCapabilityHandler(C capabilityClass) {
        this.capabilityClass = capabilityClass;
    }

    @Override
    public boolean canHandle(Method method) {
        return method.getDeclaringClass().equals(capabilityClass.getClass());
    }
}
