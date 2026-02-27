package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DefaultBinderInvocationHandler implements InvocationHandler {

    protected final ICapabilityDispatcher dispatcher;

    protected DefaultBinderInvocationHandler(ICapabilityDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.isDefault())
            return InvocationHandler.invokeDefault(proxy, method, args);

        if (method.getDeclaringClass() == Object.class) {
            return switch (method.getName()) {
                case "equals" -> proxy == args[0];
                case "hashCode" -> System.identityHashCode(proxy);
                case "toString" -> proxy.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(proxy));
                default -> method.invoke(this, args);
            };
        }

        return dispatcher.dispatch(method);
    }
}
