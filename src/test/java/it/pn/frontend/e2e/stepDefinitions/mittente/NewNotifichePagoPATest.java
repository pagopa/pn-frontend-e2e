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

        PhysicalCommunicationTypeEnum modelloNotifica = notificationBuilder.modelloNotifica(datiNotifica.get("modello"));
        NotificationFeePolicyEnum feePolicy = notificationBuilder.notificaFeePolicy(datiNotifica.get("costiNotifica"));
        ArrayList<Recipient> recipients = notificationBuilder.destinatarioBuilder(datiNotifica);
        ArrayList<Document> documents = notificationBuilder.preloadDocument(Integer.parseInt(datiNotifica.get("documenti")));
        ArrayList<NotificationPaymentItem> payments = notificationBuilder.paymentsBuilder(Integer.parseInt(datiNotifica.get("avvisoPagoPa")), Integer.parseInt(datiNotifica.get("F24")), datiNotifica.get("costiNotifica"));
        if (datiNotifica.get("multidestinatario").equalsIgnoreCase("true")){
            recipients = notificationBuilder.multidestinatarioBuilder(recipients, datiNotifica);
        }
        for (Recipient recipient : recipients) {
            recipient.setPayments(payments);
        }

        NewNotificationRequest notification = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, modelloNotifica, "010202N", feePolicy);

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
                    notificationStatus = getNotificationStatus.get("notificationRequestStatus").toString();
                    if (!notificationStatus.equals("ACCEPTED")) {
                        WebTool.waitTime(90);
                        log.info("Tentativo n. " + maxAttemptsPolling + " - Stato notifica: " + notificationStatus);
                        maxAttemptsPolling++;
                    } else {
                        log.info("Notifica per destinatario creata con successo");
                        notificationSingleton.setScenarioIun(Hooks.getScenario(), WebTool.decodeNotificationRequestId(responseOfCreateNotification.getNotificationRequestId()));
                        log.info("Il codice IUN della notifica creata è il seguente: {}", notificationSingleton.getIun(Hooks.getScenario()));
                        driver.navigate().refresh();
                        return;
                    }
                } while (notificationStatus.equals("WAITING"));
            } else {
                log.warn("Tentativo #{} di creazione della notifica fallito. Riprovo...", attempt);
                notification.setPaProtocolNumber(WebTool.generatePaProtocolNumber());
                attempt++;
            }
        }
        log.error("Errore nella creazione della notifica per PF dopo {} tentativi", maxAttempts);
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }
}