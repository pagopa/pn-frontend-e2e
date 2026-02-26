package it.pn.frontend.e2e.framework.core.presentation_binder;

import it.pn.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.pn.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.pn.frontend.e2e.framework.core.model.Location;
import it.pn.frontend.e2e.framework.core.model.Selector;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class AbstractBinderInvocationHandler<S extends Selector, L extends Location, E extends AbstractPresentationElement<S,L>> implements InvocationHandler {

    protected final ICapabilityDispatcher<S,L,E> dispatcher;

    protected AbstractBinderInvocationHandler(ICapabilityDispatcher<S,L,E> dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.isDefault())
            return InvocationHandler.invokeDefault(proxy, method, args);

        if (method.getDeclaringClass() == Object.class)
            return method.invoke(this, args);

        return dispatcher.dispatch(method);
    }
}
