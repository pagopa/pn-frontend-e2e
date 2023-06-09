package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ApiKeyPAPage  extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("ApiKeyPAPage");

    @FindBy(xpath = "//button[contains(@data-testid,'generateApiKey')]")
    WebElement generateApiKeyButton;

    @FindBy(xpath = "//input[contains(@id,'name')]")
    WebElement apiKeyNameInput;

    @FindBy(xpath = "//button[contains(text(),'Continua')]")
    WebElement apiContinuaButton;

    @FindBy(xpath = "//button[contains(text(),'Torna a API Key')]")
    WebElement tornaApiButton;

    @FindBy(xpath = "//li[contains(@data-testid,'buttonBlock')]")
    WebElement blockButton;

    @FindBy(xpath = "//button[@data-testid='close-modal-button']")
    WebElement annullaButtonNelPopUp;

    @FindBy(xpath = "//button[@data-testid='action-modal-button']")
    WebElement confermaButtonNelPopUp;

    @FindBy(xpath = "//li[contains(@data-testid,'buttonEnable')]")
    WebElement attivaButtonNelMenu;

    @FindBy(xpath = "//li[contains(@data-testid,'buttonRotate')]")
    WebElement ruotaButtonNelMenu;

    @FindBy(xpath = "//input[@id='groups']")
    WebElement gruppoInput;

    @FindBy(xpath = "//li[contains(@data-testid,'buttonView')]")
    WebElement visualizzaApiButton;

    @FindBy(xpath = "//button[@data-testid='close-modal-button']")
    WebElement closeButtonPopUpVisualizza;

    public ApiKeyPAPage(WebDriver driver) {
        super(driver);
    }

    public void clickSulBottoneGeneraApiKey() {
        this.generateApiKeyButton.click();
    }

    public void inserireUnNomePerApiKey(String nomeApiKey) {
        this.apiKeyNameInput.sendKeys(nomeApiKey);
    }

    public void clickSulBottoneContinua() {
        this.apiContinuaButton.click();
    }

    public void siVisualizzaCorrettamenteConfermaPage() {
        try{
            By confirmationApiPageTitle = By.xpath("//h4[contains(text(),'API Key generata con successo!')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(confirmationApiPageTitle));
            logger.info("Api Key ConfirmationPage caricata");
        }catch (TimeoutException e){
            logger.error("Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
            Assert.fail("Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
        }
    }

    public void clickSulBottoneTornaApiKey() {
        this.tornaApiButton.click();
    }

    public void siVisualizzaNuovaApiAttiva(String nomeApiKey) {
        try{
            By statoAttivoField = By.xpath("//span[contains(text(),'Attiva')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoAttivoField));
            By apiNameAttivoField = By.xpath("//p[contains(text(),'"+nomeApiKey+"')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(apiNameAttivoField));
            logger.info("Api Key ConfirmationPage caricata");
        }catch (TimeoutException e){
            logger.error("Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
            Assert.fail("Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
        }
    }

    public void clickMenuButton() {
        /*By attivaButtonBy = By.xpath("//div[@role='button' and @data-testid='statusChip-Bloccata']");
        List<WebElement> attivaButtonList = this.elements(attivaButtonBy);

        if (attivaButtonList.size() == 0) {
            logger.error("nessun attiva button");
            Assert.fail("nessun attiva button");
        }*/

        By menuAttivaButtonBy = By.xpath("//td[div/div/div/div[@role='button' and @data-testid='statusChip-Attiva']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']");
        List<WebElement> menuAttivaButton = this.elements(menuAttivaButtonBy);

        if (menuAttivaButton.size() == 0) {
            logger.error("nessun menu attiva button");
            Assert.fail("nessun menu attiva button");
        }

        menuAttivaButton.get(0).click();
    }

    public void clickSuBlocca() {this.blockButton.click();}

    public void siVisualizzaPopUp() {
        try {
            By popupTitleBy = By.xpath("//h5[contains(text(),'Blocca API Key')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popupTitleBy));
            logger.info("Il popup si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il popup NON si visualizza correttamente con errore:" + e.getMessage());
            Assert.fail("Il popup NON si visualizza correttamente con errore:" + e.getMessage());
        }
    }

    public void clickSuAnnulla() {this.annullaButtonNelPopUp.click();}

    public void clickSuConfermaNelPopUp() {this.confermaButtonNelPopUp.click();}

    public void notificaSelezionataStatoBloccata() {
        try {
            By statoNotificaBloccata = By.xpath("//span[contains(text(),'Bloccata')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBloccata));
            logger.info("La notifica è in stato bloccata");
        } catch (TimeoutException e) {
            logger.error("La notifica è in stato bloccata con errore:" + e.getMessage());
            Assert.fail("La notifica è in stato bloccata con errore:" + e.getMessage());
        }
    }

    public void clickAttivaSulMenu() {this.attivaButtonNelMenu.click();}

    public void siVisualizzaPoPUpAttiva() {
        try {
            By popUpAttiva = By.xpath("//h5[contains(text(),'Attiva API Key')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popUpAttiva));
            logger.info("Si visualizza correttamente il popup attiva");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il popup attiva con errore:" + e.getMessage());
            Assert.fail("NON si visualizza correttamente il popup attiva con errore:" + e.getMessage());
        }
    }

    public void siVisualizzaNotificaSelezionataBloccata() {
        try {
            By statoNotificaBloccata = By.xpath("//span[contains(text(),'Attiva')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBloccata));
            logger.info("La notifica è in stato bloccata");
        } catch (TimeoutException e) {
            logger.error("La notifica è in stato bloccata con errore:" + e.getMessage());
            Assert.fail("La notifica è in stato bloccata con errore:" + e.getMessage());
        }
    }

    public void clickRuotaSulMenu() {this.ruotaButtonNelMenu.click();}

    public void siVisualizzaPoPUpRuota() {
        try {
            By popUpRuota = By.xpath("//h5[contains(text(),'Ruota API Key')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popUpRuota));
            logger.info("Si visualizza correttamente il popup ruota");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
            Assert.fail("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
        }
    }

    public void siVisualizzaNotificaSelezionataRuotata() {
        try {
            By popUpRuotataBy = By.xpath("//span[contains(text(),'Ruotata')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popUpRuotataBy));
            logger.info("Si visualizza correttamente il popup ruota");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
            Assert.fail("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
        }
    }

    public void clickMenuButtonBlocca() {
        By menuAttivaButtonBy = By.xpath("//td[div/div/div/div[@role='button' and @data-testid='statusChip-Bloccata']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']");
        List<WebElement> menuAttivaButton = this.elements(menuAttivaButtonBy);

        if (menuAttivaButton.size() == 0) {
            logger.error("nessun menu attiva button");
            Assert.fail("nessun menu attiva button");
        }
        if (menuAttivaButton.get(0).isDisplayed()){
            menuAttivaButton.get(0).click();
        }else {
            this.js().executeScript("arguments[0].scrollIntoView(true);",menuAttivaButton.get(0));
            menuAttivaButton.get(0).click();
        }


    }

    public boolean siVisualizzaApiKeyConTesto() {
        By apiKeyBy = By.xpath("//td[div/div[contains(@class,'MuiBox-root css-4l7hgf')]]");
        List<WebElement> apiKeyList = this.elements(apiKeyBy);
        for (int i = 0; i < apiKeyList.size(); i++) {
            if (apiKeyList.get(i).getText() == null){
                return false;
            }
        }
        return true;
    }

    public boolean siVisualizzaNomeEDataConTesto() {
        By dataCellBy = By.xpath("//td[div/p[contains(@class,'MuiTypography-root MuiTypography-body1')]]");
        List<WebElement> dataCellList = this.elements(dataCellBy);
        for (int i=0; i < dataCellList.size(); i++){
            if (dataCellList.get(i).getText() == null){
                return false;
            }
        }
        return true;
    }
    public boolean siVisualizzaGruppoConTesto() {
        By gruppoCellBy = By.xpath("//td[div/div/div/div/div/span[contains(@class,'css-t63gu0')]]");
        List<WebElement> gruppoCellList = this.elements(gruppoCellBy);
        for (int i = 0; i < gruppoCellList.size(); i++){
            if (gruppoCellList.get(i).getText() == null){
                return false;
            }
        }
        return true;
    }

    public boolean siVisualizzaStatoConTesto() {
        By statoCellBy = By.xpath("//td[div/div/div/div[@role='button' ]]");
        List<WebElement> statoCells = this.elements(statoCellBy);
        for (int i = 0; i < statoCells.size(); i++) {
            if (statoCells.get(i).getText() == null) {
                return false;
            }
        }
        return true;
    }

    public boolean siVisualizzaMenuApiKey() {
        By menuButtonBy = By.xpath("//td[div/div/div/div[@role='button']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuButtonBy));
        List<WebElement> menuButtons = this.elements(menuButtonBy);
        return menuButtons.size() != 0;
    }

    public void mouseHover() {
        By statoCellBy = By.xpath("//td[div/div/div/div[@role='button' ]]");
        WebElement statoCell = this.element(statoCellBy);
        Actions action = new Actions(this.driver);
        action.moveToElement(statoCell).perform();
    }

    public void waitLoadMessaggioData() {
        try {
            By messaggioBy = By.xpath("//div[contains(@id,'mui-16')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(messaggioBy));
            logger.info("Il messaggio con la data di creazione si vede correttamente");
        }catch (TimeoutException e){
            logger.error("Il messaggio con la data di creazione NON si vede correttamente con errore: "+e.getMessage());
            Assert.fail("Il messaggio con la data di creazione NON si vede correttamente con errore: "+e.getMessage());
        }
    }

    public void inserireGruppoApi(String gruppo) {
        this.gruppoInput.sendKeys(gruppo);
        this.apiKeyNameInput.click();
    }

    public void cancellareTestoInserito() {
        try{
            By testoInseritoBy = By.xpath("//input[contains(@id,'name')]");
            this.js().executeScript("arguments[0].click()", this.element(testoInseritoBy));
            this.element(testoInseritoBy).clear();
            logger.info("Il testo è stato cancellato correttamente");
        }catch (TimeoutException e){
            logger.error("Il testo NON è stato cancellato correttamente");
            Assert.fail("Il testo è NON stato cancellato correttamente");
        }
    }

    public void siVisualizzaMessaggioErroreApiName() {
        try {
            By nameErrorMessageANBy = By.xpath("//p[contains(@id,'name-helper-text')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nameErrorMessageANBy));
            logger.info("Si visualizza correttamente il messaggio di errore");
        }catch (TimeoutException e){
            logger.error("NON si visualizza correttamente il messaggio di errore");
            Assert.fail("NON si visualizza correttamente il messaggio di errore");
        }
    }

    public void clickSuVisualizza() {
        this.visualizzaApiButton.click();
    }

    public void siVisualizzaPopUpVisualizza() {
        try {
            By subTitleVisualizzaBy = By.xpath("//p[contains(@data-testid,'subtitle-top')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(subTitleVisualizzaBy));
            logger.info("Si visualizza correttamente il pop up visualizza");
        }catch (TimeoutException e){
            logger.error("NON si visualizza correttamente il pop up visualizza");
            Assert.fail("NON si visualizza correttamente il pop up visualizza");
        }
    }

    public void chiudiPopUpVisualizza() {
        this.closeButtonPopUpVisualizza.click();
    }
}
