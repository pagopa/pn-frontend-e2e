package it.pn.frontend.e2e.section;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
            getWebDriverWait(20)
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

        try {
            WebElement accettaTuttiButton = getWebDriverWait(30)
                    .withMessage("Il bottone 'Accetta tutti i cookie' non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));

            logger.info("Si seleziona 'Accetta tutti i cookie'");
            new Actions(driver).moveToElement(accettaTuttiButton).click().perform();

        } catch (TimeoutException e) {
            Assertions.fail("Non è cliccabile il bottone 'Accetta tutti i cookie': " + e.getMessage());
        }

    }

    //    public void chiudiPagamentoPopup() {
//            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Chiudi']")));
//            logger.info("Si seleziona chiudi i cookies");
//             chiudiPagamentoPopupButton = driver.findElement(By.xpath("//button[@aria-label='Chiudi']"));
//
//            Actions actions = new Actions(driver);
//            actions.moveToElement(chiudiPagamentoPopupButton).click().perform();
//    }
    public void chiudiPagamentoPopup() {
        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Chiudi']")));
        logger.info("Si seleziona chiudi i cookies");
        chiudiPagamentoPopupButton = driver.findElement(By.xpath("//button[@aria-label='Chiudi']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(chiudiPagamentoPopupButton).click().perform();
    }
}
