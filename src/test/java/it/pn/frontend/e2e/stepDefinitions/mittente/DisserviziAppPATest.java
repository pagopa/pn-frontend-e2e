package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.AccediAPiattaformaNotifichePage;
import it.pn.frontend.e2e.pages.mittente.DisserviziAppPAPage;
import it.pn.frontend.e2e.utility.DownloadFile;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class DisserviziAppPATest {
    private static final Logger logger = LoggerFactory.getLogger("DisserviziAppPATest");
    private final WebDriver driver = Hooks.driver;
    private final AccediAPiattaformaNotifichePage notifichePage = new AccediAPiattaformaNotifichePage(this.driver);
    private final DisserviziAppPAPage disserviziAppPAPage = new DisserviziAppPAPage(this.driver);

    @When("Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'")
    public void nellaPaginaPiattaformaNotificheSelezionareLaVoceStatoDellaPiattaforma() {
        logger.info("Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'");
        notifichePage.clickStatoDellaPiattaforma();
    }

    @Then("Si visualizza correttamente la pagina dello 'stato della piattaforma' di mittente")
    public void siVisualizzaCorrettamenteLaPaginaDelloStatoDellaPiattaformaDiMittente() {
        logger.info("Si visualizza correttamente la Pagina dello Stato della piattaforma di mittente");
        disserviziAppPAPage.waitLoadStatoDellaPiattaformaPage();
    }

    @And("Si visualizza correttamente la tabella dei disservizi")
    public void siVisualizzaCorrettamenteLaTabellaDeiDisservizi() {
        logger.info("Si visualizza correttamente la tabella dei disservizi");
        disserviziAppPAPage.waitLoadDisserviziTable();
    }

    @And("Si visualizza un record in elenco relativo ad un disservizio ancora in corso")
    public void siVisualizzaUnRecordInElencoRelativoAdUnDisservizioAncoraInCorso() {
        logger.info("Si visualizza un record in elenco relativo ad un disservizio ancora in corso");
        disserviziAppPAPage.checkDisserviziInCorso();
    }

    @And("Si visualizza un record in elenco relativo ad un disservizio risolto")
    public void siVisualizzaUnRecordInElencoRelativoAdUnDisservizioRisolto() {
        logger.info("Si visualizza un record in elenco relativo ad un disservizio risolto");
        disserviziAppPAPage.checkDisserviziRisolto();
    }

    @And("Si scarica attestazione opponibile, e si controlla che il download sia avvenuto")
    public void siScaricaAttestazioneOpponibileDisservizi() throws IOException {
        logger.info("Si scarica attestazione opponibile");
        DownloadFile downloadFile = new DownloadFile(this.driver);
        String workingDirectory = System.getProperty("user.dir");
        File pathCartella = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/destinatario/personaGiuridica");

        boolean headless = System.getProperty("headless").equalsIgnoreCase("true");
        if (!downloadFile.controlloEsistenzaCartella(pathCartella)) {
            pathCartella.mkdirs();
        }
        disserviziAppPAPage.clickLinkAttestazioniOpponibileDisservizi(0);
        WebTool.waitTime(5);
        String legalFactId = downloadFile.getLegalFactId();
        String urlFileAttestazioneOpponibile = WebTool.getApiBaseUrl() + "downtime/legal-facts/" + legalFactId;

        File file = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/destinatario/notificaN" + 0 + ".pdf");
        downloadFile.downloadAttestazioneDisservizi(urlFileAttestazioneOpponibile, file, headless);
        if (!headless) {
            disserviziAppPAPage.goBack();
        }

        downloadFile.controlloDownload(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/destinatario", 1);
    }

    @And("Si visualizzano tutti i record in elenco relativi a disservizi risolti")
    public void siVisualizzanoTuttiIRecordInElencoRelativiADisserviziRisolti() {
        logger.info("Si visualizzano tutti i record in elenco relativi a disservizi risolti");
        disserviziAppPAPage.checkDisserviziDisponibili();
    }
}
