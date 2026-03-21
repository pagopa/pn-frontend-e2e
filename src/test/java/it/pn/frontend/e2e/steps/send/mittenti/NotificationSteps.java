package it.pn.frontend.e2e.steps.send.mittenti;

import it.frontend.e2e.framework.web.WebPresentationGateway;
import it.pn.frontend.e2e.config.ScenarioContext;
import it.pn.frontend.e2e.factory.NotificationFactory;
import it.pn.frontend.e2e.model.NotificationData;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NotificationCreate;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NotificationDebtPosition;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NotificationDocumentation;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NotificationRecipients;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NotificationSuccess;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationSteps {

    private final WebPresentationGateway browser;
    private final ScenarioContext scenarioContext;
    private final NotificationFactory notificationFactory;

    @Given("una notifica di tipo {string}")
    public void loadNotificationTemplate(String templateName) {
        NotificationData notification = notificationFactory.load(templateName);
        scenarioContext.setObject("notification", notification);
    }

    @When("compila il form con i dati della notifica")
    public void fillNotificationForm() {
        NotificationData data = scenarioContext
                .getObject("notification", NotificationData.class);
        NotificationCreate page = scenarioContext
                .getObject("currentPage", NotificationCreate.class);

        page.fillFields(data);
        browser.click(page.groupOptionSelector(data.getGroup()));

        page.continueButton().click();

        NotificationRecipients nextPage = browser.bind(NotificationRecipients.class);
        nextPage.assertLoaded();
        scenarioContext.setObject("currentPage", nextPage);
    }

    @When("compila i dati del destinatario")
    public void fillRecipientForm() {
        NotificationData data = scenarioContext
                .getObject("notification", NotificationData.class);
        scenarioContext.getObject("currentPage", NotificationRecipients.class)
                       .fillAndSubmit(data);
       
        NotificationDebtPosition nextPage = browser.bind(NotificationDebtPosition.class);
        nextPage.assertLoaded();
        scenarioContext.setObject("currentPage", nextPage);
    }

    @When("seleziona la posizione debitoria")
    public void selectDebtPosition() {
        NotificationData data = scenarioContext
                .getObject("notification", NotificationData.class);
        scenarioContext.getObject("currentPage", NotificationDebtPosition.class)
                       .selectPaymentTypeAndSubmit(data.getPaymentType());
        
        NotificationDocumentation nextPage = browser.bind(NotificationDocumentation.class);
        nextPage.assertLoaded();
        scenarioContext.setObject("currentPage", nextPage);
    }

    @When("carica il documento allegato")
    public void uploadDocument() {
        NotificationData data = scenarioContext
                .getObject("notification", NotificationData.class);
        scenarioContext.getObject("currentPage", NotificationDocumentation.class)
                       .uploadAndSubmit(data);
    }

    @Then("la notifica è stata inviata con successo")
    public void assertNotificationSentSuccessfully() {
        NotificationSuccess successPage = browser.bind(NotificationSuccess.class);
        successPage.assertLoaded();
        scenarioContext.setObject("currentPage", successPage);
    }
}