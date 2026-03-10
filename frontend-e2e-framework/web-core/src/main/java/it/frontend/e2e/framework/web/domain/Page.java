package it.frontend.e2e.framework.web.domain;

import it.frontend.e2e.framework.core.capability.Capability;
import it.frontend.e2e.framework.core.model.DomainElement;

public interface Page extends DomainElement, Capability {
    default void assertLoaded() {
        throw new UnsupportedOperationException("Method assertLoaded() not implemented for " + this.getClass().getName());
    }
}
