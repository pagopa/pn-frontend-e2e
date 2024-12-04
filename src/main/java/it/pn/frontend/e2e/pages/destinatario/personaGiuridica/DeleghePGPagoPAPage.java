package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

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

    private WebTool webTool;

    public DeleghePGPagoPAPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadDeleghePage() {
        try {
            getWebDriverWait(10).withMessage("il titolo della pagina deleghe PG non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Deleghe-page"))));
            getWebDriverWait(10).withMessage("Il bottone deleghe a carico dell'impresa non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("tab-2"))));
//            getWebDriverWait(10).withMessage("la tabella delle deleghe a carico dell impresa non é caricabile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("//div[@data-testid='delegationsOfTheCompany']"))));
            getWebDriverWait(10).withMessage("la tabella delle deleghe a carico dell impresa non é caricabile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='delegationsOfTheCompany']"))));
            logger.info("Deleghe page si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Deleghe page non si visualizza correttamente con errore: " + e.getMessage());
            Assertions.fail("Deleghe page non si visualizza correttamente con errore: " + e.getMessage());
        }
    }

    public void clickDelegatiImpresa() {
        try {
            delegatiImpresaButton = driver.findElement(By.id("tab-1"));
            getWebDriverWait(10).withMessage("Non è possibile cliccare il bottone delegati dell impresa").until(ExpectedConditions.elementToBeClickable(delegatiImpresaButton));
            delegatiImpresaButton.click();
            logger.info("Bottone delegati dell impresa cliccato");
        } catch (TimeoutException e) {
            logger.error("il bottone delegati imprese non è cliccabile" + e.getMessage());
            Assertions.fail("il bottone delegati imprese non è cliccabile" + e.getMessage());
        }

    }

    public boolean cercaEsistenzaDelegaPG(String ragioneSociale) {
        try {
            getWebDriverWait(30).withMessage("delega non trovata").until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + ragioneSociale + "')]]")));
            return true;
        } catch (TimeoutException | NoSuchElementException  e) {
            return false;
        }

    }

    public void clickRevocaMenuButtonPG(String ragioneSociale) {
        nomeDelegato = driver.findElements(By.id("delegatesBodyRowDesktop"));
        getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElements(nomeDelegato));
        WebElement menuButton;
        for (WebElement delegato : nomeDelegato) {
            if (delegato.getText().contains(ragioneSociale)) {
                menuButton = delegato.findElement(By.tagName("button"));
                getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(menuButton));
                menuButton.click();
                webTool.waitTime(20);
                getWebDriverWait(10).withMessage("bottone revoca delega non cliccabile").until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));
                revocaMenuButton = driver.findElement(By.id("revoke-delegation-button"));
                getWebDriverWait(10).until(ExpectedConditions.visibilityOf(revocaMenuButton));
                revocaMenuButton.click();
            }
        }
    }

    public void clickSuDelegheCaricoDellImpresa() {
        logger.info("Click sezione deleghe a carico dell impresa");
        delegheCaricoImpresaButton = driver.findElement(By.id("tab-2"));
        delegheCaricoImpresaButton.click();
    }

    public void verificaPresenzaElencoDeleghe() {
        try {
            WebElement tableDelegheBy = driver.findElement(By.id("notifications-table"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(tableDelegheBy));
            logger.info("L'elenco delle deleghe si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("L'elenco delle deleghe non si visualizza correttamente con errore: " + e.getMessage());
            Assertions.fail("L'elenco delle deleghe non si visualizza correttamente con errore: " + e.getMessage());
        }
    }

    public void insertCFDelegante(String codiceFiscale) {
        cfTextField = driver.findElement(By.id("taxId"));
        cfTextField.sendKeys(codiceFiscale);
    }

    public void clickFiltraButton() {
        filtraButton = driver.findElement(By.id("confirm-button"));
        filtraButton.click();
    }

    public boolean controlloDelegaRestituita(String ragioneSociale) {
        List<WebElement> delegaBy = driver.findElements(By.xpath("//p[contains(text(),'" + ragioneSociale + "')]"));
        try {
            getWebDriverWait(60).withMessage("ragione sociale non caricata").until(ExpectedConditions.visibilityOfAllElements(delegaBy));
            logger.info("controllo ragione sociale");
        } catch (TimeoutException e) {
            logger.error("ragione sociale non caricata" + e.getMessage());
            Assertions.fail("ragione sociale non caricata" + e.getMessage());
        }
        logger.info("ragione sociale caricata correttamente");
        return delegaBy.size() == 1;

    }

    public void clickConfirmCodeButton() {
        confirmCodeButton = driver.findElement(By.id("code-confirm-button"));
        confirmCodeButton.click();
    }

    public void waitLoadPopUpGruppo() {
        try {
            WebElement titlePageBy = driver.findElement(By.id("dialog-title"));
            WebElement assegnaGruppoButtonBy = driver.findElement(By.xpath("//span[@data-testid='associate-group']"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(titlePageBy));
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(assegnaGruppoButtonBy));
            logger.info("Si vede correttamente il pop-up di assegnazione gruppo");
        } catch (TimeoutException e) {
            logger.error("Non si vede correttamente il pop-up di assegnazione gruppo con errore: " + e.getMessage());
            Assertions.fail("Non si vede correttamente il pop-up di assegnazione gruppo con errore: " + e.getMessage());
        }
    }

    public void clickAssegnaGruppoRadioButton() {
        logger.info("Click sul radio button assegna gruppo");
        assegnaGruppoRadioButton = driver.findElement(By.id("associate-group"));
        assegnaGruppoRadioButton.click();
    }

    public void clickGruppoField(String gruppo) {
        gruppoField = driver.findElement(By.id("input-group"));
        gruppoField.sendKeys(gruppo);
        // WebTool.waitTime(1000);
        WebElement gruppiOption = driver.findElement(By.id("input-group-option-0"));
        getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(gruppiOption));
        gruppiOption.click();
        WebElement closeSelectionButton = driver.findElements(By.xpath("//button[contains(@class, 'MuiAutocomplete-popupIndicator')]")).get(1);
        closeSelectionButton.click();
        logger.info("click su gruppo riuscito");
    }

    public void clickBottoneConferma() {
        confermaButton = driver.findElement(By.id("group-confirm-button"));
        getWebDriverWait(10).withMessage("Il bottone conferma nel pop up di scelta gruppo non è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaButton));
        confermaButton.click();
    }

    public void clickBottoneConfermaDelega() {
        confermaAccettazioneDelegaButton = driver.findElement(By.id("code-confirm-button"));
        getWebDriverWait(20).withMessage("il bottone conferma delega pg non é visibile").until(ExpectedConditions.elementToBeClickable(confermaAccettazioneDelegaButton));
        confermaAccettazioneDelegaButton.click();
    }

    public boolean verificaEsistenzaErroreCodiceSbagliato() {
        try {
            getWebDriverWait(5).withMessage("Alert non visualizzato correttamente").until(ExpectedConditions.visibilityOfElementLocated((By.id("alert-api-status"))));
            logger.info("Errore codice sbagliato trovato");
            return true;
        } catch (TimeoutException e) {
            logger.info("Errore non trovato");
            return false;
        }

    }

    public void controlloStatoAttiva(String ragioneSociale) {
        try {
            WebElement statoAttivaBy = driver.findElement(By.xpath("//tr[contains(td/div/p, '" + ragioneSociale + "')]//span[contains(., 'Attiva')]"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(statoAttivaBy));
            logger.info("La delega ha lo stato Attiva");
        } catch (TimeoutException e) {
            logger.error("La delega NON ha lo stato Attiva con errore: " + e.getMessage());
            Assertions.fail("La delega NON ha lo stato Attiva con errore: " + e.getMessage());
        }
    }

    public void clickNonAssegnaGruppo() {
        WebElement nonGruppoRadioButtonLabel = driver.findElement(By.id("associate-form-group"));
        nonGruppoRadioButton = driver.findElement(By.id("associate-no-group"));
        getWebDriverWait(30)
                .withMessage("Il pulsante radiobutton 'Non assegnare ad un gruppo' non è visibile")
                .until(ExpectedConditions.visibilityOf(nonGruppoRadioButtonLabel));
        nonGruppoRadioButton.click();
    }

    public void clickOpzioneRifiuta() {
        opzioneRifiuta = driver.findElement(By.id("reject-delegation-button"));
        opzioneRifiuta.click();
    }

    public void clickBottoneRifiuta() {
        rifiutaButton = driver.findElement(By.id("dialog-action-button"));
        rifiutaButton.click();
    }

    public void waitLoadPopUpRevoca() {
        try {
            WebElement revocaPopUpBy = driver.findElement(By.xpath("//div[@aria-labelledby='responsive-dialog-title']"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(revocaPopUpBy));
            logger.info("Si visualizza il pop-up rifiuta delega");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza il pop-up rifiuta delega con errore: " + e.getMessage());
            Assertions.fail("Non si visualizza il pop-up rifiuta delega con errore: " + e.getMessage());
        }
    }

    public void clickOpzioneModifica() {
        try {
            opzioneModifica = driver.findElement(By.id("update-delegation-button"));
            getWebDriverWait(30).withMessage("il pulsante modifica delega non é presente").until(ExpectedConditions.elementToBeClickable(opzioneModifica));
            opzioneModifica.click();
        } catch (TimeoutException e) {
            logger.error(" errore: " + e.getMessage());
            Assertions.fail(" errore: " + e.getMessage());
        }
    }

    public void waitLoadPopUpModifica() {
        try {
            WebElement titlePOPUPBy = driver.findElement(By.id("dialog-title"));
            getWebDriverWait(30).withMessage("Il titolo del pop-up non è visibile").until(ExpectedConditions.visibilityOf(titlePOPUPBy));
            logger.info("Si visualizza correttamente il pop-up");
        } catch (TimeoutException e) {
            logger.error("NON Si  visualizza  correttamente il pop-up con errore: " + e.getMessage());
            Assertions.fail("NON Si visualizza correttamente il pop-up con errore: " + e.getMessage());
        }
    }

    public boolean verificaPresenzaGruppo(String ragioneSociale) {
        try {
//            WebElement gruppoBy = driver.findElement(By.xpath("//tr[contains(td/div/p, '" + ragioneSociale + "')]//span[contains(text(),'Test gruppi')]"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(td/div/p, '" + ragioneSociale + "')]//span[contains(text(),'Test gruppi')]")));
            return true;
        } catch (TimeoutException e) {
            logger.info("gruppo non presente");
            return false;
        }

    }

    public void inserireGruppoDelegante() {
        searchGroupTextField = driver.findElement(By.id("groups"));
        getWebDriverWait(30).withMessage("Il campo cerca gruppo non è cliccabile").until(ExpectedConditions.elementToBeClickable(searchGroupTextField));
        searchGroupTextField.click();

        groupOption = driver.findElement(By.id("groups-option-0"));
        getWebDriverWait(30).withMessage("l'opzione gruppo non è cliccabile").until(ExpectedConditions.elementToBeClickable(groupOption));
        groupOption.click();
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
            webTool.waitTime(10);
            WebElement menuDelegati = driver.findElement(By.id("tab-1"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(menuDelegati));
            menuDelegati.click();
            webTool.waitTime(10);
            WebElement menuDelega = driver.findElement(By.xpath("//button[@data-testid='delegationMenuIcon']"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(menuDelega));
            logger.info("Trovato correttamente almeno una delega");
            return true;
        } catch (TimeoutException e) {
            logger.warn("Deleghe NON trovate con errore: " + e.getMessage());
            return false;
        }
    }

    public void checkAlertWrongDelegationCode() {
        try {
            WebElement alertCloseButtonBy = driver.findElement(By.xpath("//button[@aria-label='Close']"));
            getWebDriverWait(5).withMessage("Alert non visualizzato correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("alert-api-status"))));
            logger.info("Alert visualizzato correttamente in pagina");
            alertCloseButtonBy.click();
            logger.info("Alert chiusa");
        } catch (TimeoutException e) {
            logger.error("Alert non visualizzato con errore: " + e.getMessage());
            Assertions.fail("Alert non visualizzato con errore: " + e.getMessage());
        }
    }

    public void clickButtonIndietroDaAssegnaGruppo() {
        try {
            logger.info("Si clicca sul bottone indietro per tornare al pop-up di inserimento codice delega");
            WebElement buttonIndietroPopUpAssegnaGruppo = driver.findElement(By.xpath("//button[@data-testid='groupCancelButton']"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(buttonIndietroPopUpAssegnaGruppo));
            buttonIndietroPopUpAssegnaGruppo.click();
            logger.info("Bottone indietro cliccato");
        } catch (TimeoutException e) {
            logger.error("Bottone non visualizzato con errore " + e.getMessage());
            Assertions.fail("Bottone non visualizzato con errore: " + e.getMessage());
        }
    }

    public void clickButtonIndietroCloseModale() {
        logger.info("Si clicca sul bottone indietro per tornare al tabella deleghe");
        WebElement buttonIndietroPopUpAssegnaGruppo = driver.findElement(By.xpath("//button[@data-testid='codeCancelButton']"));
        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(buttonIndietroPopUpAssegnaGruppo));
        buttonIndietroPopUpAssegnaGruppo.click();
        logger.info("Bottone indietro cliccato");
    }


    public void checkTextboxCodiceSonoRosse() {
        final String textboxIsInvalid = "true";
        boolean isInvalid = true;
        for (int i = 0; i < 5; i++) {
            String xpathBy = "code-input-" + i;
            getWebDriverWait(10).withMessage("Textbox di input codice delega non visualizzata").until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(xpathBy)));
            By codiceDelegaInput = By.id(xpathBy);
            String stateInput = element(codiceDelegaInput).getAttribute("aria-invalid");
            if (!textboxIsInvalid.equals(stateInput)) {
                isInvalid = false;
            }
        }
        if (isInvalid) {
            logger.info("Textbox di input codice delega invalido");
        } else {
            logger.error("Almeno una textbox di input codice delega non in stato invalido");
            Assertions.fail("Almeno una textbox di input codice delega non in stato invalido");
        }
    }

    public void checkErroreInSelezioneGruppo() {
        driver.findElement(By.id("input-group")).sendKeys("Errore Test");
        WebElement gruppoNonTrovato = driver.findElement(By.xpath("//div[contains(text(),'Nessun gruppo trovato')]"));
        try {
            getWebDriverWait(10).withMessage("testo di errore non trovato").until(ExpectedConditions.visibilityOf(gruppoNonTrovato));
            logger.info("testo di errore gruppo trovato con successo");
        } catch (TimeoutException e) {
            logger.error("errore in cattura testo di errore ricerca gruppo per assegnazione con errore:" + e.getMessage());
            Assertions.fail("errore in cattura testo di errore ricerca gruppo per assegnazione con errore:" + e.getMessage());
        }

    }


    public void clickIndietroInInserimentoCodiceVerifica() {
        buttonIndietroCodiceDiVerifica = driver.findElement(By.id("code-cancel-button"));
        getWebDriverWait(10).withMessage("bottone indietro in inserimento codice non trovato").until(ExpectedConditions.visibilityOf(buttonIndietroCodiceDiVerifica));
        buttonIndietroCodiceDiVerifica.click();
    }

    public void clickButtonIndietroInAssegnazioneGruppo() {
        buttonIndietroInAssegnazioneGruppo = driver.findElement(By.xpath("//button[@data-testid='groupCancelButton']"));
        getWebDriverWait(10).withMessage("bottone indietro in assegnazione gruppo non trovato").until(ExpectedConditions.visibilityOf(buttonIndietroInAssegnazioneGruppo));
        buttonIndietroInAssegnazioneGruppo.click();
    }

    public void checkTabellaDelegheACaricoDellImpresa() {
        webTool.waitTime(5);
        WebElement menuDelega = driver.findElement(By.xpath("//table[@id='notifications-table']//following-sibling::td//button[@data-testid='delegationMenuIcon']"));
        WebElement colonnaNome = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Nome')]"));
        WebElement colonnaInizioDelega = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Inizio delega')]"));
        WebElement colonnaFineDelega = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Fine delega')]"));
        WebElement colonnaPermessi = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Permessi')]"));
        WebElement colonnaGruppi = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Gruppi')]"));
        WebElement colonnaStato = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Stato')]"));
        tabelleDelleDelegheACaricoDellImpresa = driver.findElement(By.id("notifications-table"));
        try {
            getWebDriverWait(10).withMessage("tabella deleghe dell impresa non caricata correttamente").until(ExpectedConditions.visibilityOf(tabelleDelleDelegheACaricoDellImpresa));
            getWebDriverWait(10).withMessage("colonna nome non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaNome));
            getWebDriverWait(10).withMessage("colonna inizio delega non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaInizioDelega));
            getWebDriverWait(10).withMessage("colonna fine delega non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaFineDelega));
            getWebDriverWait(10).withMessage("colonna permessi non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaPermessi));
            getWebDriverWait(10).withMessage("colonna gruppi non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaGruppi));
            getWebDriverWait(10).withMessage("colonna stato non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaStato));
            getWebDriverWait(10).withMessage("menu non caricato correttamente").until(ExpectedConditions.visibilityOf(menuDelega));
        } catch (TimeoutException e) {
            logger.error("tabella deleghe a carico dell impresa non caricata correttamente" + e.getMessage());
            Assertions.fail("tabella deleghe a carico dell impresa non caricata correttamente" + e.getMessage());
        }

    }

    public void checkErroreInInserimentoCodice() {
        errorCodeInPopUp = driver.findElement(By.id("error-alert"));
        getWebDriverWait(10).withMessage("errore in inserimento codice errato non trovato").until(ExpectedConditions.visibilityOf(errorCodeInPopUp));
    }
}