package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccediAPiattaformaNotifichePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AccediAPiattaformaNotifichePage");

    @FindBy(id = "spidButton")
    WebElement accediButton;

    @FindBy(css = "[id='notificationsTable.body.row']")
    WebElement notificheButton;

    @FindBy(id = "side-item-Notifiche")
    WebElement notificheMenuButton;

    @FindBy(id = "side-item-Recapiti")
    WebElement recapitiButton;

    @FindBy(id = "side-item-Stato della piattaforma")
    WebElement buttonEnterIntoDisservizi;

    @FindBy(css = "[data-testid='download-legalfact']")
    WebElement attestazione;

    @FindBy(id = "breadcrumb-indietro-button")
    WebElement indietroButton;

    @FindBy(css = "[class='MuiTableBody-root css-1xnox0e']")
    WebElement sezioneDeiDati;

    @FindBy(css = "[data-testid='paymentInfoBox']")
    WebElement sezionePagamento;

    @FindBy(css = "[role='radiogroup']")
    WebElement radioBox;

    @FindBy(css = "[data-testid='download-f24-button']")
    WebElement modelloF24;

    @FindBy(css = "[data-testid='download-pagoPA-notice-button']")
    WebElement scaricaAvviso;

    @FindBy(css = "[data-testid='pay-button']")
    WebElement pagaAvviso;

    @FindBy(xpath = "//span[contains(text(),'Codice avviso')]")
    WebElement codiceAvviso;
    @FindBy(xpath = "//span[contains(text(),'Scade il')]")
    WebElement scadenzaAvviso;
    @FindBy(css = ".MuiTypography-caption-semibold.css-1g3z0lx")
    WebElement codiceAvvisoSpan;
    @FindBy(css = "[data-testid='payment-amount']")
    WebElement paymentAmount;
    @FindBy(css = "[data-testid='apply-costs-caption']")
    WebElement costiNotifica;

    @FindBy(xpath = "/html/body/div/div/div/div/input")
    WebElement creditCardNumber;

    @FindBy(xpath = "//td[@id='row-value-4']")
    WebElement iunCode;

    @FindBy(xpath = "//div[@data-testid='pagopa-item']")
    List<WebElement> pagopaItems;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/div/main/div/div/div[1]/div[3]/div[4]/div/button[2]")
    List<WebElement> pagopaAllegatoItems;



    public AccediAPiattaformaNotifichePage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAccediAPiattaformaNotifichePage() {

            By titleLabel = By.id("login-mode-page-title");
            By loginBy = By.id("spidButton");
            getWebDriverWait(30).withMessage("Il titolo della pagina accedi a piattaforma notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            getWebDriverWait(30).withMessage("Il bottone login della pagina accedi a piattaforma notifiche non è visibile e cliccabile").until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(loginBy),ExpectedConditions.elementToBeClickable(loginBy)));
            logger.info("Accedi A Piattaforma Notifiche Page caricata");

    }

    public void selezionaAccediButton() {
        js().executeScript("arguments[0].click()", accediButton);
    }


    public void clickRecapitiButton() {
        getWebDriverWait(30).withMessage("Il bottone recapiti non è visibile").until(ExpectedConditions.visibilityOf(recapitiButton));
        js().executeScript("arguments[0].click()", recapitiButton);
    }

    public void clickNotificheButton() {
        getWebDriverWait(30).withMessage("Il bottone notifiche non è visibile").until(ExpectedConditions.visibilityOf(notificheButton));
        notificheButton.click();
    }

    public void clickAttestazionePersalvare() {
        getWebDriverWait(30).withMessage("L'attestazione non è visibile").until(ExpectedConditions.visibilityOf(attestazione));
        js().executeScript("arguments[0].click()", attestazione);
    }

    public boolean isBackButtonDisplayed() {
        return getWebDriverWait(30).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton)).isDisplayed();
    }

    public void clickIndietroButton() {
        getWebDriverWait(30).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton));
        js().executeScript("arguments[0].click()", indietroButton);
    }

    public boolean sezioneDeiDatiDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione dei dati non è visibile").until(ExpectedConditions.visibilityOf(sezioneDeiDati)).isDisplayed();
    }

    public boolean sezionePagamentoDisplayed() {
        try {
            return getWebDriverWait(30).withMessage("Il sezione pagamento non è visibile").until(ExpectedConditions.visibilityOf(sezionePagamento)).isDisplayed();

        } catch (NoSuchElementException | TimeoutException e) {

            logger.warn("Il sezione pagamento non è visibile");
            return false;
        }
    }


    public boolean isRadioBoxPresent() {
        try {
            return getWebDriverWait(30).withMessage("Il radio box non è visibile").until(ExpectedConditions.visibilityOf(radioBox)).isDisplayed();

        } catch (NoSuchElementException | TimeoutException e) {

            logger.warn("Il radio box non è visibile");
            return false;
        }
    }

    public void clickRadioBoxButton() {
        WebElement radioButton = driver.findElements(By.xpath("//span[@data-testid='radio-button']")).get(0);
        getWebDriverWait(30).withMessage("Il radio box button non è cliccabile").until(ExpectedConditions.elementToBeClickable(radioButton));
        radioButton.click();
    }

    public void clickStatoDellaPiattaforma() {
        getWebDriverWait(10).withMessage("Il bottone stato della piattaforma non è visibile").until(ExpectedConditions.visibilityOf(buttonEnterIntoDisservizi));
        buttonEnterIntoDisservizi.click();
    }

    public void clickNotifiche() {
        getWebDriverWait(10).withMessage("Il bottone stato della piattaforma non è visibile").until(ExpectedConditions.visibilityOf(buttonEnterIntoDisservizi));
        notificheMenuButton.click();
    }

    public boolean titoloDiPagamentoDisplayed() {
        By titoloPagamento = By.xpath("//span[contains(text(),'Pagamento di Test')]");
        return getWebDriverWait(30).withMessage("Il sezione titolo pagamento non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titoloPagamento)).isDisplayed();
    }

    public boolean codiceAvvisoDisplayed() {
        try {
            getWebDriverWait(5).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(codiceAvviso)).isDisplayed();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean modelloF24Displayed() {
        return getWebDriverWait(5).withMessage("Il sezione scarica modello F24 non è visibile").until(ExpectedConditions.visibilityOf(modelloF24)).isDisplayed();
    }

    public boolean scaricaAvvisoDisplayed() {
        try {
            getWebDriverWait(5).withMessage("Il sezione scarica avviso non è visibile").until(ExpectedConditions.visibilityOf(scaricaAvviso)).isDisplayed();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    //TODO Verificare....
    public boolean piuAvvisiDisplayed() {
        if (pagopaItems.isEmpty()){
            logger.info("Avvisi pagoPa sono trovati");
            return true;
        }
        getWebDriverWait(3).withMessage("Avvisi pagoPa non sono trovati").until(ExpectedConditions.visibilityOf(pagopaItems.get(0))).isDisplayed();
        if (pagopaItems.size() < 2) {
            logger.info("Avvisi pagoPa non sono trovati");
            return false;
        }else {
            logger.info("Avvisi pagoPa sono trovati");
            return true;
        }
    }

    public boolean allegatoPagoPaDisplayed() {
        if (pagopaAllegatoItems.isEmpty()) {
            logger.info("Allegati pagoPa non sono trovati");
            return true;
        }else {
            return false;
        }
    }

    public void clickAvvisoPagoPADestinatario() {
        getWebDriverWait(30).withMessage("Il sezione scarica avviso non è cliccabile").until(ExpectedConditions.elementToBeClickable(scaricaAvviso));
        scaricaAvviso.click();
    }

    public boolean pagaAvvisoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione paga avviso non è visibile").until(ExpectedConditions.visibilityOf(pagaAvviso)).isDisplayed();
    }

    public void checkButtonPagaIsDisplayed() {
        getWebDriverWait(10).withMessage("Il bottone per il pagamento della notifica è visibile").until(ExpectedConditions.invisibilityOf(pagaAvviso));
    }

    public void siVisualizzaSezionePagamento() {
        getWebDriverWait(10).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(codiceAvviso));
        getWebDriverWait(10).withMessage("Il sezione data di scadenza avviso non è visibile").until(ExpectedConditions.visibilityOf(scadenzaAvviso));
        getWebDriverWait(10).withMessage("Il sezione importo di avviso non è visibile").until(ExpectedConditions.visibilityOf(paymentAmount));
        getWebDriverWait(10).withMessage("Il sezione scarica avviso non è visibile").until(ExpectedConditions.visibilityOf(scaricaAvviso));
        logger.info("Si visualizza correttamente il sezione pagamento di notifica");
    }

    public boolean siControllaCostiDiNotifica() {
        try {
            getWebDriverWait(10).withMessage("Costi di notifica inclusi").until(ExpectedConditions.visibilityOf(costiNotifica));
            return true;
        } catch (RuntimeException e) {
            logger.info("Costi di notifica non inclusi");
            return false;
        }
    }

    public void cliccaPaga() {
        logger.info("Si clicca su bottone paga");
        WebTool.waitTime(5);
        pagaAvviso = driver.findElement(By.cssSelector("[data-testid='pay-button']"));
        pagaAvviso.click();
    }

    public void inserireDatiPagamento(String email) {
        By emailPagamento = By.id("email");
        By confermaEmailPagamento = By.id("confirmEmail");
        By continuaPagamento = By.id("paymentEmailPageButtonContinue");
        element(emailPagamento).sendKeys(email);
        element(confermaEmailPagamento).sendKeys(email);
        element(continuaPagamento).click();
    }

    public void checkoutPagamento() throws InterruptedException {
        logger.info("Si procede con il pagamento");
        WebTool.waitTime(5);
        element(By.cssSelector("[data-qaid='CP']")).click();
        WebTool.waitTime(5);
        // frame of the card number
        WebElement iframeCardNumber = driver.findElement(By.xpath("//iframe[@id='frame_CARD_NUMBER']"));
        driver.switchTo().frame(iframeCardNumber);
        WebTool.waitTime(5);
        getWebDriverWait(10).withMessage("Il textbox numero di carta non è visibile").until(ExpectedConditions.visibilityOf(creditCardNumber));
        creditCardNumber.click();
        creditCardNumber.clear();
        creditCardNumber.sendKeys("5127390031101597");
        driver.switchTo().defaultContent();

        //frame of the expiry date
        WebElement iframeExpiry = driver.findElement(By.xpath("//iframe[@id='frame_EXPIRATION_DATE']"));
        driver.switchTo().frame(iframeExpiry);
        By scadenza = By.xpath("//input[@id='EXPIRATION_DATE']");
        getWebDriverWait(20).withMessage("Il textbox scadenza non è visibile").until(ExpectedConditions.visibilityOfElementLocated(scadenza));
        element(scadenza).click();
        element(scadenza).clear();
        element(scadenza).sendKeys("10/24");
        driver.switchTo().defaultContent();

        //frame of the security code
        WebElement iframeSecurityCode = driver.findElement(By.xpath("//iframe[@id='frame_SECURITY_CODE']"));
        driver.switchTo().frame(iframeSecurityCode);
        By codice = By.xpath("//input[@id='SECURITY_CODE']");
        getWebDriverWait(10).withMessage("Il textbox codice di sicurezza non è visibile").until(ExpectedConditions.visibilityOfElementLocated(codice));
        element(codice).click();
        element(codice).clear();
        element(codice).sendKeys("015");
        driver.switchTo().defaultContent();

        //frame of the cardholder name
        WebElement iframeTitolare = driver.findElement(By.xpath("//iframe[@id='frame_CARDHOLDER_NAME']"));
        driver.switchTo().frame(iframeTitolare);
        By titolare = By.xpath("//input[@id='CARDHOLDER_NAME']");
        getWebDriverWait(10).withMessage("Il textbox titolare non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titolare));
        element(titolare).click();
        element(titolare).clear();
        element(titolare).sendKeys("Titolare");
        driver.switchTo().defaultContent();

        WebTool.waitTime(5);
        WebElement continuaBottone = driver.findElement(By.xpath("//button[@aria-label='Continue']") );
        getWebDriverWait(8).withMessage("Il bottone Continua non è cliccabile").until(ExpectedConditions.elementToBeClickable(continuaBottone));
        continuaBottone.click();

        WebTool.waitTime(2);

        logger.info("HTML.."+ driver.getPageSource());
        //Select Nexi
        WebElement modificaButton = driver.findElement(By.xpath("//button[@aria-label='Modify PSP']"));
        getWebDriverWait(5).withMessage("Il bottone modifica non è cliccabile").until(ExpectedConditions.elementToBeClickable(modificaButton));
        modificaButton.click();

        WebTool.waitTime(2);

        List<WebElement> nexiButton =  driver.findElements(By.xpath("//div[contains(text(),'Nexi')]"));
        WebTool.waitTime(10);
        //getWebDriverWait(10).withMessage("Il bottone Nexi non è cliccabile").until(ExpectedConditions.elementToBeClickable(nexiButton));
        if (nexiButton.size()==2){
            nexiButton.get(1).click();
        }else {
            nexiButton.get(0).click();
        }
        WebTool.waitTime(2);

        WebElement pagaButton =  driver.findElement(By.xpath("//button[@id='paymentCheckPageButtonPay']"));
        getWebDriverWait(5).withMessage("Il bottone Paga non è cliccabile").until(ExpectedConditions.elementToBeClickable(pagaButton));
        pagaButton.click();
        WebTool.waitTime(35);
        List<WebElement> chiudi = driver.findElements(By.xpath("//button[contains(text(),'Chiudi')]"));
        getWebDriverWait(10).withMessage("Il bottone Chiudi non è cliccabile").until(ExpectedConditions.elementToBeClickable(chiudi.get(0)));
        chiudi.get(0).click();
    }

    public void siVisualizzaStatoPagato() {
        WebTool.waitTime(20);
        By statoPagamento = By.xpath("//div[@id='status-chip-Pagato']");
        getWebDriverWait(5).withMessage("Lo stato di pagamento non è visibile").until(ExpectedConditions.visibilityOfElementLocated(statoPagamento));
        logger.info("Lo stato di pagamento è Pagato");
    }
}


