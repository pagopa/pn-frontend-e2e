package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ComeVuoiAccederePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ComeVuoiAccederePage.class);

    @FindBy(id = "spidButton")
    WebElement spidButton;

    public ComeVuoiAccederePage(WebDriver driver) {
        this.driver = driver;
    }

    //    public void waitLoadComeVuoiAccederePage() {
//        try {
//            WebElement titoloLabel = driver.findElement(By.id("login-mode-page-title"));
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(titoloLabel));
//            logger.info("Come Vuoi Accedere Page caricata");
//        } catch (TimeoutException e) {
//            Assertions.fail("Come Vuoi Accedere Page non caricata con errore : " + e.getMessage());
//        }
//    }
    public void waitLoadComeVuoiAccederePage() {
        By titoloLabelLocator = By.id("login-mode-page-title");

        getWebDriverWait(30)
                .withMessage("La pagina 'Come Vuoi Accedere' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(titoloLabelLocator));

        logger.info("Come Vuoi Accedere Page caricata correttamente");
    }


    //    public void selezionaSpidButton() {
//
//        getWebDriverWait(60).withMessage("Il bottone SPID non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("spidButton"))));
//        spidButton = driver.findElement(By.id("spidButton"));
//        spidButton.click();
//    }
    public void selezionaSpidButton() {
        By spidButtonLocator = By.id("spidButton");

        WebElement spidButton = getWebDriverWait(60)
                .withMessage("Il bottone SPID non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(spidButtonLocator));

        spidButton.click();
        logger.info("Bottone SPID cliccato correttamente");
    }


    //    public boolean verificaPresenzaSpidButton() {
//
//        getWebDriverWait(60).withMessage("spid button non è visibile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("spidButton"))));
//        spidButton = driver.findElement(By.id("spidButton"));
//        return spidButton.isDisplayed();
//    }
    public boolean verificaPresenzaSpidButton() {
        By spidButtonLocator = By.id("spidButton");

        WebElement spidButton = getWebDriverWait(60)
                .withMessage("SPID button non è visibile")
                .until(ExpectedConditions.elementToBeClickable(spidButtonLocator));

        return spidButton.isDisplayed();
    }

}
