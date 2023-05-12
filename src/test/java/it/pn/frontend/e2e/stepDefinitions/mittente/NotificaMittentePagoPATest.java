package it.pn.frontend.e2e.stepDefinitions.mittente;

import com.beust.jcommander.Parameterized;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.mittente.AreaRiservataPAPage;
import it.pn.frontend.e2e.pages.mittente.InvioNotifichePAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePAPage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.mittente.*;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.substring;

public class NotificaMittentePagoPATest {

    private static final Logger logger = LoggerFactory.getLogger("NotificaMittenteTest");

    private final WebDriver driver = Hooks.driver;
    private Map<String, Object> datiNotifica = new HashMap<>();
    private Map<String, Object> destinatario = new HashMap<>();

    @When("Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche DEV")
    public void nellaHomePageMittenteCliccareSuGestisciDiPiattaformaDEV() {
        logger.info("Cliccare sul bottone Gestisci di Piattaforma Notifiche DEV");

        AreaRiservataPAPage areaRiservataPAPage = new AreaRiservataPAPage(this.driver);
        areaRiservataPAPage.selezionaPiattaformaNotificaDev();
    }

    @When("Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche SVIL")
    public void nellaHomePageMittenteCliccareSuGestisciDiPiattaformaSVIL() {
        logger.info("Cliccare sul bottone Gestisci di Piattaforma Notifiche SVIL");

        AreaRiservataPAPage areaRiservataPAPage = new AreaRiservataPAPage(this.driver);
        areaRiservataPAPage.selezionaPiattaformaNotificaSvil();
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotifiche() {
        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

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

        InformazioniPreliminariPASection informazioniPreliminariPASection = new InformazioniPreliminariPASection(this.driver);
        informazioniPreliminariPASection.insertNumeroDiProtocollo(this.datiNotifica.get("numeroProtocollo").toString());
        informazioniPreliminariPASection.insertOggettoNotifica(this.datiNotifica.get("oggettoDellaNotifica").toString());
        informazioniPreliminariPASection.insertDescrizione(this.datiNotifica.get("descrizione").toString());
        informazioniPreliminariPASection.insertGruppo(this.datiNotifica.get("gruppo").toString());
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
        logger.info("Inserimento del nome cognome e codice fiscale dal file destinatario.yaml");

        DataPopulation dataPopulation = new DataPopulation();
        this.destinatario = dataPopulation.readDataPopulation(destinatarioFile + ".yaml");

        DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
        destinatarioPASection.selezionarePersonaFisica();
        destinatarioPASection.inserireNomeDestinatario(this.destinatario.get("name").toString());
        destinatarioPASection.inserireCognomeDestinatario(this.destinatario.get("familyName").toString());
        destinatarioPASection.inserireCodiceFiscaleDestinatario(this.destinatario.get("codiceFiscale").toString());
    }

    @And("Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati del destinatario {string}")
    public void nellaSectionDestinatarioCliccareSuAggiungiIndirizzoFisicoCompilareIDatiDelDestinatario(String destinatarioFile) {
        logger.info("Inserimento dei dati mancanti nella section destinatario");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        DataPopulation dataPopulation = new DataPopulation();
        this.destinatario = dataPopulation.readDataPopulation(destinatarioFile + ".yaml");

        DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
        destinatarioPASection.selezionaAggiungiUnIndirizzoFisico();
        destinatarioPASection.inserireIndirizzo(this.destinatario.get("indirizzo").toString());
        destinatarioPASection.inserireNumeroCivico(this.destinatario.get("numeroCivico").toString());
        destinatarioPASection.inserireLocalita(this.destinatario.get("localita").toString());
        destinatarioPASection.inserireComune(this.destinatario.get("comune").toString());
        destinatarioPASection.inserireProvincia(this.destinatario.get("provincia").toString());
        destinatarioPASection.inserireCodicePostale(this.destinatario.get("codicepostale").toString());
        destinatarioPASection.inserireStato(this.destinatario.get("stato").toString());
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
    public void siVisualizzaCorrettamenteLaFraseLaNotificaÈStataCorrettamenteCreata() {
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
        this.destinatario = dataPopulation.readDataPopulation("destinatario.yaml");

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String dataNotifica = dateFormat.format(date).replace("-", "/");

        String coidiceIUNOld = this.datiNotifica.get("codiceIUN").toString();

        for(int i=0; i<12; i++){
            if(i>=1){

                piattaformaNotifichePAPage.aggionamentoPagina();
                piattaformaNotifichePAPage.insertCodiceFiscale(this.destinatario.get("codiceFiscale").toString());
                piattaformaNotifichePAPage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
                piattaformaNotifichePAPage.selezionareStatoNotifica("Depositata");
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
}
