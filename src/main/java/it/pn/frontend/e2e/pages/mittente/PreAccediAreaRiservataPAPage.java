package it.pn.frontend.e2e.pages.mittente;


import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class PreAccediAreaRiservataPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("PreAccediAreaRiservataPAPage");

    @FindBy(id = "login-button")
    WebElement procediAlLoginButton;

    public PreAccediAreaRiservataPAPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadPreAccediAreaRiservataPAPage(){
        try{
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("login-page-title"))));
            logger.info("Il titolo della Pre Accedi Area Riservata PA Page è caricato");
        }catch (TimeoutException e){
            Assertions.fail("Il titolo della Pre Accedi Area Riservata PA Page non caricato con errore  : "+e.getMessage());
        }
    }

    public void selezionaProcediAlLoginButton(){
        logger.info("Si clicca sul bottone procedi al login");
        try {
            getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("login-button"))));
            procediAlLoginButton = driver.findElement(By.id("login-button"));
            js().executeScript("arguments[0].click()", driver.findElement(By.id("login-button")));
            logger.info("click sul bottone procedi alla login effetuato");
        }catch (TimeoutException e){
            logger.error("il bottone procedi alla login non è cliccabile");
        }
    }
}
