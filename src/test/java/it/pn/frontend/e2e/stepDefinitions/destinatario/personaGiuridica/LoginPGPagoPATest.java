package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.mittente.SpidAcsMittente;
import it.pn.frontend.e2e.api.mittente.SpidLoginMittente;
import it.pn.frontend.e2e.api.mittente.SpidTestEnvWestEuropeAzureContainerIoContinueResponse;
import it.pn.frontend.e2e.api.mittente.SpidTestEnvWestEuropeAzureContainerIoLogin;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.*;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.HeaderPGSection;
import it.pn.frontend.e2e.utility.CookieConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.cookie.BasicClientCookie;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


    @Given("Login Page persona giuridica {string} viene visualizzata")
    public void loginPagePersonaGiuridicaVieneVisualizzata(String datiPersonaGiuridicaFile) {
        this.datiPersonaGiuridica = dataPopulation.readDataPopulation(datiPersonaGiuridicaFile + ".yaml");
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> this.driver.get(this.datiPersonaGiuridica.get("url").toString());
            case "test", "uat" ->
                    this.driver.get(this.datiPersonaGiuridica.get("url").toString().replace("dev", variabileAmbiente));
            default ->
                    Assert.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
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
            Assert.fail("Codice risposta ricevuto per questo end point: '" + this.urlPersonaGiuridica.get("urlPortale") + "' è : " + this.urlPersonaGiuridica.get("responseCode"));

        }

        this.driver.get(this.urlPersonaGiuridica.get("urlPortale"));

        if (!CookieConfig.isCookieEnabled()) {
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
            Assert.fail("api spid login risponde con body vuoto");
        }

        String cookiesNameFromSpidLoginMittente = spidLoginMittente.getCookieName();
        if (cookiesNameFromSpidLoginMittente != null) {
            logger.info("cookiesNameFromSpidLoginMittente : " + cookiesNameFromSpidLoginMittente);
        } else {
            Assert.fail("cookiesNameFromSpidLoginMittente is null");
        }

        String cookiesValueFromSpidLoginMittente = spidLoginMittente.getCookieValue();
        if (cookiesValueFromSpidLoginMittente != null) {
            logger.info("cookiesValueFromSpidLoginMittente : " + cookiesValueFromSpidLoginMittente);
        } else {
            Assert.fail("cookiesValueFromSpidLoginMittente is null");
        }

        String cookiesDomainFromSpidLoginMittente = spidLoginMittente.getCookieDomain();
        if (cookiesDomainFromSpidLoginMittente != null) {
            logger.info("cookiesDomainFromSpidLoginMittente : " + cookiesDomainFromSpidLoginMittente);
        } else {
            Assert.fail("cookiesDomainFromSpidLoginMittente is null");
        }

        String cookiesPathFromSpidLoginMittente = spidLoginMittente.getCookiePath();
        if (cookiesPathFromSpidLoginMittente != null) {
            logger.info("cookiesPathFromSpidLoginMittente : " + cookiesPathFromSpidLoginMittente);
        } else {
            Assert.fail("cookiesPathFromSpidLoginMittente is null");
        }

        boolean cookiesHttOnlyFromSpidLoginMittente = spidLoginMittente.getCookieHttpOnly();
        if (cookiesHttOnlyFromSpidLoginMittente) {
            logger.info("cookiesHttOnlyFromSpidLoginMittente : " + cookiesHttOnlyFromSpidLoginMittente);
        } else {
            Assert.fail("cookiesHttOnlyFromSpidLoginMittente : " + cookiesHttOnlyFromSpidLoginMittente);
        }

        String requestKeyFromSpidLoginMittente = spidLoginMittente.getRequestKey();
        if (requestKeyFromSpidLoginMittente != null) {
            logger.info("requestKeyFromSpidLoginMittente : " + requestKeyFromSpidLoginMittente);
        } else {
            Assert.fail("requestKeyFromSpidLoginMittente is null");
        }

        String relayStateFromSpidLoginMittente = spidLoginMittente.getRelayState();
        if (relayStateFromSpidLoginMittente != null) {
            logger.info("relayStateFromSpidLoginMittente : " + relayStateFromSpidLoginMittente);
        } else {
            Assert.fail("relayStateFromSpidLoginMittente is null");
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
            Assert.fail(" api selc-u-spid-testenv.westeurope.azurecontainer.io/login ha risposto con body vuoto");
        }

        String requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin = spidTestEnvWestEuropeAzureContainerIoLogin.getRequestKeyOutput();
        if (requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin != null) {
            logger.info("requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin : " + requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin);
        } else {
            Assert.fail("requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin is null");
        }

        SpidTestEnvWestEuropeAzureContainerIoContinueResponse spidTestEnvWestEuropeAzureContainerIoContinueResponse =
                new SpidTestEnvWestEuropeAzureContainerIoContinueResponse(
                        requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin, cookieStore
                );

        spidTestEnvWestEuropeAzureContainerIoContinueResponse.setSpidTestEnvWestEuropeAzureContainerIoContinueResponseEndPoint("https://selc-u-pnpg-spid-testenv.westeurope.azurecontainer.io/continue-response");
        spidTestEnvWestEuropeAzureContainerIoContinueResponse.runSpidTestEnvWestEuropeAzureContainerIoContinueResponse();
        if (spidTestEnvWestEuropeAzureContainerIoContinueResponse.getResponseBody() == null) {
            Assert.fail(" api selc-u-spid-testenv.westeurope.azurecontainer.io/continue-response");
        }

        String samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse = spidTestEnvWestEuropeAzureContainerIoContinueResponse.getSamlResponseOutput();
        if (samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse != null) {
            logger.info("samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse : " + samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse);
        } else {
            Assert.fail("samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse is null");
        }

        String relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse = spidTestEnvWestEuropeAzureContainerIoContinueResponse.getRelayStateOutput();
        if (relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse != null) {
            logger.info("relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse : " + relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse);
        } else {
            Assert.fail("relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse is null");
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
            Assert.fail("urlPersonaGiuridica è null ");
        }
    }

    @Then("Home page persona giuridica viene visualizzata correttamente")
    public void homePagePersonaGiuridicaVieneVisualizzataCorrettamente() {
        headerPGSection.waitLoadHeaderPGPage();
        HomePagePG homePagePG = new HomePagePG(this.driver);
        homePagePG.waitLoadHomePagePGPage();

    }

    @When("Login con persona giuridica {string}")
    public void loginConPersonaGiuridica(String nomeFile) {
        logger.info("La persona guiridica cerca di fare il login");

        this.datiPersonaGiuridica = this.dataPopulation.readDataPopulation(nomeFile + ".yaml");

        CookiesSection cookiesSection;

        if (!CookieConfig.isCookieEnabled()) {
            cookiesSection = new CookiesSection(this.driver);
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }

        PreAccediAreaRiservataPGPage preAccediAreaRiservataPGPage = new PreAccediAreaRiservataPGPage(this.driver);
        preAccediAreaRiservataPGPage.waitLoadPreAccediPage();
        preAccediAreaRiservataPGPage.clickAccediButton();

        if (!CookieConfig.isCookieEnabled()) {
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
        loginPGPagoPAPage.insertUsername(this.datiPersonaGiuridica.get("user").toString());
        loginPGPagoPAPage.insertPassword(this.datiPersonaGiuridica.get("pwd").toString());
        loginPGPagoPAPage.clickInviaButton();


        AutorizzaInvioDatiPGPage autorizzaInvioDatiPGPage = new AutorizzaInvioDatiPGPage(this.driver);
        autorizzaInvioDatiPGPage.waitLoadAutorizzaInvioDatiPGPage();
        autorizzaInvioDatiPGPage.clickInviaButton();

        SelezionaImpresaPage selezionaImpresaPage = new SelezionaImpresaPage(this.driver);
        selezionaImpresaPage.waitLoadSelezionaImpresaPage();
        selezionaImpresaPage.clickSuImpresa(this.datiPersonaGiuridica.get("ragioneSociale").toString());
        selezionaImpresaPage.clickAccediButton();
    }

    @And("Logout da portale persona giuridica")
    public void logoutDaPortalePersonaGiuridica() {
        headerPGSection.waitLoadHeaderPGPage();
        headerPGSection.clickEsciButton();

        accediAreaRiservataPGPage.waitLoadAccediAreaRiservataPGPage();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
            Assert.fail("Codice risposta ricevuto per questo end point: '" + this.urlPersonaGiuridica.get("urlPortale") + "' è : " + this.urlPersonaGiuridica.get("responseCode"));

        }

        this.driver.get(this.urlPersonaGiuridica.get("urlPortale"));

        if (!CookieConfig.isCookieEnabled()) {
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
}
