package it.pn.frontend.e2e.section;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookiesSection extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("CookiesPage");

    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement accettaTuttiButton;

    public CookiesSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadCookiesPage(){
        try{
            By scopriDipiuLink = By.id("onetrust-pc-btn-handler");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(scopriDipiuLink));
            logger.info("Cookies Page caricata");
        }catch (TimeoutException e){
            logger.error("Cookies Page non caricata con errore : "+e.getMessage());
            Assert.fail("Cookies Page non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionaAccettaTuttiButton(){
        this.accettaTuttiButton.click();
    }
}
