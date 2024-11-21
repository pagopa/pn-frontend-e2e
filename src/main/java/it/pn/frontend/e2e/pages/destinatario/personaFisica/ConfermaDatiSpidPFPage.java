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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ConfermaDatiSpidPFPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("ConfermaDatiSpidPFPage");


    @FindBy(xpath = "//li[contains(text(),'name')]/strong")
    WebElement nomeUtenteLabel;

    @FindBy(xpath = "//li[contains(text(),'familyName')]/strong")
    WebElement cognomeUtenteLabel;

    @FindBy(xpath = "//li[contains(text(),'fiscalNumber')]/strong")
    WebElement fiscalNumberLabel;

    @FindBy(css = "input[value='Conferma']")
    WebElement confermaButton;

    public ConfermaDatiSpidPFPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadConfermaDatiSpidDEPage(){
        try{
            WebElement spidLogo = driver.findElement(By.id("idp-logo"));
            WebElement alertBox = driver.findElement(By.xpath("//h3[@class='alert-heading']"));
            this.getWebDriverWait(30).withMessage("spid logo non visibile").until(ExpectedConditions.visibilityOf(spidLogo));
            this.getWebDriverWait(30).withMessage("alert box non è visibile").until(ExpectedConditions.visibilityOf(alertBox));
            logger.info("Conferma Dati Spid DE Page caricata");
        }catch (TimeoutException e){
            logger.error("Conferma Dati Spid DE Page non caricata con errore : "+e.getMessage());
            Assertions.fail("Conferma Dati Spid DE Page non caricata con errore : "+e.getMessage());

        }
    }

    public String leggiNomeUtente(){
        nomeUtenteLabel = driver.findElement(By.xpath("//li[contains(text(),'name')]/strong"));
        getWebDriverWait(30).withMessage("nome utente non è visibile").until(ExpectedConditions.visibilityOf(nomeUtenteLabel));
        return nomeUtenteLabel.getText();
    }

    public String leggiCognomeUtente(){
        cognomeUtenteLabel = driver.findElement(By.xpath("//li[contains(text(),'familyName')]/strong"));
        getWebDriverWait(30).withMessage("cognome utente non è visibile").until(ExpectedConditions.visibilityOf(cognomeUtenteLabel));
        return cognomeUtenteLabel.getText();
    }

    public String leggiNumeroFiscale(){
        fiscalNumberLabel = driver.findElement(By.xpath("//li[contains(text(),'fiscalNumber')]/strong"));
        getWebDriverWait(30).withMessage("codice fiscale utente non è visibile").until(ExpectedConditions.visibilityOf(fiscalNumberLabel));
        return fiscalNumberLabel.getText();
    }

    public void selezionaConfermaButton(){
        logger.info("HTML11...."+driver.getPageSource());
        confermaButton = driver.findElement(By.cssSelector("input[value='Conferma']"));
        getWebDriverWait(30).withMessage("conferma dati spid button non è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaButton));
        confermaButton.click();
    }
}
