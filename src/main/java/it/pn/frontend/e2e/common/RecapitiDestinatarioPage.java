package it.pn.frontend.e2e.common;

import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


public class RecapitiDestinatarioPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(RecapitiDestinatarioPage.class);


    @FindBy(id = "default_pec-button")
    WebElement attivaButton;

    @FindBy(id = "default_email-button")
    WebElement avvisamiViaEmailButton;

    @FindBy(id = "code-confirm-button")
    WebElement confermaButtonPopUp;

    @FindBy(id = "code-cancel-button")
    WebElement annullaButton;

    @FindBy(xpath = "//button[@data-testid='add email']")
    WebElement avvisamiMailButton;

    @FindBy(id = "default_sms-button")
    WebElement avvisamiSMSButton;

    @FindBy(id = "default_email")
    WebElement inserimentoMailField;

    @FindBy(id = "default_sms")
    WebElement inserimentoPhoneField;

    @FindBy(id = "cancelContact-default_pec")
    WebElement eliminaPECButton;

    @FindBy(xpath = "//button[@id='confirmDialog']")
    WebElement confermaButtonPoPUpPec;

    @FindBy(id = "modifyContact-default_email")
    WebElement modificaEmailButton;

    @FindBy(id = "sender")
    WebElement enteField;

    @FindBy(id = "addressType")
    WebElement tipoIndirizzoField;

    @FindBy(id = "s_value")
    WebElement indirizzoPecField;

    @FindBy(id = "addSpecialButton")
    WebElement associaButton;

    @FindBy(id = "s_value")
    WebElement emailField;

    @FindBy(xpath = "//form[@data-testid = 'default_pecContact']//button[contains(text(),'Elimina')]")
    List<WebElement> eliminaButtonList;

    @FindBy(id = "buttonAnnulla")
    WebElement buttonAnnullaEliminazioneInPopUp;

    @FindBy(id = "default_email-typography")
    WebElement emailAssociata;

    @FindBy(id = "courtesyContacts-phone")
    WebElement cellulareAssociato;

    @FindBy(id = "default_pec")
    WebElement pecField;


    @FindBy(id = "default_pec-typography")
    WebElement pecEmail;

    @FindBy(xpath = "//div[@data-testid='addDomicileBanner']")
    WebElement bannerDomicilioMancante;

    private WebTool webTool;


    public RecapitiDestinatarioPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void eliminaPecEsistente() {
        clickSuEliminaPec();
        if (waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi PEC")) {
            clickSuConfermaElimina();
        } else {
            clickSuChiudiPopUp();
            eliminaNuovaPec();
            clickSuEliminaPec();
            waitLoadPopUpElimina();
            clickSuConfermaElimina();
        }
    }

    public void clickSuChiudiPopUp() {
        logger.info("Log clickSuChiudiPopUp");

        By chiudiButtonLocator = By.xpath("//h2[@id='dialog-title']/following-sibling::div/button[contains(text(),'Annulla')]");

        WebElement chiudiButton = getWebDriverWait(10)
                .withMessage("Il bottone chiudi non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(chiudiButtonLocator));

        js().executeScript("arguments[0].click()", chiudiButton);
        logger.info("Popup chiuso con successo");
    }


    public void insertEmailPEC(String emailPEC) {
        WebElement insertPec = getWebDriverWait(30)
                .withMessage("Campo input PEC non trovato o non interagibile")
                .until(ExpectedConditions.elementToBeClickable(By.id("default_pec")));

        insertPec.clear();
        insertPec.sendKeys(emailPEC);

        logger.info("Email PEC '{}' inserita con successo.", emailPEC);
    }

    public void confermaButtonClick() {
        By attivaButtonLocator = By.id("default_pec-button");

        WebElement attivaButton = getWebDriverWait(10)
                .withMessage("Il bottone Attiva non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(attivaButtonLocator));

        attivaButton.click();
        logger.info("Bottone Attiva cliccato con successo");
    }

    public void clickConfermaButtonEliminaPopUp() {
        By confermaButtonLocator = By.xpath("//h2[@id='dialog-title']/following-sibling::div/button[contains(text(),'Conferma')]");

        WebElement confermaEliminaButton = getWebDriverWait(10)
                .withMessage("Il bottone conferma non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(confermaButtonLocator));

        confermaEliminaButton.click();
        logger.info("clickConfermaButtonEliminaPopUp eseguito con successo");
    }

    public void waitLoadPopUp() {
        try {
            getWebDriverWait(30)
                    .withMessage("Non viene visualizzato correttamente il titolo")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("dialog-title")));

            getWebDriverWait(30)
                    .withMessage("La descrizione non viene visualizzata")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("dialog-description")));

            getWebDriverWait(30)
                    .withMessage("La scritta 'Inserisci codice' non viene visualizzata correttamente")
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@data-testid='dialog-content']//p[contains(@class,'MuiTypography-root MuiTypography-body1') and not(@role='button')]")
                    ));

            List<WebElement> inputBoxes = getWebDriverWait(30)
                    .withMessage("Le input boxes non vengono visualizzate")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath("//div[@data-testid='dialog-content']//div[@aria-hidden='true']//div")
                    ));

            if (inputBoxes.size() != 5) {
                Assertions.fail("Il numero di input box non è corretto. Attesi: 5, trovati: " + inputBoxes.size());
            }

            getWebDriverWait(30)
                    .withMessage("La descrizione secondaria non viene visualizzata")
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@data-testid='dialog-content']//div[contains(@class,'MuiTypography-root MuiTypography-body2')]")
                    ));

            confermaButtonPopUp = getWebDriverWait(30)
                    .withMessage("Pulsante 'Conferma' non trovato")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("code-confirm-button")));
            annullaButton = getWebDriverWait(30)
                    .withMessage("Pulsante 'Annulla' non trovato")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("code-cancel-button")));
            if (!confermaButtonPopUp.isEnabled() || !annullaButton.isEnabled()) {
                Assertions.fail("I pulsanti all'interno del pop-up non sono abilitati come previsto");
            }
            logger.info("Il pop-up di conferma viene visualizzato correttamente");
        } catch (TimeoutException | StaleElementReferenceException e) {
            Assertions.fail("Il pop-up di conferma NON viene visualizzato correttamente. Errore: " + e.getMessage());
        }
    }

    public void sendOTP(String otp) {
        String[] otpDigits = otp.split("");
        try {
            // Attendi che tutti i campi di input OTP siano visibili
            getWebDriverWait(30)
                    .withMessage("I campi di input OTP non sono visibili")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-testid='dialog-content']//div[@aria-hidden='true']//div")));

            // Inserisci ogni cifra dell'OTP nel rispettivo campo di input
            for (int i = 0; i < otpDigits.length; i++) {
                WebElement codiceDelegaInput = driver.findElement(By.xpath("//div[@data-testid='dialog-content']//input"));
                codiceDelegaInput.sendKeys(otpDigits[i]);
            }
            logger.info("Il codice OTP è stato inserito correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il codice OTP NON è stato inserito correttamente con errore: " + e.getMessage());
        } catch (Exception e) {
            Assertions.fail("Errore durante l'inserimento del codice OTP: " + e.getMessage());
        }
    }

    public void clearOTP() {
        try {
            // Attendi che tutti i campi di input OTP siano visibili
            List<WebElement> otpInputs = getWebDriverWait(30)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-testid='dialog-content']//div[@aria-hidden='true']//div")));

            // Cancella ogni campo di input OTP
            for (int i = otpInputs.size() - 1; i >= 0; i--) {
                WebElement codiceDelegaInput = driver.findElement(By.xpath("//div[@data-testid='dialog-content']//input"));
                codiceDelegaInput.sendKeys(Keys.BACK_SPACE);
            }

            logger.info("Il codice OTP è stato cancellato correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("I campi di input OTP non sono stati trovati: " + e.getMessage());
        } catch (Exception e) {
            Assertions.fail("Errore durante la cancellazione del codice OTP: " + e.getMessage());

        }
    }

    public void confermaButtonClickPopUp() {
        webTool.waitTime(3);

        By confirmButtonLocator = By.id("code-confirm-button");

        // Attendo che il bottone principale sia cliccabile e clicco
        WebElement confermaButton = getWebDriverWait(20)
                .until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        confermaButton.click();
        webTool.waitTime(5);

        // Controllo se appare un secondo bottone conferma e lo clicco
        List<WebElement> confermaButtonPostInserimentoList = driver.findElements(confirmButtonLocator);
        if (!confermaButtonPostInserimentoList.isEmpty()) {
            confermaButtonPostInserimentoList.get(0).click();
            logger.info("Secondo click sul bottone conferma post-inserimento eseguito");
        }
    }

    public boolean waitMessaggioErrore() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.id("error-alert")));
            logger.info("Il messaggio di errore viene visualizzato correttamente");
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void annullaButtonClick() {
        try {
            WebElement annulla = getWebDriverWait(20)
                    .withMessage("Il bottone annulla non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.id("code-cancel-button")));
            annulla.click();
            logger.info("Il bottone 'Annulla' è stato cliccato.");
        } catch (TimeoutException e) {
            Assertions.fail("Il bottone annulla non è cliccabile con errore: " + e.getMessage());
        } catch (Exception e) {
            Assertions.fail("Si è verificato un errore imprevisto durante il click del bottone annulla: " + e.getMessage());
        }
    }

    public void clickAvvisami() {
        By avvisamiLocator = By.xpath("//button[@data-testid='add email']");

        WebElement avvisamiMailButton = getWebDriverWait(30)
                .withMessage("Il bottone 'Avvisami' della mail non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(avvisamiLocator));

        avvisamiMailButton.click();
        logger.info("Click sul bottone 'Avvisami' eseguito");
    }

    public void clickAvvisamiSMS() {
        By avvisamiSmsLocator = By.id("default_sms-button");

        WebElement avvisamiSMSButton = getWebDriverWait(30)
                .withMessage("Il bottone 'Avvisami' dell'SMS non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(avvisamiSmsLocator));

        avvisamiSMSButton.click();
        logger.info("Click sul bottone 'Avvisami SMS' eseguito");
    }

    public void insertEmail(String email) {
        By emailLocator = By.id("default_email");

        WebElement inserimentoMailField = getWebDriverWait(10)
                .withMessage("L'input email non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(emailLocator));

        // scrolla solo se non visibile
        if (!inserimentoMailField.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true);", inserimentoMailField);
        }

        inserimentoMailField.clear();
        inserimentoMailField.sendKeys(email);
        logger.info("Email inserita: {}", email);
    }

    public void insertPhone(String cellulare) {
        By phoneLocator = By.id("default_sms");

        WebElement inserimentoPhoneField = getWebDriverWait(30)
                .withMessage("L'input numero telefono non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(phoneLocator));
        // Scroll solo se il campo non è visibile
        if (!inserimentoPhoneField.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true);", inserimentoPhoneField);
        }
        inserimentoPhoneField.clear();
        inserimentoPhoneField.sendKeys(cellulare);
    }

    public boolean verificaPecAssociata() {
        By pecLocator = By.id("default_pec-typography");
        try {
            getWebDriverWait(40)
                    .withMessage("PEC associata non presente")
                    .until(ExpectedConditions.visibilityOfElementLocated(pecLocator));
            return true;
        } catch (TimeoutException e) {
            logger.error("PEC associata non trovata: {}", e.getMessage());
            return false;
        }
    }

    public boolean siVisualizzaPopUpConferma() {
        By confermaButtonLocator = By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]");
        try {
            getWebDriverWait(20)
                    .until(ExpectedConditions.visibilityOfElementLocated(confermaButtonLocator));
            return true;
        } catch (TimeoutException e) {
            logger.error("Pop up conferma non trovato: {}", e.getMessage());
            return false;
        }
    }

    public void clickConfermaButton() {
        By confermaButtonLocator = By.xpath("//button[@id='confirmDialog']");
        getWebDriverWait(10)
                .withMessage("Il bottone conferma del pop up non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(confermaButtonLocator));
        driver.findElement(confermaButtonLocator).click();
    }

    public void visualizzaValidazione() {
        By validazionePecLocator = By.xpath("//p[contains(text(), 'Validazione PEC in corso')]");
        try {
            getWebDriverWait(10)
                    .until(ExpectedConditions.visibilityOfElementLocated(validazionePecLocator));
        } catch (TimeoutException e) {
            Assertions.fail("PEC non associata con errore: " + e.getMessage());
        }
    }

    public boolean verificaMailAssociata() {
        By mailLocator = By.id("default_email-typography");
        try {
            getWebDriverWait(30)
                    .withMessage("L'email di cortesia non è presente")
                    .until(ExpectedConditions.visibilityOfElementLocated(mailLocator));
            return true;
        } catch (TimeoutException e) {
            logger.info("Nessuna email di cortesia impostata");
            return false;
        }
    }


    public boolean verificaNumeroDiCellulareAssociato() {
        try {
            getWebDriverWait(15).withMessage("Il numero di cellulare associato non è presente").until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("courtesyContacts-phone")));
            return true;
        } catch (TimeoutException e) {
            logger.info("Nessun numero di cellulare di cortesia impostato");
            return false;
        }
    }

    public boolean verificaMailField() {
        By mailFieldLocator = By.id("default_email");
        try {
            getWebDriverWait(20)
                    .withMessage("Il campo email non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(mailFieldLocator));
            return true;
        } catch (TimeoutException e) {
            logger.info("Il campo email non è visibile");
            return false;
        }
    }


    public boolean siVisualizzaPecInserita() {
        try {
            getWebDriverWait(5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'PEC associata')]")));
            return true;
        } catch (TimeoutException e) {
            logger.error("Pec inserita non presente con errore: {}", e.getMessage());
            return false;
        }
    }

    public void clickSuModifica() {
        By modificaEmailLocator = By.id("modifyContact-default_email");
        try {
            WebElement modificaEmailButton = getWebDriverWait(10)
                    .withMessage("Il bottone modifica email non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(modificaEmailLocator));

            if (!modificaEmailButton.isDisplayed()) {
                js().executeScript("arguments[0].scrollIntoView(true);", modificaEmailButton);
            }

            modificaEmailButton.click();
            logger.info("Click sul bottone Modifica email effettuato");
        } catch (TimeoutException e) {
            Assertions.fail("Il bottone Modifica email non è stato trovato o non è cliccabile: " + e.getMessage());
        }
    }


    public void clickSuModificaPEC() {
        try {
            WebElement modificaButton = getWebDriverWait(20)
                    .withMessage("Non si riesce a cliccare o vedere il bottone modifica PEC")
                    .until(ExpectedConditions.elementToBeClickable(By.id("modifyContact-default_pec")));

            modificaButton.click();
            logger.info("Cliccato sul pulsante 'Modifica PEC'.");
        } catch (TimeoutException e) {
            Assertions.fail("Non si riesce a cliccare o vedere il bottone modifica PEC. Errore: " + e.getMessage());
        }

    }

    public void verificaAssenzaModificaPEC() {
        try {
            boolean assente = getWebDriverWait(10)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("modifyContact-default_pec")));

            Assertions.assertTrue(assente, "Il bottone 'Modifica PEC' è presente, ma non dovrebbe esserlo.");
        } catch (TimeoutException e) {
            Assertions.fail("Il bottone 'Modifica PEC' è visibile e il test deve fallire.");
        }
    }

    public void cancellaTesto() {
        try {
            webTool.waitTime(5);
            WebElement inputPecField = getWebDriverWait(30)
                    .withMessage("Impossibile trovare input per inserire la nuova PEC")
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("default_pec")));
            js().executeScript("arguments[0].click()", inputPecField);
            clearWebElementField(inputPecField);
            if (!inputPecField.getAttribute("value").isEmpty()) {
                Assertions.fail("Il campo PEC non è stato cancellato correttamente.");
            }

            logger.info("Testo email PEC cancellato con successo");
        } catch (TimeoutException e) {
            Assertions.fail("Non si riesce a cancellare il testo della email PEC: " + e.getMessage());
        }
    }

    public void clickSuSalva() {
        By salvaButtonLocator = By.xpath("//button[contains(text(),'Salva')]");
        try {
            WebElement salvaButton = getWebDriverWait(30)
                    .withMessage("Il bottone Salva non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(salvaButtonLocator));
            salvaButton.click();
            logger.info("Click sul bottone Salva effettuato");
        } catch (TimeoutException e) {
            Assertions.fail("Il bottone Salva non è stato trovato o non è cliccabile: " + e.getMessage());
        }
    }

    public boolean siControllaPECModificata(String pecInserita) {
        By pecLocator = By.id("default_pec-typography");
        try {
            WebElement pecBy = getWebDriverWait(20)
                    .withMessage("Non trovata nessuna email PEC inserita")
                    .until(ExpectedConditions.visibilityOfElementLocated(pecLocator));
            return pecBy.getText().equals(pecInserita);
        } catch (TimeoutException e) {
            logger.error("PEC non trovata o non visibile: {}", e.getMessage());
            return false;
        }
    }

    public void clickSuEliminaPec() {
        By eliminaPecLocator = By.id("cancelContact-default_pec");
        WebElement eliminaPECButton = getWebDriverWait(20)
                .withMessage("Il bottone elimina della PEC associata non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(eliminaPecLocator));
        logger.info("click sul pulsante elimina PEC");
        eliminaPECButton.click();
    }

    public String waitLoadPopUpElimina() {
        try {
            WebElement titlePopUp = getWebDriverWait(10)
                    .withMessage("Non è stato caricato il titolo del modal")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("dialog-title")));

            getWebDriverWait(10)
                    .withMessage("Non è stato caricato il sottotitolo del modal")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("dialog-description")));

            getWebDriverWait(10)
                    .withMessage("Non è stato caricato il bottone annulla del modal")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonAnnulla")));

            getWebDriverWait(10)
                    .withMessage("Non è stato caricato il bottone conferma del modal")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-labelledby='dialog-title']//button[contains(text(),'Conferma')]")));

            return titlePopUp.getText();
        } catch (TimeoutException e) {
            logger.info("Non è stato caricato un elemento del pop up con errore: {}", e.getMessage());
            return null;
        }
    }

    public void clickSuConfermaElimina() {
        logger.info("PRIMA DI clickSuConfermaElimina");

        WebElement confermaRimuoviPECBy = getWebDriverWait(20)
                .withMessage("Non è stato possibile cliccare sul bottone conferma")
                .until(ExpectedConditions.elementToBeClickable(By.id("buttonConferma")));

        confermaRimuoviPECBy.click();

        logger.info("DOPO DI clickSuConfermaElimina");
    }

    public boolean siControllaEliminazionePEC() {
        webTool.waitTime(5); // eventuale pausa opzionale
        try {
            WebElement pecField = getWebDriverWait(10)
                    .withMessage("Il campo PEC non è presente")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_pec")));
            return pecField.isDisplayed();
        } catch (TimeoutException e) {
            return false; // il campo non è più visibile
        }
    }

    public boolean siControllaPresenzaPEC() {
        webTool.waitTime(5);
        try {
            WebElement pecEmail = getWebDriverWait(10)
                    .withMessage("L'email PEC non è presente")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_pec-typography")));
            return pecEmail.isDisplayed();
        } catch (TimeoutException e) {
            return false; // PEC non visibile
        }
    }

    public void insertEnte(String comune) {
        webTool.waitTime(5);
        WebElement enteField = getWebDriverWait(10)
                .withMessage("Il campo ente non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("sender")));

        enteField.clear();
        enteField.sendKeys(comune);
        // Aspetta che la lista suggerita sia caricata
        webTool.waitTime(5);
        // Seleziona il primo elemento della lista
        enteField.sendKeys(Keys.ARROW_DOWN);
        enteField.sendKeys(Keys.ENTER);
        // Verifica che il valore selezionato corrisponda al comune desiderato
        getWebDriverWait(10)
                .withMessage("Il comune selezionato non corrisponde a: " + comune)
                .until(ExpectedConditions.attributeContains(enteField, "value", comune));
    }

    public void clickSuIndirizzoPEC() {
        webTool.waitTime(5);
        // Aspetta che il campo sia cliccabile e cliccalo
        WebElement tipoIndirizzoField = getWebDriverWait(10)
                .withMessage("Il campo tipo indirizzo non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("addressType")));
        tipoIndirizzoField.click();
        // Aspetta che l'opzione PEC sia visibile e cliccabile
        WebElement opzionePEC = getWebDriverWait(30)
                .withMessage("L'opzione 'Indirizzo PEC' non è visibile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-value='PEC']")));
        opzionePEC.click();
    }

    public void insertPECAggiuntiva(String emailPec) {
        webTool.waitTime(5);
        WebElement indirizzoPecField = getWebDriverWait(10)
                .withMessage("Il campo PEC aggiuntiva non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("s_value")));

        js().executeScript("arguments[0].scrollIntoView(true);", indirizzoPecField); // assicura che sia in viewport
        indirizzoPecField.clear();
        indirizzoPecField.sendKeys(emailPec);
    }

    public void clickSuAssocia() {
        WebElement associaButton = getWebDriverWait(10)
                .withMessage("Il bottone associa non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("addSpecialButton")));

        js().executeScript("arguments[0].click()", associaButton);
    }

    public void insertEmailAggiuntiva(String mail) {
        WebElement emailField = getWebDriverWait(10)
                .withMessage("L'input per l'email aggiuntiva non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("s_value")));
        try {
            if (!emailField.isDisplayed()) {
                js().executeScript("arguments[0].scrollIntoView(true);", emailField);
            }
            emailField.clear();
            emailField.sendKeys(mail);
        } catch (ElementNotInteractableException e) {
            // Se l'elemento non è interagibile, proviamo comunque a inviare il testo
            emailField.sendKeys(mail);
        }
    }

    public void siControllaEmailAggiunta() {
        getWebDriverWait(10)
                .withMessage("La mail non è stata aggiunta correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//form[@data-testid='a95dace4-4a47-4149-a814-0e669113ce40_emailContact']")
                ));
    }

    public boolean controlloEmailAssociata(String email) {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@data-testid='courtesyContacts']//div//p[contains(text(),'" + email + "')]")
            ));
            return true;
        } catch (TimeoutException e) {
            logger.error("Email associata non presente con errore: {}", e.getMessage());
            return false;
        }
    }

    public boolean verificaNuovaEmailEPEC(String nuovaEmail) {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@data-testid='DigitalContactsCardBody']//p[contains(text(),'" + nuovaEmail + "')]")
            ));
            return true;
        } catch (TimeoutException e) {
            logger.error("Email non presente con errore: {}", e.getMessage());
            return false;
        }
    }

    public void eliminaNuovaEmail() {
        webTool.waitTime(5);

        List<WebElement> eliminaButtonList = driver.findElements(
                By.xpath("//form[@data-testid='default_pecContact']//button[contains(text(),'Elimina')]")
        );

        if (!eliminaButtonList.isEmpty()) {
            WebElement ultimoEliminaButton = eliminaButtonList.get(eliminaButtonList.size() - 1);
            getWebDriverWait(10).withMessage("Non è stato possibile cliccare sul bottone elimina email")
                    .until(ExpectedConditions.elementToBeClickable(ultimoEliminaButton));

            js().executeScript("arguments[0].click()", ultimoEliminaButton);

            By confermaPopUp = By.xpath("//div[@aria-labelledby='dialog-title']//div/button[contains(text(),'Conferma')]");
            getWebDriverWait(10).withMessage("Il bottone del pop-up non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(confermaPopUp));

            driver.findElement(confermaPopUp).click();
        }
    }

    public void eliminaNuovaPec() {
        By eliminaButtonLocator = By.xpath("//form[@data-testid='default_pecContact']//button[contains(text(),'Elimina')]");
        WebElement eliminaButton = getWebDriverWait(30)
                .withMessage("Non è stato possibile cliccare sul bottone elimina PEC")
                .until(ExpectedConditions.elementToBeClickable(eliminaButtonLocator));

        js().executeScript("arguments[0].click()", eliminaButton);

        By confermaPopUpLocator = By.xpath("//div[@aria-labelledby='dialog-title']//div/button[contains(text(),'Conferma')]");
        WebElement confermaPopUp = getWebDriverWait(30)
                .withMessage("Il bottone del pop-up non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(confermaPopUpLocator));

        confermaPopUp.click();
    }


    public void confermaButtonEliminaClick() {

    }

    public String getEmailErrorMessage() {
        By errorMessageLocator = By.id("default_email-helper-text");
        WebElement errorMessage = getWebDriverWait(10)
                .withMessage("Il messaggio di errore email non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorMessage.getText();
    }

    public String getPecErrorMessage() {
        By errorMessageLocator = By.id("default_pec-helper-text");
        WebElement errorMessage = getWebDriverWait(30)
                .withMessage("Il messaggio di errore PEC non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorMessage.getText();
    }

    public String getPecInvalidMessage() {
        By errorMessageLocator = By.id("pec-helper-text");
        WebElement errorMessage = getWebDriverWait(30)
                .withMessage("Il messaggio di errore PEC non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorMessage.getText();
    }

    public boolean verificaBottoneConfermaDisabilitato() {
        By attivaButtonLocator = By.id("default_pec-button");
        try {
            WebElement attivaButton = getWebDriverWait(30)
                    .withMessage("Il bottone PEC non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(attivaButtonLocator));
            return Boolean.parseBoolean(attivaButton.getAttribute("disabled"));
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Bottone non disabilitato: {}", e.getMessage());
            return false;
        }
    }

    public void clickHoCapitoCheckBoxPopup() {
        By hoCapitoCheckBoxLocator = By.xpath("//span[contains(text(),'Ho capito')]/preceding-sibling::span/input");
        WebElement hoCapitoCheckBox = getWebDriverWait(10)
                .withMessage("Checkbox 'Ho capito' non visibile")
                .until(ExpectedConditions.elementToBeClickable(hoCapitoCheckBoxLocator));
        logger.info("Click su checkbox 'Ho capito'");
        hoCapitoCheckBox.click();
    }

    public void confermaEmailPopup() {
        By confirmButtonLocator = By.id("code-confirm-button");
        WebElement popupConfirmButton = getWebDriverWait(10)
                .withMessage("Il bottone di conferma del popup non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        popupConfirmButton.click();
    }

    public void clickAvvisamiViaEmail() {
        By avvisamiEmailLocator = By.id("default_email-button");
        WebElement avvisamiViaEmailButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Avvisami via email' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(avvisamiEmailLocator));
        avvisamiViaEmailButton.click();
    }

    public boolean avvisamiViaEmailIsDisabled() {
        By avvisamiEmailLocator = By.id("default_email-button");
        try {
            WebElement avvisamiViaEmailButton = getWebDriverWait(30)
                    .withMessage("Il bottone 'Avvisami via email' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(avvisamiEmailLocator));
            return Boolean.parseBoolean(avvisamiViaEmailButton.getAttribute("disabled"));
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void visualizzazioneSezioneAltriRecapiti() {
        By titleLocator = By.id("courtesyContactsTitle");
        getWebDriverWait(5)
                .withMessage("Non si visualizza correttamente il titolo della sezione Altri Recapiti")
                .until(ExpectedConditions.visibilityOfElementLocated(titleLocator));
    }

    public void visualizzazioneCampiSezioneAltriRecapiti() {
        vaiInFondoAllaPagina();
        // Attesa dei titoli e della tabella
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo della sezione Altri Recapiti")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(@id, 'specialContact')]")));
        getWebDriverWait(10).withMessage("Non si visualizza il titolo della tabella")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Già associati')]")));
        getWebDriverWait(10).withMessage("Non si visualizza la tabella dei recapiti già associati")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div[1]/div/main/div/div[2]/div[2]/div/div/table")));
        // Recupero tabella e righe
        WebElement tableGiaAssociati = driver.findElement(By.xpath("//table[@aria-label='Già associati']"));
        List<WebElement> tableRows = tableGiaAssociati.findElements(By.xpath(".//tbody/tr"));

        for (WebElement row : tableRows) {
            List<WebElement> columns = row.findElements(By.xpath(".//td"));

            if (columns.get(0).getText().contains("Comune di Verona")) {
                logger.info("Si visualizza l'ente inserito correttamente");
            }

            if (columns.get(1).getText().contains("pec@pec.pagopa.it")) {
                WebElement modifyButton = columns.get(1).findElement(By.xpath(".//button[contains(@id, 'modifyContact')]"));
                WebElement cancelButton = columns.get(1).findElement(By.xpath(".//button[contains(@id, 'cancelContact')]"));

                getWebDriverWait(10).withMessage("Bottone modifica non visualizzato o non cliccabile")
                        .until(ExpectedConditions.elementToBeClickable(modifyButton));

                getWebDriverWait(10).withMessage("Bottone elimina non visualizzato o non cliccabile")
                        .until(ExpectedConditions.elementToBeClickable(cancelButton));

                logger.info("Si visualizza l'indirizzo PEC inserito correttamente");
            }

            if (columns.get(2).getText().contains("-") && columns.get(3).getText().contains("-")) {
                logger.info("Si visualizzano il cellulare e la mail inseriti correttamente");
            }
            break;
        }
    }


    public void checkButtonAnnullaEliminazioneInPopUp() {
        try {
            getWebDriverWait(10)
                    .withMessage("Pulsante annulla eliminazione non trovato")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonAnnulla")));

            logger.info("Pulsante annulla eliminazione visibile");
        } catch (TimeoutException e) {
            Assertions.fail("Caricamento pop-up con errore: " + e.getMessage());
        } catch (StaleElementReferenceException e) {

            getWebDriverWait(10)
                    .withMessage("Pulsante annulla eliminazione non trovato eccezione StaleElementReferenceException")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonAnnulla")));
        }
    }

    public void clickButtonAnnullaEliminazioneInPopUp() {
        By annullaButtonBy = By.id("buttonAnnulla");

        WebElement buttonAnnulla = getWebDriverWait(10)
                .withMessage("Non è stato possibile cliccare sul bottone annulla")
                .until(ExpectedConditions.elementToBeClickable(annullaButtonBy));

        buttonAnnulla.click();
    }

    public void checkEmailPrecedentementeSalvata(String email) {
        webTool.waitTime(10);
        By emailTypoBy = By.id("default_email-typography");

        WebElement emailAssociata = getWebDriverWait(10)
                .withMessage("L'email precedentemente salvata non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(emailTypoBy));

        if (emailAssociata.getText().equalsIgnoreCase(email)) {
            logger.info("La mail associata risulta uguale alla precedente");
        } else {
            logger.error("La mail associata è diversa dalla precedentemente salvata");
            Assertions.fail("La mail associata è diversa dalla precedentemente salvata");
        }
    }


    public void verificaPecModificabile() {
        try {

            getWebDriverWait(15).until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(By.id("default_pec")),
                    ExpectedConditions.attributeToBe(By.id("default_pec"), "readonly", "")));
            logger.info("pec modificabile");
        } catch (TimeoutException e) {
            Assertions.fail("pec non modificabile con errore:" + e.getMessage());
        }
    }

    public void verificaPecNonModificabile() {
        try {

            getWebDriverWait(15).until(ExpectedConditions.and(
                    ExpectedConditions.invisibilityOfElementLocated(By.id("default_pec")),
                    ExpectedConditions.visibilityOfElementLocated(By.id("default_pec-typography"))));
            logger.info("pec non modificabile");
        } catch (TimeoutException e) {
            Assertions.fail("pec modificabile con errore:" + e.getMessage());
        }
    }

    public void clickSuAnnulla() {
        By annullaButtonBy = By.xpath("//button[contains(text(),'Annulla')]");

        WebElement annullaButton = getWebDriverWait(10)
                .withMessage("Non si riesce a cliccare o vedere il bottone Annulla")
                .until(ExpectedConditions.elementToBeClickable(annullaButtonBy));

        annullaButton.click();
    }

    public void clickConfermaRecapitoGiaPresente() {
        By confermaButtonBy = By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]");

        WebElement confermaButton = getWebDriverWait(10)
                .withMessage("Bottone conferma non visualizzato o non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(confermaButtonBy));

        confermaButton.click();
    }

    public void checkNumeroDiCellulareCorretto(String cellulare) {
        By telefonoBy = By.id("courtesyContacts-phone");

        getWebDriverWait(10).withMessage("Numero di cellulare appena inserito non visualizzato o errato")
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated(telefonoBy),
                        ExpectedConditions.attributeToBe(telefonoBy, "innerText", "+39" + cellulare)
                ));
    }

    public void clickSuBottoneCellulareDiCortesia(String actionButton) {
        By bottoneBy = By.xpath("//form[contains(., 'Numero di cellulare')]//button[contains(text(), '" + actionButton + "')]");

        WebElement bottoneAction = getWebDriverWait(10)
                .withMessage("Il bottone '" + actionButton + "' non è cliccabile su Cellulare Di Cortesia")
                .until(ExpectedConditions.elementToBeClickable(bottoneBy));

        bottoneAction.click();
    }

    public void clickSuBottoneEmailDiCortesia(String actionButton) {
        By bottoneBy = By.xpath("//button[contains(text(), '" + actionButton + "')]");

        WebElement bottoneAction = getWebDriverWait(10)
                .withMessage("Il bottone '" + actionButton + "' non è cliccabile su Email Di Cortesia")
                .until(ExpectedConditions.elementToBeClickable(bottoneBy));

        bottoneAction.click();
    }

    public void clickSuBottoneDisattivaIO() {
        By bottoneBy = By.xpath("//div[@id='ioContactSection']//button[contains(text(), 'Disattiva')]");

        WebElement bottoneAction = getWebDriverWait(10)
                .withMessage("Il bottone 'Disattiva' in IO non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(bottoneBy));

        bottoneAction.click();
    }

    public void checkNumeroDiCellulareNonPresente() {
        By smsInputBy = By.id("default_sms");
        try {
            getWebDriverWait(10).withMessage("Input numero di cellulare non visualizzato o non vuoto")
                    .until(ExpectedConditions.and(
                            ExpectedConditions.visibilityOfElementLocated(smsInputBy),
                            ExpectedConditions.attributeToBe(smsInputBy, "value", "")
                    ));
        } catch (TimeoutException e) {
            Assertions.fail("Input numero di cellulare non visualizzato o non vuoto con errore: " + e.getMessage());
        }
    }

    public void checkCampoEmailModificabile() {
        By emailFieldBy = By.id("default_email");
        try {
            getWebDriverWait(10)
                    .withMessage("Campo email non modificabile")
                    .until(ExpectedConditions.visibilityOfElementLocated(emailFieldBy));
        } catch (TimeoutException e) {
            Assertions.fail("Campo email non modificabile con errore: " + e.getMessage());
        }
    }

    public void checkMessaggioErroreTreTentativiOTPSbagliato() {
        By errorAlertBy = By.id("error-alert");
        By errorTitleBy = By.id("codeModalErrorTitle");

        try {
            // Aspetta che l'alert di errore sia visibile
            getWebDriverWait(10)
                    .withMessage("Il messaggio di errore non viene visualizzato")
                    .until(ExpectedConditions.visibilityOfElementLocated(errorAlertBy));

            // Legge il testo e verifica il messaggio
            String testo = driver.findElement(errorAlertBy).getText().replace("\n", " ");
            String messaggioAtteso = "Hai fatto troppi tentativi Hai inserito troppe volte un codice sbagliato. Per riprovare premi “Annulla”, assicurati che il contatto sia corretto e inserisci il nuovo codice.";

            if (testo.contains(messaggioAtteso)) {
                logger.info("Si visualizza correttamente il messaggio di errore");
            } else {
                logger.error("Non si visualizza il messaggio di errore");
                Assertions.fail("Non si visualizza il messaggio di errore");
            }

            // Controlla anche il titolo della modale
            getWebDriverWait(10)
                    .withMessage("Titolo di errore della modale non visualizzato")
                    .until(ExpectedConditions.visibilityOfElementLocated(errorTitleBy));

        } catch (TimeoutException e) {
            Assertions.fail("Il messaggio di errore non viene visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public void clickDropdownAltriRecapiti(String dropdown) {
        By dropdownBy;

        if (dropdown.equalsIgnoreCase("ente")) {
            dropdownBy = By.id("sender");
        } else {
            dropdownBy = By.id("addressType");
        }
        getWebDriverWait(10)
                .withMessage("Non è stato possibile cliccare sul dropdown")
                .until(driver -> {
                    WebElement element = driver.findElement(dropdownBy);
                    if (element.isDisplayed() && element.isEnabled()) {
                        element.click();
                        return true;
                    }
                    return false;
                });
    }


    public void visualizzaListaEnti(List<String> enti) {
        for (String ente : enti) {
            By enteBy = By.xpath("//li//p[contains(text(),'" + ente + "')]");

            getWebDriverWait(10)
                    .withMessage("Ente: " + ente + " non visibile")
                    .until(driver -> {
                        WebElement element = driver.findElement(enteBy);
                        return element.isDisplayed();
                    });
        }
    }

    public void visualizzazioneSezioneAltriRecapitiPG(String textboxId) {
        String id = "";
        if (textboxId.equalsIgnoreCase("pec")) {
            id = "s_pec-label";
        } else if (textboxId.equalsIgnoreCase("email")) {
            id = "s_mail";
        }

        By titoloSezione = By.id("specialContactTitle");
        By sottotitoloSezione = By.xpath("//p[contains(text(),'Se si desidera che')]");
        By enteField = By.id("sender");
        By tipoRecapitoField = By.id("addressType");
        By textboxField = By.id(id);
        By associaButton = By.id("addSpecialButton");

        getWebDriverWait(5).withMessage("Non si visualizza correttamente il titolo della sezione altri recapiti")
                .until(ExpectedConditions.visibilityOfElementLocated(titoloSezione));

        getWebDriverWait(5).withMessage("Non si visualizza correttamente il sottotitolo della sezione altri recapiti")
                .until(ExpectedConditions.visibilityOfElementLocated(sottotitoloSezione));

        getWebDriverWait(5).withMessage("Non si visualizza correttamente ente della sezione altri recapiti")
                .until(ExpectedConditions.visibilityOfElementLocated(enteField));

        getWebDriverWait(5).withMessage("Non si visualizza correttamente tipo di recapito della sezione altri recapiti")
                .until(ExpectedConditions.visibilityOfElementLocated(tipoRecapitoField));

        getWebDriverWait(5).withMessage("Non si visualizza correttamente textbox della sezione altri recapiti")
                .until(ExpectedConditions.visibilityOfElementLocated(textboxField));

        getWebDriverWait(5).withMessage("Non si visualizza correttamente il bottone associa della sezione altri recapiti")
                .until(ExpectedConditions.visibilityOfElementLocated(associaButton));
    }

    public void selezionaTipoEmail() {
        webTool.waitTime(2);

        By tipoIndirizzo = By.id("addressType");
        By opzioneEmail = By.id("EMAIL");

        // Clicca sul dropdown
        getWebDriverWait(10)
                .withMessage("Dropdown tipo indirizzo non visibile")
                .until(ExpectedConditions.elementToBeClickable(tipoIndirizzo))
                .click();

        // Seleziona l'opzione EMAIL
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo email")
                .until(ExpectedConditions.elementToBeClickable(opzioneEmail))
                .click();
    }

    public void selezionaTipoPec() {
        webTool.waitTime(2);

        By tipoIndirizzo = By.id("addressType");
        By opzionePec = By.id("PEC");

        // Clicca sul dropdown
        getWebDriverWait(10)
                .withMessage("Dropdown tipo indirizzo non visibile")
                .until(ExpectedConditions.elementToBeClickable(tipoIndirizzo))
                .click();

        // Seleziona l'opzione PEC
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo PEC")
                .until(ExpectedConditions.elementToBeClickable(opzionePec))
                .click();
    }

    public void selezionaTipoCelulare() {
        webTool.waitTime(2);

        By tipoIndirizzo = By.id("addressType");
        By opzioneCelulare = By.id("Celulare");

        // Clicca sul dropdown
        getWebDriverWait(10)
                .withMessage("Dropdown tipo indirizzo non visibile")
                .until(ExpectedConditions.elementToBeClickable(tipoIndirizzo))
                .click();

        // Seleziona l'opzione Celulare
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione Celulare")
                .until(ExpectedConditions.elementToBeClickable(opzioneCelulare))
                .click();
    }

    public void checkMessaggioDiErrore(String tipo) {
        By inputField;
        By helperText;

        if (tipo.equalsIgnoreCase("pec")) {
            inputField = By.id("s_pec"); // ID reale dell'input PEC
            helperText = By.id("s_pec-helper-text");
        } else if (tipo.equalsIgnoreCase("email")) {
            inputField = By.id("s_mail"); // ID reale dell'input Email
            helperText = By.id("s_mail-helper-text");
        } else {
            throw new IllegalArgumentException("Tipo non valido: " + tipo);
        }
        // Verifica il bordo rosso tramite aria-invalid
        getWebDriverWait(5)
                .withMessage("La textbox " + tipo + " non presenta il bordo rosso")
                .until(driver -> "true".equals(driver.findElement(inputField).getAttribute("aria-invalid")));
        // Verifica visibilità del messaggio di errore
        getWebDriverWait(5)
                .withMessage("Il messaggio di errore per " + tipo + " non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(helperText));
        // Verifica che il bottone associa sia disabilitato
        Assertions.assertNotNull(associaButton.getAttribute("disabled"), "Il bottone Associa non è disabilitato");
    }

    public void clearMailbox(String check) {
        if (check == null) return;

        if (check.equalsIgnoreCase("pec")) {
            // Valorizza il campo con wait esplicito
            indirizzoPecField = getWebDriverWait(15)
                    .withMessage("Il campo PEC non è visibile")
                    .until(driver -> driver.findElement(By.id("s_value"))); // sostituire con l'id corretto

            js().executeScript("arguments[0].setAttribute('autocomplete', 'off')", indirizzoPecField);
            indirizzoPecField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);

        } else if (check.equalsIgnoreCase("email")) {
            // Valorizza il campo con wait esplicito
            emailField = getWebDriverWait(15)
                    .withMessage("Il campo Email non è visibile")
                    .until(driver -> driver.findElement(By.id("email"))); // sostituire con l'id corretto

            js().executeScript("arguments[0].setAttribute('autocomplete', 'off')", emailField);
            emailField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }
    }


    public void clickConfermaPopupOTP() {
        WebElement confirmOtpPopup = getWebDriverWait(15)
                .withMessage("Il bottone 'Conferma' nel popup OTP non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("code-confirm-button")));

        confirmOtpPopup.click();
    }

    public void clickAnnullaPopupOTP() {
        WebElement cancelOtpPopup = getWebDriverWait(5)
                .withMessage("Il bottone Confirm non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("code-cancel-button")));

        cancelOtpPopup.click();
    }

    public boolean waitErrorMessagePopupOTP() {
        try {
            getWebDriverWait(5)
                    .withMessage("Il messaggio di errore inserimento OTP non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("codeModalErrorTitle")));

            logger.info("Il messaggio di errore viene visualizzato correttamente");
            return true;
        } catch (TimeoutException e) {
            logger.info("Il messaggio di errore non viene visualizzato");
            return false;
        }
    }


    public void clickInizia() {
        WebElement button = getWebDriverWait(5).withMessage("Impossibile Cliccare su Inizia").
                until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@data-testid='legalContacts']//button[contains(@class,'MuiButton-containedPrimary')]")
                ));
        button.click();
    }

    public void clickAttiva() {
        WebElement button = getWebDriverWait(5).withMessage("Impossibile Cliccare su Attiva")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@data-testid='activateButton']")));
        button.click();
    }

    public void clickNonOra() {
        WebElement nonOraButton = getWebDriverWait(15)
                .withMessage("Impossibile cliccare su 'Non ora'")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class, 'MuiBox-root')]//button[contains(@class, 'MuiButton-sizeMedium')])[2]")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", nonOraButton);

    }

    public void clickNonOraUat() {
        WebElement nonOraButton = getWebDriverWait(15)
                .withMessage("Impossibile cliccare su 'Non ora Uat'")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class, 'MuiBox-root')]//button[contains(@class, 'MuiButton-sizeMedium')])[3]")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", nonOraButton);
    }

    public void clickLoFaroPiuTardiOrConfermaModificaRecapito() {
        WebElement loFaroPiuTardi = getWebDriverWait(25)
                .withMessage("Impossibile Cliccare su Lo faro piu tardi o su Conferma Modifica Recapito")
                .until(ExpectedConditions.elementToBeClickable(
                        By.id("dialog-close-button")));
        loFaroPiuTardi.click();
    }

    public void clickTornaAiTuoiRecapiti() {
        WebElement tornaAiTuoiRecapiti = getWebDriverWait(25).withMessage("Impossibile Cliccare su Vai ai tuoi recapiti")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='wizard-feedback-button']")));
        tornaAiTuoiRecapiti.click();
    }

    public void verificaAttivazioneDomicilioDigitaleDellaTuaImpresa() {
        verificaPresenza("Impossibile trovare Il domicilio digitale della tua impresa ", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[@data-testid='legalContactsTitle']")
        ));

        verificaPresenza("Impossibile trovare Attivo ", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-testid='legalContacts']//div[contains(@class, 'MuiChip-colorSuccess')]")
        ));

        verificaPresenza("Impossibile trovare Gestisci ", ExpectedConditions.elementToBeClickable(
                By.cssSelector("button svg[data-testid='ConstructionIcon']")
        ));

        verificaPresenza("Impossibile trovare Disattiva ", ExpectedConditions.elementToBeClickable(
                By.cssSelector("button svg[data-testid='PowerSettingsNewIcon']")
        ));
    }

    private void verificaPresenza(String message, ExpectedCondition<WebElement> isTrue) {
        WebElement titolo = getWebDriverWait(15).withMessage(message)
                .until(isTrue);
        Assertions.assertNotNull(titolo);
    }

    public void clickSuBottoneAttivaSENDSuIO() {
        WebElement bottoneActionBy = getWebDriverWait(10)
                .withMessage("Il bottone Attiva SEND Su IO non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("ioContactButton")));
        bottoneActionBy.click();

    }

    public void clickAnnulla() {
        WebElement annulla = getWebDriverWait(15)
                .withMessage("Non è presente il bottone Annulla")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(), 'Annulla')]")));
        annulla.click();
    }

    public void verificaDaAttivareDomicilioDigitale() {
        verificaPresenza("Impossibile trovare Il domicilio digitale della tua impresa ", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[contains(text(), 'domicilio digitale')]")
        ));
        verificaPresenza("Impossibile trovare Attivo ", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[contains(text(), 'domicilio digitale')]//following::span[contains(text(), 'Da attivare')]")
        ));
        verificaPresenza("Impossibile Cliccare su Inizia ", ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Inizia')]")
        ));
    }

    public void verificaDaAttivareIO() {
        verificaPresenza("Impossibile trovare sezione integrazione IO ", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='ioContactSection']//h6[@data-testid='ioContactTitle']")
        ));
        verificaPresenza("Impossibile trovare Da attivare ", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='ioContactSection']//div[contains(@class,'MuiChip-colorDefault')]")
        ));
        verificaPresenza("Impossibile Cliccare su Scarica app IO ", ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='ioContactSection']//button[contains(@class,'MuiButton-contained')]")
        ));
    }

    public void verificaAttivoIO() {
        verificaPresenza("Impossibile trovare sezione integrazione IO ", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='ioContactSection']//h6[@data-testid='ioContactTitle']")
        ));
        verificaPresenza("Impossibile trovare Attivo ", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='ioContactSection']//div[contains(@class,'MuiChip-colorSuccess')]")
        ));
    }

    public void checkBannerRecapitoCortesiaMancante() {
        verificaPresenza("Il banner di recapito di cortesia mancante non è presente", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-testid='addDomicileBanner' and contains(@class,'MuiAlert-outlinedWarning')]")));
        verificaPresenza("Il banner di recapito di cortesia mancante non ha il testo corretto", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@data-testid='addDomicileBanner' and contains(@class,'MuiAlert-outlinedWarning')]//p)[1]")));
        verificaPresenza("Il banner di recapito di cortesia mancante non ha il sottotesto corretto", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@data-testid='addDomicileBanner' and contains(@class,'MuiAlert-outlinedWarning')]//p)[2]")));
    }

    public void checkPresenzaBannerEmailMancante() {
        try {
            verificaPresenza("Il banner di email mancante non è presente", ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@data-testid='addDomicileBanner']")));
            verificaPresenza("Il banner di email mancante non ha il testo corretto", ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@data-testid='addDomicileBanner']//..//p[contains(text(),'Aggiungi un indirizzo email per ricevere un avviso quando hai una nuova notifica su SEND.')]")));
        } catch (TimeoutException e) {
            Assertions.fail("Il banner di email mancante non è presente");
        }
    }

    public void checkAssenzaBannerEmailMancante() {
        try {
            verificaPresenza("Il banner di email mancante non è presente", ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@data-testid='addDomicileBanner']")));
            verificaPresenza("Il banner di email mancante non ha il testo corretto", ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@data-testid='addDomicileBanner']//..//p[contains(text(),'Aggiungi un indirizzo email per ricevere un avviso quando hai una nuova notifica su SEND.')]")));
            Assertions.fail("Il banner di email mancante è presente.");
        } catch (TimeoutException e) {
            Assertions.assertTrue(true, "Il banner di email mancante non è presente");
        }
    }

    public void checkBannerPECInValidazione(String ente) {
        verificaPresenza("Il banner di PEC in validazione non è presente", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-testid='PecVerificationAlert']")));
        if (ente.isEmpty()) {
            verificaPresenza("Il banner di PEC in validazione non ha il testo corretto", ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@data-testid='PecVerificationAlert']//..//p[contains(text(),'Fino al termine del processo non sarà possibile modificare i recapiti a valore legale.')]")));
        } else {
            verificaPresenza("Il banner di PEC in validazione non ha il testo corretto", ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@data-testid='PecVerificationAlert']//..//p[contains(text(),'La piattaforma SEND resterà il tuo domicilio digitale per " + ente + " fino al completamento della validazione.')]")));
        }
    }

    public void cliccaBottone(String testo) {
        WebElement button = getWebDriverWait(15)
                .withMessage("Non è presente il bottone '" + testo + "'")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(), '" + testo + "')]")));
        button.click();
    }

    public void cliccaNotifica(String testo) {
        WebElement button = getWebDriverWait(15)
                .withMessage("Non è presente la notifica '" + testo + "'")
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("[data-testid='sideMenuItem-" + testo + "']")
                ));
        button.click();
    }

    public void cliccaBottoneEsciPG() {
        WebElement button = getWebDriverWait(15)
                .withMessage("Non è presente il bottone Esci PG")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("(//div[contains(@class, 'MuiBox-root')]//button[contains(@class, 'MuiButton-sizeMedium')])[1]")));
        button.click();
    }


    public void verificaPagina(String testo) {
        getWebDriverWait(25)
                .withMessage("Non è presente Il testo '" + testo + "'")
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(text(), '" + testo + "')]")));
    }

    public void clickInserisciEmailPopUp() {
        WebElement bottoneInserisciEmail = getWebDriverWait(10)
                .withMessage("Bottone Inserisci email non trovato")
                .until(ExpectedConditions.elementToBeClickable(By.id("dialog-close-button")));
        bottoneInserisciEmail.click();
    }

    public void insertPEC(String emailPec) {
        WebElement pecInput = getWebDriverWait(20)
                .withMessage("Impossibile Inserisci PEC")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='pec-wizard-input']//input")));
        pecInput.sendKeys(emailPec);
    }

    public void clickConferma() {
        WebElement confermaButton = getWebDriverWait(60)
                .withMessage("Impossibile trovare il tasto Conferma")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='next-button']")));
        confermaButton.click();
    }

    public void verificaDaAttivareEmail() {
        getWebDriverWait(20)
                .withMessage("Impossibile trovare lo stato Da attivare in Email")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'MuiChip-root')]/span[normalize-space(text())='Da attivare']")));

    }

    public void clickMenuEnteMittenteInseriemntoEnte(String ente) {

        WebElement inputEnte = getWebDriverWait(10)
                .withMessage("Impossibile trovare il campo 'Ente mittente'")
                .until(ExpectedConditions.elementToBeClickable(By.id("sender")));
        inputEnte.clear();
        inputEnte.sendKeys(ente);
        webTool.waitTime(1);
        inputEnte.sendKeys(Keys.ARROW_DOWN);
        inputEnte.sendKeys(Keys.ENTER);
    }

    public void clickMenuEnteMittenteInseriemntoEnte() {

        WebElement inputEnte = getWebDriverWait(10)
                .withMessage("Impossibile trovare il campo 'Ente mittente'")
                .until(ExpectedConditions.elementToBeClickable(By.id("sender")));
        inputEnte.clear();
        inputEnte.sendKeys(" ");

        inputEnte.sendKeys(Keys.ARROW_DOWN);
        inputEnte.sendKeys(Keys.ENTER);


    }

    public void inserisciPecInPersonalizzaIlTuoDomicilioDigitalePerEnte(String pecOrEmail) {
        WebElement inputPEC = getWebDriverWait(10)
                .withMessage("Impossibile inserire Pec o Email")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("s_value")));
        inputPEC.clear();
        inputPEC.sendKeys(pecOrEmail);

        clickConferma();
    }

    public void verificaEdEliminaPersonalizzatiPerEnte() {
        try {
            WebElement eliminaButton = getWebDriverWait(5)
                    .withMessage("Impossibile trovare il tasto 'Elimina verificaEdEliminaPersonalizzatiPerEnte'")
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[starts-with(@data-testid, 'cancelContact-') or starts-with(@id, 'cancelContact-')]")));
            if (eliminaButton.isDisplayed() && eliminaButton.isEnabled()) {
                js().executeScript("arguments[0].scrollIntoView(true);", eliminaButton);
                eliminaButton.click();
                clickSuConfermaElimina();
            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("Bottone 'Elimina Personalizzati Per Ente' non presente.");
        } catch (Exception e) {
            Assertions.fail("Errore inaspettato durante la ricerca o il click sul bottone 'Elimina Personalizzati Per Ente'.", e);
        }
    }

    public void clickModificaPersonalizzatiPerEnte() {
        WebElement modificaPersonalizzatiPerEnte = getWebDriverWait(5)
                .withMessage("Impossibile trovare il tasto 'Elimina'")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@id, 'modifyContact-')])[1]")));
        modificaPersonalizzatiPerEnte.click();
    }

    public void clickModificaPersonalizzatiPerEnteOFF() {
        WebElement modificaPersonalizzatiPerEnte = getWebDriverWait(5)
                .withMessage("Impossibile trovare il tasto 'Elimina'")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@id, 'modifyContact-')])[2]")));
        modificaPersonalizzatiPerEnte.click();
    }

    public void clickEliminaPersonalizzatiPerEnte() {
        WebElement eliminaPersonalizzatiPerEnte = getWebDriverWait(5)
                .withMessage("Impossibile trovare il tasto 'Elimina'")
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("(//button[contains(@class, 'MuiButton-nakedError')])[2]")));
        eliminaPersonalizzatiPerEnte.click();

        clickSuConfermaElimina();
    }


    public void modificaPecPersonalizzatiPerEnteEConferma(String pec) {

        WebElement pecInput = getWebDriverWait(5)
                .withMessage("Impossibile modificare il testo per la Pec ")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@class, 'MuiInputBase-input')]")));

        pecInput.sendKeys(Keys.CONTROL + "a");
        pecInput.sendKeys(Keys.DELETE);

        pecInput.sendKeys(pec);

        confermaPecPersonalizzatiPerEnte();

    }

    private void confermaPecPersonalizzatiPerEnte() {
        WebElement confermaButton = getWebDriverWait(5)
                .withMessage("Impossibile cliccare conferma modifica Pec Personalizzati Per Ente E Conferma ")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id, 'saveContact')]")));
        confermaButton.click();
    }

    public void selezionaTipologia(String tipologia) {
        WebElement tipoField = getWebDriverWait(10)
                .withMessage("Impossibile selezione Tipologia ")
                .until(ExpectedConditions.elementToBeClickable(By.id("channelType")));
        tipoField.click();

        WebElement pecOption = getWebDriverWait(10)
                .withMessage("Impossibile visualizzare mene a discesa di tipologia ")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//li[.='" + tipologia + "']"))
                );
        pecOption.click();
    }

    public void verificaPresenzaMessaggio() {
        getWebDriverWait(10)
                .withMessage("Impossibile trovare messaggio associato ")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='alreadyExistsAlert']")));
    }

    public void verificaAssenzaSezionePersonalizzatiPerEnte() {

        try {
            getWebDriverWait(10)
                    .withMessage("Impossibile trovare messaggio associato ")
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//div[@data-testid='specialContacts']")
                    ));
            Assertions.fail("La sezione 'PERSONALIZZATI PER ENTE' è presente.");

        } catch (TimeoutException e) {
            Assertions.assertTrue(true, "La sezione 'PERSONALIZZATI PER ENTE' non è presente, come previsto.");
        }
    }

    public void clickBottoneDisattivaInDomicilioDigitale(String testo) {
        WebElement disattivaButton = getWebDriverWait(10).withMessage("Disattiva Non è presente dentro '" + testo + "'")
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//h6[contains(text(), '" + testo + "')]/ancestor::div[contains(@class, 'MuiCardHeader-root')]//following-sibling::div//button[contains(text(), 'Disattiva')]")));
        disattivaButton.click();
    }

    public void verificaEDisattivaPersonalizzatiPerEnte() {
        try {
            WebElement disattivaButton = getWebDriverWait(5)
                    .withMessage("Impossibile trovare il tasto 'Disattiva ' PERSONALIZZATI PER ENTE").until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@data-testid='cancelContact-special_SERCQ_SEND']")
                    ));

            if (disattivaButton.isDisplayed() && disattivaButton.isEnabled()) {
                disattivaButton.click();
                clickSuConfermaElimina();


            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("Bottone 'Disattiva Personalizzati Per Ente' non presente.");
        } catch (Exception e) {
            Assertions.fail("Errore inaspettato durante la ricerca o il click sul bottone 'Disattiva Personalizzati Per Ente'.", e);
        }
    }


    public void verificaAndOrDisattiva(String testo) {
        try {
            WebElement disattivaButton = getWebDriverWait(10).withMessage("Non è presente dentro '" + testo + "' il testo 'Disattiva'")
                    .until(ExpectedConditions.elementToBeClickable
                            (By.xpath("//*[@data-testid='legalContacts']//button[.//*[@data-testid='PowerSettingsNewIcon']]")));
            if (disattivaButton.isDisplayed() && disattivaButton.isEnabled()) {
                logger.info("Bottone 'Disattiva' trovato, lo clicco!");
                disattivaButton.click();
                clickSuConfermaElimina();
            } else {
                logger.warn("Bottone 'Disattiva' trovato ma non è visibile o abilitato.");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("Bottone 'Disattiva' non presente.");
        } catch (Exception e) {
            Assertions.fail("Errore inaspettato durante la ricerca o il click sul bottone 'Disattiva'.", e);
        }


    }


    public void verificaEDisattivaDomicilioDigitale(String chooseButton) {
        try {
            WebElement disattivaButton = getWebDriverWait(20).withMessage("Non è presente dentro Domicilio Digitale il testo 'Disattiva'")
                    .until(ExpectedConditions.elementToBeClickable
                            (By.xpath("//*[@data-testid='legalContacts']//button[.//*[@data-testid='PowerSettingsNewIcon']]")));
            if (disattivaButton.isDisplayed() && disattivaButton.isEnabled() && chooseButton.equalsIgnoreCase("Conferma")) {
                disattivaButton.click();
                clickSuConfermaElimina();
            } else if (disattivaButton.isDisplayed() && disattivaButton.isEnabled() && chooseButton.equalsIgnoreCase("Annulla")) {
                disattivaButton.click();
                clickAnnulla();
            } else {
                logger.warn("Bottone 'Disattiva Domicilio Digitale' non è visibile o abilitato.");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("Bottone 'Disattiva Domicilio Digitale' non presente.");
        } catch (Exception e) {
            Assertions.fail("Errore inaspettato durante la ricerca o il click sul bottone 'Disattiva Domicilio Digitale'.", e);
        }
    }

    public void disattivaDomicilioDigitaleAnnulla() {
        try {
            WebElement disattivaButton = getWebDriverWait(20).withMessage("Non è presente dentro Domicilio Digitale il testo 'Disattiva'")
                    .until(ExpectedConditions.elementToBeClickable
                            (By.xpath("//*[@data-testid='legalContacts']//button[.//*[@data-testid='PowerSettingsNewIcon']]")));
            if (disattivaButton.isDisplayed() && disattivaButton.isEnabled()) {
                disattivaButton.click();
                clickButtonAnnullaEliminazioneInPopUp();
            } else {
                logger.warn("Bottone 'Disattiva Domicilio Digitale' trovato ma non è visibile o abilitato.");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("Bottone 'Disattiva Domicilio Digitale' non presente.");
        } catch (Exception e) {
            Assertions.fail("Errore inaspettato durante la ricerca o il click sul bottone 'Disattiva Domicilio Digitale e Annulla'.", e);
        }
    }

    public void verificaEDisattivaAppIO() {
        try {
            WebElement disattivaButton = getWebDriverWait(10).withMessage("Non è presente dentro AppIO 'Disattiva'")
                    .until(ExpectedConditions.elementToBeClickable
                            (By.xpath("//button[contains(@class, 'MuiButton-sizeSmall') and .//*[@data-testid='PowerSettingsNewIcon']]")));
            if (disattivaButton.isDisplayed() && disattivaButton.isEnabled()) {
                disattivaButton.click();
                clickSuConfermaElimina();
            } else {
                logger.warn("Bottone 'Disattiva AppIO' trovato ma non è visibile o abilitato.");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("Bottone 'Disattiva AppIO' non presente.");
        } catch (Exception e) {
            Assertions.fail("Errore inaspettato durante la ricerca o il click sul bottone 'Disattiva AppIO'.", e);
        }
    }

    public void disattivaAppIOeAnnulla() {
        try {
            WebElement disattivaButton = getWebDriverWait(10).withMessage("Non è presente dentro AppIO 'Disattiva'")
                    .until(ExpectedConditions.elementToBeClickable
                            (By.xpath("//button[contains(@class, 'MuiButton-sizeSmall') and .//*[@data-testid='PowerSettingsNewIcon']]")));
            if (disattivaButton.isDisplayed() && disattivaButton.isEnabled()) {
                disattivaButton.click();
                clickAnnulla();
            } else {
                logger.warn("Bottone 'Disattiva AppIO' trovato ma non è visibile o abilitato.");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("Bottone 'Disattiva AppIO' non presente.");
        } catch (Exception e) {
            Assertions.fail("Errore inaspettato durante la ricerca o il click sul bottone 'Disattiva AppIO'.", e);
        }
    }

    public void verificaEDisattivaEmail() {
        try {
            WebElement disattivaButton = getWebDriverWait(15)
                    .withMessage("Il bottone 'Disattiva Email' non è presente entro il tempo limite.")
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-testid='disable-email']")));


            if (!disattivaButton.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", disattivaButton);
                webTool.waitTime(1);
            }

            try {
                getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(disattivaButton)).click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", disattivaButton);
            }
            clickSuConfermaElimina();
        } catch (TimeoutException e) {
            logger.warn("Bottone 'Disattiva Email' non trovato entro il tempo limite.");
        } catch (Exception e) {
            Assertions.fail("Errore inaspettato durante la disattivazione dell'email: " + e.getMessage());
        }
    }

    public void disattivaIndirizzoEmailAziendale() {
        WebElement disattivaButton = getWebDriverWait(20)
                .withMessage("Pulsante 'Disattiva' non trovato per l'indirizzo email aziendale")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//h6[@data-testid='emailContactTitle' and (contains(text(),'email'))]/ancestor::div[contains(@data-testid,'PnInfoCardHeader')]//button[@data-testid='disable-email']")
                ));

        disattivaButton.click();
        clickButtonAnnullaEliminazioneInPopUp();
    }

    public void verificaEDisattivaCellulare() {
    }

    public void clickBottoneIndietroTrasferisciPersonalizzaIlDomicilioDigitale() {

        WebElement indietroButton = getWebDriverWait(10)
                .withMessage("Impossibile Trovare il tasto Inditro del metodo clickBottoneIndietroTrasferisciPersonalizzaIlDomicilioDigitale")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='prev-button']")));
        indietroButton.click();


    }

    public void clickBottoneEsciPF() {
        WebElement esciButton = getWebDriverWait(10)
                .withMessage("Impossibile Trovare il tasto Esci del metodo clickBottoneEsciPF")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button//*[@data-testid='ArrowBackIcon']")));
        esciButton.click();
    }

    public String getEmailInvalidMessage() {
        WebElement errorMessage = getWebDriverWait(30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_email-helper-text")));

        return errorMessage.getText();
    }


    public void verificaIndirizzoEmailNonValido() {
        getWebDriverWait(10)
                .withMessage("Il messaggio di errore per l'indirizzo email non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_email-helper-text")));

    }

    public void clickContinuaTabInserisciUnRecapito() {
        WebElement bottoneContinua = getWebDriverWait(10)
                .withMessage("Impossibile Trovare il tasto Continua nel tab Inserisci Un Recapito ")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(normalize-space(), 'Continu')]"))
                );
        bottoneContinua.click();
    }

    public void verificaPresenzaCampoObbligatorio() {
        getWebDriverWait(10)
                .withMessage("Impossibile Trovare il messaggio Campo obbligatorio").until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.id("disclaimer-helper-text")));
    }

    public void spuntareCheckboxPrivacy() {
        List<String> possibleIds = Arrays.asList("s_disclaimer", "disclaimer");
        WebElement checkboxInput = null;
        for (String id : possibleIds) {
            try {
                checkboxInput = getWebDriverWait(5)
                        .withMessage("Checkbox con id '" + id + "' non trovata")
                        .until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
                break;
            } catch (TimeoutException ignored) {
            }
        }

        if (checkboxInput == null) {
            Assertions.fail("Nessuna checkbox trovata con id tra: " + possibleIds);
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkboxInput);
    }

    public void verificaIndirizzoPecNonValido() {
        getWebDriverWait(10)
                .withMessage("Il messaggio di errore per l'indirizzo pec non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("pec-helper-text")));
    }

    public void verificaScomparsaBannerInizia() {
        boolean invisibile = getWebDriverWait(10)
                .withMessage("Il tasto 'Inizia' nel banner è ancora visibile")
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[@data-testid='addDomicileBanner']//button[contains(@class, 'MuiButton-root')]")));

        Assertions.assertTrue(invisibile, "Il tasto 'Inizia' è ancora presente nel banner.");
    }

    public void verificaAbilitazioneCampoEmail() {
        WebElement emailInput = getWebDriverWait(10)
                .withMessage("L'input email non è visibile nella pagina")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("default_email")));

        Assertions.assertTrue(emailInput.isEnabled(), "Il campo email esiste ma non è abilitato per l'inserimento.");

    }

    public void verificaCampoObbligatorioEnteETipologia() {
        WebElement enteHelperText = getWebDriverWait(10)
                .withMessage("Messaggio 'Campo obbligatorio' per il campo Ente mittente non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("sender-helper-text")));

        Assertions.assertEquals("Campo obbligatorio", enteHelperText.getText().trim());

        // Verifica campo "Tipologia"
        WebElement pecHelperText = getWebDriverWait(10)
                .withMessage("Messaggio 'Indirizzo PEC non valido' non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("channelType-helper-text")));

        Assertions.assertEquals("Campo obbligatorio", pecHelperText.getText().trim());
    }

    public void clickContinuaSenzaCollegareIO() {
        WebElement continuaButton = getWebDriverWait(20)
                .withMessage("Pulsante 'Continua senza collegare IO' non trovato")
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-testid='skipButton']")
                ));

        continuaButton.click();
    }

    public void clickScollegaSENDDaIOInAttivaDomicilioDigitaleSuSEND() {
        WebElement button = getWebDriverWait(20)
                .withMessage("Impossibile trovare il pulsante Scollega SEND Da IO In Attiva Domicilio Digitale Su SEND ")
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-testid='disableIOButton']")
                ));
        button.click();
    }

    public void clickScollegaSENDDaIONelPopUpAggiungiITuoiRecapitiEImportante() {
        WebElement dialogButton = getWebDriverWait(40)
                .withMessage("Impossibile trovare il pulsante nel Pop-UP Scollega SEND Da IO In Aggiungere i tuoi recapiti è importante")
                .until(ExpectedConditions.elementToBeClickable(By.id("dialog-confirm-button")));
        dialogButton.click();

    }
}