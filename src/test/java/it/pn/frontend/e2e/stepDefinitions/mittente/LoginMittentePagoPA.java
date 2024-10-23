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
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Lazy;

import java.util.Map;
import java.util.concurrent.TimeUnit;

//@Component
//@Scope("prototype")
public class LoginMittentePagoPA {
    // Logger per tracciare informazioni importanti durante l'esecuzione del test
    private static final Logger logger = LoggerFactory.getLogger("LoginMittentePagoPA");

    // WebDriver gestito con Spring, inizializzato in modo lazy per ottimizzare le risorse da capire?
    //@Lazy
    @Autowired
    private WebDriver driver;

    @Autowired
    private DataPopulation dataPopulation;

    // Percorso del file token specificato nelle configurazioni, con valore di default 'tokenLogin.yaml'
    @Value("${token.login.file:tokenLogin.yaml}")
    private String FILE_TOKEN_LOGIN;

    private Map<String, Object> datiMittente;
    private Map<String, String> urlMittente;

    @Given("Login Page mittente {string} viene visualizzata")
    public void loginPageMittenteVieneVisualizzata(String datiMittenteFile) {
        // Recupero dell'ambiente corrente e visualizzazione della pagina di login
        logger.info("Si recupera l'ambiente e si visualizza la pagina di login");

        this.datiMittente = dataPopulation.readDataPopulation(datiMittenteFile + ".yaml");
        String variabileAmbiente = System.getProperty("environment");
        // Gestione degli ambienti 'dev', 'test', 'uat' per la navigazione
        switch (variabileAmbiente) {
            case "dev" -> this.driver.get(this.datiMittente.get("url").toString());
            case "test", "uat" ->
                    this.driver.get(this.datiMittente.get("url").toString().replace("dev", variabileAmbiente));
            default ->
                    Assertions.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
    }

    @Given("Login Page mittente viene visualizzata")
    public void loginPageMittenteVieneVisualizzata(Map<String,String> datiMittenteTable) {
        logger.info("Si recupera l'ambiente e si visualizza la pagina di login");
        String variabileAmbiente = System.getProperty("environment");

        this.datiMittente = dataPopulation.readDataPopulation("mittente.yaml");
        switch (variabileAmbiente) {
            case "dev" -> this.driver.get(datiMittenteTable.get("url"));
            case "test", "uat" ->
                    this.driver.get(datiMittenteTable.get("url").replace("dev", variabileAmbiente));
            default ->
                    Assertions.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
    }

    @Given("PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard")
    public void loginMittenteConTokenExchange() {
        String environment = System.getProperty("environment");
        String token = "";
        switch (environment) {
            case "dev" ->
                    token = dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokendevMittente").toString();
            case "test" ->
                    token = dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokentestMittente").toString();
            default -> {
                logger.error("Ambiente non valido");
                Assertions.fail("Ambiente non valido o non trovato!");
            }
        }

        // Si effettua il login con token exchange
        String urlLogin = "https://selfcare." + environment + ".notifichedigitali.it/#selfCareToken=" + token;
        this.driver.get(urlLogin);
        logger.info("Login effettuato con successo");
        // Attesa statica di 10 secondi - considerare l'uso di WebDriverWait per migliorare l'efficienza
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

        this.datiMittente = dataPopulation.readDataPopulation(datiMittenteFile + ".yaml");

        // Creazione dell'oggetto pagina per la gestione del pre-accesso all'area riservata
        PreAccediAreaRiservataPAPage preAccediAreaRiservataPAPage = new PreAccediAreaRiservataPAPage(this.driver);
        preAccediAreaRiservataPAPage.waitLoadPreAccediAreaRiservataPAPage();
        preAccediAreaRiservataPAPage.selezionaProcediAlLoginButton();

        // Verifica della presenza dell'URL e dei cookie per proseguire con l'accettazione dei cookie
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

    @And("Si clicca sul bottone test")
    public void clickTestButton() {
        AcccediAreaRiservataPAPage acccediAreaRiservataPAPage = new AcccediAreaRiservataPAPage(this.driver);
        acccediAreaRiservataPAPage.clickTestBottone();
    }

    @And("Si clicca bottone accetta cookies")
    public void clickAcceptCookies() {
        // Gestione della sezione cookies, accettando i cookie se necessario
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
