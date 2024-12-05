package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
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
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

public class UtentiPGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("UtentiPGPage");

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
    @FindBy(xpath = "//p[contains(text(),'Elimina Utente')]")
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


    public void clickSezioneUtenti() {
        webTool.waitTime(10);
        sezioneUtenti = driver.findElement(By.xpath("//span[contains(text(),'Utenti')]"));
        getWebDriverWait(10).withMessage("La sezione Utenti non è cliccabile").until(ExpectedConditions.elementToBeClickable(sezioneUtenti));
        sezioneUtenti.click();
        //build url
        String companyId = "d0f52c7d-76d5-4520-8971-edffeb5b46d5";
        if (webDriverConfig != null){
            environment = webDriverConfig.getEnvironment();
        }

        String utentiUrl = "https://imprese." + environment + ".notifichedigitali.it/dashboard/" + companyId + "/users";
        //switch tab
        String parentWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                this.driver.switchTo().window(handle);
                logger.info(driver.getTitle());
                break;
            }
        }
    }

    public void clickSezioneUtentiDaRiepilogo() {
        sezioneUtenti = driver.findElement(By.xpath("//span[contains(text(),'Utenti')]"));
        getWebDriverWait(10).withMessage("Il sezione Utenti non è cliccabile").until(ExpectedConditions.elementToBeClickable(sezioneUtenti));
        sezioneUtenti.click();
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

    public void waitLoadUtentiPage() {
        try {
            webTool.waitTime(15);
            WebElement utentiPageTitle = driver.findElement(By.xpath("//div/h4[contains(text(),'Utenti')]"));
            WebElement sottoTitolo = driver.findElement(By.xpath("//div/p[contains(text(),'Gestisci gli utenti')]"));
            WebElement addUserButton = driver.findElement(By.xpath("//button[contains(text(),'Aggiungi utente')]"));
            WebElement filtraButton = driver.findElement(By.xpath("//button[contains(text(),'Filtra')]"));
            WebElement utentiTable = driver.findElement(By.id("UsersSearchTableBox"));
            getWebDriverWait(10).withMessage("il titolo Utenti della pagina utenti non è visibile").until(ExpectedConditions.visibilityOf(utentiPageTitle));
            getWebDriverWait(10).withMessage("il sottotitolo della pagina utenti non è visibile").until(ExpectedConditions.visibilityOf(sottoTitolo));
            getWebDriverWait(10).withMessage("il bottone aggiungi utente della pagina utenti non è visibile").until(ExpectedConditions.visibilityOf(addUserButton));
            getWebDriverWait(10).withMessage("il bottone filtra della pagina utenti non è visibile").until(ExpectedConditions.visibilityOf(filtraButton));
            getWebDriverWait(10).withMessage("la tabella della pagina utenti non è visibile").until(ExpectedConditions.visibilityOf(utentiTable));
            logger.info("Si visualizza correttamente utenti page");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente utenti page con errore:" + e.getMessage());
            Assertions.fail("Non si visualizza correttamente utenti page con errore:" + e.getMessage());
        }
    }

    public void clickAddUserButton() {
        addUserButton = driver.findElement(By.xpath("//button[contains(text(),'Aggiungi utente')]"));
        getWebDriverWait(10).withMessage("il bottone aggiungi utente non è cliccabile").until(ExpectedConditions.elementToBeClickable(addUserButton));
        addUserButton.click();
    }

    public void waitLoadAggiungiUtentePage() {
        indietroButton = driver.findElement(By.xpath("//button[contains(text(),'Indietro')]"));
        titoloAggiungiUtente = driver.findElement(By.xpath("//div/h4[contains(text(),'Aggiungi un nuovo utente')]"));
        sottotitoloAggiungiUtente = driver.findElement(By.xpath("//div/p[contains(text(),'Inserisci i dati dell’utente,')]"));
        codiceFiscaleBox = driver.findElement(By.id("taxCode"));
        nameBox = driver.findElement(By.id("name"));
        surnameBox = driver.findElement(By.id("surname"));
        emailBox = driver.findElement(By.id("email"));
        confirmEmailBox = driver.findElement(By.id("confirmEmail"));
        selectProductDropdown = driver.findElement(By.id("select-label-products"));
        continueButton = driver.findElement(By.xpath("//button[contains(text(),'Continua')]"));
        getWebDriverWait(10).withMessage("il bottone indietro Utenti / Aggiungi nuovo utente della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(indietroButton));
        getWebDriverWait(10).withMessage("il titolo della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(titoloAggiungiUtente));
        getWebDriverWait(10).withMessage("il sottotitolo della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(sottotitoloAggiungiUtente));
        getWebDriverWait(10).withMessage("il box codice fiscale della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(codiceFiscaleBox));
        getWebDriverWait(10).withMessage("il box nome della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(nameBox));
        getWebDriverWait(10).withMessage("il box cognome della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(surnameBox));
        getWebDriverWait(10).withMessage("il box email della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(emailBox));
        getWebDriverWait(10).withMessage("il box conferma email della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(confirmEmailBox));
        getWebDriverWait(10).withMessage("il combobox seleziona prodotto della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(selectProductDropdown));
        getWebDriverWait(10).withMessage("il bottone continua della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(continueButton));
        logger.info("Si visualizza correttamente aggiungi nuovo utente page");
    }

    public void insertData(String codiceFiscale, String name, String surname, String email) throws InterruptedException {
        codiceFiscaleBox = driver.findElement(By.id("taxCode"));
        surnameBox = driver.findElement(By.id("surname"));
        emailBox = driver.findElement(By.id("email"));
        confirmEmailBox = driver.findElement(By.id("confirmEmail"));
        codiceFiscaleBox.sendKeys(codiceFiscale);
        webTool.waitTime(2);
        if (nameBox.getAttribute("value").equalsIgnoreCase(name) && surnameBox.getAttribute("value").equalsIgnoreCase(surname)) {
            logger.info("Il nome e il cognome è generato correttamente");
        } else {
            logger.error("Il nome e il cognome non è generato correttamente");
            Assertions.fail("Il nome e il cognome non è generato correttamente");
        }
        this.js().executeScript("arguments[0].setAttribute('autocomplete', 'off')", emailBox);
        emailBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        emailBox.sendKeys(email);
        confirmEmailBox.clear();
        confirmEmailBox.sendKeys(email);
    }

    public void selectProduct(String product) {
        selectProductDropdown = driver.findElement(By.id("select-label-products"));
        getWebDriverWait(10).withMessage("il combobox seleziona il prodotto non è cliccabile").until(ExpectedConditions.visibilityOf(selectProductDropdown));
        Actions actions = new Actions(driver);
        actions.moveToElement(selectProductDropdown).click().perform();
        WebElement productButton = driver.findElement(By.xpath("//li[contains(text(),'" + product + "')]"));
        getWebDriverWait(10).withMessage("il prodotto" + product + "non è cliccabile").until(ExpectedConditions.elementToBeClickable(productButton));
        productButton.click();
        logger.info("Ruolo :" + product);
        adminRadioButton = driver.findElement(By.xpath("//p[contains(text(),'Amministratore')]"));
        getWebDriverWait(10).withMessage("il radioBottone Amministratore della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(adminRadioButton));
    }

    public void selectRole() {
        adminRadioButton = driver.findElement(By.xpath("//p[contains(text(),'Amministratore')]"));
        continueButton = driver.findElement(By.xpath("//button[contains(text(),'Continua')]"));
        adminRadioButton.click();
        if (continueButton.getAttribute("disabled") == null) {
            logger.info("il bottone Continua è attivo");
        }
        logger.info("Si clicca sul bottone Continua");
        Actions actions = new Actions(driver);
        actions.moveToElement(continueButton).click().perform();
        webTool.waitTime(10);
        confirmPopup = driver.findElement(By.xpath("//p[contains(text(),'Vuoi assegnare a')]"));
        getWebDriverWait(10).withMessage("il popup assegna ruolo non è visualizzata").until(ExpectedConditions.visibilityOf(confirmPopup));
    }

    public void clickRejectPopup() {
        cancelButton = driver.findElement(By.xpath("//button[contains(text(),'Annulla')]"));
        getWebDriverWait(10).withMessage("il bottone annula non è visibile").until(ExpectedConditions.visibilityOf(cancelButton));
        logger.info("Si clicca sul bottone Annula");
        cancelButton.click();
    }

    public void clickContinueAndAssign() {
        continueButton = driver.findElement(By.xpath("//button[contains(text(),'Continua')]"));
        getWebDriverWait(10).withMessage("il bottone continua non è visibile o cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(continueButton),
                ExpectedConditions.elementToBeClickable(continueButton)
        ));
        continueButton.click();
        webTool.waitTime(10);
        assegnaButton = driver.findElement(By.xpath("//button[contains(text(),'Assegna')]"));
        getWebDriverWait(10).withMessage("il bottone assegna non è visibile").until(ExpectedConditions.visibilityOf(assegnaButton));
        assegnaButton.click();
    }

    public void waitSuccessMessage() {
        try {
            webTool.waitTime(10);
//            successMessage = driver.findElement(By.xpath("//p[contains(text(),'Utente aggiunto correttamente')]"));
            if (driver.findElement(By.xpath("//p[contains(text(),'Utente aggiunto correttamente')]")).isDisplayed()) {
                logger.info("Si visualizza correttamente messaggio di successo");
            }
        } catch (NoSuchElementException e) {
            logger.warn("Hai già aggiunto questo utente." + e);
        }
    }

    public void waitLoadRecapPage() {
        try {
            WebElement titolo = driver.findElement(By.xpath("//main//h4"));
            getWebDriverWait(10).withMessage("il titolo della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOf(titolo));
            getWebDriverWait(10).withMessage("il bottone modifica della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//button[contains(text(),'Modifica')]"))));
            getWebDriverWait(10).withMessage("il bottone rimuovi della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//span[contains(text(),'Rimuovi')]"))));
            getWebDriverWait(10).withMessage("la sezione nome della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(),'Nome')]"))));
            getWebDriverWait(10).withMessage("la sezione cognome della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated( (By.xpath("//p[contains(text(),'Cognome')]"))));
            getWebDriverWait(10).withMessage("la sezione codice fiscale della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(),'Codice Fiscale')]"))));
            getWebDriverWait(10).withMessage("la sezione email della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(),'Email')]"))));
            logger.info("Si visualizza correttamente pagina riepilogativa");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente pagina riepilogativa con errore:" + e.getMessage());
            Assertions.fail("Non si visualizza correttamente pagina riepilogativa con errore:" + e.getMessage());
        }
    }

    public void clickModifyButton() {

        getWebDriverWait(10).withMessage("il bottone modifica non è cliccabile clickModifyButton").until(ExpectedConditions.elementToBeClickable(driver.findElement( By.xpath("//button[contains(text(),'Modifica')]"))));
//        modificaButton = driver.findElement( By.xpath("//button[contains(text(),'Modifica')]"));
        logger.info("Si clicca sul bottone modifica");
        driver.findElement( By.xpath("//button[contains(text(),'Modifica')]")).click();
    }

    public void checkEmailBoxActive() {
        emailBox = driver.findElement( By.id("email"));
        confirmEmailBox = driver.findElement( By.id("confirmEmail"));
        String checkEmail = emailBox.getAttribute("disabled");
        String checkConfirmEmail = confirmEmailBox.getAttribute("disabled");
        if (checkEmail == null && checkConfirmEmail == null) {
            logger.info("il campo email e il campo conferma email sono attivi");
        } else {
            logger.error("il campo email e il campo conferma email sono disattivi");
            Assertions.fail("il campo email e il campo conferma email sono disattivi");
        }
    }

    public void inserisciNuovoEmail(String newMail) {
        emailBox = driver.findElement( By.id("email"));
        confirmEmailBox = driver.findElement( By.id("confirmEmail"));

        emailBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        emailBox.sendKeys(newMail);
        wrongMailErrorMessage  = driver.findElement( By.id( "confirmEmail-helper-text"));
        getWebDriverWait(10).withMessage("il messaggio errore email non è visibile").until(ExpectedConditions.visibilityOf(wrongMailErrorMessage));
        confirmEmailBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        confirmEmailBox.sendKeys(newMail);
    }

    public void clickConfirm() {
        confermaButton  = driver.findElement(By.xpath( "//button[contains(text(),'Conferma')]"));
        getWebDriverWait(10).withMessage("il bottone conferma non è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaButton));
        confermaButton.click();
        successModifyMessage  = driver.findElement(By.xpath("//p[contains(text(),'Profilo modificato correttamente')]"));
        getWebDriverWait(10).withMessage("il messaggio successo non è visibile").until(ExpectedConditions.elementToBeClickable(successModifyMessage));
    }

    public void checkNewEmail(String newEmail) {
        WebElement newEmailField = driver.findElement(By.xpath("//p[contains(text(),'" + newEmail + "')]"));
        getWebDriverWait(10).withMessage("il nuovo email non è visibile").until(ExpectedConditions.visibilityOf(newEmailField));
        logger.info("il nuovo email è visibile");
    }

    public void clickRemoveButton() {
        removeButton  = driver.findElement(By.xpath( "//span[contains(text(),'Rimuovi')]"));
        getWebDriverWait(10).withMessage("il bottone rimuovi non è cliccabile").until(ExpectedConditions.elementToBeClickable(removeButton));
        logger.info("Si clicca sul bottone rimuovi");
        removeButton.click();
    }

    public void checkRemoveUserPopup() {
        removeUserPopup  = driver.findElement(By.xpath( "//p[contains(text(),'Elimina Utente')]"));
        getWebDriverWait(10).withMessage("il popup elimina utente non è visibile").until(ExpectedConditions.visibilityOf(removeUserPopup));
        logger.info("il popup elimina utente è visualizzata correttamente");
    }

    public void clickRemoveRoleButton() {
        removeRuoloButton  = driver.findElement(By.xpath( "//button[contains(text(),'Rimuovi')]"));
        getWebDriverWait(10).withMessage("il bottone rimuovi ruolo non è cliccabile").until(ExpectedConditions.elementToBeClickable(removeRuoloButton));
        logger.info("Si clicca sul bottone rimuovi ruolo");
        removeRuoloButton.click();
    }

    public void checkUserDeletedMEssage() {
        roleDeletedMessage  = driver.findElement(By.xpath(  "//p[contains(text(),'Utente rimosso correttamente')]"));
        getWebDriverWait(10).withMessage("il messaggio utente rimosso non è visibile").until(ExpectedConditions.elementToBeClickable(roleDeletedMessage));
        logger.info("Si visualizza correttamente il messaggio utente rimosso");
    }

    public void getUserDetailsPage(String name) {
        WebElement findUserByName = driver.findElement(By.xpath("//p[contains(text(),'" + name + "')]"));
        getWebDriverWait(10).withMessage("l'utente apena creato non è visibile").until(ExpectedConditions.visibilityOf(findUserByName));
        findUserByName.click();
    }
}
