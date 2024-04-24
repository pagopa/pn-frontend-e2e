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

    public InformazioniPreliminariPASection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadInformazioniPreliminariPASection() {
        try {
            By protocolloNumberBY = By.id("paProtocolNumber");
            By informazioniTitle = By.id("title-heading-section");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(informazioniTitle));
            this.getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(protocolloNumberBY));
            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(this.numeroProtocolloTextField));
            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(this.oggettoNotificaTextField));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.descrizioneTextField));
            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(this.codiceTassonometricoTextField));
            logger.info("Informazioni preliminari PA Section caricata");
        } catch (TimeoutException e) {
            logger.error("Informazioni preliminari PA Section non caricata. L'elemento NumProtocollo, Ogetto, descrizione o codicetassonometrico non caricato con errore : " + e.getMessage());
            Assert.fail("Informazioni preliminari PA Section non caricata con errore : " + e.getMessage());
        }
    }

    public void insertNumeroDiProtocollo(String numeroProtocollo) {
        By numeroProtocolloTextBy = By.id("paProtocolNumber");
        WebElement numeroProtocolloTextInput = driver.findElement(numeroProtocolloTextBy);
        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(numeroProtocolloTextInput));
        logger.info("inserimento testo in numero protocollo");
        scrollToElementClickAndInsertText(numeroProtocolloTextInput, numeroProtocollo);
    }

    public void insertOggettoNotifica(String oggettoDellaNotifica) {
        logger.info("inserimento oggetto notifica");
        this.scrollToElementClickAndInsertText(this.oggettoNotificaTextField, oggettoDellaNotifica);
    }

    public void insertDescrizione(String descrizione) {
        logger.info("inserimento descrizione");
        this.scrollToElementClickAndInsertText(this.descrizioneTextField, descrizione);
    }

    public void insertGruppo(String gruppo) {
        logger.info("inserimento gruppo");
        if (this.gruppoListBox.isDisplayed()) {
            this.gruppoListBox.click();
        } else {
            this.scrollToElementClickAndInsertText(this.gruppoListBox, null);
        }

        try {
            By gruppoBy = By.xpath("//li[contains(text(),'" + gruppo + "')]");
            getWebDriverWait(40).until(ExpectedConditions.visibilityOfElementLocated(gruppoBy));
            logger.info("gruppo " + gruppo + " trovato con successo");
            element(gruppoBy).click();
        } catch (TimeoutException e) {
            logger.info("gruppo " + gruppo + " NON trovato con errore : " + e.getMessage());
            Assert.fail("gruppo " + gruppo + " NON trovato con errore : " + e.getMessage());
        }
    }

    public void insertCodiceTassonometrico(String codiceTassonometrico) {
        logger.info("inserimento codice tassonometrico");
        this.scrollToElementClickAndInsertText(this.codiceTassonometricoTextField, codiceTassonometrico);
    }

    public void selectRaccomandataAR() {
        logger.info("selezione raccomandata AR");
        this.raccomandataARButton.click();
    }

    public void selectRegisteredLetter890() {
        logger.info("selezione registered letter 890");
        this.registeredLetter890Button.click();
    }
}
