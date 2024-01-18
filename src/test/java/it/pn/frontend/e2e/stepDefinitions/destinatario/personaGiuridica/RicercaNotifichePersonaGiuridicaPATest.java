package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.NotificheDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
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
    RicercaNotifichePGPage ricercaNotifichePGPage = new RicercaNotifichePGPage(this.driver);
    private Map<String, Object> datiNotificaPG;
    private Map<String, Object> datiNotificaNonValidoPG;


    @When("Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa")
    public void cliccareSuNotificheDellImpresa(){
        logger.info("Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa");
        ricercaNotifichePGPage.clickNotificheImpresa();
    }

    @And("Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN da dati notifica {string}")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaInserireIlCodiceIUNDaDatiNotifica(String datiNotificaPG) throws InterruptedException {
        logger.info("Si inserisce il codice IUN");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotificaPG = dataPopulation.readDataPopulation(datiNotificaPG+".yaml");
        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        notificheDestinatarioPage.inserisciCodiceIUN(this.datiNotificaPG.get("codiceIUN").toString());
    }

    @When("La persona giuridica clicca sulla notifica restituita {string}")
    public void ilPersonaGiuridicaCliccaSullaNotificaRestituita(String dpFile) {
        DataPopulation dataPopulation = new DataPopulation();
        String codiceIUNPG = dataPopulation.readDataPopulation(dpFile+".yaml").get("codiceIUN").toString();
        ricercaNotifichePGPage.cliccaNotificaRestituita(codiceIUNPG);
    }

    @And("Si visualizza correttamente la section Dettaglio Notifica persona giuridica")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotificaPersonaGiuridica() {
        ricercaNotifichePGPage.waitLoadDettaglioNotificaPGSection();
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
        String ragioneSociale = dataPopulation.readDataPopulation( "personaGiuridica.yaml").get("ragioneSociale").toString();
        piattaformaNotifichePGPAPage.waitLoadPiattaformaNotificaPage(ragioneSociale);

        this.datiNotificaPG = dataPopulation.readDataPopulation(datiNotificaPG + ".yaml");
        String codiceIUNInserito = this.datiNotificaPG.get("codiceIUN").toString();

        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        boolean result = notificheDestinatarioPage.verificaCodiceIUN(codiceIUNInserito);
        if (result) {
            logger.info("Il risultato coerente con il codice IUN inserito");
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
        if (result){
            logger.info("Il risultato coerente con le date inserite");
        }else {
            logger.error("Il risultato NON coerente con le date inserite");
            Assert.fail("Il risultato NON coerente con le date inserite");
        }
    }
    @And("Cliccare sul bottone Rimuovi filtri persona giuridica")
    public void cliccare_sul_bottone_rimuovi_filtri_persona_giuridica() {
        ricercaNotifichePGPage.clickRimuoviFiltriButton();

    }
    @And("Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN non valido da dati notifica {string}")
    public void nella_pagina_piattaforma_notifiche_persona_giuridica_inserire_il_codice_iun_non_valido_da_dati_notifica(String datiNotificaNonValidoPG) throws InterruptedException {
        logger.info("Si inserisce il codice IUN non valido");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotificaNonValidoPG = dataPopulation.readDataPopulation(datiNotificaNonValidoPG+".yaml");
        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        notificheDestinatarioPage.inserisciCodiceIUN(this.datiNotificaNonValidoPG.get("codiceIUN").toString());
    }
    @Then("Viene visualizzato un messaggio in rosso di errore sotto il campo errato e il rettangolo diventa rosso e il tasto Filtra è disattivo")
    public void viene_visualizzato_un_messaggio_in_rosso_di_errore_sotto_il_campo_errato_e_il_rettangolo_diventa_rosso_e_il_tasto_è_disattivo() {

        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);

        boolean isErrorMessageDisplayed = ricercaNotifichePGPage.isErrorMessageDisplayed();

        if (isErrorMessageDisplayed) {
            logger.info("Il messagio di errore e visualizzato");
        } else {
            logger.error("Il messagio di errore non e visualizzato");
            Assert.fail("Il messagio di errore non e visualizzato");
        }

        boolean isTextBoxInValid = notificheDestinatarioPage.isTextBoxInvalid();

        if (isTextBoxInValid) {
            logger.info("IUN text box non e valido");
        } else {
            logger.error("UIN text box non e passato al lo stato non valido ");
            Assert.fail("UIN text box non e passato al lo stato non valido");

        }

        ricercaNotifichePGPage.clickFiltraButton();
        boolean isErrorMessageStillDisplayed = ricercaNotifichePGPage.isErrorMessageDisplayed();
        if (isErrorMessageStillDisplayed){
            logger.info("Il buttone Filtra e disattivato");
        }else {
            logger.error("Il buttone Filtra e attivo");
            Assert.fail("Il buttone Filtra e attivo");
        }
    }


}


