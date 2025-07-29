package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
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


    private  WebTool webTool;

    public AccediAPiattaformaNotifichePage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadAccediAPiattaformaNotifichePage() {
        getWebDriverWait(40).withMessage("Il titolo della pagina accedi a piattaforma notifiche non è visibile").until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("login-mode-page-title"))));
        getWebDriverWait(40).withMessage("Il bottone login della pagina accedi a piattaforma notifiche non è visibile e cliccabile").until(ExpectedConditions.and(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("spidButton"))), ExpectedConditions.elementToBeClickable(driver.findElement(By.id("spidButton")))));
        logger.info("Accedi A Piattaforma Notifiche Page caricata");

    }

    public void selezionaAccediButton() {
        webTool.waitTime(10);
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
        getWebDriverWait(20).withMessage("Il bottone stato della piattaforma non è visibile").until(ExpectedConditions.visibilityOf( driver.findElement(By.id("side-item-Stato della piattaforma"))));
        buttonEnterIntoDisservizi = driver.findElement(By.id("side-item-Stato della piattaforma"));
        buttonEnterIntoDisservizi.click();
    }

    public void clickNotifiche() {
        getWebDriverWait(10).withMessage("Il bottone stato della piattaforma non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("side-item-Notifiche"))));
        notificheMenuButton = driver.findElement(By.id("side-item-Notifiche"));
        notificheMenuButton.click();
    }

    public boolean titoloDiPagamentoDisplayed() {
        WebElement titoloPagamento = driver.findElement(By.xpath("//span[contains(text(),'Pagamento di Test')]"));
        getWebDriverWait(30).withMessage("Il sezione titolo pagamento non è visibile").until(ExpectedConditions.visibilityOf(titoloPagamento)).isDisplayed();
        return true;
    }

    public boolean codiceAvvisoDisplayed() {
//        try {
//            webTool.waitTime(5);
//            codiceAvviso = driver.findElement(By.xpath("//span[contains(text(),'Codice avviso')]"));
//            getWebDriverWait(5).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(codiceAvviso)).isDisplayed();
//            return true;
//        } catch (RuntimeException e) {
//            return false;
//        }
        try {
            WebElement codiceAvviso = getWebDriverWait(25)
                    .withMessage("La sezione codice avviso non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Codice avviso')]")));
            return codiceAvviso.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }



    public boolean modelloF24Displayed() {
        webTool.waitTime(5);
        modelloF24 = driver.findElement(By.cssSelector("[data-testid='download-f24-button']"));
        return getWebDriverWait(5).withMessage("Il sezione scarica modello F24 non è visibile").until(ExpectedConditions.visibilityOf(modelloF24)).isDisplayed();
    }

    public boolean scaricaAvvisoDisplayed() {
        try {
           // scaricaAvviso = driver.findElement(By.cssSelector("[data-testid='download-pagoPA-notice-button']"));
            getWebDriverWait(5).withMessage("Il sezione scarica avviso non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[data-testid='download-pagoPA-notice-button']")))).isDisplayed();
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
        getWebDriverWait(30).withMessage("Il sezione scarica avviso non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[data-testid='download-pagoPA-notice-button']"))));
        scaricaAvviso = driver.findElement(By.cssSelector("[data-testid='download-pagoPA-notice-button']"));
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
          //  costiNotifica = driver.findElement(By.cssSelector("[data-testid='apply-costs-caption']"));
            getWebDriverWait(10).withMessage("Costi di notifica inclusi").until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[data-testid='apply-costs-caption']"))));
            costiNotifica = driver.findElement(By.cssSelector("[data-testid='apply-costs-caption']"));
            return true;
        } catch (RuntimeException e) {
            logger.info("Costi di notifica non inclusi");
            return false;
        }
    }

    public void cliccaPaga() {
        logger.info("Si clicca su bottone paga");
        webTool.waitTime(15);
//        pagaAvviso = driver.findElement(By.cssSelector("[data-testid='pay-button']"));
//        pagaAvviso.click();
        WebElement cliccaPaga = getWebDriverWait(15)
                .withMessage("Il bottone 'Paga' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='pay-button']")));
        // Clicca sul pulsante 'Paga'
        cliccaPaga.click();
    }

    public void inserireDatiPagamento(String email) {
        webTool.waitTime(5);
        WebElement emailPagamento = driver.findElement(By.id("email"));
        WebElement confermaEmailPagamento = driver.findElement(By.id("confirmEmail"));
        WebElement continuaPagamento = driver.findElement(By.id("paymentEmailPageButtonContinue"));
        emailPagamento.sendKeys(email);
        confermaEmailPagamento.sendKeys(email);
        continuaPagamento.click();
    }

//    public void checkoutPagamento() throws InterruptedException {
//        logger.info("Si procede con il pagamento");
//        webTool.waitTime(5);
//        driver.findElement(By.cssSelector("[data-qaid='CP']")).click();
//        webTool.waitTime(5);
//        // frame of the card number
//        WebElement iframeCardNumber = driver.findElement(By.xpath("//iframe[@id='frame_CARD_NUMBER']"));
//        driver.switchTo().frame(iframeCardNumber);
//        webTool.waitTime(15);
//        creditCardNumber = driver.findElement(By.xpath("/html/body/div/div/div/div/input"));
//        getWebDriverWait(15).withMessage("Il textbox numero di carta non è visibile").until(ExpectedConditions.visibilityOf(creditCardNumber));
//        creditCardNumber.click();
//        creditCardNumber.clear();
//        logger.info("Si inserisce numero di CC");
//        creditCardNumber.sendKeys("5255000010002856");
//        driver.switchTo().defaultContent();
//
//        //frame of the expiry date
//        WebElement iframeExpiry = driver.findElement(By.xpath("//iframe[@id='frame_EXPIRATION_DATE']"));
//        driver.switchTo().frame(iframeExpiry);
//        webTool.waitTime(20);
//        WebElement scadenza = driver.findElement(By.xpath("//input[@id='EXPIRATION_DATE']"));
//        getWebDriverWait(20).withMessage("Il textbox scadenza non è visibile").until(ExpectedConditions.visibilityOf(scadenza));
//        scadenza.click();
//        scadenza.clear();
//        scadenza.sendKeys("12/30");
//        driver.switchTo().defaultContent();
//
//        //frame of the security code
//        WebElement iframeSecurityCode = driver.findElement(By.xpath("//iframe[@id='frame_SECURITY_CODE']"));
//        driver.switchTo().frame(iframeSecurityCode);
//        webTool.waitTime(10);
//        WebElement codice = driver.findElement(By.xpath("//input[@id='SECURITY_CODE']"));
//        getWebDriverWait(20).withMessage("Il textbox codice di sicurezza non è visibile").until(ExpectedConditions.visibilityOf(codice));
//        codice.click();
//        codice.clear();
//        codice.sendKeys("123");
//        driver.switchTo().defaultContent();
//
//        //frame of the cardholder name
//        WebElement iframeTitolare = driver.findElement(By.xpath("//iframe[@id='frame_CARDHOLDER_NAME']"));
//        driver.switchTo().frame(iframeTitolare);
//        webTool.waitTime(10);
//        WebElement titolare = driver.findElement(By.xpath("//input[@id='CARDHOLDER_NAME']"));
//        getWebDriverWait(10).withMessage("Il textbox titolare non è visibile").until(ExpectedConditions.visibilityOf(titolare));
//        titolare.click();
//        titolare.clear();
//        titolare.sendKeys("Titolare");
//        driver.switchTo().defaultContent();
//        WebElement continuaBottone = getWebDriverWait(55)
//                .withMessage("Il bottone Continua non è cliccabile in Inserisci i dati della carta ")
//                .until(ExpectedConditions.elementToBeClickable(By.id("submit"))); // mui-5
//        continuaBottone.click();
//        logger.info("Il bottone Continua cliccato in Inserisci i dati della carta");
//
//        clickIntesaSanpaoloRadioButton();
//
//        WebElement continuaButtonScegliPagamento = getWebDriverWait(60)
//                .withMessage("Il bottone Continua su Scegli chi gestirà il pagamento non è cliccabile ")
//                .until(ExpectedConditions.presenceOfElementLocated(
//                        By.id("paymentPspListPageButtonContinue")
//                ));
//        continuaButtonScegliPagamento.click();
//
//        //Select Nexi
//        webTool.waitTime(10);
//        WebElement modificaButton = getWebDriverWait(120)
//                .withMessage("Il bottone modifica non è cliccabile")
//                .until(ExpectedConditions.elementToBeClickable(By.id("pspEdit")));////for local test use //button[@aria-label='Modifica PSP']
//        modificaButton.click();
//        webTool.waitTime(10);
//
//        getWebDriverWait(80)
//                .withMessage("Intesa Sanpaolo S.p.A non è cliccabile")
//                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[contains(text(),'Intesa Sanpaolo S.p.A')]"))));
//        driver.findElement(By.xpath("(//div[contains(text(),'Intesa Sanpaolo S.p.A')])[2]")).click();
//
////        WebElement pagaButton = driver.findElement(By.xpath("//button[@id='paymentCheckPageButtonPay']"));
////        getWebDriverWait(15).withMessage("Il bottone Paga non è cliccabile").until(ExpectedConditions.elementToBeClickable(pagaButton));
////        pagaButton.click();
//        WebElement pagaButton = getWebDriverWait(25)
//                .withMessage("Il bottone Paga non è cliccabile")
//                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='paymentCheckPageButtonPay']")));
//        pagaButton.click();
//
//        webTool.waitTime(120);//necessaria
////        getWebDriverWait(50)
////                .withMessage("Il bottone Continua non è cliccabile ")
////                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Continu')]"))));
////        driver.findElement(By.xpath("//button[contains(text(),'Continu')]")).click();//for local test use //button[@aria-label='Continua']
//        WebElement continueButton = getWebDriverWait(170)
//                .withMessage("Il bottone Continua non è cliccabile")
//                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Continu')]")));
//
//        // Clicca sul bottone
//        continueButton.click();
//    }

    public void checkoutPagamento() {
        logger.info("Si procede con il pagamento");

        // Clicca sul pulsante con data-qaid='CP'
        WebElement cpButton = getWebDriverWait(60)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-qaid='CP']")));
        cpButton.click();

        // Inserimento del numero della carta di credito
        handleCardField("//iframe[@id='frame_CARD_NUMBER']", "//html/body/div/div/div/div/input", "5255000010002856", "numero di carta");

        // Inserimento della data di scadenza
        handleCardField("//iframe[@id='frame_EXPIRATION_DATE']", "//input[@id='EXPIRATION_DATE']", "12/30", "scadenza");

        // Inserimento del codice di sicurezza
        handleCardField("//iframe[@id='frame_SECURITY_CODE']", "//input[@id='SECURITY_CODE']", "123", "codice di sicurezza");

        // Inserimento del nome del titolare della carta
        handleCardField("//iframe[@id='frame_CARDHOLDER_NAME']", "//input[@id='CARDHOLDER_NAME']", "Titolare", "titolare");

        // Clicca sul bottone Continua
        WebElement continuaButton = getWebDriverWait(60)
                .withMessage("Il bottone Continua non è cliccabile in Inserisci i dati della carta")
                .until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        continuaButton.click();
        logger.info("Il bottone Continua cliccato in Inserisci i dati della carta");

        //Check se amount è pagabile da Intesa Sanpaolo (radio button non c'è in caso di amount pari a 6.000,00 euro)
        String amount = driver.findElement(By.xpath("//button[@aria-label='Apri riepilogo pagamento']")).getText();
        logger.info("Amount to pay {}", amount);

        // Clicca sul radio button di Intesa Sanpaolo o Nexi (a seconda se amount da pagare è autorizzabile)
        if (amount.contains("6.000,00")) {

            // Clicca sul bottone Modifica
            WebElement modificaButton = getWebDriverWait(60)
                    .withMessage("Il bottone modifica non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.id("pspEdit")));
            modificaButton.click();

            // Clicca su Nexi
            WebElement intesaSanpaolo = getWebDriverWait(60)
                    .withMessage("Nexi non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(text(),'Nexi')])[2]")));
            intesaSanpaolo.click();
        }

        else {

            clickIntesaSanpaoloRadioButton();

            // Clicca sul bottone Continua su Scegli chi gestirà il pagamento
            WebElement continuaButtonScegliPagamento = getWebDriverWait(60)
                    .withMessage("Il bottone Continua su Scegli chi gestirà il pagamento non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.id("paymentPspListPageButtonContinue")));
            continuaButtonScegliPagamento.click();

            // Clicca sul bottone Modifica
            WebElement modificaButton = getWebDriverWait(60)
                    .withMessage("Il bottone modifica non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.id("pspEdit")));
            modificaButton.click();

            // Clicca su Intesa Sanpaolo S.p.A
            WebElement intesaSanpaolo = getWebDriverWait(60)
                    .withMessage("Intesa Sanpaolo S.p.A non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(text(),'Intesa Sanpaolo S.p.A')])[2]")));
            intesaSanpaolo.click();
        }

        // Clicca sul bottone Paga
        WebElement pagaButton = getWebDriverWait(60)
                .withMessage("Il bottone Paga non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='paymentCheckPageButtonPay']")));
        pagaButton.click();

        //verifica conferma pagamento
        WebElement titoloConfermaPagamento = getWebDriverWait(200)
                .withMessage("Il titolo di conferma di pagamento avvenuto di " + amount + " non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("responsePageMessageTitle")));

        WebElement bodyConfermaPagamento = getWebDriverWait(200)
                .withMessage("Il body di conferma di pagamento avvenuto di " + amount + " non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("responsePageMessageBody")));

        Assertions.assertTrue(titoloConfermaPagamento.getText().contains("Hai pagato"), "Il pagamento di " + amount + " non è stato effettuato con messaggio " + titoloConfermaPagamento.getText());
        Assertions.assertTrue(bodyConfermaPagamento.getText().contains("Abbiamo inviato la conferma del pagamento"), "Il pagamento di " + amount + " non è stato effettuato con messaggio " + bodyConfermaPagamento.getText());

        // Clicca sul bottone Continua finale
        WebElement continueButton = getWebDriverWait(200)
                .withMessage("Il bottone Continua non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Continu')]")));
        continueButton.click();
    }

    private void handleCardField(String iframeXPath, String inputXPath, String inputValue, String fieldName) {
        // Trova e passa all'iframe
        WebElement iframe = getWebDriverWait(20)
                .withMessage("Impossibile trovare Trova e passa all'iframe: "+iframeXPath)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(iframeXPath)));
        driver.switchTo().frame(iframe);

        // Trova e interagisci con il campo di input
        WebElement inputField = getWebDriverWait(20)
                .withMessage("Il textbox " + fieldName + " non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(inputXPath)));
        inputField.click();
        inputField.clear();
        inputField.sendKeys(inputValue);

        // Torna al contenuto principale della pagina
        driver.switchTo().defaultContent();
    }

    public void clickIntesaSanpaoloRadioButton() {
        try {
            // Trova il radio button associato a "Intesa Sanpaolo S.p.A"
            WebElement intesaSanpaoloContainer = getWebDriverWait(20)
                    .withMessage("Impossibile trovare il radio button associato a Intesa Sanpaolo S.p.A ")
                    .until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@id='BCITITMM']")
            ));

            // Trova il radio button all'interno del container specifico
            WebElement radioButton = intesaSanpaoloContainer.findElement(
                    By.xpath(".//*[local-name()='svg' and @data-testid='RadioButtonUncheckedIcon']")
            );

            // Clicca sul radio button
            radioButton.click();
        } catch (Exception e) {
            // Gestione delle eccezioni: stampa l'errore se il radio button non è trovato o non è cliccabile
            Assertions.fail("Errore durante il click sul radio button di Intesa Sanpaolo S.p.A: " + e.getMessage());
        }
    }

    public void siVisualizzaStatoPagato() {
//        getWebDriverWait(240)
//                .withMessage("Lo stato di pagamento non è visibile")
//                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='status-chip-Pagato']"))));
        getWebDriverWait(240)
                .withMessage("Lo stato di pagamento 'Pagato' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='status-chip-Pagato']")));

        logger.info("Lo stato di pagamento è Pagato");
    }
}


