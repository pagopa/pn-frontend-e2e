package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class DeleghePGPagoPAPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DeleghePGPagoPAPage");

    @FindBy(id = "tab-1")
    WebElement delegatiImpresaButton;

    @FindBy(id = "tab-2")
    WebElement delegheCaricoImpresaButton;
    @FindBy(id = "revoke-delegation-button")
    WebElement revocaMenuButton;

    @FindBy(id = "taxId")
    WebElement cfTextField;

    @FindBy(id = "confirm-button")
    WebElement filtraButton;

    @FindBy(id = "code-confirm-button")
    WebElement confirmCodeButton;

    @FindBy(id = "associate-group")
    WebElement assegnaGruppoRadioButton;

    @FindBy(id = "input-group")
    WebElement gruppoField;

    @FindBy(id = "group-confirm-button")
    WebElement confermaButton;

    @FindBy(id = "code-confirm-button")
    WebElement confermaAccettazioneDelegaButton;

    @FindBy(id = "associate-no-group")
    WebElement nonGruppoRadioButton;

    @FindBy(id = "reject-delegation-button")
    WebElement opzioneRifiuta;

    @FindBy(id = "dialog-action-button")
    WebElement rifiutaButton;

    @FindBy(id = "update-delegation-button")
    WebElement opzioneModifica;

    @FindBy(id = "groups")
    WebElement searchGroupTextField;

    @FindBy(id = "groups-option-0")
    WebElement groupOption;

    @FindBy(id = "delegatesBodyRowDesktop")
    List<WebElement> nomeDelegato;

    @FindBy(xpath = "//div[@data-testid='delegationsOfTheCompany']")
    WebElement tabellaVuotaDelegheACaricoDellImpresa;

    @FindBy(id = "notifications-table")
    WebElement tabelleDelleDelegheACaricoDellImpresa;

    @FindBy(id = "code-cancel-button")
    WebElement buttonIndietroCodiceDiVerifica;

    @FindBy(xpath = "//button[@data-testid='groupCancelButton']")
    WebElement buttonIndietroInAssegnazioneGruppo;

    @FindBy(id = "alert-api-status")
    WebElement alertPopUp;

    @FindBy(id = "error-alert")
    WebElement errorCodeInPopUp;


    public DeleghePGPagoPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDeleghePage() {
        try {
            By titlePage = By.id("Deleghe-page");
            getWebDriverWait(10).withMessage("il titolo della pagina deleghe PG non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            getWebDriverWait(10).withMessage("Il bottone deleghe a carico dell'impresa non è visibile").until(ExpectedConditions.visibilityOf(this.delegheCaricoImpresaButton));
            getWebDriverWait(10).withMessage("la tabella delle deleghe a carico dell impresa non é caricabile").until(ExpectedConditions.visibilityOf(tabellaVuotaDelegheACaricoDellImpresa));
            logger.info("Deleghe page si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Deleghe page non si visualizza correttamente con errore: " + e.getMessage());
            Assert.fail("Deleghe page non si visualizza correttamente con errore: " + e.getMessage());
        }
    }

    public void clickDelegatiImpresa() {
        try {
            getWebDriverWait(10).withMessage("Non è possibile cliccare il bottone delegati dell impresa").until(ExpectedConditions.elementToBeClickable(delegatiImpresaButton));
            this.delegatiImpresaButton.click();
            logger.info("Bottone delegati dell impresa cliccato");
        } catch (TimeoutException e) {
            logger.error("il bottone delegati imprese non è cliccabile" + e.getMessage());
            Assert.fail("il bottone delegati imprese non è cliccabile" + e.getMessage());
        }

    }

    public boolean cercaEsistenzaDelegaPG(String ragioneSociale) {
        By delegaExist = By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + ragioneSociale + "')]]");
        try {
            this.getWebDriverWait(30).withMessage("delega non trovata").until(ExpectedConditions.visibilityOfElementLocated(delegaExist));
            return true;
        } catch (TimeoutException e) {
            return false;
        }

    }

    public void clickRevocaMenuButtonPG(String ragioneSociale) {
        this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElements(nomeDelegato));
        WebElement menuButton;
        for (WebElement delegato : nomeDelegato) {
            if (delegato.getText().contains(ragioneSociale)) {
                menuButton = delegato.findElement(By.tagName("button"));
                this.getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(menuButton));
                menuButton.click();
                this.getWebDriverWait(10).until(ExpectedConditions.visibilityOf(this.revocaMenuButton));
                this.revocaMenuButton.click();
            }
        }
    }

    public void clickSuDelegheCaricoDellImpresa() {
        logger.info("Click sezione deleghe a carico dell impresa");
        this.delegheCaricoImpresaButton.click();
    }

    public void verificaPresenzaElencoDeleghe() {
        try {
            By tableDelegheBy = By.id("notifications-table");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(tableDelegheBy));
            logger.info("L'elenco delle deleghe si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("L'elenco delle deleghe non si visualizza correttamente con errore: " + e.getMessage());
            Assert.fail("L'elenco delle deleghe non si visualizza correttamente con errore: " + e.getMessage());
        }
    }

    public void insertCFDelegante(String codiceFiscale) {
        this.cfTextField.sendKeys(codiceFiscale);
    }

    public void clickFiltraButton() {
        this.filtraButton.click();
    }

    public boolean controlloDelegaRestituita(String ragioneSociale) {
        By delegaBy = By.xpath("//p[contains(text(),'" + ragioneSociale + "')]");
        try {
            this.getWebDriverWait(60).withMessage("ragione sociale non caricata").until(ExpectedConditions.visibilityOfElementLocated(delegaBy));
            logger.info("controllo ragione sociale");
        } catch (TimeoutException e) {
            logger.error("ragione sociale non caricata" + e.getMessage());
            Assert.fail("ragione sociale non caricata" + e.getMessage());
        }
        logger.info("ragione sociale caricata correttamente");
        return this.elements(delegaBy).size() == 1;

    }

    public void clickConfirmCodeButton() {
        this.confirmCodeButton.click();
    }

    public void waitLoadPopUpGruppo() {
        try {
            By titlePageBy = By.id("dialog-title");
            By assegnaGruppoButtonBy = By.xpath("//span[@data-testid='associate-group']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(assegnaGruppoButtonBy));
            this.logger.info("Si vede correttamente il pop-up di assegnazione gruppo");
        } catch (TimeoutException e) {
            this.logger.error("Non si vede correttamente il pop-up di assegnazione gruppo con errore: " + e.getMessage());
            Assert.fail("Non si vede correttamente il pop-up di assegnazione gruppo con errore: " + e.getMessage());
        }
    }

    public void clickAssegnaGruppoRadioButton() {
        logger.info("Click sul radio button assegna gruppo");
        this.assegnaGruppoRadioButton.click();
    }

    public void clickGruppoField(String gruppo) {
        gruppoField.sendKeys(gruppo);
       // WebTool.waitTime(1000);
        By gruppiOption = By.id("input-group-option-0");
       getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(gruppiOption));
        element(gruppiOption).click();
        WebElement closeSelectionButton = driver.findElements(By.xpath("//button[contains(@class, 'MuiAutocomplete-popupIndicator')]")).get(1);
        closeSelectionButton.click();
        logger.info("click su gruppo riuscito");
    }

    public void clickBottoneConferma() {
        getWebDriverWait(10).withMessage("Il bottone conferma nel pop up di scelta gruppo non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.confermaButton));
        this.confermaButton.click();
    }

    public void clickBottoneConfermaDelega() {
        getWebDriverWait(20).withMessage("il bottone conferma delega pg non é visibile").until(ExpectedConditions.elementToBeClickable(confermaAccettazioneDelegaButton));
        confermaAccettazioneDelegaButton.click();
    }

    public boolean verificaEsistenzaErroreCodiceSbagliato() {
        try {
            this.getWebDriverWait(5).withMessage("Alert non visualizzato correttamente").until(ExpectedConditions.visibilityOf(alertPopUp));
            logger.info("Errore codice sbagliato trovato");
            return true;
        } catch (TimeoutException e) {
            logger.info("Errore non trovato");
            return false;
        }

    }

    public void controlloStatoAttiva(String ragioneSociale) {
        try {
            By statoAttivaBy = By.xpath("//tr[contains(td/div/p, '" + ragioneSociale + "')]//span[contains(., 'Attiva')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoAttivaBy));
            logger.info("La delega ha lo stato Attiva");
        } catch (TimeoutException e) {
            logger.error("La delega NON ha lo stato Attiva con errore: " + e.getMessage());
            Assert.fail("La delega NON ha lo stato Attiva con errore: " + e.getMessage());
        }
    }

    public void clickNonAssegnaGruppo() {
        By nonGruppoRadioButtonLabel = By.id("associate-form-group");
        this.getWebDriverWait(30)
                .withMessage("Il pulsante radiobutton 'Non assegnare ad un gruppo' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(nonGruppoRadioButtonLabel));
        nonGruppoRadioButton.click();
    }

    public void clickOpzioneRifiuta() {
        this.opzioneRifiuta.click();
    }

    public void clickBottoneRifiuta() {
        this.rifiutaButton.click();
    }

    public void waitLoadPopUpRevoca() {
        try {
            By revocaPopUpBy = By.xpath("//div[@aria-labelledby='responsive-dialog-title']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(revocaPopUpBy));
            this.logger.info("Si visualizza il pop-up rifiuta delega");
        } catch (TimeoutException e) {
            this.logger.error("Non si visualizza il pop-up rifiuta delega con errore: " + e.getMessage());
            Assert.fail("Non si visualizza il pop-up rifiuta delega con errore: " + e.getMessage());
        }
    }

    public void clickOpzioneModifica() {
        try {
            this.getWebDriverWait(30).withMessage("il pulsante modifica delega non é presente").until(ExpectedConditions.elementToBeClickable(opzioneModifica));
            this.opzioneModifica.click();
        } catch (TimeoutException e) {
            logger.error(" errore: " + e.getMessage());
            Assert.fail(" errore: " + e.getMessage());
        }
    }

    public void waitLoadPopUpModifica() {
        try {
            By titlePOPUPBy = By.id("dialog-title");
            this.getWebDriverWait(30).withMessage("Il titolo del pop-up non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePOPUPBy));
            logger.info("Si visualizza correttamente il pop-up");
        } catch (TimeoutException e) {
            logger.error("NON Si  visualizza  correttamente il pop-up con errore: " + e.getMessage());
            Assert.fail("NON Si visualizza correttamente il pop-up con errore: " + e.getMessage());
        }
    }

    public boolean verificaPresenzaGruppo(String ragioneSociale) {
        try {
            By gruppoBy = By.xpath("//tr[contains(td/div/p, '" + ragioneSociale + "')]//span[contains(text(),'Test gruppi')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(gruppoBy));
            return true;
        } catch (TimeoutException e) {
            logger.info("gruppo non presente");
            return false;
        }

    }

    public void inserireGruppoDelegante() {
        this.getWebDriverWait(30).withMessage("Il campo cerca gruppo non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.searchGroupTextField));
        this.searchGroupTextField.click();
        this.getWebDriverWait(30).withMessage("l'opzione gruppo non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.groupOption));
        this.groupOption.click();
    }

    public void inserimentoCodiceDelegaACaricoDellImpresaAPI(String codiceDelega) {
        String[] codiciDelega = codiceDelega.split("");
        for (int i = 0; i < 5; i++) {
            String xpathBy = "code-input-" + i;
            By codiceDelegaInputBy = By.id(xpathBy);
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(codiceDelegaInputBy));
            WebElement codiceDelegaInput = driver.findElement(codiceDelegaInputBy);
            codiceDelegaInput.sendKeys(codiciDelega[i]);
        }

    }

    public boolean siVisualizzaUnaDelegaPG() {
        try {
            By menuDelegati = By.id("tab-1");
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(menuDelegati));
            element(menuDelegati).click();
            By menuDelega = By.xpath("//button[@data-testid='delegationMenuIcon']");
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(menuDelega));
            logger.info("Trovato correttamente almeno una delega");
            return true;
        } catch (TimeoutException e) {
            logger.warn("Deleghe NON trovate con errore: " + e.getMessage());
            return false;
        }
    }

    public void checkAlertWrongDelegationCode() {
        try {
            By alertCloseButtonBy = By.xpath("//button[@aria-label='Close']");
            this.getWebDriverWait(5).withMessage("Alert non visualizzato correttamente").until(ExpectedConditions.visibilityOf(alertPopUp));
            logger.info("Alert visualizzato correttamente in pagina");
            WebElement alertCloseButton = driver.findElement(alertCloseButtonBy);
            alertCloseButton.click();
            logger.info("Alert chiusa");
        } catch (TimeoutException e) {
            logger.error("Alert non visualizzato con errore: " + e.getMessage());
            Assert.fail("Alert non visualizzato con errore: " + e.getMessage());
        }
    }

    public void clickButtonIndietroDaAssegnaGruppo() {
        try {
            logger.info("Si clicca sul bottone indietro per tornare al pop-up di inserimento codice delega");
            By buttonIndietroPopUpAssegnaGruppo = By.xpath("//button[@data-testid='groupCancelButton']");
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(buttonIndietroPopUpAssegnaGruppo));
            WebElement buttonIndietroClick = driver.findElement(buttonIndietroPopUpAssegnaGruppo);
            buttonIndietroClick.click();
            logger.info("Bottone indietro cliccato");
        } catch (TimeoutException e) {
            logger.error("Bottone non visualizzato con errore " + e.getMessage());
            Assert.fail("Bottone non visualizzato con errore: " + e.getMessage());
        }
    }

    public void clickButtonIndietroCloseModale() {
            logger.info("Si clicca sul bottone indietro per tornare al tabella deleghe");
            By buttonIndietroPopUpAssegnaGruppo = By.xpath("//button[@data-testid='codeCancelButton']");
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(buttonIndietroPopUpAssegnaGruppo));
            WebElement buttonIndietroClick = driver.findElement(buttonIndietroPopUpAssegnaGruppo);
            buttonIndietroClick.click();
            logger.info("Bottone indietro cliccato");
    }



    public void checkTextboxCodiceSonoRosse() {
        final String textboxIsInvalid = "true";
        boolean isInvalid = true;
        for (int i = 0; i < 5; i++) {
            String xpathBy = "code-input-" + i;
            By codiceDelegaInputBy = By.id(xpathBy);
            getWebDriverWait(10).withMessage("Textbox di input codice delega non visualizzata").until(ExpectedConditions.visibilityOfElementLocated(codiceDelegaInputBy));
            WebElement codiceDelegaInput = driver.findElement(codiceDelegaInputBy);
            String stateInput = codiceDelegaInput.getAttribute("aria-invalid");
            if (!textboxIsInvalid.equals(stateInput)) {
                isInvalid = false;
            }
        }
        if (isInvalid) {
            logger.info("Textbox di input codice delega invalido");
        } else {
            logger.error("Almeno una textbox di input codice delega non in stato invalido");
            Assert.fail("Almeno una textbox di input codice delega non in stato invalido");
        }
    }

    public void checkErroreInSelezioneGruppo() {
        gruppoField.sendKeys("Errore Test");
        By gruppoNonTrovato = By.xpath("//div[contains(text(),'Nessun gruppo trovato')]");
        try{
            getWebDriverWait(10).withMessage("testo di errore non trovato").until(ExpectedConditions.visibilityOfElementLocated(gruppoNonTrovato));
            logger.info("testo di errore gruppo trovato con successo");
        }catch (TimeoutException e){
            logger.error("errore in cattura testo di errore ricerca gruppo per assegnazione con errore:" + e.getMessage());
            Assert.fail("errore in cattura testo di errore ricerca gruppo per assegnazione con errore:" + e.getMessage());
        }

    }


    public void clickIndietroInInserimentoCodiceVerifica() {
        getWebDriverWait(10).withMessage("bottone indietro in inserimento codice non trovato").until(ExpectedConditions.visibilityOf(buttonIndietroCodiceDiVerifica));
    buttonIndietroCodiceDiVerifica.click();
    }

    public void clickButtonIndietroInAssegnazioneGruppo(){
        getWebDriverWait(10).withMessage("bottone indietro in assegnazione gruppo non trovato").until(ExpectedConditions.visibilityOf(buttonIndietroInAssegnazioneGruppo));
        buttonIndietroInAssegnazioneGruppo.click();
    }
    public void checkTabellaDelegheACaricoDellImpresa() {
        By menuDelega = By.xpath("//table[@id='notifications-table']//following-sibling::td//button[@data-testid='delegationMenuIcon']");
        By colonnaNome = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Nome')]");
        By colonnaInizioDelega = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Inizio delega')]");
        By colonnaFineDelega = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Fine delega')]");
        By colonnaPermessi = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Permessi')]");
        By colonnaGruppi = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Gruppi')]");
        By colonnaStato = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Stato')]");
        try {
            getWebDriverWait(10).withMessage("tabella deleghe dell impresa non caricata correttamente").until(ExpectedConditions.visibilityOf(tabelleDelleDelegheACaricoDellImpresa));
            getWebDriverWait(10).withMessage("colonna nome non caricata correttamente").until(ExpectedConditions.visibilityOfElementLocated(colonnaNome));
            getWebDriverWait(10).withMessage("colonna inizio delega non caricata correttamente").until(ExpectedConditions.visibilityOfElementLocated(colonnaInizioDelega));
            getWebDriverWait(10).withMessage("colonna fine delega non caricata correttamente").until(ExpectedConditions.visibilityOfElementLocated(colonnaFineDelega));
            getWebDriverWait(10).withMessage("colonna permessi non caricata correttamente").until(ExpectedConditions.visibilityOfElementLocated(colonnaPermessi));
            getWebDriverWait(10).withMessage("colonna gruppi non caricata correttamente").until(ExpectedConditions.visibilityOfElementLocated(colonnaGruppi));
            getWebDriverWait(10).withMessage("colonna stato non caricata correttamente").until(ExpectedConditions.visibilityOfElementLocated(colonnaStato));
            getWebDriverWait(10).withMessage("menu non caricato correttamente").until(ExpectedConditions.visibilityOfElementLocated(menuDelega));
        } catch (TimeoutException e) {
            logger.error("tabella deleghe a carico dell impresa non caricata correttamente" + e.getMessage());
            Assert.fail("tabella deleghe a carico dell impresa non caricata correttamente" + e.getMessage());
        }

    }

    public void checkErroreInInserimentoCodice() {
        getWebDriverWait(10).withMessage("errore in inserimento codice errato non trovato").until(ExpectedConditions.visibilityOf(errorCodeInPopUp));
    }
}