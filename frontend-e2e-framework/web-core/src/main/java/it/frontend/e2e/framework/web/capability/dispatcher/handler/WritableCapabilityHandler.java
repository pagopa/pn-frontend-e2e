package it.frontend.e2e.framework.web.capability.dispatcher.handler;

import it.frontend.e2e.framework.core.capability.dispatcher.handler.AbstractCapabilityHandler;
import it.frontend.e2e.framework.web.capability.core.Writable;

import java.lang.reflect.Method;

public class WritableCapabilityHandler extends AbstractCapabilityHandler<Writable> {

    public WritableCapabilityHandler() {
        super(Writable.class);
    }

    @Override
    public <T> T handle(Method method) {
        return null;
    }
}
