package it.frontend.e2e.framework.core.capability.handler;

import it.frontend.e2e.framework.core.capability.core.TestCapability;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.AbstractCapabilityHandler;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

public class TestCapabilityHandler extends AbstractCapabilityHandler<TestCapability> {

    public TestCapabilityHandler() {
        super(TestCapability.class);
    }

    @Override
    public <T> T handle(Method method, Object[] args) {

        return switch (method.getName()) {
            case "action" -> {
                System.out.println("Action method called");
                yield null;
            }
            case "getList" -> {
                System.out.println("getList method called");
                yield (T) List.of();
            }
            case "getOptional" -> {
                System.out.println("getOptional method called");
                yield (T) Optional.empty();
            }
            default -> throw new UnsupportedOperationException("Method not supported: " + method.getName());
        };
    }
}