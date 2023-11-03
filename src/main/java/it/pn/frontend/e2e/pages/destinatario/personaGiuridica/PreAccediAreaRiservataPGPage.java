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

public class PreAccediAreaRiservataPGPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("PreAccediAreaRiservataPGPage");

    @FindBy(id = "login-button")
    WebElement accediButton;

    public PreAccediAreaRiservataPGPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadPreAccediPage() {
        try {
            By titlePage = By.id("login-page-title");

            this.getWebDriverWait(30).withMessage("Il titolo della pagina PreAccediAreaRiservataPGPage non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            this.getWebDriverWait(30).withMessage("Il bottone accedi della pagina PreAccediAreaRiservataPGPage non è visibile").until(ExpectedConditions.visibilityOf(accediButton));

            logger.info("PreAccediAreaRiservataPGPage caricata corretamente");

        }catch (TimeoutException e ){
            logger.error("PreAccediAreaRiservataPGPage non  caricata corretamente con errore: " +e.getMessage());
            Assert.fail("PreAccediAreaRiservataPGPage non  caricata corretamente con errore: " +e.getMessage());
        }
    }

    public void clickAccediButton() {
        this.getWebDriverWait(30).withMessage("Il bottone accedi della pagina PreAccediAreaRiservataPGPage non è cliccabile").until(ExpectedConditions.elementToBeClickable(accediButton));
        this.accediButton.click();
    }
}
