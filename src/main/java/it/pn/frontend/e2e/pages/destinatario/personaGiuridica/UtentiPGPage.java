package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class UtentiPGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("UtentiPGPage");

    public UtentiPGPage(WebDriver driver) {super(driver);}




    @FindBy(xpath = "//button[contains(text(),'Aggiungi utente')]")
    WebElement addUserButton;

    @FindBy(xpath = "//button[contains(text(),'Indietro')]")
    List<WebElement> indietroButton;

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

    @FindBy(id = "mui-component-select-products")
    WebElement selectProductDropdown;

    @FindBy(xpath = "//button[contains(text(),'Continua')]")
    WebElement continueButton;

    @FindBy(xpath = "//p[contains(text(),'Amministratore')]")
    WebElement adminRadioButton;

    @FindBy(xpath = "//p[contains(text(),'Vuoi assegnare a')]")
    WebElement confirmPopup;

    @FindBy(xpath = "//button[contains(text(),'Annulla')]")
    WebElement annulaButton;

    @FindBy(xpath = "//button[contains(text(),'Assegna')]")
    WebElement assegnaButton;

    @FindBy(xpath = "//p[contains(text(),'Utente aggiunto correttamente')]")
    WebElement successMessage;

    @FindBy(xpath = "//p[contains(text(),'Hai già aggiunto questo utente')]")
    WebElement userExistsMessage;

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

    @FindBy(xpath = "//p[contains(text(),'Rimuovi Ruolo')]")
    WebElement removeRolePopup;

    @FindBy(xpath = "//button[contains(text(),'Rimuovi')]")
    WebElement removeRuoloButton;

    @FindBy(xpath = "//p[contains(text(),'Ruolo rimosso correttamente')]")
    WebElement roleDeletedMessage;

    @FindBy(id = "menu-item(utenti)")
    WebElement sezioneUtenti;



    public void clickSezioneUtenti() {
        getWebDriverWait(30).withMessage("Il sezione Utenti non è cliccabile").until(ExpectedConditions.elementToBeClickable(sezioneUtenti));
        sezioneUtenti.click();
        //build url
        String companyId = "d0f52c7d-76d5-4520-8971-edffeb5b46d5";
        String environment = System.getProperty("environment");
        String utentiUrl = "https://imprese." + environment + ".notifichedigitali.it/dashboard/" + companyId + "/users";

        //switch tab
       /*   String parentWindowHandle = driver.getWindowHandle();
          Set<String> windowHandles = driver.getWindowHandles();
          for (String handle : windowHandles){
              if(!handle.equals(parentWindowHandle)){
                 this.driver.switchTo().window(handle);
                  logger.info(driver.getTitle());
                  break;
              }
          }*/
        this.driver.get(utentiUrl);

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
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente utenti page con errore:"+e.getMessage());
            Assert.fail("Non si visualizza correttamente utenti page con errore:"+e.getMessage());
        }
    }

    public void clickAddUserButton(){
        getWebDriverWait(30).withMessage("il bottone aggiungi utente non è cliccabile").until(ExpectedConditions.elementToBeClickable(addUserButton));
        addUserButton.click();
    }

    public void waitLoadAggiungiUtentePage(){
        try {
            getWebDriverWait(10).withMessage("il bottone indietro Utenti / Aggiungi nuovo utente della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(indietroButton.get(0)));
            getWebDriverWait(10).withMessage("il titolo della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(titoloAggiungiUtente));
            getWebDriverWait(10).withMessage("il sottotitolo della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(sottotitoloAggiungiUtente));
            getWebDriverWait(10).withMessage("il box codice fiscale della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(codiceFiscaleBox));
            getWebDriverWait(10).withMessage("il box nome della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(nameBox));
            getWebDriverWait(10).withMessage("il box cognome della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(surnameBox));
            getWebDriverWait(10).withMessage("il box email della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(emailBox));
            getWebDriverWait(10).withMessage("il box conferma email della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(confirmEmailBox));
            getWebDriverWait(10).withMessage("il combobox seleziona prodotto della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(selectProductDropdown));
            getWebDriverWait(10).withMessage("il bottone indietro della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(indietroButton.get(1)));
            getWebDriverWait(10).withMessage("il bottone continua della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(continueButton));
            logger.info("Si visualizza correttamente aggiungi nuovo utente page");
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente aggiungi nuovo utente con errore:"+e.getMessage());
            Assert.fail("Non si visualizza correttamente aggiungi nuovo utente page con errore:"+e.getMessage());
        }
    }

    public void insertData(String codiceFiscale,String name, String surname, String email ){

        codiceFiscaleBox.sendKeys(codiceFiscale);
        if (nameBox.getText().equalsIgnoreCase(name) && surnameBox.getText().equalsIgnoreCase(surname)){
            logger.info("Il nome e il cognome è generato correttamente");
        }else {
            logger.error("Il nome e il cognome non è generato correttamente");
            Assert.fail("Il nome e il cognome non è generato correttamente");
        }
        emailBox.sendKeys(email);
        confirmEmailBox.sendKeys(email);
    }

    public void selectProduct(String product){
        Select combobox = new Select(selectProductDropdown);
        combobox.selectByVisibleText(product);
        logger.info("Ruolo :" + product);
        getWebDriverWait(30).withMessage("il radioBottone Amministratore della pagina aggiungi nuovo utente non è visibile").until(ExpectedConditions.visibilityOf(adminRadioButton));
    }

    public void selectRole(){
        adminRadioButton.click();
        logger.info("Si clicca sul bottone Continue");
        continueButton.click();
        getWebDriverWait(30).withMessage("il popup assegna ruolo non è visualizzata").until(ExpectedConditions.visibilityOf(confirmPopup));
    }

    public void clickRejectPopup(){
        getWebDriverWait(30).withMessage("il bottone annula non è visibile").until(ExpectedConditions.visibilityOf(annulaButton));
        logger.info("Si clicca sul bottone Annula");
        annulaButton.click();
    }

    public void clickContinueAndAssign(){
        continueButton.click();
        getWebDriverWait(30).withMessage("il bottone assegna non è visibile").until(ExpectedConditions.visibilityOf(assegnaButton));
        assegnaButton.click();
    }

    public void waitSuccessMessage(){
        if(successMessage.isDisplayed()){
            logger.info("Si visualizza correttamente messaggio di successo");
        }else if (userExistsMessage.isDisplayed()){
            logger.error("Hai già aggiunto questo utente. Selezionare un altro prodotto o aggiungere un nuovo utente");
            Assert.fail("Hai già aggiunto questo utente. Selezionare un altro prodotto o aggiungere un nuovo utente");
        }
    }

    public void waitLoadRecapPage(){
        try {
            By indietroButton = By.xpath("//button[contains(text(),'Indietro')]");
            By titolo = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/main/div/div/div[2]");
            By modificaButton = By.xpath("//button[contains(text(),'Modifica')]");
            By rimuoviButton = By.xpath("//span[contains(text(),'Rimuovi')]");
            By nameField = By.xpath("//p[contains(text(),'Nome')]");
            By surnameField = By.xpath("//p[contains(text(),'Cognome')]");
            By codiceFiscaleField = By.xpath("//p[contains(text(),'Codice Fiscale')]");
            By emailField = By.xpath("//p[contains(text(),'Email')]");


            getWebDriverWait(10).withMessage("il bottone indietro della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(indietroButton));
            getWebDriverWait(10).withMessage("il titolo della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titolo));
            getWebDriverWait(10).withMessage("il bottone modifica della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(modificaButton));
            getWebDriverWait(10).withMessage("il bottone rimuovi della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(rimuoviButton));
            getWebDriverWait(10).withMessage("la sezione nome della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(nameField));
            getWebDriverWait(10).withMessage("la sezione cognome della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(surnameField));
            getWebDriverWait(10).withMessage("la sezione codice fiscale della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(codiceFiscaleField));
            getWebDriverWait(10).withMessage("la sezione email della pagina riepilogativa non è visibile").until(ExpectedConditions.visibilityOfElementLocated(emailField));
            logger.info("Si visualizza correttamente pagina riepilogativa");
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente pagina riepilogativa con errore:"+e.getMessage());
            Assert.fail("Non si visualizza correttamente pagina riepilogativa con errore:"+e.getMessage());
        }
    }

    public void clickModifyButton(){
        getWebDriverWait(30).withMessage("il bottone modifica non è cliccabile").until(ExpectedConditions.elementToBeClickable(modificaButton));
        logger.info("Si clicca sul bottone modifica");
        modificaButton.click();
    }

    public void checkEmailBoxActive(){
      String checkEmail = emailBox.getAttribute("disabled");
      String checkConfirmEmail =  confirmEmailBox.getAttribute("disabled");

       if(checkEmail==null && checkConfirmEmail==null){
           logger.info("il campo email e il campo conferma email sono attivi");
       }else {
           logger.error("il campo email e il campo conferma email sono disattivi");
           Assert.fail("il campo email e il campo conferma email sono disattivi");
       }
    }

    public void inserisciNuovoEmail(String newMail){
        emailBox.clear();
        emailBox.sendKeys(newMail);
        getWebDriverWait(30).withMessage("il messaggio errore email non è visibile").until(ExpectedConditions.visibilityOf(wrongMailErrorMessage));
        confirmEmailBox.clear();
        confirmEmailBox.sendKeys(newMail);
    }

    public void clickConfirm(){
        getWebDriverWait(30).withMessage("il bottone conferma non è cliccabile").until(ExpectedConditions.elementToBeClickable(confermaButton));
        confermaButton.click();
        getWebDriverWait(30).withMessage("il messaggio successo non è visibile").until(ExpectedConditions.elementToBeClickable(successModifyMessage));
    }

    public void checkNewEmail(String newEmail){
        By newEmailField = By.xpath("//p[contains(text(),'" + newEmail + "')]");
        getWebDriverWait(30).withMessage("il nuovo email non è visibile").until(ExpectedConditions.visibilityOfElementLocated(newEmailField));
        logger.info("il nuovo email è visibile");
    }

    public void clickRemoveButton(){
        getWebDriverWait(30).withMessage("il bottone rimuovi non è cliccabile").until(ExpectedConditions.elementToBeClickable(removeButton));
        logger.info("Si clicca sul bottone rimuovi");
        confermaButton.click();
    }

    public void checkRemoveRolePopup(){
        getWebDriverWait(30).withMessage("il popup rimuovi ruolo non è visibile").until(ExpectedConditions.visibilityOf(removeRolePopup));
        logger.info("il popup rimouvi ruolo è visualizzata correttamente");
    }

    public void clickRemoveRoleButton(){
        getWebDriverWait(30).withMessage("il bottone rimuovi ruolo non è cliccabile").until(ExpectedConditions.elementToBeClickable(removeRuoloButton));
        logger.info("Si clicca sul bottone rimuovi ruolo");
        removeRuoloButton.click();
    }

    public void checkRoleDeletedMEssage(){
        getWebDriverWait(30).withMessage("il messaggio utente rimosso non è visibile").until(ExpectedConditions.elementToBeClickable(roleDeletedMessage));
        logger.info("Si visualizza correttamente il messaggio utente rimosso");
    }

    public void getUserDetailsPage(String name){
        WebElement findUserByName = driver.findElement(By.xpath("//p[contains(text(),'" + name + "')]"));
        getWebDriverWait(30).withMessage("l'utente apena creato non è visibile").until(ExpectedConditions.visibilityOf(findUserByName));
        findUserByName.click();
    }

}
