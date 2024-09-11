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

    @FindBy(id = "default_sms")
    WebElement phoneNumInputField;

    @FindBy(xpath = "//button[@data-testid='courtesy-sms-button']")
    WebElement avvisamiViaSMSButton;

    @FindBy(id = "addressType")
    WebElement tipoIndirizzoField;

    @FindBy(id = "subtitle-page")
    List<WebElement> subTitlesSection;

    public ITuoiRecapitiPage(WebDriver driver) {
        super(driver);
    }

    public void iTuoiRecapitiButtonClick() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(this.iTuoiRecapitiButton));
            this.js().executeScript("arguments[0].click()", this.iTuoiRecapitiButton);
        } catch (TimeoutException e) {
            logger.error("il bottone i tuoi Recapiti non trovato o non è cliccabile: " + e.getMessage());
            Assert.fail("il bottone i tuoi Recapiti non trovato o non è cliccabile: " + e.getMessage());
        }
    }

    public void waitLoadITuoiRecapitiPage() {
        try {
            By titlePageByOne = By.xpath("//h4[contains(@id,'Recapiti-page')]");
            By titlePageByTwo = By.xpath("//h4[contains(@id,'I tuoi recapiti-page')]");
            By subTitlePageBy = By.id("subtitle-page");
            getWebDriverWait(10).until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(titlePageByOne),
                    ExpectedConditions.visibilityOfElementLocated(titlePageByTwo)
            ));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(subTitlePageBy));
            logger.info("La pagina I Tuoi Recapiti si vede correttamente");
        } catch (TimeoutException e) {
            logger.error("La pagina I Tuoi Recapiti NON si vede correttamente con errori:" + e.getMessage());
            Assert.fail("La pagina I Tuoi Recapiti NON si vede correttamente con errori:" + e.getMessage());
        }
    }

    public void waitLoadCourtesyContacts() {
        try {
            By courtesyContactTitle = By.id("E-mail o numero di cellulare-page");
            getWebDriverWait(10).withMessage("il titolo del contatto di cortesia non è presente o non ha il testo corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(courtesyContactTitle),
                    ExpectedConditions.textToBe(courtesyContactTitle, "E-mail o numero di cellulare")));
            By courtesyContactSubtitle = By.xpath("//p[contains(@id,'subtitle-page') and contains(text(),'e-mail o un SMS')]");
            final String subtitleText = "Quando c’è una notifica per te, ti inviamo un’e-mail o un SMS. Accedi a SEND per leggerla e pagare eventuali spese. Qui ricevi anche eventuali comunicazioni importanti.";
            getWebDriverWait(10).withMessage("il sottotitolo del contatto di cortesia non è presente o non ha il testo corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(courtesyContactSubtitle),
                    ExpectedConditions.textToBe(courtesyContactSubtitle, subtitleText)));
            By emailTextBox = By.id("default_email");
            getWebDriverWait(10).withMessage("il campo email non è presente o non ha il placeholder corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(emailTextBox),
                    ExpectedConditions.attributeToBe(emailTextBox, "placeholder", "Il tuo indirizzo e-mail")
            ));
            By ctaAvvisamiViaEmail = By.id("courtesy-email-button");
            getWebDriverWait(10).withMessage("il bottone avvisami via email non è presente").until(ExpectedConditions.visibilityOfElementLocated(ctaAvvisamiViaEmail));
            By contactDisclaimer = By.xpath("//div[@data-testid='contacts disclaimer']");
            final String disclaimerText = "Se non hai la PEC, leggi subito la notifica: non riceverai la raccomandata cartacea e risparmierai tempo e denaro.";
            getWebDriverWait(10).withMessage("il disclaimer del contatto di cortesia non è presente o non ha il testo corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(contactDisclaimer),
                    ExpectedConditions.textToBe(contactDisclaimer, disclaimerText)
            ));
            logger.info("Il contatto di cortesia si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("il contatto di cortesia non si visualizza correttamente con errori:" + e.getMessage());
            Assert.fail("il contatto di cortesia non si visualizza correttamente con errori:" + e.getMessage());
        }
    }


    public void sendOTP(String otp) {
        String[] otps = otp.split("");
        try {
            By otpInputby = By.xpath("//input[contains(@id,'code-input')]");
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(otpInputby));
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


    public void clickSalvaEmail() {
        By salvaButtonBy = By.xpath("//button[contains(text(),'Salva')]");
        this.driver.findElement(salvaButtonBy).click();
    }

    public void eliminaEmailEsistente() {
        By eliminaMailButton = By.xpath("//button[@id='cancelContact-default']");
        getWebDriverWait(10).withMessage("il Bottone elimina e-mail non presente").until(ExpectedConditions.elementToBeClickable(eliminaMailButton));
        this.js().executeScript("arguments[0].click();", this.element(eliminaMailButton));
    }

    public void insertEmail(String emailPEC) {
        By inserimentoEmailFieldBy = By.id("default_email");
        getWebDriverWait(10).withMessage("input pec field non trovato").until(ExpectedConditions.visibilityOfElementLocated(inserimentoEmailFieldBy));
        this.element(inserimentoEmailFieldBy).sendKeys(emailPEC);
    }


    public void insertTelephoneNumber(String phoneNumber) {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.phoneNumInputField));
        this.phoneNumInputField.sendKeys(phoneNumber);
    }

    public void clickAvvisamiViaSMS() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.avvisamiViaSMSButton));
        this.js().executeScript("arguments[0].click()", this.avvisamiViaSMSButton);
    }

    public String getPhoneErrorMessage() {
        By errorBy = By.id("default_sms-helper-text");
        WebElement errorMessage = driver.findElement(errorBy);
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(errorMessage));
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
            By pecInputBy = By.id("default_email");
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
        // wait 2 seconds for the options to become visible
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("errore: " + e.getMessage());
        }
        By opzionePEC = By.id("dropdown-EMAIL");
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo email")
                .until(ExpectedConditions.elementToBeClickable(opzionePEC));
        this.element(opzionePEC).click();
    }

    public void selezionaTipoPec() {
        this.tipoIndirizzoField.click();
        // wait 2 seconds for the options to become visible
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("errore: " + e.getMessage());
        }
        By opzionePEC = By.id("dropdown-PEC");
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo email")
                .until(ExpectedConditions.elementToBeClickable(opzionePEC));
        this.element(opzionePEC).click();
    }

    public void checkPostModifica() {
        By saveButton = By.id("saveModifyButton-default");
        By cancelButton = By.xpath("//button[contains(text(),'Annulla')]");
        By emailField = By.id("default_email");
        getWebDriverWait(10).withMessage("Non si visualizza il bottone salva e non è cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(saveButton),
                ExpectedConditions.elementToBeClickable(saveButton)));
        getWebDriverWait(10).withMessage("Non si visualizza il bottone annulla e non è cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(cancelButton),
                ExpectedConditions.elementToBeClickable(cancelButton)));
        getWebDriverWait(10).withMessage("Non si visualizza il campo email e non è modificabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(emailField),
                ExpectedConditions.attributeToBe(this.element(emailField), "readonly", ""),
                ExpectedConditions.attributeToBeNotEmpty(this.element(emailField), "value")));
    }

    public void checkRiquadroPEC() {
        try {
            By titleSection = By.xpath("//div[@data-testid='DigitalContactsCardBody']//*/div/div");
            By pecField = By.id("default_pec");
            By confirmButton = By.id("add-contact");
            By infoBanner = By.xpath("//span[@data-testid='legal-contact-disclaimer']");
            getWebDriverWait(10).withMessage("Non si visualizza il titolo della sezione recapito legale o il contenuto è errato").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(titleSection),
                    ExpectedConditions.attributeToBe(this.element(titleSection), "innerText", "Recapito legale")));
            getWebDriverWait(10).withMessage("Non si visualizza il sottotitolo della sezione recapito legale o il contenuto è errato").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(subTitlesSection.get(1)),
                    ExpectedConditions.attributeToBe(subTitlesSection.get(1), "innerText", "Quando c’è una notifica per te, ti inviamo qui l’avviso di avvenuta ricezione. Accedi a SEND per leggerla e pagare eventuali spese.")));
            getWebDriverWait(10).withMessage("Non si visualizza il campo pec o non è modificabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(pecField),
                    ExpectedConditions.attributeToBe(this.element(pecField), "readonly", ""),
                    ExpectedConditions.attributeToBe(this.element(pecField), "placeholder", "Il tuo indirizzo PEC")));
            getWebDriverWait(10).withMessage("Non si visualizza il bottone conferma o non è cliccabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(confirmButton),
                    ExpectedConditions.not(ExpectedConditions.elementToBeClickable(confirmButton))));
            getWebDriverWait(10).withMessage("Non si visualizza il banner informativo o il suo contenuto è errato").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(infoBanner),
                    ExpectedConditions.attributeToBe(this.element(infoBanner), "innerText", "Questo è l’indirizzo principale che verrà utilizzato per inviarti gli avvisi di avvenuta ricezione in via digitale. Inserendolo, non riceverai più raccomandate cartacee.")));
            logger.info("Il riquadro PEC si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il riquadro PEC NON si visualizza correttamente con errori:" + e.getMessage());
            Assert.fail("Il riquadro PEC NON si visualizza correttamente con errori:" + e.getMessage());
        }
    }
}
