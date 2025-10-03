package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ScegliSpidPGPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(ScegliSpidPGPage.class);

    @FindBy(id = "xx_testenv2")
    WebElement testButton;

    public ScegliSpidPGPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTestButton() {
        WebElement testButton = getWebDriverWait(60)
                .withMessage("Il bottone TEST non è cliccabile nella login")
                .until(ExpectedConditions.elementToBeClickable(By.id("xx_testenv2")));
        testButton.click();
    }

}
