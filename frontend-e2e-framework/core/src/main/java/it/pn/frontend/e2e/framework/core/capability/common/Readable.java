package it.pn.frontend.e2e.framework.core.capability.common;

import it.pn.frontend.e2e.framework.core.adapter.model.PresentationElement;

public interface Readable {
    <T extends PresentationElement> T get();
}
