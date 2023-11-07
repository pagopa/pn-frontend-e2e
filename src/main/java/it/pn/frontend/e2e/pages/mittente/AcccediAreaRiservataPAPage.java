package it.pn.frontend.e2e.pages.mittente;


import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
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
            By titoloLabel = By.xpath("//h3[@class = 'MuiTypography-root MuiTypography-h3 css-ktw4ma']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titoloLabel));
            logger.info("Acccedi Area Riservata Page caricata");
        }catch (TimeoutException e){
            logger.error("Il titolo nella pagina  Acccedi Area Riservata non è presente con errore : "+e.getMessage());
            Assert.fail("Il titolo nella pagina  Acccedi Area Riservata non è presente con errore : "+e.getMessage());
        }
    }

    public void selezionareSipButton(){
        logger.info("Si seleziona il bottone Spid");
        this.spidButton.click();
    }
}
