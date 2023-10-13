package it.pn.frontend.e2e.pages.mittente;


import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PreAccediAreaRiservataPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("PreAccediAreaRiservataPAPage");

    @FindBy(id = "login-button")
    WebElement procediAlLoginButton;

    public PreAccediAreaRiservataPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadPreAccediAreaRiservataPAPage(){
        try{
            By titleLabel = By.id("login-page-title");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            logger.info("TA_QA : Il titolo della Pre Accedi Area Riservata PA Page è caricato");
        }catch (TimeoutException | NoSuchElementException e){
            logger.error("TA_QA : Il titolo della Pre Accedi Area Riservata PA Page non caricato con errore : "+e.getMessage());
            Assert.fail("TA_QA :Il titolo della Pre Accedi Area Riservata PA Page non caricato con errore  : "+e.getMessage());
        }
    }

    public void selezionaProcediAlLoginButton(){
        logger.info("TA_QA : si clicca sul buttone procedi all login");
        this.js().executeScript("arguments[0].click()",this.procediAlLoginButton);
    }
}
