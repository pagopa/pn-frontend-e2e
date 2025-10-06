package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
public class GruppiPGPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(GruppiPGPage.class);

    @FindBy(id = "side-item-Gruppi")
    WebElement gruppiButton;

    @FindBy(id = "name")
    WebElement inputNomeGruppo;

    @FindBy(id = "description")
    WebElement inputDescrizioneGruppo;

    @FindBy(id = "product-select")
    WebElement selectSelezioneProdotto;

    @FindBy(id = "members-select")
    WebElement selectSelezioneMembri;

    @FindBy(xpath = "//button[contains(text(), 'Conferma')]")
    WebElement buttonConferma;


    private WebTool webTool;

    private AccediAreaRiservataPGPage accediAreaRiservataPGPage;

    private ScegliSpidPGPage scegliSpidPGPage;

    private LoginPGPagoPAPage loginPGPagoPAPage;

    private AutorizzaInvioDatiPGPage autorizzaInvioDatiPGPage;

    private Actions actions;

    public GruppiPGPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
        accediAreaRiservataPGPage = new AccediAreaRiservataPGPage(driver);
        scegliSpidPGPage = new ScegliSpidPGPage(driver);
        loginPGPagoPAPage = new LoginPGPagoPAPage(driver);
        autorizzaInvioDatiPGPage = new AutorizzaInvioDatiPGPage(driver);
    }

    public void clickGruppiButton() {
        By gruppiButtonLocator = By.id("side-item-Gruppi");
        WebElement gruppiButton = getWebDriverWait(15)
                .withMessage("Il bottone 'Gruppi' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(gruppiButtonLocator));
        js().executeScript("arguments[0].scrollIntoView(true);", gruppiButton);
        gruppiButton.click();
        logger.info("Cliccato il bottone 'Gruppi'");
        webTool.switchToOtherTab();
    }

    public void loginGruppi(String nome, String pwd) {
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

    public void waitLoadGruppiPage() {
        By pageTitle = By.xpath("//h4[contains(text(), 'Gruppi')]");
        By pageSubtitle = By.xpath("//p[contains(text(), 'I gruppi sono un insieme di utenti, per esempio appartenenti allo stesso ufficio o dipartimento, a cui si affida la gestione delle notifiche. Qui puoi gestire i gruppi dell’impresa e crearne di nuovi.')]");
        By buttonCreaGruppo = By.xpath("//button[contains(text(), 'Crea gruppo')]");
        By tableList = By.xpath("//div[@role='tablist']");

        getWebDriverWait(30).withMessage("Non si visualizza correttamente il titolo della pagina")
                .until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        getWebDriverWait(30).withMessage("Non si visualizza correttamente il sottotitolo della pagina")
                .until(ExpectedConditions.visibilityOfElementLocated(pageSubtitle));
        getWebDriverWait(30).withMessage("Non si visualizza correttamente il bottone crea gruppo")
                .until(ExpectedConditions.visibilityOfElementLocated(buttonCreaGruppo));
        getWebDriverWait(30).withMessage("Non si visualizza correttamente la lista della tabella")
                .until(ExpectedConditions.visibilityOfElementLocated(tableList));
        logger.info("La pagina Gruppi è stata caricata correttamente");
    }

    public void clickButtonCreaGruppo() {
        By buttonCreaGruppo = By.xpath("//button[contains(text(), 'Crea gruppo')]");
        WebElement button = getWebDriverWait(10)
                .withMessage("Il bottone 'Crea gruppo' non è visibile o non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(buttonCreaGruppo));
        button.click();
        logger.info("Cliccato sul bottone 'Crea gruppo'");
    }

    public void waitLoadCreaGruppoPage() {
        By pageTitleBy = By.xpath("//h4[contains(text(), 'Crea un nuovo gruppo')]");
        By buttonIndietroBy = By.xpath("//button[contains(text(), 'Indietro')]");
        By navigationBarBy = By.xpath("//nav[@aria-label='breadcrumb']");
        By pageSubTitleBy = By.xpath("//p[contains(text(), 'Inserisci il nome, la descrizione del gruppo e gli utenti che vuoi associarvi.')]");
        By inputNomeGruppoBy = By.id("name");
        By inputDescrizioneGruppoBy = By.id("description");
        By selectSelezioneProdottoBy = By.id("product-select");
        By selectSelezioneMembriBy = By.id("members-select");
        By buttonConfermaBy = By.xpath("//button[contains(text(), 'Conferma')]");

        getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente il titolo della pagina")
                .until(ExpectedConditions.visibilityOfElementLocated(pageTitleBy));

        getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente il bottone indietro ad inizio pagina")
                .until(ExpectedConditions.visibilityOfElementLocated(buttonIndietroBy));

        getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente la barra di navigazione")
                .until(ExpectedConditions.visibilityOfElementLocated(navigationBarBy));

        getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente il sottotitolo della pagina")
                .until(ExpectedConditions.visibilityOfElementLocated(pageSubTitleBy));

        inputNomeGruppo = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente l'input del nome del gruppo")
                .until(ExpectedConditions.visibilityOfElementLocated(inputNomeGruppoBy));

        inputDescrizioneGruppo = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente l'input della descrizione del gruppo")
                .until(ExpectedConditions.visibilityOfElementLocated(inputDescrizioneGruppoBy));

        selectSelezioneProdotto = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente la select per la selezione del prodotto")
                .until(ExpectedConditions.visibilityOfElementLocated(selectSelezioneProdottoBy));

        selectSelezioneMembri = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente la select per la selezione dei membri")
                .until(ExpectedConditions.visibilityOfElementLocated(selectSelezioneMembriBy));

        buttonConferma = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente il bottone conferma")
                .until(ExpectedConditions.visibilityOfElementLocated(buttonConfermaBy));

        logger.info("Pagina 'Crea Gruppo' caricata correttamente");
    }

    public void creazioneNuovoGruppo(Map<String, String> datiGruppo) {
        log.info("Inserimento nome del gruppo");
        String timestamp = datiGruppo.get("nome") + Instant.now().toString();
        inputNomeGruppo = driver.findElement(By.id("name"));
        inputNomeGruppo.click();
        inputNomeGruppo.sendKeys(timestamp);
        log.info("Inserimento descrizione del gruppo");
        inputDescrizioneGruppo = driver.findElement(By.id("description"));
        inputDescrizioneGruppo.click();
        inputDescrizioneGruppo.sendKeys(timestamp);
        vaiInFondoAllaPagina();
        log.info("Click sulla select per la selezione dei membri");
        selectSelezioneMembri = driver.findElement(By.id("members-select"));
        selectSelezioneMembri.click();
        log.info("Click sul membro Dante Alighieri");
        webTool.waitTime(10);
        WebElement checkBoxDanteAlighieri = driver.findElement(By.xpath("//li//p[contains(text(),'Dante')]"));
        checkBoxDanteAlighieri.click();
        actions = new Actions(driver);
        actions.moveToElement(selectSelezioneMembri).click().perform();
    }

    public void checkButtonConfermaAndClick() {
        By buttonConfermaBy = By.xpath("//button[contains(text(), 'Conferma')]");
        buttonConferma = getWebDriverWait(15)
                .withMessage("Il bottone 'Conferma' non è visibile o non è abilitato")
                .until(ExpectedConditions.elementToBeClickable(buttonConfermaBy));
        log.info("Il bottone 'Conferma' è abilitato, lo si clicca");
        buttonConferma.click();
    }

    public void waitLoadGruppoCreatoPage(String nomeGruppo) {
        By pageTitleBy = By.xpath("//h4[contains(text(), '" + nomeGruppo + "')]");
        By navigationBarBy = By.xpath("//nav[@aria-label='breadcrumb']");
        By buttonEliminaBy = By.xpath("//button[contains(text(), 'Elimina')]");
        By buttonModificaBy = By.xpath("//button[contains(text(), 'Modifica')]");
        By buttonSospendiBy = By.xpath("//button[contains(text(), 'Sospendi')]");
        By buttonDuplicaBy = By.xpath("//button[contains(text(), 'Duplica')]");
        By descrizioneGruppoBy = By.xpath("//div/p[@aria-label]");
        By creatoGruppoBy = By.xpath("//div/p[text()='Creato da - in data']");
        By modificatoGruppoBy = By.xpath("//div/p[text()='Modificato da - in data']");
        By buttonAggiungiUtenteBy = By.xpath("//button[contains(text(), 'Aggiungi utente')]");

        getWebDriverWait(15).withMessage("Non si visualizza correttamente il titolo della pagina")
                .until(ExpectedConditions.visibilityOfElementLocated(pageTitleBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente la barra di navigazione")
                .until(ExpectedConditions.visibilityOfElementLocated(navigationBarBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente il bottone elimina")
                .until(ExpectedConditions.visibilityOfElementLocated(buttonEliminaBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente il bottone modifica")
                .until(ExpectedConditions.visibilityOfElementLocated(buttonModificaBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente il bottone sospendi")
                .until(ExpectedConditions.visibilityOfElementLocated(buttonSospendiBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente il bottone duplica")
                .until(ExpectedConditions.visibilityOfElementLocated(buttonDuplicaBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente la descrizione del gruppo creato")
                .until(ExpectedConditions.visibilityOfElementLocated(descrizioneGruppoBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente la data di creazione del gruppo")
                .until(ExpectedConditions.visibilityOfElementLocated(creatoGruppoBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente la data di modifica del gruppo")
                .until(ExpectedConditions.visibilityOfElementLocated(modificatoGruppoBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente il bottone aggiungi utente")
                .until(ExpectedConditions.visibilityOfElementLocated(buttonAggiungiUtenteBy));
    }

    public void waitLoadGruppoSospesoPage(String nomeGruppo) {
        By pageTitleBy = By.xpath("//h4[contains(text(), '" + nomeGruppo + "')]");
        By navigationBarBy = By.xpath("//nav[@aria-label='breadcrumb']");
        By buttonEliminaBy = By.xpath("//button[contains(text(), 'Elimina')]");
        By buttonRiattivaBy = By.xpath("//button[contains(text(), 'Riattiva')]");
        By descrizioneGruppoBy = By.xpath("//div/p[@aria-label]");
        By creatoGruppoBy = By.xpath("//div/p[text()='Creato da - in data']");
        By modificatoGruppoBy = By.xpath("//div/p[text()='Modificato da - in data']");

        getWebDriverWait(15).withMessage("Non si visualizza correttamente il titolo della pagina")
                .until(ExpectedConditions.visibilityOfElementLocated(pageTitleBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente la barra di navigazione")
                .until(ExpectedConditions.visibilityOfElementLocated(navigationBarBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente il bottone elimina")
                .until(ExpectedConditions.visibilityOfElementLocated(buttonEliminaBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente il bottone riattiva")
                .until(ExpectedConditions.visibilityOfElementLocated(buttonRiattivaBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente la descrizione del gruppo creato")
                .until(ExpectedConditions.visibilityOfElementLocated(descrizioneGruppoBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente la data di creazione del gruppo")
                .until(ExpectedConditions.visibilityOfElementLocated(creatoGruppoBy));
        getWebDriverWait(15).withMessage("Non si visualizza correttamente la data di modifica del gruppo")
                .until(ExpectedConditions.visibilityOfElementLocated(modificatoGruppoBy));
    }

    public void clickGruppiButtonSchedaGruppi() {
        By buttonGruppiBy = By.xpath("//li[.//span[contains(text(), 'Gruppi')]]");
        WebElement buttonGruppi = getWebDriverWait(10)
                .withMessage("Il bottone 'Gruppi' nella sidebar non si visualizza e non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(buttonGruppiBy));
        buttonGruppi.click();
    }

    public void clickBottonePaginaDettaglioGruppo(String azioneSuGruppi) {
        By actionButtonDettaglioGruppoBy = By.xpath("//button[contains(text(), '" + azioneSuGruppi + "')]");

        WebElement actionButtonDettaglioGruppo = getWebDriverWait(10)
                .withMessage("Il bottone " + azioneSuGruppi + " non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(actionButtonDettaglioGruppoBy));

        actionButtonDettaglioGruppo.click();
    }

    public void clickBottonePopUpPaginaDettaglioGruppo(String azioneSuGruppi) {
        By actionButtonsBy = By.xpath("//button[contains(text(), '" + azioneSuGruppi + "')]");
        List<WebElement> actionButtons = getWebDriverWait(10)
                .withMessage("I bottoni " + azioneSuGruppi + " non sono presenti")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(actionButtonsBy));
        if (actionButtons.size() < 2) {
            Assertions.fail("Non ci sono abbastanza bottoni per selezionare il secondo.");
        }
        WebElement secondButton = getWebDriverWait(10)
                .withMessage("Il secondo bottone " + azioneSuGruppi + " non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(actionButtons.get(1)));
        secondButton.click();
    }

    public void checkCampiModificabili() {
        webTool.waitTime(10);
        inputNomeGruppo = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente l'input del nome del gruppo")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        inputDescrizioneGruppo = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente l'input della descrizione del gruppo")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));
        selectSelezioneMembri = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente l'input per la selezione dei membri del gruppo")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("members-select")));
        WebElement inputSelectProdotti = getWebDriverWait(10)
                .withMessage("Non si legge correttamente la proprietà dell'input per la selezione dei prodotti del gruppo")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='SEND - Notifiche Digitali']")));
        Assertions.assertEquals("true", inputSelectProdotti.getAttribute("disabled"),
                "L'input dei prodotti dovrebbe essere disabilitato");
    }

    public void siModificaUnCampoDelGruppo(String campo, String modifica) {
        WebElement inputField;

        if (campo.equalsIgnoreCase("descrizione")) {
            inputField = getWebDriverWait(10)
                    .withMessage("Il campo descrizione non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));
        } else {
            inputField = getWebDriverWait(10)
                    .withMessage("Il campo nome non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        }

        inputField.click();
        inputField.clear(); // svuota il campo in modo sicuro
        inputField.sendKeys(modifica);
    }

    public void checkPopUpConfermaModifica(String testoPopUp) {
        By popUpLocator = By.xpath("//div[@role='alert']//p[text()='" + testoPopUp + "']");
        getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente il popup di conferma")
                .until(ExpectedConditions.visibilityOfElementLocated(popUpLocator));
    }

    public void checkModificheSalvate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        // Locator per la descrizione modificata
        By descrizioneLocator = By.xpath("//div[p[@aria-label and contains(text(), 'Nuova descrizione')]]");
        // Attendo che la descrizione sia visibile e contenga il testo corretto
        getWebDriverWait(10).withMessage("La descrizione modificata non viene visualizzata o non è corretta")
                .until(ExpectedConditions.textToBePresentInElementLocated(descrizioneLocator, "Nuova descrizione"));
        // Locator per le date (creazione e modifica)
        By dateLocator = By.xpath("//div[contains(@class, 'MuiGrid-root')]//p[text()='" + formattedDate + "']");
        // Attendo che ci siano almeno 2 date visibili
        getWebDriverWait(10).withMessage("La data della modifica non è visualizzata o non è corretta")
                .until(driver -> {
                    List<WebElement> dates = driver.findElements(dateLocator);
                    return dates.size() > 1 && dates.get(1).isDisplayed();
                });
        // Recupero la seconda data e verifico
        List<WebElement> dataCreazioneEModifica = driver.findElements(dateLocator);
        Assertions.assertTrue(dataCreazioneEModifica.size() > 1,
                "Non sono presenti sufficienti date di creazione/modifica");
        Assertions.assertEquals(formattedDate, dataCreazioneEModifica.get(1).getText(),
                "La data della modifica non è corretta");
    }

    public void checkPopUpSospensioneGruppo() {
        WebElement popUpTitle = driver.findElement(By.xpath("//h6[contains(text(), 'Sospendi gruppo')]"));
        WebElement popUpSubtitle = driver.findElement(By.xpath("//p[contains(text(), 'Vuoi sospendere il gruppo')]"));
        WebElement buttonAnnulla = driver.findElement(By.xpath("//button[contains(text(), 'Annulla')]"));
        WebElement buttonSospendi = driver.findElement(By.xpath("//button[contains(text(), 'Sospendi')]"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo del pop up").until(ExpectedConditions.visibilityOf(popUpTitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo del pop up").until(ExpectedConditions.visibilityOf(popUpSubtitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone annulla del pop up").until(ExpectedConditions.visibilityOf(buttonAnnulla));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone sospendi del pop up").until(ExpectedConditions.visibilityOf(buttonSospendi));
    }

    public void checkVoceSospeso() {
        By voceSospesoLocator = By.xpath("//div[@aria-label='Suspended']//span[contains(text(), 'Sospeso')]");
        getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente la voce sospeso nella descrizione del gruppo")
                .until(ExpectedConditions.visibilityOfElementLocated(voceSospesoLocator));
    }

    public void checkPopUpRiattivazioneGruppo() {
        WebElement popUpTitle = driver.findElement(By.xpath("//h6[contains(text(), 'Riattiva gruppo')]"));
        WebElement popUpSubtitle = driver.findElement(By.xpath("//p[contains(text(), 'Vuoi riattivare il gruppo')]"));
        WebElement buttonAnnulla = driver.findElement(By.xpath("//button[contains(text(), 'Annulla')]"));
        WebElement buttonRiattiva = driver.findElement(By.xpath("//button[contains(text(), 'Riattiva')]"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo del pop up").until(ExpectedConditions.visibilityOf(popUpTitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo del pop up").until(ExpectedConditions.visibilityOf(popUpSubtitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone annulla del pop up").until(ExpectedConditions.visibilityOf(buttonAnnulla));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone riattiva del pop up").until(ExpectedConditions.visibilityOf(buttonRiattiva));
    }

    public void checkPopUpDuplicazioneGruppo() {
        WebElement popUpTitle = driver.findElement(By.xpath("//h6[contains(text(), 'Duplica gruppo')]"));
        WebElement popUpSubtitle = driver.findElement(By.xpath("//p[contains(text(), 'Vuoi duplicare il gruppo')]"));
        WebElement buttonAnnulla = driver.findElement(By.xpath("//button[contains(text(), 'Annulla')]"));
        WebElement buttonDuplica = driver.findElement(By.xpath("//button[contains(text(), 'Duplica')]"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo del pop up").until(ExpectedConditions.visibilityOf(popUpTitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo del pop up").until(ExpectedConditions.visibilityOf(popUpSubtitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone annulla del pop up").until(ExpectedConditions.visibilityOf(buttonAnnulla));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone duplica del pop up").until(ExpectedConditions.visibilityOf(buttonDuplica));
    }

    public void waitLoadDuplicazionePage() {
        By pageTitleLocator = By.xpath("//h4[contains(text(), 'Duplica gruppo')]");
        By buttonIndietroLocator = By.xpath("//button[contains(text(), 'Indietro')]");
        By navigationBarLocator = By.xpath("//nav[@aria-label='breadcrumb']");
        By pageSubTitleLocator = By.xpath("//p[contains(text(), 'Duplica il gruppo e modifica i dati')]");
        By inputNomeGruppoLocator = By.id("name");
        By inputDescrizioneGruppoLocator = By.id("description");
        By selectSelezioneMembriLocator = By.id("members-select");
        By selectSelezioneProdottoLocator = By.id("product-select");
        By buttonConfermaLocator = By.xpath("//button[contains(text(), 'Conferma')]");

        getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente il titolo della pagina")
                .until(ExpectedConditions.visibilityOfElementLocated(pageTitleLocator));

        getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente il bottone indietro ad inizio pagina")
                .until(ExpectedConditions.visibilityOfElementLocated(buttonIndietroLocator));

        getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente la barra di navigazione")
                .until(ExpectedConditions.visibilityOfElementLocated(navigationBarLocator));

        getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente il sottotitolo della pagina")
                .until(ExpectedConditions.visibilityOfElementLocated(pageSubTitleLocator));

        inputNomeGruppo = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente l'input del nome del gruppo e il value è errato")
                .until(driver -> {
                    WebElement el = driver.findElement(inputNomeGruppoLocator);
                    if (el.isDisplayed() && el.getAttribute("value").contains("Copia di Gruppo Test")) {
                        return el;
                    }
                    return null;
                });

        inputDescrizioneGruppo = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente l'input della descrizione del gruppo")
                .until(ExpectedConditions.visibilityOfElementLocated(inputDescrizioneGruppoLocator));

        selectSelezioneMembri = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente la select per la selezione dei membri")
                .until(ExpectedConditions.visibilityOfElementLocated(selectSelezioneMembriLocator));

        selectSelezioneProdotto = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente la select per la selezione del prodotto")
                .until(ExpectedConditions.visibilityOfElementLocated(selectSelezioneProdottoLocator));

        buttonConferma = getWebDriverWait(10)
                .withMessage("Non si visualizza correttamente il bottone conferma")
                .until(ExpectedConditions.visibilityOfElementLocated(buttonConfermaLocator));
    }

    public void checkPopUpEliminazioneGruppo() {
        WebElement popUpTitle = driver.findElement(By.xpath("//h6[contains(text(), 'Elimina gruppo')]"));
        WebElement buttonAnnulla = driver.findElement(By.xpath("//button[contains(text(), 'Annulla')]"));
        WebElement buttonElimina = driver.findElement(By.xpath("//button[contains(text(), 'Elimina')]"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo del pop up").until(ExpectedConditions.visibilityOf(popUpTitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone annulla del pop up").until(ExpectedConditions.visibilityOf(buttonAnnulla));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone elimina del pop up").until(ExpectedConditions.visibilityOf(buttonElimina));
    }

    public void eliminaGruppoDaPaginaIniziale(String azioneGruppo, String nomeGruppo) {
        By tableRowLocator = By.xpath("//div[contains(.//p, '" + nomeGruppo + "') and @data-field='name']");

        WebElement tableRowGruppo = getWebDriverWait(10)
                .withMessage("Non è stata trovata la riga del gruppo con il nome indicato nello step del test")
                .until(ExpectedConditions.elementToBeClickable(tableRowLocator));
        tableRowGruppo.click();

        clickBottonePaginaDettaglioGruppo(azioneGruppo);
        clickBottonePopUpPaginaDettaglioGruppo(azioneGruppo);
    }

}