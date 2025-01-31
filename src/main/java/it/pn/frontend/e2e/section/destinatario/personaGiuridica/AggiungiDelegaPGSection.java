package it.pn.frontend.e2e.section.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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


    private  WebTool webTool;

    private boolean dataFineErrata;

    public AggiungiDelegaPGSection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadAggiungiDelegaPage() {
        try {
            WebElement titlePageBy = driver.findElement(By.id("Aggiungi una delega-page"));
            personaGiuridicaButton = driver.findElement(By.id("select-pg"));
//            nomeInput = driver.findElement(By.id("nome"));
//            cognomeInput = driver.findElement(By.id("cognome"));
            codiceFiscaleInput = driver.findElement(By.id("codiceFiscale"));

            getWebDriverWait(10).withMessage("Il titolo della pagina aggiungi delegha non è caricato").until(ExpectedConditions.visibilityOf(titlePageBy));
            getWebDriverWait(10).withMessage("Il bottone persona giuridica non è visibile").until(ExpectedConditions.visibilityOf(personaGiuridicaButton));
//            getWebDriverWait(10).withMessage("Il campo nome persona fisica non è visibile").until(ExpectedConditions.or(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("nome")), driver.findElement(By.id("cognome"))), ExpectedConditions.visibilityOf(ragioneSocialeInput)));
            getWebDriverWait(10).withMessage("Il campo nome persona fisica non è visibile").until(ExpectedConditions.or(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.id("nome"))),ExpectedConditions.visibilityOfElementLocated(By.id("ragioneSociale"))));
            getWebDriverWait(10).withMessage("il campo codice fiscale non è visibile").until(ExpectedConditions.visibilityOf(codiceFiscaleInput));
            WebElement radioButtonTuttiEnti = driver.findElement(By.id("tutti-gli-enti-selezionati"));
            getWebDriverWait(10).withMessage("Il radio button tutti gli enti non è visibile").until(ExpectedConditions.visibilityOf(radioButtonTuttiEnti));
            WebElement radioButtonSoloEnti = driver.findElement(By.id("enti-selezionati"));
            getWebDriverWait(10).withMessage("Il radio button solo enti selezionati non è visibile").until(ExpectedConditions.visibilityOf(radioButtonSoloEnti));
            LocalDate dateTomorrow = LocalDate.now().plusDays(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            dataTermineDelegaInput = driver.findElement(By.id("expirationDate"));

            getWebDriverWait(10).withMessage("Il campo periodi di validità della delega non è visibile o non ha la data corretta").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(dataTermineDelegaInput),
                    ExpectedConditions.attributeContains(dataTermineDelegaInput, "value", dateTomorrow.format(formatter))
            ));
            WebElement verificationCode = driver.findElement(By.xpath("//div[@data-testid='verificationCode']"));
            getWebDriverWait(10).withMessage("Il codice ci verifica della delega non è visibile").until(ExpectedConditions.visibilityOf(verificationCode));
            logger.info("Si visualizza correttamente la sezione Aggiungi una delega");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente la sezione Aggiungi una delega con errore: " + e.getMessage());
            Assertions.fail("Non si visualizza correttamente la sezione Aggiungi una delega con errore: " + e.getMessage());
        }
    }


    public void clickSulBottoneInviaRichiesta() {
        inviaLaRichiestaButton = driver.findElement(By.xpath("//button[contains(@data-testid,'createButton')]"));
        getWebDriverWait(10).withMessage("Bottone invia la richiesta non visualizzato").until(ExpectedConditions.elementToBeClickable(inviaLaRichiestaButton));
        logger.info("click invia richiesta");
        inviaLaRichiestaButton.click();
        webTool.waitTime(15);
        getWebDriverWait(10).withMessage("Bottone torna alle deleghe non visualizzato").until(ExpectedConditions.elementToBeClickable((By.id("courtesy-page-button"))));
        tornaDelegheButton = driver.findElement(By.id("courtesy-page-button"));
        tornaDelegheButton.click();
        logger.info("click torna alle deleghe");
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
        codiceVerificaList = driver.findElements(By.xpath("//div[contains(@data-testid, 'codeDigit')]"));
        StringBuilder codiceVerifica = new StringBuilder();
        for (WebElement webElement : codiceVerificaList) {
            codiceVerifica.append(webElement.getText());
        }
        return codiceVerifica.toString();
    }

    public void inserireCF(String cf) {
        logger.info("inserimento cf");
        codiceFiscaleInput = driver.findElement(By.id("codiceFiscale"));
        codiceFiscaleInput.sendKeys(cf);
    }

    public void selectSoloEntiSelezionati() {
        logger.info("Si seleziona il radio button solo enti selezionati");
        soloEntiSelezionatiRadioButton = driver.findElement(By.xpath("//input[contains(@value,'entiSelezionati')]"));
        soloEntiSelezionatiRadioButton.click();
    }

    public void selezionaUnEnte(String ente) {
        enteElementInput = driver.findElement(By.id("enti"));
        getWebDriverWait(30).withMessage("il campo ente non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(enteElementInput));
        logger.info("selezione e inserimento dati ente");

        enteElementInput.click();
        enteElementInput.sendKeys(ente);
        logger.info("Inserito ente: "+ente);

        // select menu;
        WebElement menuEntiOptionBy = driver.findElement(By.xpath("//div[@role='presentation']"));
        getWebDriverWait(30).withMessage("Opzioni menu enti non visualizzato").until(ExpectedConditions.visibilityOf(menuEntiOptionBy));

        js().executeScript("arguments[0].click()", menuEntiOptionBy);

        //click on option 0
        WebElement comuneOptionBy = driver.findElement(By.id("enti-option-0"));
       getWebDriverWait(30).withMessage("Non si visualizza l opzione").until(ExpectedConditions.elementToBeClickable(comuneOptionBy));
        js().executeScript("arguments[0].click()", comuneOptionBy);
        logger.info("Ente cliccato");
    }


    public boolean insertDataErrata() {
        LocalDate dataDaInserire = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dataDaInserire.format(formatter);
        return insertData(formattedDate);
    }



    public void insertDataCorretta() {
        LocalDate dataDaInserire = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dataDaInserire.format(formatter);
        insertData(formattedDate);
        // dataTermineDelegaInput.sendKeys(formattedDate);
    }


    public boolean insertData(String dataInserita) {
        boolean result = true;
        WebElement calendar = null;
        int dayDa = 0;
        try {
            dataTermineDelegaInput = driver.findElement(By.id("expirationDate"));

            getWebDriverWait(10).withMessage("il campo data non è visibile nella pagina").until(ExpectedConditions.visibilityOf(dataTermineDelegaInput));

            webTool.waitTime(15);

            dataTermineDelegaInput = getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(By.id("expirationDate")));

            webTool.waitTime(10);
            String[] arraySplitDateDa = dataInserita.split("/");

            List<WebElement> dataFieldList = driver.findElements(By.cssSelector(".MuiInputBase-input"));
            dayDa = Integer.parseInt(arraySplitDateDa[0]);

            // Step 2: Click on the input field to open the calendar pop-up
            dataFieldList.get(3).click();

            // Step 3: Wait for the calendar pop-up to appear
            calendar = getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".MuiDateCalendar-root")));  // Adjust based on your app

            // Step 4: Select a date (e.g., the 15th day of the current month)
            WebElement dateToSelect = calendar.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//div[contains(@class,'MuiDayCalendar-monthContainer')]//*[text()='" + dayDa + "']"));
            dateToSelect.click();

            getWebDriverWait(3).until(ExpectedConditions.attributeToBe(dataTermineDelegaInput, "value", dataInserita));
        } catch (ElementClickInterceptedException e) {
            logger.error("Non è possibile settare una data Fine precedente rispetto alla data Inizio: " + e.getMessage());
            if(calendar!= null) {
                dayDa = dayDa+2;
                WebElement dateToSelect = calendar.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//div[contains(@class,'MuiDayCalendar-monthContainer')]//*[text()='" + dayDa + "']"));
                dateToSelect.click();
            }
            result = false;
        }
        return  result;
    }


    public String waitMessaggioErroreData() {
        messaggioErroreData = driver.findElement(By.id("expirationDate-helper-text"));
        getWebDriverWait(30).withMessage("Il messaggio errore delega non è visibile").until(ExpectedConditions.visibilityOf(messaggioErroreData));
        return messaggioErroreData.getText();
    }

    public void clearInputData() {
        dataTermineDelegaInput = driver.findElement(By.id("expirationDate"));
        getWebDriverWait(30).withMessage("Il campo data termine delega non è cliccabile ")
                .until(ExpectedConditions.elementToBeClickable(dataTermineDelegaInput));
        dataTermineDelegaInput.click();
        String name = this.dataTermineDelegaInput.getAttribute("value");
        for (int index = 0; index < name.length(); index++) {
            this.dataTermineDelegaInput.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void clearDateField() {
        dataTermineDelegaInput = driver.findElement(By.id("expirationDate"));
        getWebDriverWait(30).withMessage("Il campo data termine delega non è cliccabile ")
                .until(ExpectedConditions.elementToBeClickable(dataTermineDelegaInput));
        dataTermineDelegaInput.click();
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
    }

    public void selectPersonaGiuridicaRadioButton() {
        logger.info("Si seleziona il radio button persona giuridica");
        personaGiuridicaButton = driver.findElement(By.id("select-pg"));
        personaGiuridicaButton.click();
    }

    public void insertRagioneSociale(String ragioneSociale) {
        logger.info("Inserimento ragione sociale");
        ragioneSocialeTextField = driver.findElement(By.id("ragioneSociale"));
        ragioneSocialeTextField.sendKeys(ragioneSociale);
    }
}
