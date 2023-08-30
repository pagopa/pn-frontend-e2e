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

public class AcccediAreaRiservataPGPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("AcccediAreaRiservataPGPage");

    @FindBy(id = "spidButton")
    WebElement spidButton;
    public AcccediAreaRiservataPGPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAccediAreaRiservataPGPage() {
        try {
            By titleBy = By.xpath("//h3[contains(text(),'Come vuoi accedere?')]");
            By spidButtonBy = By.id("spidButton");

            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleBy));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(spidButtonBy));

            logger.info("AccediAreaRiservaPage caricata correttamente");

        }catch (TimeoutException e ){
            logger.error("AccediAreaRiservaPage non caricata correttamente con errore: "+e.getMessage());
            Assert.fail("AccediAreaRiservaPage non caricata correttamente con errore: "+e.getMessage());
        }

    }

    public void clickSpidButton() {
        spidButton.click();
    }
}
