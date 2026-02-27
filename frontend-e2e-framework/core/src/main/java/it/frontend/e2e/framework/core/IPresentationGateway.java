package it.frontend.e2e.framework.core;

import it.frontend.e2e.framework.core.adapter.IPresentationApiAdapter;
import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;
import it.frontend.e2e.framework.core.binder.IBinder;

public interface IPresentationGateway<E extends AbstractPresentationElement<S,L>, S extends Selector, L extends Location>
        extends IPresentationApiAdapter<E, S, L>, IBinder {
}
