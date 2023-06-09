package it.pn.frontend.e2e.pages.destinatario;

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

public class AccediApiattaformaNotifichePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AccediApiattaformaNotifichePage");


    @FindBy(id = "login-button")
    //@FindBy(xpath = "//button[contains(@id,'login-button')]")
    WebElement accediButton;

    public AccediApiattaformaNotifichePage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAccediApiattaformaNotifichePage(){
        try{
            //By titleLabel = By.xpath("//h4[contains(@id,'login-page-title')]");
            By titleLabel = By.id("login-page-title");
            By loginBy = By.id("login-button");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(loginBy));
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(loginBy));
            logger.info("Accedi A Piattaforma Notifiche Page caricata");
        }catch (TimeoutException e){
            logger.info("Accedi A Piattaforma Notifiche Page non caricata con errore : "+e.getMessage());
            Assert.fail("Accedi A Piattaforma Notifiche Page non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionaAccediButton(){
        this.js().executeScript("arguments[0].click()",this.accediButton);
        //this.accediButton.click();
    }
}
