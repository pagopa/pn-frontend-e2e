package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

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


public class DisserviziAppPage extends BasePage {

    @FindBy(id = "Stato della piattaforma-page")
    WebElement titlePage;

    private final Logger logger = LoggerFactory.getLogger(DisserviziAppPage.class);

    private WebTool webTool;

    public DisserviziAppPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadStatoDellaPiattaformaPage() {
        webTool.waitTime(10);
        By titlePageLocator = By.id("Stato della piattaforma-page");
        titlePage = getWebDriverWait(10)
                .withMessage("Titolo della pagina 'Stato della piattaforma' non presente")
                .until(ExpectedConditions.visibilityOfElementLocated(titlePageLocator));
        logger.info("Si visualizza correttamente la sezione disservizi");
    }

    public void checkDatiPaginaDisservizi() {
        By subtitleLocator = By.id("subtitle-page");
        By boxStatusLocator = By.xpath("//div[@data-testid='app-status-bar']");
        By dateLastCheckLocator = By.xpath("//div[@data-testid='appStatus-lastCheck']");
        try {
            getWebDriverWait(10)
                    .withMessage("Titolo pagina non presente")
                    .until(ExpectedConditions.visibilityOf(titlePage));
            // Attendo sottotitolo
            getWebDriverWait(10)
                    .withMessage("Sottotitolo pagina non presente")
                    .until(ExpectedConditions.visibilityOfElementLocated(subtitleLocator));
            // Attendo box status
            getWebDriverWait(10)
                    .withMessage("Stato dell'applicazione non presente")
                    .until(ExpectedConditions.visibilityOfElementLocated(boxStatusLocator));
            // Attendo ultimo aggiornamento
            getWebDriverWait(10)
                    .withMessage("Ultimo aggiornamento stato piattaforma non presente")
                    .until(ExpectedConditions.visibilityOfElementLocated(dateLastCheckLocator));
        } catch (TimeoutException e) {
            Assertions.fail("Dati presenti nella pagina 'Stato della piattaforma' non corretti: " + e.getMessage());
        }
    }

    public void checkElencoDisservizi() {
        By rowLocator = By.id("tableDowntimeLog.row");
        By colDataInizioLocator = By.xpath("//th[contains(text(),'Data di inizio')]");
        By colDataFineLocator = By.xpath("//th[contains(text(),'Data di fine')]");
        By colServizioLocator = By.xpath("//th[contains(text(),'Servizio coinvolto')]");
        By colAttestazioniLocator = By.xpath("//th[contains(text(),'Attestazioni opponibili a terzi')]");
        By colStatoLocator = By.xpath("//th[contains(text(),'Stato')]");

        try {
            getWebDriverWait(30)
                    .withMessage("Tabella dei disservizi non trovata")
                    .until(ExpectedConditions.visibilityOfElementLocated(rowLocator));
            getWebDriverWait(30)
                    .withMessage("Colonna 'Data di inizio' non visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(colDataInizioLocator));
            getWebDriverWait(30)
                    .withMessage("Colonna 'Data di fine' non visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(colDataFineLocator));
            getWebDriverWait(30)
                    .withMessage("Colonna 'Servizio coinvolto' non visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(colServizioLocator));
            getWebDriverWait(30)
                    .withMessage("Colonna 'Attestazioni opponibili a terzi' non visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(colAttestazioniLocator));
            getWebDriverWait(30)
                    .withMessage("Colonna 'Stato' non visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(colStatoLocator));
            logger.info("Si visualizza correttamente l'elenco dei disservizi");
        } catch (TimeoutException e) {
            Assertions.fail("L'elenco dei disservizi non è visualizzato correttamente: " + e.getMessage());
        }
    }

    public boolean checkDisservizioInCorso() {
        By disservizioLocator = By.xpath("//div[@data-testid='downtime-status']");
        try {
            List<WebElement> dateDisservizioCreato = getWebDriverWait(30)
                    .withMessage("Disservizio non creato")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(disservizioLocator));
            for (WebElement disservizio : dateDisservizioCreato) {
                if (disservizio.getText().contains("In corso")) {
                    return true;
                }
            }
            return false;
        } catch (TimeoutException e) {
            logger.info("Nessun disservizio in corso trovato");
            return false;
        }
    }

}
