package it.pn.frontend.e2e.stepDefinitions.mittente;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.utility.DownloadFile;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePAPage;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaSection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class DownloadFileMittentePagoPATest {
    private static final Logger logger = LoggerFactory.getLogger("DownloadFileMittentePagoPATest");
    private final WebDriver driver = Hooks.driver;
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;

    private  Map<String,Object> datiNotifica = new HashMap<>();

     private DownloadFile downloadFile;

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
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        int count = 1;
        dettaglioNotificaSection.clickLinkDocumentiAllegati();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String workingDirectory = System.getProperty("user.dir");
        final String filepath = workingDirectory+"/src/test/resources/dataPopulation/downloadFileNotifica/mittente/notificaN";
        final String urlDocumenti ="https://webapi.test.notifichedigitali.it/delivery/notifications/sent/" + this.datiNotifica.get("codiceIUN").toString() + "/attachments/documents/0";

        final String urlDocumentiAllegati = getUrl(urlDocumenti);
        File file = new File(filepath+count+".pdf");
        downloadFile = new DownloadFile();
        downloadFile.download(urlDocumentiAllegati,file);
        count = count+1;

        int numeroLinkAvvenutaRicezione = dettaglioNotificaSection.getLinkAvvenutaRicezione();
        for (int i = 1; i <numeroLinkAvvenutaRicezione ; i++) {
            dettaglioNotificaSection.clickLinkAvvenutaRicezione(i);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            final String url = "https://webapi.test.notifichedigitali.it/delivery-push/"+ this.datiNotifica.get("codiceIUN").toString() +"/document/AAR?documentId=safestorage:";
            final String urlAvvenutaRicezione = getUrl(url);
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
            final String urlFileAttestazioneOppponubile = getUrl(urlAttestazione);
            file = new File(filepath+count+".pdf");
            count = count+1;
            downloadFile.download(urlFileAttestazioneOppponubile,file);
        }
        count = count -1;
        final String pathOfdownloadedFile = workingDirectory+"/src/test/resources/dataPopulation/downloadFileNotifica/mittente";
        downloadFile.controlloDownload(pathOfdownloadedFile,count);

    }

    private String getUrl(String urlChiamata) {
        String url = "";
        for (int i = 0; i < netWorkInfos.size(); i++) {
            if (netWorkInfos.get(i).getRequestUrl().contains(urlChiamata) && netWorkInfos.get(i).getRequestMethod().equals("GET")){
                String values = netWorkInfos.get(i).getResponseBody();
                List<String> results = Splitter.on(CharMatcher.anyOf(",;:")).splitToList(values);

                for (int index=0;index<results.size(); index++){
                    if(results.get(index).startsWith("//")) {
                        url = results.get(index);
                        break;
                    }
                }
                if(url.endsWith("}")) {
                    url = "https:" + url.substring(0, url.length() - 2);
                }else {
                    url = "https:" + url.substring(0, url.length() - 1);
                }
                logger.info("url: "+url);
            }
        }
        return url;
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

        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");

        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.clickLinkAttestazioneOpponibile(nomeFile);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final String url = getUrl("https://webapi.test.notifichedigitali.it/delivery-push/");
        final String workingDirectory = System.getProperty("user.dir");

        nomeFile = nomeFile.replace(" ","_").replace(":", "");
        File file = new File(workingDirectory+"/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf");

        downloadFile = new DownloadFile();
        downloadFile.download(url,file);

    }

    @Then("Si controlla il testo all interno del file {string}")
    public void siControllaIlTestoAlSuoInterno(String nomeFile) {
        logger.info("Si controlla che il testo al suo interno si coretto");

        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        Map<String, String> infoNotifiche = dettaglioNotificaSection.recuperoInfoNotifiche();
        if(nomeFile.equals("Attestazione_opponibile_a_terzi_notifica_presa_in_carico")){
            if (dettaglioNotificaSection.controlloTestoFile("/src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf", infoNotifiche.get("mittente").toString())) {
                logger.info("Il nome del mittente all'interno del file è corretto");
            } else {
                logger.error("Il nome del mittente  all'interno del file  NON è corretto");
                Assert.fail("Il nome del mittente  all'interno del file  NON è corretto");
            }
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
    }
}
