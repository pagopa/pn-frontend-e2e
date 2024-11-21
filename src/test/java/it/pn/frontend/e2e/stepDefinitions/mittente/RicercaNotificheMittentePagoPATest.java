package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.DataPopulationConfig;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.PiattaformaNotifichePGPAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.mittente.DestinatarioPASection;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;
import it.pn.frontend.e2e.utility.DataPopulation;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RicercaNotificheMittentePagoPATest extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("NotificaMittenteTest");

//    private Map<String, Object> personaFisica = new HashMap<>();
//    private Map<String, Object> datiNotifica = new HashMap<>();
    private boolean dataFineErrata;

    private  DestinatarioPASection destinatarioPASection;

    private  PiattaformaNotifichePage piattaformaNotifichePage;

    private  PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage;

    private  HeaderPASection headerPASection;
    @Autowired
    private DataPopulationConfig dataPopulationConfig;


    @PostConstruct
    public void init(){
        logger.info("INIT TEST...: ");
        piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(driver);
        piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        headerPASection = new HeaderPASection(driver);
        destinatarioPASection = new DestinatarioPASection(driver);
    }

    @And("Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica {string}")
    public void inserireCodiceFiscale(String dpFile) {
        logger.info("Avvio ricerca tramite codice fiscale");
        //personaFisica
//        String cf = destinatarioPASection.ricercaInformazione(personaFisica.get("codiceFiscale").toString().split(","), 0);
        String cf = destinatarioPASection.ricercaInformazione(dataPopulationConfig.getPersonaFisica().getCodiceFiscale().split(","), 0);
        piattaformaNotifichePage.insertCodiceFiscale(cf);

    }

    @And("Cliccare sul bottone Filtra")
    public void cliccareSulBottoneFiltra() {
        logger.info("Si clicca sul tasto filtra");
        piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
    }

    @And("Si verifica che non ci sono notifiche disponibili")
    public void siVerificaCheNonCiSonoNotifiche() {
        logger.info("Si verifica che non ci sono notifiche disponibili con i fitri applicati");
        piattaformaNotifichePage.verificaNotificheNonDisponibili();
    }

    @And("Si visualizza correttamente box di pagamento")
    public void siVisualizzaBoxPagamento() {
        if (piattaformaNotifichePGPAPage.sezionePagamentoDisplayed()) {
            logger.info("Si visualizza correttamente box di pagamento");
        } else {
            logger.error("Non si visualizza correttamente box di pagamento");
            Assertions.fail("Non si visualizza correttamente box di pagamento");
        }
    }

    @And("Si visualizza correttamente il messaggio notifica annullata")
    public void siVisulizzaIlMessagioAnnullato() {
        if (piattaformaNotifichePGPAPage.messaggioNotificaAnnullataDisplayed()) {
            logger.info("Si visualizza correttamente il messaggio notifica annulata");
        } else {
            logger.error("Non si visualizza correttamente il messaggio notifica annulata");
            Assertions.fail("Non si visualizza correttamente il messaggio notifica annulata");
        }

    }


    @And("Si verifica che il bottone Filtra sia disabilitato")
    public void siVerificaCheIlBottoneFiltraSiaDisabilitato() {
        logger.info("Si verifica che il bottone Filtra sia disabilitato");

        if (piattaformaNotifichePage.isFiltraButtonDisabled()) {
            logger.info("Il bottone Filtra è disabilitato");
        } else {
            logger.error("Il bottone Filtra è abilitato");
            Assertions.fail("Il bottone Filtra è abilitato");
        }
    }

    @And("Cliccare sul bottone Filtra del delegato")
    public void cliccareSulBottoneFiltraDelDelegato() {
        logger.info("Si clicca sul tasto filtra del delegante sotto notifiche");

        piattaformaNotifichePage.selectFiltraNotificaButtonDestinatario();
    }

    @Then("Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice fiscale del destinatario {string}")
    public void nellaPaginaPiattaformaNotificheVengoRestituiteTutteLeNotificheConIlCodiceFiscaleDelDestinatario(String codiceFiscale) {
        logger.info("Si verifica i risultati restituiti");

        headerPASection.waitLoadHeaderSection();

        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
        int listaCF = piattaformaNotifichePage.getListaCf(codiceFiscale);

        if (listaCF >= 1) {
            logger.info("Il codice fiscale della notifica è uguale a quello selezionato");

        } else {
            logger.error("Codici fiscali non presenti o non uguali a quello selezionato " + codiceFiscale);
            Assertions.fail("Codici fiscali non presenti o non uguali a quello selezionato " + codiceFiscale);
        }
    }

    @And("Nella pagina Piattaforma Notifiche i risultati sono contenuti in una o più pagine")
    public void nellaPaginaPiattaformaNotificheIRisultatiSonoContenutiInUnaOPiuPagine() {
        logger.info("Se i risultati sono contenuti in più pagine è possibile effettuare il cambio pagina");

        if (piattaformaNotifichePage.verificaEsistenzaEPassaggioPagina()) {
            logger.info("Bottone pagina 2 trovato e cliccato");

            headerPASection.waitLoadHeaderSection();
            piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();

        } else {
            logger.info("Bottone pagina 2 NON trovato");
        }
    }

    @And("Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica")
    public void nellaPaginaPiattaformaNotificheInserireIlCodiceIUNDellaNotifica() {
        logger.info("Si inserisce il codice IUN");
        //"datiNotifica"
//        piattaformaNotifichePage.inserimentoCodiceIUN(this.datiNotifica.get("codiceIUN").toString());
        piattaformaNotifichePage.inserimentoCodiceIUN(dataPopulationConfig.getDatiNotifica().getCodiceIUN());
    }

    @Then("Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice IUN della notifica")
    public void nellaPaginaPiattaformaNotificheVengoRestituiteTutteLeNotificheConIlCodiceIUNDellaNotifica() {
        logger.info("Si verifica i risultati restituiti");
        headerPASection.waitLoadHeaderSection();

        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
        String codiceIUNInserito = piattaformaNotifichePage.getCodiceIunInserito();
        boolean result = piattaformaNotifichePage.verificaCodiceIUN(codiceIUNInserito);
        if (result) {
            logger.info("Notifica con codice IUN: " + codiceIUNInserito + " trovata correttamente");
        } else {

            logger.error("Notifica con codice IUN: " + codiceIUNInserito + " NON trovata");
            Assertions.fail("Notifica con codice IUN: " + codiceIUNInserito + " NON trovata");

        }
    }


    @And("^Nella pagina Piattaforma Notifiche inserire una data da (.*) a (.*)$")
    public void nellaPaginaPiattaformaNotificheInserireUnaDataDaDAAA(String dataDA, String dataA) {
        logger.info("Si inserisce l'arco temporale su cui effettuare la ricerca");

        if (!piattaformaNotifichePage.controlloDateInserite(dataDA)) {
            logger.error("Formato della data DA  sbagliato. Deve essere dd/MM/yyyy");
            Assertions.fail("Formato della data DA  sbagliato. Deve essere dd/MM/yyyy");
        }
        if (!piattaformaNotifichePage.controlloDateInserite(dataA)) {
            logger.error("Formato della data A  sbagliato. Deve essere dd/MM/yyyy");
            Assertions.fail("Formato della data A  sbagliato. Deve essere dd/MM/yyyy");
        }
        piattaformaNotifichePage.inserimentoArcoTemporale(dataDA, dataA);
    }


    @And("^Nella pagina Piattaforma Notifiche inserire un arco temporale errato da (.*) a (.*)$")
    public void nellaPaginaPiattaformaNotificheInserireUnaDataDaDAAAErrata(String dataDA, String dataA) {
        logger.info("Si inserisce l'arco temporale su cui effettuare la ricerca");

        if (!piattaformaNotifichePage.controlloDateInserite(dataDA)) {
            logger.error("Formato della data DA  sbagliato. Deve essere dd/MM/yyyy");
            Assertions.fail("Formato della data DA  sbagliato. Deve essere dd/MM/yyyy");
        }
        if (!piattaformaNotifichePage.controlloDateInserite(dataA)) {
            logger.error("Formato della data A  sbagliato. Deve essere dd/MM/yyyy");
            Assertions.fail("Formato della data A  sbagliato. Deve essere dd/MM/yyyy");
        }
        dataFineErrata =  piattaformaNotifichePage.inserimentoArcoTemporaleErrato(dataDA, dataA);
    }

    @And("Verifica che non è possibile selezionare una data Fine antecedente alla data Inizio")
    public void verificaArcoTemporaleSelezionato() {
        logger.info("Si controlla l'arco temporale che sia errato su cui effettuare la ricerca");
        Assertions.assertFalse(dataFineErrata);
    }


    @Then("Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con la data della notifica compresa tra <da> e <a>")
    public void nellaPaginaPiattaformaNotificheVengoRestituiteTutteLeNotificheConLaDataDellaNotificaCompresaTraDaEA() {
        logger.info("Si verifica che le date restituite siano comprese nell'arco temporale");

        headerPASection.waitLoadHeaderSection();

        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();

        int results = piattaformaNotifichePage.getListDate();
        if (results >= 1) {
            logger.info("Sono presenti risultati per il filtro data");
        } else {
            logger.error("Le date delle notifiche NON sono uguali a quelle selezionate");
            Assertions.fail("Le date delle notifiche NON sono uguali a quelle selezionate");
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
                Assertions.fail("Stato notifica inserito non valido");
            }
        }

        piattaformaNotifichePage.selezionareStatoNotifica(statoInserito);

    }

    @Then("^Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con lo stato della notifica(.*)$")
    public void nellaPaginaPiattaformaNotificheVengoRestituiteTutteLeNotificheConLoStatoDellaNotificaStato(String statoNotifica) {
        logger.info("Si controllano che gli stati delle notifiche siano uguali a quello selezionato");

        headerPASection.waitLoadHeaderSection();

        int numeroStatoNotifica = piattaformaNotifichePage.getListStato(statoNotifica.substring(1));

        if (numeroStatoNotifica >= 1) {
            logger.info("Gli stati della notifica sono uguali a quelli selezionati");
        } else {
            logger.error("Gli stati della notifica NON sono uguali a quelli selezionati");
            Assertions.fail("Gli stati della notifica NON sono uguali a quelli selezionati");

        }

    }

    @And("Nella pagina Piattaforma Notifiche inserire la data invio notifica")
    public void nellaPaginaPiattaformaNotificheInserireLaDataInvioNotifica() {
        logger.info("Inserimento data invio notifica");

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String dataNotifica = dateFormat.format(date).replace("-", "/");

        piattaformaNotifichePage.inserimentoArcoTemporale(dataNotifica, dataNotifica);
    }

    @And("Nella pagina piattaforma Notifiche selezionare lo stato notifica Depositata")
    public void nellaPaginaPiattaformaNotificheSelezionareLoStatoNotifica() {
        logger.info("Si seleziona lo stato notifica Depositata");

        piattaformaNotifichePage.selezionareStatoNotifica("ACCEPTED");
    }


    @And("Nella pagina Piattaforma Notifiche inserire un arco temporale")
    public void nellaPaginaPiattaformaNotificheInserireUnArcoTemporale() {
        LocalDate dateA = LocalDate.now();
        LocalDate dateDa = dateA.minusDays(35);

        String dataa = piattaformaNotifichePage.conversioneFormatoDate(dateA.toString());
        String datada = piattaformaNotifichePage.conversioneFormatoDate(dateDa.toString());
        piattaformaNotifichePage.inserimentoArcoTemporale(datada, dataa);
    }

    @And("Il sistema restituisce notifiche con codice fiscale e arco temporale uguale a quelli inserito")
    public void ilSistemaRestituisceNotificheConCodiceFiscaleEArcoTemporaleUgualeAQuelliInserito() {
        logger.info("Si verifica i risultati restituiti");
;
        headerPASection.waitLoadHeaderSection();

        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();

       // DataPopulation dataPopulation = new DataPopulation();
       // this.personaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");

        String cfInserito = dataPopulationConfig.getPersonaFisica().getCodiceFiscale();
        int listaCF = piattaformaNotifichePage.getListaCf(cfInserito);

        if (listaCF >= 1) {
            logger.info("Il codice fiscale della notifica è uguale a quello selezionato");

        } else {
            logger.error("Il codice fiscale della notifica NON è uguale a quello selezionato");
            Assertions.fail("Il codice fiscale notifica NON è uguale a quello selezionato");
        }

        int results = piattaformaNotifichePage.controlloNumeroRisultatiDate();
        if (results >= 1) {
            logger.info("Sono presenti risultati per il filtro data");
        } else {
            logger.error("Le date delle notifiche NON sono uguali a quelle selezionate");
            Assertions.fail("Le date delle notifiche NON sono uguali a quelle selezionate");
        }
    }

    @And("Nella pagina Piattaforma Notifiche inserire una data")
    public void nellaPaginaPiattaformaNotificheInserireUnaData() {
        LocalDate data = LocalDate.now();

        String dataInserita = piattaformaNotifichePage.conversioneFormatoDate(data.toString());
        piattaformaNotifichePage.inserimentoData(dataInserita);
    }


    @And("^Il sistema restituisce notifiche con data e stato uguale a quelli inserito (.*)$")
    public void ilSistemaRestituisceNotificheConDataEStatoUgualeAQuelliInseritoStato(String statoNotifica) {
        logger.info("Si verifica i risultati restituiti");

        headerPASection.waitLoadHeaderSection();

        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();

        int numeroStatoNotificha = piattaformaNotifichePage.getListStato(statoNotifica);

        if (numeroStatoNotificha >= 1) {
            logger.info("Gli stati della notifica sono uguali a quelli selezionati");
        } else {

            logger.error("Gli stati della notifica NON sono uguali a quelli selezionati");
            Assertions.fail("Gli stati della notifica NON sono uguali a quelli selezionati");
        }

        int results = piattaformaNotifichePage.controlloNumeroRisultatiDate();
        if (results >= 1) {
            logger.info("Sono presenti risultati per il filtro data");
        } else {
            logger.error("Le date delle notifiche NON sono uguali a quelle selezionate");
            Assertions.fail("Le date delle notifiche NON sono uguali a quelle selezionate");
        }
    }

    @And("^Il sistema restituisce notifiche con arco temporale e stato uguale a quelli inserito (.*)$")
    public void ilSistemaRestituisceNotificheConArcoTemporaleEStatoUgualeAQuelliInseritoStato(String statoNotifica) {
        logger.info("Si verifica i risultati restituiti");

        headerPASection.waitLoadHeaderSection();
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
        int results = piattaformaNotifichePage.controlloNumeroRisultatiDate();
        if (results >= 1) {
            logger.info("Sono presenti risultati per il filtro data");
        } else {
            logger.error("Le date delle notifiche NON sono uguali a quelle selezionate");
            Assertions.fail("Le date delle notifiche NON sono uguali a quelle selezionate");
        }
        int numeroStatoNotificha = piattaformaNotifichePage.getListStato(statoNotifica);

        if (numeroStatoNotificha >= 1) {
            logger.info("Gli stati della notifica sono uguali a quelli selezionati");
        } else {

            logger.error("Gli stati della notifica NON sono uguali a quelli selezionati");
            Assertions.fail("Gli stati della notifica NON sono uguali a quelli selezionati");
        }
    }

    @And("Il sistema non restituisce notifiche")
    public void ilSistemaNonRestituisceNotifiche() {
        if (piattaformaNotifichePage.verificaEsistenzaRisultati()) {
            logger.info("Il filtro non ha nessun risultato");
        } else {
            logger.error("Il filtro ha portate qualche risultato");
            Assertions.fail("Il filtro ha portate qualche risultato");
        }
    }

    @And("Il sistema restituisce notifiche con codice fiscale e data uguale a quelli inserito")
    public void ilSistemaRestituisceNotificheConCodiceFiscaleEDataUgualeAQuelliInserito() {
        logger.info("Si verifica i risultati restituiti");

        headerPASection.waitLoadHeaderSection();

        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();

        //DataPopulation dataPopulation = new DataPopulation();
        //this.personaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");

        String cfInserito = dataPopulationConfig.getPersonaFisica().getCodiceFiscale();
        int listaCF = piattaformaNotifichePage.getListaCf(cfInserito);

        if (listaCF >= 1) {
            logger.info("Il codice fiscale della notifica è uguale a quello selezionato");

        } else {
            logger.error("Il codice fiscale della notifica NON è uguale a quello selezionato");
            Assertions.fail("Il codice fiscale notifica NON è uguale  a quello selezionato");
        }

        int results = piattaformaNotifichePage.controlloNumeroRisultatiDate();
        if (results >= 1) {
            logger.info("Sono presenti risultati per il filtro data");
        } else {
            logger.error("Le date delle notifiche NON sono uguali a quelle selezionate");
            Assertions.fail("Le date delle notifiche NON sono uguali a quelle selezionate");
        }
    }

    @And("^Il sistema restituisce notifiche con codice fiscale e stato uguale a quelli inserito (.*)$")
    public void ilSistemaRestituisceNotificheConCodiceFiscaleEStatoUgualeAQuelliInseritoStato(String statoNotifica) {
        logger.info("Si verifica i risultati restituiti");

        headerPASection.waitLoadHeaderSection();

        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();

        //DataPopulation dataPopulation = new DataPopulation();
        //this.personaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");

        String cfInserito = dataPopulationConfig.getPersonaFisica().getCodiceFiscale();
        int listaCF = piattaformaNotifichePage.getListaCf(cfInserito);

        if (listaCF >= 1) {
            logger.info("Il codice fiscale della notifica è uguale a quello selezionato");

        } else {
            logger.error("Il codice fiscale della notifica NON è uguale  a quello selezionato");
            Assertions.fail("Il codice fiscale notifica NON è uguale  a quello selezionato");
        }
        int numeroStatoNotificha = piattaformaNotifichePage.getListStato(statoNotifica);

        if (numeroStatoNotificha >= 1) {
            logger.info("Gli stati della notifica sono uguali a quelli selezionati");
        } else {

            logger.error("Gli stati della notifica NON sono uguali a quelli selezionati");
            Assertions.fail("Gli stati della notifica NON sono uguali a quelli selezionati");
        }
    }

    @And("Nella pagina piattaforma Notifiche selezionare lo stato notifica {string}")
    public void nellaPaginaPiattaformaNotificheSelezionareLoStatoNotifica(String statoInserito) {

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
                Assertions.fail("Stato notifica inserito non valido");
            }
        }
        piattaformaNotifichePage.selezionareStatoNotifica(statoInserito);
    }

    @And("Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica {string} con allegato")
    public void nellaPaginaPiattaformaNotificheInserireIlCodiceIUNDellaNotificaConAllegato(String codiceIUN) {
        piattaformaNotifichePage.inserimentoCodiceIUN(codiceIUN);
    }

    @And("Si verifica che i campi della ricerca delle date siano errate")
    public void siVerificaCheICampiDellaRicercaDelleDateSianoErrate() {
        if (piattaformaNotifichePage.controlloDateErrate()) {
            logger.info("Le date inserite sono errate");
        } else {
            logger.error("Le date inserite sono corrette");
            Assertions.fail("Le date inserite sono corrette");
        }
    }

    @Then("Nella pagina piattaforma Notifiche è presente un campo di ricerca con un menu a tendina per selezionare lo stato della notifica")
    public void nellaPaginaPiattaformaNotificheÈPresenteUnCampoDiRicercaConUnMenuATendinaPerSelezionareLoStatoDellaNotifica() {
        if (piattaformaNotifichePage.controlloEsistenzaStato()) {
            logger.info("Campo stato notifica trovato");
        } else {
            logger.error("Campo stato notifica NON trovato");
            Assertions.fail("Campo stato notifica NON trovato");
        }
    }

    @And("Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN {string}")
    public void nellaPaginaPiattformaNotificheSiEffettuaLaRicercaPerCodiceIUN(String codiceIUN) {
        logger.info("Si cerca una notifica tramite IUN: {}", codiceIUN);
        piattaformaNotifichePage.inserimentoCodiceIUN(codiceIUN);
        piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
    }


    @And("Si clicca la notifica ricercata")
    public void siCliccaLaNotificaRicercata() {
        logger.info("Se presente si clicca la notifica ricercata");
        piattaformaNotifichePage.clickNotificaRicercata();
    }

    @And("Si clicca sul bottone vedi tutti")
    public void siCliccaVediTutti() {
        logger.info("Si clicca sul bottone vedi tutti");
        piattaformaNotifichePage.clickVediTutti();
    }
}

