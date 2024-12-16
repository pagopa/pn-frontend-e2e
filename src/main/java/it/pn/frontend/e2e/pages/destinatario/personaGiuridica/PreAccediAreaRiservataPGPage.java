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

public class PreAccediAreaRiservataPGPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("PreAccediAreaRiservataPGPage");

    @FindBy(id = "login-button")
    WebElement accediButton;

    public PreAccediAreaRiservataPGPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadPreAccediPage() {
        try {
           // WebElement titlePage = driver.findElement(By.id("login-page-title"));
            getWebDriverWait(30).withMessage("Il titolo della pagina PreAccediAreaRiservataPGPage non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("login-page-title"))));
            getWebDriverWait(30).withMessage("Il bottone accedi della pagina PreAccediAreaRiservataPGPage non è visibile").until(ExpectedConditions.visibilityOf(accediButton));
            logger.info("PreAccediAreaRiservataPGPage caricata corretamente");
        } catch (TimeoutException e ){
            logger.error("PreAccediAreaRiservataPGPage non  caricata corretamente con errore: " +e.getMessage());
            Assertions.fail("PreAccediAreaRiservataPGPage non  caricata corretamente con errore: " +e.getMessage());
        }
    }

    public void clickAccediButton() {
        accediButton = driver.findElement(By.id("login-button"));
        getWebDriverWait(30).withMessage("Il bottone accedi della pagina PreAccediAreaRiservataPGPage non è cliccabile").until(ExpectedConditions.elementToBeClickable(accediButton));
        accediButton.click();
    }
}
