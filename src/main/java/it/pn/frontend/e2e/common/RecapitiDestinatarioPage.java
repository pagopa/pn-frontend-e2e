package it.pn.frontend.e2e.common;

import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class RecapitiDestinatarioPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("RecapitiDestinatarioPage");


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
        getWebDriverWait(10).withMessage("Il bottone chiudi non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//h2[@id='dialog-title']/following-sibling::div/button[contains(text(),'Annulla')]"))));
        WebElement chiudiButtonBy = driver.findElement(By.xpath("//h2[@id='dialog-title']/following-sibling::div/button[contains(text(),'Annulla')]"));
        js().executeScript("arguments[0].click()", chiudiButtonBy);
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
        getWebDriverWait(10).withMessage("Il bottone Attiva non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("default_pec-button"))));
        WebElement attivaButton = driver.findElement(By.id("default_pec-button"));
        attivaButton.click();
    }

    public void clickConfermaButtonEliminaPopUp() {
        getWebDriverWait(10).withMessage("Il bottone conferma non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//h2[@id='dialog-title']/following-sibling::div/button[contains(text(),'Conferma')]"))));
        WebElement confermaEliminaButton = driver.findElement(By.xpath("//h2[@id='dialog-title']/following-sibling::div/button[contains(text(),'Conferma')]"));
        confermaEliminaButton.click();
        logger.info("clickConfermaButtonEliminaPopUp");
    }

    //cambiati check del testo per evitare casi di element stale exception e per differenziare tra i casi per cittadini e imprese (format del testo è diverso tra i portali)
    public void waitLoadPopUp() {
        try {
            getWebDriverWait(30).withMessage("Non viene visualizzato correttamente il titolo").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-title"))));
            getWebDriverWait(30).withMessage("La descrizione non viene visualizzata").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-description"))));
            getWebDriverWait(30).withMessage("La scritta 'Inserisci codice' non viene visualizzata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='dialog-content']//p[contains(@class,'MuiTypography-root MuiTypography-body1') and not(@role='button')]"))));
            getWebDriverWait(30).withMessage("Le input boxes non vengono visualizzate").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//input[contains(@id,'code-input-')]"))));
            List<WebElement> inputBoxes = driver.findElements(By.xpath("//input[contains(@id,'code-input-')]"));
            if (inputBoxes.size() != 5) {
                Assertions.fail("Il numero di input box non è corretto");
            }
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//div[@data-testid='dialog-content']//div[contains(@class,'MuiTypography-root MuiTypography-body2')]"))));
            confermaButtonPopUp = driver.findElement(By.id("code-confirm-button"));
            annullaButton = driver.findElement(By.id("code-cancel-button"));
            boolean checkButton = confermaButtonPopUp.isEnabled() && annullaButton.isEnabled();
            if (!checkButton) {
                Assertions.fail("i pulsanti all'interno del pop-up non rispettano le condizioni");
            }
            logger.info("Il pop-up di conferma viene visualizzato correttamente");
        } catch (TimeoutException | StaleElementReferenceException e) {
            Assertions.fail("Il pop-up di conferma NON viene visualizzato correttamente con errori:" + e.getMessage());
        }
    }

    public void sendOTP(String otp) {
        String[] otps = otp.split("");
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//input[contains(@id,'code-input')]"))));
            List<WebElement> otpInputby = driver.findElements(By.xpath("//input[contains(@id,'code-input')]"));
            for (int i = 0; i < otps.length; i++) {
                otpInputby.get(i).sendKeys(otps[i]);
            }
            logger.info("Il codice otp viene inserito correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il codice otp NON viene inserito correttamente con errore:" + e.getMessage());
        }
    }

    public void clearOTP() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//input[contains(@id,'code-input')]"))));
        List<WebElement> otpInputby = driver.findElements(By.xpath("//input[contains(@id,'code-input')]"));
        for (int i = 4; i >= 0; i--) {
            otpInputby.get(i).sendKeys(Keys.BACK_SPACE);
        }
        logger.info("Il codice otp viene cancellato correttamente");

    }

    public void confermaButtonClickPopUp() {
        webTool.waitTime(3);
        getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("code-confirm-button"))));
        confermaButtonPopUp = driver.findElement(By.id("code-confirm-button"));
        confermaButtonPopUp.click();
        webTool.waitTime(5);
        List<WebElement> confermaButtonPostInserimentoBy = driver.findElements(By.id("code-confirm-button"));
        if (!confermaButtonPostInserimentoBy.isEmpty()) {
            confermaButtonPostInserimentoBy.get(0).click();
        }
        // if confermaButtonPostInserimento appears, click it otherwise go on

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
        getWebDriverWait(30).withMessage("Il bottone avvisami della mail non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@data-testid='add email']"))));
        avvisamiMailButton = driver.findElement(By.xpath("//button[@data-testid='add email']"));
        avvisamiMailButton.click();
    }

    public void clickAvvisamiSMS() {
        getWebDriverWait(30).withMessage("Il bottone avvisami del sms non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("default_sms-button"))));
        avvisamiSMSButton = driver.findElement(By.id("default_sms-button"));
        avvisamiSMSButton.click();
    }

    public void insertEmail(String email) {
        getWebDriverWait(10).withMessage("l'input mail non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email"))));
        inserimentoMailField = driver.findElement(By.id("default_email"));

        if (!inserimentoMailField.getAttribute("value").isEmpty()) {
            inserimentoMailField.clear();
        }
        if (inserimentoMailField.isDisplayed()) {
            inserimentoMailField.clear();
            inserimentoMailField.sendKeys(email);
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", inserimentoMailField);
            inserimentoMailField.clear();
            inserimentoMailField.sendKeys(email);
        }
    }

    public void insertPhone(String cellulare) {

        getWebDriverWait(30).withMessage("l'input numero telefono non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_sms"))));
        inserimentoPhoneField = driver.findElement(By.id("default_sms"));
        if (inserimentoPhoneField.isDisplayed()) {
            inserimentoPhoneField.clear();
            inserimentoPhoneField.sendKeys(cellulare);
        } else {
            js().executeScript("arguments[0].scrollIntoView(true);", inserimentoPhoneField);
            inserimentoPhoneField.clear();
            inserimentoPhoneField.sendKeys(cellulare);
        }
    }

    public boolean verificaPecAssociata() {
        try {
            getWebDriverWait(40).withMessage("PEC associata non presente").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_pec-typography"))));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("pec associata non trovata {}", e.getMessage());
            return false;
        }
    }

    public boolean siVisualizzaPopUpConferma() {
        try {
            getWebDriverWait(20).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]"))));
            return true;
        } catch (TimeoutException e) {
            logger.error("pop up conferma non trovato: \n" + e.getMessage());
            return false;
        }
    }

    public void clickConfermaButton() {
        getWebDriverWait(10).withMessage("Il bottone conferma del pop up non é cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id='confirmDialog']"))));
        confermaButtonPoPUpPec = driver.findElement(By.xpath("//button[@id='confirmDialog']"));
        confermaButtonPoPUpPec.click();
    }

    public void visualizzaValidazione() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(), 'Validazione PEC in corso')]"))));
        } catch (TimeoutException e) {
            Assertions.fail("Pec non associata con errore:" + e.getMessage());
        }
    }

    public boolean verificaMailAssociata() {
        try {
            getWebDriverWait(30).withMessage("L'email di cortesia non è presente")
                    .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email-typography"))));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
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

    public boolean verificaDomicilioDigitaleAssociato() {
        try {
            getWebDriverWait(30).withMessage("Il domicilio digitale non è attivo").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='legalContacts']"))));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("Domicilio digitale non attivo");
            return false;
        }
    }

    public boolean verificaMailField() {
        webTool.waitTime(20);
        inserimentoMailField = driver.findElement(By.id("default_email"));
        return inserimentoMailField.isDisplayed();
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
        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("modifyContact-default_email"))));
        modificaEmailButton = driver.findElement(By.id("modifyContact-default_email"));
        if (modificaEmailButton.isDisplayed()) {
            this.modificaEmailButton.click();
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", modificaEmailButton);
            this.modificaEmailButton.click();
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
        getWebDriverWait(30).withMessage("Non si riesce a cliccare sul bottone salva").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Salva')]"))));
        WebElement salvaButtonBy = driver.findElement(By.xpath("//button[contains(text(),'Salva')]"));
        salvaButtonBy.click();
    }

    public boolean siControllaPECModificata(String pecInserita) {
        getWebDriverWait(20).withMessage("Non trovata nessuna email PEC inserita").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_pec-typography"))));
        WebElement pecBy = driver.findElement(By.id("default_pec-typography"));
        return pecBy.getText().equals(pecInserita);
    }


    public void clickSuEliminaPec() {
        getWebDriverWait(20).withMessage("Il bottone elimina della PEC associata non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("cancelContact-default_pec"))));
        eliminaPECButton = driver.findElement(By.id("cancelContact-default_pec"));
        logger.info("click sul pulsante elimina pec");
        eliminaPECButton.click();
    }

    public String waitLoadPopUpElimina() {
        try {
            getWebDriverWait(10).withMessage("Non è stato caricato il titolo del modal").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-title"))));
            getWebDriverWait(10).withMessage("Non è stato caricato il sottotitolo del modal").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-description"))));
            getWebDriverWait(10).withMessage("Non è stato caricato il bottone annulla del modal").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("buttonAnnulla"))));
            getWebDriverWait(10).withMessage("Non è stato caricato il bottone conferma del modal").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@aria-labelledby='dialog-title']//button[contains(text(),'Conferma')]"))));
        } catch (TimeoutException e) {
            logger.info("Non è stato caricato un elemento del pop up con errore: {}", e.getMessage());
        }
        WebElement titlePopUp = driver.findElement(By.id("dialog-title"));
        return titlePopUp.getText();
    }

    public void clickSuConfermaElimina() {
        logger.info("PRIMA DI clickSuConfermaElimina");
        getWebDriverWait(20).withMessage("Non è stato possibile cliccare sul bottone conferma").until(ExpectedConditions.elementToBeClickable( driver.findElement(By.id("buttonConferma"))));
        WebElement confermaRimuoviPECBy = driver.findElement(By.id("buttonConferma"));
        confermaRimuoviPECBy.click();
        logger.info("DOPO DI clickSuConfermaElimina");
    }

    public boolean siControllaEliminazionePEC() {
        webTool.waitTime(5);
        pecField = driver.findElement(By.id("default_pec"));
        return pecField.isDisplayed();
    }

    public boolean siControllaPresenzaPEC() {
        webTool.waitTime(5);
        pecEmail = driver.findElement(By.id("default_pec-typography"));
        return pecEmail.isDisplayed();
    }

    public void insertEnte(String comune) {
        webTool.waitTime(5);
        enteField = driver.findElement(By.id("sender"));
        enteField.sendKeys(comune);
        // wait 2seconds for the list to appear
        webTool.waitTime(5);
        enteField.sendKeys(Keys.ARROW_DOWN);
        enteField.sendKeys(Keys.ENTER);
        // verify if the first option is the one we want by checking the value
        getWebDriverWait(10).withMessage("Il comune non è visibile").until(ExpectedConditions.attributeContains(enteField, "value", comune));
    }

    public void clickSuIndirizzoPEC() {
        webTool.waitTime(5);
        tipoIndirizzoField = driver.findElement(By.id("addressType"));
        tipoIndirizzoField.click();
        WebElement opzionePEC = driver.findElement(By.xpath("//li[@data-value ='PEC']"));
        getWebDriverWait(30).withMessage("Non è visibile l'opzione 'Indirizzo PEC'").until(ExpectedConditions.elementToBeClickable(opzionePEC));
        opzionePEC.click();
    }

    public void insertPECAggiuntiva(String emailPec) {
        try {
            webTool.waitTime(5);
            indirizzoPecField = driver.findElement(By.id("s_value"));
            if (indirizzoPecField.isDisplayed()) {
                indirizzoPecField.sendKeys(emailPec);
            } else {
                js().executeScript("arguments[0].scrollIntoView(true);", indirizzoPecField);
                indirizzoPecField.sendKeys(emailPec);
            }
        } catch (ElementNotInteractableException e) {
            indirizzoPecField.sendKeys(emailPec);
        }
    }

    public void clickSuAssocia() {
        getWebDriverWait(10).withMessage("Il bottone associa non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("addSpecialButton"))));
        associaButton = driver.findElement(By.id("addSpecialButton"));
        js().executeScript("arguments[0].click()", associaButton);
    }


    public void insertEmailAggiuntiva(String mail) {
        try {
            webTool.waitTime(5);
            emailField = driver.findElement(By.id("s_value"));
            if (emailField.isDisplayed()) {
                emailField.sendKeys(mail);
            } else {
                js().executeScript("arguments[0].scrollIntoView(true);", emailField);
                emailField.sendKeys(mail);
            }
        } catch (ElementNotInteractableException e) {
            emailField.sendKeys(mail);
        }
    }

    public void siControllaEmailAggiunta() {
        getWebDriverWait(10).withMessage("La mail non è stata aggiunta correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//form[@data-testid='a95dace4-4a47-4149-a814-0e669113ce40_emailContact']"))));
    }

    public boolean controlloEmailAssociata(String email) {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid = 'courtesyContacts']//div//p[contains(text(),'" + email + "')]"))));
            return true;
        } catch (TimeoutException e) {
            logger.error("email associata non presente con errore {}", e.getMessage());
            return false;
        }
    }

    public boolean verificaNuovaEmailEPEC(String nuovaEmail) {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(" //div[@data-testid = 'DigitalContactsCardBody']//p[contains(text(),'" + nuovaEmail + "')]"))));
            return true;
        } catch (TimeoutException e) {
            logger.error("email non presente con errore \n" + e.getMessage());
            return false;
        }
    }

    public void eliminaNuovaEmail() {
        webTool.waitTime(5);
        eliminaButtonList = driver.findElements(By.xpath("//form[@data-testid = 'default_pecContact']//button[contains(text(),'Elimina')]"));
        int posizioneElimina = this.eliminaButtonList.size() - 1;
        if (posizioneElimina != -1) {
            getWebDriverWait(10).withMessage("Non è stato possibile cliccare sul bottone elimina email").until(ExpectedConditions.elementToBeClickable(eliminaButtonList.get(posizioneElimina)));
            js().executeScript("arguments[0].click()", eliminaButtonList.get(posizioneElimina));
            WebElement confermaPopUpBy = driver.findElement(By.xpath("//div[@aria-labelledby='dialog-title']//div/button[contains(text(),'Conferma')]"));
            getWebDriverWait(10).withMessage("Il bottone del pop-up non è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaPopUpBy));
            confermaPopUpBy.click();
        }

    }

    public void eliminaNuovaPec() {
        getWebDriverWait(30).withMessage("Non è stato possibile cliccare sul bottone elimina email").until(ExpectedConditions.elementToBeClickable( driver.findElements(By.xpath("//form[@data-testid = 'default_pecContact']//button[contains(text(),'Elimina')]")).get(0)));
        eliminaButtonList = driver.findElements(By.xpath("//form[@data-testid = 'default_pecContact']//button[contains(text(),'Elimina')]"));
        js().executeScript("arguments[0].click()", eliminaButtonList.get(0));
        WebElement confermaPopUpBy = driver.findElement(By.xpath("//div[@aria-labelledby='dialog-title']//div/button[contains(text(),'Conferma')]"));
        getWebDriverWait(30).withMessage("Il bottone del pop-up non  è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaPopUpBy));
        confermaPopUpBy.click();
    }

    public void confermaButtonEliminaClick() {

    }

    public String getEmailErrorMessage() {

        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email-helper-text"))));
        WebElement errorMessage = driver.findElement(By.id("default_email-helper-text"));
        return errorMessage.getText();
    }

    public String getPecErrorMessage() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_pec-helper-text"))));
        WebElement errorMessage = driver.findElement(By.id("default_pec-helper-text"));
        return errorMessage.getText();
    }

    public String getPecInvalidMessage() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("pec-helper-text"))));
        WebElement errorMessage = driver.findElement(By.id("pec-helper-text"));
        return errorMessage.getText();
    }

    public boolean verificaBottoneConfermaDisabilitato() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_pec-button"))));
            attivaButton = driver.findElement(By.id("default_pec-button"));
            return Boolean.parseBoolean(attivaButton.getAttribute("disabled"));
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("bottone non disabilitato {}", e.getMessage());
            return false;
        }
    }

    public void clickHoCapitoCheckBoxPopup() {
        WebElement hoCapitoCheckBox = driver.findElement(By.xpath("//span[contains(text(),'Ho capito')]/preceding-sibling::span/input"));
        logger.info("click su checkbox ho capito");
        hoCapitoCheckBox.click();
    }

    public void confermaEmailPopup() {
        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("code-confirm-button"))));
        WebElement popupConfirmButtonBy = driver.findElement(By.id("code-confirm-button"));
        popupConfirmButtonBy.click();
    }

    public boolean verificaPopUpConfermaEmail() {
        WebElement hoCapitoCheckboxBy = driver.findElement(By.xpath("//span[contains(text(),'Ho capito')]/preceding-sibling::span/input"));
        return hoCapitoCheckboxBy.isSelected();
    }

    public void clickAvvisamiViaEmail() {

        getWebDriverWait(10).withMessage("avvisami via email non è visibile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("default_email-button"))));
        avvisamiViaEmailButton = driver.findElement(By.id("default_email-button"));
        avvisamiViaEmailButton.click();
    }

    public boolean avvisamiViaEmailIsDisabled() {
        try {
            getWebDriverWait(30).withMessage("avvisami via email non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email-button"))));
            avvisamiViaEmailButton = driver.findElement(By.id("default_email-button"));
            return Boolean.parseBoolean(avvisamiViaEmailButton.getAttribute("disabled"));
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void visualizzazioneSezioneAltriRecapiti() {
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il titolo della sezione altri recapiti").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("courtesyContactsTitle"))));
    }

    public void visualizzazioneCampiSezioneAltriRecapiti() {
        vaiInFondoAllaPagina();
        getWebDriverWait(10).withMessage("Non si visualizza correttamente  il titolo della sezione altri recapiti").until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//h5[contains(@id, 'specialContact')]"))));
        getWebDriverWait(10).withMessage("Non si visualizza il titolo della tabella").until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//p[contains(text(), 'Già associati')]"))));
        getWebDriverWait(10).withMessage("Non si visualizza la tabella dei recapiti già associati").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='root']/div[1]/div/main/div/div[2]/div[2]/div/div/table"))));
        WebElement tableGiaAssociati = driver.findElement(By.xpath("//table[@aria-label='Già associati']"));
        List<WebElement> tableRows = tableGiaAssociati.findElements(By.xpath(".//tbody/tr"));
        for (WebElement row : tableRows) {
            List<WebElement> columns = row.findElements(By.xpath(".//td"));
            if (columns.get(0).getText().contains("Comune di Verona")) {
                logger.info("Si visualizza l'ente inserito correttamente");
            }
            if (columns.get(1).getText().contains("pec@pec.pagopa.it")) {
                getWebDriverWait(10).withMessage("Bottone modifica non visualizzato e non cliccabile").until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//button[contains(@id, 'modifyContact')]"))),
                        ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(".//button[contains(@id, 'modifyContact')]")))));
                getWebDriverWait(10).withMessage("Bottone elimina non visualizzato e non cliccabile").until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//button[contains(@id, 'cancelContact')]"))),
                        ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(".//button[contains(@id, 'cancelContact')]")))));
                logger.info("Si visualizza l'indirizzo pec inserito correttamente");
            }
            if (columns.get(2).getText().contains("-") && columns.get(3).getText().contains("-")) {
                logger.info("Si visualizzano il cellulare e la mail inseriti correttamente");
            }
            break;
        }
    }

    public void checkButtonAnnullaEliminazioneInPopUp() {
        try {
            getWebDriverWait(10).withMessage("pulsante annulla eliminazione non trovato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("buttonAnnulla"))));
            logger.info("pulsante annulla eliminazione visibile");
        } catch (TimeoutException e) {
            Assertions.fail("caricamento pop-up con errore:" + e.getMessage());
        }
    }

    public void clickButtonAnnullaEliminazioneInPopUp() {
       // webTool.waitTime(5);
        getWebDriverWait(10).withMessage("Non è stato possibile cliccare sul bottone annulla").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("buttonAnnulla"))));
        buttonAnnullaEliminazioneInPopUp = driver.findElement(By.id("buttonAnnulla"));
        buttonAnnullaEliminazioneInPopUp.click();
    }

    public void checkEmailPrecedentementeSalvata(String email) {
        webTool.waitTime(10);
        emailAssociata = driver.findElement(By.id("default_email-typography"));
        if (emailAssociata.getText().equalsIgnoreCase(email)) {
            logger.info("la mail associata risulta uguale alla precedente");
        } else {
            logger.error("la mail associata é diversa dalla precedentemente salvata");
            Assertions.fail("la mail associata é diversa dalla precedentemente salvata");
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

        getWebDriverWait(10).withMessage("Non si riesce a cliccare o vedere il bottone annulla").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Annulla')]"))),
                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Annulla')]")))));
        WebElement annullaButtonBy = driver.findElement(By.xpath("//button[contains(text(),'Annulla')]"));
        annullaButtonBy.click();
    }

    public void clickConfermaRecapitoGiaPresente() {

        getWebDriverWait(10).withMessage("Bottone conferma non visualizzato").until(ExpectedConditions.and(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]"))), ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]")))));
        WebElement confermaButton = driver.findElement(By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]"));
        confermaButton.click();
    }

    public void checkNumeroDiCellulareCorretto(String cellulare) {
        getWebDriverWait(10).withMessage("Numero di cellulare appena inserito non visualizzato").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(driver.findElement(By.id("courtesyContacts-phone"))),
                ExpectedConditions.attributeToBe(driver.findElement(By.id("courtesyContacts-phone")), "innerText", "+39" + cellulare)));
    }

    public void clickSuBottoneCellulareDiCortesia(String actionButton) {
        getWebDriverWait(10).withMessage("Il bottone non è cliccabile Su Bottone Cellulare Di Cortesia").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//form[contains(., 'Numero di cellulare')]//button[contains(text(), '" + actionButton + "')]"))));
        WebElement bottoneActionBy = driver.findElement(By.xpath("//form[contains(., 'Numero di cellulare')]//button[contains(text(), '" + actionButton + "')]"));
        bottoneActionBy.click();
    }

    public void clickSuBottoneEmailDiCortesia(String actionButton) {
        WebElement bottoneActionBy = driver.findElement(By.xpath("//button[contains(text(), '" + actionButton + "')]"));
        getWebDriverWait(10).withMessage("Il bottone non è cliccabile Su Bottone Email Di Cortesia").until(ExpectedConditions.visibilityOf(bottoneActionBy));
        bottoneActionBy.click();
    }

    public void clickSuBottoneDisattivaIO() {
        WebElement bottoneActionBy = driver.findElement(By.xpath("//div[@id='ioContactSection']//button[contains(text(), 'Disattiva')]"));
        getWebDriverWait(10).withMessage("Il bottone non è cliccabile").until(ExpectedConditions.visibilityOf(bottoneActionBy));
        bottoneActionBy.click();
    }

    public void checkNumeroDiCellulareNonPresente() {
        try {
            getWebDriverWait(10).withMessage("Input numero di cellulare non visualizzato o non vuoto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("default_sms"))),
                    ExpectedConditions.attributeToBe(driver.findElement(By.id("default_sms")), "value", "")
            ));
        } catch (TimeoutException e) {
            Assertions.fail("Input numero di cellulare non visualizzato o non vuoto con errore: " + e.getMessage());
        }
    }

    public void checkCampoEmailModificabile() {
        try {
            getWebDriverWait(10).withMessage("Campo email non modificabile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email"))));
        } catch (TimeoutException e) {
            Assertions.fail("Campo email non modificabile con errore: " + e.getMessage());
        }
    }

    public void checkDisclaimer() {
        try {
            getWebDriverWait(10).withMessage("Testo del disclaimer non visualizzato").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(text(), 'Se l’impresa non ha una PEC')]"))));
            getWebDriverWait(10).withMessage("Checkbox 'Ho capito' non Visualizzata").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@data-testid='disclaimer-checkbox']"))));
            getWebDriverWait(10).withMessage("Bottone annulla non visualizzato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("cancelButton"))));
            getWebDriverWait(10).withMessage("Bottone conferma non visualizzato o cliccabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("confirmButton"))),
                    ExpectedConditions.attributeToBe(driver.findElement(By.id("confirmButton")), "disabled", "true")));
        } catch (TimeoutException e) {
            Assertions.fail("Qualche componente del pop up non viene visualizzato con errore: " + e.getMessage());
        }
    }

    public void checkMessaggioErroreTreTentativiOTPSbagliato() {
        try {
            WebElement elementErrorAlert = driver.findElement(By.id("error-alert"));
            String testo = elementErrorAlert.getText().replace("\n", " ");

            if (testo.contains("Hai fatto troppi tentativi Hai inserito troppe volte un codice sbagliato. Per riprovare premi “Annulla”, assicurati che il contatto sia corretto e inserisci il nuovo codice.")) {
                logger.info("Si visualizza correttamente il messaggio di errore");
            } else {
                logger.error("Non si visualizza il messaggio di errore");
                Assertions.fail("Non si visualizza il messaggio di errore");
            }
            getWebDriverWait(10).withMessage("Titolo di errore della modale non visualizzato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("codeModalErrorTitle"))));
            getWebDriverWait(10).withMessage("Il messaggio di errore non viene visualizzato e il testo non è corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("error-alert"))),
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("codeModalErrorTitle")))
            ));
        } catch (TimeoutException e) {
            Assertions.fail("Il messaggio di errore non viene visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public void clickDropdownAltriRecapiti(String dropdown) {
        WebElement dropdownBy;
        if (dropdown.equalsIgnoreCase("ente")) {
            dropdownBy = driver.findElement(By.id("sender"));
        } else {
            dropdownBy = driver.findElement(By.id("addressType"));
        }
        getWebDriverWait(10).withMessage("Non è stato possibile cliccare sul dropdown").until(ExpectedConditions.elementToBeClickable(dropdownBy));
        dropdownBy.click();
    }

    public void visualizzaListaEnti(List<String> enti) {
        for (String ente : enti) {
            getWebDriverWait(10).withMessage("Ente: " + ente + " non visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li//p[contains(text(),'" + ente + "')]"))));
        }
    }

    public void visualizzazioneSezioneAltriRecapitiPG(String textboxId) {
        String id = "";
        if (textboxId.equalsIgnoreCase("pec")) {
            id = "s_pec-label";
        }
        if (textboxId.equalsIgnoreCase("email")) {
            id = "s_mail";
        }
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il titolo della sezione altri recapiti").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("specialContactTitle"))));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il sottotitolo della sezione altri recapiti").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(),'Se si desidera che')]"))));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  ente della sezione altri recapiti").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("sender"))));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  tipo di recapito della sezione altri recapiti").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("addressType"))));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  textbox della sezione altri recapiti").until(ExpectedConditions.visibilityOf(driver.findElement(By.id(id))));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il bottone associa della sezione altri recapiti").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("addSpecialButton"))));
    }

    public void selezionaTipoEmail() {
        webTool.waitTime(2);
        tipoIndirizzoField = driver.findElement(By.id("addressType"));
        tipoIndirizzoField.click();

        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo email")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("EMAIL"))));
        WebElement opzioneEmail = driver.findElement(By.id("EMAIL"));
        opzioneEmail.click();
    }

    public void selezionaTipoPec() {
        webTool.waitTime(2);
        tipoIndirizzoField = driver.findElement(By.id("addressType"));
        tipoIndirizzoField.click();
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo PEC")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("PEC"))));
        WebElement opzionePEC = driver.findElement(By.id("PEC"));
        opzionePEC.click();
    }

    public void selezionaTipoCelulare() {
        webTool.waitTime(2);
        tipoIndirizzoField = driver.findElement(By.id("addressType"));
        tipoIndirizzoField.click();


        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione celulare")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("Celulare"))));
        WebElement opzioneCelulare = driver.findElement(By.id("Celulare"));
        opzioneCelulare.click();
    }

    public void checkMessaggioDiErrore(String check) {
        String id = "";
        indirizzoPecField = driver.findElement(By.id("s_value"));
        if (check.equalsIgnoreCase("pec")) {
            id = "s_pec-helper-text";
            Assertions.assertFalse(indirizzoPecField.getAttribute("aria-invalid").equalsIgnoreCase("false"), "la textbox non presenta il bordo rosso");
        }
        emailField = driver.findElement(By.id("s_value"));
        if (check.equalsIgnoreCase("email")) {
            id = "s_mail-helper-text";
            Assertions.assertFalse(emailField.getAttribute("aria-invalid").equalsIgnoreCase("false"), "la textbox non presenta il bordo rosso");
        }

        WebElement errorMessage = driver.findElement(By.id(id));
        getWebDriverWait(5).withMessage("Il messaggio di errore non è visibile").until(ExpectedConditions.visibilityOf(errorMessage));
        Assertions.assertNotNull("lIl bottone Associa è attivo", associaButton.getAttribute("disabled"));
    }

    public void clearMailbox(String check) {
        if (check.equalsIgnoreCase("pec")) {
            indirizzoPecField = driver.findElement(By.id("s_value"));
            js().executeScript("arguments[0].setAttribute('autocomplete', 'off')", indirizzoPecField);
            indirizzoPecField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }
        if (check.equalsIgnoreCase("email")) {
            emailField = driver.findElement(By.id("s_value"));
            js().executeScript("arguments[0].setAttribute('autocomplete', 'off')", emailField);
            emailField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }

    }

    public void clickConfermaPopupOTP() {
        getWebDriverWait(5).withMessage("il bottone Confirm non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("code-confirm-button"))));
        WebElement confirmOtpPopup = driver.findElement(By.id("code-confirm-button"));
        confirmOtpPopup.click();
    }

    public void clickAnnullaPopupOTP() {
        getWebDriverWait(5).withMessage("il bottone Confirm non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("code-cancel-button"))));
        WebElement cancelOtpPopup = driver.findElement(By.id("code-cancel-button"));
        cancelOtpPopup.click();
    }

    public boolean waitErrorMessagePopupOTP() {
        try {
            getWebDriverWait(5).withMessage("Il messaggio di errore inserimento OTP non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("codeModalErrorTitle"))));
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
        WebElement loFaroPiuTardi = getWebDriverWait(25).withMessage("Impossibile Cliccare su Lo faro piu tardi o su Conferma Modifica Recapito")
                .until(ExpectedConditions.elementToBeClickable(
                By.id("dialog-confirm-button")));
        loFaroPiuTardi.click();
    }

    public void clickTornaAiTuoiRecapiti() {
        WebElement tornaAiTuoiRecapiti = getWebDriverWait(15).withMessage("Impossibile Cliccare su Vai ai tuoi recapiti")
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
//                By.xpath("//div[@data-testid='legalContacts']//div[contains(@class, 'MuiCardHeader-root')]//following-sibling::div//button[contains(text(), 'Gestisci')]")
        ));

        verificaPresenza("Impossibile trovare Disattiva ", ExpectedConditions.elementToBeClickable(
                By.cssSelector("button svg[data-testid='PowerSettingsNewIcon']")
//                By.xpath("//h6[contains(text(), 'domicilio digitale')]/ancestor::div[contains(@class, 'MuiCardHeader-root')]//following-sibling::div//button[contains(text(), 'Disattiva')]")
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

    public void verificaDaAttivareIO () {
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
        }
        catch (TimeoutException e) {
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
        }
        catch (TimeoutException e) {
            Assertions.assertTrue(true, "Il banner di email mancante non è presente");
        }
    }

    public void checkBannerPECInValidazione(String ente) {
        verificaPresenza("Il banner di PEC in validazione non è presente", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-testid='PecVerificationAlert']")));
        if (ente.isEmpty()) {
            verificaPresenza("Il banner di PEC in validazione non ha il testo corretto", ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@data-testid='PecVerificationAlert']//..//p[contains(text(),'Fino al termine del processo non sarà possibile modificare i recapiti a valore legale.')]")));
        }
        else {
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

        WebElement pecInput =  getWebDriverWait(5)
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
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//li[.='"+tipologia+"']"))
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

        } catch (TimeoutException  e) {
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
//                js().executeScript("arguments[0].scrollIntoView(true);", disattivaButton);
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


    public void verificaEDisattivaDomicilioDigitale() {
        try {
            WebElement disattivaButton = getWebDriverWait(10).withMessage("Non è presente dentro Domicilio Digitale il testo 'Disattiva'")
                    .until(ExpectedConditions.elementToBeClickable
                            (By.xpath("//*[@data-testid='legalContacts']//button[.//*[@data-testid='PowerSettingsNewIcon']]")));
            if (disattivaButton.isDisplayed() && disattivaButton.isEnabled()) {
                disattivaButton.click();
                clickSuConfermaElimina();
            } else {
                logger.warn("Bottone 'Disattiva Domicilio Digitale' trovato ma non è visibile o abilitato.");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("Bottone 'Disattiva Domicilio Digitale' non presente.");
        } catch (Exception e) {
            Assertions.fail("Errore inaspettato durante la ricerca o il click sul bottone 'Disattiva Domicilio Digitale'.", e);
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

//    public void verificaEDisattivaEmail1() {
//        try {
//            WebElement disattivaButton = getWebDriverWait(15).withMessage("Non è presente dentro Email 'Disattiva'")
//                    .until(ExpectedConditions.elementToBeClickable
//                            (By.cssSelector("button[data-testid='disable-email']")));
//            if (disattivaButton.isDisplayed() && disattivaButton.isEnabled()) {
//                disattivaButton.click();
//                clickSuConfermaElimina();
//            } else {
//                logger.warn("Bottone 'Disattiva Email' trovato ma non è visibile o abilitato.");
//            }
//        } catch (NoSuchElementException | TimeoutException e) {
//            logger.info("Bottone 'Disattiva Email' non presente.");
//        } catch (Exception e) {
//            Assertions.fail("Errore inaspettato durante la ricerca o il click sul bottone 'Disattiva Email'.", e);
//        }
//    }
public void verificaEDisattivaEmail() {
    try {
        WebElement disattivaButton = getWebDriverWait(15)
                .withMessage("Il bottone 'Disattiva Email' non è presente entro il tempo limite.")
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-testid='disable-email']")));

        getWebDriverWait(10).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".MuiAlert-root")));

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

    public void verificaEDisattivaCellulare() {
        // TODO DA VERICARE
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
                By.cssSelector("button.css-y0rh4q")));
        esciButton.click();
    }



    public void selezionaLaNotificaAvvenutoAccesso() {
        // Attendi che tutte le righe della tabella siano visibili e ottienile
        List<WebElement> rows = getWebDriverWait(10)
                .withMessage("Impossibile trovare la tabella delle Notifiche")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("tr[data-testid='notificationsTable.body.row']")));

        for (WebElement row : rows) {
            // Attendi che lo stato della notifica nella riga corrente sia visibile
            WebElement statusChip = getWebDriverWait(10)
                    .withMessage("Impossibile trovare lo colonna STATO")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid^='statusChip-']")));
            String status = statusChip.getAttribute("aria-label");

            // Controlla se lo stato è "Avvenuto accesso"
            if (status.contains("Avvenuto accesso")) {
                // Attendi che il pulsante "Vedi dettaglio" nella riga corrente sia cliccabile
                WebElement detailButton = getWebDriverWait(10)
                        .withMessage("Impossibile Trovare il tasto Vedi Dettaglio con stato Avvenuto accesso")
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='goToNotificationDetail']")));
                // Scorri l'elemento nella vista (se necessario)
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", detailButton);
                // Attendi che l'elemento sia cliccabile
                detailButton = getWebDriverWait(10)
                        .withMessage("Il pulsante 'Vedi Dettaglio' non è cliccabile")
                        .until(ExpectedConditions.elementToBeClickable(detailButton));

                // Forza il clic utilizzando JavascriptExecutor (se necessario)
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", detailButton);


            }
        }
    }

    public String getEmailInvalidMessage() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email-helper-text"))));
        WebElement errorMessage = driver.findElement(By.id("default_email-helper-text"));
        return errorMessage.getText();

    }
}