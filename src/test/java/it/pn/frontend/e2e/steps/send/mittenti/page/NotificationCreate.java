package it.pn.frontend.e2e.steps.send.mittenti.page;

import it.frontend.e2e.framework.annotation.location.web.Url;
import it.frontend.e2e.framework.annotation.selector.XPath;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.capability.core.Readable;
import it.frontend.e2e.framework.web.capability.core.Writable;
import it.frontend.e2e.framework.web.domain.Page;
import org.assertj.core.api.Assertions;

@Url("${url.selfcare.notifiche.base}/dashboard/nuova-notifica#selfCareToken=${token.mittente}")
public interface NotificationCreate extends Page {

    @XPath("//*[@id=\"title-of-page\"]")
    Readable<String> breadcrumbs();

    @XPath("//*[@data-testid='titleBox']")
    Readable<String> header();

    @XPath("//*[@id=\"subject\"]")
    Writable<String> subject();

   /*
       // Language radio — selects "Italian" (value="it")
    @XPath("//input[@name='lang'][@value='it']")
    Clickable selectLanguageItalian();

    // Language radio — selects "Italian and another language" (value="other")
    @XPath("//input[@name='lang'][@value='other']")
    Clickable selectLanguageOther();

    

    @XPath("//input[@id='abstract']")
    Writable<String> description();

    @XPath("//input[@id='paProtocolNumber']")
    Writable<String> protocolNumber();

    @XPath("//input[@id='taxonomyCode']")
    Writable<String> taxonomyCode();

    // Physical communication type radios
    @XPath("//input[@name='physicalCommunicationType'][@value='REGISTERED_LETTER_890']")
    Clickable selectModel890();

    @XPath("//input[@name='physicalCommunicationType'][@value='AR_REGISTERED_LETTER']")
    Clickable selectRegisteredLetter();

    // Group combobox — clicks to open the dropdown
    @XPath("//*[@id='group']")
    Clickable openGroupDropdown();

    // Selects a specific group option by visible text
    @XPath("//*[@role='option'][normalize-space()='${group.name}']")
    Clickable groupOption();

    // Continue button
    @XPath("//*[@data-testid='step-submit']")
    Clickable continueButton();
 selectLanguageItalian().click();
        subject().write(subject);
        protocolNumber().write(protocol);
        taxonomyCode().write(taxonomy);
        selectModel890().click();
        openGroupDropdown().click();
        groupOption().click();       // ${group.name} resolved via ScenarioContext
        continueButton().click();
    
   
   */

    default void fillAndSubmit(
            String subject,
            String protocol,
            String taxonomy,
            String groupName
    ) {
        
      subject().write(subject);
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
