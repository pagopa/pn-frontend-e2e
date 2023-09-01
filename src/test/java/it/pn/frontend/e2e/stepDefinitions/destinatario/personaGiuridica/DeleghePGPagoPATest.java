package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.DeleghePGPagoPAPage;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.AggiungiDelegaPGSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.DelegatiImpresaSection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DeleghePGPagoPATest {
    private final Logger logger = LoggerFactory.getLogger("DeleghePGPagoPATest");

    private final WebDriver driver = Hooks.driver;

    private final DeleghePGPagoPAPage deleghePGPagoPAPage = new DeleghePGPagoPAPage(this.driver);

    private final DelegatiImpresaSection delegatiImpresaSection = new DelegatiImpresaSection(this.driver);

    private final AggiungiDelegaPGSection aggiungiDelegaPGSection = new AggiungiDelegaPGSection(this.driver);

    private final DataPopulation dataPopulation = new DataPopulation();

    private Map<String,Object> datiDelega = new HashMap<>();
    Map<String,Object> datiPersonaFisica = new HashMap<>();

    @And("Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa")
    public void siVisulaizzaLaPaginaDeleghe(){
        logger.info("Si controlla che si visualizza la pagina Deleghe");

        deleghePGPagoPAPage.waitLoadDeleghePage();

    }

    @And("Nella pagina Deleghe si clicca su Delegati dall impresa")
    public void nellaPaginaDelegheSiCliccaSuDelegatiDallImpresa() {
        logger.info("Si clicca sul delegati dell'impresa");

        deleghePGPagoPAPage.clickDelegatiImpresa();

    }

    @And("Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa")
    public void siVisualizzaCorrettamenteLaPaginaDelegheSezioneDelegheDellImpresa() {
        logger.info("Si controlla che la sezione Deleghe dell'impresa");

        delegatiImpresaSection.waitLoadDelegatiImpresaPage();

    }

    @And("Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega")
    public void nellaSezioneDelegatiDellImpresaClickSulBottoneAggiungiNuovaDelega() {
        logger.info("Nella sezione Deleghe dell'impresa si clicca su aggiungi una nuova delega");

        delegatiImpresaSection.clickAggiungiDelegaButton();
    }

    @And("Si visualizza la sezione Aggiungi Delega persona giuridica")
    public void siVisualizzaLaSezioneLeTueDeleghePersonaGiuridica() {
        logger.info("Si controllo che si visualizza la sezione Aggiungi delega");

        aggiungiDelegaPGSection.waitLoadAggiungiDelegaPage();

    }

    @And("Nella sezione Aggiungi Delega persona giuridica inserire i dati {string}")
    public void nellaSezioneLeTueDeleghePersonaGiuridicaInserireIDati(String dpFile) {
        logger.info("Si aggiungo tutti i dati del delegato");

        this.datiDelega = dataPopulation.readDataPopulation(dpFile+".yaml");

        aggiungiDelegaPGSection.selectpersonaGiuridicaRadioButton();
        aggiungiDelegaPGSection.insertRagioneSociale(this.datiDelega.get("ragioneSociale").toString());
        aggiungiDelegaPGSection.inserireCF(this.datiDelega.get("codiceFiscale").toString());
        aggiungiDelegaPGSection.selectSoloEntiSelezionati();
        aggiungiDelegaPGSection.waitLoadAggiungiDelegaPage();
        aggiungiDelegaPGSection.selezionaUnEnte(datiDelega.get("ente").toString());
    }

    @And("Nella sezione Aggiungi Delega persona giuridica verificare che la data sia corretta")
    public void nellaSezioneLeTueDeleghePersonaGiuridicaVerificareCheLaDataSiaCorretta() {
        logger.info("Si controlla che la data di fine delega sia corretta");

        if (aggiungiDelegaPGSection.verificareCheLaDataSiaCorretta()){
            logger.info("La data di fine delega è coretta");
        }else {
            logger.error("La data di fine delega non è coretta");
            Assert.fail("La data di fine delega non è coretta");
        }
    }

    @And("Nella sezione Aggiungi Delega persona giuridica salvare il codice verifica all'interno del file {string}")
    public void nellaSezioneLeTueDeleghePersonaGiuridicaSalvareIlCodiceVerificaAllInternoDelFile(String dpFile) {
        logger.info("Si salva il codice della delega nel file "+dpFile);

        this.datiDelega = dataPopulation.readDataPopulation(dpFile+".yaml");

        String codiceDelega = aggiungiDelegaPGSection.salvataggioCodiceVerifica();
        this.datiDelega.put("codiceDelega",codiceDelega);

        dataPopulation.writeDataPopulation(dpFile+".yaml",this.datiDelega);
    }

    @And("Nella sezione Aggiungi Delega  persona giuridica click sul bottone Invia richiesta e sul bottone torna alle deleghe")
    public void nellaSezioneLeTueDeleghePersonaGiuridicaclickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe() {
        logger.info("Si clicca sul bottone invia richiesta");

        aggiungiDelegaPGSection.clickSulBottoneInviaRichiesta();

    }

    @And("Nella sezione Delegati dall impresa si visualizza la delega in stato di attesa di conferma")
    public void nellaSezioneDelegatiDallImpresaSiVisualizzaLaDelegaInStatoDiAttesaDiConferma() {
        logger.info("Si controla che la delega sia in stato attesa di conferma");

        this.datiDelega = dataPopulation.readDataPopulation("nuovaDelegaPG.yaml");

       delegatiImpresaSection.waitLoadDelegatiImpresaPage();
       delegatiImpresaSection.controlloCreazioneDelega(this.datiDelega.get("ragioneSociale").toString());
    }

    @And("Si controlla che non sia presente una delga con stesso nome {string} persona giuridica")
    public void siControllaCheNonSiaPresenteUnaDelgaConStessoNomePersonaGiuridica(String nomeFile) {
        logger.info("Si controlla che non ci sia una delega con lo stesso nome");

        this.datiDelega = dataPopulation.readDataPopulation(nomeFile+".yaml");

        String ragioneSociale =  datiDelega.get("ragioneSociale").toString();

        if (deleghePGPagoPAPage.CercaEsistenzaDelegaPG(ragioneSociale)){
            logger.info("Delega con lo stesso nome trovata");
            deleghePGPagoPAPage.clickRevocaMenuButtonPG(ragioneSociale);
            deleghePGPagoPAPage.waitPopUpRevoca();
            deleghePGPagoPAPage.clickRevocaButton();
        }else {
            logger.info("Delega con lo stesso nome NON trovata");
        }
    }
    @And("Nella sezione Le Tue Deleghe inserire una data con formato errato e andecedente alla data")
    public void nellaSezioneLeTueDelegheInserireUnaDataConFormatoErratoEAndecedenteAllaData() {
        logger.info("Si inserisce una data errata e andecedente");

        aggiungiDelegaPGSection.clearImputData();
        aggiungiDelegaPGSection.insertDataErrata();
    }

    @And("Nella sezione Le Tue Deleghe si visualizza il messaggio di errore data errata")
    public void nellaSezioneLeTueDelegheSiVisualizzaIlMessaggioDiErroreDataErrata() {
        logger.info("Si controlla che si veda il messaggio di errore");

        aggiungiDelegaPGSection.waitMessaggioErroreData();
    }

    @And("Nella pagina Deleghe si clicca su Deleghe a carico dell impresa")
    public void nellaPaginaDelegheSiCliccaSuDelegheACaricoDellImpresa() {
        logger.info("Si clicca sul tab Deleghe a carico dell'impresa");

        deleghePGPagoPAPage.clickSuDelegheCaricoDellImpresa();
    }

    @And("Si vede correttamente l'elenco delle deleghe")
    public void siVedeCorrettamenteLElencoDelleDeleghe() {
        logger.info("Si controlla che si visualizzi l'elenco delle deleghe");

        deleghePGPagoPAPage.verificaPresenzaElencoDeleghe();

    }

    @And("Nella pagina Deleghe sezione Deleghe a Carico dell impresa si inserisce il codice fiscale del delegante {string}")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiInserisceIlCodiceFiscaleDelDelegante(String dpFile) {
        logger.info("Si inserisce il codice fiscale del delegante");

        this.datiPersonaFisica = this.dataPopulation.readDataPopulation(dpFile+".yaml");

        deleghePGPagoPAPage.insertCFDelegante(this.datiPersonaFisica.get("codiceFiscale").toString());

    }

    @And("Nella pagina Deleghe sezione Deleghe a Carico dell impresa si clicca su bottone Filtra")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiCliccaSuBottoneFiltra() {
        logger.info("Si clicca filtra button");

        this.deleghePGPagoPAPage.clickFiltraButton();

    }

    @And("Nella pagina Deleghe sezione Deleghe a Carico dell impresa si controlla che venga restituita la delega con il codice fiscale inserito {string}")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiControllaCheVengaRestituitaLaDelegaConIlCodiceFiscaleInserito(String dpFile) {
        this.datiPersonaFisica = this.dataPopulation.readDataPopulation(dpFile+".yaml");

        if (deleghePGPagoPAPage.controlloDelegaRestituita(this.datiPersonaFisica.get("name").toString(),this.datiPersonaFisica.get("familyName").toString())){
            this.logger.info("La delega restituita è corretta");
        }else {
            this.logger.error("La delega restituita NON è corretta");
            Assert.fail("La delega restituita NON è corretta");
        }
    }

    @And("Nella pagina Deleghe sezione Deleghe dell impresa  si verifica sia presente una delega")
    public void nellaPaginaDelegheSezioneDelegheDellImpresaSiVerificaSiaPresenteUnaDelega() {
        logger.info("Si controlla che ci sia almeno una delega");

        this.delegatiImpresaSection.siVisualizzaUnaDelega();

    }

    @And("Nella pagina Deleghe sezione Deleghe dell impresa si clicca sul menu della delega")
    public void nellaPaginaDelegheSezioneDelegheDellImpresaSiCliccaSulMenuDellaDelega() {
        logger.info("Si clicca sul menu delle delega");

        this.datiDelega = dataPopulation.readDataPopulation("nuovaDelegaPG.yaml");

        this.delegatiImpresaSection.clickMenuDelega(datiDelega.get("ragioneSociale").toString());

    }

    @And("Nella pagina Deleghe sezione Deleghe dell impresa si sceglie l'opzione mostra codice")
    public void nellaPaginaDelegheSezioneDelegheDellImpresaSiSceglieLOpzioneMostraCodice() {
        logger.info("Si sceglie l'opzione mostra codice");

        delegatiImpresaSection.clickMostraCodice();
    }

    @Then("Si clicca sul bottone annulla")
    public void siCliccaSulBottoneAnnulla() {
        logger.info("Si clicca sul bottone annulla");
        delegatiImpresaSection.clickAnnulla();
    }

    @And("Nella sezione Deleghe persona giuridica si sceglie l'opzione revoca")
    public void nellaSezioneDeleghePersonaGiuridicaSiSceglieLOpzioneRevoca() {
        logger.info("Si clicca sull'opzione revoca del menu");

        delegatiImpresaSection.clickRevocaMenuButtonPG();
    }

    @And("Nella pagina Deleghe sezione Deleghe a Carico dell impresa si inserisce il gruppo del delegante")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiInserisceIlGruppoDelDelegante() {
        logger.info("Si inserisce il gruppo del delegante");

        deleghePGPagoPAPage.inserireGruppoDelegante();
    }
}
