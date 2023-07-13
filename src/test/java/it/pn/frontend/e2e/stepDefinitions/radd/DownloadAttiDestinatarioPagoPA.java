package it.pn.frontend.e2e.stepDefinitions.radd;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.radd.HomePage;
import it.pn.frontend.e2e.pages.radd.RichiestaAttiPage;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DownloadAttiDestinatarioPagoPA {

    private final WebDriver driver = Hooks.driver;
    private final Logger logger = LoggerFactory.getLogger("DownloadAttiDestinatarioPagoPA");

    private Map<String, Object> datiRADD = new HashMap<>();

    @Given("la Homepage RADD è visualizzata correttamente")
    public void la_homepage_radd_e_visualizzata_correttamente() {
        HomePage homePage = new HomePage(this.driver);
        homePage.siVisualizzaCorrettamenteHomePage();
    }

    @When("nella Homepage RADD sezione Documenti allegati della notifica e attestazioni opponibili a terzi clicca sul bottone con freccia")
    public void clickSuDocumentiAllegati() {

        HomePage homePage = new HomePage(this.driver);
        homePage.clickSuDocumentiAllegati();
    }

    @Then("la pagina Richiesta Atti sezione Dati della notifica è visualizzata correttamente")
    public void laPaginaRichiestaAttiSezioneDatiDellaNotificaEVisualizzataCorrettamente() {
        HomePage homePage = new HomePage(this.driver);
        homePage.richiestaAttipageSiVisualizzaCorretamente();
    }

    @When("nella pagina atti-opponibili-terzi sezione Dati della notifica inserire il codice IUN {string}")
    public void nellaPaginaRichiestaAttiSezioneDatiDellaNotificaInserireIlCodiceIUN(String dpFile) {
        DataPopulation dataPopulation = new DataPopulation();

        this.datiRADD = dataPopulation.readDataPopulation(dpFile+".yaml");
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.insertCodiceIun(this.datiRADD.get("codiceIun").toString());
    }

    @And("nella pagina atti-opponibili-terzi sezione Dati della notifica è selezionato di default il Soggetto giuridico come Persona fisica")
    public void SoggettoGiuridicoComePersonaFisicaSelezionato() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        if(richiestaAttiPage.selectSoggettoGiuridico()){
            logger.info("Persona fisica è selezionata di default");
        } else {
            logger.error("Persona fisica non è selezionata di default");
            Assert.fail("Persona fisica non è selezionata di default");
        }
    }

    @And("nella pagina atti-opponibili-terzi sezione Dati della notifica inserire il Codice fiscale del destinatario {string}")
    public void nellaPaginaAttiOpponibiliTerziSezioneDatiDellaNotificaInserireIlCodiceFiscaleDelDestinatario(String dpFile) {
        DataPopulation dataPopulation = new DataPopulation();

        this.datiRADD = dataPopulation.readDataPopulation(dpFile+".yaml");
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.insertCodiceFiscale(this.datiRADD.get("codiceFiscaleDestinario").toString());
    }

    @And("nella pagina atti-opponibili-terzi sezione Dati della notifica cliccare sul bottone Continua")
    public void nellaPaginaAttiOpponibiliTerziSezioneDatiDellaNotificaCliccareSulBottoneContinua() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.clickContinuaButton();
    }

    @And("la pagina atti-opponibili-terzi sezione Caricamento documenti è visualizzata correttamente")
    public void laPaginaAttiOpponibiliTerziSezioneCaricamentoDocumentiEVisualizzataCorrettamente() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.waitLoadCaricamentoDocumenti();
    }

    @And("nella pagina atti-opponibili-terzi sezione Caricamento documenti caricare il documento di riconoscimento del destinario nel box uno Carica il documento di riconoscimento del destinatario")
    public void CaricareIlDocumentoDiRiconoscimentoDelDestinarioNelBoxDestinatario() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
//        richiestaAttiPage.uploadDocumentoDestinatario();
//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        File documentoFile = new File("src/test/resources/dataPopulation/fileUpload/semiOfficial1.jpg");
        String pathDocumentiFile = documentoFile.getAbsolutePath();
        richiestaAttiPage.uploadFilefromPC(pathDocumentiFile);
    }

    @And("nella pagina atti-opponibili-terzi sezione Caricamento documenti cliccare sul bottone Continua")
    public void nellaPaginaAttiOpponibiliTerziSezioneCaricamentoDocumentiCliccareSulBottoneContinua() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.clickContinua2Button();
    }

    @And("la pagina atti-opponibili-terzi sezione Allegati e attestazioni è visualizzata correttamente")
    public void laPaginaAttiOpponibiliTerziSezioneAllegatiEAttestazioniEVisualizzataCorrettamente() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.waitLoadAllegatiAttestazioni();
    }

    @And("nella pagina atti-opponibili-terzi sezione Allegati e attestazioni esegue il download dei documenti")
    public void nellaPaginaAttiOpponibiliTerziSezioneAllegatiEAttestazioniEsegueIlDownloadDeiDocumenti() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.clickDownload();
        richiestaAttiPage.controlloDownload();
    }



    @Given("operatore è loggato")
    public void operatoreELoggato() {
        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> datiNotifica = dataPopulation.readDataPopulation("RADD.yaml");

        driver.get(datiNotifica.get("url").toString());
    }

    @And("nella pagina atti-opponibili-terzi sezione Allegati e attestazioni cliccare sul bottone ho finito")
    public void nellaPaginaAttiOpponibiliTerziSezioneAllegatiEAttestazioniCliccareSulBottoneHoFinito() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        richiestaAttiPage.clickHoFinito();
    }

    @Then("Si visualizza correttamente la pagina di conferma RADD")
    public void siVisualizzaCorrettamenteLaPaginaDiConfermaRADD() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.waitLoadConfirmationPage();
    }

    @And("nella pagina di conferma cliccare sul bottone chiudi")
    public void nellaPaginaDiConfermaCliccareSulBottoneChiudi() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.clickChiudiButton();
    }

    @And("nella pagina atti-opponibili-terzi sezione Dati della notifica inserire il Codice fiscale del delegato {string}")
    public void InserireIlCodiceFiscaleDelDelDelegato(String dpFile) {
        DataPopulation dataPopulation = new DataPopulation();

        this.datiRADD = dataPopulation.readDataPopulation(dpFile+".yaml");
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.insertCodiceFiscaleDelegato(this.datiRADD.get("codiceFiscaleDelegato").toString());
    }

    @And("nella pagina Richiesta Atti sezione Caricamento documenti caricare il documento di riconoscimento del delegato nel box due Carica il documento di riconoscimento del delegato")
    public void CaricareIlDocumentoDelDelegatoNelBox() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        File documentoFile1 = new File("src/test/resources/dataPopulation/fileUpload/semiOfficial3.jpg");
        String pathDocumentiFile1 = documentoFile1.getAbsolutePath();
        richiestaAttiPage.uploadFilefromPC1(pathDocumentiFile1);
    }

    @And("nella pagina Richiesta Atti sezione Caricamento documenti caricare il Modulo di delega nel box tre  Modulo di delega")
    public void CaricareIlModuloDiDelegaNelBoxTreModuloDiDelega() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        File documentoFile2 = new File("src/test/resources/dataPopulation/fileUpload/Modello-delega-ritiro-documenti-768x527.jpg");
        String pathDocumentiFile2 = documentoFile2.getAbsolutePath();
        richiestaAttiPage.uploadFilefromPC2(pathDocumentiFile2);
    }

    @And("la pagina Richiesta Atti sezione Allegati e attestazioni è visualizzata correttamente")
    public void laPaginaRichiestaAttiSezioneAllegatiEAttestazioniEVisualizzataCorrettamente() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);

        richiestaAttiPage.attestazioniSectionIsDisplayed();
    }

    @And("nella pagina Richiesta Atti sezione Allegati e attestazioni è visualizzata la frase Assicurati di scaricare tutti i documenti")
    public void nellaPaginaRichiestaAttiSezioneAllegatiEAttestazioniEVisualizzataLaFraseAssicuratiDiScaricareTuttiIDocumenti() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.assicuratiIsDisplayed();
    }

    @Then("nella pagina di conferma cliccare sul link dello storico")
    public void nellaPaginadiConfermaLaFraseStoricoDelleRicercheELinkata() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.clickLinkStorico();
    }

    @And("la landing page si visualizza")
    public void laLandingPageSiVisualizza() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.waitLoadLandingPage();
    }

    @And("nella landing page cliccare sul tasto torna alla home")
    public void nellaLandingPageCliccareSulTastoTornaAllaHome() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.clickTornaHomeButton();
    }

    @Then("nella pagina Richiesta Atti sezione Dati della notifica visualizzare la frase di errore primo step")
    public void nellaPaginaRichiestaAttiSezioneDatiDellaNotificaVisualizzareLaFraseInRossoNonSonoStatiTrovatiDocumenti() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        if (richiestaAttiPage.codiceFiscaleErrorMessage()){
            logger.info("Il messaggio di errore si visualizza correttamente");
        } else {
            logger.error("Il messaggio di errore NON si visualizza correttamente");
            Assert.fail("Il messaggio di errore NON si visualizza correttamente");
        }
    }

    @Then("nella pagina Richiesta Atti sezione Dati della notifica visualizzare la frase di errore secondo step")
    public void siVisualizzaIlMessaggioDiErrore() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        if (richiestaAttiPage.upLoadErrorMessage()){
            logger.info("Il messaggio di errore si visualizza correttamente");
        } else {
            logger.error("Il messaggio di errore NON si visualizza correttamente");
            Assert.fail("Il messaggio di errore NON si visualizza correttamente");
        }
    }

    @Then("nella pagina Richiesta Atti sezione Dati della notifica visualizzare la frase di errore terzo step")
    public void nellaPaginaRichiestaAttiSezioneDatiDellaNotificaVisualizzareLaFraseDiErroreTerzoStep() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        if (richiestaAttiPage.downloadErrorMessage()){
            logger.info("Il messaggio di errore si visualizza correttamente");
        } else {
            logger.error("Il messaggio di errore NON si visualizza correttamente");
            Assert.fail("Il messaggio di errore NON si visualizza correttamente");
        }
    }

    @Then("Si visualizza un messaggio di errore")
    public void nellaPaginaRichiestaAttiSezioneCaricamentoDocumentiSiVisualizzaUnMessaggioDiErrore() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        if (richiestaAttiPage.upLoadErrorMessageEstenzione()){
            logger.info("Il messaggio di errore si visualizza correttamente");
        } else {
            logger.error("Il messaggio di errore NON si visualizza correttamente");
            Assert.fail("Il messaggio di errore NON si visualizza correttamente");
        }
    }

    @And("nella pagina atti-opponibili-terzi sezione Allegati il bottone ho finito si abilita")
    public void nellaPaginaAttiOpponibiliTerziSezioneAllegatiIlBottoneHoFinitoSiAbilita() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        if (richiestaAttiPage.hoFinitoButtonEAbilitato()) {
            logger.info("Il bottone ho finito è abilitato");
        } else {
            logger.error("Il bottone ho finito NON è abilitato");
            Assert.fail("Il bottone ho finito NON è abilitato");
        }
    }

    @And("nella pagina atti-opponibili-terzi sezione Allegati cliccare sul bottone home page")
    public void nellaPaginaAttiOpponibiliTerziSezioneAllegatiCliccareSulBottoneHomePage() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.clickHomePageButton();
    }

    @And("nella pagina atti-opponibili-terzi sezione Caricamento documenti caricare un documento con estenzione non valida")
    public void nellaPaginaAttiOpponibiliTerziSezioneCaricamentoDocumentiCaricareUnDocumentoConEstenzioneNonValida() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        File documentoFile = new File("src/test/resources/dataPopulation/fileUpload/sample.pdf");
        String pathDocumentiFile = documentoFile.getAbsolutePath();
        richiestaAttiPage.uploadFilefromPC(pathDocumentiFile);
    }

    @When("nella pagina atti-opponibili-terzi sezione Dati del destinatario selezionare persona giudridica")
    public void nellaPaginaAttiOpponibiliTerziSezioneDatiDelDestinatarioSelezionarePersonaGiudridica() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.clickPersonaGiuridicaButton();
    }

    @When("nella pagina atti-opponibili-terzi sezione Dati della notifica inserire codice IUN persona giuridica {string}")
    public void nellaPaginaAttiOpponibiliTerziSezioneDatiDellaNotificaInserireCodiceIUNPersonaGiuridica(String dpFile) {
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(dpFile+".yaml");
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.insertCodiceFiscale(this.datiRADD.get("partitaIVAPGDA").toString());
    }

    @When("nella pagina atti-opponibili-terzi sezione Dati della notifica inserire la partita iva {string}")
    public void nellaPaginaAttiOpponibiliTerziSezioneDatiDellaNotificaInserireLaPartitaIva(String dpFile) {
        DataPopulation dataPopulation = new DataPopulation();
        this.datiRADD = dataPopulation.readDataPopulation(dpFile+".yaml");
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        richiestaAttiPage.insertCodiceIun(this.datiRADD.get("codiceIUNPGDA").toString());
    }

}


