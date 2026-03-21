package it.pn.frontend.e2e.steps.send.mittenti.pages;

import it.frontend.e2e.framework.annotation.location.web.Url;
import it.frontend.e2e.framework.annotation.selector.XPath;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.core.model.selector.XPathSelector;
import it.frontend.e2e.framework.web.capability.core.Readable;
import it.frontend.e2e.framework.web.capability.core.Writable;
import it.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.model.NotificationData;

import org.assertj.core.api.Assertions;

@Url("${url.selfcare.notifiche.base}/dashboard/nuova-notifica#selfCareToken=${token.mittente}")
public interface NotificationCreate extends Page {

    @XPath("//*[@id=\"title-of-page\"]")
    Readable<String> breadcrumbs();

    @XPath("//*[@data-testid='titleBox']")
    Readable<String> header();

    @XPath("//*[@id=\"subject\"]")
    Writable<String> subject();

    @XPath("//input[@id='abstract']")
    Writable<String> description();

    @XPath("//input[@id='paProtocolNumber']")
    Writable<String> protocolNumber();

    @XPath("//input[@id='taxonomyCode']")
    Writable<String> taxonomyCode();

    // Group combobox — clicks to open the dropdown
    @XPath("//*[@id='group']")
    Clickable openGroupDropdown();
 
    // Continue button
    @XPath("//*[@data-testid='step-submit']")
    Clickable continueButton();

    default void fillFields(NotificationData data) {
        //selectLanguageItalian().click();
        subject().write(data.getSubject());
        protocolNumber().write(data.getProtocolNumber());
        taxonomyCode().write(data.getTaxonomyCode());
        //selectModel890().click();
        openGroupDropdown().click();
    }

    default XPathSelector groupOptionSelector(String groupName) {
        return XPathSelector.of(
            "//*[@role='option'][normalize-space()='" + groupName + "']"
        );
    }

    @Override
    default void assertLoaded() {
        header().readAndAssert(h -> {
            Assertions.assertThat(h).isNotNull();
            Assertions.assertThat(h.getText())
                      .isIn("Send a new notification", "Invia una nuova notifica");
        });
    }
}
