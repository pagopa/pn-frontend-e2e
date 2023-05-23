package it.pn.frontend.e2e.pages.destinatario;

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

public class ScegliSpidDEPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("ScegliSpidDEPage");

    @FindBy(xpath = "//button[contains(@id,'spid-select-xx_testenv2')]")
    WebElement testButton;

    public ScegliSpidDEPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadScegliSpidDEPage(){
        try{
            By titlePage = By.cssSelector("div.MuiTypography-root.MuiTypography-h3");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            logger.info("Scegli Spid DE Page caricata");
        }catch (TimeoutException e){
            logger.error("Scegli Spid DE Page non caricata con errore : "+e.getMessage());
            Assert.fail("Scegli Spid DE Page non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionareTestButton(){
        this.testButton.click();
    }


}
