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

    private WebTool webTool;

    public ConfermaDatiSpidPFPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadConfermaDatiSpidDEPage(){
        try{
            webTool.waitTime(5);
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
       // webTool.waitTime(30);
        nomeUtenteLabel = driver.findElement(By.xpath("//li[contains(text(),'name')]/strong"));
        getWebDriverWait(30).withMessage("nome utente non è visibile").until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[contains(text(),'name')]/strong")));
        return nomeUtenteLabel.getText();
    }

    public String leggiCognomeUtente(){
        //webTool.waitTime(40);
       // cognomeUtenteLabel = driver.findElement(By.xpath("//li[contains(text(),'familyName')]/strong"));
        getWebDriverWait(30).withMessage("cognome utente non è visibile").until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[contains(text(),'familyName')]/strong")));
        return element(By.xpath("//li[contains(text(),'familyName')]/strong")).getText();
    }

    public String leggiNumeroFiscale(){
       // webTool.waitTime(40);
        //fiscalNumberLabel = driver.findElement(By.xpath("//li[contains(text(),'fiscalNumber')]/strong"));
        getWebDriverWait(30).withMessage("codice fiscale utente non è visibile").until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[contains(text(),'fiscalNumber')]/strong")));
        return element(By.xpath("//li[contains(text(),'fiscalNumber')]/strong")).getText();
    }

    public void selezionaConfermaButton(){
       // confermaButton = driver.findElement(By.cssSelector("input[value='Conferma']"));
        getWebDriverWait(30).withMessage("conferma dati spid button non è cliccabile").until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Conferma']")));
        element(By.cssSelector("input[value='Conferma']")).click();
    }
}
