package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeleghePGPagoPAPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DeleghePGPagoPAPage");

    @FindBy(id = "tab-1")
    WebElement delegatiImpresaButton;

    @FindBy(id = "tab-2")
    WebElement delegheCaricoImpresaButton;
    @FindBy(id = "revoke-delegation-button")
    WebElement revocaMenuButton;

    @FindBy(id = "taxId")
    WebElement cfTextField;

    @FindBy(id = "confirm-button")
    WebElement filtraButton;

    @FindBy(id = "code-confirm-button")
    WebElement accettaDelegaButton;

    @FindBy(id = "associate-form-group")
    WebElement assegnaGruppoRadioButton;

    By gruppoField = By.xpath("//div[@role='dialog']//input[@id='groups']");

    @FindBy(id = "group-confirm-button")
    WebElement confermaButton;

    @FindBy(id = "associate-form-no-group")
    WebElement nonGruppoRadioButton;

    @FindBy(id = "reject-delegation-button")
    WebElement opzioneRifiuta;

    @FindBy(id = "dialog-action-button")
    WebElement rifiutaButton;

    @FindBy(id = "update-delegation-button")
    WebElement opzioneModifica;

    @FindBy(id = "groups")
    WebElement searchGroupTextField;

    @FindBy(id = "groups-option-0")
    WebElement groupOption;
    @FindBy(id = "breadcrumb-indietro-button")
    WebElement indietroButton;

    @FindBy(css = "[data-testid='notificationDetailTableRow']")
    WebElement sezioneDeiDati;

    @FindBy(css = "[data-testid='notification-payment-recipient-title']")
    WebElement sezionePagamento;

    @FindBy(css = "[data-testid='menu-item(recapiti)']")
    WebElement sezioneRecapiti;

    @FindBy(id = "notification-detail-document-attached")
    WebElement documentiAllegati;

    @FindBy(id = "notification-state")
    WebElement statoDelloNotifiche;

    @FindBy(css = "[data-testid='download-legalfact']")
    WebElement attestazione;


    public DeleghePGPagoPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDeleghePage() {
        try{
            By titlePage = By.id("Deleghe-page");
            this.getWebDriverWait(30).withMessage("il titolo della pagina deleghe PG non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            this.getWebDriverWait(30).withMessage("Il bottone deleghe a carico dell'impresa non è visibile").until(ExpectedConditions.visibilityOf(this.delegheCaricoImpresaButton));
            logger.info("Deleghe page si visualizza correttamente");
        } catch (TimeoutException e){
            logger.error("Deleghe page non si visualizza correttamente con errore: "+e.getMessage());
            Assert.fail("Deleghe page non si visualizza correttamente con errore: "+e.getMessage());
        }
    }

    public void clickDelegatiImpresa() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(delegatiImpresaButton));
            this.delegatiImpresaButton.click();
        }catch(TimeoutException e){
            logger.error("il bottone delegati imprese non è cliccabile"+ e.getMessage());
            Assert.fail("il bottone delegati imprese non è cliccabile"+ e.getMessage());
        }

    }

    public boolean cercaEsistenzaDelegaPG(String ragioneSociale) {
        try {
            By nomeDelegato = By.xpath("//td[@scope='col' and div/p[contains(text(),'"+ragioneSociale+"')]]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeDelegato));
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            logger.error("ricerca esistenza delega pg non riuscita con errore: "+ e.getMessage());
            return false;
        }
    }
    public void clickRevocaMenuButtonPG(String ragioneSociale) {
        By menuButton = By.xpath("//td[@scope='col' and div/p[contains(text(),'"+ragioneSociale+"')]]/following-sibling::td[@scope='col']//button[@data-testid='delegationMenuIcon']");
        this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(menuButton));
        this.js().executeScript("arguments[0].click()",this.element(menuButton));
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.revocaMenuButton));
        this.revocaMenuButton.click();
    }

    public void clickSuDelegheCaricoDellImpresa() {
            this.delegheCaricoImpresaButton.click();
    }

    public void verificaPresenzaElencoDeleghe() {
        try{
            By tableDelegheBy = By.id("notifications-table");
            this.getWebDriverWait(50).until(ExpectedConditions.visibilityOfElementLocated(tableDelegheBy));
            logger.info("L'elenco delle deleghe si visualizza correttamente");
        } catch (TimeoutException e){
           logger.error("L'elenco delle deleghe non si visualizza correttamente con errore: "+e.getMessage());
            Assert.fail("L'elenco delle deleghe non si visualizza correttamente con errore: "+e.getMessage());
        }
    }

    public void insertCFDelegante(String codiceFiscale) {
        this.cfTextField.sendKeys(codiceFiscale);
    }

    public void clickFiltraButton() {
        this.filtraButton.click();
    }

    public boolean controlloDelegaRestituita(String ragioneSociale) {
        By delegaBy = By.xpath("//p[contains(text(),'"+ragioneSociale+"')]");
        try{
            this.getWebDriverWait(60).withMessage("ragione sociale non caricata").until(ExpectedConditions.visibilityOfElementLocated(delegaBy));
            logger.info("controllo ragione sociale");
        } catch (TimeoutException e ){
            logger.error("ragione sociale non caricata"+e.getMessage());
            Assert.fail("ragione sociale non caricata"+e.getMessage());
        }
        logger.info("ragione sociale caricata correttamente");
        return this.elements(delegaBy).size() == 1;

    }

    public void clickBottoneAccetta() {
        this.accettaDelegaButton.click();
    }

    public void waitLoadPopUpGruppo() {
        try {
            By titlePageBy = By.id("dialog-title");
            By assegnaGruppoButtonBy = By.xpath("//span[@data-testid='associate-group']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(assegnaGruppoButtonBy));
            this.logger.info("Si vede correttamente il pop-up di assegnazione gruppo");
        }catch (TimeoutException e){
            this.logger.error("Non si vede correttamente il pop-up di assegnazione gruppo con errore: "+e.getMessage());
            Assert.fail("Non si vede correttamente il pop-up di assegnazione gruppo con errore: "+e.getMessage());
        }
    }

    public void clickAssegnaGruppoRadioButton() {
        this.assegnaGruppoRadioButton.click();
    }

    public void clickGruppoField() {
        this.element(gruppoField).sendKeys("Test gruppi");
        By gruppoOption = By.id("groups-option-0");
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.element(gruppoOption)));
        this.js().executeScript("arguments[0].click()",this.element(gruppoOption));
    }

    public void clickBottoneConferma() {
        getWebDriverWait(40).withMessage("Il bottone conferma nel pop up di scelta gruppo non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.confermaButton));
        this.confermaButton.click();
    }

    public boolean verificaEsistenzaErroreCodiceSbagliato() {
        try{
            By esistenzaBy = By.id("alert-api-status}");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(esistenzaBy));
            logger.info("Errore codice sbagliato trovato");
            return true;
        }catch (TimeoutException e){
            logger.info("errore non trovato");
            return false;
        }

    }


    public void controlloStatoAttiva(String ragioneSociale) {
        try {
            By statoAttivaBy = By.xpath("//tr[@data-testid='delegationsBodyRowDesktop']//td[@scope='col' and div/p[contains(text(),'"+ragioneSociale+"')]]/following-sibling::td[@scope='col']//div/div/span[contains(text(),'Attiva')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoAttivaBy));
            logger.info("La delega ha lo stato Attiva");
        }catch (TimeoutException e){
            logger.error("La delega NON ha lo stato Attiva con errore: "+e.getMessage());
            Assert.fail("La delega NON ha lo stato Attiva con errore: "+e.getMessage());
        }
    }

    public void clickNonAssegnaGruppo() {
        this.nonGruppoRadioButton.click();
    }

    public void clickOpzioneRifiuta() {
        this.opzioneRifiuta.click();
    }

    public void clickBottoneRifiuta() {
        this.rifiutaButton.click();
    }

    public void waitLoadPopUpRevoca() {
        try {
            By revocaPopUpBy = By.xpath("//div[@aria-labelledby='responsive-dialog-title']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(revocaPopUpBy));
            this.logger.info("Si visualizza il pop-up rifiuta delega");
        }catch (TimeoutException e){
            this.logger.error("Non si visualizza il pop-up rifiuta delega con errore: "+e.getMessage());
            Assert.fail("Non si visualizza il pop-up rifiuta delega con errore: "+e.getMessage());
        }
    }

    public void clickOpzioneModifica() {
        try{
            this.getWebDriverWait(30).withMessage("il pulsante modifica delega non é presente").until(ExpectedConditions.elementToBeClickable(opzioneModifica));
            this.opzioneModifica.click();
        }catch (TimeoutException e ){
            logger.error(" errore: "+e.getMessage());
            Assert.fail(" errore: "+e.getMessage());
        }
    }

    public void waitLoadPopUpModifica() {
        try {
            By titlePOPUPBy = By.id("dialog-title");
            By nonAssegnaButtonBy = By.id("associate-form-no-group");
            this.getWebDriverWait(30).withMessage("Il titolo del pop-up non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePOPUPBy));
            this.getWebDriverWait(30).withMessage("Il bottone non assegna sul pop-up non è cliccabile").until(ExpectedConditions.elementToBeClickable(nonAssegnaButtonBy));
            logger.info("Si visualizza correttamente il pop-up");
        }catch (TimeoutException e){
            logger.error("NON Si  visualizza  correttamente il pop-up con errore: "+e.getMessage());
            Assert.fail("NON Si visualizza correttamente il pop-up con errore: "+e.getMessage());
        }
    }

    public boolean verificaPresenzaGruppo(String ragioneSociale) {
        try {
            By gruppoBy = By.xpath("//td[@scope='col' and div/p[contains(text(),'"+ragioneSociale+"')]]/following-sibling::td[@scope='col']//span[contains(text(),'Test gruppi')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(gruppoBy));
            return true;
        }catch (TimeoutException e) {
            logger.info("gruppo non presente");
            return false;
        }

    }

    public void inserireGruppoDelegante() {
        this.getWebDriverWait(30).withMessage("Il campo cerca gruppo non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.searchGroupTextField));
        this.searchGroupTextField.click();
        this.getWebDriverWait(30).withMessage("l'opzione gruppo non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.groupOption));
        this.groupOption.click();
    }

    public boolean isBackButtonDisplayed() {
        return getWebDriverWait(30).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton)).isDisplayed();
    }

    public boolean sezioneDeiDatiDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione dei dati non è visibile").until(ExpectedConditions.visibilityOf(sezioneDeiDati)).isDisplayed();
    }

    public boolean sezionePagamentoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione pagamento non è visibile").until(ExpectedConditions.visibilityOf(sezionePagamento)).isDisplayed();
    }

    public boolean sezioneRecapitiDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione recapiti non è visibile").until(ExpectedConditions.visibilityOf(sezionePagamento)).isDisplayed();
    }
    public boolean documentiAllegatiDisplayed() {
        return getWebDriverWait(30).withMessage("Il documenti allegati non è visibile").until(ExpectedConditions.visibilityOf(documentiAllegati)).isDisplayed();

    }
    public boolean statoDelloNotificheDisplayed() {
        return getWebDriverWait(30).withMessage("Il stato dello notifiche non è visibile").until(ExpectedConditions.visibilityOf(statoDelloNotifiche)).isDisplayed();

    }
    public boolean atteztazioneDisplayed() {
        return getWebDriverWait(30).withMessage("L'attestazione non è visibile").until(ExpectedConditions.visibilityOf(attestazione)).isDisplayed();

    }
}


