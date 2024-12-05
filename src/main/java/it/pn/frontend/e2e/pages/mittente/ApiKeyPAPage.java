package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
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
/*
* Principali Modifiche
Spring Boot Integration: La classe è annotata con @Component per essere riconosciuta come bean.
Iniezione del WebDriver: Il driver viene iniettato tramite @Autowired nel costruttore.
Logging Ottimizzato: Utilizzo di placeholder {} con SLF4J nei metodi di logging per migliore leggibilità e prestazioni.
* dubbio su Action per capire come iniettarlo in Spring
*
* */

public class ApiKeyPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ApiKeyPAPage.class);

    @FindBy(id = "generate-api-key")
    private WebElement generateApiKeyButton;

    @FindBy(id = "name")
    private WebElement apiKeyNameInput;

    @FindBy(id = "button-view-groups-id")
    private WebElement visualizzaIdGruppo;

    @FindBy(id = "continue-button")
    private WebElement apiContinuaButton;

    @FindBy(id = "go-to-api-keys")
    private WebElement tornaApiButton;

    @FindBy(id = "button-block")
    private WebElement blockButton;

    @FindBy(id = "close-modal-button")
    private WebElement annullaButtonNelPopUp;

    @FindBy(id = "action-modal-button")
    private WebElement confermaButtonNelPopUp;

    @FindBy(xpath = "//li[contains(@data-testid,'buttonEnable')]")
    private WebElement attivaButtonNelMenu;

    @FindBy(id = "button-rotate")
    private WebElement ruotaButtonNelMenu;

    @FindBy(id = "groups")
    private WebElement gruppoInput;

    @FindBy(id = "button-view")
    private WebElement visualizzaApiButton;

    @FindBy(id = "close-modal-button")
    private WebElement closeButtonPopUpVisualizza;


    private WebTool webTool;

    public ApiKeyPAPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadApikeyPage() {
        try {
            WebElement apiKeyTitle = driver.findElement(By.id("API Key-page"));
            getWebDriverWait(30).withMessage("Il titolo della pagina ApiKey non è visibile")
                    .until(ExpectedConditions.visibilityOf(apiKeyTitle));
            generateApiKeyButton = driver.findElement(By.id("generate-api-key"));
            getWebDriverWait(40).withMessage("Il bottone genera ApiKey non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(generateApiKeyButton));
            logger.info("Api Key Page caricata");
        } catch (TimeoutException e) {
            logger.error("Api Key Page NON caricata con errore: {}", e.getMessage());
            Assertions.fail("Api Key Page NON caricata con errore: " + e.getMessage());
        }
    }

    public void clickSulBottoneGeneraApiKey() {
        generateApiKeyButton = driver.findElement(By.id("generate-api-key"));
        js().executeScript("arguments[0].click()", generateApiKeyButton);
    }

    public void inserireUnNomePerApiKey(String nomeApiKey) {
        apiKeyNameInput = driver.findElement(By.id("name"));
        getWebDriverWait(30).withMessage("Il campo Nome Apikey non è visibile")
                .until(ExpectedConditions.visibilityOf(apiKeyNameInput));
        apiKeyNameInput.sendKeys(nomeApiKey);
    }

    public void clickSulBottoneContinua() {
        apiContinuaButton = driver.findElement(By.id("continue-button"));
        getWebDriverWait(40).withMessage("Il bottone Continua non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(apiContinuaButton));
        apiContinuaButton.click();
    }

    public void siVisualizzaCorrettamenteConfermaPage() {
        try {
            WebElement confirmationApiPageTitle = driver.findElement(By.id("go-to-api-keys"));
            getWebDriverWait(30).withMessage("Il titolo della pagina conferma Apikey non è visibile")
                    .until(ExpectedConditions.visibilityOf(confirmationApiPageTitle));
            logger.info("Api Key Confirmation Page caricata");
        } catch (TimeoutException e) {
            logger.error("Il titolo della Api Key ConfirmationPage NON caricata con errore: {}", e.getMessage());
            Assertions.fail("Il titolo della Api Key ConfirmationPage NON caricata con errore: " + e.getMessage());
        }
    }

    public void clickSulBottoneTornaApiKey() {
        tornaApiButton = driver.findElement(By.id("go-to-api-keys"));
        tornaApiButton.click();
    }

    public void siVisualizzaNuovaApiAttiva(String nomeApiKey) {
        try {
            WebElement statoAttivoField = driver.findElement(By.xpath("//div[@data-testid='statusChip-Attiva']"));
            getWebDriverWait(30).withMessage("lo stato dell'ApiKey non è Attiva")
                    .until(ExpectedConditions.visibilityOf(statoAttivoField));
            WebElement apiNameAttivoField = driver.findElement(By.xpath("//p[contains(text(),'" + nomeApiKey + "')]"));
            getWebDriverWait(30).withMessage("Il nome del ApiKey attiva non è: " + nomeApiKey)
                    .until(ExpectedConditions.visibilityOf(apiNameAttivoField));
            logger.info("Api Key Confirmation Page caricata");
        } catch (TimeoutException e) {
            logger.error("Api Key Confirmation Page NON caricata con errore: {}", e.getMessage());
            Assertions.fail("Api Key Confirmation Page NON caricata con errore: " + e.getMessage());
        }
    }

    public String getNomi(int i) {
        List<WebElement> nomiApiKeyBy = driver.findElements(By.xpath("//tbody/tr/td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1kqk1ww')]/div/p"));
        getWebDriverWait(30).withMessage("la lista dei nomi ApiKey non è visibile")
                .until(ExpectedConditions.visibilityOfAllElements(nomiApiKeyBy));
        return nomiApiKeyBy.get(i).getText();
    }

    public int getPosizioneMenuButton() {
        List<WebElement> statiApiKeyBy = driver.findElements(By.xpath("//div[contains(@id,'status-chip-')]"));
        getWebDriverWait(30).withMessage("lista stati ApiKey non trovata")
                .until(ExpectedConditions.visibilityOfAllElements(statiApiKeyBy));
        for (int i = 0; i < statiApiKeyBy.size(); i++) {
            if (statiApiKeyBy.get(i).getAttribute("id").equalsIgnoreCase("status-chip-Attiva")) {
                if (!getNomi(i).equalsIgnoreCase("fe-TA-apikey-test")) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void clickMenuButton() {
        List<WebElement> menuAttivaButtonBy = driver.findElements(By.xpath("//button[@data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']"));
        getWebDriverWait(30).withMessage("menu Apikey da Bloccare non trovato")
                .until(ExpectedConditions.visibilityOfAllElements(menuAttivaButtonBy));
        int posizioneMenuButton = getPosizioneMenuButton();
        if (posizioneMenuButton >= 0) {
            menuAttivaButtonBy.get(posizioneMenuButton).click();
        } else {
            logger.error("Nessuna Api Key diversa da 'fe-TA-apikey-test' da bloccare");
            Assertions.fail("Nessuna Api Key diversa da 'fe-TA-apikey-test' da bloccare");
        }
    }

    public void clickSuBlocca() {
        blockButton = driver.findElement(By.id("button-block"));
        getWebDriverWait(40).withMessage("Il bottone Blocca apiKey non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.blockButton));
        blockButton.click();
    }

    public void siVisualizzaPopUp() {
        try {
            WebElement popupTitleBy = driver.findElement(By.xpath("//h2[contains(text(),'Blocca API Key')]"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(popupTitleBy));
            logger.info("Il popup si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il popup NON si visualizza correttamente con errore:" + e.getMessage());
            Assertions.fail("Il popup NON si visualizza correttamente con errore:" + e.getMessage());
        }
    }

    public void clickSuAnnulla() {
        annullaButtonNelPopUp = driver.findElement(By.id("close-modal-button"));
        getWebDriverWait(40).withMessage("il Bottone Annulla nel pop up non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.annullaButtonNelPopUp));
        annullaButtonNelPopUp.click();
    }

    public void clickSuConfermaNelPopUp() {
        confermaButtonNelPopUp = driver.findElement(By.id("action-modal-button"));
        getWebDriverWait(40).withMessage("il Bottone Conferma nel pop up non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.confermaButtonNelPopUp));
        confermaButtonNelPopUp.click();
    }

    public void notificaSelezionataStatoBloccata() {
        try {
            WebElement statoNotificaBloccata = driver.findElement(By.id("status-chip-Bloccata"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(statoNotificaBloccata));
            logger.info("La notifica è in stato bloccata");
        } catch (TimeoutException e) {
            logger.error("La notifica è in stato bloccata con errore:" + e.getMessage());
            Assertions.fail("La notifica è in stato bloccata con errore:" + e.getMessage());
        }
    }

    public void clickAttivaSulMenu() {
        attivaButtonNelMenu = driver.findElement(By.xpath("//li[contains(@data-testid,'buttonEnable')]"));
        getWebDriverWait(30).withMessage("il bottone attiva seul menu non è cliccabile").until(ExpectedConditions.elementToBeClickable(attivaButtonNelMenu));
        attivaButtonNelMenu.click();
    }

    public void siVisualizzaPoPUpAttiva() {
        try {
            WebElement popUpAttiva = driver.findElement(By.xpath("//h2[contains(text(),'Attiva API Key')]"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(popUpAttiva));
            logger.info("Si visualizza correttamente il popup attiva");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il popup attiva con errore:" + e.getMessage());
            Assertions.fail("NON si visualizza correttamente il popup attiva con errore:" + e.getMessage());
        }
    }

    public void siVisualizzaNotificaSelezionataBloccata() {
        try {
            WebElement statoNotificaBloccata = driver.findElement(By.xpath("//span[contains(text(),'Attiva')]"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(statoNotificaBloccata));
            logger.info("La notifica è in stato bloccata");
        } catch (TimeoutException e) {
            logger.error("La notifica è in stato bloccata con errore:" + e.getMessage());
            Assertions.fail("La notifica è in stato bloccata con errore:" + e.getMessage());
        }
    }

    public void clickRuotaSulMenu() {
        ruotaButtonNelMenu = driver.findElement(By.id("button-rotate"));
        getWebDriverWait(30).withMessage("Il Bottone ruota del menu apikey non è cliccabile").until(ExpectedConditions.elementToBeClickable(ruotaButtonNelMenu));
        ruotaButtonNelMenu.click();
    }

    public void siVisualizzaPoPUpRuota() {
        try {
            WebElement popUpRuota = driver.findElement(By.xpath("//h2[contains(text(),'Ruota API Key')]"));
            getWebDriverWait(30).withMessage("Il titolo Ruota Apikey  sul pop up non trovato").until(ExpectedConditions.visibilityOf(popUpRuota));
            logger.info("Si visualizza correttamente il popup ruota");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
            Assertions.fail("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
        }
    }

    public void siVisualizzaNotificaSelezionataRuotata() {
        try {
            WebElement popUpRuotataBy = driver.findElement(By.xpath("//div[@data-testid='statusChip-Ruotata']"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(popUpRuotataBy));
            logger.info("Si visualizza correttamente il popup ruota");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
            Assertions.fail("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
        }
    }

    public int verificaBottoni() {
        List<WebElement> menuBloccaButtonBy = driver.findElements(By.xpath("//td/div/div/div/div[@role='button' and @data-testid='statusChip-Bloccata']"));
        getWebDriverWait(20).withMessage("la lista bottoni Bloccati non trovata").until(ExpectedConditions.visibilityOfAllElements(menuBloccaButtonBy));

        boolean ruotata;
        for (int i = 0; i < menuBloccaButtonBy.size(); i++) {
            js().executeScript("arguments[0].scrollIntoView(true);", menuBloccaButtonBy.get(i));
            ruotata = verificaBottoneCheNonSiaRuotata(menuBloccaButtonBy.get(i));
            if (!ruotata) {
                return i;
            }
        }
        return -1;
    }

    public boolean verificaBottoneCheNonSiaRuotata(WebElement currentButton) {

        Actions action = new Actions(this.driver);
        action.moveToElement(currentButton).perform();

        List<WebElement> statiBottoneBy = driver.findElements(By.xpath("//div[@class='MuiBox-root css-13brihr']/div[@class='MuiBox-root css-0']"));
        getWebDriverWait(20).withMessage("la lista stati bottone non trovata").until(ExpectedConditions.visibilityOfAllElements(statiBottoneBy));

        String stato = "Ruotata";
        for (WebElement lista : statiBottoneBy) {
            if (lista.getText().contains(stato)) {
                action.moveToElement(currentButton, 100, 0).perform();
                return true;
            }
        }

        return false;
    }

    public void clickMenuButtonBlocca() {

        int posizione = verificaBottoni();

        if (posizione >= 0) {
            clickSulBottoneBloccatoMaiRuotato(posizione);
        } else {
            logger.info("Nessuna Api Key bloccata da attivare, procedo a bloccare una attivata");
            clickMenuButton();
            clickSuBlocca();
            siVisualizzaPopUp();
            clickSuConfermaNelPopUp();
            int newPosizione = verificaBottoni();
            clickSulBottoneBloccatoMaiRuotato(newPosizione);
        }
    }

    public void clickSulBottoneBloccatoMaiRuotato(int posizione) {
        List<WebElement> menuAttivaButtonBy = driver.findElements(By.xpath("//td[div/div/div/div[@role='button' and @data-testid='statusChip-Bloccata']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']"));
        getWebDriverWait(20).withMessage("la lista attiva bottone non trovata").until(ExpectedConditions.visibilityOfAllElements(menuAttivaButtonBy));

        menuAttivaButtonBy.get(posizione).click();
    }

    public boolean siVisualizzaApiKeyConTesto() {
        try {
            List<WebElement> apiKeyBy = driver.findElements(By.xpath("//td[div/div[contains(@class,'MuiBox-root css-4l7hgf')]]"));
            for (WebElement webElement : apiKeyBy) {
                getWebDriverWait(30).until(ExpectedConditions.visibilityOf(webElement));
                if (webElement.getText() == null) {
                    return false;
                }
            }
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean siVisualizzaNomeEDataConTesto() {
        try {
            List<WebElement> dataCellBy = driver.findElements(By.xpath("//td[div/p[contains(@class,'MuiTypography-root MuiTypography-body1')]]"));
            for (WebElement webElement : dataCellBy) {
                getWebDriverWait(30).until(ExpectedConditions.visibilityOf(webElement));
                if (webElement.getText() == null) {
                    return false;
                }
            }
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean siVisualizzaGruppoConTesto() {
        try {
            List<WebElement> gruppoCellList = driver.findElements(By.xpath("//td[div/div/div/div/div/span[contains(@class,'css-t63gu0')]]"));
            for (WebElement webElement : gruppoCellList) {
                getWebDriverWait(30).until(ExpectedConditions.visibilityOf(webElement));
                if (webElement.getText() == null) {
                    return false;
                }
            }
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean siVisualizzaStatoConTesto() {
        try {
            List<WebElement> statoCells = driver.findElements(By.xpath("//td[div/div/div/div[@role='button']]"));
            for (WebElement statoCell : statoCells) {
                getWebDriverWait(30).until(ExpectedConditions.visibilityOf(statoCell));
                if (statoCell.getText() == null) {
                    return false;
                }
            }
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean siVisualizzaMenuApiKey() {
        List<WebElement> menuButtonBy = driver.findElements(By.xpath("//td[div/div/div/div[@role='button']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']"));
        getWebDriverWait(30).withMessage("il bottone menu del apikey non trovato").until(ExpectedConditions.visibilityOfAllElements(menuButtonBy));
        return !menuButtonBy.isEmpty();
    }

    public void mouseHover() {
        WebElement statoCellBy = driver.findElement(By.id("status-chip-Attiva"));
        getWebDriverWait(30).withMessage("stato attiva non trovato").until(ExpectedConditions.visibilityOf(statoCellBy));
        Actions action = new Actions(driver);
        action.moveToElement(statoCellBy).perform();
        logger.info("mouse hover sullo stato attiva effetuato correttamente");
    }

    public void waitLoadMessaggioData() {
        try {
            WebElement messaggioBy = driver.findElement(By.xpath("//div[@data-popper-placement = 'bottom']"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(messaggioBy));
            logger.info("Il messaggio con la data di creazione si vede correttamente");
        } catch (TimeoutException e) {
            logger.error("Il messaggio con la data di creazione NON si vede correttamente con errore: " + e.getMessage());
            Assertions.fail("Il messaggio con la data di creazione NON si vede correttamente con errore: " + e.getMessage());
        }
    }

    public void inserireGruppoApi(String gruppo) {
        gruppoInput = driver.findElement(By.id("groups"));
        getWebDriverWait(30).withMessage("Il campo gruppo Apikey non è visibile").until(ExpectedConditions.visibilityOf(gruppoInput));
        gruppoInput.sendKeys(gruppo);
        WebElement groupOption = driver.findElement(By.id("groups-option-0"));
        getWebDriverWait(60).withMessage("Il campo Nome del Gruppo Apikey non è cliccabile").until(ExpectedConditions.elementToBeClickable(groupOption));
        groupOption.click();
        gruppoInput.click();

    }

    public void cancellareTestoInserito() {
        try {
            WebElement testoInseritoBy = driver.findElement(By.id("name"));
            this.getWebDriverWait(40).withMessage("il campo name non è visibile").until(ExpectedConditions.visibilityOf(testoInseritoBy));
            this.js().executeScript("arguments[0].click()", testoInseritoBy);

            String name = testoInseritoBy.getAttribute("value");
            for (int index = 0; index < name.length(); index++) {
                testoInseritoBy.sendKeys(Keys.BACK_SPACE);
            }

            logger.info("Il testo è stato cancellato correttamente");
        } catch (TimeoutException e) {
            logger.error("Il testo NON è stato cancellato correttamente" + e.getMessage());
            Assertions.fail("Il testo è NON stato cancellato correttamente" + e.getMessage());
        }
    }

    public void siVisualizzaMessaggioErroreApiName() {
        try {
            WebElement nameErrorMessageANBy = driver.findElement(By.xpath("//p[contains(@id,'name-helper-text')]"));
            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(nameErrorMessageANBy));
            logger.info("Si visualizza correttamente il messaggio di errore");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il messaggio di errore" + e.getMessage());
            Assertions.fail("NON si visualizza correttamente il messaggio di errore" + e.getMessage());
        }
    }

    public void clickSuVisualizza() {
        visualizzaApiButton = driver.findElement(By.id("button-view"));
        getWebDriverWait(10).withMessage("Il Bottone visualizza non è cliccabile").until(ExpectedConditions.elementToBeClickable(visualizzaApiButton));
        visualizzaApiButton.click();
    }

    public void siVisualizzaPopUpVisualizza() {
        try {
            WebElement subTitleVisualizzaBy = driver.findElement(By.id("subtitle-top"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(subTitleVisualizzaBy));
            logger.info("Si visualizza correttamente il sotto titolo del pop up");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il sotto titolo pop up: " + e.getMessage());
            Assertions.fail("NON si visualizza correttamente il sotto titolo pop up: " + e.getMessage());
        }
    }

    public void chiudiPopUpVisualizza() {
        closeButtonPopUpVisualizza = driver.findElement(By.id("close-modal-button"));
        getWebDriverWait(30).withMessage("il Bottone chiudere pop up non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(closeButtonPopUpVisualizza));
        closeButtonPopUpVisualizza.click();
    }

    public void clickVisualizzaIdApiKey() {
       // visualizzaIdGruppo = driver.findElement(By.id("button-view-groups-id"));
        getWebDriverWait(20).withMessage("Il bottone visualizza Id api key non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("button-view-groups-id")));
        element(By.id("button-view-groups-id")).click();
        getWebDriverWait(10).withMessage("L'email di cortesia non è presente").until(ExpectedConditions.visibilityOfElementLocated(By.id("default_email-typography")));
    }

    public void popUpGruppiAssociati() {
        try {
            WebElement popUpGruppiAssociatiBy = driver.findElement(By.xpath("//h2[contains(text(),'Gruppi associati alla API')]"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(popUpGruppiAssociatiBy));
            logger.info("Si visualizza correttamente il titolo popup Gruppi associati alla API");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente il titolo popup Gruppi associati alla API con errore:" + e.getMessage());
            Assertions.fail("Non si visualizza correttamente il titolo popup Gruppi associati alla API con errore:" + e.getMessage());
        }
    }

    public String copiaApiKey() {
        try {
            List<WebElement> apiKeys = driver.findElements(By.xpath("//button[@data-testid='copyToClipboardGroupsId']"));
            getWebDriverWait(10).withMessage("il bottone copia api key non è cliccabile").until(ExpectedConditions.elementToBeClickable(apiKeys.get(0)));
            apiKeys.get(0).click();
             /* We can't check if there is the green "CheckIcon" element because webdriver doesn't refresh the elements reloaded
             i.e: When you click the button 'copy-to-clipboard' and you try to get the attribute 'aria-label' you'll notice that the attribute
             won't change at all and will keep staying 'Copia' even though it should be 'Group ID copiati' for a couple of seconds. */

            // This will just copy from the modal the value of the input field
            clickMenuButton();
            clickSuVisualizza();
            WebElement inputFieldApiKey = driver.findElement(By.xpath("//div[@data-testid='dialog-content']//input"));
            return inputFieldApiKey.getAttribute("value");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il campo ApiKey con errore:" + e.getMessage());
            Assertions.fail("NON si visualizza correttamente il campo ApiKey con errore:" + e.getMessage());
            return null;
        } finally {
            this.chiudiPopUpVisualizza();
        }
    }

    public void mouseHoverGroups() {
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@data-testid='tableApiKeys.body.row']"));
        if (!rows.isEmpty()) {
            List<WebElement> cells = rows.get(0).findElements(By.xpath("//td"));
            getWebDriverWait(10).withMessage("colonna gruppi non trovato").until(ExpectedConditions.visibilityOf(cells.get(0)));
            Actions action = new Actions(this.driver);
            action.moveToElement(cells.get(3)).perform();
            logger.info("mouse hover sopra gruppi effetuato correttamente");
        } else {
            logger.error("Non è stato possibile trovare la colonna gruppi della prima riga");
            Assertions.fail("Non è stato possibile trovare la colonna gruppi della prima riga");
        }
    }

    public String copiaApiKeyESalva() {
        WebElement apiKeyField = driver.findElement(By.id("apiKeyId"));
        return apiKeyField.getAttribute("value");
    }

    public String visualizzaApiKeyInElenco() {
        webTool.waitTime(5);
        List<WebElement> contextMenu = driver.findElements(By.xpath("//button[@data-testid='contextMenuButton']"));
        contextMenu.get(0).click();
        webTool.waitTime(1);
        WebElement visualizzaCodiceButton = driver.findElement(By.xpath("//li[@data-testid='buttonView']"));
        visualizzaCodiceButton.click();
        webTool.waitTime(1);
        WebElement apiKeyDaElenco = driver.findElement(By.xpath("//input[@aria-invalid='false']"));
        return apiKeyDaElenco.getAttribute("value");
    }
}
