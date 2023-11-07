package it.pn.frontend.e2e.common;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelpdeskPage extends BasePage{

    @FindBy(tagName =  "button")
    WebElement loginButton;

    @FindBy(id = "Email")
    WebElement emailInput;

    @FindBy(id = "Password")
    WebElement passwordInput;

    @FindBy(xpath = "//button[@aria-label='more']")
    WebElement buttonMenu;

    private final Logger logger = LoggerFactory.getLogger("Helpdesk Page");

    public HelpdeskPage(WebDriver driver) {
        super(driver);
    }


    public void changePage(String HelpdeskURL){
        this.driver.get(HelpdeskURL);
    }

    public void checkForm() {
        try{
            logger.info("Check form in corso");
            this.getWebDriverWait(40).withMessage("email non presente").until(ExpectedConditions.visibilityOf(this.emailInput));
            this.getWebDriverWait(40).withMessage("password non presente").until(ExpectedConditions.visibilityOf(this.passwordInput));
            this.getWebDriverWait(40).withMessage("submit non presente").until(ExpectedConditions.visibilityOf(this.loginButton));
        }catch (TimeoutException e){
            logger.error("Form non presente errore: "+e.getMessage());
            Assert.fail("Form non presente errore: "+e.getMessage());
        }
    }

    public void checkHome(){
        try{
            By titlePage = By.xpath("//h5[contains(text(),'Monitoraggio Piattaforma Notifiche')]");
            this.getWebDriverWait(40).withMessage("home non presente").until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            logger.info("pagina home carica");
        }catch(TimeoutException e){
            logger.error("errore caricamento home helpdesk: "+e.getMessage());
            Assert.fail("errore caricamento home helpdesk: "+e.getMessage());
        }
    }

    public void checkMonitoraggio(){
        try{
            TimeUnit.SECONDS.sleep(5);
            this.getWebDriverWait(30).withMessage("elenco monitoraggio non trovato").until(ExpectedConditions.visibilityOf(this.buttonMenu));
            logger.info("pagina monitoraggio caricata");
        }catch (TimeoutException | InterruptedException e){
            logger.error("errore caricamento home monitoraggio: "+e.getMessage());
            Assert.fail("errore caricamento home monitoraggio: "+e.getMessage());
        }
    }

    public void insertUsername(String user) {
        logger.info("inserisco email");
        this.emailInput.sendKeys(user);
    }


    public void insertPassword(String pwd) {
        logger.info("inserisco password");
        this.passwordInput.sendKeys(pwd);
    }


    public void clickInviaButton() {
        logger.info("clicco il bottone login");
        this.loginButton.click();
    }

    public void clickMonitoraggio(){
        try {
            By monitoraggioButton = By.xpath("//h5[contains(text(),'Monitoraggio Piattaforma Notifiche')]");
            logger.info("clicco sulla card monitoraggio piattaforma notifiche");
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(monitoraggioButton));
            this.elements(monitoraggioButton).get(0).click();
        }catch (TimeoutException e ){
            logger.error("Card monitoraggio non cliccabile: "+e.getMessage());
            Assert.fail("Card monitoraggio non cliccabile: "+e.getMessage());
        }
    }

    public void handleDisservizio(String type){
        logger.info("clicco sul menu della tabella");
        this.js().executeScript("arguments[0].click()",buttonMenu);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: "+e.getMessage());
            throw new RuntimeException(e);
        }
        try{
            logger.info("Click su button 'Inserisci X'".replace("X", type));
            By buttonPreCreateDisservizio = By.xpath("//li[contains(text(),'Inserire X')]".replace("X",type));
            this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(buttonPreCreateDisservizio));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                logger.error("pausa con errore: "+e.getMessage());
                throw new RuntimeException(e);
            }
            this.elements(buttonPreCreateDisservizio).get(0).click();
        }catch (TimeoutException e ){
            logger.error(" button 'Inserisci X' non trovato: ".replace("X",type)+e.getMessage());
            Assert.fail("button 'Inserisci X' non trovato: ".replace("X",type)+e.getMessage());
        }
        try{
            logger.info("Click su button 'Inserisci'");
            By buttonCreateDisservizio = By.xpath("//button[contains(text(),'Inserisci')]");
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(buttonCreateDisservizio));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                logger.error("pausa con errore: "+e.getMessage());
                throw new RuntimeException(e);
            }
            this.element(buttonCreateDisservizio).click();
        }catch (TimeoutException e){
            logger.error("button 'Inserisci' non trovato: "+e.getMessage());
            Assert.fail("button 'Inserisci' non trovato: "+e.getMessage());
        }

    }

    public void checkIsCreatedDisservizio(){
        try {

            WebElement dateDisservizio =  this.elements(By.xpath("//div[@data-field='data']")).get(1);
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(dateDisservizio));
            if (dateDisservizio.getText() != null){
                logger.info("disservizio creato correttamente");
            }
        }catch (TimeoutException e){
            logger.error("disservizio non creato: "+e.getMessage());
            Assert.fail("disservizio non creato: "+e.getMessage());
        }
    }

    public void clickSezioneRicerca(){
        try {
            By ricercaButton = By.xpath("//h5[contains(text(),'Ricerca ed estrazione dati')]");
            logger.info("clicco sulla card ricerca ed estrazione dati");
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(ricercaButton));
            this.elements(ricercaButton).get(0).click();
        }catch (TimeoutException e ){
            logger.error("Card ricerca non cliccabile: "+e.getMessage());
            Assert.fail("Card ricerca non cliccabile: "+e.getMessage());
        }
    }

    public void checkRicercaPage(){
        logger.info("check pagina ricerca ed estrazione dati");
        try {
            By selectTypeOfEstrazioneDati= By.id("Tipo Estrazione");
            By numeroTicketInput= By.id("Numero Ticket");
            By codiceFiscaleInput= By.id("Codice Fiscale");
            By buttonCerca= By.xpath("//button[contains(text(),'Ricerca')]");
            By buttonResetFiltri= By.xpath("//button[contains(text(),'Resetta filtri')]");
            this.getWebDriverWait(30).withMessage("Tipo estrazione non trovato").until(ExpectedConditions.visibilityOfElementLocated(selectTypeOfEstrazioneDati));
            this.getWebDriverWait(30).withMessage("numero ticket input non trovato").until(ExpectedConditions.visibilityOfElementLocated(numeroTicketInput));
            this.getWebDriverWait(30).withMessage("codice fiscale input non trovato").until(ExpectedConditions.visibilityOfElementLocated(codiceFiscaleInput));
            this.getWebDriverWait(30).withMessage("button ricerca non trovato").until(ExpectedConditions.visibilityOfElementLocated(buttonCerca));
            this.getWebDriverWait(30).withMessage("button reset filtri non trovato").until(ExpectedConditions.visibilityOfElementLocated(buttonResetFiltri));
        }catch (TimeoutException e){
            logger.error("home ricerca non caricata correttamente: "+e.getMessage());
            Assert.fail("home ricerca non caricata correttamente: "+e.getMessage());
        }


    }

    public void insertCfOnRicercaPage(String codiceFiscale) {
    }
}
