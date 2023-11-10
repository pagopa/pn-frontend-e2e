package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutorizziInvioDatiPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AutorizziInvioDatiPAPage");

    @FindBy(css = "button[name='confirm']")
    WebElement inviaButton;

    public AutorizziInvioDatiPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAutorizziInvioDatiPAPage(){
        try{
            By titlePage = By.cssSelector("h1.u-text-r-xl.u-margin-bottom-l");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            logger.info("TA_QA: Autorizzi Invio Dati PA Page caricata");
        }catch (TimeoutException | NoSuchElementException e){
            logger.error("TA_QA: il titolo di Autorizzi Invio Dati PA Page non caricato con errore : "+e.getMessage());
            Assert.fail("TA_QA: il titolo di Autorizzi Invio Dati PA Page non caricato con errore : "+e.getMessage());
        }
    }

    public void selezionareInvia(){
        logger.info("Click button Invia");
        try {
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.inviaButton));
            this.inviaButton.click();
        }catch (TimeoutException e){
        logger.error("Il bottone invia nella pagina Autorizza Invio Dati  non è stato cliccato con errore : "+e.getMessage());
        Assert.fail("Il bottone invia nella pagina Autorizza Invio Dati non è stato cliccato con errore : "+e.getMessage());
    }
    }
}
