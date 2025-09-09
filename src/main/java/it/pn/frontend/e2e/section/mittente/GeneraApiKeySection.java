package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GeneraApiKeySection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(GeneraApiKeySection.class);

    public GeneraApiKeySection(WebDriver driver) {
        this.driver = driver;
    }


    public void waitLoadGeneraApiKey() {
        try {
            WebElement generaApiPageTitle = driver.findElement(By.id("Genera una API Key-page"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(generaApiPageTitle));
            logger.info("Api Key caricata");
        } catch (TimeoutException e) {
            Assertions.fail("Genera Api Key NON caricata con errore : " + e.getMessage());
        }
    }
}
