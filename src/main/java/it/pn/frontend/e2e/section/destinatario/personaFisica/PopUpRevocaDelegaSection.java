package it.pn.frontend.e2e.section.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PopUpRevocaDelegaSection extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("RevocaDelegaSection");

    @FindBy(id = "confirmation-dialog-delegations")
    WebElement title;

    @FindBy(id = "dialog-action-button")
    WebElement revocaDialogAction;

    public PopUpRevocaDelegaSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadPopUpRevocaDelegaSection(){
            this.getWebDriverWait(10).withMessage("la sezione revoca delega non è caricata").until(ExpectedConditions.visibilityOf(this.title));
            this.getWebDriverWait(10).withMessage("Il bottone Revoca la delega sul pop up non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.revocaDialogAction));
    }

    public void clickRevocaLaDelega() {
        this.getWebDriverWait(10).withMessage("Non è possibile cliccare il bottone").until(ExpectedConditions.elementToBeClickable(this.revocaDialogAction));
        logger.info("click revoca delega");
        this.revocaDialogAction.click();
    }

}
