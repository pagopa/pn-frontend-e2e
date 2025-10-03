package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginPGPagoPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPGPagoPAPage.class);

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[@name = 'confirm']")
    WebElement inviaButton;

    private WebTool webTool;

    public LoginPGPagoPAPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadLoginPGPage() {
        try {
            WebElement titlePageBy = driver.findElement(By.xpath("//h1[contains(text(),'Login')]"));
            usernameField = driver.findElement(By.id("username"));
            passwordField = driver.findElement(By.id("password"));

            getWebDriverWait(30).withMessage("il titolo della pagina Login PG non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[contains(text(),'Login')]"))));
            getWebDriverWait(30).withMessage("Il campo username della pagina Login PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("username"))));
            getWebDriverWait(30).withMessage("Il campo password della pagina Login PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("password"))));
            getWebDriverWait(35).withMessage("Il bottone invia della pagina Login PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@name = 'confirm']"))));
            logger.info("LoginPGPage caricata correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("LoginPGPage non caricata correttamente con errore :" + e.getMessage());
        }
    }

    public void insertUsername(String user) {
        usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(user);
    }


    public void insertPassword(String pwd) {
        passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(pwd);
    }


    public void clickInviaButton() {
        webTool.waitTime(5);
        inviaButton = driver.findElement(By.xpath("//button[@name = 'confirm']"));
        inviaButton.click();
    }
}
