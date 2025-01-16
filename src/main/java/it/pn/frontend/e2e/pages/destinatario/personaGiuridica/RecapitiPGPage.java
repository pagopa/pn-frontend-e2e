package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;
import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


public class RecapitiPGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("RecapitiPGPage");


    public RecapitiPGPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadRecapitiPage() {
        try {
            WebElement racapitiPageTitle = driver.findElement(By.id("Recapiti-page"));
            getWebDriverWait(10).withMessage("il titolo Recapiti della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOf(racapitiPageTitle));
            logger.info("Si visualizza correttamente recapiti page");
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente recapiti page con errore:"+e.getMessage());
            Assertions.fail("Non si visualizza correttamente recapiti page con errore:"+e.getMessage());
        }
    }
}
