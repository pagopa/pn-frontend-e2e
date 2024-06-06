package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.DettaglioNotificaSection;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.mittente.DisserviziAppPAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaMittenteSection;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.DownloadFile;
import it.pn.frontend.e2e.utility.WebTool;
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
    private Map<String, Object> datiNotifica = new HashMap<>();
    private DownloadFile downloadFile;

    @When("Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita")
    public void clickNotificaRestituita() {
        logger.info("Si clicca sulla notifica restituita");
        logger.info("GENERATED IUN: {}", System.getProperty("IUN"));
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.selezionaNotificaIUN(System.getProperty("IUN"));
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
        String variabileAmbiente = System.getProperty("environment");
        File pathCartella = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/mittente");
        downloadFile = new DownloadFile(this.driver);
        boolean headless = System.getProperty("headless").equalsIgnoreCase("true");
        if (!downloadFile.controlloEsistenzaCartella(pathCartella)) {
            pathCartella.mkdirs();
        }
        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        int count = 1;

        dettaglioNotificaMittenteSection.waitLoadingSpinner();
        String codiceIUN;
        String destinatario = dettaglioNotificaMittenteSection.getInfoNotifica(1);
        if (destinatario.contains("-")) {
            codiceIUN = dettaglioNotificaMittenteSection.getInfoNotifica(3);
        } else {
            codiceIUN = dettaglioNotificaMittenteSection.getInfoNotifica(4);
        }
        dettaglioNotificaMittenteSection.clickLinkDocumentiAllegati();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        final String filepath = workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/mittente/notificaN";

        final String urlDocumenti = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/delivery/notifications/sent/" + codiceIUN + "/attachments/documents/0";

        final String urlDocumentiAllegati = downloadFile.getUrl(urlDocumenti);
        File file = new File(filepath + count + ".pdf");
        if (headless && urlDocumentiAllegati.isEmpty()) {
            String testoLink = dettaglioNotificaMittenteSection.getTextDocumentiAllegati();
            logger.error("Non è stato recuperato url per il download per il link: " + testoLink);
            Assert.fail("Non è stato recuperato url per il download per il link: " + testoLink);
        }
        downloadFile.download(urlDocumentiAllegati, file, headless);
        if (!headless) {
            dettaglioNotificaSection.goBack();
        }
        dettaglioNotificaSection.waitLoadDettaglioNotificaDESection();
        count = count + 1;

        int numeroLinkAvvenutaRicezione = dettaglioNotificaMittenteSection.getLinkAvvenutaRicezione();
        for (int i = 1; i < numeroLinkAvvenutaRicezione; i++) {
            dettaglioNotificaMittenteSection.clickLinkAvvenutaRicezione(i);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            final String url = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/delivery-push/" + codiceIUN + "/document/AAR?documentId=safestorage:";
            final String urlAvvenutaRicezione = downloadFile.getUrl(url);
            if (headless && urlAvvenutaRicezione.isEmpty()) {
                String testoLink = dettaglioNotificaMittenteSection.getTextLinkAvvenutaRicezione(i);
                logger.error("Non è stato recuperato url per il download per il link: " + testoLink);
                Assert.fail("Non è stato recuperato url per il download per il link: " + testoLink);
            }
            file = new File(filepath + count + ".pdf");
            count = count + 1;

            downloadFile.download(urlAvvenutaRicezione, file, headless);
            if (!headless) {
                dettaglioNotificaSection.goBack();
            }
            dettaglioNotificaSection.waitLoadDettaglioNotificaDESection();
        }

        int numeroLinkAttestazioniOpponibile = dettaglioNotificaSection.getLinkAttestazioniOpponibili();

        for (int i = 0; i < numeroLinkAttestazioniOpponibile; i++) {
            dettaglioNotificaSection.clickLinkAttestazioniOpponibile(i);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            final String urlAttestazione = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/delivery-push/";
            final String urlFileAttestazioneOppponubile = downloadFile.getUrl(urlAttestazione);
            if (headless && urlFileAttestazioneOppponubile.isEmpty()) {
                String testoLink = dettaglioNotificaSection.getTextLinkAttestazioniOpponibili(i);
                logger.error("Non è stato recuperato url per il download per il link: " + testoLink);
                Assert.fail("Non è stato recuperato url per il download per il link: " + testoLink);
            }
            file = new File(filepath + count + ".pdf");
            count = count + 1;
            downloadFile.download(urlFileAttestazioneOppponubile, file, headless);
            if (!headless) {
                dettaglioNotificaSection.goBack();
            }
            dettaglioNotificaSection.waitLoadDettaglioNotificaDESection();
        }
        count = count - 1;
        final String pathOfDownloadedFile = workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/mittente";
        downloadFile.controlloDownload(pathOfDownloadedFile, count);

    }

    @And("Nella sezione Dettaglio Notifiche si scarica il documento allegato")
    public void downloadDocumentiAllegati(){
        logger.info("Si scaricano solo i documenti Allegati all'interno della notifica");

        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        DataPopulation dataPopulation = new DataPopulation();

        String workingDirectory = System.getProperty("user.dir");
        String variabileAmbiente = System.getProperty("environment");
        File pathCartella = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/mittente");
        DownloadFile downloadFile = new DownloadFile(this.driver);
        boolean headless = System.getProperty("headless").equalsIgnoreCase("true");
        if (!downloadFile.controlloEsistenzaCartella(pathCartella)) {
            pathCartella.mkdirs();
        }
        int count = 1;

        dettaglioNotificaMittenteSection.waitLoadingSpinner();
        String codiceIUN;
        String destinatario = dettaglioNotificaMittenteSection.getInfoNotifica(1);
        if (destinatario.contains("-")) {
            codiceIUN = dettaglioNotificaMittenteSection.getInfoNotifica(3);
        } else {
            codiceIUN = dettaglioNotificaMittenteSection.getInfoNotifica(4);
        }
        dettaglioNotificaMittenteSection.clickLinkDocumentiAllegati();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        final String filepath = workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/mittente/notificaN";
        final String urlDocumenti = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/delivery/notifications/sent/" + codiceIUN + "/attachments/documents/0";
        final String urlDocumentiAllegati = downloadFile.getUrl(urlDocumenti);
        File file = new File(filepath + count + "PN_NOTIFICATION_ATTACHMENTS.pdf");

        if (headless && urlDocumentiAllegati.isEmpty()) {
            logger.error("Non è stato recuperato l'URL per il download dei documenti Allegati.");
            Assert.fail("Non è stato recuperato l'URL per il download dei documenti Allegati.");
        }
        downloadFile.download(urlDocumentiAllegati, file, headless);
        if (!headless) {
            dettaglioNotificaMittenteSection.goBack();
        }
        dettaglioNotificaSection.waitLoadDettaglioNotificaDESection();
        count++;

        final String pathOfDownloadedFile = workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/mittente";
        downloadFile.controlloDownload(pathOfDownloadedFile, count);
    }

    @Then("Si clicca sul documento allegato")
    public void clickDocumentoAllegato(){
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        dettaglioNotificaMittenteSection.clickLinkDocumentiAllegati();
        driver.navigate().back();
    }

    @Then("Si clicca sul documento AAR")
    public void clickDocumentoAAR(){
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        dettaglioNotificaMittenteSection.clickLinkAvvenutaRicezione(0);
        driver.navigate().back();
    }

    @Then("Si clicca sul documento Attestazione")
    public void clickDocumentoAttestazione(){
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.clickLinkAttestazioniOpponibile(0);
        driver.navigate().back();
    }
    @And("Nella sezione Dettaglio Notifiche si scarica il file AAR")
    public void downloadFileAAR(){
        logger.info("Si scaricano solo i file AAR all'interno della notifica");

        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        DataPopulation dataPopulation = new DataPopulation();

        String workingDirectory = System.getProperty("user.dir");
        String variabileAmbiente = System.getProperty("environment");
        File pathCartella = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/mittente");
        DownloadFile downloadFile = new DownloadFile(this.driver);
        boolean headless = System.getProperty("headless").equalsIgnoreCase("true");
        if (!downloadFile.controlloEsistenzaCartella(pathCartella)) {
            pathCartella.mkdirs();
        }
        int count = 1;

        dettaglioNotificaMittenteSection.waitLoadingSpinner();
        String codiceIUN;
        String destinatario = dettaglioNotificaMittenteSection.getInfoNotifica(1);
        if (destinatario.contains("-")) {
            codiceIUN = dettaglioNotificaMittenteSection.getInfoNotifica(3);
        } else {
            codiceIUN = dettaglioNotificaMittenteSection.getInfoNotifica(4);
        }
        int numeroLinkAvvenutaRicezione = dettaglioNotificaMittenteSection.getLinkAvvenutaRicezione();
        for (int i = 1; i < numeroLinkAvvenutaRicezione; i++) {
            dettaglioNotificaMittenteSection.clickLinkAvvenutaRicezione(i);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            final String url = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/delivery-push/" + codiceIUN + "/document/AAR?documentId=safestorage:";
            final String urlAvvenutaRicezione = downloadFile.getUrl(url);
            if (urlAvvenutaRicezione.isEmpty()) {
                String testoLink = dettaglioNotificaMittenteSection.getTextLinkAvvenutaRicezione(i);
                logger.error("Non è stato recuperato l'URL per il download per il link: " + testoLink);
                Assert.fail("Non è stato recuperato l'URL per il download per il link: " + testoLink);
            }
            File file = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/mittente/notificaN" + count + "Avviso_di_avvenuta_ricezione.pdf");
            downloadFile.download(urlAvvenutaRicezione, file, headless);
            if (!headless) {
                dettaglioNotificaMittenteSection.goBack();
            }
            dettaglioNotificaSection.waitLoadDettaglioNotificaDESection();
            count++;
        }

        final String pathOfDownloadedFile = workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/mittente";
        downloadFile.controlloDownload(pathOfDownloadedFile, count);
    }

    @And("Nella sezione Dettaglio Notifiche si scarica il file Attestazioni Opponibili")
    public void downloadAttestazioniOpponibili(){
            logger.info("Si scaricano solo i file per attestazioni opponibili all'interno della notifica");

            DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
            DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
            DataPopulation dataPopulation = new DataPopulation();

            String workingDirectory = System.getProperty("user.dir");
            String variabileAmbiente = System.getProperty("environment");
            File pathCartella = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/mittente");
            DownloadFile downloadFile = new DownloadFile(this.driver);
            boolean headless = System.getProperty("headless").equalsIgnoreCase("true");
            if (!downloadFile.controlloEsistenzaCartella(pathCartella)) {
                pathCartella.mkdirs();
            }
            int count = 1;

        dettaglioNotificaMittenteSection.waitLoadingSpinner();
        String codiceIUN;
        String destinatario = dettaglioNotificaMittenteSection.getInfoNotifica(1);
        if (destinatario.contains("-")) {
            codiceIUN = dettaglioNotificaMittenteSection.getInfoNotifica(3);
        } else {
            codiceIUN = dettaglioNotificaMittenteSection.getInfoNotifica(4);
        }
        int numeroLinkAttestazioniOpponibile = dettaglioNotificaSection.getLinkAttestazioniOpponibili();
            for (int i = 0; i < numeroLinkAttestazioniOpponibile; i++) {
                dettaglioNotificaSection.clickLinkAttestazioniOpponibile(i);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                final String urlAttestazione = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/delivery-push/";
                final String urlFileAttestazioneOpponibile = downloadFile.getUrl(urlAttestazione);
                if (urlFileAttestazioneOpponibile.isEmpty()) {
                    String testoLink = dettaglioNotificaSection.getTextLinkAttestazioniOpponibili(i);
                    logger.error("Non è stato recuperato l'URL per il download per il link: " + testoLink);
                    Assert.fail("Non è stato recuperato l'URL per il download per il link: " + testoLink);
                }
                File file = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/mittente/notificaN" + count + "Attestazioni_Opponoboli.pdf");
                downloadFile.download(urlFileAttestazioneOpponibile, file, headless);
                if (!headless) {
                    dettaglioNotificaSection.goBack();
                }
                dettaglioNotificaSection.waitLoadDettaglioNotificaDESection();
                count++;
            }

            final String pathOfDownloadedFile = workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/mittente";
            downloadFile.controlloDownload(pathOfDownloadedFile, count);
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
        boolean headless = System.getProperty("headless").equalsIgnoreCase("true");
        this.datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        dettaglioNotificaMittenteSection.clickLinkAttestazioneOpponibile(nomeFile);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        downloadFile = new DownloadFile(this.driver);

        String variabileAmbiente = System.getProperty("environment");
        final String url = downloadFile.getUrl("https://webapi." + variabileAmbiente + ".notifichedigitali.it/delivery-push/");
        if (headless && url.isEmpty()) {
            logger.error("Non è stato recuperato url per il download per il link: " + nomeFile);
            Assert.fail("Non è stato recuperato url per il download per il link: " + nomeFile);
        }
        nomeFile = nomeFile.replace(" ", "_").replace(":", "");
        File file = new File("src/test/resources/dataPopulation/downloadFileNotifica/mittente/" + nomeFile + ".pdf");
        logger.info("Il file verrà scaricato in: " + file.getAbsolutePath());
        downloadFile.download(url, file, headless);
        if (!headless) {
            dettaglioNotificaMittenteSection.goBack();
        }
    }

    @Then("Si controlla il testo all interno del file {string}")
    public void siControllaIlTestoAlSuoInterno(String nomeFile) {
        logger.info("Si controlla che il testo al suo interno si corretto");

        DettaglioNotificaMittenteSection dettaglioNotificaMittenteSection = new DettaglioNotificaMittenteSection(this.driver);
        Map<String, String> infoNotifiche = dettaglioNotificaMittenteSection.recuperoInfoNotifiche();
        if (nomeFile.contains("PN_NOTIFICATION_ATTACHMENTS")){
            if (dettaglioNotificaMittenteSection.controlloTestoFile(nomeFile, "A Simple PDF File")) {
                logger.info("Il testo all'interno del file è corretto");
            } else {
                logger.error("Il testo all'interno del file  NON è corretto");
                Assert.fail("Il testo  all'interno del file  NON è corretto");
            }
        } else if (nomeFile.contains("Avviso di avvenuta ricezione")) {
            if (dettaglioNotificaMittenteSection.controlloTestoFile(nomeFile, "A Simple PDF File")) {
                logger.info("Il testo all'interno del file è corretto");
            } else {
                logger.error("Il testo all'interno del file  NON è corretto");
                Assert.fail("Il testo  all'interno del file  NON è corretto");
            }
        } else {
            if (nomeFile.equals("Attestazione_opponibile_a_terzi_notifica_presa_in_carico")) {
                if (dettaglioNotificaMittenteSection.controlloTestoFile(nomeFile, infoNotifiche.get("mittente"))) {
                    logger.info("Il nome del mittente all'interno del file è corretto");
                } else {
                    logger.error("Il nome del mittente  all'interno del file  NON è corretto");
                    Assert.fail("Il nome del mittente  all'interno del file  NON è corretto");
                }
            }

            if (dettaglioNotificaMittenteSection.controlloTestoFile(nomeFile, infoNotifiche.get("destinatario"))) {
                logger.info("Il nome del destinatario all'interno del file è corretto");
            } else {
                logger.error("Il nome del destinatario  all'interno del file  NON è corretto");
                Assert.fail("Il nome del destinatario  all'interno del file  NON è corretto");
            }

            if (dettaglioNotificaMittenteSection.controlloTestoFile(nomeFile, infoNotifiche.get("codiceFiscale"))) {
                logger.info("Il codiceFiscale del destinatario all'interno del file è corretto");
            } else {
                logger.error("Il codiceFiscale del destinatario  all'interno del file  NON è corretto");
                Assert.fail("Il codiceFiscale del destianatario  all'interno del file  NON è corretto");
            }

            if (dettaglioNotificaMittenteSection.controlloTestoFileData(nomeFile, infoNotifiche.get("data"))) {
                logger.info("La data della notifica all'interno del file è corretta");
            } else {
                logger.error("La data della notifica all'interno del file  NON è corretta");
                Assert.fail("La data della notifica  all'interno del file  NON è corretta");
            }

            if (dettaglioNotificaMittenteSection.controlloTestoFileCodiceIUN(nomeFile, infoNotifiche.get("codiceIUN"))) {
                logger.info("Il codice IUN della notifica all'interno del file è corretto");
            } else {
                logger.error("Il codice IUN della notifica all'interno del file  NON è corretto");
                Assert.fail("Il codice IUN della notifica  all'interno del file  NON è corretto");
            }
        }
    }

    @And("Nella pagina Piattaforma Notifiche si recupera un codice IUN di una persona giuridica")
    public void nellaPaginaPiattaformaNotificheSiRecuperaUnCodiceIUN() {
        logger.info("Nella pagina Piattaforma Notifiche si recupera un codice IUN");

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.siCambiaIlNumeroElementiVisualizzatiAttraversoIlFiltro();
        piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
        piattaformaNotifichePage.waitLoadingSpinner();
        List<String> codiciIun = piattaformaNotifichePage.getCodiceIunPersonaGiuridica();
        DataPopulation dataPopulation = new DataPopulation();

        this.datiNotifica = dataPopulation.readDataPopulation("datiNotificaPG.yaml");
        String codiceIun = this.datiNotifica.get("codiceIUN").toString();
        if (codiciIun.contains(codiceIun)) {
            piattaformaNotifichePage.inserimentoCodiceIUN(codiceIun);
        } else {
            piattaformaNotifichePage.inserimentoCodiceIUN(codiciIun.get(0));
        }

    }

    @And("Nella pagina Piattaforma Notifiche si verifica l'esistenza della notifica con il codice IUN")
    public void nellaPaginaPiattaformaNotificheSiVerificaLEsistenzaDellaNotificaConIlCodiceIUN() {
        logger.info("Si verifica l'esistenza della notifica con il codice IUN");

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        BackgroundTest backgroundTest = new BackgroundTest();
        DataPopulation dataPopulation = new DataPopulation();

        List<String> codiciIun = piattaformaNotifichePage.getCodiceIunPresenti();
        Map<String, Object> personaFisica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        String codiceIun = personaFisica.get("codiceIUN").toString();
        if (!codiciIun.contains(codiceIun)) {
            backgroundTest.invioNotificaErrorePec();
        }
    }

    @And("Download file attestazione disservizio")
    public void downloadFileAttestazioneDisservizio() {
        logger.info("si effettua download del disservizio");

        DisserviziAppPAPage disserviziAppPAPage = new DisserviziAppPAPage(driver);

        disserviziAppPAPage.downloadAttestazione();
        WebTool.waitTime(3);
    }
}
