package it.pn.frontend.e2e.section.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class AggiungiDelegaPGSection extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("AggiungiDelegaPGSection");


    @FindBy(xpath = "//input[@name='selectPersonaFisicaOrPersonaGiuridica' and @value = 'PG']")
    WebElement personaGiuridicaButton;

    @FindBy(id = "ragioneSociale")
    WebElement ragioneSocialeTextField;

    @FindBy(xpath = "//button[contains(@data-testid,'createButton')]")
    WebElement inviaLaRichiestaButton;

    @FindBy(xpath = "//button[@id='courtesy-page-button']")
    WebElement tornaDelegheButton;

    @FindBy(xpath = "//input[contains(@id,'expirationDate')]")
    WebElement dataTermineDelegaInput;

    @FindBy(xpath = "//div[contains(@data-testid, 'codeDigit')]")
    List<WebElement> codiceVerificaList;

    @FindBy(xpath = "//input[contains(@id,'codiceFiscale')]")
    WebElement codiceFiscaleInput;

    @FindBy(xpath = "//input[contains(@value,'entiSelezionati')]")
    WebElement SoloEntiSelezionatiRadioButton;

    @FindBy(id ="enti")
    WebElement enteElementInput;

    @FindBy(id = "expirationDate-helper-text")
    WebElement messaggioErroreData;

    public AggiungiDelegaPGSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAggiungiDelegaPage() {
        try {
            By titlePageBy = By.id("Aggiungi una delega-page");
            this.getWebDriverWait(40).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(40).until(ExpectedConditions.visibilityOf(this.codiceFiscaleInput));
            logger.info("Si visualizza correttamente la sezione Aggiungi una delega");
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente la sezione Aggiungi una delega con errore: "+e.getMessage());
            Assert.fail("Non si visualizza correttamente la sezione Aggiungi una delega con errore: "+e.getMessage());
        }
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
        for (WebElement webElement : this.codiceVerificaList) {
            codiceVerifica = codiceVerifica + webElement.getText();
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
        this.getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(this.enteElementInput));
        this.enteElementInput.sendKeys(ente);

        // select menu;
        By menuEntiOptionBy = By.xpath("//div[@role='presentation']");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuEntiOptionBy));
        WebElement menuEntiOption = this.driver.findElement(menuEntiOptionBy);
        this.js().executeScript("arguments[0].click()",menuEntiOption);

        //click on option 0
        By comuneOptionBy = By.id("enti-option-0");
        this.getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(comuneOptionBy));
        WebElement comuneOption = this.driver.findElement(comuneOptionBy);
        this.js().executeScript("arguments[0].click()", comuneOption);
    }


    public void insertDataErrata() {
        LocalDate dataDaInserire =  LocalDate.now().minusDays(1);
        dataTermineDelegaInput.sendKeys(dataDaInserire.toString());
    }

    public void waitMessaggioErroreData() {
        this.getWebDriverWait(30).withMessage("Il messaggio errore delega non è visibile").until(ExpectedConditions.visibilityOf(this.messaggioErroreData));
    }

    public void clearImputData() {
        this.dataTermineDelegaInput.click();
        String name = this.dataTermineDelegaInput.getAttribute("value");
        for(int index = 0; index<name.length(); index++){
           this.dataTermineDelegaInput.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void selectpersonaGiuridicaRadioButton() {
        this.personaGiuridicaButton.click();
    }

    public void insertRagioneSociale(String ragioneSociale) {
        ragioneSocialeTextField.sendKeys(ragioneSociale);
    }
}
