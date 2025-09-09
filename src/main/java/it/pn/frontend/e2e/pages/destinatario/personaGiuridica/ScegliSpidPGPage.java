package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

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


public class ScegliSpidPGPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(ScegliSpidPGPage.class);

    @FindBy(id = "xx_testenv2")
    WebElement testButton;

    public ScegliSpidPGPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadScegliSpidPGPage() {
        try {
            WebElement titlePageBy = driver.findElement(By.xpath("//div[contains(text(),'Scegli il tuo SPID')]"));
            testButton = driver.findElement(By.id("xx_testenv2"));
            getWebDriverWait(30).withMessage("Il titolo della pagina Scegli il tuo SPID non è visibile").until(ExpectedConditions.visibilityOf(titlePageBy));
            getWebDriverWait(30).withMessage("Il bottone test della pagina Scegli il tuo SPID non è cliccabile").until(ExpectedConditions.elementToBeClickable(testButton));
            logger.info("ScegliSpidPGPage caricata correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("ScegliSpidPGPage non caricata correttamente con errore: " + e.getMessage());
        }
    }

    public void clickTestButton() {
        testButton = driver.findElement(By.id("xx_testenv2"));
        getWebDriverWait(60).withMessage("Il bottone TEST non è cliccabile nella login").until(ExpectedConditions.elementToBeClickable(testButton));
        testButton.click();
    }
}
