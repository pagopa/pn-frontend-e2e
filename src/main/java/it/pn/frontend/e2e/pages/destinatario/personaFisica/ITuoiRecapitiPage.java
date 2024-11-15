package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class ITuoiRecapitiPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("ITuoiRecapitiPage");

    @FindBy(id = "side-item-I tuoi recapiti")
    WebElement iTuoiRecapitiButton;

    @FindBy(id = "default_sms")
    WebElement phoneNumInputField;

    @FindBy(id = "default_sms-button")
    WebElement avvisamiViaSMSButton;

    @FindBy(id = "addressType")
    WebElement tipoIndirizzoField;

    @FindBy(xpath = "//*[@id='legalContactsSection']/p")
    WebElement subTitlesSection;

    public ITuoiRecapitiPage(WebDriver driver) {
        this.driver = driver;
    }

    public void iTuoiRecapitiButtonClick() {
        try {
            iTuoiRecapitiButton = driver.findElement(By.id("side-item-I tuoi recapiti"));
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(iTuoiRecapitiButton));
            js().executeScript("arguments[0].click()", this.iTuoiRecapitiButton);
        } catch (TimeoutException e) {
            logger.error("il bottone i tuoi Recapiti non trovato o non è cliccabile: " + e.getMessage());
            Assertions.fail("il bottone i tuoi Recapiti non trovato o non è cliccabile: " + e.getMessage());
        }
    }

    public void waitLoadITuoiRecapitiPage() {
        try {
            WebElement titlePageByOne = driver.findElement(By.xpath("//h4[contains(@id,'Recapiti-page')]"));
            WebElement titlePageByTwo = driver.findElement(By.xpath("//h4[contains(@id,'I tuoi recapiti-page')]"));
            WebElement subTitlePageBy = driver.findElement(By.id("subtitle-page"));
            getWebDriverWait(10).until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(titlePageByOne),
                    ExpectedConditions.visibilityOf(titlePageByTwo)
            ));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(subTitlePageBy));
            logger.info("La pagina I Tuoi Recapiti si vede correttamente");
        } catch (TimeoutException e) {
            logger.error("La pagina I Tuoi Recapiti NON si vede correttamente con errori:" + e.getMessage());
            Assertions.fail("La pagina I Tuoi Recapiti NON si vede correttamente con errori:" + e.getMessage());
        }
    }

    public void waitLoadCourtesyContacts() {
        try {
            WebElement courtesyContactTitle = driver.findElement(By.id("E-mail o numero di cellulare-page"));
            getWebDriverWait(10).withMessage("il titolo del contatto di cortesia non è presente o non ha il testo corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(courtesyContactTitle),
                    ExpectedConditions.textToBePresentInElement(courtesyContactTitle, "E-mail o numero di cellulare")));
            WebElement courtesyContactSubtitle = driver.findElement(By.xpath("//p[contains(@id,'subtitle-page') and contains(text(),'e-mail o un SMS')]"));
            final String subtitleText = "Quando c’è una notifica per te, ti inviamo un’e-mail o un SMS. Accedi a SEND per leggerla e pagare eventuali spese. Qui ricevi anche eventuali comunicazioni importanti.";
            getWebDriverWait(10).withMessage("il sottotitolo del contatto di cortesia non è presente o non ha il testo corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(courtesyContactSubtitle),
                    ExpectedConditions.textToBePresentInElement(courtesyContactSubtitle, subtitleText)));
            WebElement emailTextBox = driver.findElement(By.id("default_email"));
            getWebDriverWait(10).withMessage("il campo email non è presente o non ha il placeholder corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(emailTextBox),
                    ExpectedConditions.attributeToBe(emailTextBox, "placeholder", "Il tuo indirizzo e-mail")
            ));
            WebElement ctaAvvisamiViaEmail = driver.findElement(By.id("courtesy-email-button"));
            getWebDriverWait(10).withMessage("il bottone avvisami via email non è presente").until(ExpectedConditions.visibilityOf(ctaAvvisamiViaEmail));
            WebElement contactDisclaimer = driver.findElement(By.xpath("//div[@data-testid='contacts disclaimer']"));
            final String disclaimerText = "Se non hai la PEC, leggi subito la notifica: non riceverai la raccomandata cartacea e risparmierai tempo e denaro.";
            getWebDriverWait(10).withMessage("il disclaimer del contatto di cortesia non è presente o non ha il testo corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(contactDisclaimer),
                    ExpectedConditions.textToBePresentInElement(contactDisclaimer, disclaimerText)
            ));
            logger.info("Il contatto di cortesia si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("il contatto di cortesia non si visualizza correttamente con errori:" + e.getMessage());
            Assertions.fail("il contatto di cortesia non si visualizza correttamente con errori:" + e.getMessage());
        }
    }


    public void sendOTP(String otp) {
        String[] otps = otp.split("");
        try {
            List<WebElement> otpInputby = driver.findElements(By.xpath("//input[contains(@id,'code-input')]"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElements(otpInputby));
            List<WebElement> otpInputs = otpInputby;
            for (int i = 0; i < otps.length; i++) {
                otpInputs.get(i).sendKeys(otps[i]);
            }
            logger.info("Il codice otp viene inserito correttamente");
        } catch (TimeoutException e) {
            logger.error("Il codice otp NON viene inserito correttamente con errore:" + e.getMessage());
            Assertions.fail("Il codice otp NON viene inserito correttamente con errore:" + e.getMessage());
        }
    }


    public void clickSalvaEmail() {
        WebElement salvaButtonBy = driver.findElement(By.xpath("//button[contains(text(),'Salva')]"));
        salvaButtonBy.click();
    }

    public void eliminaEmailEsistente() {
        WebElement eliminaMailButton = driver.findElement(By.xpath("//button[@id='cancelContact-default_email']"));
        getWebDriverWait(10).withMessage("il Bottone elimina e-mail non presente").until(ExpectedConditions.elementToBeClickable(eliminaMailButton));
        this.js().executeScript("arguments[0].click();", eliminaMailButton);
    }

    public void insertEmail(String emailPEC) {
        WebElement inserimentoEmailFieldBy = driver.findElement(By.id("default_email"));
        getWebDriverWait(10).withMessage("input pec field non trovato").until(ExpectedConditions.visibilityOf(inserimentoEmailFieldBy));
        inserimentoEmailFieldBy.sendKeys(emailPEC);
    }


    public void insertTelephoneNumber(String phoneNumber) {
        phoneNumInputField = driver.findElement(By.id("default_sms"));
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(phoneNumInputField));
        phoneNumInputField.sendKeys(phoneNumber);
    }

    public void clickAvvisamiViaSMS() {
        avvisamiViaSMSButton = driver.findElement(By.id("default_sms-button"));
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(avvisamiViaSMSButton));
        js().executeScript("arguments[0].click()", avvisamiViaSMSButton);
    }

    public String getPhoneErrorMessage() {
        WebElement errorMessage = driver.findElement(By.id("default_sms-helper-text"));
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public boolean avvisamiViaSMSIsDisabled() {
        try {
            avvisamiViaSMSButton = driver.findElement(By.id("default_sms-button"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(avvisamiViaSMSButton));
            return Boolean.parseBoolean(this.avvisamiViaSMSButton.getAttribute("disabled"));
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }


    public void cancellaTesto() {
        try {
            WebElement pecInput = driver.findElement(By.id("default_email"));
    
            this.js().executeScript("arguments[0].click()", pecInput);
            String emailPec = pecInput.getAttribute("value");
            for (int i = 0; i < emailPec.length(); i++) {
                pecInput.sendKeys(Keys.BACK_SPACE);
            }
        } catch (TimeoutException e) {
            logger.error("Non si riesce ad cancellare il testo della  email :" + e.getMessage());
            Assertions.fail("Non si riesce ad cancellare il testo della  email :" + e.getMessage());
        }
    }

    public void verificaEmailModificata() {
        WebElement newEmailBy = driver.findElement(By.xpath("//p[contains(text(),'provaemail@test.it')]"));
        getWebDriverWait(30).withMessage("La nuova mail non si visualizza correttamente").until(ExpectedConditions.visibilityOf(newEmailBy));
    }


    public void selezionaTipoEmail() {
        this.tipoIndirizzoField.click();
        // wait 2 seconds for the options to become visible
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("errore: " + e.getMessage());
        }
        WebElement opzionePEC = driver.findElement(By.id("dropdown-EMAIL"));
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo email")
                .until(ExpectedConditions.elementToBeClickable(opzionePEC));
        opzionePEC.click();
    }

    public void selezionaTipoPec() {
        tipoIndirizzoField = driver.findElement(By.id("addressType"));
        tipoIndirizzoField.click();
        // wait 2 seconds for the options to become visible
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("errore: " + e.getMessage());
        }
        WebElement opzionePEC = driver.findElement(By.id("dropdown-PEC"));
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo email")
                .until(ExpectedConditions.elementToBeClickable(opzionePEC));
       opzionePEC.click();
    }

    public void checkPostModifica() {
        WebElement saveButton = driver.findElement(By.id("saveContact-default_email"));
        WebElement cancelButton = driver.findElement(By.xpath("//button[contains(text(),'Annulla')]"));
        WebElement emailField = driver.findElement(By.id("default_email"));
        getWebDriverWait(10).withMessage("Non si visualizza il bottone salva e non è cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(saveButton),
                ExpectedConditions.elementToBeClickable(saveButton)));
        getWebDriverWait(10).withMessage("Non si visualizza il bottone annulla e non è cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(cancelButton),
                ExpectedConditions.elementToBeClickable(cancelButton)));
        getWebDriverWait(10).withMessage("Non si visualizza il campo email e non è modificabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(emailField),
                ExpectedConditions.attributeToBe(emailField, "readonly", ""),
                ExpectedConditions.attributeToBeNotEmpty(emailField, "value")));
    }

    public void checkRiquadroPEC() {
        try {
            WebElement titleSection = driver.findElement(By.id("legalContactsTitle"));
            WebElement pecField = driver.findElement(By.id("default_pec"));
            WebElement confirmButton = driver.findElement(By.id("default_pec-button"));
            List<WebElement> infoBanner = driver.findElements(By.xpath("//p[@data-testid='DigitalContactsCardDescription']"));
            getWebDriverWait(10).withMessage("Non si visualizza il titolo della sezione recapito legale o il contenuto è errato").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(titleSection),
                    ExpectedConditions.attributeToBe(titleSection, "innerText", "Recapito a valore legale")));
            getWebDriverWait(10).withMessage("Non si visualizza il sottotitolo della sezione recapito legale o il contenuto è errato").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(subTitlesSection),
                    ExpectedConditions.attributeToBe(subTitlesSection, "innerText", "È il recapito ufficiale che scegli per ricevere comunicazioni a valore legale dalla PA. Se attivi un recapito a valore legale riceverai le notifiche di SEND solo in digitale, senza più preoccuparti dei documenti cartacei.")));
            getWebDriverWait(10).withMessage("Non si visualizza il campo pec o non è modificabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(pecField),
                    ExpectedConditions.attributeToBe(pecField, "placeholder", "La tua PEC")));
            getWebDriverWait(10).withMessage("Non si visualizza il bottone conferma o non è cliccabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(confirmButton),
                    ExpectedConditions.not(ExpectedConditions.elementToBeClickable(confirmButton))));
            getWebDriverWait(10).withMessage("Non si visualizza il banner informativo o il suo contenuto è errato").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(infoBanner.get(0)),
                    ExpectedConditions.attributeToBe(infoBanner.get(0), "innerText", "Quando un ente invia una comunicazione per te su SEND, ricevi l’avviso ufficiale sulla PEC che hai scelto.")));
            logger.info("Il riquadro PEC si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il riquadro PEC NON si visualizza correttamente con errori:" + e.getMessage());
            Assertions.fail("Il riquadro PEC NON si visualizza correttamente con errori:" + e.getMessage());
        }
    }
}
