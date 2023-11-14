package it.pn.frontend.e2e.pages.destinatario.personaFisica;

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
        super(driver);
    }

    public void waitLoadConfermaDatiSpidDEPage(){
        try{
            By spidLogo = By.id("idp-logo");
            By alertBox = By.xpath("//h3[@class='alert-heading']");
            this.getWebDriverWait(30).withMessage("spid logo non visibile").until(ExpectedConditions.visibilityOfElementLocated(spidLogo));
            this.getWebDriverWait(30).withMessage("alert box non è visibile").until(ExpectedConditions.visibilityOfElementLocated(alertBox));
            logger.info("Conferma Dati Spid DE Page caricata");
        }catch (TimeoutException e){
            logger.error("Conferma Dati Spid DE Page non caricata con errore : "+e.getMessage());
            Assert.fail("Conferma Dati Spid DE Page non caricata con errore : "+e.getMessage());

        }
    }

    public String leggiNomeUtente(){
        getWebDriverWait(30).withMessage("nome utente non è visibile").until(ExpectedConditions.visibilityOf(this.nomeUtenteLabel));
        return this.nomeUtenteLabel.getText();
    }

    public String leggiCogomeUtente(){
        getWebDriverWait(30).withMessage("cognome utente non è visibile").until(ExpectedConditions.visibilityOf(this.cognomeUtenteLabel));
        return this.cognomeUtenteLabel.getText();
    }

    public String leggiNumeroFiscale(){
        getWebDriverWait(30).withMessage("codice fiscale utente non è visibile").until(ExpectedConditions.visibilityOf(this.cognomeUtenteLabel));
        return this.fiscalNumberLabel.getText();
    }

    public void selezionaConfermaButton(){
        this.getWebDriverWait(30).withMessage("conferma dati spid button non è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaButton));
        this.confermaButton.click();
    }
}
