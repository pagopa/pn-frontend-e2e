package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneraApiKeySection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("GeneraApiKeySection");

    public GeneraApiKeySection(WebDriver driver) {
        super(driver);
    }


    public void waitLoadGeneraApiKey() {
        try{
            By generaApiPageTitle = By.id("Genera una API Key-page");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(generaApiPageTitle));
            logger.info("Api Key caricata");
        }catch (TimeoutException e){
            logger.error("Genera Api Key NON caricata con errore : "+e.getMessage());
            Assert.fail("Genera Api Key NON caricata con errore : "+e.getMessage());
        }
    }
}
