package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.exceptions.RestNotificationException;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.model.*;
import it.pn.frontend.e2e.model.enums.NotificationFeePolicyEnum;
import it.pn.frontend.e2e.model.enums.PhysicalCommunicationTypeEnum;
import it.pn.frontend.e2e.model.enums.RecipientTypeEnum;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewNotifichePagoPATest {
    private static final Logger logger = LoggerFactory.getLogger("NewNotifichePagoPATest");
    private final RestNotification restNotification = new RestNotification();
    private final WebDriver driver = Hooks.driver;

    @When("Creo in background una notifica con un destinatario e un documento tramite API REST")
    public void creoUnaNotificaConUnDestinatarioEUnDocumento(Map<String, Boolean> pagamenti) throws RestNotificationException {
        int maxAttempts = 3;
        int attempt = 1;

        boolean avvisoPagoPa = pagamenti.get("avvisoPagoPa");
        boolean f24 = pagamenti.get("F24");
        boolean entrambi = pagamenti.get("entrambi");
        ArrayList<Recipient> recipients = new ArrayList<>();
        recipients.add(new Recipient());
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(new Document());
        List<NotificationPaymentItem> payments = new ArrayList<>();
        PagoPaPayment pagoPaPayment = new PagoPaPayment(WebTool.generateNoticeCodeNumber());
        F24Payment modelloF24 = new F24Payment();

        if (avvisoPagoPa && !f24 && !entrambi) {
            NotificationPaymentItem pagamento = new NotificationPaymentItem(pagoPaPayment);
            payments.add(pagamento);
        } else if (!avvisoPagoPa && f24 && !entrambi) {
            NotificationPaymentItem pagamento = new NotificationPaymentItem(modelloF24);
            payments.add(pagamento);
        } else if (!avvisoPagoPa && !f24 && entrambi) {
            NotificationPaymentItem pagamento = new NotificationPaymentItem(pagoPaPayment, modelloF24);
            payments.add(pagamento);
        } else {
            logger.error("Le condizioni del map dei pagamenti non sono corrette");
            Assert.fail("Le condizioni del map dei pagamenti non sono corrette");
        }
        for (Recipient recipient : recipients) {
            recipient.setPayments(payments);
        }

        NewNotificationRequest notification = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.REGISTERED_LETTER_890, "010202N", NotificationFeePolicyEnum.DELIVERY_MODE);

        while (attempt <= maxAttempts) {
            NewNotificationResponse response = restNotification.newNotificationWithOneRecipientAndDocument(notification, pagamenti);

            if (response != null) {
                logger.info("Notifica creata con successo");
                System.setProperty("IUN", WebTool.decodeNotificationRequestId(response.getNotificationRequestId()));
                return;
            } else {
                logger.warn("Tentativo #" + attempt + " di creazione della notifica fallito. Riprovo...");
                notification.setPaProtocolNumber(WebTool.generatePaProtocolNumber());
                attempt++;
            }
        }

        logger.error("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }

    @And("Creo in background una notifica PG con un destinatario e un documento tramite API REST")
    public void creoUnaNotificaPGConUnDestinatarioEUnDocumento(Map<String, String> recipient, Map<String, Boolean> pagamenti) throws RestNotificationException {
        int maxAttempts = 3;
        int attempt = 1;

        ArrayList<Recipient> recipients = new ArrayList<>();
        recipients.add(new Recipient(recipient.get("denomination"), RecipientTypeEnum.PG, recipient.get("taxId"), new PhysicalAddress(), new DigitalDomicile()));
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(new Document());
        NewNotificationRequest notification = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.AR_REGISTERED_LETTER, "123456A", NotificationFeePolicyEnum.FLAT_RATE);

        while (attempt <= maxAttempts) {
            NewNotificationResponse response = restNotification.newNotificationWithOneRecipientAndDocument(notification, pagamenti);

            if (response != null) {
                logger.info("Notifica creata con successo");
                System.setProperty("IUN", WebTool.decodeNotificationRequestId(response.getNotificationRequestId()));
                return;
            } else {
                logger.warn("Tentativo #" + attempt + " di creazione della notifica fallito. Riprovo...");
                notification.setPaProtocolNumber(WebTool.generatePaProtocolNumber());
                attempt++;
            }
        }

        logger.error("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }


    /* TODO
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
        WebTool.waitTime(minutes * 60);
        driver.navigate().refresh();
        /* TODO
        Need to implement the check of the notification
         */
    }


}
