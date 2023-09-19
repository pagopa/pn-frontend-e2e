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
        this.confermaButtonPopUp.click();
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
        this.annullaButton.click();
    }

}
