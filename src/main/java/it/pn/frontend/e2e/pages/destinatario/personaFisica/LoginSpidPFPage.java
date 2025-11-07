package it.pn.frontend.e2e.pages.destinatario.personaFisica;

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


public class LoginSpidPFPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginSpidPFPage.class);


    @FindBy(id = "username")
    WebElement userNameTextField;

    @FindBy(id = "password")
    WebElement pwdTextFiled;

    @FindBy(xpath = "//button[contains(@class,'button-spid')]")
    WebElement entraConSpidButton;

    private WebTool webTool;

    public LoginSpidPFPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadLoginSpidDEPage() {
        try {
//            webTool.waitTime(5);
            //TODO:Andrea
            webTool.waitTime(1);

            getWebDriverWait(40)
                    .withMessage("Il logo SPID non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("idp-logo")));

            logger.info("Login Spid DE Page caricata");
        } catch (TimeoutException e) {
            Assertions.fail("Login Spid waitLoadLoginSpidDEPage con errore : " + e.getMessage());
        }
    }

    public void inserisciUtente(String user) {
        By userNameLocator = By.id("username");

        getWebDriverWait(30).withMessage("L'input userName non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(userNameLocator))
                .sendKeys(user);

        logger.info("Inserito utente: {}", user);
    }

    public void inserisciPassword(String pwd) {
        By passwordLocator = By.id("password");

        getWebDriverWait(30).withMessage("L'input password non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(passwordLocator))
                .sendKeys(pwd);
        logger.info("Inserita password.");
    }

    public void selezionaEntraConSpidButton() {
        By entraConSpidLocator = By.xpath("//button[contains(@class,'button-spid')]");

        WebElement entraConSpidButton = getWebDriverWait(30)
                .withMessage("Il bottone entra con SPID non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(entraConSpidLocator));
        entraConSpidButton.click();
        logger.info("Clic sul bottone 'Entra con SPID' eseguito.");
    }

}
