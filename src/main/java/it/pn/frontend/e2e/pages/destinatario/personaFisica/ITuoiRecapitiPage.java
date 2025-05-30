package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
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
        WebElement buttonTuoiRecapiti = getWebDriverWait(20)
                .withMessage("Impossibile cliccare su menu laterale 'I tuoi recapiti'")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("side-item-I tuoi recapiti")));
        getWebDriverWait(20).until(ExpectedConditions.visibilityOf(buttonTuoiRecapiti));
        getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(buttonTuoiRecapiti));
        js().executeScript("arguments[0].scrollIntoView(true);", buttonTuoiRecapiti);
        js().executeScript("arguments[0].click();", buttonTuoiRecapiti);

        logger.info("Cliccato su 'I tuoi recapiti' con successo.");
    } catch (TimeoutException e) {
        Assertions.fail("Il bottone 'I tuoi recapiti' non trovato o non è cliccabile: " + e.getMessage());
    }
}
    public void waitLoadITuoiRecapitiPage() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[contains(@id,'Recapiti-page')]")))
            ));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("subtitle-page"))));
            logger.info("La pagina I Tuoi Recapiti si vede correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("La pagina I Tuoi Recapiti NON si vede correttamente con errori:" + e.getMessage());
        }
    }

    public void waitLoadCourtesyContacts() {
        try {
            getWebDriverWait(10).withMessage("il titolo del contatto di cortesia non è presente o non ha il testo corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("E-mail o numero di cellulare-page"))),
                    ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("E-mail o numero di cellulare-page")), "E-mail o numero di cellulare")));
            final String subtitleText = "Quando c’è una notifica per te, ti inviamo un’e-mail o un SMS. Accedi a SEND per leggerla e pagare eventuali spese. Qui ricevi anche eventuali comunicazioni importanti.";
            getWebDriverWait(10).withMessage("il sottotitolo del contatto di cortesia non è presente o non ha il testo corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(@id,'subtitle-page') and contains(text(),'e-mail o un SMS')]"))),
                    ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//p[contains(@id,'subtitle-page') and contains(text(),'e-mail o un SMS')]")), subtitleText)));
            getWebDriverWait(10).withMessage("il campo email non è presente o non ha il placeholder corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email"))),
                    ExpectedConditions.attributeToBe(driver.findElement(By.id("default_email")), "placeholder", "Il tuo indirizzo e-mail")
            ));
            getWebDriverWait(10).withMessage("il bottone avvisami via email non è presente").until(ExpectedConditions.visibilityOf( driver.findElement(By.id("courtesy-email-button"))));
            final String disclaimerText = "Se non hai la PEC, leggi subito la notifica: non riceverai la raccomandata cartacea e risparmierai tempo e denaro.";
            getWebDriverWait(10).withMessage("il disclaimer del contatto di cortesia non è presente o non ha il testo corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='contacts disclaimer']"))),
                    ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@data-testid='contacts disclaimer']")), disclaimerText)
            ));
            logger.info("Il contatto di cortesia si visualizza correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("il contatto di cortesia non si visualizza correttamente con errori:" + e.getMessage());
        }
    }

    public void waitLoadAttivazioneDomicilioDigitalePage() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[@data-testid='wizard-feedback-title']")))
            );
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@data-testid='wizard-feedback-button']"))));
            logger.info("La pagina Attivazione Domicilio Digitale si vede correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("La pagina Attivazione Domicilio Digitale NON si vede correttamente con errori:" + e.getMessage());
        }
    }

    public void waitLoadGestisciIlTuoDominioDigitalePage() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='wizard-title']")))
            );
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='legalContactManager']"))));
            logger.info("La pagina Gestisci Il Tuo Domicilio Digitale si vede correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("La pagina Gestisci Il Tuo Domicilio Digitale NON si vede correttamente con errori:" + e.getMessage());
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
        getWebDriverWait(20).withMessage("input email field non trovato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email"))));
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
            Assertions.fail("Non si riesce ad cancellare il testo della  email :" + e.getMessage());
        }
    }

    public void verificaEmailModificata() {
        getWebDriverWait(30)
                .withMessage("La nuova mail non si visualizza correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'provaemail@test.it')]")));
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
            Assertions.fail("Il riquadro PEC NON si visualizza correttamente con errori:" + e.getMessage());
        }
    }
    public void clickConfermaEmail() {
        webTool.waitTime(2);
        WebElement confermaEmail = getWebDriverWait(10)
                .withMessage("Non si visualizza il bottone salva e non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("saveContact-default_email"))));
        confermaEmail.click();
    }

    public void siCliccaSulBottoneDelPopUpOkHoCapito() {
        try {
            webTool.waitTime(6);
            logger.info("metodo Ok Ho Capito");
            WebElement okHoCapitoButton = getWebDriverWait(30)
                    .withMessage("Non si visualizza il bottone 'Ok ho capito' nel pop-up")
                    .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@data-testid='understandButton']"))));

            // Verifica se il bottone è visibile e cliccabile
            if (okHoCapitoButton.isDisplayed()) {
                okHoCapitoButton.click();
                logger.info("Bottone 'Ok ho capito' cliccato nel pop-up.");
            } else {
                // Se non è visibile, utilizza JavaScript per cliccarlo
                js().executeScript("arguments[0].click()", okHoCapitoButton);
                logger.info("Bottone 'Ok ho capito' cliccato tramite JavaScript.");
            }

        } catch (TimeoutException e) {
            // Gestione del caso in cui il bottone non sia visibile entro il tempo limite
            Assertions.fail("Errore: Il bottone 'Ok ho capito' nel pop-up non è visibile entro il tempo limite. Dettagli: " + e.getMessage());
        } catch (Exception e) {
            // Gestione di eventuali altre eccezioni
            Assertions.fail("Si è verificato un errore imprevisto durante il clic sul bottone 'Ok ho capito'. Dettagli: " + e.getMessage());
        }
        webTool.waitTime(1);
    }

    public void siCliccaSulBottoneDelPopUpAnnulla() {
        webTool.waitTime(3);
        logger.info("metodo annulla Annulla");
        WebElement annullaPopUp = getWebDriverWait(30)
                .withMessage("Non si visualizza il bottone Annulla nel pop -up")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@data-testid='discardButton']"))));
        if (annullaPopUp.isDisplayed()) {
            annullaPopUp.click();
        } else {
            js().executeScript("arguments[0].click()", annullaPopUp);
        }

    }

    public void clickInsirisciPec() {
        WebElement inserisciPecButton =
                getWebDriverWait(30)
                        .withMessage("Impossibile trovare il tasto Inserisci PEC")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testid='pec-section']//button")));
        inserisciPecButton.click();
    }

    public void clickBottoneNotifica() {
        WebElement notificheButton = getWebDriverWait(30)
                .withMessage("Impossibile cliccare sul tasto notifica")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='sideMenuItem-Notifiche']")));
        notificheButton.click();
    }

    public void clickLinkConsegnata() {
        WebElement consegnataLink = getWebDriverWait(30)
                .withMessage("Impossibile cliccare sul tasto notifica")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'consegnata')]")));
        consegnataLink.click();
    }

    public void clickBottoneNotificheDellImpresa() {
        WebElement notificheImpresaButton = getWebDriverWait(30)
                .withMessage("Impossibile cliccare NotificheDellImpresa")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Notifiche dell')]")));
        notificheImpresaButton.click();
    }

    public void clickBottoneConfermaPerModificaPEC() {

        WebElement button = getWebDriverWait(30)
                .withMessage("Pulsante 'Conferma' per la modifica  non trovato o non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("saveContact-default_pec")));

        button.click();
    }

    public void clickBottoneGestisci() {
        WebElement gestisciButton = getWebDriverWait(10)
                .withMessage("Impossibile cliccare sul bottone Gestisci ")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Gestisci')]")));
        gestisciButton.click();
    }

    public void clickBottoneIndietro() {
        WebElement indietroButton = getWebDriverWait(10)
                .withMessage("Impossibile cliccare sul bottone Indietro ")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='prev-button']")));
        indietroButton.click();
    }

    public void clickScaricaIO() {
        try {
            WebElement scaricaButton = getWebDriverWait(10)
                    .withMessage("Impossibile cliccare sul bottone Scarica l'app IO ")
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Scarica')]")));
            scaricaButton.click();
        }
        catch (TimeoutException e) {
            Assertions.fail("Utente ha già un profilo IO");
        }
    }

    public void clickCollegaSENDSuIO() {
        WebElement buttonCollegaSENDSuIO = getWebDriverWait(10)
                .withMessage("Impossibile cliccare sul bottone Collega SEND Su IO ")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirmButton']")));
        buttonCollegaSENDSuIO.click();
    }

    public void clickBottoneConfermaAttivaDomicilioDigitale() {
        WebElement buttonConferma = getWebDriverWait(10)
                .withMessage("Impossibile cliccare sul bottone Conferma Attiva Domicilio Digitale")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Conferma']")));
        buttonConferma.click();

    }

    public void verificaPresenzaBottoneAttivaSENDSuIO() {
        getWebDriverWait(10)
                .withMessage("Inpossibile verificare il bottone Attiva SEND Su IO ")
                .until(ExpectedConditions.elementToBeClickable(By.id("ioContactButton")));
    }

    public void checkImpossibileDisattivareDomicilioDigitale() {
        getWebDriverWait(10)
                .withMessage("Pop-up 'Impossibile disattivare il Domicilio Digitale' non è visibile")
                .until(ExpectedConditions.and(ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-title"))), ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//div[@data-testid='dialog-content']"))))));
    }

    public void siChiudeImpossibileDisattivareDomicilioDigitale() {
        WebElement buttonChiudi = getWebDriverWait(10)
                .withMessage("Bottone 'Ok, ho capito' per pop-up 'Impossibile disattivare il Domicilio Digitale' non è visibile")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("buttonClose"))));
        buttonChiudi.click();
    }

    public void clickNotifiche() {
        WebElement notificaButton = getWebDriverWait(10)
                .withMessage("Impossibile cliccare sul menu laterale la Voce Notifiche ")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='sideMenuItem-Notifiche']")));
        notificaButton.click();
    }

    public void clickLeTueNotifiche() {
        WebElement elemento = getWebDriverWait(10)
                .withMessage("Impossibile cliccare sul menu laterale la Voce Le Tue Notifiche ")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='sideMenuItem-Le tue notifiche']")));
        elemento.click();
    }

    public void clickBottoneIniziaNelBanner() {

        getWebDriverWait(10)
                .withMessage("Impossibile Trovare il Banner ")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='addDomicileBanner']")));

        WebElement iniziaButton = getWebDriverWait(10)
                .withMessage("Impossibile I tasto Inizia")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='addDomicileBanner']//button[contains(@class, 'MuiButton-root')]")));
        iniziaButton.click();
    }

    public void clickAnnullaServizioNotificheDigitali() {
        WebElement annullaButton = getWebDriverWait(10)
                .withMessage("Impossibile Trovare il tasto Annulla")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class, 'css-ipfk0c')]//button")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", annullaButton);
    }

    public void clickITuoiDati() {
        WebElement buttonITuoiDati = getWebDriverWait(10)
                .withMessage("Impossibile trovare I Tuoi Dati Dall'icona della PF")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='party-menu-button']")));
        buttonITuoiDati.click();
        // Prendi tutte le voci del menu
        List<WebElement> menuItems =
                getWebDriverWait(10)
                        .withMessage("Impossibile trovare il menu a tendina I Tuoi Dati Dall'icona della PF")
                        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                By.cssSelector("ul[role='menu'] > li")
                        ));

        // Clicca la seconda voce (indice 0): "I TUoi dati", indipendente dalla lingua
        menuItems.get(0).click();
    }
}
