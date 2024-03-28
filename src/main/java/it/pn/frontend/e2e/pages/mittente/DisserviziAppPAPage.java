package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DisserviziAppPAPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("Disservizi PA Page");

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
            logger.info("Si visualizza correttamente la sezione disservizi");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente la sezione disservizi con errore:" + e.getMessage());
            Assert.fail("Non si visualizza correttamente la sezione disservizi con errore" + e.getMessage());
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
            List<WebElement> disserviziTableRows = disserviziTable.findElements(By.id("tableDowntimeLog.row"));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'header della tabella dei disservizi")
                    .until(ExpectedConditions.visibilityOf(disserviziTableHeader));
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
                    return;
                }
                if (status.getText().contains("-")) {
                    logger.info("Si visualizza data di fine come: -");
                }
                if (status.getText().contains("L'attestazione sarà disponibile al termine del disservizio")) {
                    logger.info("Si visualizza la frase corretta in 'Attestazioni opponibili a terzi'");
                    return;
                }
            }
        }
        logger.error("Non si visualizza un record in elenco relativo ad un disservizio ancora in corso");
        Assert.fail("Non si visualizza un record in elenco relativo ad un disservizio ancora in corso");
    }
}
