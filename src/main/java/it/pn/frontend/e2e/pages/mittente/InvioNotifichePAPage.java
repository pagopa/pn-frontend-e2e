package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InvioNotifichePAPage extends BasePage {
    @FindBy(id = "step-submit")
    WebElement continuaButton;

    public InvioNotifichePAPage(WebDriver driver) {
        super(driver);
    }

    public void selezionareContinuaButton() {
        scrollToElementClickAndInsertText(this.continuaButton, null);
    }
}
