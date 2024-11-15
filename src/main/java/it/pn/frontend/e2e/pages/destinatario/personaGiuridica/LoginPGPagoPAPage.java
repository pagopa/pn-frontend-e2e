package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class LoginPGPagoPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("LoginPGPagoPAPage");

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[@name = 'confirm']")
    WebElement inviaButton;

    public LoginPGPagoPAPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadLoginPGPage() {
        try {
            WebElement titlePageBy = driver.findElement(By.xpath("//h1[contains(text(),'Login')]"));
            usernameField = driver.findElement(By.id("username"));
            passwordField = driver.findElement(By.id("password"));
            inviaButton = driver.findElement(By.xpath("//button[@name = 'confirm']"));

            getWebDriverWait(30).withMessage("il titolo della pagina Login PG non è visibile").until(ExpectedConditions.visibilityOf(titlePageBy));
            getWebDriverWait(30).withMessage("Il campo username della pagina Login PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(usernameField));
            getWebDriverWait(30).withMessage("Il campo password della pagina Login PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(passwordField));
            getWebDriverWait(30).withMessage("Il bottone invia della pagina Login PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(inviaButton));
            logger.info("LoginPGPage caricata correttamente");
        } catch (TimeoutException e){
            logger.error("LoginPGPage non caricata correttamente con errore :" +e.getMessage());
            Assertions.fail("LoginPGPage non caricata correttamente con errore :" +e.getMessage());
        }
    }

    public void insertUsername(String user) {
        usernameField.sendKeys(user);
    }


    public void insertPassword(String pwd) {
        passwordField.sendKeys(pwd);
    }


    public void clickInviaButton() {
        inviaButton.click();
    }
}
