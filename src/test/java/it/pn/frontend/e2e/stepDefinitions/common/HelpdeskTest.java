package it.pn.frontend.e2e.stepDefinitions.common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.common.HelpdeskPage;
import it.pn.frontend.e2e.config.DataPopulationConfig;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.model.enums.Disservice;
import it.pn.frontend.e2e.model.enums.Status;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class HelpdeskTest extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(HelpdeskTest.class);


    @Autowired
    private WebDriverConfig webDriverConfig;

    private HelpdeskPage helpdeskPage;

    @Autowired
    @Lazy
    private BackgroundTest backgroundTest;

    private WebTool webTool;
    @Autowired
    private DataPopulationConfig dataPopulationConfig;

    @PostConstruct
    public void init() {
        logger.info("INIT TEST...: ");
        webTool = new WebTool(driver);
        helpdeskPage = new HelpdeskPage(driver);
    }


    @Given("Login helpdesk con utente test")
    public void loginHelpdeskConUtenteTest() {
        String variabileAmbiente = webDriverConfig.getEnvironment();
        switch (variabileAmbiente) {
            case "dev" -> helpdeskPage.changePage(dataPopulationConfig.getHelpdesk().getUrl());
            case "test", "uat" ->
                    helpdeskPage.changePage(dataPopulationConfig.getHelpdesk().getUrl().replace("dev", variabileAmbiente));
            default ->
                    Assertions.fail("Non è stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
        helpdeskPage.checkForm();
        switch (variabileAmbiente) {
            case "dev" -> {
                helpdeskPage.insertUsername(dataPopulationConfig.getHelpdesk().getUserDev());
                helpdeskPage.insertPassword(dataPopulationConfig.getHelpdesk().getPwdDev());
            }
            case "test" -> {
                helpdeskPage.insertUsername(dataPopulationConfig.getHelpdesk().getUserTest());
                helpdeskPage.insertPassword(dataPopulationConfig.getHelpdesk().getPwdTest());
            }
            case "uat" -> {
                helpdeskPage.insertUsername(dataPopulationConfig.getHelpdesk().getUserUat());
                helpdeskPage.insertPassword(dataPopulationConfig.getHelpdesk().getPwdUat());
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
        webTool.waitTime(5);
        if (!helpdeskPage.checkIsCreatedDisservizio()) {
            helpdeskPage.handleDisservizio(Disservice.CREAZIONE_NOTIFICHE, Status.KO);
            webTool.waitTime(5);
        }
    }

    @And("Si crea il disservizio new")
    public void siCreaIlDisservizioNew() {
        if (!helpdeskPage.checkIsCreatedDisservizio()) {
            helpdeskPage.handleDisservizioNew(Disservice.CREAZIONE_NOTIFICHE, Status.INSERISCI_KO);
        }
    }

    @And("Si risolve il disservizio")
    public void siRisolveIlDisservizio() {
        webTool.waitTime(5);
        if (helpdeskPage.checkIsCreatedDisservizio()) {
            helpdeskPage.handleDisservizio(Disservice.CREAZIONE_NOTIFICHE, Status.OK);
            webTool.waitTime(5);
        }
    }

    @And("Si risolve il disservizio new")
    public void siRisolveIlDisservizionew() {
        if (helpdeskPage.checkIsCreatedDisservizio()) {
            helpdeskPage.handleRisolviDisservizionew(Disservice.CREAZIONE_NOTIFICHE, Status.RISOLVI_KO);
        }
    }

    @And("Si risolve il disservizio new nome test {string}")
    public void siRisolveIlDisservizionew(String nomeTest) {
        if (helpdeskPage.checkIsCreatedDisservizio()) {
            helpdeskPage.handleRisolviDisservizionew(Disservice.CREAZIONE_NOTIFICHE, Status.RISOLVI_KO,nomeTest);
        }
    }


    @And("Si verifica la creazione del disservizio")
    public void siVerificaLaCreazioneDelDisservizio() {
        helpdeskPage.checkIsCreatedDisservizio();
    }

    @And("Si annulla un disservizio in corso")
    public void annullamentoDisservizio() {
        logger.info("Torno sulla scheda di helpdesk");
        String sendHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(sendHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        backgroundTest.getHelpdeskMonitoraggioPiattaforma();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (helpdeskPage.checkServiceStatus(Disservice.CREAZIONE_NOTIFICHE)) {
            helpdeskPage.handleDisservizio(Disservice.CREAZIONE_NOTIFICHE, Status.OK);
        } else {
            logger.info("Lo stato del servizio: {} è già in OK",Disservice.CREAZIONE_NOTIFICHE);
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Torno sulla piattaforma send per il logout");
        for (String handle : windowHandles) {
            if (handle.equals(sendHandle)) {
                driver.switchTo().window(handle);
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

    @And("viene inserito codice fiscale")
    public void vieneInseritoCodiceFiscale() {
        helpdeskPage.insertCfAndRicercaOnPage(dataPopulationConfig.getPersonaFisica().getCodiceFiscale());
    }

    @And("viene inserito codice fiscale senza ricerca {string}")
    public void vieneInseritoCodiceFiscaleSenzaRicerca(String CF) {
        helpdeskPage.insertCF(CF);
    }

    @And("viene inserito codice IUN {string}")
    public void vieneInseritoIun(String iun) {
        String codiceIun = getCodiceIun(iun, "viene inserito codice IUN");
        helpdeskPage.insertIunAndRicercaOnPage(codiceIun);
    }

    @And("viene inserito codice IUN senza ricerca {string}")
    public void vieneInseritoIunSenzaRicerca(String iun) {
        String codiceIun = getCodiceIun(iun, "viene inserito codice IUN senza ricerca");
        helpdeskPage.insertIun(codiceIun);
    }

    @And("viene inserito numero ticket")
    public void vieneInseritoNumeroTicket() {
        helpdeskPage.insertNumeroTicket();
    }

    @And("viene inserito codice univoco {string}")
    public void vieneInseritoUID(String uid) {
        helpdeskPage.insertUid(uid);
    }

    @And("Cliccare sul bottone ricerca")
    public void cliccareSulBottoneRicerca() {
        logger.info("Cliccare sul bottone ricerca");
        helpdeskPage.clickRicercaBottone();
    }

    @And("Spuntare la casella Deanonimizzazione dati")
    public void spuntareDeanonimizzazioneDati() {
        logger.info("Spuntare la casella Deanonimizzazione dati");
        helpdeskPage.spuntareDeanonimizzazioneDati();

    }

    @And("viene inserito un range temporale maggiore di 3 mesi")
    public void inserisceRangeTemporaleMaggioreDi3Mesi() {
        logger.info("viene inserito un range temporale maggiore di 3 mesi");
        helpdeskPage.inserimentoArcoTemporale();
    }

    @And("viene visualizzato messaggio di errore data")
    public void vieneVisualizzatoMessaggioDiErroreData() {
        helpdeskPage.checkMessaggioDiErroreData();
    }

    @And("viene visualizzato messaggio di errore IUN")
    public void vieneVisualizzatoMessaggioDiErroreIUN() {
        helpdeskPage.checkMessaggioDiErroreIUN();
    }

    @And("viene visualizzato messaggio di errore CF")
    public void vieneVisualizzatoMessaggioDiErroreCF() {
        helpdeskPage.checkMessaggioDiErroreCF();
    }

    @Then("controllo messaggio di successo")
    public void controlloMessaggioSuccesso() {
        helpdeskPage.checkMessaggioSuccesso();
    }

    @Then("controllo link per scaricare zip e scarico file")
    public void controlloLinkPerScaricareZip() throws IOException, AWTException {

        helpdeskPage.setHeadlessParam(webDriverConfig.getHeadless());
        helpdeskPage.checkZipLink();
    }

    @And("Inserisco la password ed estraggo il file zip")
    public void inseriscoPasswordEdEstraggoZip() throws IOException {
        logger.info("Inserisco la password ed estraggo il file zip");
        helpdeskPage.setHeadlessParam(webDriverConfig.getHeadless());
        helpdeskPage.extractZip();
    }

    @And("Controllo sia presente documento {string}")
    public void controlloPresenteDocumento(String docName) throws IOException {
        logger.info("Controllo sia presente documento {}", docName);
        webTool.waitTime(5);
        Assertions.assertTrue(helpdeskPage.trovaDocumentoConTitolo(docName), "Documento " + docName + " non è trovato");
        logger.info("Documento {} è trovato", docName);
    }

    @And("Controllo sia presente documento estratto da zip con testo {string} {string}")
    public void controlloPresenteDocumentoDaZipConTesto(String docName, String testo) throws IOException {
        logger.info("Controllo sia presente documento {} e che contenga il testo {}", docName, testo);
        webTool.waitTime(5);
        Assertions.assertTrue(helpdeskPage.trovaTestoInDocumentoDaZip(docName, testo), "Documento " + docName + " non è trovato o non contiene il testo " + testo);
        logger.info("Documento {} è trovato e contiene il testo {}", docName, testo);
    }

    @And("Controllo sia presente documento estratto da zip e che sia vuoto {string}")
    public void controlloPresenteDocumentoDaZipVuoto(String docName) throws IOException {
        logger.info("Controllo sia presente documento {} e che sia vuoto", docName);
        webTool.waitTime(5);
        Assertions.assertFalse(helpdeskPage.trovaDocumentoDaZipConDimensioni(docName), "Documento " + docName + " non è trovato oppure non è vuoto");
        logger.info("Documento {} è trovato ed è vuoto", docName);
    }

    @And("Si elimina file estratto")
    public void siEliminaFileEstratto() throws IOException {
        logger.info("Si elimina file estratto");
        helpdeskPage.EliminaFileZipEstratto();
    }

    @And("Si clicca sul bottone resetta filtri")
    public void siCliccaResettaFiltri() {
        helpdeskPage.clickResettaFiltri();
    }

    @And("Si verifica che i campi sono puliti")
    public void siVerificaCheICampiSonoPuliti() {
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
        driver.get(webDriverConfig.getUrlHelpdeskTestNotifichedigitali());
        helpdeskPage.loginHelpdeskNuovaScheda(login);
    }

    @Given("Creazione disservizio su portale helpdesk")
    public void creazioneDisservizioSuPortaleHelpdesk() {
        backgroundTest.creazioneDisservizio();
    }

    @Given("Creazione disservizio new su portale helpdesk")
    public void creazioneDisservizioNewSuPortaleHelpdesk() {
        backgroundTest.creazioneDisservizioNew();
    }

    @And("Risoluzione disservizio su portale helpdesk")
    public void risoluzioneDisservizioSuPortaleHelpdesk() {
        backgroundTest.risoluzioneDisservizio();
    }

//    @And("Risoluzione disservizio new su portale helpdesk")
//    public void risoluzioneDisservizioNewSuPortaleHelpdesk() {
//        backgroundTest.risoluzioneDisservizioNew();
//    }
    @And("Risoluzione disservizio new su portale helpdesk nome test {string}")
    public void risoluzioneDisservizioNewSuPortaleHelpdesk(String nomeTest) {
        backgroundTest.risoluzioneDisservizioNew(nomeTest);
    }

    @And("Verifica Disservizio")
    public void verificaDisservizio() {
        siRisolveIlDisservizionew();
        helpdeskPage.verificaAlert();
        Assertions.assertFalse(helpdeskPage.checkIsCreatedDisservizio(), "Disservizio non risolto.");


    }

    @And("Selezione ottieni notifica")
    public void selezioneOttieniNotifica() {
        logger.info("Selezione ottieni notifica");
        helpdeskPage.selectOttieniNotifica();
    }

    @And("Selezione ottieni log completi")
    public void selezioneOttieniLogCompleti() {
        logger.info("Selezione ottieni log completi");
        helpdeskPage.selectOttieniLogCompleti();
    }

    private String getCodiceIun(String iun, String message) {
        String codiceIun;
        switch (iun) {
            case "IUN0" -> {
                codiceIun = webDriverConfig.getCodiceIun();
            }
            case "IUN1" -> {
                codiceIun = webDriverConfig.getCodiceIunN1();
            }
            case "IUN2" -> {
                codiceIun = webDriverConfig.getCodiceIunN2();
            }
            case "IUN3" -> {
                codiceIun = webDriverConfig.getCodiceIunN3();
            }
            default -> {
                logger.error(message);
                throw new RuntimeException(message + " 'ERRATO'");
            }
        }
        return codiceIun;
    }


}
