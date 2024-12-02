package it.pn.frontend.e2e.section.mittente;

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


public class SuccessPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("SuccessPASection");

    @FindBy(id = "go-to-notifications")
    WebElement successButton;

    @FindBy(id = "title-sync-feedback")
    WebElement successCheckBy;

    public SuccessPASection(WebDriver driver) {
        this.driver = driver;
    }


    public void waitLoadSuccessPASection() {
        try {
//            successCheckBy = driver.findElement(By.id("title-sync-feedback"));
            getWebDriverWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.id("title-sync-feedback")));
            logger.info("TA_QA: La notifica è stata creata con successo, PA section caricata correttamente");
        } catch (TimeoutException e) {
            logger.error("TA_QA: Il titolo della Success PA section non caricata con errore: " + e.getMessage());
            Assertions.fail("TA_QA: Il titolo della Success PA section non caricata con errore: " + e.getMessage());
        }
    }

    public void vaiAlleNotifiche() {
        logger.info("click pulsante vai alle notifiche");
        successButton = driver.findElement(By.id("go-to-notifications"));
        successButton.click();
    }
}
