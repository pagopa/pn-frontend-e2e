package it.frontend.e2e.framework.core.capability.dispatcher.handler;

import it.frontend.e2e.framework.core.capability.Capability;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;

import java.lang.reflect.Method;

public abstract class AbstractCapabilityHandler<C extends Capability> implements ICapabilityHandler {

    protected final Class<C> capabilityClass;

    protected AbstractCapabilityHandler(Class<C> capabilityClass) {
        this.capabilityClass = capabilityClass;
    }

    @Override
    public boolean canHandle(Method method) {
        return method.getDeclaringClass().equals(capabilityClass);
    }
}
