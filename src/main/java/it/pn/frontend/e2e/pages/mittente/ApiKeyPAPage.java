package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.RegistraChiavePubblicaPGSection;
import it.pn.frontend.e2e.utility.WebTool;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
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
   private RegistraChiavePubblicaPGSection registraChiavePubblicaPGSection;

    public ApiKeyPAPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
        registraChiavePubblicaPGSection = new RegistraChiavePubblicaPGSection(driver);
    }

    public void waitLoadApikeyPage() {
        try {
            getWebDriverWait(30).withMessage("Il titolo della pagina ApiKey non è visibile")
                    .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("API Key-page"))));
            getWebDriverWait(40).withMessage("Il bottone genera ApiKey non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("generate-api-key"))));
            logger.info("Api Key Page caricata");
        } catch (TimeoutException e) {
            Assertions.fail("Api Key Page NON caricata con errore: " + e.getMessage());
        }
    }

    public void clickSulBottoneGeneraApiKey() {
        webTool.waitTime(5);
        generateApiKeyButton = driver.findElement(By.id("generate-api-key"));
        js().executeScript("arguments[0].click()", generateApiKeyButton);
    }

    public void inserireUnNomePerApiKey(String nomeApiKey) {

        getWebDriverWait(30).withMessage("Il campo Nome Apikey non è visibile")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("name"))));
        apiKeyNameInput = driver.findElement(By.id("name"));
        apiKeyNameInput.sendKeys(nomeApiKey);
    }

    public void clickSulBottoneContinua() {
        getWebDriverWait(40).withMessage("Il bottone Continua non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("continue-button"))));
        apiContinuaButton = driver.findElement(By.id("continue-button"));
        apiContinuaButton.click();
    }

    public void siVisualizzaCorrettamenteConfermaPage() {
        try {
            getWebDriverWait(30).withMessage("Il titolo della pagina conferma Apikey non è visibile")
                    .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("go-to-api-keys"))));
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
            getWebDriverWait(30).withMessage("lo stato dell'ApiKey non è Attiva")
                    .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='statusChip-Attiva']"))));
            getWebDriverWait(30).withMessage("Il nome del ApiKey attiva non è: " + nomeApiKey)
                    .until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//p[contains(text(),'" + nomeApiKey + "')]"))));
            logger.info("Api Key Confirmation Page caricata");
        } catch (TimeoutException e) {
            logger.error("Api Key Confirmation Page NON caricata con errore: {}", e.getMessage());
            Assertions.fail("Api Key Confirmation Page NON caricata con errore: " + e.getMessage());
        }
    }

    public String getNomi(int i) {
        getWebDriverWait(30).withMessage("la lista dei nomi ApiKey non è visibile")
                .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//tbody/tr/td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1kqk1ww')]/div/p"))));
        List<WebElement> nomiApiKeyBy = driver.findElements(By.xpath("//tbody/tr/td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1kqk1ww')]/div/p"));
        return nomiApiKeyBy.get(i).getText();
    }

    public int getPosizioneMenuButton() {
        getWebDriverWait(30).withMessage("lista stati ApiKey non trovata")
                .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//div[contains(@id,'status-chip-')]"))));
        List<WebElement> statiApiKeyBy = driver.findElements(By.xpath("//div[contains(@id,'status-chip-')]"));
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
        getWebDriverWait(30).withMessage("menu Apikey da Bloccare non trovato")
                .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//button[@data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']"))));
        List<WebElement> menuAttivaButtonBy = driver.findElements(By.xpath("//button[@data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']"));
        int posizioneMenuButton = getPosizioneMenuButton();
        if (posizioneMenuButton >= 0) {
            menuAttivaButtonBy.get(posizioneMenuButton).click();
        } else {
            logger.error("Nessuna Api Key diversa da 'fe-TA-apikey-test' da bloccare");
            Assertions.fail("Nessuna Api Key diversa da 'fe-TA-apikey-test' da bloccare");
        }
    }

    public void clickSuBlocca() {
        blockButton = getWebDriverWait(40).withMessage("Il bottone Blocca apiKey non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("button-block"))));
        blockButton.click();
    }

    public void siVisualizzaPopUp() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[contains(text(),'Blocca API Key')]"))));
            logger.info("Il popup si visualizza correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il popup NON si visualizza correttamente con errore:" + e.getMessage());
        }
    }

    public void clickSuAnnulla() {
        annullaButtonNelPopUp = getWebDriverWait(40).withMessage("il Bottone Annulla nel pop up non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("close-modal-button"))));
        annullaButtonNelPopUp.click();
    }

    public void clickSuConfermaNelPopUp() {
        confermaButtonNelPopUp = getWebDriverWait(40).withMessage("il Bottone Conferma nel pop up non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("action-modal-button"))));
        confermaButtonNelPopUp.click();
    }

    public void notificaSelezionataStatoBloccata() {
        try {
            getWebDriverWait(40).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("status-chip-Bloccata"))));
            logger.info("La notifica è in stato bloccata");
        } catch (TimeoutException e) {
            Assertions.fail("La notifica è in stato bloccata con errore:" + e.getMessage());
        }
    }

    public void clickAttivaSulMenu() {
        getWebDriverWait(30).withMessage("il bottone attiva seul menu non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//li[contains(@data-testid,'buttonEnable')]"))));
        attivaButtonNelMenu = driver.findElement(By.xpath("//li[contains(@data-testid,'buttonEnable')]"));
        attivaButtonNelMenu.click();
    }

    public void siVisualizzaPoPUpAttiva() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[contains(text(),'Attiva API Key')]"))));
            logger.info("Si visualizza correttamente il popup attiva");
        } catch (TimeoutException e) {
            Assertions.fail("NON si visualizza correttamente il popup attiva con errore:" + e.getMessage());
        }
    }

    public void siVisualizzaNotificaSelezionataBloccata() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Attiva')]"))));
            logger.info("La notifica è in stato bloccata");
        } catch (TimeoutException e) {
            Assertions.fail("La notifica è in stato bloccata con errore:" + e.getMessage());
        }
    }

    public void clickRuotaSulMenu() {
        ruotaButtonNelMenu = getWebDriverWait(30).withMessage("Il Bottone ruota del menu apikey non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("button-rotate"))));
        ruotaButtonNelMenu.click();
    }

    public void siVisualizzaPoPUpRuota() {
        try {
            getWebDriverWait(30).withMessage("Il titolo Ruota Apikey  sul pop up non trovato").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[contains(text(),'Ruota API Key')]"))));
            logger.info("Si visualizza correttamente il popup ruota");
        } catch (TimeoutException e) {
            Assertions.fail("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
        }
    }

    public void siVisualizzaNotificaSelezionataRuotata() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='statusChip-Ruotata']"))));
            logger.info("Si visualizza correttamente il popup ruota");
        } catch (TimeoutException e) {
            Assertions.fail("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
        }
    }

    public int verificaBottoni() {
        getWebDriverWait(35).withMessage("la lista bottoni Bloccati non trovata").until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td/div/div/div/div[@role='button' and @data-testid='statusChip-Bloccata']")));
        List<WebElement> menuBloccaButtonBy = driver.findElements(By.xpath("//td/div/div/div/div[@role='button' and @data-testid='statusChip-Bloccata']"));
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


        getWebDriverWait(20).withMessage("la lista stati bottone non trovata").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//div[@class='MuiBox-root css-13brihr']/div[@class='MuiBox-root css-0']"))));
        List<WebElement> statiBottoneBy = driver.findElements(By.xpath("//div[@class='MuiBox-root css-13brihr']/div[@class='MuiBox-root css-0']"));
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
        getWebDriverWait(20).withMessage("la lista attiva bottone non trovata").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//td[div/div/div/div[@role='button' and @data-testid='statusChip-Bloccata']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']"))));
        List<WebElement> menuAttivaButtonBy = driver.findElements(By.xpath("//td[div/div/div/div[@role='button' and @data-testid='statusChip-Bloccata']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']"));
        menuAttivaButtonBy.get(posizione).click();
    }

    public boolean siVisualizzaApiKeyConTesto() {
        try {
            webTool.waitTime(5);
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
            webTool.waitTime(5);
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
            webTool.waitTime(5);
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
            webTool.waitTime(5);
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
        getWebDriverWait(30).withMessage("il bottone menu del apikey non trovato").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//td[div/div/div/div[@role='button']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']"))));
        List<WebElement> menuButtonBy = driver.findElements(By.xpath("//td[div/div/div/div[@role='button']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']"));
        return !menuButtonBy.isEmpty();
    }

    public void mouseHover() {
        getWebDriverWait(30).withMessage("stato attiva non trovato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("status-chip-Attiva"))));
        WebElement statoCellBy = driver.findElement(By.id("status-chip-Attiva"));
        Actions action = new Actions(driver);
        action.moveToElement(statoCellBy).perform();
        logger.info("mouse hover sullo stato attiva effetuato correttamente");
    }

    public void waitLoadMessaggioData() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-popper-placement = 'bottom']"))));
            logger.info("Il messaggio con la data di creazione si vede correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il messaggio con la data di creazione NON si vede correttamente con errore: " + e.getMessage());
        }
    }

    public void inserireGruppoApi(String gruppo) {
        getWebDriverWait(30).withMessage("Il campo gruppo Apikey non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("groups"))));
        gruppoInput = driver.findElement(By.id("groups"));
        gruppoInput.sendKeys(gruppo);
        getWebDriverWait(60).withMessage("Il campo Nome del Gruppo Apikey non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("groups-option-0"))));
        WebElement groupOption = driver.findElement(By.id("groups-option-0"));
        groupOption.click();
        gruppoInput.click();

    }

    public void cancellareTestoInserito() {
        try {
            getWebDriverWait(40).withMessage("il campo name non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("name"))));
            WebElement testoInseritoBy = driver.findElement(By.id("name"));
            js().executeScript("arguments[0].click()", testoInseritoBy);

            String name = testoInseritoBy.getAttribute("value");
            for (int index = 0; index < name.length(); index++) {
                testoInseritoBy.sendKeys(Keys.BACK_SPACE);
            }
            logger.info("Il testo è stato cancellato correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il testo è NON stato cancellato correttamente" + e.getMessage());
        }
    }

    public void siVisualizzaMessaggioErroreApiName() {
        try {
            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(@id,'name-helper-text')]"))));
            logger.info("Si visualizza correttamente il messaggio di errore");
        } catch (TimeoutException e) {
            Assertions.fail("NON si visualizza correttamente il messaggio di errore" + e.getMessage());
        }
    }

    public void clickSuVisualizza() {
        visualizzaApiButton = getWebDriverWait(10).withMessage("Il Bottone visualizza non è cliccabile").until(ExpectedConditions.elementToBeClickable( driver.findElement(By.id("button-view"))));
        visualizzaApiButton.click();
    }

    public void siVisualizzaPopUpVisualizza() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("subtitle-top"))));
            logger.info("Si visualizza correttamente il sotto titolo del pop up");
        } catch (TimeoutException e) {
            Assertions.fail("NON si visualizza correttamente il sotto titolo pop up: " + e.getMessage());
        }
    }

    public void chiudiPopUpVisualizza() {

        closeButtonPopUpVisualizza = getWebDriverWait(30).withMessage("il Bottone chiudere pop up non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("close-modal-button"))));
        closeButtonPopUpVisualizza.click();
    }

    public void clickVisualizzaIdApiKey() {
        getWebDriverWait(20).withMessage("Il bottone visualizza Id api key non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("button-view-groups-id"))));
        driver.findElement(By.id("button-view-groups-id")).click();
    }

    public void popUpGruppiAssociati() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[contains(text(),'Gruppi associati alla API')]"))));
            logger.info("Si visualizza correttamente il titolo popup Gruppi associati alla API");
        } catch (TimeoutException e) {
            Assertions.fail("Non si visualizza correttamente il titolo popup Gruppi associati alla API con errore:" + e.getMessage());
        }
    }

    public String copiaApiKey() {
        try {
            getWebDriverWait(15).withMessage("il bottone copia api key non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//button[@data-testid='copyToClipboardGroupsId']")).get(0)));
            List<WebElement> apiKeys = driver.findElements(By.xpath("//button[@data-testid='copyToClipboardGroupsId']"));
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
            Assertions.fail("NON si visualizza correttamente il campo ApiKey con errore:" + e.getMessage());
            return null;
        } finally {
            this.chiudiPopUpVisualizza();
        }
    }

    public void mouseHoverGroups() {
        webTool.waitTime(3);
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
        webTool.waitTime(5);
        WebElement apiKeyField = driver.findElement(By.id("apiKeyId"));
        return apiKeyField.getAttribute("value");
    }

    public String visualizzaApiKeyInElenco() {
        webTool.waitTime(10);
        List<WebElement> contextMenu = driver.findElements(By.xpath("//button[@data-testid='contextMenuButton']"));
        contextMenu.get(0).click();
        webTool.waitTime(7);
        WebElement visualizzaCodiceButton = driver.findElement(By.xpath("//li[@data-testid='buttonView']"));
        visualizzaCodiceButton.click();
        webTool.waitTime(7);
        WebElement apiKeyDaElenco = driver.findElement(By.xpath("//input[@aria-invalid='false']"));
        return apiKeyDaElenco.getAttribute("value");
    }

    public void pulisciAmbientePublickeys() {
        logger.info("Prima della tabella");
        try {
            WebElement table = getWebDriverWait(40).withMessage("Il Tabella Integrazione Api Publickeys  NON VISIBILE").until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@data-testid='publicKeysTableDesktop']")));
            List<WebElement> rows = table.findElements(By.xpath(".//tr"));
            logger.info("Tabella RIGHE: {}", rows.size());
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.xpath(".//td"));

                // Assicurati che ci siano abbastanza celle
                if (!cells.isEmpty()) {
                    String statoValue = cells.get(3).getText();

                    if (statoValue.equalsIgnoreCase("Attiva")) {
                        logger.info("Stato Attiva.");
                        clickTrePuntiniPublicKeys();

                        if (verificaEsisteSoloRuotaVisualizzaCodice()) {
                            logger.info("Presente Stato attiva solo  con  Ruota e Visualizza Codice");
                            aggiornamentoPagina();
                            webTool.waitTime(5);
                            registraChiavePubblicaPGSection.cliccareSuiTrePuntiniConStato("Bloccata");
                            clickEliminaIntegrazioneApi();
                            clickSuConfermaNelPopUp();
                            clickTrePuntiniPublicKeys();
                        } else if (verificaEsisteSoloBloccaVisualizzaCodice()) {
                            logger.info("Presente Stato attiva solo  con  Blocca e Visualizza Codice");
                            aggiornamentoPagina();
                            webTool.waitTime(5);
                            registraChiavePubblicaPGSection.cliccareSuiTrePuntiniConStato("Ruotata");
                            clickEliminaIntegrazioneApi();
                            clickSuConfermaNelPopUp();
                            clickTrePuntiniPublicKeys();
                        } else if (verificaEsisteSoloVisualizzaCodice()) {
                            logger.info("Presente Stato attiva solo Visualizza Codice");
                            aggiornamentoPagina();
                            webTool.waitTime(5);
                            registraChiavePubblicaPGSection.cliccareSuiTrePuntiniConStato("Bloccata");
                            clickEliminaIntegrazioneApi();
                            clickSuConfermaNelPopUp();
                            registraChiavePubblicaPGSection.cliccareSuiTrePuntiniConStato("Ruotata");
                            clickEliminaIntegrazioneApi();
                            clickSuConfermaNelPopUp();
                            clickTrePuntiniPublicKeys();

                        }

                        clickSuBlocca();
                        clickSuConfermaNelPopUp();
                        logger.info("Tasto Blocca cliccato su stato Attiva.");
                        aggiornamentoPagina();
                        webTool.waitTime(5);
                        logger.info("Stato Bloccata");
                        clickTrePuntiniPublicKeys();
                        clickEliminaIntegrazioneApi();
                        clickSuConfermaNelPopUp();
                        logger.info("Tasto Elimina cliccato su stato Bloccata.");
                        aggiornamentoPagina();
                        webTool.waitTime(3);
                        logger.info("Stato Bloccata");
                        clickTrePuntiniPublicKeys();
                        clickEliminaIntegrazioneApi();
                        clickSuConfermaNelPopUp();
                        logger.info("Tasto Elimina");
                        aggiornamentoPagina();
                        webTool.waitTime(3);
                        break;

                    } else if (statoValue.equalsIgnoreCase("Ruotata") || statoValue.equalsIgnoreCase("Bloccata")) {
                        clickTrePuntiniPublicKeys();
                        clickEliminaIntegrazioneApi();
                        clickSuConfermaNelPopUp();
                        logger.info("Tasto Elimina cliccato su stato Ruotata o Bloccata.");
                        driver.navigate().refresh();
                        webTool.waitTime(3);

                    }
                }
            }
        } catch (TimeoutException e) {
            // Se la tabella NON è visibile, proseguo comunque l'esecuzione
            logger.info("Tabella NON visibile. Proseguo comunque.");
        }
    }

    public void clickTrePuntiniPublicKeys() {
        WebElement clickTrePuntiniPublicKeys = getWebDriverWait(40).withMessage("Il Tre Puntini NON VISIBILE")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@data-testid='publicKeysTableDesktop']//tr[1]//button[@data-testid='contextMenuButton']")));
        clickTrePuntiniPublicKeys.click();
    }

    public void clickEliminaIntegrazioneApi() {
        logger.info("clickEliminaIntegrazioneApi");
        WebElement button = getWebDriverWait(40).withMessage("Il tasto Elimina NON VISIBILE")
                .until(ExpectedConditions.elementToBeClickable(By.id("button-delete")));
        button.click();
    }

    public void clickSuTastoGeneraChiavePersonale() {
        WebElement button = getWebDriverWait(40).withMessage("Il tasto Genera Chiave Personale NON VISIBILE")
                .until(ExpectedConditions.elementToBeClickable(By.id("generate-virtual-key")));
        button.click();
    }

    public void verificaPopUpIntegrazioneApi(String test) {
        logger.info("POP up Verifico");
        Assertions.assertTrue(
                driver.findElement(By.xpath("//div[@class='MuiAlert-message css-cysxvc']")).getText().toLowerCase().contains(test.toLowerCase()),
                "Il testo dell'alert non contiene la stringa attesa , ma visualizza:" + driver.findElement(By.xpath("//div[@class='MuiAlert-message css-cysxvc']")).getText()
        );
        webTool.waitTime(5);
    }

    public void nellaSezioneIntegrazioneAPINonSiVisualizzaAlcunaChiave(String testo) {
        getWebDriverWait(40).withMessage("Il testo '"+testo+"' NON VISIBILE").until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@data-testid='emptyState']//p[contains(text(), '"+testo+"')]")
                )
        );
    }

    public void verificaTrePuntiniMostraDiPiu(Map<String, String> chiave) {
        if(StringUtils.isNotBlank(chiave.get("ruota"))){
            getWebDriverWait(40).withMessage("Il tasto: '"+chiave.get("ruota")+"' NON VISIBILE").until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("button-rotate"))
            );
        }

        if(StringUtils.isNotBlank(chiave.get("blocca"))){
            getWebDriverWait(40).withMessage("Il tasto: '"+chiave.get("blocca")+"' NON VISIBILE").until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("button-block"))
            );
        }
        if(StringUtils.isNotBlank(chiave.get("view"))){
            getWebDriverWait(40).withMessage("Il tasto: '"+chiave.get("view")+"' NON VISIBILE").until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("button-view"))
            );
        }
        if(StringUtils.isNotBlank(chiave.get("delete"))){
            getWebDriverWait(40).withMessage("Il tasto: '"+chiave.get("view")+"' NON VISIBILE").until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("button-delete"))
            );
        }
    }

    private boolean verificaEsisteSoloRuotaVisualizzaCodice() {
        List<WebElement> menuItems = driver.findElements(By.xpath("//ul[@role='menu']/li"));
        if (menuItems.size() != 2) {
            return false;
        }
        boolean hasRotate = menuItems.stream()
                .anyMatch(item -> "button-rotate".equals(item.getAttribute("id")));
        boolean hasViewCode = menuItems.stream()
                .anyMatch(item -> "button-view".equals(item.getAttribute("id")));

        return hasRotate && hasViewCode;
    }

    private boolean verificaEsisteSoloBloccaVisualizzaCodice() {
        List<WebElement> menuItems = driver.findElements(By.xpath("//ul[@role='menu']/li"));
        if (menuItems.size() != 2) {
            return false;
        }
        boolean hasRotate = menuItems.stream()
                .anyMatch(item -> "button-block".equals(item.getAttribute("id")));
        boolean hasViewCode = menuItems.stream()
                .anyMatch(item -> "button-view".equals(item.getAttribute("id")));

        return hasRotate && hasViewCode;
    }

    private boolean verificaEsisteSoloVisualizzaCodice() {
        List<WebElement> menuItems = driver.findElements(By.xpath("//ul[@role='menu']/li"));
        if (menuItems.size() != 1) {
            return false;
        }
        return menuItems.stream()
                .anyMatch(item -> "button-view".equals(item.getAttribute("id")));
    }

    public void verificaTestoNelPopUp(String testo) {

        getWebDriverWait(40).withMessage("Il tasto: '"+testo+"' NON VISIBILE nel pop-up")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + testo + "')]")));
    }

    public void pulisciAmbienteVirtualKeys() {
        try {
            WebElement table = getWebDriverWait(40).withMessage("Il Tabella Integrazione Api VirtualKeys  NON VISIBILE").until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@data-testid='virtualKeysTableDesktop']")));
            List<WebElement> rows = table.findElements(By.xpath(".//tr"));
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.xpath(".//td"));

                // Assicurati che ci siano abbastanza celle
                if (!cells.isEmpty()) {
                    String statoValue = cells.get(3).getText();

                    if (statoValue.equalsIgnoreCase("Attiva")) {
                        clickTrePuntiniVirtualKeys();
                        clickSuBlocca();
                        clickSuConfermaNelPopUp();
                        logger.info("Tasto Blocca cliccato su stato Attiva.");
                        aggiornamentoPagina();
                        webTool.waitTime(5);

                        clickTrePuntiniVirtualKeys();
                        clickEliminaIntegrazioneApi();
                        clickSuConfermaNelPopUp();
                        logger.info("Tasto Elimina cliccato su stato Bloccata.");
                        driver.navigate().refresh();
                        webTool.waitTime(5);
                        try {
                            clickTrePuntiniVirtualKeys();
                            clickEliminaIntegrazioneApi();
                            clickSuConfermaNelPopUp();
                            logger.info("Tasto Elimina ");
                            driver.navigate().refresh();
                            webTool.waitTime(5);
                        }catch (Exception e){
                            logger.info("Continuo ");
                        }
                        break;
                    } else if (statoValue.equalsIgnoreCase("Ruotata") || statoValue.equalsIgnoreCase("Bloccata")) {
                        clickTrePuntiniVirtualKeys();
                        clickEliminaIntegrazioneApi();
                        clickSuConfermaNelPopUp();
                        logger.info("Tasto Elimina cliccato su stato Ruotata o Bloccata.");
                        driver.navigate().refresh();
                        webTool.waitTime(3);

                    }
                }
            }
        } catch (TimeoutException e) {
            // Se la tabella NON è visibile, proseguo comunque l'esecuzione
            logger.info("Tabella NON visibile. Proseguo comunque.");
        }
    }
    public void clickTrePuntiniVirtualKeys() {
        WebElement clickTrePuntiniVirtualKeys = getWebDriverWait(40).withMessage("Il Tre Puntini Virtual  NON VISIBILE")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@data-testid='virtualKeysTableDesktop']//tr[1]//button[@data-testid='contextMenuButton']")));
        clickTrePuntiniVirtualKeys.click();
    }

}
