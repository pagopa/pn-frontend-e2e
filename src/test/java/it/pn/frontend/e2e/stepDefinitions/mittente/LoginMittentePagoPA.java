package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.mittente.SpidAcsMittente;
import it.pn.frontend.e2e.api.mittente.SpidLoginMittente;
import it.pn.frontend.e2e.api.mittente.SpidTestEnvWestEuropeAzureContainerIoContinueResponse;
import it.pn.frontend.e2e.api.mittente.SpidTestEnvWestEuropeAzureContainerIoLogin;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.pages.mittente.*;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.cookie.BasicClientCookie;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//@Component
//@Scope("prototype")
@Primary
public class LoginMittentePagoPA {
    private static final Logger logger = LoggerFactory.getLogger("LoginMittentePagoPA");

    // WebDriver gestito con Spring, inizializzato in modo lazy per ottimizzare le risorse da capire?
    //@Lazy
    //@Autowired
    //private WebDriver driver;

   @Autowired
   private DataPopulation dataPopulation;
    @Autowired
    @Lazy
    private  WebTool webTool;

    // Percorso del file token specificato nelle configurazioni, con valore di default 'tokenLogin.yaml'
   // @Value("${token.login.file:tokenLogin.yaml}")
   // private String FILE_TOKEN_LOGIN;

    private Map<String, Object> datiMittente;
    private Map<String, String> urlMittente;

    @Autowired
    private HooksNew hooks;

    @Autowired
    private WebDriverConfig webDriverConfig;

    @Autowired
    private CookiesSection cookiesSection;

    @Autowired
    private AcccediAreaRiservataPAPage acccediAreaRiservataPAPage;

    @Autowired
    private ScegliSpidPAPage scegliSpidPAPage;

    @Autowired
    private LoginPAPage loginPAPage;

    @Autowired
    private HeaderPASection headerPASection;

    @Autowired
    private PiattaformaNotifichePage piattaformaNotifichePage;

    @Autowired
    private PreAccediAreaRiservataPAPage preAccediAreaRiservataPAPage;

    @Autowired
    private AutorizziInvioDatiPAPage autorizziInvioDatiPAPage;

    @Autowired
    private SelezionaEntePAPage selezionaEntePAPage;

    @Autowired
    private AreaRiservataPAPage areaRiservataPAPage;
    @Autowired
    BasicCookieStore cookieStore;




    @Given("Login Page mittente {string} viene visualizzata")
    public void loginPageMittenteVieneVisualizzata(String datiMittenteFile) {
        logger.info("Si recupera l'ambiente e si visualizza la pagina di login 1");

        this.datiMittente = dataPopulation.readDataPopulation(datiMittenteFile + ".yaml");
        String variabileAmbiente = webDriverConfig.getEnvironment();
        switch (variabileAmbiente) {
            case "dev" ->hooks.getDriver().get(webDriverConfig.getUrlMittente());
            case "test", "uat" ->
                    hooks.getDriver().get(webDriverConfig.getUrlMittente().replace("dev", variabileAmbiente));
            default ->
                    Assertions.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
    }

    @Given("Login Page mittente viene visualizzata")
    public void loginPageMittenteVieneVisualizzata(Map<String,String> datiMittenteTable) {
        logger.info("Si recupera l'ambiente e si visualizza la pagina di login");
        String variabileAmbiente = webDriverConfig.getEnvironment();
        this.datiMittente = dataPopulation.readDataPopulation("mittente.yaml");
        switch (variabileAmbiente) {
            case "dev" -> hooks.getDriver().get(webDriverConfig.getUrlMittente());
            case "test", "uat" ->
                    hooks.getDriver().get(webDriverConfig.getUrlMittente().replace("dev", variabileAmbiente));
            default ->
                    Assertions.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
    }

    @Given("PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard")
    public void loginMittenteConTokenExchange() {

       // String environment = System.getProperty("environment");
        String environment = webDriverConfig.getEnvironment();
        String token = "";
        switch (environment) {
            case "dev" ->
                    token = webDriverConfig.getTokendevMittente();
            case "test" ->
                    token = webDriverConfig.getTokentestMittente();
            default -> {
                logger.error("Ambiente non valido");
                Assertions.fail("Ambiente non valido o non trovato!");
            }
        }

        // Si effettua il login con token exchange
        String urlLogin = "https://selfcare." + environment + ".notifichedigitali.it/#selfCareToken=" + token;
        hooks.getDriver().get(urlLogin);
        logger.info("Login effettuato con successo");
        // Attesa statica di 10 secondi - considerare l'uso di WebDriverWait per migliorare l'efficienza
        webTool.waitTime(10);

        // Si visualizza la dashboard e si verifica che gli elementi base siano presenti (header e title della pagina)
        headerPASection.waitLoadHeaderSection();
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
    }

    @When("Login con mittente {string}")
    public void loginConMittente(String datiMittenteFile) {
        logger.info("Si effetua la Login dal portale mittente");

        this.datiMittente = dataPopulation.readDataPopulation(datiMittenteFile + ".yaml");

        // Creazione dell'oggetto pagina per la gestione del pre-accesso all'area riservata
        preAccediAreaRiservataPAPage.waitLoadPreAccediAreaRiservataPAPage();
        preAccediAreaRiservataPAPage.selezionaProcediAlLoginButton();

        // Verifica della presenza dell'URL e dei cookie per proseguire con l'accettazione dei cookie
        if (hooks.getDriver().getCurrentUrl().contains(webDriverConfig.getUrlSelfCare()) ||
                !webDriverConfig.getCookieConfig().isCookieEnabled()) {
            logger.info("cookies start");

            cookiesSection.selezionaAccettaTuttiButton();
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
            logger.info("cookies end");
        }

        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();
        acccediAreaRiservataPAPage.selezionareSpidButton();

        scegliSpidPAPage.selezionareTestButton();

        loginPAPage.waitLoadLoginPAPage();
        loginPAPage.inserisciUtenete(webDriverConfig.getUserMittente());
        loginPAPage.inserisciPassword(webDriverConfig.getPwdMittente());
        loginPAPage.selezionaInviaDati();

        autorizziInvioDatiPAPage.waitLoadAutorizziInvioDatiPAPage();
        autorizziInvioDatiPAPage.selezionareInvia();

        selezionaEntePAPage.waitLoadSelezionaEntePAPage();
        selezionaEntePAPage.cercaComune(this.datiMittente.get("comune").toString());
        selezionaEntePAPage.selezionareComune(this.datiMittente.get("comune").toString());
        selezionaEntePAPage.selezionaAccedi();
    }

    @When("Login con mittente")
    public void loginConMittente(Map<String,String> datiMittenteFile) {
        logger.info("Si effetua la Login dal portale mittente");

        preAccediAreaRiservataPAPage.waitLoadPreAccediAreaRiservataPAPage();
        preAccediAreaRiservataPAPage.selezionaProcediAlLoginButton();

        if (hooks.getDriver().getCurrentUrl().contains(webDriverConfig.getUrlSelfCare()) ||
                !webDriverConfig.getCookieConfig().isCookieEnabled()) {
            logger.info("cookies start");
            cookiesSection.selezionaAccettaTuttiButton();
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
            logger.info("cookies end");
        }

        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();
        acccediAreaRiservataPAPage.selezionareSpidButton();

        scegliSpidPAPage.selezionareTestButton();

        loginPAPage.waitLoadLoginPAPage();
        loginPAPage.inserisciUtenete(webDriverConfig.getUserMittente());
        loginPAPage.inserisciPassword( webDriverConfig.getPwdMittente());
        loginPAPage.selezionaInviaDati();

        autorizziInvioDatiPAPage.waitLoadAutorizziInvioDatiPAPage();
        autorizziInvioDatiPAPage.selezionareInvia();

        selezionaEntePAPage.waitLoadSelezionaEntePAPage();
        selezionaEntePAPage.cercaComune(datiMittenteFile.get("comune"));
        selezionaEntePAPage.selezionareComune(datiMittenteFile.get("comune"));
        selezionaEntePAPage.selezionaAccedi();
    }

    @When("Login mittente tramite request method")
    public void portaleMittenteIsDisplayed() throws InterruptedException {
        String userMittente = webDriverConfig.getUserMittente();
        String pwdMittente = webDriverConfig.getPwdMittente();
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
            Assertions.fail("Codice risposta ricevuto per questo end point: '" + this.urlMittente.get("urlPortale") + "' è : " + this.urlMittente.get("responseCode"));
        }

        hooks.getDriver().get(this.urlMittente.get("urlPortale"));

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
            Assertions.fail(" api spid login risponde con body vuoto");
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

//        BasicCookieStore cookieStore = new BasicCookieStore();
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

        spidTestEnvWestEuropeAzureContainerIoContinueResponse.setSpidTestEnvWestEuropeAzureContainerIoContinueResponseEndPoint("https://selc-u-spid-testenv.westeurope.azurecontainer.io/continue-response");
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

        spidAcsMittente.setSpidAcsEndPoint("https://api.uat.selfcare.pagopa.it/spid/v1/acs");
        spidAcsMittente.runSpidAcs();
        this.urlMittente = spidAcsMittente.getSpidAcsMittenteResponse();

        if (this.urlMittente.get("urlPortale") != null) {
            logger.info("urlMittente : " + this.urlMittente.get("urlPortale"));
        } else {
            Assertions.fail("urlMittente è null ");
        }
    }


    @Then("Home page mittente viene visualizzata correttamente")
    public void homePageMittenteVieneVisualizzataCorrettamente() {
        logger.info("Home page mittente viene visualizzata correttamente");

        headerPASection.waitLoadHeaderSection();

        areaRiservataPAPage.waitLoadAreaRiservataPAPage();
        if (areaRiservataPAPage.verificaCodiceFiscale(this.datiMittente.get("codiceFiscale").toString())) {
            logger.info("Codice fiscale presente");
        } else {
            logger.info("Codice fiscale non presente o errato");
        }

        // Uso di attesa fissa di 5 secondi - considerare l'uso di WebDriverWait per migliorare la stabilità del test
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("Logout da portale mittente")
    public void logoutDaPortaleMittente() {
        logger.info("Si esce dal portale mittente");

        webTool.waitTime(2);

        headerPASection.waitLoadHeaderSection();
        headerPASection.selezionaEsciButton();

        if (!webDriverConfig.getCookieConfig().isCookieEnabled()) {
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }

        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();

        webTool.waitTime(5);


    }

    @When("Login con mittente tramite token exchange")
    public void loginConMittenteTramiteTokenExchange() {
        logger.info("Si effettua la login del mittente tramite token");

        String variabileAmbiente = webDriverConfig.getEnvironment();
        String urlInziale = "https://selfcare." + variabileAmbiente + ".notifichedigitali.it/#selfCareToken=";
        String token;

        if (variabileAmbiente.equalsIgnoreCase("test")) {
            token = webDriverConfig.getTokentestMittente();
        } else {
            token = webDriverConfig.getTokendevMittente();
        }
        String url = urlInziale + token;
        hooks.getDriver().get(url);
    }

    @And("Si clicca sul bottone test")
    public void clickTestButton() {
        acccediAreaRiservataPAPage.clickTestBottone();
    }

    @And("Si clicca bottone accetta cookies")
    public void clickAcceptCookies() {
        // Gestione della sezione cookies, accettando i cookie se necessario
        if (cookiesSection.waitLoadCookiesPage()) {
            cookiesSection.selezionaAccettaTuttiButton();
        }
    }

    @And("Si clicca sul bottone esci")
    public void siCLiccaSulBottoneEsci() {
        headerPASection.selezionaEsciButton();
    }
}
