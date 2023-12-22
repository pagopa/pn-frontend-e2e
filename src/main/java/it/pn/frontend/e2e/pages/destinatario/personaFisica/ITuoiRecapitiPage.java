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

    @FindBy(id = "side-item-I tuoi recapiti")
    WebElement iTuoiRecapitiButton;

    @FindBy(id = "phone")
    WebElement phoneNumInputField;

    @FindBy(xpath = "//button[@data-testid='add phone']")
    WebElement avvisamiViaSMSButton;

    @FindBy(id = "addressType")
    WebElement tipoIndirizzoField;

    public ITuoiRecapitiPage(WebDriver driver) {
        super(driver);
    }

    public void ITuoiRecapitiButtonClick() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.iTuoiRecapitiButton));
            this.js().executeScript("arguments[0].click()", this.iTuoiRecapitiButton);
        } catch (TimeoutException e) {
            logger.error(" il bottone i tuoi Recapiti non trovato o non è cliccabile: " + e.getMessage());
            Assert.fail("il bottone i tuoi Recapiti non trovato o non è cliccabile: " + e.getMessage());
        }
    }

    public void waitLoadITuoiRecapitiPage() {
        try {
           By titlePageBy = By.xpath("//h4[contains(@id,'ecapiti-page')]");
            By subTitlePageBy = By.id("subtitle-page");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(subTitlePageBy));
            logger.info("La pagina I Tuoi Recapiti si vede correttamente");
        } catch (TimeoutException e) {
            logger.error("La pagina I Tuoi Recapiti NON si vede correttamente con errori:" + e.getMessage());
            Assert.fail("La pagina I Tuoi Recapiti NON si vede correttamente con errori:" + e.getMessage());
        }
    }



    public void sendOTP(String otp) {
        String[] otps = otp.split("");
        try {
            By otpInputby = By.xpath("//input[contains(@id,'code-input')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(otpInputby));
            List<WebElement> otpInputs = this.elements(otpInputby);
            for (int i = 0; i < otps.length; i++) {
                otpInputs.get(i).sendKeys(otps[i]);
            }
            logger.info("Il codice otp viene inserito correttamente");
        } catch (TimeoutException e) {
            logger.error("Il codice otp NON viene inserito correttamente con errore:" + e.getMessage());
            Assert.fail("Il codice otp NON viene inserito correttamente con errore:" + e.getMessage());
        }
    }



    public void  clickSalvaemail() {
        By salvaButtonBy = By.xpath("//button[contains(text(),'Salva')]");
        this.driver.findElement(salvaButtonBy).click();
    }

    public void eliminaEmailEsistente() {
        By eliminaMailButton = By.xpath("//p[contains(text(),'Indirizzo e-mail')]/following-sibling::div/div/button[contains(text(),'Elimina')]");
        this.getWebDriverWait(40).withMessage("il Bottone elimina e-mail non presente").until(ExpectedConditions.elementToBeClickable(eliminaMailButton));
        this.js().executeScript("arguments[0].click();", this.element(eliminaMailButton));
    }

    public void insertEmail(String emailPEC) {
        By inserimentoEmailFieldBy = By.id("email");
        this.getWebDriverWait(30).withMessage("input pec field non trovato").until(ExpectedConditions.visibilityOfElementLocated(inserimentoEmailFieldBy));
        this.element(inserimentoEmailFieldBy).sendKeys(emailPEC);
    }



    public void insertTelephoneNumber(String phoneNumber) {
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.phoneNumInputField));
        this.phoneNumInputField.sendKeys(phoneNumber);
    }

    public void clickAvvisamiViaSMS() {
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.avvisamiViaSMSButton));
        this.js().executeScript("arguments[0].click()", this.avvisamiViaSMSButton);
    }

    public String getPhoneErrorMessage() {
        By errorBy = By.id("phone-helper-text");
        WebElement errorMessage = driver.findElement(errorBy);
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public boolean avvisamiViaSMSIsDisabled() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.avvisamiViaSMSButton));
            return Boolean.parseBoolean(this.avvisamiViaSMSButton.getAttribute("disabled"));
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }


    public void cancellaTesto() {
        try {
            By pecInputBy = By.id("email");
            WebElement pecInput = this.element(pecInputBy);
            this.js().executeScript("arguments[0].click()", pecInput);
            String emailPec = pecInput.getAttribute("value");
            for (int i = 0; i < emailPec.length(); i++) {
                pecInput.sendKeys(Keys.BACK_SPACE);
            }
        } catch (TimeoutException e) {
            logger.error("Non si riesce ad cancellare il testo della  email :" + e.getMessage());
            Assert.fail("Non si riesce ad cancellare il testo della  email :" + e.getMessage());
        }
    }

    public void verificaEmailModificata() {
        By newEmailBy = By.xpath("//p[contains(text(),'provaemail@test.it')]");
        getWebDriverWait(30).withMessage("La nuova mail non si visualizza correttamente").until(ExpectedConditions.visibilityOfElementLocated(newEmailBy));
    }



    public void selezionaTipoEmail() {
        this.tipoIndirizzoField.click();

        By opzionePEC = By.xpath("//li[@data-value ='EMAIL']");
        this.getWebDriverWait(30).withMessage("Non è visibile l'opzione 'Indirizzo email'").until(ExpectedConditions.elementToBeClickable(opzionePEC));
        this.element(opzionePEC).click();
    }

    public void waitLoadRecapitiGiaAssociatoSection() {
        By titlePage = By.xpath("//p[contains(text(),'Già associati')]");
        By tableBy = By.xpath("//table[@aria-label = 'Già associati']");
        By eliminaBy = By.xpath("//form[@data-testid = 'specialContactForm']//div//button[contains(text(),'Elimina')]");
        By modificaBy = By.xpath("//form[@data-testid = 'specialContactForm']//div//button[contains(text(),'Modifica')]");
        this.getWebDriverWait(30).withMessage("Non si visualizza il titolo 'Gia Associati'").until(ExpectedConditions.visibilityOfElementLocated(titlePage));
        this.getWebDriverWait(30).withMessage("Non si visualizza il titolo 'Gia Associati'").until(ExpectedConditions.visibilityOfElementLocated(tableBy));
        this.getWebDriverWait(30).withMessage("Non si visualizza il titolo 'Gia Associati'").until(ExpectedConditions.visibilityOfElementLocated(eliminaBy));
        this.getWebDriverWait(30).withMessage("Non si visualizza il titolo 'Gia Associati'").until(ExpectedConditions.visibilityOfElementLocated(modificaBy));
    }
}
