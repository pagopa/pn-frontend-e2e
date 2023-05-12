package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePAPage;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaSection;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadFileMittentePagoPATest {
    private static final Logger logger = LoggerFactory.getLogger("DownloadFileMittentePagoPATest");
    private static WebDriver driver = Hooks.driver;

    @When("Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita")
    public void clickNotificaRestituita(){
        logger.info("Si clicca sulla notifica restituita");
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.selezionaNotifica();
    }

    @And("Si visualizza correttamente la sezione Dettaglio Notifica")
    public void siVisualizzaCorrettamenteLaSezioneDettaglioNotifica() {
        logger.info("Viene caricato correttamente la sezione Dettaglio Notifica");
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.waitLoadDettaglioNotificaSection();
    }

    @Then("Nella sezione Dettaglio Notifica si scaricano tutti i file presenti")
    public void downloadECheckFile() {
        logger.info("Si scaricano tutti i file all'interno della notifica");
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.downloadFileNotifica("/src/test/resources/dataPopulation/downloadFileNotifica/mittente");
        dettaglioNotificaSection.downloadFileAttestazioni("/src/test/resources/dataPopulation/downloadFileNotifica/mittente");
        dettaglioNotificaSection.controlloDownload();
    }
}
