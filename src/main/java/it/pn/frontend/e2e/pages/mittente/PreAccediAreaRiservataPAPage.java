package it.pn.frontend.e2e.pages.mittente;


import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
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
            logger.info("Il titolo della Pre Accedi Area Riservata PA Page è caricato");
        }catch (TimeoutException e){
            logger.error("Il titolo della Pre Accedi Area Riservata PA Page non caricato con errore : "+e.getMessage());
            Assertions.fail("Il titolo della Pre Accedi Area Riservata PA Page non caricato con errore  : "+e.getMessage());
        }
    }

    public void selezionaProcediAlLoginButton(){
        logger.info("Si clicca sul bottone procedi al login");
        try {
            getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(procediAlLoginButton));
            this.js().executeScript("arguments[0].click()", this.procediAlLoginButton);
            logger.info("click sul bottone procedi alla login effetuato");
        }catch (TimeoutException e){
            logger.error("il bottone procedi alla login non è cliccabile");
        }
    }
}
