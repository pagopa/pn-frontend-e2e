package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.PiattaformaNotifichePGPAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.mittente.DestinatarioPASection;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;
import it.pn.frontend.e2e.stepDefinitions.common.SharedSteps;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RicercaNotificheMittentePagoPATest {

    private static final Logger logger = LoggerFactory.getLogger("NotificaMittenteTest");

    private final WebDriver driver = Hooks.driver;
    private Map<String, Object> personaFisica = new HashMap<>();
    private Map<String, Object> datiNotifica = new HashMap<>();
    SharedSteps sharedSteps = new SharedSteps();

    @And("Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica {string}")
    public void inserireCodiceFiscale(String codiceFiscale) {
        logger.info("Avvio ricerca tramite codice fiscale");

        DataPopulation dataPopulation = new DataPopulation();
        //this.personaFisica = dataPopulation.readDataPopulation(dpFile + ".yaml");

        DestinatarioPASection destinatarioPASection = new DestinatarioPASection(this.driver);
        //String cf = destinatarioPASection.ricercaInformazione(personaFisica.get("codiceFiscale").toString().split(","), 0);

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);

        piattaformaNotifichePage.insertCodiceFiscale(codiceFiscale);

    }

    @And("Cliccare sul bottone Filtra")
    public void cliccareSulBottoneFiltra() {
        logger.info("Si clicca sul tasto filtra");
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
    }

    @And("Si verifica che non ci sono notifiche disponibili")
    public void siVerificaCheNonCiSonoNotifiche() {
        logger.info("Si verifica che non ci sono notifiche disponibili con i fitri applicati");
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.verificaNotificheNonDisponibili();
    }

    @And("Si visualizza correttamente box di pagamento")
    public void siVisualizzaBoxPagamento() {
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        if (piattaformaNotifichePGPAPage.sezionePagamentoDisplayed()) {
            logger.info("Si visualizza correttamente box di pagamento");
        } else {
            logger.error("Non si visualizza correttamente box di pagamento");
            Assert.fail("Non si visualizza correttamente box di pagamento");
        }
    }

    @And("Si visualizza correttamente il messaggio notifica annullata")
    public void siVisulizzaIlMessagioAnnullato() {
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        if (piattaformaNotifichePGPAPage.messaggioNotificaAnnullataDisplayed()) {
            logger.info("Si visualizza correttamente il messaggio notifica annulata");
        } else {
            logger.error("Non si visualizza correttamente il messaggio notifica annulata");
            Assert.fail("Non si visualizza correttamente il messaggio notifica annulata");
        }

    }


    @And("Si verifica che il bottone Filtra sia disabilitato")
    public void siVerificaCheIlBottoneFiltraSiaDisabilitato() {
        logger.info("Si verifica che il bottone Filtra sia disabilitato");

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        if (piattaformaNotifichePage.isFiltraButtonDisabled()) {
            logger.info("Il bottone Filtra è disabilitato");
        } else {
            logger.error("Il bottone Filtra è abilitato");
            Assert.fail("Il bottone Filtra è abilitato");
        }
    }

    @And("Cliccare sul bottone Filtra del delegato")
    public void cliccareSulBottoneFiltraDelDelegato() {
        logger.info("Si clicca sul tasto filtra del delegante sotto notifiche");

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.selectFiltraNotificaButtonDestinatario();
    }

    @Then("Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice fiscale del destinatario {string}")
    public void nellaPaginaPiattaformaNotificheVengoRestituiteTutteLeNotificheConIlCodiceFiscaleDelDestinatario(String codiceFiscale) {
        logger.info("Si verifica i risultati restituiti");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
        int listaCF = piattaformaNotifichePage.getListaCf(codiceFiscale);

        if (listaCF >= 1) {
            logger.info("Il codice fiscale della notifica è uguale a quello selezionato");

        } else {
            logger.error("Codici fiscali non presenti o non uguali a quello selezionato " + codiceFiscale);
            Assert.fail("Codici fiscali non presenti o non uguali a quello selezionato " + codiceFiscale);
        }
    }

    @And("Nella pagina Piattaforma Notifiche i risultati sono contenuti in una o più pagine")
    public void nellaPaginaPiattaformaNotificheIRisultatiSonoContenutiInUnaOPiuPagine() {
        logger.info("Se i risultati sono contenuti in più pagine è possibile effettuare il cambio pagina");

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);

        if (piattaformaNotifichePage.verificaEsistenzaEPassaggioPagina()) {
            logger.info("Bottone pagina 2 trovato e cliccato");

            HeaderPASection headerPASection = new HeaderPASection(this.driver);
            headerPASection.waitLoadHeaderSection();
            piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();

        } else {
            logger.info("Bottone pagina 2 NON trovato");
        }
    }

    @And("Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica {string}")
    public void nellaPaginaPiattaformaNotificheInserireIlCodiceIUNDellaNotifica(String dpDatiNotifica) {
        logger.info("Si inserisce il codice IUN");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(dpDatiNotifica + ".yaml");
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.inserimentoCodiceIUN(this.datiNotifica.get("codiceIUN").toString());
    }


    @Then("Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice IUN della notifica")
    public void nellaPaginaPiattaformaNotificheVengoRestituiteTutteLeNotificheConIlCodiceIUNDellaNotifica() {
        logger.info("Si verifica i risultati restituiti");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
        String codiceIUNInserito = piattaformaNotifichePage.getCodiceIunInserito();
        boolean result = piattaformaNotifichePage.verificaCodiceIUN(codiceIUNInserito);
        if (result) {
            logger.info("Notifica con codice IUN: " + codiceIUNInserito + " trovata correttamente");
        } else {

            logger.error("Notifica con codice IUN: " + codiceIUNInserito + " NON trovata");
            Assert.fail("Notifica con codice IUN: " + codiceIUNInserito + " NON trovata");

        }
    }


    @And("^Nella pagina Piattaforma Notifiche inserire una data da (.*) a (.*)$")
    public void nellaPaginaPiattaformaNotificheInserireUnaDataDaDAAA(String dataDA, String dataA) {
        logger.info("Si inserisce l'arco temporale su cui effettuare la ricerca");

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        if (!piattaformaNotifichePage.controlloDateInserite(dataDA)) {
            logger.error("Formato della data DA  sbagliato. Deve essere dd/MM/yyyy");
            Assert.fail("Formato della data DA  sbagliato. Deve essere dd/MM/yyyy");
        }
        if (!piattaformaNotifichePage.controlloDateInserite(dataA)) {
            logger.error("Formato della data A  sbagliato. Deve essere dd/MM/yyyy");
            Assert.fail("Formato della data A  sbagliato. Deve essere dd/MM/yyyy");
        }
        piattaformaNotifichePage.inserimentoArcoTemporale(dataDA, dataA);
    }

    @Then("Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con la data della notifica compresa tra <da> e <a>")
    public void nellaPaginaPiattaformaNotificheVengoRestituiteTutteLeNotificheConLaDataDellaNotificaCompresaTraDaEA() {
        logger.info("Si verifica che le date restituite siano comprese nell'arco temporale");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();

        int results = piattaformaNotifichePage.getListDate();
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
            case "TUTI GLI STATI" -> statoInserito = "All";
            case "DEPOSITATA" -> statoInserito = "ACCEPTED";
            case "INVIO IN CORSO" -> statoInserito = "DELIVERING";
            case "CONSEGNATA" -> statoInserito = "DELIVERED";
            case "PERFEZIONATA PER DECORRENZA TERMINI" -> statoInserito = "EFFECTIVE_DATE";
            case "AVVENUTO ACCESSO" -> statoInserito = "VIEWED";
            case "ANNULLATA" -> statoInserito = "CANCELLED";
            case "DESTINATARIO IRREPERIBILE" -> statoInserito = "UNREACHABLE";
            default -> {
                logger.error("Stato notifica inserito non valido");
                Assert.fail("Stato notifica inserito non valido");
            }
        }

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.selezionareStatoNotifica(statoInserito);

    }

    @Then("^Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con lo stato della notifica(.*)$")
    public void nellaPaginaPiattaformaNotificheVengoRestituiteTutteLeNotificheConLoStatoDellaNotificaStato(String statoNotifica) {
        logger.info("Si controllano che gli stati delle notifiche siano uguali a quello selezionato");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);

        int numeroStatoNotifica = piattaformaNotifichePage.getListStato(statoNotifica.substring(1));

        if (numeroStatoNotifica >= 1) {
            logger.info("Gli stati della notifica sono uguali a quelli selezionati");
        } else {
            logger.error("Gli stati della notifica NON sono uguali a quelli selezionati");
            Assert.fail("Gli stati della notifica NON sono uguali a quelli selezionati");

        }

    }

    @And("Nella pagina Piattaforma Notifiche inserire la data invio notifica")
    public void nellaPaginaPiattaformaNotificheInserireLaDataInvioNotifica() {
        logger.info("Inserimento data invio notifica");

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String dataNotifica = dateFormat.format(date).replace("-", "/");

        piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
    }

    @And("Nella pagina piattaforma Notifiche selezionare lo stato notifica Depositata")
    public void nellaPaginaPiattaformaNotificheSelezionareLoStatoNotifica() {
        logger.info("Si seleziona lo stato notifica Depositata");

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
    }


    @And("Nella pagina Piattaforma Notifiche inserire un arco temporale")
    public void nellaPaginaPiattaformaNotificheInserireUnArcoTemporale() {
        LocalDate dateA = LocalDate.now();
        LocalDate dateDa = dateA.minusDays(5);

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        String dataa = piattaformaNotifichePage.conversioneFormatoDate(dateA.toString());
        String datada = piattaformaNotifichePage.conversioneFormatoDate(dateDa.toString());
        piattaformaNotifichePage.inserimentoArcoTemporale(datada, dataa);
    }

    @And("Il sistema restituisce notifiche con codice fiscale e arco temporale uguale a quelli inserito")
    public void ilSistemaRestituisceNotificheConCodiceFiscaleEArcoTemporaleUgualeAQuelliInserito() {
        logger.info("Si verifica i risultati restituiti");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();

        DataPopulation dataPopulation = new DataPopulation();
        this.personaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");

        String cfInserito = this.personaFisica.get("codiceFiscale").toString();
        int listaCF = piattaformaNotifichePage.getListaCf(cfInserito);

        if (listaCF >= 1) {
            logger.info("Il codice fiscale della notifica è uguale a quello selezionato");

        } else {
            logger.error("Il codice fiscale della notifica NON è uguale a quello selezionato");
            Assert.fail("Il codice fiscale notifica NON è uguale a quello selezionato");
        }

        int results = piattaformaNotifichePage.controlloNumeroRisultatiDate();
        if (results >= 1) {
            logger.info("Sono presenti risultati per il filtro data");
        } else {
            logger.error("Le date delle notifiche NON sono uguali a quelle selezionate");
            Assert.fail("Le date delle notifiche NON sono uguali a quelle selezionate");
        }
    }

    @And("Nella pagina Piattaforma Notifiche inserire una data")
    public void nellaPaginaPiattaformaNotificheInserireUnaData() {
        LocalDate data = LocalDate.now();

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        String dataInserita = piattaformaNotifichePage.conversioneFormatoDate(data.toString());
        piattaformaNotifichePage.inserimentoData(dataInserita);
    }


    @And("^Il sistema restituisce notifiche con data e stato uguale a quelli inserito (.*)$")
    public void ilSistemaRestituisceNotificheConDataEStatoUgualeAQuelliInseritoStato(String statoNotifica) {
        logger.info("Si verifica i risultati restituiti");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();

        int numeroStatoNotificha = piattaformaNotifichePage.getListStato(statoNotifica);

        if (numeroStatoNotificha >= 1) {
            logger.info("Gli stati della notifica sono uguali a quelli selezionati");
        } else {

            logger.error("Gli stati della notifica NON sono uguali a quelli selezionati");
            Assert.fail("Gli stati della notifica NON sono uguali a quelli selezionati");
        }

        int results = piattaformaNotifichePage.controlloNumeroRisultatiDate();
        if (results >= 1) {
            logger.info("Sono presenti risultati per il filtro data");
        } else {
            logger.error("Le date delle notifiche NON sono uguali a quelle selezionate");
            Assert.fail("Le date delle notifiche NON sono uguali a quelle selezionate");
        }
    }

    @And("^Il sistema restituisce notifiche con arco temporale e stato uguale a quelli inserito (.*)$")
    public void ilSistemaRestituisceNotificheConArcoTemporaleEStatoUgualeAQuelliInseritoStato(String statoNotifica) {
        logger.info("Si verifica i risultati restituiti");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
        int results = piattaformaNotifichePage.controlloNumeroRisultatiDate();
        if (results >= 1) {
            logger.info("Sono presenti risultati per il filtro data");
        } else {
            logger.error("Le date delle notifiche NON sono uguali a quelle selezionate");
            Assert.fail("Le date delle notifiche NON sono uguali a quelle selezionate");
        }
        int numeroStatoNotificha = piattaformaNotifichePage.getListStato(statoNotifica);

        if (numeroStatoNotificha >= 1) {
            logger.info("Gli stati della notifica sono uguali a quelli selezionati");
        } else {

            logger.error("Gli stati della notifica NON sono uguali a quelli selezionati");
            Assert.fail("Gli stati della notifica NON sono uguali a quelli selezionati");
        }
    }

    @And("Il sistema non restituisce notifiche")
    public void ilSistemaNonRestituisceNotifiche() {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        if (piattaformaNotifichePage.verificaEsistenzaRisultati()) {
            logger.info("Il filtro non ha nessun risultato");
        } else {
            logger.error("Il filtro ha portate qualche risultato");
            Assert.fail("Il filtro ha portate qualche risultato");
        }
    }

    @And("Il sistema restituisce notifiche con codice fiscale e data uguale a quelli inserito")
    public void ilSistemaRestituisceNotificheConCodiceFiscaleEDataUgualeAQuelliInserito() {
        logger.info("Si verifica i risultati restituiti");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();

        DataPopulation dataPopulation = new DataPopulation();
        this.personaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");

        String cfInserito = this.personaFisica.get("codiceFiscale").toString();
        int listaCF = piattaformaNotifichePage.getListaCf(cfInserito);

        if (listaCF >= 1) {
            logger.info("Il codice fiscale della notifica è uguale a quello selezionato");

        } else {
            logger.error("Il codice fiscale della notifica NON è uguale a quello selezionato");
            Assert.fail("Il codice fiscale notifica NON è uguale  a quello selezionato");
        }

        int results = piattaformaNotifichePage.controlloNumeroRisultatiDate();
        if (results >= 1) {
            logger.info("Sono presenti risultati per il filtro data");
        } else {
            logger.error("Le date delle notifiche NON sono uguali a quelle selezionate");
            Assert.fail("Le date delle notifiche NON sono uguali a quelle selezionate");
        }
    }

    @And("^Il sistema restituisce notifiche con codice fiscale e stato uguale a quelli inserito (.*)$")
    public void ilSistemaRestituisceNotificheConCodiceFiscaleEStatoUgualeAQuelliInseritoStato(String statoNotifica) {
        logger.info("Si verifica i risultati restituiti");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();

        DataPopulation dataPopulation = new DataPopulation();
        this.personaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");

        String cfInserito = this.personaFisica.get("codiceFiscale").toString();
        int listaCF = piattaformaNotifichePage.getListaCf(cfInserito);

        if (listaCF >= 1) {
            logger.info("Il codice fiscale della notifica è uguale a quello selezionato");

        } else {
            logger.error("Il codice fiscale della notifica NON è uguale  a quello selezionato");
            Assert.fail("Il codice fiscale notifica NON è uguale  a quello selezionato");
        }
        int numeroStatoNotificha = piattaformaNotifichePage.getListStato(statoNotifica);

        if (numeroStatoNotificha >= 1) {
            logger.info("Gli stati della notifica sono uguali a quelli selezionati");
        } else {

            logger.error("Gli stati della notifica NON sono uguali a quelli selezionati");
            Assert.fail("Gli stati della notifica NON sono uguali a quelli selezionati");
        }
    }

    @And("Nella pagina piattaforma Notifiche selezionare lo stato notifica {string}")
    public void nellaPaginaPiattaformaNotificheSelezionareLoStatoNotifica(String statoInserito) {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);

        switch (statoInserito.toUpperCase()) {
            case "TUTI GLI STATI" -> statoInserito = "All";
            case "DEPOSITATA" -> statoInserito = "ACCEPTED";
            case "INVIO IN CORSO" -> statoInserito = "DELIVERING";
            case "CONSEGNATA" -> statoInserito = "DELIVERED";
            case "PERFEZIONATA PER DECORRENZA TERMINI" -> statoInserito = "EFFECTIVE_DATE";
            case "AVVENUTO ACCESSO" -> statoInserito = "VIEWED";
            case "ANNULLATA" -> statoInserito = "CANCELLED";
            case "DESTINATARIO IRREPERIBILE" -> statoInserito = "UNREACHABLE";
            default -> {
                logger.error("Stato notifica inserito non valido");
                Assert.fail("Stato notifica inserito non valido");
            }
        }
        piattaformaNotifichePage.selezionareStatoNotifica(statoInserito);
    }

    @And("Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica {string} con allegato")
    public void nellaPaginaPiattaformaNotificheInserireIlCodiceIUNDellaNotificaConAllegato(String codiceIUN) {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.inserimentoCodiceIUN(codiceIUN);
    }

    @And("Si verifica che i campi della ricerca delle date siano errate")
    public void siVerificaCheICampiDellaRicercaDelleDateSianoErrate() {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        if (piattaformaNotifichePage.controlloDateErrate()) {
            logger.info("Le date inserite sono errate");
        } else {
            logger.error("Le date inserite sono corrette");
            Assert.fail("Le date inserite sono corrette");
        }
    }

    @Then("Nella pagina piattaforma Notifiche è presente un campo di ricerca con un menu a tendina per selezionare lo stato della notifica")
    public void nellaPaginaPiattaformaNotificheÈPresenteUnCampoDiRicercaConUnMenuATendinaPerSelezionareLoStatoDellaNotifica() {
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        if (piattaformaNotifichePage.controlloEsistenzaStato()) {
            logger.info("Campo stato notifica trovato");
        } else {
            logger.error("Campo stato notifica NON trovato");
            Assert.fail("Campo stato notifica NON trovato");
        }
    }

    @And("Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN {string}")
    public void nellaPaginaPiattformaNotificheSiEffettuaLaRicercaPerCodiceIUN(String codiceIUN) {
        logger.info("Si cerca una notifica tramite IUN: {}", codiceIUN);
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.inserimentoCodiceIUN(codiceIUN);
        piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
    }


    @And("Si clicca la notifica ricercata")
    public void siCliccaLaNotificaRicercata() {
        logger.info("Se presente si clicca la notifica ricercata");
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.clickNotificaRicercata();
    }

    @And("Si clicca sul bottone vedi tutti")
    public void siCliccaVediTutti() {
        logger.info("Si clicca sul bottone vedi tutti");
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.clickVediTutti();
    }
}

