package it.frontend.e2e.framework.core.presentation_binder;

import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;

public interface IBinder<S extends Selector, L extends Location> {
    <T extends AbstractPresentationElement<S,L>> T bind(Class<T> type);
}
