package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Driver;

public class RicercaNotifichePGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("RicercaNotifichePGPage");

    public RicercaNotifichePGPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "filter-notifications-button")
    WebElement filtraButton;
    @FindBy(css = "[data-testid='cancelButton']")
    WebElement rimuoviFiltriButton;
    @FindBy(id = "iunMatch-helper-text")
    WebElement nonValidIunMessage;
    @FindBy(id = "notificationsTable.body.row")
    WebElement primaNotifica;

    public void clickNotificheImpresa() {
        try {
            By notificheImpresaButton = By.xpath("//div[@data-testid=\"sideMenuItem-Notifiche dell'impresa\"]");
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(notificheImpresaButton));
            logger.info("Si clicca sulla voce notifiche dell'impresa");
            this.element(notificheImpresaButton).click();
        } catch (Exception e) {
            logger.error("Non si clicca sulla voce notifiche dell'impresa con errore:" + e.getMessage());
            Assertions.fail("Non si clicca sulla voce notifiche dell'impresa con errore:" + e.getMessage());
        }
    }

    public void cliccaNotificaRestituita(String codiceIun) {
        By notificaBy = By.xpath("//button[contains(text(),'" + codiceIun + "')]");
        try {
            this.getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(notificaBy));
            this.js().executeScript("arguments[0].click()", this.element(notificaBy));
        } catch (TimeoutException e) {
            logger.error("Notifica non trovata con errore: " + e.getMessage());
            Assertions.fail("Notifica non trovata con errore: " + e.getMessage());
        } catch (StaleElementReferenceException e) {
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(notificaBy));
            this.js().executeScript("arguments[0].click()", this.element(notificaBy));
        }
    }

    public void waitLoadDettaglioNotificaPGDelegato() {
        try {
            By statoNotificaBy = By.id("notification-state");
            By indietroButtonBy = By.id("breadcrumb-indietro-button");
            By informazioniBy = By.id("notification-detail-table");
            By allegatiSection = By.id("notification-detail-document-attached");
            By bannerRecapiti = By.id("side-item-Recapiti");
            By attestazioneOpponibile = By.xpath("//button[contains(text(),'Attestazione opponibile a terzi: ')]");

            getWebDriverWait(10).withMessage("Dettaglio notifica non è visibile").until(ExpectedConditions.visibilityOfElementLocated(informazioniBy));
            getWebDriverWait(10).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOfElementLocated(indietroButtonBy));
            getWebDriverWait(10).withMessage("La sezione Documenti allegati non è visibile").until(ExpectedConditions.visibilityOfElementLocated(allegatiSection));
            getWebDriverWait(10).withMessage("Lo stato della notifica non non è visibile").until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBy));
            getWebDriverWait(10).withMessage("Il banner Recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(bannerRecapiti));
            getWebDriverWait(10).withMessage("La sezione attestazione opponibili non è visibile").until(ExpectedConditions.visibilityOfElementLocated(attestazioneOpponibile));
            logger.info("La pagina dettaglio notifica si è caricata correttamente");
        } catch (TimeoutException e) {
            logger.error("La pagina dettaglio notifica NON si è caricata correttamente con errore:" + e.getMessage());
            Assertions.fail("La pagina dettaglio notifica NON si è caricata correttamente con errore:" + e.getMessage());
        }
    }

    public void clickFiltraButton() {
        getWebDriverWait(30).withMessage("Il bottone filtra nella pagina ricerca Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.filtraButton));
        this.filtraButton.click();
    }

    public void clickRimuoviFiltriButton() {
        getWebDriverWait(30).withMessage("Il bottone rimuovi filtri nella pagina ricerca Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.rimuoviFiltriButton));
        this.rimuoviFiltriButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        return getWebDriverWait(30).withMessage("Il messagio di errore non e visibile").until(ExpectedConditions.visibilityOf(nonValidIunMessage)).isDisplayed();
    }

    public void waitLoadNotifichePGPage() {
        try {
            By tableNotifiche = By.id("notifications-table");
            this.getWebDriverWait(40).withMessage("La tabella delle Notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(tableNotifiche));
            logger.info("Notifiche PG Page caricata");
        } catch (TimeoutException e) {
            logger.error("Notifiche PG Page non caricata con errore : " + e.getMessage());
            Assertions.fail("Notifiche PG Page non caricata con errore : " + e.getMessage());
        }
    }

    public void cliccaSuPrimaNotifica(){
        try {
            WebTool.waitTime(10);
            primaNotifica = driver.findElement(By.id("notificationsTable.body.row"));
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(primaNotifica));
            logger.info("Si clicca sulla prima notifica");
            primaNotifica.click();
        } catch (Exception e) {
            logger.error("Non si clicca sulla prima notifica con errore:" + e.getMessage());
            Assertions.fail("Non si clicca sulla prima notifica con errore:" + e.getMessage());
        }

    }
}