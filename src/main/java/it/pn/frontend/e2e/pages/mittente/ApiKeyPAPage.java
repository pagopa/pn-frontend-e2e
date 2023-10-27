package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
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

    @FindBy(xpath = "//li[@data-testid='buttonViewGroupsId']")
    WebElement visualizzaIdGruppo;

    @FindBy(id = "continue-button")
    WebElement apiContinuaButton;

    @FindBy(id = "go-to-api-keys")
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

    @FindBy(xpath = "//button[@data-testid='close-modal-button']")
    WebElement chiudiPopUp;

    public ApiKeyPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadApikeyPage() {
        try {
            By apiKeyTitle = By.id("API Key-page");
            By generaApiKeyButtonBy = By.xpath("//button[contains(@data-testid,'generateApiKey')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(apiKeyTitle));
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(generaApiKeyButtonBy));
            logger.info("Api Key Page caricata");
        } catch (TimeoutException e) {
            logger.error("Api Key Page NON caricata con errore : " + e.getMessage());
            Assert.fail("Api Key Page NON caricata con errore : " + e.getMessage());
        }
    }

    public void clickSulBottoneGeneraApiKey() {
        this.js().executeScript("arguments[0].click()",generateApiKeyButton);
    }

    public void inserireUnNomePerApiKey(String nomeApiKey) {
        this.apiKeyNameInput.sendKeys(nomeApiKey);
    }

    public void clickSulBottoneContinua() {
        this.apiContinuaButton.click();
    }

    public void siVisualizzaCorrettamenteConfermaPage() {
        try{
            By confirmationApiPageTitle = By.id("go-to-api-keys");
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
            By statoAttivoField = By.xpath("//div[@data-testid='statusChip-Attiva']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoAttivoField));
            By apiNameAttivoField = By.xpath("//p[contains(text(),'"+nomeApiKey+"')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(apiNameAttivoField));
            logger.info("Api Key ConfirmationPage caricata");
        }catch (TimeoutException e){
            logger.error("Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
            Assert.fail("Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
        }
    }

    public String getNomi(int i){
        By nomiApiKeyBy = By.xpath("//tbody/tr/td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-bri9q1')]/div/p");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomiApiKeyBy));
        List<WebElement> nomiApiKeyList = this.elements(nomiApiKeyBy);

        return nomiApiKeyList.get(i).getText();

    }

    public int getPosizioneMenuButton(){
        By statiApiKeyBy = By.xpath("//div[contains(@id,'status-chip-')]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statiApiKeyBy));
        List<WebElement> statiApiKeyList = this.elements(statiApiKeyBy);

        for(int i=0; i<statiApiKeyList.size(); i++){
            if(statiApiKeyList.get(i).getAttribute("id").equalsIgnoreCase("status-chip-Attiva")){
                if(!getNomi(i).equalsIgnoreCase("fe-TA-apikey-test")){
                    return i;
                }
            }
        }

        return -1;

    }

    public void clickMenuButton() {

        By menuAttivaButtonBy = By.xpath("//button[@data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']");
        List<WebElement> menuAttivaButton = this.elements(menuAttivaButtonBy);
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuAttivaButtonBy));

        int posizioneMenuButton = getPosizioneMenuButton();
        if(posizioneMenuButton >= 0){
            menuAttivaButton.get(posizioneMenuButton).click();
        } else {
            logger.error("Nessuna Api Key diversa da 'fe-TA-apikey-test' da bloccare");
            Assert.fail("Nessuna Api Key diversa da 'fe-TA-apikey-test' da bloccare");
        }

    }

    public void clickSuBlocca() {this.blockButton.click();}

    public void siVisualizzaPopUp() {
        try {
            By popupTitleBy = By.xpath("//h2[contains(text(),'Blocca API Key')]");
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
            By statoNotificaBloccata = By.xpath("//div[@data-testid='statusChip-Bloccata']");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBloccata));
            logger.info("La notifica è in stato bloccata");
        } catch (TimeoutException e) {
            logger.error("La notifica è in stato bloccata con errore:" + e.getMessage());
            Assert.fail("La notifica è in stato bloccata con errore:" + e.getMessage());
        }
    }

    public void clickAttivaSulMenu() {
        getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.attivaButtonNelMenu));
        this.attivaButtonNelMenu.click();}

    public void siVisualizzaPoPUpAttiva() {
        try {
            By popUpAttiva = By.xpath("//h2[contains(text(),'Attiva API Key')]");
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
            By popUpRuota = By.xpath("//h2[contains(text(),'Ruota API Key')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popUpRuota));
            logger.info("Si visualizza correttamente il popup ruota");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
            Assert.fail("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
        }
    }

    public void siVisualizzaNotificaSelezionataRuotata() {
        try {
            By popUpRuotataBy = By.xpath("//div[@data-testid='statusChip-Ruotata']");
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
        try {
            By apiKeyBy = By.xpath("//td[div/div[contains(@class,'MuiBox-root css-4l7hgf')]]");
            List<WebElement> apiKeyList = this.elements(apiKeyBy);
            for (WebElement webElement : apiKeyList) {
                this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(webElement));
                if (webElement.getText() == null) {
                    return false;
                }
            }
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }

    public boolean siVisualizzaNomeEDataConTesto() {
        try {
            By dataCellBy = By.xpath("//td[div/p[contains(@class,'MuiTypography-root MuiTypography-body1')]]");
            List<WebElement> dataCellList = this.elements(dataCellBy);
            for (WebElement webElement : dataCellList) {
                this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(webElement));
                if (webElement.getText() == null) {
                    return false;
                }
            }
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }
    public boolean siVisualizzaGruppoConTesto() {
        try {
            By gruppoCellBy = By.xpath("//td[div/div/div/div/div/span[contains(@class,'css-t63gu0')]]");
            List<WebElement> gruppoCellList = this.elements(gruppoCellBy);
            for (WebElement webElement : gruppoCellList) {
                this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(webElement));
                if (webElement.getText() == null) {
                    return false;
                }
            }
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }

    public boolean siVisualizzaStatoConTesto() {
        try {
            By statoCellBy = By.xpath("//td[div/div/div/div[@role='button']]");
            List<WebElement> statoCells = this.elements(statoCellBy);
            for (WebElement statoCell : statoCells) {
                this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(statoCell));
                if (statoCell.getText() == null) {
                    return false;
                }
            }
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }

    public boolean siVisualizzaMenuApiKey() {
        By menuButtonBy = By.xpath("//td[div/div/div/div[@role='button']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuButtonBy));
        List<WebElement> menuButtons = this.elements(menuButtonBy);
        return menuButtons.size() != 0;
    }

    public void mouseHover() {
        By statoCellBy = By.id("status-chip-Attiva");
        WebElement statoCell = this.element(statoCellBy);
        Actions action = new Actions(this.driver);
        action.moveToElement(statoCell).perform();
    }

    public void waitLoadMessaggioData() {
        try {
            By messaggioBy = By.xpath("//div[@data-popper-placement = 'bottom']");
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
            By testoInseritoBy = By.id("name");
            this.js().executeScript("arguments[0].click()", this.element(testoInseritoBy));

            String name = this.element(testoInseritoBy).getAttribute("value");
            for(int index = 0; index<name.length(); index++){
                this.element(testoInseritoBy).sendKeys(Keys.BACK_SPACE);
            }

            logger.info("Il testo è stato cancellato correttamente");
        }catch (TimeoutException e){
            logger.error("Il testo NON è stato cancellato correttamente");
            Assert.fail("Il testo è NON stato cancellato correttamente");
        }
    }

    public void siVisualizzaMessaggioErroreApiName() {
        try {
            By nameErrorMessageANBy = By.xpath("//p[contains(@id,'name-helper-text')]");
            getWebDriverWait(60).until(ExpectedConditions.visibilityOfElementLocated(nameErrorMessageANBy));
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
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.closeButtonPopUpVisualizza));
        this.closeButtonPopUpVisualizza.click();
    }

    public void clickVisualizzaIdApiKey() {
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.visualizzaIdGruppo));
        this.visualizzaIdGruppo.click();
    }

    public void popUpGruppiAssociati() {
        try{
            By popUpGruppiAssociatiBy = By.xpath("//h2[contains(text(),'Gruppi associati alla API')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popUpGruppiAssociatiBy));
            logger.info("Si visualizza correttamente il popup gruppi associati");
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente il popup gruppi associati con errore:"+e.getMessage());
            Assert.fail("Non si visualizza correttamente il popup gruppi associati con errore:"+e.getMessage());
        }
    }

    public void chiudiPopUp() {
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.chiudiPopUp));
        this.chiudiPopUp.click();
    }

}
