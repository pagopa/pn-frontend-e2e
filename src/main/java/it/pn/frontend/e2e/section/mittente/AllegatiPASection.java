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

import java.util.List;

public class AllegatiPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(AllegatiPASection.class);

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
        try {
            getWebDriverWait(30)
                    .withMessage("Il titolo 'Documenti allegati' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Documenti allegati') or contains(text(),'Attachments') or contains(text(),'Annexes') or contains(text(),'Anhänge') or contains(text(),'Priloge')]")));
            logger.info("Allegati PA Section caricata");
        } catch (TimeoutException e) {
            Assertions.fail("Allegati PA Section non caricata con errore: " + e.getMessage());
        }
    }

    //    public void caricareNotificaPdfDalComputer(String pathNotificaFile) {
//        webTool.waitTime(20);
//
//        selezionaloDalTuoComputerInput = driver.findElement(By.cssSelector("div[data-testid='fileInput'] > input[accept='application/pdf']"));
//        if (!selezionaloDalTuoComputerInput.isDisplayed()) {
//            js().executeScript("arguments[0].scrollIntoView(true)", selezionaloDalTuoComputerInput);
//            selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
//        } else {
//            selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
//        }
//    }
    public void caricareNotificaPdfDalComputer(String pathNotificaFile) {
        webTool.waitTime(20);
        selezionaloDalTuoComputerInput = getWebDriverWait(20)
                .withMessage("Campo upload PDF non trovato")
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("div[data-testid='fileInput'] > input[accept='application/pdf']")));

        js().executeScript("arguments[0].scrollIntoView(true);", selezionaloDalTuoComputerInput);
        selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);

        logger.info("Caricato file PDF: {}", pathNotificaFile);
    }


    //    public void caricareSingolaNotificaPdfDalComputer(String pathNotificaFile, int posizione) {
//        webTool.waitTime(20);
//        List<WebElement> fileInputs = driver.findElements(By.cssSelector("div[data-testid='fileInput'] > input[accept='application/pdf']"));
//        if (!fileInputs.get(posizione).isDisplayed()) {
//            js().executeScript("arguments[0].scrollIntoView(true)", fileInputs.get(posizione));
//            fileInputs.get(posizione).sendKeys(pathNotificaFile);
//        } else {
//            fileInputs.get(posizione).sendKeys(pathNotificaFile);
//        }
//    }
    public void caricareSingolaNotificaPdfDalComputer(String pathNotificaFile, int posizione) {
        webTool.waitTime(20);
        List<WebElement> fileInputs = getWebDriverWait(20)
                .withMessage("Input file PDF non trovato in posizione " + posizione)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("div[data-testid='fileInput'] > input[accept='application/pdf']")));

        if (posizione < 0 || posizione >= fileInputs.size()) {
            throw new IllegalArgumentException("Posizione " + posizione + " non valida. Numero di input disponibili: " + fileInputs.size());
        }

        WebElement inputFile = fileInputs.get(posizione);

        js().executeScript("arguments[0].scrollIntoView(true);", inputFile);
        inputFile.sendKeys(pathNotificaFile);

        logger.info("Caricato file PDF '{}' nel campo posizione {}", pathNotificaFile, posizione);
    }


    //    public void caricareSingolaNotificaJsonDalComputer(String pathNotificaFile, int posizione) {
//        webTool.waitTime(20);
//        List<WebElement> fileInputs = driver.findElements(By.cssSelector("div[data-testid='fileInput'] > input[accept='application/json']"));
//        if (!fileInputs.get(posizione).isDisplayed()) {
//            js().executeScript("arguments[0].scrollIntoView(true)", fileInputs.get(posizione));
//            fileInputs.get(posizione).sendKeys(pathNotificaFile);
//        } else {
//            fileInputs.get(posizione).sendKeys(pathNotificaFile);
//        }
//    }
    public void caricareSingolaNotificaJsonDalComputer(String pathNotificaFile, int posizione) {
        webTool.waitTime(20);
        List<WebElement> fileInputs = getWebDriverWait(20)
                .withMessage("Input file JSON non trovato in posizione " + posizione)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("div[data-testid='fileInput'] > input[accept='application/json']")));

        if (posizione < 0 || posizione >= fileInputs.size()) {
            throw new IllegalArgumentException("Posizione " + posizione + " non valida. Numero di input disponibili: " + fileInputs.size());
        }

        WebElement inputFile = fileInputs.get(posizione);

        js().executeScript("arguments[0].scrollIntoView(true);", inputFile);
        inputFile.sendKeys(pathNotificaFile);

        logger.info("Caricato file JSON '{}' nel campo posizione {}", pathNotificaFile, posizione);
    }


    //    public void messagioDiErroreDoc() {
//        WebElement errorMessageDoc = driver.findElement(By.id("file-upload-error"));
//        getWebDriverWait(5).withMessage("Il messagio di formato errato non è visibile").until(ExpectedConditions.visibilityOf(errorMessageDoc));
//    }
    public void messagioDiErroreDoc() {
        WebElement errorMessageDoc = getWebDriverWait(5)
                .withMessage("Il messaggio di formato errato non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("file-upload-error")));

        logger.info("Messaggio di errore visibile: {}", errorMessageDoc.getText());
    }


    //    public void checkCodiceHash() {
//        WebElement copiaHash = driver.findElement(By.xpath("//button[@aria-label='Copia']"));
//        getWebDriverWait(5).withMessage("Il codice hash non è visibile").until(ExpectedConditions.visibilityOf(copiaHash));
//    }
    public void checkCodiceHash() {
        WebElement copiaHash = getWebDriverWait(5)
                .withMessage("Il codice hash non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Copia']")));

        logger.info("Codice hash visibile");
    }


    //    public void clickAggiungiNuovoDocumento() {
//        WebElement addNewDocuments = driver.findElement(By.xpath("//*[@data-testId='add-another-doc']"));
//        getWebDriverWait(5).withMessage("Il bottone aggiungi nuovo documento non è cliccabile").until(ExpectedConditions.elementToBeClickable(addNewDocuments));
//        js().executeScript("arguments[0].click()", addNewDocuments);
//    }
    public void clickAggiungiNuovoDocumento() {
        WebElement addNewDocuments = getWebDriverWait(5)
                .withMessage("Il bottone aggiungi nuovo documento non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testId='add-another-doc']")));

        js().executeScript("arguments[0].click()", addNewDocuments);
    }


    //    public void eliminaAtto() {
//        List<WebElement> deleteIcons = driver.findElements(By.xpath("//*[@data-testid='DeleteIcon']"));
//        deleteIcons.get(0).click();
//    }
    public void eliminaAtto() {
        By deleteIcon = By.xpath("//*[@data-testid='DeleteIcon']");

        List<WebElement> deleteIcons = getWebDriverWait(10)
                .withMessage("Nessuna icona di cancellazione trovata")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(deleteIcon));

        WebElement firstDelete = deleteIcons.get(0);

        getWebDriverWait(10)
                .withMessage("La prima icona di cancellazione non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(firstDelete))
                .click();

        logger.info("Cliccata la prima icona di cancellazione atto");
    }



    //    public boolean verificaCaricamentoNotificaPdf() {
//        WebElement copiaHash = driver.findElement(By.xpath("//button[@aria-label='Copia' or @aria-label='Copy' or @aria-label='Copie' or @aria-label='Kopieren']"));
//        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(copiaHash));
//        logger.info("check caricamento notifica pdf");
//        return copiaHash.isDisplayed();
//    }
    public boolean verificaCaricamentoNotificaPdf() {
        By copiaHashBy = By.xpath("//button[@aria-label='Copia' or @aria-label='Copy' or @aria-label='Copie' or @aria-label='Kopieren']");

        WebElement copiaHash = getWebDriverWait(30)
                .withMessage("Il pulsante copia hash della notifica PDF non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(copiaHashBy));

        logger.info("check caricamento notifica pdf");
        return copiaHash.isDisplayed();
    }


    //    public void inserimentoNomeAllegato(String nomeAtto) {
//        nomeAttoTextField = driver.findElement(By.id("documents.0.name"));
//        if (!nomeAttoTextField.isDisplayed()) {
//            js().executeScript("arguments[0].scrollIntoView(true)", nomeAttoTextField);
//        }
//        logger.info("inserimento nome allegato");
//        this.nomeAttoTextField.click();
//        this.nomeAttoTextField.sendKeys(nomeAtto);
//    }
    public void inserimentoNomeAllegato(String nomeAtto) {
        By nomeAttoBy = By.id("documents.0.name");

        nomeAttoTextField = getWebDriverWait(10)
                .withMessage("Il campo nome allegato non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(nomeAttoBy));

        if (!nomeAttoTextField.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true)", nomeAttoTextField);
        }

        logger.info("inserimento nome allegato");
        nomeAttoTextField.click();
        nomeAttoTextField.sendKeys(nomeAtto);
    }


    //    public void inserimentoNomeSecondoAllegato(String nomeAtto) {
//        nomeSecondoAttoTextField = driver.findElement(By.id("documents.1.name"));
//        if (!nomeSecondoAttoTextField.isDisplayed()) {
//            js().executeScript("arguments[0].scrollIntoView(true)", nomeSecondoAttoTextField);
//        }
//        logger.info("inserimento nome allegato");
//        this.nomeSecondoAttoTextField.click();
//        this.nomeSecondoAttoTextField.sendKeys(nomeAtto);
//    }
    public void inserimentoNomeSecondoAllegato(String nomeAtto) {
        By nomeSecondoAttoBy = By.id("documents.1.name");

        nomeSecondoAttoTextField = getWebDriverWait(10)
                .withMessage("Il campo nome del secondo allegato non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(nomeSecondoAttoBy));

        if (!nomeSecondoAttoTextField.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true)", nomeSecondoAttoTextField);
        }

        logger.info("inserimento nome secondo allegato");
        nomeSecondoAttoTextField.click();
        nomeSecondoAttoTextField.sendKeys(nomeAtto);
    }


    //    public void inserimentoNomeTerzoAllegato(String nomeAtto) {
//        nomeTerzoAttoTextField = driver.findElement(By.id("documents.2.name"));
//        if (!nomeTerzoAttoTextField.isDisplayed()) {
//            js().executeScript("arguments[0].scrollIntoView(true)", nomeTerzoAttoTextField);
//        }
//        logger.info("inserimento nome allegato");
//        nomeTerzoAttoTextField.click();
//        nomeTerzoAttoTextField.sendKeys(nomeAtto);
//    }
    public void inserimentoNomeTerzoAllegato(String nomeAtto) {
        By nomeTerzoAttoBy = By.id("documents.2.name");

        nomeTerzoAttoTextField = getWebDriverWait(10)
                .withMessage("Il campo nome del terzo allegato non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(nomeTerzoAttoBy));

        if (!nomeTerzoAttoTextField.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true)", nomeTerzoAttoTextField);
        }

        logger.info("inserimento nome terzo allegato");
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
        } catch (TimeoutException e) {
            Assertions.fail("click non avvenuto con successo su invio allegati con errore: " + e.getMessage());
        }
    }


    public void selectInviaButtonPosizioneDebitoria() {

        WebElement inviaBtn = getWebDriverWait(30)
                .until(ExpectedConditions.elementToBeClickable(By.id("step-submit")));

        // Scroll e click JS fallback
        js().executeScript("arguments[0].scrollIntoView({block: 'center'});", inviaBtn);
        try {
            inviaBtn.click();
        } catch (Exception e) {
            js().executeScript("arguments[0].click();", inviaBtn);
        }
    }


    //    public boolean verificaMessaggioErrore() {
//        try {
//            WebElement messageErrorBy = driver.findElement(By.id("alert-1"));
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(messageErrorBy));
//            logger.info("messaggio di errore presente");
//            return true;
//        } catch (TimeoutException e) {
//            logger.info("messaggio di errore non presente");
//            return false;
//        }
//    }
    public boolean verificaMessaggioErrore() {
        By messageErrorBy = By.id("alert-1");

        List<WebElement> elements = getWebDriverWait(30)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(messageErrorBy));

        boolean isVisible = !elements.isEmpty() && elements.get(0).isDisplayed();

        if (isVisible) {
            logger.info("messaggio di errore presente");
        } else {
            logger.info("messaggio di errore non presente");
        }

        return isVisible;
    }


//    public void caricareJsonDalComputer(String pathNotificaFile) {
//        webTool.waitTime(20);
//
//        selezionaloDalTuoComputerInput = driver.findElement(By.cssSelector("div[data-testid='fileInput'] > input[accept='application/json']"));
//        if (!selezionaloDalTuoComputerInput.isDisplayed()) {
//            js().executeScript("arguments[0].scrollIntoView(true)", selezionaloDalTuoComputerInput);
//            selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
//        } else {
//            selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
//        }
//    }

    public void caricareJsonDalComputer(String pathNotificaFile) {
        webTool.waitTime(20);

        By fileInputBy = By.cssSelector("div[data-testid='fileInput'] > input[accept='application/json']");
        WebElement selezionaloDalTuoComputerInput = getWebDriverWait(10)
                .until(ExpectedConditions.presenceOfElementLocated(fileInputBy));

        if (!selezionaloDalTuoComputerInput.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true)", selezionaloDalTuoComputerInput);
        }

        selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
    }

    public void inserisciTitoloDocumentoDocumentiAllegati(int numeroTitoloDoc) {

        String idCampo = String.format("documents.%d.name", numeroTitoloDoc - 1);
        WebElement campoNome = getWebDriverWait(10)
                .withMessage("Impossibile inserire il Titolo Documento Posizione Debitoria num: " + (numeroTitoloDoc - 1))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(idCampo)));


        if (!campoNome.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true)", campoNome);
        }

        campoNome.click();
        campoNome.clear(); // facoltativo, ma spesso utile
        campoNome.sendKeys(new StringBuilder("Docuemnto_Doc_All_").append(numeroTitoloDoc));
    }


}

