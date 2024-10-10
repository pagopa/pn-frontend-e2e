package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.DisserviziAppPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.PiattaformaNotifichePGPAPage;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.WebTool;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class DisserviziAppPGTest {
    private final WebDriver driver = Hooks.driver;

    private final Logger logger = LoggerFactory.getLogger("DisserviziAppPGTest");

    private final DisserviziAppPage disserviziAppPage = new DisserviziAppPage(this.driver);


    @Given("Nella dashboard persona giuridica clicca su disservizi app")
    public void nellaDashboardPersonaGiuridicaCliccaSuDisserviziApp() {
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
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
        BackgroundTest backgroundTest = new BackgroundTest();
        logger.info("Torno sulla scheda della piattaforma send");
        String helpdeskHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(helpdeskHandle)) {
                this.driver.switchTo().window(handle);
                break;
            }
        }
        backgroundTest.getStatoDellaPiattaformaPage();
        boolean res = false;
        for (int i = 0; i < 2; i++) {
            WebTool.waitTime(30);
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
