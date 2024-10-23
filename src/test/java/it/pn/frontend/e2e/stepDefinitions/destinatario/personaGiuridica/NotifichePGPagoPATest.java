package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.DettaglioNotificaSection;
import it.pn.frontend.e2e.common.NotificheDestinatarioPage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.AccediAPiattaformaNotifichePage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.DeleghePGPagoPAPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.HomePagePG;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.PiattaformaNotifichePGPAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.LeTueDelegheSection;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaMittenteSection;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.*;
import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NotifichePGPagoPATest {
    private final Logger logger = LoggerFactory.getLogger("NotifichePGPagoPATest");
    private final WebDriver driver = Hooks.driver;
    private final LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
    private final PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
    private final PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
    private final DestinatarioPage destinatarioPage = new DestinatarioPage(this.driver);
    List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
    DeleghePGPagoPAPage deleghePage = new DeleghePGPagoPAPage(this.driver);
    Map<String, Object> personaGiuridica = new HashMap<>();

    @Autowired
    private CookieConfig cookieConfig;
    @Autowired
    private WebDriverConfig webDriverConfig;

    @And("Nella Home page persona giuridica si clicca su Send Notifiche Digitali")
    public void clickSendNotificheDigitali() {
        this.logger.info("Si clicca su Send Notifiche Digitali");

        HomePagePG homePagePG = new HomePagePG(this.driver);
        homePagePG.waitLoadHomePagePGPage();
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> homePagePG.clickSendNotificheDigitali(5);
            case "test" -> homePagePG.clickSendNotificheDigitali(6);
            default ->
                    Assertions.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
    }


    @And("Si visualizza correttamente la Pagina Notifiche persona giuridica {string}")
    public void siVisualizzaCorrettamenteLaPaginaNotifichePersonaGiuridica(String ragioneSociale) {
        if (!cookieConfig.isCookieEnabled()) {
            CookiesSection cookiesSection = new CookiesSection(this.driver);
            if (cookiesSection.waitLoadCookiesPage()) {
                logger.info("Si accettano i cookies");
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }
        piattaformaNotifichePGPAPage.waitLoadPiattaformaNotificaPage(ragioneSociale);

        String urlChiamata = webDriverConfig.getBaseUrl() + "notifications/received?";

        int codiceRispostaChiamataApi = getCodiceRispostaChiamataApi(urlChiamata);
        if (codiceRispostaChiamataApi != 200 && codiceRispostaChiamataApi != 0) {
            logger.error("TA_QA: La chiamata, " + urlChiamata + " è andata in errore");
            Assertions.fail("TA_QA: La chiamata, " + urlChiamata + " è andata in errore");
        } else if (codiceRispostaChiamataApi == 0) {
            logger.error("TA_QA: La chiamata, " + urlChiamata + " non trovata");
            Assertions.fail("TA_QA: La chiamata, " + urlChiamata + " non trovata");
        }
    }

    private int getCodiceRispostaChiamataApi(String urlChiamata) {
        logger.info("Recupero codice risposta della chiamata" + urlChiamata);

        int codiceRispostaChiamataApi = 0;
        for (NetWorkInfo chiamate : netWorkInfos) {
            if (chiamate.getRequestUrl().startsWith(urlChiamata) && chiamate.getRequestMethod().equals("GET")) {
                codiceRispostaChiamataApi = Integer.parseInt(chiamate.getResponseStatus());
                break;
            }
        }
        return codiceRispostaChiamataApi;
    }

    @When("Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaClickSulBottoneDeleghe() {
        logger.info("Si clicca sul bottone Deleghe");

        piattaformaNotifichePGPAPage.clickSuDelegeButton();
    }

    @And("Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe")
    public void visualizzaDelegheSection() {
        logger.info("Si visualizza la sezione deleghe");
        deleghePage.waitLoadDeleghePage();
    }

    @And("Nella Pagina Notifiche destinatario si clicca su notifiche delegate")
    public void nellaPaginaNotifichePersonaGiuridicaSiCliccaSuNotificheDelegate() {
        logger.info("Si clicca correttamente su notifiche delegate");

        piattaformaNotifichePGPAPage.clickNotificheENotificheDelegate();
    }


    @And("Nella Pagina Notifiche destinatario si clicca solo su notifiche delegate")
    public void nellaPaginaNotifichePersonaGiuridicaSiCliccaSoloSuNotificheDelegate() {
        logger.info("Si clicca correttamente su notifiche delegate");

        piattaformaNotifichePGPAPage.clickNotificheDelegate();
    }
    @And("Nella sezione Deleghe si verifica sia presente una delega accettata per PG")
    public void nellaSezioneDelegheSiVerificaSiaPresenteUnaDelegaAccettataPerPG() {
        logger.info("Si controlla che sia presente una delega");
        BackgroundTest backgroundTest = new BackgroundTest();
        if (!this.deleghePage.siVisualizzaUnaDelegaPG()) {
            backgroundTest.loginPGDeleghe("personaGiuridica");
            backgroundTest.aggiuntaNuovaDelegaDellImpresaPG();
            backgroundTest.logoutPG();
            backgroundTest.loginPGDeleghe("delegatoPG");
            backgroundTest.accettazioneDelegaPG();
        } else if (this.leTueDelegheSection.controlloPresenzaBottoneAccetta()) {
            backgroundTest.accettazioneDelegaPG();
        }
        this.driver.navigate().refresh();
    }

    @And("Si visualizza correttamente la Pagina Notifiche persona giuridica sezione notifiche delegate {string}")
    public void siVisualizzaCorrettamenteLaPaginaNotifichePersonaGiuridicaSezioneNotificheDelegate(String ragioneSociale) {
        piattaformaNotifichePGPAPage.waitLoadSezioneNotificheDelegate(ragioneSociale);
    }

    @When("Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaSiCliccaSulBottoneITuoiRecapiti() {
        logger.info("Si clicca sulla voce recapiti nel menu");

        piattaformaNotifichePGPAPage.clickRecapitiButton();
    }

    @Then("Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica persona giuridica, e si controlla che il download sia avvenuto {string}")
    public void siSelezionanoIFileAttestazioniOpponibiliDaScaricareAllInternoDellaNotificaPersonaGiuridicaESiControllaCheIlDownloadSiaAvvenuto(String dpFile) {
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        int numeroLinkAttestazioniOpponibile = dettaglioNotificaSection.getLinkAttestazioniOpponibili();
        DownloadFile downloadFile = new DownloadFile(this.driver);
        DataPopulation dataPopulation = new DataPopulation();
        Map<String, Object> datiNotifica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        String workingDirectory = System.getProperty("user.dir");
        File pathCartella = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/destinatario/personaGiuridica");
        boolean headless = System.getProperty("headless").equalsIgnoreCase("true");
        if (!downloadFile.controlloEsistenzaCartella(pathCartella)) {
            pathCartella.mkdirs();
        }
        for (int i = 0; i < numeroLinkAttestazioniOpponibile; i++) {
            dettaglioNotificaSection.clickLinkAttestazioniOpponibile(i);
            WebTool.waitTime(5);
            String urlFileAttestazioneOppponubile = downloadFile.getUrl("https://webapi.test.notifichedigitali.it/bff/v1/notifications/received/" + datiNotifica.get("codiceIUN").toString() + "/documents/");
            if (headless && urlFileAttestazioneOppponubile.isEmpty()) {
                String testoLink = dettaglioNotificaSection.getTextLinkAttestazioniOpponibili(i);
                logger.error("Non è stato recuperato url per il download per il link: " + testoLink);
                Assertions.fail("Non è stato recuperato url per il download per il link: " + testoLink);
            }
            File file = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/destinatario/notificaN" + i + ".pdf");
            downloadFile.download(urlFileAttestazioneOppponubile, file, headless);
            if (!headless) {
                dettaglioNotificaSection.goBack();
            }
        }
        downloadFile.controlloDownload(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/destinatario", numeroLinkAttestazioniOpponibile);
    }


    private String getBearerToken() {
        List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
        String bearerToken = "";
        for (NetWorkInfo netWorkInfo : netWorkInfos) {
            String urlChiamata = webDriverConfig.getBaseUrl() + "notifications/received?";
            if (netWorkInfo.getRequestUrl().contains(urlChiamata)) {
                bearerToken = netWorkInfo.getAuthorizationBearer();
            }
        }
        return bearerToken;
    }

    public void siRecuperaBearerToken(String dpFile) {
        logger.info("Si recupera il bearer token");
        DataPopulation dataPopulation = new DataPopulation();
        personaGiuridica = dataPopulation.readDataPopulation(dpFile + ".yaml");

        if (!cookieConfig.isCookieEnabled()) {
            CookiesSection cookiesSection = new CookiesSection(this.driver);
            if (cookiesSection.waitLoadCookiesPage()) {
                logger.info("Si accettano i cookies");
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }
        piattaformaNotifichePGPAPage.waitLoadPiattaformaNotificaPage(personaGiuridica.get("ragioneSociale").toString());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String bearerToken = getBearerToken();
        personaGiuridica.put("bearerToken", bearerToken);
        dataPopulation.writeDataPopulation(dpFile + ".yaml", personaGiuridica);
    }

    @And("Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro")
    public void nellaSezioneDettaglioNotificheSiCliccaSuLopzioneIndietro() {
        logger.info("Si clicca sul bottone indietro");
        piattaformaNotifichePGPAPage.clickIndietroButton();
    }

    @And("Si verifica la presenza del banner di avviso annullamento notifica")
    public void siVerificaLaPresenzaDelBannerDiAvvisoAnnullamentoNotifica() {
        logger.info("Si verifica che nel dettaglio della notifica sia presente il banner di annullamento");
        destinatarioPage.checkBannerAnnullamentoNotifica();
    }

    @And("Si verifica la presenza dello stato {string} nella timeline della notifica")
    public void siVerificaLaPresenzaDelloStatoNellaTimelineDellaNotifica(String statoNotifica) {
        logger.info("Si verifica che nella timeline della notifica sia presente lo stato " + statoNotifica);
        piattaformaNotifichePage.verificaPresenzaStato(statoNotifica);
    }

    @And("Si clicca sul radio bottone di pagamento")
    public void siCliccaRadioButtonPagamento() {
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        piattaformaNotifichePGPAPage.clickRadioBoxButton();
    }

    @And("Si controlla sia presente il modello F24 destinatario")
    public void siControllaSiaPresenteIlModelloF24Destinatario() {
        logger.info("Si controlla sia presente il modello F24 PG");
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        if (piattaformaNotifichePGPAPage.modelloF24Displayed()) {
            logger.info("Il modello F24 è trovato");
        } else {
            logger.error("Il modello F24 non è trovato");
            Assertions.fail("Il modello F24 non è trovato");
        }
    }

    @And("Si controlla non sia presente il modello F24 destinatario")
    public void siControllaNonSiaPresenteIlModelloF24Destinatario() {
        logger.info("Si controlla non sia presente il modello F24 PG");
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        if (!piattaformaNotifichePGPAPage.modelloF24Displayed()) {
            logger.info("Il modello F24 non è trovato");
        } else {
            logger.error("Il modello F24 è trovato");
            Assertions.fail("Il modello F24 è trovato");
        }
    }

    @And("Si clicca sul modello F24 destinatario numero {int}")
    public void siCliccaIlModelloF24Destinatario(int numOfF24) {
        logger.info("Si clicca  modello F24 destinatario");
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        piattaformaNotifichePGPAPage.vaiInFondoAllaPagina();
        piattaformaNotifichePGPAPage.clickModelloF24Numero(numOfF24);
    }


    @And("Si controlla sia presente l'avviso PagoPa destinatario")
    public void siControllaSiaPresenteLAvvisoPagoPaDestinatario() {
        logger.info("Si controlla la presenza del box per il pagamento della notifica");
        AccediAPiattaformaNotifichePage accediAPiattaformaNotifichePage = new AccediAPiattaformaNotifichePage(this.driver);
        if (accediAPiattaformaNotifichePage.scaricaAvvisoDisplayed()) {
            logger.info("Avviso PagoPA è trovato");
        } else {
            logger.error("Avviso PagoPA non è trovato");
            Assertions.fail("Avviso PagoPA non è trovato");
        }
    }

    @And("Si controlla sia presente piu avvisi PagoPa PG")
    public void siControllaSiaPresentePiuAvvisiPagoPaPG() {
        AccediAPiattaformaNotifichePage accediAPiattaformaNotifichePage = new AccediAPiattaformaNotifichePage(this.driver);
        Assertions.assertTrue(accediAPiattaformaNotifichePage.piuAvvisiDisplayed(),"Avvissi PagoPA non sono trovati");
        logger.info("Avvissi PagoPA sono trovati");

    }

    @And("Si controlla sia presente l'avvisi PagoPa PG")
    public void siControllaSiaPresenteLAvvisiPagoPaPG() {
        logger.info("Si controlla la presenza del box per il pagamento della notifica");
        AccediAPiattaformaNotifichePage accediAPiattaformaNotifichePage = new AccediAPiattaformaNotifichePage(this.driver);
        Assertions.assertTrue(accediAPiattaformaNotifichePage.piuAvvisiDisplayed(),"Avviso PagoPA non è trovato");
        logger.info("Avviso PagoPA è trovato");
    }

    @And("Si controlla non sia presente l'avviso PagoPa")
    public void siControllaNonSiaPresenteLAvvisoPagoPa() {
        logger.info("Si controlla la presenza del box per il pagamento della notifica");
        AccediAPiattaformaNotifichePage accediAPiattaformaNotifichePage = new AccediAPiattaformaNotifichePage(this.driver);
        WebTool.waitTime(5);
        Assertions.assertTrue( accediAPiattaformaNotifichePage.piuAvvisiDisplayed(), "Avviso PagoPA non sia presente");
        logger.info("Avviso PagoPA è presente");
    }

    @And("Si controlla non sia presente l'allegato PagoPa")
    public void siControllaNonSiaPresenteLAllegatoPagoPa() {
        logger.info("Si controlla la presenza del box per il pagamento della notifica");
        AccediAPiattaformaNotifichePage accediAPiattaformaNotifichePage = new AccediAPiattaformaNotifichePage(this.driver);
        WebTool.waitTime(5);
        Assertions.assertTrue(accediAPiattaformaNotifichePage.allegatoPagoPaDisplayed(),"Avviso PagoPA non sia presente");
        logger.info("Avviso PagoPA è presente");
    }

    @And("Si clicca l'avviso PagoPa destinatario")
    public void siCliccaLAvvisoPagoPaDestinatario() {
        logger.info("Si clicca l'avviso PagoPa destinatario");
        AccediAPiattaformaNotifichePage accediAPiattaformaNotifichePage = new AccediAPiattaformaNotifichePage(this.driver);
        accediAPiattaformaNotifichePage.clickAvvisoPagoPADestinatario();
    }

    @And("Si controlla sia visualizza box allegati modelli F24 PG")
    public void siControllaSiaVisualizzaBoxF24PG() {
        logger.info("Si controlla sia presente il box allegati modelli F24");
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        piattaformaNotifichePGPAPage.checkBoxModelloF24PG();
    }

    @And("Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN {string}")
    public void nellaPaginaPiattformaNotificheSiEffettuaLaRicercaPerCodiceIUN(String codiceIUN) {
        logger.info("Si cerca una notifica tramite IUN: " + codiceIUN);
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.inserimentoCodiceIUN(codiceIUN);
        piattaformaNotifichePage.selectFiltraNotificaButtonDestinatario();
    }

    @And("Si controlla la presenza di codice avviso")
    public void siControllaLaPresenzaDiCodiceAvviso() {
        logger.info("Si controlla la presenza di codice avviso");
        AccediAPiattaformaNotifichePage accediAPiattaformaNotifichePage = new AccediAPiattaformaNotifichePage(this.driver);
        if (accediAPiattaformaNotifichePage.codiceAvvisoDisplayed()) {
            logger.info("Codice avviso è visuallizato corrttamente");
        } else {
            logger.error("Codice avviso non è visuallizato corrttamente");
            Assertions.fail("Codice avviso non è visuallizato corrttamente");
        }
    }

    @And("controllo link per scaricare zip e scarico file Ricevuta di consegna")
    public void scaricaRicevutaDiConsegna() throws AWTException, IOException {
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        piattaformaNotifichePGPAPage.clickRicevutaDiConsegna();
    }

    @And("controllo Ricevuta di consegna link cliccabile")
    public void controlloRicevutaDiConsegnaLinkCliccabile() {
        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        notificheDestinatarioPage.checkRicevutaConsegnaCliccabile();
    }

    @And("Controllo sia presente documento pdf")
    public void controlloPresenteDocumento() throws IOException {
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        logger.info("Controllo sia presente documento pdf");
        Assertions.assertTrue(piattaformaNotifichePGPAPage.checkIfPdfExists(), "Documento non è trovato");
        logger.info("Documento è trovato");
    }

    @And("estraggo il file zip")
    public void inseriscoPasswordEdEstraggoZip() throws IOException {
        logger.info("estraggo il file zip");
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        piattaformaNotifichePGPAPage.extractZipWithoutPassword();
    }

    @And("Nella sezione Dettaglio Notifiche PG si seleziona il file, {string}, da scaricare")
    public void siSelezionanoIlFileDaScaricare(String nomeFile) throws IOException {
        logger.info("Si cerca di scaricare il file " + nomeFile);

        boolean headless = System.getProperty("headless").equalsIgnoreCase("true");
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        dettaglioNotificaMittenteSection.clickLinkAttestazioneOpponibile(nomeFile);
        WebTool.waitTime(5);
        DownloadFile downloadFile = new DownloadFile(this.driver);

        final String url = downloadFile.getUrl(webDriverConfig.getBaseUrl() + "notifications/received/");
        if (headless && url.isEmpty()) {
            logger.error("Non è stato recuperato url per il download per il link: " + nomeFile);
            Assertions.fail("Non è stato recuperato url per il download per il link: " + nomeFile);
        }
        nomeFile = nomeFile.replace(" ", "_").replace(":", "");
        File file = new File("src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf");
        logger.info("Il file verrà scaricato in: " + file.getAbsolutePath());
        FileUtils.copyURLToFile(new URL(url), file, 1000, 1000);
        if (!headless) {
            dettaglioNotificaMittenteSection.goBack();
        }
    }

    @Then("Si controlla il testo all interno del file destinatario {string}")
    public void siControllaIlTestoAlSuoInternoDestonatario(String nomeFile) {
    logger.info("Si controlla che il testo al suo interno si corretto");
    piattaformaNotifichePGPAPage.controllaTesto(nomeFile);
    }

    @And("Si controlla il SHA all interno del file atteztazione")
    public void siControllaIlShaAllInternoAot() {
        logger.info("Si controlla che il testo al suo interno si corretto");
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        if (dettaglioNotificaMittenteSection.controlloSHAFile("Attestazione_opponibile_a_terzi_notifica_presa_in_carico")) {
            logger.info("Il codice SHA all'interno del file è corretto");
        } else {
            logger.error("Il codice SHA  all'interno del file  NON è corretto");
            Assertions.fail("Il codice SHA  all'interno del file  NON è corretto");
        }
    }
}
