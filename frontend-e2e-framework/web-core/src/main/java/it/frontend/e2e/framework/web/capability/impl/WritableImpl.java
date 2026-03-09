package it.frontend.e2e.framework.web.capability.impl;

import it.frontend.e2e.framework.core.assertion.AssertionAction;
import it.frontend.e2e.framework.core.capability.context.CapabilityContext;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.capability.core.Writable;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.XPathSelector;

public class WritableImpl<T> extends AbstractCapabilityImpl implements Writable<T> {

    public WritableImpl(IWebPresentationApiAdapter adapter) {
        super(adapter);
    }

    @Override
    public void write(T value) {
        XPathSelector xPathSelector = XPathSelector.of(CapabilityContext.selector());
        adapter.sendText(xPathSelector, value.toString());
    }

    @Override
    public void writeAndAssert(T value) {
        XPathSelector xPathSelector = XPathSelector.of(CapabilityContext.selector());
        adapter.sendTextAndAssert(xPathSelector, value.toString());
    }

    @Override
    public void writeAndAssert(T value, AssertionAction<WebPresentationElement> assertionAction) {
        XPathSelector xPathSelector = XPathSelector.of(CapabilityContext.selector());
        adapter.sendTextAndAssert(xPathSelector, value.toString(), assertionAction);
    }
}
