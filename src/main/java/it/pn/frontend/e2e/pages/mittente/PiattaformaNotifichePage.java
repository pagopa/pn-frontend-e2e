package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PiattaformaNotifichePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("notificaMittentePagoPA");

    @FindBy(id = "recipientId")
    WebElement cfTextField;
    @FindBy(id = "filter-button]")
    WebElement filtraButton;

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

    @FindBy(id = "page3")
    WebElement pageNumberButton;

//    @FindBy(xpath = "//button[contains(@class,'MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-disableElevation MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-disableElevation css-34ped8')]")
//    WebElement pageNumberList;

    @FindBy(xpath = "//li[@data-testid='pageSize-50']")
    WebElement numberElement;

    @FindBy(xpath = "//button[contains(@data-testid,'step-submit')]")
    WebElement continuaButtonDisabled;

    @FindBy(id = "subject")
    WebElement oggettoDellaNotificaTextField;

    @FindBy(id = "subject-helper-text")
    WebElement errorMessage;

    @FindBy(xpath = "//button[@data-testid='breadcrumb-indietro-button']")
    WebElement indietroButton;

    @FindBy(id = "button-exit")
    WebElement esciButton;

    @FindBy(xpath = "//div[contains(text(),'Estensione file non supportata. Riprovare con un altro file.')]")
    WebElement estenzioneSbagliataMessage;

    @FindBy(xpath = "//button[contains(@data-testid,'newNotificationBtn')]")
    WebElement inviaNuovaNotificaButton;




    public PiattaformaNotifichePage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadPiattaformaNotifichePAPage() {
        try {
            By notificheTitle = By.xpath("//h4[contains(text(),'Notifiche')]");
            By inviaNuovaNotificaButton = By.xpath("//button[contains(@data-testid,'newNotificationBtn')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(inviaNuovaNotificaButton));
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(inviaNuovaNotificaButton));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notificheTitle));
            logger.info("Piattaforma Notifiche Page caricata");
        } catch (TimeoutException e) {
            logger.error("Piattaforma Notifiche Page non caricata con errore : " + e.getMessage());
            Assert.fail("Piattaforma Notifiche Page non caricata con errore : " + e.getMessage());
        }
    }

    public void insertCodiceFiscale(String codiceFiscale) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.cfTextField.sendKeys(codiceFiscale);
    }

    public void selectFiltraButton() {
        this.filtraButton.click();
    }

    public int getListaCf(String cfInserito) {
        By cfListBy = By.xpath("//p[contains(text(),'" + cfInserito + "')]");
        attesaCaricamentoPagina();
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(cfListBy));
        logger.info("Codici fiscali trovati correttamente");

        return this.elements(cfListBy).size();
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
            By numeroButtonBy = By.xpath("//button[contains(@aria-label,'pagina 2')]");
            this.getWebDriverWait(20).until(ExpectedConditions.visibilityOfElementLocated(numeroButtonBy));
            logger.info("Bottone 2 trovato");

            WebElement numeroPagina = this.element(numeroButtonBy);
            numeroPagina.click();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void inserimentoCodiceIUN(String codiceIUN) {
        getWebDriverWait(40).until(ExpectedConditions.visibilityOf(this.codiceIUNTextField));
        this.codiceIUNTextField.sendKeys(codiceIUN);
    }

    public boolean verificaCodiceIUN(String codiceIUNInserito) {
        WebElement codiceIUN = getCodiceIUN(codiceIUNInserito);
        return codiceIUN != null;
    }

    private WebElement getCodiceIUN(String codiceIUNInserito) {
        try {
            By codiceIUNBy = By.xpath("//button[contains(text(),'" + codiceIUNInserito + "')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiceIUNBy));
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
        this.dataInizioField.sendKeys(da);
        this.dataFineField.sendKeys(a);
    }

    public int getListDate() {
        By dataListBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-c3sxw2')]");
        attesaCaricamentoPagina();
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(dataListBy));
        logger.info("Date trovate correttamente");

        return this.elements(dataListBy).size();
    }

    public void selezionareStatoNotifica(String statoInserito) {
        this.statoNotificaField.click();
        try {
            By statoNotificaBy = By.xpath("//li[contains(@data-value,'"+statoInserito+"')]");
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(statoNotificaBy));

            if (this.element(statoNotificaBy).isDisplayed()) {
                this.js().executeScript("arguments[0].click()",this.element(statoNotificaBy));
            }else {
                this.js().executeScript("arguments[0].scrollIntoView(true);",this.element(statoNotificaBy));
                this.js().executeScript("arguments[0].click()",this.element(statoNotificaBy));
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
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBy));
            return this.elements(statoNotificaBy).size();
        }catch (TimeoutException e){
            return 0;
        }
    }

    public boolean IsAnAdvancedStatus(){
        By statoNotificaBy = By.xpath("//div[contains(@id,'status-chip-')]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBy));
        String status = this.element(statoNotificaBy).getText();
        return !status.equalsIgnoreCase("Depositata");
    }

    public void selezionaNotifica() {
        waitLoadPage();
        try {
            By notificaBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-155o2nr')]");
            attesaCaricamentoPagina();
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(notificaBy));
            this.element(notificaBy).click();
        } catch (TimeoutException e) {
            logger.error("Notifica non trovata con errore: " + e.getMessage());
            Assert.fail("Notifica non trovata con errore: " + e.getMessage());
        }
    }

    public void selectInviaUnaNuovaNotificaButton() {
        this.js().executeScript("arguments[0].click()",this.inviaNuovaNotificaButton);
    }


    public void waitLoadRefreshPage() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String ricercaNotifica(String oggettoDellaNotifica, String statoNotifica) {

        String codiceIUN = "";

        int posizioneNotifica = verificaEsistenzaNotifica(oggettoDellaNotifica, statoNotifica);
        logger.info("la posizione della notifica è uguale: " + posizioneNotifica);
        if (posizioneNotifica != -1) {

            By listaCodiceIUNBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-155o2nr')]");
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
            By listaOggettiBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-hgkziz')]");
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
        getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.apiKeyButton));
        this.js().executeScript("arguments[0].click()",this.apiKeyButton);
    }



    public String conversioneFormatoDate(String date) {
        String[] dateInserita = date.split("-");
        return dateInserita[2] + "/" + dateInserita[1] + "/" + dateInserita[0];
    }

    public int controlloNumeroRisultatiDate() {
        By dataListBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-c3sxw2')]");
        attesaCaricamentoPagina();
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(dataListBy));
        logger.info("Date trovate correttamente");

        return this.elements(dataListBy).size();
    }

    public void inserimentoData(String dataInserita) {
        this.dataInizioField.sendKeys(dataInserita);
    }

    public boolean verificaEsistenzaRisultati() {
        try {
            By messaggioNessunRisultatoby = By.xpath("//button[contains(@data-testid,'callToActionFirst')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(messaggioNessunRisultatoby));
            logger.info("Messaggio visualizzato correttamente");
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void siVisualizzaCorrettamenteIlCFField() {
        try {
            By codiceFiscaleField = By.xpath("//input[contains(@id,'recipientId')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiceFiscaleField));
            logger.info("Il campo di codice fiscale si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il campo di codice fiscale NON si visualizza correttamente con errore : " + e.getMessage());
            Assert.fail("Il campo di codice fiscale NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteIlCodiceIUNField() {
        try {
            By codiceIUNField = By.xpath("//input[contains(@id, 'iunMatch')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiceIUNField));
            logger.info("Il campo di codice iun si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il campo di codice iun NON si visualizza correttamente con errore : " + e.getMessage());
            Assert.fail("Il campo di codice iun NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteLoStatoField() {
        try {
            By statoField = By.xpath("//div[@id='status']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoField));
            logger.info("Il campo dello stato si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il campo dello stato NON si visualizza correttamente con errore : " + e.getMessage());
            Assert.fail("Il campo dello stato NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteLaDataInzioField() {
        try {
            By dataDaField = By.xpath("//input[contains(@id, 'startDate')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(dataDaField));
            logger.info("Il campo della data di inizio si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il campo della data di inizio NON si visualizza correttamente con errore : " + e.getMessage());
            Assert.fail("Il campo della data di inizio NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteLaDataFineField() {
        try {
            By dataAField = By.xpath("//input[contains(@id, 'endDate')]");
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
        return this.elements(cfFiealdBy).size() >= 1;
    }

    public boolean verificaEsistenzaCodiceIUNNotifiche() {
        By codiciIUNBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-155o2nr')]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiciIUNBy));
        return this.elements(codiciIUNBy).size() >= 1;
    }

    public boolean verificaEsistenzaGruppoNotifiche() {
        By gruppiBy = By.xpath("//td[button/div/span[contains(@class,'css-t63gu0')]]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(gruppiBy));
        return this.elements(gruppiBy).size() >= 1;
    }

    public boolean verificaEsistenzaStatoNotifiche() {
        By statiBy = By.xpath("//td[button/div/div[contains(@data-testid,'statusChip-')]]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statiBy));
        return this.elements(statiBy).size() >= 1;
    }


    public int getNRighe() {
        By nRigheBy = By.xpath("//tr[@data-testid='notificationsTable.row']");
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
        LocalDate dataPrecedente;
        for (int i = 0; i < listaDate.size() - 1; i++) {
            String dataDopo = listaDate.get(i).getText();
            String dataPrima = listaDate.get(i + 1).getText();
            if ("Oggi".equals(dataDopo)) {
                dataSuccessiva = LocalDate.now();
            } else {
                String[] dateA = dataDopo.split("/");
                dataDopo = dateA[2] + "-" + dateA[1] + "-" + dateA[0];
                dataSuccessiva = LocalDate.parse(dataDopo);
            }
            if ("Oggi".equals(dataPrima)) {
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
        logger.info("Le date sono visualizzate correttamente");
    }

    private List<WebElement> getListaDate() {
        try {
            By dataListBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-c3sxw2')]");
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
        if (!numeroNotificheButton.isDisplayed()) {
            this.js().executeScript("arguments[0].scrollIntoView(true);", numeroNotificheButton);
        }
    }

    public void siCambiaPaginaUtilizzandoUnaFrecetta() {
        if (!frecciaPaginaSuccessiva.isDisplayed()) {
            this.js().executeScript("arguments[0].scrollIntoView(true);", numeroNotificheButton);
        }
        frecciaPaginaSuccessiva.click();
    }

    public void siCambiaPaginaUtilizzandoUnNumero() {
        if (!pageNumberButton.isDisplayed()) {
            this.js().executeScript("arguments[0].scrollIntoView(true);", pageNumberButton);
        }
        this.js().executeScript("arguments[0].click()",this.pageNumberButton);
    }

    public void siCambiaIlNumeroElementiVisualizzatiAttraversoIlFiltro() {
        if (!numeroNotificheButton.isDisplayed()) {
            this.js().executeScript("arguments[0].scrollIntoView(true);", numeroNotificheButton);
        }
        this.js().executeScript("arguments[0].click()",this.numeroNotificheButton);
        this.js().executeScript("arguments[0].click()", this.numberElement);
    }

    public void clickContinuaDisabled() {this.continuaButtonDisabled.isDisplayed();}

    public void inserimentoOggettoNotificaErrato(String oggettoDellaNotifica) {
      this.scrollToElementClickAndInsertText(this.oggettoDellaNotificaTextField,oggettoDellaNotifica);
    }
    public boolean errorMessage() {return this.errorMessage.isDisplayed();}

    public void clickIndietroButton() {this.indietroButton.click();}

    public void vuoiUscirePopUp() {
    try{
        By vuoiUscirePUBy = By.xpath("//h2[contains(text(),'Vuoi uscire?')]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(vuoiUscirePUBy));
        logger.info("Si visualizza il pop up vuoi uscire");
    }catch (TimeoutException e){
        logger.error("Non si visualizza il pop up vuoi uscire con errore:"+e.getMessage());
        Assert.fail("Non si visualizza il pop up vuoi uscire con errore:"+e.getMessage());
    }
    }

        public void clickSuEsci() {this.esciButton.click();}

    public boolean estensioneSbagliataErrore() {return this.estenzioneSbagliataMessage.isDisplayed();}

    public void waitloadingSpinner(){
        By loadingSpinnerBy = By.xpath("//span[@role = 'loadingSpinner']");
        getWebDriverWait(60).until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinnerBy));
    }

    public List<String> getCodiceIunPresenti() {
        By notificaCodiceIunBy = By.xpath("//td[@class = 'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-155o2nr']//button");
        List<WebElement> righeTabella = this.elements(notificaCodiceIunBy);
        List<String> listaCodici = new ArrayList<>();
        for (WebElement rigaTabella: righeTabella) {
            String codiceIun = rigaTabella.getText();
            listaCodici.add(codiceIun);
        }
        return listaCodici;
    }

    public String getCodiceIunInserito() {
        return codiceIUNTextField.getText();
    }

    public List<String> getCodiceIunPersonaGiuridica() {
        By notificaCodiceIunBy = By.xpath("//td[@class = 'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1p8q4rm' and button/p[contains(text(),'12666810299')]]/following-sibling::td[@class = 'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-155o2nr']//button");
        List<WebElement> righeTabella = this.elements(notificaCodiceIunBy);
        List<String> listaCodici = new ArrayList<>();
        for (WebElement rigaTabella: righeTabella) {
            String codiceIun = rigaTabella.getText();
            listaCodici.add(codiceIun);
        }
        return listaCodici;
    }
}