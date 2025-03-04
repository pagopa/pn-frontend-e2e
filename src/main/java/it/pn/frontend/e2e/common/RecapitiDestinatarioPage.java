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
        //By chiudiButtonBy = By.xpath("//button[contains(text(),'Chiudi')]");
        logger.info("Log clickSuChiudiPopUp");
        getWebDriverWait(10).withMessage("Il bottone chiudi non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//h2[@id='dialog-title']/following-sibling::div/button[contains(text(),'Annulla')]"))));
        WebElement chiudiButtonBy = driver.findElement(By.xpath("//h2[@id='dialog-title']/following-sibling::div/button[contains(text(),'Annulla')]"));
        js().executeScript("arguments[0].click()", chiudiButtonBy);
    }

    public void insertEmailPEC(String emailPEC) {
        getWebDriverWait(30).withMessage("input pec field non trovato").until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.id("default_pec"))));
        WebElement pecField = element(By.id("default_pec"));
        pecField.sendKeys(emailPEC);
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

    public void waitLoadPopUp() {
        try {
            //WebElement titleBy = driver.findElement(By.id("dialog-title"));
            //WebElement descriptionBy = driver.findElement(By.id("dialog-description"));
            // WebElement titleOption = driver.findElement(By.xpath("//div[@data-testid='dialog-content']//p[contains(text(), 'Inserisci codice')]"));

            // The message is different in PG and PF
           // WebElement footerNotReceived = driver.findElement(By.xpath("//div[contains(text(), 'Non l’hai ricevuto?')]"));
            getWebDriverWait(20).withMessage("Non viene visualizzato correttamente il titolo").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-title"))));
            getWebDriverWait(20).withMessage("La descrizione non viene visualizzata e il testo non è corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-description"))),
                    ExpectedConditions.attributeContains(driver.findElement(By.id("dialog-description")), "textContent", "Il codice è valido per 15 minuti.")));
            getWebDriverWait(20).withMessage("La scritta 'Inserisci codice' non viene visualizzata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='dialog-content']//p[contains(text(), 'Inserisci codice')]"))));
            getWebDriverWait(20).withMessage("Le input boxes non vengono visualizzate").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//input[contains(@id,'code-input-')]"))));
            List<WebElement> inputBoxes = driver.findElements(By.xpath("//input[contains(@id,'code-input-')]"));
            if (inputBoxes.size() != 5) {
                Assertions.fail("Il numero di input box non è corretto");
            }
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//div[contains(text(), 'Non l’hai ricevuto?')]"))));
            getWebDriverWait(10).withMessage("Non viene visualizzato correttamente il titolo").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-title"))));
            confermaButtonPopUp = driver.findElement(By.id("code-confirm-button"));
            annullaButton = driver.findElement(By.id("code-cancel-button"));
            boolean checkButton = !confermaButtonPopUp.isEnabled() && annullaButton.isEnabled();
            if (!checkButton) {
                Assertions.fail("i pulsanti all'interno del pop-up non rispettano le condizioni");
            }
            logger.info("Il pop-up di conferma viene visualizzato correttamente");
        } catch (TimeoutException e) {
            logger.error("Il pop-up di conferma NON viene visualizzato correttamente con errori: " + e.getMessage());
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
            logger.error("Il codice otp NON viene inserito correttamente con errore:" + e.getMessage());
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
            getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("code-cancel-button"))));
            annullaButton = driver.findElement(By.id("code-cancel-button"));
            annullaButton.click();
        } catch (TimeoutException e) {
            logger.error("Il bottone annulla non è cliccabile con errore: " + e.getMessage());
            Assertions.fail("Il bottone annulla non è cliccabile con errore: " + e.getMessage());
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
       // inserimentoMailField = driver.findElement(By.id("default_email"));
        getWebDriverWait(10).withMessage("l'input mail non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email"))));
        inserimentoMailField = driver.findElement(By.id("default_email"));

        if (!inserimentoMailField.getAttribute("value").isEmpty()) {
            inserimentoMailField.clear();
        }
        if (inserimentoMailField.isDisplayed()) {
            inserimentoMailField.sendKeys(email);
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", inserimentoMailField);
            inserimentoMailField.sendKeys(email);
        }
    }

    public void insertPhone(String cellulare) {

        getWebDriverWait(30).withMessage("l'input numero telefono non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_sms"))));
        inserimentoPhoneField = driver.findElement(By.id("default_sms"));
        if (inserimentoPhoneField.isDisplayed()) {
            inserimentoPhoneField.sendKeys(cellulare);
        } else {
            js().executeScript("arguments[0].scrollIntoView(true);", inserimentoPhoneField);
            inserimentoPhoneField.sendKeys(cellulare);
        }
    }

    public boolean verificaPecAssociata() {
        try {
            getWebDriverWait(40).withMessage("PEC associata non presente").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_pec-typography"))));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("pec associata non trovata" + e.getMessage());
            return false;
        }
    }

    public boolean siVisualizzaPopUpConferma() {
        try {
            //WebElement popUpConfermaTitleBy = driver.findElement(By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]"))));
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
           // WebElement pecAssociata = driver.findElement(By.xpath("//p[contains(text(), 'Validazione PEC in corso')]"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(), 'Validazione PEC in corso')]"))));
        } catch (TimeoutException e) {
            logger.error("Pec non associata con errore:" + e.getMessage());
            Assertions.fail("Pec non associata con errore:" + e.getMessage());
        }
    }

    public boolean verificaMailAssociata() {
        try {
            getWebDriverWait(30).withMessage("L'email di cortesia non è presente").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email-typography"))));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("Nessuna email di cortesia impostata");
            return false;
        }
    }

    public boolean verificaNumeroDiCellulareAssociato() {
        try {
           // cellulareAssociato = driver.findElement(By.id("courtesyContacts-phone"));
            getWebDriverWait(15).withMessage("Il numero di cellulare associato non è presente").until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("courtesyContacts-phone")));
            return true;
        } catch (TimeoutException e) {
            logger.info("Nessun numero di cellulare di cortesia impostato");
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
//            WebElement pecInseritaBy = driver.findElement(By.xpath("//p[contains(text(),'PEC associata')]"));
//            getWebDriverWait(5).until(ExpectedConditions.visibilityOf(pecInseritaBy));
            getWebDriverWait(5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'PEC associata')]")));
            return true;
        } catch (TimeoutException e) {
            logger.error("Pec inserita non presente con errore:" + e.getMessage());
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
            getWebDriverWait(10).withMessage("Non si riesce a cliccare o vedere il bottone modifica PEC").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("modifyContact-default_pec"))),
                    ExpectedConditions.elementToBeClickable(driver.findElement(By.id("modifyContact-default_pec")))));
            WebElement modificaButtonBy = driver.findElement(By.id("modifyContact-default_pec"));
            modificaButtonBy.click();
        } catch (TimeoutException e) {
            logger.error("Non si riesce a cliccare o vedere il bottone modifica PEC con errore:" + e.getMessage());
            Assertions.fail("Non si riesce a cliccare o vedere il bottone modifica PEC con errore:" + e.getMessage());
        }
    }

    public void cancellaTesto() {
        try {
            webTool.waitTime(5);
           // WebElement pecField = element(By.id("default_pec"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_pec"))));
            WebElement pecField = driver.findElement(By.id("default_pec"));
            js().executeScript("arguments[0].click()", pecField);
            clearWebElementField(pecField);
            logger.info("testo email pec cancellata");
        } catch (TimeoutException e) {
            logger.error("Non si riesce a cancellare il testo della  email PEC :" + e.getMessage());
            Assertions.fail("Non si riesce a cancellare il testo della  email PEC :" + e.getMessage());
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

      //  WebElement subTitlePopUp = driver.findElement(By.id("dialog-description"));
//        WebElement bottoneAnnulaPopup = driver.findElement(By.id("code-cancel-button"));
        //WebElement confermaEliminaButtonBy = driver.findElement(By.xpath("//div[@aria-labelledby='dialog-title']//button[contains(text(),'Conferma')]"));
        try {
           // buttonAnnullaEliminazioneInPopUp = driver.findElement(By.id("buttonAnnulla"));
            getWebDriverWait(10).withMessage("Non è stato caricato il titolo del modal").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-title"))));
            getWebDriverWait(10).withMessage("Non è stato caricato il sottotitolo del modal").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-description"))));
            getWebDriverWait(10).withMessage("Non è stato caricato il bottone annulla del modal").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("buttonAnnulla"))));
            getWebDriverWait(10).withMessage("Non è stato caricato il bottone conferma del modal").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@aria-labelledby='dialog-title']//button[contains(text(),'Conferma')]"))));
        } catch (TimeoutException e) {
            logger.info("Non è stato caricato un elemento del pop up con errore: " + e.getMessage());
        }
        WebElement titlePopUp = driver.findElement(By.id("dialog-title"));
        return titlePopUp.getText();
    }

    public void clickSuConfermaElimina() {
        getWebDriverWait(10).withMessage("Non è stato possibile cliccare sul bottone conferma").until(ExpectedConditions.elementToBeClickable( driver.findElement(By.id("buttonConferma"))));
        WebElement confermaRimuoviPECBy = driver.findElement(By.id("buttonConferma"));
        confermaRimuoviPECBy.click();
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
      //  WebElement pecAssociataBy = driver.findElement(By.xpath("//form[@data-testid='a95dace4-4a47-4149-a814-0e669113ce40_emailContact']"));
        getWebDriverWait(10).withMessage("La mail non è stata aggiunta correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//form[@data-testid='a95dace4-4a47-4149-a814-0e669113ce40_emailContact']"))));
    }

    public boolean controlloEmailAssociata(String email) {
        try {
           // WebElement emailBy = driver.findElement(By.xpath("//div[@data-testid = 'courtesyContacts']//div//p[contains(text(),'" + email + "')]"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid = 'courtesyContacts']//div//p[contains(text(),'" + email + "')]"))));
            return true;
        } catch (TimeoutException e) {
            logger.error("email associata non presente con errore" + e.getMessage());
            return false;
        }
    }

    public boolean verificaNuovaEmailEPEC(String nuovaEmail) {
        try {
           // WebElement emailBy = driver.findElement(By.xpath(" //div[@data-testid = 'DigitalContactsCardBody']//p[contains(text(),'" + nuovaEmail + "')]"));
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

        getWebDriverWait(10).withMessage("Il bottone conferma del pop-up elimina non cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("buttonConferma"))));
        WebElement confermaEliminaButtonBy = driver.findElement(By.id("buttonConferma"));
        confermaEliminaButtonBy.click();
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

    public boolean verificaBottoneConfermaDisabilitato() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_pec-button"))));
            attivaButton = driver.findElement(By.id("default_pec-button"));
            return Boolean.parseBoolean(attivaButton.getAttribute("disabled"));
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("bottone non disabilitato " + e.getMessage());
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
       // WebElement altriRecapitiSectionBy = driver.findElement(By.id("courtesyContactsTitle"));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il titolo della sezione altri recapiti").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("courtesyContactsTitle"))));
    }

    public void visualizzazioneCampiSezioneAltriRecapiti() {
        vaiInFondoAllaPagina();
       // WebElement altriRecapitiSectionBy = driver.findElement(By.xpath("//h5[contains(@id, 'specialContact')]"));
       // WebElement titleGiaAssociatiBy = driver.findElement(By.xpath("//p[contains(text(), 'Già associati')]"));
      //  WebElement tableGiaAssociati = driver.findElement(By.xpath("//*[@id='root']/div[1]/div/main/div/div[2]/div[2]/div/div/table"));
        //WebElement tableGiaAssociati = driver.findElement(By.xpath("//table[@aria-label='Già associati']"));
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
              //  WebElement modifyButtonBy = driver.findElement(By.xpath(".//button[contains(@id, 'modifyContact')]"));
               // WebElement deleteButtonBy = driver.findElement(By.xpath(".//button[contains(@id, 'cancelContact')]"));
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
            //webTool.waitTime(3);
           // buttonAnnullaEliminazioneInPopUp = driver.findElement(By.id("buttonAnnulla"));
            getWebDriverWait(10).withMessage("pulsante annulla eliminazione non trovato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("buttonAnnulla"))));
            logger.info("pulsante annulla eliminazione visibile");
        } catch (TimeoutException e) {
            logger.error("caricamento pop-up con errore:" + e.getMessage());
            Assertions.fail("caricamento pop-up con errore:" + e.getMessage());
        }
    }

    public void clickButtonAnnullaEliminazioneInPopUp() {
       // webTool.waitTime(5);
      //
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
            logger.error("pec non modificabile con errore:" + e.getMessage());
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
            logger.error("pec modificabile con errore:" + e.getMessage());
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
        //cellulareAssociato = driver.findElement(By.id("courtesyContacts-phone"));
        getWebDriverWait(10).withMessage("Numero di cellulare appena inserito non visualizzato").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(driver.findElement(By.id("courtesyContacts-phone"))),
                ExpectedConditions.attributeToBe(driver.findElement(By.id("courtesyContacts-phone")), "innerText", "+39" + cellulare)));
    }

    public void clickSuBottoneCellulareDiCortesia(String actionButton) {
        getWebDriverWait(10).withMessage("Il bottone non è cliccabile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//form[contains(., 'Numero di cellulare')]//button[contains(text(), '" + actionButton + "')]"))));
        WebElement bottoneActionBy = driver.findElement(By.xpath("//form[contains(., 'Numero di cellulare')]//button[contains(text(), '" + actionButton + "')]"));
        bottoneActionBy.click();
    }

    public void clickSuBottoneEmailDiCortesia(String actionButton) {
        WebElement bottoneActionBy = driver.findElement(By.xpath("//button[contains(text(), '" + actionButton + "')]"));
        getWebDriverWait(10).withMessage("Il bottone non è cliccabile").until(ExpectedConditions.visibilityOf(bottoneActionBy));
        bottoneActionBy.click();
    }

    public void checkNumeroDiCellulareNonPresente() {
        try {
            //inserimentoPhoneField = driver.findElement(By.id("default_sms"));
            getWebDriverWait(10).withMessage("Input numero di cellulare non visualizzato o non vuoto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("default_sms"))),
                    ExpectedConditions.attributeToBe(driver.findElement(By.id("default_sms")), "value", "")
            ));
        } catch (TimeoutException e) {
            logger.error("Input numero di cellulare non visualizzato o non vuoto con errore: " + e.getMessage());
            Assertions.fail("Input numero di cellulare non visualizzato o non vuoto con errore: " + e.getMessage());
        }
    }

    public void checkCampoEmailModificabile() {
        try {
          //  inserimentoMailField = driver.findElement(By.id("default_email"));
            getWebDriverWait(10).withMessage("Campo email non modificabile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_email"))));
        } catch (TimeoutException e) {
            logger.error("Campo email non modificabile con errore: " + e.getMessage());
            Assertions.fail("Campo email non modificabile con errore: " + e.getMessage());
        }
    }

    public void checkDisclaimer() {
       //WebElement textDisclaimerBy = driver.findElement(By.xpath("//div[contains(text(), 'Se l’impresa non ha una PEC')]"));
        // WebElement hoCapitoCheckboxBy = driver.findElement(By.xpath("//span[@data-testid='disclaimer-checkbox']"));
        // WebElement annullaButtonBy = driver.findElement(By.id("cancelButton"));
        // WebElement confermaButtonBy = driver.findElement(By.id("confirmButton"));
        try {
            getWebDriverWait(10).withMessage("Testo del disclaimer non visualizzato").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(text(), 'Se l’impresa non ha una PEC')]"))));
            getWebDriverWait(10).withMessage("Checkbox 'Ho capito' non Visualizzata").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@data-testid='disclaimer-checkbox']"))));
            getWebDriverWait(10).withMessage("Bottone annulla non visualizzato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("cancelButton"))));
            getWebDriverWait(10).withMessage("Bottone conferma non visualizzato o cliccabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("confirmButton"))),
                    ExpectedConditions.attributeToBe(driver.findElement(By.id("confirmButton")), "disabled", "true")));
        } catch (TimeoutException e) {
            logger.error("Qualche componente del pop up non viene visualizzato con errore: " + e.getMessage());
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
           // WebElement messageContainer = driver.findElement(By.id("error-alert"));
           // WebElement modalErrorTitle = driver.findElement(By.id("codeModalErrorTitle"));
            getWebDriverWait(10).withMessage("Titolo di errore della modale non visualizzato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("codeModalErrorTitle"))));
            getWebDriverWait(10).withMessage("Il messaggio di errore non viene visualizzato e il testo non è corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("error-alert"))),
                    ExpectedConditions.visibilityOf(driver.findElement(By.id("codeModalErrorTitle")))
            ));
        } catch (TimeoutException e) {
            logger.error("Il messaggio di errore non viene visualizzato correttamente con errore: " + e.getMessage());
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
           // WebElement enteRadice = driver.findElement(By.xpath("//li//p[contains(text(),'" + ente + "')]"));
            getWebDriverWait(10).withMessage("Ente: " + ente + " non visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li//p[contains(text(),'" + ente + "')]"))));
        }
    }

    public void visualizzazioneSezioneAltriRecapitiPG(String textboxId) {
        String id = "";
        // WebElement altriRecapitiSectionBy = driver.findElement(By.id("specialContactTitle"));
        //WebElement sottoTitolo = driver.findElement(By.xpath("//p[contains(text(),'Se si desidera che')]"));
        // WebElement ente = driver.findElement(By.id("sender"));
        // WebElement tipoDiRecapito = driver.findElement(By.id("addressType"));
        if (textboxId.equalsIgnoreCase("pec")) {
            id = "s_pec-label";
        }
        if (textboxId.equalsIgnoreCase("email")) {
            id = "s_mail";
        }
       // WebElement textBox = driver.findElement(By.id(id));
        //  WebElement associaButton = driver.findElement(By.id("addSpecialButton"));
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
            WebElement errorMessageOtp = driver.findElement(By.id("codeModalErrorTitle"));
            logger.info("Il messaggio di errore viene visualizzato correttamente");
            return true;
        } catch (TimeoutException e) {
            logger.info("Il messaggio di errore non viene visualizzato");

            return false;
        }
    }

    public void verificaAndOrDisattiva(String testo) {
        try {
            WebElement disattivaButton = getWebDriverWait(5).withMessage("Non è presente dentro '" + testo + "' il testo 'Disattiva'")
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//h6[contains(text(), '" + testo + "')]/ancestor::div[contains(@class, 'MuiCardHeader-root')]//following-sibling::div//button[contains(text(), 'Disattiva')]")));
            if (disattivaButton.isDisplayed() && disattivaButton.isEnabled()) {
                logger.info("Bottone 'Disattiva' trovato, lo clicco!");
                disattivaButton.click();
                clickSuConfermaElimina(); // Funzione che esegue un altro click o conferma
            } else {
                logger.warn("Bottone 'Disattiva' trovato ma non è visibile o abilitato.");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("Bottone 'Disattiva' non presente.");
        } catch (Exception e) {
            logger.error("Errore inaspettato durante la ricerca o il click sul bottone 'Disattiva'.", e);
        }


    }

    public void clickInizia() {
        WebElement button = getWebDriverWait(5).withMessage("Impossibile Cliccare su Inizia").
                until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Inizia')]")
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
        WebElement nonOraButton = getWebDriverWait(5).withMessage("Impossibile Cliccare su Non Ora")
                .until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Non ora')]")));
        nonOraButton.click();
    }

    public void clickLoFaroPiuTardi() {
        WebElement loFaroPiuTardi = getWebDriverWait(5).withMessage("Impossibile Cliccare su Lo faro piu tardi")
                .until(ExpectedConditions.elementToBeClickable(
                By.id("dialog-confirm-button")));
        loFaroPiuTardi.click();
    }

    public void clickTornaAiTuoiRecapiti() {
        WebElement tornaAiTuoiRecapiti = getWebDriverWait(5).withMessage("Impossibile Cliccare su Torna ai tuoi recapiti")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='wizard-feedback-button']")));
        tornaAiTuoiRecapiti.click();
    }

    public void verificaAttivazioneDomicilioDigitaleDellaTuaImpresa() {
        verificaPresenzaInDomicilioDigitale("Impossibile trovare Il domicilio digitale della tua impresa ", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[contains(text(), 'domicilio digitale')]")
        ));

        verificaPresenzaInDomicilioDigitale("Impossibile trovare Attivo ", ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[contains(text(), 'domicilio digitale')]//following::span[contains(text(), 'Attivo')]")
        ));

        verificaPresenzaInDomicilioDigitale("Impossibile trovare Gestisci ", ExpectedConditions.elementToBeClickable(
                By.xpath("//h6[contains(text(), 'domicilio digitale')]/ancestor::div[contains(@class, 'MuiCardHeader-root')]//following-sibling::div//button[contains(text(), 'Gestisci')]")
        ));

        verificaPresenzaInDomicilioDigitale("Impossibile trovare Disattiva ", ExpectedConditions.elementToBeClickable(
                By.xpath("//h6[contains(text(), 'domicilio digitale')]/ancestor::div[contains(@class, 'MuiCardHeader-root')]//following-sibling::div//button[contains(text(), 'Disattiva')]")
        ));
    }

    private void verificaPresenzaInDomicilioDigitale(String message, ExpectedCondition<WebElement> isTrue) {
        WebElement titolo = getWebDriverWait(15).withMessage(message)
                .until(isTrue);
        Assertions.assertNotNull(titolo);
    }

    public void clickSuBottoneCollegaSENDaIO() {
        getWebDriverWait(10).withMessage("Il bottone non è cliccabile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div//..//button[contains(text(), 'app IO')]"))));
        WebElement bottoneActionBy = driver.findElement(By.xpath("//div//..//button[contains(text(), 'app IO')]"));
        bottoneActionBy.click();
    }
}