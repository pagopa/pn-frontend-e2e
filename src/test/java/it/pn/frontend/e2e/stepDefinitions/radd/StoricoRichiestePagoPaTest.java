package it.pn.frontend.e2e.stepDefinitions.radd;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.radd.HomePage;
import it.pn.frontend.e2e.pages.radd.RichiestaAttiPage;
import it.pn.frontend.e2e.pages.radd.StoricoRichiestePage;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class StoricoRichiestePagoPaTest {
    private final WebDriver driver = Hooks.driver;

    private final Logger logger = LoggerFactory.getLogger("StoricoRichiestePagoPaTest");

    private Map<String, Object> datiRADD = new HashMap<>();

    @When("nella Homepage RADD sezione Storico delle ricerche clicca sul bottone con freccia")
    public void clickStoricoRichiesteButton() {
        logger.info("Si clicca sulla card Storico delle Richieste");
        HomePage homePage = new HomePage(this.driver);
        homePage.clickSuStoricoRichieste();
    }

    @Then("la pagina Storico delle richieste è visualizzata correttamente")
    public void laPaginaStoricoDelleRichiesteEVisualizzataCorrettamente() {
        logger.info("Si visualizza la pagina Storico delle Richieste");

        StoricoRichiestePage storicoRichiestePagoPa = new StoricoRichiestePage(this.driver);
        storicoRichiestePagoPa.waitLoadStoricoRichieste();
    }

    @When("Nella pagina Storico delle ricerche il radio button Documenti allegati e attestazioni opponibili a terzi è selezionato")
    public void nellaPaginaStoricoDelleRicercheIlRadioButtonDocumentiAllegatiEAttestazioniOpponibiliATerziESelezionato() {
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        if (storicoRichiestePage.selectDocumentiAllegatiAttestazioRadioButton()) {
            logger.info("Il radio button Documenti allegati e attestazioni opponibili a terzi è selezioanto");
        } else {
            logger.error("Il radio button Documenti allegati e attestazioni opponibili a terzi NON è stato selezioanto");
            Assert.fail("Il radio button Documenti allegati e attestazioni opponibili a terzi NON è stato selezioanto");
        }
    }

    @And("Nella pagina Storico delle ricerche selezionare il radio button Id Operazione")
    public void nellaPaginaStoricoDelleRicercheSelezionareIlRadioButtonIdOperazione() {
        logger.info("Si clicca sul bottone Id Operazione");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.clickIdOperazioneRadioButton();
    }

    @And("Nella pagina Storico delle ricerche inserire nel campo di testo l' Id operazione {string} da ricercare")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiTestoLIdOperazioneDaRicercare(String datiRADDFile) {
        logger.info("Si inserisce l'id Operazione");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(datiRADDFile + ".yaml");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.insertIdOperazione(this.datiRADD.get("idOperazioneDAA").toString());
    }

    @And("Nella pagina Storico delle ricerche cliccare sul bottone Cerca")
    public void nellaPaginaStoricoDelleRicercheCliccareSulBottoneCerca() {
        logger.info("Si clicca sul bottone cerca");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.clickCercaButton();
    }

    @And("Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi viene restituita la richiesta con l'id operazione ricercato")
    public void nellaPaginaRisultatoRicercaDocumentiAllegatiEAttestazioniOpponibileATerziVieneRestituitaLaRichiestaConLIdOperazioneRicercato() {
        logger.info("Si visualizza la richiesta con l'id operazione ricercato");

        DataPopulation dataPopulation = new DataPopulation();

        this.datiRADD = dataPopulation.readDataPopulation("RADD.yaml");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        int nRighe = storicoRichiestePage.getNRighe(this.datiRADD.get("idOperazioneDAA").toString());
        if (nRighe != 0) {
            logger.info("La richiesta viene visualizzata correttamente");
        } else {
            logger.error("La richiesta  NON viene visualizzata correttamente");
            Assert.fail("La richiesta NON viene visualizzata correttamente");
        }
    }

    @And("Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi cliccare sulla richiesta restituita")
    public void nellaPaginaRisultatoRicercaDocumentiAllegatiEAttestazioniOpponibileATerziCliccareSullaRichiestaRestituita() {
        logger.info("Si clicca sulla richiesta restituita");
        DataPopulation dataPopulation = new DataPopulation();

        this.datiRADD = dataPopulation.readDataPopulation("RADD.yaml");


        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.clickRichiestaRestituita(this.datiRADD.get("codiceIUNDAA").toString());
    }

    @And("La modale Dettaglio della richiesta ID richiesta viene visualizzata correttamente")
    public void laModaleDettaglioDellaRichiestaIDRichiestaVieneVisualizzataCorrettamente() {
        logger.info("Si visualizza il pop-up Dettaglio della Richiesta");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.waitLoadDettaglioRichiesta();
    }

    @And("Nella modale cliccare sul bottone Chiudi")
    public void nellaModaleCliccareSulBottoneChiudi() {
        logger.info("Si clicca sul bottone chiudi");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.clickChiudiButton();
    }

    @And("Nella pagina Storico delle ricerche il radio button Codice IUN è selezionato")
    public void nellaPaginaStoricoDelleRicercheIlRadioButtonCodiceIUNESelezionato() {
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        if (storicoRichiestePage.selectIUNRadioButton()) {
            logger.info("Il radio button codice IUN è selezionato");
        } else {
            logger.error("Il radio button codice IUN NON è stato selezionato");
            Assert.fail("Il radio button codice IUN NON è stato selezionato");
        }
    }

    @And("Nella pagina Storico delle ricerche inserire nel campo di testo il codice IUN da ricercare da {string}")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiTestoIlCodiceIUNDaRicercareDa(String dpFile) {
        logger.info("Il codice IUN da ricercare è stato inserito correttamente");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(dpFile + ".yaml");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.insertCodiceIUN(this.datiRADD.get("codiceIUNDAA").toString());
    }

    @And("Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi viene restituita la richiesta con il codice IUN ricercato")
    public void nellaPaginaRisultatoRicercaDocumentiAllegatiEAttestazioniOpponibileATerziVieneRestituitaLaRichiestaConIlCodiceIUNRicercato() {
        logger.info("Si controlla che si visualizzino le richieste con il codice IUN");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation("RADD.yaml");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        int nRigheRichiesta = storicoRichiestePage.getNRighe(datiRADD.get("codiceIUNDAA").toString());
        if (nRigheRichiesta != 0) {
            logger.info("Il numero di richieste è diverso da zero");
        } else {
            logger.error("Non ci sono richieste per il codice IUN inserito");
            Assert.fail("Non ci sono richieste per il codice IUN inserito");
        }
    }

    @And("Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi cliccare sul bottone Nuova ricerca")
    public void nellaPaginaRisultatoRicercaDocumentiAllegatiEAttestazioniOpponibileATerziCliccareSulBottoneNuovaRicerca() {
        logger.info("Si clicca sul bottone nuova ricerca");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.clickNuovaRicercaButton();
    }

    @And("Nella pagina Storico delle ricerche selezionare il radio button Codice fiscale destinatario")
    public void nellaPaginaStoricoDelleRicercheSelezionareIlRadioButtonCodiceFiscaleDestinatario() {
        logger.info("Si clicca sul codice fiscale radio button");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.clickCodiceFiscaleRadioButton();
    }

    @And("Nella pagina Storico delle ricerche inserire nel campo di input il codice fiscale del destinatario {string}")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiInputIlCodiceFiscaleDelDestinatario(String dpFile) {
        logger.info("Si controlla che si visualizzino le richieste con il codice IUN");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(dpFile + ".yaml");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.insertCodiceFiscale(this.datiRADD.get("codiceFiscaleDAA").toString());
    }

    @And("Nella pagina Storico delle ricerche inserire nei campi di input una data")
    public void nellaPaginaStoricoDelleRicercheInserireNeiCampiDiInputUnaData() {
        logger.info("Si inseriscono nei campi di input delle date");
        LocalDate dataFine = LocalDate.now();
        LocalDate dataInizio = dataFine.minusDays(4);

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        String dataA = storicoRichiestePage.conversioneData(dataFine.toString());
        String dataDA = storicoRichiestePage.conversioneData(dataInizio.toString());

        storicoRichiestePage.insertDataInizio(dataDA);
        storicoRichiestePage.insertDataFine(dataA);
    }


    @And("Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi vengono restituite le richieste con il codice fiscale del destinatario  ricercato")
    public void nellaPaginaRisultatoRicercaDocumentiAllegatiEAttestazioniOpponibileATerziVengonoRestituiteLeRichiesteConIlCodiceFiscaleDelDestinatarioRicercato() {
        logger.info("Si controlla che vengano restituiti dei risultati");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation("RADD.yaml");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        int numeroRighe = storicoRichiestePage.getNRighe(this.datiRADD.get("codiceFiscaleDAA").toString());

        if (numeroRighe >= 1) {
            logger.info("I risultati sono coerenti con il codice fiscale inserito");
        } else {
            logger.error("I risultati NON sono coerenti con il codice fiscale inserito");
            Assert.fail("I risultati NON sono coerenti con il codice fiscale inserito");
        }
    }

    @And("Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi cliccare su una richiesta")
    public void nellaPaginaRisultatoRicercaDocumentiAllegatiEAttestazioniOpponibileATerziCliccareSuUnaRichiesta() {
        logger.info("Si clicca sulla richiesta restituita");
        DataPopulation dataPopulation = new DataPopulation();

        this.datiRADD = dataPopulation.readDataPopulation("RADD.yaml");


        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.clickRichiestaRestituita(this.datiRADD.get("codiceFiscaleDAA").toString());
    }

    @Then("Nella pagina Storico delle ricerche viene visualizzato un pop up con l'etichetta KO per id operazione")
    public void nellaPaginaStoricoDelleRicercheVieneVisualizzatoUnPopUpConLEtichettaKO() {
        logger.info("Si verifica visualizzazione messaggio di errore");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.waitErrorMessageIdOperazione("Transazione inesistente");
    }

    @And("Nella pagina Storico delle ricerche inserire nel campo di testo Id operazione errato {string} da ricercare")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiTestoIdOperazioneErratoDaRicercare(String datiRADDFile) {
        logger.info("Si inserisce l'id Operazione errato");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(datiRADDFile + ".yaml");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.insertIdOperazione(this.datiRADD.get("idOperazioneErroreDAA").toString());
    }

    @And("Nella pagina Storico delle ricerche inserire nel campo di input il codice fiscale errato del destinatario {string}")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiInputIlCodiceFiscaleErratoDelDestinatario(String datiRADDFile) {
        logger.info("Si inserisce il codice fiscale errato");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(datiRADDFile + ".yaml");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.insertCodiceFiscale(this.datiRADD.get("codiceFiscaleErroreDAA").toString());
    }


    @Then("Nella pagina Storico delle ricerche viene visualizzato un pop up con l'etichetta KO per codice fiscale")
    public void nellaPaginaStoricoDelleRicercheVieneVisualizzatoUnPopUpConLEtichettaKOPerCodiceFiscale() {
        logger.info("Si verifica visualizzazione messaggio di errore");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.waitErrorMessageIdOperazione("Non ci transazioni per questo codice fiscale");
    }

    @And("Nella pagina Storico delle ricerche inserire nel campo di testo il codice IUN errato da ricercare da {string}")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiTestoIlCodiceIUNErratoDaRicercareDa(String datiRADDFile) {
        logger.info("Si inserisce il codice IUN errato");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(datiRADDFile + ".yaml");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.insertCodiceIUN(this.datiRADD.get("codiceIUNErroreDAA").toString());
    }

    @Then("Nella pagina Storico delle ricerche viene visualizzato un pop up con l'etichetta KO per codice IUN")
    public void nellaPaginaStoricoDelleRicercheVieneVisualizzatoUnPopUpConLEtichettaKOPerCodiceIUN() {
        logger.info("Si verifica visualizzazione messaggio di errore");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.waitErrorMessageIdOperazione("Non ci sono operation id");
    }

    @When("Nella pagina Storico delle ricerche selezionare il radio button Avvisi di avvenuta ricezione")
    public void nellaPaginaStoricoDelleRicercheSelezionareIlRadioButtonAvvisiDiAvvenutaRicezione() {
        logger.info("Si seleziona il radio button Avvisi di avvenuta ricezione");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.clickAARRAdioButton();
    }

    @And("Nella pagina Storico delle ricerche inserire nel campo di input il codice fiscale errato per AAR del destinatario {string}")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiInputIlCodiceFiscaleErratoPerAARDelDestinatario(String datiRADDFile) {
        logger.info("Si inserisce il codice fiscale errato");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(datiRADDFile + ".yaml");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.insertCodiceFiscale(this.datiRADD.get("codiceFiscaleErroreAAR").toString());
    }


    @And("Nella pagina Storico delle ricerche inserire nel campo di testo l'id Operazione errato {string} da ricercare")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiTestoLIdOperazioneErratoDaRicercare(String datiRADDFile) {
        logger.info("Si inserisce il campo id operazione errato");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(datiRADDFile + ".yaml");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.insertOperationID(this.datiRADD.get("idOperazioneErroreAAR").toString());
    }

    @And("Nella pagina Storico delle ricerche inserire nel campo di testo il codice IUN errato {string} da ricercare")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiTestoIlCodiceIUNErratoDaRicercare(String datiRADDFile) {
        logger.info("Si inserisce il codice IUN errato");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(datiRADDFile + ".yaml");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.insertCodiceIUN(this.datiRADD.get("codiceIUNErroreAAR").toString());
    }

    @And("Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi cliccare sulla richiesta restituita idOP")
    public void nellaPaginaRisultatoRicercaDocumentiAllegatiEAttestazioniOpponibileATerziCliccareSullaRichiestaRestituitaIdOP() {
        logger.info("Si clicca sulla richiesta restituita");
        DataPopulation dataPopulation = new DataPopulation();

        this.datiRADD = dataPopulation.readDataPopulation("RADD.yaml");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.clickRichiestaRestituita(this.datiRADD.get("idOperazioneDAA").toString());
    }

    @When("Nella pagina Storico delle ricerche il radio button avvisi avvenuta ricezione è selezionato")
    public void nellaPaginaStoricoDelleRicercheIlRadioButtonAvvisiAvvenutaRicezioneESelezionato() {
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.selectAvvisiAvvenutaRicezioneButton();
        logger.info("Il radio button avvisi avvenuta ricezione è selezionato");
    }

    @And("Nella pagina Storico delle ricerche inserire nel campo di input il codice fiscale del destinatario AAR {string}")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiInputIlCodiceFiscaleDelDestinatarioAAR(String dpFile) {
        logger.info("Si controlla che si visualizzino le richieste con il codice fiscale");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(dpFile + ".yaml");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.insertCodiceFiscale(this.datiRADD.get("codiceFiscaleRPAAR").toString());
    }

    @And("Nella pagina Risultato ricerca avvisi avvenuta ricezione vengono restituite le richieste con il codice fiscale del destinatario ricercato")
    public void nellaPaginaRisultatoRicercaAvvisiAvvenutaRicezioneVengonoRestituiteLeRichiesteConIlCodiceFiscaleDelDestinatarioRicercato() {
        logger.info("Si controlla che vengano restituiti dei risultati");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation("RADD.yaml");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        int numeroRighe = storicoRichiestePage.getNRighe(this.datiRADD.get("codiceFiscaleRPAAR").toString());

        if (numeroRighe >= 1) {
            logger.info("I risultati sono coerenti con il codice fiscale inserito");
        } else {
            logger.error("I risultati NON sono coerenti con il codice fiscale inserito");
            Assert.fail("I risultati NON sono coerenti con il codice fiscale inserito");
        }
    }

    @And("Nella pagina Risultato ricerca avvisi avvenuta ricezione cliccare su una richiesta")
    public void nellaPaginaRisultatoRicercaAvvisiAvvenutaRicezioneCliccareSuUnaRichiesta() {
        logger.info("Si clicca sulla richiesta restituita");
        DataPopulation dataPopulation = new DataPopulation();

        this.datiRADD = dataPopulation.readDataPopulation("RADD.yaml");


        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.clickRichiestaRestituita(this.datiRADD.get("codiceFiscaleRPAAR").toString());

    }

    @And("Nella pagina Storico delle ricerche inserire nel campo di input il codice IUN del destinatario AAR {string}")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiInputIlCodiceIUNDelDestinatarioAAR(String dpFile) {
        logger.info("Si controlla che si visualizzino le richieste con il codice IUN");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(dpFile + ".yaml");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.insertCodiceIUN(this.datiRADD.get("codiceIUNErroreRPAAR").toString());
    }

    @And("Nella pagina Risultato ricerca avvisi avvenuta ricezione vengono restituite le richieste con il codice IUN del destinatario ricercato")
    public void nellaPaginaRisultatoRicercaAvvisiAvvenutaRicezioneVengonoRestituiteLeRichiesteConIlCodiceIUNDelDestinatarioRicercato() {
        logger.info("Si controlla che vengano restituiti dei risultati");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation("RADD.yaml");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        int numeroRighe = storicoRichiestePage.getNRighe(this.datiRADD.get("codiceIUNErroreRPAAR").toString());

        if (numeroRighe >= 1) {
            logger.info("I risultati sono coerenti con il codice IUN inserito");
        } else {
            logger.error("I risultati NON sono coerenti con il codice IUN inserito");
            Assert.fail("I risultati NON sono coerenti con il codice IUN inserito");
        }
    }

    @And("Nella pagina Storico delle ricerche inserire nel campo di input l'ID operazione del destinatario AAR {string}")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiInputLIDOperazioneDelDestinatarioAAR(String datiRADDFile) {
        logger.info("Si inserisce il campo id operazione");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(datiRADDFile + ".yaml");
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.insertOperationID(this.datiRADD.get("idOperazioneRPAAR").toString());
    }

    @And("Nella pagina Risultato ricerca avvisi avvenuta ricezione vengono restituite le richieste con l'ID operazione del destinatario ricercato")
    public void nellaPaginaRisultatoRicercaAvvisiAvvenutaRicezioneVengonoRestituiteLeRichiesteConLIDOperazioneDelDestinatarioRicercato() {
        logger.info("Si controlla che vengano restituiti dei risultati");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation("RADD.yaml");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        int numeroRighe = storicoRichiestePage.getNRighe(this.datiRADD.get("idOperazioneRPAAR").toString());

        if (numeroRighe >= 1) {
            logger.info("I risultati sono coerenti con il ID operazione inserito");
        } else {
            logger.error("I risultati NON sono coerenti con il ID operazione inserito");
            Assert.fail("I risultati NON sono coerenti con il ID operazione inserito");
        }
    }

    @And("Nella pagina Storico delle ricerche inserire nel campo di input della notifica inserire la partita iva {string}")
    public void nellaPaginaStoricoDelleRicercheInserireNelCampoDiInputDellaNotificaInserireLaPartitaIva(String dpFile) {
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(dpFile+".yaml");
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.insertPartitaIva(this.datiRADD.get("partitaIVAPGDA").toString());
    }

    @And("Nella pagina Storico delle ricerche selezionare il radio button Persona Giuridica")
    public void nellaPaginaStoricoDelleRicercheSelezionareIlRadioButtonPersonaGiuridica() {
        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        storicoRichiestePage.clickPersonaGiuridicaRadioButton();
    }

    @And("Nella pagina Risultato ricerca avvisi avvenuta ricezione vengono restituite le richieste con partita iva del destinatario ricercato")
    public void nellaPaginaRisultatoRicercaAvvisiAvvenutaRicezioneVengonoRestituiteLeRichiesteConPartitaIvaDelDestinatarioRicercato() {
        logger.info("Si controlla che vengano restituiti dei risultati");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation("RADD.yaml");

        StoricoRichiestePage storicoRichiestePage = new StoricoRichiestePage(this.driver);
        int numeroRighe = storicoRichiestePage.getNRighe(this.datiRADD.get("partitaIVAPGDA").toString());

        if (numeroRighe >= 1) {
            logger.info("I risultati sono coerenti con il codice fiscale inserito");
        } else {
            logger.error("I risultati NON sono coerenti con il codice fiscale inserito");
            Assert.fail("I risultati NON sono coerenti con il codice fiscale inserito");
        }
    }
}
