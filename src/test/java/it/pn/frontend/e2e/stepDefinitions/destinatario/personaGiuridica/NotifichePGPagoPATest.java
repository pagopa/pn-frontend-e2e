package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.DettaglioNotificaSection;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.HomePagePG;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.PiattaformaNotifichePGPAPage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.DownloadFile;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NotifichePGPagoPATest {
    private final Logger logger = LoggerFactory.getLogger("NotifichePGPagoPATest");
    private final WebDriver driver = Hooks.driver;

    private final PiattaformaNotifichePGPAPage piattaformaNotifichePGPAPage = new PiattaformaNotifichePGPAPage(this.driver);
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
        CookiesSection cookiesSection = new CookiesSection(this.driver);
        if(cookiesSection.waitLoadCookiesPage()){
            logger.info("Si accettano i cookies");
            cookiesSection.selezionaAccettaTuttiButton();
        }
        piattaformaNotifichePGPAPage.waitLoadPitattaformaNotificaPage();
    }

    @When("Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaClickSulBottoneDeleghe() {
        logger.info("Si clicca sul bottone Deleghe");

        piattaformaNotifichePGPAPage.clickSuDelegeButton();
    }

    @And("Nella Pagina Notifiche persona giuridica si clicca su notifiche delegate")
    public void nellaPaginaNotifichePersonaGiuridicaSiCliccaSuNotificheDelegate() {
        logger.info("Si clicca correttamente su notifiche delegate");
        piattaformaNotifichePGPAPage.clickNotificheDelegate();
    }

    @And("Si visualizza correttamente la Pagina Notifiche persona giuridica sezione notifiche delegate")
    public void siVisualizzaCorrettamenteLaPaginaNotifichePersonaGiuridicaSezioneNotificheDelegate() {
        piattaformaNotifichePGPAPage.waitLoadSezioneNotificheDelegate();
    }

    @When("Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaSiCliccaSulBottoneITuoiRecapiti() {
        logger.info("Si clicca sulla voce recapiti nel menu");
        piattaformaNotifichePGPAPage.clickRecapitiButton();
    }

    @Then("Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica persona giuridica, e si controlla che il download sia avvenuto {string}")
    public void siSelezionanoIFileAttestazioniOpponibiliDaScaricareAllInternoDellaNotificaPersonaGiuridicaESiControllaCheIlDownloadSiaAvvenuto(String dpFile) {
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        int numeroLinkAttestazioniOpponibile = dettaglioNotificaSection.getLinkAttestazioniOpponubili();
        DownloadFile downloadFile = new DownloadFile();
        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> datiNotifica = dataPopulation.readDataPopulation(dpFile+".yaml");
        String workingDirectory = System.getProperty("user.dir");
        File pathCartella = new File(workingDirectory+"/src/test/resources/dataPopulation/downloadFileNotifica/destinatario/personaGiuridica");
        if (!downloadFile.controlloEsistenzaCartella(pathCartella)){
            pathCartella.mkdirs();
        }
        for (int i = 0; i <numeroLinkAttestazioniOpponibile ; i++) {
            dettaglioNotificaSection.clickLinkAttestazionipponibile(i);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String urlFileAttestazioneOppponubile = downloadFile.getUrl("https://webapi.test.notifichedigitali.it/delivery-push/"+datiNotifica.get("codiceIUN").toString()+"/legal-facts/");
            File file = new File(workingDirectory+"/src/test/resources/dataPopulation/downloadFileNotifica/destinatario/notificaN"+i+".pdf");
            downloadFile.download(urlFileAttestazioneOppponubile,file);
        }
        downloadFile.controlloDownload(workingDirectory+"/src/test/resources/dataPopulation/downloadFileNotifica/destinatario",numeroLinkAttestazioniOpponibile);
    }
}
