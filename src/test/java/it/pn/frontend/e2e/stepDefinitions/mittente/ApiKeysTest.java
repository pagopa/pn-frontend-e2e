package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.mittente.ApiKeyPAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePAPage;
import it.pn.frontend.e2e.section.mittente.GeneraApiKeySection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ApiKeysTest {

    private static final Logger logger = LoggerFactory.getLogger("ApiKeysTest");

    private final WebDriver driver = Hooks.driver;

    @And("Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu")
    public void nellaPaginaPiattaformaNotificheSelezionareLaVoceApiKeyNelMenu() {
        logger.info("Si cerca di cliccare sulla voce ApiKeys");

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        piattaformaNotifichePAPage.selezionareLaVoceApiKey();

    }

    @And("Si visualizza correttamente la pagina Api Key")
    public void siVisualizzaCorrettamenteLaPaginaApiKey() {
        logger.info("Si visualizza correttamente la pagina Api Key");

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.waitLoadApikeyPage();
    }

    @When("Nella pagina Api Key si clicca sul bottone genera Api Key")
    public void nellaPaginaApiKeySiCliccaSulBottoneGeneraApiKey() {
        logger.info("Si clicca sul bottone genera ApiKey");

        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.waitLoadPage();
        apiKeyPAPage.clickSulBottoneGeneraApiKey();
    }

    @Then("Si visualizza correttamente la sezione genera Api key")
    public void siVisualizzaCorrettamenteLaSezioneGeneraApiKey() {
        logger.info("Si visualizza correttamente la sezione genera Api Key");

        GeneraApiKeySection generaApiKeySection = new GeneraApiKeySection(this.driver);
        generaApiKeySection.waitLoadGeneraApiKey();
    }

    @And("Nella sezione genera Api Key inserire il nome {string} per l Api Key")
    public void nellaSezioneGeneraApiKeyInserireIlNomePerLApiKey(String nomeApiKey) {
        logger.info("Si inserisce il nome della api key");
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.waitLoadPage();
        apiKeyPAPage.inserireUnNomePerApiKey(nomeApiKey);
    }

    @And("Nella sezione genera Api Key cliccare bottone continua")
    public void nellaSezioneGeneraApiKeyCliccareBottoneContinua() {
        logger.info("Si clicca sul bottone continua");
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.clickSulBottoneContinua();
    }

    @And("Si visualizza correttamente la pagina di conferma")
    public void siVisualizzaCorrettamenteLaPaginaDiConferma() {
        logger.info("Si visualizza la pagina di conferma");
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.siVisualizzaCorrettamenteConfermaPage();
    }

    @And("Nella pagina di conferma cliccare sul bottone Torna a API key")
    public void nellaPaginaDiConfermaCliccareSulBottoneTornaAAPIKey() {
        logger.info("Si clicca sul bottone torna a API Key");
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.clickSulBottoneTornaApiKey();
    }

    @Then("Si visualizza correttamente l api key {string} nell elenco in stato attivo")
    public void siVisualizzaCorrettamenteLApiKeyNellElencoInStatoAttivo(String nomeApiKey) {
        logger.info("Si controlla che sia stato creata l'api key");
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.siVisualizzaNuovaApiAttiva(nomeApiKey);
    }

    @When("Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco")
    public void nellaPaginaApiKeySiCliccaSulBottoneMenuDiUnaApiKeyAttivaPresenteInElenco() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.clickMenuButton();
    }

    @And("Nella pagina Api Key si clicca sulla voce blocca del menu Api Key")
    public void nellaPaginaApiKeySiCliccaSullaVoceBloccaDelMenuApiKey() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.clickSuBlocca();
    }

    @And("Nella pagina Api Key si visualizza il pop up blocca Api Key")
    public void nellaPaginaApiKeySiVisualizzaIlPopUpBloccaApiKey() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.siVisualizzaPopUp();
    }

    @And("Nella pop up cliccare sul tasto annulla")
    public void nellaPopUpCliccareSulTastoAnnulla() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.clickSuAnnulla();
    }

    @And("Nella pop up cliccare sul tasto conferma")
    public void nellaPopUpCliccareSulTastoConferma() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.clickSuConfermaNelPopUp();
    }

    @Then("Nella pagina Api Key si visualizza la notifica selezionata nello stato bloccata")
    public void nellaPaginaApiKeySiVisualizzaLaNotificaSelezionataNelloStatoBloccata() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.notificaSelezionataStatoBloccata();
    }

    @When("Nella pagina Api Key si clicca sul bottone menu di una Api Key bloccata presente in elenco")
    public void nellaPaginaApiKeySiCliccaSulBottoneMenuDiUnaApiKeyBloccataPresenteInElenco() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.clickMenuButtonBlocca();
    }


    @And("Nella pagina Api Key si clicca sulla voce attiva del menu Api Key")
    public void nellaPaginaApiKeySiCliccaSullaVoceAttivaDelMenuApiKey() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.clickAttivaSulMenu();
    }

    @And("Nella pagina Api Key si visualizza il pop up attiva Api Key")
    public void nellaPaginaApiKeySiVisualizzaIlPopUpAttivaApiKey() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.siVisualizzaPoPUpAttiva();
    }

    @Then("Nella pagina Api Key si visualizza la notifica selezionata nello stato attiva")
    public void nellaPaginaApiKeySiVisualizzaLaNotificaSelezionataNelloStatoAttiva() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.siVisualizzaNotificaSelezionataBloccata();
    }

    @And("Nella pagina Api Key si clicca sulla voce ruota del menu Api Key")
    public void nellaPaginaApiKeySiCliccaSullaVoceRuotaDelMenuApiKey() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.clickRuotaSulMenu();
    }

    @And("Nella pagina Api Key si visualizza il pop up ruota Api Key")
    public void nellaPaginaApiKeySiVisualizzaIlPopUpRuotaApiKey() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.siVisualizzaPoPUpRuota();
    }

    @Then("Nella pagina Api Key si visualizza la notifica selezionata nello stato ruota")
    public void nellaPaginaApiKeySiVisualizzaLaNotificaSelezionataNelloStatoRuota() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.siVisualizzaNotificaSelezionataRuotata();
    }

    @And("Si visualizza correttamente la lista delle Api Key generate")
    public void siVisualizzaCorrettamenteLaListaDelleApiKeyGenerate() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);

        if (apiKeyPAPage.siVisualizzaApiKeyConTesto()){
            logger.info("Si visualizza correttamente l'Api Key delle Api Key");
        }else {
            logger.error("NON si visualizza correttamente l'Api Key delle Api Key");
            Assert.fail("NON si visualizza correttamente l'Api Key delle Api Key");
        }

        if (apiKeyPAPage.siVisualizzaNomeEDataConTesto()){
            logger.info("Si visualizza correttamente le date delle Api Key");
        }else {
            logger.error("NON si visualizza correttamente le date delle Api Key");
            Assert.fail("NON si visualizza correttamente le date delle Api Key");
        }

        if (apiKeyPAPage.siVisualizzaGruppoConTesto()){
            logger.info("Si visualizza correttamente il gruppo delle Api Key");
        }else {
            logger.error("NON si visualizza correttamente il gruppo delle Api Key");
            Assert.fail("NON si visualizza correttamente il gruppo delle Api Key");
        }

        if (apiKeyPAPage.siVisualizzaStatoConTesto()){
            logger.info("Si visualizza correttamente lo stato delle api key");
        }else {
            logger.error("NON si visualizza correttamente lo stato delle api key");
            Assert.fail("NON si visualizza correttamente lo stato delle api key");
        }

        if (apiKeyPAPage.siVisualizzaMenuApiKey()){
            logger.info("Si visualizza correttamente il bottone del menu Api Key");
        }else {
            logger.error("NON si visualizza correttamente il bottone del menu Api Key");
            Assert.fail("NON si visualizza correttamente il bottone del menu Api Key");
        }
    }

    @Then("Nella pagina Api Key posizionare il cursore sullo stato dell'operazione")
    public void nellaPaginaApiKeyPosizionareIlCursoreSulloStatoDellOperazione() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.mouseHover();
        apiKeyPAPage.waitLoadMessaggioData();
    }

    @And("Nella sezione genera Api Key inserire un gruppo")
    public void nellaSezioneGeneraApiKeyInserireUnGruppo() {
        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        String variabileAmbiente = System.getProperty("environment");
        String gruppo="";
        switch (variabileAmbiente) {
            case "dev" ->  gruppo = datiNotifica.get("gruppoDev").toString();
            case "test", "uat" -> gruppo = datiNotifica.get("gruppoTest").toString();
        }
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.inserireGruppoApi(gruppo);
    }

    @And("Nella sezione genera Api Key cancellare il testo inserito")
    public void nellaSezioneGeneraApiKeyCancellareIlTestoInserito() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.cancellareTestoInserito();
    }

    @Then("Nella sezione genera si visualizza un messaggio di errore")
    public void nellaSezioneGeneraSiVisualizzaUnMessaggioDiErrore() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.siVisualizzaMessaggioErroreApiName();
    }

    @And("Nella pagina Api Key si clicca sulla voce visualizza del menu Api Key")
    public void nellaPaginaApiKeySiCliccaSullaVoceVisualizzaDelMenuApiKey() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.clickSuVisualizza();
    }

    @And("Nella pagina Api Key si visualizza il pop up visualizza Api Key")
    public void nellaPaginaApiKeySiVisualizzaIlPopUpVisualizzaApiKey() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.siVisualizzaPopUpVisualizza();
    }

    @Then("Nel pop up visualizza cliccare sul tasto chiudi")
    public void nelPopUpVisualizzaCliccareSulTastoChiudi() {
        ApiKeyPAPage apiKeyPAPage = new ApiKeyPAPage(this.driver);
        apiKeyPAPage.chiudiPopUpVisualizza();
    }


}
