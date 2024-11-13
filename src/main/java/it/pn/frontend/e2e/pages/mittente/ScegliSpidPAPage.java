package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
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
/*
*Modifiche e Spiegazioni
Annotazione @Component: Marca la classe come un componente gestito da Spring, permettendo l’iniezione nei contesti necessari.
Costruttore con @Autowired: Permette a Spring di iniettare automaticamente WebDriver.
Logging migliorato: Utilizza il logger.error con {} per un’output più leggibile e facile da mantenere.
* */

public class ScegliSpidPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("ScegliSpidPAPage");

    @FindBy(id = "xx_testenv2")
    WebElement testButton;




    public void waitLoadScegliSpidPAPage() {
        try {
            By titlePage = By.cssSelector("div.MuiTypography-root.MuiTypography-h4");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            logger.info("Il titolo della pagina Scegli Spid PA è stato caricato");
        } catch (TimeoutException e) {
            logger.error("Il titolo della pagina Scegli Spid PA non è stato caricato con errore: {}", e.getMessage());
            Assertions.fail("Il titolo della pagina Scegli Spid PA non è stato caricato con errore: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Errore generico durante la scelta del provider SPID: {}", e.getMessage());
            Assertions.fail("Errore generico durante la scelta del provider SPID: " + e.getMessage());
        }
    }

    public void selezionareTestButton() {
        logger.info("Si clicca sul bottone Test dello SPID");
        try {
            getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(this.testButton));
            this.testButton.click();
            logger.info("Click sul bottone TEST effettuato con successo");
        } catch (TimeoutException e) {
            logger.error("Il bottone TEST non è cliccabile, errore: {}", e.getMessage());
            Assertions.fail("Il bottone TEST non è cliccabile, errore: " + e.getMessage());
        }
    }
}