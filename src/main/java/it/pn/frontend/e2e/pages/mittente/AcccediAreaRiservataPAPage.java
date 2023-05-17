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

public class AcccediAreaRiservataPAPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("AcccediAreaRiservataPAPage");
    @FindBy(id = "spidButton")
    WebElement spidButton;

    public AcccediAreaRiservataPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadLoginPageMittente(){
        try{
            By titoloLabel = By.cssSelector(".MuiTypography-root.MuiTypography-h3.css-ktw4ma");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titoloLabel));
            logger.info("Acccedi Area Riservata Page caricata");
        }catch (TimeoutException e){
            logger.error("Acccedi Area Riservata Page non caricata con errore : "+e.getMessage());
            Assert.fail("Acccedi Area Riservata Page non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionareSipButton(){
        this.spidButton.click();
    }
}
