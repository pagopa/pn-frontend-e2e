package it.frontend.e2e.framework.web.capability.dispatcher.handler;

import it.frontend.e2e.framework.core.capability.dispatcher.handler.AbstractCapabilityHandler;
import it.frontend.e2e.framework.web.capability.core.Readable;

import java.lang.reflect.Method;

public class ReadableCapabilityHandler extends AbstractCapabilityHandler<Readable> {

    protected ReadableCapabilityHandler() {
        super(Readable.class);
    }

    @Override
    public <T> T handle(Method method) {
        return null;
    }
}
