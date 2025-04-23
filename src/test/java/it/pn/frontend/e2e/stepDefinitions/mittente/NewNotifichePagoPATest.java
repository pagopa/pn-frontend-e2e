package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.DataPopulationConfig;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.model.notification.NewNotificationRequest;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.utility.DataPopulation;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class NewNotifichePagoPATest extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("NewNotifichePagoPATest");

    private final String FILE_TOKEN_LOGIN = "tokenLogin.yaml";

    @Value("${environment}")
    private String environmentParam;

    @Autowired
    private DataPopulation dataPopulation;
    @Autowired
    private DataPopulationConfig dataPopulationConfig;

    private DestinatarioPage destinatarioPage;

    @Autowired
    @Lazy
    private NewNotificationRequest notificationRequest;

    @Autowired
    private NotificationSingleton notificationSingleton;

    @Autowired
    private RestNotification restNotification;
    @Autowired
    private HooksNew hooksNew;


    @PostConstruct
    public void init(){
        logger.info("INIT TEST...: ");
        destinatarioPage = new DestinatarioPage(driver);
        destinatarioPage.setNotificationRequest(notificationRequest);
        destinatarioPage.setNotificationSingleton(notificationSingleton);
        destinatarioPage.setRestNotification(restNotification);
    }


    @Then("Creo in background una notifica per destinatario tramite API REST")
    public void creoUnaNotificaPerDestinatarioTramiteAPIREST(){
        destinatarioPage.setHooksNew(hooksNew);
        destinatarioPage.checkCreateNewNotification();
    }

    @And("Si aggiunge un destinatario alla notifica")
    public void siAggiungeUnDestinatarioAllaNotifica(Map<String, String> datiDestinatario) {
        destinatarioPage.aggiuntaDestinatarioANotifica(datiDestinatario);
    }

    @When("Si inizializzano i dati per la notifica")
    public void siInizializzanoIDatiPerLaNotifica(Map<String, String> datiNotifica) {
        destinatarioPage.inizializzazioneDatiNotifica(datiNotifica);
    }

    @And("Si completa percorso RADD")
    public void siCompletaPercorsoRADD(Map<String,String> datiDestinatario) {
        String environment = environmentParam;
        String token = "";
        switch (environment) {
            case "dev" -> {
                /* TODO */
            }
            case "test" ->
                    token = dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokentestRaddista1").toString();
            default -> {
                log.error("Ambiente non valido");
                Assertions.fail("Ambiente non valido o non trovato!");
            }
        }
        String operationId = UUID.randomUUID().toString();
        destinatarioPage.raddFlow(token,datiDestinatario.get("tipoDestinatario"), datiDestinatario.get("codiceFiscale"),operationId);
    }

    @And("Seleziona Avviso PagoPA {int}")
    public void selezionaAvvisoPagoPA(int numeroPosizioneDebitoria) {
        destinatarioPage.selezionaAvvisoPagoPA( numeroPosizioneDebitoria);
    }

    @And("Seleziona Avviso PagoPA add Modello F24 {int}")
    public void selezionaAvvisoPagoPAaddModelloF24(int numeroPosizioneDebitoria) {
        destinatarioPage.selezionaAvvisoPagoPAaddModelloF24( numeroPosizioneDebitoria);
    }

    @And("Seleziona Modello F24 {int}")
    public void selezionaModelloF24(int numeroPosizioneDebitoria) {
        destinatarioPage.selezionaModelloF24( numeroPosizioneDebitoria);
    }

    @And("Verifica Avviso PagoPA {int}")
    public void verificaAvvisoPagoPA(int numeroPosizioneDebitoria) {
        destinatarioPage.verificaAvvisoPagoPA(numeroPosizioneDebitoria);
    }

    @And("Verifica Avviso PagoPA add Modello F24 {int}")
    public void verificaAvvisoPagoPAaddModelloF24(int numeroPosizioneDebitoria) {
        destinatarioPage.verificaAvvisoPagoPAaddModelloF24( numeroPosizioneDebitoria);
    }

    @And("Verifica Modello F24 {int}")
    public void verificaModelloF24(int numeroPosizioneDebitoria) {
        destinatarioPage.verificaModelloF24( numeroPosizioneDebitoria);
    }

    @And("Inserire Tutti Codice Avviso")
    public void inserireTuttiCodiceAvviso() {
        List<String> codiciAvvisi = destinatarioPage.inserireTuttiCodiceAvviso();
        dataPopulationConfig.getDatiNotifica().setCodiciAvvisi(codiciAvvisi);
    }
    @And("Inserire Tutti Codice Fiscale Ente")
    public void inserireTuttiCodiceFiscaleEnte() {
        destinatarioPage.inserireTuttiCodiceFiscaleEnte();
    }
    @And("Verifica Tutti Codice Fiscale Ente")
    public void verificaTuttiCodiceFiscaleEnte() {
        destinatarioPage.verificaTuttiCodiceFiscaleEnte();
    }

    @And("Inserire Tutti Codice Avviso Errati")
    public void inserireTuttiCodiceAvvisoErrati() {
        destinatarioPage.inserireTuttiCodiceAvvisoErrati();
    }
    @And("Inserire Tutti Codice Fiscale Ente Errati")
    public void inserireTuttiCodiceFiscaleEnteErrati() {
        destinatarioPage.inserireTuttiCodiceFiscaleEnteErrati();
    }

    @And("Si verifica la presenza della sezione Posizione Debitoria")
    public void siVerificaPresenzaSezionePosizioneDebitoria() {
        logger.info("Si verifica la presenza della sezione Posizione Debitoria");
        destinatarioPage.verificaPresenzaSezionePosizioneDebitoria();
    }

    @And("Si verifica la presenza della sezione Dettaglio Posizione Debitoria")
    public void siVerificaPresenzaSezioneDettaglioPosizioneDebitoria() {
        logger.info("Si verifica la presenza della sezione DettaglioPosizione Debitoria");
        destinatarioPage.verificaPresenzaSezioneDettaglioPosizioneDebitoria();
    }

    @And("Seleziona Nessun Pagamento {int}")
    public void selezionaNessunPagamento(int numeroPosizioneDebitoria) {
        destinatarioPage.selezionaNessunPagamento(numeroPosizioneDebitoria);
    }

    @And("Verifica Nessun Pagamento {int}")
    public void verificaNessunPagamento(int numeroPosizioneDebitoria) {
        destinatarioPage.verificaNessunPagamento(numeroPosizioneDebitoria);
    }

    @And("Seleziona Incluso Nell Atto {int}")
    public void selezionaInclusoNellAtto(int numeroPosizioneDebitoria) {
        destinatarioPage.selezionaInclusoNellAtto(numeroPosizioneDebitoria);
    }

    @And("Seleziona A Carico del Destinatario {int}")
    public void selezionaACaricoDelDestinatario(int numeroPosizioneDebitoria) {
        destinatarioPage.selezionaACaricoDelDestinatario(numeroPosizioneDebitoria);
    }

    @And("Seleziona Modo Sincrono {int}")
    public void selezionaModoSincrono(int numeroPosizioneDebitoria) {
        destinatarioPage.selezionaModoSincrono(numeroPosizioneDebitoria);
    }

    @And("Seleziona Modo Asincrono {int}")
    public void selezionaModoAsincrono(int numeroPosizioneDebitoria) {
        destinatarioPage.selezionaModoAsincrono( numeroPosizioneDebitoria);
    }

    @And("Verifica Presenza Sezione Tecnologia Pagamento Avviso PagoPA")
    public void verificaPresenzaSezioneTecnologiaPagamentoPagoPA() {
        Assertions.assertTrue(destinatarioPage.verificaPresenzaSezioneTecnologiaPagamentoAvvisoPagoPA());
    }

    @And("Verifica Assenza Sezione Tecnologia Pagamento Avviso PagoPA")
    public void verificaAssenzaSezioneTecnologiaPagamentoAvvisoPagoPA() {
        Assertions.assertFalse(destinatarioPage.verificaPresenzaSezioneTecnologiaPagamentoAvvisoPagoPA());
    }

    @And("Inserire Costo di notifica")
    public void inserireCostoDiNotifica() {
        destinatarioPage.inserireCostoDiNotifica();
    }

    @And("Inserire IVA")
    public void inserireIVA() {
        destinatarioPage.inserireIVA();
    }

    @And("Verifica Presenza Sezione Specifiche Avviso PagoPA {int}")
    public void verificaPresenzaSezioneSpecifichePagamentoPagoPA(int numeroPosizioneDebitoria) {
        Assertions.assertTrue(destinatarioPage.verificaPresenzaSezioneSpecificheAvvisoPagoPAPerValore(numeroPosizioneDebitoria));
    }

    @And("Verifica Assenza Sezione Specifiche Avviso PagoPA {int}")
    public void verificaAssenzaSezioneSpecifichePagamentoPagoPA(int numeroPosizioneDebitoria) {
        Assertions.assertFalse(destinatarioPage.verificaPresenzaSezioneSpecificheAvvisoPagoPAPerValore(numeroPosizioneDebitoria));
    }

    @And("Verifica Presenza Sezione Specifiche Modello F24 {int}")
    public void verificaPresenzaSezioneSpecificheModelloF24(int numeroPosizioneDebitoria) {
        Assertions.assertTrue(destinatarioPage.verificaPresenzaSezioneSpecificheModelloF24PerValore(numeroPosizioneDebitoria));
    }

    @And("Verifica Assenza Sezione Specifiche Modello F24 {int}")
    public void verificaAssenzaSezioneSpecificheModelloF24(int numeroPosizioneDebitoria) {
        Assertions.assertFalse(destinatarioPage.verificaPresenzaSezioneSpecificheModelloF24PerValore(numeroPosizioneDebitoria));
    }

    @And("Seleziona Applica Costo Notifica")
    public void selezionaApplicaCostoNotifica() {
        destinatarioPage.selezionaApplicaCostoNotifica();
    }


    @And("Click Su Aggiungi Altro Modello F24 {int}")
    public void clickSuAggiungiAltroModelloF24(int posizione) {
        destinatarioPage.clickSuAggiungiAltroModelloF24(posizione);
    }

//    @And("Click Su Aggiungi Codice Di Avviso PagoPa")
//    public void clickSuAggiungiCodiceDiAvvisoPagoPa() {
//        destinatarioPage.clickSuAggiungiCodiceDiAvvisoPagoPa();
//    }

    @And("Click Su Aggiungi Codice Di Avviso PagoPa {int}")
    public void clickSuAggiungiCodiceDiAvvisoPagoPa(int posizione) {
        destinatarioPage.clickSuAggiungiCodiceDiAvvisoPagoPa(posizione);
    }


    @And("Inserisci Titolo Documento Posizione Debitoria {int}")
    public void inserisciTitoloDocumentoPosizioneDebitoria(int numNotifiche) {
        destinatarioPage.inserisciTitoloDocumentoPosizioneDebitoria(numNotifiche);
    }


    @And("Click Su Aggiungi un altro documento")
    public void clickSuAggiungiUnAltroDocumento() {
        destinatarioPage.clickSuAggiungiUnAltroDocumento();
    }

    @And("Verifica Codici Avvisi")
    public void verificaCodiciAvvisi() {
        destinatarioPage.verificaCodiciAvvisi(dataPopulationConfig.getDatiNotifica().getCodiciAvvisi());

    }
}