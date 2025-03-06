package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.model.webViewMultiLanguage.WaitLoadAccediAreaRiservataPgLanguage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;


public class AccediAreaRiservataPGPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("AccediAreaRiservataPGPage");

    @FindBy(id = "spidButton")
    WebElement spidButton;

    private WebTool webTool;

    public AccediAreaRiservataPGPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }


    public void waitLoadAccediAreaRiservataPGPage() {
        try {
            webTool.waitTime(30);
            spidButton = driver.findElement(By.id("spidButton"));
            By titleBy = By.xpath("//h3[contains(text(),'Come vuoi accedere?')]");
            getWebDriverWait(30).withMessage("il titolo della pagina Accedi Area Riservata non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleBy));
            getWebDriverWait(30).withMessage("Lo spid Button della pagina Accedi Area Riservata non è visibile").until(ExpectedConditions.elementToBeClickable(spidButton));
            logger.info("Accedi Area Riservata Page caricata correttamente");
        } catch (TimeoutException e ){
            Assertions.fail(MessageFormat.format("Accedi Area Riservata Page non caricata correttamente con errore: {0}", e.getMessage()));
        }

    }

    public void clickSpidButton() {
        spidButton = driver.findElement(By.id("spidButton"));
        spidButton.click();
    }

    public void waitLoadAccediAreaRiservataPGPage(String lingua, WaitLoadAccediAreaRiservataPgLanguage waitLoadAccediAreaRiservataPgLanguage) {
        String xpath = getwaitLoadAccediAreaRiservataPGPage(lingua, waitLoadAccediAreaRiservataPgLanguage);
        getWebDriverWait(30).
                until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath)))).
                click();
    }

    private String getwaitLoadAccediAreaRiservataPGPage(String lingua, WaitLoadAccediAreaRiservataPgLanguage waitLoadAccediAreaRiservataPgLanguage) {
        switch (lingua.toUpperCase()) { // Converte tutto in maiuscolo
            case "EN":
                return "//h3[contains(text(),'" + waitLoadAccediAreaRiservataPgLanguage.getWaitLoadAccediAreaRiservataPGPageEn() + "')]";
            case "FR":
                return "//h3[contains(text(),'" + waitLoadAccediAreaRiservataPgLanguage.getWaitLoadAccediAreaRiservataPGPageFr() + "')]";
            case "DE":
                return"//h3[contains(text(),'" + waitLoadAccediAreaRiservataPgLanguage.getWaitLoadAccediAreaRiservataPGPageDe() + "')]";
            case "SL":
                return"//h3[contains(text(),'" + waitLoadAccediAreaRiservataPgLanguage.getWaitLoadAccediAreaRiservataPGPageSl() + "')]";
            default:
                return"//h3[contains(text(),'" + waitLoadAccediAreaRiservataPgLanguage.getWaitLoadAccediAreaRiservataPGPageIt() + "')]";
        }
    }

    public void clickScegliAmbienteSendBottonePersonaGiuridica(String ambiente) {
        WebElement forwardButton = getWebDriverWait(10)
                .withMessage("Il bottone per l Ambiente  '"+ambiente+"' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//h6[contains(@aria-label, '"+ambiente+"')]/ancestor::div[contains(@class, 'MuiCard-root')]//button")));
                                                                                                    //div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button
        forwardButton.click();
    }
}
