package it.frontend.e2e.framework.core.capability.handler;

import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;
import it.frontend.e2e.framework.core.capability.Gettable;

import java.lang.reflect.Method;

public abstract class AbstractCapabilityHandler<
        S extends Selector,
        L extends Location,
        E extends AbstractPresentationElement<S,L>,
        C extends Gettable<S, L, E>
        > implements ICapabilityHandler {

    protected final Class<C> capabilityClass;

    protected AbstractCapabilityHandler(Class<C> capabilityClass) {
        this.capabilityClass = capabilityClass;
    }

    @Override
    public boolean canHandle(Method method) {
        return method.getDeclaringClass().equals(capabilityClass);
    }
}
