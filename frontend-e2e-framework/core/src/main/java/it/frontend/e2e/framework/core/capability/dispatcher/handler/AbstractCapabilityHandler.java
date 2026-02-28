package it.frontend.e2e.framework.core.capability.dispatcher.handler;

import it.frontend.e2e.framework.core.capability.Capability;

import java.lang.reflect.Method;

public abstract class AbstractCapabilityHandler<C extends Capability> implements ICapabilityHandler {

    protected final Class<C> capabilityClass;

    protected AbstractCapabilityHandler(Class<C> capabilityClass) {
        this.capabilityClass = capabilityClass;
    }

    @Override
    public boolean canHandle(Method method) {
        Class<?> dc = method.getDeclaringClass();
        return capabilityClass.isAssignableFrom(dc) || dc.isAssignableFrom(capabilityClass);
    }
}
