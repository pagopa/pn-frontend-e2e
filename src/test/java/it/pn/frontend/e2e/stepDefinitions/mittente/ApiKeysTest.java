package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.pages.mittente.ApiKeyPAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.mittente.GeneraApiKeySection;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class ApiKeysTest  extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("ApiKeysTest");

    @Getter
    @Setter
    private  String ApiKey;

    @Autowired
    private WebDriverConfig webDriverConfig;

    private PiattaformaNotifichePage piattaformaNotifichePage;

    private ApiKeyPAPage apiKeyPAPage;

    private GeneraApiKeySection generaApiKeySection;

    @PostConstruct
    public void init(){
        logger.info("INIT TEST...: ");
        piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        apiKeyPAPage = new ApiKeyPAPage(driver);
        generaApiKeySection = new GeneraApiKeySection(driver);
    }

    @And("Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu")
    public void nellaPaginaPiattaformaNotificheSelezionareLaVoceApiKeyNelMenu() {
        logger.info("Si cerca di cliccare sulla voce ApiKeys");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        piattaformaNotifichePage.selezionareLaVoceApiKey();

    }

    @And("Si visualizza correttamente la pagina Api Key")
    public void siVisualizzaCorrettamenteLaPaginaApiKey() {
        logger.info("Si visualizza correttamente la pagina Api Key");
        apiKeyPAPage.waitLoadApikeyPage();
    }

    @When("Nella pagina Api Key si clicca sul bottone genera Api Key")
    public void nellaPaginaApiKeySiCliccaSulBottoneGeneraApiKey() {
        logger.info("Si clicca sul bottone genera ApiKey");
        apiKeyPAPage.waitLoadPage();
        apiKeyPAPage.clickSulBottoneGeneraApiKey();
    }

    @Then("Si visualizza correttamente la sezione genera Api key")
    public void siVisualizzaCorrettamenteLaSezioneGeneraApiKey() {
        logger.info("Si visualizza correttamente la sezione genera Api Key");
        generaApiKeySection.waitLoadGeneraApiKey();
    }

    @And("Nella sezione genera Api Key inserire il nome {string} per l Api Key")
    public void nellaSezioneGeneraApiKeyInserireIlNomePerLApiKey(String nomeApiKey) {
        logger.info("Si inserisce il nome della api key");

        apiKeyPAPage.waitLoadPage();
        apiKeyPAPage.inserireUnNomePerApiKey(nomeApiKey);
    }

    @And("Nella sezione genera Api Key cliccare bottone continua")
    public void nellaSezioneGeneraApiKeyCliccareBottoneContinua() {
        logger.info("Si clicca sul bottone continua");

        apiKeyPAPage.clickSulBottoneContinua();
    }

    @And("Si visualizza correttamente la pagina di conferma")
    public void siVisualizzaCorrettamenteLaPaginaDiConferma() {
        logger.info("Si visualizza la pagina di conferma");

        apiKeyPAPage.siVisualizzaCorrettamenteConfermaPage();
    }

    @And("Nella pagina di conferma cliccare sul bottone Torna a API key")
    public void nellaPaginaDiConfermaCliccareSulBottoneTornaAAPIKey() {
        logger.info("Si clicca sul bottone torna a API Key");

        apiKeyPAPage.clickSulBottoneTornaApiKey();
    }

    @Then("Si visualizza correttamente l api key {string} nell elenco in stato attivo")
    public void siVisualizzaCorrettamenteLApiKeyNellElencoInStatoAttivo(String nomeApiKey) {
        logger.info("Si controlla che sia stato creata l'api key");
        apiKeyPAPage.waitLoadApikeyPage();
        apiKeyPAPage.siVisualizzaNuovaApiAttiva(nomeApiKey);
    }

    @When("Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco")
    public void nellaPaginaApiKeySiCliccaSulBottoneMenuDiUnaApiKeyAttivaPresenteInElenco() {
        apiKeyPAPage.clickMenuButton();
    }

    @And("Nella pagina Api Key si clicca sulla voce blocca del menu Api Key")
    public void nellaPaginaApiKeySiCliccaSullaVoceBloccaDelMenuApiKey() {
        apiKeyPAPage.clickSuBlocca();
    }

    @And("Nella pagina Api Key si visualizza il pop up blocca Api Key")
    public void nellaPaginaApiKeySiVisualizzaIlPopUpBloccaApiKey() {
        apiKeyPAPage.siVisualizzaPopUp();
    }

    @And("Nella pop up cliccare sul tasto annulla")
    public void nellaPopUpCliccareSulTastoAnnulla() {
        apiKeyPAPage.clickSuAnnulla();
    }

    @And("Nella pop up cliccare sul tasto conferma")
    public void nellaPopUpCliccareSulTastoConferma() {
        apiKeyPAPage.clickSuConfermaNelPopUp();
    }

    @Then("Nella pagina Api Key si visualizza la notifica selezionata nello stato bloccata")
    public void nellaPaginaApiKeySiVisualizzaLaNotificaSelezionataNelloStatoBloccata() {
        apiKeyPAPage.notificaSelezionataStatoBloccata();
    }

    @When("Nella pagina Api Key si clicca sul bottone menu di una Api Key bloccata presente in elenco")
    public void nellaPaginaApiKeySiCliccaSulBottoneMenuDiUnaApiKeyBloccataPresenteInElenco() {
        apiKeyPAPage.clickMenuButtonBlocca();
    }


    @And("Nella pagina Api Key si clicca sulla voce attiva del menu Api Key")
    public void nellaPaginaApiKeySiCliccaSullaVoceAttivaDelMenuApiKey() {
        apiKeyPAPage.clickAttivaSulMenu();
    }

    @And("Nella pagina Api Key si visualizza il pop up attiva Api Key")
    public void nellaPaginaApiKeySiVisualizzaIlPopUpAttivaApiKey() {
        apiKeyPAPage.siVisualizzaPoPUpAttiva();
    }

    @Then("Nella pagina Api Key si visualizza la notifica selezionata nello stato attiva")
    public void nellaPaginaApiKeySiVisualizzaLaNotificaSelezionataNelloStatoAttiva() {
        apiKeyPAPage.siVisualizzaNotificaSelezionataBloccata();
    }

    @And("Nella pagina Api Key si clicca sulla voce ruota del menu Api Key")
    public void nellaPaginaApiKeySiCliccaSullaVoceRuotaDelMenuApiKey() {
        apiKeyPAPage.clickRuotaSulMenu();
    }

    @And("Nella pagina Api Key si visualizza il pop up ruota Api Key")
    public void nellaPaginaApiKeySiVisualizzaIlPopUpRuotaApiKey() {
        apiKeyPAPage.siVisualizzaPoPUpRuota();
    }

    @Then("Nella pagina Api Key si visualizza la notifica selezionata nello stato ruota")
    public void nellaPaginaApiKeySiVisualizzaLaNotificaSelezionataNelloStatoRuota() {
        apiKeyPAPage.siVisualizzaNotificaSelezionataRuotata();
    }

    @And("Si visualizza correttamente la lista delle Api Key generate")
    public void siVisualizzaCorrettamenteLaListaDelleApiKeyGenerate() {
        if (apiKeyPAPage.siVisualizzaApiKeyConTesto()) {
            logger.info("Si visualizza correttamente l'Api Key delle Api Key");
        } else {
            logger.error("NON si visualizza correttamente l'Api Key delle Api Key");
            Assertions.fail("NON si visualizza correttamente l'Api Key delle Api Key");
        }

        if (apiKeyPAPage.siVisualizzaNomeEDataConTesto()) {
            logger.info("Si visualizza correttamente le date delle Api Key");
        } else {
            logger.error("NON si visualizza correttamente le date delle Api Key");
            Assertions.fail("NON si visualizza correttamente le date delle Api Key");
        }

        if (apiKeyPAPage.siVisualizzaGruppoConTesto()) {
            logger.info("Si visualizza correttamente il gruppo delle Api Key");
        } else {
            logger.error("NON si visualizza correttamente il gruppo delle Api Key");
            Assertions.fail("NON si visualizza correttamente il gruppo delle Api Key");
        }

        if (apiKeyPAPage.siVisualizzaStatoConTesto()) {
            logger.info("Si visualizza correttamente lo stato delle api key");
        } else {
            logger.error("NON si visualizza correttamente lo stato delle api key");
            Assertions.fail("NON si visualizza correttamente lo stato delle api key");
        }

        if (apiKeyPAPage.siVisualizzaMenuApiKey()) {
            logger.info("Si visualizza correttamente il bottone del menu Api Key");
        } else {
            logger.error("NON si visualizza correttamente il bottone del menu Api Key");
            Assertions.fail("NON si visualizza correttamente il bottone del menu Api Key");
        }
    }

    @Then("Nella pagina Api Key posizionare il cursore sullo stato dell'operazione")
    public void nellaPaginaApiKeyPosizionareIlCursoreSulloStatoDellOperazione() {
        apiKeyPAPage.mouseHover();
        apiKeyPAPage.waitLoadMessaggioData();
    }

    @And("Nella sezione genera Api Key inserire un gruppo")
    public void nellaSezioneGeneraApiKeyInserireUnGruppo() {
        String variabileAmbiente = webDriverConfig.getEnvironment();
        String gruppo = "";
        String gruppo2 = "";
        switch (variabileAmbiente) {
            case "dev" -> gruppo = "GruppoTest";
            case "test", "uat" -> {
                gruppo = "test-TA-FE-TEST";
                gruppo2 = "test-2-ta";
            }
        }
        apiKeyPAPage.inserireGruppoApi(gruppo);
        if (!gruppo2.isEmpty()) {
            apiKeyPAPage.inserireGruppoApi(gruppo2);
        }
    }

    @And("Nella sezione genera Api Key cancellare il testo inserito")
    public void nellaSezioneGeneraApiKeyCancellareIlTestoInserito() {
        apiKeyPAPage.cancellareTestoInserito();
    }

    @Then("Nella sezione genera si visualizza un messaggio di errore")
    public void nellaSezioneGeneraSiVisualizzaUnMessaggioDiErrore() {
        apiKeyPAPage.siVisualizzaMessaggioErroreApiName();
    }

    @And("Nella pagina Api Key si clicca sulla voce visualizza del menu Api Key")
    public void nellaPaginaApiKeySiCliccaSullaVoceVisualizzaDelMenuApiKey() {
        apiKeyPAPage.clickSuVisualizza();
    }

    @And("Nella pagina Api Key si visualizza il pop up visualizza Api Key")
    public void nellaPaginaApiKeySiVisualizzaIlPopUpVisualizzaApiKey() {
        apiKeyPAPage.siVisualizzaPopUpVisualizza();
    }

    @Then("Nel pop up visualizza cliccare sul tasto chiudi")
    public void nelPopUpVisualizzaCliccareSulTastoChiudi() {
        apiKeyPAPage.chiudiPopUpVisualizza();
    }

    @And("Nella pagina Api Key si clicca sulla voce visualizza id gruppo del menu Api Key")
    public void nellaPaginaApiKeySiCliccaSullaVoceVisualizzaIdGruppoDelMenuApiKey() {
        logger.info("Si clicca sulla voce visualizza id gruppo");

        apiKeyPAPage.clickVisualizzaIdApiKey();
    }

    @And("Nella pagina Api Key si visualizza il pop up Gruppi associati alla API")
    public void nellaPaginaApiKeySiVisualizzaIlPopUpGruppiAssociatiAllaAPI() {
        logger.info("Si controlla che si vede il pop-up gruppi associato alla Api");

        apiKeyPAPage.popUpGruppiAssociati();
    }

    @Then("Nella pop up cliccare sul tasto chiudi")
    public void nellaPopUpCliccareSulTastoChiudi() {
        logger.info("Si clicca sul tasto chiudi");

        apiKeyPAPage.chiudiPopUpVisualizza();
    }

    @And("Si copia correttamente la Api Key cliccando sul bottone di copia")
    public void siCopiaCorrettamenteLaApiKeyCliccandoSulBottoneDiCopia() {
        logger.info("Si clicca copia sul tasto 'copy-clipboard' per copiare la Api Key");
        String apikeyCopied = apiKeyPAPage.copiaApiKey();
        logger.info("La Api Key copiata è: " + apikeyCopied);
    }

    @Then("Nella pagina Api Key posizionare il cursuore sopra il numero gruppi")
    public void nellaPaginaApiKeyPosizionareIlCursuoreSopraIlNumeroGruppi() {
        apiKeyPAPage.mouseHoverGroups();
        apiKeyPAPage.waitLoadMessaggioData();
    }

    @And("Si copia e salva API key generata")
    public void siCopiaESalvaApiKeyGenearta(){
        logger.info("Si copia e salva API key generata");
        setApiKey(apiKeyPAPage.copiaApiKeyESalva());
    }

    @And("Si clicca visualizza codice e verifica che il valore dell'apikey copiato sia uguale")
    public void siVerificaValoreApiKeyUguale(){
        logger.info("Verifica che il valore dell'apikey copiato sia uguale a quello visualizzato in elenco");
        String apiKeyDaElenco =  apiKeyPAPage.visualizzaApiKeyInElenco();
       Assertions.assertTrue(getApiKey().equalsIgnoreCase(apiKeyDaElenco));
    }
}
