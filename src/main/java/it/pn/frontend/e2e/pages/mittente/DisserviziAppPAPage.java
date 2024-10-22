package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DisserviziAppPAPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("Disservizi PA Page");

    private DataPopulation dataPopulation = new DataPopulation();

    public DisserviziAppPAPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "notifications-table")
    WebElement disserviziTable;

    @FindBy(xpath = "//tr[@id='tableDowntimeLog.row']//td//div[@data-testid='downtime-status']")
    List<WebElement> statusList;


    @FindBy(css = "[data-testid='download-legal-fact']")
    List<WebElement> attestazioniFile;

    @FindBy(xpath = "//span[contains(text(), 'Risolto')]")
    List<WebElement> stato;

    public void waitLoadStatoDellaPiattaformaPage() {
        WebTool.waitTime(5);
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

            disserviziTable = driver.findElement(By.id("notifications-table"));
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

    public void getDateDisservice() {
        List<WebElement> disserviziTableRows = disserviziTable.findElements(By.id("tableDowntimeLog.row"));

        if (!disserviziTableRows.isEmpty()) {
            WebElement primaRiga = disserviziTableRows.get(0);
            String dataInizioPrimaRiga = primaRiga.findElements(By.xpath("//td[@data-testid='tableDowntimeLog.row.cell']//div//div//p[contains(text(), 'ore')]")).get(0).getText();
            String dataFinePrimaRiga = primaRiga.findElements(By.xpath("//td[@data-testid='tableDowntimeLog.row.cell']//div//div//p[contains(text(), 'ore')]")).get(1).getText();

            dataPopulation.setDataDa(dataInizioPrimaRiga);
            dataPopulation.setDataA(dataFinePrimaRiga);
        } else {
            logger.error("non é stato possibile recuperare i dati dalla tabella dei disservizi");
            Assert.fail("non é stato possibile recuperare i dati dalla tabella dei disservizi");

        }
    }

    public void waitLoadDisserviziTable() {
        try {
            WebTool.waitTime(10);
            disserviziTable = driver.findElement(By.id("notifications-table"));
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
        aggiornamentoPagina();
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

    public void checkDisservizioRisolto(String tipoDisservizio) {
        aggiornamentoPagina();
        disserviziTable = driver.findElement(By.id("notifications-table"));
        List<WebElement> disserviziTableRowsWithTypeOfDisservice = disserviziTable.findElements(By.xpath("//tr[@id='tableDowntimeLog.row' and contains(., '" + tipoDisservizio + "')]"));
        if (!disserviziTableRowsWithTypeOfDisservice.isEmpty()) {
            WebElement primaRiga = disserviziTableRowsWithTypeOfDisservice.get(0);
            WebElement dataFinePrimaRiga = primaRiga.findElements(By.xpath("//td[@data-testid='tableDowntimeLog.row.cell']//div//div//p[contains(text(), 'ore')]")).get(1);
            WebElement statoPrimaRiga = primaRiga.findElement(By.xpath("//td[@data-testid='tableDowntimeLog.row.cell']//div//div//span[contains(text(), 'Risolto')]"));
            if (dataFinePrimaRiga.isDisplayed() && statoPrimaRiga.isDisplayed()) {
                logger.info("Disservizio risolto trovato");
            } else {
                logger.error("Non si visualizza un record in elenco relativo ad un disservizio risolto");
                Assert.fail("Non si visualizza un record in elenco relativo ad un disservizio risolto");
            }
        }
    }

    public void checkDisserviziDisponibili() {
        aggiornamentoPagina();
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


    public void downloadAttestazione() {
        List<WebElement> disserviziTableRows = disserviziTable.findElements(By.id("tableDowntimeLog.row"));
        if (!disserviziTableRows.isEmpty()) {
            logger.info("tabella caricata e non vuota");

            Calendar calendar = GregorianCalendar.getInstance();
            int index = calendar.get(Calendar.HOUR_OF_DAY);

            logger.info("SIZE ROWS TABLE..." + disserviziTableRows.size());
            logger.info("ROWS SELEZIONATA: " + index);
            WebElement riga = null;
            if (disserviziTableRows.size() >= index) {
                riga = disserviziTableRows.get(index);
            } else {
                riga = disserviziTableRows.get(disserviziTableRows.size() - 1);
            }

            WebElement linkDownloadAttestazione = riga.findElements(By.xpath("//button[@data-testid='download-legal-fact']")).get(0);
            linkDownloadAttestazione.click();
            logger.info("click effettuato con successo");
        } else {
            logger.error("Non ci sono notifiche da selezionare nel arco temporale settato");
            Assert.fail("Non ci sono notifiche da selezionare nel arco temporale settato");
        }
    }

    public void downloadAttestazione(int rows) {
        List<WebElement> disserviziTableRows = disserviziTable.findElements(By.id("tableDowntimeLog.row"));
        if (!disserviziTableRows.isEmpty()) {
            logger.info("tabella caricata e non vuota");

            Calendar calendar = GregorianCalendar.getInstance();
            int index = calendar.get(Calendar.HOUR_OF_DAY) + rows;
            logger.info("HOUR..." + index);
            logger.info("SIZE ROWS TABLE..." + disserviziTableRows.size());
            logger.info("ROWS TABLE..." + rows);
            logger.info("ROWS SELEZIONATA: " + index);
            WebElement riga = null;
            if (disserviziTableRows.size() > index) {
                logger.info("ROWS SELEZIONATA1: " + index);
                riga = disserviziTableRows.get(index);
            } else {
                logger.info("ROWS SELEZIONATA2: " + rows);
                if ((rows + 1) < disserviziTableRows.size()) {
                    logger.info("ROWS SELEZIONATA3: " + (disserviziTableRows.size() - (rows + 1)));
                    riga = disserviziTableRows.get(disserviziTableRows.size() - (rows + 1));

                } else if(disserviziTableRows.size() > rows) {
                    logger.info("ROWS SELEZIONATA4: " + rows);
                    riga = disserviziTableRows.get(rows);
                }
                else {
                    logger.info("ROWS SELEZIONATA5: " + (disserviziTableRows.size() - 1));
                    riga = disserviziTableRows.get(disserviziTableRows.size() - 1);
                }

            }

            WebElement linkDownloadAttestazione = riga.findElements(By.xpath("//button[@data-testid='download-legal-fact']")).get(0);
            linkDownloadAttestazione.click();
            logger.info("click effettuato con successo");
        } else {
            logger.error("Non ci sono notifiche da selezionare nel arco temporale settato");
            Assert.fail("Non ci sono notifiche da selezionare nel arco temporale settato");
        }
    }


    public void clickLinkAttestazioniOpponibileDisservizi(int numeroLinkAttestazioniOpponibile) {
        if (attestazioniFile.get(numeroLinkAttestazioniOpponibile).isDisplayed()) {
            attestazioniFile.get(numeroLinkAttestazioniOpponibile).click();
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", attestazioniFile.get(numeroLinkAttestazioniOpponibile));
            attestazioniFile.get(numeroLinkAttestazioniOpponibile).click();
        }
    }

    public boolean confrontoFileConDisservizio() {
        getDateDisservice();
        logger.info("date prese con successo dal disserivizio");
        String folderPath = System.getProperty("downloadFilePath");
        // Stringa da cercare nel nome del file
        String searchString = "PN_DOWNTIME_LEGAL_FACTS";
        // Creazione di un oggetto File che rappresenta la cartella
        File folder = new File(folderPath);
        // Controllo che il percorso specificato sia una directory
        if (folder.isDirectory()) {
            // Ottieni l'elenco di tutti i file nella cartella
            File[] files = folder.listFiles();
            // Verifica che la cartella non sia vuota
            if (files != null && files.length > 0) {
                logger.info("Verifica cartella non vuota" + files.length);
                // Cerca i file che contengono la stringa specificata nel nome
                for (File file : files) {
                    logger.info("Verifica cartella non vuota" + file.getName());
                    if (file.isFile() && file.getName().contains(searchString)) {
                        // Puoi eseguire altre operazioni sul file qui
                        try {
                            PDFTextStripper pdfTextStripper = new PDFTextStripper();
                            String text = pdfTextStripper.getText(PDDocument.load(file));
                            logger.info("DATA_POPULATION_A: " + dataPopulation.getDataA());
                            logger.info("DATA_POPULATION_DA: " + dataPopulation.getDataDa());
                            if (text.contains(dataPopulation.getDataA()) && text.contains(dataPopulation.getDataDa())) {
                                return true;
                            }
                            //break// Rimuovere il commento se si desidera fermarsi al primo file trovato
                        } catch (IOException e) {
                            logger.error("Errore nel leggere il PDF: " + file.getName(), e);
                            Assert.fail("Errore nel leggere il PDF: " + file.getName());
                        }
                    }
                }
            } else {
                System.out.println("La cartella è vuota o non è possibile accedervi.");
            }
        } else {
            System.out.println("Il percorso specificato non è una directory.");
        }
        return false;
    }
}


