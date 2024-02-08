package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.DettaglioNotificaSection;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.DeleghePGPagoPAPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.HomePagePG;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.PiattaformaNotifichePGPAPage;
import it.pn.frontend.e2e.section.CookiesSection;
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
    DataPopulation dataPopulation = new DataPopulation();
    Map<String, Object> personaGiuridica = new HashMap<>();
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


    @And("^Si visualizza correttamente la Pagina Notifiche persona giuridica (.*)$")
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

    @And("Nella Pagina Notifiche persona giuridica si clicca su notifiche delegate")
    public void nellaPaginaNotifichePersonaGiuridicaSiCliccaSuNotificheDelegate() {
        logger.info("Si clicca correttamente su notifiche delegate");

        piattaformaNotifichePGPAPage.clickNotificheDelegate();
    }

    @And("Si visualizza correttamente la Pagina Notifiche persona giuridica sezione notifiche delegate {string}")
    public void siVisualizzaCorrettamenteLaPaginaNotifichePersonaGiuridicaSezioneNotificheDelegate(String dpFile) {
        String ragioneSociale = dataPopulation.readDataPopulation(dpFile + ".yaml").get("ragioneSociale").toString();
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
    @And("Nella Pagina Notifiche persona giuridica si clicca su notifica")
    public void nellaPaginaNotifichePersonaGiuridicaSiCliccaSuNotifica() {
        logger.info("Si clicca sulla notificha presenta in elenco");

        piattaformaNotifichePGPAPage.clickNotificheButton();
    }

    @And("Nella sezione Dettaglio Notifiche si visualizza opzione indietro, sezione dei dati, sezione pagamento")
    public void nellaSezioneDettaglioNotificheSiVisualizzaOpzioneIndietroSezioneDeiDatiSezionePagamento() {
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);

        boolean isBackButtonDisplayed = piattaformaNotifichePGPAPage.isBackButtonDisplayed();

        if (isBackButtonDisplayed){
            logger.info("Il bottone indietro è visualizzato");
        }else {
            logger.error("Il bottone indietro non è visualizzato ");
            Assert.fail("Il bottone indietro non è visualizzato ");
        }

        boolean sezioneDeiDati = piattaformaNotifichePGPAPage.sezioneDeiDatiDisplayed();

        if (sezioneDeiDati) {
            logger.info(" Il sezione dei dati è visualizzato ");

        }else {
            logger.error("Sezione dei dati non è visualizzato");
            Assert.fail("Sezione dei dati non è visualizzato");
        }

        boolean sezionePagamentoIsDisplayed = piattaformaNotifichePGPAPage.sezionePagamentoDisplayed();

        if (!sezionePagamentoIsDisplayed) {
            logger.info("La notifica non prevede il pagamento ");
        }else{
            logger.info("La notifica prevede il pagamento");
            boolean radioBoxPresent = piattaformaNotifichePGPAPage.isRadioBoxPresent();

            if (radioBoxPresent){

                piattaformaNotifichePGPAPage.clickRadioBoxButton(piattaformaNotifichePGPAPage.cssBuildRadioButton());
            }

            boolean titoloPagamentoIsDisplayed = piattaformaNotifichePGPAPage.titoloDiPagamentoDisplayed();
            if (titoloPagamentoIsDisplayed){
                logger.info("Sezione titolo di pagamento è visualizzato");
            }else{
                logger.error("Sezione titolo di pagamento non è visualizzato");
                Assert.fail("Sezione titolo di pagamento non è visualizzato");
            }

            boolean codiceAvvisoIsDisplayed = piattaformaNotifichePGPAPage.codiceAvvisoDisplayed();
            if (codiceAvvisoIsDisplayed){
                logger.info("Sezione codice avviso è visualizzato");
            }else{
                logger.error("Sezione codice avviso non è visualizzato");
                Assert.fail("Sezione codice avviso non è visualizzato");
            }

            boolean modelloF24IsDisplayed = piattaformaNotifichePGPAPage.modelloF24Displayed();
            if (modelloF24IsDisplayed){
                logger.info("Sezione scarica modello F24 è visualizzato");
            }else{
                logger.error("Sezione scarica modello F24 non è visualizzato");
                Assert.fail("Sezione scarica modello F24 non è visualizzato");
            }

            boolean scaricaAvvisoDisplayed = piattaformaNotifichePGPAPage.scaricaAvvisoDisplayed();
            if (scaricaAvvisoDisplayed){
                logger.info("Sezione scarica avviso è visualizzato");
            }else{
                logger.error("Sezione scarica avviso non è visualizzato");
                Assert.fail("Sezione scarica avviso non è visualizzato");
            }

            boolean pagaAvvisoDisplayed = piattaformaNotifichePGPAPage.pagaAvvisoDisplayed();
            if (pagaAvvisoDisplayed){
                logger.info("Sezione paga avviso è visualizzato");
            }else{
                logger.error("Sezione paga avviso non è visualizzato");
                Assert.fail("Sezione paga avviso non è visualizzato");
            }
        }

    }
    @And("Nella sezione Dettaglio Notifiche si visualizza banner Recapiti, documenti allegati, altri documenti, stato dello notifiche, attestazioni")
    public void nellaSezioneDettaglioNotificheSiVisualizzaBannerRecapitiDocumentiAllegatiAltriDocumentiStatoDelloNotificheAttestazioni() {

        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);

        boolean sezioneRecapiti = piattaformaNotifichePGPAPage.sezioneRecapitiDisplayed();


        if (sezioneRecapiti){
            logger.info("Il sezione recapiti è visualizzato");
        }else {
            logger.error("Il sezione recapiti non è visualizzato ");
            Assert.fail("Il sezione recapiti  non è visualizzato ");
        }

        boolean documentiAllegati = piattaformaNotifichePGPAPage.documentiAllegatiDisplayed();


        if (documentiAllegati){
            logger.info("Il documenti allegati è visualizzato");
        }else {
            logger.error("Il documenti allegati non è visualizzato ");
            Assert.fail("Il documenti allegati non è visualizzato ");

        }
        boolean statoDelloNotifiche = piattaformaNotifichePGPAPage.statoDelloNotificheDisplayed();


        if (statoDelloNotifiche){
            logger.info("Il stato dello notifiche è visualizzato");
        }else {
            logger.error("Il stato dello notifiche non è visualizzato ");
            Assert.fail("Il stato dello notifiche non è visualizzato ");

        }

        boolean attestazione = piattaformaNotifichePGPAPage.attestazioneDisplayed();


        if (attestazione){
            logger.info("L'attestazione è visualizzato");
        }else {
            logger.error("L'attestazione non è visualizzato ");
            Assert.fail("L'attestazione non è visualizzato ");
        }
    }

    @And("Nella sezione Dettaglio Notifiche si clicca su link di documento allegato per salvare in locale")
    public void nellaSezioneDettaglioNotificheSiCliccaSuLinkDiDocumentoAllegatoPerSalvareInLocale() {

        logger.info("Si clicca sul link di documento allegato per salvare in locale ");

        piattaformaNotifichePGPAPage.clickDocumentButton();
        boolean isPdfFileDisplayed = piattaformaNotifichePGPAPage.isTypePdfDisplayed();
        if (isPdfFileDisplayed){
            logger.info("Il documento allegato è scaricato");
        }else{
            logger.error("Il documento allegato non è scaricato");
            Assert.fail("Il documento allegato non è scaricato");
        }
        driver.navigate().back();
    }

    @And("Nella sezione Dettaglio Notifiche si clicca su link di attestazione per salvare in locale")
    public void nellaSezioneDettaglioNotificheSiCliccaSuLinkDiAttestazionePerSalvare_(){

        logger.info("Si clicca sul link di attestazione per salvare in locale ");

        piattaformaNotifichePGPAPage.clickatestazionePersalvare();
        boolean isPdfFileDisplayed = piattaformaNotifichePGPAPage.isTypePdfDisplayed();
        if (isPdfFileDisplayed){
            logger.info("L'attestazione allegato è scaricato");
        }else{
            logger.error("L'attestazione allegato non è scaricato");
            Assert.fail("L'attestazione allegato non è scaricato");
        }
        driver.navigate().back();

    }

    @And("Nella sezione Dettaglio Notifiche si clicca su Vedi piu dettaglio nella parte dello stato della notifica")
    public void nellaSezioneDettaglioNotificheSiCliccaSuVediPiuDettaglioNellaParteDelloStatoDellaNotifica() throws InterruptedException {

        boolean vediPiuDettagliDisplayed = piattaformaNotifichePGPAPage.vediPiuDettagli();

        if (vediPiuDettagliDisplayed){
            piattaformaNotifichePGPAPage.clickVediPiuDettagli();
            Thread.sleep(1000);
            if (piattaformaNotifichePGPAPage.vediPiuDettagli()){
                piattaformaNotifichePGPAPage.clickVediPiuDettagli();
            }
        }else {
            logger.error("Il bottone vedi piu dettagli non è visualizzato");
            Assert.fail("Il bottone vedi piu dettagli non è visualizzato");
        }

    }

    @And("Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro")
    public void nellaSezioneDettaglioNotificheSiCliccaSuLopzioneIndietro() {
        logger.info("Il bottone indietro non è visibile");
        piattaformaNotifichePGPAPage.clickIndietroButton();
    }

}
