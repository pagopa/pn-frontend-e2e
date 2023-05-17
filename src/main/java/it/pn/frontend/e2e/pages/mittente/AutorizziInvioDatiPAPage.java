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

public class AutorizziInvioDatiPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AutorizziInvioDatiPAPage");

    @FindBy(css = "button[name='confirm']")
    WebElement invaButton;

    public AutorizziInvioDatiPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAutorizziInvioDatiPAPage(){
        try{
            By titlePage = By.cssSelector("h1.u-text-r-xl.u-margin-bottom-l");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            logger.info("Autorizzi Invio Dati PA Page caricata");
        }catch (TimeoutException e){
            logger.error("Autorizzi Invio Dati PA Page non caricata con errore : "+e.getMessage());
            Assert.fail("Autorizzi Invio Dati PA Page non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionareInvia(){
        this.invaButton.click();
    }
}
