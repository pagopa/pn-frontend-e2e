package it.pn.frontend.e2e.common;

import it.pn.frontend.e2e.model.enums.Disservice;
import it.pn.frontend.e2e.model.enums.Status;
import it.pn.frontend.e2e.utility.WebTool;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import net.lingala.zip4j.ZipFile;


public class HelpdeskPage extends BasePage {

    @FindBy(id = "buttonLogin")
    WebElement loginButton;

    @FindBy(id = "Email")
    WebElement emailInput;

    @FindBy(id = "Password")
    WebElement passwordInput;

    @FindBy(id = "logout")
    WebElement buttonLogout;

    @FindBy(id = "Numero Ticket")
    WebElement numeroTicketInput;

    @FindBy(id = "Codice Fiscale")
    WebElement codiceFiscaleInput;

    @FindBy(id = "IUN")
    WebElement iunInput;

    @FindBy(id = "ricerca")
    WebElement buttonRicerca;

    @FindBy(xpath = "//div[1]/div[4]/div/label/span[1]")
    WebElement deanonimizzazioneDati;

    @FindBy(xpath = "//p[contains(text(),'Codice Univoco')]")
    WebElement Uid;

    @FindBy(xpath = "//p[contains(text(),'Codice Fiscale')]")
    WebElement CfPersonaFisica;


    @FindBy(id = "Tipo Estrazione")
    WebElement selectTypeOfEstrazioneDati;

    @FindBy(id = "Codice Univoco (uid)")
    WebElement inputUid;

    @FindBy(id = "cardTitle-Monitoraggio Piattaforma Notifiche")
    WebElement monitoraggioPN;

    @FindBy(xpath = ".//div[@data-field='functionality' and @role='cell']")
    List<WebElement> services;

    @FindBy(xpath = ".//div[@data-field='data' and @role='cell']")
    List<WebElement> serviceDates;

    @FindBy(xpath = ".//button[@role='menuitem']")
    List<WebElement> serviceStatusButtons;

    @FindBy(xpath = "//button[contains(@aria-label,'Choose date')]")
    List<WebElement> calendarIcon;


    private final Logger logger = LoggerFactory.getLogger("Helpdesk Page");

    private String codiceFiscale;
    private String zipPassword;
    private String codiceIdentificativoPF;

    public HelpdeskPage(WebDriver driver) {
        super(driver);
    }


    public void changePage(String HelpdeskURL) {
        this.driver.get(HelpdeskURL);
    }

    public void checkForm() {
        try {
            logger.info("Check form in corso");
            this.getWebDriverWait(40).withMessage("email non presente").until(ExpectedConditions.visibilityOf(this.emailInput));
            this.getWebDriverWait(40).withMessage("password non presente").until(ExpectedConditions.visibilityOf(this.passwordInput));
            this.getWebDriverWait(40).withMessage("submit non presente").until(ExpectedConditions.visibilityOf(this.loginButton));
        } catch (TimeoutException e) {
            logger.error("Form non presente errore: " + e.getMessage());
            Assert.fail("Form non presente errore: " + e.getMessage());
        }
    }

    public void checkHome() {
        try {
            this.getWebDriverWait(10).withMessage("home non presente").until(ExpectedConditions.visibilityOf(this.monitoraggioPN));
            logger.info("pagina home carica");
        } catch (TimeoutException e) {
            logger.error("errore caricamento home helpdesk: " + e.getMessage());
            Assert.fail("errore caricamento home helpdesk: " + e.getMessage());
        }
    }

    public void waitLoadServiceTable() {
        if (services.size() < 3) {
            logger.error("I servizi visualizzati sono meno di 3");
            Assert.fail("I servizi visualizzati sono meno di 3");
        }
        getWebDriverWait(10).withMessage("Non è visibile la tabella dei disservizi").until(ExpectedConditions.visibilityOfAllElements(services));
    }

    public void insertUsername(String user) {
        logger.info("inserisco email");
        this.emailInput.sendKeys(user);
    }


    public void insertPassword(String pwd) {
        logger.info("inserisco password");
        this.passwordInput.sendKeys(pwd);
    }


    public void clickInviaButton() {
        logger.info("clicco il bottone login");
        this.loginButton.click();
    }

    public void clickMonitoraggio() {
        try {
            logger.info("clicco sulla card monitoraggio piattaforma notifiche");
            this.getWebDriverWait(10).withMessage("Il bottone monitoraggio non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.monitoraggioPN));
            this.monitoraggioPN.click();
        } catch (TimeoutException e) {
            logger.error("Card monitoraggio non cliccabile: " + e.getMessage());
            Assert.fail("Card monitoraggio non cliccabile: " + e.getMessage());
        }
    }

    public void handleDisservizio(Disservice disservizio, Status status) {
        waitLoadServiceTable();
        getWebDriverWait(10).withMessage("Non sono visibili i pulsanti per il disservizio").until(ExpectedConditions.visibilityOfAllElements(serviceStatusButtons));
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getText().equals(disservizio.getValue())) {
                logger.info("Clicco sul menu del disservizio: " + disservizio.getValue());
                serviceStatusButtons.get(i).click();
                break;
            }
        }
        // this is useful to check if we correctly clicked on the menu and the tooltip is displayed otherwise we fail the test
        WebElement tooltip = this.element(By.xpath(".//div[@role='tooltip']"));
        if (!tooltip.isDisplayed()) {
            logger.error("Non è stato possibile visualizzare il tooltip per il disservizio");
            Assert.fail("Non è stato possibile visualizzare il tooltip per il disservizio");
        }
        WebElement button = tooltip.findElement(By.id(status.getValue() + "-insert"));
        getWebDriverWait(10).withMessage("Il pulsante: " + status.getValue() + " non è visibile o cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(button),
                ExpectedConditions.elementToBeClickable(button)
        ));
        button.click();
        WebElement buttonInsert = this.element(By.id("buttonInserisciDisservizio"));
        getWebDriverWait(10).withMessage("Il pulsante di inserimento del servizio non è visibile o cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(buttonInsert),
                ExpectedConditions.elementToBeClickable(buttonInsert)
        ));
        buttonInsert.click();
        // When we insert a new status of a service there is alway an alert that shows up, this is useful when we want to make sure the status has changed
        WebElement alertSuccess = this.element(By.xpath(".//div[@role='alert']"));
        getWebDriverWait(10).withMessage("L'alert di successo post-inserimento servizio o disservizio non è visibile").until(ExpectedConditions.visibilityOf(alertSuccess));
        logger.info(disservizio.getValue() + " è stato cambiato con successo in " + status.getValue());
    }

    public boolean checkServiceStatus(Disservice disservizio) {
        waitLoadServiceTable();
        getWebDriverWait(10).withMessage("Non sono visibili i stati dei servizi").until(ExpectedConditions.visibilityOfAllElements(serviceDates));
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getText().equals(disservizio.getValue())) {
                // check status based on the service date if present or not
                return !serviceDates.get(i).getText().isEmpty();
            }
        }
        return false;
    }

    public boolean checkIsCreatedDisservizio() {
        try {
            WebElement dateDisservizio = this.elements(By.xpath("//div[@data-field='data']")).get(1);
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOf(dateDisservizio));
            if (dateDisservizio.getText() != null && !dateDisservizio.getText().isEmpty()) {
                logger.info("disservizio già in corso");
                return true;
            }
            return false;
        } catch (TimeoutException e) {
            logger.error("disservizio non creato: " + e.getMessage());
            Assert.fail("disservizio non creato: " + e.getMessage());
            return false;
        }
    }

    public void clickSezioneRicerca() {
        try {
            By ricercaButton = By.id("cardTitle-Ricerca ed estrazione dati");
            logger.info("clicco sulla card ricerca ed estrazione dati");
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(ricercaButton));
            this.elements(ricercaButton).get(0).click();
        } catch (TimeoutException e) {
            logger.error("Card ricerca non cliccabile: " + e.getMessage());
            Assert.fail("Card ricerca non cliccabile: " + e.getMessage());
        }
    }

    public void checkRicercaPage() {
        logger.info("check pagina ricerca ed estrazione dati");
        try {
            By buttonResetFiltri = By.id("resetFilter");
            this.getWebDriverWait(30).withMessage("Tipo estrazione non trovato").until(ExpectedConditions.visibilityOf(selectTypeOfEstrazioneDati));
            this.getWebDriverWait(30).withMessage("numero ticket input non trovato").until(ExpectedConditions.visibilityOf(numeroTicketInput));
            this.getWebDriverWait(30).withMessage("codice fiscale input non trovato").until(ExpectedConditions.visibilityOf(codiceFiscaleInput));
            this.getWebDriverWait(30).withMessage("button ricerca non trovato").until(ExpectedConditions.visibilityOf(buttonRicerca));
            this.getWebDriverWait(30).withMessage("button reset filtri non trovato").until(ExpectedConditions.visibilityOfElementLocated(buttonResetFiltri));
        } catch (TimeoutException e) {
            logger.error("home ricerca non caricata correttamente: " + e.getMessage());
            Assert.fail("home ricerca non caricata correttamente: " + e.getMessage());
        }
    }

    public void logout() {
        try {
            logger.info("controllo esistenza bottone logout");
            this.getWebDriverWait(30).withMessage("bottone logout non trovato").until(ExpectedConditions.visibilityOf(buttonLogout));
            logger.info("click sul bottone logout");
            buttonLogout.click();
            logger.info("apertura dialog di conferma logout");
            By buttonConfermaLogout = By.xpath("//button[contains(text(),'Esci')]");
            logger.info("controllo esistenza pulsante conferma logout");
            this.getWebDriverWait(30).withMessage("bottone conferma logout non trovato").until(ExpectedConditions.visibilityOfElementLocated(buttonConfermaLogout));
            logger.info("click conferma logout");
            this.elements(buttonConfermaLogout).get(0).click();
        } catch (TimeoutException e) {
            logger.error("logout non riuscito correttamente: " + e.getMessage());
            Assert.fail("logout non riuscito correttamente: " + e.getMessage());
        }
    }

    public void insertCfAndRicercaOnPage(String codiceFiscale) {
        logger.info("inserisco numero ticket");
        numeroTicketInput.sendKeys("testTAFE01");
        logger.info("inserisco codice fiscale");
        setCodiceFiscale(codiceFiscale);
        codiceFiscaleInput.sendKeys(codiceFiscale);
        logger.info("clicco sul bottone di ricerca");
        try {
            this.getWebDriverWait(30).withMessage("bottone per la ricerca non trovato").until(ExpectedConditions.elementToBeClickable(buttonRicerca));
            buttonRicerca.click();
        } catch (TimeoutException e) {
            logger.error("bottone non cliccabile:" + e.getMessage());
            Assert.fail("bottone non cliccabile:" + e.getMessage());

        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void insertCF(String codiceFiscale) {
        logger.info("inserisco codice fiscale");
        setCodiceFiscale(codiceFiscale);
        codiceFiscaleInput.sendKeys(codiceFiscale);
    }

    public void insertIunAndRicercaOnPage(String iun) {
        logger.info("inserisco numero ticket");
        numeroTicketInput.sendKeys("testTAFE01");
        logger.info("inserisco codice IUN");
        iunInput.sendKeys(iun);
        logger.info("clicco sul bottone di ricerca");
        try {
            this.getWebDriverWait(30).withMessage("bottone per la ricerca non trovato").until(ExpectedConditions.elementToBeClickable(buttonRicerca));
            buttonRicerca.click();
        } catch (TimeoutException e) {
            logger.error("bottone non cliccabile:" + e.getMessage());
            Assert.fail("bottone non cliccabile:" + e.getMessage());

        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void insertIun(String iun) {
        logger.info("inserisco codice IUN");
        iunInput.sendKeys(iun);

    }

    public void insertUid(String uid) {
        logger.info("inserisco codice univoco");
        inputUid.sendKeys(uid);

    }
    public void insertNumeroTicket() {
        logger.info("inserisco numero ticket");
        numeroTicketInput.sendKeys("testTAFE01");
    }


    public void checkUid() {
        try {
            logger.info("controllo esistenza codice univoco");
            this.getWebDriverWait(30).withMessage("Codice univoco non trovato").until(ExpectedConditions.visibilityOf(Uid));
            setCodiceIdentificativoPF(Uid.getText().replace("Codice Univoco: ", ""));
        } catch (TimeoutException e) {
            logger.error("codice univoco non trovato: " + e.getMessage());
            Assert.fail("codice univoco non trovato: " + e.getMessage());
        }
    }

    public void setCodiceIdentificativoPF(String codiceIdentificativoPF) {
        this.codiceIdentificativoPF = codiceIdentificativoPF;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public void setPassword(String password){
        this.zipPassword = password;
    }

    public void changeOption() {
        logger.info("click su tipo estrazione dati");
        By inputTipoEstrazione = By.xpath("//div[@data-testid='select-Tipo Estrazione']");
        this.element(inputTipoEstrazione).click();
        try {
            logger.info("selezione ottieni codice fiscale");
            By selectTypeOfOttieniCF = By.xpath("//li[contains(text(),'Ottieni CF')]");
            logger.info("controllo esistenza selezione");
            this.getWebDriverWait(30).withMessage("opzione ottieni cf non trovata").until(ExpectedConditions.visibilityOfElementLocated(selectTypeOfOttieniCF));
            this.element(selectTypeOfOttieniCF).click();
            By checkUidIsDisplayed = By.id("Codice Univoco (uid)");
            this.getWebDriverWait(30).withMessage("input uid non trovato").until(ExpectedConditions.visibilityOfElementLocated(checkUidIsDisplayed));
        } catch (TimeoutException e) {
            logger.error("opzione ottieni cf non trovata: " + e.getMessage());
            Assert.fail("opzione ottieni cf non trovata: " + e.getMessage());
        }
    }

    public void selectOttieniNotifica() {
        logger.info("click su tipo estrazione dati");
        By inputTipoEstrazione = By.xpath("//div[@data-testid='select-Tipo Estrazione']");
        this.element(inputTipoEstrazione).click();
        try {
            logger.info("selezione ottieni notifica");
            By selectTypeOfOttieniCF = By.xpath("//li[contains(text(),'Ottieni notifica')]");
            logger.info("controllo esistenza selezione");
            this.getWebDriverWait(30).withMessage("opzione ottieni notifica non trovata").until(ExpectedConditions.visibilityOfElementLocated(selectTypeOfOttieniCF));
            this.element(selectTypeOfOttieniCF).click();
            By checkIunIsDisplayed = By.id("IUN");
            this.getWebDriverWait(30).withMessage("input IUN non trovato").until(ExpectedConditions.visibilityOfElementLocated(checkIunIsDisplayed));
        } catch (TimeoutException e) {
            logger.error("opzione ottieni notifica non trovata: " + e.getMessage());
            Assert.fail("opzione ottieni notifica non trovata: " + e.getMessage());
        }
    }
    public void selectOttieniLogCompleti() {
        logger.info("click su tipo estrazione dati");
        By inputTipoEstrazione = By.xpath("//div[@data-testid='select-Tipo Estrazione']");
        this.element(inputTipoEstrazione).click();
        try {
            logger.info("selezione ottieni log completi");
            By selectTypeOfOttieniLogCompleti = By.xpath("//li[contains(text(),'Ottieni log completi')]");
            logger.info("controllo esistenza selezione");
            this.getWebDriverWait(30).withMessage("opzione ottieni log completi non trovata").until(ExpectedConditions.visibilityOfElementLocated(selectTypeOfOttieniLogCompleti));
            this.element(selectTypeOfOttieniLogCompleti).click();
            By checkNumeroTicketIsDisplayed = By.id("Numero Ticket");
            this.getWebDriverWait(30).withMessage("input Numero Ticket non trovato").until(ExpectedConditions.visibilityOfElementLocated(checkNumeroTicketIsDisplayed));
        } catch (TimeoutException e) {
            logger.error("opzione ottieni log comleti non trovata: " + e.getMessage());
            Assert.fail("opzione ottieni log completi non trovata: " + e.getMessage());
        }
    }


    public void checkMessaggioSuccesso(){
        try {
            logger.info("controllo esistenza messaggio di successo");
            By messaggio = By.xpath("//p[contains(text(),'Operazione completata con successo')]");
            this.getWebDriverWait(90).withMessage("Messaggio di successo non trovato").until(ExpectedConditions.visibilityOfElementLocated(messaggio));
        } catch (TimeoutException e) {
            logger.error("Messaggio di successo non trovato: " + e.getMessage());
            Assert.fail("Messaggio di successo non trovato: " + e.getMessage());
        }
    }

    public void checkMessaggioDiErroreData(){
        try {
            logger.info("controllo esistenza messaggio di errore");
            By messaggio = By.xpath("//p[contains(text(),'intervallo temporale non può superare i 3 mesi')]");
            this.getWebDriverWait(15).withMessage("Messaggio di errore non trovato").until(ExpectedConditions.visibilityOfElementLocated(messaggio));
        } catch (TimeoutException e) {
            logger.error("Messaggio di errore non trovato: " + e.getMessage());
            Assert.fail("Messaggio di errore non trovato: " + e.getMessage());
        }
    }
    public void checkMessaggioDiErroreIUN(){
        try {
            logger.info("controllo esistenza messaggio di errore");
            By messaggio = By.xpath("//p[contains(text(),'Inserimento errato')]");
            this.getWebDriverWait(15).withMessage("Messaggio di errore non trovato").until(ExpectedConditions.visibilityOfElementLocated(messaggio));
        } catch (TimeoutException e) {
            logger.error("Messaggio di errore non trovato: " + e.getMessage());
            Assert.fail("Messaggio di errore non trovato: " + e.getMessage());
        }
    }

    public void checkMessaggioDiErroreCF(){
        try {
            logger.info("controllo esistenza messaggio di errore");
            By messaggio = By.xpath("//p[contains(text(),'Inserimento errato')]");
            this.getWebDriverWait(15).withMessage("Messaggio di errore non trovato").until(ExpectedConditions.visibilityOfElementLocated(messaggio));
        } catch (TimeoutException e) {
            logger.error("Messaggio di errore non trovato: " + e.getMessage());
            Assert.fail("Messaggio di errore non trovato: " + e.getMessage());
        }
    }


    public void checkZipLink() throws IOException {
        boolean headless = System.getProperty("headless").equalsIgnoreCase("true");
        if (!headless) {
            try {
                logger.info("controllo esistenza link per scaricare zip");
                By zipLink = By.xpath("//a[contains(text(),'Download')]");
                this.getWebDriverWait(10).withMessage("Link per scaricare zip non trovato").until(ExpectedConditions.visibilityOfElementLocated(zipLink));
                this.element(zipLink).click();
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

                logger.info("Zip scaricato");
            } catch (TimeoutException e) {
                logger.error("Link per scaricare zip non trovato: " + e.getMessage());
                Assert.fail("Link per scaricare zip non trovato: " + e.getMessage());
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                String pageSrc= driver.getPageSource();
                logger.info("Page src" + pageSrc);
                By zipLink = By.xpath("//a[contains(text(),'Download')]");
                String url = this.element(zipLink).getAttribute("href");
                String workingDirectory = System.getProperty("user.dir");
                File downloadDirectory = new File(workingDirectory + "/src/test/resources/dataPopulation/zip");

                // Generate a unique filename for the downloaded ZIP file
                String fileName = "downloaded_" + System.currentTimeMillis() + ".zip";

                File downloadFile = new File(downloadDirectory, fileName);
                FileUtils.copyURLToFile(new URL(url), downloadFile, 1000, 1000);
                logger.info("ZIP file downloaded successfully.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    private static void pressTabKey(Robot robot, int times) {
        for (int i = 0; i < times; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(500);
        }
    }


    public void extractZip() throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        String zipDirectoryPath = workingDirectory + "/src/test/resources/dataPopulation/zip";
        String extractDirectoryPath = zipDirectoryPath + "/extract";

        // Find the latest ZIP file
        File latestZipFile = findLatestZipFile(zipDirectoryPath);
        if (latestZipFile == null) {
            throw new IOException("No ZIP file found in the directory: " + zipDirectoryPath);
        }

        // Extract the ZIP file
        ZipFile zip = new ZipFile(latestZipFile,zipPassword.toCharArray());
        zip.extractAll(extractDirectoryPath);

        // Log extracted files
        Files.walk(Paths.get(extractDirectoryPath)).forEach(path -> {
            if (Files.isRegularFile(path)) {
                System.out.println("Found file: " + path.toString());
            }
        });
    }

    public File findLatestZipFile(String directoryPath) throws IOException {
        try (Stream<Path> files = Files.list(Paths.get(directoryPath))) {
            return files
                    .filter(file -> !Files.isDirectory(file) && file.toString().endsWith(".zip"))
                    .max(Comparator.comparingLong(file -> file.toFile().lastModified()))
                    .map(Path::toFile)
                    .orElse(null);
        }
    }

    public void checkPassword(){
        try {
            logger.info("controllo esistenza password");
            By messaggio = By.xpath("//p[contains(text(),'Password:')]");
            this.getWebDriverWait(10).withMessage("Password non trovato").until(ExpectedConditions.visibilityOfElementLocated(messaggio));
            String password = this.element(messaggio).getText().split(": ")[1];
            setPassword(password);

        } catch (TimeoutException e) {
            logger.error("Password non trovato: " + e.getMessage());
            Assert.fail("Password non trovato: " + e.getMessage());
        }
    }

    public void EliminaFileZipEstratto() throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        String zipDirectoryPath = workingDirectory + "/src/test/resources/dataPopulation/zip";
        String extractDirectoryPath = zipDirectoryPath + "/extract";

        // Find the latest ZIP file
        File latestZipFile = findLatestZipFile(zipDirectoryPath);
        if (latestZipFile != null) {
            Files.deleteIfExists(latestZipFile.toPath());
            System.out.println("Deleted ZIP file: " + latestZipFile.getName());
        } else {
            System.out.println("No ZIP file found in the directory: " + zipDirectoryPath);
        }

        deleteFilesInDirectory(extractDirectoryPath, null);

        System.out.println("Cleanup completed successfully.");
    }

    public boolean trovaDocumentoConTitolo(String docName) throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        String extractDirectoryPath = workingDirectory + "/src/test/resources/dataPopulation/zip/extract";
        Path extractDir = Paths.get(extractDirectoryPath);

        try (Stream<Path> files = Files.walk(extractDir)) {
            return files
                    .filter(Files::isRegularFile)
                    .anyMatch(file -> file.getFileName().toString().equals(docName));
        }
    }

    private void deleteFilesInDirectory(String directoryPath, String extension) throws IOException {
        Path dir = Paths.get(directoryPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    if (extension == null || entry.toString().endsWith(extension)) {
                        Files.delete(entry);
                        System.out.println("Deleted file: " + entry.toString());
                    }
                }
            }
        } catch (IOException e) {
            throw new IOException("Failed to delete files in directory: " + directoryPath, e);
        }
    }

    public void clickResettaFiltri(){
      By bottoneReset = By.xpath("//button[@id='resetFilter']");
      getWebDriverWait(5).withMessage("Il bottone resetta non è cliccabile").until(ExpectedConditions.elementToBeClickable(bottoneReset));
      this.element(bottoneReset).click();
    }

    public void checkCampiPuliti(){
        if (numeroTicketInput.getAttribute("value").isEmpty() && codiceFiscaleInput.getAttribute("value").isEmpty()){
            logger.info("I campi sono puliti");
        }else {
            logger.error("I campi non sono puliti");
            Assert.fail("I campi non sono puliti");
        }
    }

    public void checkCodiceFiscale() {
        inputUid.sendKeys(this.codiceIdentificativoPF);
        buttonRicerca.click();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: " + e.getMessage());
            throw new RuntimeException(e);
        }
        String cfResult = CfPersonaFisica.getText().replace("Codice Fiscale: ", "");
        if (cfResult.equals(this.codiceFiscale)) {
            logger.info("codice fiscale corrispondente");
        } else {
            logger.error("codice fiscale diverso");
            Assert.fail("dopo ricerca di corrispondenza il codice fiscale risulta differente");
        }
    }

    public void loginHelpdeskNuovaScheda(Map<String, String> login) {
        this.getWebDriverWait(10).withMessage("Non si visualizza il campo email").until(ExpectedConditions.visibilityOf(this.emailInput));
        this.getWebDriverWait(10).withMessage("Non si visualizza il campo password").until(ExpectedConditions.visibilityOf(this.passwordInput));
        this.getWebDriverWait(10).withMessage("Non si visualizza il bottone LOGIN").until(ExpectedConditions.visibilityOf(this.loginButton));
        this.emailInput.sendKeys(login.get("utente"));
        this.passwordInput.sendKeys(login.get("password"));
        this.loginButton.click();
        logger.info("Visualizzazione pagina login corretta");
    }

    public void clickRicercaBottone(){
        getWebDriverWait(3).withMessage("Il bottone ricerca non è cliccabile").until(ExpectedConditions.elementToBeClickable(buttonRicerca));
        buttonRicerca.click();
    }

    public void spuntareDeanonimizzazioneDati(){
        getWebDriverWait(3).withMessage("Il Deanonimizzazione dati non è cliccabile").until(ExpectedConditions.elementToBeClickable(deanonimizzazioneDati));
        deanonimizzazioneDati.click();
    }


    public void inserimentoArcoTemporale() throws InterruptedException {
        WebTool.waitTime(20);
            By calendarButton = By.xpath("//*[@data-testid='CalendarIcon']");
            getWebDriverWait(5).until(ExpectedConditions.visibilityOfElementLocated(calendarButton));
            this.element(calendarButton).click();
            logger.info("Calendario cliccato");

        By previousMonth = By.xpath("//button[@aria-label='Previous month']");
        this.element(previousMonth).click();
        Thread.sleep(1000);
        By dateEleven = By.xpath("//button[contains(text(),'11')]");
        this.element(dateEleven).click();
    }
}
