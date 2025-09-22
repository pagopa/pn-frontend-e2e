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

import java.util.List;

public class RicercaNotifichePGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger(RicercaNotifichePGPage.class);


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
    private WebTool webTool;

    public RicercaNotifichePGPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void clickNotificheImpresa() {
        By notificheImpresaLocator = By.xpath("//div[@data-testid=\"sideMenuItem-Notifiche dell'impresa\"]");

        WebElement notificheImpresaButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Notifiche dell'impresa' non è visibile")
                .until(ExpectedConditions.elementToBeClickable(notificheImpresaLocator));

        logger.info("Si clicca sulla voce 'Notifiche dell'impresa'");
        notificheImpresaButton.click();
    }

    public void cliccaNotificaRestituita(String codiceIun) {
        By notificaLocator = By.xpath("//button[contains(text(),'" + codiceIun + "')]");

        try {
            WebElement notificaButton = getWebDriverWait(60)
                    .withMessage("Notifica con codice " + codiceIun + " non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(notificaLocator));
            js().executeScript("arguments[0].click()", notificaButton);

        } catch (StaleElementReferenceException e) {
            WebElement notificaButton = getWebDriverWait(30)
                    .withMessage("Notifica con codice " + codiceIun + " non cliccabile dopo StaleElementReferenceException")
                    .until(ExpectedConditions.elementToBeClickable(notificaLocator));
            js().executeScript("arguments[0].click()", notificaButton);
        } catch (TimeoutException e) {
            Assertions.fail("Notifica con codice " + codiceIun + " non trovata entro il timeout: " + e.getMessage());
        }
    }

    public void waitLoadDettaglioNotificaPGDelegato() {
        By statoNotificaLocator = By.id("notification-state");
        By indietroButtonLocator = By.id("breadcrumb-indietro-button");
        By informazioniLocator = By.id("notification-detail-table");
        By allegatiSectionLocator = By.id("notification-detail-document-attached");
        By bannerRecapitiLocator = By.id("side-item-Recapiti");
        By attestazioneOpponibileLocator = By.xpath("//button[contains(text(),'Attestazione opponibile a terzi: ')]");

        getWebDriverWait(10)
                .withMessage("Dettaglio notifica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(informazioniLocator));

        getWebDriverWait(10)
                .withMessage("Il bottone indietro non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(indietroButtonLocator));

        getWebDriverWait(10)
                .withMessage("La sezione Documenti allegati non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(allegatiSectionLocator));

        getWebDriverWait(10)
                .withMessage("Lo stato della notifica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(statoNotificaLocator));

        getWebDriverWait(10)
                .withMessage("Il banner Recapiti non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(bannerRecapitiLocator));

        getWebDriverWait(10)
                .withMessage("La sezione attestazione opponibili non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(attestazioneOpponibileLocator));

        logger.info("La pagina dettaglio notifica si è caricata correttamente");
    }

    public void clickFiltraButton() {
        By filtraButtonLocator = By.id("filter-notifications-button");

        WebElement filtraButton = getWebDriverWait(30)
                .withMessage("Il bottone 'Filtra' nella pagina ricerca Notifiche PG non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(filtraButtonLocator));

        filtraButton.click();
        logger.info("Click sul bottone 'Filtra' effettuato");
    }

    public void clickRimuoviFiltriButton() {
        By rimuoviFiltriLocator = By.cssSelector("[data-testid='cancelButton']");

        WebElement rimuoviFiltriButton = getWebDriverWait(30)
                .withMessage("Il bottone 'Rimuovi filtri' nella pagina ricerca Notifiche PG non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(rimuoviFiltriLocator));

        rimuoviFiltriButton.click();
        logger.info("Click sul bottone 'Rimuovi filtri' effettuato");
    }

    public boolean isErrorMessageDisplayed() {
        By errorMessageLocator = By.id("iunMatch-helper-text");

        try {
            WebElement nonValidIunMessage = getWebDriverWait(30)
                    .withMessage("Il messaggio di errore non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));

            return nonValidIunMessage.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("Il messaggio di errore non è visibile entro 30 secondi");
            return false;
        }
    }

    public void waitLoadNotifichePGPage() {
        webTool.waitTime(10);
        By notificationsTableLocator = By.id("notifications-table");

        getWebDriverWait(40)
                .withMessage("La tabella delle Notifiche non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(notificationsTableLocator));

        logger.info("Notifiche PG Page caricata correttamente");
    }

    public void cliccaSuPrimaNotifica() {
        try {
            webTool.waitTime(10);
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable((By.id("notificationsTable.body.row"))));
            logger.info("Si clicca sulla prima notifica");
            primaNotifica = driver.findElement(By.id("notificationsTable.body.row"));
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(primaNotifica.findElement(By.xpath("//button[@data-testid='goToNotificationDetail']"))));
            WebElement vediDettaglio = primaNotifica.findElement(By.xpath("//button[@data-testid='goToNotificationDetail']"));
            vediDettaglio.click();
        } catch (Exception e) {
            Assertions.fail("Non si clicca sulla prima notifica con errore:" + e.getMessage());
        }

    }

    public void clickAnnullaValidazione() {
        WebElement annullaValidazioneButton = getWebDriverWait(20)
                .withMessage("Impossibile cliccare su annulla validazione")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='cancelValidation']")));
        annullaValidazioneButton.click();
    }

    public void clickBottoneConfermaPopUp() {
        WebElement confermaButton = getWebDriverWait(20)
                .withMessage("Impossibile cliccare su annulla validazione")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Conferma')]")));
        confermaButton.click();
    }

    public void verificaNeumroCopyCostiDiNotificaInclusi(int numeroCopy, String codiceIUN) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        try {
            if (numeroCopy == 0) {
                // Verifica l'assenza di elementi
                try {
                    List<WebElement> costElements = getWebDriverWait(2).until(
                            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                    By.xpath("//p[contains(@data-testid, 'apply-costs-caption')]")
                            )
                    );
                    Assertions.assertTrue(costElements.isEmpty(), "Dovrebbero non esserci elementi con 'apply-costs-caption', ma ne sono stati trovati: " + costElements.size());
                } catch (TimeoutException e) {
                    // Se non vengono trovati elementi, il test continua
                    logger.info("Nessun elemento con 'apply-costs-caption' trovato, come atteso.");
                }
            } else {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                webTool.waitTime(2);
                List<WebElement> costElements = getWebDriverWait(30)
                        .withMessage("Impossibile trovare il Copy Costi Di Notifica Inclusi con IUN: " + codiceIUN)
                        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                By.xpath("//p[contains(@data-testid, 'apply-costs-caption')]")
                        ));
                Assertions.assertEquals(numeroCopy, costElements.size(), "Il numero atteso: " + numeroCopy + " non corrisponde al numero di Copy Costi Di Notifica Inclusi visualizzato: " + costElements.size() + " Con Codice IUN: " + codiceIUN);
            }
        } catch (Exception e) {
            Assertions.fail("Errore durante la verifica del numero di Copy Costi Di Notifica Inclusi: " + e.getMessage());
        }
    }

}