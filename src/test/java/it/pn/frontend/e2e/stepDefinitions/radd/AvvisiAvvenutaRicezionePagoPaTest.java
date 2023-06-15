package it.pn.frontend.e2e.stepDefinitions.radd;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.radd.AvvisiAvvenutaRicezionePage;
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

public class AvvisiAvvenutaRicezionePagoPaTest {
    private final WebDriver driver = Hooks.driver;
    private final Logger logger = LoggerFactory.getLogger("AvvisiAvvenutaRicezionePagoPa");

    private Map<String,Object> datiAAR = new HashMap<>();

    @When("nella Homepage RADD sezione Avvisi di avvenuta ricezione clicca sul bottone Vai a Avvisi di avvenuta ricezione")
    public void clickAvvisiAvvenutaRecezioneButton(){
        logger.info("Si clicca su Avvisi di avvenuta ricezione");

        HomePage homePage = new HomePage(this.driver);
        homePage.clickSuAvvenutaRecezione();
    }

    @Then("la pagina Avvisi di avvenuta ricezione sezione Dati del destinatario è visualizzata correttamente")
    public void laPaginaAvvisiDiAvvenutaRicezioneSezioneDatiDelDestinatarioEVisualizzataCorrettamente() {
        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.waitLoadAvvenutaRicezione();
    }

    @When("nella pagina Avvisi di avvenuta ricezione sezione Dati del destinatario il Soggetto giuridico e selezionato di default come Persona fisica")
    public void nellaPaginaAvvisiDiAvvenutaRicezioneSezioneDatiDelDestinatarioIlSoggettoGiuridicoASelezionatoDiDefaultComePersonaFisica() {
        logger.info("Si controlla che sia selezionato Persona fisica");

        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        if(avvisiAvvenutaRicezionePage.checkPersonaFisica()){
            logger.info("Persona fisica Ã¨ selezionato");
        }else {
            logger.error("Persona fisica non Ã¨ selezionato");
            Assert.fail("Persona fisica non Ã¨ selezionato");
        }
    }

    @And("nella pagina Avvisi di avvenuta ricezione sezione Dati del destinatario inserire il Codice fiscale del destinatario {string}")
    public void nellaPaginaAvvisiDiAvvenutaRicezioneSezioneDatiDelDestinatarioInserireIlCodiceFiscaleDelDestinatario(String dpFile) {
        logger.info("Si cerca di inserire il codice fiscale del destinatario");

        DataPopulation dataPopulation = new DataPopulation();
        this.datiAAR = dataPopulation.readDataPopulation(dpFile+".yaml");

        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.inserimentoCFDDestinatario(this.datiAAR.get("codiceFiscaleDestinatarioAAR").toString());
    }

    @And("nella pagina Avvisi di avvenuta ricezione sezione Dati del destinatario cliccare sul bottone Continua")
    public void nellaPaginaAvvisiDiAvvenutaRicezioneSezioneDatiDelDestinatarioCliccareSulBottoneContinua() {
        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.clickContinuaButton();
    }

    @And("nella pagina Avvisi di avvenuta ricezione sezione Dati del destinatario inserire il Codice fiscale del delegato {string}")
    public void nellaPaginaAvvisiDiAvvenutaRicezioneSezioneDatiDelDestinatarioInserireIlCodiceFiscaleDelDelegato(String dpFile) {
        logger.info("Si cerca di inserire il codice fiscale delegato");

        DataPopulation dataPopulation = new DataPopulation();
        this.datiAAR = dataPopulation.readDataPopulation(dpFile+".yaml");

        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.inserimentoCFDelegato(this.datiAAR.get("codiceFiscaleDelegatoAAR").toString());
    }

    @And("nella pagina Avvisi di avvenuta ricezione sezione Caricamento documenti cliccare sul bottone Continua")
    public void nellaPaginaAvvisiDiAvvenutaRicezioneSezioneCaricamentoDocumentiCliccareSulBottoneContinua() {
        logger.info("Cliccare sul bottone continua");

        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.clickContinuaButton();
    }

    @And("la pagina Avvisi di avvenuta ricezione sezione Caricamento documenti è visualizzata correttamente")
    public void laPaginaAvvisiDiAvvenutaRicezioneSezioneCaricamentoDocumentiEVisualizzataCorrettamente() {
        logger.info("Si visualizza correttamente la sezione Caricamento Documenti");
        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.waitLoadCaricamentoDocumenti();
    }

    @And("nella pagina Avvisi di avvenuta ricezione sezione Caricamento documenti caricare il documento di riconoscimento del destinario nel box 1 Carica il documento di riconoscimento del destinatario")
    public void nellaPaginaAvvisiDiAvvenutaRicezioneSezioneCaricamentoDocumentiCaricareIlDocumentoDiRiconoscimentoDelDestinarioNelBoxUnoCaricaIlDocumentoDiRiconoscimentoDelDestinatario() {
        logger.info("Si cerca di caricare il documento di identitÃ  del Destinatario");

        File documentoFile = new File("src/test/resources/dataPopulation/fileUpload/semiOfficial1.jpg");
        String pathDocumentiFile = documentoFile.getAbsolutePath();

        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.uploadDocumento(pathDocumentiFile);
    }

    @And("nella pagina Avvisi di avvenuta ricezione sezione Caricamento documenti caricare il documento di riconoscimento del delegato nel box due Carica il documento di riconoscimento del delegato")
    public void nellaPaginaAvvisiDiAvvenutaRicezioneSezioneCaricamentoDocumentiCaricareIlDocumentoDiRiconoscimentoDelDelegatoNelBoxDueCaricaIlDocumentoDiRiconoscimentoDelDelegato() {
        logger.info("Si carica il documento di identitÃ  del delegato");

        File documentoFile = new File("src/test/resources/dataPopulation/fileUpload/semiOfficial3.jpg");
        String pathDocumentiFile = documentoFile.getAbsolutePath();

        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.uploadDocumento(pathDocumentiFile);
    }

    @And("nella pagina Avvisi di avvenuta ricezione sezione Caricamento documenti caricare il Modulo di delega nel box tre Modulo di delega")
    public void nellaPaginaAvvisiDiAvvenutaRicezioneSezioneCaricamentoDocumentiCaricareIlModuloDiDelegaNelBoxTreModuloDiDelega() {
        logger.info("Si carica il modulo della delega");

        File documentoFile = new File("src/test/resources/dataPopulation/fileUpload/Modello-delega-ritiro-documenti-768x527.jpg");
        String pathDocumentiFile = documentoFile.getAbsolutePath();

        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.uploadDocumento(pathDocumentiFile);
    }

    @And("la pagina Avvisi di avvenuta ricezione sezione Avvisi di avvenuta ricezione è visualizzata correttamente")
    public void laPaginaAvvisiDiAvvenutaRicezioneSezioneAvvisiDiAvvenutaRicezioneEVisualizzataCorrettamente() {
        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.waitLoadAvvenutaRicezioneUltimoStep();
    }

    @And("nella pagina Avvisi di avvenuta ricezione sezione Avvisi di avvenuta ricezione esegue il download dei documenti")
    public void nellaPaginaAvvisiDiAvvenutaRicezioneSezioneAvvisiDiAvvenutaRicezioneEsegueIlDownloadDeiDocumenti() {
        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.scaricaTuttiDocumenti();
    }

    @And("nella pagina Avvisi di avvenuta ricezione sezione Avvisi il bottone ho finito si abilita")
    public void nellaPaginaAvvisiDiAvvenutaRicezioneSezioneAvvisiIlBottoneHoFinitoSiAbilita() {
        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.hoFinitoButtonAbilitato();
    }

    @And("nella pagina Avvisi di avvenuta ricezione sezione Avvisi cliccare sul bottone home page")
    public void nellaPaginaAvvisiDiAvvenutaRicezioneSezioneAvvisiCliccareSulBottoneHomePage() {
        AvvisiAvvenutaRicezionePage avvisiAvvenutaRicezionePage = new AvvisiAvvenutaRicezionePage(this.driver);
        avvisiAvvenutaRicezionePage.clickArrowBack();
    }

    @And("nella pagina Avvisi di avvenuta ricezione sezione Caricamento documenti caricare un documento con estenzione non valida")
    public void nellaPaginaAvvisiDiAvvenutaRicezioneSezioneCaricamentoDocumentiCaricareUnDocumentoConEstenzioneNonValida() {
        RichiestaAttiPage richiestaAttiPage = new RichiestaAttiPage(this.driver);
        File documentoFile = new File("src/test/resources/dataPopulation/fileUpload/sample.pdf");
        String pathDocumentiFile = documentoFile.getAbsolutePath();
        richiestaAttiPage.uploadFilefromPC(pathDocumentiFile);
    }
}
