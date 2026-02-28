package it.frontend.e2e.framework.core.capability.dispatcher.handler;

import java.lang.reflect.Method;

public interface ICapabilityHandler {
    boolean canHandle(Method method);
    <T> T handle(Method method, Object[] args);
}
