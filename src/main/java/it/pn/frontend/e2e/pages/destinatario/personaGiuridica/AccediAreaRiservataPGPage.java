package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.model.webViewMultiLanguage.WaitLoadAccediAreaRiservataPgLanguage;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccediAreaRiservataPGPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(AccediAreaRiservataPGPage.class);

    @FindBy(id = "spidButton")
    WebElement spidButton;

    private WebTool webTool;

    public AccediAreaRiservataPGPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }


    //    public void waitLoadAccediAreaRiservataPGPage() {
//        try {
//            webTool.waitTime(30);
//            By titleBy = By.xpath("//h3[contains(text(),'Come vuoi accedere?')]");
//            getWebDriverWait(30).withMessage("il titolo della pagina Accedi Area Riservata non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleBy));
//            getWebDriverWait(30).withMessage("Lo spid Button della pagina Accedi Area Riservata non è visibile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("spidButton"))));
//            logger.info("Accedi Area Riservata Page caricata correttamente");
//        } catch (TimeoutException e) {
//            Assertions.fail("Accedi Area Riservata Page non caricata correttamente con errore: " + e.getMessage());
//        }
//
//    }
    public void waitLoadAccediAreaRiservataPGPage() {
        webTool.waitTime(30);

        By titleBy = By.xpath("//h3[contains(text(),'Come vuoi accedere?')]");
        By spidButtonBy = By.id("spidButton");

        getWebDriverWait(30)
                .withMessage("Il titolo della pagina Accedi Area Riservata non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(titleBy));

        getWebDriverWait(30)
                .withMessage("Lo SPID Button della pagina Accedi Area Riservata non è visibile")
                .until(ExpectedConditions.elementToBeClickable(spidButtonBy));

        logger.info("Accedi Area Riservata Page caricata correttamente");
    }


    //    public void clickSpidButton() {
//        spidButton = getWebDriverWait(30).withMessage("Impossibile premere il tasto spid Button della pagina Accedi Area Riservata non è visibile")
//                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("spidButton"))));
//        spidButton.click();
//    }
    public void clickSpidButton() {
        By spidButtonBy = By.id("spidButton");

        WebElement spidButton = getWebDriverWait(30)
                .withMessage("Impossibile premere il tasto SPID Button: non visibile o non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(spidButtonBy));

        spidButton.click();
    }


    //    public void waitLoadAccediAreaRiservataPGPage(String lingua, WaitLoadAccediAreaRiservataPgLanguage waitLoadAccediAreaRiservataPgLanguage) {
//        String xpath = getwaitLoadAccediAreaRiservataPGPage(lingua, waitLoadAccediAreaRiservataPgLanguage);
//        getWebDriverWait(30).
//                until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath)))).
//                click();
//    }
    public void waitLoadAccediAreaRiservataPGPage(String lingua, WaitLoadAccediAreaRiservataPgLanguage waitLoadAccediAreaRiservataPgLanguage) {
        String xpath = getwaitLoadAccediAreaRiservataPGPage(lingua, waitLoadAccediAreaRiservataPgLanguage);
        By elementoBy = By.xpath(xpath);

        WebElement elemento = getWebDriverWait(30)
                .withMessage("Elemento non cliccabile nella pagina Accedi Area Riservata")
                .until(ExpectedConditions.elementToBeClickable(elementoBy));

        elemento.click();
    }


    private String getwaitLoadAccediAreaRiservataPGPage(String lingua, WaitLoadAccediAreaRiservataPgLanguage waitLoadAccediAreaRiservataPgLanguage) {
        switch (lingua.toUpperCase()) { // Converte tutto in maiuscolo
            case "EN":
                return "//h3[contains(text(),'" + waitLoadAccediAreaRiservataPgLanguage.getWaitLoadAccediAreaRiservataPGPageEn() + "')]";
            case "FR":
                return "//h3[contains(text(),'" + waitLoadAccediAreaRiservataPgLanguage.getWaitLoadAccediAreaRiservataPGPageFr() + "')]";
            case "DE":
                return "//h3[contains(text(),'" + waitLoadAccediAreaRiservataPgLanguage.getWaitLoadAccediAreaRiservataPGPageDe() + "')]";
            case "SL":
                return "//h3[contains(text(),'" + waitLoadAccediAreaRiservataPgLanguage.getWaitLoadAccediAreaRiservataPGPageSl() + "')]";
            default:
                return "//h3[contains(text(),'" + waitLoadAccediAreaRiservataPgLanguage.getWaitLoadAccediAreaRiservataPGPageIt() + "')]";
        }
    }

    public void clickScegliAmbienteSendBottonePersonaGiuridica(String ambiente) {
        WebElement forwardButton = getWebDriverWait(20)
                .withMessage("Il bottone per l Ambiente '" + ambiente + "' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//h6[contains(@aria-label, '" + ambiente + "')]/ancestor::div[contains(@class, 'MuiCard-root')]//button")));
        forwardButton.click();
        webTool.waitTime(2);
    }

    public void clickScegliAmbienteSendBottonePersonaGiuridicaUAT() {
        WebElement forwardButton = getWebDriverWait(20)
                .withMessage("Il bottone per l Ambiente 'UAT' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//h6[@aria-label='SEND - Notifiche Digitali UAT']/ancestor::div[contains(@class, 'MuiCard-root')]//button")));
        forwardButton.click();
        webTool.waitTime(2);
    }
}
