package it.pn.frontend.e2e.section.mittente;

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

public class SuccessPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("SuccessPASection");

    @FindBy(id = "go-to-notifications")
    WebElement successButton;

    public SuccessPASection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadSuccessPASection() {
        try{
            By successCheckBy = By.id("title-sync-feedback");
            this.getWebDriverWait(this.loadComponentWaitTime).until(ExpectedConditions.visibilityOfElementLocated(successCheckBy));
            logger.info("TA_QA: La notifica è stata creata con successo, PA section caricata correttamente");
        }catch (TimeoutException e){
            logger.error("TA_QA: Il titolo della Success PA section non caricata con errore: "+e.getMessage());
            Assert.fail("TA_QA: Il titolo della Success PA section non caricata con errore: "+e.getMessage());
        }
    }

    public void vaiAlleNotifiche() {
        logger.info("click pulsante vai alle notifiche");
        this.successButton.click();
    }
}
