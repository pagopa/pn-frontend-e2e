package it.pn.frontend.e2e.pages.mittente;


import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.section.CookiesSection;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AcccediAreaRiservataPAPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("AccediAreaRiservataPAPage");
    @FindBy(id = "spidButton")
    WebElement spidButton;

    @FindBy (id = "forward_prod-pn-test")
    WebElement testButton;

    public AcccediAreaRiservataPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadLoginPageMittente(){
        try{
            By titoloLabel = By.xpath("//h3[@class = 'MuiTypography-root MuiTypography-h3 css-ktw4ma']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titoloLabel));
            logger.info("Accedi Area Riservata Page caricata");
        }catch (TimeoutException e){
            logger.error("Il titolo nella pagina  Accedi Area Riservata non è presente con errore : "+e.getMessage());
            Assert.fail("Il titolo nella pagina  Accedi Area Riservata non è presente con errore : "+e.getMessage());
        }
    }

    public void selezionareSpidButton(){
        logger.info("Si seleziona il bottone Spid");
        try {
            this.getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(spidButton));

            spidButton.click();
            logger.info("click sul bottone SPID effettuato");
        }catch (TimeoutException e){
            logger.error("Il bottone SPID non è cliccabile con errore : "+e.getMessage());
            Assert.fail("Il bottone SPID non è cliccabile con errore : "+e.getMessage());
        }
    }

    public void clickTestBottone(){
        getWebDriverWait(5).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(testButton), ExpectedConditions.elementToBeClickable(testButton)));
        testButton.click();
    }
}
