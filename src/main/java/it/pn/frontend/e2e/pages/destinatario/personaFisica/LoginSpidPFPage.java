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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class LoginSpidPFPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("LoginSpidPFPage");


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

    public void waitLoadLoginSpidDEPage(){
        try{
            webTool.waitTime(5);
//            WebElement spidLogo = driver.findElement(By.id("idp-logo"));
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(spidLogo));

             getWebDriverWait(40)
                    .withMessage("Il logo SPID non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("idp-logo")));

            logger.info("Login Spid DE Page caricata");
        }catch (TimeoutException e){
            Assertions.fail("Login Spid waitLoadLoginSpidDEPage con errore : "+e.getMessage());
        }
    }

    public void inserisciUtente(String user){
        userNameTextField = driver.findElement(By.id("username"));
        getWebDriverWait(30).withMessage("L'input userName non è visibile").until(ExpectedConditions.visibilityOf(userNameTextField));
        userNameTextField.sendKeys(user);
    }

    public void inserisciPassword(String pwd){
        pwdTextFiled = driver.findElement(By.id("password"));
        getWebDriverWait(30).withMessage("L'input password non è visibile").until(ExpectedConditions.visibilityOf(pwdTextFiled));
        pwdTextFiled.sendKeys(pwd);
    }

    public void selezionaEntraConSpidButton(){
        entraConSpidButton = driver.findElement(By.xpath("//button[contains(@class,'button-spid')]"));
        getWebDriverWait(30).withMessage("Il bottone entra con spid non è cliccabile").until(ExpectedConditions.elementToBeClickable(entraConSpidButton));
        entraConSpidButton.click();
    }
}
