package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Set;

public class UtentiPGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger(UtentiPGPage.class);

    @FindBy(xpath = "//button[contains(text(),'Aggiungi utente')]")
    WebElement addUserButton;
    @FindBy(xpath = "//button[contains(text(),'Indietro')]")
    WebElement indietroButton;
    @FindBy(xpath = "//div/h4[contains(text(),'Aggiungi un nuovo utente')]")
    WebElement titoloAggiungiUtente;
    @FindBy(xpath = "//div/p[contains(text(),'Inserisci i dati dell’utente,')]")
    WebElement sottotitoloAggiungiUtente;
    @FindBy(id = "taxCode")
    WebElement codiceFiscaleBox;
    @FindBy(id = "name")
    WebElement nameBox;
    @FindBy(id = "surname")
    WebElement surnameBox;
    @FindBy(id = "email")
    WebElement emailBox;
    @FindBy(id = "confirmEmail")
    WebElement confirmEmailBox;
    @FindBy(id = "select-label-products")
    WebElement selectProductDropdown;
    @FindBy(xpath = "//button[contains(text(),'Continua')]")
    WebElement continueButton;
    @FindBy(xpath = "//p[contains(text(),'Amministratore')]")
    WebElement adminRadioButton;
    @FindBy(xpath = "//p[contains(text(),'Vuoi assegnare a')]")
    WebElement confirmPopup;
    @FindBy(xpath = "//button[contains(text(),'Annulla')]")
    WebElement cancelButton;
    @FindBy(xpath = "//button[contains(text(),'Assegna')]")
    WebElement assegnaButton;
    @FindBy(xpath = "//p[contains(text(),'Utente aggiunto correttamente')]")
    WebElement successMessage;
    @FindBy(xpath = "//button[contains(text(),'Modifica')]")
    WebElement modificaButton;
    @FindBy(id = "confirmEmail-helper-text")
    WebElement wrongMailErrorMessage;
    @FindBy(xpath = "//button[contains(text(),'Conferma')]")
    WebElement confermaButton;
    @FindBy(xpath = "//p[contains(text(),'Profilo modificato correttamente')]")
    WebElement successModifyMessage;
    @FindBy(xpath = "//span[contains(text(),'Rimuovi')]")
    WebElement removeButton;
    @FindBy(xpath = "//h6[contains(text(),'Elimina Utente')]")
    WebElement removeUserPopup;
    @FindBy(xpath = "//button[contains(text(),'Rimuovi')]")
    WebElement removeRuoloButton;
    @FindBy(xpath = "//p[contains(text(),'Utente rimosso correttamente')]")
    WebElement roleDeletedMessage;
    @FindBy(xpath = "//span[contains(text(),'Utenti')]")
    WebElement sezioneUtenti;

    @Autowired
    @Lazy
    private WebDriverConfig webDriverConfig;

    private ScegliSpidPGPage scegliSpidPGPage;

    private AccediAreaRiservataPGPage accediAreaRiservataPGPage;

    private LoginPGPagoPAPage loginPGPagoPAPage;

    private AutorizzaInvioDatiPGPage autorizzaInvioDatiPGPage;

    private WebTool webTool;

    @Getter
    @Setter
    private String environment;


    public UtentiPGPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
        scegliSpidPGPage = new ScegliSpidPGPage(driver);
        accediAreaRiservataPGPage = new AccediAreaRiservataPGPage(driver);
        loginPGPagoPAPage = new LoginPGPagoPAPage(driver);
        autorizzaInvioDatiPGPage = new AutorizzaInvioDatiPGPage(driver);
    }


    //    public void clickSezioneUtenti(String testo) {
//        webTool.waitTime(10);
//        sezioneUtenti = driver.findElement(By.xpath("//span[contains(text(),'" + testo + "')]"));
//        getWebDriverWait(10).withMessage("La sezione Utenti non è cliccabile").until(ExpectedConditions.elementToBeClickable(sezioneUtenti));
//        sezioneUtenti.click();
//        //build url
//        String companyId = "d0f52c7d-76d5-4520-8971-edffeb5b46d5";
//        if (webDriverConfig != null) {
//            environment = webDriverConfig.getEnvironment();
//        }
//
//        String utentiUrl = "https://imprese." + environment + ".notifichedigitali.it/dashboard/" + companyId + "/users";
//        //switch tab
//        String parentWindowHandle = driver.getWindowHandle();
//        Set<String> windowHandles = driver.getWindowHandles();
//        for (String handle : windowHandles) {
//            if (!handle.equals(parentWindowHandle)) {
//                this.driver.switchTo().window(handle);
//                logger.info(driver.getTitle());
//                break;
//            }
//        }
//    }
    public void clickSezioneUtenti(String testo) {
        webTool.waitTime(10);

        By sezioneUtentiLocator = By.xpath("//span[contains(text(),'" + testo + "')]");

        WebElement sezioneUtenti = getWebDriverWait(10)
                .withMessage("La sezione Utenti con testo '" + testo + "' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(sezioneUtentiLocator));

        sezioneUtenti.click();
        logger.info("Click sulla sezione Utenti con testo '{}'", testo);

        // Build URL
        String companyId = "d0f52c7d-76d5-4520-8971-edffeb5b46d5";
        if (webDriverConfig != null) {
            environment = webDriverConfig.getEnvironment();
        }

        String utentiUrl = "https://imprese." + environment + ".notifichedigitali.it/dashboard/" + companyId + "/users";

        // Switch tab se necessario
        String parentWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                logger.info("Titolo della nuova finestra: {}", driver.getTitle());
                break;
            }
        }
    }


    //    public void clickSezioneUtentiDaRiepilogo() {
//        sezioneUtenti = driver.findElement(By.xpath("//span[contains(text(),'Utenti')]"));
//        getWebDriverWait(10).withMessage("Il sezione Utenti non è cliccabile").until(ExpectedConditions.elementToBeClickable(sezioneUtenti));
//        sezioneUtenti.click();
//    }
    public void clickSezioneUtentiDaRiepilogo() {
        By sezioneUtentiLocator = By.xpath("//span[contains(text(),'Utenti')]");

        WebElement sezioneUtenti = getWebDriverWait(10)
                .withMessage("La sezione 'Utenti' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(sezioneUtentiLocator));

        sezioneUtenti.click();
        logger.info("Click sulla sezione 'Utenti' effettuato");
    }


    public void loginUtenti(String nome, String pwd) {
        accediAreaRiservataPGPage.waitLoadAccediAreaRiservataPGPage();
        accediAreaRiservataPGPage.clickSpidButton();

        scegliSpidPGPage.clickTestButton();

        loginPGPagoPAPage.waitLoadLoginPGPage();
        loginPGPagoPAPage.insertUsername(nome);
        loginPGPagoPAPage.insertPassword(pwd);
        loginPGPagoPAPage.clickInviaButton();

        autorizzaInvioDatiPGPage.waitLoadAutorizzaInvioDatiPGPage();
        autorizzaInvioDatiPGPage.clickInviaButton();
    }

//    public void waitLoadUtentiPage() {
//        try {
//            webTool.waitTime(15);
//            WebElement utentiPageTitle = driver.findElement(By.xpath("//div/h4[contains(text(),'Utenti')]"));
//            WebElement sottoTitolo = driver.findElement(By.xpath("//div/p[contains(text(),'Gestisci gli utenti')]"));
//            WebElement addUserButton = driver.findElement(By.xpath("//button[contains(text(),'Aggiungi utente')]"));
//            WebElement filtraButton = driver.findElement(By.xpath("//button[contains(text(),'Filtra')]"));
//            WebElement utentiTable = driver.findElement(By.id("UsersSearchTableBox"));
//            getWebDriverWait(10).withMessage("il titolo Utenti della pagina utenti non è visibile").until(ExpectedConditions.visibilityOf(utentiPageTitle));
//            getWebDriverWait(10).withMessage("il sottotitolo della pagina utenti non è visibile").until(ExpectedConditions.visibilityOf(sottoTitolo));
//            getWebDriverWait(10).withMessage("il bottone aggiungi utente della pagina utenti non è visibile").until(ExpectedConditions.visibilityOf(addUserButton));
//            getWebDriverWait(10).withMessage("il bottone filtra della pagina utenti non è visibile").until(ExpectedConditions.visibilityOf(filtraButton));
//            getWebDriverWait(10).withMessage("la tabella della pagina utenti non è visibile").until(ExpectedConditions.visibilityOf(utentiTable));
//            logger.info("Si visualizza correttamente utenti page");
//        } catch (TimeoutException e) {
//            Assertions.fail("Non si visualizza correttamente utenti page con errore:" + e.getMessage());
//        }
//    }

    public void waitLoadUtentiPage() {
        By utentiPageTitleLocator = By.xpath("//div/h4[contains(text(),'Utenti')]");
        By sottoTitoloLocator = By.xpath("//div/p[contains(text(),'Gestisci gli utenti')]");
        By addUserButtonLocator = By.xpath("//button[contains(text(),'Aggiungi utente')]");
        By filtraButtonLocator = By.xpath("//button[contains(text(),'Filtra')]");
        By utentiTableLocator = By.id("UsersSearchTableBox");

        webTool.waitTime(15);

        getWebDriverWait(10)
                .withMessage("Il titolo 'Utenti' della pagina utenti non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(utentiPageTitleLocator));

        getWebDriverWait(10)
                .withMessage("Il sottotitolo della pagina utenti non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(sottoTitoloLocator));

        getWebDriverWait(10)
                .withMessage("Il bottone 'Aggiungi utente' della pagina utenti non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(addUserButtonLocator));

        getWebDriverWait(10)
                .withMessage("Il bottone 'Filtra' della pagina utenti non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(filtraButtonLocator));

        getWebDriverWait(10)
                .withMessage("La tabella della pagina utenti non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(utentiTableLocator));

        logger.info("La pagina Utenti si visualizza correttamente");
    }


    //    public void clickAddUserButton() {
//        addUserButton = driver.findElement(By.xpath("//button[contains(text(),'Aggiungi utente')]"));
//        getWebDriverWait(10).withMessage("il bottone aggiungi utente non è cliccabile").until(ExpectedConditions.elementToBeClickable(addUserButton));
//        addUserButton.click();
//    }
    public void clickAddUserButton() {
        By addUserButtonLocator = By.xpath("//button[contains(text(),'Aggiungi utente')]");
        WebElement addUserButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Aggiungi utente' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(addUserButtonLocator));

        addUserButton.click();
    }


    //    public void waitLoadAggiungiUtentePage() {
//        indietroButton = driver.findElement(By.xpath("//button[contains(text(),'Indietro')]"));
//        titoloAggiungiUtente = driver.findElement(By.xpath("//div/h4[contains(text(),'Aggiungi un nuovo utente')]"));
//        sottotitoloAggiungiUtente = driver.findElement(By.xpath("//div/p[contains(text(),'Inserisci i dati dell’utente,')]"));
//        codiceFiscaleBox = driver.findElement(By.id("taxCode"));
//        nameBox = driver.findElement(By.id("name"));
//        surnameBox = driver.findElement(By.id("surname"));
//        emailBox = driver.findElement(By.id("email"));
//        confirmEmailBox = driver.findElement(By.id("confirmEmail"));
//        selectProductDropdown = driver.findElement(By.id("select-label-products"));
//        continueButton = driver.findElement(By.xpath("//button[contains(text(),'Continua')]"));
//        getWebDriverWait(10).withMessage("il bottone indietro Utenti / Aggiungi nuovo utente della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(indietroButton));
//        getWebDriverWait(10).withMessage("il titolo della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(titoloAggiungiUtente));
//        getWebDriverWait(10).withMessage("il sottotitolo della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(sottotitoloAggiungiUtente));
//        getWebDriverWait(10).withMessage("il box codice fiscale della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(codiceFiscaleBox));
//        getWebDriverWait(10).withMessage("il box nome della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(nameBox));
//        getWebDriverWait(10).withMessage("il box cognome della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(surnameBox));
//        getWebDriverWait(10).withMessage("il box email della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(emailBox));
//        getWebDriverWait(10).withMessage("il box conferma email della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(confirmEmailBox));
//        getWebDriverWait(10).withMessage("il combobox seleziona prodotto della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(selectProductDropdown));
//        getWebDriverWait(10).withMessage("il bottone continua della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(continueButton));
//        logger.info("Si visualizza correttamente aggiungi nuovo utente page");
//    }
    public void waitLoadAggiungiUtentePage() {
        By indietroButtonLocator = By.xpath("//button[contains(text(),'Indietro')]");
        By titoloAggiungiUtenteLocator = By.xpath("//div/h4[contains(text(),'Aggiungi un nuovo utente')]");
        By sottotitoloAggiungiUtenteLocator = By.xpath("//div/p[contains(text(),'Inserisci i dati dell’utente,')]");
        By codiceFiscaleBoxLocator = By.id("taxCode");
        By nameBoxLocator = By.id("name");
        By surnameBoxLocator = By.id("surname");
        By emailBoxLocator = By.id("email");
        By confirmEmailBoxLocator = By.id("confirmEmail");
        By selectProductDropdownLocator = By.id("select-label-products");
        By continueButtonLocator = By.xpath("//button[contains(text(),'Continua')]");

        getWebDriverWait(10)
                .withMessage("Il bottone 'Indietro' della pagina aggiungi nuovo utente non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(indietroButtonLocator));

        getWebDriverWait(10)
                .withMessage("Il titolo della pagina aggiungi nuovo utente non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(titoloAggiungiUtenteLocator));

        getWebDriverWait(10)
                .withMessage("Il sottotitolo della pagina aggiungi nuovo utente non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(sottotitoloAggiungiUtenteLocator));

        getWebDriverWait(10)
                .withMessage("Il box 'Codice Fiscale' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(codiceFiscaleBoxLocator));

        getWebDriverWait(10)
                .withMessage("Il box 'Nome' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(nameBoxLocator));

        getWebDriverWait(10)
                .withMessage("Il box 'Cognome' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(surnameBoxLocator));

        getWebDriverWait(10)
                .withMessage("Il box 'Email' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(emailBoxLocator));

        getWebDriverWait(10)
                .withMessage("Il box 'Conferma Email' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(confirmEmailBoxLocator));

        getWebDriverWait(10)
                .withMessage("Il combobox 'Seleziona prodotto' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(selectProductDropdownLocator));

        getWebDriverWait(10)
                .withMessage("Il bottone 'Continua' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(continueButtonLocator));

        logger.info("La pagina 'Aggiungi nuovo utente' si visualizza correttamente");
    }


    //    public void insertData(String codiceFiscale, String name, String surname, String email) throws InterruptedException {
//        codiceFiscaleBox = driver.findElement(By.id("taxCode"));
//        surnameBox = driver.findElement(By.id("surname"));
//        emailBox = driver.findElement(By.id("email"));
//        confirmEmailBox = driver.findElement(By.id("confirmEmail"));
//        codiceFiscaleBox.sendKeys(codiceFiscale);
//        webTool.waitTime(2);
//        if (nameBox.getAttribute("value").equalsIgnoreCase(name) && surnameBox.getAttribute("value").equalsIgnoreCase(surname)) {
//            logger.info("Il nome e il cognome è generato correttamente");
//        } else {
//            logger.error("Il nome e il cognome non è generato correttamente");
//            Assertions.fail("Il nome e il cognome non è generato correttamente");
//        }
//        this.js().executeScript("arguments[0].setAttribute('autocomplete', 'off')", emailBox);
//        emailBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
//        emailBox.sendKeys(email);
//        confirmEmailBox.clear();
//        confirmEmailBox.sendKeys(email);
//    }
    public void insertData(String codiceFiscale, String name, String surname, String email) throws InterruptedException {
        By codiceFiscaleLocator = By.id("taxCode");
        By nameLocator = By.id("name");
        By surnameLocator = By.id("surname");
        By emailLocator = By.id("email");
        By confirmEmailLocator = By.id("confirmEmail");

        WebElement codiceFiscaleBox = getWebDriverWait(10)
                .withMessage("Il box 'Codice Fiscale' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(codiceFiscaleLocator));

        WebElement nameBox = getWebDriverWait(10)
                .withMessage("Il box 'Nome' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(nameLocator));

        WebElement surnameBox = getWebDriverWait(10)
                .withMessage("Il box 'Cognome' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(surnameLocator));

        WebElement emailBox = getWebDriverWait(10)
                .withMessage("Il box 'Email' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(emailLocator));

        WebElement confirmEmailBox = getWebDriverWait(10)
                .withMessage("Il box 'Conferma Email' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(confirmEmailLocator));

        // Inserimento codice fiscale
        codiceFiscaleBox.sendKeys(codiceFiscale);
        webTool.waitTime(2);

        // Verifica nome e cognome
        if (nameBox.getAttribute("value").equalsIgnoreCase(name) &&
                surnameBox.getAttribute("value").equalsIgnoreCase(surname)) {
            logger.info("Il nome e il cognome sono generati correttamente");
        } else {
            logger.error("Il nome e il cognome non sono generati correttamente");
            Assertions.fail("Il nome e il cognome non sono generati correttamente");
        }

        // Inserimento email
        this.js().executeScript("arguments[0].setAttribute('autocomplete', 'off')", emailBox);
        emailBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        emailBox.sendKeys(email);
        confirmEmailBox.clear();
        confirmEmailBox.sendKeys(email);
    }


    //    public void selectProduct(String product) {
//        selectProductDropdown = driver.findElement(By.id("select-label-products"));
//        getWebDriverWait(10).withMessage("il combobox seleziona il prodotto non è cliccabile").until(ExpectedConditions.visibilityOf(selectProductDropdown));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(selectProductDropdown).click().perform();
//        WebElement productButton = driver.findElement(By.xpath("//li[contains(text(),'" + product + "')]"));
//        getWebDriverWait(10).withMessage("il prodotto" + product + "non è cliccabile").until(ExpectedConditions.elementToBeClickable(productButton));
//        productButton.click();
//        logger.info("Ruolo: {}", product);
//        adminRadioButton = driver.findElement(By.xpath("//p[contains(text(),'Amministratore')]"));
//        getWebDriverWait(10).withMessage("il radioBottone Amministratore della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(adminRadioButton));
//    }
    public void selectProduct(String product) {
        By selectProductDropdownLocator = By.id("select-label-products");
        WebElement selectProductDropdown = getWebDriverWait(10)
                .withMessage("Il combobox 'Seleziona prodotto' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(selectProductDropdownLocator));

        // Click sul dropdown
        Actions actions = new Actions(driver);
        actions.moveToElement(selectProductDropdown).click().perform();

        // Selezione del prodotto
        By productButtonLocator = By.xpath("//li[contains(text(),'" + product + "')]");
        WebElement productButton = getWebDriverWait(10)
                .withMessage("Il prodotto '" + product + "' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(productButtonLocator));
        productButton.click();
        logger.info("Ruolo selezionato: {}", product);

        // Verifica radio button Amministratore
        By adminRadioButtonLocator = By.xpath("//p[contains(text(),'Amministratore')]");
        getWebDriverWait(10)
                .withMessage("Il radio button 'Amministratore' della pagina aggiungi nuovo utente non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(adminRadioButtonLocator));
    }


    //    public void selectRole() {
//        adminRadioButton = driver.findElement(By.xpath("//p[contains(text(),'Amministratore')]"));
//        continueButton = driver.findElement(By.xpath("//button[contains(text(),'Continua')]"));
//        adminRadioButton.click();
//        if (continueButton.getAttribute("disabled") == null) {
//            logger.info("il bottone Continua è attivo");
//        }
//        logger.info("Si clicca sul bottone Continua");
//        Actions actions = new Actions(driver);
//        actions.moveToElement(continueButton).click().perform();
//        webTool.waitTime(10);
//        confirmPopup = driver.findElement(By.xpath("//p[contains(text(),'Vuoi assegnare a')]"));
//        getWebDriverWait(10).withMessage("il popup assegna ruolo non è visualizzata").until(ExpectedConditions.visibilityOf(confirmPopup));
//    }
    public void selectRole() {
        // Attendo e clicco sul radio "Amministratore"
        WebElement adminRadioButton = getWebDriverWait(20)
                .withMessage("Il radio button 'Amministratore' non è visibile")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Amministratore')]")));
        adminRadioButton.click();
        logger.info("Selezionato il ruolo Amministratore");

        // Attendo la visibilità del bottone "Continua"
        WebElement continueButton = getWebDriverWait(20)
                .withMessage("Il bottone 'Continua' non è visibile")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Continua')]")));

        if (continueButton.getAttribute("disabled") == null) {
            logger.info("Il bottone Continua è attivo");
        } else {
            logger.warn("Il bottone Continua risulta disabilitato");
        }

        // Click robusto tramite Actions
        logger.info("Si clicca sul bottone Continua");
        new Actions(driver).moveToElement(continueButton).click().perform();

        // Attendo il popup di conferma
        WebElement confirmPopup = getWebDriverWait(20)
                .withMessage("Il popup di conferma 'Vuoi assegnare a...' non è visibile")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Vuoi assegnare a')]")));
        logger.info("Popup di conferma ruolo visibile: {}", confirmPopup.getText());
    }


    //    public void clickRejectPopup() {
//        cancelButton = driver.findElement(By.xpath("//button[contains(text(),'Annulla')]"));
//        getWebDriverWait(10).withMessage("il bottone annula non è visibile").until(ExpectedConditions.visibilityOf(cancelButton));
//        logger.info("Si clicca sul bottone Annulla");
//        cancelButton.click();
//    }
    public void clickRejectPopup() {
        By cancelButtonLocator = By.xpath("//button[contains(text(),'Annulla')]");

        WebElement cancelButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Annulla' non è visibile")
                .until(ExpectedConditions.elementToBeClickable(cancelButtonLocator));

        cancelButton.click();
        logger.info("Click sul bottone 'Annulla' effettuato");
    }


    //    public void clickContinueAndAssign() {
//        continueButton = driver.findElement(By.xpath("//button[contains(text(),'Continua')]"));
//        getWebDriverWait(10).withMessage("il bottone continua non è visibile o cliccabile").until(ExpectedConditions.and(
//                ExpectedConditions.visibilityOf(continueButton),
//                ExpectedConditions.elementToBeClickable(continueButton)
//        ));
//        continueButton.click();
//        webTool.waitTime(10);
//        assegnaButton = driver.findElement(By.xpath("//button[contains(text(),'Assegna')]"));
//        getWebDriverWait(10).withMessage("il bottone assegna non è visibile").until(ExpectedConditions.visibilityOf(assegnaButton));
//        assegnaButton.click();
//    }
    public void clickContinueAndAssign() {
        By continueButtonLocator = By.xpath("//button[contains(text(),'Continua')]");
        By assegnaButtonLocator = By.xpath("//button[contains(text(),'Assegna')]");

        WebElement continueButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Continua' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(continueButtonLocator));

        continueButton.click();
        logger.info("Click sul bottone 'Continua' effettuato");

        webTool.waitTime(2); // breve attesa per la transizione

        WebElement assegnaButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Assegna' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(assegnaButtonLocator));

        assegnaButton.click();
        logger.info("Click sul bottone 'Assegna' effettuato");
    }


    //    public void waitSuccessMessage() {
//        try {
//            webTool.waitTime(10);
//            if (driver.findElement(By.xpath("//p[contains(text(),'Utente aggiunto correttamente')]")).isDisplayed()) {
//                logger.info("Si visualizza correttamente messaggio di successo");
//            }
//        } catch (NoSuchElementException e) {
//            logger.warn("Hai già aggiunto questo utente. {}", e);
//        }
//    }
    public void waitSuccessMessage() {
        webTool.waitTime(10);
        By successMessageLocator = By.xpath("//p[contains(text(),'Utente aggiunto correttamente')]");

        try {
            WebElement successMessage = getWebDriverWait(10)
                    .withMessage("Il messaggio di successo non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));

            if (successMessage.isDisplayed()) {
                logger.info("Si visualizza correttamente il messaggio di successo");
            }
        } catch (TimeoutException e) {
            logger.warn("Hai già aggiunto questo utente oppure il messaggio non è visibile. {}", e.getMessage());
        }
    }


    //    public void waitLoadRecapPage() {
//        try {
//            WebElement titolo = driver.findElement(By.xpath("//main//h4"));
//            getWebDriverWait(15).withMessage("il titolo della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOf(titolo));
//            getWebDriverWait(15).withMessage("il bottone modifica della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//button[contains(text(),'Modifica')]")))));
//            getWebDriverWait(15).withMessage("il bottone rimuovi della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//span[contains(text(),'Rimuovi')]")))));
//            getWebDriverWait(15).withMessage("la sezione nome della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//p[contains(text(),'Nome')]")))));
//            getWebDriverWait(15).withMessage("la sezione cognome della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//p[contains(text(),'Cognome')]")))));
//            getWebDriverWait(15).withMessage("la sezione codice fiscale della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//p[contains(text(),'Codice Fiscale')]")))));
//            getWebDriverWait(15).withMessage("la sezione email della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//p[contains(text(),'Email')]")))));
//            logger.info("Si visualizza correttamente pagina riepilogativa");
//        } catch (TimeoutException e) {
//            Assertions.fail("Non si visualizza correttamente pagina riepilogativa con errore:" + e.getMessage());
//        }
//    }
    public void waitLoadRecapPage() {
        try {
            By titoloLocator = By.xpath("//main//h4");
            By modificaButtonLocator = By.xpath("//button[contains(text(),'Modifica')]");
            By rimuoviButtonLocator = By.xpath("//span[contains(text(),'Rimuovi')]");
            By nomeSectionLocator = By.xpath("//p[contains(text(),'Nome')]");
            By cognomeSectionLocator = By.xpath("//p[contains(text(),'Cognome')]");
            By codiceFiscaleSectionLocator = By.xpath("//p[contains(text(),'Codice Fiscale')]");
            By emailSectionLocator = By.xpath("//p[contains(text(),'Email')]");

            getWebDriverWait(15)
                    .withMessage("Il titolo della pagina riepilogativa non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(titoloLocator));

            getWebDriverWait(15)
                    .withMessage("Il bottone 'Modifica' della pagina riepilogativa non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(modificaButtonLocator));

            getWebDriverWait(15)
                    .withMessage("Il bottone 'Rimuovi' della pagina riepilogativa non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(rimuoviButtonLocator));

            getWebDriverWait(15)
                    .withMessage("La sezione 'Nome' della pagina riepilogativa non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(nomeSectionLocator));

            getWebDriverWait(15)
                    .withMessage("La sezione 'Cognome' della pagina riepilogativa non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(cognomeSectionLocator));

            getWebDriverWait(15)
                    .withMessage("La sezione 'Codice Fiscale' della pagina riepilogativa non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(codiceFiscaleSectionLocator));

            getWebDriverWait(15)
                    .withMessage("La sezione 'Email' della pagina riepilogativa non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(emailSectionLocator));

            logger.info("Pagina riepilogativa visualizzata correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("La pagina riepilogativa non si visualizza correttamente: " + e.getMessage());
        }
    }


    //    public void clickModifyButton() {
//
//        getWebDriverWait(15).withMessage("il bottone modifica non è cliccabile clickModifyButton").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Modifica')]"))));
//        logger.info("Si clicca sul bottone modifica");
//        driver.findElement(By.xpath("//button[contains(text(),'Modifica')]")).click();
//    }
    public void clickModifyButton() {
        By modificaButtonLocator = By.xpath("//button[contains(text(),'Modifica')]");

        WebElement modificaButton = getWebDriverWait(15)
                .withMessage("Il bottone 'Modifica' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(modificaButtonLocator));

        modificaButton.click();
        logger.info("Click sul bottone 'Modifica' effettuato");
    }

    //
//    public void checkEmailBoxActive() {
//        emailBox = driver.findElement(By.id("email"));
//        confirmEmailBox = driver.findElement(By.id("confirmEmail"));
//        String checkEmail = emailBox.getAttribute("disabled");
//        String checkConfirmEmail = confirmEmailBox.getAttribute("disabled");
//        if (checkEmail == null && checkConfirmEmail == null) {
//            logger.info("il campo email e il campo conferma email sono attivi");
//        } else {
//            Assertions.fail("il campo email e il campo conferma email sono disattivi");
//        }
//    }
    public void checkEmailBoxActive() {
        By emailBoxLocator = By.id("email");
        By confirmEmailBoxLocator = By.id("confirmEmail");

        WebElement emailBox = getWebDriverWait(10)
                .withMessage("Il campo email non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(emailBoxLocator));

        WebElement confirmEmailBox = getWebDriverWait(10)
                .withMessage("Il campo conferma email non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(confirmEmailBoxLocator));

        boolean emailActive = emailBox.getAttribute("disabled") == null;
        boolean confirmEmailActive = confirmEmailBox.getAttribute("disabled") == null;

        if (emailActive && confirmEmailActive) {
            logger.info("Il campo email e il campo conferma email sono attivi");
        } else {
            Assertions.fail("Il campo email e/o il campo conferma email sono disattivi");
        }
    }

    //    public void inserisciNuovoEmail(String newMail) {
//        emailBox = driver.findElement(By.id("email"));
//        confirmEmailBox = driver.findElement(By.id("confirmEmail"));
//
//        emailBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
//        emailBox.sendKeys(newMail);
//        wrongMailErrorMessage = driver.findElement(By.id("confirmEmail-helper-text"));
//        getWebDriverWait(10).withMessage("il messaggio errore email non è visibile").until(ExpectedConditions.visibilityOf(wrongMailErrorMessage));
//        confirmEmailBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
//        confirmEmailBox.sendKeys(newMail);
//    }
    public void inserisciNuovoEmail(String newMail) {
        By emailBoxLocator = By.id("email");
        By confirmEmailBoxLocator = By.id("confirmEmail");
        By wrongMailErrorLocator = By.id("confirmEmail-helper-text");

        WebElement emailBox = getWebDriverWait(10)
                .withMessage("Il campo email non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(emailBoxLocator));

        WebElement confirmEmailBox = getWebDriverWait(10)
                .withMessage("Il campo conferma email non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(confirmEmailBoxLocator));

        // Pulizia e inserimento email usando Keys.CONTROL + A e DELETE
        emailBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        emailBox.sendKeys(newMail);

        // Attesa messaggio errore email
        getWebDriverWait(10)
                .withMessage("Il messaggio di errore email non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(wrongMailErrorLocator));

        confirmEmailBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        confirmEmailBox.sendKeys(newMail);

        logger.info("Nuova email inserita correttamente mantenendo Keys.CONTROL");
    }


    //    public void clickConfirm() {
//
//        getWebDriverWait(10).withMessage("il bottone conferma non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Continua')]"))));
//        driver.findElement(By.xpath("//button[contains(text(),'Continua')]")).click();
//        getWebDriverWait(10).withMessage("il messaggio successo non è visibile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//p[contains(text(),'Profilo modificato correttamente')]"))));
//    }
    public void clickConfirm() {
        By confirmButtonLocator = By.xpath("//button[contains(text(),'Continua')]");
        By successMessageLocator = By.xpath("//p[contains(text(),'Profilo modificato correttamente')]");
        WebElement confirmButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Continua' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        confirmButton.click();
        logger.info("Click sul bottone 'Continua' effettuato");
        getWebDriverWait(10)
                .withMessage("Il messaggio di successo non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));
        logger.info("Messaggio 'Profilo modificato correttamente' visibile");
    }


    //    public void checkNewEmail(String newEmail) {
//        WebElement newEmailField = driver.findElement(By.xpath("//p[contains(text(),'" + newEmail + "')]"));
//        getWebDriverWait(10).withMessage("il nuovo email non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(),'" + newEmail + "')]"))));
//        logger.info("il nuovo email è visibile");
//    }
    public void checkNewEmail(String newEmail) {
        By newEmailLocator = By.xpath("//p[contains(text(),'" + newEmail + "')]");

        getWebDriverWait(10)
                .withMessage("Il nuovo email '" + newEmail + "' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(newEmailLocator));

        logger.info("Il nuovo email '{}' è visibile", newEmail);
    }

    //
//    public void clickRemoveButton() {
//        removeButton = driver.findElement(By.xpath("//span[contains(text(),'Rimuovi')]"));
//        getWebDriverWait(10).withMessage("il bottone rimuovi non è cliccabile").until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Rimuovi')]")));
//        logger.info("Si clicca sul bottone rimuovi");
//        element(By.xpath("//span[contains(text(),'Rimuovi')]")).click();
//    }
    public void clickRemoveButton() {
        By removeButtonLocator = By.xpath("//span[contains(text(),'Rimuovi')]");

        WebElement removeButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Rimuovi' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(removeButtonLocator));

        removeButton.click();
        logger.info("Click sul bottone 'Rimuovi' effettuato");
    }


    //    public void checkRemoveUserPopup() {
//        removeUserPopup = driver.findElement(By.xpath("//p[contains(text(),'Elimina Utente')]"));
//        getWebDriverWait(10).withMessage("il popup elimina utente non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(),'Elimina Utente')]"))));
//        logger.info("il popup elimina utente è visualizzata correttamente");
//    }
    public void checkRemoveUserPopup() {
        removeUserPopup = driver.findElement(By.xpath("//h6[contains(text(),'Elimina Utente')]"));
        getWebDriverWait(10).withMessage("il popup elimina utente non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h6[contains(text(),'Elimina Utente')]"))));
        logger.info("il popup elimina utente è visualizzata correttamente");
    }


    //    public void clickRemoveRoleButton() {
//        removeRuoloButton = driver.findElement(By.xpath("//button[contains(text(),'Rimuovi')]"));
//        getWebDriverWait(10).withMessage("il bottone rimuovi ruolo non è cliccabile").until(ExpectedConditions.elementToBeClickable(removeRuoloButton));
//        logger.info("Si clicca sul bottone rimuovi ruolo");
//        removeRuoloButton.click();
//    }
    public void clickRemoveRoleButton() {
        By removeRoleButtonLocator = By.xpath("//button[contains(text(),'Rimuovi')]");

        WebElement removeRoleButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Rimuovi ruolo' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(removeRoleButtonLocator));

        removeRoleButton.click();
        logger.info("Click sul bottone 'Rimuovi ruolo' effettuato");
    }


    //    public void checkUserDeletedMEssage() {
//        roleDeletedMessage = driver.findElement(By.xpath("//p[contains(text(),'Utente rimosso correttamente')]"));
//        getWebDriverWait(10).withMessage("il messaggio utente rimosso non è visibile").until(ExpectedConditions.elementToBeClickable(roleDeletedMessage));
//        logger.info("Si visualizza correttamente il messaggio utente rimosso");
//    }
    public void checkUserDeletedMEssage() {
        By roleDeletedMessageLocator = By.xpath("//p[contains(text(),'Utente rimosso correttamente')]");

        getWebDriverWait(10)
                .withMessage("Il messaggio 'Utente rimosso correttamente' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(roleDeletedMessageLocator));

        logger.info("Il messaggio 'Utente rimosso correttamente' è visualizzato correttamente");
    }


    //    public void getUserDetailsPage(String name) {
//        WebElement findUserByName = driver.findElement(By.xpath("//p[contains(text(),'" + name + "')]"));
//        getWebDriverWait(10).withMessage("l'utente apena creato non è visibile").until(ExpectedConditions.visibilityOf(findUserByName));
//        findUserByName.click();
//    }
    public void getUserDetailsPage(String name) {
        By userLocator = By.xpath("//p[contains(text(),'" + name + "')]");

        WebElement findUserByName = getWebDriverWait(10)
                .withMessage("L'utente appena creato '" + name + "' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(userLocator));

        findUserByName.click();
        logger.info("Click sull'utente '{}' per aprire la pagina dettagli", name);
    }


}
