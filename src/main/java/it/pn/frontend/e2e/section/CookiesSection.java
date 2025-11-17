package it.pn.frontend.e2e.section;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CookiesSection extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(CookiesSection.class);



    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement accettaTuttiButton;

    @FindBy(xpath = "//button[@aria-label='Chiudi']")
    WebElement chiudiPagamentoPopupButton;

    public CookiesSection(WebDriver driver) {
        this.driver = driver;
    }

    public boolean waitLoadCookiesPage() {
        try {
            getWebDriverWait(30)
                    .withMessage("Problemi con il metodo waitLoadCookiesPage")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-banner-sdk")));
            logger.info("Cookies Page caricata");
            return true;
        } catch (TimeoutException e) {
            logger.warn("Il banner del cookie non è caricato con errore: {}", e.getMessage());
            return false;
        }
    }

    public void selezionaAccettaTuttiButton() {
//        try {
//            WebElement accettaTuttiButton = getWebDriverWait(60)
//                    .until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
//            logger.info("Si seleziona 'Accetta tutti i cookie'");
//            new Actions(driver).moveToElement(accettaTuttiButton).click().perform();
//        } catch (TimeoutException e) {
//            logger.info("Non è cliccabile il bottone 'Accetta tutti i cookie': " + e.getMessage());
//        }


        try {
            getWebDriverWait(60).until(driver -> {
                List<WebElement> buttons = driver.findElements(By.id("onetrust-accept-btn-handler"));
                if (buttons.isEmpty()) return false;              // non presente, riprova
                try {
                    buttons.get(0).click();                        // prova a cliccare
                    return true;                                   // clic riuscito
                } catch (StaleElementReferenceException e) {
                    return true;                                   // click ha rimosso il banner → OK
                } catch (ElementClickInterceptedException e) {
                    return false;                                  // non cliccabile ancora, riprova
                }
            });
            logger.info("Si seleziona 'Accetta tutti' i cookie banner cliccato.");
        } catch (TimeoutException e) {
            logger.info("Cookie banner NON presente o non cliccabile, continuo.");
        }


    }

}
