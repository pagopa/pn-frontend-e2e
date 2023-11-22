package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InvioNotifichePAPage extends BasePage {
    @FindBy(id = "step-submit")
    WebElement continuaButton;

    public InvioNotifichePAPage(WebDriver driver) {
        super(driver);
    }

    public void selezionareContinuaButton() {
        getWebDriverWait(30).withMessage("Il bottone continua nella pagina invio notifica PA non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.continuaButton));
        scrollToElementClickAndInsertText(this.continuaButton, null);
    }
}
