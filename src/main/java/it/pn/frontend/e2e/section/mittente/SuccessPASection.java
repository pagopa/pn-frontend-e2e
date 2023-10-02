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
            By successCheckBy = By.cssSelector("svg[class='MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-15b4v0q']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(successCheckBy));
            logger.info("Success PA section caricata");
        }catch (TimeoutException e){
            logger.error("Success PA section non caricata con errore: "+e.getMessage());
            Assert.fail("Success PA section non caricata con errore: "+e.getMessage());
        }
    }

    public void vaiAlleNotifiche() {
        this.successButton.click();
    }
}
