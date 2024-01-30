package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccediAPiattaformaNotifichePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AccediAPiattaformaNotifichePage");


    @FindBy(id = "login-button")
    WebElement accediButton;

    @FindBy(css = "[id='notificationsTable.body.row']")
    WebElement notificheButton;

    @FindBy(id = "side-item-Recapiti")
    WebElement recapitiButton;

    @FindBy(id = "side-item-Stato della piattaforma")
    WebElement buttonEnterIntoDisservizi;

    @FindBy(css = "[data-testid='download-legalfact']")
    WebElement attestazione;

    @FindBy(id = "breadcrumb-indietro-button")
    WebElement indietroButton;

    @FindBy(css = "[class='MuiTableBody-root css-1xnox0e']")
    WebElement sezioneDeiDati;

    @FindBy(css = "[data-testid='paymentInfoBox']")
    WebElement sezionePagamento;

    @FindBy(css = "[role='radiogroup']")
    WebElement radioBox;

    @FindBy(css = "[name='radio-buttons-group']")
    WebElement radioButton;

    @FindBy(linkText = "Pagamento di Test")
    WebElement titoloPagamento;

    @FindBy(css = "[data-testid='download-f24-button']")
    WebElement modelloF24;

    @FindBy(css = "[data-testid='download-pagoPA-notice-button']")
    WebElement scaricaAvviso;

    @FindBy(css = "[data-testid='pay-button']")
    WebElement pagaAvviso;

    @FindBy(linkText = "Codice avviso")
    WebElement codiceAvviso;
    @FindBy(css = ".MuiTypography-caption-semibold.css-1g3z0lx")
    WebElement codiceAvvisoSpan;

    @FindBy(id = "side-item-Recapiti")
    WebElement sezioneRecapiti;

    @FindBy(id = "notification-detail-document-attached")
    WebElement documentiAllegati;

    @FindBy(id = "notification-state")
    WebElement statoDelloNotifiche;

    @FindBy(css = "[data-testid='more-less-timeline-step']")
    WebElement vediPiuDettagli;

    @FindBy(linkText = "Vedi meno dettagli")
    WebElement vediMenoDettagliButton;

    @FindBy(css="[type='application/pdf']")
    WebElement pdfType;

    @FindBy(css = "[data-testid='documentButton']")
    WebElement documentButton;

    public AccediAPiattaformaNotifichePage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAccediAPiattaformaNotifichePage(){
        try{
            By titleLabel = By.id("login-page-title");
            By loginBy = By.id("login-button");
            this.getWebDriverWait(30).withMessage("Il titolo della pagina accedi a piattaforma notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            this.getWebDriverWait(30).withMessage("Il bottone login della pagina accedi a piattaforma notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(loginBy));
            this.getWebDriverWait(30).withMessage("Il bottone login della pagina accedi a piattaforma notifiche non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.accediButton));
            logger.info("Accedi A Piattaforma Notifiche Page caricata");
        }catch (TimeoutException e){
            logger.info("Accedi A Piattaforma Notifiche Page non caricata con errore : "+e.getMessage());
            Assert.fail("Accedi A Piattaforma Notifiche Page non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionaAccediButton(){
        this.js().executeScript("arguments[0].click()",this.accediButton);
    }


    public void clickRecapitiButton() {
        getWebDriverWait(30).withMessage("Il bottone recapiti non è visibile").until(ExpectedConditions.visibilityOf(recapitiButton));
        this.js().executeScript("arguments[0].click()", this.recapitiButton);
    }

    public void clickNotificheButton() {
        getWebDriverWait(30).withMessage("Il bottone notifiche non è visibile").until(ExpectedConditions.visibilityOf(notificheButton));
        notificheButton.click();
    }

    public void clickatestazionePersalvare() {
        getWebDriverWait(30).withMessage("Il attestazione non è visibile").until(ExpectedConditions.visibilityOf(attestazione));
        this.js().executeScript("arguments[0].click()", this.attestazione);
    }

    public boolean isBackButtonDisplayed() {
        return getWebDriverWait(30).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton)).isDisplayed();
    }

    public void clickIndietroButton() {
        getWebDriverWait(30).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton));
        this.js().executeScript("arguments[0].click()", this.indietroButton);
    }

    public boolean sezioneDeiDatiDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione dei dati non è visibile").until(ExpectedConditions.visibilityOf(sezioneDeiDati)).isDisplayed();
    }

    public boolean sezionePagamentoDisplayed() {
        try{
            return  getWebDriverWait(30).withMessage("Il sezione pagamento non è visibile").until(ExpectedConditions.visibilityOf(sezionePagamento)).isDisplayed();

        }catch(NoSuchElementException | TimeoutException e){

            logger.warn("Il sezione pagamento non è visibile");
            return false;
        }
    }


    public boolean isRadioBoxPresent() {
        try{
            return getWebDriverWait(30).withMessage("Il radio box non è visibile").until(ExpectedConditions.visibilityOf(radioBox)).isDisplayed();

        }catch(NoSuchElementException | TimeoutException e){

            logger.warn("Il radio box non è visibile");
            return false;
        }
    }

    public String cssBuildRadioButton(){
        return "[value='" + codiceAvvisoSpan.getText() + "']";
    }

    public void clickRadioBoxButton(String css) {

        getWebDriverWait(30).withMessage("Il radio box button non è cliccabile").until(ExpectedConditions.elementToBeClickable(radioButton));
        radioButton.click();
    }


    public boolean titoloDiPagamentoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione titolo pagamento non è visibile").until(ExpectedConditions.visibilityOf(titoloPagamento)).isDisplayed();
    }

    public boolean codiceAvvisoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(codiceAvviso)).isDisplayed();
    }

    public boolean modelloF24Displayed() {
        return getWebDriverWait(30).withMessage("Il sezione scarica modello F24 non è visibile").until(ExpectedConditions.visibilityOf(modelloF24)).isDisplayed();
    }

    public boolean scaricaAvvisoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione scarica avviso non è visibile").until(ExpectedConditions.visibilityOf(scaricaAvviso)).isDisplayed();
    }

    public boolean pagaAvvisoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione paga avviso non è visibile").until(ExpectedConditions.visibilityOf(pagaAvviso)).isDisplayed();
    }

    public boolean sezioneRecapitiDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione recapiti non è visibile").until(ExpectedConditions.visibilityOf(sezioneRecapiti)).isDisplayed();
    }
    public boolean documentiAllegatiDisplayed() {
        return getWebDriverWait(30).withMessage("Il documenti allegati non è visibile").until(ExpectedConditions.visibilityOf(documentiAllegati)).isDisplayed();

    }
    public boolean statoDelloNotificheDisplayed() {
        return getWebDriverWait(30).withMessage("Il stato dello notifiche non è visibile").until(ExpectedConditions.visibilityOf(statoDelloNotifiche)).isDisplayed();

    }
    public boolean attestazioneDisplayed() {
        return getWebDriverWait(30).withMessage("L'attestazione non è visibile").until(ExpectedConditions.visibilityOf(attestazione)).isDisplayed();

    }
    public boolean vediPiuDettagli() {
        return getWebDriverWait(30).withMessage("Il bottone vedi piu dettagli non è visibile").until(ExpectedConditions.visibilityOf(vediPiuDettagli)).isDisplayed();
    }

    public void clickVediPiuDettagli(){

        getWebDriverWait(30).withMessage("Il bottone vedi piu dettagli non è visibile").until(ExpectedConditions.visibilityOf(vediPiuDettagli));
        vediPiuDettagli.click();

    }

    public boolean vediMenoDettagliDisplayed() throws InterruptedException {
        Thread.sleep(1000);
        return getWebDriverWait(30).withMessage("Il bottone vedi meno dettagli non è visibile").until(ExpectedConditions.visibilityOf(vediMenoDettagliButton)).isDisplayed();
    }

    public boolean isTypePdfDisplayed(){
        return getWebDriverWait(30).withMessage("Il format di file e PDF").until(ExpectedConditions.visibilityOf(pdfType)).isDisplayed();
    }

    public void clickDocumentButton(){

        getWebDriverWait(30).withMessage("Il bottone documento allegato non è visibile").until(ExpectedConditions.visibilityOf(documentButton));
        this.js().executeScript("arguments[0].click()", this.documentButton);

    }

    public void clickNotificheImpresa() {
        try {
            By notificheImpresaButton = By.xpath("//div[@data-testid=\"sideMenuItem-Notifiche dell'impresa\"]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notificheImpresaButton));
            logger.info("Si clicca sulla voce notifiche dell'impresa");
        } catch (Exception e) {
            logger.error("Non si clicca sulla voce notifiche dell'impresa con errore:" + e.getMessage());
            Assert.fail("Non si clicca sulla voce notifiche dell'impresa con errore:" + e.getMessage());
        }
    }

}


