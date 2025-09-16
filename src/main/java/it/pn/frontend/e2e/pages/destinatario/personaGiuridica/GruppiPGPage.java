package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
public class GruppiPGPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("GruppiPGPage");

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
        gruppiButton = driver.findElement(By.id("side-item-Gruppi"));
        js().executeScript("arguments[0].scrollIntoView(true);", gruppiButton);
        getWebDriverWait(10).withMessage("Il bottone gruppi non è visibile").until(ExpectedConditions.visibilityOf(gruppiButton));
        gruppiButton.click();
        webTool.waitTime(5);
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
        WebElement pageTitle = driver.findElement(By.xpath("//h4[contains(text(), 'Gruppi')]"));
        WebElement pageSubtitle = driver.findElement(By.xpath("//p[contains(text(), 'I gruppi sono un insieme di utenti, per esempio appartenenti allo stesso ufficio o dipartimento, a cui si affida la gestione delle notifiche. Qui puoi gestire i gruppi dell’impresa e crearne di nuovi.')]"));
        WebElement buttonCreaGruppo = driver.findElement(By.xpath("//button[contains(text(), 'Crea gruppo')]"));
        WebElement tableList = driver.findElement(By.xpath("//div[@role='tablist']"));
        getWebDriverWait(30).withMessage("Non si visualizza correttamente il titolo della pagina").until(ExpectedConditions.visibilityOf(pageTitle));
        getWebDriverWait(30).withMessage("Non si visualizza correttamente il sottotitolo della pagina").until(ExpectedConditions.visibilityOf(pageSubtitle));
        getWebDriverWait(30).withMessage("Non si visualizza correttamente il bottone crea gruppo").until(ExpectedConditions.visibilityOf(buttonCreaGruppo));
        getWebDriverWait(30).withMessage("Non si visualizza correttamente la lista della tabella").until(ExpectedConditions.visibilityOf(tableList));

    }

    public void clickButtonCreaGruppo() {
        WebElement buttonCreaGruppo = driver.findElement(By.xpath("//button[contains(text(), 'Crea gruppo')]"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone crea gruppo e non è cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(buttonCreaGruppo),
                ExpectedConditions.elementToBeClickable(buttonCreaGruppo)));
        buttonCreaGruppo.click();
    }

    public void waitLoadCreaGruppoPage() {

        WebElement pageTitle = driver.findElement(By.xpath("//h4[contains(text(), 'Crea un nuovo gruppo')]"));
        List<WebElement> buttonIndietro = driver.findElements(By.xpath("//button[contains(text(), 'Indietro')]"));
        WebElement navigationBar = driver.findElement(By.xpath("//nav[@aria-label='breadcrumb']"));
        WebElement pageSubTitle = driver.findElement(By.xpath("//p[contains(text(), 'Inserisci il nome, la descrizione del gruppo e gli utenti che vuoi associarvi.')]"));
        webTool.waitTime(10);
        inputNomeGruppo = driver.findElement(By.id("name"));
        inputDescrizioneGruppo = driver.findElement(By.id("description"));
        selectSelezioneProdotto = driver.findElement(By.id("product-select"));
        selectSelezioneMembri = driver.findElement(By.id("members-select"));
        buttonConferma = driver.findElement(By.xpath("//button[contains(text(), 'Conferma')]"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo della pagina").until(ExpectedConditions.visibilityOf(pageTitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone indietro ad inizio pagina").until(ExpectedConditions.visibilityOf(buttonIndietro.get(0)));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la barra di navigazione").until(ExpectedConditions.visibilityOf(navigationBar));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo della pagina").until(ExpectedConditions.visibilityOf(pageSubTitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input del nome del gruppo").until(ExpectedConditions.visibilityOf(inputNomeGruppo));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input della descrizione del gruppo").until(ExpectedConditions.visibilityOf(inputDescrizioneGruppo));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la select per la selezione del prodotto").until(ExpectedConditions.visibilityOf(selectSelezioneProdotto));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la select per la selezione dei membri").until(ExpectedConditions.visibilityOf(selectSelezioneMembri));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone conferma").until(ExpectedConditions.visibilityOf(buttonConferma));
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
        webTool.waitTime(10);
        buttonConferma = driver.findElement(By.xpath("//button[contains(text(), 'Conferma')]"));
        getWebDriverWait(10).withMessage("Il bottone conferma non è abilitato").until(ExpectedConditions.elementToBeClickable(buttonConferma));
        log.info("Il bottone è abilitato e lo si clicca");
        buttonConferma.click();
    }

    public void waitLoadGruppoCreatoPage(String nomeGruppo) {
        WebElement pageTitle = driver.findElement(By.xpath("//h4[contains(text(), '" + nomeGruppo + "')]"));
        WebElement navigationBar = driver.findElement(By.xpath("//nav[@aria-label='breadcrumb']"));
        WebElement buttonElimina = driver.findElement(By.xpath("//button[contains(text(), 'Elimina')]"));
        WebElement buttonModifica = driver.findElement(By.xpath("//button[contains(text(), 'Modifica')]"));
        WebElement buttonSospendi = driver.findElement(By.xpath("//button[contains(text(), 'Sospendi')]"));
        WebElement buttonDuplica = driver.findElement(By.xpath("//button[contains(text(), 'Duplica')]"));
        WebElement descrizioneGruppo = driver.findElement(By.xpath("//div/p[@aria-label]"));
        WebElement creatoGruppo = driver.findElement(By.xpath("//div/p[text()='Creato da - in data']"));
        WebElement modificatoGruppo = driver.findElement(By.xpath("//div/p[text()='Modificato da - in data']"));
        WebElement buttonAggiungiUtente = driver.findElement(By.xpath("//button[contains(text(), 'Aggiungi utente')]"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo della pagina").until(ExpectedConditions.visibilityOf(pageTitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la barra di navigazione").until(ExpectedConditions.visibilityOf(navigationBar));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone elimina").until(ExpectedConditions.visibilityOf(buttonElimina));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone modifica").until(ExpectedConditions.visibilityOf(buttonModifica));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone sospendi").until(ExpectedConditions.visibilityOf(buttonSospendi));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone duplica").until(ExpectedConditions.visibilityOf(buttonDuplica));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la descrizione del gruppo creato").until(ExpectedConditions.visibilityOf(descrizioneGruppo));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la data di creazione del gruppo").until(ExpectedConditions.visibilityOf(creatoGruppo));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la data di modifica del gruppo").until(ExpectedConditions.visibilityOf(modificatoGruppo));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone aggiungi utente").until(ExpectedConditions.visibilityOf(buttonAggiungiUtente));
    }

    public void waitLoadGruppoSospesoPage(String nomeGruppo) {
        WebElement pageTitle = driver.findElement(By.xpath("//h4[contains(text(), '" + nomeGruppo + "')]"));
        WebElement navigationBar = driver.findElement(By.xpath("//nav[@aria-label='breadcrumb']"));
        WebElement buttonElimina = driver.findElement(By.xpath("//button[contains(text(), 'Elimina')]"));
        WebElement buttonRiattiva = driver.findElement(By.xpath("//button[contains(text(), 'Riattiva')]"));
        WebElement descrizioneGruppo = driver.findElement(By.xpath("//div/p[@aria-label]"));
        WebElement creatoGruppo = driver.findElement(By.xpath("//div/p[text()='Creato da - in data']"));
        WebElement modificatoGruppo = driver.findElement(By.xpath("//div/p[text()='Modificato da - in data']"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo della pagina").until(ExpectedConditions.visibilityOf(pageTitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la barra di navigazione").until(ExpectedConditions.visibilityOf(navigationBar));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone elimina").until(ExpectedConditions.visibilityOf(buttonElimina));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone riattiva").until(ExpectedConditions.visibilityOf(buttonRiattiva));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la descrizione del gruppo creato").until(ExpectedConditions.visibilityOf(descrizioneGruppo));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la data di creazione del gruppo").until(ExpectedConditions.visibilityOf(creatoGruppo));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la data di modifica del gruppo").until(ExpectedConditions.visibilityOf(modificatoGruppo));
    }

    public void clickGruppiButtonSchedaGruppi() {
        WebElement buttonGruppi = driver.findElement(By.xpath("//li[.//span[contains(text(), 'Gruppi')]]"));
        getWebDriverWait(10).withMessage("Il bottone 'Gruppi' nella sidebar non si visualizza e non è cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(buttonGruppi),
                ExpectedConditions.elementToBeClickable(buttonGruppi)
        ));
        buttonGruppi.click();
    }

    public void clickBottonePaginaDettaglioGruppo(String azioneSuGruppi) {
        webTool.waitTime(10);
        WebElement actionButtonDettaglioGruppo = driver.findElement(By.xpath("//button[contains(text(), '" + azioneSuGruppi + "')]"));
        getWebDriverWait(10).withMessage("Il bottone " + azioneSuGruppi + " non è cliccabile").until(ExpectedConditions.elementToBeClickable(actionButtonDettaglioGruppo));
        actionButtonDettaglioGruppo.click();
    }

    public void clickBottonePopUpPaginaDettaglioGruppo(String azioneSuGruppi) {
        webTool.waitTime(10);
        List<WebElement> actionButtonDettaglioGruppoList = driver.findElements(By.xpath("//button[contains(text(), '" + azioneSuGruppi + "')]"));
        getWebDriverWait(10).withMessage("Il bottone " + azioneSuGruppi + " non è cliccabile").until(ExpectedConditions.elementToBeClickable(actionButtonDettaglioGruppoList.get(1)));
        actionButtonDettaglioGruppoList.get(1).click();
    }

    public void checkCampiModificabili() {
        webTool.waitTime(10);
        WebElement inputSelectProdotti = driver.findElement(By.xpath("//input[@value='SEND - Notifiche Digitali']"));
        inputNomeGruppo = driver.findElement(By.id("name"));
        inputDescrizioneGruppo = driver.findElement(By.id("description"));
        selectSelezioneMembri = driver.findElement(By.id("members-select"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input del nome del gruppo").until(ExpectedConditions.visibilityOf(inputNomeGruppo));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input della descrizione del gruppo").until(ExpectedConditions.visibilityOf(inputDescrizioneGruppo));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input per la selezione dei membri del gruppo").until(ExpectedConditions.visibilityOf(selectSelezioneMembri));
        getWebDriverWait(10).withMessage("Non si legge correttamente la proprietà dell'input per la selezione dei prodotti del gruppo").until(ExpectedConditions.attributeToBe(inputSelectProdotti, "disabled", "true"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input per la selezione dei prodotti del gruppo").until(ExpectedConditions.invisibilityOf(inputSelectProdotti));
    }

    public void siModificaUnCampoDelGruppo(String campo, String modifica) {
        try {
            Thread.sleep(2000);
            if (campo.equalsIgnoreCase("descrizione")) {
                inputDescrizioneGruppo = driver.findElement(By.id("description"));
                inputDescrizioneGruppo.click();
                js().executeScript("arguments[0].value = '';", inputDescrizioneGruppo);
                inputDescrizioneGruppo.sendKeys(modifica);
            } else {
                inputNomeGruppo = driver.findElement(By.id("name"));
                inputNomeGruppo.click();
                js().executeScript("arguments[0].value = '';", inputNomeGruppo);
                inputNomeGruppo.sendKeys(modifica);
            }
        } catch (InterruptedException e) {
            log.error("È fallita l'attesa prima della modifica del campo con errore: {}", e.getMessage());
            Assertions.fail("È fallita l'attesa prima della modifica del campo con errore: " + e.getMessage());
        }
    }

    public void checkPopUpConfermaModifica(String testoPopUp) {
        WebElement popUpConferma = driver.findElement(By.xpath("//div[@role='alert']//p[text()='" + testoPopUp + "']"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il popup di conferma").until(ExpectedConditions.visibilityOf(popUpConferma));
    }

    public void checkModificheSalvate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        WebElement descrizioneModificata = driver.findElement(By.xpath("//div[p[@aria-label and contains(text(), 'Nuova descrizione')]]"));
        List<WebElement> dataCreazioneEModifica = driver.findElements(By.xpath("//div[contains(@class, 'MuiGrid-root')]//p[text()='" + formattedDate + "']"));
        getWebDriverWait(10).withMessage("La descrizione modificata non viene visualizzata e non è corretta").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElements(descrizioneModificata),
                ExpectedConditions.attributeToBe(descrizioneModificata, "textContent", "Nuova descrizione")
        ));
        getWebDriverWait(10).withMessage("La data della modifica non è visualizzata e non è corretta").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(dataCreazioneEModifica.get(1)),
                ExpectedConditions.attributeToBe(dataCreazioneEModifica.get(1), "textContent", formattedDate)
        ));
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
        WebElement voceSospeso = driver.findElement(By.xpath("//div[@aria-label='Suspended']//span[contains(text(), 'Sospeso')]"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la voce sospeso nella descrizione del gruppo").until(ExpectedConditions.visibilityOf(voceSospeso));
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

        WebElement pageTitle = driver.findElement(By.xpath("//h4[contains(text(), 'Duplica gruppo')]"));
        List<WebElement> buttonIndietro = driver.findElements(By.xpath("//button[contains(text(), 'Indietro')]"));
        WebElement navigationBar = driver.findElement(By.xpath("//nav[@aria-label='breadcrumb']"));
        WebElement pageSubTitle = driver.findElement(By.xpath("//p[contains(text(), 'Duplica il gruppo e modifica i dati')]"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo della pagina").until(ExpectedConditions.visibilityOf(pageTitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone indietro ad inizio pagina").until(ExpectedConditions.visibilityOf(buttonIndietro.get(0)));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la barra di navigazione").until(ExpectedConditions.visibilityOf(navigationBar));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo della pagina").until(ExpectedConditions.visibilityOf(pageSubTitle));
        inputNomeGruppo = driver.findElement(By.id("name"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input del nome del gruppo e il value è errato").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(inputNomeGruppo),
                ExpectedConditions.attributeContains(inputNomeGruppo, "value", "Copia di Gruppo Test")
        ));

        inputDescrizioneGruppo = driver.findElement(By.id("description"));
        selectSelezioneMembri = driver.findElement(By.id("members-select"));
        selectSelezioneProdotto = driver.findElement(By.id("product-select"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input della descrizione del gruppo").until(ExpectedConditions.visibilityOf(inputDescrizioneGruppo));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la select per la selezione del prodotto").until(ExpectedConditions.visibilityOf(selectSelezioneProdotto));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente la select per la selezione dei membri").until(ExpectedConditions.visibilityOf(selectSelezioneMembri));
        buttonConferma = driver.findElement(By.xpath("//button[contains(text(), 'Conferma')]"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone conferma").until(ExpectedConditions.visibilityOf(buttonConferma));
    }

    public void checkPopUpEliminazioneGruppo() {
        WebElement popUpTitle = driver.findElement(By.xpath("//h6[contains(text(), 'Elimina gruppo')]"));
//        WebElement popUpSubtitle = driver.findElement(By.xpath("//p[contains(text(), 'Vuoi eliminare il gruppo')]"));
        WebElement buttonAnnulla = driver.findElement(By.xpath("//button[contains(text(), 'Annulla')]"));
        WebElement buttonElimina = driver.findElement(By.xpath("//button[contains(text(), 'Elimina')]"));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo del pop up").until(ExpectedConditions.visibilityOf(popUpTitle));
//        getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo del pop up").until(ExpectedConditions.visibilityOf(popUpSubtitle));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone annulla del pop up").until(ExpectedConditions.visibilityOf(buttonAnnulla));
        getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone elimina del pop up").until(ExpectedConditions.visibilityOf(buttonElimina));
    }

    public void eliminaGruppoDaPaginaIniziale(String azioneGruppo, String nomeGruppo) {
        WebElement tableRowGruppo = driver.findElement(By.xpath("//div[contains(.//p, '" + nomeGruppo + "') and @data-field='name']"));
        getWebDriverWait(10).withMessage("Non è stata trovata la riga del gruppo con il nome indicato nello step del test").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(tableRowGruppo),
                ExpectedConditions.elementToBeClickable(tableRowGruppo)
        ));
        tableRowGruppo.click();
        clickBottonePaginaDettaglioGruppo(azioneGruppo);
        clickBottonePopUpPaginaDettaglioGruppo(azioneGruppo);
    }
}