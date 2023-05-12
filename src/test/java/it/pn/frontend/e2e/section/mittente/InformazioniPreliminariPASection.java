package it.pn.frontend.e2e.section.mittente;

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

public class InformazioniPreliminariPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("InformazioniPreliminariPASection");

    @FindBy(xpath = "//input[@id='paProtocolNumber' and @name='paProtocolNumber']")
    WebElement numeroProtocolloTextField;

    @FindBy(xpath = "//input[@id='subject' and @name='subject']")
    WebElement oggettoNotificaTextField;

    @FindBy(xpath = "//input[@id='abstract' and @name='abstract']")
    WebElement descrizioneTextField;

    @FindBy(xpath = "//div[contains(@id,'group')]")
    WebElement gruppoListBox;

    @FindBy(xpath = "//input[@id='taxonomyCode' and @name='taxonomyCode']")
    WebElement codiceTassonometricoTextField;

    @FindBy(xpath = "//input[@value='AR_REGISTERED_LETTER']")
    WebElement raccomandataARButton;

    public InformazioniPreliminariPASection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadInformazioniPreliminariPASection() {
        try {
            By inofrmazioniTitle = By.xpath("//h3[contains(text(),'Informazioni preliminari')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(inofrmazioniTitle));
            logger.info("Informazioni preliminari PA Section caricata");

        } catch (TimeoutException e) {
            logger.error("Informazioni preliminari PA Section non caricata con errore : " + e.getMessage());
            Assert.fail("Informazioni preliminari PA Section non caricata con errore : " + e.getMessage());
        }
    }

    public void insertNumeroDiProtocollo(String numeroProtocollo) {
        scrollToElementClickAndInsertText(this.numeroProtocolloTextField, numeroProtocollo);
    }

    public void insertOggettoNotifica(String oggettoDellaNotifica) {
        this.scrollToElementClickAndInsertText(this.oggettoNotificaTextField, oggettoDellaNotifica);
    }

    public void insertDescrizione(String descrizione) {
        this.scrollToElementClickAndInsertText(this.descrizioneTextField, descrizione);
    }

    public void insertGruppo(String gruppo) {
        this.gruppoListBox.click();
        try{
            By gruppoBy = By.xpath("//li[contains(text(),'"+gruppo+"')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(gruppoBy));
            logger.info("gruppo "+gruppo+" trovato con successo");
            element(gruppoBy).click();
        }catch (TimeoutException e){
            logger.info("gruppo "+gruppo+" NON trovato con errore : "+e.getMessage());
            Assert.fail("gruppo "+gruppo+" NON trovato con errore : "+e.getMessage());
        }
    }

    public void insertCodiceTassonometrico(String codiceTassonometrico) {
        this.scrollToElementClickAndInsertText(this.codiceTassonometricoTextField, codiceTassonometrico);
    }

    public void selectRaccomandataAR() {

        this.raccomandataARButton.click();

    }
    private void scrollToElementClickAndInsertText(WebElement element, String text){
        if(!element.isDisplayed()){
            this.js().executeScript("arguments[0].scrollIntoView(true);",element);
        }
        element.click();
        if(text != null){
            element.sendKeys(text);
        }
    }
}
