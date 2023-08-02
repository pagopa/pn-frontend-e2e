package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecapitiPGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("RecapitiPGPage");

    public RecapitiPGPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadRecapitiPage() {
        try {
            By racapitiPageTitle = By.id("title-of-page");
            By confermaPecButton = By.xpath("//button[@data-testid='add contact']");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(racapitiPageTitle));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(confermaPecButton));
            logger.info("Si visualizza correttamente recapiti page");
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente recapiti page con errore:"+e.getMessage());
            Assert.fail("Non si visualizza correttamente recapiti page con errore:"+e.getMessage());
        }
    }

}
