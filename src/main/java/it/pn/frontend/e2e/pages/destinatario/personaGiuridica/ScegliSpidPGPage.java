package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScegliSpidPGPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("ScegliSpidPGPage");

    @FindBy(id = "xx_testenv2")
    WebElement testButton;
    public ScegliSpidPGPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadScegliSpidPGPage() {

        try {
            By titlePageBy = By.xpath("//div[contains(text(),'Scegli il tuo SPID')]");

            this.getWebDriverWait(30).withMessage("Il titolo della pagina Scegli il tuo SPID non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).withMessage("Il buttone test della pagina Scegli il tuo SPID non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.testButton));

            logger.info("ScegliSpidPGPage caricata correttamente");

        }catch (TimeoutException e){
            logger.error("ScegliSpidPGPage  non caricata correttamente con errore: " +e.getMessage());
            Assert.fail("ScegliSpidPGPage  non caricata correttamente con errore: " +e.getMessage());
        }
    }

    public void clickTestButton() {
        this.testButton.click();
    }
}
