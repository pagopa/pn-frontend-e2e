package it.frontend.e2e.framework.web.capability.impl;

import it.frontend.e2e.framework.core.capability.context.CapabilityContext;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.core.model.selector.XPathSelector;

public class ClickableImpl extends AbstractCapabilityImpl implements Clickable {

    public ClickableImpl(IWebPresentationApiAdapter adapter) {
        super(adapter);
    }

    @Override
    public void click() {
        XPathSelector xPathSelector = XPathSelector.of(CapabilityContext.selector());
        adapter.waitForElement(xPathSelector, 10);
        adapter.click(xPathSelector);
    }
}
