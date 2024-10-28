package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.AccediAPiattaformaNotifichePage;
import it.pn.frontend.e2e.pages.mittente.DisserviziAppPAPage;
import it.pn.frontend.e2e.utility.DownloadFile;
import it.pn.frontend.e2e.utility.WebTool;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
/*
Modifiche Principali

*Iniezione delle Dipendenze con @Autowired: Abbiamo rimosso la creazione manuale delle istanze e invece abbiamo iniettato le dipendenze WebDriver, WebDriverConfig, AccediAPiattaformaNotifichePage, e DisserviziAppPAPage tramite @Autowired.
* Integrazione DownloadFile e WebTool: Poiché DownloadFile e WebTool vengono utilizzati internamente senza dipendenze da Spring, non è necessario modificarli; il loro uso rimane invariato.
* Annotazione @Component: La classe DisserviziAppPATest è stata annotata come componente Spring per consentire l'iniezione automatica delle dipendenze. */
@Component
public class DisserviziAppPATest {

    private static final Logger logger = LoggerFactory.getLogger(DisserviziAppPATest.class);

    @Autowired
    private WebDriverConfig webDriverConfig;

    @Autowired
    private WebDriver driver;

    @Autowired
    private AccediAPiattaformaNotifichePage notifichePage;

    @Autowired
    private DisserviziAppPAPage disserviziAppPAPage;

    @When("Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'")
    public void nellaPaginaPiattaformaNotificheSelezionareLaVoceStatoDellaPiattaforma() {
        logger.info("Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'");
        notifichePage.clickStatoDellaPiattaforma();
    }

    @When("Nella pagina Piattaforma Notifiche selezionare la voce 'Notifiche'")
    public void nellaPaginaPiattaformaNotificheSelezionareLaVoceNotifiche() {
        logger.info("Nella pagina Piattaforma Notifiche selezionare la voce 'Notifiche'");
        notifichePage.clickNotifiche();
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

    @And("Si visualizza un record in elenco relativo ad un disservizio risolto {string}")
    public void siVisualizzaUnRecordInElencoRelativoAdUnDisservizioRisolto(String tipoDisservizio) {
        logger.info("Si visualizza un record in elenco relativo ad un disservizio risolto");
        disserviziAppPAPage.checkDisservizioRisolto(tipoDisservizio);
    }

    @And("Si scarica attestazione opponibile, e si controlla che il download sia avvenuto")
    public void siScaricaAttestazioneOpponibileDisservizi() throws IOException {
        logger.info("Si scarica attestazione opponibile");
        DownloadFile downloadFile = new DownloadFile(this.driver);
        String workingDirectory = System.getProperty("user.dir");
        File pathCartella = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/destinatario/personaGiuridica");

        boolean headless = webDriverConfig.getHeadless().equalsIgnoreCase("true");
        if (!downloadFile.controlloEsistenzaCartella(pathCartella)) {
            pathCartella.mkdirs();
        }
        disserviziAppPAPage.clickLinkAttestazioniOpponibileDisservizi(0);
        WebTool.waitTime(5);
        String legalFactId = downloadFile.getLegalFactId();
        String urlFileAttestazioneOpponibile = webDriverConfig.getBaseUrl() + "downtime/legal-facts/" + legalFactId;

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

    @And("Controllo corrispondenza dati con pdf")
    public void controlloCorrispondenzaDatiConPdf() {
        logger.info("check dati con pdf e disservizio");

        boolean isCorrect = disserviziAppPAPage.confrontoFileConDisservizio();
        if (!isCorrect) {
            logger.error("i dati del pdf non corrispondono a quelli della tabella");
            Assertions.fail("i dati del pdf non corrispondono a quelli della tabella");
        }
    }
}
