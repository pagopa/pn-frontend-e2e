package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.NotificheDestinatarioPage;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.PiattaformaNotifichePGPAPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.RicercaNotifichePGPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.HeaderPGSection;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Map;

public class RicercaNotifichePersonaGiuridicaPATest {
    private final Logger logger = LoggerFactory.getLogger("RicercaNotifichePersonaGiuridicaPATest");

    @Autowired
    private HooksNew hooks;

    @Autowired
    private PiattaformaNotifichePage piattaformaNotifichePage;
    @Autowired
    private RicercaNotifichePGPage ricercaNotifichePGPage;
    @Autowired
    private DestinatarioPage destinatarioPage;
    @Autowired
    private NotificheDestinatarioPage notificheDestinatarioPage;
    @Autowired
    private PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage;
    @Autowired
    private HeaderPASection headerPASection;
    @Autowired
    private HeaderPGSection headerPGSection;
    @Autowired
    private NotifichePFPage notifichePFPage;

    private Map<String, Object> datiNotificaPG;

    @When("Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa")
    public void cliccareSuNotificheDellImpresa() {
        logger.info("Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa");
        ricercaNotifichePGPage.clickNotificheImpresa();
    }

    @And("Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN da dati notifica {string}")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaInserireIlCodiceIUNDaDatiNotifica(String iun) throws InterruptedException {
        logger.info("Si inserisce il codice IUN");
        notificheDestinatarioPage.inserisciCodiceIUN(iun);
    }

    @When("La persona giuridica clicca sulla notifica restituita {string}")
    public void ilPersonaGiuridicaCliccaSullaNotificaRestituita(String iun) {
        ricercaNotifichePGPage.cliccaNotificaRestituita(iun);
    }

    @And("La persona giuridica clicca sulla prima notifica restituita")
    public void laPersonaGiuridicaCliccaSullaPrimaNotificaRestituita() {
        ricercaNotifichePGPage.cliccaSuPrimaNotifica();
    }

    @And("Si visualizza correttamente la section Dettaglio Notifica persona giuridica delegato")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotificaPersonaGiuridicaDelegato() {
        ricercaNotifichePGPage.waitLoadDettaglioNotificaPGDelegato();
    }

    @And("Si controlla se la notifica prevede il pagamento")
    public void siControllaSeLaNotificaPrevedeIlPagamento() {
        boolean sezionePagamentoIsDisplayed = piattaformaNotifichePGPAPage.sezionePagamentoDisplayed();
        if (!sezionePagamentoIsDisplayed) {
            logger.info("La notifica non prevede il pagamento ");
        } else {
            logger.info("La notifica prevede il pagamento");
            boolean radioBoxPresent = piattaformaNotifichePGPAPage.isRadioBoxPresent();

            if (radioBoxPresent) {
                piattaformaNotifichePGPAPage.clickRadioBoxButton();
            }

            boolean titoloPagamentoIsDisplayed = piattaformaNotifichePGPAPage.titoloDiPagamentoDisplayed();
            if (titoloPagamentoIsDisplayed) {
                logger.info("Sezione titolo di pagamento è visualizzato");
            } else {
                logger.error("Sezione titolo di pagamento non è visualizzato");
                Assertions.fail("Sezione titolo di pagamento non è visualizzato");
            }

            boolean codiceAvvisoIsDisplayed = piattaformaNotifichePGPAPage.codiceAvvisoDisplayed();
            if (codiceAvvisoIsDisplayed) {
                logger.info("Sezione codice avviso è visualizzato");
            } else {
                logger.error("Sezione codice avviso non è visualizzato");
                Assertions.fail("Sezione codice avviso non è visualizzato");
            }

            boolean modelloF24IsDisplayed = piattaformaNotifichePGPAPage.modelloF24Displayed();
            if (modelloF24IsDisplayed) {
                logger.info("Sezione scarica modello F24 è visualizzato");
            } else {
                logger.error("Sezione scarica modello F24 non è visualizzato");
                Assertions.fail("Sezione scarica modello F24 non è visualizzato");
            }
        }
    }

    @And("Cliccare sul bottone Filtra persona giuridica")
    public void cliccareSulBottoneFiltraPersonaGiuridica() {
        ricercaNotifichePGPage.clickFiltraButton();
    }

    @And("Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaVengoRestituiteTutteLeNotificheConIlCodiceIUNDellaNotifica(Map<String, String> datiPG) {
        logger.info("Si verifica i risultati restituiti");

        String iun = datiPG.get("iun");
        String ragioneSociale = datiPG.get("ragioneSociale");
        headerPASection.waitLoadHeaderSection();
        piattaformaNotifichePGPAPage.waitLoadPiattaformaNotificaPage(ragioneSociale);
        boolean result = notificheDestinatarioPage.verificaCodiceIUN(iun);
        if (result) {
            logger.info("Il risultato é coerente con il codice IUN inserito");
        } else {
            logger.error("Gli stati della notifica NON sono uguali a quelli selezionati");
            Assertions.fail("Gli stati della notifica NON sono uguali a quelli selezionati");
        }
    }

    @And("Nella pagina Piattaforma Notifiche persona giuridica inserire un arco temporale")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaInserireUnArcoTemporale() {
        LocalDate dateA = LocalDate.now();
        LocalDate dateDa = dateA.minusDays(5);

        String dataa = piattaformaNotifichePage.conversioneFormatoDate(dateA.toString());
        String datada = piattaformaNotifichePage.conversioneFormatoDate(dateDa.toString());
        piattaformaNotifichePage.inserimentoArcoTemporale(datada, dataa);
    }

    @And("Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaVengoRestituiteTutteLeNotificheConLaDataDellaNotificaCompresaConLeDatePrecedentementeInserite() {
        headerPGSection.waitLoadHeaderPGPage();
        piattaformaNotifichePGPAPage.waitLoadPiattaformaNotificaPage("Convivio Spa");
        boolean result = notifichePFPage.getListData();
        if (result) {
            logger.info("Il risultato é coerente con le date inserite");
        } else {
            logger.error("Il risultato NON é coerente con le date inserite");
            Assertions.fail("Il risultato NON é coerente con le date inserite");
        }
    }

    @And("Cliccare sul bottone Rimuovi filtri persona giuridica")
    public void cliccareSulBottoneRimuoviFiltriPersonaGiuridica() {
        ricercaNotifichePGPage.clickRimuoviFiltriButton();
    }

    @And("Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN non valido da dati notifica {string}")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaInserireIlCodiceIunNonValidoDaDatiNotifica(String datiNotificaNonValidoPG) throws InterruptedException {
        logger.info("Si inserisce il codice IUN non valido");
        notificheDestinatarioPage.inserisciCodiceIUN(datiNotificaNonValidoPG);
    }

    @Then("Viene visualizzato un messaggio in rosso di errore sotto il campo errato e il rettangolo diventa rosso e il tasto Filtra è disattivo")
    public void vieneVisualizzatoUnMessaggioInRossoDiErroreSottoIlCampoErratoEIlRettangoloDiventaRossoEIlTastoFiltraEDisattivo() {
        boolean isErrorMessageDisplayed = ricercaNotifichePGPage.isErrorMessageDisplayed();
        if (isErrorMessageDisplayed) {
            logger.info("il messaggio di errore é visualizzato");
        } else {
            logger.error("il messaggio di errore non é visualizzato");
            Assertions.fail("il messaggio di errore non é visualizzato");
        }

        boolean isTextBoxInValid = notificheDestinatarioPage.isTextBoxInvalid();
        if (isTextBoxInValid) {
            logger.info("IUN text box non é valido");
        } else {
            logger.error("IUN text box non é passato allo stato non valido");
            Assertions.fail("IUN text box non é passato allo stato non valido");
        }
        ricercaNotifichePGPage.clickFiltraButton();
        boolean isErrorMessageStillDisplayed = ricercaNotifichePGPage.isErrorMessageDisplayed();
        if (isErrorMessageStillDisplayed) {
            logger.info("Il bottone Filtra é dissativato");
        } else {
            logger.error("Il bottone Filtra é attivo");
            Assertions.fail("Il bottone Filtra é attivo");
        }
    }

    @And("Se i risultati sono contenuti in più pagine persona giuridica è possibile effettuare il cambio pagina")
    public void seIRisultatiSonoContenutiInPiuPagineDestinatarioEPossibileEffettuareIlCambioPagina() {
        logger.info("Se i risultati sono contenuti in più pagine è possibile effettuare il cambio pagina");
        if (piattaformaNotifichePage.verificaEsistenzaEPassaggioPagina()) {
            logger.info("Bottone pagina 2 trovato e cliccato");
            headerPGSection.waitLoadHeaderPGPage();
            ricercaNotifichePGPage.waitLoadNotifichePGPage();
        } else {
            logger.info("Bottone pagina 2 non trovato non effettuato il passaggio di pagina");
        }
    }

    @And("Nella pagina Piattaforma Notifiche destinatario si inserisce una data con formato errato")
    public void nellaPaginaPiattaformaNotificheDestinatarioSiInserisceUnaDataConFormatoErrato() {
        logger.info("Si inserisce un formato data non valido in entrambi i campi per ricerca notifica");
        destinatarioPage.inserimentoDataErrato();
    }

    @Then("Il rettangolo del campo errato diventa rosso e il tasto Filtra è disattivo")
    public void ilRettangoloDelCampoErratoDiventaRossoEIlTastoFiltraèDisattivo() {
        destinatarioPage.isDateBoxInvalid();
        ricercaNotifichePGPage.clickFiltraButton();
        boolean isDateBoxStillInvalid = destinatarioPage.isDateBoxInvalid();
        if (isDateBoxStillInvalid) {
            logger.info("Il bottone Filtra é disattivato");
        } else {
            logger.error("Il bottone Filtra é attivo");
            Assertions.fail("Il bottone Filtra é attivo");
        }
    }
}