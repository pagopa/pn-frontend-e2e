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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        try {
            webTool.waitTime(10);
            titlePage = driver.findElement(By.id("Stato della piattaforma-page"));
            getWebDriverWait(10).withMessage("Titolo della pagina non presente").until(ExpectedConditions.visibilityOf(titlePage));
            logger.info("Si visualizza correttamente la sezione disservizi");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente la sezione disservizi con errore:" + e.getMessage());
            Assertions.fail("Non si visualizza correttamente la sezione disservizi con errore" + e.getMessage());
        }
    }

    public void checkDatiPaginaDisservizi() {
        try {
            WebElement subtitlePage = driver.findElement(By.id("subtitle-page"));
            WebElement boxStatus = driver.findElement(By.xpath("//div[@data-testid='app-status-bar']"));
            WebElement dateLastCheck = driver.findElement(By.xpath("//div[@data-testid='appStatus-lastCheck']"));
            this.getWebDriverWait(10).withMessage("titolo non presente").until(ExpectedConditions.visibilityOf(titlePage));
            this.getWebDriverWait(10).withMessage("sottotitolo pagina non presente").until(ExpectedConditions.visibilityOf(subtitlePage));
            this.getWebDriverWait(10).withMessage("stato dell'applicazione non presente").until(ExpectedConditions.visibilityOf(boxStatus));
            this.getWebDriverWait(10).withMessage("ultimo aggiornamento stato piattaforma non presente").until(ExpectedConditions.visibilityOf(dateLastCheck));
        } catch (TimeoutException e) {
            logger.error("Dati presenti nella pagina stato della piattaforma non corretti: " + e.getMessage());
            Assertions.fail("Dati presenti nella pagina stato della piattaforma non corretti: " + e.getMessage());
        }
    }

    public void checkElencoDisservizi() {
        try {
            WebElement elementoDellaListaBy = driver.findElement(By.id("tableDowntimeLog.row"));
            this.getWebDriverWait(30).withMessage("tabella non trovata").until(ExpectedConditions.visibilityOf(elementoDellaListaBy));
            WebElement nomeColonnaDataInizioBy = driver.findElement(By.xpath("//th[contains(text(),'Data di inizio')]"));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(nomeColonnaDataInizioBy));
            WebElement nomeColonnaDataFineBy = driver.findElement(By.xpath("//th[contains(text(),'Data di fine')]"));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(nomeColonnaDataFineBy));
            WebElement nomeColonnaServizioBy = driver.findElement(By.xpath("//th[contains(text(),'Servizio coinvolto')]"));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(nomeColonnaServizioBy));
            WebElement nomeColonnaAttestazioniBy = driver.findElement(By.xpath("//th[contains(text(),'Attestazioni opponibili a terzi')]"));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(nomeColonnaAttestazioniBy));
            WebElement nomeColonnaStatoBy = driver.findElement(By.xpath("//th[contains(text(),'Stato')]"));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(nomeColonnaStatoBy));
            logger.info("Si visualizza correttamente l'elenco dei disservizi");
        } catch (Exception e) {
            Assertions.fail("NON si visualizza correttamente l'elenco dei disservizi con errore:" + e.getMessage());
        }
    }

    public boolean checkDisservizioInCorso() {
        try {
            List<WebElement> dateDisservizioCreato = driver.findElements(By.xpath("//div[@data-testid='downtime-status']"));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(dateDisservizioCreato));
            for (WebElement disservizio : dateDisservizioCreato) {
                if (disservizio.getText().contains("In corso")) {
                    return true;
                }
            }
            return false;
        } catch (TimeoutException e) {
            logger.info("disservizio non creato");
            return false;
        }
    }
}
