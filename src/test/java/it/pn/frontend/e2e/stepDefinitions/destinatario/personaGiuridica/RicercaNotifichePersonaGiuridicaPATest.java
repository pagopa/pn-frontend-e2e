package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.RicercaNotifichePGPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.HeaderPGSection;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Map;

public class RicercaNotifichePersonaGiuridicaPATest {

    private final Logger logger = LoggerFactory.getLogger("RicercaNotifichePersonaGiuridicaPATest");
    private final WebDriver driver = Hooks.driver;
    RicercaNotifichePGPage ricercaNotifichePGPage = new RicercaNotifichePGPage(this.driver);
    private Map<String, Object> datiNotificaPG;

    @When("Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa")
    public void cliccareSuNotificheDellImpresa(){
        logger.info("Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa");
        ricercaNotifichePGPage.clickNotificheImpresa();
    }

    @And("Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN da dati notifica {string}")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaInserireIlCodiceIUNDaDatiNotifica(String datiNotificaPG) {
        logger.info("Si inserisce il codice IUN");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotificaPG = dataPopulation.readDataPopulation(datiNotificaPG+".yaml");
        NotifichePFPage NotifichePFPage = new NotifichePFPage(this.driver);
        NotifichePFPage.inserisciCodiceIUN(this.datiNotificaPG.get("codiceIUN").toString());
    }

    @When("Il persona giuridica clicca sulla notifica restituita {string}")
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

    @Then("Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica {string}")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaVengoRestituiteTutteLeNotificheConIlCodiceIUNDellaNotifica(String datiNotificaPG) {
        logger.info("Si verifica i risultati restituiti");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPage();
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotificaPG = dataPopulation.readDataPopulation(datiNotificaPG + ".yaml");
        String codiceIUNInserito = this.datiNotificaPG.get("codiceIUN").toString();
        boolean result = notifichePFPage.verificaCodiceIUN(codiceIUNInserito);
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

    @Then("Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaVengoRestituiteTutteLeNotificheConLaDataDellaNotificaCompresaConLeDatePrecedentementeInserite() {
        HeaderPGSection headerPGSection = new HeaderPGSection(this.driver);
        headerPGSection.waitLoadHeaderPGPage();

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

}


