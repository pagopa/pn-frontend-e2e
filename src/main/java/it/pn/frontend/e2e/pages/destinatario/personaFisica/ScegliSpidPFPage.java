package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ScegliSpidPFPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ScegliSpidPFPage.class);


    @FindBy(id = "spid-select-xx_testenv2")
    WebElement testButton;

    private WebTool webTool;

    public ScegliSpidPFPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    //    public void waitLoadScegliSpidDEPage() {
//        try {
//            WebElement titlePage = driver.findElement(By.id("spid-select"));
//            getWebDriverWait(30).withMessage("Il titolo scegli Spid PF non trovato").until(ExpectedConditions.visibilityOf(titlePage));
//            logger.info("Scegli Spid DE Page caricata");
//        } catch (TimeoutException e) {
//            Assertions.fail("Scegli Spid DE Page non caricata con errore : " + e.getMessage());
//        }
//    }
    public void waitLoadScegliSpidDEPage() {
        By titlePageLocator = By.id("spid-select");

        WebElement titlePage = getWebDriverWait(30)
                .withMessage("Il titolo scegli SPID PF non trovato")
                .until(ExpectedConditions.visibilityOfElementLocated(titlePageLocator));

        logger.info("Scegli SPID DE Page caricata");
    }


    public void selezionareTestButton() {
        WebElement testButton = getWebDriverWait(60)
                .withMessage("il bottone TEST del metodo selezionareTestButton non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("spid-select-xx_testenv2")));
        testButton.click();
    }


}
