package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccediAPiattaformaNotifichePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AccediAPiattaformaNotifichePage");


    @FindBy(id = "login-button")
    WebElement accediButton;

    @FindBy(css = "[id='notificationsTable.body.row']")
    WebElement notificheButton;

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

    @FindBy(css = "[name='radio-buttons-group']")
    WebElement radioButton;

    @FindBy(linkText = "Pagamento di Test")
    WebElement titoloPagamento;

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

    public AccediAPiattaformaNotifichePage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAccediAPiattaformaNotifichePage() {
        try {
            By titleLabel = By.id("login-page-title");
            By loginBy = By.id("login-button");
            this.getWebDriverWait(30).withMessage("Il titolo della pagina accedi a piattaforma notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            this.getWebDriverWait(30).withMessage("Il bottone login della pagina accedi a piattaforma notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(loginBy));
            this.getWebDriverWait(30).withMessage("Il bottone login della pagina accedi a piattaforma notifiche non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.accediButton));
            logger.info("Accedi A Piattaforma Notifiche Page caricata");
        } catch (TimeoutException e) {
            logger.info("Accedi A Piattaforma Notifiche Page non caricata con errore : " + e.getMessage());
            Assert.fail("Accedi A Piattaforma Notifiche Page non caricata con errore : " + e.getMessage());
        }
    }

    public void selezionaAccediButton() {
        this.js().executeScript("arguments[0].click()", this.accediButton);
    }


    public void clickRecapitiButton() {
        getWebDriverWait(30).withMessage("Il bottone recapiti non è visibile").until(ExpectedConditions.visibilityOf(recapitiButton));
        this.js().executeScript("arguments[0].click()", this.recapitiButton);
    }

    public void clickNotificheButton() {
        getWebDriverWait(30).withMessage("Il bottone notifiche non è visibile").until(ExpectedConditions.visibilityOf(notificheButton));
        notificheButton.click();
    }

    public void clickAttestazionePersalvare() {
        getWebDriverWait(30).withMessage("L'attestazione non è visibile").until(ExpectedConditions.visibilityOf(attestazione));
        this.js().executeScript("arguments[0].click()", this.attestazione);
    }

    public boolean isBackButtonDisplayed() {
        return getWebDriverWait(30).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton)).isDisplayed();
    }

    public void clickIndietroButton() {
        getWebDriverWait(30).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton));
        this.js().executeScript("arguments[0].click()", this.indietroButton);
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

    public String cssBuildRadioButton() {
        return "[value='" + codiceAvvisoSpan.getText() + "']";
    }

    public void clickRadioBoxButton(String css) {

        getWebDriverWait(30).withMessage("Il radio box button non è cliccabile").until(ExpectedConditions.elementToBeClickable(radioButton));
        radioButton.click();
    }

    public void clickStatoDellaPiattaforma() {
        getWebDriverWait(10).withMessage("Il bottone stato della piattaforma non è visibile").until(ExpectedConditions.visibilityOf(buttonEnterIntoDisservizi));
        buttonEnterIntoDisservizi.click();
    }


    public boolean titoloDiPagamentoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione titolo pagamento non è visibile").until(ExpectedConditions.visibilityOf(titoloPagamento)).isDisplayed();
    }

    public boolean codiceAvvisoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(codiceAvviso)).isDisplayed();
    }

    public boolean modelloF24Displayed() {
        return getWebDriverWait(30).withMessage("Il sezione scarica modello F24 non è visibile").until(ExpectedConditions.visibilityOf(modelloF24)).isDisplayed();
    }

    public boolean scaricaAvvisoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione scarica avviso non è visibile").until(ExpectedConditions.visibilityOf(scaricaAvviso)).isDisplayed();
    }

    public boolean pagaAvvisoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione paga avviso non è visibile").until(ExpectedConditions.visibilityOf(pagaAvviso)).isDisplayed();
    }

    public void siVisualizzaSezionePagamento(){
        try {
            getWebDriverWait(10).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(codiceAvviso));
            getWebDriverWait(10).withMessage("Il sezione data di scadenza avviso non è visibile").until(ExpectedConditions.visibilityOf(scadenzaAvviso));
            getWebDriverWait(10).withMessage("Il sezione importo di avviso non è visibile").until(ExpectedConditions.visibilityOf(paymentAmount));
            getWebDriverWait(10).withMessage("Il sezione scarica avviso non è visibile").until(ExpectedConditions.visibilityOf(scaricaAvviso));
            logger.info("Si visualizza correttamente il sezione pagamento di notifica");
        }catch (RuntimeException e) {
            logger.info("Non si visualizza correttamente il sezione pagamento di notifica con errore : " + e.getMessage());
            Assert.fail("Non si visualizza correttamente il sezione pagamento di notifica con errore : " + e.getMessage());
        }
    }

    public boolean siControllaCostiDiNotifica(){
        try {
            getWebDriverWait(10).withMessage("Costi di notifica inclusi").until(ExpectedConditions.visibilityOf(costiNotifica));
            return true;
        }catch (RuntimeException e) {
            logger.info("Costi di notifica non inclusi");
            return false;
        }
    }

    public void cliccaPaga(){
        logger.info("Si clicca su bottone paga");
        pagaAvviso.click();
    }

    public void inserireDatiPagamento(String email){
        By emailPagamento = By.id("email");
        By confermaEmailPagamento = By.id("confirmEmail");
        By continuaPagamento = By.id("paymentEmailPageButtonContinue");
        this.element(emailPagamento).sendKeys(email);
        this.element(confermaEmailPagamento).sendKeys(email);
        this.element(continuaPagamento).click();
    }

    public void checkoutPagamento() throws InterruptedException {
        logger.info("Si procede con il pagamento");
        this.element(By.cssSelector("[data-qaid='CP']")).click();
        Thread.sleep(5000);
        // frame of the card number
        WebElement iframeCardNumber  = driver.findElement(By.xpath("//iframe[@id='frame_CARD_NUMBER']"));
        driver.switchTo().frame(iframeCardNumber);
        getWebDriverWait(10).withMessage("Il textbox numero di carta non è visibile").until(ExpectedConditions.visibilityOf(creditCardNumber));
        creditCardNumber.click();
        creditCardNumber.clear();
        creditCardNumber.sendKeys("5186151650005008");
        driver.switchTo().defaultContent();

        //frame of the expiry date
        WebElement iframeExpiry  = driver.findElement(By.xpath("//iframe[@id='frame_EXPIRATION_DATE']"));
        driver.switchTo().frame(iframeExpiry);
        By scadenza = By.xpath("//input[@id='EXPIRATION_DATE']");
        getWebDriverWait(20).withMessage("Il textbox scadenza non è visibile").until(ExpectedConditions.visibilityOfElementLocated(scadenza));
        this.element(scadenza).click();
        this.element(scadenza).clear();
        this.element(scadenza).sendKeys("12/26");
        driver.switchTo().defaultContent();

        //frame of the security code
        WebElement iframeSecurityCode  = driver.findElement(By.xpath("//iframe[@id='frame_SECURITY_CODE']"));
        driver.switchTo().frame(iframeSecurityCode);
        By codice = By.xpath("//input[@id='SECURITY_CODE']");
        getWebDriverWait(10).withMessage("Il textbox codice di sicurezza non è visibile").until(ExpectedConditions.visibilityOfElementLocated(codice));
        this.element(codice).click();
        this.element(codice).clear();
        this.element(codice).sendKeys("123");
        driver.switchTo().defaultContent();

        //frame of the cardholder name
        WebElement iframeTitolare  = driver.findElement(By.xpath("//iframe[@id='frame_CARDHOLDER_NAME']"));
        driver.switchTo().frame(iframeTitolare);
        By titolare = By.xpath("//input[@id='CARDHOLDER_NAME']");
        getWebDriverWait(10).withMessage("Il textbox titolare non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titolare));
        this.element(titolare).click();
        this.element(titolare).clear();
        this.element(titolare).sendKeys("Titolare");
        driver.switchTo().defaultContent();

        By continuaBottone = By.xpath("//button[@aria-label='Continua']");
        getWebDriverWait(5).withMessage("Il bottone Continua non è cliccabile").until(ExpectedConditions.elementToBeClickable(continuaBottone));
        this.element(continuaBottone).click();

        By pagaButton = By.xpath("//button[@id='paymentCheckPageButtonPay']");
        getWebDriverWait(5).withMessage("Il bottone Paga non è cliccabile").until(ExpectedConditions.elementToBeClickable(pagaButton));
        this.element(pagaButton).click();

    }

    public void siVisualizzaStatoPagato(){
        By statoPagamento = By.xpath("//div[@id='status-chip-In elaborazione']");
        getWebDriverWait(5).withMessage("Lo stato di pagamento non è visibile").until(ExpectedConditions.visibilityOfElementLocated(statoPagamento));
    }
}


