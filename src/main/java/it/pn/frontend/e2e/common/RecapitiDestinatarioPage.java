package it.pn.frontend.e2e.common;

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

    @FindBy(xpath = "//button[@data-testid = 'addSpecialButton']")
    WebElement associaButton;

    @FindBy(id = "s_mail")
    WebElement emailField;

    @FindBy(xpath = "//form[@data-testid = 'specialContactForm']//div//button[contains(text(),'Elimina')]")
    List<WebElement> eliminaButtonList;

    @FindBy(id = "buttonAnnulla")
    WebElement buttonAnnullaEliminazioneInPopUp;

    @FindBy(id = "courtesyContacts-email")
    WebElement emailAssociata;

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
        this.getWebDriverWait(30).withMessage("Il bottone chiudi non è cliccabile").until(ExpectedConditions.elementToBeClickable(chiudiButtonBy));
        this.js().executeScript("arguments[0].click()", this.element(chiudiButtonBy));
    }

    public void insertEmailPEC(String emailPEC) {
        By inserimentoPecFieldBy = By.id("pec");
        this.getWebDriverWait(10).withMessage("input pec field non trovato").until(ExpectedConditions.visibilityOfElementLocated(inserimentoPecFieldBy));
        this.element(inserimentoPecFieldBy).sendKeys(emailPEC);
    }

    public void confermaButtonClick() {
        this.getWebDriverWait(10).withMessage("Il bottone conferma non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.confermaButton));
        this.confermaButton.click();
    }

    public void clickConfermaButtonEliminaPopUp() {
        By confermaEliminaButton = By.xpath("//h2[@id='dialog-title']/following-sibling::div/button[contains(text(),'Conferma')]");
        this.getWebDriverWait(60).withMessage("Il bottone conferma non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.element(confermaEliminaButton)));
        this.element(confermaEliminaButton).click();
    }

    public void waitLoadPopUp() {
        try {
            By titleBy = By.id("dialog-title");
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(titleBy));
            boolean checkButton = !confermaButtonPopUp.isEnabled() && annullaButton.isEnabled();
            if(!checkButton){
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



    public void confermaButtonClickPopUp() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(this.confermaButtonPopUp));
            this.confermaButtonPopUp.click();
            By confermaButtonPostInserimentoBy = By.id("confirmDialog");
            if (!driver.findElements(confermaButtonPostInserimentoBy).isEmpty()) {
                this.element(confermaButtonPostInserimentoBy).click();
            }
            // if confermaButtonPostInserimento appears, click it otherwise go on
        } catch (TimeoutException e) {
            logger.error("Il bottone conferma all'interno del popup non è cliccabile con errore:" + e.getMessage());
            Assert.fail("Il bottone conferma all'interno del popup non è cliccabile con errore:" + e.getMessage());
        }
    }

    public boolean waitMessaggioErrore() {
        try {
            By messaggioErroreBy = By.id("error-alert");
            this.getWebDriverWait(5).until(ExpectedConditions.visibilityOfElementLocated(messaggioErroreBy));
            logger.info("Il messaggio di errore viene visualizzato correttamente");
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void annullaButtonClick() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.annullaButton));
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
        getWebDriverWait(30).withMessage("l'input mail non è visibile").until(ExpectedConditions.visibilityOf(this.inserimentoMailField));
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
            By pecAssociata = By.xpath("//p[contains(text(), 'PEC associata')]");
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(pecAssociata));
            return true;
        } catch (TimeoutException e) {
            logger.error("pec associata non trovata" + e.getMessage());
            return false;
        }
    }

    public boolean siVisualizzaPopUpConferma() {
        try {
            By popUpConfermaBy = By.xpath("//h2[contains(text(),'Grazie!')]");
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(popUpConfermaBy));
            return true;
        } catch (TimeoutException e) {
            logger.error("pop up conferma non trovato" + e.getMessage());
            return false;
        }
    }

    public void clickConfermaButton() {
        this.getWebDriverWait(30).withMessage("Il bottone conferma del pop up non é cliccabile").until(ExpectedConditions.elementToBeClickable(confermaButtonPoPUpPec));
        this.confermaButtonPoPUpPec.click();
    }

    public void visualizzaValidazione() {
        try {
            By pecAssociata = By.xpath("//p[contains(text(), 'Validazione PEC in corso')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(pecAssociata));
        } catch (TimeoutException e) {
            logger.error("Pec non associata con errore:" + e.getMessage());
            Assert.fail("Pec non associata con errore:" + e.getMessage());
        }
    }

    public boolean verificaMailAssociata() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(emailAssociata));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean siVisualizzaPecInserita() {
        try {
            By pecInseritaBy = By.xpath("//p[contains(text(),'PEC associata')]");
            this.getWebDriverWait(5).until(ExpectedConditions.visibilityOfElementLocated(pecInseritaBy));
            return true;
        } catch (TimeoutException e) {
            logger.error("Pec inserita non presente con errore:" + e.getMessage());
            return false;
        }
    }

    public void clickSuModifica() {
        By modificaMailButton = By.xpath("//p[contains(text(),'Indirizzo e-mail')]/following-sibling::div/div/button[contains(text(),'Modifica')]");
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(modificaMailButton));
        if (modificaEmailButton.isDisplayed()) {
            this.modificaEmailButton.click();
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", modificaEmailButton);
            this.modificaEmailButton.click();
        }


    }

    public void clickSuModificaPEC() {
        By modificaButtonBy = By.xpath("//p[contains(text(),'PEC associata')]/following-sibling::div/div/button[contains(text(),'Modifica')]");
        this.getWebDriverWait(30).withMessage("Non si riesce a cliccare sul bottone modifica PEC").until(ExpectedConditions.elementToBeClickable(modificaButtonBy));
        this.element(modificaButtonBy).click();
    }

    public void cancellaTesto() {
        try {
            By pecInputBy = By.id("pec");
            WebElement pecInput = this.element(pecInputBy);
            this.js().executeScript("arguments[0].click()", pecInput);
            String emailPec = pecInput.getAttribute("value");
            for (int i = 0; i < emailPec.length(); i++) {
                pecInput.sendKeys(Keys.BACK_SPACE);
            }
            logger.error("testo email pec cancellata");

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
        this.getWebDriverWait(10).withMessage("Non trovata nessuna email PEC inserita").until(ExpectedConditions.visibilityOfElementLocated(pecBy));
        WebElement pec = this.element(pecBy);
        return pec.getText().equals(pecInserita);
    }


    public void clickSuEliminaPec() {
        this.getWebDriverWait(30).withMessage("Non si è riuscito ad cliccare sul bottone elimina PEC").until(ExpectedConditions.elementToBeClickable(eliminaPECButton));
        logger.info("click sul pulsante elimina pec");
        this.eliminaPECButton.click();
    }

    public String waitLoadPopUpElimina() {
        By titlePopUp = By.id("dialog-title");
        By subTitlePopUp = By.id("dialog-description");
        this.getWebDriverWait(30).withMessage("Non è stato caricato il titolo del pop-up").until(ExpectedConditions.visibilityOfElementLocated(titlePopUp));
        this.getWebDriverWait(30).withMessage("Non è stato caricato il sottotitolo del pop-up").until(ExpectedConditions.visibilityOfElementLocated(subTitlePopUp));
        return this.element(titlePopUp).getText();
    }

    public void clickSuConfermaElimina() {
        By confermaRimuoviPECBy = By.xpath("//button[contains(text(),'Annulla')]/following-sibling::button");
        this.getWebDriverWait(30).withMessage("Non è stato possibile cliccare sul bottone conferma").until(ExpectedConditions.elementToBeClickable(confermaRimuoviPECBy));
        this.element(confermaRimuoviPECBy).click();
    }

    public boolean siControllaNonPresenzaPEC() {
        try {
            By pecField = By.id("pec");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(pecField));
            logger.info("pec presente");
            return true;
        } catch (TimeoutException e) {
            logger.error("pec non presente con errore" + e.getMessage());
            return false;
        }
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
        this.getWebDriverWait(10).withMessage("Il comune non è visibile").until(ExpectedConditions.attributeContains(this.enteField, "value", comune));
    }

    public void clickSuIndirizzoPEC() {
        this.tipoIndirizzoField.click();
        By opzionePEC = By.xpath("//li[@data-value ='PEC']");
        this.getWebDriverWait(30).withMessage("Non è visibile l'opzione 'Indirizzo PEC'").until(ExpectedConditions.elementToBeClickable(opzionePEC));
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
        this.getWebDriverWait(30).withMessage("Il bottone associa non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.associaButton));
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
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(emailBy));
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
        this.getWebDriverWait(30).withMessage("Non è stato possibile cliccare sul bottone elimina email").until(ExpectedConditions.elementToBeClickable(this.eliminaButtonList.get(0)));
        this.js().executeScript("arguments[0].click()", this.eliminaButtonList.get(0));
        By confermaPopUpBy = By.xpath("//div[@aria-labelledby='dialog-title']//div/button[contains(text(),'Conferma')]");
        this.getWebDriverWait(30).withMessage("Il bottone del pop-up non  è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaPopUpBy));
        this.element(confermaPopUpBy).click();
    }

    public void confermaButtonEliminaClick() {
        By confermaEliminaButtonBy = By.xpath("//div[@aria-labelledby='dialog-title']//button[contains(text(),'Conferma')]");
        this.getWebDriverWait(40).withMessage("Il bottone conferma del pop-up elimina non cliccabile").until(ExpectedConditions.elementToBeClickable(confermaEliminaButtonBy));
        this.element(confermaEliminaButtonBy).click();
    }

    public String getEmailErrorMessage() {
        By errorBy = By.id("email-helper-text");
        WebElement errorMessage = driver.findElement(errorBy);
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(errorMessage));
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
        By popupConfirmaButtonBy = By.xpath("//button[@data-testid='disclaimer-confirm-button']");
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(popupConfirmaButtonBy));
        this.driver.findElement(popupConfirmaButtonBy).click();
    }

    public boolean verificaPopUpConfermaEmail() {
        By hoCapitoCheckboxBy = By.xpath("//span[contains(text(),'Ho capito')]/preceding-sibling::span/input");
        return this.driver.findElement(hoCapitoCheckboxBy).isSelected();
    }

    public void clickAvvisamiViaEmail() {
        getWebDriverWait(60).withMessage("avvisami via email non è visibile").until(ExpectedConditions.elementToBeClickable(this.avvisamiViaEmailButton));
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

    public void visualizzazioneSezioneAltriRecapitiPG() {
        vaiInFondoAllaPagina();
        By altriRecapitiSectionBy = By.id("specialContactTitle");
        getWebDriverWait(5).withMessage(" Non si visualizza correttamente  il titolo della sezione altri recapiti").until(ExpectedConditions.visibilityOfElementLocated(altriRecapitiSectionBy));
    }

    public void checkButtonAnnullaEliminazioneInPopUp(){
        try{
            getWebDriverWait(20).withMessage("pulsante annulla eliminazione non trovato").until(ExpectedConditions.visibilityOf(buttonAnnullaEliminazioneInPopUp));
            logger.info("pulsante annulla eliminazione visibile");
        }catch(TimeoutException e){
            logger.error("caricamento pop-up con errore:" + e.getMessage());
            Assert.fail("caricamento pop-up con errore:" + e.getMessage());
        }
    }
    public void clickButtonAnnullaEliminazioneInPopUp(){
        buttonAnnullaEliminazioneInPopUp.click();
    }

    public void checkEmailPrecedentementeSalvata(String email){
        if(emailAssociata.getText().equalsIgnoreCase(email)){
            logger.info("la mail associata risulta uguale alla precedente");
        }else{
            logger.error("la mail associata é diversa dalla precedentemente salvata");
            Assert.fail("la mail associata é diversa dalla precedentemente salvata");
        }
    }

}


