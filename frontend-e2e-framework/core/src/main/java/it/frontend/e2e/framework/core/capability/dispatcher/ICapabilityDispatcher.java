package it.frontend.e2e.framework.core.capability.dispatcher;

import java.lang.reflect.Method;

public interface ICapabilityDispatcher {
    <T> T dispatch(Method method, Object[] args);
}
