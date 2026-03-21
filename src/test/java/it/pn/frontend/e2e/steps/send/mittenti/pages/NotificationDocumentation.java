package it.pn.frontend.e2e.steps.send.mittenti.pages;

import it.frontend.e2e.framework.annotation.location.web.Url;
import it.frontend.e2e.framework.annotation.selector.XPath;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.capability.core.Readable;
import it.frontend.e2e.framework.web.capability.core.Uploadable;
import it.frontend.e2e.framework.web.capability.core.Writable;
import it.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.model.NotificationData;

import org.assertj.core.api.Assertions;

@Url("${url.selfcare.notifiche.base}/dashboard/nuova-notifica#selfCareToken=${token.mittente}")
public interface NotificationDocumentation extends Page {

    @XPath("//*[@data-testid='titleBox']")
    Readable<String> header();

    @XPath("//input[@id='documents.0.name']")
    Writable<String> documentTitle();
    
    @XPath("//*[@id=\"file-input\"]")
    Uploadable attachment();

    @XPath("//*[@data-testid='step-submit']")
    Clickable sendButton();

    @XPath("//*[@data-testid='previous-step']")
    Clickable backButton();

    default void uploadAndSubmit(NotificationData data) {
        documentTitle().write(data.getDocumentTitle());
        attachment().upload(data.getDocumentFilePath());
        sendButton().click();
    }

    @Override
    default void assertLoaded() {
        header().readAndAssert(h ->
            Assertions.assertThat(h.getText())
                      .isIn("Invia una nuova notifica", "Send a new notification")
        );
    }
}
