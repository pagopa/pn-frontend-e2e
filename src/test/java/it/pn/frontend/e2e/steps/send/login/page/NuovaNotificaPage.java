package it.pn.frontend.e2e.steps.send.login.page;

import it.frontend.e2e.framework.annotation.Selector;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.capability.core.Readable;
import it.frontend.e2e.framework.web.domain.Page;
import org.assertj.core.api.Assertions;

public interface NuovaNotificaPage extends Page {

    @Selector("//*[@id=\"Invia una nuova notifica-page\"]")
    Readable<String> header();

    @Selector("//*[@id=\"breadcrumb-indietro-button\"]")
    Clickable backButton();

    @Override
    default void assertLoaded() {
        header().readAndAssert((h) -> {
            Assertions.assertThat(h).isNotNull();
            Assertions.assertThat(h.getText()).isEqualTo("Invia una nuova notifica");
        });
    }

    default void back(){
        backButton().click();
    }
}
