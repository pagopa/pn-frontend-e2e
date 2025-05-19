package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DettaglioNotificaMittenteSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("DettaglioNotificaMittenteSection");


    @FindBy(id = "more-less-timeline-step")
    List<WebElement> vediDettagliButton;

    @FindBy(xpath = "//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-paddingNone MuiTableCell-sizeMedium css-11dv4ll')]")
    List<WebElement> infoNotifiche;

    @FindBy(xpath = "//button[contains(@data-testid,'documentButton')]")
    List<WebElement> linkAllegati;

    @FindBy(xpath = "//button[contains(@data-testid,'breadcrumb-indietro-button')]")
    WebElement indietroButton;

    @FindBy(xpath = "//div[@data-testid='paymentInfoBox']")
    WebElement containerPaymentBox;

    @FindBy(id = "recipients-select")
    WebElement selectMultiDestinatario;

    @FindBy(xpath = "//span[contains(text(),'Codice Avviso')]")
    WebElement codiceAvvisoMittente;

    @FindBy(xpath = "//*[@id='row-value-5']/div")
    WebElement codiceIUN;

    @FindBy(id = "IUN")
    WebElement iunInput;

    @FindBy(id = "Numero Ticket")
    WebElement numeroTicketInput;

    @FindBy(id = "ricerca")
    WebElement buttonRicerca;

    @FindBy(xpath = "//span[contains(text(), 'Amministratore')]")
    WebElement enteButton;

    @FindBy(xpath = "//div[3]/div/div[1]/div/div/input")
    WebElement apiKeyField;

    private int numeriStatiNotifica;

    private PiattaformaNotifichePage piattaformaNotifichePage;

    private WebTool webTool;

    public DettaglioNotificaMittenteSection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
        piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
    }

    public void waitLoadDettaglioNotificaSection() {
       // WebElement titleDettaglioNotificaField = driver.findElement(By.id("title-of-page"));
        getWebDriverWait(20).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("title-of-page"))));
        logger.info("Dettaglio Notifica Section caricata");
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
          //  WebElement codiceIUNBy = driver.findElement(By.xpath("//td[contains(text(),'Codice IUN')]"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//td[contains(text(),'Codice IUN')]"))));
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
            Assertions.fail("File non trovato con errore: " + e.getMessage());
        }
        return false;
    }


    public boolean controlloSHAFile(String nameFile) {
        String basePathFile = "src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nameFile + ".pdf";
        File file = new File(basePathFile);
        logger.info("percorso file: " + file.getAbsolutePath());
        // Define the regex pattern for SHA-256
        String sha256Pattern = "\\b[A-Fa-f0-9]{64}\\b";
        Pattern pattern = Pattern.compile(sha256Pattern);

        try {
            PDDocument pdfFile = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String testoFile = pdfStripper.getText(pdfFile).replaceAll("\r\n|\r|\n", "");
            Matcher matcher = pattern.matcher(testoFile);
            logger.info("check corrispondenza testo con pdf");
            if (matcher.find()) {
                pdfFile.close();
                return true;
            }
            pdfFile.close();
        } catch (IOException e) {
            logger.error("File non trovato con errore: " + e.getMessage());
            Assertions.fail("File non trovato con errore: " + e.getMessage());
        }
        return false;
    }

    public void clickVediPiuDettaglio() {
        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.id("more-less-timeline-step")).get(0)));
        logger.info("click su vedi dettagli");
        vediDettagliButton = driver.findElements(By.id("more-less-timeline-step"));
        vediDettagliButton.get(0).click();
        try {
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(driver.findElements(By.id("more-less-timeline-step")).get(1)));
            vediDettagliButton.get(1).click();
        } catch (Exception e) {
            logger.info("ulteriore vedi dettaglio non presente");
        }
    }

    public void siVisualizzaPercorsoNotifica() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//div[contains(@data-testid,'itemStatus')]"))));
            List<WebElement> newPercorsoNotificaBy = driver.findElements(By.xpath("//div[contains(@data-testid,'itemStatus')]"));
            if (newPercorsoNotificaBy.size() > numeriStatiNotifica) {
                logger.info("TA_QA: L'elenco completo degli stati presente");
            }
        } catch (NoSuchElementException e) {
            logger.error("TA_QA: L'elenco completo degli stati NON presente con errore: " + e.getMessage());
            Assertions.fail("TA_QA: L'elenco completo degli stati NON presentecon errore: " + e.getMessage());
        }
    }

    public void clickIndietroButton() {
        logger.info("click su pulsante indietro");
        indietroButton = driver.findElement(By.xpath("//button[contains(@data-testid,'breadcrumb-indietro-button')]"));
        indietroButton.click();
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
            Assertions.fail("File non trovato con errore: " + e.getMessage());
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
            Assertions.fail("File non trovato con errore: " + e.getMessage());
        }
        return false;
    }

    public void clickLinkDocumentiAllegati() {
        linkAllegati = driver.findElements(By.xpath("//button[contains(@data-testid,'documentButton')]"));
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
        linkAllegati = driver.findElements(By.xpath("//button[contains(@data-testid,'documentButton')]"));
        if (this.linkAllegati.get(i).isDisplayed()) {
            this.linkAllegati.get(i).click();
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", this.linkAllegati.get(i));
            this.linkAllegati.get(i).click();
        }
    }

    public int getLinkAvvenutaRicezione() {
        linkAllegati = driver.findElements(By.xpath("//button[contains(@data-testid,'documentButton')]"));
        return linkAllegati.size();
    }

    public void clickLinkAttestazioneOpponibile(String nomeFile) {
        try {
            List<WebElement> fileLinkBy = driver.findElements(By.xpath("//button[contains(text(),'" + nomeFile + "')]"));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(fileLinkBy));
            if (fileLinkBy.get(0).isDisplayed()) {
                fileLinkBy.get(0).click();
            } else {
                this.js().executeScript("arguments[0].scrollIntoView(true);", fileLinkBy);
                fileLinkBy.get(0).click();
            }
        } catch (TimeoutException e) {
            logger.error("Non riuscito ad trovare il link con errore: " + e.getMessage());
            Assertions.fail("Non riuscito ad trovare il link con errore: " + e.getMessage());
        }
    }

    public void verificaInvioPECInCorso() {
        try {
            getWebDriverWait(50)
                    .withMessage("Impossibile trovare Invio via PEC ")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/span[contains(text(),'Invio via PEC')]/following-sibling::div//p[contains(text(),'È in corso l')]")));
            logger.info("La pec è in stato invio in corso");
        } catch (TimeoutException e) {
            Assertions.fail("La pec NON è in stato invio in corso con errore: " + e.getMessage());
        }
    }

    public String getTextLinkAvvenutaRicezione(int i) {
        linkAllegati = driver.findElements(By.xpath("//button[contains(@data-testid,'documentButton')]"));
        return linkAllegati.get(i).getText();
    }

    public String getTextDocumentiAllegati() {
        linkAllegati = driver.findElements(By.xpath("//button[contains(@data-testid,'documentButton')]"));
        return linkAllegati.get(0).getText();
    }


    public void checkNumeroFallimentiInvioViaPEC(int numeroFallimenti) {
        try {
            List<WebElement> invioPECFallitoBy = driver.findElements(By.xpath("//span[text()='Invio via PEC fallito']"));
            logger.info("L'invio della notifica è fallito questo numero di volte: " + invioPECFallitoBy.size());
            if (invioPECFallitoBy.size() != numeroFallimenti) {
                logger.error("L'invio della notifica non è fallito " + numeroFallimenti + " volta/e");
                Assertions.fail("L'invio della notifica non è fallito " + numeroFallimenti + " volta/e");
            }
        } catch (TimeoutException e) {
            logger.error("NON è fallito l'invio della notifica: " + e.getMessage());
            Assertions.fail("NON è fallito l'invio della notifica: " + e.getMessage());
        }
    }

    public void checkStatoTimeline(String statoTimeline) {
        try {
           // WebElement stato = driver.findElement(By.xpath(statoTimeline));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(statoTimeline))));
            logger.info("stato timeline checkato con successo avvenuta");
        } catch (TimeoutException e) {
            logger.error("checkato stato timeline non avvenuta con errore: " + e.getMessage());
            Assertions.fail("checkato stato timeline non avvenuta con errore: " + e.getMessage());
        }
    }

    public void siCliccaSuAllegatoInTimeline(String xpath) {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath(xpath))));
            WebElement allegatoTimeline = driver.findElement(By.xpath(xpath));
            allegatoTimeline.click();
            checkURL("pn-safestorage");
            driver.navigate().back();
            logger.info("allegato timeline trovato con successo");
        } catch (TimeoutException e) {
            logger.error("allegato timeline trovato non con successo: " + e.getMessage());
            Assertions.fail("allegato timeline trovato non con successo: " + e.getMessage());
        }

    }

    public void siVerificaLaCliccabilitaSuAllegatoInTimeline(String xpath) {
        vaiInFondoAllaPagina();
        //WebElement allegatoTimeline = driver.findElement(By.xpath(xpath));
        getWebDriverWait(10).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))), ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath)))));
        logger.info("allegato timeline trovato con successo e cliccabile");
    }

    public void checkInvioADomicilioDiPiattaforma(String domicilioDiPiattaforma) {
        try {
            //WebElement invioDomicilioDiPiattaformaBy = driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC riuscito') and (//div[contains(p/text(), '" + domicilioDiPiattaforma + "')])]"));
            getWebDriverWait(10).withMessage("Non si visualizza l'invio della notifica al domicilio di piattaforma nella timeline").until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC riuscito') and (//div[contains(p/text(), '" + domicilioDiPiattaforma + "')])]"))));
        } catch (TimeoutException e) {
            logger.error("L'invio della notifica al domicilio di piattaforma indicato non viene effettuato con errore: " + e.getMessage());
            Assertions.fail("L'invio della notifica al domicilio di piattaforma indicato non viene effettuato con errore: " + e.getMessage());
        }

    }

    public void checkDoppioFallimentoInvioViaPEC(int numeroFallimenti) {
        try {
            List<WebElement> invioPECFallitoBy = driver.findElements(By.xpath("//span[text()='Invio via PEC fallito']"));
            logger.info("L'invio della notifica è fallito questo numero di volte: " + invioPECFallitoBy.size());
            if (invioPECFallitoBy.size() != numeroFallimenti) {
                logger.error("L'invio della notifica non è fallito " + numeroFallimenti + " volta/e");
                Assertions.fail("L'invio della notifica non è fallito " + numeroFallimenti + " volta/e");
            }
        } catch (TimeoutException e) {
            logger.error("NON è fallito l'invio della notifica: " + e.getMessage());
            Assertions.fail("NON è fallito l'invio della notifica: " + e.getMessage());
        }
    }

    public void checkInvioRaccomandataSemplice() {
        try {
           // WebElement invioRaccomandataSemplice = driver.findElement(By.xpath("//span[text()='Invio via raccomandata semplice']"));
            getWebDriverWait(10).withMessage("Non si visualizza l'invio della raccomandata nella timeline della notifica").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Invio via raccomandata semplice']"))));
            logger.info("L'invio della notifica avviene via raccomandata semplice");
        } catch (TimeoutException e) {
            logger.error("L'invio della notifica per raccomandata non viene visualizzato: " + e.getMessage());
            Assertions.fail("L'invio della notifica per raccomandata non viene visualizzato: " + e.getMessage());
        }
    }

    public void checkTentatoInvioADomicilioSpeciale(String domicilioSpeciale) {
        try {
          //  WebElement invioDomicilioSpecialeBy = driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC fallito') and (//div[contains(p/text(), '" + domicilioSpeciale + "')])]"));
            getWebDriverWait(10).withMessage("Non si visualizza l'invio della notifica al domicilio speciale nella timeline").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC fallito') and (//div[contains(p/text(), '" + domicilioSpeciale + "')])]"))));
        } catch (TimeoutException e) {
            logger.error("L'invio della notifica al domicilio speciale indicato non viene effettuato con errore: " + e.getMessage());
            Assertions.fail("L'invio della notifica al domicilio speciale indicato non viene effettuato con errore: " + e.getMessage());
        }
    }

    public void checkStepInvioNotificaViaPEC(String emailPEC) {
       // WebElement invioViaPECBy = driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC') and (//div[contains(p/text(), '" + emailPEC + "')])]"));
       // WebElement invioPresoInCaricoBy = driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC preso in carico') and (//div[contains(p/text(), '" + emailPEC + "')])]"));
       // WebElement invioRiuscitoBy = driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC riuscito') and (//div[contains(p/text(), '" + emailPEC + "')])]"));
        getWebDriverWait(10).withMessage("Non si visualizza il tentativo di invio della notifica al domicilio generale").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC') and (//div[contains(p/text(), '" + emailPEC + "')])]"))));
        getWebDriverWait(10).withMessage("Non si visualizza la presa in carico dell'invio della notifica al domicilio generale").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC preso in carico') and (//div[contains(p/text(), '" + emailPEC + "')])]"))));
        getWebDriverWait(10).withMessage("Non si visualizza la riuscita dell'invio della notifica al domicilio generale").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC riuscito') and (//div[contains(p/text(), '" + emailPEC + "')])]"))));
    }

    public void checkAvvisoPagoPa() {
        try {
            //WebElement boxPagamento = driver.findElement(By.xpath("//div[@data-testid='payment-item']"));
            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"))));
            containerPaymentBox = driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"));
            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
            getWebDriverWait(10).withMessage("Non si visualizza l'avviso PagoPA per il pagamento della notifica").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='payment-item']"))));
        } catch (TimeoutException e) {
            logger.error("Box per il pagamento della notifica non visualizzato correttamente con errore: " + e.getMessage());
            Assertions.fail("Box per il pagamento della notifica non visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public boolean checkAvvisoPagoPaVisibile() {
        try {
            //WebElement avvisoButton = driver.findElement(By.xpath("//button[contains(text(),'Avviso pagoPA')]"));
            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"))));
            containerPaymentBox = driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"));
            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
            getWebDriverWait(10).withMessage("Non si visualizza l'avviso PagoPA per il pagamento della notifica").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Avviso pagoPA')]"))));
            return true;
        } catch (TimeoutException e) {
            logger.error("Non si visualizza l'avviso PagoPA per il pagamento della notifica");
            return false;
        }
    }

    public boolean checkCodiceAvvisoVisibile() {
        try {
           // codiceAvvisoMittente = driver.findElement(By.xpath("//span[contains(text(),'Codice Avviso')]"));
            getWebDriverWait(5).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Codice Avviso')]")))).isDisplayed();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public void clickAvvisoPagoPa() {
        try {
            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"))));
            containerPaymentBox = driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"));
            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
            getWebDriverWait(10).withMessage("Non si visualizza l'avviso PagoPA per il pagamento della notifica").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Avviso pagoPA')]"))));
            WebElement avvisoPagoPa = driver.findElement(By.xpath("//button[contains(text(),'Avviso pagoPA')]"));
            avvisoPagoPa.click();
        } catch (TimeoutException e) {
            logger.error("Box per il pagamento della notifica non visualizzato correttamente con errore: " + e.getMessage());
            Assertions.fail("Box per il pagamento della notifica non visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public void checkModelloF24() {
       // WebElement modelloF24 = driver.findElement(By.xpath("//span[@data-testid='f24']"));
        getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"))));
        containerPaymentBox = driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"));
        js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
        getWebDriverWait(10).withMessage("Non si visualizza il contenitore del modello F24").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@data-testid='f24']"))));

    }

    public void checkBoxModelloF24() {
        try {
          //  WebElement boxModelloF24 = driver.findElement(By.xpath("//span[contains(text(),'Modelli F24 allegati')]"));
            getWebDriverWait(10).withMessage("Non si visualizza il box allegati modelli F24").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Modelli F24 allegati')]"))));
        } catch (TimeoutException e) {
            logger.error("Box del modello F24 non visualizzato correttamente con errore: " + e.getMessage());
            Assertions.fail("Box del modello F24 non visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public void siCliccaSulBottoneChiudi() {
        getWebDriverWait(10).withMessage("Il bottone chiudi non cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@data-testid='close-dialog']"))));
        WebElement chiudiBoxF24 = driver.findElement(By.xpath("//button[@data-testid='close-dialog']"));
        chiudiBoxF24.click();
    }

    public void checkBoxPagamentoMultiDestinatario() {
        try {
            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"))));
            containerPaymentBox = driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"));
            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
            getWebDriverWait(10).withMessage("Non si visualizza l'input per la selezione del destinatario").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("recipients-select"))));
            selectMultiDestinatario = driver.findElement(By.id("recipients-select"));
        } catch (TimeoutException e) {
            logger.error("Dettaglio notifica multi destinatario non ancora pagata visualizzata non correttamente con errore: " + e.getMessage());
            Assertions.fail("Dettaglio notifica multi destinatario non ancora pagata visualizzata non correttamente con errore: " + e.getMessage());
        }
    }

    public void clickMultiDestinatario() {
        try {
            getWebDriverWait(10).withMessage("L'input per la selezione del destinatario non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("recipients-select"))));
            selectMultiDestinatario = driver.findElement(By.id("recipients-select"));
            js().executeScript("arguments[0].scrollIntoView(true)", selectMultiDestinatario);
            selectMultiDestinatario.click();
            getWebDriverWait(10).withMessage("Non si visualizza la lista delle opzioni destinatario").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//li[@role='option']"))));
            List<WebElement> selectOption = driver.findElements(By.xpath("//li[@role='option']"));
            selectOption.get(0).click();
        } catch (TimeoutException e) {
            logger.error("Non è stato possibile selezionare un destinatario con errore: " + e.getMessage());
            Assertions.fail("Non è stato possibile selezionare un destinatario con errore: " + e.getMessage());
        }
    }

    public void checkAttestazionePresaInCarico() {
        try {
            //WebElement attestazionePresaInCarico = driver.findElement(By.xpath("//button[contains(text(),'presa in carico')]"));
            getWebDriverWait(10).withMessage("Non si visualizza attestazione opponibile a terzi notifica presa in carico").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'presa in carico')]"))));
        } catch (TimeoutException e) {
            logger.error("Attestazione opponibile a terzi notifica presa in carico non visualizzato correttamente con errore: " + e.getMessage());
            Assertions.fail("Attestazione opponibile a terzi notifica presa in carico non visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public void checkAlertRADD() {
        //WebElement alertRADD = driver.findElement(By.xpath("//div[@data-testid='raddAlert']"));
        getWebDriverWait(10).withMessage("Non si visualizza l'alert radd").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='raddAlert']"))));
    }

    public void checkInvioMessaggioDiCortesia() {
        boolean testSuccess = false;
        for (int i = 0; i < 8; i++) {
            try {
                WebElement vediPiuDettagli = driver.findElement(By.id("more-less-timeline-step"));
                if (vediPiuDettagli != null) {
                    vediPiuDettagli.click();
                }
                WebElement messaggioCortesia = driver.findElement(By.xpath("//span[contains(text(), 'Invio del messaggio di cortesia')]"));
                if (messaggioCortesia != null) {
                    logger.info("L'invio del messaggio al contatto di cortesia è avvenuto");
                    testSuccess = true;
                    break;
                }
            } catch (NoSuchElementException e) {
                logger.info("Dopo " + (i + 1) + " tentativi l'invio del messaggio al contatto di cortesia non è avvenuto");
            }
            webTool.waitTime(15);
            driver.navigate().refresh();
        }
        if (!testSuccess) {
            logger.error("L'invio del messaggio al contatto di cortesia non è avvenuto");
            Assertions.fail("L'invio del messaggio al contatto di cortesia non è avvenuto");
        }
    }

    public String salvaIUN() {
        codiceIUN = driver.findElement(By.xpath("//*[@id='row-value-5']/div"));
        return codiceIUN.getText();
    }

    public String getApiKey() {
        return apiKeyField.getAttribute("value");
    }

    public void insertIunSalvatoAndRicercaOnPage(String iun) {
        logger.info("inserisco numero ticket");
        numeroTicketInput = driver.findElement(By.id("Numero Ticket"));
        numeroTicketInput.sendKeys("testTAFE01");
        logger.info("inserisco codice IUN");
        iunInput = driver.findElement(By.id("IUN"));
        iunInput.sendKeys(iun);
        logger.info("clicco sul bottone di ricerca");

        getWebDriverWait(30).withMessage("bottone per la ricerca non trovato").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("ricerca"))));
        buttonRicerca = driver.findElement(By.id("ricerca"));
        buttonRicerca.click();
        webTool.waitTime(3);
    }

    public void sceglieEnte(String nomeEnte) {
        logger.info("Si sceglie ente: " + nomeEnte);
        enteButton = driver.findElement(By.xpath("//span[contains(text(), 'Amministratore')]"));
        enteButton.click();
        webTool.waitTime(1);
        WebElement ente = driver.findElement(By.xpath("//h6[contains(text(), '" + nomeEnte + "')]"));
        ente.click();
    }

    public void ricercaNotificaConIunSalvatoMittente(String iun) {
        piattaformaNotifichePage.inserimentoCodiceIUN(iun);
        piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
    }

    public void ricercaNotificaConIunSalvatoDestinatario(String iun) {
        piattaformaNotifichePage.inserimentoCodiceIUN(iun);
        piattaformaNotifichePage.selectFiltraNotificaButtonDestinatario();
    }

    public void selezionaServizioNotificheDigitale() {
        WebElement button = getWebDriverWait(30).withMessage("Servizio Notifiche Digitali non trovato").until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[contains(text(),'Servizio Notifiche Digitali')]")
        ));
        button.click();
    }

    public void clickLaTuaImpresa(String testo) {

        WebElement button = getWebDriverWait(30).withMessage("click La Tua Impresa non trovato").until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(text(),'"+testo+"')]")
        ));
        button.click();
    }
}