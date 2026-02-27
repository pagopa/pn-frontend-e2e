package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.core.capability.Capability;
import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;

public interface IBinder<S extends Selector, L extends Location, E extends AbstractPresentationElement<S,L>> {
    <T extends Capability<S,L,E>> T bind(Class<T> type);
}
