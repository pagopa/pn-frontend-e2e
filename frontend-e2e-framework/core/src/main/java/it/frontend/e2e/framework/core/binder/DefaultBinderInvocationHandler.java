package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.core.capability.Capability;
import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.frontend.e2e.framework.core.model.DomainElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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

        // RICORSIONE: se il return type è un DomainElement -> nuovo proxy dello stesso framework
        Class<?> rt = method.getReturnType();

        if (DomainElement.class.isAssignableFrom(rt)) {
            return Proxy.newProxyInstance(
                    rt.getClassLoader(),
                    new Class<?>[]{rt},
                    this
            );
        }

        if (Capability.class.isAssignableFrom(rt)) {
            if (!rt.isInterface()) {
                throw new IllegalStateException("Capability must be an interface: " + rt.getName());
            }

            return Proxy.newProxyInstance(
                    rt.getClassLoader(),
                    new Class<?>[]{rt},
                    this
            );
        }

        return dispatcher.dispatch(method, args);
    }
}
