package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.DettaglioNotificaSection;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaMittenteSection;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.DownloadFile;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class DownloadFileMittentePagoPATest {
    private static final Logger logger = LoggerFactory.getLogger("DownloadFileMittentePagoPATest");
    private final WebDriver driver = Hooks.driver;

    private  Map<String,Object> datiNotifica = new HashMap<>();

     private DownloadFile downloadFile;

    @When("Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita")
    public void clickNotificaRestituita() {
        logger.info("Si clicca sulla notifica restituita");
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.selezionaNotifica();
    }

    @And("Si visualizza correttamente la sezione Dettaglio Notifica")
    public void siVisualizzaCorrettamenteLaSezioneDettaglioNotifica() {
        logger.info("Viene caricato correttamente la sezione Dettaglio Notifica");
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        dettaglioNotificaMittenteSection.waitLoadDettaglioNotificaSection();
    }

    @Then("Nella sezione Dettaglio Notifica si scaricano tutti i file presenti")
    public void downloadECheckFile() {
        logger.info("Si scaricano tutti i file all'interno della notifica");
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        DataPopulation dataPopulation = new DataPopulation();
        String workingDirectory = System.getProperty("user.dir");
        File pathCartella = new File(workingDirectory+"/src/test/resources/dataPopulation/downloadFileNotifica/mittente");
        downloadFile = new DownloadFile();
        if (!downloadFile.controlloEsistenzaCartella(pathCartella)){
            pathCartella.mkdirs();
        }
        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        int count = 1;
        dettaglioNotificaMittenteSection.clickLinkDocumentiAllegati();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        final String filepath = workingDirectory+"/src/test/resources/dataPopulation/downloadFileNotifica/mittente/notificaN";
        final String urlDocumenti ="https://webapi.test.notifichedigitali.it/delivery/notifications/sent/" + this.datiNotifica.get("codiceIUN").toString() + "/attachments/documents/0";

        final String urlDocumentiAllegati = downloadFile.getUrl(urlDocumenti);
        File file = new File(filepath+count+".pdf");

        downloadFile.download(urlDocumentiAllegati,file);
        count = count+1;

        int numeroLinkAvvenutaRicezione = dettaglioNotificaMittenteSection.getLinkAvvenutaRicezione();
        for (int i = 1; i <numeroLinkAvvenutaRicezione ; i++) {
            dettaglioNotificaMittenteSection.clickLinkAvvenutaRicezione(i);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            final String url = "https://webapi.test.notifichedigitali.it/delivery-push/"+ this.datiNotifica.get("codiceIUN").toString() +"/document/AAR?documentId=safestorage:";
            final String urlAvvenutaRicezione = downloadFile.getUrl(url);
            file = new File(filepath+count+".pdf");
            count = count+1;

            downloadFile.download(urlAvvenutaRicezione,file);
        }

        int numeroLinkAttestazioniOpponibile = dettaglioNotificaSection.getLinkAttestazioniOpponubili();

        for (int i = 0; i <numeroLinkAttestazioniOpponibile ; i++) {
            dettaglioNotificaSection.clickLinkAttestazionipponibile(i);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            final String urlAttestazione = "https://webapi.test.notifichedigitali.it/delivery-push/";
            final String urlFileAttestazioneOppponubile = downloadFile.getUrl(urlAttestazione);
            file = new File(filepath+count+".pdf");
            count = count+1;
            downloadFile.download(urlFileAttestazioneOppponubile,file);
        }
        count = count -1;
        final String pathOfdownloadedFile = workingDirectory+"/src/test/resources/dataPopulation/downloadFileNotifica/mittente";
        downloadFile.controlloDownload(pathOfdownloadedFile,count);

    }

    @When("Cliccare sulla notifica restituita")
    public void cliccareSullaNotificaRestituita() {
        logger.info("Si clicca sulla notifica");
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.selezionaNotifica();
    }

    @And("Si visualizza correttamente la section Dettaglio Notifica")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotifica() {
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        dettaglioNotificaMittenteSection.waitLoadDettaglioNotificaSection();
    }

    @And("Nella sezione Dettaglio Notifiche si seleziona il file, {string}, da scaricare")
    public void siSelezionanoIlFileDaScaricare(String nomeFile) {
        logger.info("Si cerca di scaricare il file " + nomeFile);

        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");

        String nomeFileAvvenutoAccesso = "Attestazione opponibile a terzi: avvenuto accesso";

        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);

        if(nomeFile.equals(nomeFileAvvenutoAccesso)){
            dettaglioNotificaMittenteSection.clickLinkAttestazioneOpponibileAvvenutoAccesso();
        }else {
            dettaglioNotificaMittenteSection.clickLinkAttestazioneOpponibile(nomeFile);
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        downloadFile = new DownloadFile();
        final String url = downloadFile.getUrl("https://webapi.test.notifichedigitali.it/delivery-push/");
        final String workingDirectory = System.getProperty("user.dir");

        nomeFile = nomeFile.replace(" ","_").replace(":", "");
        File file = new File(workingDirectory+"/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf");

        downloadFile.download(url,file);

    }

    @Then("Si controlla il testo all interno del file {string}")
    public void siControllaIlTestoAlSuoInterno(String nomeFile) {
        logger.info("Si controlla che il testo al suo interno si coretto");

        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        Map<String, String> infoNotifiche = dettaglioNotificaMittenteSection.recuperoInfoNotifiche();
        if(nomeFile.equals("Attestazione_opponibile_a_terzi_notifica_presa_in_carico")){
            if (dettaglioNotificaMittenteSection.controlloTestoFile("/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf", infoNotifiche.get("mittente"))) {
                logger.info("Il nome del mittente all'interno del file è corretto");
            } else {
                logger.error("Il nome del mittente  all'interno del file  NON è corretto");
                Assert.fail("Il nome del mittente  all'interno del file  NON è corretto");
            }
        }

        if (dettaglioNotificaMittenteSection.controlloTestoFile("/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf", infoNotifiche.get("destinatario"))) {
            logger.info("Il nome del destinatario all'interno del file è corretto");
        } else {
            logger.error("Il nome del destinatario  all'interno del file  NON è corretto");
            Assert.fail("Il nome del destinatario  all'interno del file  NON è corretto");
        }

        if (dettaglioNotificaMittenteSection.controlloTestoFile("/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf", infoNotifiche.get("codiceFiscale"))) {
            logger.info("Il codiceFiscale del destinatario all'interno del file è corretto");
        } else {
            logger.error("Il codiceFiscale del destinatario  all'interno del file  NON è corretto");
            Assert.fail("Il codiceFiscale del destianatario  all'interno del file  NON è corretto");
        }

        if (dettaglioNotificaMittenteSection.controlloTestoFileData("/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf", infoNotifiche.get("data"))) {
            logger.info("La data della notifica all'interno del file è corretta");
        } else {
            logger.error("La data della notifica all'interno del file  NON è corretta");
            Assert.fail("La data della notifica  all'interno del file  NON è corretta");
        }

        if (dettaglioNotificaMittenteSection.controlloTestoFileCodiceIUN("/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf", infoNotifiche.get("codiceIUN"))) {
            logger.info("Il codice IUN della notifica all'interno del file è corretto");
        } else {
            logger.error("Il codice IUN della notifica all'interno del file  NON è corretto");
            Assert.fail("Il codice IUN della notifica  all'interno del file  NON è corretto");
        }
    }
}
