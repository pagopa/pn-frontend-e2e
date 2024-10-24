package it.pn.frontend.e2e.stepDefinitions.mittente;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.mittente.AccettazioneRichiestaNotifica;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.model.enums.AppPortal;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.pages.mittente.AreaRiservataPAPage;
import it.pn.frontend.e2e.pages.mittente.InvioNotifichePAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.mittente.*;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.LoginPersonaFisicaPagoPA;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.LoginPGPagoPATest;
import it.pn.frontend.e2e.utility.CookieConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.Setter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.apache.commons.lang3.StringUtils.substring;

public class NotificaMittentePagoPATest {

    private static final Logger logger = LoggerFactory.getLogger("NotificaMittentePagoPATest");
    private final WebDriver driver = Hooks.driver;
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
    private final PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
    private final AllegatiPASection allegatiPASection = new AllegatiPASection(this.driver);
    private final DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
    private final DataPopulation dataPopulation = new DataPopulation();
    private final DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
    private final String variabileAmbiente = System.getProperty("environment");
    private final InformazioniPreliminariPASection informazioniPreliminariPASection = new InformazioniPreliminariPASection(this.driver);
    private final LoginPersonaFisicaPagoPA loginPersonaFisicaPagoPA = new LoginPersonaFisicaPagoPA();
    private final LoginPGPagoPATest loginPGPagoPATest = new LoginPGPagoPATest();
    private final String PF = "persona fisica";
    private final String PG = "persona giuridica";
    private final String PA = "pubblica amministrazione";
    private final NotificationSingleton notificationSingleton = NotificationSingleton.getInstance();
    private Map<String, Object> datiNotifica = new HashMap<>();
    private Map<String, String> datiNotificaMap = new HashMap<>();
    private Map<String, Object> personaFisica = new HashMap<>();
    private Map<String, Object> personaGiuridica = new HashMap<>();
    private Map<String, Object> personeFisiche = new HashMap<>();
    @Setter
    private String Iun;
    @Setter
    private String ApiKey;

    @When("Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche")
    public void nellaHomePageMittenteCliccareSuGestisciDiPiattaforma() {
        AreaRiservataPAPage areaRiservataPAPage = new AreaRiservataPAPage(this.driver);
        logger.info("Cliccare sul bottone di Piattaforma Notifiche dell'Ambiente " + variabileAmbiente);
        switch (variabileAmbiente) {
            case "dev" -> areaRiservataPAPage.selezionaPiattaformaNotificaDev();
            case "test" -> areaRiservataPAPage.selezionaPiattaformaNotificaTest();
            case "uat" -> areaRiservataPAPage.selezionaPiattaformaNotificaUat();
        }
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotifiche() {
        logger.info("Si visualizza correttamente la pagina Piattaforma Notifiche");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String urlChiamata = WebTool.getApiBaseUrl() + "notifications/sent?";
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

    public String getNumeroProtocollo() {
        logger.info("Si recupera l'ultimo numero protocollo utilizzato");

        this.piattaformaNotifichePage.siCambiaIlNumeroElementiVisualizzatiAttraversoIlFiltro();
        WebTool.waitTime(5);
        String urlNotifiche = WebTool.getApiBaseUrl() + "notifications/";
        for (NetWorkInfo netWorkInfo : netWorkInfos) {
            if (netWorkInfo.getRequestUrl().contains(urlNotifiche) && netWorkInfo.getRequestUrl().endsWith("size=10")) {
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

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();
        informazioniPreliminariPASection.waitLoadInformazioniPreliminariPASection();
    }

    @And("Nella section Informazioni preliminari inserire i dati della notifica {string} senza pagamento")
    public void nellaSectionInformazioniPreliminariInserireIDatiDellaNotificaSenzaPagamento(String datiNotificaFile) {
        logger.info("Inserimento dei dati della notifica senza pagamento dal file " + datiNotificaFile + ".yaml");
        aggiornamentoNumeroProtocollo();
        this.datiNotifica = dataPopulation.readDataPopulation(datiNotificaFile + ".yaml");
        String gruppo = "";
        switch (variabileAmbiente) {
            case "dev" -> gruppo = datiNotifica.get("gruppoDev").toString();
            case "test", "uat" -> gruppo = datiNotifica.get("gruppoTest").toString();
        }
        informazioniPreliminariPASection.insertOggettoNotifica(this.datiNotifica.get("oggettoDellaNotifica").toString());
        informazioniPreliminariPASection.insertDescrizione(this.datiNotifica.get("descrizione").toString());
        informazioniPreliminariPASection.insertNumeroDiProtocollo(this.datiNotifica.get("numeroProtocollo").toString());
        informazioniPreliminariPASection.insertGruppo(gruppo);
        informazioniPreliminariPASection.insertCodiceTassonometrico(this.datiNotifica.get("codiceTassonometrico").toString());
        informazioniPreliminariPASection.selectRaccomandataAR();
    }


    private void aggiornamentoNumeroProtocollo() {
        logger.info("Aggiornamento del numero protocollo");
        Map<String, Object> allDatataPopulation = dataPopulation.readDataPopulation("datiNotifica.yaml");
        String numeroProtocolOld = allDatataPopulation.get("numeroProtocollo").toString();
        String numeroProtocolNew;
        do {
            numeroProtocolNew = WebTool.generatePaProtocolNumber();
        } while (numeroProtocolOld.equals(numeroProtocolNew));
        allDatataPopulation.put("numeroProtocollo", numeroProtocolNew);
        dataPopulation.writeDataPopulation("datiNotifica.yaml", allDatataPopulation);

    }

    @And("Cliccare su continua")
    public void cliccareSuContinua() {
        logger.info("Cliccare sul bottone continua");
        InvioNotifichePAPage invioNotifichePAPage = new InvioNotifichePAPage(this.driver);
        invioNotifichePAPage.selezionareContinuaButton();
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


    @And("Si visualizza correttamente la timeline relativi a tutti i destinatari")
    public void siVisualizzaCorrettamenteLaTimelineRelativiATuttiIDestinatari(Map<String, String> destinatari) {
        piattaformaNotifichePage.visualizzaTimelineTuttiDestinatari(destinatari);
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionDestinatario() {
        logger.info("Verifica visualizzazione della section Destinatario");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        destinatarioPASection.waitLoadDestinatarioPASection();
    }

    @And("Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica {string}")
    public void nellaSectionDestinatarioInserireNomeCognomeECodiceFiscaleDaDestinatario(String destinatarioFile) {
        logger.info("Inserimento del nome cognome e codice fiscale dal file personaFisica.yaml");


        this.personeFisiche = dataPopulation.readDataPopulation(destinatarioFile + ".yaml");


        destinatarioPASection.selezionarePersonaFisica();
        destinatarioPASection.inserireNomeDestinatario(this.personeFisiche.get("name").toString());
        destinatarioPASection.inserireCognomeDestinatario(this.personeFisiche.get("familyName").toString());
        destinatarioPASection.inserireCodiceFiscaleDestinatario(this.personeFisiche.get("codiceFiscale").toString());
    }

    @And("Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica {string}")
    public void nellaSectionDestinatarioCliccareSuAggiungiIndirizzoFisicoCompilareIDatiDelDestinatario(String personaFisicaFile) {
        logger.info("Inserimento dei dati mancanti nella section destinatario");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();


        this.personeFisiche = dataPopulation.readDataPopulation(personaFisicaFile + ".yaml");


        destinatarioPASection.selezionaAggiungiUnIndirizzoFisico();
        destinatarioPASection.inserireIndirizzo(this.personeFisiche.get("indirizzo").toString());
        destinatarioPASection.inserireNumeroCivico(this.personeFisiche.get("numeroCivico").toString());
        destinatarioPASection.inserireLocalita(this.personeFisiche.get("localita").toString());
        destinatarioPASection.inserireComune(this.personeFisiche.get("comune").toString());
        destinatarioPASection.inserireProvincia(this.personeFisiche.get("provincia").toString());
        destinatarioPASection.inserireCodicePostale(this.personeFisiche.get("codicepostale").toString());
        destinatarioPASection.inserireStato(this.personeFisiche.get("stato").toString());
    }

    @And("Nella section Destinatario settare come CAP {string}")
    public void nellaSectionDestinatarioSettareComeCAP(String cap) {
        // this assumes that previously the test scenario enforces the destinatarioPASection to appear,
        // e.g. through siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionDestinatario

        destinatarioPASection.cambiareCodicePostale(cap);
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionAllegati() {
        logger.info("Verifica visualizzazione della section Allegati");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        AllegatiPASection allegatiPASection = new AllegatiPASection(this.driver);
        allegatiPASection.waitLoadAllegatiPASection();
    }

    @And("Nella section Allegati caricare l'atto e inserire il nome atto {string}")
    public void nellaSectionAllegatiCaricareLAttoEInserireIlNomeAtto(String datiNotificaFile) {
        logger.info("Caricamento dell'allegato notifica.pdf");

        AllegatiPASection allegatiPASection = new AllegatiPASection(this.driver);
        File notificaFile = new File("src/test/resources/notifichePdf/notifica.pdf");
        String pathNotificaFile = notificaFile.getAbsolutePath();
        allegatiPASection.caricareNotificaPdfDalComputer(pathNotificaFile);

        if (allegatiPASection.verificaCaricamentoNotificaPdf()) {
            logger.info("File notifica.pdf caricato correttamente");
        } else {
            logger.error("File notifica.pdf non caricato");
            Assert.fail("File notifica.pdf non caricato");
        }


        this.datiNotifica = dataPopulation.readDataPopulation(datiNotificaFile + ".yaml");

        allegatiPASection.inserimentoNomeAllegato(this.datiNotifica.get("nomeDocumentoNotifica").toString());
    }

    @And("Nella section Allegati cliccare sul bottone Invia")
    public void nellaSectionAllegatiCliccareSulBottoneInvia() {
        logger.info("Cliccare sul bottone Invia");

        AllegatiPASection allegatiPASection = new AllegatiPASection(this.driver);
        allegatiPASection.selectInviaButton();
        if (allegatiPASection.verificaMessaggioErrore()) {
            aggiornamentoNumeroProtocolloAllegati();
            logger.error("Si vede il messaggio di dati non corretti");
            Assert.fail("Si vede il messaggio di dati non corretti");
        }
    }

    private void aggiornamentoNumeroProtocolloAllegati() {
        logger.info("Aggiornamento del numero protocollo");

        String nomeFile = "datiNotifica.yaml";
        String numeroProtocolloKey = "numeroProtocollo";
        DataPopulation dataPopulation = new DataPopulation();
        String numeroProtocolOld = dataPopulation.readDataPopulation(nomeFile).get(numeroProtocolloKey).toString();
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
                logger.error(numeroProtocolOld + " oltre questo numero protocollo per la giornata di : " + dataProtocolOld + " non si può andare");
                Assert.fail(numeroProtocolOld + " oltre questo numero protocollo per la giornata di : " + dataProtocolOld + " non si può andare");
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

        Map<String, Object> allDatataPopulation = dataPopulation.readDataPopulation("datiNotifica.yaml");
        allDatataPopulation.put("numeroProtocollo", numeroProtocol);
        dataPopulation.writeDataPopulation("datiNotifica.yaml", allDatataPopulation);

    }

    @Then("Si visualizza correttamente la frase La notifica è stata correttamente creata")
    public void siVisualizzaCorrettamenteLaFraseLaNotificaEStataCorrettamenteCreata() {
        logger.info("Verifica visualizzazione frase: La notifica è stata correttamente creata");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        SuccessPASection successPASection = new SuccessPASection(this.driver);
        successPASection.waitLoadSuccessPASection();

    }

    @And("Cliccare sul bottone vai alle notifiche")
    public void cliccareSulBottoneVaiAlleNotifiche() {
        logger.info("Si clicca sul bottone 'vai alle notifiche'");

        SuccessPASection successPASection = new SuccessPASection(this.driver);
        successPASection.vaiAlleNotifiche();
    }

    @And("Verifica dello stato della notifica come depositata {string}")
    public void verificaDelloStatoDellaNotificaComeDepositata(String statoNotifica) {
        logger.info("Verifica dello stato della notifica come 'Depositata'");

        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        this.personaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String dataNotifica = dateFormat.format(date).replace("-", "/");

        String coidiceIUNOld = this.datiNotifica.get("codiceIUN").toString();
        for (int i = 0; i < 12; i++) {
            if (i >= 1) {
                piattaformaNotifichePage.aggiornamentoPagina();
                piattaformaNotifichePage.insertCodiceFiscale(this.personaFisica.get("codiceFiscale").toString());
                piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
                piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
            }
            piattaformaNotifichePage.waitLoadRefreshPage();
            String codiceIUN = piattaformaNotifichePage.ricercaNotifica(this.datiNotifica.get("oggettoDellaNotifica").toString(), statoNotifica);
            if (!codiceIUN.equals("")) {
                if (!codiceIUN.equals(coidiceIUNOld)) {
                    this.datiNotifica.put("codiceIUN", codiceIUN);
                    dataPopulation.writeDataPopulation("datiNotifica.yaml", this.datiNotifica);
                    logger.info("Stato notifica uguale a Depositata e codice IUN aggiornato correttamente");
                    return;
                }
            }
        }

        logger.error("Il server ha impiegato troppo tempo nel generare la notifica");
        Assert.fail("Il server ha impiegato troppo tempo nel generare la notifica");

    }

    @And("Nella pagina Piattaforma Notifiche accetta i Cookies")
    public void nellaPaginaPiattaformaNotificheAccettaICookies() {
        if (!CookieConfig.isCookieEnabled()) {
            CookiesSection cookiesSection = new CookiesSection(this.driver);
            cookiesSection.waitLoadCookiesPage();
            cookiesSection.selezionaAccettaTuttiButton();
        }
    }

    @When("Cliccare sulla notifica restituita")
    public void cliccareSullaNotificaRestituita() {
        logger.info("Si clicca sulla notifica");

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.selezionaPrimaNotifica();
    }

    @When("Cliccare sulla notifica  maggiore di 120 giorni")
    public void cliccareSullaNotificaRestituita120Giorni() {
        logger.info("Si clicca sulla notifica maggiore di 120 giorni");

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.selezionaNotifica120Giorni();
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
        Assert.assertFalse(actualApiKey.equalsIgnoreCase(ApiKey));
        logger.info("Api Keys sono diversi");
    }

    @And("viene inserito codice IUN salvato")
    public void vieneInseritoIunSalvato() {
        dettaglioNotificaMittenteSection.insertIunSalvatoAndRicercaOnPage(Iun);
    }

    @And("ricerca notifica con IUN salvato")
    public void ricercaNotificaConIunSalvato(){
        dettaglioNotificaMittenteSection.ricercaNotificaConIunSalvato(Iun);
    }

    @And("Mittente ricerca notifica con IUN salvato {string}")
    public void ricercaNotificaConIunMittente(String iun){
        dettaglioNotificaMittenteSection.ricercaNotificaConIunSalvato(iun);
    }

    @And("Si visualizza correttamente la section Dettaglio Notifica")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotifica() {
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        dettaglioNotificaMittenteSection.waitLoadDettaglioNotificaSection();
    }

    @And("Nella pagina dettaglio notifica cliccare sull'opzione vedi più dettagli")
    public void nellaPaginaDettaglioNotificaCliccareSullOpzioneVediPiuDettagli() {
        dettaglioNotificaMittenteSection.waitLoadDettaglioNotificaSection();
        this.dettaglioNotificaMittenteSection.clickVediPiuDettaglio();
    }

    @And("Si visualizza correttamente l elenco completo degli stati che la notifica ha percorso")
    public void siVisualizzaCorrettamenteLElencoCompletoDegliStatiCheLaNotificaHaPercorso() {
        dettaglioNotificaMittenteSection.waitLoadDettaglioNotificaSection();
        dettaglioNotificaMittenteSection.siVisualizzaPercorsoNotifica();
    }

    @Then("Si clicca sul bottone indietro")
    public void siCliccaSulBottoneIndietro() {
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        dettaglioNotificaMittenteSection.clickIndietroButton();
    }

    @And("Nella pagina Piattaforma Notifiche visualizzano correttamente i filtri di ricerca")
    public void nellaPaginaPiattaformaNotificheVisualizzanoCorrettamenteIFiltriDiRicerca() {
        piattaformaNotifichePage.siVisualizzaCorrettamenteIlCFField();
        piattaformaNotifichePage.siVisualizzaCorrettamenteIlCodiceIUNField();
        piattaformaNotifichePage.siVisualizzaCorrettamenteLoStatoField();
        piattaformaNotifichePage.siVisualizzaCorrettamenteLaDataInzioField();
        piattaformaNotifichePage.siVisualizzaCorrettamenteLaDataFineField();
    }

    @Then("Nella pagina Piattaforma Notifiche si visualizza correttamente l'elenco delle notifiche")
    public void nellaPaginaPiattaformaNotificheSiVisualizzaCorrettamenteLElencoDelleNotifiche() {
        logger.info("Si visualizza l'elenco delle notifiche");

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
            Assert.fail("I codici fiscali delle notifiche non vengono visualizzati correttamente");
        }
        List<WebElement> listaOggetti = piattaformaNotifichePage.ricercaListaOggetti();
        if (listaOggetti != null && listaOggetti.size() >= 1) {
            logger.info("La lista degli oggetti viene visualizzata correttamente");
        } else {
            logger.error("La lista degli oggetti non viene visualizzata correttamente");
            Assert.fail("La lista degli oggetti non viene visualizzata correttamente");
        }

        if (piattaformaNotifichePage.verificaEsistenzaCodiceIUNNotifiche()) {
            logger.info("La lista dei codici iun viene visualizzata correttamente");
        } else {
            logger.error("La lista dei codici iun non viene visualizzata correttamente");
            Assert.fail("La lista dei codici iun non viene visualizzata correttamente");
        }

        if (piattaformaNotifichePage.verificaEsistenzaGruppoNotifiche()) {
            logger.info("La lista dei gruppi vengono visualizzate correttamente");
        } else {
            logger.error("La lista dei gruppi non vengono visualizzate correttamente");
            Assert.fail("La lista dei gruppi non vengono visualizzate correttamente");
        }

        if (piattaformaNotifichePage.verificaEsistenzaStatoNotifiche()) {
            logger.info("La lista degli stati viene visualizzata correttamente");
        } else {
            logger.error("La lista degli stati non viene visualizzata correttamente");
            Assert.fail("La lista degli stati non viene visualizzata correttamente");
        }
    }

    @Then("Nella section Destinatario viene visualizzato un solo destinatario")
    public void nellaSectionDestinatarioVieneVisualizzatoUnSoloDestinatario() {
        logger.info("Verifica visualizzazione di un solo destinatario");

        if (destinatarioPASection.verificaNumeroDestinatari()) {
            logger.info("Viene visualizzato un solo destinatario");
        } else {
            logger.error("Viene visualizzato più di un destinatario");
            Assert.fail("Viene visualizzato più di un destinatario");
        }
    }

    @And("Nella pagina Piattaforma Notifiche si visualizzano le notifiche a partire dalla più recente")
    public void nellaPaginaPiattaformaNotificheSiVisualizzanoLeNotificheAPartireDallaPiuRecente() {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.controlloOrdineNotifiche();
    }

    @And("Nella pagina Piattaforma Notifiche si scrolla fino alla fine della pagina")
    public void nellaPaginaPiattaformaNotificheSiScrollaFinoAllaFineDellaPagina() {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.siScrollaFinoAllaFineDellaPagina();
    }

    @And("Nella pagina Piattaforma Notifiche si controlla che vengano visualizzate dieci notifiche")
    public void nellaPaginaPiattaformaNotificheSiControllaCheVenganoVisualizzateNotifiche() {
        logger.info("si controlla che vengono visualizzate dieci notifiche");

        String nNotificheInviate = piattaformaNotifichePage.numeroNotifiche();
        if (nNotificheInviate.equals("10")) {
            logger.info("Il numero di notifiche é corretto");
        } else {
            logger.error("Il numero di notifiche non é corretto");
            Assert.fail("Il numero di notifiche non é corretto");
        }
    }

    @And("Nella pagina Piattaforma Notifiche si cambia pagina utilizzando una freccetta")
    public void nellaPaginaPiattaformaNotificheSiCambiaPaginaUtilizzandoUnaFreccetta() {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.siCambiaPaginaUtilizzandoUnaFrecetta(1);
    }

    @And("Nella pagina stato della piattaforma si cambia pagina utilizzando una freccetta {int}")
    public void nellaPaginaStatoDellaPiattaformaSiCambiaPaginaUtilizzandoUnaFreccetta(Integer numPage) {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.siCambiaPaginaUtilizzandoUnaFrecetta(numPage);
    }

    @And("Nella pagina stato della piattaforma si cambia pagina utilizzando una freccetta fino all'ultima")
    public void nellaPaginaStatoDellaPiattaformaSiCambiaPaginaUtilizzandoUnaFreccetta() {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.selezionaUltimaPaginaUtilizzandoUnaFrecetta();
    }

    @And("Nella pagina Piattaforma Notifiche si cambia pagina utilizzando un numero")
    public void nellaPaginaPiattaformaNotificheSiCambiaPaginaUtilizzandoUnNumero() {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.siCambiaPaginaUtilizzandoUnNumero();
    }

    @Then("Nella pagina Piattaforma Notifiche si cambia il numero elementi visualizzati attraverso il filtro")
    public void nellaPaginaPiattaformaNotificheSiCambiaIlNumeroElementiVisualizzatiAttraversoIlFiltroNumeroNotifiche() {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.siCambiaIlNumeroElementiVisualizzatiAttraversoIlFiltro();
    }

    @And("Nella pagina Piattaforma Notifiche si controlla che vengano visualizzate tutte notifiche")
    public void nellaPaginaPiattaformaNotificheSiControllaCheVenganoVisualizzateTutteNotifiche() {
        piattaformaNotifichePage.waitLoadPage();
        int numeroRighe = piattaformaNotifichePage.getNRighe();
        if (numeroRighe > 10) {
            logger.info("Numero righe differente da quello di default");
        } else {
            logger.error("Numero righe uguale da quello di default");
            Assert.fail("Numero righe uguale da quello di default");
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

        this.personeFisiche = dataPopulation.readDataPopulation("personeFisiche.yaml");
        int nPersoneFisicheInt = 1;
        if (isNumeric(npersoneFisiche)) {
            nPersoneFisicheInt = Integer.parseInt(npersoneFisiche) - 1;
            if (nPersoneFisicheInt > 4 || nPersoneFisicheInt == 0) {
                logger.error("Devi inserire un nummero da 1 a 5");
                Assert.fail("Devi inserire un nummero da 1 a 5");
            }
        } else {
            logger.error("Formato non accettato. Devi inserire un numero da 1 a 5");
            Assert.fail("Formato non accettato. Devi inserire un numero da 1 a 5");
        }

        destinatarioPASection.inserimentoMultiDestinatario(this.personeFisiche, nPersoneFisicheInt);
    }

    @And("Nella section Destinatario si cerca di aggiungere il sesto destinatario")
    public void nellaSectionDestinatarioSiCercaDiAggiungereIlSestoDestinatario() {
        logger.info("Si cerca di inserire il sesto destinatario");


        if (destinatarioPASection.inserireIlSestoDestinatario()) {
            logger.info("Non si riesce ad aggiungere il sesto destinatario");
        } else {
            logger.error("Si riesce ad aggiungere il sesto destinatario");
            Assert.fail("Si riesce ad aggiungere il sesto destinatario");
        }
    }

    @And("Nella section Destinatario si inserisce lo stesso destinatario di prima {string}")
    public void nellaSectionDestinatarioSiInserisceLoStessoDestinatarioDiPrima(String dpFile) {
        logger.info("Si inserisce lo stesso destinatario di prima");

        this.personaFisica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        destinatarioPASection.inserimentoMultiDestinatario(personaFisica, 1);
    }

    @Then("Si visualizza correttamente l errore di stesso codice fiscale")
    public void siVisualizzaCorrettamenteLErroreDiStessoCodiceFiscale() {
        logger.info("Si visualizzano i messaggi di errore del campo codice fiscale");
        destinatarioPASection.waitMessaggioErrore();
    }

    @And("Si verifica che la notifica sia nello stato avanzato")
    public void siVerificaCheLaNotificaSiaNelloStato() {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage((this.driver));

        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        boolean notificaTrovata = false;
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
                piattaformaNotifichePage.inserimentoCodiceIUN(datiNotifica.get("codiceIUN").toString());
                piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
            } else {
                notificaTrovata = true;
                break;
            }
        }
        if (!notificaTrovata) {
            logger.error("La notifica non è stata trovata dopo 1m40s");
            Assert.fail("La notifica non è stata trovata dopo 1m40s");
        }
    }

    @And("Si verifica che l'invio della pec sia in corso")
    public void siVerificaCheLInvioDellaPecSiaInCorso() {
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        dettaglioNotificaMittenteSection.clickVediPiuDettaglio();
        dettaglioNotificaMittenteSection.verificaInvioPECInCorso();
    }

    @And("Verifica dello stato della notifica inviata tramite pec come {string}")
    public void verificaDelloStatoDellaNotificaInviataTramitePecCome(String statoNotifica) {


        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        this.personeFisiche = dataPopulation.readDataPopulation("personaFisicaPec.yaml");

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String dataNotifica = dateFormat.format(date).replace("-", "/");

        String codiceIUNOld = this.datiNotifica.get("codiceIUN").toString();
        CookiesSection cookiesSection = new CookiesSection(this.driver);
        for (int i = 0; i < 12; i++) {
            if (i >= 1) {
                piattaformaNotifichePage.aggiornamentoPagina();
                if (!CookieConfig.isCookieEnabled()) {
                    if (cookiesSection.waitLoadCookiesPage()) {
                        cookiesSection.selezionaAccettaTuttiButton();
                    }
                }
                piattaformaNotifichePage.insertCodiceFiscale(this.personeFisiche.get("codiceFiscale").toString());
                piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
                piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
            }
            piattaformaNotifichePage.waitLoadRefreshPage();
            String codiceIUN = piattaformaNotifichePage.ricercaNotifica(this.datiNotifica.get("oggettoDellaNotifica").toString(), statoNotifica);
            if (!codiceIUN.equals("")) {
                if (!codiceIUN.equals(codiceIUNOld)) {
                    this.datiNotifica.put("codiceIUN", codiceIUN);
                    dataPopulation.writeDataPopulation("datiNotifica.yaml", this.datiNotifica);
                    logger.info("Stato notifica uguale a Depositata e codice IUN aggiornato correttamente");
                    return;
                }
            }
        }

        logger.error("Il server ha impiegato troppo tempo nel generare la notifica");
        Assert.fail("Il server ha impiegato troppo tempo nel generare la notifica");

    }

    @And("Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica pec {string}")
    public void nellaPaginaPiattaformaNotificheInserireIlCodiceIUNDellaNotificaPec(String dpDatiiNotifica) {
        logger.info("Si inserisce il codice IUN");

        this.datiNotifica = dataPopulation.readDataPopulation(dpDatiiNotifica + ".yaml");

        piattaformaNotifichePage.aggiornamentoPagina();
        piattaformaNotifichePage.waitLoadRefreshPage();
        piattaformaNotifichePage.inserimentoCodiceIUN(this.datiNotifica.get("codiceIUN").toString());
    }

    @And("Nella section si prova ad cliccare sul tasto continua senza aver inserito nessun dato")
    public void nellaSectionInformazioniPreliminariSiProvaAdCliccareSulTastoContinuaSenzaAverInseritoNessunDato() {
        logger.info("Si clicca sul tasto continua senza aver inserito nessun dato");

        piattaformaNotifichePage.clickContinuaDisabled();
    }

    @And("Nella section Informazioni preliminari inserire i dati della notifica sbagliati {string} senza pagamento")
    public void nellaSectionInformazioniPreliminariInserireIDatiDellaNotificaSbagliatiSenzaPagamento(String datiNotificaErrore) {
        logger.info("Si inserisce l'oggetto della notifica errato");

        Map<String, Object> datiNotificaErrore1 = dataPopulation.readDataPopulation(datiNotificaErrore + ".yaml");
        piattaformaNotifichePage.inserimentoOggettoNotificaErrato(datiNotificaErrore1.get("oggettoDellaNotifica").toString());
    }

    @And("Nella section Informazioni preliminari si visualizza un messaggio di errore")
    public void nellaSectionInformazioniPreliminariSiVisualizzaUnMessaggioDiErrore() {
        logger.info("Si verifica sia presente un messaggio di errore");

        if (piattaformaNotifichePage.errorMessage()) {
            logger.info("Si visualizza correttamente il messaggio di errore");
        } else {
            logger.error("Non si visualizza correttamente il messaggio di errore");
            Assert.fail("Non si visualizza correttamente il messaggio di errore");
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

    @And("Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica {string}")
    public void nellaSectionDestinatarioInserireRagioneSocialeEPartitaIVADallaPersonaGiuridica(String personaGiuridica) {
        logger.info("Si inserisce la ragione sociale e la partita iva della persona giuridica");

        this.personaGiuridica = dataPopulation.readDataPopulation(personaGiuridica + ".yaml");

        destinatarioPASection.insertRagioneSociale(this.personaGiuridica.get("ragioneSociale").toString());
        destinatarioPASection.insertPartitaIva(this.personaGiuridica.get("codiceFiscale").toString());
    }

    @And("Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona giuridica {string}")
    public void nellaSectionDestinatarioCliccareSuAggiungiDomicilioDigitaleCompilareIDatiDellaPersonaGiuridica(String personaGiuridica) {
        logger.info("Si inserisce un domicilio digitale della persona giuridica");

        this.personaGiuridica = dataPopulation.readDataPopulation(personaGiuridica + ".yaml");

        destinatarioPASection.checkBoxAggiungiDomicilio();
        destinatarioPASection.insertDomicilioDigitale(this.personaGiuridica.get("emailPec").toString());
    }

    @And("Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona giuridica {string}")
    public void nellaSectionDestinatarioCliccareSuAggiungiIndirizzoFisicoCompilareIDatiDellaPersonaGiuridica(String dpFile) {
        logger.info("Si inseriscono i dati personali della persona giuridica");

        this.personaGiuridica = dataPopulation.readDataPopulation(dpFile + ".yaml");


        destinatarioPASection.selezionaAggiungiUnIndirizzoFisico();
        destinatarioPASection.inserireIndirizzo(this.personaGiuridica.get("indirizzo").toString());
        destinatarioPASection.inserireNumeroCivico(this.personaGiuridica.get("numeroCivico").toString());
        destinatarioPASection.inserireLocalita(this.personaGiuridica.get("localita").toString());
        destinatarioPASection.inserireComune(this.personaGiuridica.get("comune").toString());
        destinatarioPASection.inserireProvincia(this.personaGiuridica.get("provincia").toString());
        destinatarioPASection.inserireCodicePostale(this.personaGiuridica.get("codicepostale").toString());
        destinatarioPASection.inserireStato(this.personaGiuridica.get("stato").toString());

    }

    @And("Nella section Destinatario inserire i dati errati dalla persona giuridica {string}")
    public void nellaSectionDestinatarioInserireIDatiErratiDallaPersonaGiuridica(String personaGiuridicaErrore) {
        logger.info("Si inseriscono i dati errati per persona giuridica");

        Map<String, Object> personaGiuridicaErrore1 = dataPopulation.readDataPopulation(personaGiuridicaErrore + ".yaml");

        destinatarioPASection.checkBoxAggiungiDomicilio();
        destinatarioPASection.insertDomicilioDigitaleErrato(personaGiuridicaErrore1.get("emailPec").toString());
        Assert.assertEquals("l'errore  attuale 'Indirizzo PEC non valido' è diverso di :" + destinatarioPASection.getDomicilioDigitaleError(), "Indirizzo PEC non valido", destinatarioPASection.getDomicilioDigitaleError());

        destinatarioPASection.insertCodiceFiscaleErrato(personaGiuridicaErrore1.get("codiceFiscale").toString());
        Assert.assertEquals("l'errore  attuale 'Il valore inserito non è corretto' è diverso di :" + destinatarioPASection.getCodiceFiscaleError(), "Il valore inserito non è corretto", destinatarioPASection.getCodiceFiscaleError());

    }

    @And("Nella section Allegati caricare l'atto e inserire il nome atto con estenzione non valida")
    public void nellaSectionAllegatiCaricareLAttoEInserireIlNomeAttoConEstenzioneNonValida() {
        logger.info("Si inserisce un file con estensione sbagliata");

        AllegatiPASection allegatiPASection = new AllegatiPASection(this.driver);
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
            Assert.fail("Non si visualizza correttamente il messaggio di errore: Estensione file non supportata. Riprovare con un altro file.");
        }
    }

    @And("Nella section Destinatario selezionare il radio button persona giuridica")
    public void nellaSectionDestinatarioSelezionareIlRadioButtonPersonaGiuridica() {
        logger.info("Si clicca su persona giuridica");
        destinatarioPASection.clickRadioButtonPersonaGiuridica();
    }

    @And("^Nella section Destinatario inserire i dati del destinatari persone giuridiche aggiuntivi per (.*)$")
    public void nellaSectionDestinatarioInserireIDatiDelDestinatariPersoneGiuridicheAggiuntiviPerNumeroDestinatari(String nDestinatari) {
        logger.info("Si cerca di aggiungere" + nDestinatari + " personeGiuridiche");

        Map<String, Object> personeGiuridiche = dataPopulation.readDataPopulation("personeGiuridiche.yaml");
        int nDestinatariInt = 1;
        if (isNumeric(nDestinatari)) {
            nDestinatariInt = Integer.parseInt(nDestinatari) - 1;
            if (nDestinatariInt > 4 || nDestinatariInt == 0) {
                logger.error("Devi inserire un nummero da 1 a 5");
                Assert.fail("Devi inserire un nummero da 1 a 5");
            }
        } else {
            logger.error("Formato non accettato. Devi inserire un numero da 1 a 5");
            Assert.fail("Formato non accettato. Devi inserire un numero da 1 a 5");
        }

        destinatarioPASection.inserimentoMultiDestinatarioPG(personeGiuridiche, nDestinatariInt);
    }

    @And("Nella section cliccare sul tasto torna a informazioni preliminari")
    public void nellaSectionCliccareSulTastoTornaAInformazioniPreliminari() {
        logger.info("Si cerca di tornare alla sezione Informazione Preliminari");

        destinatarioPASection.clickSuTornaInformazioniPreliminari();
    }

    @And("Nella section Destinatario inserire i dati del destinatario persona giuridica aggiuntiva")
    public void nellaSectionDestinatarioInserireIDatiDelDestinatarioPersonaGiuridichaAggiuntiva(Map<String, String> destinatario) {
        logger.info("Si cerca di aggiungere la persona giuridica aggiuntiva");
        destinatarioPASection.inserimentoDestinatarioPGAggiuntivo(destinatario);
    }

    @And("Verifica dello stato della notifica persona giuridica come depositata {string}")
    public void verificaDelloStatoDellaNotificaPersonaGiuridicaComeDepositata(String statoNotifica) {
        logger.info("Verifica dello stato della notifica come 'Depositata'");

        this.datiNotifica = dataPopulation.readDataPopulation("datiNotificaPG.yaml");
        this.personaFisica = dataPopulation.readDataPopulation("personaGiuridica.yaml");

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String dataNotifica = dateFormat.format(date).replace("-", "/");
        String codiceFiscale = this.personaFisica.get("codiceFiscale").toString();

        String codiceIUNOld = this.datiNotifica.get("codiceIUN").toString();
        CookiesSection cookiesSection = new CookiesSection(this.driver);
        String codiceIUN = "";
        for (int i = 0; i < 12; i++) {
            if (i >= 1) {
                piattaformaNotifichePage.aggiornamentoPagina();
                if (!CookieConfig.isCookieEnabled()) {
                    if (cookiesSection.waitLoadCookiesPage()) {
                        cookiesSection.selezionaAccettaTuttiButton();
                    }
                }
                piattaformaNotifichePage.insertCodiceFiscale(codiceFiscale);
                piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
                piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
            }
            piattaformaNotifichePage.waitLoadRefreshPage();
            codiceIUN = piattaformaNotifichePage.ricercaNotifica(this.datiNotifica.get("oggettoDellaNotifica").toString(), statoNotifica);
            if (!codiceIUN.equals("")) {
                if (!codiceIUN.equals(codiceIUNOld)) {
                    this.datiNotifica.put("codiceIUN", codiceIUN);
                    dataPopulation.writeDataPopulation("datiNotificaPG.yaml", this.datiNotifica);
                    logger.info("Stato notifica uguale a Depositata e codice IUN aggiornato correttamente");
                    break;
                }
            }
        }
        if (codiceIUN.equals("")) {
            logger.error("Notifica non trovata il sistema ha impiegato troppo tempo a rispondere");
            Assert.fail("Notifica non trovata il sistema ha impiegato troppo tempo a rispondere");
        }
    }

    @And("Nella pagina Piattaforma Notifiche si recupera l ultimo numero protocollo")
    public void nellaPaginaPiattaformaNotificheSiRecuperaLUltimoNumeroProtocollo() {
        String numeroProtocollo = getNumeroProtocollo();
        if (numeroProtocollo != null) {
            this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
            this.datiNotifica.put("numeroProtocollo", numeroProtocollo);
            dataPopulation.writeDataPopulation("datiNotifica.yaml", this.datiNotifica);
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
        informazioniPreliminariPASection.insertOggettoNotifica(datiNotifica.get("oggettoNotifica"));
        informazioniPreliminariPASection.insertDescrizione(datiNotifica.get("descrizione"));
        informazioniPreliminariPASection.insertNumeroDiProtocollo(numeroDiProtocollo);
        informazioniPreliminariPASection.insertGruppo(datiNotifica.get("gruppo"));
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
        String numeroDiProtocollo = WebTool.generatePaProtocolNumber();
        informazioniPreliminariPASection.insertOggettoNotifica(datiNotifica.get("oggettoNotifica"));
        informazioniPreliminariPASection.insertDescrizione(datiNotifica.get("descrizione"));
        informazioniPreliminariPASection.insertNumeroDiProtocollo(numeroDiProtocollo);
        informazioniPreliminariPASection.insertGruppo(datiNotifica.get("gruppo"));
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
        String nomeDestinatario = destinatario.get("nomeCognomeDestinatario");
        if (destinatario.get("soggettoGiuridico").equals("PF")) {
            destinatarioPASection.selezionarePersonaFisica();
            destinatarioPASection.inserireNomeDestinatario(nomeDestinatario.split(" ")[0]);
            destinatarioPASection.inserireCognomeDestinatario(nomeDestinatario.split(" ")[1]);
        } else {
            destinatarioPASection.clickRadioButtonPersonaGiuridica();
            destinatarioPASection.insertRagioneSociale(nomeDestinatario);
        }
        destinatarioPASection.inserireCodiceFiscaleDestinatario(destinatario.get("codiceFiscale"));
    }

    @And("Si verifica che il form di inserimento manuale della notifica è vuoto")
    public void siVerificaCheIlFormDiInserimentoManualeDellaNotificaEVuoto() {
        if (informazioniPreliminariPASection.checkFormInfoPreliminari()) {
            logger.info("Il form di inserimento manuale della notifica è vuoto");
        } else {
            logger.error("Il form di inserimento manuale della notifica non è vuoto");
            Assert.fail("Il form di inserimento manuale della notifica non è vuoto");
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
            Assert.fail("I campi sezione destinatario non sono popolati");
        }
    }

    @And("Nella section Destinitario si clicca su {string} e si inseriscono i dati")
    public void nellaSectionDestinitarioSiCliccaSuESiInserisconoIDati(String tipoIndirizzo, Map<String, String> indirizzo) {
        logger.info("Si clicca su " + tipoIndirizzo + " e si inseriscono i dati");
        if (tipoIndirizzo.contains("Aggiungi un indirizzo fisico")) {
            destinatarioPASection.selezionaAggiungiUnIndirizzoFisico();
        } else {
            destinatarioPASection.checkBoxAggiungiDomicilio();
            destinatarioPASection.insertDomicilioDigitale(indirizzo.get("digitalAddress"));
            return;
        }
        destinatarioPASection.inserireIndirizzo(indirizzo.get("indirizzo"));
        destinatarioPASection.inserireNumeroCivico(indirizzo.get("civico"));
        destinatarioPASection.inserireLocalita(indirizzo.get("localita"));
        destinatarioPASection.inserireComune(indirizzo.get("comune"));
        destinatarioPASection.inserireProvincia(indirizzo.get("provincia"));
        destinatarioPASection.inserireCodicePostale(indirizzo.get("cap"));
        destinatarioPASection.inserireStato(indirizzo.get("stato"));
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
            Assert.fail("File notifica.pdf non caricato");
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
            Assert.fail("File notifica.pdf non caricato");
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
            Assert.fail("File notifica.pdf non caricato");
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
        piattaformaNotifichePage.verificaNotificaCreata();
    }

    @Then("In parallelo si effettua l'accesso al portale destinatario {string} e si apre la notifica ricevuta")
    public void inParalleloSiEffettuaLAccessoAlPortaleDestinatarioESiApreLaNotificaRicevuta(String destinatario) {
        if (PF.equalsIgnoreCase(destinatario)) {
            WebTool.switchToPortal(AppPortal.PF);
        } else {
            WebTool.switchToPortal(AppPortal.PG);
        }
        piattaformaNotifichePage.selezionaPrimaNotifica();
        WebTool.waitTime(5);
        WebTool.closeTab();
    }

    @Then("In parallelo si effettua l'accesso al portale destinatario persona fisica e si verifica la timeline {string}")
    public void inParalleloSiEffettuaLAccessoAlPortaleDestinatarioPFESiVerificaLaTimeline(String messaggio) {
        WebTool.switchToPortal(AppPortal.PF);
        piattaformaNotifichePage.selezionaPrimaNotifica();
        WebTool.waitTime(5);
        piattaformaNotifichePage.visualizzaTimeline(messaggio);
        WebTool.closeTab();
    }

    @Then("In parallelo si effettua l'accesso al portale destinatario persona giuridica e si apre la notifica ricevuta")
    public void inParalleloSiEffettuaLAccessoAlPortaleDestinatarioPGESiApreLaNotificaRicevuta() {
        WebTool.switchToPortal(AppPortal.PG);
        piattaformaNotifichePage.selezionaPrimaNotifica();
        WebTool.waitTime(5);
        WebTool.closeTab();
    }

    @Then("In parallelo si effettua l'accesso al portale destinatario persona giuridica e si verifica la timeline {string}")
    public void inParalleloSiEffettuaLAccessoAlPortaleDestinatarioPGESiVerificaLaTimeline(String messagio) {
        WebTool.switchToPortal(AppPortal.PG);
        piattaformaNotifichePage.selezionaPrimaNotifica();
        WebTool.waitTime(5);
        piattaformaNotifichePage.visualizzaTimeline(messagio);
        WebTool.closeTab();
    }

    @Then("In parallelo si effettua l'accesso al portale di {string}")
    public void inParalleloSiEffettuaLAccessoAlPortaleDestinatario(String portal) {
        portal = portal.toLowerCase();
        switch (portal) {
            case PF:
                WebTool.switchToPortal(AppPortal.PF);
                break;
            case PG:
                WebTool.switchToPortal(AppPortal.PG);
                break;
            case PA:
                WebTool.switchToPortal(AppPortal.PA);
                break;
            default:
                logger.error("Tipologia di portale non specificato o errato!");
                Assert.fail("Tipologia di portale non specificato o errato!");
        }
        WebTool.waitTime(5);
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
        WebTool.waitTime(3);
        piattaformaNotifichePage.clickAnnullaNotificaModale();
        WebTool.waitTime(3);
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
        WebTool.waitTime(10);
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
        WebTool.waitTime(400);
        driver.navigate().refresh();
        WebTool.waitTime(3);
    }

    @And("Si seleziona la notifica")
    public void siSelezionaLaNotifica() {
        BackgroundTest backgroundTest = new BackgroundTest();
        String iun = notificationSingleton.getIun(Hooks.scenario);
        backgroundTest.siFiltraLaTabellaDelleNotifichePerIUNDestinatario(iun);
    }


    @And("Si seleziona la notifica mittente")
    public void siSelezionaLaNotificaMittente() {
        BackgroundTest backgroundTest = new BackgroundTest();
        String iun = notificationSingleton.getIun(Hooks.scenario);
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
        Assert.assertFalse( "Avviso PagoPA è trovato",dettaglioNotificaMittenteSection.checkAvvisoPagoPaVisibile());
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
        final String urlNotificationRequest = WebTool.getApiBaseUrl() + "notifications/sent";
        final String urlRichiestaNotifica = "https://api." + variabileAmbiente + ".notifichedigitali.it/delivery/v2.3/requests/";
        AccettazioneRichiestaNotifica accettazioneRichiestaNotifica = new AccettazioneRichiestaNotifica();
        String codiceApi;
        if (variabileAmbiente.equals("test")) {
            codiceApi = "2b3d47f4-44c1-4b49-b6ef-54dc1c531311";
        } else {
            codiceApi = "a9f0508d-c344-4347-807f-343bc8210996";
        }
        accettazioneRichiestaNotifica.setxApikey(codiceApi);
        String statusNotifica = "WAITING";
        WebTool.waitTime(5);
        String notificationRequestId = getNotificationRequestId(urlNotificationRequest);
        if (notificationRequestId == null) {
            logger.error("NotificationRequestId non trovato, il codice della risposta al url " + urlNotificationRequest + " è diverso di 202 ");
            Assert.fail("NotificationRequestId non trovato, il codice della risposta al url " + urlNotificationRequest + " è diverso di 202 ");
        }
        accettazioneRichiestaNotifica.setNotificationRequestId(notificationRequestId);
        accettazioneRichiestaNotifica.setRichiestaNotificaEndPoint(urlRichiestaNotifica);
        do {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean result = accettazioneRichiestaNotifica.runGetRichiestaNotifica();
            if (result) {
                statusNotifica = accettazioneRichiestaNotifica.getStatusNotifica();
                logger.info("lo stato della notifica è :" + statusNotifica);
            } else {
                if (accettazioneRichiestaNotifica.getResponseCode() != 200) {
                    logger.error("la risposta dell'accettazione della notifica " + notificationRequestId + " è: " + accettazioneRichiestaNotifica.getResponseCode());
                    Assert.fail("la risposta dell'accettazione della notifica " + notificationRequestId + " è: " + accettazioneRichiestaNotifica.getResponseCode());
                }
            }
        } while (statusNotifica.equals("WAITING"));
        return new EsitoNotifica(statusNotifica, accettazioneRichiestaNotifica, notificationRequestId);
    }

    @And("Si verifica che la notifica viene creata correttamente {string}")
    public void siVerificaCheLaNotificaVieneCreataCorrettamente(String dpFile) {
        EsitoNotifica esitoNotifica = this.siVerificaEsitoNotifica(dpFile);
        if (esitoNotifica.statusNotifica.equals("ACCEPTED")) {
            logger.info("La notifica è stata Accettata");
            String codiceIUN = esitoNotifica.accettazioneRichiestaNotifica.getCodiceIUN();
            datiNotifica = dataPopulation.readDataPopulation(dpFile + ".yaml");
            if (codiceIUN != null && !codiceIUN.isEmpty()) {
                datiNotifica.put("codiceIUN", codiceIUN);
                dataPopulation.writeDataPopulation(dpFile + ".yaml", datiNotifica);
                logger.info("La notifica è stata creata correttamente");
            }
        } else {
            logger.error("La notifica " + esitoNotifica.notificationRequestId + " è stata rifiuta: " + esitoNotifica.accettazioneRichiestaNotifica.getResponseReasonPhrase());
            Assert.fail("La notifica " + esitoNotifica.notificationRequestId + " è stata rifiuta: " + esitoNotifica.accettazioneRichiestaNotifica.getResponseReasonPhrase());
        }
    }

    @And("Si verifica che la notifica e' stata rifiutata {string}")
    public void siVerificaCheLaNotificaVieneRifiutata(String dpFile) {
        EsitoNotifica esitoNotifica = this.siVerificaEsitoNotifica(dpFile);
        if (esitoNotifica.statusNotifica.equals("REFUSED")) {
            logger.info("La notifica è stata Rifiutata");
        } else {
            logger.error("La notifica " + esitoNotifica.notificationRequestId + " è stata accettata: " + esitoNotifica.statusNotifica);
            logger.error(esitoNotifica.accettazioneRichiestaNotifica.getResponseBody());
            Assert.fail("La notifica " + esitoNotifica.notificationRequestId + " è stata accettata: ");
        }
    }

    private String getNotificationRequestId(String urlNotificationRequest) {
        /*
         * In case of error, I prefer to display a message that corresponds to the actual situation,
         * so now two different error cases are distinguished
         * (1) a POST request with the provided URL was found, but the status is not 202
         * (2) no POST requests with the provided URL were found
         */
        boolean foundRequestWithUndesiredStatus = false;
        for (NetWorkInfo netWorkInfo : netWorkInfos) {
            if (netWorkInfo.getRequestUrl().equals(urlNotificationRequest) && netWorkInfo.getRequestMethod().equals("POST") && netWorkInfo.getResponseStatus().equals("202")) {
                String values = netWorkInfo.getResponseBody();
                List<String> results = Splitter.on(CharMatcher.anyOf(",:")).splitToList(values);
                String result = results.get(1);
                return result.substring(1, result.length() - 1);
            } else if (netWorkInfo.getRequestUrl().equals(urlNotificationRequest) && netWorkInfo.getRequestMethod().equals("POST") && !netWorkInfo.getResponseStatus().equals("202")) {
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

    @And("Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica {string}")
    public void nellaSectionDestinatarioCliccareSuAggiungiDomicilioDigitaleCompilareIDatiDellaPersonaFisica(String dpFile) {
        logger.info("Si inserisce un domicilio digitale della persona giuridica");

        this.personaFisica = dataPopulation.readDataPopulation(dpFile + ".yaml");

        destinatarioPASection.checkBoxAggiungiDomicilio();
        destinatarioPASection.insertDomicilioDigitale(this.personaFisica.get("emailPecErrore").toString());
    }

    @And("Nella pagina Piattaforma Notifiche si recupera un codice IUN valido")
    public void nellaPaginaPiattaformaNotificheSiRecuperaUnCodiceIUNValido() {
        logger.info("Si recupera un codice IUN valido");

        List<String> codiciIun = piattaformaNotifichePage.getCodiceIunPresenti();
        this.personaFisica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        String codiceIun = this.personaFisica.get("codiceIUN").toString();
        if (codiciIun.contains(codiceIun)) {
            piattaformaNotifichePage.inserimentoCodiceIUN(codiceIun);
        } else {
            piattaformaNotifichePage.inserimentoCodiceIUN(codiciIun.get(0));
            this.personaFisica.put("codiceIUN", codiciIun.get(0));
            dataPopulation.writeDataPopulation("datiNotifica.yaml", this.personaFisica);
        }
    }

    @And("Si verifica che la notifica sia nello stato consegnata")
    public void siVerificaCheLaNotificaSiaNelloStatoConsegnata() {
        logger.info("Si verifica che la notifica sia nello stato consegnata");

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage((this.driver));

        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
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
                piattaformaNotifichePage.inserimentoCodiceIUN(datiNotifica.get("codiceIUN").toString());
                piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
            } else {
                notificaTrovata = true;
                break;
            }
        }
        if (!notificaTrovata) {
            logger.error("La notifica non è stata trovata dopo 1m40s");
            Assert.fail("La notifica non è stata trovata dopo 1m40s");
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

        Assert.assertTrue("Nessun errore visualizzato insirendo CF sbagliato", piattaformaNotifichePage.controlloEsistenzaMessagioErroreCF());
    }

    @And("Nella pagina Piattaforma Notifiche si controlla che il bottone Filtra sia attivo")
    public void nellaPaginaPiattaformaNotificheSiControllacheFiltraSiaDisattivo() {
        logger.info("Si controlla che il bottone Filtra sia attivo");

        Assert.assertFalse("il bottone Filtra è disabilitato", piattaformaNotifichePage.verificaBottoneFiltraDisabilitato());
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

        Assert.assertTrue("Nessun errore visualizzato insirendo IUN sbagliato", piattaformaNotifichePage.controlloEsistenzaMessagioErroreIUN());
        logger.info("Messaggio di errore 'Inserisci un codice IUN valido' trovato");
    }

    @Then("Nella section si visualizza correttamente i campi vuoti")
    public void nellaSectionSiVisualizzaCorrettamenteICampiVuoti() {
        logger.info("Si verifica che i campi sono vuoti");

        if (piattaformaNotifichePage.verificaCampiPreliminariNotificaVuoti()) {
            logger.info("I campi sono vuoti");
        } else {
            Assert.fail("I campi non sono vuoti");
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
        AllegatiPASection allegatiPASection = new AllegatiPASection(driver);
        File notificaFile = new File("src/test/resources/notifichePdf/notifica.pdf");
        datiNotifica = dataPopulation.readDataPopulation(datiNotificaMap.get("nomeFileYaml") + ".yaml");

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
            Assert.fail("File notifica.pdf non caricato");
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
        WebTool.waitTime(2);
        if (viewDetail.equals("true")){
            dettaglioNotificaMittenteSection.clickVediPiuDettaglio();
        }
        dettaglioNotificaMittenteSection.checkStatoTimeline(idStato);
    }

    @And("Si verifica che la ricevuta di postalizzazione sia cliccabile")
    public void siVerificaLaCliccabilitaDellaRicevutaDiPostalizzazione(Map<String, String> datiDettaglioNotifica) {
        String idStato = datiDettaglioNotifica.get("xpathStato");
        String viewDetail = datiDettaglioNotifica.get("vediDettagli");
        siVisualizzaCorrettamenteLElencoCompletoDegliStatiCheLaNotificaHaPercorso();
        WebTool.waitTime(2);
        if (viewDetail.equals("true")){
            dettaglioNotificaMittenteSection.clickVediPiuDettaglio();
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


}
