package it.frontend.e2e.framework.core.capability;

import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;

import java.util.Optional;

public interface Gettable<S extends Selector, L extends Location, E extends AbstractPresentationElement<S,L>> {
    Optional<E> get();
}
