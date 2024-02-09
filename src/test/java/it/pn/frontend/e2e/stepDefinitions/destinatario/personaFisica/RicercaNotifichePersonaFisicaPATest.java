package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.NotificheDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.section.destinatario.personaFisica.HeaderPFSection;
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
    private final WebDriver driver = Hooks.driver;
    private Map<String, Object> datiNotifica = new HashMap<>();
    private Map<String, Object> datiNotificaNonValidoPF;

    @When("Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheDestinatario() {
        logger.info("Verifica visualizzazione piattaforma notifiche persona fisica");
        HeaderPFSection headerPFSection = new HeaderPFSection(this.driver);
        headerPFSection.waitLoadHeaderDESection();

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPage();
    }

    @And("Nella pagina Piattaforma Notifiche  persona fisica inserire il codice IUN da dati notifica {string}")
    public void nellaPaginaPiattaformaNotificheDestinatarioInserireIlCodiceIUNDaDatiNotifica(String dpDataNotifica) throws InterruptedException {
        logger.info("Si inserisce il codice IUN");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(dpDataNotifica + ".yaml");
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
        logger.info("Si verificano i risultati restituiti");
        HeaderPFSection headerPFSection = new HeaderPFSection(this.driver);
        headerPFSection.waitLoadHeaderDESection();

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPage();

        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(dpDatiNotifica + ".yaml");
        String codiceIUNInserito = datiNotifica.get("codiceIUN").toString();

        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        boolean result = notificheDestinatarioPage.verificaCodiceIUN(codiceIUNInserito);
        if (result) {
            logger.info("Il risultato é coerente con il codice IUN inserito");
        } else {
            logger.error("Il risultato NON é coerente con il codice IUN inserito");
            Assert.fail("Il risultato NON é coerente con il coodice IUN inserito");
        }
    }

    @And("Nella pagina Piattaforma Notifiche persona fisica inserire un arco temporale")
    public void nellaPaginaPiattaformaNotificheDestinatarioInserireUnaDataDaDAAA() {
        logger.info("Si inserisce l'arco temporale su cui effettuare la ricerca ");
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        LocalDate dataFine = LocalDate.of(2023,11, 9);
        LocalDate dataInizio = LocalDate.of(2023,11, 7);
        String dataDA = notifichePFPage.controlloDateInserite(dataInizio.toString());
        String dataA = notifichePFPage.controlloDateInserite(dataFine.toString());
        notifichePFPage.inserimentoArcoTemporale(dataDA, dataA);
    }

    @Then("Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite")
    public void nellaPaginaPiattaformaNotificheDestinatarioVengoRestituiteTutteLeNotificheConLaDataDellaNotificaCompresaTraDaEA() {
        HeaderPFSection headerPFSection = new HeaderPFSection(this.driver);
        headerPFSection.waitLoadHeaderDESection();

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPage();
        boolean result = notifichePFPage.getListData();
        if (result) {
            logger.info("Il risultato é coerente con le date inserite");
        } else {
            logger.error("Il risultato NON é coerente con le date inserite");
            Assert.fail("Il risultato NON é coerente con le date inserite");
        }
    }

    @And("Se i risultati sono contenuti in più pagine persona fisica è possibile effettuare il cambio pagina")
    public void seIRisultatiSonoContenutiInPiuPagineDestinatarioEPossibileEffettuareIlCambioPagina() {
        logger.info("Se i risultati sono contenuti in più pagine è possibile effettuare il cambio pagina");

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);

        if (notifichePFPage.verificaEsistenzaEPassaggioPagina()) {
            logger.info("Bottone pagina 2 trovato e cliccato");

            HeaderPFSection headerPFSection = new HeaderPFSection(this.driver);
            headerPFSection.waitLoadHeaderDESection();
            notifichePFPage.waitLoadNotificheDEPage();
        } else {
            logger.info("Bottone pagina 2 non trovato non effettuato il passaggio di pagina");
        }
    }

    @And("Cliccare sul bottone Rimuovi filtri persona fisica")
    public void cliccareSulBottoneRimuoviFiltriPersonaFisica() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.clickRimuoviFiltriButton();
    }

    @And("Nella pagina Piattaforma Notifiche  persona fisica inserire il codice IUN non valido da dati notifica {string}")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaInserireIlCodiceIunNonValidoDaDatiNotifica(String datiNotificaNonValidoPF) throws InterruptedException {
        logger.info("Si inserisce il codice IUN non valido");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotificaNonValidoPF = dataPopulation.readDataPopulation(datiNotificaNonValidoPF + ".yaml");
        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        notificheDestinatarioPage.inserisciCodiceIUN(this.datiNotificaNonValidoPF.get("codiceIUN").toString());
    }

    @Then("Nella pagina Piattaforma Notifiche persona fisica viene visualizzato un messaggio in rosso di errore sotto il campo errato e il rettangolo diventa rosso e il tasto Filtra è disattivo")
    public void NellaPaginaPiattaformaNotifichePersonaFisicaVieneVisualizzatoUnMessaggioInRossoDiErroreSottoIlCampoErratoEIlRettangoloDiventaRossoEIlTastoFiltraEDisattivo() {

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);

        boolean isErrorMessageDisplayed = notifichePFPage.isErrorMessageDisplayed();

        if (isErrorMessageDisplayed) {
            logger.info("il messaggio di errore é visualizzato");
        } else {
            logger.error("il messaggio di errore non é visualizzato");
            Assert.fail("il messaggio di errore non é visualizzato");
        }

        boolean isTextBoxInValid = notifichePFPage.isTextBoxInvalid();

        if (isTextBoxInValid) {
            logger.info("IUN text box non é valido");
        } else {
            logger.error("IUN text box non é passato allo stato non valido");
            Assert.fail("IUN text box non é passato allo stato non valido");

        }

        notifichePFPage.clickFiltraButton();
        boolean isErrorMessageStillDisplayed = notifichePFPage.isErrorMessageDisplayed();
        if (isErrorMessageStillDisplayed) {
            logger.info("Il bottone Filtra é dissativato");
        } else {
            logger.error("Il bottone Filtra é attivo");
            Assert.fail("Il bottone Filtra é attivo");
        }
    }


}
