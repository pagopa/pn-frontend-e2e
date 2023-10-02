package it.pn.frontend.e2e.section.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PopUpRevocaDelegaSection extends BasePage {
    @FindBy(id = "confirmation-dialog-delegations")
    WebElement title;

    @FindBy(xpath = "//button[contains(text(),'Revoca la delega')]")
    WebElement revocaDialogAction;

    public PopUpRevocaDelegaSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadPopUpRevocaDelegaSection(){
            this.getWebDriverWait(30).withMessage("la sezione revoca delega non è caricata").until(ExpectedConditions.visibilityOf(this.title));
    }

    public void clickRevocaLaDelega() {
        this.revocaDialogAction.click();
    }

}
