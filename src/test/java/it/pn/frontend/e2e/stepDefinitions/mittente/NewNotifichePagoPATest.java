package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.model.notification.NewNotificationRequest;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.utility.DataPopulation;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

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

    @And("Seleziona Avviso PagoPA")
    public void selezionaAvvisoPagoPA() {
        destinatarioPage.selezionaAvvisoPagoPA();
    }

    @And("Seleziona Avviso PagoPA add Modello F24")
    public void selezionaAvvisoPagoPAaddModelloF24() {
        destinatarioPage.selezionaAvvisoPagoPAaddModelloF24();
    }

    @And("Seleziona Modello F24")
    public void selezionaModelloF24() {
        destinatarioPage.selezionaModelloF24();
    }

    @And("Cliccare su Sincrona")
    public void cliccareSuSincrona() {
        destinatarioPage.cliccareSuSincrona();
    }

    @And("Inserisco Codice Avviso")
    public void inseriscoCodiceAvviso() {
        destinatarioPage.inseriscoCodiceAvviso();
    }

    @And("Cliccare su A carico del destinatario")
    public void cliccareSuACaricoDelDestinatario() {
        destinatarioPage.cliccareSuACaricoDelDestinatario();
    }

    @And("Inserire Costo notifica {string}")
    public void inserireCostoNotifica(String costo) {
        destinatarioPage.inserireCostoNotifica(costo);
    }

    @And("Selezionare la percentuale {string}")
    public void selezionareLaPercentuale(String percentuale) {
        destinatarioPage.selezionareLaPercentuale(percentuale);
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

    @And("Seleziona Nessun Pagamento")
    public void selezionaNessunPagamento() {
        destinatarioPage.selezionaNessunPagamento();
    }
    @And("Seleziona Incluso Nell Atto")
    public void selezionaInclusoNellAtto() {
        destinatarioPage.selezionaInclusoNellAtto();
    }
    @And("Seleziona Modo Sincrono")
    public void selezionaModoSincrono() {
        destinatarioPage.selezionaModoSincrono();
    }
    @And("Seleziona Modo Asincrono")
    public void selezionaModoAsincrono() {
        destinatarioPage.selezionaModoAsincrono();
    }
}