package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.DeleghePage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.section.destinatario.personaFisica.LeTueDelegheSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.PopUpRevocaDelegaSection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DeleghePagoPATest {

    private final WebDriver driver = Hooks.driver;
    Map<String, Object> deleghe = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger("DeleghePagoPATest");

    private final LeTueDelegheSection  leTueDelegheSection = new LeTueDelegheSection(this.driver);

    private final PopUpRevocaDelegaSection popUpRevocaDelegaSection = new PopUpRevocaDelegaSection(this.driver);

    private final DataPopulation dataPopulation = new DataPopulation();

    private final DeleghePage deleghePage = new DeleghePage(this.driver);

    @When("Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe")
    public void wait_deleghe_Button() {
        logger.info("Si clicca sul bottone deleghe");
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitESelectDelegheButton();
    }

    @And("Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe")
    public void visualizzaDelegheSection() {
        logger.info("Si visualizza la sezione deleghe");

        deleghePage.waitDeleghePage();
    }

    @And("Nella sezione Deleghe click sul bottone aggiungi nuova delega")
    public void nellaSezioneDelegheClickSulBottoneAggiungiNuovaDelega() {
        logger.info("Click sul bottone aggiungi nuova delega");

        deleghePage.clickAggiungiDelegaButton();
    }

    @And("Si visualizza la sezione Le Tue Deleghe")
    public void siVisualizzaLaSezioneLeTueDeleghe() {
        logger.info("Si visualizza la sezione Le Tue Deleghe");
        LeTueDelegheSection deleghePage = new LeTueDelegheSection(this.driver);
        deleghePage.waitNuovaDelegaSection();
    }

    @And("Nella sezione Le Tue Deleghe inserire i dati {string}")
    public void nellaSezioneLeTueDelegheInserireIDati(String dpFile) {
        logger.info("Nella sezione Le Tue Deleghe inserire i dati");

        this.leTueDelegheSection.selectpersonaFisicaRadioButton();


        deleghe = dataPopulation.readDataPopulation(dpFile+".yaml");

        this.leTueDelegheSection.insertNomeCognome(deleghe.get("nome").toString(),deleghe.get("cognome").toString());
        this.leTueDelegheSection.inserireCF(deleghe.get("codiceFiscale").toString());
        this.leTueDelegheSection.selectSoloEntiSelezionati();
        this.leTueDelegheSection.waitLoadPage();
        this.leTueDelegheSection.selezionaUnEnte(deleghe.get("ente").toString());

    }

    @And("Nella sezione Le Tue Deleghe verificare che la data sia corretta")
    public void nellaSezioneLeTueDelegheVerificareCheLaDataSiaCorretta() {
        logger.info("Si controlla che la data visualizzata sia corretta");


        if (this.leTueDelegheSection.verificareCheLaDataSiaCorretta()){
            logger.info("La data inserita è corretta");
        } else {
            logger.error("La data inserita non è corretta");
            Assert.fail("La data inserita non è corretta");
        }
    }

    @And("Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file {string}")
    public void nellaSezioneLeTueDelegheSalvareIlCodiceVerificaAllInternoDelFile(String dpFile) {
        logger.info("Si salva il codice deleghe nel file "+ dpFile);

        deleghe = dataPopulation.readDataPopulation(dpFile+".yaml");


        String codiceVerifica = this.leTueDelegheSection.salvataggioCodiceVerifica();
        deleghe.put("codiceDelega",codiceVerifica);
        dataPopulation.writeDataPopulation(dpFile+".yaml", deleghe);
    }

    @And("Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe")
    public void nellaSezioneLeTueDelegheClickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe() {

        this.leTueDelegheSection.clickSulBottoneInviaRichiesta();
    }

    @And("Nella sezione Deleghe si visualizza la delega in stato di attesa di conferma")
    public void nellaSezioneDelegheSiVisualizzaLaDelegaInStatoDiAttesaDiConferma() {

        deleghePage.waitDeleghePage();
        deleghePage.controlloCreazioneDelega();
    }

    @And("Nella sezione Deleghe si verifica sia presente una delega")
    public void nellaSezioneDelegheSiVerificaSiaPresenteUnaDelega() {
        logger.info("Si controlla che sia presente una delega");

        this.deleghePage.siVisualizzaUnaDelega();
    }

    @And("Nella sezione Deleghe si clicca sul menu della delega {string}")
    public void nellaSezioneDelegheSiCliccaSulMenuDellaDelega(String dpFile) {
        logger.info("Si clicca sul menu della delega");
        this.deleghe = this.dataPopulation.readDataPopulation(dpFile+".yaml");

        this.deleghePage.clickMenuDelega(this.deleghe.get("nome").toString(),this.deleghe.get("cognome").toString());
    }

    @And("Nella sezione Deleghe si sceglie l'opzione mostra codice")
    public void nellaSezioneDelegheSiSceglieLOpzioneMostraCodice() {
        logger.info("Si clicca su mostra codice");

        this.deleghePage.siSceglieLOpzioneMostraCodice();
    }

    @Then("Si clicca sul bottone chiudi")
    public void siCliccaSulBottoneChiudi() {
        logger.info("Si clicca sul bottone chiudi");

        this.deleghePage.siCliccaSulBottoneChiudi();
    }

    @And("Nella sezione Deleghe si sceglie l'opzione revoca")
    public void nellaSezioneDelegheSiSceglieLOpzioneRevoca() {
        logger.info("Si clicca sulla opzione revoca delega");

        this.deleghePage.clickOpzioneRevoca();
    }

    @Then("Si conferma l'azione scegliendo revoca la delega")
    public void siConfermaLAzioneScegliendoRevocaLaDelega() {
        popUpRevocaDelegaSection.clickRevocaLaDelega();
    }

    @And("Nella sezione Le Tue Deleghe click sul bottone Invia richiesta")
    public void nellaSezioneLeTueDelegheClickSulBottoneInviaRichiesta() {
        logger.info("Si clicca sul bottone  invia richiesta");
        this.leTueDelegheSection.clickInviaRichiesta();
    }

    @And("Nella sezione Le Tue Deleghe si visualizza il messaggio di errore")
    public void nellaSezioneLeTueDelegheSiVisualizzaIlMessaggioDiErrore() {
        logger.info("Si visualizza il messaggio di errore");
        String actualErrorMessage = this.leTueDelegheSection.MessaggioDiErrore().toLowerCase();
        String expectedErrorMessage = "errore inserimento delega";
        Assert.assertEquals("Messeggio di Errore non è uguale a Codice fiscale non valido",expectedErrorMessage,actualErrorMessage);
    }

    @And("Nella sezione Le Tue Deleghe si visualizza il messaggio di errore delega gia aggiunta")
    public void nellaSezioneLeTueDelegheSiVisualizzaIlMessaggioDiErroreDelegaGiaAggiunta() {
        logger.info("Si visualizza il messaggio di errore delega gia aggiunta");
        this.leTueDelegheSection.messaggioDiErroreDelegaPresente();
    }

    @And("Si verifica sia presente una delega nella sezione Deleghe a Tuo Carico")
    public void siVerificaSiaPresenteUnaDelegaNellaSezioneDelegheATuoCarico() {
        logger.info("Si controlla che ci sia almeno una delega");
        this.deleghePage.vaiInFondoAllaPagina();
        this.deleghePage.siVisualizzaUnaDelega();
    }

    @And("si sceglie opzione accetta")
    public void siSceglieOpzioneAccetta() {
        logger.info("Si sceglie l'opzione accetta");
        this.leTueDelegheSection.clickOpzioneAccetta();
    }

    @And("Si inserisce il codice delega nel pop-up {string}")
    public void siInserisceIlCodiceDelegaNelPopUp(String dpFile) {
        logger.info("Si inserisce il codice per accettare la delega");

        this.leTueDelegheSection.waitPopUpLoad();

        Map<String,Object> destinatari = dataPopulation.readDataPopulation(dpFile + ".yaml");
        this.leTueDelegheSection.inserireCodiceDelega(destinatari.get("codiceDelega").toString());
    }

    @And("Si clicca sul bottone Accetta")
    public void siCliccaSulBottoneAccetta() {
        logger.info("Nel pop-up si clicca sul bottone accetta");

        this.leTueDelegheSection.clickAccettaButton();
    }

    @And("Si controlla che la delega a lo stato Attiva {string}")
    public void siControllaCheLaDelegaALoStatoAttiva(String dpFile) {
        logger.info("Si controlla che la delega abbia lo stato Attiva");
        this.deleghe = this.dataPopulation.readDataPopulation(dpFile+".yaml");
        this.leTueDelegheSection.controlloStatoAttiva(deleghe.get("name").toString(),deleghe.get("familyName").toString());
    }

    @And("Nella sezione Deleghe si visualizza il titolo")
    public void siVisualizzaIlTitolo() {

        if(this.leTueDelegheSection.siVisualizzaIlTitolo()){
            logger.info("Il titolo della sezione Deleghe si visualizza correttamente");
        }else {
            logger.error("Il titolo della sezione Deleghe NON si visualizza correttamente");
            Assert.fail("Il titolo della sezione Deleghe NON si visualizza correttamente");
        }
    }

    @And("Nella sezione Deleghe si visualizza il sottotitolo")
    public void siVisualizzaIlSottotitolo() {
        if( this.leTueDelegheSection.siVisualizzaIlSottotitolo()){
            logger.info("Il sottotitolo della sezione Deleghe si visualizza correttamente");
        }else {
            logger.error("Il sottotitolo della sezione Deleghe NON si visualizza correttamente");
            Assert.fail("Il sottotitolo della sezione Deleghe NON si visualizza correttamente");
        }
    }

    @And("Nella sezione Deleghe si visualizza il bottone aggiungi una delega")
    public void siVisualizzaIlBottoneAggiungiUnaDelega() {

        if(this.leTueDelegheSection.siVisualizzaIlBottoneAggiungiUnaDelega()){
            logger.info("Il bottone aggiungi delega si visualizza correttamente");
        }else {
            logger.error("Il bottone aggiungi delega NON si visualizza correttamente");
            Assert.fail("Il bottone aggiungi delega NON si visualizza correttamente");
        }
    }

    @And("Nella sezione Deleghe si visualizzano tutti i campi dell'elenco dei delegati")
    public void siVisualizzanoTuttiICampiDellElencoDeiDelegati() {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(this.leTueDelegheSection.siVisualizzaIlNomeDelegato()){
            logger.info("Si visualizza correttamente il nome del delegato");
        }else {
            logger.error("NON si visualizza correttamente il nome del delegato");
            Assert.fail("NON si visualizza correttamente il nome del delegato");
        }

        if (this.leTueDelegheSection.siVisualizzaDataInizioDelega()){
            logger.info("Si visualizza correttamente la data di inizio della delega");
        }else {
            logger.error("NON si visualizza correttamente la data di inizio della delega");
            Assert.fail("NON Si visualizza correttamente la data di inizio della delega");
        }

        if(this.leTueDelegheSection.siVisualizzaDataFinoDelega()){
            logger.info("Si visualizza correttamente la data di fine delle deleghe");
        }else {
            logger.error("NON si visualizza correttamente la data di fine delle deleghe");
            Assert.fail("NON si visualizza correttamente la data di fine delle deleghe");
        }

        if (this.leTueDelegheSection.siVisualizzaPermessiDelega()){
            logger.info("Si visualizza correttamente il permesso della delega");
        }else {
            logger.error("NON si visualizza correttamente il permesso della delega");
            Assert.fail("NON si visualizza correttamente il permesso della delega");
        }
    }


    @And("Si controlla che non sia presente una delega con stesso nome {string}")
    public void siControllaCheNonSiaPresenteUnaDelegaConStessoNome(String dpFile) {
        logger.info("Si controlla che non ci sia una delega con lo stesso nome");




        Map<String,Object> datiDelega = dataPopulation.readDataPopulation(dpFile+".yaml");

        String nome =  datiDelega.get("nome").toString();
        String cognome =  datiDelega.get("cognome").toString();


        if ( deleghePage.CercaEsistenzaDelega(nome,cognome)){
            logger.info("Delega con lo stesso nome trovata");
            deleghePage.clickRevocaButton(nome,cognome);
            this.popUpRevocaDelegaSection.waitLoadPopUpRevocaDelegaSection();
            this.popUpRevocaDelegaSection.clickRevocaLaDelega();

        }else {
            logger.info("Delega con lo stesso nome NON trovata");
        }
    }

    @And("Nella pagina Deleghe si clicca sul menu della delega")
    public void nellaPaginaDelegheSiCliccaSulMenuDellaDelega() {
        logger.info("Si clicca sul menu delle delega");
        deleghePage.clickMenuPerRifiuto();
    }

    @And("Nella pagina Deleghe si sceglie opzione rifiuta")
    public void nellaPaginaDelegheSiSceglieOpzioneRifiuta() {
        logger.info("Si sceglie l'opzione rifiuta");
        deleghePage.clickRifiuta();
    }


    @And("Si clicca sul bottone rifiuta all'interno del pop-up")
    public void siCliccaSulBottoneRifiutaAllInternoDelPopUp() {
        logger.info("Si clicca sul bottone rifiuta nel pop-up");
        deleghePage.clickRiufitaPopUp();
    }

    @And("Si clicca sul bottone annulla all'interno del pop-up")
    public void siCliccaSulBottoneAnnullaAllInternoDelPopUp() {
        logger.info("Si clicca sul bottone annulla nel pop-up");
        deleghePage.clickAnnullaPopUp();
    }

    @And("Si controlla che la delega non sia più presente nella lista")
    public void siControllaCheLaDelegaNonSiaPiuPresenteNellaLista() {
        logger.info("Si controlla che la delega non sia più presente nella lista");
        if (deleghePage.verificaEsistenzaDelega()){
            logger.info("Si controlla che la delega non sia più presente nella lista");
        } else {
            logger.error("La delega è ancora presente in lista");
            Assert.fail("La delega è ancora presente in lista");
        }
    }

    @And("Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaSiVedeLaSezioneDeleghe() {
        logger.info("Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe");
        deleghePage.waitLoadDelegheSection();
    }
}
