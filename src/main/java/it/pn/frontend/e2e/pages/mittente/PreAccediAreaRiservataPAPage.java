package it.pn.frontend.e2e.pages.mittente;


import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PreAccediAreaRiservataPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(PreAccediAreaRiservataPAPage.class);

    @FindBy(id = "login-button")
    WebElement procediAlLoginButton;

    public PreAccediAreaRiservataPAPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadPreAccediAreaRiservataPAPage() {
        getWebDriverWait(30)
                .withMessage("Il titolo della Pre Accedi Area Riservata PA Page non caricato")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("login-page-title")));

        logger.info("Il titolo della Pre Accedi Area Riservata PA Page è caricato");
    }

    public void selezionaProcediAlLoginButton() {
        logger.info("Si clicca sul bottone procedi al login");
        WebElement procediAlLoginButton = getWebDriverWait(60)
                .withMessage("Il bottone procedi al login non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
        js().executeScript("arguments[0].click()", procediAlLoginButton);
        logger.info("Click sul bottone procedi al login effettuato");
    }

}
