package it.frontend.e2e.framework.core.capability.dispatcher;

import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.List;

@Getter
public class DefaultCapabilityDispatcher implements ICapabilityDispatcher {
    protected final List<ICapabilityHandler> handlers;

    public DefaultCapabilityDispatcher(List<ICapabilityHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public <T> T dispatch(Method method, Object[] args, String selector) {
        return handlers.stream()
                .filter(h -> h.canHandle(method))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("No handler for " + method.getDeclaringClass().getSimpleName())
                )
                .handle(method, args);
    }
}
