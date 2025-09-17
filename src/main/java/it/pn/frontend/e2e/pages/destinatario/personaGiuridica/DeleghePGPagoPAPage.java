package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class DeleghePGPagoPAPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger(DeleghePGPagoPAPage.class);


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

    //    public void waitLoadDeleghePage() {
//        try {
//            getWebDriverWait(10).withMessage("il titolo della pagina deleghe PG non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Deleghe-page"))));
//            getWebDriverWait(10).withMessage("Il bottone deleghe a carico dell'impresa non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("tab-2"))));
//            getWebDriverWait(10).withMessage("la tabella delle deleghe a carico dell impresa non é caricabile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='delegationsOfTheCompany']"))));
//            logger.info("Deleghe page si visualizza correttamente");
//        } catch (TimeoutException e) {
//            Assertions.fail("Deleghe page non si visualizza correttamente con errore: " + e.getMessage());
//        }
//    }
    public void waitLoadDeleghePage() {
        getWebDriverWait(10)
                .withMessage("Il titolo della pagina deleghe PG non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("Deleghe-page")));
        getWebDriverWait(10)
                .withMessage("Il bottone deleghe a carico dell'impresa non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("tab-2")));
        getWebDriverWait(10)
                .withMessage("La tabella delle deleghe a carico dell'impresa non è caricabile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='delegationsOfTheCompany']")));
        logger.info("Deleghe page si visualizza correttamente");
    }


    public void clickDelegatiImpresa() {
        try {
            WebElement clickDelegatiImpresaButton = getWebDriverWait(15)
                    .withMessage("Non è possibile cliccare il bottone delegati dell'impresa")
                    .until(ExpectedConditions.elementToBeClickable(By.id("tab-1")));
            clickDelegatiImpresaButton.click();
        } catch (TimeoutException | NoSuchElementException e) {
            Assertions.fail("Il bottone delegati imprese non è cliccabile: " + e.getMessage());
        }
    }

    public boolean cercaEsistenzaDelegaPG(String ragioneSociale) {
        logger.info("CERCA_ESISTENZA_DELEGA_PG: {}", ragioneSociale);
        try {
            getWebDriverWait(35)
                    .withMessage("Delega non trovata")
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//table[@id='notifications-table']//td[p[contains(text(),'" + ragioneSociale + "')]]")
                    ));
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    //    public void clickRevocaMenuButtonPG(String ragioneSociale) {
//        getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id("delegatesBodyRowDesktop"))));
//        nomeDelegato = driver.findElements(By.id("delegatesBodyRowDesktop"));
//        WebElement menuButton;
//        for (WebElement delegato : nomeDelegato) {
//            if (delegato.getText().contains(ragioneSociale)) {
//                menuButton = delegato.findElement(By.tagName("button"));
//                getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(menuButton));
//                menuButton.click();
//                webTool.waitTime(20);
//                getWebDriverWait(10).withMessage("bottone revoca delega non cliccabile").until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));
//                revocaMenuButton = driver.findElement(By.id("revoke-delegation-button"));
//                getWebDriverWait(10).until(ExpectedConditions.visibilityOf(revocaMenuButton));
//                revocaMenuButton.click();
//            }
//        }
//    }
    public void clickRevocaMenuButtonPG(String ragioneSociale) {
        By delegatoRowLocator = By.id("delegatesBodyRowDesktop");
        List<WebElement> delegati = getWebDriverWait(10)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(delegatoRowLocator));
        for (WebElement delegato : delegati) {
            if (delegato.getText().contains(ragioneSociale)) {
                WebElement menuButton = getWebDriverWait(10)
                        .until(ExpectedConditions.elementToBeClickable(delegato.findElement(By.tagName("button"))));
                menuButton.click();
                webTool.waitTime(2); // attesa breve se necessaria per animazioni o apertura menu
                By revokeButtonLocator = By.id("revoke-delegation-button");
                revocaMenuButton = getWebDriverWait(10)
                        .withMessage("Bottone revoca delega non cliccabile")
                        .until(ExpectedConditions.elementToBeClickable(revokeButtonLocator));
                revocaMenuButton.click();
                logger.info("Click su revoca delega per: {}", ragioneSociale);
                break; // esco dal ciclo una volta trovato il delegato corretto
            }
        }
    }


    //    public void clickSuDelegheCaricoDellImpresa() {
//        logger.info("Click sezione deleghe a carico dell impresa");
//        delegheCaricoImpresaButton = driver.findElement(By.id("tab-2"));
//        delegheCaricoImpresaButton.click();
//    }
    public void clickSuDelegheCaricoDellImpresa() {
        logger.info("Click sezione deleghe a carico dell'impresa");
        By delegheCaricoLocator = By.id("tab-2");
        delegheCaricoImpresaButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Deleghe a carico dell'impresa' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(delegheCaricoLocator));
        delegheCaricoImpresaButton.click();
    }


    public void verificaPresenzaElencoDeleghe() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notifications-table"))));
            logger.info("L'elenco delle deleghe si visualizza correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("L'elenco delle deleghe non si visualizza correttamente con errore: " + e.getMessage());
        }
    }

    //    public void insertCFDelegante(String codiceFiscale) {
//        cfTextField = driver.findElement(By.id("taxId"));
//        cfTextField.sendKeys(codiceFiscale);
//    }
    public void insertCFDelegante(String codiceFiscale) {
        By cfLocator = By.id("taxId");
        cfTextField = getWebDriverWait(10)
                .withMessage("Il campo Codice Fiscale non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(cfLocator));
        cfTextField.sendKeys(codiceFiscale);
    }


    //    public void clickFiltraButton() {
//        filtraButton = driver.findElement(By.id("confirm-button"));
//        filtraButton.click();
//    }
    public void clickFiltraButton() {
        By filtraButtonLocator = By.id("confirm-button");
        filtraButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Filtra' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(filtraButtonLocator));
        filtraButton.click();
    }


    public boolean controlloDelegaRestituita(String ragioneSociale) {
        List<WebElement> delegaBy = null;
        try {
            delegaBy = getWebDriverWait(70)
                    .withMessage("Ragione sociale non caricata")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath("//p[contains(text(),'" + ragioneSociale + "')]")
                    ));
            logger.info("controllo ragione sociale");
        } catch (TimeoutException e) {
            Assertions.fail("ragione sociale non caricata" + e.getMessage());
        }
        logger.info("ragione sociale caricata correttamente");
        return !delegaBy.isEmpty();

    }

    //    public void clickConfirmCodeButton() {
//        confirmCodeButton = driver.findElement(By.id("code-confirm-button"));
//        confirmCodeButton.click();
//    }
    public void clickConfirmCodeButton() {
        By confirmButtonLocator = By.id("code-confirm-button");
        confirmCodeButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Conferma codice' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        confirmCodeButton.click();
    }


    //    public void waitLoadPopUpGruppo() {
//        try {
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-title"))));
//            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@data-testid='associate-group']"))));
//            logger.info("Si vede correttamente il pop-up di assegnazione gruppo");
//        } catch (TimeoutException e) {
//            Assertions.fail("Non si vede correttamente il pop-up di assegnazione gruppo con errore: " + e.getMessage());
//        }
//    }
    public void waitLoadPopUpGruppo() {
        By dialogTitleLocator = By.id("dialog-title");
        By associateGroupButtonLocator = By.xpath("//span[@data-testid='associate-group']");
        getWebDriverWait(30)
                .withMessage("Il titolo del pop-up di assegnazione gruppo non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(dialogTitleLocator));
        getWebDriverWait(30)
                .withMessage("Il bottone per associare il gruppo non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(associateGroupButtonLocator));
        logger.info("Si vede correttamente il pop-up di assegnazione gruppo");
    }


    //    public void clickAssegnaGruppoRadioButton() {
//        logger.info("Click sul radio button assegna gruppo");
//        assegnaGruppoRadioButton = driver.findElement(By.id("associate-group"));
//        assegnaGruppoRadioButton.click();
//    }
    public void clickAssegnaGruppoRadioButton() {
        logger.info("Click sul radio button 'Assegna gruppo'");
        By radioButtonLocator = By.id("associate-group");
        assegnaGruppoRadioButton = getWebDriverWait(10)
                .withMessage("Il radio button 'Assegna gruppo' non è visibile o cliccabile")
                .until(ExpectedConditions.presenceOfElementLocated(radioButtonLocator));
        assegnaGruppoRadioButton.click();
    }


    //    public void clickGruppoField(String gruppo) {
//        gruppoField = driver.findElement(By.id("input-group"));
//        gruppoField.sendKeys(gruppo);
//        getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-group-option-0"))));
//        WebElement gruppiOption = driver.findElement(By.id("input-group-option-0"));
//        gruppiOption.click();
//        Actions actions = new Actions(driver);
//        actions.sendKeys(Keys.ESCAPE).build().perform();
//        logger.info("click su gruppo riuscito");
//    }
    public void clickGruppoField(String gruppo) {
        By gruppoFieldLocator = By.id("input-group");
        By firstOptionLocator = By.id("input-group-option-0");
        gruppoField = getWebDriverWait(10)
                .withMessage("Il campo gruppo non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(gruppoFieldLocator));
        gruppoField.sendKeys(gruppo);
        WebElement gruppiOption = getWebDriverWait(30)
                .withMessage("La prima opzione del gruppo non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(firstOptionLocator));
        gruppiOption.click();
        new Actions(driver).sendKeys(Keys.ESCAPE).build().perform();
        logger.info("Click su gruppo riuscito");
    }


    //    public void clickBottoneConferma() {
//        getWebDriverWait(40).withMessage("Il bottone conferma nel pop up di scelta gruppo non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("group-confirm-button"))));
//        confermaButton = driver.findElement(By.id("group-confirm-button"));
//        confermaButton.click();
//    }
    public void clickBottoneConferma() {
        By confermaButtonLocator = By.id("group-confirm-button");
        confermaButton = getWebDriverWait(40)
                .withMessage("Il bottone conferma nel pop-up di scelta gruppo non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(confermaButtonLocator));
        confermaButton.click();
        logger.info("Click sul bottone conferma eseguito correttamente");
    }


    //    public void clickBottoneConfermaDelega() {
//        getWebDriverWait(20).withMessage("il bottone conferma delega pg non é visibile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("code-confirm-button"))));
//        confermaAccettazioneDelegaButton = driver.findElement(By.id("code-confirm-button"));
//        confermaAccettazioneDelegaButton.click();
//    }
    public void clickBottoneConfermaDelega() {
        By confermaDelegaLocator = By.id("code-confirm-button");
        confermaAccettazioneDelegaButton = getWebDriverWait(20)
                .withMessage("Il bottone conferma delega PG non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(confermaDelegaLocator));
        confermaAccettazioneDelegaButton.click();
        logger.info("Click sul bottone conferma delega eseguito correttamente");
    }


    public boolean verificaEsistenzaErroreCodiceSbagliato() {
        try {
            getWebDriverWait(10).withMessage("Alert non visualizzato correttamente").until(ExpectedConditions.visibilityOfElementLocated((By.id("alert-api-status"))));
            logger.info("Errore codice sbagliato trovato");
            return true;
        } catch (TimeoutException e) {
            logger.info("Errore non trovato");
            return false;
        }

    }

    //    public void controlloStatoAttiva(String ragioneSociale) {
//        try {
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tr[contains(td/p, '" + ragioneSociale + "')]//span[contains(., 'Attiva')]"))));
//            logger.info("La delega ha lo stato Attiva");
//        } catch (TimeoutException e) {
//            Assertions.fail("La delega NON ha lo stato Attiva con errore: " + e.getMessage());
//        }
//    }
    public void controlloStatoAttiva(String ragioneSociale) {
        String xpathStatoAttiva = "//tr[contains(td/p, '" + ragioneSociale + "')]//span[contains(., 'Attiva')]";
        By statoAttivaLocator = By.xpath(xpathStatoAttiva);
        getWebDriverWait(30)
                .withMessage("La delega NON ha lo stato Attiva per: " + ragioneSociale)
                .until(ExpectedConditions.visibilityOfElementLocated(statoAttivaLocator));
        logger.info("La delega ha lo stato Attiva per: " + ragioneSociale);
    }


    //    public void clickNonAssegnaGruppo() {
//        nonGruppoRadioButton = driver.findElement(By.id("associate-no-group"));
//        getWebDriverWait(30)
//                .withMessage("Il pulsante radiobutton 'Non assegnare ad un gruppo' non è visibile")
//                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("associate-form-group"))));
//        nonGruppoRadioButton.click();
//    }
    public void clickNonAssegnaGruppo() {
        By nonGruppoLocator = By.id("associate-no-group");
        By formGroupLocator = By.id("associate-form-group");
        nonGruppoRadioButton = getWebDriverWait(30)
                .withMessage("Il radio button 'Non assegnare ad un gruppo' non è visibile o cliccabile")
                .until(ExpectedConditions.presenceOfElementLocated(nonGruppoLocator));
        getWebDriverWait(30)
                .withMessage("Il form di gruppo non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(formGroupLocator));
        nonGruppoRadioButton.click();
        logger.info("Click sul radio button 'Non assegnare ad un gruppo' riuscito");
    }


    //    public void clickOpzioneRifiuta() {
//        opzioneRifiuta = driver.findElement(By.id("reject-delegation-button"));
//        opzioneRifiuta.click();
//    }
    public void clickOpzioneRifiuta() {
        By rejectButtonLocator = By.id("reject-delegation-button");
        opzioneRifiuta = getWebDriverWait(10)
                .withMessage("Il bottone 'Rifiuta delega' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(rejectButtonLocator));
        opzioneRifiuta.click();

        logger.info("Click sul bottone 'Rifiuta delega' eseguito correttamente");
    }


    //    public void clickBottoneRifiuta() {
//        rifiutaButton = driver.findElement(By.id("dialog-confirm-button"));
//        rifiutaButton.click();
//    }
    public void clickBottoneRifiuta() {
        By rifiutaButtonLocator = By.id("dialog-confirm-button");
        rifiutaButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Rifiuta' nel dialog non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(rifiutaButtonLocator));
        rifiutaButton.click();

        logger.info("Click sul bottone 'Rifiuta' eseguito correttamente");
    }


    //    public void waitLoadPopUpRevoca() {
//        try {
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@aria-labelledby='confirmation-dialog-title']"))));
//            logger.info("Si visualizza il pop-up rifiuta delega");
//        } catch (TimeoutException e) {
//            Assertions.fail("Non si visualizza il pop-up rifiuta delega con errore: " + e.getMessage());
//        }
//    }
    public void waitLoadPopUpRevoca() {
        By popUpRevocaLocator = By.xpath("//div[@aria-labelledby='confirmation-dialog-title']");
        getWebDriverWait(30)
                .withMessage("Il pop-up 'Rifiuta delega' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(popUpRevocaLocator));
        logger.info("Si visualizza correttamente il pop-up rifiuta delega");
    }


    //    public void clickOpzioneModifica() {
//        try {
//            getWebDriverWait(30).withMessage("il pulsante modifica delega non é presente").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("update-delegation-button"))));
//            opzioneModifica = driver.findElement(By.id("update-delegation-button"));
//            opzioneModifica.click();
//        } catch (TimeoutException e) {
//            Assertions.fail(" errore: " + e.getMessage());
//        }
//    }
    public void clickOpzioneModifica() {
        By modificaButtonLocator = By.id("update-delegation-button");
        opzioneModifica = getWebDriverWait(30)
                .withMessage("Il pulsante 'Modifica delega' non è presente o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(modificaButtonLocator));
        opzioneModifica.click();
        logger.info("Click sul pulsante 'Modifica delega' eseguito correttamente");
    }

    //    public void waitLoadPopUpModifica() {
//        try {
//            getWebDriverWait(30).withMessage("Il titolo del pop-up non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-title"))));
//            logger.info("Si visualizza correttamente il pop-up");
//        } catch (TimeoutException e) {
//            Assertions.fail("NON Si visualizza correttamente il pop-up con errore: " + e.getMessage());
//        }
//    }
    public void waitLoadPopUpModifica() {
        By popUpTitleLocator = By.id("dialog-title");
        getWebDriverWait(30)
                .withMessage("Il titolo del pop-up non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(popUpTitleLocator));
        logger.info("Si visualizza correttamente il pop-up");
    }


    public boolean verificaPresenzaGruppo(String ragioneSociale) {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(td/p, '" + ragioneSociale + "')]//span[contains(text(),'Test gruppi')]")));
            return true;
        } catch (TimeoutException e) {
            logger.info("gruppo non presente");
            return false;
        }

    }

    //    public void inserireGruppoDelegante() {
//        getWebDriverWait(30).withMessage("Il campo cerca gruppo non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("groups"))));
//        searchGroupTextField = driver.findElement(By.id("groups"));
//        searchGroupTextField.click();
//
//        getWebDriverWait(30).withMessage("l'opzione gruppo non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("groups-option-0"))));
//        groupOption = driver.findElement(By.id("groups-option-0"));
//        groupOption.click();
//    }
    public void inserireGruppoDelegante() {
        By groupFieldLocator = By.id("groups");
        By firstOptionLocator = By.id("groups-option-0");
        searchGroupTextField = getWebDriverWait(30)
                .withMessage("Il campo 'Cerca gruppo' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(groupFieldLocator));
        searchGroupTextField.click();
        groupOption = getWebDriverWait(30)
                .withMessage("L'opzione gruppo non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(firstOptionLocator));
        groupOption.click();
        logger.info("Selezione del gruppo delegante avvenuta correttamente");
    }


    //    public void inserimentoCodiceDelegaACaricoDellImpresaAPI(String codiceDelega) {
//        String[] codiciDelega = codiceDelega.split("");
//        for (int i = 0; i < 5; i++) {
//            String xpathBy = "code-input-" + i;
//            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.id(xpathBy)));
//            By codiceDelegaInputBy = By.id(xpathBy);
//            WebElement codiceDelegaInput = driver.findElement(codiceDelegaInputBy);
//            codiceDelegaInput.sendKeys(codiciDelega[i]);
//        }
//
//    }
    public void inserimentoCodiceDelegaACaricoDellImpresaAPI(String codiceDelega) {
        String[] codiciDelega = codiceDelega.split("");
        for (int i = 0; i < 5; i++) {
            WebElement codiceDelegaInput = driver.findElement(By.xpath("//div[@data-testid='dialog-content']//input"));
            codiceDelegaInput.sendKeys(codiciDelega[i]);
        }
        logger.info("Inserimento del codice delega completato");
    }

    public boolean siVisualizzaUnaDelegaPG() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("tab-1")));
            element(By.id("tab-1")).click();
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@data-testid='delegationMenuIcon']")));
            logger.info("Trovato correttamente almeno una delega");
            return true;
        } catch (TimeoutException e) {
            logger.warn("Deleghe NON trovate con errore: {}", e.getMessage());
            return false;
        }
    }

    //    public void checkAlertWrongDelegationCode() {
//        try {
//            getWebDriverWait(5).withMessage("Alert non visualizzato correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("alert-api-status"))));
//            logger.info("Alert visualizzato correttamente in pagina");
//            logger.info("Alert chiusa");
//        } catch (TimeoutException e) {
//            Assertions.fail("Alert non visualizzato con errore: " + e.getMessage());
//        }
//    }
    public void checkAlertWrongDelegationCode() {
        By alertLocator = By.id("alert-api-status");
        // Attendo che l'alert sia visibile
        getWebDriverWait(5)
                .withMessage("L'alert del codice delega errato non è visualizzato correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(alertLocator));
        logger.info("Alert chiusa");
    }


    //    public void clickButtonIndietroDaAssegnaGruppo() {
//        try {
//            logger.info("Si clicca sul bottone indietro per tornare al pop-up di inserimento codice delega");
//            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@data-testid='groupCancelButton']"))));
//            WebElement buttonIndietroPopUpAssegnaGruppo = driver.findElement(By.xpath("//button[@data-testid='groupCancelButton']"));
//            buttonIndietroPopUpAssegnaGruppo.click();
//            logger.info("Bottone indietro cliccato");
//        } catch (TimeoutException e) {
//            Assertions.fail("Bottone non visualizzato con errore: " + e.getMessage());
//        }
//    }
    public void clickButtonIndietroDaAssegnaGruppo() {
        By backButtonLocator = By.xpath("//button[@data-testid='groupCancelButton']");
        logger.info("Si clicca sul bottone indietro per tornare al pop-up di inserimento codice delega");
        WebElement buttonIndietroPopUpAssegnaGruppo = getWebDriverWait(10)
                .withMessage("Il bottone 'Indietro' nel pop-up di assegnazione gruppo non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(backButtonLocator));
        buttonIndietroPopUpAssegnaGruppo.click();
        logger.info("Bottone 'Indietro' cliccato correttamente");
    }


    //    public void clickButtonIndietroCloseModale() {
//        logger.info("Si clicca sul bottone indietro per tornare al tabella deleghe");
//        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@data-testid='codeCancelButton']"))));
//        WebElement buttonIndietroPopUpAssegnaGruppo = driver.findElement(By.xpath("//button[@data-testid='codeCancelButton']"));
//        buttonIndietroPopUpAssegnaGruppo.click();
//        logger.info("Bottone indietro cliccato");
//    }
    public void clickButtonIndietroCloseModale() {
        By backButtonLocator = By.xpath("//button[@data-testid='codeCancelButton']");
        logger.info("Si clicca sul bottone indietro per tornare alla tabella deleghe");
        WebElement buttonIndietroPopUpAssegnaGruppo = getWebDriverWait(10)
                .withMessage("Il bottone 'Indietro' nel pop-up non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(backButtonLocator));
        buttonIndietroPopUpAssegnaGruppo.click();
        logger.info("Bottone 'Indietro' cliccato correttamente");
    }


    public void checkTextboxCodiceSonoRosse() {
        final String textboxIsInvalid = "true";
        boolean isInvalid = true;
        int attempt = 1;
        int maxAttempts = 7;
        String stateInput = driver.findElement(By.xpath("//div[@data-testid='dialog-content']//input")).getAttribute("aria-invalid");
        while (attempt < maxAttempts) {

            if (!(textboxIsInvalid.equals(stateInput))) {
                isInvalid = false;
                webTool.waitTime(5);
                logger.info("Tentativo n° {} per l'attesa della formattazione...", attempt);
                attempt++;
            }
            else {
                break;
            }
        }
        if (isInvalid) {
            logger.info("Textbox di input codice delega invalido");
        } else {
            logger.error("Almeno una textbox di input codice delega non in stato invalido");
            Assertions.fail("Almeno una textbox di input codice delega non in stato invalido");
        }
    }

    //    public void checkErroreInSelezioneGruppo() {
//        driver.findElement(By.id("input-group")).sendKeys("Errore Test");
//        try {
//            getWebDriverWait(10).withMessage("testo di errore non trovato").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(text(),'Nessun gruppo trovato')]"))));
//            driver.findElement(By.id("input-group")).sendKeys(Keys.ESCAPE);
//            logger.info("testo di errore gruppo trovato con successo");
//        } catch (TimeoutException e) {
//            Assertions.fail("errore in cattura testo di errore ricerca gruppo per assegnazione con errore:" + e.getMessage());
//        }
//
//    }
    public void checkErroreInSelezioneGruppo() {
        By groupInputLocator = By.id("input-group");
        By errorTextLocator = By.xpath("//div[contains(text(),'Nessun gruppo trovato')]");
        WebElement groupInput = getWebDriverWait(10)
                .withMessage("Il campo 'input-group' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(groupInputLocator));
        groupInput.sendKeys("Errore Test");
        getWebDriverWait(10)
                .withMessage("Testo di errore 'Nessun gruppo trovato' non trovato")
                .until(ExpectedConditions.visibilityOfElementLocated(errorTextLocator));
        groupInput.sendKeys(Keys.ESCAPE);
        logger.info("Testo di errore gruppo trovato con successo");
    }


    //    public void clickIndietroInInserimentoCodiceVerifica() {
//        getWebDriverWait(10).withMessage("bottone indietro in inserimento codice non trovato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("code-cancel-button"))));
//        buttonIndietroCodiceDiVerifica = driver.findElement(By.id("code-cancel-button"));
//        buttonIndietroCodiceDiVerifica.click();
//    }
    public void clickIndietroInInserimentoCodiceVerifica() {
        By backButtonLocator = By.id("code-cancel-button");
        buttonIndietroCodiceDiVerifica = getWebDriverWait(10)
                .withMessage("Bottone 'Indietro' in inserimento codice verifica non trovato o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(backButtonLocator));
        buttonIndietroCodiceDiVerifica.click();
        logger.info("Click sul bottone 'Indietro' in inserimento codice verifica eseguito correttamente");
    }

    //    public void checkTabellaDelegheACaricoDellImpresa() {
//        webTool.waitTime(10);
//        WebElement menuDelega = driver.findElement(By.xpath("//table[@id='notifications-table']//following-sibling::td//button[@data-testid='delegationMenuIcon']"));
//        WebElement colonnaNome = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Nome')]"));
//        WebElement colonnaInizioDelega = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Inizio delega')]"));
//        WebElement colonnaFineDelega = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Fine delega')]"));
//        WebElement colonnaPermessi = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Permessi')]"));
//        WebElement colonnaGruppi = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Gruppi')]"));
//        WebElement colonnaStato = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Stato')]"));
//        tabelleDelleDelegheACaricoDellImpresa = driver.findElement(By.id("notifications-table"));
//        try {
//            getWebDriverWait(10).withMessage("tabella deleghe dell impresa non caricata correttamente").until(ExpectedConditions.visibilityOf(tabelleDelleDelegheACaricoDellImpresa));
//            getWebDriverWait(10).withMessage("colonna nome non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaNome));
//            getWebDriverWait(10).withMessage("colonna inizio delega non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaInizioDelega));
//            getWebDriverWait(10).withMessage("colonna fine delega non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaFineDelega));
//            getWebDriverWait(10).withMessage("colonna permessi non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaPermessi));
//            getWebDriverWait(10).withMessage("colonna gruppi non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaGruppi));
//            getWebDriverWait(10).withMessage("colonna stato non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaStato));
//            getWebDriverWait(10).withMessage("menu non caricato correttamente").until(ExpectedConditions.visibilityOf(menuDelega));
//        } catch (TimeoutException e) {
//            Assertions.fail("tabella deleghe a carico dell impresa non caricata correttamente" + e.getMessage());
//        }
//
//    }
    public void checkTabellaDelegheACaricoDellImpresa() {
        webTool.waitTime(10);

        By tableLocator = By.id("notifications-table");
        By menuDelegaLocator = By.xpath("//table[@id='notifications-table']//following-sibling::td//button[@data-testid='delegationMenuIcon']");
        By colonnaNomeLocator = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Nome')]");
        By colonnaInizioLocator = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Inizio delega')]");
        By colonnaFineLocator = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Fine delega')]");
        By colonnaPermessiLocator = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Permessi')]");
        By colonnaGruppiLocator = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Gruppi')]");
        By colonnaStatoLocator = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Stato')]");

        try {
            tabelleDelleDelegheACaricoDellImpresa = getWebDriverWait(10)
                    .withMessage("Tabella deleghe dell'impresa non caricata correttamente")
                    .until(ExpectedConditions.visibilityOfElementLocated(tableLocator));
            getWebDriverWait(10).withMessage("Colonna 'Nome' non caricata correttamente")
                    .until(ExpectedConditions.visibilityOfElementLocated(colonnaNomeLocator));
            getWebDriverWait(10).withMessage("Colonna 'Inizio delega' non caricata correttamente")
                    .until(ExpectedConditions.visibilityOfElementLocated(colonnaInizioLocator));
            getWebDriverWait(10).withMessage("Colonna 'Fine delega' non caricata correttamente")
                    .until(ExpectedConditions.visibilityOfElementLocated(colonnaFineLocator));
            getWebDriverWait(10).withMessage("Colonna 'Permessi' non caricata correttamente")
                    .until(ExpectedConditions.visibilityOfElementLocated(colonnaPermessiLocator));
            getWebDriverWait(10).withMessage("Colonna 'Gruppi' non caricata correttamente")
                    .until(ExpectedConditions.visibilityOfElementLocated(colonnaGruppiLocator));
            getWebDriverWait(10).withMessage("Colonna 'Stato' non caricata correttamente")
                    .until(ExpectedConditions.visibilityOfElementLocated(colonnaStatoLocator));
            getWebDriverWait(10).withMessage("Menu delega non caricato correttamente")
                    .until(ExpectedConditions.visibilityOfElementLocated(menuDelegaLocator));
        } catch (TimeoutException e) {
            Assertions.fail("Tabella deleghe a carico dell'impresa non caricata correttamente: " + e.getMessage());
        }
    }


    //    public void checkErroreInInserimentoCodice() {
//        getWebDriverWait(20).withMessage("errore in inserimento codice errato non trovato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("error-alert"))));
//    }
    public void checkErroreInInserimentoCodice() {
        By errorAlertLocator = By.id("error-alert");
        getWebDriverWait(20)
                .withMessage("Errore in inserimento codice errato non trovato")
                .until(ExpectedConditions.visibilityOfElementLocated(errorAlertLocator));
    }

}