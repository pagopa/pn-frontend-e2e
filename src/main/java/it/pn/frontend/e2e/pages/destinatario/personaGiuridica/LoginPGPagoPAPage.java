package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.By;
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
        By titleBy = By.xpath("//h1[contains(text(),'Login')]");
        By usernameBy = By.id("username");
        By passwordBy = By.id("password");
        By submitBy = By.xpath("//button[@name='confirm']");

        getWebDriverWait(30)
                .withMessage("Il titolo della pagina Login PG non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(titleBy));
        usernameField = getWebDriverWait(30)
                .withMessage("Il campo username della pagina Login PG non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(usernameBy));
        passwordField = getWebDriverWait(30)
                .withMessage("Il campo password della pagina Login PG non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(passwordBy));
        getWebDriverWait(35)
                .withMessage("Il bottone invia della pagina Login PG non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(submitBy));
        logger.info("LoginPGPage caricata correttamente");
    }


    public void insertUsername(String user) {
        usernameField = getWebDriverWait(30)
                .withMessage("Il campo username non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("username")));
        usernameField.sendKeys(user);
    }

    public void insertPassword(String pwd) {
        passwordField = getWebDriverWait(30)
                .withMessage("Il campo password non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("password")));
        passwordField.sendKeys(pwd);
    }

    public void clickInviaButton() {
        webTool.waitTime(5); // attesa opzionale
        inviaButton = getWebDriverWait(30)
                .withMessage("Il bottone 'Invia' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='confirm']")));
        inviaButton.click();
    }

}
