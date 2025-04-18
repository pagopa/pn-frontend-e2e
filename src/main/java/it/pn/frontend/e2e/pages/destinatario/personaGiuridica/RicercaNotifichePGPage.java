package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.sql.Driver;

public class RicercaNotifichePGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("RicercaNotifichePGPage");



    @FindBy(id = "filter-notifications-button")
    WebElement filtraButton;
    @FindBy(css = "[data-testid='cancelButton']")
    WebElement rimuoviFiltriButton;
    @FindBy(id = "iunMatch-helper-text")
    WebElement nonValidIunMessage;
    @FindBy(id = "notificationsTable.body.row")
    WebElement primaNotifica;

    @Autowired
    @Lazy
    private  WebTool webTool;

    public RicercaNotifichePGPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void clickNotificheImpresa() {
        try {
            WebElement notificheImpresaButton = driver.findElement(By.xpath("//div[@data-testid=\"sideMenuItem-Notifiche dell'impresa\"]"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(notificheImpresaButton));
            logger.info("Si clicca sulla voce notifiche dell'impresa");
            notificheImpresaButton.click();
        } catch (Exception e) {
            logger.error("Non si clicca sulla voce notifiche dell'impresa con errore:" + e.getMessage());
            Assertions.fail("Non si clicca sulla voce notifiche dell'impresa con errore:" + e.getMessage());
        }
    }

    public void cliccaNotificaRestituita(String codiceIun) {
        WebElement notificaBy = driver.findElement(By.xpath("//button[contains(text(),'" + codiceIun + "')]"));
        try {
            getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(notificaBy));
            js().executeScript("arguments[0].click()", notificaBy);
        } catch (TimeoutException e) {
            logger.error("Notifica non trovata con errore: " + e.getMessage());
            Assertions.fail("Notifica non trovata con errore: " + e.getMessage());
        } catch (StaleElementReferenceException e) {
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(notificaBy));
            this.js().executeScript("arguments[0].click()", notificaBy);
        }
    }

    public void waitLoadDettaglioNotificaPGDelegato() {
        try {
            webTool.waitTime(10);
            WebElement statoNotificaBy = driver.findElement(By.id("notification-state"));
            WebElement indietroButtonBy = driver.findElement(By.id("breadcrumb-indietro-button"));
            WebElement informazioniBy = driver.findElement(By.id("notification-detail-table"));
            WebElement allegatiSection = driver.findElement(By.id("notification-detail-document-attached"));
            WebElement bannerRecapiti = driver.findElement(By.id("side-item-Recapiti"));
            WebElement attestazioneOpponibile = driver.findElement(By.xpath("//button[contains(text(),'Attestazione opponibile a terzi: ')]"));

            getWebDriverWait(10).withMessage("Dettaglio notifica non è visibile").until(ExpectedConditions.visibilityOf(informazioniBy));
            getWebDriverWait(10).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButtonBy));
            getWebDriverWait(10).withMessage("La sezione Documenti allegati non è visibile").until(ExpectedConditions.visibilityOf(allegatiSection));
            getWebDriverWait(10).withMessage("Lo stato della notifica non non è visibile").until(ExpectedConditions.visibilityOf(statoNotificaBy));
            getWebDriverWait(10).withMessage("Il banner Recapiti non è visibile").until(ExpectedConditions.visibilityOf(bannerRecapiti));
            getWebDriverWait(10).withMessage("La sezione attestazione opponibili non è visibile").until(ExpectedConditions.visibilityOf(attestazioneOpponibile));
            logger.info("La pagina dettaglio notifica si è caricata correttamente");
        } catch (TimeoutException e) {
            logger.error("La pagina dettaglio notifica NON si è caricata correttamente con errore:" + e.getMessage());
            Assertions.fail("La pagina dettaglio notifica NON si è caricata correttamente con errore:" + e.getMessage());
        }
    }

    public void clickFiltraButton() {
        filtraButton = driver.findElement(By.id("filter-notifications-button"));
        getWebDriverWait(30).withMessage("Il bottone filtra nella pagina ricerca Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(filtraButton));
        filtraButton.click();
    }

    public void clickRimuoviFiltriButton() {
        rimuoviFiltriButton = driver.findElement(By.cssSelector("[data-testid='cancelButton']"));
        getWebDriverWait(30).withMessage("Il bottone rimuovi filtri nella pagina ricerca Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(rimuoviFiltriButton));
        rimuoviFiltriButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        nonValidIunMessage = driver.findElement(By.id("iunMatch-helper-text"));
        return getWebDriverWait(30).withMessage("Il messagio di errore non e visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("iunMatch-helper-text")))).isDisplayed();
    }

    public void waitLoadNotifichePGPage() {
        try {
           // WebElement tableNotifiche = driver.findElement(By.id("notifications-table"));
            webTool.waitTime(10);
            getWebDriverWait(40).withMessage("La tabella delle Notifiche non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notifications-table"))));
            logger.info("Notifiche PG Page caricata");
        } catch (TimeoutException e) {
            logger.error("Notifiche PG Page non caricata con errore : " + e.getMessage());
            Assertions.fail("Notifiche PG Page non caricata con errore : " + e.getMessage());
        }
    }

}