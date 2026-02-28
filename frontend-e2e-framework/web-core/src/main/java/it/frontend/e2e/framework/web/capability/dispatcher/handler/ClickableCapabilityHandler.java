package it.frontend.e2e.framework.web.capability.dispatcher.handler;

import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.AbstractCapabilityHandler;
import it.frontend.e2e.framework.web.capability.core.Gettable;

import java.lang.reflect.Method;

public class ClickableCapabilityHandler extends AbstractCapabilityHandler<Clickable> {

    public ClickableCapabilityHandler() {
        super(Clickable.class);
    }

    @Override
    public <T> T handle(Method method, Object[] args) {
        switch (method.getName()) {
            case "click" -> {
                System.out.println("Clicked!");
                return null;
            }
            default -> throw new UnsupportedOperationException("Method " + method.getName() + " is not supported by " + capabilityClass.getSimpleName());
        }
    }
}
