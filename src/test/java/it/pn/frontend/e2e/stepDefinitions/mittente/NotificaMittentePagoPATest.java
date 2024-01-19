package it.pn.frontend.e2e.stepDefinitions.mittente;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.mittente.AccettazioneRichiestaNotifica;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.mittente.AreaRiservataPAPage;
import it.pn.frontend.e2e.pages.mittente.InvioNotifichePAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.mittente.*;
import it.pn.frontend.e2e.utility.CookieConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
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
    private Map<String, Object> datiNotifica = new HashMap<>();
    private Map<String, Object> personaFisica = new HashMap<>();
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
    private Map<String, Object> personaGiuridica = new HashMap<>();
    private Map<String, Object> personeFisiche = new HashMap<>();
    private final PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
    private final DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
    private final DataPopulation dataPopulation = new DataPopulation();
    private final DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);

    @When("Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche")
    public void nellaHomePageMittenteCliccareSuGestisciDiPiattaforma() {
        AreaRiservataPAPage areaRiservataPAPage = new AreaRiservataPAPage(this.driver);
        String variabileAmbiente = System.getProperty("environment");
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

        String variabileAmbiente = System.getProperty("environment");
        String urlChiamata = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/delivery/notifications/sent?";
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
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String urlNotifiche = "https://webapi.test.notifichedigitali.it/delivery/notifications/";
        for (NetWorkInfo netWorkInfo : netWorkInfos) {
            if (netWorkInfo.getRequestUrl().contains(urlNotifiche) && netWorkInfo.getRequestUrl().endsWith("size=50")) {
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

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionInformazioniPreliminari() {
        logger.info("Verifica visualizzazione section Informazioni preliminari");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        InformazioniPreliminariPASection informazioniPreliminariPASection = new InformazioniPreliminariPASection(this.driver);
        informazioniPreliminariPASection.waitLoadInformazioniPreliminariPASection();
    }

    @And("Nella section Informazioni preliminari inserire i dati della notifica {string} senza pagamento")
    public void nellaSectionInformazioniPreliminariInserireIDatiDellaNotificaSenzaPagamento(String datiNotificaFile) {
        logger.info("Inserimento dei dati della notifica senza pagamento dal file " + datiNotificaFile + ".yaml");

        aggiornamentoNumeroProtocollo();

        this.datiNotifica = dataPopulation.readDataPopulation(datiNotificaFile + ".yaml");

        String variabileAmbiente = System.getProperty("environment");
        String gruppo = "";
        switch (variabileAmbiente) {
            case "dev" -> gruppo = datiNotifica.get("gruppoDev").toString();
            case "test", "uat" -> gruppo = datiNotifica.get("gruppoTest").toString();
        }
        InformazioniPreliminariPASection informazioniPreliminariPASection = new InformazioniPreliminariPASection(this.driver);
        informazioniPreliminariPASection.insertOggettoNotifica(this.datiNotifica.get("oggettoDellaNotifica").toString());
        informazioniPreliminariPASection.insertDescrizione(this.datiNotifica.get("descrizione").toString());
        informazioniPreliminariPASection.insertNumeroDiProtocollo(this.datiNotifica.get("numeroProtocollo").toString());
        informazioniPreliminariPASection.insertGruppo(gruppo);
        informazioniPreliminariPASection.insertCodiceTassonometrico(this.datiNotifica.get("codiceTassonometrico").toString());
        informazioniPreliminariPASection.selectRaccomandataAR();
    }

    private void aggiornamentoNumeroProtocollo() {
        logger.info("Aggiornamento del numero protocollo");


        String numeroProtocolOld = dataPopulation.readDataPopulation("datiNotifica.yaml").get("numeroProtocollo").toString();
        String identificativoProtocollo = substring(numeroProtocolOld, 0, 10);
        if (identificativoProtocollo.length() < 10) {
            identificativoProtocollo = "TA-FFSMRC-";
        }
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
            numeroProtocol = identificativoProtocollo + dataProtocol + "-" + counter;
        } else {
            numeroProtocol = identificativoProtocollo + dataProtocol + "-0";
        }

        logger.info("numero Protocollo generato : " + numeroProtocol);

        Map<String, Object> allDatataPopulation = dataPopulation.readDataPopulation("datiNotifica.yaml");
        allDatataPopulation.put("numeroProtocollo", numeroProtocol);
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
        } catch (Exception exc) {
            logger.error(exc.toString());
            throw new RuntimeException(exc);
        }
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
                piattaformaNotifichePage.aggionamentoPagina();
                piattaformaNotifichePage.insertCodiceFiscale(this.personaFisica.get("codiceFiscale").toString());
                piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
                piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePage.selectFiltraButton();
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

    @And("Nella pagina dettaglio notifica cliccare sull'opzione vedi più dettagli")
    public void nellaPaginaDettaglioNotificaCliccareSullOpzioneVediPiuDettagli() {
        dettaglioNotificaMittenteSection.waitLoadDettaglioNotificaSection();
        this.dettaglioNotificaMittenteSection.clickVediPiuDettaglio();
    }

    @And("Si visualizza correttamente l elenco completo degli stati che la notifica ha percorso")
    public void siVisualizzaCorrettamenteLElencoCompletoDegliStatiCheLaNotificaHaPercorso() {
        dettaglioNotificaMittenteSection.waitLoadDettaglioNotificaSection();
        dettaglioNotificaMittenteSection.siVisualizzaPercosoNotifica();

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
        piattaformaNotifichePage.siCambiaPaginaUtilizzandoUnaFrecetta();
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
        logger.info("Si visualizza il messaggio di errore stesso codice fiscale");

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
                piattaformaNotifichePage.aggionamentoPagina();
                piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
                piattaformaNotifichePage.inserimentoCodiceIUN(datiNotifica.get("codiceIUN").toString());
                piattaformaNotifichePage.selectFiltraButton();
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
                piattaformaNotifichePage.aggionamentoPagina();
                if (!CookieConfig.isCookieEnabled()) {
                    if (cookiesSection.waitLoadCookiesPage()) {
                        cookiesSection.selezionaAccettaTuttiButton();
                    }
                }
                piattaformaNotifichePage.insertCodiceFiscale(this.personeFisiche.get("codiceFiscale").toString());
                piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
                piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePage.selectFiltraButton();
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

        piattaformaNotifichePage.aggionamentoPagina();
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
                piattaformaNotifichePage.aggionamentoPagina();
                if (!CookieConfig.isCookieEnabled()) {
                    if (cookiesSection.waitLoadCookiesPage()) {
                        cookiesSection.selezionaAccettaTuttiButton();
                    }
                }
                piattaformaNotifichePage.insertCodiceFiscale(codiceFiscale);
                piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
                piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePage.selectFiltraButton();
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

    /**
     * Factorize out the assessment of the esito notifica from siVerificaCheLaNotificaVieneCreataCorrettamente,
     * this was motivated by the need to add siVerificaCheLaNotificaVieneRifiutata
     * that needs to perform the same assessment.
     */
    protected EsitoNotifica siVerificaEsitoNotifica(String dpFile) {
        logger.info("si verifica se la notifica è stata accettata o rifiutata");
        String variabileAmbiente = System.getProperty("environment");
        final String urlNotificationRequest = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/delivery/v2.1/requests";
        final String urlRichiestaNotifica = "https://api." + variabileAmbiente + ".notifichedigitali.it/delivery/v2.1/requests/";
        AccettazioneRichiestaNotifica accettazioneRichiestaNotifica = new AccettazioneRichiestaNotifica();
        String codiceApi;
        if (variabileAmbiente.equals("test")) {
            codiceApi = dataPopulation.readDataPopulation("mittente.yaml").get("codiceApiKeyTEST").toString();
        } else {
            codiceApi = dataPopulation.readDataPopulation("mittente.yaml").get("codiceApiKeyDEV").toString();
        }
        accettazioneRichiestaNotifica.setxApikey(codiceApi);
        String statusNotifica = "WAITING";
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
            this.datiNotifica = dataPopulation.readDataPopulation(dpFile + ".yaml");
            if (codiceIUN != null && !codiceIUN.equals("")) {
                this.datiNotifica.put("codiceIUN", codiceIUN);
                this.dataPopulation.writeDataPopulation(dpFile + ".yaml", this.datiNotifica);
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
            logger.error(esitoNotifica.accettazioneRichiestaNotifica.getresponseBody());
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
                piattaformaNotifichePage.aggionamentoPagina();
                piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
                piattaformaNotifichePage.inserimentoCodiceIUN(datiNotifica.get("codiceIUN").toString());
                piattaformaNotifichePage.selectFiltraButton();
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
        piattaformaNotifichePage.selectFiltraButton();
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
        piattaformaNotifichePage.selectFiltraButton();
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
}
