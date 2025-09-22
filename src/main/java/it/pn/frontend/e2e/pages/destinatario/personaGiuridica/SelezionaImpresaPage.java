package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.model.webViewMultiLanguage.ButtonLanguage;
import it.pn.frontend.e2e.model.webViewMultiLanguage.WaitLoadSelezionaImpresaLanguage;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SelezionaImpresaPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(SelezionaImpresaPage.class);

    @FindBy(xpath = "//button[contains(text(),'Accedi')]")
    WebElement accediButton;


    private WebTool webTool;

    public SelezionaImpresaPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadSelezionaImpresaPage() {
        webTool.waitTime(1);
        getWebDriverWait(70)
                .withMessage("Il titolo della pagina Seleziona la tua impresa non è visibile")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3[contains(text(),'Le tue imprese su SEND')]")));
        getWebDriverWait(70)
                .withMessage("Il bottone accedi della pagina Seleziona la tua impresa non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Accedi')]")));
        logger.info("Seleziona Impresa Page caricata correttamente");
    }

    public void clickAccediButton() {
        webTool.waitTime(30);
        WebElement accediButton = getWebDriverWait(30)
                .withMessage("Il bottone accedi della pagina Seleziona la tua impresa non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Accedi')]")));
        logger.info("click su pulsante accedi");
        accediButton.click();
    }

    public boolean clickSuImpresa(String ragioneSociale) {
        try {
            logger.info("RAGIONE SOCIALE: {}", ragioneSociale);
            By impresaBy = By.xpath("//h6[contains(text(),'" + ragioneSociale + "')]");
            WebElement impresa = getWebDriverWait(5)
                    .withMessage("l'ente: " + ragioneSociale + " della pagina Seleziona la tua impresa non è visibile")
                    .until(ExpectedConditions.elementToBeClickable(impresaBy));
            js().executeScript("arguments[0].click()", impresa);
            logger.info("check su impresa");
            return true;
        } catch (ElementClickInterceptedException e) {
            logger.info("Impresa non cliccabile");
            return false;
        }
    }

    public void clickAccediButton(String lingua, ButtonLanguage buttonLanguage) {
        String xpath = getAccediButtonXpath(lingua, buttonLanguage);
        By accediButtonBy = By.xpath(xpath);
        WebElement accediButton = getWebDriverWait(30)
                .until(ExpectedConditions.elementToBeClickable(accediButtonBy));
        accediButton.click();
    }


    private String getAccediButtonXpath(String lingua, ButtonLanguage buttonLanguage) {
        switch (lingua.toUpperCase()) { // Converte tutto in maiuscolo
            case "EN":
                return "//button[contains(text(),'" + buttonLanguage.getClickAccediButtonEn() + "')]";
            case "FR":
                return "//button[contains(text(),'" + buttonLanguage.getClickAccediButtonFr() + "')]";
            case "DE":
                return "//button[contains(text(),'" + buttonLanguage.getClickAccediButtonDe() + "')]";
            case "SL":
                return "//button[contains(text(),'" + buttonLanguage.getClickAccediButtonSl() + "')]";
            default:
                return "//button[contains(text(),'" + buttonLanguage.getClickAccediButtonIt() + "')]";
        }
    }

    public void waitLoadSelezionaImpresaPage(String lingua, WaitLoadSelezionaImpresaLanguage waitLoadSelezionaImpresaLanguage) {
        String xpath = getWaitLoadSelezionaImpresaPageXpath(lingua, waitLoadSelezionaImpresaLanguage);
        By impresaPageBy = By.xpath(xpath);
        WebElement impresaPage = getWebDriverWait(30)
                .until(ExpectedConditions.elementToBeClickable(impresaPageBy));
        impresaPage.click();
    }

    private String getWaitLoadSelezionaImpresaPageXpath(String lingua, WaitLoadSelezionaImpresaLanguage waitLoadSelezionaImpresaLanguage) {
        switch (lingua.toUpperCase()) { // Converte tutto in maiuscolo
            case "EN":
                return "//h3[contains(text(),'" + waitLoadSelezionaImpresaLanguage.getWaitLoadSelezionaImpresaPageEn() + "')]";
            case "FR":
                return "//h3[contains(text(),'" + waitLoadSelezionaImpresaLanguage.getWaitLoadSelezionaImpresaPageFr() + "')]";
            case "DE":
                return "//h3[contains(text(),'" + waitLoadSelezionaImpresaLanguage.getWaitLoadSelezionaImpresaPageDe() + "')]";
            case "SL":
                return "//h3[contains(text(),'" + waitLoadSelezionaImpresaLanguage.getWaitLoadSelezionaImpresaPageSl() + "')]";
            default:
                return "//h3[contains(text(),'" + waitLoadSelezionaImpresaLanguage.getWaitLoadSelezionaImpresaPageIt() + "')]";
        }
    }

}
