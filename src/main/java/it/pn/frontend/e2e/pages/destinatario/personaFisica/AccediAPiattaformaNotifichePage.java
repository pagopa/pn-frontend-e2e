package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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


    private  WebTool webTool;

    public AccediAPiattaformaNotifichePage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadAccediAPiattaformaNotifichePage() {
        WebElement titleLabel = driver.findElement(By.id("login-mode-page-title"));
        WebElement loginBy = driver.findElement(By.id("spidButton"));

        getWebDriverWait(30).withMessage("Il titolo della pagina accedi a piattaforma notifiche non è visibile").until(ExpectedConditions.visibilityOfAllElements(titleLabel));
        getWebDriverWait(30).withMessage("Il bottone login della pagina accedi a piattaforma notifiche non è visibile e cliccabile").until(ExpectedConditions.and(ExpectedConditions.visibilityOfAllElements(loginBy), ExpectedConditions.elementToBeClickable(loginBy)));
        logger.info("Accedi A Piattaforma Notifiche Page caricata");

    }

    public void selezionaAccediButton() {
        accediButton = driver.findElement(By.id("spidButton"));
        js().executeScript("arguments[0].click()", accediButton);
    }


    public void clickRecapitiButton() {
        recapitiButton = driver.findElement(By.id("side-item-Recapiti"));
        getWebDriverWait(30).withMessage("Il bottone recapiti non è visibile").until(ExpectedConditions.visibilityOf(recapitiButton));
        js().executeScript("arguments[0].click()", recapitiButton);
    }

    public void clickNotificheButton() {
        notificheButton = driver.findElement(By.cssSelector("[id='notificationsTable.body.row']"));
        getWebDriverWait(30).withMessage("Il bottone notifiche non è visibile").until(ExpectedConditions.visibilityOf(notificheButton));
        notificheButton.click();
    }

    public void clickAttestazionePersalvare() {
        attestazione = driver.findElement(By.cssSelector("[data-testid='download-legalfact']"));
        getWebDriverWait(30).withMessage("L'attestazione non è visibile").until(ExpectedConditions.visibilityOf(attestazione));
        js().executeScript("arguments[0].click()", attestazione);
    }

    public boolean isBackButtonDisplayed() {
        indietroButton = driver.findElement(By.id("breadcrumb-indietro-button"));
        return getWebDriverWait(30).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton)).isDisplayed();
    }

    public void clickIndietroButton() {
        indietroButton = driver.findElement(By.id("breadcrumb-indietro-button"));
        getWebDriverWait(30).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton));
        js().executeScript("arguments[0].click()", indietroButton);
    }

    public boolean sezioneDeiDatiDisplayed() {
        sezioneDeiDati = driver.findElement(By.cssSelector("[class='MuiTableBody-root css-1xnox0e']"));
        return getWebDriverWait(30).withMessage("Il sezione dei dati non è visibile").until(ExpectedConditions.visibilityOf(sezioneDeiDati)).isDisplayed();
    }

    public boolean sezionePagamentoDisplayed() {
        try {
            sezioneDeiDati = driver.findElement(By.cssSelector("[data-testid='paymentInfoBox']"));
            return getWebDriverWait(30).withMessage("Il sezione pagamento non è visibile").until(ExpectedConditions.visibilityOf(sezionePagamento)).isDisplayed();

        } catch (NoSuchElementException | TimeoutException e) {

            logger.warn("Il sezione pagamento non è visibile");
            return false;
        }
    }


    public boolean isRadioBoxPresent() {
        try {
            radioBox = driver.findElement(By.cssSelector("[role='radiogroup']"));
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
        webTool.waitTime(10);
        buttonEnterIntoDisservizi = driver.findElement(By.id("side-item-Stato della piattaforma"));
        getWebDriverWait(10).withMessage("Il bottone stato della piattaforma non è visibile").until(ExpectedConditions.visibilityOf(buttonEnterIntoDisservizi));
        buttonEnterIntoDisservizi.click();
    }

    public void clickNotifiche() {
        notificheMenuButton = driver.findElement(By.id("side-item-Notifiche"));
        getWebDriverWait(10).withMessage("Il bottone stato della piattaforma non è visibile").until(ExpectedConditions.visibilityOf(notificheMenuButton));
        notificheMenuButton.click();
    }

    public boolean titoloDiPagamentoDisplayed() {
        WebElement titoloPagamento = driver.findElement(By.xpath("//span[contains(text(),'Pagamento di Test')]"));
        getWebDriverWait(30).withMessage("Il sezione titolo pagamento non è visibile").until(ExpectedConditions.visibilityOf(titoloPagamento)).isDisplayed();
        return true;
    }

    public boolean codiceAvvisoDisplayed() {
        try {
            codiceAvviso = driver.findElement(By.xpath("//span[contains(text(),'Codice avviso')]"));
            getWebDriverWait(5).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(codiceAvviso)).isDisplayed();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean modelloF24Displayed() {
        modelloF24 = driver.findElement(By.cssSelector("[data-testid='download-f24-button']"));
        return getWebDriverWait(5).withMessage("Il sezione scarica modello F24 non è visibile").until(ExpectedConditions.visibilityOf(modelloF24)).isDisplayed();
    }

    public boolean scaricaAvvisoDisplayed() {
        try {
            scaricaAvviso = driver.findElement(By.cssSelector("[data-testid='download-pagoPA-notice-button']"));
            getWebDriverWait(5).withMessage("Il sezione scarica avviso non è visibile").until(ExpectedConditions.visibilityOf(scaricaAvviso)).isDisplayed();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    //TODO Verificare....
    public boolean piuAvvisiDisplayed() {
        pagopaItems = driver.findElements(By.xpath("//div[@data-testid='pagopa-item']"));
        if (pagopaItems.isEmpty()) {
            logger.info("Avvisi pagoPa sono trovati");
            return true;
        }
        getWebDriverWait(3).withMessage("Avvisi pagoPa non sono trovati").until(ExpectedConditions.visibilityOf(pagopaItems.get(0))).isDisplayed();
        if (pagopaItems.size() < 2) {
            logger.info("Avvisi pagoPa non sono trovati");
            return false;
        } else {
            logger.info("Avvisi pagoPa sono trovati");
            return true;
        }
    }

    public boolean allegatoPagoPaDisplayed() {
        pagopaAllegatoItems = driver.findElements(By.xpath("//*[@id=\"root\"]/div[1]/div/main/div/div/div[1]/div[3]/div[4]/div/button[2]"));
        if (pagopaAllegatoItems.isEmpty()) {
            logger.info("Allegati pagoPa non sono trovati");
            return true;
        } else {
            return false;
        }
    }

    public void clickAvvisoPagoPADestinatario() {
        scaricaAvviso = driver.findElement(By.cssSelector("[data-testid='download-pagoPA-notice-button']"));
        getWebDriverWait(30).withMessage("Il sezione scarica avviso non è cliccabile").until(ExpectedConditions.elementToBeClickable(scaricaAvviso));
        scaricaAvviso.click();
    }

    public boolean pagaAvvisoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione paga avviso non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[data-testid='pay-button']")))).isDisplayed();
    }

    public boolean checkButtonPagaIsDisplayed() {
        try {
            return getWebDriverWait(10).withMessage("Il bottone per il pagamento della notifica è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[data-testid='pay-button']")))).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.warn("Il messaggio notifica annullata non è visibile");
            return false;
        }
    }

    public void siVisualizzaSezionePagamento() {
        webTool.waitTime(10);
        codiceAvviso = driver.findElement(By.xpath("//span[contains(text(),'Codice avviso')]"));
        scadenzaAvviso = driver.findElement(By.xpath("//span[contains(text(),'Scade il')]"));
        paymentAmount = driver.findElement(By.cssSelector("[data-testid='payment-amount']"));
        scaricaAvviso = driver.findElement(By.cssSelector("[data-testid='download-pagoPA-notice-button']"));

        getWebDriverWait(10).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(codiceAvviso));
        getWebDriverWait(10).withMessage("Il sezione data di scadenza avviso non è visibile").until(ExpectedConditions.visibilityOf(scadenzaAvviso));
        getWebDriverWait(10).withMessage("Il sezione importo di avviso non è visibile").until(ExpectedConditions.visibilityOf(paymentAmount));
        getWebDriverWait(10).withMessage("Il sezione scarica avviso non è visibile").until(ExpectedConditions.visibilityOf(scaricaAvviso));
        logger.info("Si visualizza correttamente il sezione pagamento di notifica");
    }

    public boolean siControllaCostiDiNotifica() {
        try {
            costiNotifica = driver.findElement(By.cssSelector("[data-testid='apply-costs-caption']"));
            getWebDriverWait(10).withMessage("Costi di notifica inclusi").until(ExpectedConditions.visibilityOf(costiNotifica));
            return true;
        } catch (RuntimeException e) {
            logger.info("Costi di notifica non inclusi");
            return false;
        }
    }

    public void cliccaPaga() {
        logger.info("Si clicca su bottone paga");
        webTool.waitTime(5);
        pagaAvviso = driver.findElement(By.cssSelector("[data-testid='pay-button']"));
        pagaAvviso.click();
    }

    public void inserireDatiPagamento(String email) {
        WebElement emailPagamento = driver.findElement(By.id("email"));
        WebElement confermaEmailPagamento = driver.findElement(By.id("confirmEmail"));
        WebElement continuaPagamento = driver.findElement(By.id("paymentEmailPageButtonContinue"));
        emailPagamento.sendKeys(email);
        confermaEmailPagamento.sendKeys(email);
        continuaPagamento.click();
    }

    public void checkoutPagamento() throws InterruptedException {
        logger.info("Si procede con il pagamento");
        webTool.waitTime(5);
        driver.findElement(By.cssSelector("[data-qaid='CP']")).click();
        webTool.waitTime(5);
        // frame of the card number
        WebElement iframeCardNumber = driver.findElement(By.xpath("//iframe[@id='frame_CARD_NUMBER']"));
        driver.switchTo().frame(iframeCardNumber);
        webTool.waitTime(10);
        creditCardNumber = driver.findElement(By.xpath("/html/body/div/div/div/div/input"));
        getWebDriverWait(10).withMessage("Il textbox numero di carta non è visibile").until(ExpectedConditions.visibilityOf(creditCardNumber));
        creditCardNumber.click();
        creditCardNumber.clear();
        logger.info("Si inserisce numero di CC");
        creditCardNumber.sendKeys("5127390031101597");
        driver.switchTo().defaultContent();

        //frame of the expiry date
        WebElement iframeExpiry = driver.findElement(By.xpath("//iframe[@id='frame_EXPIRATION_DATE']"));
        driver.switchTo().frame(iframeExpiry);
        webTool.waitTime(20);
        WebElement scadenza = driver.findElement(By.xpath("//input[@id='EXPIRATION_DATE']"));
        getWebDriverWait(20).withMessage("Il textbox scadenza non è visibile").until(ExpectedConditions.visibilityOf(scadenza));
        scadenza.click();
        scadenza.clear();
        scadenza.sendKeys("10/25");
        driver.switchTo().defaultContent();

        //frame of the security code
        WebElement iframeSecurityCode = driver.findElement(By.xpath("//iframe[@id='frame_SECURITY_CODE']"));
        driver.switchTo().frame(iframeSecurityCode);
        webTool.waitTime(10);
        WebElement codice = driver.findElement(By.xpath("//input[@id='SECURITY_CODE']"));
        getWebDriverWait(10).withMessage("Il textbox codice di sicurezza non è visibile").until(ExpectedConditions.visibilityOf(codice));
        codice.click();
        codice.clear();
        codice.sendKeys("015");
        driver.switchTo().defaultContent();

        //frame of the cardholder name
        WebElement iframeTitolare = driver.findElement(By.xpath("//iframe[@id='frame_CARDHOLDER_NAME']"));
        driver.switchTo().frame(iframeTitolare);
        webTool.waitTime(10);
        WebElement titolare = driver.findElement(By.xpath("//input[@id='CARDHOLDER_NAME']"));
        getWebDriverWait(10).withMessage("Il textbox titolare non è visibile").until(ExpectedConditions.visibilityOf(titolare));
        titolare.click();
        titolare.clear();
        titolare.sendKeys("Titolare");
        driver.switchTo().defaultContent();

//        getWebDriverWait(15).withMessage("Il bottone Continua non è cliccabile").until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Continue']")));
//        WebElement continuaBottone = driver.findElement(By.xpath("//button[@aria-label='Continue']")); //for local test use //button[@aria-label='Continua']
        getWebDriverWait(15).withMessage("Il bottone Continua non è cliccabile").until(ExpectedConditions.elementToBeClickable(By.id("mui-4")));
        WebElement continuaBottone = driver.findElement(By.id("mui-4")); //for local test use //button[@aria-label='Continua']
        continuaBottone.click();
        webTool.waitTime(10);
        //Select Nexi
        webTool.waitTime(10);
        WebElement modificaButton = driver.findElement(By.xpath("//button[@aria-label='Change payment service provider (PSP)']")); //for local test use //button[@aria-label='Modifica PSP']
        getWebDriverWait(5).withMessage("Il bottone modifica non è cliccabile").until(ExpectedConditions.elementToBeClickable(modificaButton));
        modificaButton.click();

        webTool.waitTime(10);
        List<WebElement> nexiButton = driver.findElements(By.xpath("//div[contains(text(),'Intesa Sanpaolo S.p.A')]"));
        webTool.waitTime(10);
        //getWebDriverWait(10).withMessage("Il bottone Nexi non è cliccabile").until(ExpectedConditions.elementToBeClickable(nexiButton));
        if (nexiButton.size() == 2) {
            nexiButton.get(1).click();
        } else {
            nexiButton.get(0).click();
        }
        webTool.waitTime(5);

        WebElement pagaButton = driver.findElement(By.xpath("//button[@id='paymentCheckPageButtonPay']"));
        getWebDriverWait(5).withMessage("Il bottone Paga non è cliccabile").until(ExpectedConditions.elementToBeClickable(pagaButton));
        pagaButton.click();
        webTool.waitTime(50);
        List<WebElement> chiudi = driver.findElements(By.xpath("//button[contains(text(),'Continue')]")); //for local test use //button[@aria-label='Continua']
        getWebDriverWait(10).withMessage("Il bottone Chiudi non è cliccabile").until(ExpectedConditions.elementToBeClickable(chiudi.get(0)));
        chiudi.get(0).click();
    }

    public void siVisualizzaStatoPagato() {
        webTool.waitTime(20);
        WebElement statoPagamento = driver.findElement(By.xpath("//div[@id='status-chip-Pagato']"));
        getWebDriverWait(5).withMessage("Lo stato di pagamento non è visibile").until(ExpectedConditions.visibilityOf(statoPagamento));
        logger.info("Lo stato di pagamento è Pagato");
    }
}


