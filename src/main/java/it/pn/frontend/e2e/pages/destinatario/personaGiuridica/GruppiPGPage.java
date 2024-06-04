package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
public class GruppiPGPage extends BasePage {

    private final AccediAreaRiservataPGPage accediAreaRiservataPGPage = new AccediAreaRiservataPGPage(this.driver);
    private final Actions actions = new Actions(this.driver);

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

    public GruppiPGPage(WebDriver driver) {
        super(driver);
    }

    public void clickGruppiButton() {
        this.js().executeScript("arguments[0].scrollIntoView(true);", gruppiButton);
        getWebDriverWait(10).withMessage("Il bottone gruppi non è visibile").until(ExpectedConditions.visibilityOf(gruppiButton));
        gruppiButton.click();
        WebTool.switchToOtherTab();
    }

    public void loginGruppi(String nome, String pwd) {
        accediAreaRiservataPGPage.waitLoadAccediAreaRiservataPGPage();
        accediAreaRiservataPGPage.clickSpidButton();
        ScegliSpidPGPage scegliSpidPGPage = new ScegliSpidPGPage(this.driver);
        scegliSpidPGPage.clickTestButton();
        LoginPGPagoPAPage loginPGPagoPAPage = new LoginPGPagoPAPage(this.driver);
        loginPGPagoPAPage.waitLoadLoginPGPage();
        loginPGPagoPAPage.insertUsername(nome);
        loginPGPagoPAPage.insertPassword(pwd);
        loginPGPagoPAPage.clickInviaButton();
        AutorizzaInvioDatiPGPage autorizzaInvioDatiPGPage = new AutorizzaInvioDatiPGPage(this.driver);
        autorizzaInvioDatiPGPage.waitLoadAutorizzaInvioDatiPGPage();
        autorizzaInvioDatiPGPage.clickInviaButton();
    }

    public void waitLoadGruppiPage() {
        try {
            By pageTitle = By.xpath("//h4[contains(text(), 'Gruppi')]");
            By pageSubtitle = By.xpath("//p[contains(text(), 'I gruppi sono un insieme di utenti, per esempio appartenenti allo stesso ufficio o dipartimento, a cui si affida la gestione delle notifiche. Qui puoi gestire i gruppi dell’impresa e crearne di nuovi.')]");
            By buttonCreaGruppo = By.xpath("//button[contains(text(), 'Crea gruppo')]");
            By tableList = By.xpath("//div[@role='tablist']");
            //By card1 = By.xpath("//div[contains(div/p/text(), 'SEND - Notifiche Digitali')]");


            getWebDriverWait(30).withMessage("Non si visualizza correttamente il titolo della pagina").until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            getWebDriverWait(30).withMessage("Non si visualizza correttamente il sottotitolo della pagina").until(ExpectedConditions.visibilityOfElementLocated(pageSubtitle));
            getWebDriverWait(30).withMessage("Non si visualizza correttamente il bottone crea gruppo").until(ExpectedConditions.visibilityOfElementLocated(buttonCreaGruppo));
            getWebDriverWait(30).withMessage("Non si visualizza correttamente la lista della tabella").until(ExpectedConditions.visibilityOfElementLocated(tableList));

        } catch (TimeoutException e) {
            log.error("La pagine non viene caricata correttamente con errore: " + e.getMessage());
            Assert.fail("La pagine non viene caricata correttamente con errore: " + e.getMessage());
        }
    }

    public void clickButtonCreaGruppo() {
        try {
            By buttonCreaGruppo = By.xpath("//button[contains(text(), 'Crea gruppo')]");
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone crea gruppo e non è cliccabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(buttonCreaGruppo),
                    ExpectedConditions.elementToBeClickable(buttonCreaGruppo)));
            element(buttonCreaGruppo).click();
        } catch (TimeoutException e) {
            log.error("Il bottone crea gruppo non viene visualizzato correttamente e non è cliccabile con errore: " + e.getMessage());
            Assert.fail("Il bottone crea gruppo non viene visualizzato correttamente e non è cliccabile con errore: " + e.getMessage());
        }
    }

    public void waitLoadCreaGruppoPage() {
        try {
            By pageTitle = By.xpath("//h4[contains(text(), 'Crea un nuovo gruppo')]");
            List<WebElement> buttonIndietro = driver.findElements(By.xpath("//button[contains(text(), 'Indietro')]"));
            By navigationBar = By.xpath("//nav[@aria-label='breadcrumb']");
            By pageSubTitle = By.xpath("//p[contains(text(), 'Inserisci il nome, la descrizione del gruppo e gli utenti che vuoi associarvi.')]");
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo della pagina").until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone indietro ad inizio pagina").until(ExpectedConditions.visibilityOf(buttonIndietro.get(0)));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la barra di navigazione").until(ExpectedConditions.visibilityOfElementLocated(navigationBar));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo della pagina").until(ExpectedConditions.visibilityOfElementLocated(pageSubTitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input del nome del gruppo").until(ExpectedConditions.visibilityOf(inputNomeGruppo));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input della descrizione del gruppo").until(ExpectedConditions.visibilityOf(inputDescrizioneGruppo));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la select per la selezione del prodotto").until(ExpectedConditions.visibilityOf(selectSelezioneProdotto));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la select per la selezione dei membri").until(ExpectedConditions.visibilityOf(selectSelezioneMembri));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone indietro a fine pagina").until(ExpectedConditions.visibilityOf(buttonIndietro.get(1)));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone conferma").until(ExpectedConditions.visibilityOf(buttonConferma));
        } catch (TimeoutException e) {
            log.error("La pagina per la creazione di un nuovo gruppo non viene caricata correttamente con errore: " + e.getMessage());
            Assert.fail("La pagina per la creazione di un nuovo gruppo non viene caricata correttamente con errore: " + e.getMessage());
        }
    }

    public void creazioneNuovoGruppo(Map<String, String> datiGruppo) {
        log.info("Inserimento nome del gruppo");
        String timestamp = datiGruppo.get("nome") + Instant.now().toString();
        scrollToElementClickAndInsertText(inputNomeGruppo, timestamp);
        log.info("Inserimento descrizione del gruppo");
        scrollToElementClickAndInsertText(inputDescrizioneGruppo, datiGruppo.get("descrizione"));
        vaiInFondoAllaPagina();
        log.info("Click sulla select per la selezione dei membri");
        selectSelezioneMembri.click();
        log.info("Click sul membro Dante Alighieri");
        By checkBoxDanteAlighieri = By.xpath("//li[@data-value='Dante']/span");
        element(checkBoxDanteAlighieri).click();
        actions.moveToElement(selectSelezioneMembri).click().perform();
    }

    public void checkButtonConfermaAndClick() {
        try {
            getWebDriverWait(10).withMessage("Il bottone conferma non è abilitato").until(ExpectedConditions.elementToBeClickable(buttonConferma));
            log.info("Il bottone è abilitato e lo si clicca");
            buttonConferma.click();
        } catch (TimeoutException e) {
            log.error("Il bottone conferma non è abilitato con errore: " + e.getMessage());
            Assert.fail("Il bottone conferma non è abilitato con errore: " + e.getMessage());
        }
    }

    public void waitLoadGruppoCreatoPage(String nomeGruppo) {
        try {
            By pageTitle = By.xpath("//h4[contains(text(), '" + nomeGruppo + "')]");
            By buttonIndietro = By.xpath("//button[contains(text(), 'Indietro')]");
            By navigationBar = By.xpath("//nav[@aria-label='breadcrumb']");
            By buttonElimina = By.xpath("//button[contains(text(), 'Elimina')]");
            By buttonModifica = By.xpath("//button[contains(text(), 'Modifica')]");
            By buttonSospendi = By.xpath("//button[contains(text(), 'Sospendi')]");
            By buttonDuplica = By.xpath("//button[contains(text(), 'Duplica')]");
            By descrizioneGruppo = By.xpath("//div/p[text()='descrizione']");
            By creatoGruppo = By.xpath("//div/p[text()='Creato da - in data']");
            By modificatoGruppo = By.xpath("//div/p[text()='Modificato da - in data']");
            By buttonAggiungiUtente = By.xpath("//button[contains(text(), 'Aggiungi utente')]");
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo della pagina").until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone indietro").until(ExpectedConditions.visibilityOfElementLocated(buttonIndietro));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la barra di navigazione").until(ExpectedConditions.visibilityOfElementLocated(navigationBar));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone elimina").until(ExpectedConditions.visibilityOfElementLocated(buttonElimina));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone modifica").until(ExpectedConditions.visibilityOfElementLocated(buttonModifica));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone sospendi").until(ExpectedConditions.visibilityOfElementLocated(buttonSospendi));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone duplica").until(ExpectedConditions.visibilityOfElementLocated(buttonDuplica));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la descrizione del gruppo creato").until(ExpectedConditions.visibilityOfElementLocated(descrizioneGruppo));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la data di creazione del gruppo").until(ExpectedConditions.visibilityOfElementLocated(creatoGruppo));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la data di modifica del gruppo").until(ExpectedConditions.visibilityOfElementLocated(modificatoGruppo));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone aggiungi utente").until(ExpectedConditions.visibilityOfElementLocated(buttonAggiungiUtente));
        } catch (TimeoutException e) {
            log.error("La pagina di riepilogo del gruppo creato non viene visualizzata correttamente con errore: " + e.getMessage());
            Assert.fail("La pagina di riepilogo del gruppo creato non viene visualizzata correttamente con errore: " + e.getMessage());
        }
    }

    public void waitLoadGruppoSospesoPage(String nomeGruppo) {
        try {
            By pageTitle = By.xpath("//h4[contains(text(), '" + nomeGruppo + "')]");
            By buttonIndietro = By.xpath("//button[contains(text(), 'Indietro')]");
            By navigationBar = By.xpath("//nav[@aria-label='breadcrumb']");
            By buttonElimina = By.xpath("//button[contains(text(), 'Elimina')]");
            By buttonRiattiva = By.xpath("//button[contains(text(), 'Riattiva')]");
            By descrizioneGruppo = By.xpath("//div/p[text()='descrizione']");
            By creatoGruppo = By.xpath("//div/p[text()='Creato da - in data']");
            By modificatoGruppo = By.xpath("//div/p[text()='Modificato da - in data']");
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo della pagina").until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone indietro").until(ExpectedConditions.visibilityOfElementLocated(buttonIndietro));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la barra di navigazione").until(ExpectedConditions.visibilityOfElementLocated(navigationBar));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone elimina").until(ExpectedConditions.visibilityOfElementLocated(buttonElimina));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone riattiva").until(ExpectedConditions.visibilityOfElementLocated(buttonRiattiva));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la descrizione del gruppo creato").until(ExpectedConditions.visibilityOfElementLocated(descrizioneGruppo));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la data di creazione del gruppo").until(ExpectedConditions.visibilityOfElementLocated(creatoGruppo));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la data di modifica del gruppo").until(ExpectedConditions.visibilityOfElementLocated(modificatoGruppo));
        } catch (TimeoutException e) {
            log.error("La pagina di riepilogo del gruppo sospeso non viene visualizzata correttamente con errore: " + e.getMessage());
            Assert.fail("La pagina di riepilogo del gruppo sospeso non viene visualizzata correttamente con errore: " + e.getMessage());
        }
    }

    public void clickGruppiButtonSchedaGruppi() {
        try {
            By buttonGruppi = By.xpath("//li[.//span[contains(text(), 'Gruppi')]]");
            getWebDriverWait(10).withMessage("Il bottone 'Gruppi' nella sidebar non si visualizza e non è cliccabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(buttonGruppi),
                    ExpectedConditions.elementToBeClickable(buttonGruppi)
            ));
            element(buttonGruppi).click();
        } catch (TimeoutException e) {
            log.error("Non si riesce a tornare sulla pagina principale nella scheda dei gruppi con errore: " + e.getMessage());
            Assert.fail("Non si riesce a tornare sulla pagina principale nella scheda dei gruppi con errore: " + e.getMessage());
        }
    }

    public void clickBottonePaginaDettaglioGruppo(String azioneSuGruppi) {
        try {
            By actionButtonDettaglioGruppo = By.xpath("//button[contains(text(), '" + azioneSuGruppi + "')]");
            getWebDriverWait(10).withMessage("Il bottone " + azioneSuGruppi + " non è cliccabile").until(ExpectedConditions.elementToBeClickable(actionButtonDettaglioGruppo));
            element(actionButtonDettaglioGruppo).click();
        } catch (TimeoutException e) {
            log.error("Non è possibile cliccare il bottone " + azioneSuGruppi + " con errore: " + e.getMessage());
            Assert.fail("Non è possibile cliccare il bottone " + azioneSuGruppi + " con errore: " + e.getMessage());
        }
    }

    public void clickBottonePopUpPaginaDettaglioGruppo(String azioneSuGruppi) {
        try {
            List<WebElement> actionButtonDettaglioGruppoList = driver.findElements(By.xpath("//button[contains(text(), '" + azioneSuGruppi + "')]"));
            getWebDriverWait(10).withMessage("Il bottone " + azioneSuGruppi + " non è cliccabile").until(ExpectedConditions.elementToBeClickable(actionButtonDettaglioGruppoList.get(1)));
            actionButtonDettaglioGruppoList.get(1).click();
        } catch (TimeoutException e) {
            log.error("Non è possibile cliccare il bottone " + azioneSuGruppi + " con errore: " + e.getMessage());
            Assert.fail("Non è possibile cliccare il bottone " + azioneSuGruppi + " con errore: " + e.getMessage());
        }
    }

    public void checkCampiModificabili() {
        try {
            By inputSelectProdotti = By.xpath("//input[@value='SEND - Notifiche Digitali']");
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input del nome del gruppo").until(ExpectedConditions.visibilityOf(inputNomeGruppo));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input della descrizione del gruppo").until(ExpectedConditions.visibilityOf(inputDescrizioneGruppo));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input per la selezione dei membri del gruppo").until(ExpectedConditions.visibilityOf(selectSelezioneMembri));
            getWebDriverWait(10).withMessage("Non si legge correttamente la proprietà dell'input per la selezione dei prodotti del gruppo").until(ExpectedConditions.attributeToBe(inputSelectProdotti, "disabled", "true"));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input per la selezione dei prodotti del gruppo").until(ExpectedConditions.invisibilityOfElementLocated(inputSelectProdotti));
        } catch (TimeoutException e) {
            log.error("I campi non sono modificabili con errore: " + e.getMessage());
            Assert.fail("I campi non sono modificabili con errore: " + e.getMessage());
        }
    }

    public void siModificaUnCampoDelGruppo(String campo, String modifica) {
        try {
            Thread.sleep(2000);
            if (campo.equalsIgnoreCase("descrizione")) {
                inputDescrizioneGruppo.click();
                js().executeScript("arguments[0].value = '';", inputDescrizioneGruppo);
                inputDescrizioneGruppo.sendKeys(modifica);
            } else {
                inputNomeGruppo.click();
                js().executeScript("arguments[0].value = '';", inputNomeGruppo);
                inputNomeGruppo.sendKeys(modifica);
            }
        } catch (TimeoutException e) {
            log.error("Non si riesce a modificare il campo " + campo + " con errore: " + e.getMessage());
            Assert.fail("Non si riesce a modificare il campo " + campo + " con errore: " + e.getMessage());
        } catch (InterruptedException e) {
            log.error("È fallita l'attesa prima della modifica del campo con errore: " + e.getMessage());
            Assert.fail("È fallita l'attesa prima della modifica del campo con errore: " + e.getMessage());
        }
    }

    public void checkPopUpConfermaModifica(String testoPopUp) {
        try {
            By popUpConferma = By.xpath("//div[@role='alert']//p[text()='" + testoPopUp + "']");
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il popup di conferma").until(ExpectedConditions.visibilityOfElementLocated(popUpConferma));
        } catch (TimeoutException e) {
            log.error("Non si visualizza il popup di conferma modifica del gruppo con errore: " + e.getMessage());
            Assert.fail("Non si visualizza il popup di conferma modifica del gruppo con errore: " + e.getMessage());
        }
    }

    public void checkModificheSalvate() {
        try {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = currentDate.format(formatter);
            By descrizioneModificata = By.xpath("//div[p[@aria-label and contains(text(), 'Nuova descrizione')]]");
            List<WebElement> dataCreazioneEModifica = driver.findElements(By.xpath("//div[contains(@class, 'MuiGrid-root')]//p[text()='" + formattedDate + "']"));
            getWebDriverWait(10).withMessage("La descrizione modificata non viene visualizzata e non è corretta").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(descrizioneModificata),
                    ExpectedConditions.attributeToBe(descrizioneModificata, "textContent", "Nuova descrizione")
            ));
            getWebDriverWait(10).withMessage("La data della modifica non è visualizzata e non è corretta").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(dataCreazioneEModifica.get(1)),
                    ExpectedConditions.attributeToBe(dataCreazioneEModifica.get(1), "textContent", formattedDate)
            ));
        } catch (TimeoutException e) {
            log.error("Non si visualizzano le modifiche apportate al gruppo con errore: " + e.getMessage());
            Assert.fail("Non si visualizzano le modifiche apportate al gruppo con errore: " + e.getMessage());
        }
    }

    public void checkPopUpSospensioneGruppo() {
        try {
            By popUpTitle = By.xpath("//p[contains(text(), 'Sospendi gruppo')]");
            By popUpSubtitle = By.xpath("//p[contains(text(), 'Vuoi sospendere il gruppo')]");
            By buttonAnnulla = By.xpath("//button[contains(text(), 'Annulla')]");
            By buttonSospendi = By.xpath("//button[contains(text(), 'Sospendi')]");
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo del pop up").until(ExpectedConditions.visibilityOfElementLocated(popUpTitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo del pop up").until(ExpectedConditions.visibilityOfElementLocated(popUpSubtitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone annulla del pop up").until(ExpectedConditions.visibilityOfElementLocated(buttonAnnulla));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone sospendi del pop up").until(ExpectedConditions.visibilityOfElementLocated(buttonSospendi));
        } catch (TimeoutException e) {
            log.error("Il popup di sospensione del gruppo non viene mostrato correttamente con errore: " + e.getMessage());
            Assert.fail("Il popup di sospensione del gruppo non viene mostrato correttamente con errore: " + e.getMessage());
        }
    }

    public void checkVoceSospeso() {
        try {
            By voceSospeso = By.xpath("//div[@aria-label='Suspended']//span[contains(text(), 'Sospeso')]");
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la voce sospeso nella descrizione del gruppo").until(ExpectedConditions.visibilityOfElementLocated(voceSospeso));
        } catch (TimeoutException e) {
            log.error("La voce sospeso nella descrizione del gruppo non viene visualizzata correttamente con errore: " + e.getMessage());
            Assert.fail("La voce sospeso nella descrizione del gruppo non viene visualizzata correttamente con errore: " + e.getMessage());
        }
    }

    public void checkPopUpRiattivazioneGruppo() {
        try {
            By popUpTitle = By.xpath("//p[contains(text(), 'Riattiva gruppo')]");
            By popUpSubtitle = By.xpath("//p[contains(text(), 'Vuoi riattivare il gruppo')]");
            By buttonAnnulla = By.xpath("//button[contains(text(), 'Annulla')]");
            By buttonRiattiva = By.xpath("//button[contains(text(), 'Riattiva')]");
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo del pop up").until(ExpectedConditions.visibilityOfElementLocated(popUpTitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo del pop up").until(ExpectedConditions.visibilityOfElementLocated(popUpSubtitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone annulla del pop up").until(ExpectedConditions.visibilityOfElementLocated(buttonAnnulla));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone riattiva del pop up").until(ExpectedConditions.visibilityOfElementLocated(buttonRiattiva));
        } catch (TimeoutException e) {
            log.error("Il popup di riattivazione del gruppo non viene mostrato correttamente con errore: " + e.getMessage());
            Assert.fail("Il popup di riattivazione del gruppo non viene mostrato correttamente con errore: " + e.getMessage());
        }
    }

    public void checkPopUpDuplicazioneGruppo() {
        try {
            By popUpTitle = By.xpath("//p[contains(text(), 'Duplica gruppo')]");
            By popUpSubtitle = By.xpath("//p[contains(text(), 'Vuoi duplicare il gruppo')]");
            By buttonAnnulla = By.xpath("//button[contains(text(), 'Annulla')]");
            By buttonDuplica = By.xpath("//button[contains(text(), 'Duplica')]");
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo del pop up").until(ExpectedConditions.visibilityOfElementLocated(popUpTitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo del pop up").until(ExpectedConditions.visibilityOfElementLocated(popUpSubtitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone annulla del pop up").until(ExpectedConditions.visibilityOfElementLocated(buttonAnnulla));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone duplica del pop up").until(ExpectedConditions.visibilityOfElementLocated(buttonDuplica));
        } catch (TimeoutException e) {
            log.error("Il popup di duplicazione del gruppo non viene mostrato correttamente con errore: " + e.getMessage());
            Assert.fail("Il popup di duplicazione del gruppo non viene mostrato correttamente con errore: " + e.getMessage());
        }
    }

    public void waitLoadDuplicazionePage() {
        try {
            By pageTitle = By.xpath("//h4[contains(text(), 'Duplica gruppo')]");
            List<WebElement> buttonIndietro = driver.findElements(By.xpath("//button[contains(text(), 'Indietro')]"));
            By navigationBar = By.xpath("//nav[@aria-label='breadcrumb']");
            By pageSubTitle = By.xpath("//p[contains(text(), 'Duplica il gruppo e modifica i dati')]");
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo della pagina").until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone indietro ad inizio pagina").until(ExpectedConditions.visibilityOf(buttonIndietro.get(0)));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la barra di navigazione").until(ExpectedConditions.visibilityOfElementLocated(navigationBar));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo della pagina").until(ExpectedConditions.visibilityOfElementLocated(pageSubTitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input del nome del gruppo e il value è errato").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(inputNomeGruppo),
                    ExpectedConditions.attributeContains(inputNomeGruppo, "value", "Copia di Gruppo Test")
            ));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'input della descrizione del gruppo").until(ExpectedConditions.visibilityOf(inputDescrizioneGruppo));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la select per la selezione del prodotto").until(ExpectedConditions.visibilityOf(selectSelezioneProdotto));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente la select per la selezione dei membri").until(ExpectedConditions.visibilityOf(selectSelezioneMembri));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone indietro a fine pagina").until(ExpectedConditions.visibilityOf(buttonIndietro.get(1)));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone conferma").until(ExpectedConditions.visibilityOf(buttonConferma));
        } catch (TimeoutException e) {
            log.error("La pagina di duplicazione di un gruppo non viene caricata correttamente con errore: " + e.getMessage());
            Assert.fail("La pagina di duplicazione di un gruppo non viene caricata correttamente con errore: " + e.getMessage());
        }
    }

    public void checkPopUpEliminazioneGruppo() {
        try {
            By popUpTitle = By.xpath("//p[contains(text(), 'Elimina gruppo')]");
            By popUpSubtitle = By.xpath("//p[contains(text(), 'Vuoi eliminare il gruppo ')]");
            By buttonAnnulla = By.xpath("//button[contains(text(), 'Annulla')]");
            By buttonElimina = By.xpath("//button[contains(text(), 'Elimina')]");
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il titolo del pop up").until(ExpectedConditions.visibilityOfElementLocated(popUpTitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il sottotitolo del pop up").until(ExpectedConditions.visibilityOfElementLocated(popUpSubtitle));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone annulla del pop up").until(ExpectedConditions.visibilityOfElementLocated(buttonAnnulla));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il bottone elimina del pop up").until(ExpectedConditions.visibilityOfElementLocated(buttonElimina));
        } catch (TimeoutException e) {
            log.error("Il popup di eliminazione del gruppo non viene mostrato correttamente con errore: " + e.getMessage());
            Assert.fail("Il popup di eliminazione del gruppo non viene mostrato correttamente con errore: " + e.getMessage());
        }
    }

    public void eliminaGruppoDaPaginaIniziale(String azioneGruppo, String nomeGruppo) {
        try{
            By tableRowGruppo = By.xpath("//div[contains(.//p, '" + nomeGruppo + "') and @data-field='name']");
            getWebDriverWait(10).withMessage("Non è stata trovata la riga del gruppo con il nome indicato nello step del test").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(tableRowGruppo),
                    ExpectedConditions.elementToBeClickable(tableRowGruppo)
            ));
            element(tableRowGruppo).click();
            clickBottonePaginaDettaglioGruppo(azioneGruppo);
            clickBottonePopUpPaginaDettaglioGruppo(azioneGruppo);
        } catch (TimeoutException e){
            log.error("Non è stato possibile cancellare il gruppo creato all'inizio del test con errore: " + e.getMessage());
            Assert.fail("Non è stato possibile cancellare il gruppo creato all'inizio del test con errore: " + e.getMessage());
        }
    }
}