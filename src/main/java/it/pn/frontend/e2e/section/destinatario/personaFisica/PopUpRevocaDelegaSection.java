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
        title = driver.findElement(By.id("confirmation-dialog-delegations"));
        revocaDialogAction = driver.findElement(By.id("dialog-action-button"));
        getWebDriverWait(10).withMessage("la sezione revoca delega non è caricata").until(ExpectedConditions.visibilityOf(title));
        getWebDriverWait(10).withMessage("Il bottone Revoca la delega sul pop up non è cliccabile").until(ExpectedConditions.elementToBeClickable(revocaDialogAction));
    }

    public void clickRevocaLaDelega() {
//        revocaDialogAction = driver.findElement(By.id("dialog-confirm-button"));
//        getWebDriverWait(10).withMessage("Non è possibile cliccare il bottone").until(ExpectedConditions.elementToBeClickable(revocaDialogAction));
//        logger.info("click revoca delega");
//        this.revocaDialogAction.click();
//        getWebDriverWait(10).withMessage("Il toast di conferma rimozione delega non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='snackBarContainer']"))));
        WebElement revocaDialogAction = getWebDriverWait(10)
                .withMessage("Non è possibile cliccare il bottone 'Revoca la delega'")
                .until(ExpectedConditions.elementToBeClickable(By.id("dialog-confirm-button")));

        revocaDialogAction.click();
    }

}
