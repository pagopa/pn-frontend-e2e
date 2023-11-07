package it.pn.frontend.e2e.stepDefinitions.common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.HelpdeskPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HelpdeskTest {
    private final WebDriver driver = Hooks.driver;

    private final DataPopulation dataPopulation = new DataPopulation();

    private final Logger logger = LoggerFactory.getLogger("HelpdeskAppTest");

    private HelpdeskPage helpdeskPage = new HelpdeskPage(this.driver);
    private Map<String, Object> datiTestHelpdesk = new HashMap<>();

    private Map<String, Object> datiPersonaFisica = new HashMap<>();



    @Given("Login helpdesk con utente test {string}")
    public void loginHelpdeskConUtenteTest(String nameFile) {
        this.datiTestHelpdesk = this.dataPopulation.readDataPopulation(nameFile+".yaml");
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> helpdeskPage.changePage(this.datiTestHelpdesk.get("url").toString());
            case "test", "uat" ->
                    helpdeskPage.changePage(this.datiTestHelpdesk.get("url").toString().replace("dev", variabileAmbiente));
            default -> Assert.fail("Non è stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
        helpdeskPage.checkForm();
        switch (variabileAmbiente) {
            case "dev" -> {
                helpdeskPage.insertUsername(this.datiTestHelpdesk.get("userDev").toString());
                helpdeskPage.insertPassword(this.datiTestHelpdesk.get("pwdDev").toString());
            }
            case "test" ->{
                helpdeskPage.insertUsername(this.datiTestHelpdesk.get("userTest").toString());
                helpdeskPage.insertPassword(this.datiTestHelpdesk.get("pwdTest").toString());
            }
            case "uat" ->{
                helpdeskPage.insertUsername(this.datiTestHelpdesk.get("userUat").toString());
                helpdeskPage.insertPassword(this.datiTestHelpdesk.get("pwdUat").toString());
            }
            default -> Assert.fail("Non stato possibile trovare l'ambiente inserito, Inserisci in -Denvironment test o dev o uat");
        }
        helpdeskPage.clickInviaButton();
    }

    @And("Click su card monitoraggio piattaforma")
    public void clickSuCardMonitoraggioPiattaforma() {
        helpdeskPage.clickMonitoraggio();
    }

    @And("Si visualizza correttamente home Helpdesk")
    public void siVisualizzaCorrettamenteHomeHelpdesk() {
        helpdeskPage.checkHome();
    }

    @And("Si visualizza correttamente home monitoraggio")
    public void siVisualizzaCorrettamenteHomeMonitoraggio() {
        helpdeskPage.checkMonitoraggio();
    }

    @And("Si crea il disservizio")
    public void siCreaIlDisservizio() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: "+e.getMessage());
            throw new RuntimeException(e);
        }
        helpdeskPage.handleDisservizio("KO");
    }

    @And("Si verifica la creazione del disservizio")
    public void siVerificaLaCreazioneDelDisservizio() {
        helpdeskPage.checkIsCreatedDisservizio();
    }

    @And("Annullamento disservizio")
    public void annullamentoDisservizio() {
        BackgroundTest backgroundTest = new BackgroundTest();
        backgroundTest.getHelpdeskMonitoraggioPiattaforma("testHelpdesk");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: "+e.getMessage());
            throw new RuntimeException(e);
        }
        helpdeskPage.handleDisservizio("OK");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @When("Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati")
    public void nellaHomeDiHelpdeskUtenteCliccaSuSezioneRicercaEdEstrazioneDati() {
        helpdeskPage.clickSezioneRicerca();
    }

    @And("visualizzazione corretta pagina ricerca ed estrazione dati")
    public void visualizzazioneCorrettaPaginaRicercaEdEstrazioneDati() {
        helpdeskPage.checkRicercaPage();
    }

    @And("viene inserito codice fiscale {string}")
    public void vieneInseritoCodiceFiscale(String nameFile) {
        this.datiPersonaFisica = this.dataPopulation.readDataPopulation(nameFile+".yaml");
        helpdeskPage.insertCfOnRicercaPage(datiPersonaFisica.get("codiceFiscale").toString());
    }


    @When("Nella Home di helpdesk utente clicca su logout")
    public void nellaHomeDiHelpdeskUtenteCliccaSuLogout() {
        helpdeskPage.logout();
    }

    @Then("visualizzazione corretta pagina di login")
    public void visualizzazioneCorrettaPaginaDiLogin() {
        helpdeskPage.checkForm();
    }

    @Then("controllo generazione codice univoco")
    public void controlloGenerazioneCodiceUnivoco() {
        helpdeskPage.checkUid();
    }
}
