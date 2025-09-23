package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GeneraApiKeySection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(GeneraApiKeySection.class);

    public GeneraApiKeySection(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadGeneraApiKey() {
        By generaApiPageTitleBy = By.id("Genera una API Key-page");
        getWebDriverWait(30)
                .withMessage("Genera Api Key non caricata")
                .until(ExpectedConditions.visibilityOfElementLocated(generaApiPageTitleBy));
        logger.info("Api Key caricata");
    }

}
