package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.exceptions.RestNotificationException;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.model.Document;
import it.pn.frontend.e2e.model.NewNotificationRequest;
import it.pn.frontend.e2e.model.NewNotificationResponse;
import it.pn.frontend.e2e.model.Recipient;
import it.pn.frontend.e2e.model.enums.NotificationFeePolicyEnum;
import it.pn.frontend.e2e.model.enums.PhysicalCommunicationTypeEnum;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class NewNotifichePagoPATest {
    private static final Logger logger = LoggerFactory.getLogger("NewNotifichePagoPATest");
    private final RestNotification restNotification = new RestNotification();
    private final WebDriver driver = Hooks.driver;

    @When("Creo in background una notifica con un destinatario e un documento tramite API REST")
    public void creoUnaNotificaConUnDestinatarioEUnDocumento() throws RestNotificationException {
        int maxAttempts = 3;
        int attempt = 1;

        ArrayList<Recipient> recipients = new ArrayList<>();
        recipients.add(new Recipient());
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(new Document());
        NewNotificationRequest notification = new NewNotificationRequest(DataPopulation.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.AR_REGISTERED_LETTER, "123456A", NotificationFeePolicyEnum.FLAT_RATE);

        while (attempt <= maxAttempts) {
            NewNotificationResponse response = restNotification.newNotificationWithOneRecipientAndDocument(notification);

            if (response != null) {
                logger.info("Notifica creata con successo");
                System.setProperty("IUN", DataPopulation.decodeNotificationRequestId(response.getNotificationRequestId()));
                return;
            } else {
                logger.warn("Tentativo #" + attempt + " di creazione della notifica fallito. Riprovo...");
                notification.setPaProtocolNumber(DataPopulation.generatePaProtocolNumber());
                attempt++;
            }
        }

        logger.error("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }


    // TO DO
    /*
    @When("Creo in background una notifica con multi destinatario {int} e multi documento tramite API REST")
    public void creoUnaNotificaConUnDestinatarioEUnDocumento(Integer numMultiRecipients) throws RestNotificationException {
        int maxAttempts = 3;
        int attempt = 1;

        while (attempt <= maxAttempts) {
            ArrayList<Recipient> recipients = new ArrayList<>();
            ArrayList<Document> documents = new ArrayList<>();
            for(int i=0; i<numMultiRecipients; i++){
                recipients.add(new Recipient());
                documents.add(new Document());
            }

            NewNotification notification = new NewNotification(DataPopulation.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.AR_REGISTERED_LETTER, "123456A", NotificationFeePolicyEnum.FLAT_RATE);

            NewNotificationResponse response = restNotification.newNotificationWithOneRecipientAndDocument(notification);

            if (response != null) {
                logger.info("Notifica creata con successo");
                return;
            } else {
                logger.warn("Tentativo #" + attempt + " di creazione della notifica fallito. Riprovo...");
                attempt++;
            }

        }

        logger.error("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }
     */

    @Then("Attendo {int} minuti e verifico in background che la notifica sia stata creata correttamente")
    public void verificoCheLaNotificaSiaStataCreataCorrettamente(int minutes) {
        DataPopulation.waitTime(minutes * 60);
        driver.navigate().refresh();
        /* TODO
        Need to implement the check of the notification
         */
    }


}
