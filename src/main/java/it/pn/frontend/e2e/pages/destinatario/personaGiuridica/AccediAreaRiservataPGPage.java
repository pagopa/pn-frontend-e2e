package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class AccediAreaRiservataPGPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("AccediAreaRiservataPGPage");

    @FindBy(id = "spidButton")
    WebElement spidButton;

    private WebTool webTool;

    public AccediAreaRiservataPGPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }


    public void waitLoadAccediAreaRiservataPGPage() {
        try {
            webTool.waitTime(30);
//            spidButton = driver.findElement(By.id("spidButton"));
            By titleBy = By.xpath("//h3[contains(text(),'Come vuoi accedere?')]");
            getWebDriverWait(30).withMessage("il titolo della pagina Accedi Area Riservata non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleBy));
            getWebDriverWait(30).withMessage("Lo spid Button della pagina Accedi Area Riservata non è visibile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("spidButton"))));
            logger.info("Accedi Area Riservata Page caricata correttamente");
        } catch (TimeoutException e ){
            Assertions.fail("Accedi Area Riservata Page non caricata correttamente con errore: "+e.getMessage());
        }

    }

    public void clickSpidButton() {
        spidButton = getWebDriverWait(30).withMessage("Impossibile premere il tasto spid Button della pagina Accedi Area Riservata non è visibile")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("spidButton"))));
        spidButton.click();
    }
}
