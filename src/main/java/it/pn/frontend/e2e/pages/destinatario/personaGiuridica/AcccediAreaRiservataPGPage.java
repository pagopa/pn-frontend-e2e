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

            this.getWebDriverWait(30).withMessage("il titolo della pagina Accedi Area Riservata non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleBy));
            this.getWebDriverWait(30).withMessage("Lo spid Button della pagina Accedi Area Riservata non è visibile").until(ExpectedConditions.elementToBeClickable(this.spidButton));

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
