package it.pn.frontend.e2e.section.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;


public class LeTueDelegheSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("LeTueDelegheSection");


    @FindBy(xpath = "//input[@value='PF']")
    WebElement personaFisicaRadioButton;

    @FindBy(id = "side-item-Notifiche")
    WebElement sideItemNotificheButton;

    @FindBy(id = "nome")
    WebElement inputNome;

    @FindBy(id = "cognome")
    WebElement inputCognome;

    @FindBy(id = "create-button")
    WebElement inviaLaRichiestaButton;

    @FindBy(id = "expirationDate")
    WebElement dataTermineDelegaInput;

    @FindBy(xpath = "//div[contains(@id, 'digit-')]")
    List<WebElement> codiceVerificaList;

    @FindBy(id = "codiceFiscale")
    WebElement codiceFiscaleInput;

    @FindBy(xpath = "//input[@value='entiSelezionati']")
    WebElement soloEntiSelezionatiRadioButton;

    @FindBy(id = "enti")
    WebElement enteElementInput;

    @FindBy(id = "courtesy-page-button")
    WebElement tornaDelegheButton;

    @FindBy(id = "accept-button")
    WebElement accettaButton;

    @FindBy(id = "code-confirm-button")
    WebElement accettaPopUpButton;

    @FindBy(id = "code-cancel-button")
    WebElement indietroPopUpButton;

    @FindBy(id = "Deleghe-page")
    WebElement deleghePageTitle;

    @FindBy(id = "subtitle-page")
    WebElement deleghePageSubtitle;

    @FindBy(id = "add-delegation-button")
    WebElement aggiungiDelegaButton;

    @FindBy(xpath = "//span[contains(text(),'Nome')]")
    WebElement nomeDelegaField;

    @FindBy(xpath = "//th[contains(text(),'Inizio delega')]")
    WebElement inizioDelegaField;

    @FindBy(xpath = "//span[contains(text(),'Fine delega')]")
    WebElement fineDelegaField;

    @FindBy(xpath = "//th[contains(text(),'Permessi')]")
    WebElement permessiDelegaField;

    // find all row with data-testid="delegatorsTable.body.row"
    @FindBy(xpath = "//tr[@data-testid='delegatorsTable.body.row']")
    List<WebElement> delegatorsTableRows;


    private WebTool webTool;

    public LeTueDelegheSection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }


    public void waitNuovaDelegaSection() {
        try {
            WebElement leTueDeleghePageTitle = driver.findElement(By.id("Aggiungi una delega-page"));
            inputNome = driver.findElement(By.id("nome"));
            codiceFiscaleInput = driver.findElement(By.id("codiceFiscale"));
            inputCognome = driver.findElement(By.id("cognome"));
            getWebDriverWait(10).withMessage("Il titolo della pagina non è  visibile").until(ExpectedConditions.visibilityOf(leTueDeleghePageTitle));
            getWebDriverWait(10).withMessage("L'input nome non è visibile").until(ExpectedConditions.visibilityOf(inputNome));
            getWebDriverWait(10).withMessage("L'input codice fiscale non è visibile").until(ExpectedConditions.visibilityOf(codiceFiscaleInput));
            getWebDriverWait(10).withMessage("L'input cognome non è visibile").until(ExpectedConditions.visibilityOf(inputCognome));
            logger.info("Le tue deleghe page caricata");
        } catch (TimeoutException e) {
            logger.error("Le tue deleghe page non caricata con errore :" + e.getMessage());
            Assertions.fail("Le tue deleghe page non caricata con errore :" + e.getMessage());
        }
    }

    public void selectPersonaFisicaRadioButton() {
        logger.info("click radio button persona fisica");
        personaFisicaRadioButton = driver.findElement(By.xpath("//input[@value='PF']"));
        personaFisicaRadioButton.click();
    }

    public void insertNomeCognome(String nome, String cognome) {
        logger.info("inserimento nome");
        inputNome = driver.findElement(By.id("nome"));
        inputNome.sendKeys(nome);
        logger.info("inserimento cognome");
        inputCognome = driver.findElement(By.id("cognome"));
        inputCognome.sendKeys(cognome);
    }

    public void clickSulBottoneInviaRichiesta() {

        getWebDriverWait(10).withMessage("Invia richiesta button non è cliccabile o non trovato").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("create-button"))));
        inviaLaRichiestaButton = driver.findElement(By.id("create-button"));
        logger.info("click su invia richiesta");
        inviaLaRichiestaButton.click();
        getWebDriverWait(40).withMessage("Torna deleghe button non è cliccabile o non è trovato").until(ExpectedConditions.elementToBeClickable(By.id("courtesy-page-button")));
        element(By.id("courtesy-page-button")).click();
    }

    public boolean verificareCheLaDataSiaCorretta() {
        dataTermineDelegaInput = driver.findElement(By.id("expirationDate"));
        String dataDaVerificare = dataTermineDelegaInput.getAttribute("value");
        dataDaVerificare = dataDaVerificare.replace("/", "-");
        String[] date = dataDaVerificare.split("-");
        dataDaVerificare = date[2] + "-" + date[1] + "-" + date[0];
        LocalDate dataInserita = LocalDate.parse(dataDaVerificare);
        LocalDate dataCorretta = LocalDate.now();
        dataCorretta = dataCorretta.plusDays(1);
        return dataCorretta.equals(dataInserita);
    }

    public String salvataggioCodiceVerifica() {
        codiceVerificaList = driver.findElements(By.xpath("//div[contains(@id, 'digit-')]"));
        StringBuilder codiceVerifica = new StringBuilder();
        for (WebElement webElement : codiceVerificaList) {
            codiceVerifica.append(webElement.getText());
        }
        return codiceVerifica.toString();
    }

    public void inserireCF(String cf) {
        codiceFiscaleInput = driver.findElement(By.id("codiceFiscale"));
        codiceFiscaleInput.sendKeys(cf);
    }

    public void selectSoloEntiSelezionati() {
        logger.info("click checkbox solo enti selezionati");
        soloEntiSelezionatiRadioButton = driver.findElement(By.xpath("//input[@value='entiSelezionati']"));
        soloEntiSelezionatiRadioButton.click();
    }

    public void selezionaUnEnte(String ente) {
        getWebDriverWait(10).withMessage("input ente non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("enti"))));
        enteElementInput = driver.findElement(By.id("enti"));
        logger.info("inserimento dati ente");
        enteElementInput.click();
        enteElementInput.sendKeys(ente);

        // select menu;

        getWebDriverWait(10).withMessage("il menu della selezione ente non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role='presentation']"))));
        WebElement menuEntiOptionBy = driver.findElement(By.xpath("//div[@role='presentation']"));
        js().executeScript("arguments[0].click()", menuEntiOptionBy);

        //click on option 0

        getWebDriverWait(10).withMessage("L'ente " + ente + " non è cliccabile o non è presente").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("enti-option-0"))));
        WebElement comuneOptionBy = driver.findElement(By.id("enti-option-0"));
        js().executeScript("arguments[0].click()", comuneOptionBy);
    }

    public void clickInviaRichiesta() {
        inviaLaRichiestaButton = driver.findElement(By.id("create-button"));
        getWebDriverWait(30).withMessage("il bottone invia richiesta non è cliccabile").until(ExpectedConditions.elementToBeClickable(inviaLaRichiestaButton));
        logger.info("click pulsante invia richiesta");

        inviaLaRichiestaButton.click();
    }

    public String messaggioDiErrore() {
        WebElement errorMessageBy = driver.findElement(By.xpath("//div[contains(@class,'MuiAlert-message')]/div"));
        getWebDriverWait(30).withMessage("l'alert message non è visibile").until(ExpectedConditions.visibilityOf(errorMessageBy));
        logger.info("Messaggio di errore trovato");
        return errorMessageBy.getText();
    }

    public void messaggioDiErroreDelegaPresente() {
        try {
            webTool.waitTime(5);
            getWebDriverWait(5).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(text(),'Delega già presente')]"))));
            logger.info("Il messaggio di errore viene visualizzato");
        } catch (TimeoutException e) {
            logger.error("Il messaggio di errore NON viene visualizzato con errore: " + e.getMessage());
            Assertions.fail("Il messaggio di errore NON viene visualizzato con errore: " + e.getMessage());
        }
    }

    public void clickOpzioneAccetta() {
        try {
            getWebDriverWait(40).withMessage("Il bottone clickOpzioneAccetta blocco try").until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id='accept-button']"))));
            By acceptDelegaButton = By.xpath("//*[@id='accept-button']");
            element(acceptDelegaButton).click();
        } catch (NoSuchElementException | TimeoutException e) {
            getWebDriverWait(40).withMessage("Il bottone clickOpzioneAccetta blocco catch").until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id='accept-button']"))));
            driver.findElement(By.xpath("//*[@id='accept-button']")).click();
            logger.error("Conferma Dati Spid DE Page non caricata con errore : " + e.getMessage());
            Assertions.fail("Conferma Dati Spid DE Page non caricata con errore : " + e.getMessage());
        }
    }

    public void waitPopUpLoad() {
        try {
            WebElement titlePopUpBy = driver.findElement(By.xpath("//h2[@id='dialog-title']"));
            getWebDriverWait(15).until(ExpectedConditions.visibilityOf(titlePopUpBy));
            logger.info("Il pop-up per accettare la delega visualizzato correttamente");
        } catch (TimeoutException e) {
            logger.error("Il pop-up per accettare la delega NON visualizzato correttamente con errore: " + e.getMessage());
            Assertions.fail("Il pop-up per accettare la delega NON visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public void inserireCodiceDelega(String codiceDelega) {
        String[] codiciDelega = codiceDelega.split("");
        for (int i = 0; i < 5; i++) {
            String xpathBy = "code-input-" + i;
            By codiceDelegaInputBy = By.id(xpathBy);
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(codiceDelegaInputBy));
            WebElement codiceDelegaInput = driver.findElement(codiceDelegaInputBy);
            codiceDelegaInput.sendKeys(codiciDelega[i]);
        }

    }

    public void clickNotificheDelegatePF() {
        sideItemNotificheButton = driver.findElement(By.id("side-item-Notifiche"));
        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(sideItemNotificheButton));
        sideItemNotificheButton.click();
        WebElement notificheDelegateButton = driver.findElement(By.id("side-item-Gaio Giulio Cesare"));
        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(notificheDelegateButton));
        js().executeScript("arguments[0].click()", notificheDelegateButton);
        logger.info("Si clicca correttamente sulla voce notifiche delegate");

    }

    public String getTextCodiceSbagliato() {
        WebElement errorMessageBy = driver.findElement(By.id("codeModalErrorTitle"));
        getWebDriverWait(30).withMessage("il messaggio di errore per il codice sbagliato non è visibile").until(ExpectedConditions.visibilityOf(errorMessageBy));
        return errorMessageBy.getText();
    }

    public void clickAccettaButton() {
        accettaPopUpButton = driver.findElement(By.id("code-confirm-button"));
        accettaPopUpButton.click();
    }

    public void clickIndietroButton() {
        getWebDriverWait(5).withMessage("Il bottone indietr non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("code-cancel-button"))));
        driver.findElement(By.id("code-cancel-button")).click();
    }


    public void controlloStatoAttiva(String nome, String cognome) {
        try {
//            WebElement statoAttivaBy = driver.findElement(By.xpath("//table[@id='notifications-table']//td[.//div/p[contains(text(),'" + nome + " " + cognome + "')]]/following-sibling::td//div/div[@id='chip-status-success']"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='notifications-table']//td[.//div/p[contains(text(),'" + nome + " " + cognome + "')]]/following-sibling::td//div/div[@id='chip-status-success']")));
            logger.info("La delega ha lo stato Attiva");
        } catch (TimeoutException e) {
            logger.error("La delega NON ha lo stato Attiva con errore: " + e.getMessage());
            Assertions.fail("La delega NON ha lo stato Attiva con errore: " + e.getMessage());
        }
    }

    public void controlloEsistenzaDelega(String nome, String cognome) {
        try {
            delegatorsTableRows = driver.findElements(By.xpath("//tr[@data-testid='delegatorsTable.body.row']"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElements(delegatorsTableRows));
            WebElement delega = delegatorsTableRows
                    .stream()
                    .filter(row ->
                            row.getText().contains(nome + " " + cognome))
                    .findFirst()
                    .orElse(null);
            getWebDriverWait(5).until(ExpectedConditions.visibilityOf(delega));
//            By statoAttivaBy = By.xpath("//tr[@data-testid='delegatorsTable.body.row']//td[@scope='col' and div/p[contains(text(),'" + nome + " " + cognome + "')]]");
//            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoAttivaBy));
//            logger.info("La delega con nome " + nome + "  " + cognome + "è ancora presente");
        } catch (TimeoutException e) {
            logger.error("La delega non è presente con errore: " + e.getMessage());
            Assertions.fail("La delega non è presente con errore: " + e.getMessage());
        }
    }

    public boolean siVisualizzaIlTitolo() {
        deleghePageTitle = driver.findElement(By.id("Deleghe-page"));
        getWebDriverWait(10).withMessage("Il titolo della pagina Deleghe non è visibile ").until(ExpectedConditions.visibilityOf(deleghePageTitle));
        logger.info("check visualizzazione titolo pagina deleghe");
        return deleghePageTitle.isDisplayed();
    }

    public boolean siVisualizzaIlSottotitolo() {
        deleghePageSubtitle = driver.findElement(By.id("subtitle-page"));
        getWebDriverWait(10).withMessage("Il sotto titolo della pagina Deleghe non è visibile ").until(ExpectedConditions.visibilityOf(deleghePageSubtitle));
        logger.info("check visualizzazione sottotitolo pagina deleghe");
        return deleghePageSubtitle.isDisplayed();
    }

    public boolean siVisualizzaIlBottoneAggiungiUnaDelega() {
        aggiungiDelegaButton = driver.findElement(By.id("add-delegation-button"));
        getWebDriverWait(10).withMessage("Il bottone aggiungi Delega non è visibile ").until(ExpectedConditions.visibilityOf(aggiungiDelegaButton));
        logger.info("check visualizzazione pulsante aggiungi delega");
        return aggiungiDelegaButton.isDisplayed();
    }

    public boolean siVisualizzaIlNomeDelegato() {
        nomeDelegaField = driver.findElement(By.xpath("//span[contains(text(),'Nome')]"));
        getWebDriverWait(10).withMessage("Il nome Delega non è visibile ").until(ExpectedConditions.visibilityOf(nomeDelegaField));
        logger.info("check visualizzazione nome delega");
        return nomeDelegaField.isDisplayed();
    }


    public boolean siVisualizzaDataInizioDelega() {
        inizioDelegaField = driver.findElement(By.xpath("//th[contains(text(),'Inizio delega')]"));
        getWebDriverWait(30).withMessage("Inizio data  Delega non è visibile ").until(ExpectedConditions.visibilityOf(inizioDelegaField));
        logger.info("check visualizzazione inizio data delega");
        return inizioDelegaField.isDisplayed();
    }

    public boolean siVisualizzaDataFinoDelega() {
        fineDelegaField = driver.findElement(By.xpath("//span[contains(text(),'Fine delega')]"));
        getWebDriverWait(30).withMessage("Fine data  Delega non è visibile ").until(ExpectedConditions.visibilityOf(fineDelegaField));
        logger.info("check visualizzazione data fine delega");
        return fineDelegaField.isDisplayed();
    }

    public boolean siVisualizzaPermessiDelega() {
        permessiDelegaField = driver.findElement(By.xpath("//th[contains(text(),'Permessi')]"));
        getWebDriverWait(10).withMessage("I permessi della delega non sono visualizzati correttamente").until(ExpectedConditions.visibilityOf(permessiDelegaField));
        logger.info("check visualizzazione permessi delega");
        return permessiDelegaField.isDisplayed();
    }

    public boolean controlloPresenzaBottoneAccetta() {
        try {
            getWebDriverWait(30).withMessage("accetta button non visibile").until(ExpectedConditions.visibilityOfElementLocated(By.id("accept-button")));
            logger.info("Si visualizza il bottone accetta");
            return true;
        } catch (TimeoutException e) {
            logger.info("non si visualizza il bottone accetta");
            return false;
        }
    }

    public boolean verificaEsistenzaErroreCodiceSbagliato() {
        try {
            getWebDriverWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.id("codeModalErrorTitle")));
            logger.info("Errore codice sbagliato trovato");
            return true;
        } catch (TimeoutException e) {
            logger.info("errore non trovato");
            return false;
        }

    }

    public void checkIndicatoreNumerico() {
        try {
            webTool.waitTime(10);
            WebElement notificationNumber = driver.findElement(By.xpath("//*[@data-testid='notifications']"));
            getWebDriverWait(5).withMessage("La notifica con il indicatore non è visibile").until(ExpectedConditions.visibilityOf(notificationNumber));
        } catch (RuntimeException e) {
            logger.error("Il indicatore numerico di notifica non è visibile");
            Assertions.fail("Il indicatore numerico di notifica non è visibile");
        }
    }
}


