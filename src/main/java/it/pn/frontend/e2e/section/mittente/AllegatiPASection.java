package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
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

    @FindBy(id = ":r18:")
    WebElement hashCodeTextField;

    @FindBy(id = "documents.0.name")
    WebElement nomeAttoTextField;
    @FindBy(id = "documents.1.name")
    WebElement nomeSecondoAttoTextField;
    @FindBy(id = "documents.2.name")
    WebElement nomeTerzoAttoTextField;

    @FindBy(id = "step-submit")
    WebElement inviaButton;

    private WebTool webTool;

    public AllegatiPASection(WebDriver driver) {

        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadAllegatiPASection() {
        try{
//            WebElement allegatiTitleField = driver.findElement(By.xpath("//h3[contains(text(),'Documenti allegati') or contains(text(),'Attachments') or contains(text(),'Annexes') or contains(text(),'Anhänge') or contains(text(),'Priloge')]"));
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(allegatiTitleField));
            getWebDriverWait(30)
                    .withMessage("Il titolo 'Documenti allegati' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Documenti allegati') or contains(text(),'Attachments') or contains(text(),'Annexes') or contains(text(),'Anhänge') or contains(text(),'Priloge')]")));
            logger.info("Allegati PA Section caricata");
        }catch (TimeoutException e){
            logger.error("Allegati PA Section non caricata con errore: "+e.getMessage());
            Assertions.fail("Allegati PA Section non caricata con errore: "+e.getMessage());
        }
    }

    public void caricareNotificaPdfDalComputer(String pathNotificaFile) {
        webTool.waitTime(20);

        selezionaloDalTuoComputerInput = driver.findElement(By.cssSelector("div[data-testid='fileInput'] > input[accept='application/pdf']"));
        if(!selezionaloDalTuoComputerInput.isDisplayed()){
            js().executeScript("arguments[0].scrollIntoView(true)", selezionaloDalTuoComputerInput);
            selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
        }else{
            selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
        }
    }

    public void caricareSingolaNotificaPdfDalComputer(String pathNotificaFile, int posizione) {
        webTool.waitTime(20);
        List<WebElement> fileInputs = driver.findElements(By.cssSelector("div[data-testid='fileInput'] > input[accept='application/pdf']"));
        if(!fileInputs.get(posizione).isDisplayed()){
            js().executeScript("arguments[0].scrollIntoView(true)", fileInputs.get(posizione));
            fileInputs.get(posizione).sendKeys(pathNotificaFile);
        }else{
            fileInputs.get(posizione).sendKeys(pathNotificaFile);
        }
    }

    public void caricareSingolaNotificaJsonDalComputer(String pathNotificaFile,int posizione) {
        webTool.waitTime(20);
        List<WebElement> fileInputs = driver.findElements(By.cssSelector("div[data-testid='fileInput'] > input[accept='application/json']"));
        if(!fileInputs.get(posizione).isDisplayed()){
            js().executeScript("arguments[0].scrollIntoView(true)", fileInputs.get(posizione));
            fileInputs.get(posizione).sendKeys(pathNotificaFile);
        }else{
            fileInputs.get(posizione).sendKeys(pathNotificaFile);
        }
    }


    public void messagioDiErroreDoc(){
        WebElement errorMessageDoc = driver.findElement(By.id("file-upload-error"));
        getWebDriverWait(5).withMessage("Il messagio di formato errato non è visibile").until(ExpectedConditions.visibilityOf(errorMessageDoc));
    }

    public void checkCodiceHash(){
        WebElement copiaHash = driver.findElement(By.xpath("//button[@aria-label='Copia']"));
        getWebDriverWait(5).withMessage("Il codice hash non è visibile").until(ExpectedConditions.visibilityOf(copiaHash));
       // WebElement codiceHash = driver.findElement(By.id(":r18:"));
       // getWebDriverWait(5).withMessage("Il codice hash non è visibile").until(ExpectedConditions.visibilityOf(codiceHash));
    }

    public void clickAggiungiNuovoDocumento(){
        WebElement addNewDocuments = driver.findElement(By.xpath("//*[@data-testId='add-another-doc']"));
        getWebDriverWait(5).withMessage("Il bottone aggiungi nuovo documento non è cliccabile").until(ExpectedConditions.elementToBeClickable(addNewDocuments));
      //  addNewDocuments.click();
        js().executeScript("arguments[0].click()", addNewDocuments);
    }

    public void eliminaAtto(){
        List<WebElement> deleteIcons = driver.findElements(By.xpath("//*[@data-testid='DeleteIcon']"));
        deleteIcons.get(0).click();
    }

    public boolean verificaCaricamentoNotificaPdf() {
       // hashCodeTextField = driver.findElement(By.id(":r18:"));
        WebElement copiaHash = driver.findElement(By.xpath("//button[@aria-label='Copia' or @aria-label='Copy' or @aria-label='Copie' or @aria-label='Kopieren']"));
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(copiaHash));
        logger.info("check caricamento notifica pdf");
        return copiaHash.isDisplayed();
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
             inviaButton = getWebDriverWait(10)
                     .withMessage("Il bottone Invia Non presente nel DOM")
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("step-submit")));
            getWebDriverWait(10)
                    .withMessage("Il bottone Invia Non è  visibile e cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(inviaButton));
            inviaButton.click();
            logger.info("click avvenuto con successo su invio allegati");
        }catch (TimeoutException e) {
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

    public void caricareJsonDalComputer(String pathNotificaFile) {
        webTool.waitTime(20);

        selezionaloDalTuoComputerInput = driver.findElement(By.cssSelector("div[data-testid='fileInput'] > input[accept='application/json']"));
        if(!selezionaloDalTuoComputerInput.isDisplayed()){
            js().executeScript("arguments[0].scrollIntoView(true)", selezionaloDalTuoComputerInput);
            selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
        }else{
            selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
        }
    }

    public void inserisciTitoloDocumentoDocumentiAllegati(int numeroTitoloDoc) {

        String idCampo = String.format("documents.%d.name", numeroTitoloDoc -1);
        WebElement campoNome =  getWebDriverWait(10)
                .withMessage("Impossibile inserire il Titolo Documento Posizione Debitoria num: "+(numeroTitoloDoc -1))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(idCampo)));


        if (!campoNome.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true)", campoNome);
        }

        campoNome.click();
        campoNome.clear(); // facoltativo, ma spesso utile
        campoNome.sendKeys( new StringBuilder("Docuemnto_Doc_All_").append(numeroTitoloDoc));


    }


}

