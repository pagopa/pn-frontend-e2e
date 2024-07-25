package it.pn.frontend.e2e.pages.destinatario;

import com.google.gson.internal.LinkedTreeMap;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.exceptions.RestNotificationException;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.model.documents.Document;
import it.pn.frontend.e2e.model.notification.NewNotificationRequest;
import it.pn.frontend.e2e.model.notification.NewNotificationResponse;
import it.pn.frontend.e2e.model.enums.NotificationFeePolicyEnum;
import it.pn.frontend.e2e.model.enums.PhysicalCommunicationTypeEnum;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.rest.RestRaddAlternative;
import it.pn.frontend.e2e.utility.NotificationBuilder;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class DestinatarioPage extends BasePage {

    @Getter
    @Setter
    private NewNotificationRequest notificationRequest;
    private final NotificationSingleton notificationSingleton = NotificationSingleton.getInstance();
    private final RestNotification restNotification = new RestNotification();
    private final RestRaddAlternative restRaddAlternative = new RestRaddAlternative();
    private static final NotificationBuilder notificationBuilder = new NotificationBuilder();
    private static int destinatariNumber;
    private final WebDriver driver = Hooks.driver;

    //Questa classe è utilizzata per metodi in comune tra PF e PG
    public DestinatarioPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "startDate")
    WebElement dataInizioField;
    @FindBy(id = "endDate")
    WebElement dataFineField;
    @FindBy(id = "side-item-Notifiche")
    WebElement sideItemNotificheButton;
    @FindBy(id = "notificationsTable.body.row")
    List<WebElement> listaNotificheDelegante;

    public void inserimentoDataErrato() {
        String data = "01/01/1111";
        getWebDriverWait(10).withMessage("Il campo data inizio non è visibile").until(ExpectedConditions.visibilityOfAllElements(this.dataInizioField));
        dataInizioField.click();
        dataInizioField.sendKeys(data);
        getWebDriverWait(3).withMessage("Il valore della data che si vuole inserire non corrisponde").until(ExpectedConditions.attributeToBe(this.dataInizioField, "value", data));
        getWebDriverWait(10).withMessage("Il campo data fine non è visibile").until(ExpectedConditions.visibilityOfAllElements(this.dataFineField));
        dataFineField.click();
        dataFineField.sendKeys(data);
        getWebDriverWait(3).withMessage("Il valore della data che si vuole inserire non corrisponde").until(ExpectedConditions.attributeToBe(this.dataFineField, "value", data));
    }

    public boolean isDateBoxInvalid() {
        final String isTextboxInvalid = "true";
        boolean invalidBoxDate = true;
        try {
            getWebDriverWait(10).withMessage("Il campo data inizio non è visibile").until(ExpectedConditions.visibilityOfAllElements(this.dataInizioField, this.dataFineField));
            String ariaInvalidInizio = dataInizioField.getAttribute("aria-invalid");
            String ariaInvalidFine = dataFineField.getAttribute("aria-invalid");
            if (isTextboxInvalid.equals(ariaInvalidInizio) || isTextboxInvalid.equals(ariaInvalidFine)) {
                log.info("Almeno un campo data è in stato invalido");
            } else {
                log.error("Nessuno dei campi data è passato allo stato invalido");
                Assert.fail("Nessuno dei campi data è passato allo stato invalido");
            }
        } catch (TimeoutException e) {
            log.error("Campi data non visualizzati correttamente con errore: " + e.getMessage());
            Assert.fail("Campi data non visualizzati correttamente con errore: " + e.getMessage());
        }
        return invalidBoxDate;
    }

    public void clickButtonNotificheDelegateOnSideMenu(String nomeDelegante) {
        log.info("verifica bottone notifiche nel layout");

        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(this.sideItemNotificheButton));
        sideItemNotificheButton.click();

        String id = "side-item-" + nomeDelegante;
        By buttonNotificheOnSideMenu = By.id(id);
        getWebDriverWait(10).withMessage("bottone notifiche nel layout non visibile").until(ExpectedConditions.visibilityOfElementLocated(buttonNotificheOnSideMenu));
        this.js().executeScript("arguments[0].click()", this.element(buttonNotificheOnSideMenu));

    }

    public void clickSulDettaglioNotificaDelegante() {
        WebElement singolaNotificaDelegante = listaNotificheDelegante.get(0);
        getWebDriverWait(10).withMessage("la prima notifica della tabella non è visibile").until(ExpectedConditions.visibilityOf(singolaNotificaDelegante));
        log.info("Si clicca sulla prima notifica del delegante");
        singolaNotificaDelegante.click();
    }

    public void clickProdotto(String xpath) {
        By prodottoDestinatario = By.xpath(xpath);
        getWebDriverWait(10).withMessage("prodotto non disponbile").until(ExpectedConditions.visibilityOfElementLocated(prodottoDestinatario));
        element(prodottoDestinatario).click();
    }

    public void clickTuttiGliEnti() {
        By tuttiGliEnti = By.id("tutti-gli-enti-selezionati");
        getWebDriverWait(10).withMessage("Il radio button 'tutti gli enti selezionati' non è visibile").until(ExpectedConditions.visibilityOfElementLocated(tuttiGliEnti));
        element(tuttiGliEnti).click();
    }

    public void clickSoloEntiSelezionati() {
        By soloEntiSelezionati = By.id("enti-selezionati");
        getWebDriverWait(10).withMessage("Il radio button 'solo enti selezionati' non è visibile").until(ExpectedConditions.visibilityOfElementLocated(soloEntiSelezionati));
        element(soloEntiSelezionati).click();
    }

    public void clickListaEnti() {
        By listaEnti = By.id("enti");
        getWebDriverWait(10).withMessage("Il menù a tendina degli enti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(listaEnti));
        element(listaEnti).click();
    }

    public void controlloEntiRadice(List<String> enti) {
        for (String ente : enti) {
            By enteRadice = By.xpath("//li//p[contains(text(),'" + ente + "')]");
            getWebDriverWait(10).withMessage("Ente: " + ente + " non visibile").until(ExpectedConditions.visibilityOfElementLocated(enteRadice));
        }
    }
    public void checkBannerAnnullamentoNotifica() {
            By bannerAnnullamentoNotificaBy = By.xpath("//div[@data-testid='cancelledAlertText']");
            getWebDriverWait(10).withMessage("Il banner di annullamento della notifica non è presente").until(ExpectedConditions.visibilityOfElementLocated(bannerAnnullamentoNotificaBy));
            getWebDriverWait(10).withMessage("Il banner di annullamento della notifica presenta la corretta descrizione").until(
                    ExpectedConditions.attributeToBe(bannerAnnullamentoNotificaBy, "textContent", "Questa notifica è stata annullata dall’ente mittente. Puoi ignorarne il contenuto."));
    }

    public void checkCreateNewNotification() throws RestNotificationException {
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
                notificationRequest.setPaProtocolNumber(WebTool.generatePaProtocolNumber());
                attempt++;
            }
        }
        log.error("Errore nella creazione della notifica per PF dopo {} tentativi", maxAttempts);
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }

    public void aggiuntaDestinatarioANotifica(Map<String, String> datiDestinatario){
        Assert.assertTrue("Non è possibile aggiungere un ulteriore destinatario",destinatariNumber <= 4);
            log.info("Si procede con l'inserimento del destinatario nella notifica");
            String costiNotifica = "false";
            if (notificationRequest.getNotificationFeePolicy() == NotificationFeePolicyEnum.DELIVERY_MODE) {
                costiNotifica = "true";
            }
            notificationRequest.setRecipients(notificationBuilder.destinatarioBuilder(datiDestinatario, notificationRequest.getRecipients()));
            notificationRequest.getRecipients().get(destinatariNumber).setPayments(notificationBuilder.paymentsBuilder(Integer.parseInt(datiDestinatario.getOrDefault("avvisoPagoPa", "0")), Integer.parseInt(datiDestinatario.getOrDefault("F24", "0")), costiNotifica));
            destinatariNumber++;
    }

    public void inizializzazioneDatiNotifica(Map<String, String> datiNotifica){
        PhysicalCommunicationTypeEnum modelloNotifica = notificationBuilder.modelloNotifica(datiNotifica.get("modello"));
        NotificationFeePolicyEnum feePolicy = notificationBuilder.notificaFeePolicy(datiNotifica.getOrDefault("costiNotifica", "false"));
        ArrayList<Document> documents = notificationBuilder.preloadDocument(Integer.parseInt(datiNotifica.get("documenti")));
        notificationRequest = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), datiNotifica.getOrDefault("oggettoNotifica", "PAGAMENTO RATA IMU"), null, documents, modelloNotifica, "010202N", feePolicy);
    }

    public void initRadd() {
        restRaddAlternative.startTransactionRaddAlternative();
    }
}
