package it.pn.frontend.e2e.stepDefinitions.mittente;

import com.google.gson.internal.LinkedTreeMap;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.exceptions.RestNotificationException;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.model.*;
import it.pn.frontend.e2e.model.enums.NotificationFeePolicyEnum;
import it.pn.frontend.e2e.model.enums.PhysicalCommunicationTypeEnum;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.utility.NotificationBuilder;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
public class NewNotifichePagoPATest {
    private final RestNotification restNotification = new RestNotification();
    private static final NotificationBuilder notificationBuilder = new NotificationBuilder();
    private final WebDriver driver = Hooks.driver;
    private final NotificationSingleton notificationSingleton = NotificationSingleton.getInstance();


    @When("Creo in background una notifica per destinatario tramite API REST")
    public void creoUnaNotificaPerDestinatarioTramiteAPIREST(Map<String, String> datiNotifica) throws RestNotificationException {
        int maxAttempts = 4;
        int attempt = 1;

        String costiNotifica = datiNotifica.get("costiNotifica");
        ArrayList<Recipient> recipients = notificationBuilder.destinatarioCreation(datiNotifica.get("destinatario"), datiNotifica.get("multidestinatario"));
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
            NewNotificationResponse responseOfCreateNotification = restNotification.newNotificationWithOneRecipientAndDocument(notification);

            if (responseOfCreateNotification != null) {
                log.info("Inizio controllo notifica fino a stato accettata");
                int maxAttemptsPolling = 0;
                LinkedTreeMap<String, Object> getNotificationStatus;
                String notificationStatus;
                do {
                    Assert.assertTrue("La notifica risulta ancora in stato WAITING dopo 5 tentativi", maxAttemptsPolling <= 4);
                    log.info(responseOfCreateNotification.getNotificationRequestId());
                    getNotificationStatus = restNotification.getNotificationStatus(responseOfCreateNotification.getNotificationRequestId());
                    log.info(String.valueOf(getNotificationStatus));
                    notificationStatus = getNotificationStatus.get("notificationRequestStatus").toString();
                    if (!notificationStatus.equals("ACCEPTED")) {
                        WebTool.waitTime(90);
                        log.info("Tentativo n. " + maxAttemptsPolling + " - Stato notifica: " + notificationStatus);
                        maxAttemptsPolling++;
                    } else {
                        driver.navigate().refresh();
                        return;
                    }
                } while (notificationStatus.equals("WAITING"));
                log.info("Notifica per persona fisica creata con successo");
                notificationSingleton.setScenarioIun(Hooks.getScenario(), WebTool.decodeNotificationRequestId(responseOfCreateNotification.getNotificationRequestId()));
                log.info("Il codice IUN della notifica per PF è il seguente: {}", notificationSingleton.getIun(Hooks.getScenario()));
            } else {
                log.warn("Tentativo #{} di creazione della notifica fallito. Riprovo...", attempt);
                notification.setPaProtocolNumber(WebTool.generatePaProtocolNumber());
                attempt++;
            }
        }
        log.error("Errore nella creazione della notifica per PF dopo {} tentativi", maxAttempts);
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