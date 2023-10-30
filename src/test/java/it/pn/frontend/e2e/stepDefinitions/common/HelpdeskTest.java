package it.pn.frontend.e2e.stepDefinitions.common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import it.pn.frontend.e2e.common.HelpdeskPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.utility.DataPopulation;
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

    HelpdeskPage helpdeskPage = new HelpdeskPage(this.driver);
    private Map<String, Object> datiTestHelpdesk = new HashMap<>();


    @Given("Login helpdesk con utente test {string}")
    public void loginHelpdeskConUtenteTest(String nameFile) {
        this.datiTestHelpdesk = this.dataPopulation.readDataPopulation(nameFile+".yaml");
        helpdeskPage.changePage();
        helpdeskPage.checkForm();

        helpdeskPage.insertUsername(this.datiTestHelpdesk.get("user").toString());
        helpdeskPage.insertPassword(this.datiTestHelpdesk.get("pwd").toString());
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
}
