package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.DisserviziAppPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.PiattaformaNotifichePGPAPage;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import java.util.Set;

@Primary
public class DisserviziAppPGTest {

    private final Logger logger = LoggerFactory.getLogger("DisserviziAppPGTest");

    @Autowired
    @Lazy
    private HooksNew hooks;
    @Autowired
    private  DisserviziAppPage disserviziAppPage;
    @Autowired
    private  PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage;
    @Autowired
    @Lazy
    private BackgroundTest backgroundTest;
    @Autowired
    @Lazy
    private  WebTool webTool;


    @Given("Nella dashboard persona giuridica clicca su disservizi app")
    public void nellaDashboardPersonaGiuridicaCliccaSuDisserviziApp() {

        logger.info("click sul bottone disservisi nel menu laterale");
        piattaformaNotifichePGPAPage.clickOnButtonEnterIntoDisservizi();
    }

    @And("Si visualizzano correttamente i dati sullo stato della piattaforma")
    public void siVisualizzanoCorrettamenteIDatiSulloStatoDellaPiattaforma() {
        disserviziAppPage.checkDatiPaginaDisservizi();
    }

    @And("Si visualizza correttamente la Pagina dello Stato della piattaforma")
    public void siVisualizzaCorrettamenteLaPaginaDelloStatoDellaPiattaforma() {
        disserviziAppPage.waitLoadStatoDellaPiattaformaPage();

    }

    @And("Si visualizza storico disservizi")
    public void siVisualizzaStoricoDisservizi() {
        disserviziAppPage.checkElencoDisservizi();
    }


    @And("Si verifica avvenuto disservizio in pagina stato piattaforma")
    public void siVerificaAvvenutoDisservizioInPaginaStatoPiattaforma() {
        logger.info("Torno sulla scheda della piattaforma send");
        String helpdeskHandle = hooks.getDriver().getWindowHandle();
        Set<String> windowHandles = hooks.getDriver().getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(helpdeskHandle)) {
                this.hooks.getDriver().switchTo().window(handle);
                break;
            }
        }
        backgroundTest.getStatoDellaPiattaformaPage();
        boolean res = false;
        for (int i = 0; i < 2; i++) {
            webTool.waitTime(30);
            disserviziAppPage.aggiornamentoPagina();
            if (disserviziAppPage.checkDisservizioInCorso()) {
                res = true;
                break;
            }
        }
        if (!res) {
            logger.error("dopo aver atteso alcuni secondi il disservizio non è stato ancora creato");
            Assertions.fail("dopo aver atteso alcuni secondi il disservizio non è stato ancora creato");
        }
        disserviziAppPage.waitLoadStatoDellaPiattaformaPage();

    }


}
