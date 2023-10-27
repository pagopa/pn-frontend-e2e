package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import it.pn.frontend.e2e.common.HelpdeskPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.DisserviziAppPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.PiattaformaNotifichePGPAPage;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DisserviziAppPGTest  {
    private final WebDriver driver = Hooks.driver;

    private final Logger logger = LoggerFactory.getLogger("DisserviziAppPGTest");

    private final  DisserviziAppPage disserviziAppPage = new DisserviziAppPage(this.driver);


    @Given("Nella dashboard persona giuridica clicca su disservizi app")
    public void nellaDashboardPersonaGiuridicaCliccaSuDisserviziApp(){
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        logger.info("click sul bottone disservisi nel menu laterale");
        piattaformaNotifichePGPAPage.clickOnButtonEnterIntoDisservizi();
    }

    @And("Si visualizzano correttamente i dati sullo stato della piattaforma")
    public void siVisualizzanoCorrettamenteIDatiSulloStatoDellaPiattaforma(){
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
        backgroundTest.loginPG("personaGiuridica");
        backgroundTest.getStatoDellaPiattaformaPage();
        boolean res = false;
        for (int i = 0; i < 2; i++) {
            disserviziAppPage.aggionamentoPagina();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                logger.error("pausa con errore: "+e.getMessage());
                throw new RuntimeException(e);
            }
            if(disserviziAppPage.checkDisservizioInCorso()){
                res = true;
                break;
            }
        }
        if(!res){
            logger.error("dopo aver atteso alcuni secondi il disservizio non è stato ancora creato");
            Assert.fail("dopo aver atteso alcuni secondi il disservizio non è stato ancora creato");
        }
        disserviziAppPage.waitLoadStatoDellaPiattaformaPage();

    }
}
