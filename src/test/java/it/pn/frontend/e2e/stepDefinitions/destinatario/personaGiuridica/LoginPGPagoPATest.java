package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.mittente.SpidAcsMittente;
import it.pn.frontend.e2e.api.mittente.SpidLoginMittente;
import it.pn.frontend.e2e.api.mittente.SpidTestenvWesteuropeAzurecontainerIoContinueResponse;
import it.pn.frontend.e2e.api.mittente.SpidTestenvWesteuropeAzurecontainerIoLogin;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.*;
import it.pn.frontend.e2e.pages.mittente.AutorizziInvioDatiPAPage;
import it.pn.frontend.e2e.pages.mittente.SelezionaEntePAPage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.cookie.BasicClientCookie;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginPGPagoPATest {
    private final Logger logger = LoggerFactory.getLogger("LoginPGPagoPATest");
    private final WebDriver driver = Hooks.driver;
    private Map<String, Object> datiPersonaGiuridica = new HashMap<>();

    private Map<String, String> urlPersonaGiuridica;

    DataPopulation dataPopulation = new DataPopulation();


    @Given("Login Page persona giuridica {string} viene visualizzata")
    public void login_page_persona_giuridica_viene_visualizzata(String datiPersonaGiuridicaFile) {
        this.datiPersonaGiuridica = dataPopulation.readDataPopulation(datiPersonaGiuridicaFile + ".yaml");
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> this.driver.get(this.datiPersonaGiuridica.get("url").toString());
            case "test", "uat" -> this.driver.get(this.datiPersonaGiuridica.get("url").toString().replace("dev",variabileAmbiente));
            default -> Assert.fail("Non stato possibile trovare l'ambiente inserito, Insaerisci in -Denvironment test o dev o uat");
        }
    }

    @When("Login portale persona giuridica tramite request method")
    public void loginPortalePersonaGiuridicaTramiteRequestMethod() {
        this.datiPersonaGiuridica = dataPopulation.readDataPopulation("personaGiuridica.yaml");
        String userMittente = this.datiPersonaGiuridica.get("user").toString();
        String pwdMittente = this.datiPersonaGiuridica.get("pwd").toString();
        this.readurlPortaleMittente(userMittente,pwdMittente);
        boolean urlWithTokenFound = false;
        int numProvaLogin = 0;
        while(numProvaLogin<10){
            this.readurlPortaleMittente(userMittente,pwdMittente);
            if(this.urlPersonaGiuridica.get("responseCode").equalsIgnoreCase("301")){
                urlWithTokenFound = true;
                break;
            }else {
                this.urlPersonaGiuridica.clear();
            }
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            numProvaLogin++;
        }

        if(urlWithTokenFound){
            logger.info("procedura di login from spid provata : "+numProvaLogin);
        }else{
            Assert.fail("procedura di login from spid provata : "+numProvaLogin);
        }

        this.driver.get(this.urlPersonaGiuridica.get("urlPortale"));

        CookiesSection cookiesPage = new CookiesSection(this.driver);
        if (cookiesPage.waitLoadCookiesPage()){
            cookiesPage.selezionaAccettaTuttiButton();
        }

        SelezionaEntePAPage selezionaEntePAPage = new SelezionaEntePAPage(this.driver);
        selezionaEntePAPage.waitLoadSelezionaEntePAPage();
        selezionaEntePAPage.selezionareComune(this.datiPersonaGiuridica.get("comune").toString());
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
        this.urlPersonaGiuridica = spidAcsMittente.getSpidAcsMittenteResponse();

        if(this.urlPersonaGiuridica.get("urlPortale") != null){
            logger.info("urlPersonaGiuridica : "+this.urlPersonaGiuridica.get("urlPortale"));
        }else{
            Assert.fail("urlPersonaGiuridica è null ");
        }
    }
    @Then("Home page persona giuridica viene visualizzata correttamente")
    public void homePagePersonaGiuridicaVieneVisualizzataCorrettamente() {
        
    }

    @When("Login con persona giuridica {string}")
    public void loginConPersonaGiuridica(String nomeFile) {
        logger.info("La persona guiridica cerca di fare il login");
        this.datiPersonaGiuridica = this.dataPopulation.readDataPopulation(nomeFile+".yaml");

        CookiesSection cookiesSection = new CookiesSection(this.driver);
        if (cookiesSection.waitLoadCookiesPage()){
            cookiesSection.selezionaAccettaTuttiButton();
        }

        PreAccediAreaRiservataPGPage preAccediAreaRiservataPGPage = new PreAccediAreaRiservataPGPage(this.driver);
        preAccediAreaRiservataPGPage.waitLoadPreAccediPage();
        preAccediAreaRiservataPGPage.clickAccediButton();

        if (cookiesSection.waitLoadCookiesPage()){
            cookiesSection.selezionaAccettaTuttiButton();
        }

        AcccediAreaRiservataPGPage acccediAreaRiservataPGPage = new AcccediAreaRiservataPGPage(this.driver);
        acccediAreaRiservataPGPage.waitLoadAccediAreaRiservataPGPage();
        acccediAreaRiservataPGPage.clickSpidButton();

        ScegliSpidPGPage scegliSpidPGPage = new ScegliSpidPGPage(this.driver);
        scegliSpidPGPage.waitLoadScegliSpidPGPage();
        scegliSpidPGPage.clickTestButton();

        LoginPGPagoPAPage loginPGPagoPAPage = new LoginPGPagoPAPage(this.driver);
        loginPGPagoPAPage.waitLoadLoginPGPage();
        loginPGPagoPAPage.insertUsername(this.datiPersonaGiuridica.get("user").toString());
        loginPGPagoPAPage.insertPassword(this.datiPersonaGiuridica.get("pwd").toString());
        loginPGPagoPAPage.clickInviaButton();


        AutorizziInvioDatiPGPage autorizziInvioDatiPGPage = new AutorizziInvioDatiPGPage(this.driver);
        autorizziInvioDatiPGPage.waitLoadPage();

    }
}
