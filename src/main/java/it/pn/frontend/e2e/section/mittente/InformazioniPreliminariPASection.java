package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.WebDriverConfig;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;


public class InformazioniPreliminariPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("InformazioniPreliminariPASection");

    @FindBy(id = "paProtocolNumber")
    WebElement numeroProtocolloTextField;

    @FindBy(id = "subject")
    WebElement oggettoNotificaTextField;

    @FindBy(id = "abstract")
    WebElement descrizioneTextField;

    @FindBy(id = "group")
    WebElement gruppoListBox;

    @FindBy(id = "taxonomyCode")
    WebElement codiceTassonometricoTextField;

    @FindBy(xpath = "//input[@value='AR_REGISTERED_LETTER']")
    WebElement raccomandataARButton;

    @FindBy(xpath = "//input[@value='REGISTERED_LETTER_890']")
    WebElement registeredLetter890Button;

    @Autowired
    private WebDriverConfig webDriverConfig;

    private  WebTool webTool;

    public InformazioniPreliminariPASection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }


    public void waitLoadInformazioniPreliminariPASection() {
        try {
            numeroProtocolloTextField = driver.findElement(By.id("paProtocolNumber"));
            oggettoNotificaTextField = driver.findElement(By.id("paProtocolNumber"));
            descrizioneTextField = driver.findElement(By.id("paProtocolNumber"));
            codiceTassonometricoTextField = driver.findElement(By.id("paProtocolNumber"));

            WebElement protocolloNumberBY = driver.findElement(By.id("paProtocolNumber"));
            WebElement informazioniTitle = driver.findElement(By.id("title-heading-section"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(informazioniTitle));
            getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(protocolloNumberBY));

            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(numeroProtocolloTextField));
            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(oggettoNotificaTextField));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(descrizioneTextField));
            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(codiceTassonometricoTextField));

            logger.info("Informazioni preliminari PA Section caricata");
        } catch (TimeoutException e) {
            logger.error("Informazioni preliminari PA Section non caricata. L'elemento NumProtocollo, Ogetto, descrizione o codicetassonometrico non caricato con errore : " + e.getMessage());
            Assertions.fail("Informazioni preliminari PA Section non caricata con errore : " + e.getMessage());
        }
    }

    public void insertNumeroDiProtocollo(String numeroProtocollo) {
        WebElement numeroProtocolloTextBy = driver.findElement(By.id("paProtocolNumber"));
        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(numeroProtocolloTextBy));
        logger.info("inserimento testo in numero protocollo");
        scrollToElementClickAndInsertText(numeroProtocolloTextBy, numeroProtocollo);
    }

    public boolean checkFormInfoPreliminari(){
        oggettoNotificaTextField = driver.findElement(By.id("subject"));
        descrizioneTextField = driver.findElement(By.id("abstract"));

        if (oggettoNotificaTextField.getAttribute("value").isEmpty() && descrizioneTextField.getAttribute("value").isEmpty()) {
         logger.info("Il form di inserimento manuale della notifica è vuoto");
         return true;
        }else {
           logger.info("Il form di inserimento manuale della notifica non è vuoto");
        return false;
        }
    }

    public void insertOggettoNotifica(String oggettoDellaNotifica) {
        logger.info("inserimento oggetto notifica");
        oggettoNotificaTextField = driver.findElement(By.id("subject"));
        scrollToElementClickAndInsertText(oggettoNotificaTextField, oggettoDellaNotifica);
    }

    public void insertDescrizione(String descrizione) {
        logger.info("inserimento descrizione");
        descrizioneTextField = driver.findElement(By.id("abstract"));
        scrollToElementClickAndInsertText(descrizioneTextField, descrizione);
    }

    public void insertGruppo(String gruppo) {
        logger.info("inserimento gruppo");
        gruppoListBox = driver.findElement(By.id("group"));

        if (gruppoListBox.isDisplayed()) {
            gruppoListBox.click();
        } else {
            scrollToElementClickAndInsertText(gruppoListBox, null);
        }
        try {
            WebElement gruppoBy = driver.findElement(By.xpath("//li[contains(text(),'" + gruppo + "')]"));
            getWebDriverWait(40).until(ExpectedConditions.visibilityOf(gruppoBy));
            logger.info("gruppo " + gruppo + " trovato con successo");
            gruppoBy.click();
        } catch (TimeoutException e) {
            logger.info("gruppo " + gruppo + " NON trovato con errore : " + e.getMessage());
            Assertions.fail("gruppo " + gruppo + " NON trovato con errore : " + e.getMessage());
        }
    }

    public void insertCodiceTassonometrico(String codiceTassonometrico) {
        logger.info("inserimento codice tassonometrico");
        codiceTassonometricoTextField = driver.findElement(By.id("taxonomyCode"));
        scrollToElementClickAndInsertText(codiceTassonometricoTextField, codiceTassonometrico);
    }

    public void selectRaccomandataAR() {
        logger.info("selezione raccomandata AR");
        raccomandataARButton = driver.findElement(By.xpath("//input[@value='AR_REGISTERED_LETTER']"));
        raccomandataARButton.click();
    }

    public void selectRegisteredLetter890() {
        logger.info("selezione registered letter 890");
        registeredLetter890Button = driver.findElement(By.xpath("//input[@value='REGISTERED_LETTER_890']"));
        registeredLetter890Button.click();
    }
    public void compilazioneInformazioniPreliminari(Map<String,String> datiNotificaMap) {
        String gruppo = "";
        switch (webDriverConfig.getEnvironment()) {
            case "dev" -> gruppo = datiNotificaMap.get("gruppoDev");
            case "test", "uat" -> gruppo = datiNotificaMap.get("gruppoTest");
        }
        insertNumeroDiProtocollo(WebTool.generatePaProtocolNumber());
        insertOggettoNotifica(datiNotificaMap.get("oggettoDellaNotifica"));
        insertDescrizione(datiNotificaMap.get("descrizione"));
        webTool.waitTime(2);
        insertGruppo(gruppo);
        insertCodiceTassonometrico(datiNotificaMap.get("codiceTassonometrico"));
        if (datiNotificaMap.get("modello").equals("AR")){
            selectRaccomandataAR();
        }else{
            selectRegisteredLetter890();
        }
    }
}
