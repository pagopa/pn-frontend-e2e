package it.pn.frontend.e2e.section.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PopUpRevocaDelegaSection extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(PopUpRevocaDelegaSection.class);

    @FindBy(id = "confirmation-dialog-delegations")
    WebElement title;

    @FindBy(id = "dialog-action-button")
    WebElement revocaDialogAction;

    private WebTool webTool;

    public PopUpRevocaDelegaSection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadPopUpRevocaDelegaSection() {
        title = getWebDriverWait(10)
                .withMessage("La sezione 'Revoca delega' non è caricata")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation-dialog-delegations")));
        revocaDialogAction = getWebDriverWait(10)
                .withMessage("Il bottone 'Revoca la delega' sul pop-up non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("dialog-action-button")));
    }

    public void clickRevocaLaDelega() {
        revocaDialogAction = getWebDriverWait(10)
                .withMessage("Il bottone 'Revoca la delega' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("dialog-confirm-button")));
        logger.info("Click su 'Revoca la delega'");
        revocaDialogAction.click();
        getWebDriverWait(10)
                .withMessage("Il toast di conferma rimozione delega non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='snackBarContainer']")));
    }


}
