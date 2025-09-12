package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.common.DettaglioNotificaSection;
import it.pn.frontend.e2e.common.HelpdeskPage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaMittenteSection;
import it.pn.frontend.e2e.utility.DownloadFile;
import it.pn.frontend.e2e.utility.WebTool;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class PiattaformaNotifichePGPAPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(PiattaformaNotifichePGPAPage.class);


    @FindBy(id = "side-item-Deleghe")
    private WebElement delegheSideMenu;

    @FindBy(id = "side-item-Recapiti")
    private WebElement recapitiButton;

    @FindBy(id = "side-ite-side-item-Integrazione API")
    private WebElement integrazioneAPIMenu;

    @FindBy(id = "side-item-Stato della piattaforma")
    private WebElement buttonEnterIntoDisservizi;

    @FindBy(id = "breadcrumb-indietro-button")
    private WebElement indietroButton;

    @FindBy(css = "[data-testid='paymentInfoBox']")
    private WebElement sezionePagamento;

    @FindBy(css = "[role='radiogroup']")
    private WebElement radioBox;

    @FindBy(xpath = "//span[@data-testid='radio-button']")
    private List<WebElement> radioButton;

    @FindBy(xpath = "//span[contains(text(), 'Pagamento di Test')]")
    private List<WebElement> titoloPagamento;

    @FindBy(css = "[data-testid='download-f24-button']")
    private WebElement modelloF24;

    @FindBy(xpath = "//span[contains(text(), 'Codice avviso')]")
    List<WebElement> codiceAvviso;
    @FindBy(css = ".MuiTypography-caption-semibold.css-1g3z0lx")
    private WebElement codiceAvvisoSpan;

    @FindBy(id = "side-item-Notifiche")
    private WebElement sideItemNotificheButton;

    @FindBy(xpath = "//div[@data-testId ='alert']")
    private WebElement notificaAnnullata;

    @FindBy(xpath = "//button[contains(text(), 'Ricevuta di consegna')]")
    private WebElement ricevutaDiConsegnaButton;

    @Autowired
    private WebDriverConfig webDriverConfig;

    private DownloadFile downloadFile;

    private DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection;

    private HelpdeskPage helpdeskPage;

    private DettaglioNotificaSection dettaglioNotificaSection;

    private WebTool webTool;

    public PiattaformaNotifichePGPAPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
        downloadFile = new DownloadFile(driver);
        dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(driver);
        helpdeskPage = new HelpdeskPage(driver);
        dettaglioNotificaSection = new DettaglioNotificaSection(driver);
    }

    private static void pressTabKey(Robot robot, int times) {
        for (int i = 0; i < times; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(500);
        }
    }

    //    public void clickOnButtonEnterIntoDisservizi() {
//        logger.info("click sul bottone 'stato della piattaforma'");
//        buttonEnterIntoDisservizi = driver.findElement(By.id("side-item-Stato della piattaforma"));
//        getWebDriverWait(10).withMessage("Bottone 'stato della piattaforma' non visualizzato").until(ExpectedConditions.visibilityOf(buttonEnterIntoDisservizi));
//        buttonEnterIntoDisservizi.click();
//    }
    public void clickOnButtonEnterIntoDisservizi() {
        WebElement buttonEnterIntoDisservizi = getWebDriverWait(10)
                .withMessage("Bottone 'stato della piattaforma' non visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("side-item-Stato della piattaforma")));
        buttonEnterIntoDisservizi.click();

    }


    //    public void waitLoadPiattaformaNotificaPage(String ragioneSociale) {
//        try {
//            getWebDriverWait(50).withMessage("Il titolo della pagina Notifiche PG non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[@role='heading' and contains(text(), '" + ragioneSociale + "')]"))));
//            getWebDriverWait(40).withMessage("Il campo codice iun della pagina Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("iunMatch"))));
//            getWebDriverWait(40).withMessage("Il campo data inizio della pagina Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("startDate"))));
//            getWebDriverWait(40).withMessage("Il campo data fine della pagina Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("endDate"))));
//            logger.info("La  pagina Piattaforma Notifiche si carica correttamente");
//        } catch (TimeoutException e) {
//            Assertions.fail("La pagina Piattaforma Notifiche non si carica correttamente con errore: " + e.getMessage());
//        }
//    }
    public void waitLoadPiattaformaNotificaPage(String ragioneSociale) {
        logger.info("Attendo il caricamento della pagina Piattaforma Notifiche per: {}", ragioneSociale);

        getWebDriverWait(50)
                .withMessage("Il titolo della pagina Notifiche PG non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h4[@role='heading' and contains(text(), '" + ragioneSociale + "')]")
                ));
        getWebDriverWait(40)
                .withMessage("Il campo codice IUN della pagina Notifiche PG non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("iunMatch")));
        getWebDriverWait(40)
                .withMessage("Il campo data inizio della pagina Notifiche PG non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("startDate")));
        getWebDriverWait(40)
                .withMessage("Il campo data fine della pagina Notifiche PG non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("endDate")));
    }


    //    public void clickSuDelegeButton() {
//        try {
//            getWebDriverWait(20).withMessage("Sezione deleghe nel side menu non visualizzata").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("side-item-Deleghe"))));
//            delegheSideMenu = driver.findElement(By.id("side-item-Deleghe"));
//            js().executeScript("arguments[0].click()", delegheSideMenu);
//            logger.info("click sul bottone Deleghe effetuato");
//        } catch (TimeoutException e) {
//            Assertions.fail("il bottone deleghe non è stato trovato" + e.getMessage());
//        }
//    }
    public void clickSuDelegeButton() {
        logger.info("Attendo e clicco sul bottone 'Deleghe' nel side menu");
        WebElement delegheSideMenu = getWebDriverWait(20)
                .withMessage("Sezione deleghe nel side menu non visualizzata")
                .until(ExpectedConditions.elementToBeClickable(By.id("side-item-Deleghe")));
        js().executeScript("arguments[0].click()", delegheSideMenu);
        logger.info("Click sul bottone 'Deleghe' effettuato");
    }


    //    public void clickNotificheENotificheDelegate() {
//        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("side-item-Notifiche"))));
//        sideItemNotificheButton = driver.findElement(By.id("side-item-Notifiche"));
//        sideItemNotificheButton.click();
//
//        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("menu-item(notifiche delegate)"))));
//        WebElement notificheDelegateButton = driver.findElement(By.id("menu-item(notifiche delegate)"));
//        js().executeScript("arguments[0].click()", notificheDelegateButton);
//        logger.info("Si clicca correttamente sulla voce notifiche delegate");
//    }
    public void clickNotificheENotificheDelegate() {
        WebElement sideItemNotificheButton = getWebDriverWait(10)
                .withMessage("Bottone 'Notifiche' non visibile")
                .until(ExpectedConditions.elementToBeClickable(By.id("side-item-Notifiche")));
        sideItemNotificheButton.click();

        WebElement notificheDelegateButton = getWebDriverWait(10)
                .withMessage("Bottone 'Notifiche delegate' non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("menu-item(notifiche delegate)")));
        js().executeScript("arguments[0].click()", notificheDelegateButton);

        logger.info("Si clicca correttamente sulla voce 'Notifiche delegate'");
    }


    //    public void clickNotificheDelegate() {
//        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("menu-item(notifiche delegate)"))));
//        WebElement notificheDelegateButton = driver.findElement(By.id("menu-item(notifiche delegate)"));
//        js().executeScript("arguments[0].click()", notificheDelegateButton);
//        logger.info("Si clicca correttamente sulla voce notifiche delegate");
//    }
    public void clickNotificheDelegate() {
        WebElement notificheDelegateButton = getWebDriverWait(10)
                .withMessage("Bottone 'Notifiche delegate' non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("menu-item(notifiche delegate)")));
        js().executeScript("arguments[0].click()", notificheDelegateButton);
    }


    //    public void waitLoadSezioneNotificheDelegate(String ragioneSociale) {
//        try {
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Notifiche delegate a " + ragioneSociale + "-page"))));
//            logger.info("Si visualizza correttamente la sezione notifiche delegate");
//        } catch (TimeoutException e) {
//            Assertions.fail("Non si visualizza correttamente la sezione notifiche delegate con errore" + e.getMessage());
//        }
//    }
    public void waitLoadSezioneNotificheDelegate(String ragioneSociale) {
        logger.info("Attendo la visualizzazione della sezione 'Notifiche delegate' per: {}", ragioneSociale);

        getWebDriverWait(30)
                .withMessage("La sezione 'Notifiche delegate' per " + ragioneSociale + " non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("Notifiche delegate a " + ragioneSociale + "-page")
                ));

        logger.info("La sezione 'Notifiche delegate' si visualizza correttamente");
    }


    //    public void clickRecapitiButton() {
//        getWebDriverWait(10).withMessage("Il bottone recapiti non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("side-item-Recapiti"))));
//        recapitiButton = driver.findElement(By.id("side-item-Recapiti"));
//        js().executeScript("arguments[0].click()", recapitiButton);
//    }
    public void clickRecapitiButton() {
        WebElement recapitiButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Recapiti' non è visibile")
                .until(ExpectedConditions.elementToBeClickable(By.id("side-item-Recapiti")));
        js().executeScript("arguments[0].click()", recapitiButton);
    }


    public void clickSuIntegrazioneAPIButton() {
        try {
            WebElement clickSuIntegrazioneAPIButton = getWebDriverWait(25)
                    .withMessage("Il bottone 'Integrazione API' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("side-item-Integrazione API")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickSuIntegrazioneAPIButton);

            logger.info("Clic sul bottone 'Integrazione API' effettuato con successo");
        } catch (TimeoutException e) {
            Assertions.fail("Il bottone 'Integrazione API' non è stato trovato: " + e.getMessage());
        }
    }

    //    public void clickIndietroButton() {
//        getWebDriverWait(10).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("breadcrumb-indietro-button"))));
//        indietroButton = driver.findElement(By.id("breadcrumb-indietro-button"));
//        js().executeScript("arguments[0].click()", indietroButton);
//    }
    public void clickIndietroButton() {
        WebElement indietroButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Indietro' non è visibile")
                .until(ExpectedConditions.elementToBeClickable(By.id("breadcrumb-indietro-button")));
        js().executeScript("arguments[0].click()", indietroButton);
    }


    //    public boolean sezionePagamentoDisplayed() {
//        try {
//            return getWebDriverWait(5).withMessage("Il sezione pagamento non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[data-testid='paymentInfoBox']")))).isDisplayed();
//        } catch (NoSuchElementException | TimeoutException e) {
//            logger.warn("Il sezione pagamento non è visibile");
//            return false;
//        }
//    }
    public boolean sezionePagamentoDisplayed() {
        try {
            WebElement paymentSection = getWebDriverWait(5)
                    .withMessage("La sezione pagamento non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='paymentInfoBox']")));
            return paymentSection.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("La sezione pagamento non è visibile entro 5 secondi");
            return false;
        }
    }


    //    public boolean messaggioNotificaAnnullataDisplayed() {
//        try {
//            return getWebDriverWait(5).withMessage("Il messaggio notifica annullata non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testId ='alert']")))).isDisplayed();
//        } catch (NoSuchElementException | TimeoutException e) {
//            logger.warn("Il messaggio notifica annullata non è visibile");
//            return false;
//        }
//    }
    public boolean messaggioNotificaAnnullataDisplayed() {
        try {
            WebElement alertMessage = getWebDriverWait(5)
                    .withMessage("Il messaggio 'Notifica annullata' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@data-testId='alert']")
                    ));
            return alertMessage.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("Il messaggio 'Notifica annullata' non è visibile entro 5 secondi");
            return false;
        }
    }


    //    public boolean isRadioBoxPresent() {
//        try {
//            return getWebDriverWait(30).withMessage("Il radio box non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[role='radiogroup']")))).isDisplayed();
//        } catch (NoSuchElementException | TimeoutException e) {
//            logger.warn("Il radio box non è visibile");
//            return false;
//        }
//    }
    public boolean isRadioBoxPresent() {
        try {
            WebElement radioBox = getWebDriverWait(30)
                    .withMessage("Il radio box non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role='radiogroup']")));
            return radioBox.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("Il radio box non è visibile entro 30 secondi");
            return false;
        }
    }


    //    public void clickRadioBoxButton() {
//        getWebDriverWait(10).withMessage("Il radio box button non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//span[@data-testid='radio-button']")).get(0)));
//        radioButton = driver.findElements(By.xpath("//span[@data-testid='radio-button']"));
//        radioButton.get(0).click();
//    }
    public void clickRadioBoxButton() {
        WebElement firstRadioButton = getWebDriverWait(10)
                .withMessage("Il radio box button non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("(//span[@data-testid='radio-button'])[1]") // seleziona il primo elemento
                ));

        firstRadioButton.click();
        logger.info("Click sul primo radio box button effettuato");
    }


    //    public boolean titoloDiPagamentoDisplayed() {
//        return getWebDriverWait(30).withMessage("Il sezione titolo pagamento non è visibile").until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//span[contains(text(), 'Pagamento di Test')]")).get(0))).isDisplayed();
//    }
    public boolean titoloDiPagamentoDisplayed() {
        try {
            WebElement titoloPagamento = getWebDriverWait(30)
                    .withMessage("La sezione 'Titolo pagamento' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("(//span[contains(text(), 'Pagamento di Test')])[1]") // seleziona il primo elemento
                    ));
            return titoloPagamento.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("La sezione 'Titolo pagamento' non è visibile entro 30 secondi");
            return false;
        }
    }


    //    public boolean codiceAvvisoDisplayed() {
//        return getWebDriverWait(30).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//span[contains(text(), 'Codice avviso')]")).get(0))).isDisplayed();
//    }
    public boolean codiceAvvisoDisplayed() {
        try {
            WebElement codiceAvviso = getWebDriverWait(30)
                    .withMessage("La sezione 'Codice avviso' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("(//span[contains(text(), 'Codice avviso')])[1]") // seleziona il primo elemento
                    ));
            return codiceAvviso.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("La sezione 'Codice avviso' non è visibile entro 30 secondi");
            return false;
        }
    }


    //    public boolean modelloF24Displayed() {
//        try {
//            getWebDriverWait(30)
//                    .withMessage("Il sezione scarica modello F24 non è visibile")
//                    .until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[data-testid='download-f24-button']")))).isDisplayed();
//            return true;
//        } catch (RuntimeException e) {
//            return false;
//        }
//    }
    public boolean modelloF24Displayed() {
        try {
            WebElement downloadF24Button = getWebDriverWait(30)
                    .withMessage("La sezione 'Scarica modello F24' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("[data-testid='download-f24-button']")
                    ));
            return downloadF24Button.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("La sezione 'Scarica modello F24' non è visibile entro 30 secondi");
            return false;
        }
    }


    //    public void clickModelloF24Numero(int numOfF24) {
//        getWebDriverWait(30).withMessage("Il sezione scarica modello F24 non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//button[@data-testid='download-f24-button']")).get(numOfF24 - 1)));
//        List<WebElement> f24 = driver.findElements(By.xpath("//button[@data-testid='download-f24-button']"));
//        logger.info("F24 trovato: {}", f24.size());
//        f24.get(numOfF24 - 1).click();
//    }
    public void clickModelloF24Numero(int numOfF24) {
        String xpath = String.format("(//button[@data-testid='download-f24-button'])[%d]", numOfF24);
        WebElement f24Button = getWebDriverWait(30)
                .withMessage("Il modello F24 numero " + numOfF24 + " non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        logger.info("Click sul modello F24 numero {}", numOfF24);
        f24Button.click();
    }


    //    public void checkBoxModelloF24PG() {
//        try {
//            getWebDriverWait(10).withMessage("Non si visualizza il box allegati modelli F24").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='f24only-box']"))));
//        } catch (TimeoutException e) {
//            Assertions.fail("Box del modello F24 non visualizzato correttamente con errore: " + e.getMessage());
//        }
//    }
    public void checkBoxModelloF24PG() {
        getWebDriverWait(10)
                .withMessage("Il box allegati modelli F24 non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='f24only-box']")));

        logger.info("Il box del modello F24 è visualizzato correttamente");
    }


    //    public void clickRicevutaDiConsegna() throws AWTException, IOException {
//        boolean headless = webDriverConfig.getHeadless().equalsIgnoreCase("true");
//        if (!headless) {
//            logger.info("controllo esistenza bottone per scaricare zip");
//            getWebDriverWait(10).withMessage("Il bottone Ricevuta di consegna non cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(), 'Ricevuta di consegna')]"))));
//            ricevutaDiConsegnaButton = driver.findElement(By.xpath("//button[contains(text(), 'Ricevuta di consegna')]"));
//            logger.info("Si clicca sul bottone Ricevuta di consegna");
//            ricevutaDiConsegnaButton.click();
//            Robot robot = new Robot();
//            robot.setAutoDelay(100);
//            robot.delay(2000);
//            String workingDirectory = System.getProperty("user.dir");
//            String path = workingDirectory + "/src/test/resources/dataPopulation/zip";
//
//            pressTabKey(robot, 6);
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//
//            typeFilePath(robot, path);
//
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//
//            robot.delay(1000);
//
//            pressTabKey(robot, 8);
//
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//
//            logger.info("ZIP scaricato");
//        } else {
//            String workingDirectory = System.getProperty("user.dir");
//            File downloadDirectory = new File(workingDirectory + "/src/test/resources/dataPopulation/zip");
//
//            // Generate a unique filename for the downloaded ZIP file
//            String fileName = "downloaded_" + System.currentTimeMillis() + ".zip";
//
//            String codiceIUN;
//            webTool.waitTime(1);
//            codiceIUN = dettaglioNotificaMittenteSection.getInfoNotifica(3);
//
//            File file = new File(downloadDirectory, fileName);
//            ricevutaDiConsegnaButton = driver.findElement(By.xpath("//button[contains(text(), 'Ricevuta di consegna')]"));
//            ricevutaDiConsegnaButton.click();
//            webTool.waitTime(1);
//            final String urlFileRicevuta = downloadFile.getUrl("https://webapi.test.notifichedigitali.it/bff/v1/notifications/received/" + codiceIUN + "/documents/");
//            FileUtils.copyURLToFile(new URL(urlFileRicevuta), file, 1000, 1000);
//            logger.info("ZIP file downloaded successfully.");
//        }
//    }
    public void clickRicevutaDiConsegna() throws AWTException, IOException {
        By ricevutaButtonLocator = By.xpath("//button[contains(text(), 'Ricevuta di consegna')]");
        boolean headless = webDriverConfig.getHeadless().equalsIgnoreCase("true");

        if (!headless) {
            logger.info("Controllo esistenza bottone per scaricare ZIP");

            WebElement ricevutaButton = getWebDriverWait(10)
                    .withMessage("Il bottone 'Ricevuta di consegna' non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(ricevutaButtonLocator));

            logger.info("Si clicca sul bottone 'Ricevuta di consegna'");
            ricevutaButton.click();

            Robot robot = new Robot();
            robot.setAutoDelay(100);
            robot.delay(2000);

            String workingDirectory = System.getProperty("user.dir");
            String path = workingDirectory + "/src/test/resources/dataPopulation/zip";

            pressTabKey(robot, 6);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            typeFilePath(robot, path);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            robot.delay(1000);
            pressTabKey(robot, 8);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            logger.info("ZIP scaricato correttamente");

        } else {
            logger.info("Modalità headless: download diretto del file ZIP");

            String workingDirectory = System.getProperty("user.dir");
            File downloadDirectory = new File(workingDirectory + "/src/test/resources/dataPopulation/zip");

            String fileName = "downloaded_" + System.currentTimeMillis() + ".zip";
            File file = new File(downloadDirectory, fileName);

            WebElement ricevutaButton = getWebDriverWait(10)
                    .withMessage("Il bottone 'Ricevuta di consegna' non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(ricevutaButtonLocator));

            ricevutaButton.click();
            webTool.waitTime(1);

            String codiceIUN = dettaglioNotificaMittenteSection.getInfoNotifica(3);
            final String urlFileRicevuta = downloadFile.getUrl(
                    "https://webapi.test.notifichedigitali.it/bff/v1/notifications/received/" + codiceIUN + "/documents/"
            );

            FileUtils.copyURLToFile(new URL(urlFileRicevuta), file, 1000, 1000);
            logger.info("ZIP file scaricato con successo in modalità headless: {}", file.getAbsolutePath());
        }
    }

    private void typeFilePath(Robot robot, String filePath) {
        for (char c : filePath.toCharArray()) {
            typeCharacter(robot, c);
        }
    }

    private void typeCharacter(Robot robot, char character) {
        switch (character) {
            case ':':
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.VK_SEMICOLON);
                robot.keyRelease(KeyEvent.VK_SEMICOLON);
                robot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case '\\':
                robot.keyPress(KeyEvent.VK_BACK_SLASH);
                robot.keyRelease(KeyEvent.VK_BACK_SLASH);
                break;
            case '/':
                robot.keyPress(KeyEvent.VK_SLASH);
                robot.keyRelease(KeyEvent.VK_SLASH);
                break;
            case '.':
                robot.keyPress(KeyEvent.VK_PERIOD);
                robot.keyRelease(KeyEvent.VK_PERIOD);
                break;
            default:
                if (Character.isUpperCase(character)) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(Character.toUpperCase(character));
                    robot.keyRelease(Character.toUpperCase(character));
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else {
                    robot.keyPress(Character.toUpperCase(character));
                    robot.keyRelease(Character.toUpperCase(character));
                }
        }
    }

    public boolean checkIfPdfExists() throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        String extractDirectoryPath = workingDirectory + "/src/test/resources/dataPopulation/zip/extract";
        Path extractDir = Paths.get(extractDirectoryPath);

        try (Stream<Path> files = Files.walk(extractDir)) {
            return files
                    .filter(Files::isRegularFile)
                    .anyMatch(file -> file.getFileName().toString().toLowerCase().endsWith(".pdf"));
        }
    }

    public void extractZipWithoutPassword() throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        String zipDirectoryPath = workingDirectory + "/src/test/resources/dataPopulation/zip";
        String extractDirectoryPath = zipDirectoryPath + "/extract";

        // Find the latest ZIP file
        File latestZipFile = helpdeskPage.findLatestZipFile(zipDirectoryPath);
        if (latestZipFile == null) {
            throw new IOException("No ZIP file found in the directory: " + zipDirectoryPath);
        }

        // Extract the ZIP file
        ZipFile zip = new ZipFile(latestZipFile);
        zip.extractAll(extractDirectoryPath);

        // Log extracted files
        Files.walk(Paths.get(extractDirectoryPath)).forEach(path -> {
            if (Files.isRegularFile(path)) {
                System.out.println("Found file: " + path.toString());
            }
        });
    }

    public void controllaTesto(String nomeFile) {

        Map<String, String> infoNotifiche = dettaglioNotificaSection.recuperoInfoNotificheDestinatario();
        if (nomeFile.contains("PN_NOTIFICATION_ATTACHMENTS")) {
            if (dettaglioNotificaMittenteSection.controlloTestoFile(nomeFile, "A Simple PDF File")) {
                logger.info("Il testo all'interno del file è corretto");
            } else {
                Assertions.fail("Il testo  all'interno del file  NON è corretto");
            }
        } else if (nomeFile.contains("Avviso di avvenuta ricezione")) {
            if (dettaglioNotificaMittenteSection.controlloTestoFile(nomeFile, "A Simple PDF File")) {
                logger.info("Il testo all'interno del file è corretto");
            } else {
                Assertions.fail("Il testo  all'interno del file  NON è corretto");
            }
        } else {
            if (nomeFile.equals("Attestazione_opponibile_a_terzi_notifica_presa_in_carico")) {
                if (dettaglioNotificaMittenteSection.controlloTestoFile(nomeFile, infoNotifiche.get("mittente"))) {
                    logger.info("Il nome del mittente all'interno del file è corretto");
                } else {
                    Assertions.fail("Il nome del mittente  all'interno del file  NON è corretto");
                }
            }

            if (dettaglioNotificaMittenteSection.controlloTestoFile(nomeFile, infoNotifiche.get("destinatario"))) {
                logger.info("Il nome del destinatario all'interno del file è corretto");
            } else {
                Assertions.fail("Il nome del destinatario  all'interno del file  NON è corretto");
            }
        }
    }

    public void clickNotificheENotificheImpresa() {

        WebElement notificheButton = getWebDriverWait(10)
                .withMessage("Impossibile cliccare su Notifiche")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("side-item-Notifiche")));
        notificheButton.click();

        WebElement notificheImpresaButton = getWebDriverWait(10)
                .withMessage("Impossibile cliccare su notifiche dell'impresa")
                .until(ExpectedConditions.elementToBeClickable(By.id("menu-item(notifiche dell'impresa)")));
        js().executeScript("arguments[0].click();", notificheImpresaButton);
    }
}