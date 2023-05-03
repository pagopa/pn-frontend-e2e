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

public class PreAccediAreaRiservataPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("PreAccediAreaRiservataPAPage");

    @FindBy(xpath = "//button[text()='Accedi']")
    WebElement procediAlLoginButton;

    public PreAccediAreaRiservataPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadPreAccediAreaRiservataPAPage(){
        try{
            By titleLabel = By.cssSelector("h4.MuiTypography-root.MuiTypography-h4.MuiTypography-alignCenter");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            logger.info("Pre Accedi Area Riservata PA Page caricata");
        }catch (TimeoutException e){
            logger.error("Pre Accedi Area Riservata PA Page non caricata con errore : "+e.getMessage());
            Assert.fail("Pre Accedi Area Riservata PA Page non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionaProcediAlLoginButton(){
        this.procediAlLoginButton.click();
    }
}
