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

    @FindBy(id = "generate-api-key")
    WebElement generateApiKeyButton;

    @FindBy(id = "name")
    WebElement apiKeyNameInput;

    @FindBy(id = "button-view-groups-id")
    WebElement visualizzaIdGruppo;

    @FindBy(id = "continue-button")
    WebElement apiContinuaButton;

    @FindBy(id = "go-to-api-keys")
    WebElement tornaApiButton;

    @FindBy(id = "button-block")
    WebElement blockButton;

    @FindBy(id = "close-modal-button")
    WebElement annullaButtonNelPopUp;

    @FindBy(id = "action-modal-button")
    WebElement confermaButtonNelPopUp;

    @FindBy(xpath = "//li[contains(@data-testid,'buttonEnable')]")
    WebElement attivaButtonNelMenu;

    @FindBy(id = "button-rotate")
    WebElement ruotaButtonNelMenu;

    @FindBy(id = "groups")
    WebElement gruppoInput;

    @FindBy(id = "button-view")
    WebElement visualizzaApiButton;

    @FindBy(id = "close-modal-button")
    WebElement closeButtonPopUpVisualizza;

    public ApiKeyPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadApikeyPage() {
        try {
            By apiKeyTitle = By.id("API Key-page");
            this.getWebDriverWait(30).withMessage("il titolo della pagina Apikey non è visibile").until(ExpectedConditions.visibilityOfElementLocated(apiKeyTitle));
            this.getWebDriverWait(40).withMessage("il bottone genera ApiKey non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.generateApiKeyButton));
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
        getWebDriverWait(30).withMessage("Il campo Nome Apikey non è visibile").until(ExpectedConditions.visibilityOf(this.apiKeyNameInput));
        this.apiKeyNameInput.sendKeys(nomeApiKey);
    }

    public void clickSulBottoneContinua() {
        getWebDriverWait(40).withMessage("Il bottone Continua non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.apiContinuaButton));
        this.apiContinuaButton.click();
    }

    public void siVisualizzaCorrettamenteConfermaPage() {
        try{
            By confirmationApiPageTitle = By.id("go-to-api-keys");
            this.getWebDriverWait(30).withMessage("Il titolo della pagina conferma Apikey non è visibile").until(ExpectedConditions.visibilityOfElementLocated(confirmationApiPageTitle));
            logger.info("Api Key ConfirmationPage caricata");
        }catch (TimeoutException e){
            logger.error("Il titolo della Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
            Assert.fail("Il titolo della Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
        }
    }

    public void clickSulBottoneTornaApiKey() {
        this.tornaApiButton.click();
    }

    public void siVisualizzaNuovaApiAttiva(String nomeApiKey) {
        try{
            By statoAttivoField = By.xpath("//div[@data-testid='statusChip-Attiva']");
            this.getWebDriverWait(30).withMessage("lo stato dell'ApiKey non è Attiva").until(ExpectedConditions.visibilityOfElementLocated(statoAttivoField));
            By apiNameAttivoField = By.xpath("//p[contains(text(),'"+nomeApiKey+"')]");
            this.getWebDriverWait(30).withMessage("Il nome del ApiKey attiva non è: "+nomeApiKey).until(ExpectedConditions.visibilityOfElementLocated(apiNameAttivoField));
            logger.info("Api Key ConfirmationPage caricata");
        }catch (TimeoutException e){
            logger.error("Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
            Assert.fail("Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
        }
    }

    public String getNomi(int i){
        By nomiApiKeyBy = By.xpath("//tbody/tr/td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1kqk1ww')]/div/p");
        this.getWebDriverWait(30).withMessage("la lista dei nomi ApiKey non è visibile ").until(ExpectedConditions.visibilityOfElementLocated(nomiApiKeyBy));
        List<WebElement> nomiApiKeyList = this.elements(nomiApiKeyBy);

        return nomiApiKeyList.get(i).getText();

    }

    public int getPosizioneMenuButton(){
        By statiApiKeyBy = By.xpath("//div[contains(@id,'status-chip-')]");
        this.getWebDriverWait(30).withMessage("lista stati ApiKey non trovata").until(ExpectedConditions.visibilityOfElementLocated(statiApiKeyBy));
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
        this.getWebDriverWait(30).withMessage("menu Apikey da Bloccare non trovato").until(ExpectedConditions.visibilityOfElementLocated(menuAttivaButtonBy));

        int posizioneMenuButton = getPosizioneMenuButton();
        if(posizioneMenuButton >= 0){
            menuAttivaButton.get(posizioneMenuButton).click();
        } else {
            logger.error("Nessuna Api Key diversa da 'fe-TA-apikey-test' da bloccare");
            Assert.fail("Nessuna Api Key diversa da 'fe-TA-apikey-test' da bloccare");
        }

    }

    public void clickSuBlocca() {
        this.getWebDriverWait(40).withMessage("Il bottone Blocca apiKey non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.blockButton));
        this.blockButton.click();
    }

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

    public void clickSuAnnulla() {
        this.getWebDriverWait(40).withMessage("il Bottone Annulla nel pop up non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.annullaButtonNelPopUp));
        this.annullaButtonNelPopUp.click();
    }

    public void clickSuConfermaNelPopUp() {
        this.getWebDriverWait(40).withMessage("il Bottone Conferma nel pop up non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.confermaButtonNelPopUp));
        this.confermaButtonNelPopUp.click();}

    public void notificaSelezionataStatoBloccata() {
        try {
            By statoNotificaBloccata = By.id("status-chip-Bloccata");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBloccata));
            logger.info("La notifica è in stato bloccata");
        } catch (TimeoutException e) {
            logger.error("La notifica è in stato bloccata con errore:" + e.getMessage());
            Assert.fail("La notifica è in stato bloccata con errore:" + e.getMessage());
        }
    }

    public void clickAttivaSulMenu() {
        getWebDriverWait(30).withMessage("il bottone attiva seul menu non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.attivaButtonNelMenu));
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

    public void clickRuotaSulMenu() {
        getWebDriverWait(30).withMessage("Il Bottone ruota del menu apikey non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.ruotaButtonNelMenu));
        this.ruotaButtonNelMenu.click();}

    public void siVisualizzaPoPUpRuota() {
        try {
            By popUpRuota = By.xpath("//h2[contains(text(),'Ruota API Key')]");
            getWebDriverWait(30).withMessage("Il titolo Ruota Apikey  sul pop up non trovato").until(ExpectedConditions.visibilityOfElementLocated(popUpRuota));
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

    public int verificaBottoni(){
        By menuBloccaButtonBy = By.xpath("//td/div/div/div/div[@role='button' and @data-testid='statusChip-Bloccata']");
        this.getWebDriverWait(20).withMessage("la lista bottoni Bloccati non trovata").until(ExpectedConditions.visibilityOfElementLocated(menuBloccaButtonBy));
        List<WebElement> listaBottoniBloccati = this.elements(menuBloccaButtonBy);

        boolean ruotata;
        for (int i=0; i<listaBottoniBloccati.size(); i++) {
            this.js().executeScript("arguments[0].scrollIntoView(true);", listaBottoniBloccati.get(i));
            ruotata = verificaBottoneCheNonSiaRuotata(listaBottoniBloccati.get(i));
            if(!ruotata){
                return i;
            }
        }

        return -1;

    }

    public boolean verificaBottoneCheNonSiaRuotata(WebElement currentButton){

        Actions action = new Actions(this.driver);
        action.moveToElement(currentButton).perform();


        By statiBottoneBy = By.xpath("//div[@class='MuiBox-root css-13brihr']/div[@class='MuiBox-root css-0']");
        this.getWebDriverWait(20).withMessage("la lista stati bottone non trovata").until(ExpectedConditions.visibilityOfElementLocated(statiBottoneBy));
        List<WebElement> listaStatiBottone = this.elements(statiBottoneBy);

        String stato = "Ruotata";
        for(WebElement lista: listaStatiBottone){
            if(lista.getText().contains(stato)){
                action.moveToElement(currentButton, 100,0 ).perform();
                return true;
            }
        }

        return false;

    }

    public void clickMenuButtonBlocca() {

        int posizione = verificaBottoni();

        if(posizione >= 0){
            clickSulBottoneBloccatoMaiRuotato(posizione);
        }else{
            logger.info("Nessuna Api Key bloccata da attivare, procedo a bloccare una attivata");
            clickMenuButton();
            clickSuBlocca();
            siVisualizzaPopUp();
            clickSuConfermaNelPopUp();
            int newPosizione = verificaBottoni();
            clickSulBottoneBloccatoMaiRuotato(newPosizione);
        }
    }

    public void clickSulBottoneBloccatoMaiRuotato(int posizione){
        By menuAttivaButtonBy = By.xpath("//td[div/div/div/div[@role='button' and @data-testid='statusChip-Bloccata']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']");
        this.getWebDriverWait(20).withMessage("la lista attiva bottone non trovata").until(ExpectedConditions.visibilityOfElementLocated(menuAttivaButtonBy));
        List<WebElement> menuAttivaButton = this.elements(menuAttivaButtonBy);

        menuAttivaButton.get(posizione).click();
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
        this.getWebDriverWait(30).withMessage("il bottone menu del apikey non trovato").until(ExpectedConditions.visibilityOfElementLocated(menuButtonBy));
        List<WebElement> menuButtons = this.elements(menuButtonBy);
        return menuButtons.size() != 0;
    }

    public void mouseHover() {
        By statoCellBy = By.id("status-chip-Attiva");
        WebElement statoCell = this.element(statoCellBy);
        getWebDriverWait(30).withMessage("stato attiva non trovato").until(ExpectedConditions.visibilityOf(statoCell));
        Actions action = new Actions(this.driver);
        action.moveToElement(statoCell).perform();
        logger.info("mouse hover sullo stato attiva effetuato correttamente");
    }

    public void waitLoadMessaggioData() {
        try {
            By messaggioBy = By.xpath("//div[@data-popper-placement = 'bottom']");
            this.getWebDriverWait(40).until(ExpectedConditions.visibilityOfElementLocated(messaggioBy));
            logger.info("Il messaggio con la data di creazione si vede correttamente");
        }catch (TimeoutException e){
            logger.error("Il messaggio con la data di creazione NON si vede correttamente con errore: "+e.getMessage());
            Assert.fail("Il messaggio con la data di creazione NON si vede correttamente con errore: "+e.getMessage());
        }
    }

    public void inserireGruppoApi(String gruppo) {
        getWebDriverWait(30).withMessage("Il campo gruppo Apikey non è visibile").until(ExpectedConditions.visibilityOf(this.gruppoInput));
        this.gruppoInput.sendKeys(gruppo);
        By groupOption = By.id("groups-option-0");
        getWebDriverWait(60).withMessage("Il campo Nome del Gruppo Apikey non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.element(groupOption)));
        element(groupOption).click();
        this.gruppoInput.click();

    }

    public void cancellareTestoInserito() {
        try{
            By testoInseritoBy = By.id("name");
            this.getWebDriverWait(40).withMessage("il campo name non è visibile").until(ExpectedConditions.visibilityOfElementLocated(testoInseritoBy));
            this.js().executeScript("arguments[0].click()", this.element(testoInseritoBy));

            String name = this.element(testoInseritoBy).getAttribute("value");
            for(int index = 0; index<name.length(); index++){
                this.element(testoInseritoBy).sendKeys(Keys.BACK_SPACE);
            }

            logger.info("Il testo è stato cancellato correttamente");
        }catch (TimeoutException e){
            logger.error("Il testo NON è stato cancellato correttamente"+e.getMessage());
            Assert.fail("Il testo è NON stato cancellato correttamente"+e.getMessage());
        }
    }

    public void siVisualizzaMessaggioErroreApiName() {
        try {
            By nameErrorMessageANBy = By.xpath("//p[contains(@id,'name-helper-text')]");
            getWebDriverWait(60).until(ExpectedConditions.visibilityOfElementLocated(nameErrorMessageANBy));
            logger.info("Si visualizza correttamente il messaggio di errore");
        }catch (TimeoutException e){
            logger.error("NON si visualizza correttamente il messaggio di errore"+e.getMessage());
            Assert.fail("NON si visualizza correttamente il messaggio di errore"+e.getMessage());
        }
    }

    public void clickSuVisualizza() {
        getWebDriverWait(40).withMessage("Il Bottone visualizza non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.visualizzaApiButton));
        this.visualizzaApiButton.click();
    }

    public void siVisualizzaPopUpVisualizza() {
        try {
            By subTitleVisualizzaBy = By.id("subtitle-top");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(subTitleVisualizzaBy));
            logger.info("Si visualizza correttamente il sotto titolo del pop up");
        }catch (TimeoutException e){
            logger.error("NON si visualizza correttamente il sotto titolo pop up: "+e.getMessage());
            Assert.fail("NON si visualizza correttamente il sotto titolo pop up: "+e.getMessage());
        }
    }

    public void chiudiPopUpVisualizza() {
        this.getWebDriverWait(30).withMessage("il Bottone chiudere pop up non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(this.closeButtonPopUpVisualizza));
        this.closeButtonPopUpVisualizza.click();
    }

    public void clickVisualizzaIdApiKey() {
        this.getWebDriverWait(30).withMessage("Il bottone visualizza Id api key non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(this.visualizzaIdGruppo));
        this.visualizzaIdGruppo.click();
    }

    public void popUpGruppiAssociati() {
        try{
            By popUpGruppiAssociatiBy = By.xpath("//h2[contains(text(),'Gruppi associati alla API')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popUpGruppiAssociatiBy));
            logger.info("Si visualizza correttamente il titolo popup Gruppi associati alla API");
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente il titolo popup Gruppi associati alla API con errore:"+e.getMessage());
            Assert.fail("Non si visualizza correttamente il titolo popup Gruppi associati alla API con errore:"+e.getMessage());
        }
    }

}
