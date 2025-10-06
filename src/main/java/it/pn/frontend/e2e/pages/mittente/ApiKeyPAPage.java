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
import java.util.UUID;


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
            getWebDriverWait(30)
                    .withMessage("Il titolo della pagina ApiKey non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("API Key-page")));

            getWebDriverWait(40)
                    .withMessage("Il bottone genera ApiKey non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.id("generate-api-key")));

            logger.info("Api Key Page caricata");
        } catch (TimeoutException e) {
            Assertions.fail("Api Key Page NON caricata con errore: " + e.getMessage());
        }
    }

    public void clickSulBottoneGeneraApiKey() {
        try {
            WebElement generateApiKeyButton = getWebDriverWait(20)
                    .withMessage("Il bottone genera ApiKey non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.id("generate-api-key")));
            // Prova il click "normale"
            try {
                generateApiKeyButton.click();
                logger.info("Click sul bottone 'Genera ApiKey' eseguito normalmente");
            } catch (Exception e) {
                // Fallback con JS se il click normale fallisce
                js().executeScript("arguments[0].click()", generateApiKeyButton);
                logger.warn("Click eseguito con JavaScript perché il normale click è fallito: {}", e.getMessage());
            }
        } catch (TimeoutException e) {
            Assertions.fail("Il bottone 'Genera ApiKey' NON è stato trovato/cliccabile entro il tempo limite: " + e.getMessage());
        }
    }

    public void inserireUnNomePerApiKey(String nomeApiKey) {
        try {
            WebElement apiKeyNameInput = getWebDriverWait(30)
                    .withMessage("Il campo Nome ApiKey non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

            apiKeyNameInput.clear(); // buona pratica: pulisci il campo prima di scrivere
            apiKeyNameInput.sendKeys(nomeApiKey);

            logger.info("Inserito nome ApiKey: {}", nomeApiKey);
        } catch (TimeoutException e) {
            Assertions.fail("Il campo Nome ApiKey NON è stato trovato/visibile entro il tempo limite: " + e.getMessage());
        }
    }

    public void clickSulBottoneContinua() {
        driver.findElement(By.tagName("body")).click();

        WebElement continuaButton = getWebDriverWait(40)
                .withMessage("Il bottone Continua non è abilitato")
                .until(driver -> {
                    WebElement el = driver.findElement(By.id("continue-button"));
                    return el.isEnabled() ? el : null;
                });
        continuaButton.click();

    }

    public void siVisualizzaCorrettamenteConfermaPage() {
        try {
            getWebDriverWait(30)
                    .withMessage("Il titolo della pagina conferma Apikey non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("go-to-api-keys")));

            logger.info("Api Key Confirmation Page caricata");
        } catch (TimeoutException e) {
            Assertions.fail("La Api Key Confirmation Page NON è stata caricata: " + e.getMessage());
        }
    }

    public void clickSulBottoneTornaApiKey() {
        By locator = By.id("go-to-api-keys");
        WebElement tornaApiButton = getWebDriverWait(20)
                .withMessage("Impossibile trovare go-to-api-keys")
                .until(ExpectedConditions.elementToBeClickable(locator));
        tornaApiButton.click();
    }

    public void siVisualizzaNuovaApiAttiva(String nomeApiKey) {
        try {
            // Verifica stato "Attiva"
            getWebDriverWait(30)
                    .withMessage("Lo stato dell'ApiKey non è 'Attiva'")
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@data-testid='statusChip-Attiva']")));

            // Verifica che il nome corrisponda
            getWebDriverWait(30)
                    .withMessage("Il nome dell'ApiKey attiva non è: " + nomeApiKey)
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//p[contains(text(),'" + nomeApiKey + "')]")));

            logger.info("Api Key attiva '" + nomeApiKey + "' visualizzata correttamente");

        } catch (TimeoutException e) {
            Assertions.fail("La nuova Api Key attiva NON è stata visualizzata: " + e.getMessage());
        }
    }

    public int getPosizioneMenuButton(String stato) {

        List<WebElement> statiApiKeyBy = getWebDriverWait(30).
                withMessage("Lista stati ApiKey non trovata")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@id,'status-chip-')]")));

        for (int i = 0; i < statiApiKeyBy.size(); i++) {
            if (("status-chip-" + stato).equalsIgnoreCase(statiApiKeyBy.get(i).getAttribute("id"))) {
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
        getWebDriverWait(30)
                .withMessage("Il popup 'Blocca API Key' NON si visualizza correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[contains(text(),'Blocca API Key')]")
                ));

        logger.info("Il popup si visualizza correttamente");
    }

    public void clickSuAnnulla() {
        WebElement annullaButton = getWebDriverWait(40)
                .withMessage("Il bottone Annulla nel popup non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("close-modal-button")));
        annullaButton.click();
    }

    public void clickSuConfermaNelPopUp() {
        WebElement confermaButton = getWebDriverWait(50)
                .withMessage("Bottone Conferma nel pop up non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("action-modal-button")));
        confermaButton.click();
    }

    public void notificaSelezionataStatoBloccata() {
        getWebDriverWait(20)
                .withMessage("Stato 'Bloccata' non trovato o non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("status-chip-Bloccata")));

        logger.info("La notifica è in stato bloccata");
    }

    public void clickAttivaSulMenu() {
        WebElement attivaButtonNelMenu = getWebDriverWait(30).
                withMessage("Il bottone Attiva nel menu non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@data-testid,'buttonEnable')]")));
        attivaButtonNelMenu.click();
    }

    public void siVisualizzaPoPUpAttiva() {
        getWebDriverWait(20)
                .withMessage("Popup 'Attiva API Key' non visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[contains(text(),'Attiva API Key')]")));
    }

    public void siVisualizzaNotificaSelezionataBloccata() {
        getWebDriverWait(20)
                .withMessage("Elemento 'Attiva' non visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[contains(text(),'Attiva')]")));
    }

    public void clickRuotaSulMenu() {
        WebElement ruotaButton = getWebDriverWait(20)
                .withMessage("Il bottone ruota del menu apikey non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("button-rotate")));

        ruotaButton.click();
    }

    public void siVisualizzaPoPUpRuota() {
        getWebDriverWait(20)
                .withMessage("Il titolo 'Ruota API Key' sul popup non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[contains(text(),'Ruota API Key')]")));
    }

    public void siVisualizzaNotificaSelezionataRuotata() {
        getWebDriverWait(20)
                .withMessage("Stato 'Ruotata' non visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@data-testid='statusChip-Ruotata']")));
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
                .until(ExpectedConditions.presenceOfElementLocated(By.id("button-enable")));
        if ("Attiva".equals(attivaItem.getText())) {
            return true;
        }
        return false;
    }

    public void clickMenuButtonBlocca() {
        int posizione = verificaBottoni("Bloccata");
        logger.info("Posizione: {}", posizione);

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

    public void mouseHover() {
        WebElement statoElement = getWebDriverWait(20)
                .withMessage("Stato 'Attiva' non trovato")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("status-chip-Attiva")));

        Actions action = new Actions(driver);
        action.moveToElement(statoElement).perform();
    }


    public void waitLoadMessaggioData() {
        getWebDriverWait(10)
                .withMessage("Messaggio con data di creazione non visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@data-popper-placement='bottom']")));

        logger.info("Il messaggio con la data di creazione si visualizza correttamente");
    }

    public void inserireGruppoApi(String gruppo) {
        WebElement gruppoInput = getWebDriverWait(30)
                .withMessage("Campo gruppo API Key non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("groups")));
        gruppoInput.sendKeys(gruppo);
        WebElement groupOption = getWebDriverWait(65)
                .withMessage("Opzione gruppo non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("groups-option-0")));
        groupOption.click();
    }

    public void cancellareTestoInserito() {
        WebElement nameField = getWebDriverWait(20)
                .withMessage("Campo 'name' non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        // Click per assicurarsi che il campo sia focalizzato
        js().executeScript("arguments[0].click()", nameField);

        // Cancella tutto il testo in modo efficiente
        nameField.sendKeys(Keys.CONTROL + "a"); // Seleziona tutto il testo
        nameField.sendKeys(Keys.BACK_SPACE);    // Cancella

        logger.info("Il testo è stato cancellato correttamente");
    }

    public void siVisualizzaMessaggioErroreApiName() {
        getWebDriverWait(20)
                .withMessage("Messaggio di errore API name non visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[contains(@id,'name-helper-text')]")));

        logger.info("Si visualizza correttamente il messaggio di errore");
    }

    public void clickSuVisualizza() {
        WebElement visualizzaButton = getWebDriverWait(10)
                .withMessage("Bottone Visualizza non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("button-view")));

        visualizzaButton.click();
    }

    public void siVisualizzaPopUpVisualizza() {
        getWebDriverWait(30)
                .withMessage("Sottotitolo del popup non visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("subtitle-top")));

        logger.info("Si visualizza correttamente il sottotitolo del popup");
    }

    public void chiudiPopUpVisualizza() {
        closeButtonPopUpVisualizza = getWebDriverWait(30)
                .withMessage("Bottone chiusura popup non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("close-modal-button")));

        closeButtonPopUpVisualizza.click();
    }

    public void clickVisualizzaIdApiKey() {
        WebElement viewButton = getWebDriverWait(20)
                .withMessage("Bottone visualizza ID API Key non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("button-view-groups-id")));

        viewButton.click();
    }

    public void popUpGruppiAssociati() {
        getWebDriverWait(30)
                .withMessage("Titolo 'Gruppi associati alla API' non visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[contains(text(),'Gruppi associati alla API')]")));

        logger.info("Si visualizza correttamente il titolo popup Gruppi associati alla API");
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
            return getWebDriverWait(25)
                    .withMessage("Il bottone Genera chiave pubblica non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("generate-public-key")))
                    .isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.warn("Il bottone Genera chiave pubblica non è visibile");
            return false;
        }
    }

    public void clickBottoneGeneraChiavePubblica() {
        WebElement button = getWebDriverWait(10)
                .withMessage("Bottone 'Genera chiave pubblica' non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("generate-public-key")));

        button.click();
    }

    public void mouseHoverGroups() {
        webTool.waitTime(3);
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@data-testid='tableApiKeys.body.row']"));

        if (rows.isEmpty()) {
            Assertions.fail("Nessuna riga trovata nella tabella API Keys");
            return;
        }

        WebElement firstRow = rows.get(0);
        List<WebElement> cells = firstRow.findElements(By.tagName("td"));

        if (cells.size() < 4) {
            Assertions.fail("La riga non contiene almeno 4 colonne");
            return;
        }

        WebElement groupsCell = cells.get(3);
        getWebDriverWait(10)
                .withMessage("Colonna gruppi non visibile")
                .until(ExpectedConditions.visibilityOf(groupsCell));

        Actions action = new Actions(driver);
        action.moveToElement(groupsCell).perform();
    }

    public String copiaApiKeyESalva() {
        webTool.waitTime(5);
        WebElement apiKeyField = getWebDriverWait(10)
                .withMessage("Campo API Key non trovato")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("apiKeyId")));

        return apiKeyField.getAttribute("value");
    }

    public String visualizzaApiKeyInElenco() {
        webTool.waitTime(10);
        WebElement contextMenu = getWebDriverWait(20)
                .withMessage("Context menu non trovato")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@data-testid='contextMenuButton']")));
        contextMenu.click();

        webTool.waitTime(7);
        WebElement visualizzaButton = getWebDriverWait(10)
                .withMessage("Bottone visualizza codice non trovato")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//li[@data-testid='buttonView']")));
        visualizzaButton.click();

        webTool.waitTime(7);
        WebElement apiKeyField = getWebDriverWait(10)
                .withMessage("Campo API Key non trovato")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@aria-invalid='false']")));

        return apiKeyField.getAttribute("value");
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
        getWebDriverWait(40).withMessage("Il testo '" + testo + "' NON VISIBILE").until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@data-testid='emptyState']//p[contains(text(), '" + testo + "')]")
                )
        );
    }

    public void nellaSezioneIntegrazioneAPISiVisualizzaAlert(String testo) {
        getWebDriverWait(40).withMessage("Il testo '" + testo + "' NON VISIBILE").until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@role='alert']//p[contains(text(), '" + testo + "')]")
                )
        );
    }

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
        publicKeyField.sendKeys(stato + "-" + uniqueKey);
    }

    public void clickRegistraOrFine() {
        WebElement button = getWebDriverWait(40).withMessage("Il tasto Registra").until(ExpectedConditions.elementToBeClickable(By.id("step-submit")));
        button.click();
    }

    public void verificaPopUpIntegrazioneApi(String test) {
        logger.info("Verifica popup integrazione API");

        WebElement alertMessage = getWebDriverWait(10)
                .withMessage("Messaggio alert non trovato")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='MuiAlert-message css-cysxvc']")));

        String actualText = alertMessage.getText().toLowerCase();
        String expectedText = test.toLowerCase();

        Assertions.assertTrue(actualText.contains(expectedText),
                "Il testo dell'alert non contiene la stringa attesa: '" + expectedText +
                        "', ma visualizza: '" + actualText + "'");
        //        webTool.waitTime(5);
    }

    public void clickGeneraChiavePersonale() {
        WebElement button = getWebDriverWait(40).withMessage("Il tasto Genera chiave personale NON VISIBILE").until(ExpectedConditions.elementToBeClickable(By.id("generate-virtual-key")));
        button.click();

    }

    public void clickOkHoCapito() {
        WebElement button = getWebDriverWait(40).withMessage("Il tasto Genera chiave personale NON VISIBILE")
                .until(ExpectedConditions.elementToBeClickable(By.id("close-modal-button")));
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
        WebElement threeDotsButton = getWebDriverWait(10)
                .withMessage("Bottone tre puntini non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//table[@data-testid='virtualKeysTableDesktop']//tr[1]//button[@data-testid='contextMenuButton']")));

        threeDotsButton.click();
    }

    public void pulisciAmbienteVirtualKeys() {
        try {
            logger.info("Entrato nel metodo pulisciAmbienteVirtualKeys");
            WebElement table = getWebDriverWait(20).withMessage("Prima Accesso alla Tabella Integrazione Api VirtualKeys  NON VISIBILE")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathVirtualKeysTableDesktop)));
            int b = 0;
            logger.info("Tabella pulisciAmbienteVirtualKeys size: {}", table.getSize());
            List<WebElement> righeBloccate = table.findElements(By.xpath(".//tr//td//div[@id='status-chip-Bloccata']"));
            //Si cancellano prima le righe bloccate per far proseguire i test automatici
            logger.info("Numero di righe bloccate da cancellare: {} ", righeBloccate.size());
            while (b < righeBloccate.size()) {

                WebElement row = righeBloccate.get(b);
                //si cliccano i tre puntini sulla riga con stato Bloccata
                row.findElement(By.xpath("./parent::td//..//button[@data-testid='contextMenuButton']")).click();
                clickEliminaIntegrazioneApi();
                logger.info("Elimina riga bloccata: {}   ", b);
                clickSuConfermaNelPopUp();
                logger.info("Tasto Conferma Elimina cliccato su stato Bloccata.");
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

                    if (!registraChiavePubblicaPGSection.verificaStatoChiavePersonale("Attiva")) {
                        registraChiavePubblicaPGSection.cliccareSuiTrePuntiniVirtualKeyConStato("Bloccata");
                        clickEliminaIntegrazioneApi();
                        clickSuConfermaNelPopUp();
                        aggiornaPaginaWaitTime(5);
                    }

                    if (statoValue.equalsIgnoreCase("Attiva")) {
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
