package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InvioNotifichePAPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(InvioNotifichePAPage.class);
    @FindBy(id = "step-submit")
    WebElement continuaButton;

    private WebTool webTool;

    public InvioNotifichePAPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void selezionareContinuaButton() {
        webTool.waitTime(5);
        // Attendi che il bottone "Continua" sia cliccabile
        WebElement selezionareContinuaButton = getWebDriverWait(10)
                .withMessage("Il bottone continua nella pagina invio notifica PA non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("step-submit")));
        // Scorri fino all'elemento e fai clic
        scrollToElementAndClick(selezionareContinuaButton);

    }

    public void selezionareTornaAButton() {
        // Aspetto che il bottone sia cliccabile e lo trovo direttamente
        WebElement indietroButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Torna a' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("previous-step")));

        logger.info("Clicco sul bottone 'Torna a'");
        scrollToElementAndClick(indietroButton);
    }

}
