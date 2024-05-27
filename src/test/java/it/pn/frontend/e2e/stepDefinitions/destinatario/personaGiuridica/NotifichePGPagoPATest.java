package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.DettaglioNotificaSection;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.AccediAPiattaformaNotifichePage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.DeleghePGPagoPAPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.HomePagePG;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.PiattaformaNotifichePGPAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.LeTueDelegheSection;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.CookieConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.DownloadFile;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NotifichePGPagoPATest {
    private final Logger logger = LoggerFactory.getLogger("NotifichePGPagoPATest");
    private final WebDriver driver = Hooks.driver;
    List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
    DeleghePGPagoPAPage deleghePage = new DeleghePGPagoPAPage(this.driver);
    DataPopulation dataPopulation = new DataPopulation();
    Map<String, Object> personaGiuridica = new HashMap<>();
    private final LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
    private final PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);

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
                    Assert.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
    }


    @And("Si visualizza correttamente la Pagina Notifiche persona giuridica {string}")
    public void siVisualizzaCorrettamenteLaPaginaNotifichePersonaGiuridica(String ragioneSociale) {
        if (!CookieConfig.isCookieEnabled()) {
            CookiesSection cookiesSection = new CookiesSection(this.driver);
            if (cookiesSection.waitLoadCookiesPage()) {
                logger.info("Si accettano i cookies");
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }
        piattaformaNotifichePGPAPage.waitLoadPiattaformaNotificaPage(ragioneSociale);

        String variabileAmbiente = System.getProperty("environment");
        String urlChiamata = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/delivery/notifications/received?";

        int codiceRispostaChiamataApi = getCodiceRispostaChiamataApi(urlChiamata);
        if (codiceRispostaChiamataApi != 200 && codiceRispostaChiamataApi != 0) {
            logger.error("TA_QA: La chiamata, " + urlChiamata + " è andata in errore");
            Assert.fail("TA_QA: La chiamata, " + urlChiamata + " è andata in errore");
        } else if (codiceRispostaChiamataApi == 0) {
            logger.error("TA_QA: La chiamata, " + urlChiamata + " non trovata");
            Assert.fail("TA_QA: La chiamata, " + urlChiamata + " non trovata");
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

    @And("Nella Pagina Notifiche persona giuridica si clicca su notifiche delegate")
    public void nellaPaginaNotifichePersonaGiuridicaSiCliccaSuNotificheDelegate() {
        logger.info("Si clicca correttamente su notifiche delegate");

        piattaformaNotifichePGPAPage.clickNotificheDelegate();
    }

    @And("Nella sezione Deleghe si verifica sia presente una delega accettata per PG")
    public void nellaSezioneDelegheSiVerificaSiaPresenteUnaDelegaAccettataPerPG(){
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
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String urlFileAttestazioneOppponubile = downloadFile.getUrl("https://webapi.test.notifichedigitali.it/delivery-push/" + datiNotifica.get("codiceIUN").toString() + "/legal-facts/");
            if (headless && urlFileAttestazioneOppponubile.isEmpty()) {
                String testoLink = dettaglioNotificaSection.getTextLinkAttestazioniOpponibili(i);
                logger.error("Non è stato recuperato url per il download per il link: " + testoLink);
                Assert.fail("Non è stato recuperato url per il download per il link: " + testoLink);
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
            String variabileAmbiente = System.getProperty("environment");
            String urlChiamata = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/delivery/notifications/received?";
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

        if (!CookieConfig.isCookieEnabled()) {
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


    @And("Si clicca sul radio bottone di pagamento")
    public void siCliccaRadioButtonPagamento(){
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        piattaformaNotifichePGPAPage.clickRadioBoxButton();
    }

    @And("Si controlla sia presente il modello F24 PG")
    public void siControllaSiaPresenteIlModelloF24PG() {
        logger.info("Si controlla sia presente il modello F24 PG");
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        if(piattaformaNotifichePGPAPage.modelloF24Displayed()){
           logger.info("Il modello F24 è trovato");
        }else{
            logger.error("Il modello F24 non è trovato");
            Assert.fail("Il modello F24 non è trovato");
        }
    }

    @And("Si controlla non sia presente il modello F24 PG")
    public void siControllaNonSiaPresenteIlModelloF24PG() {
        logger.info("Si controlla non sia presente il modello F24 PG");
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        if(!piattaformaNotifichePGPAPage.modelloF24Displayed()){
            logger.info("Il modello F24 non è trovato");
        }else{
            logger.error("Il modello F24 è trovato");
            Assert.fail("Il modello F24 è trovato");
        }
    }

    @And("Si clicca il modello F24 PG")
    public void siCliccaIlModelloF24PG() {
        logger.info("Si clicca il modello F24 PG");
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        piattaformaNotifichePGPAPage.clickModelloF24();
    }

    @And("Si clicca il secondo modello F24 PG")
    public void siCliccaIlSecondoModelloF24PG() {
        logger.info("Si clicca il secondo modello F24 PG");
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        piattaformaNotifichePGPAPage.clickSecondoModelloF24();
    }

    @And("Si controlla sia presente l'avviso PagoPa PG")
    public void siControllaSiaPresenteLAvvisoPagoPaPG() {
        logger.info("Si controlla la presenza del box per il pagamento della notifica");
        AccediAPiattaformaNotifichePage accediAPiattaformaNotifichePage = new AccediAPiattaformaNotifichePage(this.driver);
        if(accediAPiattaformaNotifichePage.scaricaAvvisoDisplayed()){
            logger.info("Avviso PagoPA è trovato");
        }else{
            logger.error("Avviso PagoPA non è trovato");
            Assert.fail("Avviso PagoPA non è trovato");
        }
    }

    @And("Si controlla non sia presente l'avviso PagoPa PG")
    public void siControllaNonSiaPresenteLAvvisoPagoPaPG() {
        logger.info("Si controlla la presenza del box per il pagamento della notifica");
        AccediAPiattaformaNotifichePage accediAPiattaformaNotifichePage = new AccediAPiattaformaNotifichePage(this.driver);
        if(!accediAPiattaformaNotifichePage.scaricaAvvisoDisplayed()){
            logger.info("Avviso PagoPA non è trovato");
        }else{
            logger.error("Avviso PagoPA è trovato");
            Assert.fail("Avviso PagoPA è trovato");
        }
    }

    @And("Si clicca l'avviso PagoPa PG")
    public void siCliccaLAvvisoPagoPaPG() {
        logger.info("Si clicca l'avviso PagoPa PG");
        AccediAPiattaformaNotifichePage accediAPiattaformaNotifichePage = new AccediAPiattaformaNotifichePage(this.driver);
        accediAPiattaformaNotifichePage.clickAvvisoPagoPAPG();
    }

    @And("Si controlla sia visualizza box allegati modelli F24 PG")
    public void siControllaSiaVisualizzaBoxF24PG() {
        logger.info("Si controlla sia presente il box allegati modelli F24");
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        piattaformaNotifichePGPAPage.checkBoxModelloF24PG();
    }

    @And("Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN {string}")
    public void nellaPaginaPiattformaNotificheSiEffettuaLaRicercaPerCodiceIUN(String codiceIUN) throws InterruptedException {
        logger.info("Si cerca una notifica tramite IUN: " + codiceIUN);
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.inserimentoCodiceIUN(codiceIUN);
        piattaformaNotifichePage.selectFiltraDelegatoButton();
    }

    @And("Si controlla la presenza di codice avviso")
    public void siControllaLaPresenzaDiCodiceAvviso(){
        logger.info("Si controlla la presenza di codice avviso");
        AccediAPiattaformaNotifichePage  accediAPiattaformaNotifichePage = new AccediAPiattaformaNotifichePage(this.driver);
        if(accediAPiattaformaNotifichePage.codiceAvvisoDisplayed()){
            logger.info("Codice avviso è visuallizato corrttamente");
        }else {
            logger.error("Codice avviso non è visuallizato corrttamente");
            Assert.fail("Codice avviso non è visuallizato corrttamente");
        }
    }
}
