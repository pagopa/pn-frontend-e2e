package it.pn.frontend.e2e.common;

import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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
        webTool.waitTime(5);
        WebElement chiudiButtonBy = driver.findElement(By.xpath("//h2[@id='dialog-title']/following-sibling::div/button[contains(text(),'Annulla')]"));
        getWebDriverWait(10).withMessage("Il bottone chiudi non è cliccabile").until(ExpectedConditions.elementToBeClickable(chiudiButtonBy));
        this.js().executeScript("arguments[0].click()", chiudiButtonBy);
    }

    public void insertEmailPEC(String emailPEC) {
        webTool.waitTime(5);
        pecField = driver.findElement(By.id("default_pec"));
        getWebDriverWait(10).withMessage("input pec field non trovato").until(ExpectedConditions.visibilityOf(pecField));
        pecField.sendKeys(emailPEC);
    }

    public void confermaButtonClick() {
        webTool.waitTime(5);
        WebElement attivaButton = driver.findElement(By.id("default_pec-button"));
        getWebDriverWait(10).withMessage("Il bottone Attiva non è cliccabile").until(ExpectedConditions.elementToBeClickable(attivaButton));
        attivaButton.click();
    }

    public void clickConfermaButtonEliminaPopUp() {
        WebElement confermaEliminaButton = driver.findElement(By.xpath("//h2[@id='dialog-title']/following-sibling::div/button[contains(text(),'Conferma')]"));
        getWebDriverWait(10).withMessage("Il bottone conferma non è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaEliminaButton));
        confermaEliminaButton.click();
        logger.info("clickConfermaButtonEliminaPopUp");
    }

    public void waitLoadPopUp() {
        try {
            WebElement titleBy = driver.findElement(By.id("dialog-title"));
            WebElement descriptionBy = driver.findElement(By.id("dialog-description"));
            WebElement titleOption = driver.findElement(By.xpath("//div[@data-testid='dialog-content']//p[contains(text(), 'Inserisci codice')]"));
            List<WebElement> inputBoxes = driver.findElements(By.xpath("//input[contains(@id,'code-input-')]"));
            // The message is different in PG and PF
            WebElement footerNotReceived = driver.findElement(By.xpath("//div[contains(text(), 'Non l’hai ricevuto?')]"));
            getWebDriverWait(10).withMessage("Non viene visualizzato correttamente il titolo").until(ExpectedConditions.visibilityOf(titleBy));
            getWebDriverWait(10).withMessage("La descrizione non viene visualizzata e il testo non è corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(descriptionBy),
                    ExpectedConditions.attributeContains(descriptionBy, "textContent", "Il codice è valido per 15 minuti.")));
            getWebDriverWait(10).withMessage("La scritta 'Inserisci codice' non viene visualizzata correttamente").until(ExpectedConditions.visibilityOf(titleOption));
            getWebDriverWait(10).withMessage("Le input boxes non vengono visualizzate").until(ExpectedConditions.visibilityOfAllElements(inputBoxes));
            if (inputBoxes.size() != 5) {
                Assertions.fail("Il numero di input box non è corretto");
            }
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(footerNotReceived));
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
            List<WebElement> otpInputby = driver.findElements(By.xpath("//input[contains(@id,'code-input')]"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(otpInputby));
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
        List<WebElement> otpInputby = driver.findElements(By.xpath("//input[contains(@id,'code-input')]"));
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(otpInputby));
        for (int i = 4; i >= 0; i--) {
            otpInputby.get(i).sendKeys(Keys.BACK_SPACE);
        }
        logger.info("Il codice otp viene cancellato correttamente");

    }

    public void confermaButtonClickPopUp() {
        confermaButtonPopUp = driver.findElement(By.id("code-confirm-button"));
        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(confermaButtonPopUp));
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
            webTool.waitTime(5);
            getWebDriverWait(5).until(ExpectedConditions.visibilityOfElementLocated(By.id("error-alert")));
            logger.info("Il messaggio di errore viene visualizzato correttamente");
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void annullaButtonClick() {
        try {
            annullaButton = driver.findElement(By.id("code-cancel-button"));
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(annullaButton));
            annullaButton.click();
        } catch (TimeoutException e) {
            logger.error("Il bottone annulla non è cliccabile con errore: " + e.getMessage());
            Assertions.fail("Il bottone annulla non è cliccabile con errore: " + e.getMessage());
        }
    }

    public void clickAvvisami() {
        avvisamiMailButton = driver.findElement(By.xpath("//button[@data-testid='add email']"));
        getWebDriverWait(30).withMessage("Il bottone avvisami della mail non è cliccabile").until(ExpectedConditions.elementToBeClickable(avvisamiMailButton));
        avvisamiMailButton.click();
    }

    public void clickAvvisamiSMS() {
        avvisamiSMSButton = driver.findElement(By.id("default_sms-button"));
        getWebDriverWait(30).withMessage("Il bottone avvisami del sms non è cliccabile").until(ExpectedConditions.elementToBeClickable(avvisamiSMSButton));
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
        inserimentoPhoneField = driver.findElement(By.id("default_sms"));
        getWebDriverWait(30).withMessage("l'input numero telefono non è visibile").until(ExpectedConditions.visibilityOf(inserimentoPhoneField));
        if (inserimentoPhoneField.isDisplayed()) {
            inserimentoPhoneField.sendKeys(cellulare);
        } else {
            js().executeScript("arguments[0].scrollIntoView(true);", inserimentoPhoneField);
            inserimentoPhoneField.sendKeys(cellulare);
        }
    }

    public boolean verificaPecAssociata() {
        try {

            getWebDriverWait(10).withMessage("PEC associata non presente").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("default_pec-typography"))));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("pec associata non trovata" + e.getMessage());
            return false;
        }
    }

    public boolean siVisualizzaPopUpConferma() {
        try {
            WebElement popUpConfermaTitleBy = driver.findElement(By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(popUpConfermaTitleBy));
            return true;
        } catch (TimeoutException e) {
            logger.error("pop up conferma non trovato: \n" + e.getMessage());
            return false;
        }
    }

    public void clickConfermaButton() {
        confermaButtonPoPUpPec = driver.findElement(By.xpath("//button[@id='confirmDialog']"));
        getWebDriverWait(10).withMessage("Il bottone conferma del pop up non é cliccabile").until(ExpectedConditions.elementToBeClickable(confermaButtonPoPUpPec));
        this.confermaButtonPoPUpPec.click();
    }

    public void visualizzaValidazione() {
        try {
            WebElement pecAssociata = driver.findElement(By.xpath("//p[contains(text(), 'Validazione PEC in corso')]"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(pecAssociata));
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
        modificaEmailButton = driver.findElement(By.id("modifyContact-default_email"));
        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(modificaEmailButton));
        if (modificaEmailButton.isDisplayed()) {
            this.modificaEmailButton.click();
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", modificaEmailButton);
            this.modificaEmailButton.click();
        }
    }

    public void clickSuModificaPEC() {
        try {
            webTool.waitTime(10);
            WebElement modificaButtonBy = driver.findElement(By.id("modifyContact-default_pec"));
            getWebDriverWait(10).withMessage("Non si riesce a cliccare o vedere il bottone modifica PEC").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(modificaButtonBy),
                    ExpectedConditions.elementToBeClickable(modificaButtonBy)));
            modificaButtonBy.click();
        } catch (TimeoutException e) {
            logger.error("Non si riesce a cliccare o vedere il bottone modifica PEC con errore:" + e.getMessage());
            Assertions.fail("Non si riesce a cliccare o vedere il bottone modifica PEC con errore:" + e.getMessage());
        }
    }

    public void cancellaTesto() {
        try {
            pecField = driver.findElement(By.id("default_pec"));
            js().executeScript("arguments[0].click()", pecField);
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(pecField));
            clearWebElementField(pecField);
            logger.info("testo email pec cancellata");
        } catch (TimeoutException e) {
            logger.error("Non si riesce a cancellare il testo della  email PEC :" + e.getMessage());
            Assertions.fail("Non si riesce a cancellare il testo della  email PEC :" + e.getMessage());
        }
    }

    public void clickSuSalva() {
        WebElement salvaButtonBy = driver.findElement(By.xpath("//button[contains(text(),'Salva')]"));
        getWebDriverWait(30).withMessage("Non si riesce a cliccare sul bottone salva").until(ExpectedConditions.elementToBeClickable(salvaButtonBy));
        salvaButtonBy.click();
    }

    public boolean siControllaPECModificata(String pecInserita) {
        WebElement pecBy = driver.findElement(By.id("default_pec-typography"));
        getWebDriverWait(10).withMessage("Non trovata nessuna email PEC inserita").until(ExpectedConditions.visibilityOf(pecBy));
        return pecBy.getText().equals(pecInserita);
    }


    public void clickSuEliminaPec() {
        eliminaPECButton = driver.findElement(By.id("cancelContact-default_pec"));
        getWebDriverWait(10).withMessage("Il bottone elimina della PEC associata non è cliccabile").until(ExpectedConditions.elementToBeClickable(eliminaPECButton));
        logger.info("click sul pulsante elimina pec");
        eliminaPECButton.click();
    }

    public String waitLoadPopUpElimina() {
        WebElement titlePopUp = driver.findElement(By.id("dialog-title"));
        WebElement subTitlePopUp = driver.findElement(By.id("dialog-description"));
//        WebElement bottoneAnnulaPopup = driver.findElement(By.id("code-cancel-button"));
        WebElement confermaEliminaButtonBy = driver.findElement(By.xpath("//div[@aria-labelledby='dialog-title']//button[contains(text(),'Conferma')]"));
        try {
            buttonAnnullaEliminazioneInPopUp = driver.findElement(By.id("buttonAnnulla"));
            getWebDriverWait(10).withMessage("Non è stato caricato il titolo del modal").until(ExpectedConditions.visibilityOf(titlePopUp));
            getWebDriverWait(10).withMessage("Non è stato caricato il sottotitolo del modal").until(ExpectedConditions.visibilityOf(subTitlePopUp));
            getWebDriverWait(10).withMessage("Non è stato caricato il bottone annulla del modal").until(ExpectedConditions.visibilityOf(buttonAnnullaEliminazioneInPopUp));
            getWebDriverWait(10).withMessage("Non è stato caricato il bottone conferma del modal").until(ExpectedConditions.visibilityOf(confermaEliminaButtonBy));
        } catch (TimeoutException e) {
            logger.info("Non è stato caricato un elemento del pop up con errore: " + e.getMessage());
        }
        return titlePopUp.getText();
    }

    public void clickSuConfermaElimina() {
        WebElement confermaRimuoviPECBy = driver.findElement(By.id("buttonConferma"));
        getWebDriverWait(10).withMessage("Non è stato possibile cliccare sul bottone conferma").until(ExpectedConditions.elementToBeClickable(confermaRimuoviPECBy));
        confermaRimuoviPECBy.click();
    }

    public boolean siControllaEliminazionePEC() {
        webTool.waitTime(3);
        pecField = driver.findElement(By.id("default_pec"));
        return pecField.isDisplayed();
    }

    public boolean siControllaPresenzaPEC() {
        pecEmail = driver.findElement(By.id("default_pec-typography"));
        return pecEmail.isDisplayed();
    }

    public void insertEnte(String comune) {
        webTool.waitTime(5);
        enteField = driver.findElement(By.id("sender"));
        enteField.sendKeys(comune);
        // wait 2seconds for the list to appear
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("errore" + e.getMessage());
        }
        enteField.sendKeys(Keys.ARROW_DOWN);
        enteField.sendKeys(Keys.ENTER);
        // verify if the first option is the one we want by checking the value
        getWebDriverWait(10).withMessage("Il comune non è visibile").until(ExpectedConditions.attributeContains(enteField, "value", comune));
    }

    public void clickSuIndirizzoPEC() {
        tipoIndirizzoField = driver.findElement(By.id("addressType"));
        tipoIndirizzoField.click();
        WebElement opzionePEC = driver.findElement(By.xpath("//li[@data-value ='PEC']"));
        getWebDriverWait(30).withMessage("Non è visibile l'opzione 'Indirizzo PEC'").until(ExpectedConditions.elementToBeClickable(opzionePEC));
        opzionePEC.click();
    }

    public void insertPECAggiuntiva(String emailPec) {
        try {
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
        associaButton = driver.findElement(By.id("addSpecialButton"));
        getWebDriverWait(10).withMessage("Il bottone associa non è cliccabile").until(ExpectedConditions.elementToBeClickable(associaButton));
        js().executeScript("arguments[0].click()", associaButton);
    }


    public void insertEmailAggiuntiva(String mail) {
        try {
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
        WebElement pecAssociataBy = driver.findElement(By.xpath("//form[@data-testid='a95dace4-4a47-4149-a814-0e669113ce40_emailContact']"));
        getWebDriverWait(10).withMessage("La mail non è stata aggiunta correttamente").until(ExpectedConditions.visibilityOf(pecAssociataBy));
    }

    public boolean controlloEmailAssociata(String email) {
        try {
            WebElement emailBy = driver.findElement(By.xpath("//div[@data-testid = 'courtesyContacts']//div//p[contains(text(),'" + email + "')]"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(emailBy));
            return true;
        } catch (TimeoutException e) {
            logger.error("email associata non presente con errore" + e.getMessage());
            return false;
        }
    }

    public boolean verificaNuovaEmailEPEC(String nuovaEmail) {
        try {
            WebElement emailBy = driver.findElement(By.xpath(" //div[@data-testid = 'DigitalContactsCardBody']//p[contains(text(),'" + nuovaEmail + "')]"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(emailBy));
            return true;
        } catch (TimeoutException e) {
            logger.error("email non presente con errore \n" + e.getMessage());
            return false;
        }
    }

    public void eliminaNuovaEmail() {
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
        eliminaButtonList = driver.findElements(By.xpath("//form[@data-testid = 'default_pecContact']//button[contains(text(),'Elimina')]"));
        getWebDriverWait(30).withMessage("Non è stato possibile cliccare sul bottone elimina email").until(ExpectedConditions.elementToBeClickable(eliminaButtonList.get(0)));
        js().executeScript("arguments[0].click()", eliminaButtonList.get(0));
        WebElement confermaPopUpBy = driver.findElement(By.xpath("//div[@aria-labelledby='dialog-title']//div/button[contains(text(),'Conferma')]"));
        getWebDriverWait(30).withMessage("Il bottone del pop-up non  è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaPopUpBy));
        confermaPopUpBy.click();
    }

    public void confermaButtonEliminaClick() {
        WebElement confermaEliminaButtonBy = driver.findElement(By.id("buttonConferma"));
        getWebDriverWait(10).withMessage("Il bottone conferma del pop-up elimina non cliccabile").until(ExpectedConditions.elementToBeClickable(confermaEliminaButtonBy));
        confermaEliminaButtonBy.click();
    }

    public String getEmailErrorMessage() {
        WebElement errorMessage = driver.findElement(By.id("default_email-helper-text"));
        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public String getPecErrorMessage() {
        WebElement errorMessage = driver.findElement(By.id("default_pec-helper-text"));
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public boolean verificaBottoneConfermaDisabilitato() {
        try {
            attivaButton = driver.findElement(By.id("default_pec-button"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(attivaButton));
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
        WebElement popupConfirmButtonBy = driver.findElement(By.id("code-confirm-button"));
        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(popupConfirmButtonBy));
        popupConfirmButtonBy.click();
    }

    public boolean verificaPopUpConfermaEmail() {
        WebElement hoCapitoCheckboxBy = driver.findElement(By.xpath("//span[contains(text(),'Ho capito')]/preceding-sibling::span/input"));
        return hoCapitoCheckboxBy.isSelected();
    }

    public void clickAvvisamiViaEmail() {
        avvisamiViaEmailButton = driver.findElement(By.id("default_email-button"));
        getWebDriverWait(10).withMessage("avvisami via email non è visibile").until(ExpectedConditions.elementToBeClickable(avvisamiViaEmailButton));
        avvisamiViaEmailButton.click();
    }

    public boolean avvisamiViaEmailIsDisabled() {
        try {
            avvisamiViaEmailButton = driver.findElement(By.id("default_email-button"));
            getWebDriverWait(30).withMessage("avvisami via email non è visibile").until(ExpectedConditions.visibilityOf(avvisamiViaEmailButton));
            return Boolean.parseBoolean(avvisamiViaEmailButton.getAttribute("disabled"));
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void visualizzazioneSezioneAltriRecapiti() {
        WebElement altriRecapitiSectionBy = driver.findElement(By.id("courtesyContactsTitle"));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il titolo della sezione altri recapiti").until(ExpectedConditions.visibilityOf(altriRecapitiSectionBy));
    }

    public void visualizzazioneCampiSezioneAltriRecapiti() {
        vaiInFondoAllaPagina();
        WebElement altriRecapitiSectionBy = driver.findElement(By.xpath("//h5[contains(@id, 'specialContact')]"));
        WebElement titleGiaAssociatiBy = driver.findElement(By.xpath("//p[contains(text(), 'Già associati')]"));

        WebElement tableGiaAssociati = driver.findElement(By.xpath("//*[@id='root']/div[1]/div/main/div/div[2]/div[2]/div/div/table"));
        //WebElement tableGiaAssociati = driver.findElement(By.xpath("//table[@aria-label='Già associati']"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente  il titolo della sezione altri recapiti").until(ExpectedConditions.visibilityOf(altriRecapitiSectionBy));
        getWebDriverWait(10).withMessage("Non si visualizza il titolo della tabella").until(ExpectedConditions.visibilityOf(titleGiaAssociatiBy));
        getWebDriverWait(10).withMessage("Non si visualizza la tabella dei recapiti già associati").until(ExpectedConditions.visibilityOf(tableGiaAssociati));
        List<WebElement> tableRows = tableGiaAssociati.findElements(By.xpath(".//tbody/tr"));
        for (WebElement row : tableRows) {
            List<WebElement> columns = row.findElements(By.xpath(".//td"));
            if (columns.get(0).getText().contains("Comune di Verona")) {
                logger.info("Si visualizza l'ente inserito correttamente");
            }
            if (columns.get(1).getText().contains("pec@pec.pagopa.it")) {
                WebElement modifyButtonBy = driver.findElement(By.xpath(".//button[contains(@id, 'modifyContact')]"));
                WebElement deleteButtonBy = driver.findElement(By.xpath(".//button[contains(@id, 'cancelContact')]"));
                getWebDriverWait(10).withMessage("Bottone modifica non visualizzato e non cliccabile").until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(modifyButtonBy),
                        ExpectedConditions.elementToBeClickable(modifyButtonBy)));
                getWebDriverWait(10).withMessage("Bottone elimina non visualizzato e non cliccabile").until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(deleteButtonBy),
                        ExpectedConditions.elementToBeClickable(deleteButtonBy)));
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
            webTool.waitTime(3);
            buttonAnnullaEliminazioneInPopUp = driver.findElement(By.id("buttonAnnulla"));
            getWebDriverWait(10).withMessage("pulsante annulla eliminazione non trovato").until(ExpectedConditions.visibilityOf(buttonAnnullaEliminazioneInPopUp));
            logger.info("pulsante annulla eliminazione visibile");
        } catch (TimeoutException e) {
            logger.error("caricamento pop-up con errore:" + e.getMessage());
            Assertions.fail("caricamento pop-up con errore:" + e.getMessage());
        }
    }

    public void clickButtonAnnullaEliminazioneInPopUp() {
        webTool.waitTime(5);
        buttonAnnullaEliminazioneInPopUp = driver.findElement(By.id("buttonAnnulla"));
        getWebDriverWait(10).withMessage("Non è stato possibile cliccare sul bottone annulla").until(ExpectedConditions.elementToBeClickable(buttonAnnullaEliminazioneInPopUp));
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
            webTool.waitTime(10);

            getWebDriverWait(10).until(ExpectedConditions.and(
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
            webTool.waitTime(10);

            getWebDriverWait(10).until(ExpectedConditions.and(
                    ExpectedConditions.invisibilityOfElementLocated(By.id("default_pec")),
                    ExpectedConditions.visibilityOfElementLocated(By.id("default_pec-typography"))));
            logger.info("pec non modificabile");
        } catch (TimeoutException e) {
            logger.error("pec modificabile con errore:" + e.getMessage());
            Assertions.fail("pec modificabile con errore:" + e.getMessage());
        }
    }

    public void clickSuAnnulla() {
        WebElement annullaButtonBy = driver.findElement(By.xpath("//button[contains(text(),'Annulla')]"));
        getWebDriverWait(10).withMessage("Non si riesce a cliccare o vedere il bottone annulla").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(annullaButtonBy),
                ExpectedConditions.elementToBeClickable(annullaButtonBy)));
        annullaButtonBy.click();
    }

    public void clickConfermaRecapitoGiaPresente() {
        WebElement confermaButton = driver.findElement(By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]"));
        getWebDriverWait(10).withMessage("Bottone conferma non visualizzato").until(ExpectedConditions.and(ExpectedConditions.visibilityOf(confermaButton), ExpectedConditions.elementToBeClickable(confermaButton)));
        confermaButton.click();
    }

    public void checkNumeroDiCellulareCorretto(String cellulare) {
        cellulareAssociato = driver.findElement(By.id("courtesyContacts-phone"));
        getWebDriverWait(10).withMessage("Numero di cellulare appena inserito non visualizzato").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(cellulareAssociato),
                ExpectedConditions.attributeToBe(cellulareAssociato, "innerText", "+39" + cellulare)));
    }

    public void clickSuBottoneCellulareDiCortesia(String actionButton) {
        WebElement bottoneActionBy = driver.findElement(By.xpath("//form[contains(., 'Numero di cellulare')]//button[contains(text(), '" + actionButton + "')]"));
        getWebDriverWait(10).withMessage("Il bottone non è cliccabile").until(ExpectedConditions.visibilityOf(bottoneActionBy));
        bottoneActionBy.click();
    }

    public void clickSuBottoneEmailDiCortesia(String actionButton) {
        WebElement bottoneActionBy = driver.findElement(By.xpath("//button[contains(text(), '" + actionButton + "')]"));
        getWebDriverWait(10).withMessage("Il bottone non è cliccabile").until(ExpectedConditions.visibilityOf(bottoneActionBy));
        bottoneActionBy.click();
    }

    public void checkNumeroDiCellulareNonPresente() {
        try {
            inserimentoPhoneField = driver.findElement(By.id("default_sms"));
            getWebDriverWait(10).withMessage("Input numero di cellulare non visualizzato o non vuoto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(inserimentoPhoneField),
                    ExpectedConditions.attributeToBe(inserimentoPhoneField, "value", "")
            ));
        } catch (TimeoutException e) {
            logger.error("Input numero di cellulare non visualizzato o non vuoto con errore: " + e.getMessage());
            Assertions.fail("Input numero di cellulare non visualizzato o non vuoto con errore: " + e.getMessage());
        }
    }

    public void checkCampoEmailModificabile() {
        try {
            inserimentoMailField = driver.findElement(By.id("default_email"));
            getWebDriverWait(10).withMessage("Campo email non modificabile").until(ExpectedConditions.visibilityOf(inserimentoMailField));
        } catch (TimeoutException e) {
            logger.error("Campo email non modificabile con errore: " + e.getMessage());
            Assertions.fail("Campo email non modificabile con errore: " + e.getMessage());
        }
    }

    public void checkDisclaimer() {
        WebElement textDisclaimerBy = driver.findElement(By.xpath("//div[contains(text(), 'Se l’impresa non ha una PEC')]"));
        WebElement hoCapitoCheckboxBy = driver.findElement(By.xpath("//span[@data-testid='disclaimer-checkbox']"));
        WebElement annullaButtonBy = driver.findElement(By.id("cancelButton"));
        WebElement confermaButtonBy = driver.findElement(By.id("confirmButton"));
        try {
            getWebDriverWait(10).withMessage("Testo del disclaimer non visualizzato").until(ExpectedConditions.visibilityOf(textDisclaimerBy));
            getWebDriverWait(10).withMessage("Checkbox 'Ho capito' non Visualizzata").until(ExpectedConditions.visibilityOf(hoCapitoCheckboxBy));
            getWebDriverWait(10).withMessage("Bottone annulla non visualizzato").until(ExpectedConditions.visibilityOf(annullaButtonBy));
            getWebDriverWait(10).withMessage("Bottone conferma non visualizzato o cliccabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(confermaButtonBy),
                    ExpectedConditions.attributeToBe(confermaButtonBy, "disabled", "true")));
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
            WebElement messageContainer = driver.findElement(By.id("error-alert"));
            WebElement modalErrorTitle = driver.findElement(By.id("codeModalErrorTitle"));
            getWebDriverWait(10).withMessage("Titolo di errore della modale non visualizzato").until(ExpectedConditions.visibilityOf(modalErrorTitle));
            getWebDriverWait(10).withMessage("Il messaggio di errore non viene visualizzato e il testo non è corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(messageContainer),
                    ExpectedConditions.visibilityOf(modalErrorTitle)
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
            WebElement enteRadice = driver.findElement(By.xpath("//li//p[contains(text(),'" + ente + "')]"));
            getWebDriverWait(10).withMessage("Ente: " + ente + " non visibile").until(ExpectedConditions.visibilityOf(enteRadice));
        }
    }

    public void visualizzazioneSezioneAltriRecapitiPG(String textboxId) {
        String id = "";
        WebElement altriRecapitiSectionBy = driver.findElement(By.id("specialContactTitle"));
        WebElement sottoTitolo = driver.findElement(By.xpath("//p[contains(text(),'Se si desidera che')]"));
        WebElement ente = driver.findElement(By.id("sender"));
        WebElement tipoDiRecapito = driver.findElement(By.id("addressType"));
        if (textboxId.equalsIgnoreCase("pec")) {
            id = "s_pec-label";
        }
        if (textboxId.equalsIgnoreCase("email")) {
            id = "s_mail";
        }
        WebElement textBox = driver.findElement(By.id(id));
        WebElement associaButton = driver.findElement(By.id("addSpecialButton"));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il titolo della sezione altri recapiti").until(ExpectedConditions.visibilityOf(altriRecapitiSectionBy));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il sottotitolo della sezione altri recapiti").until(ExpectedConditions.visibilityOf(sottoTitolo));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  ente della sezione altri recapiti").until(ExpectedConditions.visibilityOf(ente));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  tipo di recapito della sezione altri recapiti").until(ExpectedConditions.visibilityOf(tipoDiRecapito));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  textbox della sezione altri recapiti").until(ExpectedConditions.visibilityOf(textBox));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il bottone associa della sezione altri recapiti").until(ExpectedConditions.visibilityOf(associaButton));
    }

    public void selezionaTipoEmail() {
        tipoIndirizzoField = driver.findElement(By.id("addressType"));
        tipoIndirizzoField.click();
        webTool.waitTime(2);
        WebElement opzioneEmail = driver.findElement(By.id("EMAIL"));
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo email")
                .until(ExpectedConditions.elementToBeClickable(opzioneEmail));
        opzioneEmail.click();
    }

    public void selezionaTipoPec() {
        tipoIndirizzoField = driver.findElement(By.id("addressType"));
        tipoIndirizzoField.click();
        webTool.waitTime(2);
        WebElement opzionePEC = driver.findElement(By.id("PEC"));
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo PEC")
                .until(ExpectedConditions.elementToBeClickable(opzionePEC));
        opzionePEC.click();
    }

    public void selezionaTipoCelulare() {
        tipoIndirizzoField = driver.findElement(By.id("addressType"));
        tipoIndirizzoField.click();
        webTool.waitTime(2);
        WebElement opzioneCelulare = driver.findElement(By.id("Celulare"));
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione celulare")
                .until(ExpectedConditions.elementToBeClickable(opzioneCelulare));
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
        WebElement confirmOtpPopup = driver.findElement(By.id("code-confirm-button"));
        getWebDriverWait(5).withMessage("il bottone Confirm non è cliccabile").until(ExpectedConditions.elementToBeClickable(confirmOtpPopup));
        confirmOtpPopup.click();
    }

    public void clickAnnullaPopupOTP() {
        WebElement cancelOtpPopup = driver.findElement(By.id("code-cancel-button"));
        getWebDriverWait(5).withMessage("il bottone Confirm non è cliccabile").until(ExpectedConditions.elementToBeClickable(cancelOtpPopup));
        cancelOtpPopup.click();
    }

    public boolean waitErrorMessagePopupOTP() {
        try {
            WebElement errorMessageOtp = driver.findElement(By.id("codeModalErrorTitle"));
            getWebDriverWait(5).withMessage("Il messaggio di errore inserimento OTP non è visibile").until(ExpectedConditions.visibilityOf(errorMessageOtp));
            logger.info("Il messaggio di errore viene visualizzato correttamente");
            return true;
        } catch (TimeoutException e) {
            logger.info("Il messaggio di errore non viene visualizzato");

            return false;
        }
    }

}