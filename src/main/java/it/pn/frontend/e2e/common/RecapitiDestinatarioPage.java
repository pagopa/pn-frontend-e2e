package it.pn.frontend.e2e.common;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RecapitiDestinatarioPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("RecapitiDestinatarioPage");

    @FindBy(id = "pec")
    WebElement inserimentoPecField;

    @FindBy(id = "add-contact")
    WebElement confermaButton;

    @FindBy(id = "code-confirm-button")
    WebElement confermaButtonPopUp;

    @FindBy(id = "code-cancel-button")
    WebElement annullaButton;

    @FindBy(xpath = "//button[@data-testid='add email']")
    WebElement avvisamiMailButton;

    @FindBy(xpath = "//button[@data-testid='add phone']")
    WebElement avvisamiSMSButton;

    @FindBy(id = "email")
    WebElement inserimentoMailField;

    @FindBy(id = "phone")
    WebElement inserimentoPhoneField;

    public RecapitiDestinatarioPage(WebDriver driver) {
        super(driver);
    }

    public void insertEmailPEC(String emailPEC) {
        getWebDriverWait(30).withMessage("pec input field non è visibile").until(ExpectedConditions.visibilityOf(this.inserimentoPecField));
        if (inserimentoPecField.isDisplayed()){
            inserimentoPecField.sendKeys(emailPEC);
        }else {
            this.js().executeScript("arguments[0].scrollIntoView(true);",inserimentoPecField);
            inserimentoPecField.sendKeys(emailPEC);
        }
    }

    public void confermaButtonClick() {
        getWebDriverWait(30).withMessage("conferma button non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.confermaButton));
        this.confermaButton.click();
    }

    public void waitLoadPopUp() {
        try {
            By titleBy = By.id("dialog-title");
            this.getWebDriverWait(30).withMessage("il titolo del popup non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleBy));
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

    public void confermaButtonClickPopUp() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.confermaButtonPopUp));
            this.confermaButtonPopUp.click();
        }catch(TimeoutException e){
            logger.error("Il buttone conferma all'interno del popup non è cliccabile con errore:"+e.getMessage());
            Assert.fail("Il buttone conferma all'interno del popup non è cliccabile con errore:"+e.getMessage());
        }
    }
    public void waitMessaggioErrore() {
        try {
            By messaggioErroreBy = By.id("error-alert");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(messaggioErroreBy));
            logger.info("Il messaggio di errore viene visualizzato correttamente");
        }catch (TimeoutException e){
            logger.error("Il messaggio di errore non viene visualizzato con errore: "+e.getMessage());
            Assert.fail("Il messaggio di errore non viene visualizzato con errore: "+e.getMessage());
        }
    }

    public void annullaButtonClick() {
        try{
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.annullaButton));
            this.annullaButton.click();
        }catch(TimeoutException e){
            logger.error("Il buttone annula non è cliccabile con errore: "+e.getMessage());
            Assert.fail("Il buttone annula non è cliccabile con errore: "+e.getMessage());
        }

    }

    public void clickAvvisami() {
        getWebDriverWait(30).withMessage("Il buttone avvisami della mail non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.avvisamiMailButton));
        this.avvisamiMailButton.click();}

    public void clickAvvisamiSMS() {
        getWebDriverWait(30).withMessage("Il buttone avvisami del sms non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.avvisamiSMSButton));
        this.avvisamiSMSButton.click();}

    public void insertEmail(String email) {
        getWebDriverWait(30).withMessage("l'input mail non è visibile").until(ExpectedConditions.visibilityOf(this.inserimentoMailField));
        if (inserimentoMailField.isDisplayed()){
            inserimentoMailField.sendKeys(email);
        }else {
            this.js().executeScript("arguments[0].scrollIntoView(true);",inserimentoMailField);
            inserimentoMailField.sendKeys(email);
        }
    }

    public void insertPhone(String cellulare) {
        getWebDriverWait(30).withMessage("l'input numero telefono non è visibile").until(ExpectedConditions.visibilityOf(this.inserimentoPhoneField));
        if (inserimentoPhoneField.isDisplayed()){
            inserimentoPhoneField.sendKeys(cellulare);
        }else {
            this.js().executeScript("arguments[0].scrollIntoView(true);",inserimentoPhoneField);
            inserimentoPhoneField.sendKeys(cellulare);
        }
    }
}
