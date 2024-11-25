package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.mittente.SpidAcsMittente;
import it.pn.frontend.e2e.api.mittente.SpidLoginMittente;
import it.pn.frontend.e2e.api.mittente.SpidTestEnvWestEuropeAzureContainerIoContinueResponse;
import it.pn.frontend.e2e.api.mittente.SpidTestEnvWestEuropeAzureContainerIoLogin;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.config.WebDriverManager;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.*;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.HeaderPGSection;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.cookie.BasicClientCookie;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//@Component inserire in un secondo momenti

public class LoginPGPagoPATest extends BasePage {

    private final Logger logger = LoggerFactory.getLogger(LoginPGPagoPATest.class);
    private Map<String, Object> datiPersonaGiuridica = new HashMap<>();
    private Map<String, String> urlPersonaGiuridica;


    @Autowired
    private WebDriverConfig webDriverConfig;

    //TODO da rimuovere anche il discorso dei file yaml..
    @Autowired
    private  DataPopulation dataPopulation;


    private  HeaderPGSection headerPGSection;

    private  AccediAreaRiservataPGPage accediAreaRiservataPGPage;

    private  SelezionaImpresaPage selezionaImpresaPage;

    private  PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage;

    private  HomePagePG homePagePG;

    private  ScegliSpidPGPage scegliSpidPGPage;

    private  LoginPGPagoPAPage loginPGPagoPAPage;

    private  AutorizzaInvioDatiPGPage autorizzaInvioDatiPGPage;

    private  DestinatarioPage destinatarioPage;

    private  CookiesSection cookiesSection;

    private  WebTool webTool;


    @Autowired
    @Lazy
    private WebDriverManager webDriverManager;

    @PostConstruct
    public void init(){
        webTool = new WebTool(driver);
        headerPGSection = new HeaderPGSection(driver);
        accediAreaRiservataPGPage = new AccediAreaRiservataPGPage(driver);
        selezionaImpresaPage = new SelezionaImpresaPage(driver);
        piattaformaNotifichePGPAPage  = new PiattaformaNotifichePGPAPage(driver);
        homePagePG = new HomePagePG(driver);
        scegliSpidPGPage = new ScegliSpidPGPage(driver);
        loginPGPagoPAPage = new LoginPGPagoPAPage(driver);
        autorizzaInvioDatiPGPage = new AutorizzaInvioDatiPGPage(driver);
        destinatarioPage = new DestinatarioPage(driver);
        cookiesSection = new CookiesSection(driver);

    }


    @Given("Login Page persona giuridica viene visualizzata")
    public void loginPagePersonaGiuridicaVieneVisualizzata() {
        String variabileAmbiente = webDriverConfig.getEnvironment();
        switch (variabileAmbiente) {
            case "dev" -> driver.get(webDriverConfig.getBaseUrlPgDev());
            case "test", "uat" ->
                    driver.get(webDriverConfig.getBaseUrlPgTest().replace("dev", variabileAmbiente));
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
                    webDriverConfig.getTokendevPGDelegante()
                    :
                    webDriverConfig.getTokendevPGDelegato();
            case "test" -> token = personaGiuridica.equalsIgnoreCase("delegante") ?
                    webDriverConfig.getTokentestPGDelegante()
                    :
                    webDriverConfig.getTokentestPGDelegato();
            default -> {
                logger.error("Ambiente non valido");
                Assertions.fail("Ambiente non valido o non trovato!");
            }
        }


        String urlLogin = "https://imprese." + environment + ".notifichedigitali.it/#selfCareToken=" + token;
        driver.get(urlLogin);
        logger.info("Login effettuato con successo");

        webTool.waitTime(10);

        headerPGSection.waitLoadHeaderPGPage();

        if (personaGiuridica.equalsIgnoreCase("delegante")) {
            Map<String, Object> personaGiuridicaFile = dataPopulation.readDataPopulation("personaGiuridica.yaml");
            piattaformaNotifichePGPAPage.waitLoadPiattaformaNotificaPage(personaGiuridicaFile.get("ragioneSociale").toString());
        } else if (personaGiuridica.equalsIgnoreCase("baldassarre")) {
            piattaformaNotifichePGPAPage.waitLoadPiattaformaNotificaPage(webDriverConfig.getRagioneSocialeBaldassarre());
        } else {
            Map<String, Object> personaGiuridicaFile = dataPopulation.readDataPopulation("delegatoPG.yaml");
            piattaformaNotifichePGPAPage.waitLoadPiattaformaNotificaPage(personaGiuridicaFile.get("ragioneSociale").toString());
        }
    }

    @When("Login portale persona giuridica tramite request method")
    public void loginPortalePersonaGiuridicaTramiteRequestMethod() {
       // this.datiPersonaGiuridica = dataPopulation.readDataPopulation("personaGiuridica.yaml");
        String userMittente = webDriverConfig.getUserDante();
        String pwdMittente = webDriverConfig.getPwdDante();
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

        driver.get(this.urlPersonaGiuridica.get("urlPortale"));

        if (!webDriverManager.getCookieConfig().isCookieEnabled()) {
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }


        selezionaImpresaPage.clickSuImpresa(this.datiPersonaGiuridica.get("ragioneSociale").toString());
        selezionaImpresaPage.clickAccediButton();
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
        headerPGSection.waitLoadHeaderPGPage();
        homePagePG.waitLoadHomePagePGPage();
    }

    @When("Login con persona giuridica")
    public void loginConPersonaGiuridica(Map<String, String> datiPG) {
        logger.info("La persona guiridica cerca di fare il login");

        if (!webDriverManager.getCookieConfig().isCookieEnabled()) {
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }

        accediAreaRiservataPGPage.waitLoadAccediAreaRiservataPGPage();
        accediAreaRiservataPGPage.clickSpidButton();

        scegliSpidPGPage.clickTestButton();

        loginPGPagoPAPage.waitLoadLoginPGPage();
        loginPGPagoPAPage.insertUsername(webDriverConfig.getUserDante());
        loginPGPagoPAPage.insertPassword(webDriverConfig.getPwdDante());

        loginPGPagoPAPage.clickInviaButton();

        webTool.waitTime(5);
        autorizzaInvioDatiPGPage.waitLoadAutorizzaInvioDatiPGPage();
        logger.info("HTML..."+ driver.getPageSource());
        webTool.waitTime(2);
        autorizzaInvioDatiPGPage.clickInviaButton();

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
        webTool.waitTime(5);
        accediAreaRiservataPGPage.waitLoadAccediAreaRiservataPGPage();

        webTool.waitTime(5);
    }

    @When("Login {string} portale persona giuridica tramite request method")
    public void loginPortalePersonaGiuridicaTramiteRequestMethod(String dpFile) {
        this.datiPersonaGiuridica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        String userMittente = webDriverConfig.getUserDante();
        String pwdMittente = webDriverConfig.getPwdDante();
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

        driver.get(this.urlPersonaGiuridica.get("urlPortale"));

        if (!webDriverManager.getCookieConfig().isCookieEnabled()) {
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }


        selezionaImpresaPage.clickSuImpresa(this.datiPersonaGiuridica.get("ragioneSociale").toString());
        selezionaImpresaPage.clickAccediButton();
    }

    @When("Login portale persona giuridica tramite token exchange {string}")
    public void loginPortalePersonaGiuridicaTramiteTokenExchange(String dpFile) {
        logger.info("Si effettua il login PG tramite token");

        String variabileAmbiente = webDriverConfig.getEnvironment();
        String urlIniziale = "https://imprese." + variabileAmbiente + ".notifichedigitali.it/#selfCareToken=";
        String token;
        String user = webDriverConfig.getUserDante();
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
        driver.get(url);
    }

    public String getTokenExchangePGFromFile(String personaGiuridica) {
        String environment = webDriverConfig.getEnvironment();
        String token = "";
        switch (environment) {
            case "dev" -> token = personaGiuridica.equalsIgnoreCase("delegante") ?
                    webDriverConfig.getTokendevPGDelegante()
                    :
                    webDriverConfig.getTokendevPGDelegato();
            case "test" -> token = personaGiuridica.equalsIgnoreCase("delegante") ?
                    webDriverConfig.getTokentestPGDelegante()
                    :
                    webDriverConfig.getTokentestPGDelegato();
            default -> {
                logger.error("Ambiente non valido");
                Assertions.fail("Ambiente non valido o non trovato!");
            }
        }
        return token;
    }

    @And("Si clicca su prodotto {string}")
    public void siCliccaSuProdotto(String xpath) {
        destinatarioPage.clickProdotto(xpath);
    }

}
