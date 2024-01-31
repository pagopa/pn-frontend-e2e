package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    @FindBy(css = "[id='page2']")
    WebElement page2Button;
    @FindBy(id = "startDate")
    WebElement dataInizioField;

    public void clickNotificheImpresa() {
        try {
            By notificheImpresaButton = By.xpath("//div[@data-testid=\"sideMenuItem-Notifiche dell'impresa\"]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notificheImpresaButton));
            logger.info("Si clicca sulla voce notifiche dell'impresa");
        } catch (Exception e) {
            logger.error("Non si clicca sulla voce notifiche dell'impresa con errore:" + e.getMessage());
            Assert.fail("Non si clicca sulla voce notifiche dell'impresa con errore:" + e.getMessage());
        }
    }

    public void cliccaNotificaRestituita(String codiceIun) {
        By notificaBy = By.xpath("//button[contains(text(),'" + codiceIun + "')]");
        try {
            this.getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(notificaBy));
            this.js().executeScript("arguments[0].click()", this.element(notificaBy));
        } catch (TimeoutException e) {
            logger.error("Notifica non trovata con errore: " + e.getMessage());
            Assert.fail("Notifica non trovata con errore: " + e.getMessage());
        } catch (StaleElementReferenceException e) {
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(notificaBy));
            this.js().executeScript("arguments[0].click()", this.element(notificaBy));
        }
    }

    public void waitLoadDettaglioNotificaPGSection() {
        try {
            By pageTitleDettaglioNotifica = By.id("Pagamento rata IMU-page");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(pageTitleDettaglioNotifica));
            logger.info("La pagina dettaglio notifica si è caricata correttamente");
        } catch (TimeoutException e) {
            logger.error("La pagina dettaglio notifica NON si è caricata correttamente con errore:" + e.getMessage());
            Assert.fail("La pagina dettaglio notifica NON si è caricata correttamente con errore:" + e.getMessage());
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

    public void clickPage2Button() {
        getWebDriverWait(30).withMessage("Il bottone pagina 2  nella pagina ricerca Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.page2Button));
        this.page2Button.click();
    }

    public void inserimentoDataErrato(String da) {
        this.getWebDriverWait(10)
                .until(ExpectedConditions.visibilityOfAllElements(this.dataInizioField));

        this.dataInizioField.click();
        this.dataInizioField.sendKeys(da);
        this.getWebDriverWait(3).until(ExpectedConditions.attributeToBe(this.dataInizioField, "value", da));
    }
    public boolean isDateBoxInvalid(){
        getWebDriverWait(30).withMessage("Il campo data inizio non è visibile").until(ExpectedConditions.visibilityOf(dataInizioField));
        String ariaInvalid = dataInizioField.getAttribute("aria-invalid");
        final String isTextboxInvalid = "true";
        return isTextboxInvalid.equals(ariaInvalid);
    }
}