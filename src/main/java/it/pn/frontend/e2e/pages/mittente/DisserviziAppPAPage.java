package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.Getter;
import lombok.Setter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
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

import java.io.File;
import java.io.IOException;
import java.util.List;


public class DisserviziAppPAPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(DisserviziAppPAPage.class);


    @Autowired
    private WebDriverConfig webDriverConfig;

    @Getter
    @Setter
    private String folderPath;

    @Autowired
    private DataPopulation dataPopulation;

    @FindBy(id = "notifications-table")
    private WebElement disserviziTable;

    @FindBy(xpath = "//tr[@id='tableDowntimeLog.row']//td//div[@data-testid='downtime-status']")
    private List<WebElement> statusList;

    @FindBy(css = "[data-testid='download-legal-fact']")
    private List<WebElement> attestazioniFile;

    @FindBy(xpath = "//span[contains(text(), 'Risolto')]")
    private List<WebElement> stato;

    private WebTool webTool;

    public DisserviziAppPAPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadStatoDellaPiattaformaPage() {
        try {

            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("Stato della piattaforma-page")));
            getWebDriverWait(3).until(ExpectedConditions.textToBePresentInElementLocated(By.id("Stato della piattaforma-page"), "Stato della piattaforma"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("subtitle-page")));
            getWebDriverWait(3).until(ExpectedConditions.textToBePresentInElementLocated(By.id("subtitle-page"), "Verifica il funzionamento di SEND, visualizza lo storico dei disservizi e scarica le relative attestazioni opponibili a terzi."));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("appStatusBar")));

            String boxAlertText = element(By.id("appStatusBar")).getText();
            getWebDriverWait(3).until(ExpectedConditions.textToBePresentInElementLocated(By.id("appStatusBar"), boxAlertText.contains("C'è un disservizio in corso") ?
                    "C'è un disservizio in corso. Per maggiori dettagli, consulta la tabella qui sotto." :
                    "Tutti i servizi di SEND sono operativi."
            ));

            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("appStatusLastCheck")));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("notifications-table")));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h6[contains(text(),'Storico dei disservizi')]")));

            logger.info("Si visualizza correttamente la sezione disservizi");
        } catch (TimeoutException e) {
            Assertions.fail("Non si visualizza correttamente la sezione disservizi con errore" + e.getMessage());
        }
    }

    public void getDateDisservice() {
        driver.navigate().back();
        webTool.waitTime(5);
        getWebDriverWait(10)
                .withMessage("Tabella disservizi non trovata")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("notifications-table")));

        // Trovo tutte le righe della tabella
        List<WebElement> disserviziTableRows = getWebDriverWait(10)
                .withMessage("Nessuna riga trovata nella tabella disservizi")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("tableDowntimeLog.row")));

        if (!disserviziTableRows.isEmpty()) {
            WebElement primaRiga = disserviziTableRows.get(0);

            // Trovo le celle con le date
            List<WebElement> dateCells = primaRiga.findElements(
                    By.xpath(".//td[@data-testid='tableDowntimeLog.row.cell']//div//div//p[contains(text(), 'ore')]"));

            if (dateCells.size() >= 2) {
                String dataInizioPrimaRiga = dateCells.get(0).getText();
                String dataFinePrimaRiga = dateCells.get(1).getText();

                if (dataPopulation == null) {
                    dataPopulation = new DataPopulation();
                }
                dataPopulation.setDataDa(dataInizioPrimaRiga);
                dataPopulation.setDataA(dataFinePrimaRiga);

                logger.info("Date recuperate dalla tabella disservizi: Da={}, A={}",
                        dataInizioPrimaRiga, dataFinePrimaRiga);
            } else {
                logger.error("Numero insufficiente di celle data trovate: {}", dateCells.size());
                Assertions.fail("Numero insufficiente di celle data trovate: " + dateCells.size());
            }
        } else {
            Assertions.fail("Non è stato possibile recuperare i dati dalla tabella dei disservizi - tabella vuota");
        }
    }

    public void waitLoadDisserviziTable() {
        try {
            webTool.waitTime(20);

            // 1. Verifica tabella principale
            WebElement disserviziTable = getWebDriverWait(60)
                    .withMessage("Tabella disservizi non visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("notifications-table")));

            // 2. Verifica header
            WebElement tableHeader = disserviziTable.findElement(By.xpath(".//thead[@role='rowgroup']"));
            getWebDriverWait(10).withMessage("Header non visibile").until(ExpectedConditions.visibilityOf(tableHeader));

            // 3. Verifica titoli colonne
            List<WebElement> headerTitles = tableHeader.findElements(By.xpath(".//th[@data-testid='tableDowntimeLog.header.cell']"));
            getWebDriverWait(10).withMessage("Titoli colonne non visibili").until(ExpectedConditions.visibilityOfAllElements(headerTitles));

            if (headerTitles.size() != 5) {
                Assertions.fail("Numero colonne errato. Atteso: 5, Trovato: " + headerTitles.size());
            }

            // 4. Verifica testo titoli
            String[] expectedHeaders = {"Data di inizio", "Data di fine", "Servizio coinvolto", "Attestazioni opponibili a terzi", "Stato"};
            for (int i = 0; i < expectedHeaders.length; i++) {
                getWebDriverWait(5)
                        .withMessage("Titolo colonna " + expectedHeaders[i] + " non corretto")
                        .until(ExpectedConditions.textToBePresentInElement(headerTitles.get(i), expectedHeaders[i]));
            }

            // 5. Verifica righe dati
            List<WebElement> rows = disserviziTable.findElements(By.xpath(".//tr[contains(@id, 'tableDowntimeLog.row')]"));
            if (rows.isEmpty()) {
                Assertions.fail("Nessuna riga di dati trovata");
            }

            // 6. Verifica contenuto prime righe
            for (int i = 0; i < Math.min(rows.size(), 3); i++) {
                List<WebElement> cells = rows.get(i).findElements(By.xpath(".//td[@data-testid='tableDowntimeLog.row.cell']"));
                for (WebElement cell : cells) {
                    getWebDriverWait(3)
                            .withMessage("Cella vuota nella riga " + (i + 1))
                            .until(driver -> !cell.getText().trim().isEmpty());
                }
            }

            logger.info("Tabella disservizi visualizzata correttamente");

        } catch (TimeoutException e) {
            Assertions.fail("Tabella disservizi non visualizzata: " + e.getMessage());
        }
    }

    public void checkDisserviziInCorso() {
        webTool.waitTime(15);
        aggiornamentoPagina();

        By statusLocator = By.xpath("//tr[@id='tableDowntimeLog.row']//td//div[@data-testid='downtime-status']");
        statusList = getWebDriverWait(40)
                .withMessage("Non si trovano i record dei disservizi in corso")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(statusLocator);
                    return elements.isEmpty() ? null : elements; // aspetta fino a quando non ci sono elementi
                });

        boolean trovato = false;

        for (WebElement status : statusList) {
            String text = status.getText().trim();
            if (text.contains("In corso")) {
                logger.info("Si visualizza un record relativo ad un disservizio ancora in corso");
                trovato = true;
            }
            if (text.contains("-")) {
                logger.info("Si visualizza data di fine come '-'");
            }
            if (text.contains("L'attestazione sarà disponibile al termine del disservizio")) {
                logger.info("Si visualizza la frase corretta in 'Attestazioni opponibili a terzi'");
            }
        }

        if (!trovato) {
            Assertions.fail("Non si visualizza un record in elenco relativo ad un disservizio ancora in corso");
        }
    }


    public void checkDisservizioRisolto(String tipoDisservizio) {
        aggiornamentoPagina();
        webTool.waitTime(15);

        // Attendi che la tabella dei disservizi sia presente
        getWebDriverWait(20)
                .withMessage("Impossibile trovare notifications-table in checkDisservizioRisolto")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("notifications-table")));

        // Attendi che le righe della tabella con il tipo di disservizio specificato siano presenti
        List<WebElement> disserviziTableRowsWithTypeOfDisservice = getWebDriverWait(20)
                .withMessage("Impossibile trovare il disservizio: " + tipoDisservizio)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//tr[@id='tableDowntimeLog.row' and contains(., '" + tipoDisservizio + "')]")));

        if (!disserviziTableRowsWithTypeOfDisservice.isEmpty()) {
            // Attendi che gli elementi della data di fine siano presenti nella prima riga
            List<WebElement> dataFineElements = getWebDriverWait(20)
                    .withMessage("Impossibile trovare nella tabella le ore")
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//td[@data-testid='tableDowntimeLog.row.cell']//div//p[contains(text(), 'ore')]")));

            WebElement dataFinePrimaRiga = dataFineElements.size() > 1 ? dataFineElements.get(1) : null;
            // Attendi che lo stato "Risolto" sia presente nella prima riga
            WebElement statoPrimaRiga = getWebDriverWait(10)
                    .withMessage("Impossibile trovare nella tabella le Risolto")
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//td[@data-testid='tableDowntimeLog.row.cell']//div//span[contains(text(), 'Risolto')]")));

            if (dataFinePrimaRiga != null && dataFinePrimaRiga.isDisplayed() && statoPrimaRiga.isDisplayed()) {
                logger.info("Disservizio risolto trovato");
            } else {
                Assertions.fail("Non si visualizza un record in elenco relativo ad un disservizio risolto");
            }
        } else {
            Assertions.fail("Nessun disservizio trovato del tipo specificato: " + tipoDisservizio);
        }

    }

    public void checkDisserviziDisponibili() {
        try {
            aggiornamentoPagina();
            webTool.waitTime(20);

            // Aspetto che gli elementi di status siano presenti
            List<WebElement> statusList = getWebDriverWait(20)
                    .withMessage("Nessun elemento di status trovato nella tabella")
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//tr[contains(@id, 'tableDowntimeLog.row')]//td//div[@data-testid='downtime-status']")));

            boolean foundRisolto = false;
            boolean foundDataFine = false;
            boolean foundScaricaAttestazione = false;

            if (!statusList.isEmpty()) {
                logger.info("Trovati {} record nella tabella disservizi", statusList.size());

                for (WebElement status : statusList) {
                    String statusText = status.getText().trim();
                    logger.debug("Controllo status: {}", statusText);

                    if (statusText.contains("Risolto")) {
                        logger.info("Trovato record con disservizio risolto");
                        foundRisolto = true;

                        // Cerco gli altri elementi nella stessa riga
                        WebElement row = status.findElement(By.xpath("./ancestor::tr[contains(@id, 'tableDowntimeLog.row')]"));

                        // Verifica data di fine
                        try {
                            WebElement dataFineCell = row.findElement(By.xpath(".//td[contains(@data-testid, 'tableDowntimeLog.row.cell')][2]"));
                            String dataFineText = dataFineCell.getText().trim();

                            if (dataFineText.contains("/") || (dataFineText.contains("Oggi") && dataFineText.contains(":"))) {
                                logger.info("Data di fine servizio corretta: {}", dataFineText);
                                foundDataFine = true;
                            }
                        } catch (Exception e) {
                            logger.warn("Impossibile verificare la data di fine: {}", e.getMessage());
                        }

                        // Verifica attestazione
                        try {
                            WebElement attestazioneCell = row.findElement(By.xpath(".//td[contains(@data-testid, 'tableDowntimeLog.row.cell')][4]"));
                            String attestazioneText = attestazioneCell.getText().trim();

                            if (attestazioneText.contains("Scarica l'attestazione")) {
                                logger.info("Frase 'Scarica l'attestazione' trovata");
                                foundScaricaAttestazione = true;
                            }
                        } catch (Exception e) {
                            logger.warn("Impossibile verificare l'attestazione: {}", e.getMessage());
                        }

                        // Se ho trovato tutto in questo record, esco dal loop
                        if (foundRisolto && foundDataFine && foundScaricaAttestazione) {
                            break;
                        }
                    }
                }

                // Verifiche finali
                if (!foundRisolto) {
                    Assertions.fail("Non si visualizza un record relativo ad un disservizio risolto");
                }

                if (!foundDataFine) {
                    logger.warn("Non trovata data di fine nel formato atteso");
                }

                if (!foundScaricaAttestazione) {
                    logger.warn("Non trovata la frase 'Scarica l'attestazione'");
                }

            } else {
                Assertions.fail("Non si visualizza alcun record nella tabella dei disservizi");
            }

        } catch (TimeoutException e) {
            Assertions.fail("Timeout durante la verifica dei disservizi disponibili: " + e.getMessage());
        } catch (Exception e) {
            Assertions.fail("Errore durante la verifica dei disservizi: " + e.getMessage());
        }
    }

    public void downloadAttestazione() {
        performDownloadAttestazione(0);
    }

    public void downloadAttestazione(int rows) {
        performDownloadAttestazione(rows);
    }

    private void performDownloadAttestazione(int indexModifier) {

        //if == o random
        //if  > 0 prendo quello selezionato
        boolean downloadVerificato = false;
        List<WebElement> links = getWebDriverWait(45)
                .withMessage("Lista vuota nella pagina Stato della Piattaforma")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@data-testid='download-legal-fact']")));
        int linksSize = links.size();
        if (indexModifier == 0) {
            for (int linkIndex = 0; linkIndex < linksSize - 1; linkIndex++) {
                links.get(linkIndex).click();
                downloadVerificato = checkMessaggioScadenzaDownload();
                if (downloadVerificato) {
                    logger.info("click a");
                    break;
                } else {// Torna indietro alla pagina di Google
                    logger.info("click c");
                    driver.navigate().back();
                }
                links = getWebDriverWait(45)
                        .withMessage("Lista vuota nella pagina Stato della Piattaforma")
                        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@data-testid='download-legal-fact']")));
            }
        } else {
            if (!links.isEmpty()) {
                links.get(indexModifier).click();
                downloadVerificato = true;
            }

        }
        if (!downloadVerificato) {
            Assertions.fail("Nessun download ha superato la verifica.");
        }

    }

    private WebElement determineRowElement(List<WebElement> rows, int randomNumber, int indexModifier) {
        WebElement selectedRow;
        if (rows.size() > randomNumber) {
            selectedRow = rows.get(randomNumber);
            logger.info("Riga selezionata (index={}): {}", randomNumber, selectedRow.getText());
        } else {
            int adjustedIndex = rows.size() > indexModifier ? rows.size() - (indexModifier + 1) : rows.size() - 1;
            selectedRow = rows.get(adjustedIndex);
            logger.info("Riga selezionata (adjusted index={}): {}", adjustedIndex, selectedRow.getText());
        }
        return selectedRow;
    }

    public void clickLinkAttestazioniOpponibileDisservizi(int index) {
        List<WebElement> attestazioniFile = getWebDriverWait(10)
                .withMessage("Nessun link di attestazione opponibile trovato")
                .until(driver -> driver.findElements(By.cssSelector("[data-testid='download-legal-fact']")));

        if (attestazioniFile.size() <= index) {
            Assertions.fail("Il link con indice " + index + " non esiste. Totale link presenti: " + attestazioniFile.size());
        }

        WebElement link = attestazioniFile.get(index);

        if (!link.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true);", link);
        }

        getWebDriverWait(10)
                .withMessage("Il link di attestazione opponibile non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(link))
                .click();

        logger.info("Cliccato il link di attestazione opponibile numero {}", index);
    }

    public boolean confrontoFileConDisservizio() {
        getDateDisservice();
        logger.info("date prese con successo dal disserivizio");

        if (webDriverConfig != null) {
            folderPath = webDriverConfig.getDownloadFilePath();
        }
        logger.info("DOWNLOAD FOLDER : {}", folderPath);
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
                logger.info("Verifica cartella non vuota: {}", files.length);
                // Cerca i file che contengono la stringa specificata nel nome
                for (File file : files) {
                    logger.info("Verifica cartella non vuota: {}", file.getName());
                    if (file.isFile() && file.getName().contains(searchString)) {
                        // Puoi eseguire altre operazioni sul file qui
                        try {
                            PDFTextStripper pdfTextStripper = new PDFTextStripper();
                            String text = pdfTextStripper.getText(PDDocument.load(file));
                            logger.info("DATA_POPULATION_A: {}", dataPopulation.getDataA());
                            logger.info("DATA_POPULATION_DA: {}", dataPopulation.getDataDa());
                            if (text.contains(dataPopulation.getDataA()) && text.contains(dataPopulation.getDataDa())) {
                                return true;
                            }
                            //break// Rimuovere il commento se si desidera fermarsi al primo file trovato
                        } catch (IOException e) {
                            Assertions.fail("Errore nel leggere il PDF: " + file.getName());
                        }
                    }
                }
            } else {
                logger.info("La cartella è vuota o non è possibile accedervi.");
            }
        } else {
            logger.info("Il percorso specificato non è una directory.");
        }
        return false;
    }

    public boolean checkMessaggioScadenzaDownload() {
        try {
            getWebDriverWait(10)
                    .withMessage("In messaggio Al momento non è possibile scaricare il documento non è visibile")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Al momento non è possibile scaricare il documento')]")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}


