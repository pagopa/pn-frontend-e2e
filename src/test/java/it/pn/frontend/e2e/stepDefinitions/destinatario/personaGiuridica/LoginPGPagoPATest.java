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
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.HooksNew;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//@Component inserire in un secondo momenti
public class LoginPGPagoPATest {

    private final Logger logger = LoggerFactory.getLogger(LoginPGPagoPATest.class);

    /**
    @Autowired
    private HeaderPGSection headerPGSection;

    @Autowired
    private AccediAreaRiservataPGPage accediAreaRiservataPGPage;

    @Autowired
    private DataPopulation dataPopulation;

    @Autowired
    private List<NetWorkInfo> netWorkInfos;

    @Value("${tokenLogin.file}")
    private String FILE_TOKEN_LOGIN;

    @Value("${ragioneSociale.baldassarre}")
    private String RAGIONE_SOCIALE_BALDASSARRE;

    @Value("${url.login.pg}")
    private String URL_LOGIN_PG;
    **/

    private HeaderPGSection headerPGSection;
    private  AccediAreaRiservataPGPage accediAreaRiservataPGPage;

    private Map<String, Object> datiPersonaGiuridica = new HashMap<>();
    private Map<String, String> urlPersonaGiuridica;

    private final String FILE_TOKEN_LOGIN = "tokenLogin.yaml";
    private final String RAGIONE_SOCIALE_BALDASSARRE = "Comune di Milano";
    private final String URL_LOGIN_PG = "https://imprese.dev.notifichedigitali.it/";


    @Autowired
    private CookieConfig cookieConfig;
    @Autowired
    private WebDriverConfig webDriverConfig;
    @Autowired
    private HooksNew hooks;
    @Autowired
    private  DataPopulation dataPopulation;



    @Given("Login Page persona giuridica viene visualizzata")
    public void loginPagePersonaGiuridicaVieneVisualizzata() {
        String variabileAmbiente = webDriverConfig.getEnvironment();
        switch (variabileAmbiente) {
            case "dev" -> hooks.getDriver().get(URL_LOGIN_PG);
            case "test", "uat" ->
                    hooks.getDriver().get(URL_LOGIN_PG.replace("dev", variabileAmbiente));
            default ->
                    Assertions.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
    }

    @Given("PG - Si effettua la login tramite token exchange come {string}, e viene visualizzata la dashboard")
    public void loginMittenteConTokenExchange(String personaGiuridica) {
        String environment = webDriverConfig.getEnvironment();
        String token = "";
        switch (environment) {
            case "dev" -> token = personaGiuridica.equalsIgnoreCase("delegante") ?
                    dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokendevPGDelegante").toString()
                    : dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokendevPGDelegato").toString();
            case "test" -> token = personaGiuridica.equalsIgnoreCase("delegante") ?
                    dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokentestPGDelegante").toString()
                    : dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokentestPGDelegato").toString();
            default -> {
                logger.error("Ambiente non valido");
                Assertions.fail("Ambiente non valido o non trovato!");
            }
        }

        String urlLogin = "https://imprese." + environment + ".notifichedigitali.it/#selfCareToken=" + token;
        hooks.getDriver().get(urlLogin);
        logger.info("Login effettuato con successo");

        WebTool.waitTime(10);
        PiattaformaNotifichePGPAPage notifichePGPage = new PiattaformaNotifichePGPAPage(hooks.getDriver());

        headerPGSection = new HeaderPGSection(hooks.getDriver());
        headerPGSection.waitLoadHeaderPGPage();

        if (personaGiuridica.equalsIgnoreCase("delegante")) {
            Map<String, Object> personaGiuridicaFile = dataPopulation.readDataPopulation("personaGiuridica.yaml");
            notifichePGPage.waitLoadPiattaformaNotificaPage(personaGiuridicaFile.get("ragioneSociale").toString());
        } else if (personaGiuridica.equalsIgnoreCase("baldassarre")) {
            notifichePGPage.waitLoadPiattaformaNotificaPage(RAGIONE_SOCIALE_BALDASSARRE);
        } else {
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

        hooks.getDriver().get(this.urlPersonaGiuridica.get("urlPortale"));

        if (!cookieConfig.isCookieEnabled()) {
            CookiesSection cookiesPage = new CookiesSection(hooks.getDriver());
            if (cookiesPage.waitLoadCookiesPage()) {
                cookiesPage.selezionaAccettaTuttiButton();
            }
        }

        SelezionaImpresaPage impresaPage = new SelezionaImpresaPage(hooks.getDriver());
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
        String cookiesValueFromSpidLoginMittente = spidLoginMittente.getCookieValue();
        String cookiesDomainFromSpidLoginMittente = spidLoginMittente.getCookieDomain();
        String cookiesPathFromSpidLoginMittente = spidLoginMittente.getCookiePath();
        boolean cookiesHttOnlyFromSpidLoginMittente = spidLoginMittente.getCookieHttpOnly();
        String requestKeyFromSpidLoginMittente = spidLoginMittente.getRequestKey();
        String relayStateFromSpidLoginMittente = spidLoginMittente.getRelayState();

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
        String relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse = spidTestEnvWestEuropeAzureContainerIoContinueResponse.getRelayStateOutput();

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
        headerPGSection = new HeaderPGSection(hooks.getDriver());
        headerPGSection.waitLoadHeaderPGPage();
        HomePagePG homePagePG = new HomePagePG(hooks.getDriver());
        homePagePG.waitLoadHomePagePGPage();
    }

    @When("Login con persona giuridica")
    public void loginConPersonaGiuridica(Map<String, String> datiPG) {
        logger.info("La persona guiridica cerca di fare il login");


        CookiesSection cookiesSection;

        if (!cookieConfig.isCookieEnabled()) {
            cookiesSection = new CookiesSection(hooks.getDriver());
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }
        accediAreaRiservataPGPage = new AccediAreaRiservataPGPage(hooks.getDriver());
        accediAreaRiservataPGPage.waitLoadAccediAreaRiservataPGPage();
        accediAreaRiservataPGPage.clickSpidButton();

        ScegliSpidPGPage scegliSpidPGPage = new ScegliSpidPGPage(hooks.getDriver());
        scegliSpidPGPage.clickTestButton();

        LoginPGPagoPAPage loginPGPagoPAPage = new LoginPGPagoPAPage(hooks.getDriver());
        loginPGPagoPAPage.waitLoadLoginPGPage();
        loginPGPagoPAPage.insertUsername(datiPG.get("user"));
        loginPGPagoPAPage.insertPassword(datiPG.get("pwd"));
        loginPGPagoPAPage.clickInviaButton();


        AutorizzaInvioDatiPGPage autorizzaInvioDatiPGPage = new AutorizzaInvioDatiPGPage(hooks.getDriver());
        autorizzaInvioDatiPGPage.waitLoadAutorizzaInvioDatiPGPage();
        autorizzaInvioDatiPGPage.clickInviaButton();

        SelezionaImpresaPage selezionaImpresaPage = new SelezionaImpresaPage(hooks.getDriver());
        selezionaImpresaPage.waitLoadSelezionaImpresaPage();
        if(selezionaImpresaPage.clickSuImpresa(datiPG.get("ragioneSociale"))){
            logger.info("click su impresa");
        }
        selezionaImpresaPage.clickAccediButton();
    }

    @And("Logout da portale persona giuridica")
    public void logoutDaPortalePersonaGiuridica() {
        headerPGSection = new HeaderPGSection(hooks.getDriver());
        headerPGSection.waitLoadHeaderPGPage();
        headerPGSection.clickEsciButton();
        WebTool.waitTime(5);
        accediAreaRiservataPGPage = new AccediAreaRiservataPGPage(hooks.getDriver());
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

        hooks.getDriver().get(this.urlPersonaGiuridica.get("urlPortale"));

        if (!cookieConfig.isCookieEnabled()) {
            CookiesSection cookiesPage = new CookiesSection(hooks.getDriver());
            if (cookiesPage.waitLoadCookiesPage()) {
                cookiesPage.selezionaAccettaTuttiButton();
            }
        }


        SelezionaImpresaPage impresaPage = new SelezionaImpresaPage(hooks.getDriver());
        impresaPage.clickSuImpresa(this.datiPersonaGiuridica.get("ragioneSociale").toString());
        impresaPage.clickAccediButton();
    }

    @When("Login portale persona giuridica tramite token exchange {string}")
    public void loginPortalePersonaGiuridicaTramiteTokenExchange(String dpFile) {
        logger.info("Si effettua il login PG tramite token");

        String variabileAmbiente = webDriverConfig.getEnvironment();
        String urlIniziale = "https://imprese." + variabileAmbiente + ".notifichedigitali.it/#selfCareToken=";
        String token;
        String user = this.dataPopulation.readDataPopulation(dpFile + ".yaml").get("user").toString();
        if (user.equalsIgnoreCase("DanteAlighieri")) {
            token = variabileAmbiente.equalsIgnoreCase("test") ?
                    this.dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokentestPGDelegante").toString() :
                    this.dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokendevPGDelegante").toString();
        } else {
            token = variabileAmbiente.equalsIgnoreCase("test") ?
                    this.dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokentestPGDelegato").toString() :
                    this.dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokendevPGDelegato").toString();
        }
        String url = urlIniziale + token;
        this.hooks.getDriver().get(url);
    }

    public String getTokenExchangePGFromFile(String personaGiuridica) {
        DataPopulation dataPopulation = new DataPopulation();
        String environment = webDriverConfig.getEnvironment();
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
        DestinatarioPage destinatarioPage = new DestinatarioPage(hooks.getDriver());
        destinatarioPage.clickProdotto(xpath);
    }

}
