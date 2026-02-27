package it.frontend.e2e.framework.core.capability.core;

import it.frontend.e2e.framework.core.assertion.AssertionAction;
import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.Location;
import it.frontend.e2e.framework.core.model.Selector;

public interface Readable<T, S extends Selector, L extends Location, E extends AbstractPresentationElement<S,L>> extends Gettable<S,L,E> {
    T read();
    T readAndAssert();
    T readAndAssert(AssertionAction<E> assertionAction);
}
