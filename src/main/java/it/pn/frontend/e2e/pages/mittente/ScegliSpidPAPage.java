package it.pn.frontend.e2e.pages.mittente;

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

public class ScegliSpidPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("ScegliSpidPAPage");

    @FindBy(id = "xx_testenv2")
    WebElement testButton;

    public ScegliSpidPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadScegliSpidPAPage(){
        try{
            By titlePage = By.cssSelector("div.MuiTypography-root.MuiTypography-h4");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            logger.info("TA_QA: Scegli Spid PA Page caricata");
        }catch (TimeoutException e){
            logger.error("TA_QA: titlo della pagina Scegli Spid PA Page non caricato con errore : "+e.getMessage());
            Assert.fail("TA_QA: titlo della pagina Scegli Spid PA Page non caricato con errore : "+e.getMessage());
        }
    }

    public void selezionareTestButton(){
        logger.info("TA_QA: Si clicca sul buttone Test dello spid");
        this.testButton.click();
    }


}
