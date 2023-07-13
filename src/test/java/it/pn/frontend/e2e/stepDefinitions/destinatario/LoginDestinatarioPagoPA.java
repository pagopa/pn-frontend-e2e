package it.pn.frontend.e2e.stepDefinitions.destinatario;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.destinatario.SpidAcs;
import it.pn.frontend.e2e.api.destinatario.SpidDemoLogin;
import it.pn.frontend.e2e.api.destinatario.SpidDemoStart;
import it.pn.frontend.e2e.api.destinatario.SpidLogin;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.destinatario.*;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.HeaderDESection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginDestinatarioPagoPA {

    private static final Logger logger = LoggerFactory.getLogger("LoginDestinatarioPagoPA");
    private Map<String, Object> datiDestinatario;

    private Map<String, String> urlDestinatario;
    private final WebDriver driver = Hooks.driver;
    
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
    private Map<String, Object> datiDelegato;

    @Given("Login Page destinatario {string} viene visualizzata")
    public void login_page_destinatario_viene_visualizzata(String datiDestinatario) {
        DataPopulation dataPopulation = new DataPopulation();
        this.datiDestinatario = dataPopulation.readDataPopulation(datiDestinatario + ".yaml");
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> this.driver.get(this.datiDestinatario.get("url").toString());
            case "test", "uat" -> this.driver.get(this.datiDestinatario.get("url").toString().replace("dev",variabileAmbiente));
            default -> Assert.fail("Non stato possibile trovare l'ambiente inserito, Insaerisci in -Denvironment test o dev o uat");
        }
    }

    @When("Login con destinatario {string}")
    public void login_con_destinatario(String datiDestinatario) {
        logger.info("user destinatario : "+this.datiDestinatario.get("user").toString());
        DataPopulation dataPopulation = new DataPopulation();
        this.datiDestinatario = dataPopulation.readDataPopulation(datiDestinatario+".yaml");

        logger.info("cookies start");

        CookiesSection cookiesPage = new CookiesSection(this.driver);
        if (cookiesPage.waitLoadCookiesPage()){
            cookiesPage.selezionaAccettaTuttiButton();
        }

        logger.info("cookies end");


        AccediApiattaformaNotifichePage accediApiattaformaNotifichePage = new AccediApiattaformaNotifichePage(this.driver);
        accediApiattaformaNotifichePage.waitLoadAccediApiattaformaNotifichePage();
        accediApiattaformaNotifichePage.selezionaAccediButton();

        if (cookiesPage.waitLoadCookiesPage()){
            cookiesPage.selezionaAccettaTuttiButton();
        }

        ComeVuoiAccederePage comeVuoiAccederePage = new ComeVuoiAccederePage(this.driver);
        comeVuoiAccederePage.waitLoadComeVuoiAccederePage();
        comeVuoiAccederePage.selezionareSpidButton();

        ScegliSpidDEPage scegliSpidDEPage = new ScegliSpidDEPage(this.driver);
        scegliSpidDEPage.waitLoadScegliSpidDEPage();
        scegliSpidDEPage.selezionareTestButton();

        LoginSpidDEPage loginSpidDEPage = new LoginSpidDEPage(this.driver);
        loginSpidDEPage.waitLoadLoginSpidDEPage();
        loginSpidDEPage.inserisciUtente(this.datiDestinatario.get("user").toString());
        loginSpidDEPage.inserisciPassword(this.datiDestinatario.get("pwd").toString());
        loginSpidDEPage.selezionaEntraConSpidButton();

        ConfermaDatiSpidDEPage confermaDatiSpidDEPage = new ConfermaDatiSpidDEPage(this.driver);
        confermaDatiSpidDEPage.waitLoadConfermaDatiSpidDEPage();
        String nomeUtenteLetto = confermaDatiSpidDEPage.leggiNomeUtente();
        if(nomeUtenteLetto.equals(this.datiDestinatario.get("name").toString())){
            logger.info("nome utente letto : "+nomeUtenteLetto+" uguale a : "+this.datiDestinatario.get("name").toString());
        }else{
            logger.error("nome utente letto : "+nomeUtenteLetto+" non è uguale a : "+this.datiDestinatario.get("name").toString());
            Assert.fail("nome utente letto : "+nomeUtenteLetto+" non è uguale a : "+this.datiDestinatario.get("name").toString());
        }

        String cognomeUtenteLetto = confermaDatiSpidDEPage.leggiCogomeUtente();
        if(cognomeUtenteLetto.equals(this.datiDestinatario.get("familyName").toString())){
            logger.info("cognome utente letto : "+cognomeUtenteLetto+" uguale a : "+this.datiDestinatario.get("familyName").toString());
        }else{
            logger.error("cognome utente letto : "+cognomeUtenteLetto+" non uguale a : "+this.datiDestinatario.get("familyName").toString());
            Assert.fail("cognome utente letto : "+cognomeUtenteLetto+" non uguale a : "+this.datiDestinatario.get("familyName").toString());
        }

        String numeroFiscaleLetto = confermaDatiSpidDEPage.leggiNumeroFiscale();
        if(numeroFiscaleLetto.equals(this.datiDestinatario.get("fiscalNumber").toString())){
            logger.info("numero fiscale letto : "+numeroFiscaleLetto+" uguale a : "+this.datiDestinatario.get("fiscalNumber").toString());
        }else {
            logger.error("numero fiscale letto : "+numeroFiscaleLetto+" non uguale a : "+this.datiDestinatario.get("fiscalNumber").toString());
            Assert.fail("numero fiscale letto : "+numeroFiscaleLetto+" non uguale a : "+this.datiDestinatario.get("fiscalNumber").toString());
        }

        confermaDatiSpidDEPage.selezionaConfermaButton();
        HeaderDESection headerDESection = new HeaderDESection(this.driver);
        headerDESection.waitUrlToken();
    }

    @Then("Home page destinatario viene visualizzata correttamente")
    public void home_page_destinatario_viene_visualizzata_correttamente() {

        CookiesSection cookiesSection = new CookiesSection(this.driver);

        if (cookiesSection.waitLoadCookiesPage()){
            cookiesSection.selezionaAccettaTuttiButton();
        }
        boolean httpRequestToken = false;
       for (int index = 0; index < 30; index++){

           if (this.readHttpRequest()){
               httpRequestToken = true;
               break;
           }
           try {
               TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
       if (httpRequestToken){
           logger.info("Http token destinatario found");
       }else {
           logger.error("Http token destinatario not found");
           Assert.fail("Http token destinatario not found");
       }
        HeaderDESection headerDESection = new HeaderDESection(this.driver);
        headerDESection.waitLoadHeaderDESection();


        if (cookiesSection.waitLoadCookiesPage()){
            cookiesSection.selezionaAccettaTuttiButton();
        }

        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.waitLoadNotificheDEPage();
        if(notificheDEPage.verificaPresenzaCodiceIumTextField()){
            logger.info("text field codice ium presente");
        }else {
            logger.info("text field codice ium non presente");
            Assert.fail("text field codice ium non presente");
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("Logout da portale destinatario")
    public void logout_da_portale_destinatario() {
        HeaderDESection headerDESection = new HeaderDESection(this.driver);
        headerDESection.waitLoadHeaderDESection();
        headerDESection.selezionaprofiloUtenteMenu();
        headerDESection.selezionaVoceEsci();

        ComeVuoiAccederePage comeVuoiAccederePage = new ComeVuoiAccederePage(this.driver);
        comeVuoiAccederePage.waitLoadComeVuoiAccederePage();

        CookiesSection cookiesSection = new CookiesSection(this.driver);
        if (cookiesSection.waitLoadCookiesPage()){
            logger.info("banner dei cookies visualizzato");
            cookiesSection.selezionaAccettaTuttiButton();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("banner dei cookies sparito");
        }

        if(comeVuoiAccederePage.verificaPresenzaSpidButton()){
            logger.info("Spid Button nella pagina Come vuoi accedere portale destinatario visualizzato");
        }else {
            logger.info("Spid Button nella pagina Come vuoi accedere portale destinatario non visualizzato");
            Assert.fail("Spid Button nella pagina Come vuoi accedere portale destinatario non visualizzato");
        }



        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean readHttpRequest() {
        String variabileAmbiente = System.getProperty("environment");
        boolean urlFound = false;
        for (NetWorkInfo netWorkInfo : netWorkInfos) {
            //logger.info(netWorkInfo.getRequestId());
            logger.info(netWorkInfo.getRequestUrl());
            //logger.info(netWorkInfo.getRequestMethod());
            logger.info(netWorkInfo.getResponseStatus());
            //logger.info(netWorkInfo.getResponseBody());
            String urlToFind = "https://webapi."+variabileAmbiente+".notifichedigitali.it/token-exchange";
            urlFound = false;
            if (netWorkInfo.getRequestUrl().contains(urlToFind)) {
                urlFound = true;
                break;
            }

        }
        return urlFound;
    }

    @When("Login portale destinatario tramite request method")
    public void loginPortaleDestinatarioTramiteRequestMethod() {
        boolean urlWithTokenFound = false;
        int numProvaLogin = 0;
        String userDestinatario = this.datiDestinatario.get("user").toString();
        String pwdDestinatario = this.datiDestinatario.get("pwd").toString();
        while(numProvaLogin<10){
            this.readUrlLoginDestinatarioWithToken(userDestinatario,pwdDestinatario);
            if(this.urlDestinatario.get("responseCode").equalsIgnoreCase("301")){
                urlWithTokenFound = true;
                break;
            }else {
                this.urlDestinatario.clear();
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


        this.driver.get(this.urlDestinatario.get("urlPortale"));
    }
    private void readUrlLoginDestinatarioWithToken(String user, String pwd ){
        logger.info("spid-login");
        String variabileAmbiente = System.getProperty("environment");

        SpidLogin spidLogin = new SpidLogin("xx_testenv2","SpidL2");
        spidLogin.setSpidLoginEndPoint("https://hub-login.spid."+variabileAmbiente+".notifichedigitali.it/login");
        spidLogin.runSpidLogin();

        if(spidLogin.getResponseBody() == null){
            Assert.fail(" api spid login risponde con body vuoto");
        }

        String samlRequestFromSpidLogin = spidLogin.getSamlRequest();
        if(samlRequestFromSpidLogin != null){
            logger.info("samlRequestFromSpidLogin = "+samlRequestFromSpidLogin);
        }else{
            Assert.fail("samlRequestFromSpidLogin is null");
        }

        String relayStateFromSpidLogin = spidLogin.getRelayState();
        if(relayStateFromSpidLogin != null){
            logger.info("relayStateFromSpidLogin = "+relayStateFromSpidLogin);
        }else{
            Assert.fail("relayStateFromSpidLogin is null");
        }

        String sigAlgFromSpidLogin = spidLogin.getSigAlg();
        if(sigAlgFromSpidLogin != null){
            logger.info("sigAlgFromSpidLogin = "+sigAlgFromSpidLogin);
        }else{
            Assert.fail("sigAlgFromSpidLogin is null");
        }

        String signatureFromSpidLogin = spidLogin.getSignature();
        if(signatureFromSpidLogin != null){
            logger.info("signatureFromSpidLogin = "+signatureFromSpidLogin);
        }else{
            Assert.fail("signatureFromSpidLogin is null");
        }

        String bindingFromSpidLogin = spidLogin.getBinding();
        if(bindingFromSpidLogin != null){
            logger.info("bindingFromSpidLogin = "+bindingFromSpidLogin);
        }else{
            Assert.fail("bindingFromSpidLogin is null");
        }

        logger.info("spid demo start");

        SpidDemoStart spidDemoStart = new SpidDemoStart(
                samlRequestFromSpidLogin,
                relayStateFromSpidLogin,
                sigAlgFromSpidLogin,
                signatureFromSpidLogin,
                bindingFromSpidLogin);
        spidDemoStart.setSpidDemoStartEndPoint("https://spid-saml-check.spid."+variabileAmbiente+".notifichedigitali.it/demo/start");
        spidDemoStart.runSpidDemoStart();

        if(spidDemoStart.getResponseBody() == null){
            Assert.fail("api spid demo start risponde con body vuoto");
        }

        String spidLevelFromSpidDemoStart = spidDemoStart.getSpidLevelOutput();
        if(spidLevelFromSpidDemoStart != null){
            logger.info("spidLevelFromSpidDemoStart = "+spidLevelFromSpidDemoStart);
        }else{
            Assert.fail("spidLevelFromSpidDemoStart is null");
        }

        String organizationDisplayNameFromSpidDemoStart = spidDemoStart.getOrganizationDisplayNameOutput();
        if(organizationDisplayNameFromSpidDemoStart != null){
            logger.info("organizationDisplayNameFromSpidDemoStart = "+organizationDisplayNameFromSpidDemoStart);
        }else{
            Assert.fail("organizationDisplayNameFromSpidDemoStart is null");
        }

        String samlRequestFromSpidDemoStart = spidDemoStart.getSamlRequestOutput();
        if(samlRequestFromSpidDemoStart != null){
            logger.info("samlRequestFromSpidDemoStart = "+samlRequestFromSpidDemoStart);
        }else{
            Assert.fail("samlRequestFromSpidDemoStart is null");
        }

        String relayStateFromSpidDemoStart = spidDemoStart.getRelayStateOutput();
        if(relayStateFromSpidDemoStart != null){
            logger.info("relayStateFromSpidDemoStart = "+relayStateFromSpidDemoStart);
        }else{
            Assert.fail("relayStateFromSpidDemoStart is null");
        }

        String sigAlgFromSpidDemoStart = spidDemoStart.getSigAlgOutput();
        if(sigAlgFromSpidDemoStart != null){
            logger.info("sigAlgFromSpidDemoStart = "+sigAlgFromSpidDemoStart);
        }else{
            Assert.fail("sigAlgFromSpidDemoStart is null");
        }

        String signatureFromSpidDemoStart = spidDemoStart.getSignatureOutput();
        if(signatureFromSpidDemoStart != null){
            logger.info("signatureFromSpidDemoStart = "+signatureFromSpidDemoStart);
        }else{
            Assert.fail("signatureFromSpidDemoStart is null");
        }

        String purposeFromSpidDemoStart = spidDemoStart.getPurposeOutput();
        if(purposeFromSpidDemoStart != null){
            logger.info("purposeFromSpidDemoStart = "+purposeFromSpidDemoStart);
        }else{
            Assert.fail("purposeFromSpidDemoStart is null");
        }

        String minAgeFromSpidDemoStart = spidDemoStart.getMinAgeOutput();
        if(minAgeFromSpidDemoStart != null){
            logger.info("minAgeFromSpidDemoStart = "+minAgeFromSpidDemoStart);
        }else{
            Assert.fail("minAgeFromSpidDemoStart is null");
        }

        String maxAgeFromSpidDemoStart = spidDemoStart.getMaxAgeOutput();
        if(maxAgeFromSpidDemoStart != null){
            logger.info("maxAgeFromSpidDemoStart = "+maxAgeFromSpidDemoStart);
        }else{
            Assert.fail("maxAgeFromSpidDemoStart is null");
        }

        String retryFromSpidDemoStart = spidDemoStart.getRetryOutput();
        if(retryFromSpidDemoStart != null){
            logger.info("retryFromSpidDemoStart = "+retryFromSpidDemoStart);
        }else{
            Assert.fail("retryFromSpidDemoStart is null");
        }

        logger.info("spid-demo-login");

        SpidDemoLogin spidDemoLogin = new SpidDemoLogin(
                user, pwd,
                spidLevelFromSpidDemoStart,
                organizationDisplayNameFromSpidDemoStart,
                samlRequestFromSpidDemoStart, relayStateFromSpidDemoStart,
                sigAlgFromSpidDemoStart, signatureFromSpidDemoStart,
                purposeFromSpidDemoStart, minAgeFromSpidDemoStart,
                maxAgeFromSpidDemoStart, retryFromSpidDemoStart
        );

        spidDemoLogin.setSpidDemoLoginEndPoint("https://spid-saml-check.spid."+variabileAmbiente+".notifichedigitali.it/demo/login");
        spidDemoLogin.runSpidDemoLogin();

        if(spidDemoLogin.getResponseBody() == null){
            Assert.fail("api spid demo login risponde con body vuoto");
        }

        String relayStateFromSpidDemoLogin = spidDemoLogin.getRelayStateOutput();
        if(relayStateFromSpidDemoLogin != null){
            logger.info("relayStateFromSpidDemoLogin = "+relayStateFromSpidDemoLogin);
        }else{
            Assert.fail("relayStateFromSpidDemoLogin is null");
        }

        String samlResponseFromSpidDemoLogin = spidDemoLogin.getSAMLResponseOutput();
        if(samlResponseFromSpidDemoLogin != null){
            logger.info("samlResponseFromSpidDemoLogin = "+samlResponseFromSpidDemoLogin);
        }else{
            Assert.fail("samlResponseFromSpidDemoLogin is null");
        }

        logger.info("spid-acs");

        SpidAcs spidAcs = new SpidAcs(relayStateFromSpidDemoLogin,samlResponseFromSpidDemoLogin);
        spidAcs.setSpidAcsEndPoint("https://hub-login.spid."+variabileAmbiente+".notifichedigitali.it/acs");
        spidAcs.runSpidAcs();
        this.urlDestinatario = spidAcs.getHeaderLocal();

        if(urlDestinatario.get("urlPortale") != null){
            logger.info("urlDestinatario : "+urlDestinatario.get("urlPortale"));
        }else{
            Assert.fail("urlDestinatario è null ");
        }

    }

    @When("Login {string} portale destinatario tramite request method")
    public void loginPortaleDelegatoTramiteRequestMethod(String dpFile) {
        boolean urlWithTokenFound = false;
        int numProvaLogin = 0;
        DataPopulation dataPopulation = new DataPopulation();
        this.datiDelegato = dataPopulation.readDataPopulation(dpFile+".yaml");

        String userDelegato = this.datiDelegato.get("user").toString();
        String passwordDelegato = this.datiDelegato.get("pwd").toString();

        while(numProvaLogin<10){
            this.readUrlLoginDestinatarioWithToken(userDelegato, passwordDelegato);
            if(this.urlDestinatario.get("responseCode").equalsIgnoreCase("301")){
                urlWithTokenFound = true;
                break;
            }else {
                this.urlDestinatario.clear();
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


        this.driver.get(this.urlDestinatario.get("urlPortale"));
    }

}
