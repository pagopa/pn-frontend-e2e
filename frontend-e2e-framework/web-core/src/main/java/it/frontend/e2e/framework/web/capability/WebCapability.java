package it.frontend.e2e.framework.web.capability;

import it.frontend.e2e.framework.core.capability.Capability;
import it.frontend.e2e.framework.web.adapter.model.WebPresentationElement;
import it.frontend.e2e.framework.web.adapter.model.locator.WebLocation;
import it.frontend.e2e.framework.web.adapter.model.selector.WebSelector;

public interface WebCapability extends Capability<WebSelector, WebLocation, WebPresentationElement> {
}
