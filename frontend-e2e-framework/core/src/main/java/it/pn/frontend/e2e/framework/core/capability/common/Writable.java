package it.pn.frontend.e2e.framework.core.capability.common;

import it.pn.frontend.e2e.framework.core.adapter.model.AssertionAction;
import it.pn.frontend.e2e.framework.core.adapter.model.PresentationElement;

public interface Writable {
    void write(String text);
    void writeAndAssert(String text);
    void writeAndAssert(String text, AssertionAction<? extends PresentationElement> assertionAction);
}
