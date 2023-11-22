package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.personaFisica.SpidAcs;
import it.pn.frontend.e2e.api.personaFisica.SpidDemoLogin;
import it.pn.frontend.e2e.api.personaFisica.SpidDemoStart;
import it.pn.frontend.e2e.api.personaFisica.SpidLogin;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.*;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.HeadeFRSection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginPersonaFisicaPagoPA {

    private static final Logger logger = LoggerFactory.getLogger("LoginPersonaFisicaPagoPA");
    private Map<String, Object> datiPersonaFisica;

    private Map<String, String> urlPersonaFisica;
    private final WebDriver driver = Hooks.driver;

    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
    private Map<String, Object> datiDelegato;

    @Given("Login Page persona fisica {string} viene visualizzata")
    public void login_page_destinatario_viene_visualizzata(String datipersonaFisica) {
        DataPopulation dataPopulation = new DataPopulation();
        this.datiPersonaFisica = dataPopulation.readDataPopulation(datipersonaFisica + ".yaml");
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> this.driver.get(this.datiPersonaFisica.get("url").toString());
            case "test", "uat" ->
                    this.driver.get(this.datiPersonaFisica.get("url").toString().replace("dev", variabileAmbiente));
            default ->
                    Assert.fail("Non stato possibile trovare l'ambiente inserito, Insaerisci in -Denvironment test o dev o uat");
        }
    }

    @When("Login con persona fisica {string}")
    public void login_con_destinatario(String datipersonaFisica) {
        logger.info("user persona fisica : " + this.datiPersonaFisica.get("user").toString());
        DataPopulation dataPopulation = new DataPopulation();
        this.datiPersonaFisica = dataPopulation.readDataPopulation(datipersonaFisica + ".yaml");

        logger.info("cookies start");

        CookiesSection cookiesPage = new CookiesSection(this.driver);
        if (cookiesPage.waitLoadCookiesPage()) {
            cookiesPage.selezionaAccettaTuttiButton();
        }

        logger.info("cookies end");


        AccediApiattaformaNotifichePage accediApiattaformaNotifichePage = new AccediApiattaformaNotifichePage(this.driver);
        accediApiattaformaNotifichePage.waitLoadAccediApiattaformaNotifichePage();
        accediApiattaformaNotifichePage.selezionaAccediButton();

        if (cookiesPage.waitLoadCookiesPage()) {
            cookiesPage.selezionaAccettaTuttiButton();
        }

        ComeVuoiAccederePage comeVuoiAccederePage = new ComeVuoiAccederePage(this.driver);
        comeVuoiAccederePage.waitLoadComeVuoiAccederePage();
        comeVuoiAccederePage.selezionareSpidButton();

        ScegliSpidPFPage scegliSpidPFPage = new ScegliSpidPFPage(this.driver);
        scegliSpidPFPage.waitLoadScegliSpidDEPage();
        scegliSpidPFPage.selezionareTestButton();

        LoginSpidPFPage loginSpidPFPage = new LoginSpidPFPage(this.driver);
        loginSpidPFPage.waitLoadLoginSpidDEPage();
        loginSpidPFPage.inserisciUtente(this.datiPersonaFisica.get("user").toString());
        loginSpidPFPage.inserisciPassword(this.datiPersonaFisica.get("pwd").toString());
        loginSpidPFPage.selezionaEntraConSpidButton();

        ConfermaDatiSpidPFPage confermaDatiSpidPFPage = new ConfermaDatiSpidPFPage(this.driver);
        confermaDatiSpidPFPage.waitLoadConfermaDatiSpidDEPage();
        String nomeUtenteLetto = confermaDatiSpidPFPage.leggiNomeUtente();
        if (nomeUtenteLetto.equals(this.datiPersonaFisica.get("name").toString())) {
            logger.info("nome utente letto : " + nomeUtenteLetto + " uguale a : " + this.datiPersonaFisica.get("name").toString());
        } else {
            logger.error("nome utente letto : " + nomeUtenteLetto + " non è uguale a : " + this.datiPersonaFisica.get("name").toString());
            Assert.fail("nome utente letto : " + nomeUtenteLetto + " non è uguale a : " + this.datiPersonaFisica.get("name").toString());
        }

        String cognomeUtenteLetto = confermaDatiSpidPFPage.leggiCogomeUtente();
        if (cognomeUtenteLetto.equals(this.datiPersonaFisica.get("familyName").toString())) {
            logger.info("cognome utente letto : " + cognomeUtenteLetto + " uguale a : " + this.datiPersonaFisica.get("familyName").toString());
        } else {
            logger.error("cognome utente letto : " + cognomeUtenteLetto + " non uguale a : " + this.datiPersonaFisica.get("familyName").toString());
            Assert.fail("cognome utente letto : " + cognomeUtenteLetto + " non uguale a : " + this.datiPersonaFisica.get("familyName").toString());
        }

        String numeroFiscaleLetto = confermaDatiSpidPFPage.leggiNumeroFiscale();
        if (numeroFiscaleLetto.equals(this.datiPersonaFisica.get("fiscalNumber").toString())) {
            logger.info("numero fiscale letto : " + numeroFiscaleLetto + " uguale a : " + this.datiPersonaFisica.get("fiscalNumber").toString());
        } else {
            logger.error("numero fiscale letto : " + numeroFiscaleLetto + " non uguale a : " + this.datiPersonaFisica.get("fiscalNumber").toString());
            Assert.fail("numero fiscale letto : " + numeroFiscaleLetto + " non uguale a : " + this.datiPersonaFisica.get("fiscalNumber").toString());
        }

        confermaDatiSpidPFPage.selezionaConfermaButton();
        HeadeFRSection headeFRSection = new HeadeFRSection(this.driver);
        headeFRSection.waitUrlToken();
    }

    @Then("Home page persona fisica viene visualizzata correttamente")
    public void home_page_destinatario_viene_visualizzata_correttamente() {

        CookiesSection cookiesSection = new CookiesSection(this.driver);

        if (cookiesSection.waitLoadCookiesPage()) {
            cookiesSection.selezionaAccettaTuttiButton();
        }
        boolean httpRequestToken = false;
        for (int index = 0; index < 30; index++) {

            if (this.readHttpRequest()) {
                httpRequestToken = true;
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (httpRequestToken) {
            logger.info("Http token persona fisica found");
        } else {
            logger.error("Http token persona fisica not found");
            Assert.fail("Http token persona fisica not found");
        }
        HeadeFRSection headeFRSection = new HeadeFRSection(this.driver);
        headeFRSection.waitLoadHeaderDESection();


        if (cookiesSection.waitLoadCookiesPage()) {
            cookiesSection.selezionaAccettaTuttiButton();
        }

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPage();
        if (notifichePFPage.verificaPresenzaCodiceIunTextField()) {
            logger.info("text field codice ium presente");
        } else {
            logger.info("text field codice ium non presente");
            Assert.fail("text field codice ium non presente");
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String variabileAmbiente = System.getProperty("environment");
        String urlChiamata = "https://webapi."+variabileAmbiente+".notifichedigitali.it/delivery/notifications/received?";

        int codiceRispostaChiamataApi = getCodiceRispostaChiamataApi(urlChiamata);
        if (codiceRispostaChiamataApi != 200 && codiceRispostaChiamataApi != 0) {
            logger.error("TA_QA: La chiamata, " + urlChiamata + " è andata in errore");
            Assert.fail("TA_QA: La chiamata, " + urlChiamata + " è andata in errore");
        } else if (codiceRispostaChiamataApi == 0) {
            logger.error("TA_QA: La chiamata, " + urlChiamata + " non trovata");
            Assert.fail("TA_QA: La chiamata, " + urlChiamata + " non trovata");
        }
    }

    private int getCodiceRispostaChiamataApi(String urlChiamata) {
        logger.info("Recupero codice risposta della chiamata" + urlChiamata);
        int codiceRispostaChiamataApi = 0;
        for (NetWorkInfo chiamate : netWorkInfos) {
            if (chiamate.getRequestUrl().startsWith(urlChiamata) && chiamate.getRequestMethod().equals("GET")) {
                codiceRispostaChiamataApi = Integer.parseInt(chiamate.getResponseStatus());
                break;
            }
        }
        return codiceRispostaChiamataApi;
    }

    @And("Logout da portale persona fisica")
    public void logout_da_portale_destinatario() {
        HeadeFRSection headeFRSection = new HeadeFRSection(this.driver);
        headeFRSection.waitLoadHeaderDESection();
        headeFRSection.selezionaprofiloUtenteMenu();
        headeFRSection.selezionaVoceEsci();

        ComeVuoiAccederePage comeVuoiAccederePage = new ComeVuoiAccederePage(this.driver);
        comeVuoiAccederePage.waitLoadComeVuoiAccederePage();

        CookiesSection cookiesSection = new CookiesSection(this.driver);
        if (cookiesSection.waitLoadCookiesPage()) {
            logger.info("banner dei cookies visualizzato");
            cookiesSection.selezionaAccettaTuttiButton();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("banner dei cookies sparito");
        }

        if (comeVuoiAccederePage.verificaPresenzaSpidButton()) {
            logger.info("Spid Button nella pagina Come vuoi accedere portale persona fisica visualizzato");
        } else {
            logger.info("Spid Button nella pagina Come vuoi accedere portale persona fisica non visualizzato");
            Assert.fail("Spid Button nella pagina Come vuoi accedere portale persona fisica non visualizzato");
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
            String urlToFind = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/token-exchange";
            urlFound = false;
            if (netWorkInfo.getRequestUrl().contains(urlToFind)) {
                urlFound = true;
                break;
            }

        }
        return urlFound;
    }

    @When("Login portale persona fisica tramite request method")
    public void loginPortaleDestinatarioTramiteRequestMethod() {
        boolean urlWithTokenFound = false;
        int numProvaLogin = 0;
        String userPersonaFisica = this.datiPersonaFisica.get("user").toString();
        String pwdPersonaFisica = this.datiPersonaFisica.get("pwd").toString();
        while (numProvaLogin < 10) {
            this.readUrlLoginPersonaFisicaWithToken(userPersonaFisica, pwdPersonaFisica);
            if (this.urlPersonaFisica.get("responseCode").equalsIgnoreCase("301")) {
                urlWithTokenFound = true;
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            numProvaLogin++;
        }

        if (urlWithTokenFound) {
            logger.info("procedura di login from spid provata : " + numProvaLogin);
        } else {
            logger.error("procedura di login from spid provata : " + numProvaLogin);
            Assert.fail("Codice risposta ricevuto per questo end point: '" + this.urlPersonaFisica.get("urlPortale") + "' è : " + this.urlPersonaFisica.get("responseCode"));

        }


        this.driver.get(this.urlPersonaFisica.get("urlPortale"));
    }

    private void readUrlLoginPersonaFisicaWithToken(String user, String pwd) {
        logger.info("spid-login");
        String variabileAmbiente = System.getProperty("environment");

        SpidLogin spidLogin = new SpidLogin("xx_testenv2", "SpidL2");
        spidLogin.setSpidLoginEndPoint("https://hub-login.spid." + variabileAmbiente + ".notifichedigitali.it/login");
        spidLogin.runSpidLogin();

        if (spidLogin.getResponseBody() == null) {
            Assert.fail(" api spid login risponde con body vuoto");
        }

        String samlRequestFromSpidLogin = spidLogin.getSamlRequest();
        if (samlRequestFromSpidLogin != null) {
            logger.info("samlRequestFromSpidLogin = " + samlRequestFromSpidLogin);
        } else {
            Assert.fail("samlRequestFromSpidLogin is null");
        }

        String relayStateFromSpidLogin = spidLogin.getRelayState();
        if (relayStateFromSpidLogin != null) {
            logger.info("relayStateFromSpidLogin = " + relayStateFromSpidLogin);
        } else {
            Assert.fail("relayStateFromSpidLogin is null");
        }

        String sigAlgFromSpidLogin = spidLogin.getSigAlg();
        if (sigAlgFromSpidLogin != null) {
            logger.info("sigAlgFromSpidLogin = " + sigAlgFromSpidLogin);
        } else {
            Assert.fail("sigAlgFromSpidLogin is null");
        }

        String signatureFromSpidLogin = spidLogin.getSignature();
        if (signatureFromSpidLogin != null) {
            logger.info("signatureFromSpidLogin = " + signatureFromSpidLogin);
        } else {
            Assert.fail("signatureFromSpidLogin is null");
        }

        String bindingFromSpidLogin = spidLogin.getBinding();
        if (bindingFromSpidLogin != null) {
            logger.info("bindingFromSpidLogin = " + bindingFromSpidLogin);
        } else {
            Assert.fail("bindingFromSpidLogin is null");
        }

        logger.info("spid demo start");

        SpidDemoStart spidDemoStart = new SpidDemoStart(
                samlRequestFromSpidLogin,
                relayStateFromSpidLogin,
                sigAlgFromSpidLogin,
                signatureFromSpidLogin,
                bindingFromSpidLogin);
        spidDemoStart.setSpidDemoStartEndPoint("https://spid-saml-check.spid." + variabileAmbiente + ".notifichedigitali.it/demo/start");
        spidDemoStart.runSpidDemoStart();

        if (spidDemoStart.getResponseBody() == null) {
            Assert.fail("api spid demo start risponde con body vuoto");
        }

        String spidLevelFromSpidDemoStart = spidDemoStart.getSpidLevelOutput();
        if (spidLevelFromSpidDemoStart != null) {
            logger.info("spidLevelFromSpidDemoStart = " + spidLevelFromSpidDemoStart);
        } else {
            Assert.fail("spidLevelFromSpidDemoStart is null");
        }

        String organizationDisplayNameFromSpidDemoStart = spidDemoStart.getOrganizationDisplayNameOutput();
        if (organizationDisplayNameFromSpidDemoStart != null) {
            logger.info("organizationDisplayNameFromSpidDemoStart = " + organizationDisplayNameFromSpidDemoStart);
        } else {
            Assert.fail("organizationDisplayNameFromSpidDemoStart is null");
        }

        String samlRequestFromSpidDemoStart = spidDemoStart.getSamlRequestOutput();
        if (samlRequestFromSpidDemoStart != null) {
            logger.info("samlRequestFromSpidDemoStart = " + samlRequestFromSpidDemoStart);
        } else {
            Assert.fail("samlRequestFromSpidDemoStart is null");
        }

        String relayStateFromSpidDemoStart = spidDemoStart.getRelayStateOutput();
        if (relayStateFromSpidDemoStart != null) {
            logger.info("relayStateFromSpidDemoStart = " + relayStateFromSpidDemoStart);
        } else {
            Assert.fail("relayStateFromSpidDemoStart is null");
        }

        String sigAlgFromSpidDemoStart = spidDemoStart.getSigAlgOutput();
        if (sigAlgFromSpidDemoStart != null) {
            logger.info("sigAlgFromSpidDemoStart = " + sigAlgFromSpidDemoStart);
        } else {
            Assert.fail("sigAlgFromSpidDemoStart is null");
        }

        String signatureFromSpidDemoStart = spidDemoStart.getSignatureOutput();
        if (signatureFromSpidDemoStart != null) {
            logger.info("signatureFromSpidDemoStart = " + signatureFromSpidDemoStart);
        } else {
            Assert.fail("signatureFromSpidDemoStart is null");
        }

        String purposeFromSpidDemoStart = spidDemoStart.getPurposeOutput();
        if (purposeFromSpidDemoStart != null) {
            logger.info("purposeFromSpidDemoStart = " + purposeFromSpidDemoStart);
        } else {
            Assert.fail("purposeFromSpidDemoStart is null");
        }

        String minAgeFromSpidDemoStart = spidDemoStart.getMinAgeOutput();
        if (minAgeFromSpidDemoStart != null) {
            logger.info("minAgeFromSpidDemoStart = " + minAgeFromSpidDemoStart);
        } else {
            Assert.fail("minAgeFromSpidDemoStart is null");
        }

        String maxAgeFromSpidDemoStart = spidDemoStart.getMaxAgeOutput();
        if (maxAgeFromSpidDemoStart != null) {
            logger.info("maxAgeFromSpidDemoStart = " + maxAgeFromSpidDemoStart);
        } else {
            Assert.fail("maxAgeFromSpidDemoStart is null");
        }

        String retryFromSpidDemoStart = spidDemoStart.getRetryOutput();
        if (retryFromSpidDemoStart != null) {
            logger.info("retryFromSpidDemoStart = " + retryFromSpidDemoStart);
        } else {
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

        spidDemoLogin.setSpidDemoLoginEndPoint("https://spid-saml-check.spid." + variabileAmbiente + ".notifichedigitali.it/demo/login");
        spidDemoLogin.runSpidDemoLogin();

        if (spidDemoLogin.getResponseBody() == null) {
            Assert.fail("api spid demo login risponde con body vuoto");
        }

        String relayStateFromSpidDemoLogin = spidDemoLogin.getRelayStateOutput();
        if (relayStateFromSpidDemoLogin != null) {
            logger.info("relayStateFromSpidDemoLogin = " + relayStateFromSpidDemoLogin);
        } else {
            Assert.fail("relayStateFromSpidDemoLogin is null");
        }

        String samlResponseFromSpidDemoLogin = spidDemoLogin.getSAMLResponseOutput();
        if (samlResponseFromSpidDemoLogin != null) {
            logger.info("samlResponseFromSpidDemoLogin = " + samlResponseFromSpidDemoLogin);
        } else {
            Assert.fail("samlResponseFromSpidDemoLogin is null");
        }

        logger.info("spid-acs");

        SpidAcs spidAcs = new SpidAcs(relayStateFromSpidDemoLogin, samlResponseFromSpidDemoLogin);
        spidAcs.setSpidAcsEndPoint("https://hub-login.spid." + variabileAmbiente + ".notifichedigitali.it/acs");
        spidAcs.runSpidAcs();
        this.urlPersonaFisica = spidAcs.getHeaderLocal();

        if (urlPersonaFisica.get("urlPortale") != null) {
            logger.info("url persona fisica : " + urlPersonaFisica.get("urlPortale"));
        } else {
            Assert.fail("url persona fisica è null ");
        }

    }

    @When("Login {string} portale persona fisica tramite request method")
    public void loginPortaleDelegatoTramiteRequestMethod(String dpFile) {
        boolean urlWithTokenFound = false;
        int numProvaLogin = 0;
        DataPopulation dataPopulation = new DataPopulation();
        this.datiDelegato = dataPopulation.readDataPopulation(dpFile + ".yaml");

        String userDelegato = this.datiDelegato.get("user").toString();
        String passwordDelegato = this.datiDelegato.get("pwd").toString();

        while (numProvaLogin < 10) {
            this.readUrlLoginPersonaFisicaWithToken(userDelegato, passwordDelegato);
            if (this.urlPersonaFisica.get("responseCode").equalsIgnoreCase("301")) {
                urlWithTokenFound = true;
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            numProvaLogin++;
        }

        if (urlWithTokenFound) {
            logger.info("procedura di login from spid provata : " + numProvaLogin);
        } else {
            logger.error("procedura di login from spid provata : " + numProvaLogin);
            Assert.fail("Codice risposta ricevuto per questo end point: '" + this.urlPersonaFisica.get("urlPortale") + "' è : " + this.urlPersonaFisica.get("responseCode"));

        }


        this.driver.get(this.urlPersonaFisica.get("urlPortale"));
    }
    @When("Login portale persona fisica tramite token exchange {string}")
    public void loginPortalePersonaFisicaTramiteTokenExchange(String dpFile) {
        String variabileAmbiente = System.getProperty("environment");
        DataPopulation dataPopulation = new DataPopulation();
        String urlInziale = "https://cittadini."+variabileAmbiente+".notifichedigitali.it/#token=";
        String user = dataPopulation.readDataPopulation(dpFile+".yaml").get("user").toString();
        String token;
        if(user.equalsIgnoreCase("cesare")){

            if (variabileAmbiente.equalsIgnoreCase("test")){
                token = dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokentestPFDelegante").toString();
            }else{
                token = dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokendevPFDelegante").toString();
            }
        }else {
            if (variabileAmbiente.equalsIgnoreCase("test")){
                token = dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokentestPFDelegato").toString();
            }else{
                token = dataPopulation.readDataPopulation("tokenLogin.yaml").get("tokendevPFDelegato").toString();
            }
        }
        String url = urlInziale+token;
        this.driver.get(url);
    }
}
