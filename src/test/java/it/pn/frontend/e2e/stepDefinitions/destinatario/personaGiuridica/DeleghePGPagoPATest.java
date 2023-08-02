package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
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

    @And("Nella pagina Deleghe persona giuridica si vede la sezione Deleghe")
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
    public void nellaSezioneLeTueDeleghePersonaGiuridicaInserireIDati(String nomeFile) {
        logger.info("Si aggiungo tutti i dati del delegato");

        this.datiDelega = dataPopulation.readDataPopulation(nomeFile+".yaml");

        aggiungiDelegaPGSection.selectpersonaFisicaRadioButton();
        aggiungiDelegaPGSection.insertNomeCognome(this.datiDelega.get("nome").toString(), this.datiDelega.get("cognome").toString());
        aggiungiDelegaPGSection.inserireCF(this.datiDelega.get("codiceFiscale").toString());
        aggiungiDelegaPGSection.selectSoloEntiSelezionati();
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
    public void nellaSezioneLeTueDeleghePersonaGiuridicaSalvareIlCodiceVerificaAllInternoDelFile(String nomeFile) {
        logger.info("Si salva il codice della delega nel file "+nomeFile);

        this.datiDelega = dataPopulation.readDataPopulation(nomeFile+".yaml");

        String codiceDelega = aggiungiDelegaPGSection.salvataggioCodiceVerifica();
        this.datiDelega.put("codiceDelega",codiceDelega);

        dataPopulation.writeDataPopulation(nomeFile+".yaml",this.datiDelega);
    }

    @And("Nella sezione Aggiungi Delega  persona giuridica click sul bottone Invia richiesta e sul bottone torna alle deleghe")
    public void nellaSezioneLeTueDeleghePersonaGiuridicaclickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe() {
        logger.info("Si clicca sul bottone invia richiesta");

        aggiungiDelegaPGSection.clickSulBottoneInviaRichiesta();

    }

    @And("Nella sezione Delegati dall impresa si visualizza la delega in stato di attesa di conferma")
    public void nellaSezioneDelegatiDallImpresaSiVisualizzaLaDelegaInStatoDiAttesaDiConferma() {
        logger.info("Si controla che la delega sia in stato attesa di conferma");

        deleghePGPagoPAPage.waitLoadDeleghePage();
        deleghePGPagoPAPage.controlloCreazioneDelega();

    }
}
