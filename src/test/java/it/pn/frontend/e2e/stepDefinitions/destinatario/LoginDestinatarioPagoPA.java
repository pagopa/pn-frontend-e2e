package it.pn.frontend.e2e.stepDefinitions.destinatario;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
    private final WebDriver driver = Hooks.driver;
    
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;

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
        boolean urlFound = false;
        for (NetWorkInfo netWorkInfo : netWorkInfos) {
            //logger.info(netWorkInfo.getRequestId());
            logger.info(netWorkInfo.getRequestUrl());
            //logger.info(netWorkInfo.getRequestMethod());
            logger.info(netWorkInfo.getResponseStatus());
            //logger.info(netWorkInfo.getResponseBody());
            String urlToFind = "https://webapi.test.notifichedigitali.it/token-exchange";
            urlFound = false;
            if (netWorkInfo.getRequestUrl().contains(urlToFind)) {
                urlFound = true;
                break;
            }

        }
        return urlFound;
    }
}
