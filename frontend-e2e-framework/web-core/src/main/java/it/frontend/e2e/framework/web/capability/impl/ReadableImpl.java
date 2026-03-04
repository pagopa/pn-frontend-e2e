package it.frontend.e2e.framework.web.capability.impl;

import it.frontend.e2e.framework.core.assertion.AssertionAction;
import it.frontend.e2e.framework.web.capability.core.Readable;
import it.frontend.e2e.framework.web.model.WebPresentationElement;

public class ReadableImpl<T> implements Readable<T> {

    @Override
    public T read() {
        return null;
    }

    @Override
    public T readAndAssert() {
        return null;
    }

    @Override
    public T readAndAssert(AssertionAction<WebPresentationElement> assertionAction) {
        return null;
    }
}
