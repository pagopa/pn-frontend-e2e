package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


public class SelezionaImpresaPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("SelezionaImpresaPage");

    @FindBy(xpath = "//button[contains(text(),'Accedi')]")
    WebElement accediButton;


    private  WebTool webTool;

    public SelezionaImpresaPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadSelezionaImpresaPage() {
        webTool.waitTime(30);
        accediButton = driver.findElement(By.xpath("//button[contains(text(),'Accedi')]"));
        WebElement titlePageBy = driver.findElement(By.xpath("//h3[contains(text(),'Seleziona la tua impresa')]"));
        getWebDriverWait(30).withMessage("Il titolo della pagina Seleziona la tua impresa non è visibile").until(ExpectedConditions.visibilityOf(titlePageBy));
        getWebDriverWait(30).withMessage("Il bottone accedi della pagina Seleziona la tua impresa non è visibile").until(ExpectedConditions.visibilityOf(accediButton));
        logger.info("Seleziona Impresa Page caricata correttamente");
    }

    public void clickAccediButton() {
        webTool.waitTime(30);
        accediButton = driver.findElement(By.xpath("//button[contains(text(),'Accedi')]"));
        getWebDriverWait(30).withMessage("Il bottone accedi della pagina Seleziona la tua impresa non è cliccabile").until(ExpectedConditions.elementToBeClickable(accediButton));
        logger.info("click su pulsante accedi");
        accediButton.click();
    }

    public boolean clickSuImpresa(String ragioneSociale) {
        //insert try catch for handle element not clickable
        try {
            WebElement impresaBy = driver.findElement((By.xpath("//h6[contains(text(),'" + ragioneSociale + "')]")));
            getWebDriverWait(5).withMessage("l'ente: " + ragioneSociale + " della pagina Seleziona la tua impresa non è visibile").until(ExpectedConditions.elementToBeClickable(impresaBy));
            impresaBy.click();
            logger.info("check su impresa");
        } catch (ElementClickInterceptedException e) {
            logger.info("impresa non cliccabile");
            return false;
        }
        return true;
    }

}
