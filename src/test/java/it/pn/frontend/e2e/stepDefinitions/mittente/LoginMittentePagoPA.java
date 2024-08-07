package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.mittente.SpidAcsMittente;
import it.pn.frontend.e2e.api.mittente.SpidLoginMittente;
import it.pn.frontend.e2e.api.mittente.SpidTestEnvWestEuropeAzureContainerIoContinueResponse;
import it.pn.frontend.e2e.api.mittente.SpidTestEnvWestEuropeAzureContainerIoLogin;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.mittente.*;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;
import it.pn.frontend.e2e.utility.CookieConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.cookie.BasicClientCookie;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;


public class LoginMittentePagoPA {
    private static final Logger logger = LoggerFactory.getLogger("LoginMittentePagoPA");
    private final WebDriver driver = Hooks.driver;
    private final String FILE_TOKEN_LOGIN = "tokenLogin.yaml";
    private Map<String, Object> datiMittente;
    private Map<String, String> urlMittente;

    @Given("Login Page mittente {string} viene visualizzata")
    public void loginPageMittenteVieneVisualizzata(String datiMittenteFile) {
        logger.info("Si recupera l'ambiente e si visualizza la pagina di login");

        DataPopulation dataPopulation = new DataPopulation();
        this.datiMittente = dataPopulation.readDataPopulation(datiMittenteFile + ".yaml");
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> this.driver.get(this.datiMittente.get("url").toString());
            case "test", "uat" ->
                    this.driver.get(this.datiMittente.get("url").toString().replace("dev", variabileAmbiente));
            default ->
                    Assert.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
    }

    @Given("Login Page mittente viene visualizzata")
    public void loginPageMittenteVieneVisualizzata(Map<String,String> datiMittenteTable) {
        logger.info("Si recupera l'ambiente e si visualizza la pagina di login");
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> this.driver.get(datiMittenteTable.get("url"));
            case "test", "uat" ->
                    this.driver.get(datiMittenteTable.get("url").replace("dev", variabileAmbiente));
            default ->
                    Assert.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
    }

    @Given("PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard")
    public void loginMittenteConTokenExchange() {
        DataPopulation dataPopulation = new DataPopulation();
        String environment = System.getProperty("environment");
        String token = "";
        switch (environment) {
            case "dev" ->
                    token = dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokendevMittente").toString();
            case "test" ->
                    token = dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokentestMittente").toString();
            default -> {
                logger.error("Ambiente non valido");
                Assert.fail("Ambiente non valido o non trovato!");
            }
        }

        // Si effettua il login con token exchange
        String urlLogin = "https://selfcare." + environment + ".notifichedigitali.it/#selfCareToken=" + token;
        this.driver.get(urlLogin);
        logger.info("Login effettuato con successo");
        WebTool.waitTime(10);

        // Si visualizza la dashboard e si verifica che gli elementi base siano presenti (header e title della pagina)
        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
    }

    @When("Login con mittente {string}")
    public void loginConMittente(String datiMittenteFile) {
        logger.info("Si effetua la Login dal portale mittente");

        DataPopulation dataPopulation = new DataPopulation();
        this.datiMittente = dataPopulation.readDataPopulation(datiMittenteFile + ".yaml");

        PreAccediAreaRiservataPAPage preAccediAreaRiservataPAPage = new PreAccediAreaRiservataPAPage(this.driver);
        preAccediAreaRiservataPAPage.waitLoadPreAccediAreaRiservataPAPage();
        preAccediAreaRiservataPAPage.selezionaProcediAlLoginButton();

        if (driver.getCurrentUrl().contains("https://uat.selfcare.pagopa.it/") ||
                !CookieConfig.isCookieEnabled()) {
            logger.info("cookies start");
            CookiesSection cookiesPage;
            cookiesPage = new CookiesSection(this.driver);
            cookiesPage.selezionaAccettaTuttiButton();
            if (cookiesPage.waitLoadCookiesPage()) {
                cookiesPage.selezionaAccettaTuttiButton();
            }
            logger.info("cookies end");
        }

        AcccediAreaRiservataPAPage acccediAreaRiservataPAPage = new AcccediAreaRiservataPAPage(this.driver);
        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();
        acccediAreaRiservataPAPage.selezionareSpidButton();

        ScegliSpidPAPage scegliSpidPAPage = new ScegliSpidPAPage(this.driver);

        scegliSpidPAPage.selezionareTestButton();

        LoginPAPage loginPAPage = new LoginPAPage(this.driver);
        loginPAPage.waitLoadLoginPAPage();
        loginPAPage.inserisciUtenete(this.datiMittente.get("user").toString());
        loginPAPage.inserisciPassword(this.datiMittente.get("pwd").toString());
        loginPAPage.selezionaInviaDati();

        AutorizziInvioDatiPAPage autorizziInvioDatiPAPage = new AutorizziInvioDatiPAPage(this.driver);
        autorizziInvioDatiPAPage.waitLoadAutorizziInvioDatiPAPage();
        autorizziInvioDatiPAPage.selezionareInvia();

        SelezionaEntePAPage selezionaEntePAPage = new SelezionaEntePAPage(this.driver);
        selezionaEntePAPage.waitLoadSelezionaEntePAPage();
        selezionaEntePAPage.cercaComune(this.datiMittente.get("comune").toString());
        selezionaEntePAPage.selezionareComune(this.datiMittente.get("comune").toString());
        selezionaEntePAPage.selezionaAccedi();
    }

    @When("Login con mittente")
    public void loginConMittente(Map<String,String> datiMittenteFile) {
        logger.info("Si effetua la Login dal portale mittente");

        PreAccediAreaRiservataPAPage preAccediAreaRiservataPAPage = new PreAccediAreaRiservataPAPage(this.driver);
        preAccediAreaRiservataPAPage.waitLoadPreAccediAreaRiservataPAPage();
        preAccediAreaRiservataPAPage.selezionaProcediAlLoginButton();

        if (driver.getCurrentUrl().contains("https://uat.selfcare.pagopa.it/") ||
                !CookieConfig.isCookieEnabled()) {
            logger.info("cookies start");
            CookiesSection cookiesPage;
            cookiesPage = new CookiesSection(this.driver);
            cookiesPage.selezionaAccettaTuttiButton();
            if (cookiesPage.waitLoadCookiesPage()) {
                cookiesPage.selezionaAccettaTuttiButton();
            }
            logger.info("cookies end");
        }

        AcccediAreaRiservataPAPage acccediAreaRiservataPAPage = new AcccediAreaRiservataPAPage(this.driver);
        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();
        acccediAreaRiservataPAPage.selezionareSpidButton();

        ScegliSpidPAPage scegliSpidPAPage = new ScegliSpidPAPage(this.driver);

        scegliSpidPAPage.selezionareTestButton();

        LoginPAPage loginPAPage = new LoginPAPage(this.driver);
        loginPAPage.waitLoadLoginPAPage();
        loginPAPage.inserisciUtenete(datiMittenteFile.get("user"));
        loginPAPage.inserisciPassword(datiMittenteFile.get("pwd"));
        loginPAPage.selezionaInviaDati();

        AutorizziInvioDatiPAPage autorizziInvioDatiPAPage = new AutorizziInvioDatiPAPage(this.driver);
        autorizziInvioDatiPAPage.waitLoadAutorizziInvioDatiPAPage();
        autorizziInvioDatiPAPage.selezionareInvia();

        SelezionaEntePAPage selezionaEntePAPage = new SelezionaEntePAPage(this.driver);
        selezionaEntePAPage.waitLoadSelezionaEntePAPage();
        selezionaEntePAPage.cercaComune(datiMittenteFile.get("comune"));
        selezionaEntePAPage.selezionareComune(datiMittenteFile.get("comune"));
        selezionaEntePAPage.selezionaAccedi();
    }

    @When("Login mittente tramite request method")
    public void portaleMittenteIsDisplayed() throws InterruptedException {
        String userMittente = this.datiMittente.get("user").toString();
        String pwdMittente = this.datiMittente.get("pwd").toString();
        this.readurlPortaleMittente(userMittente, pwdMittente);
        boolean urlWithTokenFound = false;
        int numProvaLogin = 0;
        while (numProvaLogin < 10) {
            this.readurlPortaleMittente(userMittente, pwdMittente);
            if (this.urlMittente.get("responseCode").equalsIgnoreCase("301")) {
                urlWithTokenFound = true;
                break;
            }
            TimeUnit.SECONDS.sleep(18);
            numProvaLogin++;
        }

        if (urlWithTokenFound) {
            logger.info("procedura di login from spid provata : " + numProvaLogin);
        } else {
            logger.info("procedura di login from spid provata : " + numProvaLogin);
            Assert.fail("Codice risposta ricevuto per questo end point: '" + this.urlMittente.get("urlPortale") + "' è : " + this.urlMittente.get("responseCode"));
        }

        this.driver.get(this.urlMittente.get("urlPortale"));

        SelezionaEntePAPage selezionaEntePAPage = new SelezionaEntePAPage(this.driver);
        selezionaEntePAPage.waitLoadSelezionaEntePAPage();
        selezionaEntePAPage.cercaComune(this.datiMittente.get("comune").toString());
        selezionaEntePAPage.selezionareComune(this.datiMittente.get("comune").toString());
        selezionaEntePAPage.selezionaAccedi();
    }

    private void readurlPortaleMittente(String user, String password) {

        SpidLoginMittente spidLoginMittente = new SpidLoginMittente("xx_testenv2", "SpidL2");
        spidLoginMittente.setSpidLoginMittenteEndPoint("https://api.uat.selfcare.pagopa.it/spid/v1/login");
        spidLoginMittente.runSpidLoginMittente();
        if (spidLoginMittente.getResponseBody() == null) {
            Assert.fail(" api spid login risponde con body vuoto");
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
        spidTestEnvWestEuropeAzureContainerIoLogin.setSpidTestEnvWestEuropeAzureContainerIoLoginEndPoint("https://selc-u-spid-testenv.westeurope.azurecontainer.io/login");
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

        spidTestEnvWestEuropeAzureContainerIoContinueResponse.setSpidTestEnvWestEuropeAzureContainerIoContinueResponseEndPoint("https://selc-u-spid-testenv.westeurope.azurecontainer.io/continue-response");
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

        spidAcsMittente.setSpidAcsEndPoint("https://api.uat.selfcare.pagopa.it/spid/v1/acs");
        spidAcsMittente.runSpidAcs();
        this.urlMittente = spidAcsMittente.getSpidAcsMittenteResponse();

        if (this.urlMittente.get("urlPortale") != null) {
            logger.info("urlMittente : " + this.urlMittente.get("urlPortale"));
        } else {
            Assert.fail("urlMittente è null ");
        }
    }


    @Then("Home page mittente viene visualizzata correttamente")
    public void homePageMittenteVieneVisualizzataCorrettamente() {
        logger.info("Home page mittente viene visualizzata correttamente");

        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        AreaRiservataPAPage areaRiservataPAPage = new AreaRiservataPAPage(this.driver);
        areaRiservataPAPage.waitLoadAreaRiservataPAPage();
        if (areaRiservataPAPage.verificaCodiceFiscale(this.datiMittente.get("codiceFiscale").toString())) {
            logger.info("Codice fiscale presente");
        } else {
            logger.info("Codice fiscale non presente o errato");
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @And("Logout da portale mittente")
    public void logoutDaPortaleMittente() {
        logger.info("Si esce dal portale mittente");

        WebTool.waitTime(2);


        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();
        headerPASection.selezionaEsciButton();

        if (!CookieConfig.isCookieEnabled()) {
            CookiesSection cookiesSection = new CookiesSection(this.driver);
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }

        AcccediAreaRiservataPAPage acccediAreaRiservataPAPage = new AcccediAreaRiservataPAPage(this.driver);
        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();

        WebTool.waitTime(5);


    }

    @When("Login con mittente tramite token exchange")
    public void loginConMittenteTramiteTokenExchange() {
        logger.info("Si effettua la login del mittente tramite token");

        String variabileAmbiente = System.getProperty("environment");
        String urlInziale = "https://selfcare." + variabileAmbiente + ".notifichedigitali.it/#selfCareToken=";
        String token;
        DataPopulation dataPopulation = new DataPopulation();
        if (variabileAmbiente.equalsIgnoreCase("test")) {
            token = dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokentestMittente").toString();
        } else {
            token = dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokendevMittente").toString();
        }
        String url = urlInziale + token;
        driver.get(url);
    }

    @And("Si clicca sul bottone test")
    public void clickTestButton() {
        AcccediAreaRiservataPAPage acccediAreaRiservataPAPage = new AcccediAreaRiservataPAPage(this.driver);
        acccediAreaRiservataPAPage.clickTestBottone();
    }

    @And("Si clicca bottone accetta cookies")
    public void clickAcceptCookies() {
        CookiesSection cookiesPage = new CookiesSection(this.driver);
        if (cookiesPage.waitLoadCookiesPage()) {
            cookiesPage.selezionaAccettaTuttiButton();
        }
    }

    @And("Si clicca sul bottone esci")
    public void siCLiccaSulBottoneEsci() {
        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.selezionaEsciButton();
    }
}
