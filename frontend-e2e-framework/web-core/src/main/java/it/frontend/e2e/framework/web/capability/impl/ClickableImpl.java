package it.frontend.e2e.framework.web.capability.impl;

import it.frontend.e2e.framework.core.capability.context.CapabilityContext;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.model.WebSelector;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClickableImpl implements Clickable {

    private final IWebPresentationApiAdapter adapter;

    @Override
    public void click() {
        WebSelector webSelector = WebSelector.of(CapabilityContext.selector());
        adapter.waitForElement(webSelector, 10);
        adapter.click(webSelector);
    }
}
