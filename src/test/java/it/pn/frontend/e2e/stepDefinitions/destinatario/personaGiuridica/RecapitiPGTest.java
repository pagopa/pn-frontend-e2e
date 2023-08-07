package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.RecapitiPGPage;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class RecapitiPGTest {
    private final Logger logger = LoggerFactory.getLogger("RecapitiPGTest");
    private final WebDriver driver = Hooks.driver;

    RecapitiPGPage recapitiPGPage = new RecapitiPGPage(this.driver);

    @And("Si visualizza correttamente la pagina Recapiti persona giuridica")
    public void siVisualizzaRecapitiPagePersonaGiuridca(){
        logger.info("Si visualizza correttamente la pagina Recapiti persona giuridica");
        recapitiPGPage.waitLoadRecapitiPage();
    }


    @And("Nella pagina I Tuoi Recapiti si inserisce la PEC del persona giuridica {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLaPECDelPersonaGiuridica(String personaGiuridica) {
        DataPopulation dataPopulation = new DataPopulation();
        Map<String, Object> datiPG = dataPopulation.readDataPopulation(personaGiuridica+".yaml");
        //recapitiPGPage.insertPec(datiPG.get("emailPec").toString());
    }
}

