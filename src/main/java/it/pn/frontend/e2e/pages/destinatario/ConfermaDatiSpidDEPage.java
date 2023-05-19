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

public class ConfermaDatiSpidDEPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("ConfermaDatiSpidDEPage");

    @FindBy(xpath = "//li[contains(text(),'name')]/strong")
    WebElement nomeUtenteLabel;

    @FindBy(xpath = "//li[contains(text(),'familyName')]/strong")
    WebElement cognomeUtenteLabel;

    @FindBy(xpath = "//li[contains(text(),'fiscalNumber')]/strong")
    WebElement fiscalNumberLabel;

    @FindBy(xpath = "//input[contains(@value,'Conferma')]")
    WebElement confermaButton;


    public ConfermaDatiSpidDEPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadConfermaDatiSpidDEPage(){
        try{
            By spidLogo = By.cssSelector("div.spid-logo > img");
            By alertBox = By.cssSelector("h3.alert-heading");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(spidLogo));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(alertBox));
            logger.info("Conferma Dati Spid DE Page caricata");
        }catch (TimeoutException e){
            logger.error("Conferma Dati Spid DE Page non caricata con errore : "+e.getMessage());
            Assert.fail("Conferma Dati Spid DE Page non caricata con errore : "+e.getMessage());

        }
    }

    public String leggiNomeUtente(){
        return this.nomeUtenteLabel.getText();
    }

    public String leggiCogomeUtente(){
        return this.cognomeUtenteLabel.getText();
    }

    public String leggiNumeroFiscale(){
        return this.fiscalNumberLabel.getText();
    }

    public void selezionaConfermaButton(){
        this.confermaButton.click();
    }
}
