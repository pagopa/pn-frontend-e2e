package it.pn.frontend.e2e.pages.destinatario;

import com.google.gson.internal.LinkedTreeMap;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.exceptions.RestNotificationException;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.model.documents.Document;
import it.pn.frontend.e2e.model.enums.NotificationFeePolicyEnum;
import it.pn.frontend.e2e.model.enums.PhysicalCommunicationTypeEnum;
import it.pn.frontend.e2e.model.notification.NewNotificationRequest;
import it.pn.frontend.e2e.model.notification.NewNotificationResponse;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.rest.RestRaddAlternative;
import it.pn.frontend.e2e.utility.BeanProvider;
import it.pn.frontend.e2e.utility.NotificationBuilder;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class DestinatarioPage extends BasePage {


    @Getter
    @Setter
    private NewNotificationRequest notificationRequest;

    @Getter
    @Setter
    private NotificationSingleton notificationSingleton;

    @Getter
    @Setter
    private RestNotification restNotification;

    @Getter
    @Setter
    private NotificationBuilder notificationBuilder;

    private int destinatariNumber;

    @FindBy(id = "startDate")
    WebElement dataInizioField;
    @FindBy(id = "endDate")
    WebElement dataFineField;
    @FindBy(id = "side-item-Notifiche")
    WebElement sideItemNotificheButton;
    @FindBy(id = "notificationsTable.body.row")
    List<WebElement> listaNotificheDelegante;

    @Getter
    @Setter
    private HooksNew hooksNew;


    private WebTool webTool;

    public DestinatarioPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }


    public void inserimentoDataErrato() {
        dataInizioField = driver.findElement(By.id("startDate"));
        String data = "01/01/1111";
        getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElements(dataInizioField));
        dataInizioField.click();
        dataInizioField.sendKeys(data);
        getWebDriverWait(3).until(ExpectedConditions.attributeToBe(dataInizioField, "value", data));
        dataFineField = driver.findElement(By.id("endDate"));
        getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElements(dataFineField));
        dataFineField.click();
        dataFineField.sendKeys(data);
        getWebDriverWait(3).until(ExpectedConditions.attributeToBe(dataFineField, "value", data));
    }

    public boolean isDateBoxInvalid() {
        String isTextboxInvalid = "true";
        boolean invalidBoxDate = true;
        try {
            webTool.waitTime(10);
            dataInizioField = driver.findElement(By.id("startDate"));
            dataFineField = driver.findElement(By.id("endDate"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElements(dataInizioField, dataFineField));
            String ariaInvalidInizio = dataInizioField.getAttribute("aria-invalid");
            String ariaInvalidFine = dataFineField.getAttribute("aria-invalid");
            if (isTextboxInvalid.equals(ariaInvalidInizio) || isTextboxInvalid.equals(ariaInvalidFine)) {
                log.info("Almeno un campo data è in stato invalido");
            } else {
                log.error("Nessuno dei campi data è passato allo stato invalido");
                Assertions.fail("Nessuno dei campi data è passato allo stato invalido");
            }
        } catch (TimeoutException e) {
            log.error("Campi data non visualizzati correttamente con errore: " + e.getMessage());
            Assertions.fail("Campi data non visualizzati correttamente con errore: " + e.getMessage());
        }
        return invalidBoxDate;
    }

    public void clickButtonNotificheDelegateOnSideMenu(String nomeDelegante) {
        log.info("verifica bottone notifiche nel layout");
        webTool.waitTime(10);
        sideItemNotificheButton = driver.findElement(By.id("side-item-Notifiche"));
        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(sideItemNotificheButton));
        sideItemNotificheButton.click();

        String id = "side-item-" + nomeDelegante;

        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.id(id))));
        WebElement buttonNotificheOnSideMenu = driver.findElement(By.id(id));
        js().executeScript("arguments[0].click()", buttonNotificheOnSideMenu);
    }

    public void clickSulDettaglioNotificaDelegante() {
        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElements(By.id("notificationsTable.body.row")).get(0)));
        WebElement singolaNotificaDelegante = driver.findElements(By.id("notificationsTable.body.row")).get(0);
        log.info("Si clicca sulla prima notifica del delegante");
        singolaNotificaDelegante.click();
    }

    public void clickProdotto(String xpath) {
        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
        WebElement prodottoDestinatario = driver.findElement(By.xpath(xpath));
        prodottoDestinatario.click();
    }

    public void checkCreateNewNotification() throws RestNotificationException {
        int maxAttempts = 9;
        int attempt = 1;
        restNotification = getRestNotification();
        Assertions.assertNotNull(notificationRequest.getRecipients(), "Non può essere creata una notifica senza alcun destinatario");

        while (attempt <= maxAttempts) {
            NewNotificationResponse responseOfCreateNotification = restNotification.newNotificationWithOneRecipientAndDocument(notificationRequest);
            notificationSingleton = getNotificationSingleton();
            if (responseOfCreateNotification != null) {
                log.info("NEW_NOTFIC_REQUEST_ID: " + responseOfCreateNotification.getNotificationRequestId());
                log.info("Inizio controllo notifica fino a stato accettata");
                int maxAttemptsPolling = 0;
                LinkedTreeMap<String, Object> getNotificationStatus;
                String notificationStatus;
                do {
                    Assertions.assertTrue(maxAttemptsPolling <= 9, "La notifica risulta ancora in stato WAITING dopo 9 tentativi");
                    log.info(responseOfCreateNotification.getNotificationRequestId());
                    getNotificationStatus = restNotification.getNotificationStatus(responseOfCreateNotification.getNotificationRequestId());
                    notificationStatus = getNotificationStatus.get("notificationRequestStatus").toString();
                    if (!notificationStatus.equals("ACCEPTED")) {
                        webTool.waitTime(90);
                        log.info("Tentativo n. " + maxAttemptsPolling + " - Stato notifica: " + notificationStatus);
                        maxAttemptsPolling++;
                    } else {
                        log.info("Notifica per destinatario creata con successo");
                        notificationSingleton.setScenarioIun(hooksNew.getScenario(), WebTool.decodeNotificationRequestId(responseOfCreateNotification.getNotificationRequestId()));
                        log.info("Il codice IUN della notifica creata è il seguente: {}", notificationSingleton.getIun(hooksNew.getScenario()));
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
        Assertions.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }

    public void aggiuntaDestinatarioANotifica(Map<String, String> datiDestinatario) {
        notificationBuilder = new NotificationBuilder(getRestNotification());
        Assertions.assertTrue(destinatariNumber <= 4, "Non è possibile aggiungere un ulteriore destinatario");
        log.info("Si procede con l'inserimento del destinatario nella notifica");
        String costiNotifica = notificationRequest.getNotificationFeePolicy() == NotificationFeePolicyEnum.DELIVERY_MODE ? "true" : "false";
        notificationRequest.setRecipients(notificationBuilder.destinatarioBuilder(datiDestinatario, notificationRequest.getRecipients()));

        log.info("NUMERO DESTINATARI: " + notificationRequest.getRecipients().size());
        notificationRequest.getRecipients().get(destinatariNumber).setPayments(
                notificationBuilder.paymentsBuilder(
                        Integer.parseInt(datiDestinatario.getOrDefault("avvisoPagoPa", "0")),
                        Integer.parseInt(datiDestinatario.getOrDefault("F24", "0")),
                        costiNotifica
                ));
        destinatariNumber++;
    }

    public void inizializzazioneDatiNotifica(Map<String, String> datiNotifica) {
        notificationBuilder = new NotificationBuilder(getRestNotification());
        PhysicalCommunicationTypeEnum modelloNotifica = notificationBuilder.modelloNotifica(datiNotifica.get("modello"));
        NotificationFeePolicyEnum feePolicy = notificationBuilder.notificaFeePolicy(datiNotifica.getOrDefault("costiNotifica", "false"));
        ArrayList<Document> documents = notificationBuilder.preloadDocument(Integer.parseInt(datiNotifica.get("documenti")));
        notificationRequest = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), datiNotifica.getOrDefault("oggettoNotifica", "PAGAMENTO RATA IMU"), null, documents, modelloNotifica, "010202N", feePolicy);
    }

    public void raddFlow(String token, String tipoDestinatario, String codiceFiscale, String operationId) {
        final RestRaddAlternative restRaddAlternative = new RestRaddAlternative(token);
        restRaddAlternative.startTransactionRaddAlternative(tipoDestinatario, codiceFiscale, operationId);
        restRaddAlternative.completeTransactionRaddAlternative(operationId);

    }

    public void clickTuttiGliEnti() {

        getWebDriverWait(10).withMessage("Il radio button 'tutti gli enti selezionati' non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("tutti-gli-enti-selezionati"))));
        WebElement tuttiGliEnti = driver.findElement(By.id("tutti-gli-enti-selezionati"));
        tuttiGliEnti.click();
    }

    public void clickSoloEntiSelezionati() {

        getWebDriverWait(15).withMessage("Il radio button 'solo enti selezionati' non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("enti-selezionati"))));
        WebElement soloEntiSelezionati = driver.findElement(By.id("enti-selezionati"));
        soloEntiSelezionati.click();
    }

    public void clickListaEnti() {
        getWebDriverWait(15).withMessage("Il menù a tendina degli enti non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("enti"))));
        WebElement listaEnti = driver.findElement(By.id("enti"));
        listaEnti.click();
    }

    public void controlloEntiRadice(List<String> enti) {
        for (String ente : enti) {
            // WebElement enteRadice = driver.findElement(By.xpath("//li//p[contains(text(),'" + ente + "')]"));
            getWebDriverWait(15).withMessage("Ente: " + ente + " non visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li//p[contains(text(),'" + ente + "')]"))));
        }
    }

    public void checkBannerAnnullamentoNotifica() {
       // WebElement bannerAnnullamentoNotificaBy = driver.findElement(By.xpath("//div[@data-testid='cancelledAlertText']"));
        getWebDriverWait(10).withMessage("Il banner di annullamento della notifica non è presente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='cancelledAlertText']"))));
        getWebDriverWait(10).withMessage("Il banner di annullamento della notifica presenta la corretta descrizione").until(
                ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//div[@data-testid='cancelledAlertText']")), "textContent", "Questa notifica è stata annullata dall’ente mittente. Puoi ignorarne il contenuto."));
    }
}
