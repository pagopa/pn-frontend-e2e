package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.mittente.AreaRiservataPAPage;
import it.pn.frontend.e2e.pages.mittente.InvioNotifichePAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
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

import static org.apache.commons.lang3.StringUtils.*;

public class NotificaMittentePagoPATest {

    private static final Logger logger = LoggerFactory.getLogger("NotificaMittentePagoPATest");

    private final WebDriver driver = Hooks.driver;
    private Map<String, Object> datiNotifica = new HashMap<>();
    private Map<String, Object> personaFisica = new HashMap<>();
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
    private Map<String, Object> personaGiuridica = new HashMap<>();
    private Map<String, Object> personeFisiche = new HashMap<>();

    private final  PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);

    private final  DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);

    private final DataPopulation dataPopulation = new DataPopulation();

    @When("Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche")
    public void nellaHomePageMittenteCliccareSuGestisciDiPiattaforma() {
        logger.info("Cliccare sul bottone Gestisci di Piattaforma Notifiche");
        AreaRiservataPAPage areaRiservataPAPage = new AreaRiservataPAPage(this.driver);
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> areaRiservataPAPage.selezionaPiattaformaNotificaDev();
            case "test" -> areaRiservataPAPage.selezionaPiattaformaNotificaTest();
            case "uat" -> areaRiservataPAPage.selezionaPiattaformaNotificaUat();
        }
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotifiche() {
        boolean httpRequestToken = false;
        for (int index = 0; index < 30; index++) {

            if (this.readHttpRequest()) {
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
        if (cookiesSection.waitLoadCookiesPage()) {
            cookiesSection.selezionaAccettaTuttiButton();
        }

        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
    }

    @And("Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica")
    public void nellaPaginaPiattaformaNotificheCliccareSulBottoneInviaUnaNuovaNotifica() {
        logger.info("selezione bottone invia una nuova notifica");

        piattaformaNotifichePage.selectInviaUnaNuovaNotificaButton();
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

        this.datiNotifica = dataPopulation.readDataPopulation(datiNotificaFile + ".yaml");

        String variabileAmbiente = System.getProperty("environment");
        String gruppo = "";
        switch (variabileAmbiente) {
            case "dev" -> gruppo = datiNotifica.get("gruppoDev").toString();
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
        CookiesSection cookiesSection = new CookiesSection(this.driver);
        for (int i = 0; i < 12; i++) {
            if (i >= 1) {
                piattaformaNotifichePage.aggionamentoPagina();
                if (cookiesSection.waitLoadCookiesPage()) {
                    cookiesSection.selezionaAccettaTuttiButton();
                }
                piattaformaNotifichePage.insertCodiceFiscale(this.personeFisiche.get("codiceFiscale").toString());
                piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
                piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePage.selectFiltraButton();
            }
            piattaformaNotifichePage.waitLoadRefreshPage();
            String codiceIUN = piattaformaNotifichePage.ricercaNotifica(this.datiNotifica.get("oggettoDellaNotifica").toString(), statoNotifica);
            if (codiceIUN != null) {
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
        CookiesSection cookiesSection = new CookiesSection(this.driver);
        cookiesSection.waitLoadCookiesPage();
        cookiesSection.selezionaAccettaTuttiButton();
    }

    @And("Nella pagina dettaglio notifica cliccare sull'opzione vedi più dettagli")
    public void nellaPaginaDettaglioNotificaCliccareSullOpzioneVediPiuDettagli() {
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        dettaglioNotificaMittenteSection.clickVediPiuDettaglio();
    }

    @And("Si visualizza correttamente l elenco completo degli stati che la notifica ha percorso")
    public void siVisualizzaCorrettamenteLElencoCompletoDegliStatiCheLaNotificaHaPercorso() {
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        int nStatiNotifica = dettaglioNotificaMittenteSection.siVisualizzaPercosoNotifica();
        if (nStatiNotifica >= 1) {
            logger.info("L'elenco degli stati presente");
        } else {
            logger.error("L'elenco degli stati NON presente");
            Assert.fail("L'elenco degli stati NON presente");
        }
    }

    @Then("Si clicca sul bottone indietro")
    public void siCliccaSulBottoneIndietro() {
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        dettaglioNotificaMittenteSection.clickIndietroButton();
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
            String urlToFind = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/token-exchange";
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
        List<WebElement> listeOggetti = piattaformaNotifichePage.ricercaListaOggetti();
        if (listeOggetti != null && listeOggetti.size() >=1 ){
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
            logger.info("Il numero di notifiche e corretto");
        } else {
            logger.error("Il numero di notifiche non e coretto");
            Assert.fail("Il numero di notifiche non e coretto");
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
        logger.info("Si sta cercando di selezionare il buttone aiggiungere Destinatario");

        destinatarioPASection.selezionareAggiungiDestinatarioButton();
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

    @And("Si verifica che la notifica sia nello stato {string}")
    public void siVerificaCheLaNotificaSiaNelloStato(String statoNotifca) {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage((this.driver));

        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        boolean notificaTrovata = false;
        for (int i = 0; i < 10; i++) {
            int numeroNotifiche = piattaformaNotifichePage.getListStato(statoNotifca);
            if (numeroNotifiche == 0) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                piattaformaNotifichePage.aggionamentoPagina();
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

    @And("Si verifica sia presente l'indirizzo mail del destinatario {string} nei dettagli della notifica")
    public void siVerificaSiaPresenteLIndirizzoMailDelDestinatarioNeiDettagliDellaNotifica(String mailPEC) {
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        dettaglioNotificaMittenteSection.clickVediPiuDettaglio();
        int numeroPecTrovate = dettaglioNotificaMittenteSection.controlloNumeroPec(mailPEC);
        if (numeroPecTrovate != 0) {
            logger.info(" La pec è in invio in corso");
        } else {
            logger.error("La pec NON è in invio in corso");
            Assert.fail("La pec NON è in invio in corso");
        }
    }

    @And("Verifica dello stato della notifica inviata tramite pec come {string}")
    public void verificaDelloStatoDellaNotificaInviataTramitePecCome(String statoNotifica) {


        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        this.personeFisiche = dataPopulation.readDataPopulation("personaFisicaPec.yaml");

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String dataNotifica = dateFormat.format(date).replace("-", "/");

        String coidiceIUNOld = this.datiNotifica.get("codiceIUN").toString();
        CookiesSection cookiesSection = new CookiesSection(this.driver);
        for (int i = 0; i < 12; i++) {
            if (i >= 1) {
                piattaformaNotifichePage.aggionamentoPagina();
                if (cookiesSection.waitLoadCookiesPage()) {
                    cookiesSection.selezionaAccettaTuttiButton();
                }
                piattaformaNotifichePage.insertCodiceFiscale(this.personeFisiche.get("codiceFiscale").toString());
                piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
                piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePage.selectFiltraButton();
            }
            piattaformaNotifichePage.waitLoadRefreshPage();
            String codiceIUN = piattaformaNotifichePage.ricercaNotifica(this.datiNotifica.get("oggettoDellaNotifica").toString(), statoNotifica);
            if (codiceIUN != null) {
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
        if (piattaformaNotifichePage.errorMessage()){
            logger.info("Si visualizza correttamente il messaggio di errore");
        }else {
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
        logger.info("Si verifica sia presente il pop up vuoi uscire");
        if(piattaformaNotifichePage.vuoiUscirePopUp()){
            logger.info("Si visualizza correttamente il pop up vuoi uscire");
        }else {
            logger.error("Non si visualizza correttamente il pop up vuoi uscire");
            Assert.fail("Non si visualizza correttamente il pop up vuoi uscire");
        }
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
        destinatarioPASection.insertpartitaIva(this.personaGiuridica.get("codiceFiscale").toString());
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

        this.personaGiuridica = dataPopulation.readDataPopulation(dpFile +".yaml");


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
    }

    @And("Nella section Allegati caricare l'atto e inserire il nome atto con estenzione non valida")
    public void nellaSectionAllegatiCaricareLAttoEInserireIlNomeAttoConEstenzioneNonValida() {
        logger.info("Si inserisce un file con estensione sbagliata");
        AllegatiPASection allegatiPASection = new AllegatiPASection(this.driver);
        String pathDocumentiFile = System.getProperty("user.dir")+"/src/test/resources/dataPopulation/fileUpload/semiOfficial1.jpg";
        allegatiPASection.caricareNotificaPdfDalComputer(pathDocumentiFile);
    }

    @Then("Si visualizza correttamente il messaggio di errore estensione file non supportata. Riprovare con un altro file.")
    public void siVisualizzaCorrettamenteIlMessaggioDiErroreEstensioneFileNonSupportataRiprovareConUnAltroFile() {
       logger.info("Si controlla che si vede il messaggio di errore");
        if(piattaformaNotifichePage.estensioneSbagliataErrore()){
            logger.info("Si visualizza correttamente il messaggio di errore");
        }else {
            logger.error("Non si visualizza correttamente il messaggio di errore");
            Assert.fail("Non si visualizza correttamente il messaggio di errore");
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
        String codiceFiscale =this.personaFisica.get("codiceFiscale").toString();

        String coidiceIUNOld = this.datiNotifica.get("codiceIUN").toString();
        CookiesSection cookiesSection = new CookiesSection(this.driver);
        for (int i = 0; i < 12; i++) {
            if (i >= 1) {
                piattaformaNotifichePage.aggionamentoPagina();
                if (cookiesSection.waitLoadCookiesPage()) {
                    cookiesSection.selezionaAccettaTuttiButton();
                }
                piattaformaNotifichePage.insertCodiceFiscale(codiceFiscale);
                piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
                piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
                piattaformaNotifichePage.selectFiltraButton();
            }
            piattaformaNotifichePage.waitLoadRefreshPage();
            String codiceIUN = piattaformaNotifichePage.ricercaNotifica(this.datiNotifica.get("oggettoDellaNotifica").toString(), statoNotifica);
            if (codiceIUN != null) {
                if (!codiceIUN.equals(coidiceIUNOld)) {
                    this.datiNotifica.put("codiceIUN", codiceIUN);
                    dataPopulation.writeDataPopulation("datiNotifica.yaml", this.datiNotifica);
                    logger.info("Stato notifica uguale a Depositata e codice IUN aggiornato correttamente");
                    return;
                }
            }
        }
    }
}
