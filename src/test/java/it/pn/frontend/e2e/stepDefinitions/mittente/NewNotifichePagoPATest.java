package it.pn.frontend.e2e.stepDefinitions.mittente;

import com.google.gson.internal.LinkedTreeMap;
import io.cucumber.java.en.And;
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
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
public class NewNotifichePagoPATest {

    @Getter
    @Setter
    private NewNotificationRequest notificationRequest;

    private static int destinatariNumber;
    private final RestNotification restNotification = new RestNotification();
    private static final NotificationBuilder notificationBuilder = new NotificationBuilder();
    private final WebDriver driver = Hooks.driver;
    private final NotificationSingleton notificationSingleton = NotificationSingleton.getInstance();


    @Then("Creo in background una notifica per destinatario tramite API REST")
    public void creoUnaNotificaPerDestinatarioTramiteAPIREST() throws RestNotificationException {
        int maxAttempts = 4;
        int attempt = 1;
        Assert.assertNotNull("Non può essere creata una notifica senza alcun destinatario", notificationRequest.getRecipients());
        while (attempt <= maxAttempts) {
            NewNotificationResponse responseOfCreateNotification = restNotification.newNotificationWithOneRecipientAndDocument(notificationRequest);

            if (responseOfCreateNotification != null) {
                log.info("Inizio controllo notifica fino a stato accettata");
                int maxAttemptsPolling = 0;
                LinkedTreeMap<String, Object> getNotificationStatus;
                String notificationStatus;
                do {
                    Assert.assertTrue("La notifica risulta ancora in stato WAITING dopo 10 tentativi", maxAttemptsPolling <= 9);
                    log.info(responseOfCreateNotification.getNotificationRequestId());
                    getNotificationStatus = restNotification.getNotificationStatus(responseOfCreateNotification.getNotificationRequestId());
                    notificationStatus = getNotificationStatus.get("notificationRequestStatus").toString();
                    if (!notificationStatus.equals("ACCEPTED")) {
                        WebTool.waitTime(45);
                        log.info("Tentativo n. {} - Stato notifica: {}", maxAttemptsPolling, notificationStatus);
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
                notificationRequest.setPaProtocolNumber(WebTool.generatePaProtocolNumber());
                attempt++;
            }
        }
        log.error("Errore nella creazione della notifica per PF dopo {} tentativi", maxAttempts);
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }

    @And("Si aggiunge un destinatario alla notifica")
    public void siAggiungeUnDestinatarioAllaNotifica(Map<String, String> datiDestinatario) {
        if (destinatariNumber <= 4) {
            log.info("Si procede con l'inserimento del destinatario nella notifica");
            String costiNotifica = "false";
            if (notificationRequest.getNotificationFeePolicy() == NotificationFeePolicyEnum.DELIVERY_MODE) {
                costiNotifica = "true";
            }
            notificationRequest.setRecipients(notificationBuilder.destinatarioBuilder(datiDestinatario, notificationRequest.getRecipients()));
            notificationRequest.getRecipients().get(destinatariNumber).setPayments(notificationBuilder.paymentsBuilder(Integer.parseInt(datiDestinatario.getOrDefault("avvisoPagoPa", "0")), Integer.parseInt(datiDestinatario.getOrDefault("F24", "0")), costiNotifica));
            destinatariNumber++;
        } else {
            log.error("Non è possibile aggiungere un ulteriore destinatario");
            Assert.fail("Non è possibile aggiungere un ulteriore destinatario");
        }
    }

    @When("Si inizializzano i dati per la notifica")
    public void siInizializzanoIDatiPerLaNotifica(Map<String, String> datiNotifica) {
        PhysicalCommunicationTypeEnum modelloNotifica = notificationBuilder.modelloNotifica(datiNotifica.get("modello"));
        NotificationFeePolicyEnum feePolicy = notificationBuilder.notificaFeePolicy(datiNotifica.getOrDefault("costiNotifica", "false"));
        ArrayList<Document> documents = notificationBuilder.preloadDocument(Integer.parseInt(datiNotifica.get("documenti")));
        notificationRequest = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), datiNotifica.getOrDefault("oggettoNotifica", "PAGAMENTO RATA IMU"), null, documents, modelloNotifica, "010202N", feePolicy);
    }
}