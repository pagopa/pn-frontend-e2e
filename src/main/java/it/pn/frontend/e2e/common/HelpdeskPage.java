package it.pn.frontend.e2e.common;

import it.pn.frontend.e2e.model.enums.Disservice;
import it.pn.frontend.e2e.model.enums.Status;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.Getter;
import lombok.Setter;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
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
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


public class HelpdeskPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger(HelpdeskPage.class);


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

    private String codiceFiscale;
    private String zipPassword;
    private String codiceIdentificativoPF;

    @Setter
    @Getter
    private String headlessParam;

    private WebTool webTool;

    public HelpdeskPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }


    private static void pressTabKey(Robot robot, int times) {
        for (int i = 0; i < times; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(500);
        }
    }

    public void changePage(String HelpdeskURL) {
        driver.get(HelpdeskURL);
    }

    public void checkForm() {
        try {
            logger.info("Check form in corso");
            emailInput = driver.findElement(By.id("Email"));
            passwordInput = driver.findElement(By.id("Password"));
            loginButton = driver.findElement(By.id("buttonLogin"));
            getWebDriverWait(40).withMessage("email non presente").until(ExpectedConditions.visibilityOf(emailInput));
            getWebDriverWait(40).withMessage("password non presente").until(ExpectedConditions.visibilityOf(passwordInput));
            getWebDriverWait(40).withMessage("submit non presente").until(ExpectedConditions.visibilityOf(loginButton));
        } catch (TimeoutException e) {
            Assertions.fail("Form non presente errore: " + e.getMessage());
        }
    }

    public void checkHome() {
        try {
            logger.info("In attesa che la pagina home venga caricata...");

            getWebDriverWait(10)
                    .withMessage("La pagina home non è stata caricata correttamente: elemento 'Monitoraggio Piattaforma Notifiche' non trovato")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("cardTitle-Monitoraggio Piattaforma Notifiche")));

        } catch (TimeoutException e) {
            Assertions.fail("Errore caricamento home helpdesk: " + e.getMessage());
        }
    }

    public void waitLoadServiceTable() {

        List<WebElement> services = getWebDriverWait(10)
                .withMessage("Non è visibile la tabella dei disservizi o i servizi sono meno di 3")
                .until(d -> {
                    List<WebElement> elements = d.findElements(By.xpath(".//div[@data-field='functionality' and @role='cell']"));
                    if (elements.size() >= 3) {
                        return elements;
                    }
                    return null; // continua ad aspettare finché non ne trova almeno 3
                });

        if (services == null || services.isEmpty()) {
            Assertions.fail("Nessun servizio trovato entro il timeout");
        }

    }

    public void insertUsername(String user) {
        logger.info("inserisco email");
        emailInput = driver.findElement(By.id("Email"));
        emailInput.sendKeys(user);
    }

    public void insertPassword(String pwd) {
        logger.info("inserisco password");
        passwordInput = driver.findElement(By.id("Password"));
        passwordInput.sendKeys(pwd);
    }

    public void clickInviaButton() {
        logger.info("clicco il bottone login");
        loginButton = driver.findElement(By.id("buttonLogin"));
        loginButton.click();
    }

    public void clickMonitoraggio() {

        try {
            logger.info("In attesa che la card 'Monitoraggio Piattaforma Notifiche' sia cliccabile...");
            WebElement monitoraggioPN = getWebDriverWait(10)
                    .withMessage("La card 'Monitoraggio Piattaforma Notifiche' non è cliccabile entro il timeout")
                    .until(ExpectedConditions.elementToBeClickable(By.id("cardTitle-Monitoraggio Piattaforma Notifiche")));

            monitoraggioPN.click();

        } catch (TimeoutException e) {
            Assertions.fail("Card 'Monitoraggio Piattaforma Notifiche' non cliccabile: " + e.getMessage());
        }


    }

    public void handleDisservizio(Disservice disservizio, Status status) {
        waitLoadServiceTable();
        serviceStatusButtons = driver.findElements(By.xpath(".//button[@role='menuitem']"));
        getWebDriverWait(10).withMessage("Non sono visibili i pulsanti per il disservizio").until(ExpectedConditions.visibilityOfAllElements(serviceStatusButtons));
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getText().equals(disservizio.getValue())) {
                logger.info("Clicco sul menu del disservizio: {}", disservizio.getValue());
                serviceStatusButtons.get(i).click();
                break;
            }
        }
        // this is useful to check if we correctly clicked on the menu and the tooltip is displayed otherwise we fail the test
        WebElement tooltip = element(By.xpath(".//div[@role='tooltip']"));
        if (!tooltip.isDisplayed()) {
            logger.error("Non è stato possibile visualizzare il tooltip per il disservizio");
            Assertions.fail("Non è stato possibile visualizzare il tooltip per il disservizio");
        }
        WebElement button = tooltip.findElement(By.id(status.getValue() + "-insert"));
        getWebDriverWait(10).withMessage("Il pulsante: " + status.getValue() + " non è visibile o cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(button),
                ExpectedConditions.elementToBeClickable(button)
        ));
        button.click();
        WebElement buttonInsert = element(By.id("buttonInserisciDisservizio"));
        getWebDriverWait(10).withMessage("Il pulsante di inserimento del servizio non è visibile o cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(buttonInsert),
                ExpectedConditions.elementToBeClickable(buttonInsert)
        ));
        buttonInsert.click();
        //ok va sistemato il codice
        WebElement alertSuccess = element(By.xpath(".//div[@role='alert']"));
        getWebDriverWait(10).withMessage("L'alert di successo post-inserimento servizio o disservizio non è visibile").until(ExpectedConditions.visibilityOf(alertSuccess));
        logger.info("{} è stato cambiato con successo in {}", disservizio.getValue(), status.getValue());
    }

    public void handleDisservizioNew(Disservice disservice, Status status) {
        waitLoadServiceTable();
        logger.info("*-*-*-*-*-*-*-*- handleDisservizioNew *-*-*-*-*-*-*-*-*-**-");
        clickInserisciRisolviKOCreazioneNotifiche(disservice, status);
        selezionaCheckBoxPopUpCreazioneNotifiche();
        clickInserisciRisolviKOPopUpInserisciEvento();
        verificaAlert();
    }

    public void handleRisolviDisservizionew(Disservice disservice, Status status) {
        driver.navigate().refresh();
        waitLoadServiceTable();
        logger.info("*-*-*-*-*-*-*-*- handleRisolviDisservizionew *-*-*-*-*-*-*-*-*-**-");
        clickInserisciRisolviKOCreazioneNotifiche(disservice, status);
        scriviTesto();
        clickInserisciRisolviKOPopUpInserisciEvento();
        selezionaCheckBoxPopUpCreazioneNotifiche();
        clickInserisciRisolviKOPopUpInserisciEvento();
        verificaAlert();

    }

    private void scriviTesto() {
        WebElement campoEditabile = getWebDriverWait(10)
                .withMessage("Impossibile trovare il campo editabile nel pop up Risolvi Evento")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ProseMirror[contenteditable='true']")));

        String testoCasuale = "TEST AUTOMATICO: " + (new Random().nextInt(90000) + 10000);
        campoEditabile.sendKeys(testoCasuale);
    }

    public void verificaAlert() {
        getWebDriverWait(10)
                .withMessage("L'alert di successo post-inserimento servizio o disservizio non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@role='alert']")));
    }

    private void clickInserisciRisolviKOPopUpInserisciEvento() {
        WebElement bottoneInserisciKO = getWebDriverWait(30)
                .withMessage("Impossibile trovare checkbox nel pop-up Inseriscvi Evento | Creazine Notifiche")
                .until(ExpectedConditions.elementToBeClickable(By.id("createEvent")));
        bottoneInserisciKO.click();
    }

    private void selezionaCheckBoxPopUpCreazioneNotifiche() {
        WebElement checkbox = getWebDriverWait(30)
                .withMessage("Impossibile trovare checkbox nel pop-up Inseriscvi Evento | Creazine Notifiche")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'MuiCheckbox-root') and .//input[@type='checkbox']]")));
        checkbox.click();
    }

    private void clickInserisciRisolviKOCreazioneNotifiche(Disservice disservice, Status status) {
        logger.info("Cerco la riga 'Creazione Notifiche' e clicco su 'Inserisci KO'...");

        WebElement bottoneInserisciKO = getWebDriverWait(30)
                .withMessage("Bottone 'Inserisci KO' non trovato nella riga '" + disservice.getValue() + "'")
                .until(driver -> driver.findElement(By.xpath(
                        "//div[@role='row' and .//p[text()='" + disservice.getValue() + "']]//button[@id='" + status.getValue() + "']"
                )));

        bottoneInserisciKO.click();
    }

    public boolean checkServiceStatus(Disservice disservizio) {
        waitLoadServiceTable();
        serviceDates = driver.findElements(By.xpath(".//div[@data-field='data' and @role='cell']"));
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
            List<WebElement> dateElements = getWebDriverWait(10)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-field='data']")));

            if (!dateElements.isEmpty() && dateElements.size() > 1) {
                WebElement secondDateElement = dateElements.get(1);
                String text = secondDateElement.getText();
                logger.info("TEXT: {}",text);
                if (text != null && !text.trim().isEmpty()) {
                    logger.info("Disservizio già in corso rilevato.");
                    return true;
                }
            }
            logger.info("Nessun disservizio in corso.");
            return false;

        } catch (TimeoutException e) {
            logger.warn("Nessun disservizio trovato: timeout durante l'attesa degli elementi.");
            return false;
        }
    }

    public void clickSezioneRicerca() {
        try {
            By ricercaButton = By.id("cardTitle-Ricerca ed estrazione dati");
            logger.info("clicco sulla card ricerca ed estrazione dati");
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(ricercaButton));
            elements(ricercaButton).get(0).click();
        } catch (TimeoutException e) {
            Assertions.fail("Card ricerca non cliccabile: " + e.getMessage());
        }
    }

    public void checkRicercaPage() {
        logger.info("check pagina ricerca ed estrazione dati");
        try {
            WebElement buttonResetFiltri = driver.findElement(By.id("resetFilter"));
            selectTypeOfEstrazioneDati = driver.findElement(By.id("Tipo Estrazione"));
            numeroTicketInput = driver.findElement(By.id("Numero Ticket"));
            codiceFiscaleInput = driver.findElement(By.id("Codice Fiscale"));
            buttonRicerca = driver.findElement(By.id("ricerca"));

            getWebDriverWait(30).withMessage("Tipo estrazione non trovato").until(ExpectedConditions.visibilityOf(selectTypeOfEstrazioneDati));
            getWebDriverWait(30).withMessage("numero ticket input non trovato").until(ExpectedConditions.visibilityOf(numeroTicketInput));
            getWebDriverWait(30).withMessage("codice fiscale input non trovato").until(ExpectedConditions.visibilityOf(codiceFiscaleInput));
            getWebDriverWait(30).withMessage("button ricerca non trovato").until(ExpectedConditions.visibilityOf(buttonRicerca));
            getWebDriverWait(30).withMessage("button reset filtri non trovato").until(ExpectedConditions.visibilityOf(buttonResetFiltri));
        } catch (TimeoutException e) {
            Assertions.fail("home ricerca non caricata correttamente: " + e.getMessage());
        }
    }

    public void logout() {
        try {
            logger.info("controllo esistenza bottone logout");
            buttonLogout = driver.findElement(By.id("logout"));
            getWebDriverWait(30).withMessage("bottone logout non trovato").until(ExpectedConditions.visibilityOf(buttonLogout));
            logger.info("click sul bottone logout");
            buttonLogout.click();
            logger.info("apertura dialog di conferma logout");
            logger.info("controllo esistenza pulsante conferma logout");
            getWebDriverWait(30).withMessage("bottone conferma logout non trovato").until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Esci')]")));
            By buttonConfermaLogout = By.xpath("//button[contains(text(),'Esci')]");
            logger.info("click conferma logout");
            elements(buttonConfermaLogout).get(0).click();
        } catch (TimeoutException e) {
            Assertions.fail("logout non riuscito correttamente: " + e.getMessage());
        }
    }

    public void insertCfAndRicercaOnPage(String codiceFiscale) {
        logger.info("inserisco numero ticket");
        numeroTicketInput = driver.findElement(By.id("Numero Ticket"));
        numeroTicketInput.sendKeys("testTAFE01");
        logger.info("inserisco codice fiscale");
        setCodiceFiscale(codiceFiscale);
        codiceFiscaleInput = driver.findElement(By.id("Codice Fiscale"));
        codiceFiscaleInput.sendKeys(codiceFiscale);
        logger.info("clicco sul bottone di ricerca");
        try {
            buttonRicerca = driver.findElement(By.id("ricerca"));
            getWebDriverWait(30).withMessage("bottone per la ricerca non trovato").until(ExpectedConditions.elementToBeClickable(buttonRicerca));
            buttonRicerca.click();
        } catch (TimeoutException e) {
            Assertions.fail("bottone non cliccabile:" + e.getMessage());

        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void insertCF(String codiceFiscale) {
        logger.info("inserisco codice fiscale");
        setCodiceFiscale(codiceFiscale);
        codiceFiscaleInput = driver.findElement(By.id("Codice Fiscale"));
        codiceFiscaleInput.sendKeys(codiceFiscale);

    }

    public void insertIunAndRicercaOnPage(String iun) {
        logger.info("inserisco numero ticket");
        numeroTicketInput = driver.findElement(By.id("Numero Ticket"));
        numeroTicketInput.sendKeys("testTAFE01");
        logger.info("inserisco codice IUN");
        iunInput = driver.findElement(By.id("IUN"));
        iunInput.sendKeys(iun);
        logger.info("clicco sul bottone di ricerca");
        buttonRicerca = driver.findElement(By.id("ricerca"));
        getWebDriverWait(30).withMessage("bottone per la ricerca non trovato").until(ExpectedConditions.elementToBeClickable(buttonRicerca));
        buttonRicerca.click();
        webTool.waitTime(3);
    }

    public void insertIun(String iun) {
        logger.info("inserisco codice IUN");
        iunInput = driver.findElement(By.id("IUN"));
        iunInput.sendKeys(iun);

    }

    public void insertUid(String uid) {
        logger.info("inserisco codice univoco");
        inputUid = driver.findElement(By.id("Codice Univoco (uid)"));
        inputUid.sendKeys(uid);

    }

    public void insertNumeroTicket() {
        logger.info("inserisco numero ticket");
        numeroTicketInput = driver.findElement(By.id("Numero Ticket"));
        numeroTicketInput.sendKeys("testTAFE01");
    }

    public void checkUid() {
        try {
            logger.info("controllo esistenza codice univoco");
            Uid = driver.findElement(By.xpath("//p[contains(text(),'Codice Univoco')]"));
            getWebDriverWait(30).withMessage("Codice univoco non trovato").until(ExpectedConditions.visibilityOf(Uid));
            setCodiceIdentificativoPF(Uid.getText().replace("Codice Univoco: ", ""));
        } catch (TimeoutException e) {
            Assertions.fail("codice univoco non trovato: " + e.getMessage());
        }
    }

    public void setCodiceIdentificativoPF(String codiceIdentificativoPF) {
        this.codiceIdentificativoPF = codiceIdentificativoPF;
    }

    public void setCodiceFiscale(String codiceFiscale) {

        this.codiceFiscale = codiceFiscale;
    }

    public void setPassword(String password) {

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
            getWebDriverWait(30).withMessage("opzione ottieni cf non trovata").until(ExpectedConditions.visibilityOfElementLocated(selectTypeOfOttieniCF));
            element(selectTypeOfOttieniCF).click();
            By checkUidIsDisplayed = By.id("Codice Univoco (uid)");
            this.getWebDriverWait(30).withMessage("input uid non trovato").until(ExpectedConditions.visibilityOfElementLocated(checkUidIsDisplayed));
        } catch (TimeoutException e) {
            Assertions.fail("opzione ottieni cf non trovata: " + e.getMessage());
        }
    }

    public void selectOttieniNotifica() {
        logger.info("click su tipo estrazione dati");
        By inputTipoEstrazione = By.xpath("//div[@data-testid='select-Tipo Estrazione']");
        element(inputTipoEstrazione).click();
        logger.info("selezione ottieni notifica");
        By selectTypeOfOttieniCF = By.xpath("//li[contains(text(),'Ottieni notifica')]");
        logger.info("controllo esistenza selezione");
        getWebDriverWait(30).withMessage("opzione ottieni notifica non trovata").until(ExpectedConditions.visibilityOfElementLocated(selectTypeOfOttieniCF));
        element(selectTypeOfOttieniCF).click();
        By checkIunIsDisplayed = By.id("IUN");
        getWebDriverWait(30).withMessage("input IUN non trovato").until(ExpectedConditions.visibilityOfElementLocated(checkIunIsDisplayed));
    }

    public void selectOttieniLogCompleti() {
        logger.info("click su tipo estrazione dati");
        By inputTipoEstrazione = By.xpath("//div[@data-testid='select-Tipo Estrazione']");
        element(inputTipoEstrazione).click();

        logger.info("selezione ottieni log completi");
        By selectTypeOfOttieniLogCompleti = By.xpath("//li[contains(text(),'Ottieni log completi')]");
        logger.info("controllo esistenza selezione");
        getWebDriverWait(30).withMessage("opzione ottieni log completi non trovata").until(ExpectedConditions.visibilityOfElementLocated(selectTypeOfOttieniLogCompleti));
        element(selectTypeOfOttieniLogCompleti).click();
        By checkNumeroTicketIsDisplayed = By.id("Numero Ticket");
        getWebDriverWait(30).withMessage("input Numero Ticket non trovato").until(ExpectedConditions.visibilityOfElementLocated(checkNumeroTicketIsDisplayed));
    }

    public void checkMessaggioSuccesso() {

        logger.info("controllo esistenza messaggio di successo");
        By messaggio = By.xpath("//p[contains(text(),'Operazione completata con successo')]");
        getWebDriverWait(90).withMessage("Messaggio di successo non trovato").until(ExpectedConditions.visibilityOfElementLocated(messaggio));
    }

    public void checkMessaggioDiErroreData() {
        try {
            logger.info("controllo esistenza messaggio di errore");
            By messaggio = By.xpath("//p[contains(text(),'intervallo temporale non può superare i 3 mesi')]");
            getWebDriverWait(15).withMessage("Messaggio di errore non trovato").until(ExpectedConditions.visibilityOfElementLocated(messaggio));
        } catch (TimeoutException e) {
            Assertions.fail("Messaggio di errore non trovato: " + e.getMessage());
        }
    }

    public void checkMessaggioDiErroreIUN() {
        logger.info("controllo esistenza messaggio di errore");
        By messaggio = By.xpath("//p[contains(text(),'Inserimento errato')]");
        getWebDriverWait(15).withMessage("Messaggio di errore non trovato").until(ExpectedConditions.visibilityOfElementLocated(messaggio));
    }

    public void checkMessaggioDiErroreCF() {
        logger.info("controllo esistenza messaggio di errore");
        By messaggio = By.xpath("//p[contains(text(),'Inserimento errato')]");
        getWebDriverWait(15).withMessage("Messaggio di errore non trovato").until(ExpectedConditions.visibilityOfElementLocated(messaggio));
    }


    public void checkZipLink() throws IOException, AWTException {

        By zipLinkLocator = By.xpath("//a[contains(text(),'Download')]");

        // Aspetta il link e prendi l'URL del file ZIP
        WebElement zipLink = getWebDriverWait(10)
                .withMessage("Link 'Download' non trovato")
                .until(ExpectedConditions.elementToBeClickable(zipLinkLocator));

        String url = zipLink.getAttribute("href");
        String workingDirectory = System.getProperty("user.dir");
        File downloadDir = new File(workingDirectory + "/src/test/resources/dataPopulation/zip");

        // Crea la cartella se non esiste
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }

        // Scarica il file
        String fileName = "downloaded_" + System.currentTimeMillis() + ".zip";
        File destFile = new File(downloadDir, fileName);
        FileUtils.copyURLToFile(new URL(url), destFile, 5000, 5000);


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
        ZipFile zip = new ZipFile(latestZipFile, zipPassword.toCharArray());
        zip.extractAll(extractDirectoryPath);

        // Log extracted files
        Files.walk(Paths.get(extractDirectoryPath)).forEach(path -> {
            if (Files.isRegularFile(path)) {
                logger.info("Found file: {}", path.toString());
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

    public void checkPassword() {
        logger.info("controllo esistenza password");
        WebElement messaggio = getWebDriverWait(10)
                .withMessage("Impossibile trovare la Passwrd ")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Password:')]")));
        String password = messaggio.getText().split(": ")[1];
        setPassword(password);
    }

    public void EliminaFileZipEstratto() throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        String zipDirectoryPath = workingDirectory + "/src/test/resources/dataPopulation/zip";
        String extractDirectoryPath = zipDirectoryPath + "/extract";

        // Delete all ZIP files
        deleteAllZipFiles(zipDirectoryPath);

        deleteFilesInDirectory(extractDirectoryPath, null);
        logger.info("Cleanup completed successfully.");
    }

    private void deleteAllZipFiles(String directoryPath) throws IOException {
        try (Stream<Path> files = Files.list(Paths.get(directoryPath))) {
            files.filter(file -> file.toString().endsWith(".zip"))
                    .forEach(file -> {
                        try {
                            Files.deleteIfExists(file);
                            logger.info("Deleted ZIP file: {}", file.getFileName());
                        } catch (IOException e) {
                            logger.error("Failed to delete ZIP file: {}", file.getFileName());
                            e.printStackTrace();
                        }
                    });
        }
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

    public boolean trovaDocumentoDaZipConDimensioni(String docName) throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        String extractDirectoryPath = workingDirectory + "/src/test/resources/dataPopulation/zip/extract/" + docName;
        Path extractPath = Paths.get(extractDirectoryPath);
        long fileSize = Files.size(extractPath);
        logger.info("File {} ha size {}", docName, fileSize);
        return fileSize > 0;
    }

    public boolean trovaTestoInDocumentoDaZip(String docName, String searchText) throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        String extractDirectoryPath = workingDirectory + "/src/test/resources/dataPopulation/zip/extract/" + docName;
        Path extractPath = Paths.get(extractDirectoryPath);
        try (Stream<String> lines = Files.lines(extractPath)) {
            boolean found = lines.anyMatch(line -> line.contains(searchText));
            if (found) {
                logger.info("Testo {} trovato in file {}", searchText, docName);
            } else {
                logger.info("Testo {} non trovato in file {}", searchText, docName);
            }
            return found;
        } catch (IOException e) {
            logger.error("Errore nel leggere il file {}", docName, e);
            Assertions.fail("Errore nel leggere il file " + docName + ": " + e.getMessage());
            return false;
        }
    }

    private void deleteFilesInDirectory(String directoryPath, String extension) throws IOException {
        Path dir = Paths.get(directoryPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry) && (extension == null || entry.toString().endsWith(extension))) {
                    Files.delete(entry);
                    logger.info("Deleted file: {}", entry.toString());
                }

            }
        } catch (IOException e) {
            throw new IOException("Failed to delete files in directory: " + directoryPath, e);
        }
    }

    public void clickResettaFiltri() {
        WebElement bottoneReset = driver.findElement(By.xpath("//button[@id='resetFilter']"));
        getWebDriverWait(5).withMessage("Il bottone resetta non è cliccabile").until(ExpectedConditions.elementToBeClickable(bottoneReset));
        bottoneReset.click();
    }

    public void checkCampiPuliti() {
        numeroTicketInput = driver.findElement(By.id("Numero Ticket"));
        codiceFiscaleInput = driver.findElement(By.id("Codice Fiscale"));
        Assertions.assertTrue(numeroTicketInput.getAttribute("value").isEmpty() && codiceFiscaleInput.getAttribute("value").isEmpty(), "I campi non sono puliti");
        logger.info("I campi sono puliti");
    }

    public void checkCodiceFiscale() {
        inputUid = driver.findElement(By.id("Codice Univoco (uid)"));
        inputUid.sendKeys(this.codiceIdentificativoPF);
        buttonRicerca = driver.findElement(By.id("ricerca"));
        buttonRicerca.click();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        CfPersonaFisica = driver.findElement(By.xpath("//p[contains(text(),'Codice Fiscale')]"));
        String cfResult = CfPersonaFisica.getText().replace("Codice Fiscale: ", "");
        if (cfResult.equals(this.codiceFiscale)) {
            logger.info("codice fiscale corrispondente");
        } else {
            logger.error("codice fiscale diverso");
            Assertions.fail("dopo ricerca di corrispondenza il codice fiscale risulta differente");
        }
    }

    public void loginHelpdeskNuovaScheda(Map<String, String> login) {
        webTool.waitTime(10);
        emailInput = driver.findElement(By.id("Email"));
        passwordInput = driver.findElement(By.id("Password"));
        loginButton = driver.findElement(By.id("buttonLogin"));
        getWebDriverWait(10).withMessage("Non si visualizza il campo email").until(ExpectedConditions.visibilityOf(emailInput));
        getWebDriverWait(10).withMessage("Non si visualizza il campo password").until(ExpectedConditions.visibilityOf(passwordInput));
        getWebDriverWait(10).withMessage("Non si visualizza il bottone LOGIN").until(ExpectedConditions.visibilityOf(loginButton));
        emailInput.sendKeys(login.get("utente"));
        passwordInput.sendKeys(login.get("password"));
        loginButton.click();
        logger.info("Visualizzazione pagina login corretta");
    }

    public void clickRicercaBottone() {
        buttonRicerca = driver.findElement(By.id("ricerca"));
        getWebDriverWait(3).withMessage("Il bottone ricerca non è cliccabile").until(ExpectedConditions.elementToBeClickable(buttonRicerca));
        buttonRicerca.click();
    }

    public void spuntareDeanonimizzazioneDati() {
        deanonimizzazioneDati = driver.findElement(By.xpath("//div[1]/div[4]/div/label/span[1]"));
        getWebDriverWait(3).withMessage("Il Deanonimizzazione dati non è cliccabile").until(ExpectedConditions.elementToBeClickable(deanonimizzazioneDati));
        deanonimizzazioneDati.click();
    }


    public void inserimentoArcoTemporale() {
        webTool.waitTime(60);

        WebElement calendarButton = driver.findElement(By.xpath("//button[contains(@aria-label, 'Choose date')]"));
        getWebDriverWait(20).until(ExpectedConditions.visibilityOf(calendarButton));
        calendarButton.click();
        WebElement previousMonth = driver.findElement(By.xpath("//button[@aria-label='Previous month']"));
        previousMonth.click();
        webTool.waitTime(1);
        WebElement dateEleven = driver.findElement(By.xpath("//button[contains(text(),'11')]"));
        dateEleven.click();
    }


}