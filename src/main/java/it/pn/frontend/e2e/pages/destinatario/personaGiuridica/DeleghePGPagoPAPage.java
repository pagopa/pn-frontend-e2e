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

    @FindBy(xpath = "//button[@data-testid='confirmButton']")
    WebElement filtraButton;

    @FindBy(id = "code-confirm-button")
    WebElement accettaDelegaButton;

    @FindBy(xpath = "//span[@data-testid='associate-group']")
    WebElement assegnaGruppoRadioButton;

    @FindBy(xpath = "//div[@role='dialog']//input[@id='groups']")
    WebElement gruppoField;

    @FindBy(id = "groups-option-0")
    WebElement gruppoOption;

    @FindBy(id = "group-confirm-button")
    WebElement confermaButton;

    @FindBy(xpath = "//span[@data-testid='no-group']")
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

    public DeleghePGPagoPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDeleghePage() {
        try{
            By titlePage = By.id("Deleghe-page");

            this.getWebDriverWait(30).withMessage("il titolo della pagina deleghe PG non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            this.getWebDriverWait(30).withMessage("Il bottone deleghe a carico dell'impresa non è visibile").until(ExpectedConditions.visibilityOf(this.delegheCaricoImpresaButton));

            this.logger.info("Deleghe page si visualizza correttamente");

        }catch (TimeoutException e){

            this.logger.error("Deleghe page non si visualizza correttamente con errore: "+e.getMessage());
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

    public boolean CercaEsistenzaDelegaPG(String ragioneSociale) {
        try {
            By nomeDelegato = By.xpath("//td[@role='cell' and div/p[contains(text(),'"+ragioneSociale+"')]]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeDelegato));
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }
    public void clickRevocaMenuButtonPG(String ragioneSociale) {

        By menuButton = By.xpath("//td[@role='cell' and div/p[contains(text(),'"+ragioneSociale+"')]]/following-sibling::td[@role='cell']//button[@data-testid='delegationMenuIcon']");
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

            this.logger.info("L'elenco delle deleghe si visualizza correttamente");

        }catch (TimeoutException e){

            this.logger.error("L'elenco delle deleghe non si visualizza correttamente con errore: "+e.getMessage());
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
        }catch (TimeoutException e ){
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
        this.gruppoField.sendKeys("Test gruppi");
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.gruppoOption));
        this.js().executeScript("arguments[0].click()",this.gruppoOption);
    }

    public void clickBottoneConferma() {
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
            By statoAttivaBy = By.xpath("//tr[@data-testid='table(notifications).row']//td[@role='cell' and div/p[contains(text(),'"+ragioneSociale+"')]]/following-sibling::td[@role='cell']//div/div/span[contains(text(),'Attiva')]");
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
        this.opzioneModifica.click();
    }

    public void waitLoadPopUpModifica() {
        try {
            By titlePOPUPBy = By.id("dialog-title");
            By nonAssegnaButtonBy = By.xpath("//span[@data-testid='no-group']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePOPUPBy));
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(nonAssegnaButtonBy));
            logger.info("Si visualizza correttamente il pop-up");
        }catch (TimeoutException e){
            logger.error("Si visualizza NON correttamente il pop-up con errore: "+e.getMessage());
            Assert.fail("Si visualizza NON correttamente il pop-up con errore: "+e.getMessage());
        }
    }

    public boolean verificaPresenzaGruppo(String ragioneSociale) {
        try {
            By gruppoBy = By.xpath("//td[@role='cell' and div/p[contains(text(),'"+ragioneSociale+"')]]/following-sibling::td[@role='cell']//span[contains(text(),'Test gruppi')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(gruppoBy));
            return true;
        }catch (TimeoutException e) {
            return false;
        }

    }

    public void inserireGruppoDelegante() {
        this.searchGroupTextField.click();
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.groupOption));
        this.groupOption.click();
    }
}
