package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.mittente.SpidAcsMittente;
import it.pn.frontend.e2e.api.mittente.SpidLoginMittente;
import it.pn.frontend.e2e.api.mittente.SpidTestEnvWestEuropeAzureContainerIoContinueResponse;
import it.pn.frontend.e2e.api.mittente.SpidTestEnvWestEuropeAzureContainerIoLogin;
import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ComeVuoiAccederePage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.*;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.HeaderPGSection;
import it.pn.frontend.e2e.utility.CookieConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.cookie.BasicClientCookie;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginPGPagoPATest {
    private final Logger logger = LoggerFactory.getLogger("LoginPGPagoPATest");
    private final WebDriver driver = Hooks.driver;
    private Map<String, Object> datiPersonaGiuridica = new HashMap<>();
    private Map<String, String> urlPersonaGiuridica;
    private final DataPopulation dataPopulation = new DataPopulation();
    private final HeaderPGSection headerPGSection = new HeaderPGSection(this.driver);
    private final AccediAreaRiservataPGPage accediAreaRiservataPGPage = new AccediAreaRiservataPGPage(this.driver);
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
    private final String FILE_TOKEN_LOGIN = "tokenLogin.yaml";
    private final String RAGIONE_SOCIALE_BALDASSARRE = "Comune di Milano";
    private final String URL_LOGIN_PG = "https://imprese.dev.notifichedigitali.it/";

    @Autowired
    private CookieConfig cookieConfig;

    @Given("Login Page persona giuridica viene visualizzata")
    public void loginPagePersonaGiuridicaVieneVisualizzata() {
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> driver.get(URL_LOGIN_PG);
            case "test", "uat" ->
                   driver.get(URL_LOGIN_PG.replace("dev", variabileAmbiente));
            default ->
                    Assertions.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
    }

    @Given("PG - Si effettua la login tramite token exchange come {string}, e viene visualizzata la dashboard")
    public void loginMittenteConTokenExchange(String personaGiuridica) {
        DataPopulation dataPopulation = new DataPopulation();

        String environment = System.getProperty("environment");
        String token = "";
        switch (environment) {
            case "dev" -> token = personaGiuridica.equalsIgnoreCase("delegante") ?
                    dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokendevPGDelegante").toString()
                    :
                    dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokendevPGDelegato").toString();
            case "test" -> {
                if(personaGiuridica.equalsIgnoreCase("delegante")){
                    token = dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokentestPGDelegante").toString();
                } else if (personaGiuridica.equalsIgnoreCase("baldassarre")) {
                    token = dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokentestPGBaldassarre").toString();
                } else{
                    token = dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokentestPGDelegato").toString();
                }
            }
            default -> {
                logger.error("Ambiente non valido");
                Assertions.fail("Ambiente non valido o non trovato!");
            }
        }

        // Si effettua il login con token exchange
        String urlLogin = "https://imprese." + environment + ".notifichedigitali.it/#selfCareToken=" + token;
        this.driver.get(urlLogin);
        logger.info("Login effettuato con successo");
        WebTool.waitTime(10);
        PiattaformaNotifichePGPAPage notifichePGPage = new PiattaformaNotifichePGPAPage(this.driver);

        headerPGSection.waitLoadHeaderPGPage();

        // Si visualizza la dashboard e si verifica che gli elementi base siano presenti (header e title della pagina)
        if (personaGiuridica.equalsIgnoreCase("delegante")) {
            Map<String, Object> personaGiuridicaFile = dataPopulation.readDataPopulation("personaGiuridica.yaml");
            notifichePGPage.waitLoadPiattaformaNotificaPage(personaGiuridicaFile.get("ragioneSociale").toString());
        } else if(personaGiuridica.equalsIgnoreCase("baldassarre")) {
            notifichePGPage.waitLoadPiattaformaNotificaPage(RAGIONE_SOCIALE_BALDASSARRE);
        }else {
            Map<String, Object> personaGiuridicaFile = dataPopulation.readDataPopulation("delegatoPG.yaml");
            notifichePGPage.waitLoadPiattaformaNotificaPage(personaGiuridicaFile.get("ragioneSociale").toString());
        }
    }

    @When("Login portale persona giuridica tramite request method")
    public void loginPortalePersonaGiuridicaTramiteRequestMethod() {
        this.datiPersonaGiuridica = dataPopulation.readDataPopulation("personaGiuridica.yaml");
        String userMittente = this.datiPersonaGiuridica.get("user").toString();
        String pwdMittente = this.datiPersonaGiuridica.get("pwd").toString();
        this.readUrlPortaleMittente(userMittente, pwdMittente);
        boolean urlWithTokenFound = false;
        int numProvaLogin = 0;
        while (numProvaLogin < 10) {
            this.readUrlPortaleMittente(userMittente, pwdMittente);
            if (this.urlPersonaGiuridica.get("responseCode").equalsIgnoreCase("301")) {
                urlWithTokenFound = true;
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            numProvaLogin++;
        }

        if (urlWithTokenFound) {
            logger.info("procedura di login from spid provata : " + numProvaLogin);
        } else {
            logger.error("procedura di login from spid provata : " + numProvaLogin);
            Assertions.fail("Codice risposta ricevuto per questo end point: '" + this.urlPersonaGiuridica.get("urlPortale") + "' è : " + this.urlPersonaGiuridica.get("responseCode"));

        }

        this.driver.get(this.urlPersonaGiuridica.get("urlPortale"));

        if (!cookieConfig.isCookieEnabled()) {
            CookiesSection cookiesPage = new CookiesSection(this.driver);
            if (cookiesPage.waitLoadCookiesPage()) {
                cookiesPage.selezionaAccettaTuttiButton();
            }
        }

        SelezionaImpresaPage impresaPage = new SelezionaImpresaPage(this.driver);
        impresaPage.clickSuImpresa(this.datiPersonaGiuridica.get("ragioneSociale").toString());
        impresaPage.clickAccediButton();
    }

    private void readUrlPortaleMittente(String user, String password) {

        SpidLoginMittente spidLoginMittente = new SpidLoginMittente("xx_testenv2", "SpidL2");
        spidLoginMittente.setSpidLoginMittenteEndPoint("https://api-pnpg.uat.selfcare.pagopa.it/spid/v1/login");
        spidLoginMittente.runSpidLoginMittente();
        if (spidLoginMittente.getResponseBody() == null) {
            Assertions.fail("api spid login risponde con body vuoto");
        }

        String cookiesNameFromSpidLoginMittente = spidLoginMittente.getCookieName();
        if (cookiesNameFromSpidLoginMittente != null) {
            logger.info("cookiesNameFromSpidLoginMittente : " + cookiesNameFromSpidLoginMittente);
        } else {
            Assertions.fail("cookiesNameFromSpidLoginMittente is null");
        }

        String cookiesValueFromSpidLoginMittente = spidLoginMittente.getCookieValue();
        if (cookiesValueFromSpidLoginMittente != null) {
            logger.info("cookiesValueFromSpidLoginMittente : " + cookiesValueFromSpidLoginMittente);
        } else {
            Assertions.fail("cookiesValueFromSpidLoginMittente is null");
        }

        String cookiesDomainFromSpidLoginMittente = spidLoginMittente.getCookieDomain();
        if (cookiesDomainFromSpidLoginMittente != null) {
            logger.info("cookiesDomainFromSpidLoginMittente : " + cookiesDomainFromSpidLoginMittente);
        } else {
            Assertions.fail("cookiesDomainFromSpidLoginMittente is null");
        }

        String cookiesPathFromSpidLoginMittente = spidLoginMittente.getCookiePath();
        if (cookiesPathFromSpidLoginMittente != null) {
            logger.info("cookiesPathFromSpidLoginMittente : " + cookiesPathFromSpidLoginMittente);
        } else {
            Assertions.fail("cookiesPathFromSpidLoginMittente is null");
        }

        boolean cookiesHttOnlyFromSpidLoginMittente = spidLoginMittente.getCookieHttpOnly();
        if (cookiesHttOnlyFromSpidLoginMittente) {
            logger.info("cookiesHttOnlyFromSpidLoginMittente : " + cookiesHttOnlyFromSpidLoginMittente);
        } else {
            Assertions.fail("cookiesHttOnlyFromSpidLoginMittente : " + cookiesHttOnlyFromSpidLoginMittente);
        }

        String requestKeyFromSpidLoginMittente = spidLoginMittente.getRequestKey();
        if (requestKeyFromSpidLoginMittente != null) {
            logger.info("requestKeyFromSpidLoginMittente : " + requestKeyFromSpidLoginMittente);
        } else {
            Assertions.fail("requestKeyFromSpidLoginMittente is null");
        }

        String relayStateFromSpidLoginMittente = spidLoginMittente.getRelayState();
        if (relayStateFromSpidLoginMittente != null) {
            logger.info("relayStateFromSpidLoginMittente : " + relayStateFromSpidLoginMittente);
        } else {
            Assertions.fail("relayStateFromSpidLoginMittente is null");
        }

        BasicCookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie(cookiesNameFromSpidLoginMittente, cookiesValueFromSpidLoginMittente);
        cookie.setDomain(cookiesDomainFromSpidLoginMittente);
        cookie.setPath(cookiesPathFromSpidLoginMittente);
        cookie.setHttpOnly(cookiesHttOnlyFromSpidLoginMittente);
        cookieStore.addCookie(cookie);

        SpidTestEnvWestEuropeAzureContainerIoLogin spidTestEnvWestEuropeAzureContainerIoLogin =
                new SpidTestEnvWestEuropeAzureContainerIoLogin(
                        requestKeyFromSpidLoginMittente,
                        relayStateFromSpidLoginMittente,
                        user, password,
                        cookieStore
                );
        spidTestEnvWestEuropeAzureContainerIoLogin.setSpidTestEnvWestEuropeAzureContainerIoLoginEndPoint("https://selc-u-pnpg-spid-testenv.westeurope.azurecontainer.io/login");
        spidTestEnvWestEuropeAzureContainerIoLogin.runSpidTestEnvWestEuropeAzureContainerIoLogin();

        if (spidTestEnvWestEuropeAzureContainerIoLogin.getResponseBody() == null) {
            Assertions.fail(" api selc-u-spid-testenv.westeurope.azurecontainer.io/login ha risposto con body vuoto");
        }

        String requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin = spidTestEnvWestEuropeAzureContainerIoLogin.getRequestKeyOutput();
        if (requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin != null) {
            logger.info("requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin : " + requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin);
        } else {
            Assertions.fail("requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin is null");
        }

        SpidTestEnvWestEuropeAzureContainerIoContinueResponse spidTestEnvWestEuropeAzureContainerIoContinueResponse =
                new SpidTestEnvWestEuropeAzureContainerIoContinueResponse(
                        requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin, cookieStore
                );

        spidTestEnvWestEuropeAzureContainerIoContinueResponse.setSpidTestEnvWestEuropeAzureContainerIoContinueResponseEndPoint("https://selc-u-pnpg-spid-testenv.westeurope.azurecontainer.io/continue-response");
        spidTestEnvWestEuropeAzureContainerIoContinueResponse.runSpidTestEnvWestEuropeAzureContainerIoContinueResponse();
        if (spidTestEnvWestEuropeAzureContainerIoContinueResponse.getResponseBody() == null) {
            Assertions.fail(" api selc-u-spid-testenv.westeurope.azurecontainer.io/continue-response");
        }

        String samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse = spidTestEnvWestEuropeAzureContainerIoContinueResponse.getSamlResponseOutput();
        if (samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse != null) {
            logger.info("samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse : " + samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse);
        } else {
            Assertions.fail("samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse is null");
        }

        String relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse = spidTestEnvWestEuropeAzureContainerIoContinueResponse.getRelayStateOutput();
        if (relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse != null) {
            logger.info("relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse : " + relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse);
        } else {
            Assertions.fail("relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse is null");
        }

        SpidAcsMittente spidAcsMittente = new SpidAcsMittente(
                samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse,
                relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse,
                cookieStore
        );

        spidAcsMittente.setSpidAcsEndPoint("https://api-pnpg.uat.selfcare.pagopa.it/spid/v1/acs");
        spidAcsMittente.runSpidAcs();
        this.urlPersonaGiuridica = spidAcsMittente.getSpidAcsMittenteResponse();

        if (this.urlPersonaGiuridica.get("urlPortale") != null) {
            logger.info("urlPersonaGiuridica : " + this.urlPersonaGiuridica.get("urlPortale"));
        } else {
            Assertions.fail("urlPersonaGiuridica è null ");
        }
    }

    @Then("Home page persona giuridica viene visualizzata correttamente")
    public void homePagePersonaGiuridicaVieneVisualizzataCorrettamente() {
        headerPGSection.waitLoadHeaderPGPage();
        HomePagePG homePagePG = new HomePagePG(this.driver);
        homePagePG.waitLoadHomePagePGPage();

    }

    @When("Login con persona giuridica")
    public void loginConPersonaGiuridica(Map<String,String> datiPG) {
        logger.info("La persona guiridica cerca di fare il login");


        CookiesSection cookiesSection;

        if (!cookieConfig.isCookieEnabled()) {
            cookiesSection = new CookiesSection(this.driver);
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }
        accediAreaRiservataPGPage.waitLoadAccediAreaRiservataPGPage();
        accediAreaRiservataPGPage.clickSpidButton();

        ScegliSpidPGPage scegliSpidPGPage = new ScegliSpidPGPage(this.driver);
        scegliSpidPGPage.clickTestButton();

        LoginPGPagoPAPage loginPGPagoPAPage = new LoginPGPagoPAPage(this.driver);
        loginPGPagoPAPage.waitLoadLoginPGPage();
        loginPGPagoPAPage.insertUsername(datiPG.get("user"));
        loginPGPagoPAPage.insertPassword(datiPG.get("pwd"));
        loginPGPagoPAPage.clickInviaButton();


        AutorizzaInvioDatiPGPage autorizzaInvioDatiPGPage = new AutorizzaInvioDatiPGPage(this.driver);
        autorizzaInvioDatiPGPage.waitLoadAutorizzaInvioDatiPGPage();
        autorizzaInvioDatiPGPage.clickInviaButton();

        SelezionaImpresaPage selezionaImpresaPage = new SelezionaImpresaPage(this.driver);
        selezionaImpresaPage.waitLoadSelezionaImpresaPage();
        if(selezionaImpresaPage.clickSuImpresa(datiPG.get("ragioneSociale"))){
            logger.info("click su impresa");
        }
        selezionaImpresaPage.clickAccediButton();
    }

    @And("Logout da portale persona giuridica")
    public void logoutDaPortalePersonaGiuridica() {
        headerPGSection.waitLoadHeaderPGPage();
        headerPGSection.clickEsciButton();
        WebTool.waitTime(5);

        accediAreaRiservataPGPage.waitLoadAccediAreaRiservataPGPage();

        WebTool.waitTime(5);
    }

    @When("Login {string} portale persona giuridica tramite request method")
    public void loginPortalePersonaGiuridicaTramiteRequestMethod(String dpFile) {
        this.datiPersonaGiuridica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        String userMittente = this.datiPersonaGiuridica.get("user").toString();
        String pwdMittente = this.datiPersonaGiuridica.get("pwd").toString();
        this.readUrlPortaleMittente(userMittente, pwdMittente);
        boolean urlWithTokenFound = false;
        int numProvaLogin = 0;
        while (numProvaLogin < 10) {
            this.readUrlPortaleMittente(userMittente, pwdMittente);
            if (this.urlPersonaGiuridica.get("responseCode").equalsIgnoreCase("301")) {
                urlWithTokenFound = true;
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            numProvaLogin++;
        }

        if (urlWithTokenFound) {
            logger.info("procedura di login from spid provata : " + numProvaLogin);
        } else {
            logger.error("procedura di login from spid provata : " + numProvaLogin);
            Assertions.fail("Codice risposta ricevuto per questo end point: '" + this.urlPersonaGiuridica.get("urlPortale") + "' è : " + this.urlPersonaGiuridica.get("responseCode"));

        }

        this.driver.get(this.urlPersonaGiuridica.get("urlPortale"));

        if (!cookieConfig.isCookieEnabled()) {
            CookiesSection cookiesPage = new CookiesSection(this.driver);
            if (cookiesPage.waitLoadCookiesPage()) {
                cookiesPage.selezionaAccettaTuttiButton();
            }
        }


        SelezionaImpresaPage impresaPage = new SelezionaImpresaPage(this.driver);
        impresaPage.clickSuImpresa(this.datiPersonaGiuridica.get("ragioneSociale").toString());
        impresaPage.clickAccediButton();
    }

    @When("Login portale persona giuridica tramite token exchange {string}")
    public void loginPortalePersonaGiuridicaTramiteTokenExchange(String dpFile) {
        logger.info("Si effettua il login PG tramite token");

        String variabileAmbiente = System.getProperty("environment");
        String urlIniziale = "https://imprese." + variabileAmbiente + ".notifichedigitali.it/#selfCareToken=";
        String token;
        String user = this.dataPopulation.readDataPopulation(dpFile + ".yaml").get("user").toString();
        if (user.equalsIgnoreCase("DanteAlighieri")) {
            if (variabileAmbiente.equalsIgnoreCase("test")) {
                token = this.dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokentestPGDelegante").toString();
            } else {
                token = this.dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokendevPGDelegante").toString();
            }
        } else {
            if (variabileAmbiente.equalsIgnoreCase("test")) {
                token = this.dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokentestPGDelegato").toString();
            } else {
                token = this.dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokendevPGDelegato").toString();
            }
        }
        String url = urlIniziale + token;
        this.driver.get(url);
    }

    public String getTokenExchangePGFromFile(String personaGiuridica) {
        DataPopulation dataPopulation = new DataPopulation();
        String environment = System.getProperty("environment");
        String token = "";
        switch (environment) {
            case "dev" -> token = personaGiuridica.equalsIgnoreCase("delegante") ?
                    dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokendevPGDelegante").toString()
                    :
                    dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokendevPGDelegato").toString();
            case "test" -> token = personaGiuridica.equalsIgnoreCase("delegante") ?
                    dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokentestPGDelegante").toString()
                    :
                    dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokentestPGDelegato").toString();
            default -> {
                logger.error("Ambiente non valido");
                Assertions.fail("Ambiente non valido o non trovato!");
            }
        }
        return token;
    }

    @And("Si clicca su prodotto {string}")
    public void siCliccaSuProdotto(String xpath) {
        DestinatarioPage destinatarioPage = new DestinatarioPage(driver);
        destinatarioPage.clickProdotto(xpath);
    }

}
