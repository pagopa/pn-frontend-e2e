package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.common.HelpdeskPage;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaMittenteSection;
import it.pn.frontend.e2e.utility.DownloadFile;
import it.pn.frontend.e2e.utility.WebTool;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.stream.Stream;

public class PiattaformaNotifichePGPAPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("PiattaformaNotifichePGPAPage");

    @FindBy(id = "side-item-Deleghe")
    WebElement delegheSideMenu;

    @FindBy(id = "side-item-Recapiti")
    WebElement recapitiButton;

    @FindBy(id = "side-item-Stato della piattaforma")
    WebElement buttonEnterIntoDisservizi;

    @FindBy(id = "breadcrumb-indietro-button")
    WebElement indietroButton;

    @FindBy(css = "[data-testid='paymentInfoBox']")
    WebElement sezionePagamento;

    @FindBy(css = "[role='radiogroup']")
    WebElement radioBox;

    @FindBy(xpath = "//span[@data-testid='radio-button']")
    List<WebElement> radioButton;

    @FindBy(xpath = "//span[contains(text(), 'Pagamento di Test')]")
    List<WebElement> titoloPagamento;

    @FindBy(css = "[data-testid='download-f24-button']")
    WebElement modelloF24;

    @FindBy(xpath = "//span[contains(text(), 'Codice avviso')]")
    List<WebElement> codiceAvviso;
    @FindBy(css = ".MuiTypography-caption-semibold.css-1g3z0lx")
    WebElement codiceAvvisoSpan;

    @FindBy(id = "side-item-Notifiche")
    WebElement sideItemNotificheButton;
    @FindBy(xpath = "//div[@data-testId ='alert']")
    WebElement notificaAnnullata;

    @FindBy(xpath = "//button[contains(text(), 'Ricevuta di consegna')]")
    WebElement ricevutaDiConsegnaButton;

    public PiattaformaNotifichePGPAPage(WebDriver driver) {
        super(driver);
    }

    private static void pressTabKey(Robot robot, int times) {
        for (int i = 0; i < times; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(500);
        }
    }

    public void clickOnButtonEnterIntoDisservizi() {
        logger.info("click sul bottone 'stato della piattaforma'");
        this.getWebDriverWait(10).withMessage("Bottone 'stato della piattaforma' non visualizzato").until(ExpectedConditions.visibilityOf(this.buttonEnterIntoDisservizi));
        buttonEnterIntoDisservizi.click();
    }

    public void waitLoadPiattaformaNotificaPage(String ragioneSociale) {
        try {
            By titlePageBy = By.id("Notifiche di " + ragioneSociale + "-page");
            By codiceIunTextFieldBy = By.id("iunMatch");
            By dataInizioFieldBy = By.id("startDate");
            By dataFineFieldBy = By.id("endDate");
            this.getWebDriverWait(this.loadComponentWaitTime).withMessage("Il titolo della pagina Notifiche PG non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(40).withMessage("Il campo codice iun della pagina Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(codiceIunTextFieldBy));
            this.getWebDriverWait(40).withMessage("Il campo data inizio della pagina Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(dataInizioFieldBy));
            this.getWebDriverWait(40).withMessage("Il campo data fine della pagina Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(dataFineFieldBy));
            logger.info("La  pagina Piattaforma Notifiche si carica correttamente");
        } catch (TimeoutException e) {
            logger.error("La pagina Piattaforma Notifiche non si carica correttamente con errore: " + e.getMessage());
            Assert.fail("La pagina Piattaforma Notifiche non si carica correttamente con errore: " + e.getMessage());
        }
    }

    public void clickSuDelegeButton() {
        try {
            this.getWebDriverWait(10).withMessage("Sezione deleghe nel side menu non visualizzata").until(ExpectedConditions.visibilityOf(this.delegheSideMenu));
            this.js().executeScript("arguments[0].click()", this.delegheSideMenu);
            logger.info("click sul bottone Deleghe effetuato");
        } catch (TimeoutException e) {
            logger.error("il bottone deleghe non è stato trovato " + e.getMessage());
            Assert.fail("il bottone deleghe non è stato trovato" + e.getMessage());
        }
    }

    public void clickNotificheDelegate() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(this.sideItemNotificheButton));
            sideItemNotificheButton.click();
            By notificheDelegateButton = By.id("side-item-Notifiche delegate");
            this.getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(notificheDelegateButton));
            this.js().executeScript("arguments[0].click()", this.element(notificheDelegateButton));
            logger.info("Si clicca correttamente sulla voce notifiche delegate");
        } catch (TimeoutException e) {
            logger.error("Non si clicca correttamente sulla voce notifiche delegate con errore:" + e.getMessage());
            Assert.fail("Non si clicca correttamente sulla voce notifiche delegate con errore" + e.getMessage());
        }
    }

    public void waitLoadSezioneNotificheDelegate(String ragioneSociale) {
        try {
            By notificheDelegatePageTitle = By.id("Notifiche delegate a " + ragioneSociale + "-page");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notificheDelegatePageTitle));
            logger.info("Si visualizza correttamente la sezione notifiche delegate");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente la sezione notifiche delegate con errore:" + e.getMessage());
            Assert.fail("Non si visualizza correttamente la sezione notifiche delegate con errore" + e.getMessage());
        }
    }

    public void clickRecapitiButton() {
        getWebDriverWait(10).withMessage("Il bottone recapiti non è visibile").until(ExpectedConditions.visibilityOf(recapitiButton));
        this.js().executeScript("arguments[0].click()", this.recapitiButton);
    }

    public void clickIndietroButton() {
        getWebDriverWait(10).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton));
        this.js().executeScript("arguments[0].click()", this.indietroButton);
    }

    public boolean sezionePagamentoDisplayed() {
        try {
            return getWebDriverWait(5).withMessage("Il sezione pagamento non è visibile").until(ExpectedConditions.visibilityOf(sezionePagamento)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.warn("Il sezione pagamento non è visibile");
            return false;
        }
    }

    public boolean messaggioNotificaAnnullataDisplayed() {
        try {
            return getWebDriverWait(5).withMessage("Il messaggio notifica annullata non è visibile").until(ExpectedConditions.visibilityOf(notificaAnnullata)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.warn("Il messaggio notifica annullata non è visibile");
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
        getWebDriverWait(10).withMessage("Il radio box button non è cliccabile").until(ExpectedConditions.elementToBeClickable(radioButton.get(0)));
        radioButton.get(0).click();
    }

    public boolean titoloDiPagamentoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione titolo pagamento non è visibile").until(ExpectedConditions.visibilityOf(titoloPagamento.get(0))).isDisplayed();
    }

    public boolean codiceAvvisoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(codiceAvviso.get(0))).isDisplayed();
    }

    public boolean modelloF24Displayed() {
        try {
            getWebDriverWait(30).withMessage("Il sezione scarica modello F24 non è visibile").until(ExpectedConditions.visibilityOf(modelloF24)).isDisplayed();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public void clickModelloF24Numero(int numOfF24) {
        List<WebElement> f24 = driver.findElements(By.xpath("//button[@data-testid='download-f24-button']"));
        logger.info("F24 trovato:" + f24.size());
        WebTool.waitTime(3);
        getWebDriverWait(30).withMessage("Il sezione scarica modello F24 non è cliccabile").until(ExpectedConditions.elementToBeClickable(f24.get(numOfF24 - 1)));
        f24.get(numOfF24 - 1).click();
    }

    public void checkBoxModelloF24PG() {
        try {
            By boxModelloF24 = By.xpath("//div[@data-testid='f24only-box']");
            getWebDriverWait(10).withMessage("Non si visualizza il box allegati modelli F24").until(ExpectedConditions.visibilityOfElementLocated(boxModelloF24));
        } catch (TimeoutException e) {
            logger.error("Box del modello F24 non visualizzato correttamente con errore: " + e.getMessage());
            Assert.fail("Box del modello F24 non visualizzato correttamente con errore: " + e.getMessage());
        }
    }

    public void clickRicevutaDiConsegna() throws AWTException, IOException {
        boolean headless = System.getProperty("headless").equalsIgnoreCase("true");
        if (!headless) {
            logger.info("controllo esistenza bottone per scaricare zip");
            getWebDriverWait(10).withMessage("Il bottone Ricevuta di consegna non cliccabile").until(ExpectedConditions.elementToBeClickable(ricevutaDiConsegnaButton));
            logger.info("Si clicca sul bottone Ricevuta di consegna");
            ricevutaDiConsegnaButton.click();
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

            logger.info("ZIP scaricato");
        } else {
            String workingDirectory = System.getProperty("user.dir");
            File downloadDirectory = new File(workingDirectory + "/src/test/resources/dataPopulation/zip");

            // Generate a unique filename for the downloaded ZIP file
            String fileName = "downloaded_" + System.currentTimeMillis() + ".zip";

            DownloadFile downloadFile = new DownloadFile(this.driver);
            DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
            String codiceIUN;
            WebTool.waitTime(1);
            codiceIUN = dettaglioNotificaMittenteSection.getInfoNotifica(3);

            File file = new File(downloadDirectory, fileName);
            ricevutaDiConsegnaButton.click();
            WebTool.waitTime(1);
            final String urlFileRicevuta = downloadFile.getUrl("https://webapi.test.notifichedigitali.it/bff/v1/notifications/received/" + codiceIUN + "/documents/");
            FileUtils.copyURLToFile(new URL(urlFileRicevuta), file, 1000, 1000);
            logger.info("ZIP file downloaded successfully.");
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

        HelpdeskPage helpdeskPage = new HelpdeskPage(this.driver);
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
}