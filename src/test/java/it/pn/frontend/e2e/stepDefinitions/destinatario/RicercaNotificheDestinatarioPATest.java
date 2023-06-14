package it.pn.frontend.e2e.stepDefinitions.destinatario;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.NotificheDEPage;
import it.pn.frontend.e2e.section.destinatario.HeaderDESection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RicercaNotificheDestinatarioPATest {
        private static final Logger logger = LoggerFactory.getLogger("RicercaNotificheDestinatarioTest");

        private final WebDriver  driver = Hooks.driver;
    private Map<String, Object> datiNotifica = new HashMap<>();

    @When("Si visualizza correttamente la pagina Piattaforma Notifiche Destinatario")
        public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheDestinatario() {
            logger.info("Verifica visualizzazione piattaforma notifiche destinatario");
            HeaderDESection headerDESection = new HeaderDESection(this.driver);
            headerDESection.waitLoadHeaderDESection();

            NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
            notificheDEPage.waitLoadNotificheDEPage();
        }

    @And("Nella pagina Piattaforma Notifiche  Destinatario inserire il codice IUN da dati notifica {string}")
    public void nellaPaginaPiattaformaNotificheDestinatarioInserireIlCodiceIUNDaDatiNotifica(String dpDataNotifica) {
        logger.info("Si inserisce il codice IUN");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(dpDataNotifica+".yaml");
        NotificheDEPage NotificheDEPage = new NotificheDEPage(this.driver);
        NotificheDEPage.inserisciCodiceIUN(this.datiNotifica.get("codiceIUN").toString());
    }

    @And("Cliccare sul bottone Filtra Destinatario")
    public void cliccareSulBottoneFiltra() {
        logger.info("Si clicca sul tasto filtra");

        NotificheDEPage NotificheDEPage = new NotificheDEPage(this.driver);
        NotificheDEPage.selectFiltraButton();
    }

    @Then("Nella pagina Piattaforma Notifiche Destinatario vengo restituite tutte le notifiche con il codice IUN della notifica {string}")
    public void nellaPaginaPiattaformaNotificheDestinatarioVengoRestituiteTutteLeNotificheConIlCodiceIUNDellaNotifica(String dpDatiNotifica) {
        logger.info("Si verifica i risultati restituiti");
        HeaderDESection headerDESection = new HeaderDESection(this.driver);
        headerDESection.waitLoadHeaderDESection();

        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.waitLoadNotificheDEPage();

        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(dpDatiNotifica+".yaml");
        String codiceIUNInserito = datiNotifica.get("codiceIUN").toString();
        boolean result = notificheDEPage.verificaCodiceIUN(codiceIUNInserito);
        if (result){
            logger.info("Il risultato coerente con il codice IUN inserito");
        }else {
            logger.error("Il risultato NON coerente con il codice IUN inserito");
            Assert.fail("Il risultato NON coerente con il coodice IUN inserito");
        }
    }

    @And("Nella pagina Piattaforma Notifiche Destinatario inserire un arco temporale")
    public void nellaPaginaPiattaformaNotificheDestinatarioInserireUnaDataDaDAAA() {
        logger.info("Si inserisce l'arco temporale su cui effettuare ");
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        LocalDate dataFine = LocalDate.now();
        LocalDate dataInizio = dataFine.minusDays(5);
        String dataDA = notificheDEPage.controlloDateInserite(dataInizio.toString());
        String dataA = notificheDEPage.controlloDateInserite(dataFine.toString());
        notificheDEPage.inserimentoArcoTemporale(dataDA,dataA);
    }

    @Then("Nella pagina Piattaforma Notifiche Destinatario vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite")
    public void NellaPaginaPiattaformaNotificheDestinatarioVengoRestituiteTutteLeNotificheConLaDataDellaNotificaCompresaTraDaEA() {
        HeaderDESection headerDESection = new HeaderDESection(this.driver);
        headerDESection.waitLoadHeaderDESection();

        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.waitLoadNotificheDEPage();
        boolean result = notificheDEPage.getListData();
        if (result){
            logger.info("Il risultato coerente con le date inserite");
        }else {
            logger.error("Il risultato NON coerente con le date inserite");
            Assert.fail("Il risultato NON coerente con le date inserite");
        }
    }

    @And("Se i risultati sono contenuti in più pagine destinatario è possibile effettuare il cambio pagina")
    public void seIRisultatiSonoContenutiInPiuPagineDestinatarioEPossibileEffettuareIlCambioPagina() {
        logger.info("Se i risultati sono contenuti in più pagine è possibile effettuare il cambio pagina");

        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);

        if(notificheDEPage.verificaEsistenzaEPassaggioPagina()){
            logger.info("Bottone pagina 2 trovato e cliccato");

            HeaderDESection headerDESection = new HeaderDESection(this.driver);
            headerDESection.waitLoadHeaderDESection();
            notificheDEPage.waitLoadNotificheDEPage();

        }else {
            logger.info("Bottone pagina 2 non trovato non effettuato il passaggio di pagina");
        }
    }
}
