package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.NotificheDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.PiattaformaNotifichePGPAPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.RicercaNotifichePGPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.HeaderPGSection;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Map;

public class RicercaNotifichePersonaGiuridicaPATest {
    private final Logger logger = LoggerFactory.getLogger("RicercaNotifichePersonaGiuridicaPATest");
    private final WebDriver driver = Hooks.driver;
    private final PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
    RicercaNotifichePGPage ricercaNotifichePGPage = new RicercaNotifichePGPage(this.driver);
    DestinatarioPage destinatarioPage = new DestinatarioPage(this.driver);
    private Map<String, Object> datiNotificaPG;

    @When("Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa")
    public void cliccareSuNotificheDellImpresa() {
        logger.info("Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa");

        ricercaNotifichePGPage.clickNotificheImpresa();
    }

    @And("Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN da dati notifica {string}")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaInserireIlCodiceIUNDaDatiNotifica(String datiNotificaPG) throws InterruptedException {
        logger.info("Si inserisce il codice IUN");

        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotificaPG = dataPopulation.readDataPopulation(datiNotificaPG + ".yaml");
        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        notificheDestinatarioPage.inserisciCodiceIUN(this.datiNotificaPG.get("codiceIUN").toString());
    }

    @When("La persona giuridica clicca sulla notifica restituita {string}")
    public void ilPersonaGiuridicaCliccaSullaNotificaRestituita(String dpFile) {
        DataPopulation dataPopulation = new DataPopulation();
        String codiceIUNPG = dataPopulation.readDataPopulation(dpFile + ".yaml").get("codiceIUN").toString();
        ricercaNotifichePGPage.cliccaNotificaRestituita(codiceIUNPG);
    }

    @And("La persona giuridica clicca sulla prima notifica restituita")
    public void laPersonaGiuridicaCliccaSullaPrimaNotificaRestituita() {
        RicercaNotifichePGPage ricercaNotifichePGPage = new RicercaNotifichePGPage(this.driver);
        ricercaNotifichePGPage.cliccaSuPrimaNotifica();
    }

    @And("Si visualizza correttamente la section Dettaglio Notifica persona giuridica")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotificaPersonaGiuridica() {
        ricercaNotifichePGPage.waitLoadDettaglioNotificaPGSection();
    }

    @And("Si visualizza correttamente la section Dettaglio Notifica persona giuridica delegato")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotificaPersonaGiuridicaDelegato() {
        ricercaNotifichePGPage.waitLoadDettaglioNotificaPGDelegato();
    }



    @And("Si controlla se la notifica prevede il pagamento")
    public void siControllaSeLaNotificaPrevedeIlPagamento() {

        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);

        boolean sezionePagamentoIsDisplayed = piattaformaNotifichePGPAPage.sezionePagamentoDisplayed();

        if (!sezionePagamentoIsDisplayed) {
            logger.info("La notifica non prevede il pagamento ");
        } else {
            logger.info("La notifica prevede il pagamento");
            boolean radioBoxPresent = piattaformaNotifichePGPAPage.isRadioBoxPresent();

            if (radioBoxPresent) {

                piattaformaNotifichePGPAPage.clickRadioBoxButton(piattaformaNotifichePGPAPage.cssBuildRadioButton());
            }

            boolean titoloPagamentoIsDisplayed = piattaformaNotifichePGPAPage.titoloDiPagamentoDisplayed();
            if (titoloPagamentoIsDisplayed) {
                logger.info("Sezione titolo di pagamento è visualizzato");
            } else {
                logger.error("Sezione titolo di pagamento non è visualizzato");
                Assert.fail("Sezione titolo di pagamento non è visualizzato");
            }

            boolean codiceAvvisoIsDisplayed = piattaformaNotifichePGPAPage.codiceAvvisoDisplayed();
            if (codiceAvvisoIsDisplayed) {
                logger.info("Sezione codice avviso è visualizzato");
            } else {
                logger.error("Sezione codice avviso non è visualizzato");
                Assert.fail("Sezione codice avviso non è visualizzato");
            }

            boolean modelloF24IsDisplayed = piattaformaNotifichePGPAPage.modelloF24Displayed();
            if (modelloF24IsDisplayed) {
                logger.info("Sezione scarica modello F24 è visualizzato");
            } else {
                logger.error("Sezione scarica modello F24 non è visualizzato");
                Assert.fail("Sezione scarica modello F24 non è visualizzato");
            }
        }
    }

    @And("Cliccare sul bottone Filtra persona giuridica")
    public void cliccareSulBottoneFiltraPersonaGiuridica() {
        ricercaNotifichePGPage.clickFiltraButton();
    }

    @And("Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica {string}")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaVengoRestituiteTutteLeNotificheConIlCodiceIUNDellaNotifica(String datiNotificaPG) {
        logger.info("Si verifica i risultati restituiti");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        DataPopulation dataPopulation = new DataPopulation();

        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        String ragioneSociale = dataPopulation.readDataPopulation("personaGiuridica.yaml").get("ragioneSociale").toString();
        piattaformaNotifichePGPAPage.waitLoadPiattaformaNotificaPage(ragioneSociale);

        this.datiNotificaPG = dataPopulation.readDataPopulation(datiNotificaPG + ".yaml");
        String codiceIUNInserito = this.datiNotificaPG.get("codiceIUN").toString();

        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        boolean result = notificheDestinatarioPage.verificaCodiceIUN(codiceIUNInserito);
        if (result) {
            logger.info("Il risultato é coerente con il codice IUN inserito");
        } else {

            logger.error("Gli stati della notifica NON sono uguali a quelli selezionati");
            Assert.fail("Gli stati della notifica NON sono uguali a quelli selezionati");

        }

    }

    @And("Nella pagina Piattaforma Notifiche persona giuridica inserire un arco temporale")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaInserireUnArcoTemporale() {
        LocalDate dateA = LocalDate.now();
        LocalDate dateDa = dateA.minusDays(5);

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        String dataa = piattaformaNotifichePage.conversioneFormatoDate(dateA.toString());
        String datada = piattaformaNotifichePage.conversioneFormatoDate(dateDa.toString());
        piattaformaNotifichePage.inserimentoArcoTemporale(datada, dataa);
    }

    @And("Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaVengoRestituiteTutteLeNotificheConLaDataDellaNotificaCompresaConLeDatePrecedentementeInserite() {
        HeaderPGSection headerPGSection = new HeaderPGSection(this.driver);
        headerPGSection.waitLoadHeaderPGPage();

        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        piattaformaNotifichePGPAPage.waitLoadPiattaformaNotificaPage("Convivio Spa");

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        boolean result = notifichePFPage.getListData();
        if (result) {
            logger.info("Il risultato é coerente con le date inserite");
        } else {
            logger.error("Il risultato NON é coerente con le date inserite");
            Assert.fail("Il risultato NON é coerente con le date inserite");
        }
    }

    @And("Cliccare sul bottone Rimuovi filtri persona giuridica")
    public void cliccareSulBottoneRimuoviFiltriPersonaGiuridica() {
        ricercaNotifichePGPage.clickRimuoviFiltriButton();
    }

    @And("Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN non valido da dati notifica {string}")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaInserireIlCodiceIunNonValidoDaDatiNotifica(String datiNotificaNonValidoPG) throws InterruptedException {
        logger.info("Si inserisce il codice IUN non valido");
        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        notificheDestinatarioPage.inserisciCodiceIUN(datiNotificaNonValidoPG);
    }

    @Then("Viene visualizzato un messaggio in rosso di errore sotto il campo errato e il rettangolo diventa rosso e il tasto Filtra è disattivo")
    public void vieneVisualizzatoUnMessaggioInRossoDiErroreSottoIlCampoErratoEIlRettangoloDiventaRossoEIlTastoFiltraEDisattivo() {

        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);

        boolean isErrorMessageDisplayed = ricercaNotifichePGPage.isErrorMessageDisplayed();

        if (isErrorMessageDisplayed) {
            logger.info("il messaggio di errore é visualizzato");
        } else {
            logger.error("il messaggio di errore non é visualizzato");
            Assert.fail("il messaggio di errore non é visualizzato");
        }

        boolean isTextBoxInValid = notificheDestinatarioPage.isTextBoxInvalid();

        if (isTextBoxInValid) {
            logger.info("IUN text box non é valido");
        } else {
            logger.error("IUN text box non é passato allo stato non valido");
            Assert.fail("IUN text box non é passato allo stato non valido");

        }

        ricercaNotifichePGPage.clickFiltraButton();
        boolean isErrorMessageStillDisplayed = ricercaNotifichePGPage.isErrorMessageDisplayed();
        if (isErrorMessageStillDisplayed) {
            logger.info("Il bottone Filtra é dissativato");
        } else {
            logger.error("Il bottone Filtra é attivo");
            Assert.fail("Il bottone Filtra é attivo");
        }
    }

    @And("Se i risultati sono contenuti in più pagine persona giuridica è possibile effettuare il cambio pagina")
    public void seIRisultatiSonoContenutiInPiuPagineDestinatarioEPossibileEffettuareIlCambioPagina() {
        logger.info("Se i risultati sono contenuti in più pagine è possibile effettuare il cambio pagina");


        if(piattaformaNotifichePage.verificaEsistenzaEPassaggioPagina()){
            logger.info("Bottone pagina 2 trovato e cliccato");

            HeaderPGSection headerPGSection = new HeaderPGSection(this.driver);
            headerPGSection.waitLoadHeaderPGPage();
            ricercaNotifichePGPage.waitLoadNotifichePGPage();
        }else {
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
            Assert.fail("Il bottone Filtra é attivo");
        }
    }
}


