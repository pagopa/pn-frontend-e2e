package it.pn.frontend.e2e.pages.destinatario.personaFisica;

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

public class LoginSpidPFPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("LoginSpidPFPage");

    @FindBy(id = "username")
    WebElement userNameTextField;

    @FindBy(id = "password")
    WebElement pwdTextFiled;

    @FindBy(xpath = "//button[contains(@class,'button-spid')]")
    WebElement entraConSpidButton;


    public LoginSpidPFPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadLoginSpidDEPage(){
        try{
            By spidLogo = By.id("idp-logo");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(spidLogo));
            logger.info("Login Spid DE Page caricata");
        }catch (TimeoutException e){
            logger.error("Login Spid DE Page non caricata con errore : "+e.getMessage());
            Assertions.fail("Login Spid DE Page non caricata con errore : "+e.getMessage());
        }
    }

    public void inserisciUtente(String user){
        getWebDriverWait(30).withMessage("L'input userName non è visibile").until(ExpectedConditions.visibilityOf(userNameTextField));
        this.userNameTextField.sendKeys(user);
    }

    public void inserisciPassword(String pwd){
        getWebDriverWait(30).withMessage("L'input password non è visibile").until(ExpectedConditions.visibilityOf(pwdTextFiled));
        this.pwdTextFiled.sendKeys(pwd);
    }

    public void selezionaEntraConSpidButton(){
        getWebDriverWait(30).withMessage("Il bottone entra con spid non è cliccabile").until(ExpectedConditions.elementToBeClickable(entraConSpidButton));
        this.entraConSpidButton.click();
    }
}
