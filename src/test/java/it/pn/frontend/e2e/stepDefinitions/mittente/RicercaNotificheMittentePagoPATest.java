package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePAPage;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import it.pn.frontend.e2e.section.mittente.DestinatarioPASection;

import java.util.HashMap;
import java.util.Map;

public class RicercaNotificheMittentePagoPATest {

    private static final Logger logger = LoggerFactory.getLogger("NotificaMittenteTest");

    private final WebDriver driver = Hooks.driver;
    private Map<String, Object> destinatario = new HashMap<>();
    private Map<String, Object> datiNotifica = new HashMap<>();

    @And("Nella pagina Piattaforma Notifiche inserire il codice fiscale del destinatario {string}")
    public void inserireCodiceFiscale(String dpFile) {
        logger.info("Avvio ricerca tramite codice fiscale");

        DataPopulation dataPopulation = new DataPopulation();
        this.destinatario = dataPopulation.readDataPopulation(dpFile + ".yaml");

        DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
        String cf = destinatarioPASection.ricercaInformazione(destinatario.get("codiceFiscale").toString().split(","),0);

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);

        piattaformaNotifichePAPage.insertCodiceFiscale(cf);

    }

    @And("Cliccare sul bottone Filtra")
    public void cliccareSulBottoneFiltra() {
        logger.info("Si clicca sul tasto filtra");

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.selectFiltraButton();
    }

    @Then("Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice fiscale del destinatario {string}")
    public void nellaPaginaPiattaformaNotificheVengoRestituiteTutteLeNotificheConIlCodiceFiscaleDelDestinatario(String dpDestinatario) {
        logger.info("Si verifica i risultati restituiti");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.waitLoadPiattaformaNotifichePAPage();

        DataPopulation dataPopulation = new DataPopulation();
        this.destinatario = dataPopulation.readDataPopulation(dpDestinatario + ".yaml");

        String cfInserito = this.destinatario.get("codiceFiscale").toString();
        int listaCF = piattaformaNotifichePAPage.getListaCf(cfInserito);

        if (listaCF >= 1) {
            logger.info("Il codice fiscale della notifica è uguale a quello selezionato");

        } else {
            logger.error("Il codice fiscale della notifica NON sono uguali a quello selezionato");
            Assert.fail("Il codice fiscale notifica NON sono uguali a quello selezionato");
        }
    }

    @And("Nella pagina Piattaforma Notifiche i risultati sono contenuti in una o più pagine")
    public void nellaPaginaPiattaformaNotificheIRisultatiSonoContenutiInUnaOPiùPagine() {
        logger.info("Se i risultati sono contenuti in più pagine è possibile effettuare il cambio pagina");

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);

        if (piattaformaNotifichePAPage.verificaEsistenzaEPassaggioPagina()) {
            logger.info("Bottone pagina 2 trovato e cliccato");

            HeaderPASection headerPASection = new HeaderPASection(this.driver);
            headerPASection.waitLoadHeaderSection();
            piattaformaNotifichePAPage.waitLoadPiattaformaNotifichePAPage();

        } else {
            logger.info("Bottone pagina 2 NON trovato");
        }
    }

    @And("Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica {string}")
    public void nellaPaginaPiattaformaNotificheInserireIlCodiceIUNDellaNotifica(String dpDatiiNotifica) {
        logger.info("Si inserisce il codice IUN");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(dpDatiiNotifica + ".yaml");
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.inserimentoCodiceIUN(this.datiNotifica.get("codiceIUN").toString());
    }

    @Then("Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice IUN della notifica {string}")
    public void nellaPaginaPiattaformaNotificheVengoRestituiteTutteLeNotificheConIlCodiceIUNDellaNotifica(String dpDatiNotifica) {
        logger.info("Si verifica i risultati restituiti");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.waitLoadPiattaformaNotifichePAPage();
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(dpDatiNotifica + ".yaml");
        String codiceIUNInserito = datiNotifica.get("codiceIUN").toString();
        boolean result = piattaformaNotifichePAPage.verificaCodiceIUN(codiceIUNInserito);
        if (result) {
            logger.info("Il risultato coerente con il codice IUN inserito");
        } else {

            logger.error("Gli stati della notifica NON sono uguali a quelli selezionati");
            Assert.fail("Gli stati della notifica NON sono uguali a quelli selezionati");

        }
    }


    @And("^Nella pagina Piattaforma Notifiche inserire una data da (.*) a (.*)$")
    public void nellaPaginaPiattaformaNotificheInserireUnaDataDaDAAA(String dataDA, String dataA) {
        logger.info("Si inserisce l'arco temporale su cui effettuare la ricerca");

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        if (!piattaformaNotifichePAPage.controlloDateInserite(dataDA)) {
            logger.error("Formato della data DA  sbagliato. Deve essere dd/MM/yyyy");
            Assert.fail("Formato della data DA  sbagliato. Deve essere dd/MM/yyyy");
        }
        if (!piattaformaNotifichePAPage.controlloDateInserite(dataA)) {
            logger.error("Formato della data A  sbagliato. Deve essere dd/MM/yyyy");
            Assert.fail("Formato della data A  sbagliato. Deve essere dd/MM/yyyy");
        }
        piattaformaNotifichePAPage.inserimentoArcoTemporale(dataDA, dataA);

    }

    @Then("Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con la data della notifica compresa tra <da> e <a>")
    public void nellaPaginaPiattaformaNotificheVengoRestituiteTutteLeNotificheConLaDataDellaNotificaCompresaTraDaEA() {
        logger.info("Si verifica che le date restituite siano comprese nell'arco temporale");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.waitLoadPiattaformaNotifichePAPage();

        int results = piattaformaNotifichePAPage.getListDate();
        if (results >= 1) {
            logger.info("Sono presenti risultati per il filtro data");
        } else {
            logger.error("Le date delle notifiche NON sono uguali a quelle selezionate");
            Assert.fail("Le date delle notifiche NON sono uguali a quelle selezionate");
        }
    }

    @And("^Nella pagina piattaforma Notifiche selezionare uno stato notifica (.*)$")
    public void nellaPaginaPiattaformaNotificheSelezionareUnoStatoNotificaStato(String statoNotifica) {
        logger.info("Si seleziona lo stato: " + statoNotifica + "da casella");

        String statoInserito = statoNotifica;
        switch (statoInserito.toUpperCase()) {
            case "TUTI GLI STATI" -> statoInserito = "Tutti gli stati";
            case "DEPOSITATA" -> statoInserito = "Depositata";
            case "INVIO IN CORSO" -> statoInserito = "Invio in corso";
            case "CONSEGNATA" -> statoInserito = "Consegnata";
            case "PERFEZIONATA PER DECORRENZA TERMINI" -> statoInserito = "Perfezionata per decorrenza termini";
            case "PERFEZIONATA PER VISIONE" -> statoInserito = "Perfezionata per visione";
            case "ANNULLATA" -> statoInserito = "Annullata";
            case "DESTINATARIO IRREPERIBILE" -> statoInserito = "Destinatario irreperibile";
            default -> {
                logger.error("Stato notifica inserito non valido");
                Assert.fail("Stato notifica inserito non valido");
            }
        }

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.selezionareStatoNotifica(statoInserito);

    }

    @Then("^Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con lo stato della notifica(.*)$")
    public void nellaPaginaPiattaformaNotificheVengoRestituiteTutteLeNotificheConLoStatoDellaNotificaStato(String statoNotifica) {
        logger.info("Si controllano che gli stati delle notifiche siano uguali a quello selezionato");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);

        int numeroStatoNotifica = piattaformaNotifichePAPage.getListStato(statoNotifica.substring(1));

        if (numeroStatoNotifica >= 1) {
            logger.info("Gli stati della notifica sono uguali a quelli selezionati");
        }else {
            logger.error("Gli stati della notifica NON sono uguali a quelli selezionati");
            Assert.fail("Gli stati della notifica NON sono uguali a quelli selezionati");

        }

    }
}
