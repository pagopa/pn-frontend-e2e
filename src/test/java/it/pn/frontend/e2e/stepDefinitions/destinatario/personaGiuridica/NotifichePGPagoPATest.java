package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.HomePagePG;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.PiattaformaNotifichePGPAPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotifichePGPagoPATest {
    private final Logger logger = LoggerFactory.getLogger("NotifichePGPagoPATest");
    private final WebDriver driver = Hooks.driver;
    @And("Nella Home page persona giuridica si clicca su Send Notifiche Digitali")
    public void clickSendNotificheDigitali(){
        this.logger.info("Si clicca su Send Notifiche Digitali");
        HomePagePG homePagePG = new HomePagePG(this.driver);
        String variabileAmbiente = System.getProperty("environment");
        switch (variabileAmbiente) {
            case "dev" -> homePagePG.clickSendNotificheDigitali(5);
            case "test" -> homePagePG.clickSendNotificheDigitali(6);
            default -> Assert.fail("Non stato possibile trovare l'ambiente inserito, Insaerisci in -Denvironment test o dev o uat");
        }
    }

    @And("Si visualizza correttamente la Pagina Notifiche persona giuridica")
    public void siVisualizzaCorrettamenteLaPaginaNotifichePersonaGiuridica() {
        PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
        piattaformaNotifichePGPAPage.waitLoadPitattaformaNotificaPage();
    }
}
