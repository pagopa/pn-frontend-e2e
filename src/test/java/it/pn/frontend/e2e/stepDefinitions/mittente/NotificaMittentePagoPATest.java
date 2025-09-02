package it.pn.frontend.e2e.stepDefinitions.mittente;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.mittente.AccettazioneRichiestaNotifica;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.DataPopulationConfig;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.config.WebDriverManager;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.model.enums.AppPortal;
import it.pn.frontend.e2e.model.enums.AppPortalUrl;
import it.pn.frontend.e2e.model.recipients.PersoneFisiche;
import it.pn.frontend.e2e.model.recipients.PersoneGiuridiche;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.pages.mittente.AreaRiservataPAPage;
import it.pn.frontend.e2e.pages.mittente.InvioNotifichePAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.mittente.*;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.LoginPersonaFisicaPagoPA;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.LoginPGPagoPATest;
import it.pn.frontend.e2e.utility.AppPortalUrlFactory;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.apache.commons.lang3.StringUtils.substring;


public class NotificaMittentePagoPATest  extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(NotificaMittentePagoPATest.class);

    private final String PF = "persona fisica";
    private final String PG = "persona giuridica";
    private final String PA = "pubblica amministrazione";
    private Map<String, Object> datiNotifica_1 = new HashMap<>();
    private Map<String, String> datiNotificaMap = new HashMap<>();
//    private Map<String, Object> personaFisica = new HashMap<>();
//    private Map<String, Object> personaGiuridica = new HashMap<>();
//    private Map<String, Object> personeFisiche = new HashMap<>();
    @Getter @Setter
    private String Iun;
    @Getter  @Setter
    private String ApiKey;

    @Autowired
    private HooksNew hooksNew;

    private  WebTool webTool;

    private CookiesSection cookiesSection ;

    private AreaRiservataPAPage areaRiservataPAPage;

    private HeaderPASection headerPASection ;

    private SuccessPASection successPASection;

    private  PiattaformaNotifichePage piattaformaNotifichePage ;

    private  AllegatiPASection allegatiPASection ;

    private  DestinatarioPASection destinatarioPASection;

    private DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection;

    private InformazioniPreliminariPASection informazioniPreliminariPASection;

    private InvioNotifichePAPage invioNotifichePAPage;

    private BackgroundTest backgroundTest;

    @Autowired
    private AccettazioneRichiestaNotifica accettazioneRichiestaNotifica;

    @Autowired
    private NotificationSingleton notificationSingleton;

    @Autowired
    private WebDriverConfig webDriverConfig;

    @Autowired
    private DataPopulation dataPopulation;

    @Autowired
    @Lazy
    private WebDriverManager webDriverManager;

    @Autowired
    private  LoginPGPagoPATest loginPGPagoPATest;

    @Autowired
    private  LoginPersonaFisicaPagoPA loginPersonaFisicaPagoPA;

    @Autowired
    private DataPopulationConfig dataPopulationConfig;

    @Autowired
    private RestNotification restNotification;
    @Autowired
    private AppPortalUrlFactory urlFactory;

    @Autowired
    private RicercaNotificheMittentePagoPATest ricercaNotificheMittentePagoPATest;
 
    @PostConstruct
    public void init(){
        logger.info("INIT TEST...: ");
        webTool = new WebTool(driver);
        cookiesSection = new CookiesSection(driver);
        areaRiservataPAPage = new AreaRiservataPAPage(driver);
        headerPASection = new HeaderPASection(driver);
        successPASection = new SuccessPASection(driver);
        piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.setHooksNew(hooksNew);
        allegatiPASection = new AllegatiPASection(driver);
        destinatarioPASection = new DestinatarioPASection(driver);
        dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(driver);
        informazioniPreliminariPASection = new InformazioniPreliminariPASection(driver);
        invioNotifichePAPage = new InvioNotifichePAPage(driver);

        ricercaNotificheMittentePagoPATest.setHooksNew(hooksNew);

        backgroundTest = new BackgroundTest();
    }



    @When("Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche")
    public void nellaHomePageMittenteCliccareSuGestisciDiPiattaforma() {
        logger.info("Cliccare sul bottone di Piattaforma Notifiche dell'Ambiente " + webDriverConfig.getEnvironment());
        switch (webDriverConfig.getEnvironment()) {
            case "dev" -> areaRiservataPAPage.selezionaPiattaformaNotificaDev();
            case "test" -> areaRiservataPAPage.selezionaPiattaformaNotificaTest();
            case "uat" -> areaRiservataPAPage.selezionaPiattaformaNotificaUat();
        }
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotifiche() {
        logger.info("Si visualizza correttamente la pagina Piattaforma Notifiche");
        headerPASection.waitLoadHeaderSection();
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String urlChiamata = webDriverConfig.getBaseUrl() + "notifications/sent?";
         //TODO rivedere.....
        // Aspetta che la chiamata API venga intercettata entro 10 secondi
       // boolean apiFound = webDriveBean.waitForApiCall(urlChiamata, Duration.ofSeconds(60));
        //Assertions.assertTrue(apiFound, "API call was not captured within the timeout period");

        int codiceRispostaChiamataApi = getCodiceRispostaChiamataApi(urlChiamata);
        if (codiceRispostaChiamataApi != 200 && codiceRispostaChiamataApi != 0) {
            Assertions.fail("TA_QA: La chiamata, " + urlChiamata + " è andata in errore");
        } else if (codiceRispostaChiamataApi == 0) {
            Assertions.fail("TA_QA: La chiamata, " + urlChiamata + " non trovata");
        }
    }

    private int getCodiceRispostaChiamataApi(String urlChiamata) {
        logger.info("Recupero codice risposta della chiamata" + urlChiamata);
        logger.info("Recupero AAAAA codice risposta della chiamata NetworkInfoManager " +  webDriverManager.getNetworkInfosThread().get());
        logger.info("Recupero AAAAA codice risposta della chiamata NetworkInfoManager " +  webDriverManager.getNetworkInfosThread().get().size());

        int codiceRispostaChiamataApi = 0;
        for (NetWorkInfo chiamate : webDriverManager.getNetworkInfosThread().get()) {
            if (chiamate.getRequestUrl().startsWith(urlChiamata) && chiamate.getRequestMethod().equals("GET")) {
                codiceRispostaChiamataApi = Integer.parseInt(chiamate.getResponseStatus());
                break;
            }
        }
        return codiceRispostaChiamataApi;
    }

    public String getNumeroProtocollo() {
        logger.info("Si recupera l'ultimo numero protocollo utilizzato");
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.siCambiaIlNumeroElementiVisualizzatiAttraversoIlFiltro();
        webTool.waitTime(5);
        String urlNotifiche = webDriverConfig.getBaseUrl() + "notifications/";
        for (NetWorkInfo netWorkInfo : webDriverManager.getNetworkInfosThread().get()) {
            if (netWorkInfo.getRequestUrl().contains(urlNotifiche) && netWorkInfo.getRequestUrl().endsWith("size=10")) {
                webTool.waitTime(5);
                String responseBody = netWorkInfo.getResponseBody();
                String[] allNotifiche = responseBody.split("],\"moreResult\":");
                String[] notifiche = allNotifiche[0].split("},");
                for (String notifica : notifiche) {
                    if (notifica.contains("Pagamento rata IMU")) {
                        String[] campiNotifiche = notifica.split("\",");
                        for (String campoNotifica : campiNotifiche) {
                            if (campoNotifica.startsWith("\"paProtocolNumber\"")) {
                                String[] rigaNumeroProtocollo = campoNotifica.split(":\"");
                                return rigaNumeroProtocollo[1];
                            }

                        }
                    }
                }
            }
        }
        return null;
    }

    @And("Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica")
    public void nellaPaginaPiattaformaNotificheCliccareSulBottoneInviaUnaNuovaNotifica() {
        logger.info("Selezione bottone invia una nuova notifica");
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.selectInviaUnaNuovaNotificaButton();
        piattaformaNotifichePage.waitLoadingSpinner();
    }

    @And("Si finalizza l'invio della notifica e si controlla che venga creata correttamente")
    public void siFinalizzaLInvioDellaNotificaESiControllaCheVengaCreataCorrettamente() {
        logger.info("Si finalizza l'invio della notifica e si controlla che venga creata correttamente");
        siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionAllegati();
        nellaSectionAllegatiSiCaricaUnAtto();
        nellaSectionAllegatiCliccareSulBottoneInvia();
        siVisualizzaCorrettamenteLaFraseLaNotificaEStataCorrettamenteCreata();
        cliccareSulBottoneVaiAlleNotifiche();
        siVisualizzaCorrettamenteLaPaginaPiattaformaNotifiche();
        siVerificaCheLaNotificaEStataCreataCorrettamente();
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionInformazioniPreliminari() {
        logger.info("Verifica visualizzazione section Informazioni preliminari");
        headerPASection.waitLoadHeaderSection();
        informazioniPreliminariPASection.waitLoadInformazioniPreliminariPASection();
    }

//    @And("Nella section Informazioni preliminari inserire i dati della notifica senza pagamento")
//    public void nellaSectionInformazioniPreliminariInserireIDatiDellaNotificaSenzaPagamento() {
//        logger.info("Inserimento dei dati della notifica senza pagamento" );
//        String gruppo = "";
//        switch (webDriverConfig.getEnvironment()) {
//            case "dev" -> gruppo = dataPopulationConfig.getDatiNotifica().getGruppoDev();
//            case "test", "uat" -> gruppo = dataPopulationConfig.getDatiNotifica().getGruppoTest();
//        }
//        informazioniPreliminariPASection.insertOggettoNotifica(dataPopulationConfig.getDatiNotifica().getOggettoDellaNotifica());
//        informazioniPreliminariPASection.insertDescrizione(dataPopulationConfig.getDatiNotifica().getDescrizione());
////        informazioniPreliminariPASection.insertNumeroDiProtocollo(dataPopulationConfig.getDatiNotifica().getNumeroProtocollo());
//        informazioniPreliminariPASection.insertNumeroDiProtocollo(WebTool.generatePaProtocolNumber());
//        informazioniPreliminariPASection.insertGruppo(gruppo);
////        informazioniPreliminariPASection.insertGruppo("GruppoTest");
//        informazioniPreliminariPASection.insertCodiceTassonometrico(dataPopulationConfig.getDatiNotifica().getCodiceTassonometrico());
//        informazioniPreliminariPASection.selectRaccomandataAR();
//    }

    @And("Nella section Informazioni preliminari inserire i dati della notifica senza pagamento {string}")
    public void nellaSectionInformazioniPreliminariInserireIDatiDellaNotificaSenzaPagamento(String numeroNotificaID) {
                logger.info("Inserimento dei dati della notifica senza pagamento" );
        String gruppo = "";
        switch (webDriverConfig.getEnvironment()) {
            case "dev" -> gruppo = dataPopulationConfig.getDatiNotifica().getGruppoDev();
            case "test" -> gruppo = dataPopulationConfig.getDatiNotifica().getGruppoTest();
            case "uat" -> gruppo = dataPopulationConfig.getDatiNotifica().getGruppoUat();
        }
        informazioniPreliminariPASection.insertOggettoNotifica(dataPopulationConfig.getDatiNotifica().getOggettoDellaNotifica()+" -> "+numeroNotificaID);
        informazioniPreliminariPASection.insertDescrizione(dataPopulationConfig.getDatiNotifica().getDescrizione()+" -> "+numeroNotificaID);
//        informazioniPreliminariPASection.insertNumeroDiProtocollo(dataPopulationConfig.getDatiNotifica().getNumeroProtocollo());
        informazioniPreliminariPASection.insertNumeroDiProtocollo(WebTool.generatePaProtocolNumber());
        informazioniPreliminariPASection.insertGruppo(gruppo);
//        informazioniPreliminariPASection.insertGruppo("GruppoTest");
        informazioniPreliminariPASection.insertCodiceTassonometrico(dataPopulationConfig.getDatiNotifica().getCodiceTassonometrico());
        informazioniPreliminariPASection.selectRaccomandataAR();
    }

    @And("Nella section Informazioni preliminari inserire i dati della notifica senza pagamento senza gruppo con lingua {string}")
    public void nellaSectionInformazioniPreliminariInserireIDatiDellaNotificaSenzaPagamentoSenzaGruppoConLingua(String lingua) {
        logger.info("Inserimento dei dati della notifica senza pagamento" );
        //datiNotifica
        aggiornamentoNumeroProtocollo();

        // Compilo i campi se ho scelto una lingua diverso dall'Italiano
        switch (lingua.toLowerCase()) {
            case "francese" -> {
                informazioniPreliminariPASection.insertOggettoNotificaLinguaStraniera(dataPopulationConfig.getDatiNotifica().getOggettoDellaNotificaFr());
                informazioniPreliminariPASection.insertDescrizioneLinguaStraniera(dataPopulationConfig.getDatiNotifica().getDescrizioneFr());
            }
            case "tedesco" -> {
                informazioniPreliminariPASection.insertOggettoNotificaLinguaStraniera(dataPopulationConfig.getDatiNotifica().getOggettoDellaNotificaDe());
                informazioniPreliminariPASection.insertDescrizioneLinguaStraniera(dataPopulationConfig.getDatiNotifica().getDescrizioneDe());
            }
            case "sloveno" -> {
                informazioniPreliminariPASection.insertOggettoNotificaLinguaStraniera(dataPopulationConfig.getDatiNotifica().getOggettoDellaNotificaSl());
                informazioniPreliminariPASection.insertDescrizioneLinguaStraniera(dataPopulationConfig.getDatiNotifica().getDescrizioneSl());
            }
            default -> logger.warn("Lingua non riconosciuta: {}", lingua);
        }

        informazioniPreliminariPASection.insertOggettoNotifica(dataPopulationConfig.getDatiNotifica().getOggettoDellaNotifica());
        informazioniPreliminariPASection.insertDescrizione(dataPopulationConfig.getDatiNotifica().getDescrizione());
        informazioniPreliminariPASection.insertNumeroDiProtocollo(dataPopulationConfig.getDatiNotifica().getNumeroProtocollo());
        informazioniPreliminariPASection.insertCodiceTassonometrico(dataPopulationConfig.getDatiNotifica().getCodiceTassonometrico());
        informazioniPreliminariPASection.selectRaccomandataAR();
    }

    @And("Nella section Informazioni preliminari inserire i dati della notifica senza pagamento con nuovi codiceTassonomici {string}")
    public void nellaSectionInformazioniPreliminariInserireIDatiDellaNotificaSenzaPagamentoConNuoviCodiceTassonomici(String codiceTassonomico) {
        logger.info("Inserimento dei dati della notifica senza pagamento" );
        //datiNotifica
        aggiornamentoNumeroProtocollo();
        String gruppo = "";
        switch (webDriverConfig.getEnvironment()) {
            // case "dev" -> gruppo = datiNotifica.get("gruppoDev").toString();
            // case "test", "uat" -> gruppo = datiNotifica.get("gruppoTest").toString();
            case "dev" -> gruppo = dataPopulationConfig.getDatiNotifica().getGruppoDev();
            case "test", "uat" -> gruppo = dataPopulationConfig.getDatiNotifica().getGruppoTest();
        }
        informazioniPreliminariPASection.insertOggettoNotifica(dataPopulationConfig.getDatiNotifica().getOggettoDellaNotifica());
        informazioniPreliminariPASection.insertDescrizione(dataPopulationConfig.getDatiNotifica().getDescrizione());
        informazioniPreliminariPASection.insertNumeroDiProtocollo(dataPopulationConfig.getDatiNotifica().getNumeroProtocollo());
        informazioniPreliminariPASection.insertGruppo(gruppo);
        informazioniPreliminariPASection.insertCodiceTassonometrico(codiceTassonomico);
        informazioniPreliminariPASection.selectRaccomandataAR();
    }


    private void aggiornamentoNumeroProtocollo() {
        logger.info("Aggiornamento del numero protocollo");
        String numeroProtocolOld = dataPopulationConfig.getDatiNotifica().getNumeroProtocollo();
        String numeroProtocolNew;
        do {
            numeroProtocolNew = WebTool.generatePaProtocolNumber();
        } while (numeroProtocolOld.equals(numeroProtocolNew));
        dataPopulationConfig.getDatiNotifica().setNumeroProtocollo(numeroProtocolNew);

    }

    @And("Cliccare su continua")
    public void cliccareSuContinua() {
        logger.info("Cliccare sul bottone continua");
        invioNotifichePAPage.selezionareContinuaButton();
    }

    @And("Cliccare su Torna a")
    public void cliccareSuTornaA() {
        logger.info("Cliccare sul bottone Torna a");
        invioNotifichePAPage.selezionareTornaAButton();
    }

    @And("Aspetta {int} secondi")
    public void aspettaSecondi(int quantiSecondi) {
        logger.info("Aspetta " + quantiSecondi + " secondi");

        try {
            TimeUnit.SECONDS.sleep(quantiSecondi);
            driver.navigate().refresh();
        } catch (Exception exc) {
            logger.error(exc.toString());
            throw new RuntimeException(exc);
        }
    }

    @And("Attesa {int} secondi")
    public void attesaSecondi(int quantiSecondi) {
        logger.info("Attesa " + quantiSecondi + " secondi");
        webTool.waitTime(quantiSecondi);
    }


    @And("Si visualizza correttamente la timeline relativi a tutti i destinatari")
    public void siVisualizzaCorrettamenteLaTimelineRelativiATuttiIDestinatari(Map<String, String> destinatari) {
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.visualizzaTimelineTuttiDestinatari(destinatari);
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionDestinatario() {
        logger.info("Verifica visualizzazione della section Destinatario");

        headerPASection.waitLoadHeaderSection();

        destinatarioPASection.waitLoadDestinatarioPASection();
    }

    @And("Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica {string}")
    public void nellaSectionDestinatarioInserireNomeCognomeECodiceFiscaleDaDestinatario(String destinatarioFile) {
        logger.info("Inserimento del nome cognome e codice fiscale ");
        destinatarioPASection.selezionarePersonaFisica();
        destinatarioPASection.inserireNomeDestinatario(dataPopulationConfig.getPersonaFisica().getName());
        destinatarioPASection.inserireCognomeDestinatario(dataPopulationConfig.getPersonaFisica().getFamilyName());
        destinatarioPASection.inserireCodiceFiscaleDestinatario(dataPopulationConfig.getPersonaFisica().getCodiceFiscale());
    }

    @And("Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica {string} destinatario {int}")
    public void nellaSectionDestinatarioCliccareSuAggiungiIndirizzoFisicoCompilareIDatiDelDestinatario(String personaFisicaFile, int recipientIndex) {
        logger.info("Inserimento dei dati mancanti nella section destinatario");
        headerPASection.waitLoadHeaderSection();
        //personaFisicaPec
        //personaFisica

        //destinatarioPASection.selezionaAggiungiUnIndirizzoFisico();
        populateDestinatarioPASection(personaFisicaFile, recipientIndex);
    }



    @And("Nella section Destinatario settare come CAP {string}")
    public void nellaSectionDestinatarioSettareComeCAP(String cap) {
        // this assumes that previously the test scenario enforces the destinatarioPASection to appear,
        // e.g. through siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionDestinatario

        destinatarioPASection.cambiareCodicePostale(cap,0);
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionAllegati() {
        logger.info("Verifica visualizzazione della section Allegati");

        headerPASection.waitLoadHeaderSection();
        allegatiPASection.waitLoadAllegatiPASection();
    }

    @And("Nella section Allegati caricare l'atto e inserire il nome atto {string}")
    public void nellaSectionAllegatiCaricareLAttoEInserireIlNomeAtto(String datiNotificaFile) {
        logger.info("Caricamento dell'allegato notifica.pdf");

        File notificaFile = new File("src/test/resources/notifichePdf/notifica.pdf");
        String pathNotificaFile = notificaFile.getAbsolutePath();
        allegatiPASection.caricareNotificaPdfDalComputer(pathNotificaFile);

        if (allegatiPASection.verificaCaricamentoNotificaPdf()) {
            logger.info("File notifica.pdf caricato correttamente");
        } else {
            logger.error("File notifica.pdf non caricato");
            Assertions.fail("File notifica.pdf non caricato");
        }

        allegatiPASection.inserimentoNomeAllegato(dataPopulationConfig.getDatiNotifica().getNomeDocumentoNotifica());
    }

    @And("Nella section Allegati cliccare sul bottone Invia")
    public void nellaSectionAllegatiCliccareSulBottoneInvia() {
        logger.info("Cliccare sul bottone Invia");

        allegatiPASection.selectInviaButton();
        webTool.waitTime(3);
        /**
        if (allegatiPASection.verificaMessaggioErrore()) {
            aggiornamentoNumeroProtocolloAllegati();
            logger.error("Si vede il messaggio di dati non corretti");
            Assertions.fail("Si vede il messaggio di dati non corretti");
        }
         **/
    }

    @And("Nella section Allegati cliccare sul bottone Invia Posizione Debitoria")
    public void nellaSectionAllegatiCliccareSulBottoneInviaPosizioneDebitoria() {
        logger.info("Cliccare sul bottone Invia");

        allegatiPASection.selectInviaButtonPosizioneDebitoria();
        webTool.waitTime(4);

    }

    private void aggiornamentoNumeroProtocolloAllegati() {
        logger.info("Aggiornamento del numero protocollo");

        String numeroPotocolloKey = "numeroProtocollo";
        String numeroProtocolOld = dataPopulationConfig.getDatiNotifica().getNumeroProtocollo();
        String dataProtocolOld = substring(numeroProtocolOld, 10, 18);
        String counter = substring(numeroProtocolOld, 19);

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dataProtocol = dateFormat.format(date);

        String numeroProtocol;
        if (dataProtocol.equalsIgnoreCase(dataProtocolOld)) {

            /*
            da 0 a 9 numeri 0 a 9
            da 17 a 42 lettere maiuscole da A a Z
            */

            String temp = null;
            if (counter.equals("9")) {
                temp = String.valueOf((char) (counter.charAt(0) + 8));
            } else if (counter.equals("Z")) {
                Assertions.fail(numeroProtocolOld + " oltre questo numero protocollo per la giornata di : " + dataProtocolOld + " non si può andare");
            } else {
                temp = String.valueOf((char) (counter.charAt(0) + 1));
            }

            counter = temp;
            logger.info(counter);
            numeroProtocol = "TA-FFSMRC-" + dataProtocol + "-" + counter;
        } else {
            numeroProtocol = "TA-FFSMRC-" + dataProtocol + "-0";
        }

        logger.info("numero Protocollo generato : " + numeroProtocol);

        dataPopulationConfig.getDatiNotifica().setNumeroProtocollo(numeroProtocol);

    }

    @Then("Si visualizza correttamente la frase La notifica è stata correttamente creata")
    public void siVisualizzaCorrettamenteLaFraseLaNotificaEStataCorrettamenteCreata() {
        logger.info("Verifica visualizzazione frase: La notifica è stata correttamente creata");

        headerPASection.waitLoadHeaderSection();

        successPASection.waitLoadSuccessPASection();

    }

    @And("Cliccare sul bottone vai alle notifiche")
    public void cliccareSulBottoneVaiAlleNotifiche() {
        logger.info("Si clicca sul bottone 'vai alle notifiche'");

        successPASection.vaiAlleNotifiche();
    }

    @And("Verifica dello stato della notifica come depositata {string}")
    public void verificaDelloStatoDellaNotificaComeDepositata(String statoNotifica) {
        logger.info("Verifica dello stato della notifica come 'Depositata'");

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String dataNotifica = dateFormat.format(date).replace("-", "/");

        String coidiceIUNOld = dataPopulationConfig.getDatiNotifica().getCodiceIUN();
        for (int i = 0; i < 12; i++) {
            if (i >= 1) {
                piattaformaNotifichePage.aggiornamentoPagina();
                piattaformaNotifichePage.insertCodiceFiscale(dataPopulationConfig.getPersonaFisica().getCodiceFiscale());
                piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica,true);
                piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
            }
            piattaformaNotifichePage.setHooksNew(hooksNew);
            piattaformaNotifichePage.waitLoadRefreshPage();
            String codiceIUN = piattaformaNotifichePage.ricercaNotifica(dataPopulationConfig.getDatiNotifica().getOggettoDellaNotifica(), statoNotifica);
            if (!codiceIUN.equals("")) {
                if (!codiceIUN.equals(coidiceIUNOld)) {
                    dataPopulationConfig.getDatiNotifica().setCodiceIUN(codiceIUN);
                    logger.info("Stato notifica uguale a Depositata e codice IUN aggiornato correttamente");
                    return;
                }
            }
        }

        logger.error("Il server ha impiegato troppo tempo nel generare la notifica");
        Assertions.fail("Il server ha impiegato troppo tempo nel generare la notifica");

    }

    @And("Nella pagina Piattaforma Notifiche accetta i Cookies")
    public void nellaPaginaPiattaformaNotificheAccettaICookies() {
        if (!webDriverManager.getCookieConfig().isCookieEnabled()) {
            cookiesSection.waitLoadCookiesPage();
            cookiesSection.selezionaAccettaTuttiButton();
        }
    }

    @When("Cliccare sulla notifica restituita")
    public void cliccareSullaNotificaRestituita() {
        logger.info("Si clicca sulla notifica");
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.selezionaPrimaNotifica();
    }
    @When("Cliccare sulla notifica restituita dal filtro")
    public void cliccareSullaNotificaRestituitaDalFiltro() {
        logger.info("Si clicca sulla notifica dal filtro");
        piattaformaNotifichePage.selezionaPrimaNotifica();
    }

    @When("Cliccare sulla notifica  maggiore di 120 giorni")
    public void cliccareSullaNotificaRestituita120Giorni() {
        logger.info("Si clicca sulla notifica maggiore di 120 giorni");
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.selezionaNotifica120Giorni();
    }

    @When("Cliccare sulla notifica  maggiore di 120 giorni {int}")
    public void cliccareSullaNotificaRestituita120Giorni(Integer index) {
        logger.info("Si clicca sulla notifica maggiore di 120 giorni");
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.selezionaNotifica120Giorni(index);
    }

    @And("Salva codice IUN")
    public void salvaCodiceIUN(){
        String IUN = dettaglioNotificaMittenteSection.salvaIUN();
        setIun(IUN);
    }

    @And("Salva Api key")
    public void salvaApiKey(){
        String apiKey = dettaglioNotificaMittenteSection.getApiKey();
        setApiKey(apiKey);
    }

    @And("Si verifica che Api Key sono diversi")
    public void verificaDiversiApiKey(){
        String actualApiKey = dettaglioNotificaMittenteSection.getApiKey();
        Assertions.assertFalse(actualApiKey.equalsIgnoreCase(ApiKey));
        logger.info("Api Keys sono diversi");
    }

    @And("viene inserito codice IUN salvato")
    public void vieneInseritoIunSalvato() {
        dettaglioNotificaMittenteSection.insertIunSalvatoAndRicercaOnPage(Iun);
//        dettaglioNotificaMittenteSection.insertIunSalvatoAndRicercaOnPage("XVAU-NMXQ-RKUL-202408-Y-1");
    }

    @And("Mittente ricerca notifica con IUN salvato")
    public void ricercaNotificaConIunSalvatoMittente(){
        dettaglioNotificaMittenteSection.ricercaNotificaConIunSalvatoMittente(Iun);
    }

    @And("Destinatario ricerca notifica con IUN salvato")
    public void ricercaNotificaConIunSalvatoDestinatario(){
        dettaglioNotificaMittenteSection.ricercaNotificaConIunSalvatoDestinatario(Iun);
    }

    @And("Mittente ricerca notifica con IUN salvato {string}")
    public void ricercaNotificaConIunMittente(String iun){
        dettaglioNotificaMittenteSection.ricercaNotificaConIunSalvatoMittente(iun);
    }

    @And("Si visualizza correttamente la section Dettaglio Notifica")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotifica() {
        dettaglioNotificaMittenteSection.waitLoadDettaglioNotificaSection();
    }

    @And("Nella pagina dettaglio notifica cliccare sull'opzione vedi più dettagli")
    public void nellaPaginaDettaglioNotificaCliccareSullOpzioneVediPiuDettagli() {
        dettaglioNotificaMittenteSection.waitLoadDettaglioNotificaSection();
        this.dettaglioNotificaMittenteSection.clickVediPiuDettagli();
    }

    @And("Si visualizza correttamente l elenco completo degli stati che la notifica ha percorso")
    public void siVisualizzaCorrettamenteLElencoCompletoDegliStatiCheLaNotificaHaPercorso() {
        dettaglioNotificaMittenteSection.waitLoadDettaglioNotificaSection();
        dettaglioNotificaMittenteSection.siVisualizzaPercorsoNotifica();
    }

    @Then("Si clicca sul bottone indietro")
    public void siCliccaSulBottoneIndietro() {
        dettaglioNotificaMittenteSection.clickIndietroButton();
    }

    @And("Nella pagina Piattaforma Notifiche visualizzano correttamente i filtri di ricerca")
    public void nellaPaginaPiattaformaNotificheVisualizzanoCorrettamenteIFiltriDiRicerca() {
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.siVisualizzaCorrettamenteIlCFField();
        piattaformaNotifichePage.siVisualizzaCorrettamenteIlCodiceIUNField();
        piattaformaNotifichePage.siVisualizzaCorrettamenteLoStatoField();
        piattaformaNotifichePage.siVisualizzaCorrettamenteLaDataInzioField();
        piattaformaNotifichePage.siVisualizzaCorrettamenteLaDataFineField();
    }

    @Then("Nella pagina Piattaforma Notifiche si visualizza correttamente l'elenco delle notifiche")
    public void nellaPaginaPiattaformaNotificheSiVisualizzaCorrettamenteLElencoDelleNotifiche() {
        logger.info("Si visualizza l'elenco delle notifiche");
        piattaformaNotifichePage.setHooksNew(hooksNew);
        int nDateNotifiche = piattaformaNotifichePage.controlloNumeroRisultatiDate();
        if (nDateNotifiche >= 1) {
            logger.info("Le date delle notifiche vengono visualizzate correttamente");
        } else {
            logger.error("Le date delle notifiche non vengono visualizzate correttamente");
        }
        if (piattaformaNotifichePage.verificaEsistenzaCFNotifiche()) {
            logger.info("I codici fiscali delle notifiche vengono visualizzati correttamente");
        } else {
            logger.error("I codici fiscali delle notifiche non vengono visualizzati correttamente");
            Assertions.fail("I codici fiscali delle notifiche non vengono visualizzati correttamente");
        }
        List<WebElement> listaOggetti = piattaformaNotifichePage.ricercaListaOggetti();
        if (listaOggetti != null && listaOggetti.size() >= 1) {
            logger.info("La lista degli oggetti viene visualizzata correttamente");
        } else {
            logger.error("La lista degli oggetti non viene visualizzata correttamente");
            Assertions.fail("La lista degli oggetti non viene visualizzata correttamente");
        }

        if (piattaformaNotifichePage.verificaEsistenzaCodiceIUNNotifiche()) {
            logger.info("La lista dei codici iun viene visualizzata correttamente");
        } else {
            logger.error("La lista dei codici iun non viene visualizzata correttamente");
            Assertions.fail("La lista dei codici iun non viene visualizzata correttamente");
        }

        if (piattaformaNotifichePage.verificaEsistenzaGruppoNotifiche()) {
            logger.info("La lista dei gruppi vengono visualizzate correttamente");
        } else {
            logger.error("La lista dei gruppi non vengono visualizzate correttamente");
            Assertions.fail("La lista dei gruppi non vengono visualizzate correttamente");
        }

        if (piattaformaNotifichePage.verificaEsistenzaStatoNotifiche()) {
            logger.info("La lista degli stati viene visualizzata correttamente");
        } else {
            logger.error("La lista degli stati non viene visualizzata correttamente");
            Assertions.fail("La lista degli stati non viene visualizzata correttamente");
        }
    }

    @Then("Nella section Destinatario viene visualizzato un solo destinatario")
    public void nellaSectionDestinatarioVieneVisualizzatoUnSoloDestinatario() {
        logger.info("Verifica visualizzazione di un solo destinatario");

        if (destinatarioPASection.verificaNumeroDestinatari()) {
            logger.info("Viene visualizzato un solo destinatario");
        } else {
            logger.error("Viene visualizzato più di un destinatario");
            Assertions.fail("Viene visualizzato più di un destinatario");
        }
    }

    @And("Nella pagina Piattaforma Notifiche si visualizzano le notifiche a partire dalla più recente")
    public void nellaPaginaPiattaformaNotificheSiVisualizzanoLeNotificheAPartireDallaPiuRecente() {
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.controlloOrdineNotifiche();
    }

    @And("Nella pagina Piattaforma Notifiche si scrolla fino alla fine della pagina")
    public void nellaPaginaPiattaformaNotificheSiScrollaFinoAllaFineDellaPagina() {
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.siScrollaFinoAllaFineDellaPagina();
    }

    @And("Nella pagina Piattaforma Notifiche si controlla che vengano visualizzate dieci notifiche")
    public void nellaPaginaPiattaformaNotificheSiControllaCheVenganoVisualizzateNotifiche() {
        logger.info("si controlla che vengono visualizzate dieci notifiche");
        piattaformaNotifichePage.setHooksNew(hooksNew);
        String nNotificheInviate = piattaformaNotifichePage.numeroNotifiche();
        if (nNotificheInviate.equals("10")) {
            logger.info("Il numero di notifiche é corretto");
        } else {
            logger.error("Il numero di notifiche non é corretto");
            Assertions.fail("Il numero di notifiche non é corretto");
        }
    }

    @And("Nella pagina Piattaforma Notifiche si cambia pagina utilizzando una freccetta")
    public void nellaPaginaPiattaformaNotificheSiCambiaPaginaUtilizzandoUnaFreccetta() {
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.siCambiaPaginaUtilizzandoUnaFrecetta(1);
    }

    @And("Nella pagina stato della piattaforma si cambia pagina utilizzando una freccetta {int}")
    public void nellaPaginaStatoDellaPiattaformaSiCambiaPaginaUtilizzandoUnaFreccetta(Integer numPage) {
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.siCambiaPaginaUtilizzandoUnaFrecetta(numPage);
    }

    @And("Nella pagina stato della piattaforma si cambia pagina utilizzando una freccetta fino all'ultima")
    public void nellaPaginaStatoDellaPiattaformaSiCambiaPaginaUtilizzandoUnaFreccetta() {
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.selezionaUltimaPaginaUtilizzandoUnaFrecetta();
    }

    @And("Nella pagina stato della piattaforma si cambia pagina")
    public void nellaPaginaStatoDellaPiattaformaSiCambiaPagina() {
        piattaformaNotifichePage.setHooksNew(hooksNew);
        int pagina = piattaformaNotifichePage.getPageMeseCorrente();
//        piattaformaNotifichePage.selezionaFrecettaFinoaPagina(2);
        piattaformaNotifichePage.selezionaFrecettaFinoaPagina(pagina);
    }

    @And("Nella pagina stato della piattaforma si cambia pagina utilizzando una freccetta fino alla pagina {int}")
    public void nellaPaginaStatoDellaPiattaformaSiCambiaPaginaUtilizzandoUnaFreccettaFinoPagina(Integer pagina) {
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.selezionaFrecettaFinoaPagina(pagina);
    }



    @And("Nella pagina Piattaforma Notifiche si cambia pagina utilizzando un numero")
    public void nellaPaginaPiattaformaNotificheSiCambiaPaginaUtilizzandoUnNumero() {
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.siCambiaPaginaUtilizzandoUnNumero();
    }

    @Then("Nella pagina Piattaforma Notifiche si cambia il numero elementi visualizzati attraverso il filtro")
    public void nellaPaginaPiattaformaNotificheSiCambiaIlNumeroElementiVisualizzatiAttraversoIlFiltroNumeroNotifiche() {
        piattaformaNotifichePage.siCambiaIlNumeroElementiVisualizzatiAttraversoIlFiltro();
    }

    @And("Nella pagina Piattaforma Notifiche si controlla che vengano visualizzate tutte notifiche")
    public void nellaPaginaPiattaformaNotificheSiControllaCheVenganoVisualizzateTutteNotifiche() {
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.waitLoadPage();
        int numeroRighe = piattaformaNotifichePage.getNRighe();
        if (numeroRighe > 10) {
            logger.info("Numero righe differente da quello di default");
        } else {
            logger.error("Numero righe uguale da quello di default");
            Assertions.fail("Numero righe uguale da quello di default");
        }
    }

    @And("Nella section Destinatario cliccare su Aggiungi destinatario")
    public void nellaSectionDestinatarioCliccareSuAggiungiDestinatario() {
        logger.info("Si sta cercando di selezionare il bottone aggiungere Destinatario");

        destinatarioPASection.selezionareAggiungiDestinatarioButton();
    }

    @And("Nella section Destinatario cliccare su Rimuovi destinatario")
    public void nellaSectionDestinatarioCliccareSuRimuoviDestinatario() {
        logger.info("Si sta cercando di selezionare il bottone rimuovi Destinatario");

        destinatarioPASection.selezionareRimuoviDestinatarioButton();
    }

    @And("^Nella section Destinatario inserire i dati delle persone fisiche aggiuntive per (.*)$")
    public void nellaSectionDestinatarioInserireIDatiDelDestinatariAggiuntiviPerNumeroDestinatari(String npersoneFisiche) {
        logger.info("Si cerca di aggiungere" + npersoneFisiche + " persone Fisiche");
        PersoneFisiche personeFisiche=dataPopulationConfig.getPersoneFisiche();
        int nPersoneFisicheInt = 1;
        if (isNumeric(npersoneFisiche)) {
            nPersoneFisicheInt = Integer.parseInt(npersoneFisiche) - 1;
            if (nPersoneFisicheInt > 5 || nPersoneFisicheInt == 0) {
                Assertions.fail("Devi inserire un nummero da 1 a 6");
            }
        } else {
            Assertions.fail("Formato non accettato. Devi inserire un numero da 1 a 6");
        }

        destinatarioPASection.inserimentoMultiDestinatario(personeFisiche, nPersoneFisicheInt);
    }

    @And("Nella section Destinatario si cerca di aggiungere il sesto destinatario")
    public void nellaSectionDestinatarioSiCercaDiAggiungereIlSestoDestinatario() {
        logger.info("Si cerca di inserire il sesto destinatario");


        if (destinatarioPASection.inserireIlSestoDestinatario()) {
            logger.info("Non si riesce ad aggiungere il sesto destinatario");
        } else {
            logger.error("Si riesce ad aggiungere il sesto destinatario");
            Assertions.fail("Si riesce ad aggiungere il sesto destinatario");
        }
    }

    @And("Nella section Destinatario si inserisce lo stesso destinatario di prima {string}")
    public void nellaSectionDestinatarioSiInserisceLoStessoDestinatarioDiPrima(String dpFile) {
        logger.info("Si inserisce lo stesso destinatario di prima");
        PersoneFisiche personeFisiche=dataPopulationConfig.getPersoneFisiche();
        destinatarioPASection.inserimentoMultiDestinatario(personeFisiche, 1);
    }

    @Then("Si visualizza correttamente l errore di stesso codice fiscale")
    public void siVisualizzaCorrettamenteLErroreDiStessoCodiceFiscale() {
        logger.info("Si visualizzano i messaggi di errore del campo codice fiscale");
        destinatarioPASection.waitMessaggioErrore();
    }

    @And("Si verifica che la notifica sia nello stato avanzato")
    public void siVerificaCheLaNotificaSiaNelloStato() {

        boolean notificaTrovata = false;
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!piattaformaNotifichePage.IsAnAdvancedStatus()) {
                piattaformaNotifichePage.aggiornamentoPagina();
                piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
                piattaformaNotifichePage.inserimentoCodiceIUN(dataPopulationConfig.getDatiNotifica().getCodiceIUN());
                piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
            } else {
                notificaTrovata = true;
                break;
            }
        }
        if (!notificaTrovata) {
            Assertions.fail("La notifica non è stata trovata dopo 1m40s");
        }
    }

    @And("Si verifica che l'invio della pec sia in corso")
    public void siVerificaCheLInvioDellaPecSiaInCorso() {
        dettaglioNotificaMittenteSection.clickVediPiuDettagli();
        dettaglioNotificaMittenteSection.verificaInvioPECInCorso();
    }

    @And("Verifica dello stato della notifica inviata tramite pec come {string}")
    public void verificaDelloStatoDellaNotificaInviataTramitePecCome(String statoNotifica) {


        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String dataNotifica = dateFormat.format(date).replace("-", "/");

        String codiceIUNOld = dataPopulationConfig.getDatiNotifica().getCodiceIUN();
        for (int i = 0; i < 12; i++) {
            if (i >= 1) {
                piattaformaNotifichePage.aggiornamentoPagina();
                if (!webDriverManager.getCookieConfig().isCookieEnabled()) {
                    if (cookiesSection.waitLoadCookiesPage()) {
                        cookiesSection.selezionaAccettaTuttiButton();
                    }
                }
                piattaformaNotifichePage.insertCodiceFiscale(dataPopulationConfig.personaFisicaPec().getComune());
                piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica, true);
                piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
            }
            piattaformaNotifichePage.waitLoadRefreshPage();
            String codiceIUN = piattaformaNotifichePage.ricercaNotifica(dataPopulationConfig.getDatiNotifica().getOggettoDellaNotifica(), statoNotifica);
            if (!codiceIUN.equals("")) {
                if (!codiceIUN.equals(codiceIUNOld)) {
                    dataPopulationConfig.getDatiNotifica().setCodiceIUN(codiceIUN);
                    logger.info("Stato notifica uguale a Depositata e codice IUN aggiornato correttamente");
                    return;
                }
            }
        }

        logger.error("Il server ha impiegato troppo tempo nel generare la notifica");
        Assertions.fail("Il server ha impiegato troppo tempo nel generare la notifica");

    }

    @And("Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica pec {string}")
    public void nellaPaginaPiattaformaNotificheInserireIlCodiceIUNDellaNotificaPec(String dpDatiiNotifica) {
        logger.info("Si inserisce il codice IUN");

        piattaformaNotifichePage.aggiornamentoPagina();
        piattaformaNotifichePage.waitLoadRefreshPage();
        piattaformaNotifichePage.inserimentoCodiceIUN(dataPopulationConfig.getDatiNotifica().getCodiceIUN());
    }

    @And("Nella section si prova ad cliccare sul tasto continua senza aver inserito nessun dato")
    public void nellaSectionInformazioniPreliminariSiProvaAdCliccareSulTastoContinuaSenzaAverInseritoNessunDato() {
        logger.info("Si clicca sul tasto continua senza aver inserito nessun dato");
        piattaformaNotifichePage.clickContinuaDisabled();
    }

    @And("Nella section Informazioni preliminari inserire i dati della notifica sbagliati senza pagamento")
    public void nellaSectionInformazioniPreliminariInserireIDatiDellaNotificaSbagliatiSenzaPagamento() {
        logger.info("Si inserisce l'oggetto della notifica errato");
        piattaformaNotifichePage.inserimentoOggettoNotificaErrato(dataPopulationConfig.getDatiNotificaErrore().getOggettoDellaNotifica());
    }

    @And("Nella section Informazioni preliminari si visualizza un messaggio di errore")
    public void nellaSectionInformazioniPreliminariSiVisualizzaUnMessaggioDiErrore() {
        logger.info("Si verifica sia presente un messaggio di errore");

        if (piattaformaNotifichePage.errorMessage()) {
            logger.info("Si visualizza correttamente il messaggio di errore");
        } else {
            logger.error("Non si visualizza correttamente il messaggio di errore");
            Assertions.fail("Non si visualizza correttamente il messaggio di errore");
        }
    }

    @And("Nella section cliccare sul tasto indietro")
    public void nellaSectionInformazioniPreliminariCliccareSulTastoIndietro() {
        logger.info("Si clicca sul tasto indietro");
        piattaformaNotifichePage.clickIndietroButton();
    }

    @And("Nella section si visualizza il popup vuoi uscire")
    public void nellaSectionInformazioniPreliminariSiVisualizzaIlPopupVuoiUscire() {
        logger.info("Si verifica sia presente il pop-up vuoi uscire");

        piattaformaNotifichePage.vuoiUscirePopUp();
    }

    @And("Nella section cliccare sul tasto esci")
    public void nellaSectionInformazioniPreliminariCliccareSulTastoEsci() {
        logger.info("Si clicca sul tasto esci");

        piattaformaNotifichePage.clickSuEsci();
    }

    @And("Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica")
    public void nellaSectionDestinatarioInserireRagioneSocialeEPartitaIVADallaPersonaGiuridica() {
        logger.info("Si inserisce la ragione sociale e la partita iva della persona giuridica");
        //personaGiuridica
        destinatarioPASection.insertRagioneSociale(dataPopulationConfig.getPersonaGiuridica().getRagioneSociale());
        destinatarioPASection.insertPartitaIva(dataPopulationConfig.getPersonaGiuridica().getCodiceFiscale());
    }
    @And("Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica posizionale {int}")
    public void nellaSectionDestinatarioInserireRagioneSocialeEPartitaIVADallaPersonaGiuridicaposizionale(int posizione) {
        logger.info("Si inserisce la ragione sociale e la partita iva della persona giuridica posizionale");
        //personaGiuridica
        destinatarioPASection.insertRagioneSociale(dataPopulationConfig.getPersonaGiuridica().getRagioneSociale(),posizione);
        destinatarioPASection.insertPartitaIva(dataPopulationConfig.getPersonaGiuridica().getCodiceFiscale(),posizione);
    }

    @And("Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona giuridica")
    public void nellaSectionDestinatarioCliccareSuAggiungiDomicilioDigitaleCompilareIDatiDellaPersonaGiuridica() {
        logger.info("Si inserisce un domicilio digitale della persona giuridica");
        //personaGiuridica
        destinatarioPASection.checkBoxAggiungiDomicilio();
        destinatarioPASection.insertDomicilioDigitale(dataPopulationConfig.getPersonaGiuridica().getEmailPec());
    }

    @And("Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona giuridica {string} destinatario {int}")
    public void nellaSectionDestinatarioCliccareSuAggiungiIndirizzoFisicoCompilareIDatiDellaPersonaGiuridica(String dpFile, int recipientIndex) {
        logger.info("Si inseriscono i dati personali della persona giuridica");
        //personaGiuridica

        //destinatarioPASection.selezionaAggiungiUnIndirizzoFisico();
        destinatarioPASection.inserireIndirizzo(dataPopulationConfig.getPersonaGiuridica().getIndirizzo(),recipientIndex);
        destinatarioPASection.inserireNumeroCivico(dataPopulationConfig.getPersonaGiuridica().getNumeroCivico(),recipientIndex);
        destinatarioPASection.inserireLocalita(dataPopulationConfig.getPersonaGiuridica().getLocalita(),recipientIndex);
        destinatarioPASection.inserireComune(dataPopulationConfig.getPersonaGiuridica().getComune(),recipientIndex);
        destinatarioPASection.inserireProvincia(dataPopulationConfig.getPersonaGiuridica().getProvincia(),recipientIndex);
        destinatarioPASection.inserireCodicePostale(dataPopulationConfig.getPersonaGiuridica().getCodicePostale(),recipientIndex);
       // destinatarioPASection.inserireStato(dataPopulationConfig.getPersonaGiuridica().getStato(),recipientIndex);

    }

    @And("Nella section Destinatario inserire i dati errati dalla persona giuridica")
    public void nellaSectionDestinatarioInserireIDatiErratiDallaPersonaGiuridica() {
        logger.info("Si inseriscono i dati errati per persona giuridica");

        destinatarioPASection.checkBoxAggiungiDomicilio();
        destinatarioPASection.insertDomicilioDigitaleErrato(dataPopulationConfig.getPersonaGiuridicaErrore().getEmailPecErrore());
        Assertions.assertEquals( "Indirizzo PEC non valido", destinatarioPASection.getDomicilioDigitaleError(),"l'errore  attuale 'Indirizzo PEC non valido' è diverso di :" + destinatarioPASection.getDomicilioDigitaleError());

        destinatarioPASection.insertCodiceFiscaleErrato(dataPopulationConfig.getPersonaGiuridicaErrore().getCodiceFiscale());
        Assertions.assertEquals( "Il valore inserito non è corretto", destinatarioPASection.getCodiceFiscaleError(),"l'errore  attuale 'Il valore inserito non è corretto' è diverso di :" + destinatarioPASection.getCodiceFiscaleError());

    }

    @And("Nella section Allegati caricare l'atto e inserire il nome atto con estenzione non valida")
    public void nellaSectionAllegatiCaricareLAttoEInserireIlNomeAttoConEstenzioneNonValida() {
        logger.info("Si inserisce un file con estensione sbagliata");
        String pathDocumentiFile = System.getProperty("user.dir") + "/src/test/resources/dataPopulation/fileUpload/semiOfficial1.jpg";
        allegatiPASection.caricareNotificaPdfDalComputer(pathDocumentiFile);
    }

    @Then("Si visualizza correttamente il messaggio di errore estensione file non supportata. Riprovare con un altro file.")
    public void siVisualizzaCorrettamenteIlMessaggioDiErroreEstensioneFileNonSupportataRiprovareConUnAltroFile() {
        logger.info("Si controlla che si vede il messaggio di errore");

        if (piattaformaNotifichePage.estensioneSbagliataErrore()) {
            logger.info("Si visualizza correttamente il messaggio di errore: Estensione file non supportata. Riprovare con un altro file.");
        } else {
            logger.error("Non si visualizza correttamente il messaggio di errore: Estensione file non supportata. Riprovare con un altro file.");
            Assertions.fail("Non si visualizza correttamente il messaggio di errore: Estensione file non supportata. Riprovare con un altro file.");
        }
    }

    @And("Nella section Destinatario selezionare il radio button persona giuridica")
    public void nellaSectionDestinatarioSelezionareIlRadioButtonPersonaGiuridica() {
        logger.info("Si clicca su persona giuridica");
        destinatarioPASection.clickRadioButtonPersonaGiuridica();
    }

    @And("Nella section Destinatario selezionare il radio button persona giuridica posizionale {int}")
    public void nellaSectionDestinatarioSelezionareIlRadioButtonPersonaGiuridicaPosizionale(int posiione) {
        logger.info("Si clicca su persona giuridica posizionale");
        destinatarioPASection.clickRadioButtonPersonaGiuridicaPosizionale(posiione);
    }

    @And("^Nella section Destinatario inserire i dati del destinatari persone giuridiche aggiuntivi per (.*)$")
    public void nellaSectionDestinatarioInserireIDatiDelDestinatariPersoneGiuridicheAggiuntiviPerNumeroDestinatari(String nDestinatari) {
        logger.info("Si cerca di aggiungere " + nDestinatari + " personeGiuridiche");

        PersoneGiuridiche personeGiuridiche= dataPopulationConfig.personeGiuridiche();
        int nDestinatariInt = 1;
        if (isNumeric(nDestinatari)) {
            nDestinatariInt = Integer.parseInt(nDestinatari) - 1;
            if (nDestinatariInt > 5 || nDestinatariInt == 0) {
                Assertions.fail("Devi inserire un nummero da 1 a 6");
            }
        } else {
            Assertions.fail("Formato non accettato. Devi inserire un numero da 1 a 6");
        }

        destinatarioPASection.inserimentoMultiDestinatarioPG(personeGiuridiche, nDestinatariInt);
    }

    @And("Nella section cliccare sul tasto torna a informazioni preliminari")
    public void nellaSectionCliccareSulTastoTornaAInformazioniPreliminari() {
        logger.info("Si cerca di tornare alla sezione Informazione Preliminari");

        destinatarioPASection.clickSuTornaInformazioniPreliminari();
    }

    @And("Nella section Destinatario inserire i dati del secondo destinatario come persona fisica")
    public void nellaSectionDestinatarioInserireIDatiDelSecondoDestinatarioPersonaFisica(Map<String, String> destinatario) {
        logger.info("Si cerca di aggiungere la persona fisica aggiuntiva");
        destinatarioPASection.inserimentoSecondoDestinatarioPF(destinatario);
    }

    @And("Nella section Destinatario inserire i dati del secondo destinatario come persona giuridica")
    public void nellaSectionDestinatarioInserireIDatiDelSecondoDestinatarioPersonaGiuridica(Map<String, String> destinatario) {
        logger.info("Si cerca di aggiungere la persona giuridica aggiuntiva");
        destinatarioPASection.inserimentoDestinatarioPGAggiuntivo(destinatario);
    }

    @And("Nella section Destinatario inserire i dati del terzo destinatario come persona giuridica")
    public void nellaSectionDestinatarioInserireIDatiDelTerzoDestinatarioPersonaGiuridica(Map<String, String> destinatario) {
        logger.info("Si cerca di aggiungere la persona giuridica aggiuntiva");
        destinatarioPASection.inserimentoTerzoDestinatarioPG(destinatario);
    }

    @And("Verifica dello stato della notifica persona giuridica come depositata {string}")
    public void verificaDelloStatoDellaNotificaPersonaGiuridicaComeDepositata(String statoNotifica) {
        logger.info("Verifica dello stato della notifica come 'Depositata'");
        //TODO ATTUALMENTE non utilizzata
//        this.datiNotifica = dataPopulation.readDataPopulation("datiNotificaPG.yaml");
//        this.personaFisica = dataPopulation.readDataPopulation("personaGiuridica.yaml");

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String dataNotifica = dateFormat.format(date).replace("-", "/");
        String codiceFiscale = dataPopulationConfig.getPersonaFisica().getCodiceFiscale();

        String codiceIUNOld = dataPopulationConfig.getDatiNotificaPg().getCodiceIUN();
        String codiceIUN = "";
        for (int i = 0; i < 12; i++) {
            if (i >= 1) {
                piattaformaNotifichePage.aggiornamentoPagina();
                if (!webDriverManager.getCookieConfig().isCookieEnabled()) {
                    if (cookiesSection.waitLoadCookiesPage()) {
                        cookiesSection.selezionaAccettaTuttiButton();
                    }
                }
                piattaformaNotifichePage.insertCodiceFiscale(codiceFiscale);
                piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica,true);
                piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
            }
            piattaformaNotifichePage.waitLoadRefreshPage();
            codiceIUN = piattaformaNotifichePage.ricercaNotifica(dataPopulationConfig.getDatiNotificaPg().getOggettoDellaNotifica(), statoNotifica);
            if (!codiceIUN.equals("")) {
                if (!codiceIUN.equals(codiceIUNOld)) {
                    dataPopulationConfig.getDatiNotificaPg().setCodiceIUN(codiceIUN);
                    logger.info("Stato notifica uguale a Depositata e codice IUN aggiornato correttamente");
                    break;
                }
            }
        }
        if (codiceIUN.equals("")) {
            logger.error("Notifica non trovata il sistema ha impiegato troppo tempo a rispondere");
            Assertions.fail("Notifica non trovata il sistema ha impiegato troppo tempo a rispondere");
        }
    }

    @And("Nella pagina Piattaforma Notifiche si recupera l ultimo numero protocollo")
    public void nellaPaginaPiattaformaNotificheSiRecuperaLUltimoNumeroProtocollo() {
        String numeroProtocollo = getNumeroProtocollo();
        if (numeroProtocollo != null) {
            dataPopulationConfig.getDatiNotifica().setNumeroProtocollo(numeroProtocollo);
        }
    }

    @And("Si clicca sulla pagina numero {int} delle notifiche")
    public void siCliccaSullaSecondaPaginaDelleNotifiche(int pagina) {
        piattaformaNotifichePage.clickPagina(pagina);
    }

    @Then("Nella section Informazioni preliminari si inseriscono i dati della notifica")
    public void nellaSectionInformazioniPreliminariSiInserisconoIDatiDellaNotifica(Map<String, String> datiNotifica) {
        logger.info("Si inseriscono i dati della notifica nella sezione Informazioni Preliminari");
        String numeroDiProtocollo = WebTool.generatePaProtocolNumber();
        if (Optional.ofNullable(datiNotifica.get("oggettoNotifica")).orElse("").isEmpty()) {
            informazioniPreliminariPASection.insertOggettoNotifica(numeroDiProtocollo);
        } else {
            informazioniPreliminariPASection.insertOggettoNotifica(datiNotifica.get("oggettoNotifica"));
        }
        informazioniPreliminariPASection.insertDescrizione(datiNotifica.get("descrizione"));
        informazioniPreliminariPASection.insertNumeroDiProtocollo(numeroDiProtocollo);
        String environment = webDriverConfig.getEnvironment();
        switch (environment) {
            case "dev" ->
                    informazioniPreliminariPASection.insertGruppo("GruppoTest");
            case "test" ->
                    informazioniPreliminariPASection.insertGruppo(datiNotifica.get("gruppo"));
            case "uat" ->
                    informazioniPreliminariPASection.insertGruppo("Gruppo1");
            default -> {
                logger.error("Ambiente non valido");
                Assertions.fail("Ambiente non valido o non trovato!");
            }
        }

        informazioniPreliminariPASection.insertCodiceTassonometrico(datiNotifica.get("codiceTassonomico"));
        if (datiNotifica.get("modalitaInvio").equals("A/R")) {
            informazioniPreliminariPASection.selectRaccomandataAR();
        } else {
            informazioniPreliminariPASection.selectRegisteredLetter890();
        }
        datiNotificaMap.put("numeroProtocollo", numeroDiProtocollo);
        datiNotificaMap = datiNotifica;
    }

    @Then("Nella section Informazioni preliminari si inseriscono i dati della notifica senza gruppo")
    public void nellaSectionInformazioniPreliminariSiInserisconoIDatiDellaNotificaSenzaGruppo(Map<String, String> datiNotifica) {
        logger.info("Si inseriscono i dati della notifica nella sezione Informazioni Preliminari");
        String numeroDiProtocollo = WebTool.generatePaProtocolNumber();
        informazioniPreliminariPASection.insertOggettoNotifica(datiNotifica.get("oggettoNotifica"));
        informazioniPreliminariPASection.insertDescrizione(datiNotifica.get("descrizione"));
        informazioniPreliminariPASection.insertNumeroDiProtocollo(numeroDiProtocollo);
        informazioniPreliminariPASection.insertCodiceTassonometrico(datiNotifica.get("codiceTassonomico"));
        //informazioniPreliminariPASection.insertGruppo(datiNotifica.get("gruppo"));
        if (datiNotifica.get("modalitaInvio").equals("A/R")) {
            informazioniPreliminariPASection.selectRaccomandataAR();
        } else {
            informazioniPreliminariPASection.selectRegisteredLetter890();
        }
        datiNotificaMap.put("numeroProtocollo", numeroDiProtocollo);
        datiNotificaMap = datiNotifica;
    }


    @Then("Nella section Informazioni preliminari si inseriscono i dati della notifica senza salvare numero di protocollo")
    public void nellaSectionInformazioniPreliminariSiInserisconoIDatiDellaNotificaSenzaNumero(Map<String, String> datiNotifica) {
        logger.info("Si inseriscono i dati della notifica nella sezione Informazioni Preliminari");
        String gruppo = "";
        switch (webDriverConfig.getEnvironment()) {
            case "dev" ->
                    informazioniPreliminariPASection.insertGruppo(dataPopulationConfig.getDatiNotifica().getGruppoDev());
            case "test" ->
                    informazioniPreliminariPASection.insertGruppo(datiNotifica.get("gruppo"));
            default -> {
                logger.error("Ambiente non valido");
                Assertions.fail("Ambiente non valido o non trovato!");
            }
        }
        String numeroDiProtocollo = WebTool.generatePaProtocolNumber();
        informazioniPreliminariPASection.insertOggettoNotifica(datiNotifica.get("oggettoNotifica"));
        informazioniPreliminariPASection.insertDescrizione(datiNotifica.get("descrizione"));
        informazioniPreliminariPASection.insertNumeroDiProtocollo(numeroDiProtocollo);
//        informazioniPreliminariPASection.insertGruppo(datiNotifica.get("gruppo"));
        informazioniPreliminariPASection.insertCodiceTassonometrico(datiNotifica.get("codiceTassonomico"));
        if (datiNotifica.get("modalitaInvio").equals("A/R")) {
            informazioniPreliminariPASection.selectRaccomandataAR();
        } else {
            informazioniPreliminariPASection.selectRegisteredLetter890();
        }

    }

    @Then("Nella section Destinatario si inseriscono i dati del destinatario")
    public void nellaSectionDestinatarioSiInserisconoIDatiDelDestinatario(Map<String, String> destinatario) {
        logger.info("Si inseriscono i dati del destinatario nella sezione Destinatario");
        String nomeCognomeDestinatario = destinatario.get("nomeCognomeDestinatario");
        if (destinatario.get("soggettoGiuridico").equals("PF")) {
            destinatarioPASection.selezionarePersonaFisica();
//            destinatarioPASection.inserireNomeDestinatario(nomeDestinatario.split(" ")[0]);
//            destinatarioPASection.inserireCognomeDestinatario(nomeDestinatario.split(" ")[1]);
            destinatarioPASection.inserireNomeDestinatario(estraiNome(nomeCognomeDestinatario));
            destinatarioPASection.inserireCognomeDestinatario(estraiCognome(nomeCognomeDestinatario));
        } else {
            destinatarioPASection.clickRadioButtonPersonaGiuridica();
            destinatarioPASection.insertRagioneSociale(nomeCognomeDestinatario);
        }
        destinatarioPASection.inserireCodiceFiscaleDestinatario(destinatario.get("codiceFiscale"));
    }

    @Then("Nella section Aggiungi Destinatario si inseriscono i dati del destinatario")
    public void nellaSectionAggiungiDestinatarioSiInserisconoIDatiDelDestinatario(Map<String, String> destinatario) {
        logger.info("Si inseriscono i dati del destinatario nella sezione Destinatario");
        String nomeDestinatario = destinatario.get("nomeCognomeDestinatario");
        if (destinatario.get("soggettoGiuridico").equals("PF")) {
//            destinatarioPASection.selezionarePersonaFisica();
            destinatarioPASection.inserireAggiungiNomeDestinatario(nomeDestinatario.split(" ")[0]);
            destinatarioPASection.inserireAggiungiCognomeDestinatario(nomeDestinatario.split(" ")[1]);
        } else {
//            destinatarioPASection.clickRadioButtonPersonaGiuridica();
//            destinatarioPASection.insertRagioneSociale(nomeDestinatario);
        }
        destinatarioPASection.inserireAggiungiCodiceFiscaleDestinatario(destinatario.get("codiceFiscale"));
    }

    @And("Si verifica che il form di inserimento manuale della notifica è vuoto")
    public void siVerificaCheIlFormDiInserimentoManualeDellaNotificaEVuoto() {
        if (informazioniPreliminariPASection.checkFormInfoPreliminari()) {
            logger.info("Il form di inserimento manuale della notifica è vuoto");
        } else {
            logger.error("Il form di inserimento manuale della notifica non è vuoto");
            Assertions.fail("Il form di inserimento manuale della notifica non è vuoto");
        }
    }

    @And("Si clicca sul bottone torna a informazioni preliminari")
    public void siCliccaSulBottonTornaAInformazioniPreliminari() {
        destinatarioPASection.clickSuTornaInformazioniPreliminari();
    }

    @And("Tutti i campi precedentemente inseriti risultano ancora popolati")
    public void tuttiICampiPrecedentementeInseritiRisultanoAncoraPopolati() {
        if (destinatarioPASection.checkCampiDestinatarioPopolati()) {
            logger.info("I campi sezione destinatario sono popolati");
        } else {
            logger.error("I campi sezione destinatario non sono popolati");
            Assertions.fail("I campi sezione destinatario non sono popolati");
        }
    }

    @And("Nella section Destinitario si clicca su {string} e si inseriscono i dati")
    public void nellaSectionDestinitarioSiCliccaSuESiInserisconoIDati(String tipoIndirizzo, Map<String, String> indirizzo) {
        logger.info("Si clicca su " + tipoIndirizzo + " e si inseriscono i dati");
        if (tipoIndirizzo.contains("Aggiungi un indirizzo fisico")) {
            //destinatarioPASection.selezionaAggiungiUnIndirizzoFisico();
        } else {
            destinatarioPASection.checkBoxAggiungiDomicilio();
            destinatarioPASection.insertDomicilioDigitale(indirizzo.get("digitalAddress"));
            return;
        }
        destinatarioPASection.inserireIndirizzo(indirizzo.get("indirizzo"),0);
        destinatarioPASection.inserireNumeroCivico(indirizzo.get("civico"),0);
        destinatarioPASection.inserireLocalita(indirizzo.get("localita"),0);
        destinatarioPASection.inserireComune(indirizzo.get("comune"),0);
        destinatarioPASection.inserireProvincia(indirizzo.get("provincia"),0);
        destinatarioPASection.inserireCodicePostale(indirizzo.get("cap"),0);
        //destinatarioPASection.inserireStato(indirizzo.get("stato"),0);
    }
    @And("Nella section Aggiungi Destinitario si clicca su {string} e si inseriscono i dati")
    public void nellaSectionAggiungiDestinitarioSiCliccaSuESiInserisconoIDati(String tipoIndirizzo, Map<String, String> indirizzo) {
        logger.info("Aggiungi Si clicca su " + tipoIndirizzo + " e si inseriscono i dati");
        if (tipoIndirizzo.contains("Aggiungi un indirizzo fisico")) {
            //destinatarioPASection.selezionaAggiungiUnIndirizzoFisico();
        } else {
            destinatarioPASection.checkBoxAggiungiDomicilio();
            destinatarioPASection.insertDomicilioDigitale(indirizzo.get("digitalAddress"));
            return;
        }
        destinatarioPASection.inserireIndirizzo(indirizzo.get("indirizzo"),1);
        destinatarioPASection.inserireNumeroCivico(indirizzo.get("civico"),1);
        destinatarioPASection.inserireLocalita(indirizzo.get("localita"),1);
        destinatarioPASection.inserireComune(indirizzo.get("comune"),1);
        destinatarioPASection.inserireProvincia(indirizzo.get("provincia"),1);
        destinatarioPASection.inserireCodicePostale(indirizzo.get("cap"),1);
        //destinatarioPASection.inserireStato(indirizzo.get("stato"),0);
    }

    @Then("Nella section Allegati si carica un atto")
    public void nellaSectionAllegatiSiCaricaUnAtto() {
        logger.info("Caricamento dell'allegato notifica.pdf");

        File notificaFile = new File("src/test/resources/notifichePdf/notifica.pdf");
        String pathNotificaFile = notificaFile.getAbsolutePath();
        allegatiPASection.caricareNotificaPdfDalComputer(pathNotificaFile);

        if (allegatiPASection.verificaCaricamentoNotificaPdf()) {
            logger.info("File notifica.pdf caricato correttamente");
        } else {
            logger.error("File notifica.pdf non caricato");
            Assertions.fail("File notifica.pdf non caricato");
        }
        allegatiPASection.inserimentoNomeAllegato(datiNotificaMap.get("descrizione"));
    }

    @And("Nella section Allegati si carica secondo atto")
    public void siCaricaNuoviDocumenti() {
        logger.info("Caricamento dell'allegato notifica.pdf");

        File notificaFile = new File("src/test/resources/notifichePdf/notifica.pdf");
        String pathNotificaFile = notificaFile.getAbsolutePath();
        allegatiPASection.caricareNotificaPdfDalComputer(pathNotificaFile);

        if (allegatiPASection.verificaCaricamentoNotificaPdf()) {
            logger.info("File notifica.pdf caricato correttamente");
        } else {
            logger.error("File notifica.pdf non caricato");
            Assertions.fail("File notifica.pdf non caricato");
        }
        allegatiPASection.inserimentoNomeSecondoAllegato(datiNotificaMap.get("descrizione"));
    }

    @And("Nella section Allegati si carica terzo atto")
    public void siCaricaTerzoDocumento() {
        logger.info("Caricamento dell'allegato notifica.pdf");

        File notificaFile = new File("src/test/resources/notifichePdf/notifica.pdf");
        String pathNotificaFile = notificaFile.getAbsolutePath();
        allegatiPASection.caricareNotificaPdfDalComputer(pathNotificaFile);

        if (allegatiPASection.verificaCaricamentoNotificaPdf()) {
            logger.info("File notifica.pdf caricato correttamente");
        } else {
            logger.error("File notifica.pdf non caricato");
            Assertions.fail("File notifica.pdf non caricato");
        }
        allegatiPASection.inserimentoNomeTerzoAllegato(datiNotificaMap.get("descrizione"));
    }

    @And("Si visualizza correttamente il codice hash del documento")
    public void siVisualizzaCorrettamenteIlCodiceHashDelDocumento() {
        allegatiPASection.checkCodiceHash();
    }

    @Then("Nella section Allegati si carica un atto non pdf e visualizza messaggio di errore")
    public void nellaSectionAllegatiSiCaricaUnAttoNonPdf() {
        logger.info("Caricamento dell'allegato notifica.doc");
        File notificaFile = new File("src/test/resources/notifichePdf/notifica.doc");
        String pathNotificaFile = notificaFile.getAbsolutePath();
        allegatiPASection.caricareNotificaPdfDalComputer(pathNotificaFile);
        allegatiPASection.messagioDiErroreDoc();
    }

    @And("Si clicca sul bottone aggiungi nuovo documento")
    public void siCliccaSulBottoneAggiungiNuovoDocumento() {
        logger.info("Si aggiunge un nuovo atto");
        allegatiPASection.clickAggiungiNuovoDocumento();
    }

    @And("Si elimina un atto")
    public void siEliminaUnAtto() {
        logger.info("Si elimina un atto");
        allegatiPASection.eliminaAtto();
    }

    @And("Si verifica che la notifica è stata creata correttamente")
    public void siVerificaCheLaNotificaEStataCreataCorrettamente() {
        logger.info("Si verifica che la notifica sia stata creata correttamente filtrandolo per il numero di protocollo");
        piattaformaNotifichePage.setNetWorkInfos(webDriverManager.getNetworkInfosThread().get());
        piattaformaNotifichePage.setWebDriverManager(webDriverManager);
        piattaformaNotifichePage.setRestNotificationParam(restNotification);
        piattaformaNotifichePage.setNotificationSingletonParam(notificationSingleton);
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.verificaNotificaCreata();
    }

    @Then("In parallelo si effettua l'accesso al portale destinatario {string} e si apre la notifica ricevuta")
    public void inParalleloSiEffettuaLAccessoAlPortaleDestinatarioESiApreLaNotificaRicevuta(String destinatario) {
        if (PF.equalsIgnoreCase(destinatario)) {
            webTool.switchToPortal(AppPortal.PF);
        } else {
            webTool.switchToPortal(AppPortal.PG);
        }
        piattaformaNotifichePage.selezionaPrimaNotifica();
        webTool.waitTime(5);
        webTool.closeTab();
    }

    @Then("In parallelo si effettua l'accesso al portale destinatario persona fisica come delegante")
    public void inParalleloSiEffettuaLAccessoAlPortaleDestinatarioPFDelegante() {
        logger.info("switchToPortal(AppPortal.PF)");
        webTool.switchToPortalUrl(urlFactory,AppPortalUrl.PF_URL);
        logger.info("Accesso parallelo a portale persona fisica eseguito");
    }

    @Then("In parallelo si effettua l'accesso al portale destinatario persona giuridica come delegante")
    public void inParalleloSiEffettuaLAccessoAlPortaleDestinatarioPGDelegante() {
        logger.info("switchToPortal(AppPortal.PG)");
        webTool.switchToPortalUrl(urlFactory,AppPortalUrl.PG_URL);
        logger.info("Accesso parallelo a portale persona giuridica eseguito");
    }

    @Then("In parallelo si effettua l'accesso al portale destinatario persona fisica e si verifica la timeline {string}")
    public void inParalleloSiEffettuaLAccessoAlPortaleDestinatarioPFESiVerificaLaTimeline(String messaggio) {
        logger.info("switchToPortal(AppPortal.PF)");
        webTool.switchToPortalUrl(urlFactory,AppPortalUrl.PF_URL);
        logger.info("selezionaPrimaNotifica");
        piattaformaNotifichePage.selezionaPrimaNotifica();
        webTool.waitTime(5);
        driver.navigate().refresh();
        piattaformaNotifichePage.visualizzaTimeline(messaggio);
        webTool.closeTab();
    }

    @Then("In parallelo si effettua l'accesso al portale destinatario persona giuridica e si apre la notifica ricevuta")
    public void inParalleloSiEffettuaLAccessoAlPortaleDestinatarioPGESiApreLaNotificaRicevuta() {
        webTool.switchToPortal(AppPortal.PG);
        piattaformaNotifichePage.selezionaPrimaNotifica();
        webTool.waitTime(5);
        webTool.closeTab();
    }

    @Then("In parallelo si effettua l'accesso al portale destinatario persona giuridica e si verifica la timeline {string}")
    public void inParalleloSiEffettuaLAccessoAlPortaleDestinatarioPGESiVerificaLaTimeline(String messagio) {
        webTool.switchToPortalUrl(urlFactory,AppPortalUrl.PG_URL);
        piattaformaNotifichePage.selezionaPrimaNotifica();
        webTool.waitTime(5);
        piattaformaNotifichePage.visualizzaTimeline(messagio);
        webTool.closeTab();
    }

    @Then("In parallelo si effettua l'accesso al portale di {string}")
    public void inParalleloSiEffettuaLAccessoAlPortaleDestinatario(String portal) {
        portal = portal.toLowerCase();
        switch (portal) {
            case PF:
                webTool.switchToPortal(AppPortal.PF);
                break;
            case PG:
                webTool.switchToPortal(AppPortal.PG);
                break;
            case PA:
                webTool.switchToPortal(AppPortal.PA);
                break;
            default:
                logger.error("Tipologia di portale non specificato o errato!");
                Assertions.fail("Tipologia di portale non specificato o errato!");
        }
        webTool.waitTime(5);
    }

    @And("Nella timeline della notifica si visualizza l'invio del messaggio di cortesia")
    public void nellaTimelineDellaNotificaSiVisualizzaLInvioDelMessaggioDiCortesia() {
        logger.info("Si verifica la presenza della voce 'Invio della notifica di cortesia in corso' nella timeline della notifica");
        piattaformaNotifichePage.verificaInvioNotificaDiCortesia();
    }

    @Then("Si verifica che la notifica abbia lo stato {string}")
    public void siVerificaCheLaNotificaAbbiaLoStato(String stato) {
        logger.info("Si verifica che la notifica abbia lo stato " + stato);
        piattaformaNotifichePage.verificaPresenzaStato(stato);
    }

    @And("Si annulla la notifica")
    public void siAnnullaLaNotifica() {
        logger.info("Si clicca sul pusante annulla notifica");
        piattaformaNotifichePage.clickBottoneAnnullaNotifica();
        webTool.waitTime(3);
        piattaformaNotifichePage.clickAnnullaNotificaModale();
        webTool.waitTime(3);
    }

    @And("Il bottone annulla notifica non è visualizzabile nella descrizione della notifica")
    public void ilBottoneAnnullaNotificaNonÈVisualizzabileNellaDescrizioneDellaNotifica() {
        logger.info("Si controlla che il bottone annulla notifica non sia visualizzabile");
        piattaformaNotifichePage.checkBottoneAnnullaNotifica();
    }

    @And("Nella pagina Piattaforma Notifiche la notifica presenta lo stato {string}")
    public void nellaPaginaPiattaformaNotificheLaNotificaPresentaLoStato(String stato) {
        logger.info("Si controlla che nella pagina piattaforma notifiche la notifica abbia lo stato " + stato);
        piattaformaNotifichePage.checkStatoNotifica(stato);
    }

    @And("Si clicca il bottone indietro nella descrizione della notifica")
    public void siCliccaIlBottoneIndietroNellaDescrizioneDellaNotifica() {
        logger.info("Si clicca sul bottone indietro della pagina della descrizione della notifica");
        dettaglioNotificaMittenteSection.clickIndietroButton();
        webTool.waitTime(10);
    }

    @And("Si attende che lo stato della notifica sia {string}")
    public void siAttendeCheLoStatoDellaNotificaSia(String statoNotifica) {
        logger.info("Si clicca sulla notifica appena creata quando lo stato diventa: {}", statoNotifica);
        piattaformaNotifichePage.selezionaNotificaConStato(statoNotifica);
    }

    @And("Si attende completamento notifica {string}")
    public void siAttendeCompletamentoNotificaV2(String statoNotifica) {
        piattaformaNotifichePage.pollingSuStatoNotificaPerCompletamento(statoNotifica);
    }

    @Then("Si controlla la comparsa del pop up di conferma annullamento")
    public void siControllaLaComparsaDelPopUpDiConfermaAnnullamento() {
        logger.info("Si controlla la presenza del pop up di conferma dell'annullamento della notifica");
        piattaformaNotifichePage.checkPopUpConfermaAnnullamentoNotifica();
    }

    @And("Si verifica che l'invio della notifica sia fallito {int} volte")
    public void siVerificaCheLInvioDellaNotificaSiaFallitoDueVolte(int numeroFallimenti) {
        logger.info("Si verifica che l'invio della notifica sia fallito {} volta/e", numeroFallimenti);
        dettaglioNotificaMittenteSection.checkNumeroFallimentiInvioViaPEC(numeroFallimenti);
    }

    @And("Si attende la visualizzazione corretta del dettaglio della notifica")
    public void siAttendeCompletamentoNotifica() {
        siVisualizzaCorrettamenteLaSectionDettaglioNotifica();
        webTool.waitTime(400);
        driver.navigate().refresh();
        webTool.waitTime(3);
    }

    @And("Si seleziona la notifica")
    public void siSelezionaLaNotifica() {
        piattaformaNotifichePage.setNotificationSingletonParam(notificationSingleton);
        backgroundTest.setPiattaformaNotifichePage(piattaformaNotifichePage);
        backgroundTest.setHooksNew(hooksNew);
       //// String iun = notificationSingleton.getIun(HooksNew.scenario);
        String iun = backgroundTest.getPiattaformaNotifichePage().getNotificationSingletonParam().getIun(hooksNew.getScenario());
        logger.info("si Seleziona La Notifica con lo IUN............."+ iun);
//          backgroundTest.siFiltraLaTabellaDelleNotifichePerIUNDestinatario("PYLX-KQXU-HEPY-202506-D-1");
        backgroundTest.siFiltraLaTabellaDelleNotifichePerIUNDestinatario(iun);
    }


    @And("Si seleziona la notifica mittente")
    public void siSelezionaLaNotificaMittente() {
        logger.info("ATTENZIONE.........");
        String iun = notificationSingleton.getIun(hooksNew.getScenario());
        logger.info("SCENARIO..."+hooksNew.getScenario());
        logger.info("SCENARIO..."+iun);
        piattaformaNotifichePage.setNotificationSingletonParam(notificationSingleton);
        backgroundTest.setPiattaformaNotifichePage(piattaformaNotifichePage);
        backgroundTest.setHooksNew(hooksNew);
        backgroundTest.siFiltraLaTabellaDelleNotifichePerIUNMittente(iun);
    }

    @And("Si verifica l'invio della raccomandata semplice")
    public void siVerificaLInvioDellaRaccomandataSemplice() {
        logger.info("Si verifica l'avvenuto invio della notifica per raccomandata semplice");
        dettaglioNotificaMittenteSection.checkInvioRaccomandataSemplice();
    }

    @And("Si verifica l'invio della notifica al domicilio speciale inserito {string}")
    public void siVerificaLInvioDellaNotificaAlDomicilioSpecialeInserito(String domicilioSpeciale) {
        logger.info("Si verifica l'avvenuto invio della notifica al domicilio speciale " + domicilioSpeciale);
        dettaglioNotificaMittenteSection.checkStepInvioNotificaViaPEC(domicilioSpeciale);
    }

    @And("Si verifica il tentato invio della notifica al domicilio speciale inserito {string}")
    public void siVerificaIlTentatoInvioDellaNotificaAlDomicilioSpecialeInserito(String domicilioSpeciale) {
        logger.info("Si verifica il tentato invio al domicilio speciale " + domicilioSpeciale + " inserito nella notifica");
        dettaglioNotificaMittenteSection.checkTentatoInvioADomicilioSpeciale(domicilioSpeciale);
    }

    @And("Si verifica l'invio della notifica al domicilio di piattaforma inserito {string}")
    public void siVerificaLInvioDellaNotificaAlDomicilioDiPiattaformaInserito(String domicilioDiPiattaforma) {
        logger.info("Si verifica l'avvenuto invio della notifica al domicilio di piattaforma " + domicilioDiPiattaforma);
        dettaglioNotificaMittenteSection.checkStepInvioNotificaViaPEC(domicilioDiPiattaforma);
    }

    @And("Si verifica l'invio della notifica al domicilio generale {string}")
    public void siVerificaLInvioDellaNotificaAlDomicilioGenerale(String emailPEC) {
        logger.info("Si controllo l'invio della notifica tramite contatto del registro nazionale");
        dettaglioNotificaMittenteSection.checkStepInvioNotificaViaPEC(emailPEC);
    }


    @And("Si accede nuovamente al portale {string} con token {string} per eliminare i recapiti inseriti")
    public void siAccedeNuovamenteAlPortaleConTokenPerEliminareIRecapitiInseriti(String tipoPersona, String tipoToken) {
        logger.info("Si accede nuovamente al portale " + tipoPersona + " per eliminare i recapiti inseriti");
        if (PF.equalsIgnoreCase(tipoPersona)) {
            loginPersonaFisicaPagoPA.loginMittenteConTokenExchange(tipoToken);
            loginPersonaFisicaPagoPA.logoutDaPortaleDestinatario();
        } else {
            loginPGPagoPATest.loginMittenteConTokenExchange(tipoToken);
            loginPGPagoPATest.logoutDaPortalePersonaGiuridica();
        }
    }

    @Then("Nella section del destinatario numero {int} si inseriscono i suoi dati")
    public void nellaSectionDelDestinatarioNumeroSiInserisconoISuoiDati(int numeroDestinatario, Map<String, String> destinatario) {
        logger.info("Si inseriscono i dati del destinatario nella sezione Destinatario");
        numeroDestinatario--;
        if (destinatario.get("soggettoGiuridico").equals("PF")) {
            destinatarioPASection.selezionarePersonaFisicaMultiDestinatario(numeroDestinatario);
        } else {
            destinatarioPASection.clickRadioButtonPersonaGiuridica();
        }
        String nomeDestinatario = destinatario.get("nomeCognomeDestinatario");
        if (nomeDestinatario.split(" ").length > 0) {
            destinatarioPASection.inserireNomeMultiDestinatario(numeroDestinatario, nomeDestinatario.split(" ")[0]);
            destinatarioPASection.inserireCognomeMultiDestinatario(numeroDestinatario, nomeDestinatario.split(" ")[1]);
        } else {
            destinatarioPASection.insertRagioneSociale(nomeDestinatario);
        }
        destinatarioPASection.inserireCodiceFiscaleMultiDestinatario(numeroDestinatario, destinatario.get("codiceFiscale"));
    }

    @Then("Si controlla sia presente l'avviso PagoPa")
    public void siControllaSiaPresenteLAvvisoPagoPa() {
        logger.info("Si controlla la presenza del box per il pagamento della notifica");
        dettaglioNotificaMittenteSection.checkAvvisoPagoPa();
    }
    @And("Si controlla la presenza di codice avviso mittente")
    public void siControllaLaPresenzaDiCodiceAvviso(){
        logger.info("Si controlla la presenza di codice avviso");
        dettaglioNotificaMittenteSection.checkCodiceAvvisoVisibile();

    }

    @And("Si controlla non sia presente l'avviso PagoPa mittente")
    public void siControllaNonSiaPresenteLAvvisoPagoPaMittente() {
        logger.info("Si controlla la presenza del box per il pagamento della notifica");
        Assertions.assertFalse(dettaglioNotificaMittenteSection.checkAvvisoPagoPaVisibile(), "Avviso PagoPA è trovato");
        logger.info("Avviso PagoPA non è trovato");
    }

    @Then("Si clicca l'avviso PagoPa")
    public void siCliccaLAvvisoPagoPa() {
        logger.info("Si clicca l'avviso PagoPa");
        dettaglioNotificaMittenteSection.clickAvvisoPagoPa();
    }

    @Then("Si verifica che che non sia possibile effettuare il download del modelo F24")
    public void siVerificaF24() {
        logger.info("Si verifica che non sia possibile effettuare il download del modelo F24");
        dettaglioNotificaMittenteSection.checkModelloF24();
    }

    @Then("Si controlla sia presente il modello F24")
    public void siControllaSiaPresenteIlModelloF24() {
        logger.info("Si controlla sia presente il modello F24");
        dettaglioNotificaMittenteSection.checkModelloF24();
    }

    @And("Si verifica quando viene inviato il messaggio al contatto di cortesia")
    public void siVerificaQuandoVieneInviatoIlMessaggioAlContattoDiCortesia() {
        logger.info("Si verifica l'invio del messaggio al contatto di cortesia inserito");
        dettaglioNotificaMittenteSection.checkInvioMessaggioDiCortesia();
    }

    @And("Si controlla sia visualizza box allegati modelli F24")
    public void siControllaSiaVisualizzaBoxF24() {
        logger.info("Si controlla sia presente il box allegati modelli F24");
        dettaglioNotificaMittenteSection.checkBoxModelloF24();
    }

    @Then("Si clicca sul bottone chiudi box F24")
    public void siCliccaSulBottoneChiudiBoxF24() {
        logger.info("Si clicca sul bottone chiudi box F24");
        dettaglioNotificaMittenteSection.siCliccaSulBottoneChiudi();
    }

    @Then("Si controlla sia presente attestazione opponibile a terzi notifica presa in carico")
    public void siControllaSiaPresenteAttestazionePresaInCarico() {
        logger.info("Si controlla sia presente attestazione opponibile a terzi notifica presa in carico");
        dettaglioNotificaMittenteSection.checkAttestazionePresaInCarico();
    }

    @Then("Si controlla sia presente il box per il pagamento del multidestinatario")
    public void siControllaSiaPresenteIlBoxPerIlPagamentoDelMultidestinatario() {
        logger.info("Si verifica la presenza della select per la selezione del destinatario");
        dettaglioNotificaMittenteSection.checkBoxPagamentoMultiDestinatario();
    }

    @And("Si seleziona un destinatario")
    public void siSelezionaUnDestinatario() {
        logger.info("Si seleziona il primo destinatario presente nella selct");
        dettaglioNotificaMittenteSection.clickMultiDestinatario();
    }

    /**
     * Factorize out the assessment of the esito notifica from siVerificaCheLaNotificaVieneCreataCorrettamente,
     * this was motivated by the need to add siVerificaCheLaNotificaVieneRifiutata
     * that needs to perform the same assessment.
     */
    protected EsitoNotifica siVerificaEsitoNotifica(String dpFile) {
        logger.info("si verifica se la notifica è stata accettata o rifiutata");
        final String urlNotificationRequest = webDriverConfig.getBaseUrl() + "notifications/sent";
        final String urlRichiestaNotifica = "https://api." + webDriverConfig.getEnvironment() + ".notifichedigitali.it/delivery/v2.3/requests/";
        String codiceApi = "environmentCode";
        switch (webDriverConfig.getEnvironment()) {
            case "dev" -> codiceApi = dataPopulationConfig.getMittente().getCodiceApiKeyDEV();
            case "test" -> codiceApi = dataPopulationConfig.getMittente().getCodiceApiKeyTEST();
            case "uat" -> codiceApi = dataPopulationConfig.getMittente().getCodiceApiKeyUAT();
            default -> {
                Assertions.fail("Ambiente non valido o non trovato per siVerificaEsitoNotifica!");
            }
        }
        accettazioneRichiestaNotifica.setxApikey(codiceApi);
        String statusNotifica = "WAITING";
        webTool.waitTime(10);
        String notificationRequestId = getNotificationRequestId(urlNotificationRequest);
        if (notificationRequestId == null) {
            Assertions.fail("NotificationRequestId non trovato, il codice della risposta al url " + urlNotificationRequest + " è diverso di 202 ");
        }
        accettazioneRichiestaNotifica.setNotificationRequestId(notificationRequestId);
        accettazioneRichiestaNotifica.setRichiestaNotificaEndPoint(urlRichiestaNotifica);
        do {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean result = accettazioneRichiestaNotifica.runGetRichiestaNotifica();
            if (result) {
                statusNotifica = accettazioneRichiestaNotifica.getStatusNotifica();
                logger.info("lo stato della notifica è :" + statusNotifica);
            } else {
                if (accettazioneRichiestaNotifica.getResponseCode() != 200) {
                    Assertions.fail("la risposta dell'accettazione della notifica " + notificationRequestId + " è: " + accettazioneRichiestaNotifica.getResponseCode());
                }
            }
        } while (statusNotifica.equals("WAITING"));
        return new EsitoNotifica(statusNotifica, accettazioneRichiestaNotifica, notificationRequestId);
    }

    @And("Si verifica che la notifica viene creata correttamente {string}")
    public void siVerificaCheLaNotificaVieneCreataCorrettamente(String dpFile) {
        //datiNotifica
        //datiNotificaPG
        EsitoNotifica esitoNotifica = siVerificaEsitoNotifica(dpFile);
        if (esitoNotifica.statusNotifica.equals("ACCEPTED")) {
            logger.info("La notifica è stata Accettata");
            //String codiceIUN = esitoNotifica.accettazioneRichiestaNotifica.getCodiceIUN();
            String codiceIUN = esitoNotifica.accettazioneRichiestaNotifica.getCodiceIUN();
            if (codiceIUN != null && !codiceIUN.isEmpty()) {

                setCodiceIUN(dpFile,codiceIUN);
                logger.info("La notifica è stata creata correttamente");
            }
        } else {
            Assertions.fail("La notifica " + esitoNotifica.notificationRequestId + " è stata rifiuta: " + esitoNotifica.accettazioneRichiestaNotifica.getResponseReasonPhrase());
        }
    }

    @And("Si verifica che la notifica e' stata rifiutata {string}")
    public void siVerificaCheLaNotificaVieneRifiutata(String dpFile) {
        EsitoNotifica esitoNotifica = siVerificaEsitoNotifica(dpFile);
        if (esitoNotifica.statusNotifica.equals("REFUSED")) {
            logger.info("La notifica è stata Rifiutata");
        } else {
            logger.error("La notifica " + esitoNotifica.notificationRequestId + " è stata accettata: " + esitoNotifica.statusNotifica);
            logger.error(esitoNotifica.accettazioneRichiestaNotifica.getResponseBody());
            Assertions.fail("La notifica " + esitoNotifica.notificationRequestId + " è stata accettata: ");
        }
    }

    @And("Si ottiene il codice IUN dalla notifica creata")
    public void ottieniIUNdaRichiestaNotifica() {
        piattaformaNotifichePage.setNetWorkInfos(webDriverManager.getNetworkInfosThread().get());
        piattaformaNotifichePage.setWebDriverManager(webDriverManager);
        piattaformaNotifichePage.setRestNotificationParam(restNotification);
        piattaformaNotifichePage.setNotificationSingletonParam(notificationSingleton);
        piattaformaNotifichePage.setHooksNew(hooksNew);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String urlNotificationRequest = webDriverConfig.getBaseUrl() + "notifications/sent";
        String notificationRequestId = getNotificationRequestId(urlNotificationRequest);
        Assertions.assertNotNull(notificationRequestId, "NotificationRequestId non trovato, il codice della risposta al url " + urlNotificationRequest + " è diverso da 202");
        logger.info("ID della notifica creata: {}", notificationRequestId);
        Iun = WebTool.decodeNotificationRequestId(notificationRequestId);
        logger.info("IUN della notifica creata: {}", Iun);
    }

    @And("Aspetta la notifica con IUN salvato")
    public void aspettaNotificaConIUNSalvato() {
        Assertions.assertFalse(Iun.isEmpty(), "IUN della notifica non presente!");
        boolean notificaTrovata = piattaformaNotifichePage.attesaNotificaConIUN(Iun);
        Assertions.assertTrue(notificaTrovata, "La notifica risulta ancora non visibile sulla tabella notifiche dopo 8 tentativi");
        logger.info("La notifica è visualizzata sulla tabella notifiche");
    }

    private String getNotificationRequestId(String urlNotificationRequest) {
        /*
         * In case of error, I prefer to display a message that corresponds to the actual situation,
         * so now two different error cases are distinguished
         * (1) a POST request with the provided URL was found, but the status is not 202
         * (2) no POST requests with the provided URL were found
         */

/**
        boolean isApiCalled = webDriveBean.waitForApiCall(urlNotificationRequest, Duration.ofSeconds(50));

        if (isApiCalled) {
            logger.info("L'API è stata chiamata!");
        } else {
            logger.info("Timeout: l'API non è stata chiamata.");
        }
**/


        boolean foundRequestWithUndesiredStatus = false;
        for (NetWorkInfo netWorkInfo : webDriverManager.getNetworkInfo()) {
            logger.info("BODY URL......."+netWorkInfo.getRequestUrl());
            logger.info("BODY METHOD......."+ netWorkInfo.getRequestMethod());
            logger.info("BODY STATUS......."+ netWorkInfo.getResponseStatus());
            if (netWorkInfo.getRequestUrl().equals(urlNotificationRequest) && netWorkInfo.getRequestMethod().equals("POST") && netWorkInfo.getResponseStatus().equals("202")) {
                String values = netWorkInfo.getResponseBody();
                logger.info("BODY......."+values);
                logger.info("BODY......."+netWorkInfo.getRequestUrl());
                List<String> results = Splitter.on(CharMatcher.anyOf(",:")).splitToList(values);
                String result = results.get(1);
                return result.substring(1, result.length() - 1);
            } else if (netWorkInfo.getRequestUrl().equals(urlNotificationRequest) && netWorkInfo.getRequestMethod().equals("POST")) {
                foundRequestWithUndesiredStatus = true;
            }
        }
        if (foundRequestWithUndesiredStatus) {
            logger.error("NotificationRequestId non trovato, il codice della risposta al POST sull'url " + urlNotificationRequest + " è diverso di 202 ");
        } else {
            logger.error("NotificationRequestId non trovato, non sono state trovate chiamate POST per la url " + urlNotificationRequest);
        }
        return null;
    }

    @And("Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica")
    public void nellaSectionDestinatarioCliccareSuAggiungiDomicilioDigitaleCompilareIDatiDellaPersonaFisica() {
        logger.info("Si inserisce un domicilio digitale della persona giuridica");
        //"personaFisica"
        destinatarioPASection.checkBoxAggiungiDomicilio();
        destinatarioPASection.insertDomicilioDigitale(dataPopulationConfig.getPersonaFisica().getEmailPecErrore());
    }

    @And("Nella pagina Piattaforma Notifiche si recupera un codice IUN valido")
    public void nellaPaginaPiattaformaNotificheSiRecuperaUnCodiceIUNValido() {
        logger.info("Si recupera un codice IUN valido");

        List<String> codiciIun = piattaformaNotifichePage.getCodiceIunPresenti();
        String codiceIun = dataPopulationConfig.getDatiNotifica().getCodiceIUN();
        if (codiciIun.contains(codiceIun)) {
            piattaformaNotifichePage.inserimentoCodiceIUN(codiceIun);
        } else {
            piattaformaNotifichePage.inserimentoCodiceIUN(codiciIun.get(0));
            dataPopulationConfig.getDatiNotifica().setCodiceIUN( codiciIun.get(0));
        }
    }

    @And("Si verifica che la notifica sia nello stato consegnata")
    public void siVerificaCheLaNotificaSiaNelloStatoConsegnata() {
        logger.info("Si verifica che la notifica sia nello stato consegnata");

        boolean notificaTrovata = false;
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (piattaformaNotifichePage.getListStato("Depositata") != 0 && piattaformaNotifichePage.getListStato("Invio in corso") != 0) {
                piattaformaNotifichePage.aggiornamentoPagina();
                piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
                piattaformaNotifichePage.inserimentoCodiceIUN(dataPopulationConfig.getDatiNotifica().getCodiceIUN());
                piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
            } else {
                notificaTrovata = true;
                break;
            }
        }
        if (!notificaTrovata) {
            logger.error("La notifica non è stata trovata dopo 1m40s");
            Assertions.fail("La notifica non è stata trovata dopo 1m40s");
        }
    }

    @And("Nella pagina Piattaforma Notifiche inserire il codice fiscale sbagliato {string}")
    public void inserimentoCodiceFiscaleSbagliato(String codiceFiscaleSbagliato) {
        logger.info("inserimento codice fiscale sbagliato nella ricerca di una notifica");

        piattaformaNotifichePage.insertCodiceFiscale(codiceFiscaleSbagliato);
        piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
    }

    @Then("Nella pagina Piattaforma Notifiche si controlla che si visualizza il messaggio di errore codice fiscale")
    public void nellaPiattaformaNotificheSiControllaEsistenzaMessaggioErroreCF() {
        logger.info("si controlla che si visualizza il messaggio di errore ‘Inserisci il codice per intero’ ");

        Assertions.assertTrue(piattaformaNotifichePage.controlloEsistenzaMessagioErroreCF(), "Nessun errore visualizzato insirendo CF sbagliato");
    }

    @And("Nella pagina Piattaforma Notifiche si controlla che il bottone Filtra sia attivo")
    public void nellaPaginaPiattaformaNotificheSiControllacheFiltraSiaDisattivo() {
        logger.info("Si controlla che il bottone Filtra sia attivo");

        Assertions.assertFalse(piattaformaNotifichePage.verificaBottoneFiltraDisabilitato(),"il bottone Filtra è disabilitato");
    }

    @And("Nella pagina Piattaforma Notifiche inserire il codice IUN sbagliato {string}")
    public void nellaPaginaPiattaformaNotificheInserireIUNsbagliato(String codiceIUNSbagliato) {
        logger.info("Inserimento codice IUN sbagliato");

        piattaformaNotifichePage.inserimentoCodiceIUN(codiceIUNSbagliato);
        piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
    }

    @Then("Nella pagina Piattaforma Notifiche si visualizza il messaggio di errore codice IUN")
    public void nellaPaginaPiattaformaNotificheSiVisualizzaIlMessaggioDiErroreIUN() {
        logger.info("si controlla esistenza messaggio di errore codice IUN");

        Assertions.assertTrue(piattaformaNotifichePage.controlloEsistenzaMessagioErroreIUN(), "Nessun errore visualizzato insirendo IUN sbagliato");
        logger.info("Messaggio di errore 'Inserisci un codice IUN valido' trovato");
    }

    @Then("Nella section si visualizza correttamente i campi vuoti")
    public void nellaSectionSiVisualizzaCorrettamenteICampiVuoti() {
        logger.info("Si verifica che i campi sono vuoti");
        if (piattaformaNotifichePage.verificaCampiPreliminariNotificaVuoti()) {
            logger.info("I campi sono vuoti");
        } else {
            Assertions.fail("I campi non sono vuoti");
        }
    }

    @And("Si aggiungi un domicilio digitale {string}")
    public void SiAggiungiUnDomicilioDigitale(String mail) {
        destinatarioPASection.checkBoxAggiungiDomicilio();
        destinatarioPASection.insertDomicilioDigitale(mail);
    }

    @And("Si verifica che entrambi destinatari non raggiungibili al primo tentativo")
    public void siVerificaCheEntrambiDestinatariNonRaggiungibiliAlPrimoTentativo(Map<String, String> destinatari) {
        piattaformaNotifichePage.verificaDestinatariNonRaggiungibili(destinatari);
    }

    @And("Si verifica che destinatario raggiungibile {string}")
    public void siVerificaCheDestinatarioRaggiungibile(String message) {
        piattaformaNotifichePage.visualizzaTimeline(message);
        logger.info("Il destinatario raggiungibile");
    }

    @And("Si verifica che il destinatario è raggiungibile al tentativo successivo {string}")
    public void siVerificaCheDestinatarioRaggiungibileTentativoSuccessivo(String message) {
        piattaformaNotifichePage.verificaTentativoSuccessivo(message);
        logger.info("Il destinatario raggiungibile al tentativo successivo");
    }

    @And("Si controlla che le ricevute PEC siano scaricabili")
    public void siControllaCheLeRicevutePECSianoScaricabili() {
        logger.info("Si controlla che le ricevute PEC siano scaricabili in locale");
        piattaformaNotifichePage.checkClickDownloadRicevutePEC();
    }

    @And("Creazione notifica completa")
    public void creazioneNotificaCompleta(Map<String,String> datiNotificaMap) {
        logger.info("Inserimento dei dati della notifica senza pagamento " );
        File notificaFile = new File("src/test/resources/notifichePdf/notifica.pdf");
        aggiornamentoNumeroProtocollo();

        //Sezione preliminare
        informazioniPreliminariPASection.compilazioneInformazioniPreliminari(datiNotificaMap);
        cliccareSuContinua();

        //Dati destinatario
        siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionDestinatario();
        destinatarioPASection.compilazioneDestinario(datiNotificaMap);
        cliccareSuContinua();


        //Sezione allegati
        siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionAllegati();
        String pathNotificaFile = notificaFile.getAbsolutePath();
        allegatiPASection.caricareNotificaPdfDalComputer(pathNotificaFile);

        if (allegatiPASection.verificaCaricamentoNotificaPdf()) {
            logger.info("File notifica.pdf caricato correttamente");
        } else {
            logger.error("File notifica.pdf non caricato");
            Assertions.fail("File notifica.pdf non caricato");
        }
        allegatiPASection.inserimentoNomeAllegato(datiNotificaMap.get("nomeDocumentoNotifica"));
        nellaSectionAllegatiCliccareSulBottoneInvia();
        siVisualizzaCorrettamenteLaFraseLaNotificaEStataCorrettamenteCreata();
        cliccareSulBottoneVaiAlleNotifiche();
    }

    @And("Si controlla lo stato timeline in dettaglio notifica")
    public void siControllaLoStatoTimelineInDettaglioNotifica(Map<String, String> datiDettaglioNotifica) {
        String idStato = datiDettaglioNotifica.get("xpathStato");
        String viewDetail = datiDettaglioNotifica.get("vediDettagli");
        siVisualizzaCorrettamenteLElencoCompletoDegliStatiCheLaNotificaHaPercorso();
        webTool.waitTime(2);
        if (viewDetail.equals("true")){
            dettaglioNotificaMittenteSection.clickVediPiuDettagli();
        }
        dettaglioNotificaMittenteSection.checkStatoTimeline(idStato);
    }

    @And("Si verifica che la ricevuta di postalizzazione sia cliccabile")
    public void siVerificaLaCliccabilitaDellaRicevutaDiPostalizzazione(Map<String, String> datiDettaglioNotifica) {
        String idStato = datiDettaglioNotifica.get("xpathStato");
        String viewDetail = datiDettaglioNotifica.get("vediDettagli");
        siVisualizzaCorrettamenteLElencoCompletoDegliStatiCheLaNotificaHaPercorso();
        webTool.waitTime(2);
        if (viewDetail.equals("true")){
            dettaglioNotificaMittenteSection.clickVediPiuDettagli();
        }

        dettaglioNotificaMittenteSection.siVerificaLaCliccabilitaSuAllegatoInTimeline(idStato);
    }

    @Then("Si verifica che il mittente sia {string}")
    public void siVerificaCheIlMittenteSia(String ente) {
        logger.info("Si verifica che il mittente sia " + ente);
        piattaformaNotifichePage.verificaMittente(ente);
    }

    @And("Controllo alert RADD")
    public void controlloAlertRADD() {
        dettaglioNotificaMittenteSection.checkAlertRADD();
    }

    @And("Si sceglie ente figlio {string}")
    public void siSceglieEnteFiglio(String nomeEnte){
        dettaglioNotificaMittenteSection.sceglieEnte(nomeEnte);
    }

    @Then("Si visualizza testo nella timeline {string}")
    public void siVisualizzaTestoNellaTimeline(String testo) {
        piattaformaNotifichePage.visualizzaTimeline(testo);
    }


    @And("Selezionare da impostazione lingua {string}")
    public void selezionareDaImpostazioneLingua(String lingua) {
        piattaformaNotifichePage.selezionareDaImpostazioneLingua(lingua);

    }

    @And("verifica lingua selezionata {string}")
    public void verificaLinguaSelezionata(String lingua) {
        piattaformaNotifichePage.verificaLinguaSelezionata(lingua);
    }

    @And("selezione impostazione lingua")
    public void selezioneImpostazioneLingua() {
        piattaformaNotifichePage.selezioneImpostazioneLingua();
    }

    @And("Verifica Pop-up toast di errore {string}")
    public void verificaPopUpToast(String verifica) {
        piattaformaNotifichePage.verificaPopUpToastErrore(verifica);
    }

    @And("Verifica Messaggio toast di errore {string}")
    public void verificaMessaggioToastErrore(String verifica) {
        piattaformaNotifichePage.verificaMessaggioToastErrore(verifica);
    }

    @And("Verifica Codice toast di errore {string}")
    public void verificaCodiceToastErrore(String verifica) {
        piattaformaNotifichePage.verificaCodiceToastErrore(verifica);
    }

    @And("Copia TraceID toast di errore")
    public void copiaTraceIDToastErrore() {
        logger.info("Si clicca copia sul tasto 'Copia informazioni errore' per copiare il traceID dell'errore");
        String copiedValue = piattaformaNotifichePage.copiaTraceIDToastErrore();
        logger.info("Il traceID copiato è {}", copiedValue);
    }

    @And("Si chiude toast di errore")
    public void siChiudeToastDiErrore() {
        logger.info("Si chiude toast di errore");
        piattaformaNotifichePage.clickChiudiToastErrore();
    }

    @And("Verifica Banner {string}")
    public void verificaBanner(String banner) {
        piattaformaNotifichePage.verificaBanner(banner);
    }

    @And("Refresh pagina")
    public void refreshPagina() {
        driver.navigate().refresh();
    }

    @And("Chiudi pagina")
    public void chiudiPagina() {
        driver.close();
    }

    @And("verifica campi vuoti")
    public void verificaCampiVuoti() {
        piattaformaNotifichePage.verificaCampiVuoti();
    }

    @And("Verifica footer lingua {string}")
    public void verificaFooterLingua(String lingua) {
        piattaformaNotifichePage.verificaFooterLingua(lingua);
    }

    @And("Verifica click footer privacy o Termini Condizione {string}")
    public void verificaClickFooterPrivacyOrTerminiCondizione(String privacy) {
        piattaformaNotifichePage.verificaClickFooterPrivacyOrTerminiCondizione(privacy);
    }

    @And("Verifica traduzione testo {string}")
    public void verificaTraduzioneTesto(String testo) {
        Assertions.assertTrue(isTextPresent(testo), "Il testo '"+testo+"' non è presente!");
        logger.info("Verifica traduzione testo: {}",testo);
    }

    private boolean isTextPresent(String testo) {
       return piattaformaNotifichePage.isTextPresent(testo);
    }

    @And("Torna indietro")
    public void tornaIndietro() {
        super.goBack();
    }

    @And("Cambia lingua footer {string}")
    public void cambiaLinguaFooter(String lingua) {
        piattaformaNotifichePage.cambiaLinguaFooter(lingua);
    }

    @And("Entro dentro la prima notifica")
    public void entroDentroLaPrimaNotifica() {
        piattaformaNotifichePage.selezionaPrimaNotifica();
    }
    @And("Entro dentro la prima notifica con stato {string}")
    public void entroDentroLaPrimaNotificaConStato(String stato) {
        piattaformaNotifichePage.selezionaPrimaNotifica(stato);
    }

    @When("Seleziona voce menu laterale {string}")
    public void selezionaVoceMenuLaterale(String testo) {
        piattaformaNotifichePage.selezionaVoceMenuLaterale(testo);
    }

    @When("Click Genera Api Key")
    public void clickGeneraApiKey() {
        piattaformaNotifichePage.clickGeneraApiKey();
    }

    @And("Inserisci nome Api Key")
    public void inserisciNomeApiKey() {
        logger.info("Inserisco elemento");
        webTool.waitTime(2);
        piattaformaNotifichePage.inserisciNomeApiKey();
    }

    @And("Torna a Api Key")
    public void tornaApiKey() {
        logger.info("Premere il pulsante tornaApiKey");
        piattaformaNotifichePage.tornaApiKey();
    }

    @And("Premere tre puntini")
    public void premereTrePuntini() {
        logger.info("premereTrePuntini");
        piattaformaNotifichePage.premereTrePuntini();
    }

    @And("Seleziona Ruota")
    public void selezionaRuota() {
        logger.info("selezionaRuota");
        piattaformaNotifichePage.selezionaRuota();
    }

    @And("Click Ruota")
    public void clickRuota() {
        logger.info("clickRuota");
        piattaformaNotifichePage.clickRuota();
    }

    @And("Seleziona Blocca")
    public void selezionaBlocca() {
        logger.info("selezionaBlocca");
        piattaformaNotifichePage.selezionaBlocca();
    }

    @And("Click Blocca")
    public void clickBlocca() {
        logger.info("clickBlocca");
        piattaformaNotifichePage.clickBlocca();
    }

    @And("Seleziona Elimina")
    public void selezionaElimina() {
        logger.info("selezionaElimina");
        piattaformaNotifichePage.selezionaElimina();
    }

    @And("Click Delete")
    public void clickDelete() {
        logger.info("clickDelete");
        piattaformaNotifichePage.clickDelete();
    }

    @And("Attendi secondi {string}")
    public void attendiSecondi(String secondi) {
        webTool.waitTime(Integer.parseInt(secondi));
        logger.info("Attesa secondi: {}",secondi);
    }

    @When("Click torna alle deleghe")
    public void clickTornaAlleDeleghe() {
        destinatarioPASection.clickTornaAlleDeleghe();
    }

    @And("Selezionare da impostazione lingua la lingua {string}")
    public void selezionareDaImpostazioneLinguaLaLingua(String lingua) {
        piattaformaNotifichePage.selezionareDaImpostazioneLinguaLaLingua(lingua);
    }

    @And("Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a {int}")
    public void caricaSingoloFilePdfPosizioneDebitoriaNumeroNotifichePari(int numNotifiche) {
        String basePath = "src/test/resources/notifichePdf/";
        String fileName;

        switch (numNotifiche) {
            case 1:
                fileName = "AvvisopagoPA_1.pdf";
                break;
            case 2:
                fileName = "AvvisopagoPA_2.pdf";
                break;
            case 3:
                fileName = "AvvisopagoPA_3.pdf";
                break;
            case 4:
                fileName = "AvvisopagoPA_4.pdf";
                break;
            default:
                throw new IllegalArgumentException("Numero di notifiche non supportato: " + numNotifiche);
        }

        File notificaFile = new File(basePath + fileName);
        String pathNotificaFile = notificaFile.getAbsolutePath();
        allegatiPASection.caricareSingolaNotificaPdfDalComputer(pathNotificaFile,0);

    }

    @And("Carica Multiplo File PDF Posizione Debitoria Numero Notifiche Pari a {int}")
    public void caricaMultiploloFilePdfPosizioneDebitoriaNumeroNotifichePari(int numNotifiche) {

        //Inserire nello stesso step i file
        String basePath = "src/test/resources/notifichePdf/";
        String fileName;

        for (int i = 0; i < numNotifiche; i++) {
             switch (i) {
                 case 0 :
                    fileName = "AvvisopagoPA_1.pdf";
                    break;
                 case 1 :
                    fileName = "AvvisopagoPA_2.pdf";
                    break;
                 case 2:
                     fileName = "AvvisopagoPA_3.pdf";
                     break;
                 case 3:
                     fileName = "AvvisopagoPA_4.pdf";
                     break;
                 default :
                        throw new IllegalArgumentException("Numero Multiplo di notifiche non supportato: " + numNotifiche);
            }
            File notificaFile = new File(basePath + fileName);
            String pathNotificaFile = notificaFile.getAbsolutePath();
            allegatiPASection.caricareSingolaNotificaPdfDalComputer(pathNotificaFile,0);
        }
    }


    @And("Carica Multiplo File JSON senza costi Posizione Debitoria Numero Notifiche Pari a {int}")
    public void caricaMultiploloFileJsonSenzacostiPdfPosizioneDebitoriaNumeroNotifichePari(int numNotifiche) {
        //Inserire nello stesso step i file
        String basePath = "src/test/resources/notifichePdf/senzaCosti/";
        String fileName;

        for (int i = 0; i < numNotifiche; i++) {
            switch (numNotifiche) {
                case 0:
                    fileName = "PN_F24_META-1_notifica_senza_costi.json";
                    break;
                case 1:
                    fileName = "PN_F24_META-1_notifica_senza_costi.json";
                    break;
                case 2:
                    fileName = "PN_F24_META-3_notifica_senza_costi.json";
                    break;
                case 3:
                    fileName = "PN_F24_META-4_notifica_senza_costi.json";
                    break;
                default:
                    throw new IllegalArgumentException("Senza Costi Numero di notifiche non supportato: " + numNotifiche);
            }

            File notificaFile = new File(basePath + fileName);
            String pathNotificaFile = notificaFile.getAbsolutePath();

            allegatiPASection.caricareSingolaNotificaJsonDalComputer(pathNotificaFile,0);
        }
    }

    @And("Carica Multiplo File JSON con costi Posizione Debitoria Numero Notifiche Pari a {int}")
    public void caricaMultiploloFileJsonConcostiPdfPosizioneDebitoriaNumeroNotifichePari(int numNotifiche) {
        //Inserire nello stesso step i file
        String basePath = "src/test/resources/notifichePdf/conCosti/";
        String fileName;

        for (int i = 0; i < numNotifiche; i++) {
            switch (numNotifiche) {
                case 0:
                    fileName = "PN_F24_META-1_notifica_con_costi.json";
                    break;
                case 1:
                    fileName = "PN_F24_META-1_notifica_con_costi.json";
                    break;
                case 2:
                    fileName = "PN_F24_META-3_notifica_con_costi.json";
                    break;
                case 3:
                    fileName = "PN_F24_META-4_notifica_con_costi.json";
                    break;
                default:
                    throw new IllegalArgumentException("Con Costo Numero di notifiche non supportato: " + numNotifiche);
            }

            File notificaFile = new File(basePath + fileName);
            String pathNotificaFile = notificaFile.getAbsolutePath();

            allegatiPASection.caricareSingolaNotificaJsonDalComputer(pathNotificaFile,0);
        }
    }

    @And("Carica Multiplo File {int} non pdf o json e visualizza messaggio di errore {string}")
    public void caricaMultiploFileNonPdfOJsonEVisualizzaMessaggioDiErrore(int numeroFile, String formatoFile) {
        String basePath = "src/test/resources/notifichePdf/notifica.doc";
        File notificaFile = new File(basePath);
        String pathNotificaFile = notificaFile.getAbsolutePath();
        for (int i = 0; i < numeroFile; i++) {
            logger.info("caricaMultiploFileNonPdfOJsonEVisualizzaMessaggioDiErrore: " + i);
            if (formatoFile.equalsIgnoreCase("json")) {
                allegatiPASection.caricareSingolaNotificaJsonDalComputer(pathNotificaFile, i);
            } else {
                allegatiPASection.caricareSingolaNotificaPdfDalComputer(pathNotificaFile,i);
            }
        }
        aspettaMessaggiErroreCaricamentoFile(numeroFile);
    }

    @And("Carica Json con Costi Posizione Debitoria Numero Notifiche Pari a {int}")
    public void caricaJsonConCostiPosizioneDebitoriaNumeroNotifichePariA(int numNotifiche) {
        logger.info("Carica Json con Costi Posizione Debitoria Numero Notifiche Pari: {}", numNotifiche);
        String basePath = "src/test/resources/notifichePdf/conCosti/";
        String fileName;

        // Massimo 4 notifiche, minimo 1
        switch (numNotifiche) {
            case 1:
                fileName = "PN_F24_META-1_notifica_con_costi.json";
                break;
            case 2:
                fileName = "PN_F24_META-1_notifica_con_costi.json";
                break;
            case 3:
                fileName = "PN_F24_META-3_notifica_con_costi.json";
                break;
            case 4:
                fileName = "PN_F24_META-4_notifica_con_costi.json";
                break;
            default:
                throw new IllegalArgumentException("Numero di notifiche non supportato: " + numNotifiche);
        }

        File notificaFile = new File(basePath + fileName);
        String pathNotificaFile = notificaFile.getAbsolutePath();

        allegatiPASection.caricareJsonDalComputer(pathNotificaFile);
    }
    @And("Carica File Formato errato Posizione Debitoria Numero Notifiche Pari a {int}")
    public void caricaFilePdfFormatoErratoPosizioneDebitoriaNumeroNotifichePari(int numNotifiche) {
        logger.info("Carica File Posizione Debitoria Numero Notifiche Pari: {}", numNotifiche);
        String basePath = "src/test/resources/notifichePdf/";
        String fileName;

        switch (numNotifiche) {
            case 1:
                fileName = "PN_F24_META-1_notifica_con_costi.json";
                break;
            case 2:
                fileName = "PN_F24_META-1_notifica_con_costi.json";
                break;
            default:
                throw new IllegalArgumentException("Numero di notifiche non supportato: " + numNotifiche);
        }

        File notificaFile = new File(basePath + fileName);
        String pathNotificaFile = notificaFile.getAbsolutePath();
        allegatiPASection.caricareNotificaPdfDalComputer(pathNotificaFile);

    }

    @And("Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a {int}")
    public void caricaJsonSenzaCostiPosizioneDebitoriaNumeroNotifichePariA(int numNotifiche) {
        logger.info("Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari: {}", numNotifiche);
        String basePath = "src/test/resources/notifichePdf/senzaCosti/";
        String fileName;

        // Massimo 4 notifiche, minimo 1
        switch (numNotifiche) {
            case 1:
                fileName = "PN_F24_META-1_notifica_senza_costi.json";
                break;
            case 2:
                fileName = "PN_F24_META-2_notifica_senza_costi.json";
                break;
            case 3:
                fileName = "PN_F24_META-3_notifica_senza_costi.json";
                break;
            case 4:
                fileName = "PN_F24_META-4_notifica_senza_costi.json";
                break;
            default:
                throw new IllegalArgumentException("Metodo caricaJsonSenzaCostiPosizioneDebitoriaNumeroNotifichePariA Numero di notifiche non supportato: " + numNotifiche);
        }

        File notificaFile = new File(basePath + fileName);
        String pathNotificaFile = notificaFile.getAbsolutePath();

        allegatiPASection.caricareSingolaNotificaJsonDalComputer(pathNotificaFile,0);
    }



    @And("Inserisci Titolo Documento Documenti Allegati {int}")
    public void inserisciTitoloDocumentoDocumentiAllegati(int numeroTitoloDoc) {
        allegatiPASection.inserisciTitoloDocumentoDocumentiAllegati(numeroTitoloDoc);
    }

    @And("Verifica Presenza Sezione Pagamenti {int}")
    public void verificaPresenzaSezionePagamenti(int numeroAvvisi) {
        destinatarioPASection.verificaPresenzaSezionePagamenti( numeroAvvisi);
    }

    @And("Click Su Elimina Avviso pagoPA")
    public void clickSuEliminaAvvisoPagoPa() { destinatarioPASection.clickSuEliminaAvvisoPagoPa();
    }
    @And("Click Su Elimina Modello F24")
    public void clickSuEliminaModelloF24() { destinatarioPASection.clickSuEliminaModelloF24();
    }

    @And("Verifica Presenza Sezione Pagamenti nel menu a cascata {int}")
    public void verificaPresenzaSezionePagamentiNelMenuACascata(int numeroDiPagamenti) {
        destinatarioPASection.verificaPresenzaSezionePagamentiNelMenuACascata(numeroDiPagamenti);
    }

    @And("Verifica Presenza Sezione Pagamenti numero moduli F24 {int}")
    public void verificaPresenzaSezionePagamentiNumeroModuliF( int numeroModuli) {
        destinatarioPASection.verificaPresenzaSezionePagamentiNumeroModuliF(numeroModuli);
    }

    @And("Verifica Disabilitato Tasto Continua")
    public void verificaDisabilitatoTastoContinua() {
        destinatarioPASection.verificaDisabilitatoTastoContinua();
    }

    @And("Verifica Errori Codici Avviso NonValidi {int}")
    public void verificaErroriCodiciAvvisoNonValidi(int numeroAttesi) {
        destinatarioPASection.verificaErroriCodiciAvvisoNonValidi(numeroAttesi);
    }

    @And("Verifica Errori Codice Fiscale Ente NonValidi {int}")
    public void verificaErroriCodiceFiscaleEnteNonValidi(int numeroAttesi) {
        destinatarioPASection.verificaErroriCodiceFiscaleEnteNonValidi( numeroAttesi);

    }

    @And("Verifica Assenza Pop-up Errore per Invia Posizione Debitoria")
    public void verificaAssenzaPopUpErrorePerInviaPosizioneDebitoria() {
        Assertions.assertFalse(destinatarioPASection.verificaAssenzaPopUpErrorePerInviaPosizioneDebitoria()," Pop-up Errore per Invia Posizione Debitoria Apparso");
    }

    @And("Verifica Numero Caricamento file {int}")
    public void verificaNumeroCaricamentoFile(int numeroFile) {
        destinatarioPASection.verificaNumeroCaricamentoFile(numeroFile);
    }

    @And("Verifica Esistenza Tabella Notifiche")
    public void verificaEsistenzaTabellaNotifiche() {
        destinatarioPASection.verificaEsistenzaTabellaNotifiche();
    }


    @And("Verifica abilitazione Tasto Continua")
    public void verificaAbilitazioneTastoContinua() {
        piattaformaNotifichePage.verificaAbilitazioneTastoContinua();
    }

    @And("Verifica Pagina  Invia una nuova notifica la sezione Posizione Debitoria")
    public void verificaPaginaInviaUnaNuovaNotificaLaSezionePosizioneDebitoria() {
        piattaformaNotifichePage.verificaPaginaInviaUnaNuovaNotificaLaSezionePosizioneDebitoria();
    }

    @And("Verifica Disibilitato Tasto Continua")
    public void verificaDisibilitatoTastoContinua() {
        piattaformaNotifichePage.verificaDisibilitatoTastoContinua();
    }

    @And("Verifica presenza radion Button Inserimento automatico abilitato di default e manuale disabilitato")
    public void verificaPresenzaRadionButtonIserimentoAutomaticoAbilitatoDiDefaultEManualeDisabilitato() {
        piattaformaNotifichePage.verificaPresenzaRadionButtonInserimentoAutomaticoAbilitatoDiDefault();
        piattaformaNotifichePage.verificaPresenzaRadionButtonIserimentoManualeDisabilitato();
    }

    @And("Verifica assenza radion Button Inserimento automatico e manuale")
    public void verificaAssenzaRadionButtonIserimentoAutomaticoEManuale() {
        piattaformaNotifichePage.verificaAssenzaRadionButtonIserimentoAutomatico();
        piattaformaNotifichePage.verificaAssenzaRadionButtonIserimentoManuale();
    }

    @And("Verifica radion button Inserimento Automatico abilitato di default")
    public void verificaRadionButtonInserimentoAutomaticoAbilitatoDiDefault() {
        piattaformaNotifichePage.verificaPresenzaRadionButtonInserimentoAutomaticoAbilitatoDiDefault();
    }

    @And("Seleziona radion button Inserimento Manuale se esiste {string}")
    public void selezionaRadionButtonInserimentoManualeSeEsiste(String posizione) {
        piattaformaNotifichePage.selezionaRadionButtonInserimentoManualeSeEsiste(posizione);
    }

    @And("Verifica Banner attivo e Inserimento manuale selezionato")
    public void verificaBannerAttivoEInserimentoManualeSelezionato() {
        piattaformaNotifichePage.verificaBannerAttivoEInserimentoManualeSelezionato();
    }

    @And("Disabilita Pop-Up Chrome")
    public void disabilitaPopUpChrome() {
        driver.switchTo().alert().accept();
    }

    @And("Nel portale Send {string} accedere ad una rotta non esistente")
    public void nelPortaleSendAccedereAdUnaRottaNonEsistente(String portal) {
        portal = portal.toLowerCase();
        String env = this.webDriverConfig.getEnvironment();
        switch (portal) {
            case PF:
                driver.get(webDriverConfig.getBaseUrlPfTest()+"prova");
//                this.driver.get("https://cittadini." + env + ".notifichedigitali.it/prova");
                break;
            case PG:
                driver.get(webDriverConfig.getBaseUrlPgTest()+"prova");
//                this.driver.get("https://imprese." + env + ".notifichedigitali.it/prova");
                break;
            case PA:
                driver.get(webDriverConfig.getUrlMittente()+"/prova");
//                this.driver.get("https://selfcare." + env + ".notifichedigitali.it/prova");
                break;
            default:
                Assertions.fail("Tipologia di portale non specificato o errato!");
        }
    }

    @And("Verifica esistenza Pagina non trovata")
    public void verificaEsistenzaPaginaNonTrovata() {
        piattaformaNotifichePage.verificaEsistenzaPaginaNonTrovata();
    }

    @And("Click Torna alla home")
    public void clickTornaAllaHome() {
        piattaformaNotifichePage.clickTornaAllaHome();
    }


    /**
     * A simple object that represents the esito notifica, i.e. the return value of siVerificaEsitoNotifica.
     */
    class EsitoNotifica {
        String statusNotifica;
        AccettazioneRichiestaNotifica accettazioneRichiestaNotifica;
        String notificationRequestId;

        public EsitoNotifica(String statusNotifica, AccettazioneRichiestaNotifica accettazioneRichiestaNotifica, String notificationRequestId) {
            this.statusNotifica = statusNotifica;
            this.accettazioneRichiestaNotifica = accettazioneRichiestaNotifica;
            this.notificationRequestId = notificationRequestId;
        }
    }


    private void setCodiceIUN(String dpFile,String codiceIUN) {
        //datiNotifica,datiNotificaPG
        //datiNotifica.put("codiceIUN", codiceIUN);
        //dataPopulation.writeDataPopulation(dpFile + ".yaml", datiNotifica);
        switch (dpFile) {
            case "datiNotifica" -> {
                notificationSingleton.setScenarioIun(hooksNew.getScenario(),codiceIUN);
                //dataPopulationConfig.getDatiNotifica().setCodiceIUN(codiceIUN);
                logger.info("datiNotifica codiceIUN: "+codiceIUN);
            }
            case "datiNotificaPG" -> {
                notificationSingleton.setScenarioIun(hooksNew.getScenario(),codiceIUN);
                //dataPopulationConfig.getDatiNotificaPg().setCodiceIUN(codiceIUN);
                logger.info("datiNotificaPG codiceIUN: "+codiceIUN);
            }

            default -> {
                logger.error("Nessun codice IUN corrisponde");
                throw new RuntimeException("ERRORE Nessun codice IUN corrisponde");
            }
        }



    }
    public void aspettaMessaggiErroreCaricamentoFile(int numeroAtteso) {
        getWebDriverWait(10).until(ExpectedConditions.numberOfElementsToBe(
                By.cssSelector("#file-upload-error"),
                numeroAtteso
        ));
    }

    private void populateDestinatarioPASection(String persona, int recIndex) {
        if(persona.equalsIgnoreCase("personaFisicaPec")){
            destinatarioPASection.inserireIndirizzo(dataPopulationConfig.getPersonaFisicaPec().getIndirizzo(),recIndex);
            destinatarioPASection.inserireNumeroCivico(dataPopulationConfig.getPersonaFisicaPec().getNumeroCivico(),recIndex);
            destinatarioPASection.inserireLocalita(dataPopulationConfig.getPersonaFisicaPec().getLocalita(),recIndex);
            destinatarioPASection.inserireComune(dataPopulationConfig.getPersonaFisicaPec().getComune(),recIndex);
            destinatarioPASection.inserireProvincia(dataPopulationConfig.getPersonaFisicaPec().getProvincia(),recIndex);
            destinatarioPASection.inserireCodicePostale(dataPopulationConfig.getPersonaFisicaPec().getCodicePostale(),recIndex);
            destinatarioPASection.insertDomicilioDigitale(dataPopulationConfig.getPersonaFisicaPec().getEmailPec());
           // destinatarioPASection.inserireStato(dataPopulationConfig.getPersonaFisicaPec().getStato(),recIndex);
        }
        else {
            destinatarioPASection.inserireIndirizzo(dataPopulationConfig.getPersonaFisica().getIndirizzo(),recIndex);
            destinatarioPASection.inserireNumeroCivico(dataPopulationConfig.getPersonaFisica().getNumeroCivico(),recIndex);
            destinatarioPASection.inserireLocalita(dataPopulationConfig.getPersonaFisica().getLocalita(),recIndex);
            destinatarioPASection.inserireComune(dataPopulationConfig.getPersonaFisica().getComune(),recIndex);
            destinatarioPASection.inserireProvincia(dataPopulationConfig.getPersonaFisica().getProvincia(),recIndex);
            destinatarioPASection.inserireCodicePostale(dataPopulationConfig.getPersonaFisica().getCodicePostale(),recIndex);
           // destinatarioPASection.inserireStato(dataPopulationConfig.getPersonaFisica().getStato(),recIndex);
        }
    }

    private static final Set<String> PREFISSI_COGNOME = Set.of(
            "de", "di", "del", "della", "la", "lo", "van", "von", "san", "santa", "dos", "da", "das", "do", "dei", "degli"
    );

    public static String estraiNome(String fullName) {
        String[] parts = normalizza(fullName);
        if (parts.length <= 1) return ""; // Solo cognome o vuoto

        int splitIndex = trovaInizioCognome(parts);
        return String.join(" ", Arrays.copyOfRange(parts, 0, splitIndex)).trim();
    }

    public static String estraiCognome(String fullName) {
        String[] parts = normalizza(fullName);
        if (parts.length == 0) return "";

        int splitIndex = trovaInizioCognome(parts);
        return String.join(" ", Arrays.copyOfRange(parts, splitIndex, parts.length)).trim();
    }

    private static String[] normalizza(String fullName) {
        if (fullName == null) return new String[0];
        return fullName.trim().split("\\s+");
    }

    private static int trovaInizioCognome(String[] parts) {
        int splitIndex = parts.length - 1;

        // Torna indietro se ci sono prefissi del cognome
        for (int i = parts.length - 1; i > 0; i--) {
            if (PREFISSI_COGNOME.contains(parts[i - 1].toLowerCase())) {
                splitIndex = i - 1;
            } else {
                break;
            }
        }
        return splitIndex;
    }

}
