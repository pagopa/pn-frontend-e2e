package it.pn.frontend.e2e.pages.destinatario;

import com.google.gson.internal.LinkedTreeMap;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.DataPopulationConfig;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class DestinatarioPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("DestinatarioPage");


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

    public void selezionaAvvisoPagoPA(int numeroPosizioneDebitoria) {
        selezionaRadioButtonPerValore("PAGO_PA", numeroPosizioneDebitoria);
    }

    public void selezionaModelloF24(int numeroPosizioneDebitoria) {
        selezionaRadioButtonPerValore("F24",numeroPosizioneDebitoria);
    }

    public void selezionaAvvisoPagoPAaddModelloF24(int numeroPosizioneDebitoria) {
        selezionaRadioButtonPerValore("PAGO_PA_F24",numeroPosizioneDebitoria);
    }

    public void selezionaNessunPagamento(int numeroPosizioneDebitoria) {
        selezionaRadioButtonPerValore("NOTHING",numeroPosizioneDebitoria);
    }

    public void verificaAvvisoPagoPA(int numeroPosizioneDebitoria) {
        verificaRadioButtonPerValore("PAGO_PA", numeroPosizioneDebitoria);
    }

    public void verificaModelloF24(int numeroPosizioneDebitoria) {
        verificaRadioButtonPerValore("F24",numeroPosizioneDebitoria);
    }

    public void verificaAvvisoPagoPAaddModelloF24(int numeroPosizioneDebitoria) {
        verificaRadioButtonPerValore("PAGO_PA_F24",numeroPosizioneDebitoria);
    }

    public void verificaNessunPagamento(int numeroPosizioneDebitoria) {
        verificaRadioButtonPerValore("NOTHING",numeroPosizioneDebitoria);
    }

    public void selezionaInclusoNellAtto(int numeroPosizioneDebitoria) {
        selezionaRadioButtonPerValore("FLAT_RATE",numeroPosizioneDebitoria);
    }
    public void selezionaACaricoDelDestinatario(int numeroPosizioneDebitoria) {
        selezionaRadioButtonPerValore("DELIVERY_MODE",numeroPosizioneDebitoria);
    }

    public void selezionaModoAsincrono(int numeroPosizioneDebitoria) {
        selezionaRadioButtonPerValore("ASYNC",numeroPosizioneDebitoria);
    }

    public void selezionaModoSincrono(int numeroPosizioneDebitoria) {
        selezionaRadioButtonPerValore("SYNC",numeroPosizioneDebitoria);
    }


    public void selezionaRadioButtonPerValore(String value, int numeroPosizioneDebitoria) {
        WebElement label = getWebDriverWait(10)
                .withMessage("Impossibile selezionare radio button: "+value)
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("(//input[@type='radio' and @value='" + value + "']/ancestor::label)[" + numeroPosizioneDebitoria + "]")));
        label.click();
    }

    public void verificaRadioButtonPerValore(String value, int numeroPosizioneDebitoria) {
        WebElement label = getWebDriverWait(10)
                .withMessage("Valore radio button non cliccabile: "+value)
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("(//input[@type='radio' and @value='" + value + "']/ancestor::label)[" + numeroPosizioneDebitoria + "]")));
        Assertions.assertTrue(label.findElement(By.tagName("input")).isSelected(), "Valore radio button non previsto: " + value);
    }

    public void verificaPresenzaSezionePosizioneDebitoria() {
        getWebDriverWait(10).withMessage("Sezione Posizione Debitoria non è visibile").until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='payments-type-choice']")));
        log.info("Sezione Posizione Debitoria presente");
    }

    public void verificaPresenzaSezioneDettaglioPosizioneDebitoria() {
        getWebDriverWait(10).withMessage("Sezione Dettaglio Posizione Debitoria non è visibile").until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@data-testid='debtPositionDetailForm']")));
        log.info("Sezione Dettaglio Posizione Debitoria presente");
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

    public  List<String> inserireTuttiCodiceAvviso() {
        String[] prefissi = {"3020101244636", "3020401244637"};
        Random random = new Random();
        List<String> codiciAvvisoInseriti = new ArrayList<>();

        List<WebElement> inputCodiciAvviso = getWebDriverWait(10)
                .withMessage("Impossibile trovare input con id='noticeCode'")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#noticeCode")));

        if (inputCodiciAvviso.isEmpty()) {
            logger.warn("Nessun campo 'noticeCode' trovato.");
            return codiciAvvisoInseriti;
        }

        for (WebElement input : inputCodiciAvviso) {
            String prefisso = prefissi[random.nextInt(prefissi.length)];
            String parteRandomStr = String.format("%05d", random.nextInt(100000));
            String codiceAvviso = prefisso + parteRandomStr;

            try {
                getWebDriverWait(5).until(ExpectedConditions.elementToBeClickable(input));
                input.click();
                input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                input.sendKeys(Keys.DELETE);
                input.sendKeys(codiceAvviso);
                codiciAvvisoInseriti.add(codiceAvviso);
            } catch (Exception e) {
                Assertions.fail("Errore durante l'inserimento del codice nel campo noticeCode", e);
            }
        }
        return codiciAvvisoInseriti;
    }

    public void inserireTuttiCodiceFiscaleEnte() {
        List<WebElement> inputCodiciFiscali = getWebDriverWait(10)
                .withMessage("Impossibile trovare i campi 'Codice fiscale ente creditore'")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#creditorTaxId")));

        if (inputCodiciFiscali.isEmpty()) {
            logger.warn("Nessun campo 'creditorTaxId' trovato.");
            return;
        }

        for (WebElement input : inputCodiciFiscali) {
            try {
                getWebDriverWait(5).until(ExpectedConditions.elementToBeClickable(input));
                input.click();
                input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                input.sendKeys(Keys.DELETE);
                input.sendKeys("77777777777");
            } catch (Exception e) {
                Assertions.fail("Errore durante l'inserimento del Codice fiscale ente creditore", e);
            }
        }

    }

    public void inserireTuttiCodiceAvvisoErrati() {

        List<WebElement> inputCodiciAvviso = getWebDriverWait(10)
                .withMessage("Impossibile trovare input con id='noticeCode'")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#noticeCode")));

        if (inputCodiciAvviso.isEmpty()) {
            logger.warn("Nessun campo 'noticeCode' trovato.");
            return;
        }

        for (WebElement input : inputCodiciAvviso) {

            try {
                getWebDriverWait(5).until(ExpectedConditions.elementToBeClickable(input));
                input.click();
                input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                input.sendKeys(Keys.DELETE);
                input.sendKeys("Error");
            } catch (Exception e) {
                Assertions.fail("Errore durante l'inserimento del codice nel campo noticeCode", e);
            }
        }
    }

    public void inserireTuttiCodiceFiscaleEnteErrati() {
        List<WebElement> inputCodiciFiscali = getWebDriverWait(10)
                .withMessage("Impossibile trovare i campi 'Codice fiscale ente creditore'")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#creditorTaxId")));

        if (inputCodiciFiscali.isEmpty()) {
            logger.warn("Nessun campo 'creditorTaxId' trovato.");
            return;
        }

        for (WebElement input : inputCodiciFiscali) {
            try {
                getWebDriverWait(5).until(ExpectedConditions.elementToBeClickable(input));
                input.click();
                input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                input.sendKeys(Keys.DELETE);
                input.sendKeys("Error");
            } catch (Exception e) {
                Assertions.fail("Errore durante l'inserimento del Codice fiscale ente creditore", e);
            }
        }

    }


    public void inserireIVA() {
        WebElement campoIva  = getWebDriverWait(10)
                .withMessage("Inpossibile selezionare Iva")
                .until(ExpectedConditions.elementToBeClickable(By.id("vat")));
        campoIva .click();
        List<WebElement> opzioni = getWebDriverWait(10)
                .withMessage("Inpossibile selezionare il menu dell Iva")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//ul[@role='listbox']//li[@role='option']")
        ));

        WebElement sceltaRandom = opzioni.get(new Random().nextInt(opzioni.size()));
        sceltaRandom.click();
    }

    public void selezionaApplicaCostoNotifica() {
        List<WebElement> switchContainers = getWebDriverWait(10)
                .withMessage("Impossibile trovare uno o più switch 'Applica costo di notifica'")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//input[@id='applyCost']/parent::span")));

        if (switchContainers.isEmpty()) {
            logger.warn("Nessuno switch 'Applica costo di notifica' trovato.");
            return;
        }

        for (WebElement switchContainer : switchContainers) {
            try {
                getWebDriverWait(5).until(ExpectedConditions.elementToBeClickable(switchContainer));
                switchContainer.click();
            } catch (Exception e) {
                Assertions.fail("Errore cliccando su Applica costo di notifica: "+ e.getMessage());
            }
        }
    }


    public void clickSuAggiungiAltroModelloF24(int posizione) {
        List<WebElement> pulsanteAggiungiF24 = getWebDriverWait(10)
                .withMessage("Impossibile trovare il tasto Aggiungi Altro ModelloF24")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[data-testid='add-new-f24']")));

        WebElement bottoneDesideratoAggiungiF24 = pulsanteAggiungiF24.get(posizione);

        js().executeScript("arguments[0].scrollIntoView(true);", bottoneDesideratoAggiungiF24);
        bottoneDesideratoAggiungiF24.click();
    }

    public void clickSuAggiungiCodiceDiAvvisoPagoPa(int posizione) {

        List<WebElement> bottoniAvvisoPagoPa = getWebDriverWait(10)
                .withMessage("Impossibile trovare i bottoni 'Aggiungi codice di avviso pagoPA'")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("button[data-testid='add-new-pagopa']")));

        WebElement bottoneDesideratoAvvisoPagoPa = bottoniAvvisoPagoPa.get(posizione);

        js().executeScript("arguments[0].scrollIntoView(true);", bottoneDesideratoAvvisoPagoPa);
        bottoneDesideratoAvvisoPagoPa.click();
    }



    public void inserisciTitoloDocumentoPosizioneDebitoria(int numNotifiche) {

        StringBuilder fileName = new StringBuilder("Documento_");

        List<WebElement> campiTitolo = getWebDriverWait(10)
                .withMessage("Impossibile inserire il Titolo Documento num: "+ (numNotifiche -1))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("input[name='name']"), numNotifiche -1));

        WebElement campo = campiTitolo.get(numNotifiche -1);
        campo.clear();
        campo.sendKeys(fileName.append(numNotifiche));

    }


    public void clickSuAggiungiUnAltroDocumento() {

        WebElement bottoneAggiungi = getWebDriverWait(10)
                .withMessage("Impossibile Cliccare su Aggiungi un altro documento")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-testid='add-another-doc']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bottoneAggiungi);
        Actions actions = new Actions(driver);
        actions.moveToElement(bottoneAggiungi).click().perform();
    }

    public void verificaCodiciAvvisi(List<String> codiciAvvisi) {
        if (codiciAvvisi == null || codiciAvvisi.isEmpty()) {
            logger.info("Nessun codice avviso da verificare.");
            return;
        }

        List<WebElement> elementiCodiceAvviso = getWebDriverWait(10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("[data-testid='pagopa-item'] .MuiTypography-caption-semibold")));

        List<String> codiciTrovati = elementiCodiceAvviso.stream()
                .map(WebElement::getText)
                .filter(text -> text.matches("\\d{18}")) // prende solo stringhe di 18 cifre (codici avviso)
                .toList();

        logger.info("Codici trovati sulla pagina: {}", codiciTrovati);

        // Verifica che siano presenti tutti quelli attesi (e solo quelli)
        Assertions.assertEquals(new HashSet<>(codiciAvvisi), new HashSet<>(codiciTrovati),
                "I codici avviso presenti non corrispondono a quelli attesi.");
    }
}
