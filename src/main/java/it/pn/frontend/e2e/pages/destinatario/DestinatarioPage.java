package it.pn.frontend.e2e.pages.destinatario;

import com.google.gson.internal.LinkedTreeMap;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.exceptions.RestNotificationException;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.model.documents.Document;
import it.pn.frontend.e2e.model.enums.NotificationFeePolicyEnum;
import it.pn.frontend.e2e.model.enums.PhysicalCommunicationTypeEnum;
import it.pn.frontend.e2e.model.notification.NewNotificationRequest;
import it.pn.frontend.e2e.model.notification.NewNotificationResponse;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.rest.RestRaddAlternative;
import it.pn.frontend.e2e.utility.NotificationBuilder;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

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
                Assertions.fail("Nessuno dei campi data è passato allo stato invalido");
            }
        } catch (TimeoutException e) {
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
        //NON Utilizzata
        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElements(By.id("notificationsTable.body.row")).get(0)));
        WebElement singolaNotificaDelegante = driver.findElements(By.id("notificationsTable.body.row")).get(0);
        log.info("Si clicca sulla prima notifica del delegante");
        singolaNotificaDelegante.click();
    }

    public void clickProdotto(String xpath) {
        getWebDriverWait(20).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
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
                    log.info("responseOfCreateNotification.getNotificationRequestId(): {}",responseOfCreateNotification.getNotificationRequestId());
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
            getWebDriverWait(15).withMessage("Ente: " + ente + " non visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li//p[contains(text(),'" + ente + "')]"))));
        }
    }

    public void checkBannerAnnullamentoNotifica() {
        getWebDriverWait(10).withMessage("Il banner di annullamento della notifica non è presente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='cancelledAlertText']"))));
        getWebDriverWait(10).withMessage("Il banner di annullamento della notifica presenta la corretta descrizione").until(
                ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//div[@data-testid='cancelledAlertText']")), "textContent", "Questa notifica è stata annullata dall’ente mittente. Puoi ignorarne il contenuto."));
    }

    public void cliccareSuSincrona() {
        WebElement radioButtonSincrona = getWebDriverWait(10)
                .withMessage("radio Button  Sincrona non visibile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(@class, 'MuiFormControlLabel-root')]//span[text()='Sincrona']")));
        radioButtonSincrona.click();
    }

    public void inseriscoCodiceAvviso() {
        List<WebElement> inputFields = getWebDriverWait(10)
                .withMessage("Lista inseriscoCodiceAvviso non visibile")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("noticeCode")));
        //TODO bisogna cambiare in modo random le ultime 5 cifre
        long numero = 302010124463612500L;
        for (WebElement inputField : inputFields) {
            inputField.sendKeys(String.valueOf(numero));
            numero++;
        }
    }

    public void cliccareSuACaricoDelDestinatario() {
        WebElement caricoDestinatarioRadioButton = getWebDriverWait(10)
                .withMessage("radio Button  A carico del destinatario (puntuale) non visibile")
                .until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(@class, 'MuiFormControlLabel-root')]//span[text()='A carico del destinatario (puntuale)']")
        ));
        caricoDestinatarioRadioButton.click();
    }

    public void inserireCostoNotifica(String costo) {
        WebElement costoNotificaInput = getWebDriverWait(10)
                .withMessage("Campo inserireCostoNotifica non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("paFee")));
        costoNotificaInput.clear(); // Pulisci il campo se necessario
        costoNotificaInput.sendKeys(costo);
    }

    public void selezionareLaPercentuale(String percentuale) {
//        WebElement ivaDropdown = getWebDriverWait(10)
//                .withMessage("Non Visibile  ")
//                .until(ExpectedConditions.elementToBeClickable(By.id("vat")));
//        ivaDropdown.click();
//
//        WebElement ivaOption = getWebDriverWait(10)
//                .withMessage("radio Button  Nessun Pagamento non visibile")
//                .until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//li[@role='option' and @data-value='4']")
//        ));
//        ivaOption.click();
    }
    public void selezionaAvvisoPagoPA() {
        selezionaRadioButtonPerValore("PAGO_PA");
    }

    public void selezionaModelloF24() {
        selezionaRadioButtonPerValore("F24");
    }

    public void selezionaAvvisoPagoPAaddModelloF24() {
        selezionaRadioButtonPerValore("PAGO_PA_F24");
    }

    public void selezionaNessunPagamento() {
        selezionaRadioButtonPerValore("NOTHING");
    }

    public void selezionaInclusoNellAtto() {
        selezionaRadioButtonPerValore("FLAT_RATE");
    }
    public void selezionaACaricoDelDestinatario() {
        selezionaRadioButtonPerValore("DELIVERY_MODE");
    }

    public void selezionaModoAsincrono() {
        selezionaRadioButtonPerValore("ASYNC");
    }

    public void selezionaModoSincrono() {
        selezionaRadioButtonPerValore("SYNC");
    }


    public void selezionaRadioButtonPerValore(String value) {
        WebElement label = getWebDriverWait(10)
                .withMessage("Impossibile selezionare radion button: "+value)
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@type='radio' and @value='" + value + "']/ancestor::label")));
        label.click();
    }


    public void inserireCostoDiNotifica() {
        int centesimi = 10 + new Random().nextInt(91); // (100 - 10 + 1) = 91
        String valoreFormato = String.format(Locale.US, "%.2f", centesimi / 100.0);

        WebElement campoCosto = getWebDriverWait(10)
                .withMessage("Impossibile trovare Input Costo di notifica")
                .until(ExpectedConditions.elementToBeClickable(By.id("paFee")));

        campoCosto.clear();
        campoCosto.sendKeys(valoreFormato);
    }

    public void inserireIVA() {

        // Apri il menu
        WebElement campoIva  = getWebDriverWait(10)
                .withMessage("Inpossibile selezionare Iva")
                .until(ExpectedConditions.elementToBeClickable(By.id("vat")));
        campoIva .click();

        // Recupera tutte le opzioni visibili
        List<WebElement> opzioni = getWebDriverWait(10)
                .withMessage("Inpossibile selezionare il menu dell Iva")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//ul[@role='listbox']//li[@role='option']")
        ));

        // Sceglie un'opzione random e clicca
        WebElement sceltaRandom = opzioni.get(new Random().nextInt(opzioni.size()));
        sceltaRandom.click();
    }
}
