package it.pn.frontend.e2e.section.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.apache.commons.math3.analysis.function.Exp;
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
import java.util.concurrent.TimeUnit;

public class LeTueDelegheSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("LeTueDelegheSection");

    @FindBy(xpath = "//input[@value='PF']")
    WebElement personaFisicaRadioButton;

    @FindBy(id = "nome")
    WebElement inputNome;

    @FindBy(id = "cognome")
    WebElement inputCognome;

    @FindBy(xpath = "//button[@data-testid='createButton']")
    WebElement inviaLaRichiestaButton;

    @FindBy(id = "expirationDate")
    WebElement dataTermineDelegaInput;

    @FindBy(xpath = "//div[@data-testid= 'codeDigit']")
    List<WebElement> codiceVerificaList;

    @FindBy(id = "codiceFiscale")
    WebElement codiceFiscaleInput;
    
    @FindBy(xpath = "//input[@value='entiSelezionati']")
    WebElement SoloEntiSelezionatiRadioButton;

    @FindBy(id ="enti")
    WebElement enteElementInput;

    @FindBy(id = "courtesy-page-button")
    WebElement tornaDelegheButton;

    @FindBy(xpath = "//button[@data-testid='acceptButton']")
    WebElement accettaButton;

    @FindBy(xpath = "//button[@data-testid='codeConfirmButton']")
    WebElement accettaPopUpButton;


    @FindBy(id = "Deleghe-page")
    WebElement deleghePageTitle;

    @FindBy(xpath = "//p[contains(text(),'Qui puoi gestire')]")
    WebElement deleghePageSubtitle;

    @FindBy(xpath = "//button[@data-testid='add-delegation']")
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
            By letuedeleghePageTitle = By.id("Aggiungi una delega-page");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(letuedeleghePageTitle));
            logger.info("Le tue deleghe page caricata");
        } catch (TimeoutException e) {
            logger.error("Le tue deleghe page non caricata con errore :" + e.getMessage());
            Assert.fail(("Le tue deleghe page non caricata con errore :" + e.getMessage()));
        }
    }

    public void selectpersonaFisicaRadioButton() {
        this.personaFisicaRadioButton.click();
    }

    public void insertNomeCognome(String nome, String cognome) {
        this.inputNome.sendKeys(nome);
        this.inputCognome.sendKeys(cognome);
    }
    


    public void clickSulBottoneInviaRichiesta() {
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.inviaLaRichiestaButton));
        this.inviaLaRichiestaButton.click();
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.tornaDelegheButton));
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
        String codiceVerifica = "";
        for (int i = 0; i < this.codiceVerificaList.size(); i++) {
            codiceVerifica = codiceVerifica + this.codiceVerificaList.get(i).getText();
        }
        return codiceVerifica;
    }

    public void inserireCF(String cf) {
        this.codiceFiscaleInput.sendKeys(cf);
    }

    public void selectSoloEntiSelezionati() {
        this.SoloEntiSelezionatiRadioButton.click();
    }

    public void selezionaUnEnte(String ente) {
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.enteElementInput));
        this.enteElementInput.sendKeys(ente);

        // select menu;
        By menuEntiOptionBy = By.xpath("//div[@role='presentation']");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuEntiOptionBy));
        WebElement menuEntiOption = this.driver.findElement(menuEntiOptionBy);
        this.js().executeScript("arguments[0].click()",menuEntiOption);

        //click on option 0
        By comuneOptionBy = By.id("enti-option-0");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(comuneOptionBy));
        WebElement comuneOption = this.driver.findElement(comuneOptionBy);
        this.js().executeScript("arguments[0].click()", comuneOption);
    }

    public void clickInviaRichiesta() {
        this.inviaLaRichiestaButton.click();
    }

    public String MessaggioDiErrore() {
            By errorMessageBy = By.xpath("//div[contains(@class,'MuiAlert-message')]/div");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(errorMessageBy));
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
        getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.accettaButton));
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

    public void clickAccettaButton() {
        this.accettaPopUpButton.click();
    }

    public void controlloStatoAttiva(String nome, String cognome) {
        try {
            By statoAttivaBy = By.xpath("//tr[@data-testid='delegatorsTable.row']//td[@role='cell' and div/p[contains(text(),'"+nome + " "+ cognome +"')]]/following-sibling::td[@role='cell']//div/div/span[contains(text(),'Attiva')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoAttivaBy));
            logger.info("La delega ha lo stato Attiva");
        }catch (TimeoutException e){
            logger.error("La delega NON ha lo stato Attiva con errore: "+e.getMessage());
            Assert.fail("La delega NON ha lo stato Attiva con errore: "+e.getMessage());
        }
    }

    public boolean siVisualizzaIlTitolo() {return this.deleghePageTitle.isDisplayed();}

    public boolean siVisualizzaIlSottotitolo() {return this.deleghePageSubtitle.isDisplayed();}

    public boolean siVisualizzaIlBottoneAggiungiUnaDelega() {return this.aggiungiDelegaButton.isDisplayed();}

    public boolean siVisualizzaIlNomeDelegato() {return this.nomeDelegaField.isDisplayed();}


    public boolean siVisualizzaDataInizioDelega() {
        return this.inizioDelegaField.isDisplayed();
    }

    public boolean siVisualizzaDataFinoDelega() {
        return this.fineDelegaField.isDisplayed();
    }

    public boolean siVisualizzaPermessiDelega() {return  this.permessiDelegaField.isDisplayed();}

}


