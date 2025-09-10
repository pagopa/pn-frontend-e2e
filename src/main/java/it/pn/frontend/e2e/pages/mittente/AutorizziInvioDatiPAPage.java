package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AutorizziInvioDatiPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(AutorizziInvioDatiPAPage.class);

    @FindBy(css = "button[name='confirm']")
    private WebElement inviaButton;

    public AutorizziInvioDatiPAPage(WebDriver driver) {
        this.driver = driver;
    }

    //    public void waitLoadAutorizziInvioDatiPAPage() {
//        try {
//            WebElement titlePage = driver.findElement(By.cssSelector("h1.u-text-r-xl.u-margin-bottom-l"));
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(titlePage));
//            logger.info("Autorizzi Invio Dati PA Page caricata");
//        } catch (TimeoutException | NoSuchElementException e) {
//            Assertions.fail("Il titolo di Autorizzi Invio Dati PA Page non è caricato con errore: " + e.getMessage());
//        }
//    }
    public void waitLoadAutorizziInvioDatiPAPage() {
        getWebDriverWait(30)
                .withMessage("Titolo 'Autorizzi Invio Dati PA' non visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1.u-text-r-xl.u-margin-bottom-l")));

        logger.info("Autorizzi Invio Dati PA Page caricata");
    }

    //    public void selezionareInvia() {
//        logger.info("Click button Invia");
//        try {
//            inviaButton = driver.findElement(By.cssSelector("button[name='confirm']"));
//            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(inviaButton));
//            inviaButton.click();
//        } catch (TimeoutException e) {
//            Assertions.fail("Il bottone Invia nella pagina Autorizza Invio Dati non è stato cliccato con errore: " + e.getMessage());
//        }
//    }
    public void selezionareInvia() {
        logger.info("Click button Invia");

        getWebDriverWait(35)
                .withMessage("Bottone Invia non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[name='confirm']")))
                .click();
    }
}
