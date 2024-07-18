package it.pn.frontend.e2e.pages.mittente;

import com.google.gson.internal.LinkedTreeMap;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class PiattaformaNotifichePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("notificaMittentePagoPA");
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
    private final NotificationSingleton notificationSingleton = NotificationSingleton.getInstance();

    @FindBy(id = "recipientId")
    WebElement cfTextField;

    @FindBy(id = "filter-button")
    WebElement filtraNotificaButtonMittente;

    @FindBy(id = "filter-notifications-button")
    WebElement filtraNotificaButton;

    @FindBy(id = "iunMatch")
    WebElement codiceIUNTextField;

    @FindBy(id = "startDate")
    WebElement dataInizioField;

    @FindBy(id = "endDate")
    WebElement dataFineField;

    @FindBy(id = "status")
    WebElement statoNotificaField;

    @FindBy(id = "side-item-API Key")
    WebElement apiKeyButton;

    @FindBy(id = "rows-per-page")
    WebElement numeroNotificheButton;

    @FindBy(id = "next")
    WebElement frecciaPaginaSuccessiva;

    @FindBy(id = "page2")
    WebElement secondPage;

    @FindBy(id = "page3")
    WebElement pageNumberButton;

    @FindBy(id = "pageSize-50")
    WebElement numberElement;

    @FindBy(id = "step-submit")
    WebElement continuaButtonDisabled;

    @FindBy(id = "subject")
    WebElement oggettoDellaNotificaTextField;

    @FindBy(id = "subject-helper-text")
    WebElement errorMessage;

    @FindBy(id = "breadcrumb-indietro-button")
    WebElement indietroButton;

    @FindBy(id = "button-exit")
    WebElement esciButton;

    @FindBy(id = "file-upload-error")
    WebElement estenzioneSbagliataMessage;

    @FindBy(id = "new-notification-btn")
    WebElement inviaNuovaNotificaButton;

    @FindBy(xpath = "//form[contains(@data-testid,'preliminaryInformationsForm')]")
    WebElement preliminaryInformationsForm;

    @FindBy(xpath = "//input[@aria-invalid='true']")
    List<WebElement> inputsError;

    @FindBy(id = "notifications-table")
    WebElement notificationsTable;

    @FindBy(id = "notificationsTable.body.row")
    List<WebElement> notificationsTableLines;

    @FindBy(id = "message")
    WebElement erroreMessaggio;


    public PiattaformaNotifichePage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadPiattaformaNotifichePAPage() {
        try {
            By notificheTitle = By.id("Notifiche-page");
            this.getWebDriverWait(10).withMessage("Il bottone invia notifica non visibile").until(ExpectedConditions.visibilityOf(this.inviaNuovaNotificaButton));
            this.getWebDriverWait(10).withMessage("Il titolo non è visibile").until(ExpectedConditions.visibilityOfElementLocated(notificheTitle));
            logger.info("Piattaforma Notifiche Page caricata");
        } catch (TimeoutException e) {
            logger.error("Piattaforma Notifiche Page non caricata con errore : " + e.getMessage());
            Assert.fail("Piattaforma Notifiche Page non caricata con errore : " + e.getMessage());
        }
    }

    public void insertCodiceFiscale(String codiceFiscale) {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(this.cfTextField));
            this.cfTextField.click();
            this.cfTextField.sendKeys(codiceFiscale);
            this.getWebDriverWait(3).until(ExpectedConditions.attributeToBe(this.cfTextField, "value", codiceFiscale));
            logger.info("Codice Fiscale inserito correttamente");
        } catch (TimeoutException e) {
            logger.error("Codice Fiscale Non inserito con errore: " + e.getMessage());
            Assert.fail("Codice Fiscale Non inserito con errore: " + e.getMessage());
        }

    }

    public void selectFiltraNotificaButtonMittente() {
        getWebDriverWait(10).withMessage("Il bottone filtra non è cliccabile").until(elementToBeClickable(filtraNotificaButtonMittente));
        filtraNotificaButtonMittente.click();
        logger.info("Bottone filtra, nella pagina del mittente, cliccato correttamente");
    }

    public void selectFiltraNotificaButtonDestinatario() {
        getWebDriverWait(10).withMessage("Il filtro non è cliccabile").until(elementToBeClickable(filtraNotificaButton));
        filtraNotificaButton.click();
        logger.info("Bottone filtra, nella pagina notifiche del delegato, cliccato correttamente");
    }

    public int getListaCf(String cfInserito) {
        try {
            By cfListBy = By.xpath("//p[contains(text(),'" + cfInserito + "')]");
            attesaCaricamentoPagina();
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(cfListBy));
            logger.info("Codici fiscali trovati correttamente");
            return this.elements(cfListBy).size();
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Codici fiscali non trovati " + e.getMessage());
            Assert.fail("Codici fiscali non trovati " + e.getMessage());
            return 0;
        }

    }

    public void attesaCaricamentoPagina() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificaEsistenzaEPassaggioPagina() {
        this.js().executeScript("window.scrollBy(0,document.body.scrollHeight)");
        try {
            this.getWebDriverWait(10).withMessage("Il bottone pagina 2 non è visibile").until(ExpectedConditions.visibilityOf(secondPage));
            logger.info("Bottone pagina 2 trovato");
            secondPage.click();
            return true;
        } catch (TimeoutException e) {
            logger.error("bottone pagina 2 non trovata con errore: " + e.getMessage());
            return false;
        }
    }

    public void inserimentoCodiceIUN(String codiceIUN) {
        getWebDriverWait(10).withMessage("Il campo per l'inserimento del codice IUN non è visibile").until(ExpectedConditions.visibilityOf(codiceIUNTextField));
        codiceIUNTextField.click();
        codiceIUNTextField.sendKeys(codiceIUN);
        logger.info("Codice IUN inserito");
    }

    public boolean verificaCodiceIUN(String codiceIUNInserito) {
        WebElement codiceIUN = getCodiceIUN(codiceIUNInserito);
        return codiceIUN != null;
    }

    private WebElement getCodiceIUN(String codiceIUNInserito) {
        try {
            By codiceIUNBy = By.xpath("//button[contains(text(),'" + codiceIUNInserito + "')]");
            getWebDriverWait(30).withMessage("Il codice IUN: " + codiceIUNInserito + " non è presente")
                    .until(ExpectedConditions.visibilityOfElementLocated(codiceIUNBy));
            return this.element(codiceIUNBy);
        } catch (TimeoutException e) {
            return null;
        }
    }

    public boolean controlloDateInserite(String dataInerita) {
        String[] date = dataInerita.split("/");
        return date[0].length() == 2 && date[1].length() == 2 && date[2].length() == 4;
    }

    public void inserimentoArcoTemporale(String da, String a) {
        this.getWebDriverWait(10)
                .until(ExpectedConditions.visibilityOfAllElements(this.dataInizioField, this.dataFineField));

        this.dataInizioField.click();
        this.dataInizioField.sendKeys(da);
        this.getWebDriverWait(3).until(ExpectedConditions.attributeToBe(this.dataInizioField, "value", da));
        this.dataFineField.click();
        this.dataFineField.sendKeys(a);
        this.getWebDriverWait(3).until(ExpectedConditions.attributeToBe(this.dataFineField, "value", a));
    }

    public int getListDate() {
        By dataListBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]");
        attesaCaricamentoPagina();
        this.getWebDriverWait(30).withMessage("Nessuna data trovata").until(ExpectedConditions.visibilityOfElementLocated(dataListBy));
        logger.info("Date trovate correttamente");

        return this.elements(dataListBy).size();
    }

    public void selezionareStatoNotifica(String statoInserito) {
        this.statoNotificaField.click();
        try {
            By statoNotificaBy = By.xpath("//li[contains(@data-value,'" + statoInserito + "')]");
            this.getWebDriverWait(30).until(elementToBeClickable(statoNotificaBy));

            if (this.element(statoNotificaBy).isDisplayed()) {
                this.js().executeScript("arguments[0].click()", this.element(statoNotificaBy));
            } else {
                this.js().executeScript("arguments[0].scrollIntoView(true);", this.element(statoNotificaBy));
                this.js().executeScript("arguments[0].click()", this.element(statoNotificaBy));
            }
            logger.info("Stato notifica selezionato correttamente");
        } catch (TimeoutException e) {
            logger.error("Stato notifica NON trovata con errore: " + e.getMessage());
            Assert.fail("Stato notifica NON trovata con errore: " + e.getMessage());
        }
    }

    public int getListStato(String statoNotifica) {
        try {
            By statoNotificaBy = By.xpath("//span[contains(text(),'" + statoNotifica + "')]");
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBy));
            return this.elements(statoNotificaBy).size();
        } catch (TimeoutException e) {
            return 0;
        }
    }

    public boolean IsAnAdvancedStatus() {
        By statoNotificaBy = By.xpath("//div[contains(@id,'status-chip-')]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBy));
        String status = this.element(statoNotificaBy).getText();
        return !status.equalsIgnoreCase("Depositata");
    }

    public void selezionaNotifica() {
        waitLoadPage();
        try {
            By notificaBy = By.id("notificationsTable.body.row");
            attesaCaricamentoPagina();
            getWebDriverWait(30).withMessage("La tabella delle notifiche non è caricata correttamente").until(elementToBeClickable(notificaBy));
            List<WebElement> notifiche = this.elements(notificaBy);
            notifiche.get(0).click();
        } catch (TimeoutException e) {
            logger.error("Notifica non trovata con errore: " + e.getMessage());
            Assert.fail("Notifica non trovata con errore: " + e.getMessage());
        }
    }

    public void selezionaNotificaIUN(String IUN) {
        String variabileAmbiente = System.getProperty("environment");
        driver.navigate().to("https://selfcare." + variabileAmbiente + ".notifichedigitali.it/dashboard/" + IUN + "/dettaglio");
    }

    public void selectInviaUnaNuovaNotificaButton() {
        this.getWebDriverWait(10).withMessage("Il bottone invia notifica non è cliccabile").until(elementToBeClickable(inviaNuovaNotificaButton));
        this.inviaNuovaNotificaButton.click();
    }


    public void waitLoadRefreshPage() {
        WebTool.waitTime(5);
    }

    public String ricercaNotifica(String oggettoDellaNotifica, String statoNotifica) {

        String codiceIUN = "";

        int posizioneNotifica = verificaEsistenzaNotifica(oggettoDellaNotifica, statoNotifica);
        logger.info("la posizione della notifica è uguale: " + posizioneNotifica);
        if (posizioneNotifica != -1) {

            By listaCodiceIUNBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1cpwezh')]");
            List<WebElement> codiciIUNList = this.elements(listaCodiceIUNBy);
            codiceIUN = codiciIUNList.get(posizioneNotifica).getText();
        }
        return codiceIUN;
    }

    public int verificaEsistenzaNotifica(String oggettoDellaNotifica, String statoNotifica) {

        List<WebElement> listaOggetti = ricercaListaOggetti();
        List<WebElement> listaStati = ricercaListaStati();

        if (listaOggetti != null && listaStati != null) {

            for (int i = 0; i < listaOggetti.size(); i++) {
                logger.info("ciclo for di verifica esistenza");
                logger.info(listaOggetti.get(i).getText() + " " + oggettoDellaNotifica);
                logger.info(listaStati.get(i).getText() + " " + statoNotifica);
                if (listaOggetti.get(i).getText().equals(oggettoDellaNotifica) &&
                        listaStati.get(i).getText().equals(statoNotifica)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public List<WebElement> ricercaListaOggetti() {
        try {
            By listaOggettiBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1sdct2p')]");
            return this.elements(listaOggettiBy);
        } catch (TimeoutException e) {
            logger.info("lista oggetti ancora non presenti");
            return null;
        }
    }

    public List<WebElement> ricercaListaStati() {
        try {
            By listaOggettiBy = By.xpath("//span[contains(@class,'MuiChip-label MuiChip-labelMedium css-11cvqrr')]");
            return this.elements(listaOggettiBy);
        } catch (TimeoutException e) {
            logger.info("lista stati ancora non presenti");
            return null;
        }
    }

    public void selezionareLaVoceApiKey() {
        getWebDriverWait(30).withMessage("la voce api key non è cliccabile").until(elementToBeClickable(this.apiKeyButton));
        this.js().executeScript("arguments[0].click()", this.apiKeyButton);
    }


    public String conversioneFormatoDate(String date) {
        String[] dateInserita = date.split("-");
        return dateInserita[2] + "/" + dateInserita[1] + "/" + dateInserita[0];
    }

    public int controlloNumeroRisultatiDate() {
        By dataListBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]");
        attesaCaricamentoPagina();
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(dataListBy));
        logger.info("Date trovate correttamente");

        return this.elements(dataListBy).size();
    }

    public void inserimentoData(String dataInserita) {
        getWebDriverWait(10).withMessage("il campo data non è visibile nella pagina").until(ExpectedConditions.visibilityOf(this.dataInizioField));
        this.dataInizioField.click();
        this.dataInizioField.sendKeys(dataInserita);
        this.getWebDriverWait(3).until(ExpectedConditions.attributeToBe(this.dataInizioField, "value", dataInserita));
    }

    public boolean verificaEsistenzaRisultati() {
        try {
            By messaggioNessunRisultatoby = By.id("call-to-action-first");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(messaggioNessunRisultatoby));
            logger.info("Messaggio visualizzato correttamente");
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void siVisualizzaCorrettamenteIlCFField() {
        try {
            By codiceFiscaleField = By.id("recipientId");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiceFiscaleField));
            logger.info("Il campo di codice fiscale si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il campo di codice fiscale NON si visualizza correttamente con errore : " + e.getMessage());
            Assert.fail("Il campo di codice fiscale NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteIlCodiceIUNField() {
        try {
            By codiceIUNField = By.id("iunMatch");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiceIUNField));
            logger.info("Il campo di codice iun si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il campo di codice iun NON si visualizza correttamente con errore : " + e.getMessage());
            Assert.fail("Il campo di codice iun NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteLoStatoField() {
        try {
            By statoField = By.id("status");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoField));
            logger.info("Il campo dello stato si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il campo dello stato NON si visualizza correttamente con errore : " + e.getMessage());
            Assert.fail("Il campo dello stato NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteLaDataInzioField() {
        try {
            By dataDaField = By.id("startDate");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(dataDaField));
            logger.info("Il campo della data di inizio si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il campo della data di inizio NON si visualizza correttamente con errore : " + e.getMessage());
            Assert.fail("Il campo della data di inizio NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteLaDataFineField() {
        try {
            By dataAField = By.id("endDate");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(dataAField));
            logger.info("Il campo della data di fine si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il campo della data di fine NON si visualizza correttamente con errore : " + e.getMessage());
            Assert.fail("Il campo della data di fine NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public boolean verificaEsistenzaCFNotifiche() {
        By cfFiealdBy = By.xpath("//td[button/p[contains(@class,'MuiTypography-root MuiTypography-body2')]]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(cfFiealdBy));
        return !this.elements(cfFiealdBy).isEmpty();
    }

    public boolean verificaEsistenzaCodiceIUNNotifiche() {
        By codiciIUNBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1cpwezh')]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiciIUNBy));
        return !this.elements(codiciIUNBy).isEmpty();
    }

    public boolean verificaEsistenzaGruppoNotifiche() {
        By gruppiBy = By.xpath("//td[button/div/span[contains(@class,'css-t63gu0')]]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(gruppiBy));
        return !this.elements(gruppiBy).isEmpty();
    }

    public boolean verificaEsistenzaStatoNotifiche() {
        By statiBy = By.xpath("//td[button/div/div[contains(@data-testid,'statusChip-')]]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statiBy));
        return !this.elements(statiBy).isEmpty();
    }


    public int getNRighe() {
        By nRigheBy = By.xpath("//tr[@id='notificationsTable.body.row']");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nRigheBy));
        return this.elements(nRigheBy).size();
    }

    public String numeroNotifiche() {
        By nRigheBy = By.id("rows-per-page");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nRigheBy));
        return this.element(nRigheBy).getText();
    }

    public void controlloOrdineNotifiche() {
        List<WebElement> listaDate = getListaDate();
        LocalDate dataSuccessiva;
        final String dataOggi = "Oggi";
        LocalDate dataPrecedente;
        if (listaDate != null) {
            for (int i = 0; i < listaDate.size() - 1; i++) {
                String dataDopo = listaDate.get(i).getText();
                String dataPrima = listaDate.get(i + 1).getText();
                if (dataOggi.equals(dataDopo)) {
                    dataSuccessiva = LocalDate.now();
                } else {
                    String[] dateA = dataDopo.split("/");
                    dataDopo = dateA[2] + "-" + dateA[1] + "-" + dateA[0];
                    dataSuccessiva = LocalDate.parse(dataDopo);
                }
                if (dataOggi.equals(dataPrima)) {
                    dataPrecedente = LocalDate.now();
                } else {
                    String[] dateA = dataPrima.split("/");
                    dataPrima = dateA[2] + "-" + dateA[1] + "-" + dateA[0];
                    dataPrecedente = LocalDate.parse(dataPrima);
                }
                if (dataSuccessiva.isBefore(dataPrecedente)) {
                    logger.error("Le date non sono ordinate dal più recente");
                    Assert.fail("Le date non sono ordinate dal più recente");
                    return;
                }
            }
        }
        logger.info("Le date sono visualizzate correttamente");
    }

    private List<WebElement> getListaDate() {
        try {
            By dataListBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]");
            attesaCaricamentoPagina();
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(dataListBy));
            logger.info("Date trovate correttamente");

            return this.elements(dataListBy);

        } catch (TimeoutException e) {
            logger.error("Date NON trovate con errore: " + e.getMessage());
            Assert.fail("Date NON trovate con errore: " + e.getMessage());
            return null;
        }
    }

    public void siScrollaFinoAllaFineDellaPagina() {
        this.getWebDriverWait(30).withMessage("il bottone che indica il numero delle notifiche non è visibile")
                .until(ExpectedConditions.visibilityOf(this.numeroNotificheButton));
        if (!numeroNotificheButton.isDisplayed()) {
            this.js().executeScript("arguments[0].scrollIntoView(true);", numeroNotificheButton);
        }
    }

    public void siCambiaPaginaUtilizzandoUnaFrecetta() {
        this.getWebDriverWait(60).withMessage("il bottone pagina successiva non è cliccabile")
                .until(ExpectedConditions.visibilityOf(this.frecciaPaginaSuccessiva));
        if (!frecciaPaginaSuccessiva.isDisplayed()) {
            this.js().executeScript("arguments[0].scrollIntoView(true);", numeroNotificheButton);
        }
        frecciaPaginaSuccessiva.click();
    }

    public void siCambiaPaginaUtilizzandoUnNumero() {
        if (!pageNumberButton.isDisplayed()) {
            this.js().executeScript("arguments[0].scrollIntoView(true);", pageNumberButton);
        }
        this.js().executeScript("arguments[0].click()", this.pageNumberButton);
    }

    public void siCambiaIlNumeroElementiVisualizzatiAttraversoIlFiltro() {
        this.getWebDriverWait(10)
                .withMessage("Il pulsante 'righe per pagina' non è presente")
                .until(ExpectedConditions.visibilityOf(this.numeroNotificheButton));
        vaiInFondoAllaPagina();
        this.numeroNotificheButton.click();
        this.getWebDriverWait(10)
                .withMessage("Il pulsante '50' per assegnare il numero di notifiche per pagina non è presente")
                .until(ExpectedConditions.visibilityOf(this.numberElement));
        this.numberElement.click();
    }

    public void clickContinuaDisabled() {
        this.continuaButtonDisabled.isDisplayed();
    }

    public void inserimentoOggettoNotificaErrato(String oggettoDellaNotifica) {
        this.scrollToElementClickAndInsertText(this.oggettoDellaNotificaTextField, oggettoDellaNotifica);
    }

    public boolean errorMessage() {
        return this.errorMessage.isDisplayed();
    }

    public void clickIndietroButton() {
        this.getWebDriverWait(30).withMessage("Il bottone indietro non è  cliccabile")
                .until(elementToBeClickable(this.indietroButton));
        this.indietroButton.click();
    }

    public void vuoiUscirePopUp() {
        try {
            By vuoiUscirePUBy = By.xpath("//h2[contains(text(),'Vuoi uscire?')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(vuoiUscirePUBy));
            logger.info("Si visualizza il pop up vuoi uscire");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza il pop up vuoi uscire con errore:" + e.getMessage());
            Assert.fail("Non si visualizza il pop up vuoi uscire con errore:" + e.getMessage());
        }
    }

    public void clickSuEsci() {
        this.getWebDriverWait(30).withMessage("Il bottone esci non è  cliccabile")
                .until(elementToBeClickable(this.esciButton));
        this.esciButton.click();
    }

    public boolean estensioneSbagliataErrore() {
        return this.estenzioneSbagliataMessage.isDisplayed();
    }


    public List<String> getCodiceIunPresenti() {
        By notificaCodiceIunBy = By.xpath("//td[@class = 'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1cpwezh']//button");
        List<WebElement> righeTabella = this.elements(notificaCodiceIunBy);
        List<String> listaCodici = new ArrayList<>();
        for (WebElement rigaTabella : righeTabella) {
            String codiceIun = rigaTabella.getText();
            listaCodici.add(codiceIun);
        }
        return listaCodici;
    }

    public List<String> getCodiceIunPresentiPF() {
        By notificaCodiceIunBy = By.xpath("//td[@class = 'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-2ebubn']//button");
        List<WebElement> righeTabella = this.elements(notificaCodiceIunBy);
        List<String> listaCodici = new ArrayList<>();
        for (WebElement rigaTabella : righeTabella) {
            String codiceIun = rigaTabella.getText();
            listaCodici.add(codiceIun);
        }
        return listaCodici;
    }

    public String getCodiceIunInserito() {
        return codiceIUNTextField.getText();
    }

    public List<String> getCodiceIunPersonaGiuridica() {
        By notificaCodiceIunBy = By.xpath("//td[@class = 'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1cpwezh' and button/p[contains(text(),'27957814470')]]/following-sibling::td[@class = 'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-2ebubn']//button");
        List<WebElement> righeTabella = this.elements(notificaCodiceIunBy);
        List<String> listaCodici = new ArrayList<>();
        for (WebElement rigaTabella : righeTabella) {
            String codiceIun = rigaTabella.getText();
            listaCodici.add(codiceIun);
        }
        return listaCodici;
    }

    public boolean controlloEsistenzaMessagioErroreCF() {
        By errorMessageBy = By.id("recipientId-helper-text");
        getWebDriverWait(30).withMessage("Messagio di errore 'Inserisci il codice per intero' non trovato").until(ExpectedConditions.visibilityOfElementLocated(errorMessageBy));
        return this.element(errorMessageBy).isDisplayed();
    }


    public boolean verificaBottoneFiltraDisabilitato() {
        try {
            getWebDriverWait(30).withMessage("buttone Filtra non è visibile").until(ExpectedConditions.visibilityOf(this.filtraNotificaButtonMittente));
            return Boolean.parseBoolean(this.filtraNotificaButtonMittente.getAttribute("disabled"));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean controlloEsistenzaMessagioErroreIUN() {
        By errorMessageBy = By.id("iunMatch-helper-text");
        getWebDriverWait(30).withMessage("Messagio di errore 'Inserisci un codice IUN valido' non trovato").until(ExpectedConditions.visibilityOfElementLocated(errorMessageBy));
        return this.element(errorMessageBy).isDisplayed();
    }

    public boolean verificaCampiPreliminariNotificaVuoti() {
        List<WebElement> inputElements = preliminaryInformationsForm.findElements(By.tagName("input"));
        for (WebElement inputElement : inputElements) {
            if (inputElement.getAttribute("type").equals("text") && !inputElement.getAttribute("value").isEmpty()) {
                return false;
            }
            if (inputElement.getAttribute("type").equals("radio") && inputElement.isSelected()) {
                return false;
            }
        }
        return true;
    }

    public void checkDefaultPagination() {
        final String defaultNumberOfPage = "10";
        if (numeroNotificheButton.getText().equals(defaultNumberOfPage)) {
            logger.info("numero di default delle notifiche visualizzate corretto");
        } else {
            logger.error("numero di default delle notifiche visualizzate non corretto");
            Assert.fail("numero di default delle notifiche visualizzate non corretto");
        }
    }

    public boolean isFiltraButtonDisabled() {
        return !inputsError.isEmpty() || filtraNotificaButtonMittente.getAttribute("disabled") != null;
    }

    public boolean controlloDateErrate() {
        boolean isDateErrate = false;
        for (WebElement input : inputsError) {
            if (input.getAttribute("id").equals("startDate") || input.getAttribute("id").equals("endDate")) {
                isDateErrate = true;
                break;
            }
        }
        return isDateErrate;
    }

    public boolean controlloEsistenzaStato() {
        this.statoNotificaField.click();
        try {
            List<WebElement> statiNotifica = this.elements(By.xpath("//li[@data-value]"));
            this.getWebDriverWait(10).withMessage("Il menu a tendina dello stato notifica del filtro non è visibile").until(ExpectedConditions.visibilityOfAllElements(statiNotifica));
            ArrayList<String> stati = new ArrayList<>(List.of("Tutti gli stati", "Depositata", "Invio in corso", "Consegnata", "Perfezionata per decorrenza termini", "Avvenuto accesso", "Annullata", "Destinatario irreperibile"));
            for (WebElement stato : statiNotifica) {
                if (!stati.contains(stato.getText())) {
                    logger.error("Lo stato " + stato.getText() + " non è presente nella lista");
                    return false;
                }
            }
            logger.info("Tutti gli stati sono presenti nella lista");
            return true;
        } catch (TimeoutException e) {
            logger.error("Stato notifica NON trovata con errore: " + e.getMessage());
            Assert.fail("Stato notifica NON trovata con errore: " + e.getMessage());
            return false;
        } finally {
            this.element(By.id("menu-status")).click();
        }
    }

    public void clickPagina(int pagina) {
        String paginaString = "page" + pagina;
        By paginaBy = By.id(paginaString);

            getWebDriverWait(30).withMessage("Il bottone pagina " + pagina + " non è cliccabile")
                    .until(elementToBeClickable(element(paginaBy)));
            js().executeScript("arguments[0].scrollIntoView(true);", element(paginaBy));
            element(paginaBy).click();
            logger.info("Bottone pagina " + pagina + " cliccato correttamente");
    }

    public void checkPaginaNotificheDelegante(String nomeDelegante) {
        String idNotificationTitlePage = "Le notifiche di " + nomeDelegante + "-page";
        By titlePage = By.id(idNotificationTitlePage);

        try {
            getWebDriverWait(10).withMessage("il titolo della pagina non é caricato").until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            getWebDriverWait(20).withMessage("tabella notifiche non caricata").until(ExpectedConditions.visibilityOf(notificationsTable));

            logger.info("Pagina notifiche delegante caricata correttamente");
        } catch (TimeoutException e) {
            logger.error("Pagina notifiche delegante non caricata con errore: " + e.getMessage());
            Assert.fail("Pagina notifiche delegante non caricata con errore: " + e.getMessage());
        }
    }


    public void verificaPresenzaStato(String stato) {
        By statusChip = By.xpath("//div[@data-testid='itemStatus']//span[contains(text(),'" + stato + "')]");

          getWebDriverWait(10).withMessage("Lo stato " + stato + " non è presente")
                    .until(ExpectedConditions.visibilityOfElementLocated(statusChip));
            logger.info("Stato {} presente", stato);
    }

    public void verificaNotificaCreata() {
        RestNotification restNotification = new RestNotification();
        String notificationRequestId = "";
        for (NetWorkInfo netWorkInfo : netWorkInfos) {
            if (netWorkInfo.getRequestUrl().contains("bff/v1/notifications/sent") && netWorkInfo.getRequestMethod().equals("POST")) {
                if (netWorkInfo.getResponseStatus().equals("202") && !netWorkInfo.getResponseBody().isEmpty()) {
                    notificationRequestId = netWorkInfo.getResponseBody().split("\"notificationRequestId\":\"")[1].split("\"")[0];
                    logger.info("NotificationRequestId: {}", notificationRequestId);
                    break;
                }
            }
        }
        Assert.assertFalse("NotificationRequestId non trovato, il codice della risposta al url bff/v1/notifications/sent è diverso di 202 ", notificationRequestId.isEmpty());
        LinkedTreeMap<String, Object> notificationData;
        String notificationStatus;
        String notificationIUN;
        int maximumRetry = 0;
        do {
            Assert.assertTrue("La notifica risulta ancora in stato WAITING dopo 5 tentativi", maximumRetry <= 5);
            notificationData = restNotification.getNotificationStatus(notificationRequestId);
            notificationStatus = notificationData.get("notificationRequestStatus").toString();
            if (notificationStatus.equals("ACCEPTED")) {
                notificationIUN = notificationData.get("iun").toString();
                notificationSingleton.setScenarioIun(Hooks.getScenario(), notificationIUN);
                return;
            } else {
                WebTool.waitTime(90);
                logger.info("Tentativo n.{} - Stato notifica: {}", maximumRetry, notificationStatus);
                maximumRetry++;
            }
        } while (notificationStatus.equals("WAITING"));
        driver.navigate().refresh();
        logger.info("La notifica è stata creata correttamente");
    }



    public void clickSuNotifica() {
        String iun = notificationSingleton.getIun(Hooks.scenario);
        logger.info("iun notifica {}", iun);
        By notification = By.xpath("//table[@id='notifications-table']//tr[.//button[contains(text(),'" + iun + "')]]");
        getWebDriverWait(30).withMessage("notifica non esistente").until(ExpectedConditions.visibilityOfElementLocated(notification));
        element(notification).click();
    }

    public void checkStatoNotifica(String stato) {
        driver.navigate().refresh();
        WebTool.waitTime(10);
            WebElement notificationLine = notificationsTableLines.get(0);
            WebElement chipStatus = notificationLine.findElement(By.id("status-chip-" + stato));
            getWebDriverWait(10).withMessage("La notifica non ha lo stato " + stato).until(ExpectedConditions.visibilityOf(chipStatus));
    }

    public void selezionaNotificaConStato(String statoNotifica) {
        boolean testSuccess = false;
        for (int i = 0; i < 8; i++) {
            try {
                WebElement notificationLine = notificationsTableLines.get(0);
                WebElement chipStatus = notificationLine.findElement(By.id("status-chip-" + statoNotifica));
                if (chipStatus != null) {
                    logger.info("La notifica è passata allo stato " + statoNotifica + " e si procede con il test");
                    testSuccess = true;
                    break;
                }
            } catch (NoSuchElementException e) {
                logger.info("Dopo " + i + " tentativi la notifica non è ancora passata allo stato: " + statoNotifica);
            }
            WebTool.waitTime(15);
            driver.navigate().refresh();
        }
        if (!testSuccess) {
            logger.error("La notifica non è passata allo stato " + statoNotifica);
            Assert.fail("La notifica non è passata allo stato " + statoNotifica);
        }
    }

    public void pollingSuStatoNotificaPerCompletamento(String statoNotifica) {
        boolean testSuccess = false;
        for (int i = 0; i < 12; i++) {
            try {
                By chipStatus = By.id(statoNotifica + "-status" );
                if (chipStatus != null) {
                    logger.info("La notifica è passata allo stato " + statoNotifica + " e si procede con il test");
                    driver.navigate().refresh();
                    testSuccess = true;
                    break;
                }
            } catch (NoSuchElementException e) {
                logger.info("Dopo " + i + " tentativi la notifica non è ancora passata allo stato: " + statoNotifica);
            }
            WebTool.waitTime(15);
            driver.navigate().refresh();
        }
        if (!testSuccess) {
            logger.error("La notifica non è passata allo stato " + statoNotifica);
            Assert.fail("La notifica non è passata allo stato " + statoNotifica);
        }
    }

    public void clickAnnullaNotificaModale() {
            By bottoneAnnullaNotificaModale = By.xpath("//button[@data-testid='modalCloseAndProceedBtnId']");
            getWebDriverWait(20).until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(bottoneAnnullaNotificaModale),
                    ExpectedConditions.elementToBeClickable(bottoneAnnullaNotificaModale)));
            element(bottoneAnnullaNotificaModale).click();
    }

    public void pollingSuStatoNotificaPerCompletamento(String statoNotifica) {
        boolean testSuccess = false;
        for (int i = 0; i < 12; i++) {
            try {
                By chipStatus = By.id(statoNotifica + "-status" );
                if (chipStatus != null) {
                    logger.info("La notifica è passata allo stato " + statoNotifica + " e si procede con il test");
                    driver.navigate().refresh();
                    testSuccess = true;
                    break;
                }
            } catch (NoSuchElementException e) {
                logger.info("Dopo " + i + " tentativi la notifica non è ancora passata allo stato: " + statoNotifica);
            }
            WebTool.waitTime(15);
            driver.navigate().refresh();
        }
        if (!testSuccess) {
            logger.error("La notifica non è passata allo stato " + statoNotifica);
            Assert.fail("La notifica non è passata allo stato " + statoNotifica);
        }
    }

    public void checkPopUpConfermaAnnullamentoNotifica() {

            By popUpConfermaAnnullamento = By.xpath("//div[@role='alert']/div[text()='La richiesta di annullamento è stata accettata.']");
            getWebDriverWait(10).withMessage("Pop up NON visualizzato").until(ExpectedConditions.visibilityOfElementLocated(popUpConfermaAnnullamento));

    }

    public void verificaInvioNotificaDiCortesia() {
        By voceNotificaDiCortesia = By.xpath("//span[contains(text(), 'Invio del messaggio di cortesia')]");
        getWebDriverWait(10).withMessage("Voce nel dettaglio della notifica non trovata").until(ExpectedConditions.visibilityOfElementLocated(voceNotificaDiCortesia));
    }

    public void clickBottoneAnnullaNotifica() {
        WebElement bottoneAnnullaNotifica = driver.findElement(By.xpath("//button[@data-testid='cancelNotificationBtn']"));
        getWebDriverWait(10).withMessage("Bottone annulla notifica non visibile e cliccabile").until(ExpectedConditions.and(ExpectedConditions.visibilityOf(bottoneAnnullaNotifica), ExpectedConditions.elementToBeClickable(bottoneAnnullaNotifica)));
        scrollToElementAndClick(bottoneAnnullaNotifica);
    }


    public void visualizzaTimelineTuttiDestinatari(Map<String, String> destinatari) {
        logger.info("Si clicca vedi piu dettagli");
        List<WebElement> viewMore = driver.findElements(By.xpath("//*[@id='more-less-timeline-step']"));
        //Equals() method utilizzato per String. Per confrontare int variabile dobbiamo usare ==
        String size = Integer.toString(viewMore.size());
        if (size.equals("2")) {
            viewMore.get(1).click();
        } else {
            viewMore.get(0).click();
        }
        //PF e PG vengono usati in modo da recuperare i dati test step. destinatari.get("PF") recupera CF da tabella nel FF
        List<WebElement> destinatarioPF = driver.findElements(By.xpath("//p[contains(text(),'(" + destinatari.get("PF") + ") all')]"));
        List<WebElement> destinatarioPG = driver.findElements(By.xpath("//p[contains(text(),'(" + destinatari.get("PG") + ") all')]"));

        if (destinatarioPF.get(0).isDisplayed() && destinatarioPG.get(0).isDisplayed()) {
            logger.info("Si visualizza  gli eventi relativi a tutti i destinatari");
        } else {
            logger.error("Non si visualizza  gli eventi relativi a tutti i destinatari");
            Assert.fail("Non si visualizza  gli eventi relativi a tutti i destinatari");
        }

        logger.info("Si visualizza correttamente la timeline relativi a tutti i destinatari");
    }

    public void visualizzaTimeline(String check) {
        List<WebElement> viewMore = driver.findElements(By.xpath("//*[@id='more-less-timeline-step']"));
        viewMore.get(0).click();
        String size = Integer.toString(viewMore.size());
        if (size.equals("2")) {
            viewMore.get(1).click();
        }

        List<WebElement> findKeyWord = driver.findElements(By.xpath("//span[contains(text(),'" + check + "')]"));

        if (findKeyWord.get(0).isDisplayed()) {
            logger.info("Si visualizza la timeline correttamente");
        } else {
            logger.error("Non si visualizza  la timeline correttamente");
            Assert.fail("Non si visualizza  la timeline correttamente");
        }
    }

    public void verificaDestinatariNonRaggiungibili(Map<String, String> destinatari) {
        logger.info("Si clicca vedi piu dettagli");
        List<WebElement> viewMore = driver.findElements(By.xpath("//*[@id='more-less-timeline-step']"));
        viewMore.get(0).click();
        String size = Integer.toString(viewMore.size());
        if (size.equals("2")) {
            viewMore.get(1).click();
        }
        By destinatarioPF = By.xpath("//p[contains(text(),'" + destinatari.get("PF") + " è fallito')]");
        By destinatarioPG = By.xpath("//p[contains(text(),'" + destinatari.get("PG") + " è fallito')]");

        if (this.element(destinatarioPF).isDisplayed() && this.element(destinatarioPG).isDisplayed()) {
            logger.info("Entrambi destinatari non raggiungibili al primo tentativo");
        } else {
            logger.error("Uno dei destinatari viene raggiunto al primo tentativo");
            Assert.fail("Uno dei destinatari viene raggiunto al primo tentativo");
        }
    }

    public void verificaNotificheNonDisponibili() {
        By noResultField = By.xpath("//div[@data-testid='emptyState']");
        getWebDriverWait(5).withMessage("Ci sono risultati disponibili per il filtro di ricerca").until(ExpectedConditions.visibilityOfElementLocated(noResultField));
    }

    public void checkAllegatoScaricabile(String descrizioneAllegato) {
        try {
            By linkAllegato = By.xpath("//button[contains(., '" + descrizioneAllegato + "') and @id='document-button']");
            getWebDriverWait(10).withMessage("Non esiste il bottone per il download degli allegati, si procede con il test").until(ExpectedConditions.visibilityOfElementLocated(linkAllegato));
            logger.error("Non è corretto che il bottone per il download dell'allegato sia visibile");
            Assert.fail("Non è corretto che il bottone per il download dell'allegato sia visibile");
        } catch (TimeoutException e) {
            logger.info("Non è visibile il bottone per il download dell'allegato: " + e.getMessage());
        }
    }

    public void checkAARScaricabili() {
            By linkAAR = By.xpath("//button[contains(., 'Avviso di avvenuta ricezione') and @id='document-button']");
            getWebDriverWait(10).withMessage("Il bottone per il download degli AAR non è visibile e non è disattivato").until(ExpectedConditions.and(
                    ExpectedConditions.attributeToBe(linkAAR, "disabled", "true"),
                    ExpectedConditions.visibilityOfElementLocated(linkAAR)
            ));
    }

    public void checkAttestazioniOpponibiliATerziScaricabili() {

            By linkAttestazione = By.xpath("//button[contains(text(), 'Attestazione opponibile a terzi') and @data-testid='download-legalfact']");
            getWebDriverWait(10).withMessage("Il bottone per il download delle attestazioni opponibili a terzi non è visibile e non è disattivato").until(ExpectedConditions.and(
                    ExpectedConditions.attributeToBe(linkAttestazione, "disabled", "true"),
                    ExpectedConditions.visibilityOfElementLocated(linkAttestazione)
            ));

    }

    public void checkRicevutePECScaricabili() {

            By linkAccettazionePEC = By.xpath("//button[contains(., 'Ricevuta di accettazione PEC') and @data-testid='download-legalfact']");
            By linkConsegnaPEC = By.xpath("//button[contains(., 'Ricevuta di consegna PEC') and @data-testid='download-legalfact']");
            getWebDriverWait(10).withMessage("Il bottone per scaricare la ricevuta di accettazione PEC non è visibile e non è disattivato").until(ExpectedConditions.and(
                    ExpectedConditions.attributeToBe(linkAccettazionePEC, "disabled", "true"),
                    ExpectedConditions.visibilityOfElementLocated(linkAccettazionePEC)
            ));
            getWebDriverWait(10).withMessage("Il bottone per scaricare la ricevuta di consegna PEC non è visibile e non è disattivato").until(ExpectedConditions.and(
                    ExpectedConditions.attributeToBe(linkConsegnaPEC, "disabled", "true"),
                    ExpectedConditions.visibilityOfElementLocated(linkConsegnaPEC)
            ));

    }
    public void clickNotificaRicercata() {
        logger.info("Si clicca la notifica ricercata");
        try {
            TimeUnit.SECONDS.sleep(2);
            By notitifcaRicercata = By.id("notificationsTable.body.row");
            getWebDriverWait(10).withMessage("La notifica ricercata non è visibile").until(ExpectedConditions.visibilityOfElementLocated(notitifcaRicercata));
            element(notitifcaRicercata).click();
        } catch (TimeoutException e) {
            logger.error("Non si riesce a cliccare la notifica ricercata con errore: " + e.getMessage());
            Assert.fail("Non si riesce a cliccare la notifica ricercata con errore: " + e.getMessage());
        } catch (InterruptedException e) {
            logger.error("Attesa per rendere cliccabile la notifica interrota con errore: " + e.getMessage());
            Assert.fail("Attesa per rendere cliccabile la notifica interrota con errore: " + e.getMessage());
        }
    }

    public void checkMessaggioErroreConCodice(int code) {
        switch (code) {
            case 19 -> {
                if (erroreMessaggio.getText().contains("inserito troppe volte un nome")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    logger.error("Non si visualizza il messaggio di errore");
                    Assert.fail("Non si visualizza il messaggio di errore");
                }
            }
            case 20 -> {
                if (erroreMessaggio.getText().contains("richiesto un login con un secondo fattore di autenticazione")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    logger.error("Non si visualizza il messaggio di errore");
                    Assert.fail("Non si visualizza il messaggio di errore");
                }
            }
            case 21 -> {
                if (erroreMessaggio.getText().contains("passato troppo tempo da quando hai iniziato")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    logger.error("Non si visualizza il messaggio di errore");
                    Assert.fail("Non si visualizza il messaggio di errore");
                }
            }
            case 22 -> {
                if (erroreMessaggio.getText().contains("devi acconsentire all’invio di alcuni dati")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    logger.error("Non si visualizza il messaggio di errore");
                    Assert.fail("Non si visualizza il messaggio di errore");
                }
            }
            case 23 -> {
                if (erroreMessaggio.getText().contains("tua identità SPID risulta sospesa o revocata")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    logger.error("Non si visualizza il messaggio di errore");
                    Assert.fail("Non si visualizza il messaggio di errore");
                }
            }
            case 25 -> {
                if (erroreMessaggio.getText().contains("annullato l’operazione di login")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    logger.error("Non si visualizza il messaggio di errore");
                    Assert.fail("Non si visualizza il messaggio di errore");
                }
            }
            case 30 -> {
                if (erroreMessaggio.getText().contains("tipologia di identità SPID che hai usato")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    logger.error("Non si visualizza il messaggio di errore");
                    Assert.fail("Non si visualizza il messaggio di errore");
                }
            }
            case 1001 -> {
                if (erroreMessaggio.getText().contains("non hai l’età minima richiesta per usare")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    logger.error("Non si visualizza il messaggio di errore");
                    Assert.fail("Non si visualizza il messaggio di errore");
                }
            }
        }
    }

    public void clickVediTutti() {
        By vediTutti = By.xpath("//button[@data-testid='show-all-attachments']");
        getWebDriverWait(4).withMessage("Il bottone vedi tutti non cliccabile").until(ExpectedConditions.elementToBeClickable(vediTutti));
        element(vediTutti).click();
    }

    public void checkClickDownloadRicevutePEC() {
        List<WebElement> ricevutePEC = driver.findElements(By.xpath("//button[contains(@data-testid, 'download-legalfact') and contains(text(), 'PEC')]"));
        getWebDriverWait(10).withMessage("Le ricevute PEC non sono visibili").until(ExpectedConditions.and(ExpectedConditions.visibilityOfAllElements(ricevutePEC),ExpectedConditions.elementToBeClickable(ricevutePEC.get(0)),ExpectedConditions.elementToBeClickable(ricevutePEC.get(1))));

    }

    public void verificaMittente(String ente) {
        By mittente = By.id("row-value-1");
        getWebDriverWait(10).withMessage("Mittente non trovato").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(mittente),
                ExpectedConditions.textToBe(mittente, ente)));
    }

    public void checkBottoneAnnullaNotifica() {
        boolean isDisplayed = false;
        try {
            isDisplayed = driver.findElement(By.xpath("//button[@data-testid='cancelNotificationBtn']")).isDisplayed();
        } catch (NoSuchElementException e) {
            isDisplayed = false; // Elemento non trovato
        }
        Assert.assertFalse("Il bottone è visualizzabile", isDisplayed);
    }
}