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
import java.util.UUID;
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

    static final String xPathPublicKeysTableDesktop = "//table[@data-testid='publicKeysTableDesktop']";
    static final String xPathVirtualKeysTableDesktop = "//table[@data-testid='virtualKeysTableDesktop']";


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

    public int getPosizioneMenuButton(String stato) {

        List<WebElement> statiApiKeyBy = getWebDriverWait(30).
                withMessage("Lista stati ApiKey non trovata")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@id,'status-chip-')]")));

        for (int i = 0; i < statiApiKeyBy.size(); i++) {
            if (("status-chip-"+stato).equalsIgnoreCase(statiApiKeyBy.get(i).getAttribute("id"))) {
                return i;
            }
        }
        return -1;

    }

    public void clickMenuButton() {
        List<WebElement> webElements = verificaColonnaAzioni();
        int posizioneMenuButton = getPosizioneMenuButton("Attiva");
        if (posizioneMenuButton >= 0) {
            webElements.get(posizioneMenuButton).click();
        } else {
            Assertions.fail("Nessuna Api Key diversa da 'fe-TA-apikey-test' da bloccare");
        }
    }

    public void clickSuBlocca() {
        WebElement blockButton = getWebDriverWait(30)
                .withMessage("Il bottone Blocca API Key non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("button-block")));
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
        getWebDriverWait(40).withMessage("il Bottone Annulla nel pop up non è cliccabile").until(ExpectedConditions.elementToBeClickable( driver.findElement(By.id("close-modal-button"))));
        annullaButtonNelPopUp = driver.findElement(By.id("close-modal-button"));
        annullaButtonNelPopUp.click();
    }

    public void clickSuConfermaNelPopUp() {
        getWebDriverWait(40).withMessage("il Bottone Conferma nel pop up non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("action-modal-button"))));
        confermaButtonNelPopUp = driver.findElement(By.id("action-modal-button"));
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
//        getWebDriverWait(30).withMessage("il bottone attiva seul menu non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//li[contains(@data-testid,'buttonEnable')]"))));
//        attivaButtonNelMenu = driver.findElement(By.xpath("//li[contains(@data-testid,'buttonEnable')]"));
//        attivaButtonNelMenu.click();

        WebElement attivaButtonNelMenu = getWebDriverWait(30).
                withMessage("Il bottone Attiva nel menu non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@data-testid,'buttonEnable')]")));
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
        getWebDriverWait(30).withMessage("Il Bottone ruota del menu apikey non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("button-rotate"))));
        ruotaButtonNelMenu = driver.findElement(By.id("button-rotate"));
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

    public int verificaBottoni(String stato) {
        List<WebElement> rows = getRows();
        int i = 0;
        for (WebElement row : rows) {
            // Trova la colonna dello stato
            WebElement statusCell = row.findElement(By.cssSelector("table.MuiTable-root td:nth-child(5) .MuiChip-label"));
            String status = statusCell.getText();
            // Verifica se lo stato è "Bloccata"
            if ("Bloccata".equals(status)) {
                // Trova il bottone dei "tre puntini" e clicca
                WebElement actionsButton = row.findElement(By.cssSelector("button[data-testid='contextMenuButton']"));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", actionsButton);
                List<WebElement> menuItems = getWebDriverWait(30)
                        .withMessage("Impossibile trovare il menu a tendina ")
                        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("ul[class*='MuiMenu-list'] li")
                ));
                // Itera attraverso gli elementi della lista per trovare la voce "Attiva"
                for (WebElement item : menuItems) {
                    if ("Attiva".equals(item.getText())) {
                        logger.info("Voce 'Attiva' trovata nel menu");
                        return i;
                    }
                }
                // Chiudi il menu se "Attiva" non è stata trovata
                Actions actions = new Actions(driver);
                actions.sendKeys(Keys.ESCAPE).build().perform();
                webTool.waitTime(1);
            }
            i++;
        }
        return -1;
    }

    public boolean verificaBottoneCheNonSiaRuotata(WebElement currentButton) {

        currentButton.click();
        WebElement attivaItem = getWebDriverWait(30)
                .withMessage("Impossibile trovare la lista degli stati ")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("button-enable") ));

        // Verifica che il testo dell'elemento sia "Attiva"
        if ("Attiva".equals(attivaItem.getText())) {
            return true;
        }
        return false;

    }

    public void clickMenuButtonBlocca() {
        int posizione = verificaBottoni("Bloccata");
        logger.info("Posizione: "+ posizione);

        if (posizione < 0) {
            logger.info("Nessuna Api Key bloccata da attivare, procedo a bloccare una attivata");
            clickMenuButton();
            clickSuBlocca();
            siVisualizzaPopUp();
            clickSuConfermaNelPopUp();
            verificaBottoni("Bloccata");
        }
    }

    public void clickSulBottoneBloccatoMaiRuotato(int posizione) {

        List<WebElement> statiApiKeyBy = getWebDriverWait(30).
                withMessage("Lista stati ApiKey non trovata")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@id,'status-chip-Bloccata')]")));
        statiApiKeyBy.get(posizione).click();

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
        getWebDriverWait(30).withMessage("il bottone menu del apikey non trovato").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//table[@data-testid='virtualKeysTableDesktop']//tr[1]//button[@data-testid='contextMenuButton']"))));
        List<WebElement> menuButtonBy = driver.findElements(By.xpath("//table[@data-testid='virtualKeysTableDesktop']//tr[1]//button[@data-testid='contextMenuButton']"));
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
        getWebDriverWait(10).withMessage("Il Bottone visualizza non è cliccabile").until(ExpectedConditions.elementToBeClickable( driver.findElement(By.id("button-view"))));
        visualizzaApiButton = driver.findElement(By.id("button-view"));
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

        getWebDriverWait(30).withMessage("il Bottone chiudere pop up non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("close-modal-button"))));
        closeButtonPopUpVisualizza = driver.findElement(By.id("close-modal-button"));
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

    public void copiaApiKey(int rowIndex) {

        By copyButtonLocator = By.xpath("(//button[@data-testid='copyToClipboard'])[" + rowIndex + "]");

        logger.info("Clicco su 'Copia' nella riga {}", rowIndex);
        WebElement copyButton = getWebDriverWait(15)
                .withMessage("Impossibile trovare il tasto copi dell API key")
                .until(ExpectedConditions.elementToBeClickable(copyButtonLocator));
        copyButton.click();


    }

    public boolean generaChiavePubblicaDisplayed() {
        try {
//            return getWebDriverWait(5).withMessage("Il bottone Genera chiave pubblica non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("generate-public-key")))).isDisplayed();
            return getWebDriverWait(15)
                    .withMessage("Il bottone Genera chiave pubblica non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("generate-public-key")))
                    .isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.warn("Il bottone Genera chiave pubblica non è visibile");
            return false;
        }
    }

    public void clickBottoneGeneraChiavePubblica() {
        WebElement generaChiavePubblicaButton = getWebDriverWait(10).withMessage("Bottone Genera chiave pubblica non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("generate-public-key"))));
        generaChiavePubblicaButton.click();
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
    try {
        WebElement table = getWebDriverWait(20).withMessage("Prima Accesso alla Tabella Integrazione Api Publickeys  NON VISIBILE")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathPublicKeysTableDesktop)));
        int b = 0;
        List<WebElement> righeBloccate = table.findElements(By.xpath(".//tr//td//div[@id='status-chip-Bloccata']"));
        //Si cancellano prima le righe bloccate per far proseguire i test automatici
        logger.info("Si cancellano prima le {} righe bloccate", righeBloccate.size());
        while (b < righeBloccate.size()) {

            WebElement row = righeBloccate.get(b);
            //si cliccano i tre puntini sulla riga con stato Bloccata
            row.findElement(By.xpath("./parent::td//..//button[@data-testid='contextMenuButton']")).click();
            clickEliminaIntegrazioneApi();
            clickSuConfermaNelPopUp();
            logger.info("Tasto Elimina cliccato su stato Bloccata.");
            aggiornaPaginaWaitTime(3);

            //Si aggiorna il conto delle righe dopo la cancellazione della riga
            if (getWebDriverWait(10).withMessage("Accessi succesivi alla Tabella Integrazione Api Publickeys  NON VISIBILE")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathPublicKeysTableDesktop))).isDisplayed()) {
                table = getWebDriverWait(10).withMessage("Il Tabella Integrazione Api VirtualKeys  NON VISIBILE")
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathPublicKeysTableDesktop)));
                righeBloccate = table.findElements(By.xpath(".//tr//td//div[@id='status-chip-Bloccata']"));
                logger.info("righe bloccate mancanti {}", righeBloccate.size());
                b = 0;
            }
            b++;
        }
        //Si refresha l'element table e si cancellano ora le righe attive e ruotate.
        table = getWebDriverWait(10).withMessage("Il Tabella Integrazione Api VirtualKeys  NON VISIBILE")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathPublicKeysTableDesktop)));
        List<WebElement> rows = table.findElements(By.xpath(".//tr"));
        int i = 0;
        logger.info("si cancellano chiavi attive e ruotate");
        while (i < rows.size()) {

            WebElement row = rows.get(i);
            /**si individua la colonna dove è presente lo status chip (si è usato un xpath invece del numero della colonna nella tabella,
             * che può cambiare in base al tipo di persona che entra nella pagina
             */
            List<WebElement> cells = row.findElements(By.xpath(".//td//div[contains(@data-testid,'statusChip')]"));

            if (!cells.isEmpty()) {
                String statoValue = cells.get(0).getText();

                if (statoValue.equalsIgnoreCase("Attiva")) {

                    clickTrePuntiniPublicKeys();
                    clickSuBlocca();
                    clickSuConfermaNelPopUp();
                    aggiornaPaginaWaitTime(5);

                } else if (statoValue.equalsIgnoreCase("Ruotata") || statoValue.equalsIgnoreCase("Bloccata")) {

                    clickTrePuntiniPublicKeys();
                    clickEliminaIntegrazioneApi();
                    clickSuConfermaNelPopUp();
                    logger.info("Tasto Elimina cliccato su stato Ruotata o Bloccata.");
                    aggiornaPaginaWaitTime(3);

                }

                //Si aggiorna il conto delle righe dopo la cancellazione della riga
                if (getWebDriverWait(10).withMessage("Accessi succesivi alla Tabella Integrazione Api Publickeys  NON VISIBILE")
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathPublicKeysTableDesktop))).isDisplayed()) {
                    table = getWebDriverWait(10).withMessage("Il Tabella Integrazione Api VirtualKeys  NON VISIBILE")
                            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathPublicKeysTableDesktop)));
                    rows = table.findElements(By.xpath(".//tr"));
                    i = 0;
                }
            }

            i++;
        }
    } catch (TimeoutException e) {
        // Se la tabella NON è visibile, proseguo comunque l'esecuzione
        logger.info("Tabella NON visibile. Proseguo comunque.");
    }
}



    //N.B.: Metodo funziona solo se è presente una riga individuabile sulla tabella. In caso di righe multiple si deve dichiarare manualmente quale xpath specifico deve essere cliccato
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
        logger.info("cliccato su Elimina");
    }

    public void clickSuTastoGeneraChiavePersonale() {
        WebElement button = getWebDriverWait(40).withMessage("Il tasto Genera Chiave Personale NON VISIBILE")
                .until(ExpectedConditions.elementToBeClickable(By.id("generate-virtual-key")));
        button.click();
    }

    public void nellaSezioneIntegrazioneAPINonSiVisualizzaAlcunaChiave(String testo) {
        getWebDriverWait(40).withMessage("Il testo '"+testo+"' NON VISIBILE").until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@data-testid='emptyState']//p[contains(text(), '"+testo+"')]")
                )
        );
    }

    public void nellaSezioneIntegrazioneAPISiVisualizzaAlert(String testo) {
        getWebDriverWait(40).withMessage("Il testo '"+testo+"' NON VISIBILE").until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@role='alert']//p[contains(text(), '"+testo+"')]")
                )
        );
    }

//    public void verificaTrePuntiniMostraDiPiu(Map<String, String> chiave) {
//        if(StringUtils.isNotBlank(chiave.get("ruota"))){
//            getWebDriverWait(40).withMessage("Il tasto: '"+chiave.get("ruota")+"' NON VISIBILE").until(
//                    ExpectedConditions.visibilityOfElementLocated(By.id("button-rotate"))
//            );
//        }
//
//        if(StringUtils.isNotBlank(chiave.get("blocca"))){
//            getWebDriverWait(40).withMessage("Il tasto: '"+chiave.get("blocca")+"' NON VISIBILE").until(
//                    ExpectedConditions.visibilityOfElementLocated(By.id("button-block"))
//            );
//        }
//        if(StringUtils.isNotBlank(chiave.get("view"))){
//            getWebDriverWait(40).withMessage("Il tasto: '"+chiave.get("view")+"' NON VISIBILE").until(
//                    ExpectedConditions.visibilityOfElementLocated(By.id("button-view"))
//            );
//        }
//        if(StringUtils.isNotBlank(chiave.get("delete"))){
//            getWebDriverWait(40).withMessage("Il tasto: '"+chiave.get("view")+"' NON VISIBILE").until(
//                    ExpectedConditions.visibilityOfElementLocated(By.id("button-delete"))
//            );
//        }
//    }
    public void verificaTrePuntiniMostraDiPiu(Map<String, String> chiave) {
        webTool.waitTime(1);
        verificaVisibilitaPulsante(chiave, "ruota", "button-rotate");
        verificaVisibilitaPulsante(chiave, "blocca", "button-block");
        verificaVisibilitaPulsante(chiave, "view", "button-view");
        verificaVisibilitaPulsante(chiave, "delete", "button-delete");
    }

    private void verificaVisibilitaPulsante(Map<String, String> chiave, String chiaveNome, String buttonId) {
        if (StringUtils.isNotBlank(chiave.get(chiaveNome))) {
            webTool.waitTime(1);
            getWebDriverWait(50)
                    .withMessage("Il tasto: '" + chiave.get(chiaveNome) + "' NON VISIBILE")
                    .until(ExpectedConditions.elementToBeClickable(By.id(buttonId)));
        }
    }

    public void verificaTestoNelPopUp(String testo) {

        getWebDriverWait(40).withMessage("Il tasto: '" + testo + "' NON VISIBILE nel pop-up")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + testo + "')]")));
    }

    private void aggiornaPaginaWaitTime(int seconds) {
        aggiornamentoPagina();
        webTool.waitTime(seconds);
    }

    public void clickTrePuntiniVirtualKeys() {
        WebElement clickTrePuntiniVirtualKeys = getWebDriverWait(40).withMessage("Il Tre Puntini Virtual  NON VISIBILE")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@data-testid='virtualKeysTableDesktop']//tr[1]//button[@data-testid='contextMenuButton']")));
        clickTrePuntiniVirtualKeys.click();
    }


    public void clickRegistraChiavePubblica() {
        WebElement button = getWebDriverWait(40).withMessage("Il tasto Registra chiave pubblica NON VISIBILE").until(ExpectedConditions.elementToBeClickable(By.id("generate-public-key")));
        button.click();
    }

    public void inserisciValoreDellaChiave(String stato) {
        String uniqueKey = UUID.randomUUID().toString();
        WebElement publicKeyField = getWebDriverWait(40).withMessage("Il campo di input publicKey NON VISIBILE").until(ExpectedConditions.elementToBeClickable(By.id("publicKey")));
        publicKeyField.sendKeys(stato+"-"+uniqueKey);
    }

    public void clickRegistraOrFine() {
        WebElement button = getWebDriverWait(40).withMessage("Il tasto Registra").until(ExpectedConditions.elementToBeClickable(By.id("step-submit")));
        button.click();
    }

    public void verificaPopUpIntegrazioneApi(String test) {
        logger.info("POP up Verifico");
        webTool.waitTime(5);
        Assertions.assertTrue(
                driver.findElement(By.xpath("//div[@class='MuiAlert-message css-cysxvc']")).getText().toLowerCase().contains(test.toLowerCase()),
                "Il testo dell'alert non contiene la stringa attesa: " + test.toLowerCase() + ", ma visualizza: "+driver.findElement(By.xpath("//div[@class='MuiAlert-message css-cysxvc']")).getText().toLowerCase()
        );

        webTool.waitTime(5);
    }

    public void clickGeneraChiavePersonale() {
        WebElement button = getWebDriverWait(40).withMessage("Il tasto Genera chiave personale NON VISIBILE").until(ExpectedConditions.elementToBeClickable(By.id("generate-virtual-key")));
        button.click();

    }

    public void clickOkHoCapito() {
        WebElement button = getWebDriverWait(40).withMessage("Il tasto Genera chiave personale NON VISIBILE").until(ExpectedConditions.elementToBeClickable(By.id("close-modal-button")));
        button.click();
    }

    public void clickTrePuntiniPublicKeys(String stato) {
        WebElement clickTrePuntiniPublicKeys = getWebDriverWait(40).withMessage("Il Tre Puntini NON VISIBILE").until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@data-testid='publicKeysTableDesktop']//tr[1]//button[@data-testid='contextMenuButton']")));
        clickTrePuntiniPublicKeys.click();
    }


    public void clickRuotaIntegrazioneApi() {
        WebElement button = getWebDriverWait(40).withMessage("Il tasto Ruota NON VISIBILE").until(ExpectedConditions.elementToBeClickable(By.id("button-rotate")));
        button.click();
    }

    public void clickBloccaIntegrazioneApi() {
        WebElement button = getWebDriverWait(40).withMessage("Il tasto Blocca NON VISIBILE").until(ExpectedConditions.elementToBeClickable(By.id("button-block")));
        button.click();
    }

    public void clickTrePuntiniVirtualKeys(String stato) {
        WebElement threeDotsButtonSecondRow = driver.findElement(By.xpath("//table[@data-testid='virtualKeysTableDesktop']//tr[1]//button[@data-testid='contextMenuButton']"));
        threeDotsButtonSecondRow.click();
    }

    public void pulisciAmbienteVirtualKeys() {
        try {
            WebElement table = getWebDriverWait(20).withMessage("Prima Accesso alla Tabella Integrazione Api VirtualKeys  NON VISIBILE")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathVirtualKeysTableDesktop)));
            int b = 0;
            List<WebElement> righeBloccate = table.findElements(By.xpath(".//tr//td//div[@id='status-chip-Bloccata']"));
            //Si cancellano prima le righe bloccate per far proseguire i test automatici
            logger.info("Si cancellano prima le {} righe bloccate", righeBloccate.size());
            while (b < righeBloccate.size()) {

                WebElement row = righeBloccate.get(b);
                //si cliccano i tre puntini sulla riga con stato Bloccata
                row.findElement(By.xpath("./parent::td//..//button[@data-testid='contextMenuButton']")).click();
                clickEliminaIntegrazioneApi();
                clickSuConfermaNelPopUp();
                logger.info("Tasto Elimina cliccato su stato Bloccata.");
                aggiornaPaginaWaitTime(3);

                //Si aggiorna il conto delle righe dopo la cancellazione della riga
                if (getWebDriverWait(10).withMessage("Accessi succesivi alla Tabella Integrazione Api VirtualKeys  NON VISIBILE")
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathVirtualKeysTableDesktop))).isDisplayed()) {
                    table = getWebDriverWait(10).withMessage("Il Tabella Integrazione Api VirtualKeys  NON VISIBILE")
                            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathVirtualKeysTableDesktop)));
                    righeBloccate = table.findElements(By.xpath(".//tr//td//div[@id='status-chip-Bloccata']"));
                    b = 0;
                }
                b++;
            }
            //Si refresha l'element table e si cancellano ora le righe attive e ruotate.
            table = getWebDriverWait(20).withMessage("Prima Accesso alla Tabella Integrazione Api VirtualKeys  NON VISIBILE")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathVirtualKeysTableDesktop)));
            List<WebElement> rows = table.findElements(By.xpath(".//tr"));
            int i = 0;
            logger.info("si cancellano chiavi attive e ruotate");
            while (i < rows.size()) {
                WebElement row = rows.get(i);
                /**si individua la colonna dove è presente lo status chip (si è usato un xpath invece del numero della colonna nella tabella,
                 * che può cambiare in base al tipo di persona che entra nella pagina
                 */
                List<WebElement> cells = row.findElements(By.xpath(".//td//div[contains(@data-testid,'statusChip')]"));

                if (!cells.isEmpty()) {
                    String statoValue = cells.get(0).getText();

                    if(!registraChiavePubblicaPGSection.verificaStatoChiavePersonale("Attiva")){
                        registraChiavePubblicaPGSection.cliccareSuiTrePuntiniVirtualKeyConStato("Bloccata");
                        clickEliminaIntegrazioneApi();
                        clickSuConfermaNelPopUp();
                        aggiornaPaginaWaitTime(5);
                    }

                    if (statoValue.equalsIgnoreCase("Attiva") ) {
                        clickTrePuntiniVirtualKeys();
                        clickSuBlocca();
                        clickSuConfermaNelPopUp();
                        aggiornaPaginaWaitTime(5);
                    } else if (statoValue.equalsIgnoreCase("Ruotata") || statoValue.equalsIgnoreCase("Bloccata")) {
                        clickTrePuntiniVirtualKeys();
                        clickEliminaIntegrazioneApi();
                        clickSuConfermaNelPopUp();
                        aggiornaPaginaWaitTime(5);
                    }
                    if (getWebDriverWait(10).withMessage("Accessi succesivi alla Tabella Integrazione Api VirtualKeys  NON VISIBILE")
                            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathVirtualKeysTableDesktop))).isDisplayed()) {
                        table = getWebDriverWait(10).withMessage("Il Tabella Integrazione Api VirtualKeys  NON VISIBILE")
                                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathVirtualKeysTableDesktop)));
                        rows = table.findElements(By.xpath(".//tr"));
                        i = 0;
                    }
                }
                i++;
            }//for
        } catch (TimeoutException e) {
            logger.info("Tabella NON visibile. Proseguo comunque.");
        }

    }

    // Metodo ausiliario per ottenere tutte le righe
    private List<WebElement> getRows() {
        return getWebDriverWait(30)
                .withMessage("Impossibile accedere table.MuiTable-root tbody tr.MuiTableRow-root")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("table.MuiTable-root tbody tr.MuiTableRow-root")));
    }

    // 1. Verifica la colonna "Nome API Key"
    public void verificaColonnaNomeApiKey() {
       logger.info("verificaColonnaNomeApiKey");
        List<WebElement> celle = getWebDriverWait(20)
                .withMessage("Impossibile accedere verifica Colonna Nome ApiKey")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("table.MuiTable-root td:nth-child(1) p.MuiTypography-body1")));

        logger.info("Verifico che la colonna 'Nome API Key' non abbia valori nulli...");
        for (WebElement cella : celle) {
            String text = cella.getText().trim();
            if (text.isEmpty()) {
                throw new AssertionError("Cella nella colonna 'Nome API Key' è vuota");
            }
        }
    }

    public void verificaColonnaApiKey() {
        List<WebElement> celle = getWebDriverWait(20)
                .withMessage("Impossibile accedere verifica Colonna  ApiKey")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("table.MuiTable-root td:nth-child(2) .MuiTypography-body2")));

        logger.info("Verifico che la colonna 'Chiave API' non abbia valori nulli...");
        for (WebElement cella : celle) {
            String text = cella.getText().trim();
            if (text.isEmpty()) {
                throw new AssertionError("Cella nella colonna 'Chiave API' è vuota");
            }
        }
    }

    // 3. Verifica la colonna "Data creazione"
    public void verificaColonnaDataCreazione() {
        List<WebElement> celle = getWebDriverWait(20)
                .withMessage("Impossibile accedere verifica Colonna  Data Creazione")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("table.MuiTable-root td:nth-child(3) p[aria-current='date']")));

        logger.info("Verifico che la colonna 'Data creazione' non abbia valori nulli...");
        for (WebElement cella : celle) {
            String text = cella.getText().trim();
            if (text.isEmpty()) {
                throw new AssertionError("Cella nella colonna 'Data creazione' è vuota");
            }
        }
    }

    // 4. Verifica la colonna "Gruppi"
    public void verificaColonnaGruppi() {
        List<WebElement> celle = getWebDriverWait(20)
                .withMessage("Impossibile accedere verifica Colonna  Gruppi")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("table.MuiTable-root td:nth-child(4) span.css-l24ofh")));

        logger.info("Verifico che la colonna 'Gruppi' non abbia valori nulli...");
        for (WebElement cella : celle) {
            String text = cella.getText().trim();
            if (text.isEmpty()) {
                throw new AssertionError("Cella nella colonna 'Gruppi' è vuota");
            }
        }
    }

    // 5. Verifica la colonna "Stato"
    public void verificaColonnaStato() {
        List<WebElement> celle = getWebDriverWait(20)
                .withMessage("Impossibile accedere verifica Colonna  Stato")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("table.MuiTable-root td:nth-child(5) .MuiChip-label")));

        logger.info("Verifico che la colonna 'Stato' non abbia valori nulli...");
        for (WebElement cella : celle) {
            String text = cella.getText().trim();
            if (text.isEmpty()) {
                throw new AssertionError("Cella nella colonna 'Stato' è vuota");
            }
        }
    }

    // 6. Verifica la colonna "Azioni" (solo presenza del bottone)
    public List<WebElement> verificaColonnaAzioni() {
        List<WebElement> celle = getWebDriverWait(20)
                .withMessage("Impossibile accedere Azione (3 puntini) ")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.cssSelector("table.MuiTable-root td:nth-child(6) button"))));

        logger.info("Verifico che la colonna 'Azioni' non abbia valori nulli...");
        if (celle.isEmpty()) {
            throw new AssertionError("Nessun bottone trovato nella colonna 'Azioni'");
        }
        return celle;
    }



    public void verificaColonnaNomeChiave() {
        List<WebElement> nomeChiaveCells = getWebDriverWait(10)
                .withMessage("Impossibile trovare la colonna Nome ")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//td[1]//p[contains(@class, 'MuiTypography-body1')]")
        ));
        nomeChiaveCells.forEach(cell ->
                Assertions.assertFalse(cell.getText().isEmpty(), "Una cella nella colonna Nome Chiave è vuota.")
        );
    }

    public void verificaColonnaValoreChiave() {
        List<WebElement> valoreChiaveCells = getWebDriverWait(10)
                .withMessage("Impossibile trovare la colonna Valore ")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//td[2]//p[contains(@class, 'MuiTypography-body2')]")
        ));
        valoreChiaveCells.forEach(cell ->
                Assertions.assertFalse(cell.getText().isEmpty(), "Una cella nella colonna Valore Chiave è vuota.")
        );
    }

    public void verificaColonnaDataScadenza() {
        List<WebElement> dataScadenzaCells = getWebDriverWait(10)
                .withMessage("Impossibile trovare la colonna Scadenza ")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//td[3]//p[contains(@class, 'MuiTypography-body1')]")
        ));
        dataScadenzaCells.forEach(cell ->
                Assertions.assertFalse(cell.getText().isEmpty(), "Una cella nella colonna Data Scadenza è vuota.")
        );
    }

    public void verificaColonnaStatoPG() {
        List<WebElement> statoCells = getWebDriverWait(10)
                .withMessage("Impossibile trovare la colonna Stato ")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//td[4]//div[contains(@class, 'MuiChip-root')]//span[contains(@class, 'MuiChip-label')]")
        ));
        statoCells.forEach(cell ->
                Assertions.assertFalse(cell.getText().isEmpty(), "Una cella nella colonna Stato è vuota.")
        );
    }

    public void verificaColonnaOpzioni() {
        List<WebElement> opzioniButtons = getWebDriverWait(10)
                .withMessage("Impossibile trovare la colonna Opzioni (3 puntini) ")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//td[5]//button[contains(@data-testid, 'contextMenuButton')]")
        ));
        opzioniButtons.forEach(button ->
                Assertions.assertTrue(button.isDisplayed(), "Un pulsante nella colonna Opzioni non è visibile.")
        );
    }

}
