package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.mittente.ApiKeyPAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePAPage;
import it.pn.frontend.e2e.section.mittente.GeneraApiKeySection;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApiKeysTest {

    private static final Logger logger = LoggerFactory.getLogger("ApiKeysTest");

    private WebDriver driver = Hooks.driver;

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
}
