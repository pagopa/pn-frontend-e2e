package it.pn.frontend.e2e.section.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    @FindBy(id ="enti")
    WebElement enteElementInput;

    @FindBy(id = "courtesy-page-button")
    WebElement tornaDelegheButton;

    @FindBy(id = "accept-button")
    WebElement accettaButton;

    @FindBy(id = "code-confirm-button")
    WebElement accettaPopUpButton;

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


    public LeTueDelegheSection(WebDriver driver) {
        super(driver);
    }
    public void waitNuovaDelegaSection() {
        try {
            By leTueDeleghePageTitle = By.id("Aggiungi una delega-page");
            this.getWebDriverWait(40).withMessage("Il titolo della pagina non è  visibile").until(ExpectedConditions.visibilityOfElementLocated(leTueDeleghePageTitle));
            this.getWebDriverWait(40).withMessage("L'input nome non è visibile").until(ExpectedConditions.visibilityOf(this.inputNome));
            this.getWebDriverWait(40).withMessage("L'input codice fiscale non è visibile").until(ExpectedConditions.visibilityOf(this.codiceFiscaleInput));
            this.getWebDriverWait(40).withMessage("L'input cognome non è visibile").until(ExpectedConditions.visibilityOf(this.inputCognome));
            logger.info("Le tue deleghe page caricata");
        } catch (TimeoutException e) {
            logger.error("Le tue deleghe page non caricata con errore :" + e.getMessage());
            Assert.fail("Le tue deleghe page non caricata con errore :" + e.getMessage());
        }
    }

    public void selectPersonaFisicaRadioButton() {
        logger.info("click radio button persona fisica");
        this.personaFisicaRadioButton.click();
    }

    public void insertNomeCognome(String nome, String cognome) {
        logger.info("inserimento nome");
        this.inputNome.sendKeys(nome);
        logger.info("inserimento cognome");
        this.inputCognome.sendKeys(cognome);
    }

    public void clickSulBottoneInviaRichiesta() {
        this.getWebDriverWait(30).withMessage("Invia richiesta button non è cliccabile o non trovato").until(ExpectedConditions.elementToBeClickable(this.inviaLaRichiestaButton));
        logger.info("click su invia richiesta");
        this.inviaLaRichiestaButton.click();
        this.getWebDriverWait(30).withMessage("Torna deleghe button non è cliccabile o non è trovato").until(ExpectedConditions.elementToBeClickable(this.tornaDelegheButton));
        this.tornaDelegheButton.click();
    }

    public boolean verificareCheLaDataSiaCorretta() {
        String dataDaVerificare = this.dataTermineDelegaInput.getAttribute("value");
        dataDaVerificare = dataDaVerificare.replace("/", "-");
        String[] date = dataDaVerificare.split("-");
        dataDaVerificare = date[2] + "-" + date[1] + "-" + date[0];
        LocalDate dataInserita = LocalDate.parse(dataDaVerificare);
        LocalDate dataCorretta = LocalDate.now();
        dataCorretta = dataCorretta.plusDays(1);
        return dataCorretta.equals(dataInserita);
    }

    public String salvataggioCodiceVerifica() {
        StringBuilder codiceVerifica = new StringBuilder();
        for (WebElement webElement : this.codiceVerificaList) {
            codiceVerifica.append(webElement.getText());
        }
        return codiceVerifica.toString();
    }

    public void inserireCF(String cf) {
        this.codiceFiscaleInput.sendKeys(cf);
    }

    public void selectSoloEntiSelezionati() {
        logger.info("click checkbox solo enti selezionati");
        this.soloEntiSelezionatiRadioButton.click();
    }

    public void selezionaUnEnte(String ente) {
        this.getWebDriverWait(30).withMessage("input ente non è visibile").until(ExpectedConditions.visibilityOf(this.enteElementInput));
        logger.info("inserimento dati ente");
        this.enteElementInput.click();
        this.enteElementInput.sendKeys(ente);

        // select menu;
        By menuEntiOptionBy = By.xpath("//div[@role='presentation']");
        this.getWebDriverWait(60).withMessage("il menu della selezione ente non è visibile").until(ExpectedConditions.visibilityOfElementLocated(menuEntiOptionBy));
        WebElement menuEntiOption = this.driver.findElement(menuEntiOptionBy);
        this.js().executeScript("arguments[0].click()",menuEntiOption);

        //click on option 0
        By comuneOptionBy = By.id("enti-option-0");
        this.getWebDriverWait(60).withMessage("L'ente "+ente+" non è cliccabile o non è presente").until(ExpectedConditions.elementToBeClickable(comuneOptionBy));
        WebElement comuneOption = this.driver.findElement(comuneOptionBy);
        this.js().executeScript("arguments[0].click()", comuneOption);
    }

    public void clickInviaRichiesta() {
        getWebDriverWait(30).withMessage("il bottone invia richiesta non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.inviaLaRichiestaButton));
        logger.info("click pulsante invia richiesta");
        this.inviaLaRichiestaButton.click();
    }

    public String messaggioDiErrore() {
            By errorMessageBy = By.xpath("//div[contains(@class,'MuiAlert-message')]/div");
            this.getWebDriverWait(30).withMessage("l'alert message non è visibile").until(ExpectedConditions.visibilityOfElementLocated(errorMessageBy));
            WebElement errorMessage = driver.findElement(errorMessageBy);
            logger.info("Messaggio di errore trovato");
            return errorMessage.getText();
    }

    public void messaggioDiErroreDelegaPresente() {
        try {
            By messaggioErrore = By.xpath("//div[contains(text(),'Delega già presente')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(messaggioErrore));
            logger.info("Il messaggio di errore viene visualizzato");
        }catch (TimeoutException e){
            logger.error("Il messaggio di errore NON viene visualizzato con errore: "+e.getMessage());
            Assert.fail("Il messaggio di errore NON viene visualizzato con errore: "+e.getMessage());
        }
    }

    public void clickOpzioneAccetta() {
        this.accettaButton.click();
    }

    public void waitPopUpLoad() {
        try {
            By titlePopUpBy = By.xpath("//h2[@id = 'dialog-title']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePopUpBy));
            logger.info("Il pop-up per accettare la delega visualizzato correttamente");
        }catch (TimeoutException e){
            logger.error("Il pop-up per accettare la delega NON visualizzato correttamente con errore: "+e.getMessage());
            Assert.fail("Il pop-up per accettare la delega NON visualizzato correttamente con errore: "+e.getMessage());
        }
    }

    public void inserireCodiceDelega(String codiceDelega) {
        String[] codiciDelega = codiceDelega.split("");
        for (int i = 0; i < 5; i++){
            String xpathBy = "code-input-"+i;
            By codiceDelegaInputBy = By.id(xpathBy);
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(codiceDelegaInputBy));
            WebElement codiceDelegaInput = driver.findElement(codiceDelegaInputBy);
            codiceDelegaInput.sendKeys(codiciDelega[i]);
        }

    }

    public String getTextCodiceSbagliato(){
        By errorMessageBy = By.xpath("//div[@data-testid='CodeModal error title']");
        WebElement testoCodiceSbagliato = this.element(errorMessageBy);
        this.getWebDriverWait(30).withMessage("il messaggio di errore per il codice sbagliato non è visibile").until(ExpectedConditions.visibilityOf(this.element(errorMessageBy)));
        return testoCodiceSbagliato.getText();
    }

    public void clickAccettaButton() {
        this.accettaPopUpButton.click();
    }

    public void controlloStatoAttiva(String nome, String cognome) {
        try {
            By statoAttivaBy = By.xpath("//table[@id='notifications-table']//td[.//div/p[contains(text(),'" + nome + " " + cognome + "')]]/following-sibling::td//div/div[@id='chip-status-success']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoAttivaBy));
            logger.info("La delega ha lo stato Attiva");
        }catch (TimeoutException e){
            logger.error("La delega NON ha lo stato Attiva con errore: "+e.getMessage());
            Assert.fail("La delega NON ha lo stato Attiva con errore: "+e.getMessage());
        }
    }

    public void controlloEsistenzaDelega(String nome, String cognome) {
        try {
            By statoAttivaBy = By.xpath("//tr[@data-testid='delegatorsTable.body.row']//td[@scope='col' and div/p[contains(text(),'"+nome + " "+ cognome +"')]]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoAttivaBy));
            logger.info("La delega con nome "+nome+"  "+cognome+"è ancora presente");
        }catch (TimeoutException e){
            logger.error("La delega non è presente con errore: "+e.getMessage());
            Assert.fail("La delega non è presente con errore: "+e.getMessage());
        }
    }

    public boolean siVisualizzaIlTitolo() {
        this.getWebDriverWait(30).withMessage("Il titolo della pagina Deleghe non è visibile ").until(ExpectedConditions.visibilityOf(this.deleghePageTitle));
        logger.info("check visualizzazione titolo pagina deleghe");
        return this.deleghePageTitle.isDisplayed();}

    public boolean siVisualizzaIlSottotitolo() {
        this.getWebDriverWait(30).withMessage("Il sotto titolo della pagina Deleghe non è visibile ").until(ExpectedConditions.visibilityOf(this.deleghePageSubtitle));
        logger.info("check visualizzazione sottotitolo pagina deleghe");
        return this.deleghePageSubtitle.isDisplayed();}

    public boolean siVisualizzaIlBottoneAggiungiUnaDelega() {
        this.getWebDriverWait(30).withMessage("Il bottone aggiungi Delega non è visibile ").until(ExpectedConditions.visibilityOf(this.aggiungiDelegaButton));
        logger.info("check visualizzazione pulsante aggiungi delega");
        return this.aggiungiDelegaButton.isDisplayed();}

    public boolean siVisualizzaIlNomeDelegato() {
        this.getWebDriverWait(30).withMessage("Il nome Delega non è visibile ").until(ExpectedConditions.visibilityOf(this.nomeDelegaField));
        logger.info("check visualizzazione nome delega");
        return this.nomeDelegaField.isDisplayed();}


    public boolean siVisualizzaDataInizioDelega() {
        this.getWebDriverWait(30).withMessage("Inizio data  Delega non è visibile ").until(ExpectedConditions.visibilityOf(this.inizioDelegaField));
        logger.info("check visualizzazione inizio data delega");
        return this.inizioDelegaField.isDisplayed();
    }

    public boolean siVisualizzaDataFinoDelega() {
        this.getWebDriverWait(30).withMessage("Fine data  Delega non è visibile ").until(ExpectedConditions.visibilityOf(this.fineDelegaField));
        logger.info("check visualizzazione data fine delega");
        return this.fineDelegaField.isDisplayed();
    }

    public boolean siVisualizzaPermessiDelega() {
        logger.info("check visualizzazione permessi delega");
        return  this.permessiDelegaField.isDisplayed();
    }

    public boolean controlloPresenzaBottoneAccetta() {
        try {
            this.getWebDriverWait(30).withMessage("accetta button non visibile").until(ExpectedConditions.visibilityOf(this.accettaButton));
            logger.info("Si visualizza il bottone accetta");
            return true;
        }catch (TimeoutException e){
            logger.info("non si visualizza il bottone accetta");
            return false;
        }
    }

    public boolean verificaEsistenzaErroreCodiceSbagliato() {
        try{
            By esistenzaBy = By.xpath("//div[@data-testid='CodeModal error title']");
            this.getWebDriverWait(20).until(ExpectedConditions.visibilityOfElementLocated(esistenzaBy));
            logger.info("Errore codice sbagliato trovato");
            return true;
        }catch (TimeoutException e){
            logger.info("errore non trovato");
            return false;
        }

    }
}


