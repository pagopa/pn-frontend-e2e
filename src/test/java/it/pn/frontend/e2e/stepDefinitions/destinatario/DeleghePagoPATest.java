package it.pn.frontend.e2e.stepDefinitions.destinatario;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.DeleghePage;
import it.pn.frontend.e2e.pages.destinatario.NotificheDEPage;
import it.pn.frontend.e2e.section.destinatario.LeTueDelegheSection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DeleghePagoPATest {

    private final WebDriver driver = Hooks.driver;
    Map<String, Object> deleghe = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger("DeleghePagoPATest");

    @When("Nella pagina Piattaforma Notifiche Destinatario click sul bottone Deleghe")
    public void wait_deleghe_Button() {
        logger.info("Si clicca sul bottone deleghe");
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.waitESelectDelegheButton();
    }

    @And("Nella pagina Piattaforma Notifiche Destinatario si vede la sezione Deleghe")
    public void visualizzaDelegheSection() {
        logger.info("Si visualizza la sezione deleghe");
        DeleghePage deleghePage = new DeleghePage(this.driver);
        deleghePage.waitDeleghePage();
    }

    @And("Nella sezione Deleghe click sul bottone aggiungi nuova delega")
    public void nellaSezioneDelegheClickSulBottoneAggiungiNuovaDelega() {
        logger.info("Click sul bottone aggiungi nuova delega");
        DeleghePage deleghePage = new DeleghePage(this.driver);
        deleghePage.clickAggiungiDelegaButton();
    }

    @And("Si visualizza la sezione Le Tue Deleghe")
    public void siVisualizzaLaSezioneLeTueDeleghe() {
        logger.info("Si visualizza la sezione Le Tue Deleghe");
        DeleghePage deleghePage = new DeleghePage(this.driver);
        deleghePage.waitNuovaDelegaSection();
    }

    @And("Nella sezione Le Tue Deleghe inserire i dati {string}")
    public void nellaSezioneLeTueDelegheInserireIDati(String dpFile) {
        logger.info("Nella sezione Le Tue Deleghe inserire i dati");
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.selectpersonaFisicaRadioButton();

        DataPopulation dataPopulation = new DataPopulation();
        deleghe = dataPopulation.readDataPopulation(dpFile+".yaml");

        leTueDelegheSection.insertNomeCognome(deleghe.get("nome").toString(),deleghe.get("cognome").toString());
        leTueDelegheSection.inserireCF(deleghe.get("codiceFiscale").toString());
        leTueDelegheSection.selectSoloEntiSelezionati();
        leTueDelegheSection.waitLoadPage();
        leTueDelegheSection.selezionaUnEnte(deleghe.get("ente").toString());

    }

    @And("Nella sezione Le Tue Deleghe verificare che la data sia corretta")
    public void nellaSezioneLeTueDelegheVerificareCheLaDataSiaCorretta() {
        logger.info("Si controlla che la data visualizzata sia corretta");

        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        if (leTueDelegheSection.verificareCheLaDataSiaCorretta()){
            logger.info("La data inserita è corretta");
        } else {
            logger.error("La data inserita non è corretta");
            Assert.fail("La data inserita non è corretta");
        }
    }

    @And("Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file {string}")
    public void nellaSezioneLeTueDelegheSalvareIlCodiceVerificaAllInternoDelFile(String dpFile) {
        logger.info("Si salva il codice deleghe nel file "+ dpFile);
        DataPopulation dataPopulation = new DataPopulation();
        deleghe = dataPopulation.readDataPopulation(dpFile+".yaml");

        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        String codiceVerifica = leTueDelegheSection.salvataggioCodiceVerifica();
        deleghe.put("codiceDelega",codiceVerifica);
        dataPopulation.writeDataPopulation(dpFile+".yaml", deleghe);
    }

    @And("Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe")
    public void nellaSezioneLeTueDelegheClickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe() {
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.clickSulBottoneInviaRichiesta();
    }

    @And("Nella sezione Deleghe si visualizza la delega in stato di attesa di conferma")
    public void nellaSezioneDelegheSiVisualizzaLaDelegaInStatoDiAttesaDiConferma() {
        DeleghePage deleghePage = new DeleghePage(this.driver);
        deleghePage.waitDeleghePage();
        deleghePage.controlloCreazioneDelega();
    }

    @And("Nella sezione Deleghe si verifica sia presente una delega")
    public void nellaSezioneDelegheSiVerificaSiaPresenteUnaDelega() {
        logger.info("Si controlla che sia presente una delega");
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.siVisualizzaUnaDelega();
    }

    @And("Nella sezione Deleghe si clicca sul menu della delega")
    public void nellaSezioneDelegheSiCliccaSulMenuDellaDelega() {
        logger.info("Si clicca sul menu della delega");
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.clickMenuDelega();
    }

    @And("Nella sezione Deleghe si sceglie l'opzione mostra codice")
    public void nellaSezioneDelegheSiSceglieLOpzioneMostraCodice() {
        logger.info("Si clicca su mostra codice");
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.siSceglieLOpzioneMostraCodice();
    }

    @Then("Si clicca sul bottone chiudi")
    public void siCliccaSulBottoneChiudi() {
        logger.info("Si clicca sul bottone chiudi");
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.siCliccaSulBottoneChiudi();
    }

    @And("Nella sezione Deleghe si sceglie l'opzione revoca")
    public void nellaSezioneDelegheSiSceglieLOpzioneRevoca() {
        logger.info("Si clicca sulla opzione revoca delega");
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.clickOpzioneRevoca();
    }

    @Then("Si conferma l'azione scegliendo revoca la delega")
    public void siConfermaLAzioneScegliendoRevocaLaDelega() {
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.clickRevocaLaDelega();
    }

    @And("Nella sezione Le Tue Deleghe click sul bottone Invia richiesta")
    public void nellaSezioneLeTueDelegheClickSulBottoneInviaRichiesta() {
        logger.info("Si clicca sul bottone  invia richiesta");
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.clickInviaRichiesta();
    }

    @And("Nella sezione Le Tue Deleghe si visualizza il messaggio di errore delega a se stessi")
    public void nellaSezioneLeTueDelegheSiVisualizzaIlMessaggioDiErroreDelegaASeStessi() {
        logger.info("Si visualizza il messaggio di errore");
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.MessaggioDiErroreDelegaASeStessi();
    }

    @And("Nella sezione Le Tue Deleghe si visualizza il messaggio di errore delega gia aggiunta")
    public void nellaSezioneLeTueDelegheSiVisualizzaIlMessaggioDiErroreDelegaGiaAggiunta() {
        logger.info("Si visualizza il messaggio di errore delega gia aggiunta");

        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.messaggioDiErroreDelegaPresente();
    }

    @And("Si verifica sia presente una delega nella sezione Deleghe a Tuo Carico")
    public void siVerificaSiaPresenteUnaDelegaNellaSezioneDelegheATuoCarico() {
        logger.info("Si controlla che ci sia almeno una delega");
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.vaiInFondoAllaPagina();
        leTueDelegheSection.siVisualizzaUnaDelega();
    }

    @And("si sceglie opzione accetta")
    public void siSceglieOpzioneAccetta() {
        logger.info("Si sceglie l'opzione accetta");
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.clickOpzioneAccetta();
    }

    @And("Si inserisce il codice delega nel pop-up {string}")
    public void siInserisceIlCodiceDelegaNelPopUp(String dpFile) {
        logger.info("Si inserisce il codice per accettare la delega");

        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.waitPopUpLoad();

        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> destinatari = dataPopulation.readDataPopulation(dpFile + ".yaml");
        leTueDelegheSection.inserireCodiceDelega(destinatari.get("codiceDelega").toString());
    }

    @And("Si clicca sul bottone Accetta")
    public void siCliccaSulBottoneAccetta() {
        logger.info("Nel pop-up si clicca sul bottone accetta");

        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.clickAccettaButton();
    }

    @And("Si controlla che la delega a lo stato Attiva")
    public void siControllaCheLaDelegaALoStatoAttiva() {
        logger.info("Si controlla che la delega abbia lo stato Attiva");

        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        leTueDelegheSection.controlloStatoAttiva();
    }

    @And("Nella sezione Deleghe si visualizza il titolo")
    public void siVisualizzaIlTitolo() {
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        if(leTueDelegheSection.siVisualizzaIlTitolo()){
            logger.info("Il titolo della sezione Deleghe si visualizza correttamente");
        }else {
            logger.error("Il titolo della sezione Deleghe NON si visualizza correttamente");
            Assert.fail("Il titolo della sezione Deleghe NON si visualizza correttamente");
        }
    }

    @And("Nella sezione Deleghe si visualizza il sottotitolo")
    public void siVisualizzaIlSottotitolo() {
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        if( leTueDelegheSection.siVisualizzaIlSottotitolo()){
            logger.info("Il sottotitolo della sezione Deleghe si visualizza correttamente");
        }else {
            logger.error("Il sottotitolo della sezione Deleghe NON si visualizza correttamente");
            Assert.fail("Il sottotitolo della sezione Deleghe NON si visualizza correttamente");
        }
    }

    @And("Nella sezione Deleghe si visualizza il bottone aggiungi una delega")
    public void siVisualizzaIlBottoneAggiungiUnaDelega() {
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
        if(leTueDelegheSection.siVisualizzaIlBottoneAggiungiUnaDelega()){
            logger.info("Il bottone aggiungi delega si visualizza correttamente");
        }else {
            logger.error("Il bottone aggiungi delega NON si visualizza correttamente");
            Assert.fail("Il bottone aggiungi delega NON si visualizza correttamente");
        }
    }

    @And("Nella sezione Deleghe si visualizzano tutti i campi dell'elenco dei delegati")
    public void siVisualizzanoTuttiICampiDellElencoDeiDelegati() {
        LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);

        if(leTueDelegheSection.siVisualizzaIlNomeDelegato()){
            logger.info("Si visualizza correttamente il nome del delegato");
        }else {
            logger.error("NON si visualizza correttamente il nome del delegato");
            Assert.fail("NON si visualizza correttamente il nome del delegato");
        }

        if (leTueDelegheSection.siVisualizzaDataInizioDelega()){
            logger.info("Si visualizza correttamente la data di inizio della delega");
        }else {
            logger.error("NON si visualizza correttamente la data di inizio della delega");
            Assert.fail("NON Si visualizza correttamente la data di inizio della delega");
        }

        if(leTueDelegheSection.siVisualizzaDataFinoDelega()){
            logger.info("Si visualizza correttamente la data di fine delle deleghe");
        }else {
            logger.error("NON si visualizza correttamente la data di fine delle deleghe");
            Assert.fail("NON si visualizza correttamente la data di fine delle deleghe");
        }

        if (leTueDelegheSection.siVisualizzaPermessiDelega()){
            logger.info("Si visualizza correttamente il permesso della delega");
        }else {
            logger.error("NON si visualizza correttamente il permesso della delega");
            Assert.fail("NON si visualizza correttamente il permesso della delega");
        }
    }



}
