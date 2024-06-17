package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.exceptions.RestNotificationException;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.model.*;
import it.pn.frontend.e2e.model.enums.NotificationFeePolicyEnum;
import it.pn.frontend.e2e.model.enums.PhysicalCommunicationTypeEnum;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.utility.NotificationBuilder;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.*;

@Slf4j
public class NewNotifichePagoPATest {
    private final RestNotification restNotification = new RestNotification();
    private static final NotificationBuilder notificationBuilder = new NotificationBuilder();
    private final WebDriver driver = Hooks.driver;

    @When("Creo in background una notifica per persona fisica tramite API REST")
    public void creoUnaNotificaConUnDestinatarioEUnDocumento(Map<String, String> datiNotifica) throws RestNotificationException {
        int maxAttempts = 3;
        int attempt = 1;

        String costiNotifica = datiNotifica.get("costiNotifica");
        ArrayList<Recipient> recipients = notificationBuilder.multiDestinatarioPF(datiNotifica.get("multidestinatario"));
        ArrayList<Document> documents = notificationBuilder.preloadDocument(Integer.parseInt(datiNotifica.get("documenti")));
        ArrayList<NotificationPaymentItem> payments = notificationBuilder.paymentsCreation(Integer.parseInt(datiNotifica.get("avvisoPagoPa")), Integer.parseInt(datiNotifica.get("F24")), costiNotifica);
        for (Recipient recipient : recipients) {
            recipient.setPayments(payments);
        }

        NewNotificationRequest notification;
        if (costiNotifica.equalsIgnoreCase("false")) {
            notification = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.REGISTERED_LETTER_890, "010202N", NotificationFeePolicyEnum.FLAT_RATE);
        } else {
            notification = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.REGISTERED_LETTER_890, "010202N", NotificationFeePolicyEnum.DELIVERY_MODE);
        }
        log.info("\n\n\nbody della notifica:\n{}", notification);
        while (attempt <= maxAttempts) {
            NewNotificationResponse response = restNotification.newNotificationWithOneRecipientAndDocument(notification);

            if (response != null) {
                log.info("Notifica per persona fisica creata con successo");
                System.setProperty("IUN", WebTool.decodeNotificationRequestId(response.getNotificationRequestId()));
                log.info("Il codice IUN della notifica per PF è il seguente: {}", System.getProperty("IUN"));
                return;
            } else {
                log.warn("Tentativo #{} di creazione della notifica fallito. Riprovo...", attempt);
                notification.setPaProtocolNumber(WebTool.generatePaProtocolNumber());
                attempt++;
            }
        }
        log.error("Errore nella creazione della notifica per PF dopo {} tentativi", maxAttempts);
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }

    @And("Creo in background una notifica per persona giuridica tramite API REST")
    public void creoUnaNotificaPGConUnDestinatarioEUnDocumento(Map<String, String> datiNotifica) throws RestNotificationException {
        int maxAttempts = 3;
        int attempt = 1;

        String costiNotifica = datiNotifica.get("costiNotifica");
        ArrayList<Recipient> recipients = notificationBuilder.multiDestinatarioPG(datiNotifica.get("multidestinatario"));
        ArrayList<Document> documents = notificationBuilder.preloadDocument(Integer.parseInt(datiNotifica.get("documenti")));
        ArrayList<NotificationPaymentItem> payments = notificationBuilder.paymentsCreation(Integer.parseInt(datiNotifica.get("avvisoPagoPa")), Integer.parseInt(datiNotifica.get("F24")), costiNotifica);
        for (Recipient recipient : recipients) {
            recipient.setPayments(payments);
        }

        NewNotificationRequest notification;
        if (costiNotifica.equalsIgnoreCase("false")) {
            notification = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.REGISTERED_LETTER_890, "010202N", NotificationFeePolicyEnum.FLAT_RATE);
        } else {
            notification = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.REGISTERED_LETTER_890, "010202N", NotificationFeePolicyEnum.DELIVERY_MODE);
        }
        while (attempt <= maxAttempts) {
            NewNotificationResponse response = restNotification.newNotificationWithOneRecipientAndDocument(notification);
            if (response != null) {
                log.info("Notifica per persona giuridica creata con successo");
                System.setProperty("IUN", WebTool.decodeNotificationRequestId(response.getNotificationRequestId()));
                log.info("Il codice IUN della notifica per PG è il seguente: {}", System.getProperty("IUN"));
                return;
            } else {
                log.warn("Tentativo #{} di creazione della notifica con chiamata API fallito. Riprovo...", attempt);
                notification.setPaProtocolNumber(WebTool.generatePaProtocolNumber());
                attempt++;
            }
        }
        log.error("Errore nella creazione della notifica per PG dopo {} tentativi", maxAttempts);
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }

    @Then("Attendo {int} minuti e verifico in background che la notifica sia stata creata correttamente")
    public void verificoCheLaNotificaSiaStataCreataCorrettamente(int minutes) {
        WebTool.waitTime(minutes * 60);
        driver.navigate().refresh();
        /* TODO
        Need to implement the check of the notification
         */
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
                log.info("Notifica creata con successo");
                return;
            } else {
                log.warn("Tentativo #" + attempt + " di creazione della notifica fallito. Riprovo...");
                attempt++;
            }

        }

        log.error("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }*/

}