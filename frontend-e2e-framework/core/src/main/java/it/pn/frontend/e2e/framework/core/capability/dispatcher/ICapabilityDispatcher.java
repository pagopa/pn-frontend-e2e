package it.pn.frontend.e2e.framework.core.capability.dispatcher;

import it.pn.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.pn.frontend.e2e.framework.core.model.Location;
import it.pn.frontend.e2e.framework.core.model.Selector;

import java.lang.reflect.Method;
import java.util.Optional;

public interface ICapabilityDispatcher<S extends Selector, L extends Location, E extends AbstractPresentationElement<S,L>> {
    Optional<E> dispatch(Method method);
}
