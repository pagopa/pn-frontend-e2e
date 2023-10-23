package it.pn.frontend.e2e.section;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

    public boolean waitLoadCookiesPage(){
        try{
            By scopriDipiuLink = By.id("onetrust-banner-sdk");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(scopriDipiuLink));
            logger.info("TA_QA: Cookies Page caricata");
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            logger.warn("TA_QA: Cookies Page non caricata, il link scopri di più non è caricato con errore : "+e.getMessage());
            return false;
        }

    }

    public void selezionaAccettaTuttiButton(){
        logger.info("TA_QA : si seleziona accetta tutti i cookies");
        try {
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.accettaTuttiButton));
            Actions actions = new Actions(driver);
            actions.moveToElement(this.accettaTuttiButton).click().perform();
        }catch(TimeoutException | ElementClickInterceptedException e){
            logger.error("TA_QA: non è presente o non è cliccabile il buttone accetta tutti cookies" + e.getMessage());
            Assert.fail("TA_QA: non è presente o non è cliccabile il buttone accetta tutti cookies" + e.getMessage());

        }
    }
}
