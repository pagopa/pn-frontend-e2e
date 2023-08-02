package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PiattaformaNotifichePGPAPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("PiattaformaNotifichePGPAPage");

    @FindBy(xpath = "//div[@data-testid='sideMenuItem-Recapiti']")
    WebElement recapitiButton;

    public PiattaformaNotifichePGPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadPitattaformaNotificaPage() {
        try {
            By titlePageBy = By.id("title-of-page");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            logger.info("La piagine Piattamorma Notifiche si carica correttamente");
        }catch (TimeoutException e){
            logger.error("La piagine Piattamorma Notifiche non si carica correttamente con errore: "+e.getMessage());
            Assert.fail("La piagine Piattamorma Notifiche non si carica correttamente con errore: "+e.getMessage());
        }
    }

    public void clickRecapitiButton() {
        this.recapitiButton.click();
    }
}
