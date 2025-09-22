package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RecapitiPGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger(RecapitiPGPage.class);


    public RecapitiPGPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadRecapitiPage() {
        getWebDriverWait(10)
                .withMessage("Il titolo Recapiti della pagina non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("Recapiti-page")));
        logger.info("Si visualizza correttamente Recapiti page");
    }

}
