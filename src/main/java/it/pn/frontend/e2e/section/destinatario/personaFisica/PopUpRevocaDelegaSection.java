package it.pn.frontend.e2e.section.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PopUpRevocaDelegaSection extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("PopUpRevocaDelegaSection");

    @FindBy(xpath="//div/h5[contains(text(),'Vuoi davvero revocare la delega?')]")
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
