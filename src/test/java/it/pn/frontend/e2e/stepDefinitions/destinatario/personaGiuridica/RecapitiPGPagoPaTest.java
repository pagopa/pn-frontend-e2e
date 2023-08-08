package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.RecapitiPGPage;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

public class RecapitiPGPagoPaTest {
    private final Logger logger = LoggerFactory.getLogger("RecapitiPGPagoPaTest");
    private final WebDriver driver = Hooks.driver;
    DataPopulation dataPopulation = new DataPopulation();

    private final RecapitiPGPage recapitiPGPage = new RecapitiPGPage(this.driver);

    private final RecapitiDestinatarioPage recapitiDestinatarioPage = new RecapitiDestinatarioPage(this.driver);
    @And("Si visualizza correttamente la pagina Recapiti persona giuridica")
    public void siVisualizzaRecapitiPagePersonaGiuridca(){
        logger.info("Si visualizza correttamente la pagina Recapiti persona giuridica");
        recapitiPGPage.waitLoadRecapitiPage();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce la PEC del persona giuridica {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLaPECDelPersonaGiuridica(String personaGiuridica) {
        logger.info("Si cerca di inserire la email pec");
        Map<String, Object> datiPG = dataPopulation.readDataPopulation(personaGiuridica+".yaml");
        recapitiDestinatarioPage.insertEmailPEC(datiPG.get("emailPec").toString());
    }


    @And("Nella pagina I Tuoi Recapiti persona giuridica si inserisce una PEC sbagliata {string}")
    public void nellaPaginaITuoiRecapitiPersonaGiuridicaSiInserisceUnaPECSbagliata(String emailPec) {
        logger.info("Si cerca di inserire la email pec sbagliata");
        recapitiDestinatarioPage.insertEmailPEC(emailPec);
    }

    @Then("Nella pagina I Tuoi Recapiti persona giuridica si visualizza correttamente il messaggio di errore pec sbagliata")
    public void nellaPaginaITuoiRecapitiPersonaGiuridicaSiVisualizzaCorrettamenteIlMessaggioDiErrorePecSbagliata() {
        logger.info("Si controlla che si deve il messaggio di errore");

        recapitiPGPage.waitMessaggioDiErrorePec();
    }
}
