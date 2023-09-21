package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

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

public class LoginPGPagoPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("LoginPGPagoPAPage");

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[@name = 'confirm']")
    WebElement inviaButton;

    public LoginPGPagoPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadLoginPGPage() {
        try {
            By titlePageBy = By.xpath("//h1[contains(text(),'Login')]");

            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.inviaButton));

            logger.info("LoginPGPage caricata correttamente");

        }catch (TimeoutException e){
            logger.error("LoginPGPage non caricata correttamente con errore :" +e.getMessage());
            Assert.fail("LoginPGPage non caricata correttamente con errore :" +e.getMessage());
        }
    }

    public void insertUsername(String user) {
        this.usernameField.sendKeys(user);
    }


    public void insertPassword(String pwd) {
        this.passwordField.sendKeys(pwd);
    }


    public void clickInviaButton() {
        this.inviaButton.click();
    }
}
