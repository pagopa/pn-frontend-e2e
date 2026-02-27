package it.frontend.e2e.framework.web.domain;

import it.frontend.e2e.framework.web.capability.WebCapability;

public interface DomNode extends WebCapability {
    boolean assertLoaded();
}
