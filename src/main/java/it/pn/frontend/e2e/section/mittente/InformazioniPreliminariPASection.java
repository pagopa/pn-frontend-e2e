package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


public class InformazioniPreliminariPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(InformazioniPreliminariPASection.class);

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

    private WebTool webTool;

    public InformazioniPreliminariPASection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }


    //    public void waitLoadInformazioniPreliminariPASection() {
//        try {
//            numeroProtocolloTextField = driver.findElement(By.id("paProtocolNumber"));
//            oggettoNotificaTextField = driver.findElement(By.id("paProtocolNumber"));
//            descrizioneTextField = driver.findElement(By.id("paProtocolNumber"));
//            codiceTassonometricoTextField = driver.findElement(By.id("paProtocolNumber"));
//
//            WebElement protocolloNumberBY = driver.findElement(By.id("paProtocolNumber"));
//            WebElement informazioniTitle = driver.findElement(By.id("title-heading-section"));
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(informazioniTitle));
//            getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(protocolloNumberBY));
//
//            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(numeroProtocolloTextField));
//            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(oggettoNotificaTextField));
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(descrizioneTextField));
//            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(codiceTassonometricoTextField));
//
//            logger.info("Informazioni preliminari PA Section caricata");
//        } catch (TimeoutException e) {
//            Assertions.fail("Informazioni preliminari PA Section non caricata con errore : " + e.getMessage());
//        }
//    }
    public void waitLoadInformazioniPreliminariPASection() {
        By numeroProtocolloBy = By.id("paProtocolNumber");
        By oggettoNotificaBy = By.id("paProtocolNumber"); // probabilmente qui serve un id diverso
        By descrizioneBy = By.id("paProtocolNumber");      // probabilmente qui serve un id diverso
        By codiceTassonometricoBy = By.id("paProtocolNumber"); // probabilmente qui serve un id diverso
        By informazioniTitleBy = By.id("title-heading-section");

        WebElement informazioniTitle = getWebDriverWait(30)
                .withMessage("Titolo sezione Informazioni preliminari PA non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(informazioniTitleBy));

        WebElement protocolloNumber = getWebDriverWait(60)
                .withMessage("Campo numero protocollo non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(numeroProtocolloBy));

        numeroProtocolloTextField = getWebDriverWait(60)
                .until(ExpectedConditions.visibilityOfElementLocated(numeroProtocolloBy));
        oggettoNotificaTextField = getWebDriverWait(60)
                .until(ExpectedConditions.visibilityOfElementLocated(oggettoNotificaBy));
        descrizioneTextField = getWebDriverWait(30)
                .until(ExpectedConditions.visibilityOfElementLocated(descrizioneBy));
        codiceTassonometricoTextField = getWebDriverWait(60)
                .until(ExpectedConditions.visibilityOfElementLocated(codiceTassonometricoBy));

        logger.info("Informazioni preliminari PA Section caricata");
    }


    //    public void insertNumeroDiProtocollo(String numeroProtocollo) {
//        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("paProtocolNumber"))));
//        WebElement numeroProtocolloTextBy = driver.findElement(By.id("paProtocolNumber"));
//        logger.info("inserimento testo in numero protocollo");
//        scrollToElementClickAndInsertText(numeroProtocolloTextBy, numeroProtocollo);
//    }
    public void insertNumeroDiProtocollo(String numeroProtocollo) {
        By numeroProtocolloBy = By.id("paProtocolNumber");

        WebElement numeroProtocolloTextBy = getWebDriverWait(10)
                .withMessage("Campo numero protocollo non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(numeroProtocolloBy));

        logger.info("Inserimento testo in numero protocollo");
        scrollToElementClickAndInsertText(numeroProtocolloTextBy, numeroProtocollo);
    }


    //    public boolean checkFormInfoPreliminari() {
//        oggettoNotificaTextField = driver.findElement(By.id("subject"));
//        descrizioneTextField = driver.findElement(By.id("abstract"));
//        if (oggettoNotificaTextField.getAttribute("value").isEmpty() && descrizioneTextField.getAttribute("value").isEmpty()) {
//            logger.info("Il form di inserimento manuale della notifica è vuoto");
//            return true;
//        } else {
//            logger.info("Il form di inserimento manuale della notifica non è vuoto");
//            return false;
//        }
//    }
    public boolean checkFormInfoPreliminari() {
        By oggettoBy = By.id("subject");
        By descrizioneBy = By.id("abstract");

        WebElement oggettoNotificaTextField = getWebDriverWait(10)
                .withMessage("Campo oggetto notifica non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(oggettoBy));

        WebElement descrizioneTextField = getWebDriverWait(10)
                .withMessage("Campo descrizione notifica non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(descrizioneBy));

        boolean isEmpty = oggettoNotificaTextField.getAttribute("value").isEmpty() &&
                descrizioneTextField.getAttribute("value").isEmpty();

        if (isEmpty) {
            logger.info("Il form di inserimento manuale della notifica è vuoto");
        } else {
            logger.info("Il form di inserimento manuale della notifica non è vuoto");
        }

        return isEmpty;
    }


    //    public void insertOggettoNotificaLinguaStraniera(String oggettoDellaNotifica) {
//        logger.info("inserimento oggetto notifica");
//        oggettoNotificaTextField = driver.findElement(By.id("additionalSubject"));
//        scrollToElementClickAndInsertText(oggettoNotificaTextField, oggettoDellaNotifica);
//    }
    public void insertOggettoNotificaLinguaStraniera(String oggettoDellaNotifica) {
        logger.info("inserimento oggetto notifica");
        By oggettoBy = By.id("additionalSubject");

        WebElement oggettoNotificaTextField = getWebDriverWait(10)
                .withMessage("Campo oggetto notifica in lingua straniera non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(oggettoBy));

        scrollToElementClickAndInsertText(oggettoNotificaTextField, oggettoDellaNotifica);
    }


    //    public void insertDescrizioneLinguaStraniera(String descrizione) {
//        logger.info("inserimento descrizione");
//        descrizioneTextField = driver.findElement(By.id("additionalAbstract"));
//        scrollToElementClickAndInsertText(descrizioneTextField, descrizione);
//    }
    public void insertDescrizioneLinguaStraniera(String descrizione) {
        logger.info("inserimento descrizione");

        By descrizioneBy = By.id("additionalAbstract");
        WebElement descrizioneTextField = getWebDriverWait(10)
                .withMessage("Campo descrizione in lingua straniera non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(descrizioneBy));

        scrollToElementClickAndInsertText(descrizioneTextField, descrizione);
    }


    //    public void insertOggettoNotifica(String oggettoDellaNotifica) {
//        logger.info("inserimento oggetto notifica");
//        oggettoNotificaTextField = driver.findElement(By.id("subject"));
//        scrollToElementClickAndInsertText(oggettoNotificaTextField, oggettoDellaNotifica);
//    }
    public void insertOggettoNotifica(String oggettoDellaNotifica) {
        logger.info("inserimento oggetto notifica");

        By oggettoBy = By.id("subject");
        WebElement oggettoNotificaTextField = getWebDriverWait(10)
                .withMessage("Campo oggetto notifica non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(oggettoBy));

        scrollToElementClickAndInsertText(oggettoNotificaTextField, oggettoDellaNotifica);
    }


    //    public void insertDescrizione(String descrizione) {
//        logger.info("inserimento descrizione");
//        descrizioneTextField = driver.findElement(By.id("abstract"));
//        scrollToElementClickAndInsertText(descrizioneTextField, descrizione);
//    }
    public void insertDescrizione(String descrizione) {
        logger.info("inserimento descrizione");

        By descrizioneBy = By.id("abstract");
        WebElement descrizioneTextField = getWebDriverWait(10)
                .withMessage("Campo descrizione notifica non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(descrizioneBy));

        scrollToElementClickAndInsertText(descrizioneTextField, descrizione);
    }


    //    public void insertGruppo(String gruppo) {
//        logger.info("inserimento gruppo");
//        gruppoListBox = driver.findElement(By.id("group"));
//
//        if (gruppoListBox.isDisplayed()) {
//            gruppoListBox.click();
//        } else {
//            scrollToElementClickAndInsertText(gruppoListBox, null);
//        }
//        try {
//            WebElement gruppoBy = driver.findElement(By.xpath("//li[contains(text(),'" + gruppo + "')]"));
//            getWebDriverWait(40).until(ExpectedConditions.visibilityOf(gruppoBy));
//            logger.info("gruppo " + gruppo + " trovato con successo");
//            gruppoBy.click();
//        } catch (TimeoutException e) {
//            Assertions.fail("gruppo " + gruppo + " NON trovato con errore : " + e.getMessage());
//        }
//    }
    public void insertGruppo(String gruppo) {
        logger.info("inserimento gruppo");

        By gruppoByLocator = By.id("group");
        WebElement gruppoListBox = getWebDriverWait(10)
                .withMessage("Listbox gruppo non visibile")
                .until(ExpectedConditions.elementToBeClickable(gruppoByLocator));

        gruppoListBox.click();

        By optionByLocator = By.xpath("//li[contains(text(),'" + gruppo + "')]");
        WebElement gruppoOption = getWebDriverWait(40)
                .withMessage("Opzione gruppo '" + gruppo + "' non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(optionByLocator));

        logger.info("Gruppo '{}' trovato con successo", gruppo);
        gruppoOption.click();
    }


    //    public void insertCodiceTassonometrico(String codiceTassonometrico) {
//        logger.info("inserimento codice tassonometrico");
//        codiceTassonometricoTextField = driver.findElement(By.id("taxonomyCode"));
//        scrollToElementClickAndInsertText(codiceTassonometricoTextField, codiceTassonometrico);
//    }
    public void insertCodiceTassonometrico(String codiceTassonometrico) {
        logger.info("inserimento codice tassonometrico");

        By codiceTassonometricoLocator = By.id("taxonomyCode");
        WebElement codiceTassonometricoTextField = getWebDriverWait(10)
                .withMessage("Campo codice tassonometrico non visibile")
                .until(ExpectedConditions.elementToBeClickable(codiceTassonometricoLocator));

        scrollToElementClickAndInsertText(codiceTassonometricoTextField, codiceTassonometrico);
    }


    //    public void selectRaccomandataAR() {
//        logger.info("selezione raccomandata AR");
//        raccomandataARButton = driver.findElement(By.xpath("//input[@value='AR_REGISTERED_LETTER']"));
//        raccomandataARButton.click();
//    }
    public void selectRaccomandataAR() {
        logger.info("selezione raccomandata AR");

        By raccomandataARLocator = By.xpath("//input[@value='AR_REGISTERED_LETTER']");
        WebElement raccomandataARButton = getWebDriverWait(10)
                .withMessage("Il radio button Raccomandata AR non è cliccabile")
                .until(ExpectedConditions.presenceOfElementLocated(raccomandataARLocator));

        raccomandataARButton.click();
    }


    //    public void selectRegisteredLetter890() {
//        logger.info("selezione registered letter 890");
//        registeredLetter890Button = driver.findElement(By.xpath("//input[@value='REGISTERED_LETTER_890']"));
//        registeredLetter890Button.click();
//    }
    public void selectRegisteredLetter890() {
        logger.info("selezione registered letter 890");

        By registeredLetter890Locator = By.xpath("//input[@value='REGISTERED_LETTER_890']");
        WebElement registeredLetter890Button = getWebDriverWait(10)
                .withMessage("Il radio button Registered Letter 890 non è cliccabile")
                .until(ExpectedConditions.presenceOfElementLocated(registeredLetter890Locator));

        registeredLetter890Button.click();
    }


    public void compilazioneInformazioniPreliminari(Map<String, String> datiNotificaMap) {
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
        if (datiNotificaMap.get("modello").equals("AR")) {
            selectRaccomandataAR();
        } else {
            selectRegisteredLetter890();
        }
    }
}
