package it.pn.frontend.e2e.stepDefinitions.common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.HelpdeskPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.model.enums.Disservice;
import it.pn.frontend.e2e.model.enums.Status;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HelpdeskTest {
    private final WebDriver driver = Hooks.driver;
    private final DataPopulation dataPopulation = new DataPopulation();
    private final Logger logger = LoggerFactory.getLogger("HelpdeskAppTest");
    private HelpdeskPage helpdeskPage = new HelpdeskPage(this.driver);
    private Map<String, Object> datiTestHelpdesk = new HashMap<>();
    private Map<String, Object> datiPersonaFisica = new HashMap<>();

    @Given("Login helpdesk con utente test {string}")
    public void loginHelpdeskConUtenteTest(String nameFile) {
        this.datiTestHelpdesk = this.dataPopulation.readDataPopulation(nameFile + ".yaml");
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> helpdeskPage.changePage(this.datiTestHelpdesk.get("url").toString());
            case "test", "uat" ->
                    helpdeskPage.changePage(this.datiTestHelpdesk.get("url").toString().replace("dev", variabileAmbiente));
            default ->
                    Assertions.fail("Non è stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
        helpdeskPage.checkForm();
        switch (variabileAmbiente) {
            case "dev" -> {
                helpdeskPage.insertUsername(this.datiTestHelpdesk.get("userDev").toString());
                helpdeskPage.insertPassword(this.datiTestHelpdesk.get("pwdDev").toString());
            }
            case "test" -> {
                helpdeskPage.insertUsername(this.datiTestHelpdesk.get("userTest").toString());
                helpdeskPage.insertPassword(this.datiTestHelpdesk.get("pwdTest").toString());
            }
            case "uat" -> {
                helpdeskPage.insertUsername(this.datiTestHelpdesk.get("userUat").toString());
                helpdeskPage.insertPassword(this.datiTestHelpdesk.get("pwdUat").toString());
            }
            default ->
                    Assertions.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
        helpdeskPage.clickInviaButton();
    }

    @And("Click su card monitoraggio piattaforma")
    public void clickSuCardMonitoraggioPiattaforma() {
        helpdeskPage.clickMonitoraggio();
    }

    @And("Si visualizza correttamente home Helpdesk")
    public void siVisualizzaCorrettamenteHomeHelpdesk() {
        helpdeskPage.checkHome();
    }

    @And("Si visualizza correttamente home monitoraggio")
    public void siVisualizzaCorrettamenteHomeMonitoraggio() {
        helpdeskPage.waitLoadServiceTable();
    }

    @And("Si crea il disservizio")
    public void siCreaIlDisservizio() {
        WebTool.waitTime(5);
        if (!helpdeskPage.checkIsCreatedDisservizio()) {
            helpdeskPage.handleDisservizio(Disservice.CREAZIONE_NOTIFICHE, Status.KO);
            WebTool.waitTime(5);
        }
    }

    @And("Si risolve il disservizio")
    public void siRisolveIlDisservizio() {
        WebTool.waitTime(5);
        if (helpdeskPage.checkIsCreatedDisservizio()) {
            helpdeskPage.handleDisservizio(Disservice.CREAZIONE_NOTIFICHE,Status.OK);
            WebTool.waitTime(5);
        }
    }

    @And("Si verifica la creazione del disservizio")
    public void siVerificaLaCreazioneDelDisservizio() {
        helpdeskPage.checkIsCreatedDisservizio();
    }

    @And("Si annulla un disservizio in corso")
    public void annullamentoDisservizio() {
        BackgroundTest backgroundTest = new BackgroundTest();
        logger.info("Torno sulla scheda di helpdesk");
        String sendHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(sendHandle)) {
                this.driver.switchTo().window(handle);
                break;
            }
        }
        backgroundTest.getHelpdeskMonitoraggioPiattaforma();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: " + e.getMessage());
            throw new RuntimeException(e);
        }
        if (helpdeskPage.checkServiceStatus(Disservice.CREAZIONE_NOTIFICHE)) {
            helpdeskPage.handleDisservizio(Disservice.CREAZIONE_NOTIFICHE, Status.OK);
        } else {
            logger.info("Lo stato del servizio: " + Disservice.CREAZIONE_NOTIFICHE + " è già in OK");
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: " + e.getMessage());
            throw new RuntimeException(e);
        }
        logger.info("Torno sulla piattaforma send per il logout");
        for (String handle : windowHandles) {
            if (handle.equals(sendHandle)) {
                this.driver.switchTo().window(handle);
                break;
            }
        }
    }

    @When("Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati")
    public void nellaHomeDiHelpdeskUtenteCliccaSuSezioneRicercaEdEstrazioneDati() {
        helpdeskPage.clickSezioneRicerca();
    }

    @And("visualizzazione corretta pagina ricerca ed estrazione dati")
    public void visualizzazioneCorrettaPaginaRicercaEdEstrazioneDati() {
        helpdeskPage.checkRicercaPage();
    }

    @And("viene inserito codice fiscale {string}")
    public void vieneInseritoCodiceFiscale(String nameFile) {
        this.datiPersonaFisica = this.dataPopulation.readDataPopulation(nameFile + ".yaml");
        helpdeskPage.insertCfAndRicercaOnPage(datiPersonaFisica.get("codiceFiscale").toString());
    }

    @And("viene inserito codice fiscale senza ricerca {string}")
    public void vieneInseritoCodiceFiscaleSenzaRicerca(String CF) {
        helpdeskPage.insertCF(CF);
    }

    @And("viene inserito codice IUN {string}")
    public void vieneInseritoIun(String iun) {
        helpdeskPage.insertIunAndRicercaOnPage(iun);
    }

    @And("viene inserito codice IUN senza ricerca {string}")
    public void vieneInseritoIunSenzaRicerca(String iun) {
        helpdeskPage.insertIun(iun);
    }


    @And("viene inserito numero ticket")
    public void vieneInseritoNumeroTicket() {
        helpdeskPage.insertNumeroTicket();
    }

    @And("viene inserito codice univoco {string}")
    public void vieneInseritoUID(String uid){
        helpdeskPage.insertUid(uid);
    }

    @And("Cliccare sul bottone ricerca")
    public void cliccareSulBottoneRicerca(){
        logger.info("Cliccare sul bottone ricerca");
        helpdeskPage.clickRicercaBottone();
    }

    @And ("Spuntare la casella Deanonimizzazione dati")
    public void spuntareDeanonimizzazioneDati(){
    logger.info("Spuntare la casella Deanonimizzazione dati");
    helpdeskPage.spuntareDeanonimizzazioneDati();

    }

    @And("viene inserito un range temporale maggiore di 3 mesi")
    public void inserisceRangeTemporaleMaggioreDi3Mesi() {
        logger.info("viene inserito un range temporale maggiore di 3 mesi");
        helpdeskPage.inserimentoArcoTemporale();
    }

    @And("viene visualizzato messaggio di errore data")
    public void vieneVisualizzatoMessaggioDiErroreData(){
        helpdeskPage.checkMessaggioDiErroreData();
    }

    @And("viene visualizzato messaggio di errore IUN")
    public void vieneVisualizzatoMessaggioDiErroreIUN(){
        helpdeskPage.checkMessaggioDiErroreIUN();
    }

    @And("viene visualizzato messaggio di errore CF")
    public void vieneVisualizzatoMessaggioDiErroreCF(){
        helpdeskPage.checkMessaggioDiErroreCF();
    }



    @Then("controllo messaggio di successo")
    public void controlloMessaggioSuccesso() {
        helpdeskPage.checkMessaggioSuccesso();
    }

    @Then("controllo link per scaricare zip e scarico file")
    public void controlloLinkPerScaricareZip() throws IOException, AWTException {
        helpdeskPage.checkZipLink();
    }

    @And("Inserisco la password ed estraggo il file zip")
    public void inseriscoPasswordEdEstraggoZip() throws IOException {
        logger.info("Inserisco la password ed estraggo il file zip");
        helpdeskPage.extractZip();
    }

    @And("Controllo sia presente documento {string}")
    public void controlloPresenteDocumento(String docName) throws IOException {
        logger.info("Controllo sia presente documento" + docName);
        Assertions.assertTrue (helpdeskPage.trovaDocumentoConTitolo(docName), "Documento " + docName + " non è trovato");
            logger.info("Documento " + docName + " è trovato");
    }

    @And("Si elimina file estratto")
    public void siEliminaFileEstratto() throws IOException {
        logger.info("Si elimina file estratto");
        helpdeskPage.EliminaFileZipEstratto();
    }

    @And("Si clicca sul bottone resetta filtri")
    public void siCliccaResettaFiltri(){
        helpdeskPage.clickResettaFiltri();
    }

    @And("Si verifica che i campi sono puliti")
    public void siVerificaCheICampiSonoPuliti(){
        helpdeskPage.checkCampiPuliti();

    }

    @Then("controllo password")
    public void controlloPassword() {
        helpdeskPage.checkPassword();
    }

    @And("Nella Home di helpdesk utente clicca su logout")
    public void nellaHomeDiHelpdeskUtenteCliccaSuLogout() {
        helpdeskPage.logout();
    }

    @Then("visualizzazione corretta pagina di login")
    public void visualizzazioneCorrettaPaginaDiLogin() {
        helpdeskPage.checkForm();
    }

    @Then("controllo generazione codice univoco")
    public void controlloGenerazioneCodiceUnivoco() {
        helpdeskPage.checkUid();
    }

    @And("selezione ricerca codice fiscale")
    public void selezioneRicercaCodiceFiscale() {
        helpdeskPage.changeOption();
    }

    @Then("controllo corrispondenza codice fiscale")
    public void controlloCorrispondenzaCodiceFiscale() {
        helpdeskPage.checkCodiceFiscale();
    }

    @Given("Login helpdesk in nuova scheda")
    public void loginHelpdeskInNuovaScheda(Map<String, String> login) {
        logger.info("Si apre una nuova finestra");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.open();");
        logger.info("Si seleziona la nuova finestra aperta");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        logger.info("Nella nuova finestra aperta si va sulla pagina di login di helpdesk");
        driver.get("https://helpdesk.test.notifichedigitali.it/login");
        helpdeskPage.loginHelpdeskNuovaScheda(login);
    }

    @Given("Creazione disservizio su portale helpdesk")
    public void creazioneDisservizioSuPortaleHelpdesk() {
        BackgroundTest backgroundTest = new BackgroundTest();
        backgroundTest.creazioneDisservizio();
    }

    @And("Risoluzione disservizio su portale helpdesk")
    public void risoluzioneDisservizioSuPortaleHelpdesk() {
        BackgroundTest backgroundTest = new BackgroundTest();
        backgroundTest.risoluzioneDisservizio();
    }

    @And("Selezione ottieni notifica")
    public void selezioneOttieniNotifica(){
        logger.info("Selezione ottieni notifica");
        helpdeskPage.selectOttieniNotifica();
    }
     @And("Selezione ottieni log completi")
    public void selezioneOttieniLogCompleti(){
         logger.info("Selezione ottieni log completi");
         helpdeskPage.selectOttieniLogCompleti();
     }

}
