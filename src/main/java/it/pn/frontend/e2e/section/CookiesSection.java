package it.pn.frontend.e2e.section;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookiesSection extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("CookiesPage");

    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement accettaTuttiButton;

    public CookiesSection(WebDriver driver) {
        super(driver);
    }

    public boolean waitLoadCookiesPage(){
        try{
            By scopriDipiuLink = By.id("onetrust-pc-btn-handler");
            this.getWebDriverWait(15).until(ExpectedConditions.visibilityOfElementLocated(scopriDipiuLink));
            logger.info("Cookies Page caricata");
            return true;
        }catch (TimeoutException e){
            logger.warn("Cookies Page non caricata con errore : "+e.getMessage());
            return false;
        }

    }

    public void selezionaAccettaTuttiButton(){
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.accettaTuttiButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(this.accettaTuttiButton).click().perform();
    }
}
