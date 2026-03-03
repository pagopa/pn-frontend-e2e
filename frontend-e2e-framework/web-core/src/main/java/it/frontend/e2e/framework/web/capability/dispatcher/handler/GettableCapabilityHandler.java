package it.frontend.e2e.framework.web.capability.dispatcher.handler;

import it.frontend.e2e.framework.core.capability.dispatcher.handler.AbstractCapabilityHandler;
import it.frontend.e2e.framework.web.capability.core.Gettable;

import java.lang.reflect.Method;

public class GettableCapabilityHandler extends AbstractCapabilityHandler<Gettable> {

    public GettableCapabilityHandler() {
        // Tipo generico <Gettable> viene estratto automaticamente
    }

    @Override
    public <T> T handle(Method method, Object[] args, String selector) {
        return null;
    }
}
