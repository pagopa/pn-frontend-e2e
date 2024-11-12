package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutorizziInvioDatiPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AutorizziInvioDatiPAPage");

    @FindBy(css = "button[name='confirm']")
    private WebElement inviaButton;

    @Autowired
    private WebDriver driver;

    public void waitLoadAutorizziInvioDatiPAPage() {
        try {
            By titlePage = By.cssSelector("h1.u-text-r-xl.u-margin-bottom-l");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            logger.info("Autorizzi Invio Dati PA Page caricata");
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Il titolo di Autorizzi Invio Dati PA Page non è caricato con errore: " + e.getMessage());
            Assertions.fail("Il titolo di Autorizzi Invio Dati PA Page non è caricato con errore: " + e.getMessage());
        }
    }

    public void selezionareInvia() {
        logger.info("Click button Invia");
        try {
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(inviaButton));
            inviaButton.click();
        } catch (TimeoutException e) {
            logger.error("Il bottone Invia nella pagina Autorizza Invio Dati non è stato cliccato con errore: " + e.getMessage());
            Assertions.fail("Il bottone Invia nella pagina Autorizza Invio Dati non è stato cliccato con errore: " + e.getMessage());
        }
    }
}
