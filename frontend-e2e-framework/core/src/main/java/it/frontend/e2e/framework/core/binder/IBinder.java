package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.core.capability.Capability;
import it.frontend.e2e.framework.core.capability.core.Gettable;
import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;

public interface IBinder {
    <T extends Capability> T bind(Class<T> type);
}
