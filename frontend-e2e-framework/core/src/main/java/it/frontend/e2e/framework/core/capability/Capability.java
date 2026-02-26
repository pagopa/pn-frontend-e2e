package it.frontend.e2e.framework.core.capability;

import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;

public interface Capability<S extends Selector, L extends Location, E extends AbstractPresentationElement<S,L>> {
    E get();
}
