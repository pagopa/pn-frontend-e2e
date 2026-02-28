package it.pn.frontend.e2e.steps.send.login.page;

import it.frontend.e2e.framework.annotation.Selector;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.capability.core.Writable;
import it.frontend.e2e.framework.web.domain.Page;

public interface DashboardPartySelectionPage extends Page {

    @Selector("//*[@id=\"search\"]")
    Writable<String> searchInput();

    @Selector("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/div")
    Clickable comune();

    @Selector("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[3]/div/button")
    Clickable accediButton();

    default void selectComune(String comune) {
        searchInput().writeAndAssert(comune);
        this.comune().click();
        accediButton().click();
    }

}
