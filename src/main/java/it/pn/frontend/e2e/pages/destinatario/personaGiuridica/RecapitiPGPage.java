package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;
import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;


public class RecapitiPGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("RecapitiPGPage");

    public RecapitiPGPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadRecapitiPage() {
        try {
            By racapitiPageTitle = By.id("Recapiti-page");
            getWebDriverWait(30).withMessage("il titolo Ricapiti della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(racapitiPageTitle));
            logger.info("Si visualizza correttamente recapiti page");
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente recapiti page con errore:"+e.getMessage());
            Assert.fail("Non si visualizza correttamente recapiti page con errore:"+e.getMessage());
        }
    }
}
