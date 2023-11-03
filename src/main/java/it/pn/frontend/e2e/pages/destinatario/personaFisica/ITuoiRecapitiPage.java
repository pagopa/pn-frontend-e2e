package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ITuoiRecapitiPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("ITuoiRecapitiPage");

    @FindBy(id="side-item-I tuoi recapiti")
    WebElement iTuoiRecapitiButton;

    @FindBy(xpath = "//button[@data-testid='add email']")
    WebElement avvisamiViaEmailButton;

    @FindBy(id = "phone")
    WebElement phoneNumInputField;

    @FindBy(xpath = "//button[@data-testid='add phone']")
    WebElement avvisamiViaSMSButton;

    @FindBy(id = "pec")
    WebElement inserimentoPecField;

    @FindBy(id = "add-contact")
    WebElement confermaButton;

    @FindBy(id = "code-cancel-button")
    WebElement annullaButton;

    @FindBy(id = "code-confirm-button")
    WebElement confermaButtonPopUp;

    @FindBy(xpath = "//button[contains(text(),'Elimina')]")
    WebElement eliminaButton;

    public ITuoiRecapitiPage(WebDriver driver) {
        super(driver);
    }

    public void ITuoiRecapitiButtonClick() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.iTuoiRecapitiButton));
            this.js().executeScript("arguments[0].click()", this.iTuoiRecapitiButton);
        }catch(TimeoutException e){
            logger.error(" il bottone i tuoi Recapiti non trovato o non è cliccabile: "+ e.getMessage());
            Assert.fail("il bottone i tuoi Recapiti non trovato o non è cliccabile: "+ e.getMessage());
        }
    }

    public void waitLoadITuoiRecapitiPage() {
        try {
            By titlePageBy = By.id("I tuoi recapiti-page");
            By subTitlePageBy = By.id("subtitle-page");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(subTitlePageBy));
            logger.info("La pagina I Tuoi Recapiti si vede correttamente");
        }catch (TimeoutException e){
            logger.error("La pagina I Tuoi Recapiti NON si vede correttamente con errori:"+e.getMessage());
            Assert.fail("La pagina I Tuoi Recapiti NON si vede correttamente con errori:"+e.getMessage());
        }
    }

    public void eliminaPecEsistente(){
        this.eliminaButton.click();
        waitLoadPopUp();
        By confermaRimuoviPECBy = By.xpath("//button[contains(text(),'Annulla')]/following-sibling::button");
        this.getWebDriverWait(30).withMessage("Il bottone conferma del popup rimuovi pec non presente").until(ExpectedConditions.visibilityOfElementLocated(confermaRimuoviPECBy));
        this.element(confermaRimuoviPECBy).click();
    }

    public void insertEmailPEC(String emailPEC) {

        try{
            By inserimentoPecFieldBy = By.id("pec");
            this.getWebDriverWait(20).withMessage("input pec field non trovato").until(ExpectedConditions.visibilityOfElementLocated(inserimentoPecFieldBy));
            this.element(inserimentoPecFieldBy).sendKeys(emailPEC);

        }catch (TimeoutException e){
            eliminaPecEsistente();
            By inserimentoPecFieldBy = By.id("pec");
            this.getWebDriverWait(20).withMessage("input pec field non trovato").until(ExpectedConditions.visibilityOfElementLocated(inserimentoPecFieldBy));
            this.element(inserimentoPecFieldBy).sendKeys(emailPEC);
        }
    }
    public void confermaButtonClick() {
        this.confermaButton.click();
    }

    public void waitLoadPopUp() {
        try {
            By titleBy = By.xpath("//h2[contains(@id,'dialog-title')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleBy));
            logger.info("Il po-up di conferma viene visualizzato correttamente");
        }catch (TimeoutException e){
            logger.error("Il pop-up di conferma NON viene visualizzato correttamente con errori: "+e.getMessage());
            Assert.fail("Il pop-up di conferma NON viene visualizzato correttamente con errori:"+e.getMessage());
        }
    }

    public void sendOTP(String otp) {
        String[] otps = otp.split("");
        try{
            By otpInputby = By.xpath("//input[contains(@id,'code-input')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(otpInputby));
            List<WebElement> otpInputs= this.elements(otpInputby);
            for (int i=0; i<otps.length; i++) {
                otpInputs.get(i).sendKeys(otps[i]);
            }
            logger.info("Il codice otp viene inserito correttamente");
        }catch (TimeoutException e){
            logger.error("Il codice otp NON viene inserito correttamente con errore:"+e.getMessage());
            Assert.fail("Il codice otp NON viene inserito correttamente con errore:"+e.getMessage());
        }
    }

    public void annullaButtonClick() {
        this.annullaButton.click();
    }

    public void confermaButtonClickPopUp() {
        this.confermaButtonPopUp.click();
    }

    public String getPecErrorMessage(){
        By errorBy = By.id("pec-helper-text");
        WebElement errorMessage = driver.findElement(errorBy);
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public boolean verificaButtoneConfermaDisabilitato(){
        try{
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.confermaButton));
            return Boolean.parseBoolean(this.confermaButton.getAttribute("disabled"));
        }catch (NoSuchElementException | TimeoutException e){
            return false;
        }
    }

    public void clickEliminaEmail(){
        By eliminaButtonBy = By.xpath("//button[contains(text(),'Elimina')]");
        this.driver.findElement(eliminaButtonBy).click();
    }

    public void clicModificaEmail(){
        By modificaButtonBy = By.xpath("//button[contains(text(),'Modifica')]");
        this.driver.findElement(modificaButtonBy).click();
    }
    public void clickSalvaemail(){
        By salvaButtonBy = By.xpath("//button[contains(text(),'Salva')]");
        this.driver.findElement(salvaButtonBy).click();
    }

    public void clickConfirmaEliminaEmailPopUp(){
        By popuptitle = By.id("dialog-title");
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popuptitle));

        By confirmaEliminaEmailBy = By.xpath("//button[contains(text(),'Conferma') and @tabindex='0']");
        this.driver.findElement(confirmaEliminaEmailBy).click();
    }

    public void eliminaEmailEsistente(){
        this.eliminaButton.click();
        waitLoadPopUp();
        By confermaRimuoviPECBy = By.xpath("//button[contains(text(),'Annulla')]/following-sibling::button");
        this.getWebDriverWait(30).withMessage("il Bottone conferma del popup rimuovi e-mail non presente").until(ExpectedConditions.visibilityOfElementLocated(confermaRimuoviPECBy));
        this.element(confermaRimuoviPECBy).click();

    }

    public void insertEmail(String emailPEC) {

        try{
            By inserimentoEmailFieldBy = By.id("email");
            this.getWebDriverWait(20).withMessage("input pec field non trovato").until(ExpectedConditions.visibilityOfElementLocated(inserimentoEmailFieldBy));
            this.element(inserimentoEmailFieldBy).sendKeys(emailPEC);

        }catch (TimeoutException e){
            eliminaEmailEsistente();
            By inserimentoEmailFieldBy = By.id("email");
            this.getWebDriverWait(20).withMessage("input pec field non trovato").until(ExpectedConditions.visibilityOfElementLocated(inserimentoEmailFieldBy));
            this.element(inserimentoEmailFieldBy).sendKeys(emailPEC);
        }

    }

    public void clickAvvisamiViaEmail(){
        this.avvisamiViaEmailButton.click();
    }

    public boolean avvisamiViaEmailIsDisabled(){
        try{
            getWebDriverWait(30).withMessage("avvisami via email non è visibile").until(ExpectedConditions.visibilityOf(this.avvisamiViaEmailButton));
            return Boolean.parseBoolean(this.avvisamiViaEmailButton.getAttribute("disabled"));

        }catch (NoSuchElementException | TimeoutException e){
            return false;
        }
    }

    public String getEmailErrorMessage(){
        By errorBy = By.id("email-helper-text");
        WebElement errorMessage = driver.findElement(errorBy);
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public boolean verificaPopupConfirmaEmail(){
        By hoCapitoCheckboxBy = By.xpath("//span[contains(text(),'Ho capito')]/preceding-sibling::span/input");
        return this.driver.findElement(hoCapitoCheckboxBy).isSelected();
    }

    public void clickHoCapitoCheckBoxPopup(){
        By hoCapitoCheckboxBy = By.xpath("//span[contains(text(),'Ho capito')]/preceding-sibling::span/input");
        WebElement hoCapitoCheckBox = this.driver.findElement(hoCapitoCheckboxBy);
        hoCapitoCheckBox.click();
    }

    public void confirmaEmailPopup(){
        By popupConfirmaButtonBy = By.xpath("//button[@data-testid='disclaimer-confirm-button']");
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(popupConfirmaButtonBy));
        this.driver.findElement(popupConfirmaButtonBy).click();
    }

    public void insertTelephoneNumber(String phoneNumber){
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.phoneNumInputField));
        this.phoneNumInputField.sendKeys(phoneNumber);
    }

    public void clickAvvisamiViaSMS(){
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.avvisamiViaSMSButton));
        this.js().executeScript("arguments[0].click()",this.avvisamiViaSMSButton);
    }

    public String getPhoneErrorMessage(){
        By errorBy = By.id("phone-helper-text");
        WebElement errorMessage = driver.findElement(errorBy);
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public boolean avvisamiViaSMSIsDisabled(){
        try{
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.avvisamiViaSMSButton));
            return Boolean.parseBoolean(this.avvisamiViaSMSButton.getAttribute("disabled"));
        }catch (NoSuchElementException | TimeoutException e){
            return false;
        }
    }

}
