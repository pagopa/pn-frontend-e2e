package it.frontend.e2e.framework.web.capability.impl;

import it.frontend.e2e.framework.core.assertion.AssertionAction;
import it.frontend.e2e.framework.core.capability.context.CapabilityContext;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.capability.core.Writable;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.WebSelector;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WritableImpl<T> implements Writable<T> {

    private final IWebPresentationApiAdapter adapter;

    @Override
    public void write(T value) {
        WebSelector webSelector = WebSelector.of(CapabilityContext.selector());
        adapter.waitForElement(webSelector, 10);
        adapter.sendText(webSelector, value.toString());
    }

    @Override
    public void writeAndAssert(T value) {
        WebSelector webSelector = WebSelector.of(CapabilityContext.selector());
        adapter.waitForElement(webSelector, 10);
        adapter.sendTextAndAssert(webSelector, value.toString());
    }

    @Override
    public void writeAndAssert(T value, AssertionAction<WebPresentationElement> assertionAction) {
        WebSelector webSelector = WebSelector.of(CapabilityContext.selector());
        adapter.waitForElement(webSelector, 10);
        adapter.sendTextAndAssert(webSelector, value.toString(), assertionAction);
    }
}
