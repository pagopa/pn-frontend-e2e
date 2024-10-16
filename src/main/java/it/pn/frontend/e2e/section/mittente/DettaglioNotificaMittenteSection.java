package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.utility.WebTool;
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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public DettaglioNotificaMittenteSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDettaglioNotificaSection() {
            By titleDettaglioNotificaField = By.id("title-of-page");
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(titleDettaglioNotificaField));
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
            Assert.fail("File non trovato con errore: " + e.getMessage());
        }
        return false;
    }

    public void clickVediPiuDettaglio() {

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


    public void checkNumeroFallimentiInvioViaPEC(int numeroFallimenti) {
        try {
            By invioPECFallitoBy = By.xpath("//span[text()='Invio via PEC fallito']");
            List<WebElement> invioPECFallitoList = driver.findElements(invioPECFallitoBy);
            logger.info("L'invio della notifica è fallito questo numero di volte: " + invioPECFallitoList.size());
            if (invioPECFallitoList.size() != numeroFallimenti) {
                logger.error("L'invio della notifica non è fallito " + numeroFallimenti + " volta/e");
                Assert.fail("L'invio della notifica non è fallito " + numeroFallimenti + " volta/e");
            }
        } catch (TimeoutException e) {
            logger.error("NON è fallito l'invio della notifica: " + e.getMessage());
            Assert.fail("NON è fallito l'invio della notifica: " + e.getMessage());
        }
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

    public void siVerificaLaCliccabilitaSuAllegatoInTimeline(String xpath) {
            vaiInFondoAllaPagina();
            By allegatoTimeline = By.xpath(xpath);
            getWebDriverWait(10).until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(allegatoTimeline), ExpectedConditions.elementToBeClickable(allegatoTimeline)));
            logger.info("allegato timeline trovato con successo e cliccabile");
    }

    public void checkInvioADomicilioDiPiattaforma(String domicilioDiPiattaforma) {
        try {
            By invioDomicilioDiPiattaformaBy = By.xpath("//div[contains(span/text(), 'Invio via PEC riuscito') and (//div[contains(p/text(), '" + domicilioDiPiattaforma + "')])]");
            getWebDriverWait(10).withMessage("Non si visualizza l'invio della notifica al domicilio di piattaforma nella timeline").until(ExpectedConditions.visibilityOfElementLocated(invioDomicilioDiPiattaformaBy));
        } catch (TimeoutException e) {
            logger.error("L'invio della notifica al domicilio di piattaforma indicato non viene effettuato con errore: " + e.getMessage());
            Assert.fail("L'invio della notifica al domicilio di piattaforma indicato non viene effettuato con errore: " + e.getMessage());
        }

    }

    public void checkDoppioFallimentoInvioViaPEC(int numeroFallimenti) {
        try {
            By invioPECFallitoBy = By.xpath("//span[text()='Invio via PEC fallito']");
            List<WebElement> invioPECFallitoList = driver.findElements(invioPECFallitoBy);
            logger.info("L'invio della notifica è fallito questo numero di volte: " + invioPECFallitoList.size());
            if (invioPECFallitoList.size() != numeroFallimenti) {
                logger.error("L'invio della notifica non è fallito " + numeroFallimenti + " volta/e");
                Assert.fail("L'invio della notifica non è fallito " + numeroFallimenti + " volta/e");
            }
        } catch (TimeoutException e) {
            logger.error("NON è fallito l'invio della notifica: " + e.getMessage());
            Assert.fail("NON è fallito l'invio della notifica: " + e.getMessage());
        }
    }

    public void checkInvioRaccomandataSemplice() {
        try {
            By invioRaccomandataSemplice = By.xpath("//span[text()='Invio via raccomandata semplice']");
            getWebDriverWait(10).withMessage("Non si visualizza l'invio della raccomandata nella timeline della notifica").until(ExpectedConditions.visibilityOfElementLocated(invioRaccomandataSemplice));
            logger.info("L'invio della notifica avviene via raccomandata semplice");
        } catch (TimeoutException e) {
            logger.error("L'invio della notifica per raccomandata non viene visualizzato: " + e.getMessage());
            Assert.fail("L'invio della notifica per raccomandata non viene visualizzato: " + e.getMessage());
        }
    }

    public void checkTentatoInvioADomicilioSpeciale(String domicilioSpeciale) {
        try {
            By invioDomicilioSpecialeBy = By.xpath("//div[contains(span/text(), 'Invio via PEC fallito') and (//div[contains(p/text(), '" + domicilioSpeciale + "')])]");
            getWebDriverWait(10).withMessage("Non si visualizza l'invio della notifica al domicilio speciale nella timeline").until(ExpectedConditions.visibilityOfElementLocated(invioDomicilioSpecialeBy));
        } catch (TimeoutException e) {
            logger.error("L'invio della notifica al domicilio speciale indicato non viene effettuato con errore: " + e.getMessage());
            Assert.fail("L'invio della notifica al domicilio speciale indicato non viene effettuato con errore: " + e.getMessage());
        }
    }

    public void checkStepInvioNotificaViaPEC(String emailPEC) {
            By invioViaPECBy = By.xpath("//div[contains(span/text(), 'Invio via PEC') and (//div[contains(p/text(), '" + emailPEC + "')])]");
            By invioPresoInCaricoBy = By.xpath("//div[contains(span/text(), 'Invio via PEC preso in carico') and (//div[contains(p/text(), '" + emailPEC + "')])]");
            By invioRiuscitoBy = By.xpath("//div[contains(span/text(), 'Invio via PEC riuscito') and (//div[contains(p/text(), '" + emailPEC + "')])]");
            getWebDriverWait(10).withMessage("Non si visualizza il tentativo di invio della notifica al domicilio generale").until(ExpectedConditions.visibilityOfElementLocated(invioViaPECBy));
            getWebDriverWait(10).withMessage("Non si visualizza la presa in carico dell'invio della notifica al domicilio generale").until(ExpectedConditions.visibilityOfElementLocated(invioPresoInCaricoBy));
            getWebDriverWait(10).withMessage("Non si visualizza la riuscita dell'invio della notifica al domicilio generale").until(ExpectedConditions.visibilityOfElementLocated(invioRiuscitoBy));
    }

    public void checkAvvisoPagoPa() {
        try {
            By boxPagamento = By.xpath("//div[@data-testid='payment-item']");
            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(containerPaymentBox));
            getWebDriverWait(10).withMessage("Non si visualizza l'avviso PagoPA per il pagamento della notifica").until(ExpectedConditions.visibilityOfElementLocated(boxPagamento));
        } catch (TimeoutException e) {
            logger.error("Box per il pagamento della notifica non visualizzato correttamente con errore: " + e.getMessage());
            Assert.fail("Box per il pagamento della notifica non visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public boolean checkAvvisoPagoPaVisibile() {
        try {
            By avvisoButton = By.xpath("//button[contains(text(),'Avviso pagoPA')]");
            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(containerPaymentBox));
            getWebDriverWait(10).withMessage("Non si visualizza l'avviso PagoPA per il pagamento della notifica").until(ExpectedConditions.visibilityOfElementLocated(avvisoButton));
            return true;
        } catch (TimeoutException e) {
            logger.error("Non si visualizza l'avviso PagoPA per il pagamento della notifica");
            return false;
        }
    }

    public boolean checkCodiceAvvisoVisibile() {
        try {
            getWebDriverWait(5).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(codiceAvvisoMittente)).isDisplayed();
            return true;
        }catch (RuntimeException e){
            return false;
        }
    }
    public void clickAvvisoPagoPa() {
        try {
            By avvisoPagoPa = By.xpath("//button[contains(text(),'Avviso pagoPA')]");
            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(containerPaymentBox));
            getWebDriverWait(10).withMessage("Non si visualizza l'avviso PagoPA per il pagamento della notifica").until(ExpectedConditions.visibilityOfElementLocated(avvisoPagoPa));
            this.element(avvisoPagoPa).click();
        } catch (TimeoutException e) {
            logger.error("Box per il pagamento della notifica non visualizzato correttamente con errore: " + e.getMessage());
            Assert.fail("Box per il pagamento della notifica non visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public void checkModelloF24() {
            By modelloF24 = By.xpath("//span[@data-testid='f24']");
            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(containerPaymentBox));
            getWebDriverWait(10).withMessage("Non si visualizza il contenitore del modello F24").until(ExpectedConditions.visibilityOfElementLocated(modelloF24));

    }

    public void checkBoxModelloF24() {
        try {
            By boxModelloF24 = By.xpath("//span[contains(text(),'Modelli F24 allegati')]");
            getWebDriverWait(10).withMessage("Non si visualizza il box allegati modelli F24").until(ExpectedConditions.visibilityOfElementLocated(boxModelloF24));
        } catch (TimeoutException e) {
            logger.error("Box del modello F24 non visualizzato correttamente con errore: " + e.getMessage());
            Assert.fail("Box del modello F24 non visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public void siCliccaSulBottoneChiudi(){
        By chiudiBoxF24 = By.xpath("//button[@data-testid='close-dialog']");
        getWebDriverWait(10).withMessage("Il bottone chiudi non cliccabile").until(ExpectedConditions.elementToBeClickable(chiudiBoxF24));
        element(chiudiBoxF24).click();
    }

    public void checkBoxPagamentoMultiDestinatario() {
        try {
            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(containerPaymentBox));
            getWebDriverWait(10).withMessage("Non si visualizza l'input per la selezione del destinatario").until(ExpectedConditions.visibilityOf(selectMultiDestinatario));
        } catch (TimeoutException e) {
            logger.error("Dettaglio notifica multi destinatario non ancora pagata visualizzata non correttamente con errore: " + e.getMessage());
            Assert.fail("Dettaglio notifica multi destinatario non ancora pagata visualizzata non correttamente con errore: " + e.getMessage());
        }
    }

    public void clickMultiDestinatario() {
        try {
            js().executeScript("arguments[0].scrollIntoView(true)", selectMultiDestinatario);
            getWebDriverWait(10).withMessage("L'input per la selezione del destinatario non è cliccabile").until(ExpectedConditions.elementToBeClickable(selectMultiDestinatario));
            selectMultiDestinatario.click();
            List<WebElement> selectOption = driver.findElements(By.xpath("//li[@role='option']"));
            getWebDriverWait(10).withMessage("Non si visualizza la lista delle opzioni destinatario").until(ExpectedConditions.visibilityOfAllElements(selectOption));
            selectOption.get(0).click();
        } catch (TimeoutException e) {
            logger.error("Non è stato possibile selezionare un destinatario con errore: " + e.getMessage());
            Assert.fail("Non è stato possibile selezionare un destinatario con errore: " + e.getMessage());
        }
    }

    public void checkAttestazionePresaInCarico() {
        try {
            By attestazionePresaInCarico = By.xpath("//button[contains(text(),'presa in carico')]");
            getWebDriverWait(10).withMessage("Non si visualizza attestazione opponibile a terzi notifica presa in carico").until(ExpectedConditions.visibilityOfElementLocated(attestazionePresaInCarico));
        } catch (TimeoutException e) {
            logger.error("Attestazione opponibile a terzi notifica presa in carico non visualizzato correttamente con errore: " + e.getMessage());
            Assert.fail("Attestazione opponibile a terzi notifica presa in carico non visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public void checkAlertRADD() {
        By alertRADD = By.xpath("//div[@data-testid='raddAlert']");
        getWebDriverWait(10).withMessage("Non si visualizza l'alert radd").until(ExpectedConditions.visibilityOfElementLocated(alertRADD));
    }

    public void checkInvioMessaggioDiCortesia() {
        boolean testSuccess = false;
        for (int i = 0; i < 8; i++) {
            try {
                WebElement vediPiuDettagli = driver.findElement(By.id("more-less-timeline-step"));
                if (vediPiuDettagli != null){
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
            WebTool.waitTime(15);
            driver.navigate().refresh();
        }
        if (!testSuccess) {
            logger.error("L'invio del messaggio al contatto di cortesia non è avvenuto");
            Assert.fail("L'invio del messaggio al contatto di cortesia non è avvenuto");
        }
    }

    public String salvaIUN(){
        return codiceIUN.getText();}

    public String getApiKey(){
        return apiKeyField.getAttribute("value");
    }

    public void insertIunSalvatoAndRicercaOnPage(String iun) {
        logger.info("inserisco numero ticket");
        numeroTicketInput.sendKeys("testTAFE01");
        logger.info("inserisco codice IUN");
        iunInput.sendKeys(iun);
        logger.info("clicco sul bottone di ricerca");

        this.getWebDriverWait(30).withMessage("bottone per la ricerca non trovato").until(ExpectedConditions.elementToBeClickable(buttonRicerca));
        buttonRicerca.click();
        WebTool.waitTime(3);
    }

    public void sceglieEnte(String nomeEnte){
        logger.info("Si sceglie ente: " + nomeEnte);
        enteButton.click();
        WebTool.waitTime(1);
        By ente = By.xpath("//h6[contains(text(), '" + nomeEnte + "')]");
        element(ente).click();
    }

    public void ricercaNotificaConIunSalvato(String iun){
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.inserimentoCodiceIUN(iun);
        piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
    }
}