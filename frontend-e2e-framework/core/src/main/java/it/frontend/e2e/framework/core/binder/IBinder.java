package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.core.capability.Gettable;
import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;

public interface IBinder<S extends Selector, L extends Location, E extends AbstractPresentationElement<S,L>> {
    <T extends Gettable<S,L,E>> T bind(Class<T> type);
}
