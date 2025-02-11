package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class IntegrazioneAPIPGPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("IntegrazioneAPIPG");

    private WebTool webTool;

    public IntegrazioneAPIPGPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadIntegrazioneAPIPage() {
        try {
            webTool.waitTime(5);
            getWebDriverWait(10).withMessage("Il titolo della pagina Notifiche PG non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Integrazione API-page"))));
            logger.info("La pagina Piattaforma Integrazione API si carica correttamente");
        } catch (TimeoutException e) {
            logger.error("La pagina Integrazione API non si carica correttamente con errore: {}", e.getMessage());
            Assertions.fail(MessageFormat.format("La pagina Integrazione API non si carica correttamente con errore: {0}", e.getMessage()));
        }
    }

    public boolean generaChiavePubblicaDisplayed() {
        try {
            return getWebDriverWait(5).withMessage("Il bottone Genera chiave pubblica non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("generate-public-key")))).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.warn("Il bottone Genera chiave pubblica non è visibile");
            return false;
        }
    }

    public void clickBottoneGeneraChiavePubblica() {
        getWebDriverWait(10).withMessage("Bottone Genera chiave pubblica non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("generate-public-key"))));
        WebElement generaChiavePubblicaButton = driver.findElement(By.id("generate-public-key"));
        generaChiavePubblicaButton.click();
    }

    public void checkConfermaCreazioneChiavePubblica() {
        webTool.waitTime(5);
        getWebDriverWait(10).withMessage("Il label Stato per la chiave pubblica non è su 'Attiva' o non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@data-testid,'statusChip-Attiva')]"))));
        //TO DO: Attiva se si conferma che il pop up deve essere nella pagina Integrazione API (ora è su Registra Chiave Pubblica -> Controlla i parametri)
        //getWebDriverWait(30).withMessage("Pop up NON visualizzato").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role='alert']/div[text()='Chiave pubblica registrata con successo.']"))));
    }
}
