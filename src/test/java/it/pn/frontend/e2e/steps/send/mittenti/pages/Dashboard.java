package it.pn.frontend.e2e.steps.send.mittenti.pages;

import it.frontend.e2e.framework.annotation.location.web.Url;
import it.frontend.e2e.framework.annotation.selector.XPath;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.capability.core.Readable;
import it.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.steps.common.ListPage;

import java.util.regex.Pattern;

import org.assertj.core.api.Assertions;

@Url("${url.selfcare.notifiche.base}/dashboard#selfCareToken=${token.mittente}")
public interface Dashboard extends Page, ListPage {

    Pattern IUN_PATTERN = Pattern.compile("[A-Z]{4}-[A-Z]{4}-[A-Z]{4}-\\d{6}-[A-Z]-\\d");

    @XPath("//*[@data-testid=\"titleBox\"]")
    Readable<String> header();

    @XPath("//*[@id=\"notificationsTable.body.row\"]/td[4]")
    Readable<String> firstIunCode();

    @Override
    @XPath("//*[@id=\"notificationsTable.body.row\"]/td[7]/button")
    Clickable firstListItemButton();

    default String getIun() {
        java.util.concurrent.atomic.AtomicReference<String> value = new java.util.concurrent.atomic.AtomicReference<>();
        firstIunCode().readAndAssert(element -> value.set(element.getText()));
        return value.get();
    }

    @Override
    default void assertLoaded() {
       header().readAndAssert((h) -> {
           Assertions.assertThat(h).isNotNull();
           Assertions.assertThat(h.getText()).isIn("Notifiche", "Notifications");
       });
    }
}
