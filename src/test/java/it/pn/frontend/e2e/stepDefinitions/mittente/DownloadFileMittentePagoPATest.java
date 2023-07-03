package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePAPage;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaSection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class DownloadFileMittentePagoPATest {
    private static final Logger logger = LoggerFactory.getLogger("DownloadFileMittentePagoPATest");
    private final WebDriver driver = Hooks.driver;
    private List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;

    private  Map<String,Object> datiNotifica = new HashMap<>();

    private String tabPrimeryTab;


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
        dettaglioNotificaSection.clickLinkDocumentiAllegati();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String urlDocumentiAllegati = getUrl("https://webapi.test.notifichedigitali.it/delivery/notifications/sent/" + this.datiNotifica.get("codiceIUN").toString() + "/attachments/documents/0");
        dettaglioNotificaSection.downloadFileNotifica("src/test/resources/dataPopulation/downloadFileNotifica/mittente",urlDocumentiAllegati,1);
        dettaglioNotificaSection.clickLinkAvvenutaRicezione();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String urlAvvenutaRicezione = getUrl("https://webapi.test.notifichedigitali.it/delivery-push/"+ this.datiNotifica.get("codiceIUN").toString() +"/document/AAR?documentId=safestorage:");
        dettaglioNotificaSection.downloadFileNotifica("src/test/resources/dataPopulation/downloadFileNotifica/mittente",urlAvvenutaRicezione,2);

        int numeroLinkAttestazioniOpponibile = dettaglioNotificaSection.getLinkAttestazioniOpponubili();

        for (int i = 0; i <numeroLinkAttestazioniOpponibile ; i++) {
            dettaglioNotificaSection.clickLinkAttestazionipponibile(i);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String urlFileAttestazioneOppponubile = getUrl("https://webapi.test.notifichedigitali.it/delivery-push/"+this.datiNotifica.get("codiceIUN").toString()+"/legal-facts/");
            dettaglioNotificaSection.downloadFileNotifica("src/test/resources/dataPopulation/downloadFileNotifica/mittente",urlFileAttestazioneOppponubile,3);
        }


    }

    private String getUrl(String urlChiamata) {
        String url = "";
        for (int i = 0; i < netWorkInfos.size(); i++) {
            if (netWorkInfos.get(i).getRequestUrl().contains(urlChiamata)){
                String[] body = netWorkInfos.get(i).getResponseBody().split("url");
                url = body[1].substring(3,body[1].length()-2);
                break;
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
        String url = getUrl("https://webapi.test.notifichedigitali.it/delivery-push/");



        dettaglioNotificaSection.downloadFileNotifica("/src/test/resources/dataPopulation/downloadFileNotifica/mittente", url, 1);
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
    }
}
