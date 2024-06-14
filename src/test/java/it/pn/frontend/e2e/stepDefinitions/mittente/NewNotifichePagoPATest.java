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
import it.pn.frontend.e2e.utility.NotificationConstructor;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
public class NewNotifichePagoPATest {
    public static final String APPLICATION_PDF = "application/pdf";
    public static final String APPLICATION_JSON = "application/json";
    private static final String LOAD_TO_PRESIGNED = "LOAD_TO_PRESIGNED";
    private static final String LOAD_TO_PRESIGNED_METADATI = "LOAD_TO_PRESIGNED_METADATI";
    private final RestNotification restNotification = new RestNotification();
    private static NotificationConstructor notificationConstructor = new NotificationConstructor();
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
            for (Recipient recipient : recipients) {
                recipient.setPayments(null);
            }
        }
        for (Recipient recipient : recipients) {
            recipient.setPayments(payments);
        }

        NewNotificationRequest notification = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.REGISTERED_LETTER_890, "010202N", NotificationFeePolicyEnum.DELIVERY_MODE);

        while (attempt <= maxAttempts) {
            NewNotificationResponse response = restNotification.newNotificationWithOneRecipientAndDocument(notification);

            if (response != null) {
                log.info("Notifica creata con successo");
                System.setProperty("IUN", WebTool.decodeNotificationRequestId(response.getNotificationRequestId()));
                return;
            } else {
                log.warn("Tentativo #{} di creazione della notifica fallito. Riprovo...", attempt);
                notification.setPaProtocolNumber(WebTool.generatePaProtocolNumber());
                attempt++;
            }
        }

        log.error("Errore nella creazione della notifica dopo {} tentativi", maxAttempts);
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }

    @And("Creo in background una notifica PG con un destinatario e un documento tramite API REST")
    public void creoUnaNotificaPGConUnDestinatarioEUnDocumento(Map<String, String> datiNotifica) throws RestNotificationException {
        int maxAttempts = 3;
        int attempt = 1;

        /*
         * documenti: numero intero
         * avvisoPagoPa: numero intero
         * f24: numero intero
         * multidestinatario: true o false
         * costi di notifica: true o false
         * */

        ArrayList<Recipient> recipients = notificationConstructor.multiDestinatarioPG(datiNotifica.get("multidestinatario"));
        ArrayList<Document> documents = notificationConstructor.preloadDocument(Integer.parseInt(datiNotifica.get("document")));
        ArrayList<NotificationPaymentItem> payments = notificationConstructor.paymentsCreation(Integer.parseInt(datiNotifica.get("avvisoPagoPa")), Integer.parseInt(datiNotifica.get("F24")));

        for (Recipient recipient : recipients) {
            recipient.setPayments(payments);
        }

        NewNotificationRequest notification = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.REGISTERED_LETTER_890, "010202N", NotificationFeePolicyEnum.DELIVERY_MODE);

        while (attempt <= maxAttempts) {
            NewNotificationResponse response = restNotification.newNotificationWithOneRecipientAndDocument(notification);

            if (response != null) {
                log.info("Notifica creata con successo");
                System.setProperty("IUN", WebTool.decodeNotificationRequestId(response.getNotificationRequestId()));
                return;
            } else {
                log.warn("Tentativo #{} di creazione della notifica fallito. Riprovo...", attempt);
                notification.setPaProtocolNumber(WebTool.generatePaProtocolNumber());
                attempt++;
            }
        }

        log.error("Errore nella creazione della notifica dopo {} tentativi", maxAttempts);
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