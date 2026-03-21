package it.pn.frontend.e2e.steps.send.mittenti.pages;

import it.frontend.e2e.framework.annotation.location.web.Url;
import it.frontend.e2e.framework.annotation.selector.XPath;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.capability.core.Readable;
import it.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.steps.common.ListPage;

import org.assertj.core.api.Assertions;

@Url("${url.selfcare.notifiche.base}/api-keys#selfCareToken=${token.mittente}")
public interface APIKey extends ListPage, Page {

    @XPath("//*[@data-testid=\"titleBox\"]")
    Readable<String> header();

    @Override
    @XPath("//*[@id=\"tableApiKeys.body.row\"]/td[6]/div/div/button")
    Clickable firstListItemButton();

    @XPath("//*[@id=\"button-view\"]")
    Clickable firstSubListItemButton();

    @Override
    default void openFirstItem() {
        firstListItemButton().click();
        firstSubListItemButton().click();
    }

    @Override
    default void assertLoaded() {
       header().readAndAssert((h) -> {
           Assertions.assertThat(h).isNotNull();
           Assertions.assertThat(h.getText()).isIn("API Key", "API Key");
       });
    }
}