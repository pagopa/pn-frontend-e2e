package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePAPage;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaSection;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DownloadFileMittentePagoPATest {
    private static final Logger logger = LoggerFactory.getLogger("DownloadFileMittentePagoPATest");
    private final WebDriver driver = Hooks.driver;

    @When("Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita")
    public void clickNotificaRestituita() {
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

    @When("Cliccare sulla notifica restituita")
    public void cliccareSullaNotificaRestituita() {
        logger.info("Si clicca sulla notifica");
        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.selezionaNotifica();
    }

    @And("Si visualizza correttamente la section Dettaglio Notifica")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotifica() {
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.waitLoadDettaglioNotificaSection();
    }

    @And("Nella sezione Dettaglio Notifiche si seleziona il file, {string}, da scaricare")
    public void siSelezionanoIlFileDaScaricare(String nomeFile) {
        logger.info("Si cerca di scaricare il file " + nomeFile);

        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.downloadFileAttestazione(nomeFile, "/src/test/resources/dataPopulation/downloadFileNotifica/mittente");
    }

    @Then("Si controlla il testo all interno del file {string}")
    public void siControllaIlTestoAlSuoInterno(String nomeFile) {
        logger.info("Si controlla che il testo al suo interno si coretto");

        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        Map<String, String> infoNotifiche = dettaglioNotificaSection.recuperoInfoNotifiche();
        if (dettaglioNotificaSection.controlloTestoFile("/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf", infoNotifiche.get("mittente").toString())) {
            logger.info("Il nome del mittente all'interno del file è corretto");
        } else {
            logger.error("Il nome del mittente  all'interno del file  NON è corretto");
            Assert.fail("Il nome del mittente  all'interno del file  NON è corretto");
        }

        if (dettaglioNotificaSection.controlloTestoFile("/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf", infoNotifiche.get("destinatario").toString())) {
            logger.info("Il nome del destinatario all'interno del file è corretto");
        } else {
            logger.error("Il nome del destinatario  all'interno del file  NON è corretto");
            Assert.fail("Il nome del destinatario  all'interno del file  NON è corretto");
        }

        if (dettaglioNotificaSection.controlloTestoFile("/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf", infoNotifiche.get("codiceFiscale").toString())) {
            logger.info("Il codiceFiscale del destinatario all'interno del file è corretto");
        } else {
            logger.error("Il codiceFiscale del destinatario  all'interno del file  NON è corretto");
            Assert.fail("Il codiceFiscale del destianatario  all'interno del file  NON è corretto");
        }

        if (dettaglioNotificaSection.controlloTestoFileData("/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf", infoNotifiche.get("data").toString())) {
            logger.info("La data della notifica all'interno del file è corretta");
        } else {
            logger.error("La data della notifica all'interno del file  NON è corretta");
            Assert.fail("La data della notifica  all'interno del file  NON è corretta");
        }

        if (dettaglioNotificaSection.controlloTestoFileCodiceIUN("/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf", infoNotifiche.get("codiceIUN").toString())) {
            logger.info("Il codice IUN della notifica all'interno del file è corretto");
        } else {
            logger.error("Il codice IUN della notifica all'interno del file  NON è corretto");
            Assert.fail("Il codice IUN della notifica  all'interno del file  NON è corretto");
        }

        if (dettaglioNotificaSection.controlloTestoFile("/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf", infoNotifiche.get("gruppo").toString())) {
            logger.info("Il gruppo della notifica all'interno del file è corretto");
        } else {
            logger.error("Il gruppo della notifica all'interno del file  NON è corretto");
            Assert.fail("Il gruppo della notifica  all'interno del file  NON è corretto");
        }
    }
}
