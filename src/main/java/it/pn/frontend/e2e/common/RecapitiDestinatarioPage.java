package it.pn.frontend.e2e.common;

import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RecapitiDestinatarioPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("RecapitiDestinatarioPage");

    @FindBy(id = "add-contact")
    WebElement confermaButton;

    @FindBy(xpath = "//button[@data-testid='add email']")
    WebElement avvisamiViaEmailButton;

    @FindBy(id = "code-confirm-button")
    WebElement confermaButtonPopUp;

    @FindBy(id = "code-cancel-button")
    WebElement annullaButton;

    @FindBy(xpath = "//button[@data-testid='add email']")
    WebElement avvisamiMailButton;

    @FindBy(xpath = "//button[@data-testid='add phone']")
    WebElement avvisamiSMSButton;

    @FindBy(id = "email")
    WebElement inserimentoMailField;

    @FindBy(id = "phone")
    WebElement inserimentoPhoneField;

    @FindBy(id = "cancelContact-default")
    WebElement eliminaPECButton;

    @FindBy(xpath = "//div/h2[contains(text(),'Grazie!')]/following-sibling::div//button")
    WebElement confermaButtonPoPUpPec;

    @FindBy(xpath = "//p[contains(text(),'Indirizzo e-mail')]/following-sibling::div/div/button[contains(text(),'Modifica')]")
    WebElement modificaEmailButton;

    @FindBy(id = "sender")
    WebElement enteField;

    @FindBy(id = "addressType")
    WebElement tipoIndirizzoField;

    @FindBy(id = "s_pec")
    WebElement indirizzoPecField;

    @FindBy(id = "addSpecialButton")
    WebElement associaButton;

    @FindBy(id = "s_mail")
    WebElement emailField;

    @FindBy(xpath = "//form[@data-testid = 'specialContactForm']//div//button[contains(text(),'Elimina')]")
    List<WebElement> eliminaButtonList;

    @FindBy(id = "buttonAnnulla")
    WebElement buttonAnnullaEliminazioneInPopUp;

    @FindBy(id = "courtesyContacts-email")
    WebElement emailAssociata;

    @FindBy(id = "courtesyContacts-phone")
    WebElement cellulareAssociato;

    @FindBy(id = "pec")
    WebElement pecField;

    @FindBy(id = "legalContacts")
    WebElement pecEmail;

    public RecapitiDestinatarioPage(WebDriver driver) {
        super(driver);
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
        By chiudiButtonBy = By.xpath("//button[contains(text(),'Chiudi')]");
        getWebDriverWait(10).withMessage("Il bottone chiudi non è cliccabile").until(ExpectedConditions.elementToBeClickable(chiudiButtonBy));
        this.js().executeScript("arguments[0].click()", this.element(chiudiButtonBy));
    }

    public void insertEmailPEC(String emailPEC) {
        getWebDriverWait(10).withMessage("input pec field non trovato").until(ExpectedConditions.visibilityOf(pecField));
        pecField.sendKeys(emailPEC);
    }

    public void confermaButtonClick() {
        getWebDriverWait(10).withMessage("Il bottone conferma non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.confermaButton));
        this.confermaButton.click();
    }

    public void clickConfermaButtonEliminaPopUp() {
        By confermaEliminaButton = By.xpath("//h2[@id='dialog-title']/following-sibling::div/button[contains(text(),'Conferma')]");
        this.getWebDriverWait(10).withMessage("Il bottone conferma non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.element(confermaEliminaButton)));
        this.element(confermaEliminaButton).click();
    }

    public void waitLoadPopUp() {
        try {
            By titleBy = By.id("dialog-title");
            By descriptionBy = By.id("dialog-description");
            By titleOption = By.xpath("//div[@data-testid='dialog-content']//p[contains(text(), 'Inserisci codice')]");
            List<WebElement> inputBoxes = driver.findElements(By.xpath("//input[contains(@id,'code-input-')]"));
            // The message is different in PG and PF
            By footerNotReceived = By.xpath("//p[contains(text(), 'Non l’hai ricevuto? Controlla')]");
            getWebDriverWait(10).withMessage("Non viene visualizzato correttamente il titolo").until(ExpectedConditions.visibilityOfElementLocated(titleBy));
            getWebDriverWait(10).withMessage("La descrizione non viene visualizzata e il testo non è corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(descriptionBy),
                    ExpectedConditions.attributeContains(descriptionBy, "textContent", "Il codice è valido per 15 minuti.")));
            getWebDriverWait(10).withMessage("La scritta 'Inserisci codice' non viene visualizzata correttamente").until(ExpectedConditions.visibilityOfElementLocated(titleOption));
            getWebDriverWait(10).withMessage("Le input boxes non vengono visualizzate").until(ExpectedConditions.visibilityOfAllElements(inputBoxes));
            if (inputBoxes.size() != 5) {
                Assert.fail("Il numero di input box non è corretto");
            }
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(footerNotReceived));
            boolean checkButton = !confermaButtonPopUp.isEnabled() && annullaButton.isEnabled();
            if (!checkButton) {
                Assert.fail("i pulsanti all'interno del pop-up non rispettano le condizioni");
            }
            logger.info("Il pop-up di conferma viene visualizzato correttamente");
        } catch (TimeoutException e) {
            logger.error("Il pop-up di conferma NON viene visualizzato correttamente con errori: " + e.getMessage());
            Assert.fail("Il pop-up di conferma NON viene visualizzato correttamente con errori:" + e.getMessage());
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

    public void clearOTP() {
            By otpInputby = By.xpath("//input[contains(@id,'code-input')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(otpInputby));
            List<WebElement> otpInputs = this.elements(otpInputby);
            for (int i = 4; i >= 0; i--) {
                otpInputs.get(i).sendKeys(Keys.BACK_SPACE);
            }
            logger.info("Il codice otp viene cancellato correttamente");

    }

    public void confermaButtonClickPopUp() {

            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(confermaButtonPopUp));
            confermaButtonPopUp.click();
            WebTool.waitTime(5);
            By confermaButtonPostInserimentoBy = By.xpath("//div[@data-testid='dialog-actions']/button[contains(text(), 'Conferma')]");
            if (!driver.findElements(confermaButtonPostInserimentoBy).isEmpty()) {
                element(confermaButtonPostInserimentoBy).click();
            }
            // if confermaButtonPostInserimento appears, click it otherwise go on

    }

    public boolean waitMessaggioErrore() {
        try {
            By messaggioErroreBy = By.id("error-alert");
            getWebDriverWait(5).until(ExpectedConditions.visibilityOfElementLocated(messaggioErroreBy));
            logger.info("Il messaggio di errore viene visualizzato correttamente");
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void annullaButtonClick() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(this.annullaButton));
            this.annullaButton.click();
        } catch (TimeoutException e) {
            logger.error("Il bottone annulla non è cliccabile con errore: " + e.getMessage());
            Assert.fail("Il bottone annulla non è cliccabile con errore: " + e.getMessage());
        }
    }

    public void clickAvvisami() {
        getWebDriverWait(30).withMessage("Il bottone avvisami della mail non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.avvisamiMailButton));
        this.avvisamiMailButton.click();
    }

    public void clickAvvisamiSMS() {
        getWebDriverWait(30).withMessage("Il bottone avvisami del sms non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.avvisamiSMSButton));
        this.avvisamiSMSButton.click();
    }

    public void insertEmail(String email) {
        getWebDriverWait(10).withMessage("l'input mail non è visibile").until(ExpectedConditions.visibilityOf(this.inserimentoMailField));
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
        getWebDriverWait(30).withMessage("l'input numero telefono non è visibile").until(ExpectedConditions.visibilityOf(this.inserimentoPhoneField));
        if (inserimentoPhoneField.isDisplayed()) {
            inserimentoPhoneField.sendKeys(cellulare);
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", inserimentoPhoneField);
            inserimentoPhoneField.sendKeys(cellulare);
        }
    }

    public boolean verificaPecAssociata() {
        try {
            By pecAssociata = By.id("associatedPEC");
            getWebDriverWait(10).withMessage("PEC associata non presente").until(ExpectedConditions.visibilityOfElementLocated(pecAssociata));
            return true;
        } catch (TimeoutException e) {
            logger.error("pec associata non trovata" + e.getMessage());
            return false;
        }
    }

    public boolean siVisualizzaPopUpConferma() {
        try {
            By popUpConfermaTitleBy = By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]");
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(popUpConfermaTitleBy));
            return true;
        } catch (TimeoutException e) {
            logger.error("pop up conferma non trovato: \n" + e.getMessage());
            return false;
        }
    }

    public void clickConfermaButton() {
        getWebDriverWait(10).withMessage("Il bottone conferma del pop up non é cliccabile").until(ExpectedConditions.elementToBeClickable(confermaButtonPoPUpPec));
        this.confermaButtonPoPUpPec.click();
    }

    public void visualizzaValidazione() {
        try {
            By pecAssociata = By.xpath("//p[contains(text(), 'Validazione PEC in corso')]");
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(pecAssociata));
        } catch (TimeoutException e) {
            logger.error("Pec non associata con errore:" + e.getMessage());
            Assert.fail("Pec non associata con errore:" + e.getMessage());
        }
    }

    public boolean verificaMailAssociata() {
        try {
            getWebDriverWait(5).withMessage("L'email di cortesia non è presente").until(ExpectedConditions.visibilityOf(emailAssociata));
            return true;
        } catch (TimeoutException e) {
            logger.info("Nessuna email di cortesia impostata");
            return false;
        }
    }

    public boolean verificaNumeroDiCellulareAssociato() {
        try {
            getWebDriverWait(5).withMessage("Il numero di cellulare associato non è presente").until(ExpectedConditions.visibilityOf(cellulareAssociato));
            return true;
        } catch (TimeoutException e) {
            logger.info("Nessun numero di cellulare di cortesia impostato");
            return false;
        }
    }

    public boolean verificaMailField() {
        return inserimentoMailField.isDisplayed();
    }

    public boolean siVisualizzaPecInserita() {
        try {
            By pecInseritaBy = By.xpath("//p[contains(text(),'PEC associata')]");
            getWebDriverWait(5).until(ExpectedConditions.visibilityOfElementLocated(pecInseritaBy));
            return true;
        } catch (TimeoutException e) {
            logger.error("Pec inserita non presente con errore:" + e.getMessage());
            return false;
        }
    }

    public void clickSuModifica() {
        By modificaMailButton = By.xpath("//p[contains(text(),'Indirizzo e-mail')]/following-sibling::div/div/button[contains(text(),'Modifica')]");
        getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(modificaMailButton));
        if (modificaEmailButton.isDisplayed()) {
            this.modificaEmailButton.click();
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", modificaEmailButton);
            this.modificaEmailButton.click();
        }
    }

    public void clickSuModificaPEC() {
        try {
            By modificaButtonBy = By.xpath("//div[@data-testid='legalContacts']//button[@id='modifyContact-default']");
            this.getWebDriverWait(10).withMessage("Non si riesce a cliccare o vedere il bottone modifica PEC").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(modificaButtonBy),
                    ExpectedConditions.elementToBeClickable(modificaButtonBy)));
            this.element(modificaButtonBy).click();
        } catch (TimeoutException e) {
            logger.error("Non si riesce a cliccare o vedere il bottone modifica PEC con errore:" + e.getMessage());
            Assert.fail("Non si riesce a cliccare o vedere il bottone modifica PEC con errore:" + e.getMessage());
        }
    }

    public void cancellaTesto() {
        try {
            this.js().executeScript("arguments[0].click()", pecField);
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOf(pecField));
            this.clearWebElementField(pecField);
            logger.info("testo email pec cancellata");
        } catch (TimeoutException e) {
            logger.error("Non si riesce a cancellare il testo della  email PEC :" + e.getMessage());
            Assert.fail("Non si riesce a cancellare il testo della  email PEC :" + e.getMessage());
        }
    }

    public void clickSuSalva() {
        By salvaButtonBy = By.xpath("//button[contains(text(),'Salva')]");
        this.getWebDriverWait(30).withMessage("Non si riesce a cliccare sul bottone salva").until(ExpectedConditions.elementToBeClickable(salvaButtonBy));
        this.element(salvaButtonBy).click();
    }

    public boolean siControllaPECModificata(String pecInserita) {
        By pecBy = By.xpath("//div[@data-testid = 'legalContacts']//div//p");
        getWebDriverWait(10).withMessage("Non trovata nessuna email PEC inserita").until(ExpectedConditions.visibilityOfElementLocated(pecBy));
        WebElement pec = this.element(pecBy);
        return pec.getText().equals(pecInserita);
    }


    public void clickSuEliminaPec() {
        getWebDriverWait(10).withMessage("Il bottone elimina della PEC associata non è cliccabile").until(ExpectedConditions.elementToBeClickable(eliminaPECButton));
        logger.info("click sul pulsante elimina pec");
        this.eliminaPECButton.click();
    }

    public String waitLoadPopUpElimina() {
        By titlePopUp = By.id("dialog-title");
        By subTitlePopUp = By.id("dialog-description");
        By bottoneAnnulaPopup = By.id("code-cancel-button");
        By confermaEliminaButtonBy = By.xpath("//div[@aria-labelledby='dialog-title']//button[contains(text(),'Conferma')]");
        try {
            getWebDriverWait(10).withMessage("Non è stato caricato il titolo del modal").until(ExpectedConditions.visibilityOfElementLocated(titlePopUp));
            getWebDriverWait(10).withMessage("Non è stato caricato il sottotitolo del modal").until(ExpectedConditions.visibilityOfElementLocated(subTitlePopUp));
            getWebDriverWait(10).withMessage("Non è stato caricato il bottone annulla del modal").until(ExpectedConditions.visibilityOf(buttonAnnullaEliminazioneInPopUp));
            getWebDriverWait(10).withMessage("Non è stato caricato il bottone conferma del modal").until(ExpectedConditions.visibilityOfElementLocated(confermaEliminaButtonBy));
        } catch (TimeoutException e) {
            logger.info("Non è stato caricato un elemento del pop up con errore: " + e.getMessage());
        }
        return this.element(titlePopUp).getText();
    }

    public void clickSuConfermaElimina() {
        By confermaRimuoviPECBy = By.id("buttonConferma");
        getWebDriverWait(10).withMessage("Non è stato possibile cliccare sul bottone conferma").until(ExpectedConditions.elementToBeClickable(confermaRimuoviPECBy));
        this.element(confermaRimuoviPECBy).click();
    }

    public boolean siControllaEliminazionePEC() {
        return pecField.isDisplayed();
    }

    public boolean siControllaPresenzaPEC() {
        return pecEmail.isDisplayed();
    }

    public void insertEnte(String comune) {
        this.enteField.sendKeys(comune);
        // wait 2seconds for the list to appear
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("errore" + e.getMessage());
        }
        this.enteField.sendKeys(Keys.ARROW_DOWN);
        this.enteField.sendKeys(Keys.ENTER);
        // verify if the first option is the one we want by checking the value
        getWebDriverWait(10).withMessage("Il comune non è visibile").until(ExpectedConditions.attributeContains(this.enteField, "value", comune));
    }

    public void clickSuIndirizzoPEC() {
        this.tipoIndirizzoField.click();
        By opzionePEC = By.xpath("//li[@data-value ='PEC']");
        getWebDriverWait(30).withMessage("Non è visibile l'opzione 'Indirizzo PEC'").until(ExpectedConditions.elementToBeClickable(opzionePEC));
        this.element(opzionePEC).click();
    }

    public void insertPECAggiuntiva(String emailPec) {
        try {
            if (this.indirizzoPecField.isDisplayed()) {
                this.indirizzoPecField.sendKeys(emailPec);
            } else {
                this.js().executeScript("arguments[0].scrollIntoView(true);", this.indirizzoPecField);
                this.indirizzoPecField.sendKeys(emailPec);
            }
        } catch (ElementNotInteractableException e) {
            this.indirizzoPecField.sendKeys(emailPec);
        }
    }

    public void clickSuAssocia() {
        getWebDriverWait(10).withMessage("Il bottone associa non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.associaButton));
        this.js().executeScript("arguments[0].click()", associaButton);
    }


    public void insertEmailAggiuntiva(String mail) {
        try {
            if (this.emailField.isDisplayed()) {
                this.emailField.sendKeys(mail);
            } else {
                this.js().executeScript("arguments[0].scrollIntoView(true);", this.emailField);
                this.emailField.sendKeys(mail);
            }
        } catch (ElementNotInteractableException e) {
            this.emailField.sendKeys(mail);
        }
    }

    public void siControllaEmailAggiunta() {
        By pecAssociataBy = By.xpath("//form[@data-testid='specialContactForm']");
        this.getWebDriverWait(10).withMessage("La mail non è stata aggiunta correttamente").until(ExpectedConditions.visibilityOfElementLocated(pecAssociataBy));
    }

    public boolean controlloEmailAssociata(String email) {
        try {
            By emailBy = By.xpath("//div[@data-testid = 'courtesyContacts']//div//p[contains(text(),'" + email + "')]");
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(emailBy));
            return true;
        } catch (TimeoutException e) {
            logger.error("email associata non presente con errore" + e.getMessage());
            return false;
        }
    }

    public boolean verificaNuovaEmailEPEC(String nuovaEmail) {
        try {
            By emailBy = By.xpath("//form[@data-testid = 'specialContactForm']//div//p[contains(text(),'" + nuovaEmail + "')]");
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(emailBy));
            return true;
        } catch (TimeoutException e) {
            logger.error("email non presente con errore \n" + e.getMessage());
            return false;
        }
    }

    public void eliminaNuovaEmail() {
        int posizioneElimina = this.eliminaButtonList.size() - 1;
        this.getWebDriverWait(10).withMessage("Non è stato possibile cliccare sul bottone elimina email").until(ExpectedConditions.elementToBeClickable(this.eliminaButtonList.get(posizioneElimina)));
        this.js().executeScript("arguments[0].click()", this.eliminaButtonList.get(posizioneElimina));
        By confermaPopUpBy = By.xpath("//div[@aria-labelledby='dialog-title']//div/button[contains(text(),'Conferma')]");
        this.getWebDriverWait(10).withMessage("Il bottone del pop-up non è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaPopUpBy));
        this.element(confermaPopUpBy).click();

    }

    public void eliminaNuovaPec() {
        getWebDriverWait(30).withMessage("Non è stato possibile cliccare sul bottone elimina email").until(ExpectedConditions.elementToBeClickable(this.eliminaButtonList.get(0)));
        this.js().executeScript("arguments[0].click()", this.eliminaButtonList.get(0));
        By confermaPopUpBy = By.xpath("//div[@aria-labelledby='dialog-title']//div/button[contains(text(),'Conferma')]");
        getWebDriverWait(30).withMessage("Il bottone del pop-up non  è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaPopUpBy));
        this.element(confermaPopUpBy).click();
    }

    public void confermaButtonEliminaClick() {
        By confermaEliminaButtonBy = By.id("buttonConferma");
        this.getWebDriverWait(10).withMessage("Il bottone conferma del pop-up elimina non cliccabile").until(ExpectedConditions.elementToBeClickable(confermaEliminaButtonBy));
        this.element(confermaEliminaButtonBy).click();
    }

    public String getEmailErrorMessage() {
        By errorBy = By.id("email-helper-text");
        WebElement errorMessage = driver.findElement(errorBy);
        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public String getPecErrorMessage() {
        By errorBy = By.id("pec-helper-text");
        WebElement errorMessage = driver.findElement(errorBy);
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public boolean verificaBottoneConfermaDisabilitato() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.confermaButton));
            return Boolean.parseBoolean(this.confermaButton.getAttribute("disabled"));
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("bottone non disabilitato " + e.getMessage());
            return false;
        }
    }

    public void clickHoCapitoCheckBoxPopup() {
        By hoCapitoCheckboxBy = By.xpath("//span[contains(text(),'Ho capito')]/preceding-sibling::span/input");
        WebElement hoCapitoCheckBox = this.driver.findElement(hoCapitoCheckboxBy);
        logger.info("click su checkbox ho capito");
        hoCapitoCheckBox.click();
    }

    public void confermaEmailPopup() {
        By popupConfirmButtonBy = By.id("confirmButton");
        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(popupConfirmButtonBy));
        this.driver.findElement(popupConfirmButtonBy).click();
    }

    public boolean verificaPopUpConfermaEmail() {
        By hoCapitoCheckboxBy = By.xpath("//span[contains(text(),'Ho capito')]/preceding-sibling::span/input");
        return this.driver.findElement(hoCapitoCheckboxBy).isSelected();
    }

    public void clickAvvisamiViaEmail() {
        getWebDriverWait(10).withMessage("avvisami via email non è visibile").until(ExpectedConditions.elementToBeClickable(this.avvisamiViaEmailButton));
        this.avvisamiViaEmailButton.click();
    }

    public boolean avvisamiViaEmailIsDisabled() {
        try {
            getWebDriverWait(30).withMessage("avvisami via email non è visibile").until(ExpectedConditions.visibilityOf(this.avvisamiViaEmailButton));
            return Boolean.parseBoolean(this.avvisamiViaEmailButton.getAttribute("disabled"));
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void visualizzazioneSezioneAltriRecapiti() {
        By altriRecapitiSectionBy = By.id("specialContact");
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il titolo della sezione altri recapiti").until(ExpectedConditions.visibilityOfElementLocated(altriRecapitiSectionBy));
    }

    public void visualizzazioneCampiSezioneAltriRecapiti() {
        vaiInFondoAllaPagina();
        By altriRecapitiSectionBy = By.xpath("//h5[contains(@id, 'specialContact')]");
        By titleGiaAssociatiBy = By.xpath("//p[contains(text(), 'Già associati')]");
        WebElement tableGiaAssociati = driver.findElement(By.xpath("//table[@aria-label='Già associati']"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente  il titolo della sezione altri recapiti").until(ExpectedConditions.visibilityOfElementLocated(altriRecapitiSectionBy));
        getWebDriverWait(10).withMessage("Non si visualizza il titolo della tabella").until(ExpectedConditions.visibilityOfElementLocated(titleGiaAssociatiBy));
        getWebDriverWait(10).withMessage("Non si visualizza la tabella dei recapiti già associati").until(ExpectedConditions.visibilityOf(tableGiaAssociati));
        List<WebElement> tableRows = tableGiaAssociati.findElements(By.xpath(".//tbody/tr"));
        for (WebElement row : tableRows) {
            List<WebElement> columns = row.findElements(By.xpath(".//td"));
            if (columns.get(0).getText().contains("Comune di Verona")) {
                logger.info("Si visualizza l'ente inserito correttamente");
            }
            if (columns.get(1).getText().contains("pec@pec.pagopa.it")) {
                By modifyButtonBy = By.xpath(".//button[contains(@id, 'modifyContact')]");
                By deleteButtonBy = By.xpath(".//button[contains(@id, 'cancelContact')]");
                getWebDriverWait(10).withMessage("Bottone modifica non visualizzato e non cliccabile").until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated(modifyButtonBy),
                        ExpectedConditions.elementToBeClickable(modifyButtonBy)));
                getWebDriverWait(10).withMessage("Bottone elimina non visualizzato e non cliccabile").until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated(deleteButtonBy),
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
            getWebDriverWait(10).withMessage("pulsante annulla eliminazione non trovato").until(ExpectedConditions.visibilityOf(buttonAnnullaEliminazioneInPopUp));
            logger.info("pulsante annulla eliminazione visibile");
        } catch (TimeoutException e) {
            logger.error("caricamento pop-up con errore:" + e.getMessage());
            Assert.fail("caricamento pop-up con errore:" + e.getMessage());
        }
    }

    public void clickButtonAnnullaEliminazioneInPopUp() {
        getWebDriverWait(10).withMessage("Non è stato possibile cliccare sul bottone annulla").until(ExpectedConditions.elementToBeClickable(buttonAnnullaEliminazioneInPopUp));
        buttonAnnullaEliminazioneInPopUp.click();
    }

    public void checkEmailPrecedentementeSalvata(String email) {
        if (emailAssociata.getText().equalsIgnoreCase(email)) {
            logger.info("la mail associata risulta uguale alla precedente");
        } else {
            logger.error("la mail associata é diversa dalla precedentemente salvata");
            Assert.fail("la mail associata é diversa dalla precedentemente salvata");
        }
    }

    public void verificaPecModificabile() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(pecField),
                    ExpectedConditions.attributeToBe(pecField, "readonly", "")));
            logger.info("pec modificabile");
        } catch (TimeoutException e) {
            logger.error("pec non modificabile con errore:" + e.getMessage());
            Assert.fail("pec non modificabile con errore:" + e.getMessage());
        }
    }

    public void verificaPecNonModificabile() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.and(
                    ExpectedConditions.invisibilityOf(pecField),
                    ExpectedConditions.visibilityOf(pecEmail)));
            logger.info("pec non modificabile");
        } catch (TimeoutException e) {
            logger.error("pec modificabile con errore:" + e.getMessage());
            Assert.fail("pec modificabile con errore:" + e.getMessage());
        }
    }

    public void clickSuAnnulla() {
        By annullaButtonBy = By.xpath("//button[contains(text(),'Annulla')]");
        this.getWebDriverWait(10).withMessage("Non si riesce a cliccare o vedere il bottone annulla").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(annullaButtonBy),
                ExpectedConditions.elementToBeClickable(annullaButtonBy)));
        this.element(annullaButtonBy).click();
    }

    public void clickConfermaRecapitoGiaPresente() {
        By confermaButton = By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]");
        getWebDriverWait(10).withMessage("Bottone conferma non visualizzato").until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(confermaButton), ExpectedConditions.elementToBeClickable(confermaButton)));
        this.element(confermaButton).click();
    }

    public void checkNumeroDiCellulareCorretto(String cellulare) {
        getWebDriverWait(10).withMessage("Numero di cellulare appena inserito non visualizzato").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(cellulareAssociato),
                ExpectedConditions.attributeToBe(cellulareAssociato, "innerText", "+39" + cellulare)));
    }

    public void clickSuBottoneCellulareDiCortesia(String actionButton) {
        By bottoneActionBy = By.xpath("//form[contains(., 'Numero di cellulare')]//button[contains(text(), '" + actionButton + "')]");
        getWebDriverWait(10).withMessage("Il bottone non è cliccabile").until(ExpectedConditions.visibilityOfElementLocated(bottoneActionBy));
        this.element(bottoneActionBy).click();
    }

    public void clickSuBottoneEmailDiCortesia(String actionButton) {
        By bottoneActionBy = By.xpath("//form[contains(., 'Indirizzo e-mail')]//button[contains(text(), '" + actionButton + "')]");
        getWebDriverWait(10).withMessage("Il bottone non è cliccabile").until(ExpectedConditions.visibilityOfElementLocated(bottoneActionBy));
        this.element(bottoneActionBy).click();
    }

    public void checkNumeroDiCellulareNonPresente() {
        try {
            getWebDriverWait(10).withMessage("Input numero di cellulare non visualizzato o non vuoto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(inserimentoPhoneField),
                    ExpectedConditions.attributeToBe(inserimentoPhoneField, "value", "")
            ));
        } catch (TimeoutException e) {
            logger.error("Input numero di cellulare non visualizzato o non vuoto con errore: " + e.getMessage());
            Assert.fail("Input numero di cellulare non visualizzato o non vuoto con errore: " + e.getMessage());
        }
    }

    public void checkCampoEmailModificabile() {
        try {
            getWebDriverWait(10).withMessage("Campo email non modificabile").until(ExpectedConditions.visibilityOf(inserimentoMailField));
        } catch (TimeoutException e) {
            logger.error("Campo email non modificabile con errore: " + e.getMessage());
            Assert.fail("Campo email non modificabile con errore: " + e.getMessage());
        }
    }

    public void checkDisclaimer() {
        By textDisclaimerBy = By.xpath("//div[contains(text(), 'Se l’impresa non ha una PEC')]");
        By hoCapitoCheckboxBy = By.xpath("//span[@data-testid='disclaimer-checkbox']");
        By annullaButtonBy = By.id("cancelButton");
        By confermaButtonBy = By.id("confirmButton");
        try {
            getWebDriverWait(10).withMessage("Testo del disclaimer non visualizzato").until(ExpectedConditions.visibilityOfElementLocated(textDisclaimerBy));
            getWebDriverWait(10).withMessage("Checkbox 'Ho capito' non Visualizzata").until(ExpectedConditions.visibilityOfElementLocated(hoCapitoCheckboxBy));
            getWebDriverWait(10).withMessage("Bottone annulla non visualizzato").until(ExpectedConditions.visibilityOfElementLocated(annullaButtonBy));
            getWebDriverWait(10).withMessage("Bottone conferma non visualizzato o cliccabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(confermaButtonBy),
                    ExpectedConditions.attributeToBe(confermaButtonBy, "disabled", "true")));
        } catch (TimeoutException e) {
            logger.error("Qualche componente del pop up non viene visualizzato con errore: " + e.getMessage());
            Assert.fail("Qualche componente del pop up non viene visualizzato con errore: " + e.getMessage());
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
                Assert.fail("Non si visualizza il messaggio di errore");
            }
            By messageContainer = By.id("error-alert");
            By modalErrorTitle = By.id("codeModalErrorTitle");
            getWebDriverWait(10).withMessage("Titolo di errore della modale non visualizzato").until(ExpectedConditions.visibilityOfElementLocated(modalErrorTitle));
            getWebDriverWait(10).withMessage("Il messaggio di errore non viene visualizzato e il testo non è corretto").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(messageContainer),
                    ExpectedConditions.visibilityOfElementLocated(modalErrorTitle)
            ));
        } catch (TimeoutException e) {
            logger.error("Il messaggio di errore non viene visualizzato correttamente con errore: " + e.getMessage());
            Assert.fail("Il messaggio di errore non viene visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public void clickDropdownAltriRecapiti(String dropdown) {
        By dropdownBy;
        if (dropdown.equalsIgnoreCase("ente")) {
            dropdownBy = By.id("sender");
        } else {
            dropdownBy = By.id("addressType");
        }
        getWebDriverWait(10).withMessage("Non è stato possibile cliccare sul dropdown").until(ExpectedConditions.elementToBeClickable(dropdownBy));
        element(dropdownBy).click();
    }

    public void visualizzaListaEnti(List<String> enti) {
        for (String ente : enti) {
            By enteRadice = By.xpath("//li//p[contains(text(),'" + ente + "')]");
            getWebDriverWait(10).withMessage("Ente: " + ente + " non visibile").until(ExpectedConditions.visibilityOfElementLocated(enteRadice));
        }
    }

    public void visualizzazioneSezioneAltriRecapitiPG(String textboxId) {
        String id = "";
        By altriRecapitiSectionBy = By.id("specialContactTitle");
        By sottoTitolo = By.xpath("//p[contains(text(),'Se si desidera che')]");
        By ente = By.id("sender");
        By tipoDiRecapito = By.id("addressType");
        if (textboxId.equalsIgnoreCase("pec")){id = "s_pec-label";}
        if (textboxId.equalsIgnoreCase("email")){id = "s_mail";}
        By textBox = By.id(id);
        By associaButton = By.id("addSpecialButton");
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il titolo della sezione altri recapiti").until(ExpectedConditions.visibilityOfElementLocated(altriRecapitiSectionBy));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il sottotitolo della sezione altri recapiti").until(ExpectedConditions.visibilityOfElementLocated(sottoTitolo));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  ente della sezione altri recapiti").until(ExpectedConditions.visibilityOfElementLocated(ente));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  tipo di recapito della sezione altri recapiti").until(ExpectedConditions.visibilityOfElementLocated(tipoDiRecapito));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  textbox della sezione altri recapiti").until(ExpectedConditions.visibilityOfElementLocated(textBox));
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il bottone associa della sezione altri recapiti").until(ExpectedConditions.visibilityOfElementLocated(associaButton));
    }

    public void selezionaTipoEmail() {
        this.tipoIndirizzoField.click();
        WebTool.waitTime(2);
        By opzioneEmail = By.id("EMAIL");
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo email")
                .until(ExpectedConditions.elementToBeClickable(opzioneEmail));
        element(opzioneEmail).click();
    }

    public void selezionaTipoPec() {
        tipoIndirizzoField.click();
        WebTool.waitTime(2);
        By opzionePEC = By.id("PEC");
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione indirizzo PEC")
                .until(ExpectedConditions.elementToBeClickable(opzionePEC));
        element(opzionePEC).click();
    }

    public void selezionaTipoCelulare() {
        this.tipoIndirizzoField.click();
        WebTool.waitTime(2);
        By opzioneCelulare = By.id("Celulare");
        getWebDriverWait(10)
                .withMessage("Non è visibile l'opzione celulare")
                .until(ExpectedConditions.elementToBeClickable(opzioneCelulare));
       element(opzioneCelulare).click();
    }

    public void checkMessaggioDiErrore(String check){
        String id = "";
        if (check.equalsIgnoreCase("pec")){
            id = "s_pec-helper-text";
            Assert.assertFalse("la textbox non presenta il bordo rosso",indirizzoPecField.getAttribute("aria-invalid").equalsIgnoreCase("false"));
        }
        if (check.equalsIgnoreCase("email")){
            id = "s_mail-helper-text";
            Assert.assertFalse("la textbox non presenta il bordo rosso",emailField.getAttribute("aria-invalid").equalsIgnoreCase("false"));
        }

        By errorMessage = By.id(id);
        getWebDriverWait(5).withMessage("Il messaggio di errore non è visibile").until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        Assert.assertNotNull("lIl bottone Associa è attivo", associaButton.getAttribute("disabled"));
    }

    public void clearMailbox(String check){
        if(check.equalsIgnoreCase("pec")){
        this.js().executeScript("arguments[0].setAttribute('autocomplete', 'off')", indirizzoPecField);
        indirizzoPecField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }
        if (check.equalsIgnoreCase("email")) {
            this.js().executeScript("arguments[0].setAttribute('autocomplete', 'off')",emailField );
            emailField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }

    }

    public void clickConfermaPopupOTP(){
        By confirmOtpPopup = By.id("code-confirm-button");
        getWebDriverWait(5).withMessage("il bottone Confirm non è cliccabile").until(ExpectedConditions.elementToBeClickable(confirmOtpPopup));
        element(confirmOtpPopup).click();
    }
    public void clickAnnullaPopupOTP(){
        By cancelOtpPopup = By.id("code-cancel-button");
        getWebDriverWait(5).withMessage("il bottone Confirm non è cliccabile").until(ExpectedConditions.elementToBeClickable(cancelOtpPopup));
        element(cancelOtpPopup).click();
    }

    public boolean waitErrorMessagePopupOTP() {
        try {
            By errorMessageOtp = By.id("codeModalErrorTitle");
            getWebDriverWait(5).withMessage("Il messaggio di errore inserimento OTP non è visibile").until(ExpectedConditions.visibilityOfElementLocated(errorMessageOtp));
            logger.info("Il messaggio di errore viene visualizzato correttamente");
            return true;
        } catch (TimeoutException e) {
            logger.info("Il messaggio di errore non viene visualizzato");

            return false;
        }
    }

}