package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
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

    private WebTool webTool;

    public ITuoiRecapitiPage(WebDriver driver) {

        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void iTuoiRecapitiButtonClick() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("side-item-I tuoi recapiti"))));
            iTuoiRecapitiButton = driver.findElement(By.id("side-item-I tuoi recapiti"));
            js().executeScript("arguments[0].click()", iTuoiRecapitiButton);
        } catch (TimeoutException e) {
            logger.error("il bottone i tuoi Recapiti non trovato o non è cliccabile: " + e.getMessage());
            Assertions.fail("il bottone i tuoi Recapiti non trovato o non è cliccabile: " + e.getMessage());
        }
    }

    public void waitLoadITuoiRecapitiPage() {
        try {
           // WebElement titlePageByOne = driver.findElement(By.xpath("//h4[contains(@id,'Recapiti-page')]"));
           // WebElement titlePageByTwo = driver.findElement(By.xpath("//h4[contains(@id,'I tuoi recapiti-page')]"));
           // WebElement subTitlePageBy = driver.findElement(By.id("subtitle-page"));
            getWebDriverWait(10).until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[contains(@id,'Recapiti-page')]")))
            ));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("subtitle-page"))));
            logger.info("La pagina I Tuoi Recapiti si vede correttamente");
        } catch (TimeoutException e) {
            logger.error("La pagina I Tuoi Recapiti NON si vede correttamente con errori:" + e.getMessage());
            Assertions.fail("La pagina I Tuoi Recapiti NON si vede correttamente con errori:" + e.getMessage());
        }
    }

    public void waitLoadCourtesyContacts() {
        try {
           // WebElement courtesyContactTitle = driver.findElement(By.id("E-mail o numero di cellulare-page"));
            getWebDriverWait(10).withMessage("il titolo del contatto di cortesia non è presente o non ha il testo corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("E-mail o numero di cellulare-page"))),
                    ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("E-mail o numero di cellulare-page")), "E-mail o numero di cellulare")));
            //WebElement courtesyContactSubtitle = driver.findElement(By.xpath("//p[contains(@id,'subtitle-page') and contains(text(),'e-mail o un SMS')]"));
            final String subtitleText = "Quando c’è una notifica per te, ti inviamo un’e-mail o un SMS. Accedi a SEND per leggerla e pagare eventuali spese. Qui ricevi anche eventuali comunicazioni importanti.";
            getWebDriverWait(10).withMessage("il sottotitolo del contatto di cortesia non è presente o non ha il testo corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(@id,'subtitle-page') and contains(text(),'e-mail o un SMS')]"))),
                    ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//p[contains(@id,'subtitle-page') and contains(text(),'e-mail o un SMS')]")), subtitleText)));
            //WebElement emailTextBox = driver.findElement(By.id("default_email"));
            getWebDriverWait(10).withMessage("il campo email non è presente o non ha il placeholder corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email"))),
                    ExpectedConditions.attributeToBe(driver.findElement(By.id("default_email")), "placeholder", "Il tuo indirizzo e-mail")
            ));
           // WebElement ctaAvvisamiViaEmail = driver.findElement(By.id("courtesy-email-button"));
            getWebDriverWait(10).withMessage("il bottone avvisami via email non è presente").until(ExpectedConditions.visibilityOf( driver.findElement(By.id("courtesy-email-button"))));
            //WebElement contactDisclaimer = driver.findElement(By.xpath("//div[@data-testid='contacts disclaimer']"));
            final String disclaimerText = "Se non hai la PEC, leggi subito la notifica: non riceverai la raccomandata cartacea e risparmierai tempo e denaro.";
            getWebDriverWait(10).withMessage("il disclaimer del contatto di cortesia non è presente o non ha il testo corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='contacts disclaimer']"))),
                    ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@data-testid='contacts disclaimer']")), disclaimerText)
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
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//input[contains(@id,'code-input')]"))));
            List<WebElement> otpInputs = driver.findElements(By.xpath("//input[contains(@id,'code-input')]"));
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
        webTool.waitTime(2);
        WebElement salvaButtonBy = driver.findElement(By.xpath("//button[contains(text(),'Salva')]"));
        salvaButtonBy.click();
    }

    public void eliminaEmailEsistente() {
        getWebDriverWait(10).withMessage("il Bottone elimina e-mail non presente").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id='cancelContact-default_email']"))));
        WebElement eliminaMailButton = driver.findElement(By.xpath("//button[@id='cancelContact-default_email']"));
        js().executeScript("arguments[0].click();", eliminaMailButton);
    }

    public void insertEmail(String emailPEC) {
        getWebDriverWait(20).withMessage("input pec field non trovato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email"))));
        WebElement inserimentoEmailFieldBy = driver.findElement(By.id("default_email"));
        inserimentoEmailFieldBy.sendKeys(emailPEC);
    }


    public void insertTelephoneNumber(String phoneNumber) {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_sms"))));
        phoneNumInputField = driver.findElement(By.id("default_sms"));
        phoneNumInputField.sendKeys(phoneNumber);
    }

    public void clickAvvisamiViaSMS() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_sms-button"))));
        avvisamiViaSMSButton = driver.findElement(By.id("default_sms-button"));
        js().executeScript("arguments[0].click()", avvisamiViaSMSButton);
    }

    public String getPhoneErrorMessage() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_sms-helper-text"))));
        WebElement errorMessage = driver.findElement(By.id("default_sms-helper-text"));
        return errorMessage.getText();
    }

    public boolean avvisamiViaSMSIsDisabled() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_sms-button"))));
            avvisamiViaSMSButton = driver.findElement(By.id("default_sms-button"));
            return Boolean.parseBoolean(this.avvisamiViaSMSButton.getAttribute("disabled"));
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }


    public void cancellaTesto() {
        try {
            webTool.waitTime(3);
            WebElement pecInput = driver.findElement(By.id("default_email"));
            js().executeScript("arguments[0].click()", pecInput);
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
        //WebElement newEmailBy = driver.findElement(By.xpath("//p[contains(text(),'provaemail@test.it')]"));
        getWebDriverWait(30).withMessage("La nuova mail non si visualizza correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(),'provaemail@test.it')]"))));
    }


    public void selezionaTipoEmail() {
        webTool.waitTime(2);
        tipoIndirizzoField.click();
        // wait 2 seconds for the options to become visible
        webTool.waitTime(2);
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo email")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("dropdown-EMAIL"))));
        WebElement opzionePEC = driver.findElement(By.id("dropdown-EMAIL"));
        opzionePEC.click();
    }

    public void selezionaTipoPec() {
        webTool.waitTime(2);
        tipoIndirizzoField = driver.findElement(By.id("addressType"));
        tipoIndirizzoField.click();
        // wait 2 seconds for the options to become visible
        webTool.waitTime(2);

        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo email")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("dropdown-PEC"))));
        WebElement opzionePEC = driver.findElement(By.id("dropdown-PEC"));
       opzionePEC.click();
    }

    public void checkPostModifica() {
        // WebElement saveButton = driver.findElement(By.id("saveContact-default_email"));
        // WebElement cancelButton = driver.findElement(By.xpath("//button[contains(text(),'Annulla')]"));
        // WebElement emailField = driver.findElement(By.id("default_email"));
        getWebDriverWait(10).withMessage("Non si visualizza il bottone salva e non è cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(driver.findElement(By.id("saveContact-default_email"))),
                ExpectedConditions.elementToBeClickable(driver.findElement(By.id("saveContact-default_email")))));
        getWebDriverWait(10).withMessage("Non si visualizza il bottone annulla e non è cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Annulla')]"))),
                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Annulla')]")))));
        getWebDriverWait(10).withMessage("Non si visualizza il campo email e non è modificabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email"))),
                ExpectedConditions.attributeToBe(driver.findElement(By.id("default_email")), "readonly", ""),
                ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.id("default_email")), "value")));
    }

    public void checkRiquadroPEC() {
        try {
           // WebElement titleSection = driver.findElement(By.id("legalContactsTitle"));
           // WebElement pecField = driver.findElement(By.id("default_pec"));
            //WebElement confirmButton = driver.findElement(By.id("default_pec-button"));
           // subTitlesSection = driver.findElement(By.xpath("//*[@id='legalContactsSection']/p"));
            List<WebElement> infoBanner = driver.findElements(By.xpath("//p[@data-testid='DigitalContactsCardDescription']"));
            getWebDriverWait(10).withMessage("Non si visualizza il titolo della sezione recapito legale o il contenuto è errato").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("legalContactsTitle"))),
                    ExpectedConditions.attributeToBe(driver.findElement(By.id("legalContactsTitle")), "innerText", "Recapito a valore legale")));
            getWebDriverWait(10).withMessage("Non si visualizza il sottotitolo della sezione recapito legale o il contenuto è errato").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='legalContactsSection']/p"))),
                    ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//*[@id='legalContactsSection']/p")), "innerText", "È il recapito ufficiale che scegli per ricevere comunicazioni a valore legale dalla PA. Se attivi un recapito a valore legale riceverai le notifiche di SEND solo in digitale, senza più preoccuparti dei documenti cartacei.")));
            getWebDriverWait(10).withMessage("Non si visualizza il campo pec o non è modificabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("default_pec"))),
                    ExpectedConditions.attributeToBe(driver.findElement(By.id("default_pec")), "placeholder", "La tua PEC")));
            getWebDriverWait(10).withMessage("Non si visualizza il bottone conferma o non è cliccabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("default_pec-button"))),
                    ExpectedConditions.not(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("default_pec-button"))))));
            getWebDriverWait(10).withMessage("Non si visualizza il banner informativo o il suo contenuto è errato").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(infoBanner.get(0)),
                    ExpectedConditions.attributeToBe(infoBanner.get(1), "innerText", "Quando un ente invia una comunicazione per te su SEND, ricevi l’avviso ufficiale sulla PEC che hai scelto.")));
            logger.info("Il riquadro PEC si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il riquadro PEC NON si visualizza correttamente con errori:" + e.getMessage());
            Assertions.fail("Il riquadro PEC NON si visualizza correttamente con errori:" + e.getMessage());
        }
    }
}
