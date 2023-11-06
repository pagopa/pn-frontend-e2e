package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.NotificheDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.section.destinatario.personaFisica.HeadeFRSection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RicercaNotifichePersonaFisicaPATest {
        private static final Logger logger = LoggerFactory.getLogger("RicercaNotifichePersonaFisicaTest");

        private final WebDriver  driver = Hooks.driver;
    private Map<String, Object> datiNotifica = new HashMap<>();

    @When("Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica")
        public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheDestinatario() {
            logger.info("Verifica visualizzazione piattaforma notifiche persona fisica");
            HeadeFRSection headeFRSection = new HeadeFRSection(this.driver);
            headeFRSection.waitLoadHeaderDESection();

            NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
            notifichePFPage.waitLoadNotificheDEPage();
        }

    @And("Nella pagina Piattaforma Notifiche  persona fisica inserire il codice IUN da dati notifica {string}")
    public void nellaPaginaPiattaformaNotificheDestinatarioInserireIlCodiceIUNDaDatiNotifica(String dpDataNotifica) {
        logger.info("Si inserisce il codice IUN");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(dpDataNotifica+".yaml");
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadPage();

        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        notificheDestinatarioPage.inserisciCodiceIUN(this.datiNotifica.get("codiceIUN").toString());
    }

    @And("Cliccare sul bottone Filtra persona fisica")
    public void cliccareSulBottoneFiltra() {
        logger.info("Si clicca sul tasto filtra");

        NotifichePFPage NotifichePFPage = new NotifichePFPage(this.driver);
        NotifichePFPage.selectFiltraButton();
    }

    @Then("Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN della notifica {string}")
    public void nellaPaginaPiattaformaNotificheDestinatarioVengoRestituiteTutteLeNotificheConIlCodiceIUNDellaNotifica(String dpDatiNotifica) {
        logger.info("Si verifica i risultati restituiti");
        HeadeFRSection headeFRSection = new HeadeFRSection(this.driver);
        headeFRSection.waitLoadHeaderDESection();

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPage();

        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(dpDatiNotifica+".yaml");
        String codiceIUNInserito = datiNotifica.get("codiceIUN").toString();

        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        boolean result = notificheDestinatarioPage.verificaCodiceIUN(codiceIUNInserito);
        if (result){
            logger.info("Il risultato coerente con il codice IUN inserito");
        }else {
            logger.error("Il risultato NON coerente con il codice IUN inserito");
            Assert.fail("Il risultato NON coerente con il coodice IUN inserito");
        }
    }

    @And("Nella pagina Piattaforma Notifiche persona fisica inserire un arco temporale")
    public void nellaPaginaPiattaformaNotificheDestinatarioInserireUnaDataDaDAAA() {
        logger.info("Si inserisce l'arco temporale su cui effettuare ");
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        LocalDate dataFine = LocalDate.now();
        LocalDate dataInizio = dataFine.minusDays(5);
        String dataDA = notifichePFPage.controlloDateInserite(dataInizio.toString());
        String dataA = notifichePFPage.controlloDateInserite(dataFine.toString());
        notifichePFPage.inserimentoArcoTemporale(dataDA,dataA);
    }

    @Then("Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite")
    public void NellaPaginaPiattaformaNotificheDestinatarioVengoRestituiteTutteLeNotificheConLaDataDellaNotificaCompresaTraDaEA() {
        HeadeFRSection headeFRSection = new HeadeFRSection(this.driver);
        headeFRSection.waitLoadHeaderDESection();

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPage();
        boolean result = notifichePFPage.getListData();
        if (result){
            logger.info("Il risultato coerente con le date inserite");
        }else {
            logger.error("Il risultato NON coerente con le date inserite");
            Assert.fail("Il risultato NON coerente con le date inserite");
        }
    }

    @And("Se i risultati sono contenuti in più pagine persona fisica è possibile effettuare il cambio pagina")
    public void seIRisultatiSonoContenutiInPiuPagineDestinatarioEPossibileEffettuareIlCambioPagina() {
        logger.info("Se i risultati sono contenuti in più pagine è possibile effettuare il cambio pagina");

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);

        if(notifichePFPage.verificaEsistenzaEPassaggioPagina()){
            logger.info("Bottone pagina 2 trovato e cliccato");

            HeadeFRSection headeFRSection = new HeadeFRSection(this.driver);
            headeFRSection.waitLoadHeaderDESection();
            notifichePFPage.waitLoadNotificheDEPage();

        }else {
            logger.info("Bottone pagina 2 non trovato non effettuato il passaggio di pagina");
        }
    }


}
