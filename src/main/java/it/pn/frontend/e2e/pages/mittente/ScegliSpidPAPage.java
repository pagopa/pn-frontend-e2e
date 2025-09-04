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

public class ScegliSpidPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ScegliSpidPAPage.class);

    @FindBy(id = "xx_testenv2")
    WebElement testButton;

    public ScegliSpidPAPage(WebDriver driver) {
        this.driver = driver;
    }


    public void waitLoadScegliSpidPAPage() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.MuiTypography-root.MuiTypography-h4"))));
            logger.info("Il titolo della pagina Scegli Spid PA è stato caricato");
        } catch (TimeoutException e) {
            Assertions.fail("Il titolo della pagina Scegli Spid PA non è stato caricato con errore: " + e.getMessage());
        } catch (Exception e) {
            Assertions.fail("Errore generico durante la scelta del provider SPID: " + e.getMessage());
        }
    }

    public void selezionareTestButton() {
        logger.info("Si clicca sul bottone Test dello SPID");
        try {
            getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("xx_testenv2"))));
            testButton = driver.findElement(By.id("xx_testenv2"));
            testButton.click();
            logger.info("Click sul bottone TEST effettuato con successo");
        } catch (TimeoutException e) {
            Assertions.fail("Il bottone TEST non è cliccabile, errore: " + e.getMessage());
        }
    }
}