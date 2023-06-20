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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class DownloadFileMittentePagoPATest {
    private static final Logger logger = LoggerFactory.getLogger("DownloadFileMittentePagoPATest");
    private final WebDriver driver = Hooks.driver;
    private List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;


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
        String urlPDFDocuementiAllegati = getUrlPDFDocuementiAllegati();
        dettaglioNotificaSection.downloadFileNotifica("/src/test/resources/dataPopulation/downloadFileNotifica/mittente",urlPDFDocuementiAllegati,1);
        String urlPDFAvvenutaRicezione = getUrlPDFAvvenutaRicezione();
        dettaglioNotificaSection.downloadFileNotifica("/src/test/resources/dataPopulation/downloadFileNotifica/mittente",urlPDFAvvenutaRicezione,2);
        // dettaglioNotificaSection.downloadFileAttestazioni("/src/test/resources/dataPopulation/downloadFileNotifica/mittente");
        dettaglioNotificaSection.controlloDownload();
    }

    private String getUrlPDFAvvenutaRicezione() {
        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        String codiceIUN = datiNotifica.get("codiceIUN").toString();
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.clickLinkAvvenutaRicezione();
        String url = "";
        for (int i = 0; i < 30; i++) {
            List<String> numTab = new ArrayList<>(this.driver.getWindowHandles());
            if (numTab.size() == 2){
                this.driver.switchTo().window(numTab.get(1));
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Hooks hooks = new Hooks();
                hooks.captureHttpResponse();
                List<NetWorkInfo> netWorkInfos1 = Hooks.netWorkInfos;

                for (int y = 0; y < netWorkInfos1.size(); y++) {
                    if (netWorkInfos1.get(y).getRequestUrl().contains("https://webapi.test.notifichedigitali.it/delivery-push/"+ codiceIUN +"/document/AAR?documentId=safestorage:%2F%2FPN_AAR-e69ff3be92314ad4a3ea86024ef1a08a.pdf")) {
                        logger.info("Sono entrato nell'if");
                        String[] body = netWorkInfos1.get(y).getResponseBody().split("url");
                        url = body[1].substring(3, body[1].length() - 2);
                        break;
                    }
                }
                break;
            }else {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        logger.info("url proxy:"+url);
        return url;
    }


    private String getUrlPDFDocuementiAllegati() {
        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        String codiceIUN = datiNotifica.get("codiceIUN").toString();
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.clickLinkDocumentiAllegati();
        String url = "";
        for (int i = 0; i < 30; i++) {
            List<String> numTab = new ArrayList<>(this.driver.getWindowHandles());
            if (numTab.size() == 2){
                this.driver.switchTo().window(numTab.get(1));
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int y = 0; y < netWorkInfos.size(); y++) {
                    if (netWorkInfos.get(y).getRequestUrl().contains("https://webapi.test.notifichedigitali.it/delivery/notifications/sent/" + codiceIUN + "/attachments/documents/0")) {
                        logger.info("Sono entrato nell'if");
                        String[] body = netWorkInfos.get(y).getResponseBody().split("url");
                        url = body[1].substring(3, body[1].length() - 2);
                        break;
                    }
                }
                break;
            }else {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        logger.info("url proxy:"+url);
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

        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        String urlPDFFileAllegati = getUrlPDFileAllegati(nomeFile);
        dettaglioNotificaSection.downloadFileAttestazione(urlPDFFileAllegati, "/src/test/resources/dataPopulation/downloadFileNotifica/mittente",nomeFile);
    }

    private String getUrlPDFileAllegati(String nomePDF) {
        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        String codiceIUN = datiNotifica.get("codiceIUN").toString();
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.clickLinkAttestazioneOpponibile(nomePDF);
        String url = "";
        for (int i = 0; i < 30; i++) {
            List<String> numTab = new ArrayList<>(this.driver.getWindowHandles());
            if (numTab.size() == 2){
                this.driver.switchTo().window(numTab.get(1));
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int y = 0; y < netWorkInfos.size(); y++) {
                    if (netWorkInfos.get(y).getRequestUrl().contains("https://webapi.test.notifichedigitali.it/delivery-push/"+codiceIUN+"/legal-facts/")) {
                        logger.info("Sono entrato nell'if");
                        String[] body = netWorkInfos.get(y).getResponseBody().split("url");
                        url = body[1].substring(3, body[1].length() - 2);
                        break;
                    }
                }
                break;
            }else {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        logger.info("url proxy:"+url);
        return url;
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
