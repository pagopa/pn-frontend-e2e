package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DettaglioNotificaMittenteSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("DettaglioNotificaSection");

    @FindBy(id = "more-less-timeline-step")
    List<WebElement> vediDettagliButton;

    @FindBy(xpath = "//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-paddingNone MuiTableCell-sizeMedium css-11dv4ll')]")
    List<WebElement> infoNotifiche;

    @FindBy(xpath = "//button[contains(@data-testid,'documentButton')]")
    List<WebElement> linkAllegati;

    @FindBy(xpath = "//button[contains(@data-testid,'breadcrumb-indietro-button')]")
    WebElement indietroButton;

    private int numeriStatiNotifica;

    public DettaglioNotificaMittenteSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDettaglioNotificaSection() {
        try {
            By titleDettaglioNotificaField = By.id("title-of-page");
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(titleDettaglioNotificaField));
            logger.info("Dettaglio Notifica Section caricata");
        } catch (TimeoutException e) {
            logger.error("Dettaglio Notifica Section non caricata con errore: " + e.getMessage());
            Assert.fail("Dettaglio Notifica Section non caricata con errore: " + e.getMessage());
        }

    }

    public Map<String, String> recuperoInfoNotifiche() {
        Map<String, String> infoNotifica = new HashMap<>();
        String mittente = getInfoNotifica(0);
        infoNotifica.put("mittente", mittente);
        String destinatario = getInfoNotifica(1);
        String codiceFiscale = getInfoNotifica(2);
        if (destinatario.contains(" - ")) {
            String[] splittedDestinatario = destinatario.split(" - ");
            String[] splittedDestinatarioName = splittedDestinatario[1].split("\n");
            destinatario = splittedDestinatario[0];
            codiceFiscale = splittedDestinatarioName[0];
            infoNotifica.put("destinatario", destinatario);
            infoNotifica.put("codiceFiscale", codiceFiscale);
            String data = getInfoNotifica(2);
            infoNotifica.put("data", data);
            if (controlloCodice()) {
                String codiceIUN = getInfoNotifica(3);
                infoNotifica.put("codiceIUN", codiceIUN);
                infoNotifica.put("codiceAvviso", "nd");
            } else {
                String codiceAvviso = getInfoNotifica(3);
                infoNotifica.put("codiceAvviso", codiceAvviso);
                infoNotifica.put("codiceIUN", "nd");
            }
        } else {
            infoNotifica.put("destinatario", destinatario);
            infoNotifica.put("codiceFiscale", codiceFiscale);
            String data = getInfoNotifica(3);
            infoNotifica.put("data", data);
            if (controlloCodice()) {
                String codiceIUN = getInfoNotifica(4);
                infoNotifica.put("codiceIUN", codiceIUN);
                infoNotifica.put("codiceAvviso", "nd");
            } else {
                String codiceAvviso = getInfoNotifica(4);
                infoNotifica.put("codiceAvviso", codiceAvviso);
                infoNotifica.put("codiceIUN", "nd");
            }
        }
        return infoNotifica;
    }


    private boolean controlloCodice() {
        try {
            By codiceIUNBy = By.xpath("//td[contains(text(),'Codice IUN')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiceIUNBy));
            logger.info("codice iun presente");
            return true;
        } catch (TimeoutException e) {
            logger.info("codice iun non presente");
            return false;
        }
    }

    public String getInfoNotifica(int i) {
        return infoNotifiche.get(i).getText();
    }

    public boolean controlloTestoFile(String nameFile, String testoDaControllare) {
        String basePathFile = "src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nameFile + ".pdf";
        File file = new File(basePathFile);
        logger.info("percorso file: " + file.getAbsolutePath());
        try {
            PDDocument pdfFile = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String testoFile = pdfStripper.getText(pdfFile).replaceAll("\r\n|\r|\n", "");
            logger.info("check corrispondenza testo con pdf");
            if (testoFile.contains(testoDaControllare)) {
                pdfFile.close();
                return true;
            }
            pdfFile.close();
        } catch (IOException e) {
            logger.error("File non trovato con errore: " + e.getMessage());
            Assert.fail("File non trovato con errore: " + e.getMessage());
        }
        return false;
    }

    public void clickVediPiuDettaglio() {
        By percorsoNotificaBy = By.xpath("//div[contains(@data-testid,'itemStatus')]");
        getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(percorsoNotificaBy));
        numeriStatiNotifica = elements(percorsoNotificaBy).size();
        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(vediDettagliButton.get(0)));
        logger.info("click su vedi dettagli");
        vediDettagliButton.get(0).click();
        try{
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(vediDettagliButton.get(1)));
            vediDettagliButton.get(1).click();
        } catch (Exception e) {
            logger.info("ulteriore vedi dettaglio non presente");
        }

    }

    public void siVisualizzaPercorsoNotifica() {
        try {
            By newPercorsoNotificaBy = By.xpath("//div[contains(@data-testid,'itemStatus')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(newPercorsoNotificaBy));
            if (this.elements(newPercorsoNotificaBy).size() > this.numeriStatiNotifica) {
                logger.info("TA_QA: L'elenco completo degli stati presente");
            }
        } catch (NoSuchElementException e) {
            logger.error("TA_QA: L'elenco completo degli stati NON presente con errore: " + e.getMessage());
            Assert.fail("TA_QA: L'elenco completo degli stati NON presentecon errore: " + e.getMessage());
        }
    }

    public void clickIndietroButton() {
        logger.info("click su pulsante indietro");
        this.indietroButton.click();
    }

    public boolean controlloTestoFileCodiceIUN(String fileName, String codiceIUN) {
        String basePathFile = "src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + fileName + ".pdf";
        File file = new File(basePathFile);
        try {
            PDDocument pdfFile = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String testoFile = pdfStripper.getText(pdfFile).replaceAll("\r\n|\r|\n", " ");
            String testoDaControllare = codiceIUN.split("-")[0];
            if (testoFile.contains(testoDaControllare)) {
                pdfFile.close();
                return true;
            }
            pdfFile.close();
        } catch (IOException e) {
            logger.error("File non trovato con errore: " + e.getMessage());
            Assert.fail("File non trovato con errore: " + e.getMessage());
        }
        return false;

    }

    public boolean controlloTestoFileData(String fileName, String testoDaControllare) {
        String basePathFile = "src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + fileName + ".pdf";
        File file = new File(basePathFile);
        try {
            PDDocument pdfFile = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String testoFile = pdfStripper.getText(pdfFile).replaceAll("\r\n|\r|\n", " ");
            if (testoDaControllare.equals("Oggi")) {
                String data = LocalDate.now().toString();
                String[] date = data.split("-");
                testoDaControllare = date[2] + "/" + date[1] + "/" + date[0];
            }
            if (testoFile.contains(testoDaControllare)) {
                pdfFile.close();
                return true;
            }
            pdfFile.close();
        } catch (IOException e) {
            logger.error("File non trovato con errore: " + e.getMessage());
            Assert.fail("File non trovato con errore: " + e.getMessage());
        }
        return false;
    }

    public void clickLinkDocumentiAllegati() {
        if (this.linkAllegati.get(0).isDisplayed()) {
            this.linkAllegati.get(0).click();
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", this.linkAllegati.get(0));
            logger.info("click sul link allegati");
            this.linkAllegati.get(0).click();
        }
    }

    public void clickLinkAvvenutaRicezione(int i) {
        logger.info("click sul link avvenuta ricezione");
        if (this.linkAllegati.get(i).isDisplayed()) {
            this.linkAllegati.get(i).click();
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", this.linkAllegati.get(i));
            this.linkAllegati.get(i).click();
        }
    }

    public int getLinkAvvenutaRicezione() {
        return linkAllegati.size();
    }

    public void clickLinkAttestazioneOpponibile(String nomeFile) {
        try {
            By fileLinkBy = By.xpath("//button[contains(text(),'" + nomeFile + "')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(fileLinkBy));
            List<WebElement> fileLink = this.elements(fileLinkBy);
            if (fileLink.get(0).isDisplayed()) {
                fileLink.get(0).click();
            } else {
                this.js().executeScript("arguments[0].scrollIntoView(true);", fileLink);
                fileLink.get(0).click();
            }
        } catch (TimeoutException e) {
            logger.error("Non riuscito ad trovare il link con errore: " + e.getMessage());
            Assert.fail("Non riuscito ad trovare il link con errore: " + e.getMessage());
        }
    }

    public void verificaInvioPECInCorso() {
        try {
            By invioPec = By.xpath("//div/span[contains(text(),'Invio via PEC')]/following-sibling::div//p[contains(text(),'È in corso l')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(invioPec));
            logger.info("La pec è in stato invio in corso");
        } catch (TimeoutException e) {
            logger.error("La pec NON è in stato invio in corso con errore: " + e.getMessage());
            Assert.fail("La pec NON è in stato invio in corso con errore: " + e.getMessage());
        }
    }

    public String getTextLinkAvvenutaRicezione(int i) {
        return linkAllegati.get(i).getText();
    }

    public String getTextDocumentiAllegati() {
        return linkAllegati.get(0).getText();
    }

    public void checkStatoTimeline(String statoTimeline){
        try {
            By stato = By.xpath(statoTimeline);
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(stato));
            logger.info("stato timeline checkato con successo avvenuta");
        } catch (TimeoutException e) {
            logger.error("checkato stato timeline non avvenuta con errore: " + e.getMessage());
            Assert.fail("checkato stato timeline non avvenuta con errore: " + e.getMessage());
        }
    }

    public void siCliccaSuAllegatoInTimeline(String xpath) {
        try {
            By allegatoTimeline = By.xpath(xpath);
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(allegatoTimeline));
            element(allegatoTimeline).click();
            checkURL("pn-safestorage");
            driver.navigate().back();
            logger.info("allegato timeline trovato con successo");
        } catch (TimeoutException e) {
            logger.error("allegato timeline trovato non con successo: " + e.getMessage());
            Assert.fail("allegato timeline trovato non con successo: " + e.getMessage());
        }

    }
}
