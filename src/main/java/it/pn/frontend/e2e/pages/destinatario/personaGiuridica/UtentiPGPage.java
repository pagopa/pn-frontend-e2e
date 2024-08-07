package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;


public class UtentiPGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("UtentiPGPage");
    private final AccediAreaRiservataPGPage accediAreaRiservataPGPage = new AccediAreaRiservataPGPage(this.driver);
    private final Actions actions = new Actions(this.driver);
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

    public UtentiPGPage(WebDriver driver) {
        super(driver);
    }

    public void clickSezioneUtenti() {
        getWebDriverWait(10).withMessage("La sezione Utenti non è cliccabile").until(ExpectedConditions.elementToBeClickable(sezioneUtenti));
        sezioneUtenti.click();
        //build url
        String companyId = "d0f52c7d-76d5-4520-8971-edffeb5b46d5";
        String environment = System.getProperty("environment");
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
        getWebDriverWait(10).withMessage("Il sezione Utenti non è cliccabile").until(ExpectedConditions.elementToBeClickable(sezioneUtenti));
        sezioneUtenti.click();
    }

    public void loginUtenti(String nome, String pwd) {
        accediAreaRiservataPGPage.waitLoadAccediAreaRiservataPGPage();
        accediAreaRiservataPGPage.clickSpidButton();

        ScegliSpidPGPage scegliSpidPGPage = new ScegliSpidPGPage(this.driver);
        scegliSpidPGPage.clickTestButton();

        LoginPGPagoPAPage loginPGPagoPAPage = new LoginPGPagoPAPage(this.driver);
        loginPGPagoPAPage.waitLoadLoginPGPage();
        loginPGPagoPAPage.insertUsername(nome);
        loginPGPagoPAPage.insertPassword(pwd);
        loginPGPagoPAPage.clickInviaButton();

        AutorizzaInvioDatiPGPage autorizzaInvioDatiPGPage = new AutorizzaInvioDatiPGPage(this.driver);
        autorizzaInvioDatiPGPage.waitLoadAutorizzaInvioDatiPGPage();
        autorizzaInvioDatiPGPage.clickInviaButton();
    }

    public void waitLoadUtentiPage() {
        try {
            By utentiPageTitle = By.xpath("//div/h4[contains(text(),'Utenti')]");
            By sottoTitolo = By.xpath("//div/p[contains(text(),'Gestisci gli utenti')]");
            By addUserButton = By.xpath("//button[contains(text(),'Aggiungi utente')]");
            By filtraButton = By.xpath("//button[contains(text(),'Filtra')]");
            By utentiTable = By.id("UsersSearchTableBox");
            getWebDriverWait(10).withMessage("il titolo Utenti della pagina utenti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(utentiPageTitle));
            getWebDriverWait(10).withMessage("il sottotitolo della pagina utenti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(sottoTitolo));
            getWebDriverWait(10).withMessage("il bottone aggiungi utente della pagina utenti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(addUserButton));
            getWebDriverWait(10).withMessage("il bottone filtra della pagina utenti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(filtraButton));
            getWebDriverWait(10).withMessage("la tabella della pagina utenti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(utentiTable));
            logger.info("Si visualizza correttamente utenti page");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente utenti page con errore:" + e.getMessage());
            Assert.fail("Non si visualizza correttamente utenti page con errore:" + e.getMessage());
        }
    }

    public void clickAddUserButton() {
        getWebDriverWait(10).withMessage("il bottone aggiungi utente non è cliccabile").until(ExpectedConditions.elementToBeClickable(addUserButton));
        addUserButton.click();
    }

    public void waitLoadAggiungiUtentePage() {
        try {
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
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente aggiungi nuovo utente con errore:" + e.getMessage());
            Assert.fail("Non si visualizza correttamente aggiungi nuovo utente page con errore:" + e.getMessage());
        }
    }

    public void insertData(String codiceFiscale, String name, String surname, String email) throws InterruptedException {
        codiceFiscaleBox.sendKeys(codiceFiscale);
        Thread.sleep(2000);
        if (nameBox.getAttribute("value").equalsIgnoreCase(name) && surnameBox.getAttribute("value").equalsIgnoreCase(surname)) {
            logger.info("Il nome e il cognome è generato correttamente");
        } else {
            logger.error("Il nome e il cognome non è generato correttamente");
            Assert.fail("Il nome e il cognome non è generato correttamente");
        }
        this.js().executeScript("arguments[0].setAttribute('autocomplete', 'off')", emailBox);
        emailBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        emailBox.sendKeys(email);
        confirmEmailBox.clear();
        confirmEmailBox.sendKeys(email);
    }

    public void selectProduct(String product) {
        getWebDriverWait(10).withMessage("il combobox seleziona il prodotto non è cliccabile").until(ExpectedConditions.visibilityOf(selectProductDropdown));
        actions.moveToElement(selectProductDropdown).click().perform();
        WebElement productButton = driver.findElement(By.xpath("//li[contains(text(),'" + product + "')]"));
        getWebDriverWait(10).withMessage("il prodotto" + product + "non è cliccabile").until(ExpectedConditions.elementToBeClickable(productButton));
        productButton.click();
        logger.info("Ruolo :" + product);
        getWebDriverWait(10).withMessage("il radioBottone Amministratore della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(adminRadioButton));
    }

    public void selectRole() {
        adminRadioButton.click();
        if (continueButton.getAttribute("disabled") == null) {
            logger.info("il bottone Continua è attivo");
        }
        logger.info("Si clicca sul bottone Continua");
        actions.moveToElement(continueButton).click().perform();
        getWebDriverWait(10).withMessage("il popup assegna ruolo non è visualizzata").until(ExpectedConditions.visibilityOf(confirmPopup));
    }

    public void clickRejectPopup() {
        getWebDriverWait(10).withMessage("il bottone annula non è visibile").until(ExpectedConditions.visibilityOf(cancelButton));
        logger.info("Si clicca sul bottone Annula");
        cancelButton.click();
    }

    public void clickContinueAndAssign() {
        getWebDriverWait(10).withMessage("il bottone continua non è visibile o cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(continueButton),
                ExpectedConditions.elementToBeClickable(continueButton)
        ));
        continueButton.click();
        getWebDriverWait(10).withMessage("il bottone assegna non è visibile").until(ExpectedConditions.visibilityOf(assegnaButton));
        assegnaButton.click();
    }

    public void waitSuccessMessage() {
        try {
            if (successMessage.isDisplayed()) {
                logger.info("Si visualizza correttamente messaggio di successo");
            }
        } catch (NoSuchElementException e) {
            logger.warn("Hai già aggiunto questo utente." + e);
        }
    }

    public void waitLoadRecapPage() {
        try {
            By indietroButton = By.xpath("//button[contains(text(),'Indietro')]");
            By titolo = By.xpath("//main//h4");
            By modificaButton = By.xpath("//button[contains(text(),'Modifica')]");
            By rimuoviButton = By.xpath("//span[contains(text(),'Rimuovi')]");
            By nameField = By.xpath("//p[contains(text(),'Nome')]");
            By surnameField = By.xpath("//p[contains(text(),'Cognome')]");
            By codiceFiscaleField = By.xpath("//p[contains(text(),'Codice Fiscale')]");
            By emailField = By.xpath("//p[contains(text(),'Email')]");
           // getWebDriverWait(10).withMessage("il bottone indietro della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(indietroButton));
            getWebDriverWait(10).withMessage("il titolo della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titolo));
            getWebDriverWait(10).withMessage("il bottone modifica della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(modificaButton));
            getWebDriverWait(10).withMessage("il bottone rimuovi della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(rimuoviButton));
            getWebDriverWait(10).withMessage("la sezione nome della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(nameField));
            getWebDriverWait(10).withMessage("la sezione cognome della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(surnameField));
            getWebDriverWait(10).withMessage("la sezione codice fiscale della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(codiceFiscaleField));
            getWebDriverWait(10).withMessage("la sezione email della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(emailField));
            logger.info("Si visualizza correttamente pagina riepilogativa");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente pagina riepilogativa con errore:" + e.getMessage());
            Assert.fail("Non si visualizza correttamente pagina riepilogativa con errore:" + e.getMessage());
        }
    }

    public void clickModifyButton() {
        getWebDriverWait(10).withMessage("il bottone modifica non è cliccabile").until(ExpectedConditions.elementToBeClickable(modificaButton));
        logger.info("Si clicca sul bottone modifica");
        modificaButton.click();
    }

    public void checkEmailBoxActive() {
        String checkEmail = emailBox.getAttribute("disabled");
        String checkConfirmEmail = confirmEmailBox.getAttribute("disabled");
        if (checkEmail == null && checkConfirmEmail == null) {
            logger.info("il campo email e il campo conferma email sono attivi");
        } else {
            logger.error("il campo email e il campo conferma email sono disattivi");
            Assert.fail("il campo email e il campo conferma email sono disattivi");
        }
    }

    public void inserisciNuovoEmail(String newMail) {
        emailBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        emailBox.sendKeys(newMail);
        getWebDriverWait(10).withMessage("il messaggio errore email non è visibile").until(ExpectedConditions.visibilityOf(wrongMailErrorMessage));
        confirmEmailBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        confirmEmailBox.sendKeys(newMail);
    }

    public void clickConfirm() {
        getWebDriverWait(10).withMessage("il bottone conferma non è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaButton));
        confermaButton.click();
        getWebDriverWait(10).withMessage("il messaggio successo non è visibile").until(ExpectedConditions.elementToBeClickable(successModifyMessage));
    }

    public void checkNewEmail(String newEmail) {
        By newEmailField = By.xpath("//p[contains(text(),'" + newEmail + "')]");
        getWebDriverWait(10).withMessage("il nuovo email non è visibile").until(ExpectedConditions.visibilityOfElementLocated(newEmailField));
        logger.info("il nuovo email è visibile");
    }

    public void clickRemoveButton() {
        getWebDriverWait(10).withMessage("il bottone rimuovi non è cliccabile").until(ExpectedConditions.elementToBeClickable(removeButton));
        logger.info("Si clicca sul bottone rimuovi");
        removeButton.click();
    }

    public void checkRemoveUserPopup() {
        getWebDriverWait(10).withMessage("il popup elimina utente non è visibile").until(ExpectedConditions.visibilityOf(removeUserPopup));
        logger.info("il popup elimina utente è visualizzata correttamente");
    }

    public void clickRemoveRoleButton() {
        getWebDriverWait(10).withMessage("il bottone rimuovi ruolo non è cliccabile").until(ExpectedConditions.elementToBeClickable(removeRuoloButton));
        logger.info("Si clicca sul bottone rimuovi ruolo");
        removeRuoloButton.click();
    }

    public void checkUserDeletedMEssage() {
        getWebDriverWait(10).withMessage("il messaggio utente rimosso non è visibile").until(ExpectedConditions.elementToBeClickable(roleDeletedMessage));
        logger.info("Si visualizza correttamente il messaggio utente rimosso");
    }

    public void getUserDetailsPage(String name) {
        WebElement findUserByName = driver.findElement(By.xpath("//p[contains(text(),'" + name + "')]"));
        getWebDriverWait(10).withMessage("l'utente apena creato non è visibile").until(ExpectedConditions.visibilityOf(findUserByName));
        findUserByName.click();
    }
}
