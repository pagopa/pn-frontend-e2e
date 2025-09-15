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

    private static final Logger logger = LoggerFactory.getLogger(DettaglioNotificaMittenteSection.class);


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
        getWebDriverWait(20)
                .withMessage("Impossibile trovare Dettaglio Notifica ")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("title-of-page")));
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


    //    private boolean controlloCodice() {
//        try {
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//td[contains(text(),'Codice IUN')]"))));
//            logger.info("codice iun presente");
//            return true;
//        } catch (TimeoutException e) {
//            logger.info("codice iun non presente");
//            return false;
//        }
//    }
    private boolean controlloCodice() {
        try {
            getWebDriverWait(30)
                    .withMessage("Codice IUN non presente nella tabella")
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//td[contains(text(),'Codice IUN')]")
                    ));
            logger.info("Codice IUN presente");
            return true;
        } catch (TimeoutException e) {
            logger.info("Codice IUN non presente");
            return false;
        }
    }


    public String getInfoNotifica(int i) {
        return infoNotifiche.get(i).getText();
    }

    public boolean controlloTestoFile(String nameFile, String testoDaControllare) {
        String basePathFile = "src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nameFile + ".pdf";
        File file = new File(basePathFile);
        logger.info("percorso file: {}", file.getAbsolutePath());
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
            Assertions.fail("File non trovato con errore: " + e.getMessage());
        }
        return false;
    }


    public boolean controlloSHAFile(String nameFile) {
        String basePathFile = "src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nameFile + ".pdf";
        File file = new File(basePathFile);
        logger.info("percorso file: {}", file.getAbsolutePath());
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
            Assertions.fail("File non trovato con errore: " + e.getMessage());
        }
        return false;
    }

    public void clickVediPiuDettagli() {
        logger.info("Cerco il primo bottone 'Vedi dettaglio'");
        List<WebElement> vediDettagliButtons = getWebDriverWait(20)
                .withMessage("Imposibile trovare il primo Vedi piu dettagli ")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("more-less-timeline-step")));
        if (vediDettagliButtons.isEmpty()) {
            Assertions.fail("Non è stato trovato alcun bottone 'Vedi dettaglio'");
        }
        vediDettagliButtons.get(0).click();
        logger.info("Primo bottone 'Vedi dettaglio' cliccato");

        try {
            vediDettagliButtons = getWebDriverWait(20)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("more-less-timeline-step")));
            if (vediDettagliButtons.size() > 1) {
                vediDettagliButtons.get(1).click();
                logger.info("Secondo bottone 'Vedi dettaglio' cliccato");
            } else {
                logger.info("Solo un bottone 'Vedi dettaglio' disponibile");
            }
        } catch (TimeoutException e) {
            logger.info("Nessun ulteriore bottone 'Vedi dettaglio' trovato");
        }
    }

    //    public void siVisualizzaPercorsoNotifica() {
//        try {
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//div[contains(@data-testid,'itemStatus')]"))));
//            List<WebElement> newPercorsoNotificaBy = driver.findElements(By.xpath("//div[contains(@data-testid,'itemStatus')]"));
//            if (newPercorsoNotificaBy.size() > numeriStatiNotifica) {
//                logger.info("TA_QA: L'elenco completo degli stati presente");
//            }
//        } catch (NoSuchElementException e) {
//            Assertions.fail("TA_QA: L'elenco completo degli stati NON presentecon errore: " + e.getMessage());
//        }
//    }
    public void siVisualizzaPercorsoNotifica() {
        try {
            List<WebElement> newPercorsoNotificaBy = getWebDriverWait(30)
                    .withMessage("L'elenco completo degli stati non è visibile")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath("//div[contains(@data-testid,'itemStatus')]")
                    ));

            if (newPercorsoNotificaBy.size() > numeriStatiNotifica) {
                logger.info("TA_QA: L'elenco completo degli stati presente");
            }
        } catch (TimeoutException e) {
            Assertions.fail("TA_QA: L'elenco completo degli stati NON presente con errore: " + e.getMessage());
        }
    }


    //    public void clickIndietroButton() {
//        logger.info("click su pulsante indietro");
//        indietroButton = driver.findElement(By.xpath("//button[contains(@data-testid,'breadcrumb-indietro-button')]"));
//        indietroButton.click();
//    }
    public void clickIndietroButton() {
        logger.info("Click su pulsante indietro");

        indietroButton = getWebDriverWait(10)
                .withMessage("Il pulsante indietro non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@data-testid,'breadcrumb-indietro-button')]")));

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
            Assertions.fail("File non trovato con errore: " + e.getMessage());
        }
        return false;
    }

    //    public void clickLinkDocumentiAllegati() {
//        linkAllegati = driver.findElements(By.xpath("//button[contains(@data-testid,'documentButton')]"));
//        if (this.linkAllegati.get(0).isDisplayed()) {
//            this.linkAllegati.get(0).click();
//        } else {
//            this.js().executeScript("arguments[0].scrollIntoView(true);", this.linkAllegati.get(0));
//            logger.info("click sul link allegati");
//            this.linkAllegati.get(0).click();
//        }
//    }
    public void clickLinkDocumentiAllegati() {
        linkAllegati = getWebDriverWait(10)
                .withMessage("Il link 'Documenti Allegati' non è visibile")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//button[contains(@data-testid,'documentButton')]")
                ));

        WebElement primoLink = linkAllegati.get(0);
        if (!primoLink.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true);", primoLink);
        }

        logger.info("click sul link allegati");
        primoLink.click();
    }


    //    public void clickLinkAvvenutaRicezione(int i) {
//        logger.info("click sul link avvenuta ricezione");
//        linkAllegati = driver.findElements(By.xpath("//button[contains(@data-testid,'documentButton')]"));
//        if (this.linkAllegati.get(i).isDisplayed()) {
//            this.linkAllegati.get(i).click();
//        } else {
//            this.js().executeScript("arguments[0].scrollIntoView(true);", this.linkAllegati.get(i));
//            this.linkAllegati.get(i).click();
//        }
//    }
    public void clickLinkAvvenutaRicezione(int i) {
        logger.info("click sul link avvenuta ricezione");

        linkAllegati = getWebDriverWait(10)
                .withMessage("I link 'Documenti Allegati' non sono visibili")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//button[contains(@data-testid,'documentButton')]")
                ));

        if (i >= linkAllegati.size()) {
            throw new IndexOutOfBoundsException("Non esiste un link avvenuta ricezione all'indice: " + i);
        }

        WebElement link = linkAllegati.get(i);

        if (!link.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true);", link);
        }

        link.click();
    }


    //    public int getLinkAvvenutaRicezione() {
//        linkAllegati = driver.findElements(By.xpath("//button[contains(@data-testid,'documentButton')]"));
//        return linkAllegati.size();
//    }
    public int getLinkAvvenutaRicezione() {
        linkAllegati = getWebDriverWait(10)
                .withMessage("I link 'Documenti Allegati' non sono visibili")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//button[contains(@data-testid,'documentButton')]")
                ));

        return linkAllegati.size();
    }


    //    public void clickLinkAttestazioneOpponibile(String nomeFile) {
//        try {
//            List<WebElement> fileLinkBy = driver.findElements(By.xpath("//button[contains(text(),'" + nomeFile + "')]"));
//            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(fileLinkBy));
//            if (fileLinkBy.get(0).isDisplayed()) {
//                fileLinkBy.get(0).click();
//            } else {
//                this.js().executeScript("arguments[0].scrollIntoView(true);", fileLinkBy);
//                fileLinkBy.get(0).click();
//            }
//        } catch (TimeoutException e) {
//            Assertions.fail("Non riuscito ad trovare il link con errore: " + e.getMessage());
//        }
//    }
    public void clickLinkAttestazioneOpponibile(String nomeFile) {
        try {
            List<WebElement> fileLinkBy = getWebDriverWait(30)
                    .withMessage("Non è visibile il link del file: " + nomeFile)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath("//button[contains(text(),'" + nomeFile + "')]")
                    ));

            WebElement primoLink = fileLinkBy.get(0);

            if (!primoLink.isDisplayed()) {
                js().executeScript("arguments[0].scrollIntoView(true);", primoLink);
            }

            primoLink.click();
        } catch (TimeoutException e) {
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

    //    public String getTextLinkAvvenutaRicezione(int i) {
//        linkAllegati = driver.findElements(By.xpath("//button[contains(@data-testid,'documentButton')]"));
//        return linkAllegati.get(i).getText();
//    }
    public String getTextLinkAvvenutaRicezione(int i) {
        linkAllegati = getWebDriverWait(10)
                .withMessage("I link 'Documenti Allegati' non sono visibili")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//button[contains(@data-testid,'documentButton')]")
                ));

        if (i >= linkAllegati.size()) {
            throw new IndexOutOfBoundsException("Non esiste un link avvenuta ricezione all'indice: " + i);
        }

        return linkAllegati.get(i).getText();
    }


    //    public String getTextDocumentiAllegati() {
//        linkAllegati = driver.findElements(By.xpath("//button[contains(@data-testid,'documentButton')]"));
//        return linkAllegati.get(0).getText();
//    }
    public String getTextDocumentiAllegati() {
        linkAllegati = getWebDriverWait(10)
                .withMessage("I link 'Documenti Allegati' non sono visibili")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//button[contains(@data-testid,'documentButton')]")
                ));

        if (linkAllegati.isEmpty()) {
            throw new NoSuchElementException("Non ci sono link 'Documenti Allegati' disponibili");
        }

        return linkAllegati.get(0).getText();
    }


    //    public void checkNumeroFallimentiInvioViaPEC(int numeroFallimenti) {
//        try {
//            List<WebElement> invioPECFallitoBy = driver.findElements(By.xpath("//span[text()='Invio via PEC fallito']"));
//            logger.info("L'invio della notifica è fallito questo numero di volte: {}",invioPECFallitoBy.size());
//            if (invioPECFallitoBy.size() != numeroFallimenti) {
//                Assertions.fail("L'invio della notifica non è fallito " + numeroFallimenti + " volta/e");
//            }
//        } catch (TimeoutException e) {
//            Assertions.fail("NON è fallito l'invio della notifica: " + e.getMessage());
//        }
//    }
    public void checkNumeroFallimentiInvioViaPEC(int numeroFallimenti) {
        try {
            List<WebElement> invioPECFallitoBy = getWebDriverWait(10)
                    .withMessage("Gli elementi 'Invio via PEC fallito' non sono visibili")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath("//span[text()='Invio via PEC fallito']")
                    ));

            logger.info("L'invio della notifica è fallito questo numero di volte: {}", invioPECFallitoBy.size());

            if (invioPECFallitoBy.size() != numeroFallimenti) {
                Assertions.fail("L'invio della notifica non è fallito " + numeroFallimenti + " volta/e");
            }
        } catch (TimeoutException e) {
            Assertions.fail("NON è fallito l'invio della notifica: " + e.getMessage());
        }
    }


    public void checkStatoTimeline(String xpathStatoTimeline) {
        try {
            getWebDriverWait(15)
                    .withMessage("Impossibile controllare lo stato della timeline: " + xpathStatoTimeline)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathStatoTimeline)));
            logger.info("stato timeline checkato con successo avvenuta");
        } catch (TimeoutException e) {
            Assertions.fail("checkato stato timeline non avvenuta con errore: " + e.getMessage());
        }
    }

//    public void siCliccaSuAllegatoInTimeline(String xpath) {
//        try {
//            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
//            WebElement allegatoTimeline = driver.findElement(By.xpath(xpath));
//            allegatoTimeline.click();
//            checkURL("pn-safestorage");
//            driver.navigate().back();
//            logger.info("allegato timeline trovato con successo");
//        } catch (TimeoutException e) {
//            Assertions.fail("allegato timeline trovato non con successo: " + e.getMessage());
//        }
//
//    }

    //    public void siVerificaLaCliccabilitaSuAllegatoInTimeline(String xpath) {
//        vaiInFondoAllaPagina();
//        getWebDriverWait(10).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))), ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath)))));
//        logger.info("allegato timeline trovato con successo e cliccabile");
//    }
    public void siVerificaLaCliccabilitaSuAllegatoInTimeline(String xpath) {
        vaiInFondoAllaPagina();

        getWebDriverWait(10)
                .withMessage("L'allegato nella timeline non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        logger.info("Allegato timeline trovato con successo e cliccabile");
    }


//    public void checkInvioADomicilioDiPiattaforma(String domicilioDiPiattaforma) {
//        try {
//            getWebDriverWait(10).withMessage("Non si visualizza l'invio della notifica al domicilio di piattaforma nella timeline").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC riuscito') and (//div[contains(p/text(), '" + domicilioDiPiattaforma + "')])]"))));
//        } catch (TimeoutException e) {
//            Assertions.fail("L'invio della notifica al domicilio di piattaforma indicato non viene effettuato con errore: " + e.getMessage());
//        }
//
//    }

//    public void checkDoppioFallimentoInvioViaPEC(int numeroFallimenti) {
//        try {
//            List<WebElement> invioPECFallitoBy = driver.findElements(By.xpath("//span[text()='Invio via PEC fallito']"));
//            logger.info("L'invio della notifica è fallito questo numero di volte: {}", invioPECFallitoBy.size());
//            if (invioPECFallitoBy.size() != numeroFallimenti) {
//                Assertions.fail("L'invio della notifica non è fallito " + numeroFallimenti + " volta/e");
//            }
//        } catch (TimeoutException e) {
//            Assertions.fail("NON è fallito l'invio della notifica: " + e.getMessage());
//        }
//    }

    //    public void checkInvioRaccomandataSemplice() {
//        try {
//            getWebDriverWait(10).withMessage("Non si visualizza l'invio della raccomandata nella timeline della notifica").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Invio via raccomandata semplice']"))));
//            logger.info("L'invio della notifica avviene via raccomandata semplice");
//        } catch (TimeoutException e) {
//            Assertions.fail("L'invio della notifica per raccomandata non viene visualizzato: " + e.getMessage());
//        }
//    }
    public void checkInvioRaccomandataSemplice() {
        getWebDriverWait(10)
                .withMessage("Non si visualizza l'invio della raccomandata nella timeline della notifica")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='Invio via raccomandata semplice']")
                ));

        logger.info("L'invio della notifica avviene via raccomandata semplice");
    }


    //    public void checkTentatoInvioADomicilioSpeciale(String domicilioSpeciale) {
//        try {
//            getWebDriverWait(10).withMessage("Non si visualizza l'invio della notifica al domicilio speciale nella timeline").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC fallito') and (//div[contains(p/text(), '" + domicilioSpeciale + "')])]"))));
//        } catch (TimeoutException e) {
//            Assertions.fail("L'invio della notifica al domicilio speciale indicato non viene effettuato con errore: " + e.getMessage());
//        }
//    }
    public void checkTentatoInvioADomicilioSpeciale(String domicilioSpeciale) {
        String xpath = "//div[span[contains(text(), 'Invio via PEC fallito')] and p[contains(text(), '" + domicilioSpeciale + "')]]";

        getWebDriverWait(10)
                .withMessage("L'invio della notifica al domicilio speciale '" + domicilioSpeciale + "' non è visibile nella timeline")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        logger.info("L'invio della notifica al domicilio speciale '{}' è stato tentato", domicilioSpeciale);
    }


    //    public void checkStepInvioNotificaViaPEC(String emailPEC) {
//        getWebDriverWait(10).withMessage("Non si visualizza il tentativo di invio della notifica al domicilio generale").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC') and (//div[contains(p/text(), '" + emailPEC + "')])]"))));
//        getWebDriverWait(10).withMessage("Non si visualizza la presa in carico dell'invio della notifica al domicilio generale").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC preso in carico') and (//div[contains(p/text(), '" + emailPEC + "')])]"))));
//        getWebDriverWait(10).withMessage("Non si visualizza la riuscita dell'invio della notifica al domicilio generale").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(span/text(), 'Invio via PEC riuscito') and (//div[contains(p/text(), '" + emailPEC + "')])]"))));
//    }
    public void checkStepInvioNotificaViaPEC(String emailPEC) {
        String xpathTentativo = "//div[span[contains(text(), 'Invio via PEC')] and p[contains(text(), '" + emailPEC + "')]]";
        String xpathPresaInCarico = "//div[span[contains(text(), 'Invio via PEC preso in carico')] and p[contains(text(), '" + emailPEC + "')]]";
        String xpathRiuscito = "//div[span[contains(text(), 'Invio via PEC riuscito')] and p[contains(text(), '" + emailPEC + "')]]";

        getWebDriverWait(10)
                .withMessage("Non si visualizza il tentativo di invio della notifica al domicilio generale")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathTentativo)));

        getWebDriverWait(10)
                .withMessage("Non si visualizza la presa in carico dell'invio della notifica al domicilio generale")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathPresaInCarico)));

        getWebDriverWait(10)
                .withMessage("Non si visualizza la riuscita dell'invio della notifica al domicilio generale")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRiuscito)));

        logger.info("Step di invio PEC completati per l'email: {}", emailPEC);
    }


    //    public void checkAvvisoPagoPa() {
//        try {
//            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"))));
//            containerPaymentBox = driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"));
//            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
//            getWebDriverWait(10).withMessage("Non si visualizza l'avviso PagoPA per il pagamento della notifica").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='payment-item']"))));
//        } catch (TimeoutException e) {
//            Assertions.fail("Box per il pagamento della notifica non visualizzato correttamente con errore: " + e.getMessage());
//        }
//    }
    public void checkAvvisoPagoPa() {
        // Attende il contenitore principale dei pagamenti
        WebElement containerPaymentBox = getWebDriverWait(10)
                .withMessage("Non si visualizza il contenitore dei pagamenti")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='paymentInfoBox']")));

        // Scroll fino al container
        js().executeScript("arguments[0].scrollIntoView(true);", containerPaymentBox);

        // Controlla la presenza dell'avviso PagoPA
        getWebDriverWait(10)
                .withMessage("Non si visualizza l'avviso PagoPA per il pagamento della notifica")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='payment-item']")));

        logger.info("Box per il pagamento della notifica visualizzato correttamente");
    }


    //    public boolean checkAvvisoPagoPaVisibile() {
//        try {
//            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"))));
//            containerPaymentBox = driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"));
//            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
//            getWebDriverWait(10).withMessage("Non si visualizza l'avviso PagoPA per il pagamento della notifica").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Avviso pagoPA')]"))));
//            return true;
//        } catch (TimeoutException e) {
//            logger.error("Non si visualizza l'avviso PagoPA per il pagamento della notifica");
//            return false;
//        }
//    }
    public boolean checkAvvisoPagoPaVisibile() {
        try {
            // Attende il contenitore principale dei pagamenti
            WebElement containerPaymentBox = getWebDriverWait(10)
                    .withMessage("Non si visualizza il contenitore dei pagamenti")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='paymentInfoBox']")));

            // Scroll fino al container
            js().executeScript("arguments[0].scrollIntoView(true);", containerPaymentBox);

            // Controlla la presenza dell'avviso PagoPA
            getWebDriverWait(10)
                    .withMessage("Non si visualizza l'avviso PagoPA per il pagamento della notifica")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Avviso pagoPA')]")));

            return true;
        } catch (TimeoutException e) {
            logger.error("Non si visualizza l'avviso PagoPA per il pagamento della notifica");
            return false;
        }
    }


    //    public boolean checkCodiceAvvisoVisibile() {
//        try {
//            getWebDriverWait(5).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Codice Avviso')]")))).isDisplayed();
//            return true;
//        } catch (RuntimeException e) {
//            return false;
//        }
//    }
    public boolean checkCodiceAvvisoVisibile() {
        try {
            getWebDriverWait(5)
                    .withMessage("La sezione 'Codice Avviso' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Codice Avviso')]")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    //    public void clickAvvisoPagoPa() {
//        try {
//            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"))));
//            containerPaymentBox = driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"));
//            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
//            getWebDriverWait(10).withMessage("Non si visualizza l'avviso PagoPA per il pagamento della notifica").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Avviso pagoPA')]"))));
//            WebElement avvisoPagoPa = driver.findElement(By.xpath("//button[contains(text(),'Avviso pagoPA')]"));
//            avvisoPagoPa.click();
//        } catch (TimeoutException e) {
//            Assertions.fail("Box per il pagamento della notifica non visualizzato correttamente con errore: " + e.getMessage());
//        }
//    }
    public void clickAvvisoPagoPa() {
        try {
            WebElement containerPaymentBox = getWebDriverWait(10)
                    .withMessage("Non si visualizza il contenitore dei pagamenti")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='paymentInfoBox']")));
            js().executeScript("arguments[0].scrollIntoView(true);", containerPaymentBox);
            WebElement avvisoPagoPa = getWebDriverWait(10)
                    .withMessage("Non si visualizza l'avviso PagoPA per il pagamento della notifica")
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Avviso pagoPA')]")));

            avvisoPagoPa.click();
        } catch (TimeoutException e) {
            Assertions.fail("Box per il pagamento della notifica non visualizzato correttamente con errore: " + e.getMessage());
        }
    }


    //    public void checkModelloF24() {
//        getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"))));
//        containerPaymentBox = driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"));
//        js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
//        getWebDriverWait(10).withMessage("Non si visualizza il contenitore del modello F24").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@data-testid='f24']"))));
//
//    }
    public void checkModelloF24() {
        WebElement containerPaymentBox = getWebDriverWait(10)
                .withMessage("Non si visualizza il contenitore dei pagamenti")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='paymentInfoBox']")));
        js().executeScript("arguments[0].scrollIntoView(true);", containerPaymentBox);
        getWebDriverWait(10)
                .withMessage("Non si visualizza il contenitore del modello F24")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-testid='f24']")));
    }


    public void checkBoxModelloF24() {
        getWebDriverWait(10)
                .withMessage("Il box con testo Modelli F24 allegati non è visibile entro il timeout")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Modelli F24 allegati')]")));
    }

    //    public void siCliccaSulBottoneChiudi() {
//        getWebDriverWait(10).withMessage("Il bottone chiudi non cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@data-testid='close-dialog']"))));
//        WebElement chiudiBoxF24 = driver.findElement(By.xpath("//button[@data-testid='close-dialog']"));
//        chiudiBoxF24.click();
//    }
    public void siCliccaSulBottoneChiudi() {
        WebElement chiudiBoxF24 = getWebDriverWait(10)
                .withMessage("Il bottone chiudi non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='close-dialog']")));
        chiudiBoxF24.click();
    }


    //    public void checkBoxPagamentoMultiDestinatario() {
//        try {
//            getWebDriverWait(10).withMessage("Non si visualizza il contenitore dei pagamenti").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"))));
//            containerPaymentBox = driver.findElement(By.xpath("//div[@data-testid='paymentInfoBox']"));
//            js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);
//            getWebDriverWait(10).withMessage("Non si visualizza l'input per la selezione del destinatario").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("recipients-select"))));
//            selectMultiDestinatario = driver.findElement(By.id("recipients-select"));
//        } catch (TimeoutException e) {
//            Assertions.fail("Dettaglio notifica multi destinatario non ancora pagata visualizzata non correttamente con errore: " + e.getMessage());
//        }
//    }
    public void checkBoxPagamentoMultiDestinatario() {
        WebElement containerPaymentBox = getWebDriverWait(10)
                .withMessage("Non si visualizza il contenitore dei pagamenti")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='paymentInfoBox']")));
        js().executeScript("arguments[0].scrollIntoView(true)", containerPaymentBox);

        selectMultiDestinatario = getWebDriverWait(10)
                .withMessage("Non si visualizza l'input per la selezione del destinatario")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients-select")));
    }


    //    public void clickMultiDestinatario() {
//        try {
//            getWebDriverWait(10).withMessage("L'input per la selezione del destinatario non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("recipients-select"))));
//            selectMultiDestinatario = driver.findElement(By.id("recipients-select"));
//            js().executeScript("arguments[0].scrollIntoView(true)", selectMultiDestinatario);
//            selectMultiDestinatario.click();
//            getWebDriverWait(10).withMessage("Non si visualizza la lista delle opzioni destinatario").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//li[@role='option']"))));
//            List<WebElement> selectOption = driver.findElements(By.xpath("//li[@role='option']"));
//            selectOption.get(0).click();
//        } catch (TimeoutException e) {
//            Assertions.fail("Non è stato possibile selezionare un destinatario con errore: " + e.getMessage());
//        }
//    }
    public void clickMultiDestinatario() {
        selectMultiDestinatario = getWebDriverWait(10)
                .withMessage("L'input per la selezione del destinatario non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("recipients-select")));

        js().executeScript("arguments[0].scrollIntoView(true)", selectMultiDestinatario);
        selectMultiDestinatario.click();

        List<WebElement> selectOptions = getWebDriverWait(10)
                .withMessage("Non si visualizza la lista delle opzioni destinatario")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@role='option']")));

        selectOptions.get(0).click();
    }


    //    public void checkAttestazionePresaInCarico() {
//        try {
//            getWebDriverWait(10).withMessage("Non si visualizza attestazione opponibile a terzi notifica presa in carico").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'presa in carico')]"))));
//        } catch (TimeoutException e) {
//            Assertions.fail("Attestazione opponibile a terzi notifica presa in carico non visualizzato correttamente con errore: " + e.getMessage());
//        }
//    }
    public void checkAttestazionePresaInCarico() {
        getWebDriverWait(10)
                .withMessage("Attestazione opponibile a terzi notifica presa in carico non visualizzato correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'presa in carico')]")));
    }


    //    public void checkAlertRADD() {
//        getWebDriverWait(10).withMessage("Non si visualizza l'alert radd").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='raddAlert']"))));
//    }
    public void checkAlertRADD() {
        getWebDriverWait(10)
                .withMessage("Non si visualizza l'alert RADD")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='raddAlert']")));
    }


//    public void checkInvioMessaggioDiCortesia() {
//        boolean testSuccess = false;
//        for (int i = 0; i < 8; i++) {
//            try {
//                WebElement vediPiuDettagli = driver.findElement(By.id("more-less-timeline-step"));
//                if (vediPiuDettagli != null) {
//                    vediPiuDettagli.click();
//                }
//                WebElement messaggioCortesia = driver.findElement(By.xpath("//span[contains(text(), 'Invio del messaggio di cortesia')]"));
//                if (messaggioCortesia != null) {
//                    logger.info("L'invio del messaggio al contatto di cortesia è avvenuto");
//                    testSuccess = true;
//                    break;
//                }
//            } catch (NoSuchElementException e) {
//                logger.info("Dopo " + (i + 1) + " tentativi l'invio del messaggio al contatto di cortesia non è avvenuto");
//            }
//            webTool.waitTime(15);
//            driver.navigate().refresh();
//        }
//        if (!testSuccess) {
//            logger.error("L'invio del messaggio al contatto di cortesia non è avvenuto");
//            Assertions.fail("L'invio del messaggio al contatto di cortesia non è avvenuto");
//        }
//    }

    public void checkInvioMessaggioDiCortesia() {
        boolean testSuccess = false;
        By vediPiuDettagliBy = By.id("more-less-timeline-step");
        By messaggioCortesiaBy = By.xpath("//span[contains(text(), 'Invio del messaggio di cortesia')]");

        for (int i = 0; i < 8; i++) {
            // Aspetta che il bottone "vedi più dettagli" sia visibile e cliccabile
            List<WebElement> vediPiuDettagliList = getWebDriverWait(5)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(vediPiuDettagliBy));

            if (!vediPiuDettagliList.isEmpty()) {
                getWebDriverWait(5).until(ExpectedConditions.elementToBeClickable(vediPiuDettagliList.get(0))).click();
            }

            // Aspetta che il messaggio di cortesia sia visibile
            List<WebElement> messaggioCortesiaList = getWebDriverWait(5)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(messaggioCortesiaBy));

            if (!messaggioCortesiaList.isEmpty()) {
                logger.info("L'invio del messaggio al contatto di cortesia è avvenuto");
                testSuccess = true;
                break;
            }

            webTool.waitTime(15);
            driver.navigate().refresh();
        }

        if (!testSuccess) {
            logger.error("L'invio del messaggio al contatto di cortesia non è avvenuto dopo tutti i tentativi");
            Assertions.fail("L'invio del messaggio al contatto di cortesia non è avvenuto");
        }
    }


    //    public String salvaIUN() {
//        codiceIUN = driver.findElement(By.xpath("//*[@id='row-value-5']/div"));
//        return codiceIUN.getText();
//    }
    public String salvaIUN() {
        By codiceIUNBy = By.xpath("//*[@id='row-value-5']/div");
        WebElement codiceIUNElement = getWebDriverWait(10)
                .withMessage("Codice IUN non visibile entro il timeout")
                .until(ExpectedConditions.visibilityOfElementLocated(codiceIUNBy));
        return codiceIUNElement.getText();
    }


    public String getApiKey() {
        return apiKeyField.getAttribute("value");
    }

    //    public void insertIunSalvatoAndRicercaOnPage(String iun) {
//        logger.info("inserisco numero ticket");
//        numeroTicketInput = driver.findElement(By.id("Numero Ticket"));
//        numeroTicketInput.sendKeys("testTAFE01");
//        logger.info("inserisco codice IUN");
//        iunInput = driver.findElement(By.id("IUN"));
//        iunInput.sendKeys(iun);
//        logger.info("clicco sul bottone di ricerca");
//
//        getWebDriverWait(30).withMessage("bottone per la ricerca non trovato").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("ricerca"))));
//        buttonRicerca = driver.findElement(By.id("ricerca"));
//        buttonRicerca.click();
//        webTool.waitTime(3);
//    }
    public void insertIunSalvatoAndRicercaOnPage(String iun) {
        logger.info("inserisco numero ticket");
        numeroTicketInput = getWebDriverWait(10)
                .withMessage("Campo 'Numero Ticket' non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("Numero Ticket")));
        numeroTicketInput.sendKeys("testTAFE01");

        logger.info("inserisco codice IUN");
        iunInput = getWebDriverWait(10)
                .withMessage("Campo 'IUN' non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("IUN")));
        iunInput.sendKeys(iun);

        logger.info("clicco sul bottone di ricerca");
        buttonRicerca = getWebDriverWait(30)
                .withMessage("Bottone 'ricerca' non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("ricerca")));
        buttonRicerca.click();

        webTool.waitTime(3); // se serve ancora il delay dopo il click
    }


    //    public void sceglieEnte(String nomeEnte) {
//        logger.info("Si sceglie ente: {}", nomeEnte);
//        enteButton = driver.findElement(By.xpath("//span[contains(text(), 'Amministratore')]"));
//        enteButton.click();
//        webTool.waitTime(1);
//        WebElement ente = driver.findElement(By.xpath("//h6[contains(text(), '" + nomeEnte + "')]"));
//        ente.click();
//    }
    public void sceglieEnte(String nomeEnte) {
        logger.info("Si sceglie ente: {}", nomeEnte);
        enteButton = getWebDriverWait(10)
                .withMessage("Bottone ente non visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Amministratore')]")));
        enteButton.click();
        webTool.waitTime(1);
        WebElement ente = getWebDriverWait(10)
                .withMessage("Ente '" + nomeEnte + "' non visibile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//h6[contains(text(), '" + nomeEnte + "')]")));
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
                By.xpath("//li[contains(text(),'" + testo + "')]")
        ));
        button.click();
    }
}