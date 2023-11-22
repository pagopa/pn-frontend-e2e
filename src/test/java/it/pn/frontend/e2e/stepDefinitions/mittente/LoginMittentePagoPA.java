package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.mittente.SpidAcsMittente;
import it.pn.frontend.e2e.api.mittente.SpidLoginMittente;
import it.pn.frontend.e2e.listeners.Hooks;

import it.pn.frontend.e2e.pages.mittente.*;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.cookie.BasicClientCookie;
import it.pn.frontend.e2e.api.mittente.SpidTestenvWesteuropeAzurecontainerIoLogin;
import it.pn.frontend.e2e.api.mittente.SpidTestenvWesteuropeAzurecontainerIoContinueResponse;


public class LoginMittentePagoPA {

    private static final Logger logger = LoggerFactory.getLogger("LoginMittentePagoPA");
    private Map<String, Object> datiMittente;

    private Map<String, String> urlMittente;
    private final WebDriver driver = Hooks.driver;



    @Given("Login Page mittente {string} viene visualizzata")
    public void login_page_mittente_viene_visualizzata(String datiMittenteFile) {
        logger.info("Si recupera l'ambiente e si visualizza la pagina di login");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiMittente = dataPopulation.readDataPopulation(datiMittenteFile + ".yaml");
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> this.driver.get(this.datiMittente.get("url").toString());
            case "test", "uat" -> this.driver.get(this.datiMittente.get("url").toString().replace("dev",variabileAmbiente));
            default -> Assert.fail("Non stato possibile trovare l'ambiente inserito, Insaerisci in -Denvironment test o dev o uat");
        }
    }

    @When("Login con mittente {string}")
    public void login_con_mittente(String datiMittenteFile) {
        logger.info("Si effetua la Login dal portale mittente");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiMittente = dataPopulation.readDataPopulation(datiMittenteFile+".yaml");

        CookiesSection cookiesPage = new CookiesSection(this.driver);
        if (cookiesPage.waitLoadCookiesPage()){
            cookiesPage.selezionaAccettaTuttiButton();
        }

        PreAccediAreaRiservataPAPage preAccediAreaRiservataPAPage = new PreAccediAreaRiservataPAPage(this.driver);
        preAccediAreaRiservataPAPage.waitLoadPreAccediAreaRiservataPAPage();
        preAccediAreaRiservataPAPage.selezionaProcediAlLoginButton();

        //CookiesSection cookiesPage = new CookiesSection(this.driver);
         if (cookiesPage.waitLoadCookiesPage()){
             cookiesPage.selezionaAccettaTuttiButton();
         }

        AcccediAreaRiservataPAPage acccediAreaRiservataPAPage = new AcccediAreaRiservataPAPage(this.driver);
        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();
        acccediAreaRiservataPAPage.selezionareSipButton();

        ScegliSpidPAPage scegliSpidPAPage = new ScegliSpidPAPage(this.driver);
    /*    scegliSpidPAPage.waitLoadScegliSpidPAPage();
        if (cookiesPage.waitLoadCookiesPage()){
            cookiesPage.selezionaAccettaTuttiButton();
        } */
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

    @When("Login mittente tramite request method")
    public void portaleMittenteIsDisplayed() throws InterruptedException {
        String userMittente = this.datiMittente.get("user").toString();
        String pwdMittente = this.datiMittente.get("pwd").toString();
        this.readurlPortaleMittente(userMittente,pwdMittente);
        boolean urlWithTokenFound = false;
        int numProvaLogin = 0;
        while(numProvaLogin<10){
            this.readurlPortaleMittente(userMittente,pwdMittente);
            if(this.urlMittente.get("responseCode").equalsIgnoreCase("301")){
                urlWithTokenFound = true;
                break;
            }
            TimeUnit.SECONDS.sleep(18);
            numProvaLogin++;
        }

        if(urlWithTokenFound){
            logger.info("procedura di login from spid provata : "+numProvaLogin);
        }else{
            logger.info("procedura di login from spid provata : "+numProvaLogin);
            Assert.fail("Codice risposta ricevuto per questo end point: '"+this.urlMittente.get("urlPortale")+"' è : "+this.urlMittente.get("responseCode"));
        }

        this.driver.get(this.urlMittente.get("urlPortale"));

        CookiesSection cookiesPage = new CookiesSection(this.driver);
        if (cookiesPage.waitLoadCookiesPage()){
            cookiesPage.selezionaAccettaTuttiButton();
        }

        SelezionaEntePAPage selezionaEntePAPage = new SelezionaEntePAPage(this.driver);
        selezionaEntePAPage.waitLoadSelezionaEntePAPage();
        selezionaEntePAPage.cercaComune(this.datiMittente.get("comune").toString());
        selezionaEntePAPage.selezionareComune(this.datiMittente.get("comune").toString());
        selezionaEntePAPage.selezionaAccedi();
    }

    private void readurlPortaleMittente(String user, String password){

        SpidLoginMittente spidLoginMittente = new SpidLoginMittente("xx_testenv2","SpidL2");
        spidLoginMittente.setSpidLoginMittenteEndPoint("https://api.uat.selfcare.pagopa.it/spid/v1/login");
        spidLoginMittente.runSpidLoginMittente();
        if(spidLoginMittente.getResponseBody() == null){
            Assert.fail(" api spid login risponde con body vuoto");
        }

        String cookiesNameFromSpidLoginMittente = spidLoginMittente.getCookieName();
        if(cookiesNameFromSpidLoginMittente != null){
            logger.info("cookiesNameFromSpidLoginMittente : "+cookiesNameFromSpidLoginMittente);
        }else{
            Assert.fail("cookiesNameFromSpidLoginMittente is null");
        }

        String cookiesValueFromSpidLoginMittente = spidLoginMittente.getCookieValue();
        if(cookiesValueFromSpidLoginMittente != null){
            logger.info("cookiesValueFromSpidLoginMittente : "+cookiesValueFromSpidLoginMittente);
        }else{
            Assert.fail("cookiesValueFromSpidLoginMittente is null");
        }

        String cookiesDomainFromSpidLoginMittente = spidLoginMittente.getCookieDomain();
        if(cookiesDomainFromSpidLoginMittente != null){
            logger.info("cookiesDomainFromSpidLoginMittente : "+cookiesDomainFromSpidLoginMittente);
        }else{
            Assert.fail("cookiesDomainFromSpidLoginMittente is null");
        }

        String cookiesPathFromSpidLoginMittente = spidLoginMittente.getCookiePath();
        if(cookiesPathFromSpidLoginMittente != null){
            logger.info("cookiesPathFromSpidLoginMittente : "+cookiesPathFromSpidLoginMittente);
        }else{
            Assert.fail("cookiesPathFromSpidLoginMittente is null");
        }

        boolean cookiesHttOnlyFromSpidLoginMittente = spidLoginMittente.getCookieHttpOnly();
        if(cookiesHttOnlyFromSpidLoginMittente){
            logger.info("cookiesHttOnlyFromSpidLoginMittente : "+cookiesHttOnlyFromSpidLoginMittente);
        }else{
            Assert.fail("cookiesHttOnlyFromSpidLoginMittente : "+cookiesHttOnlyFromSpidLoginMittente);
        }

        String requestKeyFromSpidLoginMittente = spidLoginMittente.getRequestKey();
        if(requestKeyFromSpidLoginMittente != null){
            logger.info("requestKeyFromSpidLoginMittente : "+requestKeyFromSpidLoginMittente);
        }else{
            Assert.fail("requestKeyFromSpidLoginMittente is null");
        }

        String relayStateFromSpidLoginMittente = spidLoginMittente.getRelayState();
        if(relayStateFromSpidLoginMittente != null){
            logger.info("relayStateFromSpidLoginMittente : "+relayStateFromSpidLoginMittente);
        }else{
            Assert.fail("relayStateFromSpidLoginMittente is null");
        }

        BasicCookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie(cookiesNameFromSpidLoginMittente, cookiesValueFromSpidLoginMittente);
        cookie.setDomain(cookiesDomainFromSpidLoginMittente);
        cookie.setPath(cookiesPathFromSpidLoginMittente);
        cookie.setHttpOnly(cookiesHttOnlyFromSpidLoginMittente);
        cookieStore.addCookie(cookie);

        SpidTestenvWesteuropeAzurecontainerIoLogin spidTestenvWesteuropeAzurecontainerIoLogin =
                new SpidTestenvWesteuropeAzurecontainerIoLogin(
                        requestKeyFromSpidLoginMittente,
                        relayStateFromSpidLoginMittente,
                        user,password,
                        cookieStore
                );
        spidTestenvWesteuropeAzurecontainerIoLogin.setSpidTestenvWesteuropeAzurecontainerIoLoginEndPoint("https://selc-u-spid-testenv.westeurope.azurecontainer.io/login");
        spidTestenvWesteuropeAzurecontainerIoLogin.runSpidTestenvWesteuropeAzurecontainerIoLogin();

        if(spidTestenvWesteuropeAzurecontainerIoLogin.getResponseBody() == null){
            Assert.fail(" api selc-u-spid-testenv.westeurope.azurecontainer.io/login ha risposto con body vuoto");
        }

        String requestKeyFromSpidTestenvWesteuropeAzurecontainerIoLogin = spidTestenvWesteuropeAzurecontainerIoLogin.getRequestKeyOutput();
        if(requestKeyFromSpidTestenvWesteuropeAzurecontainerIoLogin !=null){
            logger.info("requestKeyFromSpidTestenvWesteuropeAzurecontainerIoLogin : "+requestKeyFromSpidTestenvWesteuropeAzurecontainerIoLogin);
        }else{
            Assert.fail("requestKeyFromSpidTestenvWesteuropeAzurecontainerIoLogin is null");
        }

        SpidTestenvWesteuropeAzurecontainerIoContinueResponse spidTestenvWesteuropeAzurecontainerIoContinueResponse =
                new SpidTestenvWesteuropeAzurecontainerIoContinueResponse(
                        requestKeyFromSpidTestenvWesteuropeAzurecontainerIoLogin, cookieStore
                );

        spidTestenvWesteuropeAzurecontainerIoContinueResponse.setSpidTestenvWesteuropeAzurecontainerIoContinueResponseEndPoint("https://selc-u-spid-testenv.westeurope.azurecontainer.io/continue-response");
        spidTestenvWesteuropeAzurecontainerIoContinueResponse.runSpidTestenvWesteuropeAzurecontainerIoContinueResponse();
        if(spidTestenvWesteuropeAzurecontainerIoContinueResponse.getResponseBody() == null){
            Assert.fail(" api selc-u-spid-testenv.westeurope.azurecontainer.io/continue-response");
        }

        String samlResponseFromSpidTestenvWesteuropeAzurecontainerIoContinueResponse = spidTestenvWesteuropeAzurecontainerIoContinueResponse.getSamlResponseOutput();
        if(samlResponseFromSpidTestenvWesteuropeAzurecontainerIoContinueResponse != null){
            logger.info("samlResponseFromSpidTestenvWesteuropeAzurecontainerIoContinueResponse : "+samlResponseFromSpidTestenvWesteuropeAzurecontainerIoContinueResponse);
        }else{
            Assert.fail("samlResponseFromSpidTestenvWesteuropeAzurecontainerIoContinueResponse is null");
        }

        String relayStateFromSpidTestenvWesteuropeAzurecontainerIoContinueResponse = spidTestenvWesteuropeAzurecontainerIoContinueResponse.getRelayStateOutput();
        if(relayStateFromSpidTestenvWesteuropeAzurecontainerIoContinueResponse != null){
            logger.info("relayStateFromSpidTestenvWesteuropeAzurecontainerIoContinueResponse : "+relayStateFromSpidTestenvWesteuropeAzurecontainerIoContinueResponse);
        }else{
            Assert.fail("relayStateFromSpidTestenvWesteuropeAzurecontainerIoContinueResponse is null");
        }

        SpidAcsMittente spidAcsMittente = new SpidAcsMittente(
                samlResponseFromSpidTestenvWesteuropeAzurecontainerIoContinueResponse,
                relayStateFromSpidTestenvWesteuropeAzurecontainerIoContinueResponse,
                cookieStore
        );

        spidAcsMittente.setSpidAcsEndPoint("https://api.uat.selfcare.pagopa.it/spid/v1/acs");
        spidAcsMittente.runSpidAcs();
        this.urlMittente = spidAcsMittente.getSpidAcsMittenteResponse();

        if(this.urlMittente.get("urlPortale") != null){
            logger.info("urlMittente : "+this.urlMittente.get("urlPortale"));
        }else{
            Assert.fail("urlMittente è null ");
        }
    }



    @Then("Home page mittente viene visualizzata correttamente")
    public void home_page_mittente_viene_visualizzata_correttamente() {
        logger.info("Home page mittente viene visualizzata correttamente");
        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        AreaRiservataPAPage areaRiservataPAPage = new AreaRiservataPAPage(this.driver);
        areaRiservataPAPage.waitLoadAreaRiservataPAPage();
        if(areaRiservataPAPage.verificaCodiceFiscale(this.datiMittente.get("codiceFiscale").toString())){
            logger.info("Codice fiscale presente");
        }else {
            logger.info("Codice fiscale non presente o errato");
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    @And("Logout da portale mittente")
    public void Logout_da_portale_mittente() {
        logger.info("Si esce dal portale mittente");
        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();
        headerPASection.selezionaEsciButton();

        SelezionaEntePAPage selezionaEntePAPage = new SelezionaEntePAPage(this.driver);
        selezionaEntePAPage.waitLoadSelezionaEntePAPage();

        headerPASection.waitLoadHeaderSection();
        headerPASection.selezionaEsciButton();

        AcccediAreaRiservataPAPage acccediAreaRiservataPAPage = new AcccediAreaRiservataPAPage(this.driver);
        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    @And("Logout da portale mittente senza entrare su notifiche")
    public void Logout_da_portale_mittente_senza_entrare_su_notifiche() {
        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();
        headerPASection.selezionaEsciButton();

        AcccediAreaRiservataPAPage acccediAreaRiservataPAPage = new AcccediAreaRiservataPAPage(this.driver);
        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
