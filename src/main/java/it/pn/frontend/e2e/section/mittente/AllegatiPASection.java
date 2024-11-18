package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class AllegatiPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AllegatiSection");

    @FindBy(css = "div[data-testid='fileInput'] > input[accept='application/pdf']")
    WebElement selezionaloDalTuoComputerInput;

    @FindBy(id = "file-upload-hash-code")
    WebElement hashCodeTextField;

    @FindBy(id = "documents.0.name")
    WebElement nomeAttoTextField;
    @FindBy(id = "documents.1.name")
    WebElement nomeSecondoAttoTextField;
    @FindBy(id = "documents.2.name")
    WebElement nomeTerzoAttoTextField;

    @FindBy(id = "step-submit")
    WebElement inviaButton;

    public AllegatiPASection(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadAllegatiPASection() {
        try{
            WebElement allegatiTitleField = driver.findElement(By.xpath("//h3[contains(text(),'Allegati')]"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(allegatiTitleField));
            logger.info("Allegati PA Section caricata");
        }catch (TimeoutException e){
            logger.error("Allegati PA Section non caricata con errore: "+e.getMessage());
            Assertions.fail("Allegati PA Section non caricata con errore: "+e.getMessage());
        }
    }

    public void caricareNotificaPdfDalComputer(String pathNotificaFile) {
        selezionaloDalTuoComputerInput = driver.findElement(By.cssSelector("div[data-testid='fileInput'] > input[accept='application/pdf']"));
        if(!selezionaloDalTuoComputerInput.isDisplayed()){
            js().executeScript("arguments[0].scrollIntoView(true)", selezionaloDalTuoComputerInput);
            selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
        }else{
            selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
        }
    }
    public void messagioDiErroreDoc(){
        WebElement errorMessageDoc = driver.findElement(By.id("file-upload-error"));
        getWebDriverWait(5).withMessage("Il messagio di formato errato non è visibile").until(ExpectedConditions.visibilityOf(errorMessageDoc));
    }

    public void checkCodiceHash(){
        WebElement codiceHash = driver.findElement(By.id("file-upload-hash-code"));
        getWebDriverWait(5).withMessage("Il codice hash non è visibile").until(ExpectedConditions.visibilityOf(codiceHash));
    }

    public void clickAggiungiNuovoDocumento(){
        WebElement addNewDocuments = driver.findElement(By.xpath("//*[@data-testId='add-another-doc']"));
        getWebDriverWait(5).withMessage("Il bottone aggiungi nuovo documento non è cliccabile").until(ExpectedConditions.elementToBeClickable(addNewDocuments));
        addNewDocuments.click();
    }

    public void eliminaAtto(){
        List<WebElement> deleteIcons = driver.findElements(By.xpath("//*[@data-testid='DeleteIcon']"));
        deleteIcons.get(0).click();
    }

    public boolean verificaCaricamentoNotificaPdf() {
        hashCodeTextField = driver.findElement(By.id("file-upload-hash-code"));
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(hashCodeTextField));
        logger.info("check caricamento notifica pdf");
        return hashCodeTextField.isDisplayed();
    }

    public void inserimentoNomeAllegato(String nomeAtto) {
        nomeAttoTextField = driver.findElement(By.id("documents.0.name"));
        if (!nomeAttoTextField.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true)", nomeAttoTextField);
        }
        logger.info("inserimento nome allegato");
        this.nomeAttoTextField.click();
        this.nomeAttoTextField.sendKeys(nomeAtto);
    }
    public void inserimentoNomeSecondoAllegato(String nomeAtto) {
        nomeSecondoAttoTextField = driver.findElement(By.id("documents.1.name"));
        if (!nomeSecondoAttoTextField.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true)", nomeSecondoAttoTextField);
        }
        logger.info("inserimento nome allegato");
        this.nomeSecondoAttoTextField.click();
        this.nomeSecondoAttoTextField.sendKeys(nomeAtto);
    }
    public void inserimentoNomeTerzoAllegato(String nomeAtto) {
        nomeTerzoAttoTextField = driver.findElement(By.id("documents.2.name"));
        if (!nomeTerzoAttoTextField.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true)", nomeTerzoAttoTextField);
        }
        logger.info("inserimento nome allegato");
        nomeTerzoAttoTextField.click();
        nomeTerzoAttoTextField.sendKeys(nomeAtto);
    }

    public void selectInviaButton() {

        try {
            inviaButton = driver.findElement(By.id("step-submit"));
            getWebDriverWait(10).until(ExpectedConditions.and(ExpectedConditions.elementToBeClickable(inviaButton),ExpectedConditions.visibilityOf(inviaButton)));
            inviaButton.click();
            logger.info("click avvenuto con successo su invio allegati");
        }catch (TimeoutException e) {
            logger.error("click non avvenuto con successo su invio allegati con errore: "+e.getMessage());
            Assertions.fail("click non avvenuto con successo su invio allegati con errore: "+e.getMessage());        }
    }

    public boolean verificaMessaggioErrore() {
        try {
            WebElement messageErrorBy = driver.findElement(By.id("alert-1"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(messageErrorBy));
            logger.info("messaggio di errore presente");
            return true;
        }catch (TimeoutException e) {
            logger.info("messaggio di errore non presente");
            return  false;
        }
    }
}

