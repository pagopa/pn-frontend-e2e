package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.DataPopulationConfig;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.config.WebDriverManager;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.IntegrazioneAPIPGPage;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class IntegrazioneAPIPGPagoPaTest extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("NotifichePGPagoPATest");
    Map<String, Object> personaGiuridica = new HashMap<>();

    private IntegrazioneAPIPGPage integrazioneAPIPGPage;

    @Autowired
    private WebDriverConfig webDriverConfig;

    @Autowired
    private DataPopulationConfig dataPopulationConfig;
    private WebDriverManager webDriverManager;

    private WebTool webTool;

    @PostConstruct
    public void init(){
        logger.info("INIT TEST...: ");
        webTool = new WebTool(driver);
        integrazioneAPIPGPage = new IntegrazioneAPIPGPage(driver);
    }

    @And("Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica")
    public void siControllaSiaPresenteIlBottoneGeneraChiavePubblica() {
        logger.info("Si controlla sia presente bottone Genera chiave pubblica");
        if (integrazioneAPIPGPage.generaChiavePubblicaDisplayed()) {
            logger.info("Il bottone Genera chiave pubblica è trovato");
        } else {
            logger.error("Il bottone Genera chiave pubblica non è trovato");
            Assertions.fail("Il bottone Genera chiave pubblica non è trovato");
        }
    }

    @And("Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica")
    public void siCliccaIlBottoneGeneraChiavePubblica() {
        logger.info("Si clicca sul bottone Genera chiave pubblica");
        integrazioneAPIPGPage.clickBottoneGeneraChiavePubblica();
    }

    @And("Si controlla la comparsa del label di stato 'Attiva' e del pop up di conferma per la creazione della chiave pubblica")
    public void checkConfermaCreazioneChiavePubblica() {
        logger.info("Si controlla la comparsa del label di stato 'Attiva' e del pop up di conferma per la creazione della chiave pubblica");
        integrazioneAPIPGPage.checkConfermaCreazioneChiavePubblica();
    }

    @And("Si controlla che il pulsante Genera chiave pubblica non sia più presente nella pagina Integrazione API")
    public void siControllaCheIlPulsanteGeneraChiavePubblicaNonSiaPiuPresente() {
        logger.info("Si controlla che pulsante Genera chiave pubblica non sia più presente nella pagina Integrazione API");
        if (!integrazioneAPIPGPage.generaChiavePubblicaDisplayed()) {
            logger.info("Il bottone Genera chiave pubblica non è più visibile dopo aver creato una chiave pubblica");
        } else {
            logger.error("Il bottone Genera chiave pubblica è ancora visibile dopo aver creato una chiave pubblica");
            Assertions.fail("Il bottone Genera chiave pubblica è ancora visibile dopo aver creato una chiave pubblica");
        }
    }
}
