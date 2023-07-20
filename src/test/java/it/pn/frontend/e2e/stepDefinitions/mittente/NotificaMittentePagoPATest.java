package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.mittente.AreaRiservataPAPage;
import it.pn.frontend.e2e.pages.mittente.InvioNotifichePAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePAPage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.mittente.*;
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

    @When("Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche")
    public void nellaHomePageMittenteCliccareSuGestisciDiPiattaforma() {
        logger.info("Cliccare sul bottone Gestisci di Piattaforma Notifiche");
        AreaRiservataPAPage areaRiservataPAPage = new AreaRiservataPAPage(this.driver);
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente){
            case "dev" -> areaRiservataPAPage.selezionaPiattaformaNotificaDev();
            case "test" -> areaRiservataPAPage.selezionaPiattaformaNotificaTest();
            case "uat" -> areaRiservataPAPage.selezionaPiattaformaNotificaUat();
        }
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotifiche() {
        boolean httpRequestToken = false;
        for (int index = 0; index < 30; index++){

            if (this.readHttpRequest()){
                httpRequestToken = true;
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (httpRequestToken){
            logger.info("Http token mittente found");
        }else {
            logger.error("Http token mittente not found");
            Assert.fail("Http token mittente not found");
        }
        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();
        CookiesSection cookiesSection = new CookiesSection(this.driver);
        if (cookiesSection.waitLoadCookiesPage()){
            cookiesSection.selezionaAccettaTuttiButton();
        }
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.waitLoadPiattaformaNotifichePAPage();
    }

    @And("Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica")
    public void nellaPaginaPiattaformaNotificheCliccareSulBottoneInviaUnaNuovaNotifica() {
        logger.info("selezione bottone invia una nuova notifica");

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.selectInviaUnaNuovaNotificaButton();
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionInformazioniPreliminari() {
        logger.info("verifica visualizzazione section Informazioni preliminari");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        InformazioniPreliminariPASection informazioniPreliminariPASection = new InformazioniPreliminariPASection(this.driver);
        informazioniPreliminariPASection.waitLoadInformazioniPreliminariPASection();
    }

    @And("Nella section Informazioni preliminari inserire i dati della notifica {string} senza pagamento")
    public void nellaSectionInformazioniPreliminariInserireIDatiDellaNotificaSenzaPagamento(String datiNotificaFile) {
        logger.info("Inserimento dei dati della notifica senza pagamento dal file datiNotifica.yaml");

        aggiornamentoNumeroProtocollo();
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(datiNotificaFile + ".yaml");

        String variabileAmbiente = System.getProperty("environment");
        String gruppo="";
        switch (variabileAmbiente) {
            case "dev" ->  gruppo = datiNotifica.get("gruppoDev").toString();
            case "test", "uat" -> gruppo = datiNotifica.get("gruppoTest").toString();
        }
        InformazioniPreliminariPASection informazioniPreliminariPASection = new InformazioniPreliminariPASection(this.driver);
        informazioniPreliminariPASection.insertNumeroDiProtocollo(this.datiNotifica.get("numeroProtocollo").toString());
        informazioniPreliminariPASection.insertOggettoNotifica(this.datiNotifica.get("oggettoDellaNotifica").toString());
        informazioniPreliminariPASection.insertDescrizione(this.datiNotifica.get("descrizione").toString());
        informazioniPreliminariPASection.insertGruppo(gruppo);
        informazioniPreliminariPASection.insertCodiceTassonometrico(this.datiNotifica.get("codiceTassonometrico").toString());
        informazioniPreliminariPASection.selectRaccomandataAR();
    }

    private void aggiornamentoNumeroProtocollo() {
        logger.info("Aggiornamento del numero protocollo");

        DataPopulation dataPopulation = new DataPopulation();
        String numeroProtocolOld = dataPopulation.readDataPopulation("datiNotifica.yaml").get("numeroProtocollo").toString();
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

    @And("Cliccare su continua")
    public void cliccareSuContinua() {
        InvioNotifichePAPage invioNotifichePAPage = new InvioNotifichePAPage(this.driver);
        invioNotifichePAPage.selezionareContinuaButton();
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionDestinatario() {
        logger.info("Verifica visualizzazione della section Destinatario");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
        destinatarioPASection.waitLoadDestinatarioPASection();
    }

    @And("Nella section Destinatario inserire nome cognome e codice fiscale da destinatario {string}")
    public void nellaSectionDestinatarioInserireNomeCognomeECodiceFiscaleDaDestinatario(String destinatarioFile) {
        logger.info("Inserimento del nome cognome e codice fiscale dal file personaFisica.yaml");

        DataPopulation dataPopulation = new DataPopulation();
        this.personaFisica = dataPopulation.readDataPopulation(destinatarioFile + ".yaml");

        DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
        destinatarioPASection.selezionarePersonaFisica();
        destinatarioPASection.inserireNomeDestinatario(this.personaFisica.get("name").toString());
        destinatarioPASection.inserireCognomeDestinatario(this.personaFisica.get("familyName").toString());
        destinatarioPASection.inserireCodiceFiscaleDestinatario(this.personaFisica.get("codiceFiscale").toString());
    }

    @And("Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati del destinatario {string}")
    public void nellaSectionDestinatarioCliccareSuAggiungiIndirizzoFisicoCompilareIDatiDelDestinatario(String destinatarioFile) {
        logger.info("Inserimento dei dati mancanti nella section destinatario");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        DataPopulation dataPopulation = new DataPopulation();
        this.personaFisica = dataPopulation.readDataPopulation(destinatarioFile + ".yaml");

        DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
        destinatarioPASection.selezionaAggiungiUnIndirizzoFisico();
        destinatarioPASection.inserireIndirizzo(this.personaFisica.get("indirizzo").toString());
        destinatarioPASection.inserireNumeroCivico(this.personaFisica.get("numeroCivico").toString());
        destinatarioPASection.inserireLocalita(this.personaFisica.get("localita").toString());
        destinatarioPASection.inserireComune(this.personaFisica.get("comune").toString());
        destinatarioPASection.inserireProvincia(this.personaFisica.get("provincia").toString());
        destinatarioPASection.inserireCodicePostale(this.personaFisica.get("codicepostale").toString());
        destinatarioPASection.inserireStato(this.personaFisica.get("stato").toString());
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

        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(datiNotificaFile + ".yaml");

        allegatiPASection.inserimentoNomeAllegato(this.datiNotifica.get("nomeDocumentoNotifica").toString());
    }

    @And("Nella section Allegati cliccare sul bottone Invia")
    public void nellaSectionAllegatiCliccareSulBottoneInvia() {
        logger.info("Cliccare sul bottone Invia");

        AllegatiPASection allegatiPASection = new AllegatiPASection(this.driver);
        allegatiPASection.selectInviaButton();
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

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        this.personaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String dataNotifica = dateFormat.format(date).replace("-", "/");

        String coidiceIUNOld = this.datiNotifica.get("codiceIUN").toString();
        CookiesSection cookiesSection = new CookiesSection(this.driver);
        for(int i=0; i<12; i++){
            if(i>=1){
                piattaformaNotifichePAPage.aggionamentoPagina();
                if (cookiesSection.waitLoadCookiesPage()){
                    cookiesSection.selezionaAccettaTuttiButton();
                }
                piattaformaNotifichePAPage.insertCodiceFiscale(this.personaFisica.get("codiceFiscale").toString());
                piattaformaNotifichePAPage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
                piattaformaNotifichePAPage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePAPage.selectFiltraButton();
            }
            piattaformaNotifichePAPage.waitLoadRefreshPage();
            String codiceIUN = piattaformaNotifichePAPage.ricercaNotifica(this.datiNotifica.get("oggettoDellaNotifica").toString(), statoNotifica);
            if(codiceIUN != null){
                if(!codiceIUN.equals(coidiceIUNOld)){
                    this.datiNotifica.put("codiceIUN",codiceIUN);
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
        CookiesSection cookiesSection = new CookiesSection(this.driver);
        cookiesSection.waitLoadCookiesPage();
        cookiesSection.selezionaAccettaTuttiButton();
    }

    @And("Nella pagina dettaglio notifica cliccare sull'opzione vedi più dettagli")
    public void nellaPaginaDettaglioNotificaCliccareSullOpzioneVediPiuDettagli() {
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.clickVediPiuDettaglio();
    }

    @And("Si visualizza correttamente l elenco completo degli stati che la notifica ha percorso")
    public void siVisualizzaCorrettamenteLElencoCompletoDegliStatiCheLaNotificaHaPercorso() {
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        int nStatiNotifica = dettaglioNotificaSection.siVisualizzaPercosoNotifica();
        if (nStatiNotifica >= 1){
            logger.info("L'elenco degli stati presente");
        }else {
            logger.error("L'elenco degli stati NON presente");
            Assert.fail("L'elenco degli stati NON presente");
        }
    }

    @Then("Si clicca sul bottone indietro")
    public void siCliccaSulBottoneIndietro() {
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.clickIndietroButton();
    }
    private boolean readHttpRequest() {
        boolean urlFound = false;
        for (NetWorkInfo netWorkInfo : netWorkInfos) {
            //logger.info(netWorkInfo.getRequestId());
            logger.info(netWorkInfo.getRequestUrl());
            //logger.info(netWorkInfo.getRequestMethod());
            logger.info(netWorkInfo.getResponseStatus());
            //logger.info(netWorkInfo.getResponseBody());
            String variabileAmbiente = System.getProperty("environment");
            String urlToFind = "https://webapi."+variabileAmbiente+".notifichedigitali.it/token-exchange";
            urlFound = false;
            if (netWorkInfo.getRequestUrl().contains(urlToFind)) {
                urlFound = true;
                break;
            }

        }
        return urlFound;
    }

    @And("Nella pagina Piattaforma Notifiche visualizzano correttamente i filtri di ricerca")
    public void nellaPaginaPiattaformaNotificheVisualizzanoCorrettamenteIFiltriDiRicerca() {
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.siVisualizzaCorrettamenteIlCFField();
        piattaformaNotifichePAPage.siVisualizzaCorrettamenteIlCodiceIUNField();
        piattaformaNotifichePAPage.siVisualizzaCorrettamenteLoStatoField();
        piattaformaNotifichePAPage.siVisualizzaCorrettamenteLaDataInzioField();
        piattaformaNotifichePAPage.siVisualizzaCorrettamenteLaDataFineField();
    }

    @Then("Nella pagina Piattaforma Notifiche si visualizza correttamente l'elenco delle notifiche")
    public void nellaPaginaPiattaformaNotificheSiVisualizzaCorrettamenteLElencoDelleNotifiche() {
        logger.info("Si visualizza l'elenco delle notifiche");
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        int nDateNotifiche = piattaformaNotifichePAPage.controlloNumeroRisultatiDate();
        if (nDateNotifiche >= 1) {
            logger.info("Le date delle notifiche vengono visualizzate correttamente");
        } else {
            logger.error("Le date delle notifiche non vengono visualizzate correttamente");
        }
        if (piattaformaNotifichePAPage.verificaEsistenzaCFNotifiche()) {
            logger.info("I codici fiscali delle notifiche vengono visualizzati correttamente");
        } else {
            logger.error("I codici fiscali delle notifiche non vengono visualizzati correttamente");
            Assert.fail("I codici fiscali delle notifiche non vengono visualizzati correttamente");
        }
        List<WebElement> listeOggetti = piattaformaNotifichePAPage.ricercaListaOggetti();
        if (listeOggetti != null && listeOggetti.size() >=1 ){
            logger.info("La lista degli oggetti viene visualizzata correttamente");
        }else {
            logger.error("La lista degli oggetti non viene visualizzata correttamente");
            Assert.fail("La lista degli oggetti non viene visualizzata correttamente");
        }

        if(piattaformaNotifichePAPage.verificaEsistenzaCodiceIUNNotifiche()){
            logger.info("La lista dei codici iun viene visualizzata correttamente");
        }else {
            logger.error("La lista dei codici iun non viene visualizzata correttamente");
            Assert.fail("La lista dei codici iun non viene visualizzata correttamente");
        }

        if (piattaformaNotifichePAPage.verificaEsistenzaGruppoNotifiche()){
            logger.info("La lista dei gruppi vengono visualizzate correttamente");
        }else {
            logger.error("La lista dei gruppi non vengono visualizzate correttamente");
            Assert.fail("La lista dei gruppi non vengono visualizzate correttamente");
        }

        if(piattaformaNotifichePAPage.verificaEsistenzaStatoNotifiche()){
            logger.info("La lista degli stati viene visualizzata correttamente");
        }else {
            logger.error("La lista degli stati non viene visualizzata correttamente");
            Assert.fail("La lista degli stati non viene visualizzata correttamente");
        }
    }

    @And("Nella pagina Piattaforma Notifiche si visualizzano le notifiche a partire dalla più recente")
    public void nellaPaginaPiattaformaNotificheSiVisualizzanoLeNotificheAPartireDallaPiuRecente() {
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(driver);
        piattaformaNotifichePAPage.controlloOrdineNotifiche();
    }

    @And("Nella pagina Piattaforma Notifiche si scrolla fino alla fine della pagina")
    public void nellaPaginaPiattaformaNotificheSiScrollaFinoAllaFineDellaPagina() {
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(driver);
        piattaformaNotifichePAPage.siScrollaFinoAllaFineDellaPagina();
    }

    @And("Nella pagina Piattaforma Notifiche si controlla che vengano visualizzate dieci notifiche")
    public void nellaPaginaPiattaformaNotificheSiControllaCheVenganoVisualizzateNotifiche() {
        logger.info("si controlla che vengono visualizzate dieci notifiche");

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        String nNotificheInviate = piattaformaNotifichePAPage.numeroNotifiche();
        if (nNotificheInviate.equals("10")){
            logger.info("Il numero di notifiche e corretto");
        }else {
            logger.error("Il numero di notifiche non e coretto");
            Assert.fail("Il numero di notifiche non e coretto");
        }
    }

    @And("Nella pagina Piattaforma Notifiche si cambia pagina utilizzando una freccetta")
    public void nellaPaginaPiattaformaNotificheSiCambiaPaginaUtilizzandoUnaFreccetta() {
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(driver);
        piattaformaNotifichePAPage.siCambiaPaginaUtilizzandoUnaFrecetta();
    }

    @And("Nella pagina Piattaforma Notifiche si cambia pagina utilizzando un numero")
    public void nellaPaginaPiattaformaNotificheSiCambiaPaginaUtilizzandoUnNumero() {
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(driver);
        piattaformaNotifichePAPage.siCambiaPaginaUtilizzandoUnNumero();
    }

    @Then("Nella pagina Piattaforma Notifiche si cambia il numero elementi visualizzati attraverso il filtro")
    public void nellaPaginaPiattaformaNotificheSiCambiaIlNumeroElementiVisualizzatiAttraversoIlFiltroNumeroNotifiche() {
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(driver);
        piattaformaNotifichePAPage.siCambiaIlNumeroElementiVisualizzatiAttraversoIlFiltro();
    }

    @And("Nella pagina Piattaforma Notifiche si controlla che vengano visualizzate tutte notifiche")
    public void nellaPaginaPiattaformaNotificheSiControllaCheVenganoVisualizzateTutteNotifiche() {
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.waitLoadPage();
        int numeroRighe = piattaformaNotifichePAPage.getNRighe();
        if (numeroRighe > 10){
            logger.info("Numero righe differente da quello di default");
        }else {
            logger.error("Numero righe uguale da quello di default");
            Assert.fail("Numero righe uguale da quello di default");
        }
    }

    @And("Nella section Destinatario cliccare su Aggiungi destinatario")
    public void nellaSectionDestinatarioCliccareSuAggiungiDestinatario() {
        logger.info("Si sta cercando di selezionare il buttone aiggiungere Destinatario");
        DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
        destinatarioPASection.selezionareAggiungiDestinatarioButton();
    }

    @And("^Nella section Destinatario inserire i dati del destinatari aggiuntivi  per (.*)$")
    public void nellaSectionDestinatarioInserireIDatiDelDestinatariAggiuntiviPerNumeroDestinatari(String nDestinatari) {
        logger.info("Si cerca di aggiungere" + nDestinatari + " destinatari");
        DataPopulation dataPopulation = new DataPopulation();
        this.personaFisica = dataPopulation.readDataPopulation("destinatari.yaml");
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
        DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
        destinatarioPASection.inserimentoMultiDestinatario(this.personaFisica, nDestinatariInt);
    }

    @And("Nella section Destinatario si cerca di aggiungere il sesto destinatario")
    public void nellaSectionDestinatarioSiCercaDiAggiungereIlSestoDestinatario() {
        logger.info("Si cerca di inserire il sesto destinatario");

        DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
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

        DataPopulation dataPopulation = new DataPopulation();
        this.personaFisica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        
        DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
        destinatarioPASection.inserimentoMultiDestinatario(personaFisica,1);

    }

    @Then("Si visualizza correttamente l errore di stesso codice fiscale")
    public void siVisualizzaCorrettamenteLErroreDiStessoCodiceFiscale() {
        logger.info("Si visualizza il messaggio di errore stesso codice fiscale");
        DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
        destinatarioPASection.waitMessaggioErrore();
    }

    @And("Si verifica che la notifica sia nello stato {string}")
    public void siVerificaCheLaNotificaSiaNelloStato(String statoNotifca) {
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage((this.driver));
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        boolean notificaTrovata = false;
        for (int i = 0; i<10; i++){
            int numeroNotifiche = piattaformaNotifichePAPage.getListStato(statoNotifca);
            if(numeroNotifiche ==0){
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                piattaformaNotifichePAPage.aggionamentoPagina();
                piattaformaNotifichePAPage.inserimentoCodiceIUN(datiNotifica.get("codiceIUN").toString());
                piattaformaNotifichePAPage.selectFiltraButton();
            } else {
                notificaTrovata = true;
                break;
            }
        }
        if(!notificaTrovata){
            logger.error("La notifica non è stata trovata dopo 1m40s");
            Assert.fail("La notifica non è stata trovata dopo 1m40s");
        }
    }

    @And("Si verifica sia presente l'indirizzo mail del destinatario {string} nei dettagli della notifica")
    public void siVerificaSiaPresenteLIndirizzoMailDelDestinatarioNeiDettagliDellaNotifica(String mailPEC) {
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.clickVediPiuDettaglio();
        int numeroPecTrovate = dettaglioNotificaSection.controlloNumeroPec(mailPEC);
        if(numeroPecTrovate != 0){
            logger.info(" La pec è in invio in corso");
        }else {
            logger.error("La pec NON è in invio in corso");
            Assert.fail("La pec NON è in invio in corso");
        }
    }

    @And("Verifica dello stato della notifica inviata tramite pec come {string}")
    public void verificaDelloStatoDellaNotificaInviataTramitePecCome(String statoNotifica) {
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        this.personaFisica = dataPopulation.readDataPopulation("personaFisicaPec.yaml");

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String dataNotifica = dateFormat.format(date).replace("-", "/");

        String coidiceIUNOld = this.datiNotifica.get("codiceIUN").toString();
        CookiesSection cookiesSection = new CookiesSection(this.driver);
        for(int i=0; i<12; i++){
            if(i>=1){
                piattaformaNotifichePAPage.aggionamentoPagina();
                if (cookiesSection.waitLoadCookiesPage()){
                    cookiesSection.selezionaAccettaTuttiButton();
                }
                piattaformaNotifichePAPage.insertCodiceFiscale(this.personaFisica.get("codiceFiscale").toString());
                piattaformaNotifichePAPage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
                piattaformaNotifichePAPage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePAPage.selectFiltraButton();
            }
            piattaformaNotifichePAPage.waitLoadRefreshPage();
            String codiceIUN = piattaformaNotifichePAPage.ricercaNotifica(this.datiNotifica.get("oggettoDellaNotifica").toString(), statoNotifica);
            if(codiceIUN != null){
                if(!codiceIUN.equals(coidiceIUNOld)){
                    this.datiNotifica.put("codiceIUN",codiceIUN);
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
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(dpDatiiNotifica + ".yaml");
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.aggionamentoPagina();
        piattaformaNotifichePAPage.waitLoadRefreshPage();
        piattaformaNotifichePAPage.inserimentoCodiceIUN(this.datiNotifica.get("codiceIUN").toString());
    }
}
