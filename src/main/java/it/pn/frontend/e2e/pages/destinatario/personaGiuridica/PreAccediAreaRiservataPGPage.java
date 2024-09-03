package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PreAccediAreaRiservataPGPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("PreAccediAreaRiservataPGPage");

    //ToDo this class is never used

    @FindBy(id = "login-button")
    WebElement accediButton;

    public PreAccediAreaRiservataPGPage(WebDriver driver) {
        super(driver);
    }
}
