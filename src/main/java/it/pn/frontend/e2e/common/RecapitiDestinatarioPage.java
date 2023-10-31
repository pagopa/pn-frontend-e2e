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

    @FindBy(xpath = "//button[@data-testid='addContact']")
    WebElement confermaButton;

    @FindBy(xpath = "//button[contains(@data-testid,'codeConfirmButton')]")
    WebElement confermaButtonPopUp;

    @FindBy(xpath = "//button[@data-testid='codeCancelButton']")
    WebElement annullaButton;

    @FindBy(xpath = "//button[@data-testid='add email']")
    WebElement avvisamiMailButton;

    @FindBy(xpath = "//button[@data-testid='add phone']")
    WebElement avvisamiSMSButton;

    @FindBy(id = "email")
    WebElement inserimentoMailField;

    @FindBy(id = "phone")
    WebElement inserimentoPhoneField;

    @FindBy(xpath = "//div/h2[contains(text(),'Grazie!')]/following-sibling::div//button")
    WebElement confermaButtonPoPUpPec;

    public RecapitiDestinatarioPage(WebDriver driver) {
        super(driver);
    }

    public void insertEmailPEC(String emailPEC) {
        if (inserimentoPecField.isDisplayed()){
            inserimentoPecField.sendKeys(emailPEC);
        }else {
            this.js().executeScript("arguments[0].scrollIntoView(true);",inserimentoPecField);
            inserimentoPecField.sendKeys(emailPEC);
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
            By messaggioErroreBy = By.xpath("//div[contains(@data-testid, 'errorAlert')]");
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

    public void clickAvvisami() {this.avvisamiMailButton.click();}

    public void clickAvvisamiSMS() {this.avvisamiSMSButton.click();}

    public void insertEmail(String email) {
        if (inserimentoMailField.isDisplayed()){
            inserimentoMailField.sendKeys(email);
        }else {
            this.js().executeScript("arguments[0].scrollIntoView(true);",inserimentoMailField);
            inserimentoMailField.sendKeys(email);
        }
    }

    public void insertPhone(String cellulare) {
        if (inserimentoPhoneField.isDisplayed()){
            inserimentoPhoneField.sendKeys(cellulare);
        }else {
            this.js().executeScript("arguments[0].scrollIntoView(true);",inserimentoPhoneField);
            inserimentoPhoneField.sendKeys(cellulare);
        }
    }

    public void verificaPecAssociata() {
        try{
            By pecAssociata = By.xpath("//p[contains(text(), 'PEC associata')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(pecAssociata));
        }catch (TimeoutException e){
            logger.error("Pec non associata con errore:"+e.getMessage());
            Assert.fail("Pec non associata con errore:"+e.getMessage());
        }
    }

    public boolean siVisualizzaPopUpConferma() {
        try{
            By popUpConfermaBy = By.xpath("//h2[contains(text(),'Grazie!')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popUpConfermaBy));
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public void clickConfermaButton() {
        this.getWebDriverWait(30).withMessage("Il bottone conferma del pop up non cliccabile").until(ExpectedConditions.elementToBeClickable(confermaButtonPoPUpPec));
        this.confermaButtonPoPUpPec.click();
    }
}
