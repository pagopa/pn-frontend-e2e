package it.pn.frontend.e2e.section.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AggiungiDelegaPGSection extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("AggiungiDelegaPGSection");

    @FindBy(id = "select-pg")
    WebElement personaGiuridicaButton;

    @FindBy(id = "ragioneSociale")
    WebElement ragioneSocialeTextField;

    @FindBy(xpath = "//button[contains(@data-testid,'createButton')]")
    WebElement inviaLaRichiestaButton;

    @FindBy(id = "breadcrumb-indietro-button")
    WebElement tornaIndietroButton;

    @FindBy(id = "courtesy-page-button")
    WebElement tornaDelegheButton;

    @FindBy(id = "expirationDate")
    WebElement dataTermineDelegaInput;

    @FindBy(xpath = "//div[contains(@data-testid, 'codeDigit')]")
    List<WebElement> codiceVerificaList;

    @FindBy(id = "nome")
    WebElement nomeInput;

    @FindBy(id = "cognome")
    WebElement cognomeInput;

    @FindBy(id = "ragioneSociale")
    WebElement ragioneSocialeInput;

    @FindBy(id = "codiceFiscale")
    WebElement codiceFiscaleInput;

    @FindBy(xpath = "//input[contains(@value,'entiSelezionati')]")
    WebElement soloEntiSelezionatiRadioButton;

    @FindBy(id = "enti")
    WebElement enteElementInput;

    @FindBy(id = "expirationDate-helper-text")
    WebElement messaggioErroreData;

    public AggiungiDelegaPGSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAggiungiDelegaPage() {
        try {
            By titlePageBy = By.id("Aggiungi una delega-page");
            this.getWebDriverWait(10).withMessage("Il titolo della pagina aggiungi delegha non è caricato").until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(10).withMessage("Il bottone persona giuridica non è visibile").until(ExpectedConditions.visibilityOf(this.personaGiuridicaButton));
            this.getWebDriverWait(10).withMessage("Il campo nome persona fisica non è visibile").until(ExpectedConditions.or(ExpectedConditions.visibilityOfAllElements(this.nomeInput, this.cognomeInput), ExpectedConditions.visibilityOf(this.ragioneSocialeInput)));
            this.getWebDriverWait(10).withMessage("il campo codice fiscale non è visibile").until(ExpectedConditions.visibilityOf(this.codiceFiscaleInput));
            By radioButtonTuttiEnti = By.id("tutti-gli-enti-selezionati");
            this.getWebDriverWait(10).withMessage("Il radio button tutti gli enti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(radioButtonTuttiEnti));
            By radioButtonSoloEnti = By.id("enti-selezionati");
            this.getWebDriverWait(10).withMessage("Il radio button solo enti selezionati non è visibile").until(ExpectedConditions.visibilityOfElementLocated(radioButtonSoloEnti));
            LocalDate dateTomorrow = LocalDate.now().plusDays(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.getWebDriverWait(10).withMessage("Il campo periodi di validità della delega non è visibile o non ha la data corretta").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(this.dataTermineDelegaInput),
                    ExpectedConditions.attributeContains(this.dataTermineDelegaInput, "value", dateTomorrow.format(formatter))
            ));
            By verificationCode = By.xpath("//div[@data-testid='verificationCode']");
            this.getWebDriverWait(10).withMessage("Il codice ci verifica della delega non è visibile").until(ExpectedConditions.visibilityOfElementLocated(verificationCode));
            logger.info("Si visualizza correttamente la sezione Aggiungi una delega");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente la sezione Aggiungi una delega con errore: " + e.getMessage());
            Assert.fail("Non si visualizza correttamente la sezione Aggiungi una delega con errore: " + e.getMessage());
        }
    }


    public void clickSulBottoneInviaRichiesta() {
        this.getWebDriverWait(10).withMessage("Bottone invia la richiesta non visualizzato").until(ExpectedConditions.elementToBeClickable(this.inviaLaRichiestaButton));
        logger.info("click invia richiesta");
        this.inviaLaRichiestaButton.click();
        this.getWebDriverWait(10).withMessage("Bottone torna alle deleghe non visualizzato").until(ExpectedConditions.elementToBeClickable(this.tornaDelegheButton));
        this.tornaDelegheButton.click();
        logger.info("click torna alle deleghe");
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
        logger.info("inserimento cf");
        this.codiceFiscaleInput.sendKeys(cf);
    }

    public void selectSoloEntiSelezionati() {
        logger.info("Si seleziona il radio button solo enti selezionati");
        this.soloEntiSelezionatiRadioButton.click();
    }

    public void selezionaUnEnte(String ente) {
        this.getWebDriverWait(30).withMessage("il campo ente non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(this.enteElementInput));
        logger.info("selezione e inserimento dati ente");

        this.enteElementInput.click();
        this.enteElementInput.sendKeys(ente);

        // select menu;
        By menuEntiOptionBy = By.xpath("//div[@role='presentation']");
        this.getWebDriverWait(30).withMessage("Opzioni menu enti non visualizzato").until(ExpectedConditions.visibilityOfElementLocated(menuEntiOptionBy));
        WebElement menuEntiOption = this.driver.findElement(menuEntiOptionBy);
        this.js().executeScript("arguments[0].click()", menuEntiOption);

        //click on option 0
        By comuneOptionBy = By.id("enti-option-0");
        this.getWebDriverWait(30).withMessage("Non si visualizza l opzione").until(ExpectedConditions.elementToBeClickable(comuneOptionBy));
        WebElement comuneOption = this.driver.findElement(comuneOptionBy);
        this.js().executeScript("arguments[0].click()", comuneOption);
        logger.info("Ente cliccato");
    }


    public void insertDataErrata() {
        LocalDate dataDaInserire = LocalDate.now().minusDays(1);
        dataTermineDelegaInput.sendKeys(dataDaInserire.toString());
    }

    public void insertDataCorretta() {
        LocalDate dataDaInserire = LocalDate.now().plusDays(1);
        dataTermineDelegaInput.sendKeys(dataDaInserire.toString());
    }

    public String waitMessaggioErroreData() {
        this.getWebDriverWait(30).withMessage("Il messaggio errore delega non è visibile").until(ExpectedConditions.visibilityOf(this.messaggioErroreData));
        return this.messaggioErroreData.getText();
    }

    public void clearInputData() {
        getWebDriverWait(30).withMessage("Il campo data termine delega non è cliccabile ")
                .until(ExpectedConditions.elementToBeClickable(this.dataTermineDelegaInput));
        this.dataTermineDelegaInput.click();
        String name = this.dataTermineDelegaInput.getAttribute("value");
        for (int index = 0; index < name.length(); index++) {
            this.dataTermineDelegaInput.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void selectPersonaGiuridicaRadioButton() {
        logger.info("Si seleziona il radio button persona giuridica");
        this.personaGiuridicaButton.click();
    }

    public void insertRagioneSociale(String ragioneSociale) {
        logger.info("Inserimento ragione sociale");
        ragioneSocialeTextField.sendKeys(ragioneSociale);
    }
}
