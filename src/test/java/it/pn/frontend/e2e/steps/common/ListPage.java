package it.pn.frontend.e2e.steps.common;

import it.frontend.e2e.framework.core.capability.core.Clickable;

public interface ListPage {

    Clickable firstListItemButton();

    default void openFirstItem() {
        firstListItemButton().click();
    }
}