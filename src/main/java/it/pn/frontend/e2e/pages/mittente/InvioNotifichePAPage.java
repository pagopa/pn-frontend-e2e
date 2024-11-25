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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


public class InvioNotifichePAPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("InvioNotifichePAPage");
    @FindBy(id = "step-submit")
    WebElement continuaButton;


    private  WebTool webTool;

    public InvioNotifichePAPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void selezionareContinuaButton() {
        webTool.waitTime(10);
        continuaButton = driver.findElement(By.id("step-submit"));
        getWebDriverWait(10).withMessage("Il bottone continua nella pagina invio notifica PA non è cliccabile").until(ExpectedConditions.elementToBeClickable(continuaButton));
        scrollToElementAndClick(continuaButton);
    }
}
