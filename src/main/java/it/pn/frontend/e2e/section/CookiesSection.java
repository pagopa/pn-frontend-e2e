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
    private static final Logger logger = LoggerFactory.getLogger("CookiesPage");



    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement accettaTuttiButton;

    @FindBy(xpath = "//button[@aria-label='Chiudi']")
    WebElement chiudiPagamentoPopupButton;

    public CookiesSection(WebDriver driver) {
        this.driver = driver;
    }

    public boolean waitLoadCookiesPage() {
        try {
           // WebElement scopriDiPiuLink = driver.findElement(By.id("onetrust-banner-sdk"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-banner-sdk")));
            logger.info("Cookies Page caricata");
            return true;
        } catch (TimeoutException e) {
            logger.warn("Il banner del cookie non è caricato con errore : " + e.getMessage());
            return false;
        }
    }

    public void selezionaAccettaTuttiButton() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("onetrust-accept-btn-handler"))));
            logger.info("Si seleziona accetta tutti i cookies");
            Actions actions = new Actions(driver);
            accettaTuttiButton = driver.findElement(By.id("onetrust-accept-btn-handler"));
            actions.moveToElement(accettaTuttiButton).click().perform();
        } catch (TimeoutException e) {
            logger.error("Non è cliccabile il bottone accetta tutti i cookies" + e.getMessage());
            Assertions.fail("Non è cliccabile il bottone accetta tutti i cookies" + e.getMessage());
        }
    }

    public void chiudiPagamentoPopup() {
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Chiudi']")));
            logger.info("Si seleziona chiudi i cookies");
             chiudiPagamentoPopupButton = driver.findElement(By.xpath("//button[@aria-label='Chiudi']"));

            Actions actions = new Actions(driver);
            actions.moveToElement(chiudiPagamentoPopupButton).click().perform();
    }
}
