package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.model.Disservice;
import it.pn.frontend.e2e.utility.DownloadFile;
import it.pn.frontend.e2e.utility.WebTool;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

public class DisserviziAppPAPage<URl> extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("Disservizi PA Page");
    private DownloadFile downloadFile;
    public DisserviziAppPAPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "notifications-table")
    WebElement disserviziTable;

    @FindBy(xpath = "//tr[@id='tableDowntimeLog.row']//td//div[@data-testid='downtime-status']")
    List<WebElement> statusList;

    public void waitLoadStatoDellaPiattaformaPage() {
        try {
            By disserviziPageTitle = By.id("Stato della piattaforma-page");
            By disserviziPageSubTitle = By.id("subtitle-page");
            By disserviziBoxAlert = By.id("appStatusBar");
            By disserviziLastUpdate = By.id("appStatusLastCheck");
            By disserviziTitleOfTable = By.xpath("//h6[contains(text(),'Storico dei disservizi')]");

            getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo della pagina")
                    .until(ExpectedConditions.visibilityOfElementLocated(disserviziPageTitle));
            getWebDriverWait(3).until(ExpectedConditions.textToBe(disserviziPageTitle, "Stato della piattaforma"));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo della pagina")
                    .until(ExpectedConditions.visibilityOfElementLocated(disserviziPageSubTitle));
            getWebDriverWait(3).until(ExpectedConditions.textToBe(disserviziPageSubTitle, "Verifica il funzionamento di SEND, visualizza lo storico dei disservizi e scarica le relative attestazioni opponibili a terzi."));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la sezione disservizi")
                    .until(ExpectedConditions.visibilityOfElementLocated(disserviziBoxAlert));

            if (this.element(disserviziBoxAlert).getText().contains("C'è un disservizio in corso")) {
                getWebDriverWait(3).until(ExpectedConditions.textToBe(disserviziBoxAlert, "C'è un disservizio in corso. Per maggiori dettagli, consulta la tabella qui sotto."));
            } else {
                getWebDriverWait(3).until(ExpectedConditions.textToBe(disserviziBoxAlert, "Tutti i servizi di SEND sono operativi."));
            }

            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'ultimo aggiornamento della pagina")
                    .until(ExpectedConditions.visibilityOfElementLocated(disserviziLastUpdate));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la tabella dei disservizi")
                    .until(ExpectedConditions.visibilityOf(disserviziTable));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo della tabella dei disservizi")
                    .until(ExpectedConditions.visibilityOfElementLocated(disserviziTitleOfTable));

            logger.info("Si visualizza correttamente la sezione disservizi");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente la sezione disservizi con errore:" + e.getMessage());
            Assert.fail("Non si visualizza correttamente la sezione disservizi con errore" + e.getMessage());
        }
    }

    public Disservice getDateDisservice(){

        List<WebElement> disserviziTableRows = disserviziTable.findElements(By.id("tableDowntimeLog.row"));
        if (!disserviziTableRows.isEmpty()) {
            WebElement primaRiga = disserviziTableRows.get(0);
            String dataInizioPrimaRiga = primaRiga.findElements(By.xpath("//td[@data-testid='tableDowntimeLog.row.cell']//div//div//p[contains(text(), 'ore')]")).get(0).getText();
            String dataFinePrimaRiga = primaRiga.findElements(By.xpath("//td[@data-testid='tableDowntimeLog.row.cell']//div//div//p[contains(text(), 'ore')]")).get(1).getText();
            return Disservice.builder()
                    .dataDa(dataInizioPrimaRiga)
                    .dataA(dataFinePrimaRiga)
                    .build();
        }else {
            logger.error("non é stato possibile recuperare i dati dalla tabella dei disservizi");
            Assert.fail("non é stato possibile recuperare i dati dalla tabella dei disservizi");
            return null;
        }
    }

    public void waitLoadDisserviziTable() {
        try {
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la tabella dei disservizi")
                    .until(ExpectedConditions.visibilityOf(disserviziTable));
            // check if the table header is present
            WebElement disserviziTableHeader = disserviziTable.findElement(By.xpath("//thead[@role='rowgroup']"));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'header della tabella dei disservizi")
                    .until(ExpectedConditions.visibilityOf(disserviziTableHeader));
            // check if the header titles are present
            List<WebElement> tableHeaderTitles = disserviziTableHeader.findElements(By.xpath("//th[@data-testid='tableDowntimeLog.header.cell']"));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il title dell'header della tabella dei disservizi")
                    .until(ExpectedConditions.visibilityOfAllElements(tableHeaderTitles));
            // specific check for the header titles of the table
            if (tableHeaderTitles.size() == 5) {
                getWebDriverWait(3).until(ExpectedConditions.textToBePresentInElement(tableHeaderTitles.get(0), "Data di inizio"));
                getWebDriverWait(3).until(ExpectedConditions.textToBePresentInElement(tableHeaderTitles.get(1), "Data di fine"));
                getWebDriverWait(3).until(ExpectedConditions.textToBePresentInElement(tableHeaderTitles.get(2), "Servizio coinvolto"));
                getWebDriverWait(3).until(ExpectedConditions.textToBePresentInElement(tableHeaderTitles.get(3), "Attestazioni opponibili a terzi"));
                getWebDriverWait(3).until(ExpectedConditions.textToBePresentInElement(tableHeaderTitles.get(4), "Stato"));
            } else {
                logger.error("Non si visualizza correttamente l'header della tabella dei disservizi");
                Assert.fail("Non si visualizza correttamente l'header della tabella dei disservizi");
            }
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'header della tabella dei disservizi")
                    .until(ExpectedConditions.visibilityOf(disserviziTableHeader));
            List<WebElement> disserviziTableRows = disserviziTable.findElements(By.id("tableDowntimeLog.row"));
            // check if the rows are not empty
            if (!disserviziTableRows.isEmpty()) {
                for (WebElement disserviziRow : disserviziTableRows) {
                    List<WebElement> disserviziColumns = disserviziRow.findElements(By.xpath("//td[@data-testid='tableDowntimeLog.row.cell']"));
                    if (!disserviziColumns.isEmpty()) {
                        // check if the columns are not empty
                        for (WebElement disserviziColumn : disserviziColumns) {
                            getWebDriverWait(3).withMessage("Non si visualizza correttamente la tabella dei disservizi")
                                    .until(ExpectedConditions.attributeToBeNotEmpty(disserviziColumn, "innerText"));
                        }
                    }
                }
            } else {
                logger.error("Non si visualizzano correttamente le righe della tabella dei disservizi");
                Assert.fail("Non si visualizzano correttamente le righe della tabella dei disservizi");
            }
            logger.info("Si visualizza correttamente la tabella dei disservizi");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente la tabella dei disservizi con errore:" + e.getMessage());
            Assert.fail("Non si visualizza correttamente la tabella dei disservizi con errore" + e.getMessage());
        }
    }

    public void checkDisserviziInCorso() {
        aggionamentoPagina();
        if (!statusList.isEmpty()) {
            for (WebElement status : statusList) {
                if (status.getText().contains("In corso")) {
                    logger.info("Si visualizza un record in elenco relativo ad un disservizio ancora in corso");
                    continue;
                }
                if (status.getText().contains("-")) {
                    logger.info("Si visualizza data di fine come: -");
                    continue;
                }
                if (status.getText().contains("L'attestazione sarà disponibile al termine del disservizio")) {
                    logger.info("Si visualizza la frase corretta in 'Attestazioni opponibili a terzi'");
                    return;
                }
            }
        } else {
            logger.error("Non si visualizza un record in elenco relativo ad un disservizio ancora in corso");
            Assert.fail("Non si visualizza un record in elenco relativo ad un disservizio ancora in corso");
        }
    }

    public void checkDisservizioRisolto() {
        aggionamentoPagina();
        List<WebElement> disserviziTableRows = disserviziTable.findElements(By.id("tableDowntimeLog.row"));
        if (!disserviziTableRows.isEmpty()){
            WebElement primaRiga = disserviziTableRows.get(0);
            WebElement dataFinePrimaRiga = primaRiga.findElements(By.xpath("//td[@data-testid='tableDowntimeLog.row.cell']//div//div//p[contains(text(), 'ore')]")).get(1);
            WebElement statoPrimaRiga = primaRiga.findElement(By.xpath("//td[@data-testid='tableDowntimeLog.row.cell']//div//div//span[contains(text(), 'Risolto')]"));
            if (dataFinePrimaRiga.isDisplayed() && statoPrimaRiga.isDisplayed()){
                logger.info("Disservizio risolto trovato");
            }else {
                logger.error("Non si visualizza un record in elenco relativo ad un disservizio risolto");
                Assert.fail("Non si visualizza un record in elenco relativo ad un disservizio risolto");
            }
        }

    }

    public void checkDisserviziDisponibili() {
        aggionamentoPagina();
        if (!statusList.isEmpty()) {
            for (WebElement status : statusList) {
                if (status.getText().contains("Risolto")) {
                    logger.info("Si visualizza un record in elenco relativo ad un disservizio risolto");
                    continue;
                } else {
                    logger.error("Non si visualizza un record in elenco relativo ad un disservizio risolto");
                    Assert.fail("Non si visualizza un record in elenco relativo ad un disservizio risolto");
                }
                if (status.getText().contains("/") || status.getText().contains("Oggi") && status.getText().contains(":")) {
                    logger.info("Si visualizza data di fine servizio");
                    continue;
                }
                if (status.getText().contains("Scarica l'attestazione")) {
                    logger.info("Si visualizza la frase corretta in 'Scarica l'attestazione'");
                }
            }
        } else {
            logger.error("Non si visualizza un record in elenco relativo ad un disservizio disponibile");
            Assert.fail("Non si visualizza un record in elenco relativo ad un disservizio disponibile");
        }
    }


    public void clickVisualizzaAttestazione() {
        List<WebElement> disserviziTableRows = disserviziTable.findElements(By.id("tableDowntimeLog.row"));
        if (!disserviziTableRows.isEmpty()){
            WebElement primaRiga = disserviziTableRows.get(0);
            WebElement linkDownloadAttestazione =primaRiga.findElements(By.xpath("//button[@data-testid='download-legal-fact']")).get(0);
            linkDownloadAttestazione.click();
        }

        getWebDriverWait(30).until(d->{
            try {
        return d.getCurrentUrl().contains("pn-safestorage");
            }catch (TimeoutException e){
                return false;
            }
        });
    }


    public void checkVisualizzazioneFileDisservizioRisolto() {
        try {
            getWebDriverWait(5).until(ExpectedConditions.urlContains("pn-safestorage"));
        }catch (TimeoutException e){
            logger.error("Non si visualizza il file del disservizio risolto");
            Assert.fail("Non si visualizza il file del disservizio risolto");
        }
    }

    public void downloadAttestazioneDisservizio(String nomeFile){
        boolean headless = System.getProperty("headless").equalsIgnoreCase("true");

        downloadFile = new DownloadFile(this.driver);

        WebTool.waitTime(3);

        final String url = driver.getCurrentUrl();
        System.out.println("getCurrentUrl"+url);

        if (!headless && url.isEmpty()) {
            logger.error("Non è stato recuperato url per il download per il link: " + nomeFile);
            Assert.fail("Non è stato recuperato url per il download per il link: " + nomeFile);
        }
        nomeFile = nomeFile.replace(" ", "_").replace(":", "");
        File file = new File("src/test/resources/dataPopulation/downloadFileDisservizio/mittente/" + nomeFile + ".pdf");
        logger.info("Il file verrà scaricato in: " + file.getAbsolutePath());
        downloadFile.download(url, file, headless);
        if (!headless) {
            goBack();
        }
    }

    public void confrontoFileConDisservizio(String nomefile) {
        try{
            driver.findElement(By.xpath("//html")).sendKeys(Keys.CONTROL+ "a");
            driver.findElement(By.xpath("//html")).sendKeys(Keys.CONTROL+ "C");
            //System.out.println("url ->"+text);

            PDDocument doc=null;
            BufferedInputStream file=null;

            /*URL urlOfPdf = new URL(getURL);
            System.out.println(urlOfPdf);

            BufferedInputStream fileToParse = new BufferedInputStream(urlOfPdf.openStream());
            PDDocument document = PDDocument.load(fileToParse);
            System.out.println("document ->"+document);

            PDFTextStripper output = new PDFTextStripper();

            System.out.println("output->"+output);

            output.setEndPage(1);
            String text = output.getText(document);
            System.out.println(text);*/

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
              /*Disservice disservice = getDateDisservice();
        String basePathFile = "src/test/resources/dataPopulation/downloadFileDisservizio/mittente/" + nomefile + ".pdf";
        File file = new File(basePathFile);
        logger.info("percorso file: " + file.getAbsolutePath());
        try {
            PDDocument pdfFile = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String testoFile = pdfStripper.getText(pdfFile).replaceAll("\r\n|\r|\n", "");
            logger.info("check corrispondenza testo con pdf");
            if (testoFile.contains(disservice.getDataA()) && testoFile.contains(disservice.getDataDa())) {
                pdfFile.close();
                return true;
            }
            pdfFile.close();
        } catch (IOException e) {
            logger.error("File non trovato con errore: " + e.getMessage());
            Assert.fail("File non trovato con errore: " + e.getMessage());
        }
        return false;*/
    }
}