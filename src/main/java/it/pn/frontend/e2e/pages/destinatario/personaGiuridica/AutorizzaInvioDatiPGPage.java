package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AutorizzaInvioDatiPGPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(AutorizzaInvioDatiPGPage.class);

    @FindBy(xpath = "//button[@name = 'confirm']")
    WebElement inviaButton;

    private WebTool webTool;

    public AutorizzaInvioDatiPGPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadAutorizzaInvioDatiPGPage() {
        By titlePageBy = By.xpath("//h1[contains(text(),'Autorizzi')]");
        By confirmButtonBy = By.xpath("//button[@name='confirm']");

        getWebDriverWait(30)
                .withMessage("Il titolo della pagina Autorizzi Invio Dati PG non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));

        getWebDriverWait(30)
                .withMessage("Il bottone Invia nella pagina Autorizzi Invio Dati PG non è visibile")
                .until(ExpectedConditions.elementToBeClickable(confirmButtonBy));

        logger.info("AutorizzaInvioDatiPGPage caricata correttamente");
    }

    public void clickInviaButton() {
        webTool.waitTime(5); // opzionale, se serve un piccolo delay

        By inviaButtonBy = By.xpath("//button[@name='confirm']");
        WebElement inviaButton = getWebDriverWait(30)
                .withMessage("Il bottone 'Invia' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(inviaButtonBy));

        inviaButton.click();
    }

}
