package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

public class LoginMittentePagoPA {

    private static final Logger logger = LoggerFactory.getLogger("LoginTest");
    private Map<String, Object> datiMittente;
    private final WebDriver driver = Hooks.driver;


    @Given("Login Page mittente {string} viene visualizzata")
    public void login_page_mittente_viene_visualizzata(String datiMittenteFile) {
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
        DataPopulation dataPopulation = new DataPopulation();
        this.datiMittente = dataPopulation.readDataPopulation(datiMittenteFile+".yaml");

        CookiesSection cookiesPage = new CookiesSection(this.driver);
        cookiesPage.waitLoadCookiesPage();
        cookiesPage.selezionaAccettaTuttiButton();

        PreAccediAreaRiservataPAPage preAccediAreaRiservataPAPage = new PreAccediAreaRiservataPAPage(this.driver);
        preAccediAreaRiservataPAPage.waitLoadPreAccediAreaRiservataPAPage();
        preAccediAreaRiservataPAPage.selezionaProcediAlLoginButton();

        //CookiesSection cookiesPage = new CookiesSection(this.driver);
        cookiesPage.waitLoadCookiesPage();
        cookiesPage.selezionaAccettaTuttiButton();

        AcccediAreaRiservataPAPage acccediAreaRiservataPAPage = new AcccediAreaRiservataPAPage(this.driver);
        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();
        acccediAreaRiservataPAPage.selezionareSipButton();

        ScegliSpidPAPage scegliSpidPAPage = new ScegliSpidPAPage(this.driver);
        scegliSpidPAPage.waitLoadScegliSpidPAPage();
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
        selezionaEntePAPage.selezionareComune(this.datiMittente.get("comune").toString());
        selezionaEntePAPage.selezionaAccedi();
    }
    @Then("Home page mittente viene visualizzata correttamente")
    public void home_page_mittente_viene_visualizzata_correttamente() {
        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();



        AreaRiservataPAPage areaRiservataPAPage = new AreaRiservataPAPage(this.driver);
        areaRiservataPAPage.waitLoadAreaRiservataPAPage();
        if(areaRiservataPAPage.verificaCodiceFiscale(this.datiMittente.get("codiceFiscale").toString())){
            logger.info("codice fiscale presente");
        }else {
            logger.info("codice fiscale non presente o errato");
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @And("Logout da portale mittente")
    public void Logout_da_portale_mittente() {
        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();
        headerPASection.selezionaEsciButton();

        //SelezionaEntePAPage selezionaEntePAPage = new SelezionaEntePAPage(this.driver);
        //selezionaEntePAPage.waitLoadSelezionaEntePAPage();

        //headerPASection.waitLoadHeaderSection();
        //headerPASection.selezionaEsciButton();

//        AcccediAreaRiservataPAPage acccediAreaRiservataPAPage = new AcccediAreaRiservataPAPage(this.driver);
//        acccediAreaRiservataPAPage.waitLoadLoginPageMittente();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
