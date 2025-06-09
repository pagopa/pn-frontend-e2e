package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.mittente.SpidAcsMittente;
import it.pn.frontend.e2e.api.mittente.SpidLoginMittente;
import it.pn.frontend.e2e.api.mittente.SpidTestEnvWestEuropeAzureContainerIoContinueResponse;
import it.pn.frontend.e2e.api.mittente.SpidTestEnvWestEuropeAzureContainerIoLogin;
import it.pn.frontend.e2e.config.DataPopulationConfig;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.config.WebDriverManager;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ConfermaDatiSpidPFPage;
import it.pn.frontend.e2e.pages.mittente.*;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;
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

import java.util.Map;
import java.util.concurrent.TimeUnit;


public class LoginMittentePagoPA extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("LoginMittentePagoPA");

   @Autowired
   private DataPopulation dataPopulation;

    private  WebTool webTool;

    // Percorso del file token specificato nelle configurazioni, con valore di default 'tokenLogin.yaml'
   // @Value("${token.login.file:tokenLogin.yaml}")

    private Map<String, String> urlMittente;

    @Autowired
    @Lazy
    private WebDriverConfig webDriverConfig;

    private CookiesSection cookiesSection;

    private AcccediAreaRiservataPAPage acccediAreaRiservataPAPage;

    private ScegliSpidPAPage scegliSpidPAPage;

    private LoginPAPage loginPAPage;

    private HeaderPASection headerPASection;

    private PiattaformaNotifichePage piattaformaNotifichePage;

    private PreAccediAreaRiservataPAPage preAccediAreaRiservataPAPage;

    private AutorizziInvioDatiPAPage autorizziInvioDatiPAPage;

    private SelezionaEntePAPage selezionaEntePAPage;

    private AreaRiservataPAPage areaRiservataPAPage;
    private ConfermaDatiSpidPFPage confermaDatiSpidPFPage ;

    @Autowired
    BasicCookieStore cookieStore;

    @Autowired
    private DataPopulationConfig dataPopulationConfig;

    @Autowired
    @Lazy
    private WebDriverManager webDriverManager;


    @PostConstruct
    public void init(){
        logger.info("INIT TEST...: ");
        webTool = new WebTool(driver);
        areaRiservataPAPage = new AreaRiservataPAPage(driver);
        selezionaEntePAPage = new SelezionaEntePAPage(driver);
        autorizziInvioDatiPAPage = new AutorizziInvioDatiPAPage(driver);
        preAccediAreaRiservataPAPage = new PreAccediAreaRiservataPAPage(driver);
        piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        headerPASection = new HeaderPASection(driver);
        loginPAPage = new LoginPAPage(driver);
        scegliSpidPAPage = new ScegliSpidPAPage(driver);
        acccediAreaRiservataPAPage = new AcccediAreaRiservataPAPage(driver);
        cookiesSection = new CookiesSection(driver);
        confermaDatiSpidPFPage = new ConfermaDatiSpidPFPage(driver);
    }


    @Given("Login Page mittente {string} viene visualizzata")
    public void loginPageMittenteVieneVisualizzata(String datiMittenteFile) {
        logger.info("Si recupera l'ambiente e si visualizza la pagina di login 1");

        String variabileAmbiente = webDriverConfig.getEnvironment();
        switch (variabileAmbiente) {
            case "dev" ->driver.get(webDriverConfig.getUrlMittente());
            case "test", "uat" ->
                    driver.get(webDriverConfig.getUrlMittente().replace("dev", variabileAmbiente));
            default ->
                    Assertions.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
    }

    @Given("Login Page mittente viene visualizzata")
    public void loginPageMittenteVieneVisualizzata(Map<String,String> datiMittenteTable) {
        logger.info("Si recupera l'ambiente e si visualizza la pagina di login");
        String variabileAmbiente = webDriverConfig.getEnvironment();
        switch (variabileAmbiente) {
            case "dev" -> driver.get(webDriverConfig.getUrlMittente());
            case "test", "uat" ->
                    driver.get(webDriverConfig.getUrlMittente().replace("dev", variabileAmbiente));
            default ->
                    Assertions.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
    }

    @Given("PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard")
    public void loginMittenteConTokenExchange() {

        String environment = webDriverConfig.getEnvironment();
        String token = "";
        token = webDriverConfig.getTokentestMittente();

        // Si effettua il login con token exchange
        String urlLogin = "https://selfcare." + environment + ".notifichedigitali.it/#selfCareToken=" + token;
        driver.get(urlLogin);
        logger.info("Login effettuato con successo");
        // Attesa statica di 10 secondi - considerare l'uso di WebDriverWait per migliorare l'efficienza
        webTool.waitTime(10);

        // Si visualizza la dashboard e si verifica che gli elementi base siano presenti (header e title della pagina)
        headerPASection.waitLoadHeaderSection();
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
    }

    @Given("PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard Comune di {string}")
    public void loginMittenteConTokenExchangeComuneDi(String comune) {
        //TODO Il parametro comune potrebbe servire in futuro se esiste il token exchange
        String environment = webDriverConfig.getEnvironment();
        String token = "";
        token = webDriverConfig.getTokentestMittenteViggiu();

        // Si effettua il login con token exchange
        String urlLogin = "https://selfcare." + environment + ".notifichedigitali.it/#selfCareToken=" + token;
        driver.get(urlLogin);
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

        // Creazione dell'oggetto pagina per la gestione del pre-accesso all'area riservata
        preAccediAreaRiservataPAPage.waitLoadPreAccediAreaRiservataPAPage();
        preAccediAreaRiservataPAPage.selezionaProcediAlLoginButton();

        // Verifica della presenza dell'URL e dei cookie per proseguire con l'accettazione dei cookie
        if (driver.getCurrentUrl().contains(webDriverConfig.getUrlSelfCare()) ||
                !webDriverManager.getCookieConfig().isCookieEnabled()) {
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
        selezionaEntePAPage.cercaComune(dataPopulationConfig.getMittente().getComune());
        selezionaEntePAPage.selezionareComune(dataPopulationConfig.getMittente().getComune());
        selezionaEntePAPage.selezionaAccedi();
    }

    @When("Login con mittente")
    public void loginConMittente(Map<String,String> datiMittenteFile) {
        logger.info("Si effetua la Login dal portale mittente");

//        preAccediAreaRiservataPAPage.waitLoadPreAccediAreaRiservataPAPage();
//        preAccediAreaRiservataPAPage.selezionaProcediAlLoginButton();

        if (driver.getCurrentUrl().contains(webDriverConfig.getUrlSelfCare()) ||
                !webDriverManager.getCookieConfig().isCookieEnabled()) {
            logger.info("cookies start");
            cookiesSection.selezionaAccettaTuttiButton();
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
            logger.info("cookies end");
        }

        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();
        acccediAreaRiservataPAPage.selezionareSpidButton();

        acccediAreaRiservataPAPage.bottoneConImgPagoPA();


//        scegliSpidPAPage.selezionareTestButton();

//        loginPAPage.waitLoadLoginPAPage();
        loginPAPage.inserisciUtenete(datiMittenteFile.get("user"));
        loginPAPage.inserisciPassword(datiMittenteFile.get("pwd"));
        loginPAPage.entraConSpid();

        confermaDatiSpidPFPage.selezionaConfermaButton();

//        headerPFSection.waitUrlToken();

//        loginPAPage.selezionaInviaDati();

//        autorizziInvioDatiPAPage.waitLoadAutorizziInvioDatiPAPage();
//        autorizziInvioDatiPAPage.selezionareInvia()

        if (driver.getCurrentUrl().contains(webDriverConfig.getUrlSelfCare()) ||
                !webDriverManager.getCookieConfig().isCookieEnabled()) {
            logger.info("cookies start");
            cookiesSection.selezionaAccettaTuttiButton();
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
            logger.info("cookies end");
        }

        webTool.waitTime(10);
        selezionaEntePAPage.waitLoadSelezionaEntePAPage();
        selezionaEntePAPage.cercaComune(datiMittenteFile.get("comune"));
        selezionaEntePAPage.selezionareComune(datiMittenteFile.get("comune"));
        selezionaEntePAPage.selezionaAccedi();
    }


    @When("Login con mittente Comune di {string}")
    public void loginConMittenteComuneDi(String comune) {
        logger.info("Si effetua la Login dal portale mittente");

//        preAccediAreaRiservataPAPage.waitLoadPreAccediAreaRiservataPAPage();
//        preAccediAreaRiservataPAPage.selezionaProcediAlLoginButton();

        if (driver.getCurrentUrl().contains(webDriverConfig.getUrlSelfCare()) ||
                !webDriverManager.getCookieConfig().isCookieEnabled()) {
            logger.info("cookies start");
            cookiesSection.selezionaAccettaTuttiButton();
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
            logger.info("cookies end");
        }

        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();
        acccediAreaRiservataPAPage.selezionareSpidButton();

        acccediAreaRiservataPAPage.bottoneConImgPagoPA();
//        scegliSpidPAPage.selezionareTestButton();

//        loginPAPage.waitLoadLoginPAPage();
        if(comune.equalsIgnoreCase("Viggiu")){
            loginPAPage.inserisciUtenete(webDriverConfig.getUserMittenteViggiu());
            loginPAPage.inserisciPassword(webDriverConfig.getPwdMittenteViggiu());
        }
        else {
            loginPAPage.inserisciUtenete(webDriverConfig.getUserMittente());
            loginPAPage.inserisciPassword(webDriverConfig.getPwdMittente());
        }
        loginPAPage.entraConSpid();

        confermaDatiSpidPFPage.selezionaConfermaButton();

//        loginPAPage.selezionaInviaDati();
//
//        autorizziInvioDatiPAPage.waitLoadAutorizziInvioDatiPAPage();
//        autorizziInvioDatiPAPage.selezionareInvia();
//
//        webTool.waitTime(10);
//        selezionaEntePAPage.waitLoadSelezionaEntePAPage();

        if (driver.getCurrentUrl().contains(webDriverConfig.getUrlSelfCare()) ||
                !webDriverManager.getCookieConfig().isCookieEnabled()) {
            logger.info("cookies start");
            cookiesSection.selezionaAccettaTuttiButton();
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
            logger.info("cookies end");
        }
        selezionaEntePAPage.waitLoadSelezionaEntePAPage();
        if(comune.equalsIgnoreCase("Viggiu")){
            selezionaEntePAPage.cercaComune(dataPopulationConfig.getMittente().getComuneViggiu());
            selezionaEntePAPage.selezionareComune(dataPopulationConfig.getMittente().getComuneViggiu());
        }
        else {
            selezionaEntePAPage.cercaComune(dataPopulationConfig.getMittente().getComune());
            selezionaEntePAPage.selezionareComune(dataPopulationConfig.getMittente().getComune());
        }
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
            logger.info("procedura di login from spid provata : {}", numProvaLogin);
        } else {
            Assertions.fail("Codice risposta ricevuto per questo end point: '" + this.urlMittente.get("urlPortale") + "' è : " + this.urlMittente.get("responseCode"));
        }

        driver.get(this.urlMittente.get("urlPortale"));

        selezionaEntePAPage.waitLoadSelezionaEntePAPage();
        selezionaEntePAPage.cercaComune(dataPopulationConfig.getMittente().getComune());
        selezionaEntePAPage.selezionareComune(dataPopulationConfig.getMittente().getComune());
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
            logger.info("cookiesNameFromSpidLoginMittente : {}", cookiesNameFromSpidLoginMittente);
        } else {
            Assertions.fail("cookiesNameFromSpidLoginMittente is null");
        }

        String cookiesValueFromSpidLoginMittente = spidLoginMittente.getCookieValue();
        if (cookiesValueFromSpidLoginMittente != null) {
            logger.info("cookiesValueFromSpidLoginMittente : {}", cookiesValueFromSpidLoginMittente);
        } else {
            Assertions.fail("cookiesValueFromSpidLoginMittente is null");
        }

        String cookiesDomainFromSpidLoginMittente = spidLoginMittente.getCookieDomain();
        if (cookiesDomainFromSpidLoginMittente != null) {
            logger.info("cookiesDomainFromSpidLoginMittente : {}", cookiesDomainFromSpidLoginMittente);
        } else {
            Assertions.fail("cookiesDomainFromSpidLoginMittente is null");
        }

        String cookiesPathFromSpidLoginMittente = spidLoginMittente.getCookiePath();
        if (cookiesPathFromSpidLoginMittente != null) {
            logger.info("cookiesPathFromSpidLoginMittente : {}", cookiesPathFromSpidLoginMittente);
        } else {
            Assertions.fail("cookiesPathFromSpidLoginMittente is null");
        }

        boolean cookiesHttOnlyFromSpidLoginMittente = spidLoginMittente.getCookieHttpOnly();
        if (cookiesHttOnlyFromSpidLoginMittente) {
            logger.info("cookiesHttOnlyFromSpidLoginMittente : {}", cookiesHttOnlyFromSpidLoginMittente);
        } else {
            Assertions.fail("cookiesHttOnlyFromSpidLoginMittente : " + cookiesHttOnlyFromSpidLoginMittente);
        }

        String requestKeyFromSpidLoginMittente = spidLoginMittente.getRequestKey();
        if (requestKeyFromSpidLoginMittente != null) {
            logger.info("requestKeyFromSpidLoginMittente : {}", requestKeyFromSpidLoginMittente);
        } else {
            Assertions.fail("requestKeyFromSpidLoginMittente is null");
        }

        String relayStateFromSpidLoginMittente = spidLoginMittente.getRelayState();
        if (relayStateFromSpidLoginMittente != null) {
            logger.info("relayStateFromSpidLoginMittente : {}", relayStateFromSpidLoginMittente);
        } else {
            Assertions.fail("relayStateFromSpidLoginMittente is null");
        }

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
            logger.info("requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin : {}", requestKeyFromSpidTestEnvWestEuropeAzureContainerIoLogin);
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
            logger.info("samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse : {} ", samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse);
        } else {
            Assertions.fail("samlResponseFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse is null");
        }

        String relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse = spidTestEnvWestEuropeAzureContainerIoContinueResponse.getRelayStateOutput();
        if (relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse != null) {
            logger.info("relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse : {}" , relayStateFromSpidTestEnvWestEuropeAzureContainerIoContinueResponse);
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
            logger.info("urlMittente : {}", this.urlMittente.get("urlPortale"));
        } else {
            Assertions.fail("urlMittente è null ");
        }
    }


    @Then("Home page mittente viene visualizzata correttamente")
    public void homePageMittenteVieneVisualizzataCorrettamente() {
        logger.info("Home page mittente viene visualizzata correttamente");

        headerPASection.waitLoadHeaderSection();
        webTool.waitTime(10);
        areaRiservataPAPage.waitLoadAreaRiservataPAPage();
        if (areaRiservataPAPage.verificaCodiceFiscale(dataPopulationConfig.getMittente().getCodiceFiscale())) {
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

        if (!webDriverManager.getCookieConfig().isCookieEnabled()) {
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }
//        Nuove specifiche UI
        headerPASection.selezionaSecondoEsciButtonPA();
        headerPASection.selezionaEsciButton();
        webTool.waitTime(5);
        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();
    }

    @When("Login con mittente tramite token exchange")
    public void loginConMittenteTramiteTokenExchange() {
        logger.info("Si effettua la login del mittente tramite token");

        String variabileAmbiente = webDriverConfig.getEnvironment();
        String urlInziale = "https://selfcare." + variabileAmbiente + ".notifichedigitali.it/#selfCareToken=";
        String token;

        token = webDriverConfig.getTokentestMittente();
        String url = urlInziale + token;
        driver.get(url);
    }

    @And("Si clicca sul bottone test")
    public void clickTestButton() {
        acccediAreaRiservataPAPage.clickScegliAmbienteSendBottoneMittente("forward_prod-pn-test");
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


    @And("Logout e Login con Comune di {string}")
    public void logoutELoginConComuneDi(String comune) {
        logger.info("Si esce dal portale mittente");
        webTool.waitTime(2);

        headerPASection.waitLoadHeaderSection();
        headerPASection.selezionaEsciButton();
        headerPASection.selezionaSecondoEsciButtonPA();
        webTool.waitTime(2);

        if(comune.equalsIgnoreCase("Viggiu")){
            logger.info("Entro come comune di {}",comune);
            selezionaEntePAPage.cercaComune(dataPopulationConfig.getMittente().getComuneViggiu());
            selezionaEntePAPage.selezionareComune(dataPopulationConfig.getMittente().getComuneViggiu());
        }
        else {
            selezionaEntePAPage.cercaComune(dataPopulationConfig.getMittente().getComune());
            selezionaEntePAPage.selezionareComune(dataPopulationConfig.getMittente().getComune());
        }
        selezionaEntePAPage.selezionaAccedi();
    }

    @And("Click entra su Send Mittente")
    public void clickEntraSuSendMittente() {
        String environment = webDriverConfig.getEnvironment();
        switch (environment) {
            case "dev" -> acccediAreaRiservataPAPage.clickScegliAmbienteSendBottoneMittente("forward_prod-pn-dev");
            case "test" -> acccediAreaRiservataPAPage.clickScegliAmbienteSendBottoneMittente("forward_prod-pn-test");
            case "uat" -> acccediAreaRiservataPAPage.clickScegliAmbienteSendBottoneMittente("forward_prod-pn");
            case "hotfix" -> acccediAreaRiservataPAPage.clickScegliAmbienteSendBottoneMittente("forward_prod-pn-hotfix");
            case "collaudo" -> acccediAreaRiservataPAPage.clickScegliAmbienteSendBottoneMittente("forward_prod-pn-coll");

            default -> {
                Assertions.fail("Ambiente non valido o non trovato!");
            }
        }
    }
}
