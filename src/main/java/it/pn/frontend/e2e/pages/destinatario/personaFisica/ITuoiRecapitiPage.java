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
    private static final Logger logger = LoggerFactory.getLogger(ITuoiRecapitiPage.class);

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
            By titoloPageLocator = By.xpath("//h4[contains(@id,'Recapiti-page')]");
            By subtitleLocator = By.id("subtitle-page");

            getWebDriverWait(10)
                    .withMessage("Il titolo della pagina I Tuoi Recapiti non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(titoloPageLocator));

            getWebDriverWait(10)
                    .withMessage("Il sottotitolo della pagina I Tuoi Recapiti non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(subtitleLocator));

            logger.info("La pagina I Tuoi Recapiti si vede correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("La pagina I Tuoi Recapiti NON si vede correttamente con errori: " + e.getMessage());
        }
    }

    public void waitLoadCourtesyContacts() {
        try {
            By titoloPageLocator = By.id("E-mail o numero di cellulare-page");
            By subtitleLocator = By.xpath("//p[contains(@id,'subtitle-page') and contains(text(),'e-mail o un SMS')]");
            By emailFieldLocator = By.id("default_email");
            By emailButtonLocator = By.id("courtesy-email-button");
            By disclaimerLocator = By.xpath("//div[@data-testid='contacts disclaimer']");

            // Titolo
            getWebDriverWait(10).withMessage("Il titolo del contatto di cortesia non è presente o non ha il testo corretto")
                    .until(ExpectedConditions.and(
                            ExpectedConditions.visibilityOfElementLocated(titoloPageLocator),
                            ExpectedConditions.textToBePresentInElementLocated(titoloPageLocator, "E-mail o numero di cellulare")
                    ));

            // Sottotitolo
            final String subtitleText = "Quando c’è una notifica per te, ti inviamo un’e-mail o un SMS. Accedi a SEND per leggerla e pagare eventuali spese. Qui ricevi anche eventuali comunicazioni importanti.";
            getWebDriverWait(10).withMessage("Il sottotitolo del contatto di cortesia non è presente o non ha il testo corretto")
                    .until(ExpectedConditions.and(
                            ExpectedConditions.visibilityOfElementLocated(subtitleLocator),
                            ExpectedConditions.textToBePresentInElementLocated(subtitleLocator, subtitleText)
                    ));

            // Campo email
            getWebDriverWait(10).withMessage("Il campo email non è presente o non ha il placeholder corretto")
                    .until(ExpectedConditions.and(
                            ExpectedConditions.visibilityOfElementLocated(emailFieldLocator),
                            ExpectedConditions.attributeToBe(emailFieldLocator, "placeholder", "Il tuo indirizzo e-mail")
                    ));

            // Bottone email
            getWebDriverWait(10).withMessage("Il bottone avvisami via email non è presente")
                    .until(ExpectedConditions.visibilityOfElementLocated(emailButtonLocator));

            // Disclaimer
            final String disclaimerText = "Se non hai la PEC, leggi subito la notifica: non riceverai la raccomandata cartacea e risparmierai tempo e denaro.";
            getWebDriverWait(10).withMessage("Il disclaimer del contatto di cortesia non è presente o non ha il testo corretto")
                    .until(ExpectedConditions.and(
                            ExpectedConditions.visibilityOfElementLocated(disclaimerLocator),
                            ExpectedConditions.textToBePresentInElementLocated(disclaimerLocator, disclaimerText)
                    ));

            logger.info("Il contatto di cortesia si visualizza correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il contatto di cortesia non si visualizza correttamente con errori: " + e.getMessage());
        }
    }

    public void waitLoadAttivazioneDomicilioDigitalePage() {
        try {
            By titoloLocator = By.xpath("//h4[@data-testid='wizard-feedback-title']");
            By buttonLocator = By.xpath("//button[@data-testid='wizard-feedback-button']");

            // Titolo visibile
            getWebDriverWait(10).withMessage("Il titolo della pagina Attivazione Domicilio Digitale non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(titoloLocator));

            // Bottone cliccabile
            getWebDriverWait(10).withMessage("Il bottone della pagina Attivazione Domicilio Digitale non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(buttonLocator));

            logger.info("La pagina Attivazione Domicilio Digitale si visualizza correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("La pagina Attivazione Domicilio Digitale NON si visualizza correttamente con errore: " + e.getMessage());
        }
    }

    public void waitLoadGestisciIlTuoDominioDigitalePage() {
        try {
            By titoloLocator = By.xpath("//div[@data-testid='wizard-title']");
            By legalContactLocator = By.xpath("//div[@data-testid='legalContactManager']");

            // Titolo visibile
            getWebDriverWait(10).withMessage("Il titolo della pagina Gestisci Il Tuo Domicilio Digitale non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(titoloLocator));

            // Sezione contatto legale visibile
            getWebDriverWait(10).withMessage("La sezione contatto legale della pagina Gestisci Il Tuo Domicilio Digitale non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(legalContactLocator));

            logger.info("La pagina Gestisci Il Tuo Domicilio Digitale si visualizza correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("La pagina Gestisci Il Tuo Domicilio Digitale NON si visualizza correttamente con errore: " + e.getMessage());
        }
    }

    public void sendOTP(String otp) {
        String[] otps = otp.split("");
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//div[@data-testid='dialog-content']//div[@aria-hidden='true']//div"))));
            WebElement codiceDelegaInput = driver.findElement(By.xpath("//div[@data-testid='dialog-content']//input"));
            for (int i = 0; i < otps.length; i++) {
                codiceDelegaInput.sendKeys(otps[i]);
            }
            logger.info("Il codice otp viene inserito correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il codice otp NON viene inserito correttamente con errore:" + e.getMessage());
        }
    }

    public void clickSalvaEmail() {
        By salvaButtonLocator = By.xpath("//button[contains(text(),'Salva')]");

        webTool.waitTime(2); // breve attesa stabilizzante

        WebElement salvaButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Salva' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(salvaButtonLocator));

        salvaButton.click();
        logger.info("Click sul bottone 'Salva' eseguito correttamente");
    }

    public void eliminaEmailEsistente() {
        By eliminaMailLocator = By.id("cancelContact-default_email");
        WebElement eliminaMailButton = getWebDriverWait(10)
                .withMessage("Il bottone elimina e-mail non è presente o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(eliminaMailLocator));
        js().executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", eliminaMailButton);
    }

    public void insertEmail(String emailPEC) {
        WebElement emailField = getWebDriverWait(20)
                .withMessage("Input email field non trovato")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_email")));
        emailField.sendKeys(emailPEC);
    }

    public void insertTelephoneNumber(String phoneNumber) {
        WebElement phoneField = getWebDriverWait(30)
                .withMessage("Input numero di telefono non trovato")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_sms")));

        phoneField.sendKeys(phoneNumber);
    }

    public void clickAvvisamiViaSMS() {
        WebElement smsButton = getWebDriverWait(10)
                .withMessage("Il pulsante avvisami via SMS non è presente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_sms-button")));

        js().executeScript("arguments[0].click()", smsButton);
    }

    public String getPhoneErrorMessage() {
        WebElement errorMessage = getWebDriverWait(30)
                .withMessage("Il messaggio di errore per il numero di telefono non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_sms-helper-text")));
        return errorMessage.getText();
    }

    public boolean avvisamiViaSMSIsDisabled() {
        try {
            avvisamiViaSMSButton = getWebDriverWait(30)
                    .withMessage("Il pulsante 'Avvisami via SMS' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_sms-button")));
            return Boolean.parseBoolean(avvisamiViaSMSButton.getAttribute("disabled"));
        } catch (TimeoutException | NoSuchElementException e) {
            // Pulsante non presente o non visibile
            return false;
        }
    }

    public void cancellaTesto() {
        try {
            webTool.waitTime(3);
            WebElement pecInput = getWebDriverWait(10)
                    .withMessage("Il campo email PEC non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_email")));
            js().executeScript("arguments[0].click()", pecInput);
            String emailPec = pecInput.getAttribute("value");
            for (int i = 0; i < emailPec.length(); i++) {
                pecInput.sendKeys(Keys.BACK_SPACE);
            }
        } catch (TimeoutException e) {
            Assertions.fail("Non si riesce a cancellare il testo della email PEC: " + e.getMessage());
        }
    }


    public void verificaEmailModificata() {
        //Questo metodo è valido solo per PF, se si vuole utilizzare un metodo generico è stato fatto overload del metodo
        getWebDriverWait(30)
                .withMessage("La nuova mail non si visualizza correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'provaemailpf@test.it')]")));
    }

    public void verificaEmailModificata(String email) {
        getWebDriverWait(30)
                .withMessage("La nuova mail non si visualizza correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + email + "')]")));
    }

    public void selezionaTipoEmail() {
        webTool.waitTime(2);
        tipoIndirizzoField.click();

        // Attesa che l'opzione diventi cliccabile
        webTool.waitTime(2);
        WebElement opzionePEC = getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo email")
                .until(ExpectedConditions.elementToBeClickable(By.id("dropdown-EMAIL")));

        opzionePEC.click();
    }

    public void selezionaTipoPec() {
        webTool.waitTime(2);
        getWebDriverWait(10)
                .withMessage("Non è visibile il campo tipo indirizzo")
                .until(ExpectedConditions.elementToBeClickable(By.id("addressType")))
                .click();

        webTool.waitTime(2);

        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione PEC")
                .until(ExpectedConditions.elementToBeClickable(By.id("dropdown-PEC")))
                .click();
    }

    public void checkPostModifica() {
        getWebDriverWait(10)
                .withMessage("Non si visualizza il bottone Salva e non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("saveContact-default_email")));

        getWebDriverWait(10)
                .withMessage("Non si visualizza il bottone Annulla e non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Annulla')]")));

        WebElement emailField = getWebDriverWait(10)
                .withMessage("Non si visualizza il campo email e non è modificabile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_email")));

        getWebDriverWait(10).withMessage("Il campo email non è readonly")
                .until(ExpectedConditions.attributeToBe(emailField, "readonly", ""));

        getWebDriverWait(10).withMessage("Il campo email non ha valore")
                .until(ExpectedConditions.attributeToBeNotEmpty(emailField, "value"));
    }

    public void checkRiquadroPEC() {
        // Titolo della sezione recapito legale
        WebElement titolo = getWebDriverWait(10)
                .withMessage("Non si visualizza il titolo della sezione recapito legale o il contenuto è errato")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("legalContactsTitle")));
        getWebDriverWait(10).withMessage("Il testo del titolo è errato")
                .until(ExpectedConditions.attributeToBe(titolo, "innerText", "Recapito a valore legale"));
        // Sottotitolo
        WebElement sottotitolo = getWebDriverWait(10)
                .withMessage("Non si visualizza il sottotitolo della sezione recapito legale o il contenuto è errato")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='legalContactsSection']/p")));
        getWebDriverWait(10).withMessage("Il testo del sottotitolo è errato")
                .until(ExpectedConditions.attributeToBe(sottotitolo, "innerText", "È il recapito ufficiale che scegli per ricevere comunicazioni a valore legale dalla PA. Se attivi un recapito a valore legale riceverai le notifiche di SEND solo in digitale, senza più preoccuparti dei documenti cartacei."));
        // Campo PEC
        WebElement pecField = getWebDriverWait(10)
                .withMessage("Non si visualizza il campo PEC o non è modificabile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_pec")));
        getWebDriverWait(10).withMessage("Il placeholder del campo PEC è errato")
                .until(ExpectedConditions.attributeToBe(pecField, "placeholder", "La tua PEC"));
        // Bottone conferma
        WebElement pecButton = getWebDriverWait(10)
                .withMessage("Non si visualizza il bottone conferma o non è cliccabile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_pec-button")));
        getWebDriverWait(10).withMessage("Il bottone conferma dovrebbe essere disabilitato")
                .until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(pecButton)));
        // Banner informativo
        List<WebElement> infoBanner = getWebDriverWait(10)
                .withMessage("Non si visualizza il banner informativo")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[@data-testid='DigitalContactsCardDescription']")));
        getWebDriverWait(10).withMessage("Il contenuto del banner informativo è errato")
                .until(ExpectedConditions.attributeToBe(infoBanner.get(1), "innerText", "Quando un ente invia una comunicazione per te su SEND, ricevi l’avviso ufficiale sulla PEC che hai scelto."));
    }

    public void clickConfermaEmail() {
        webTool.waitTime(2);
        WebElement confermaEmail = getWebDriverWait(10)
                .withMessage("Non si visualizza il bottone salva e non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("saveContact-default_email")));
        confermaEmail.click();
    }

    public void siCliccaSulBottoneDelPopUpOkHoCapito() {
        try {
            webTool.waitTime(6);
            logger.info("Metodo: Ok Ho Capito");

            WebElement okHoCapitoButton = getWebDriverWait(30)
                    .withMessage("Non si visualizza il bottone 'Ok ho capito' nel pop-up")
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='understandButton']")));

            okHoCapitoButton.click();
            logger.info("Bottone 'Ok ho capito' cliccato nel pop-up.");

        } catch (TimeoutException e) {
            Assertions.fail("Errore: Il bottone 'Ok ho capito' non è visibile o cliccabile entro il tempo limite. Dettagli: " + e.getMessage());
        } catch (Exception e) {
            Assertions.fail("Errore imprevisto durante il clic sul bottone 'Ok ho capito'. Dettagli: " + e.getMessage());
        }

        webTool.waitTime(1);
    }

    public void siCliccaSulBottoneDelPopUpAnnulla() {
        webTool.waitTime(3);
        WebElement annullaPopUp = getWebDriverWait(30)
                .withMessage("Non si visualizza il bottone Annulla nel pop-up")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='discardButton']")));
        js().executeScript("arguments[0].click()", annullaPopUp);
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
        } catch (TimeoutException e) {
            Assertions.fail("Utente ha già un profilo IO");
        }
    }

    public void clickCollegaSENDSuIO() {
        WebElement buttonCollegaSENDSuIO = getWebDriverWait(10)
                .withMessage("Impossibile cliccare sul bottone Collega SEND Su IO ")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirmButton']")));
        buttonCollegaSENDSuIO.click();
    }

    public void clickAttivaDomicilioDigitale() {
        WebElement buttonConferma = getWebDriverWait(10)
                .withMessage("Impossibile cliccare sul bottone  Attiva Domicilio Digitale")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='activateButton']")));
        buttonConferma.click();

    }

    public void clickAttivaDomicilioDigitalePEC() {
        WebElement buttonConferma = getWebDriverWait(10)
                .withMessage("Impossibile cliccare sul bottone  Attiva Domicilio Digitale")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='next-button']")));
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
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated(By.id("dialog-title")),
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='dialog-content']"))
                ));
    }

    public void siChiudeImpossibileDisattivareDomicilioDigitale() {
        WebElement buttonChiudi = getWebDriverWait(10)
                .withMessage("Bottone 'Ok, ho capito' per pop-up 'Impossibile disattivare il Domicilio Digitale' non è visibile")
                .until(ExpectedConditions.elementToBeClickable(By.id("buttonClose")));
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

        WebElement iniziaButton = getWebDriverWait(20)
                .withMessage("Impossibile I tasto Inizia nel banner")
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

    public void clickContinua() {
        WebElement continuaButton = getWebDriverWait(10)
                .withMessage("Il pulsante 'Continua' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-testid='continueButton']")
                ));
        continuaButton.click();
    }

}
