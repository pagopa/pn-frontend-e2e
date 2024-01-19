package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.DettaglioNotificaSection;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.HeaderFRSection;
import it.pn.frontend.e2e.utility.CookieConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.DownloadFile;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NotifichePersonaFisicaPAgoPATest {

    private static final Logger logger = LoggerFactory.getLogger("NotifichePersonaFisicaTest");
    private final WebDriver driver = Hooks.driver;
    private Map<String, Object> personaFisica = new HashMap<>();
    private final PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
    private final DataPopulation dataPopulation = new DataPopulation();


    @When("Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone Notifiche")
    public void nellaPiattaformaDestinatarioCliccareSulBottoneNotifiche() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.clickNotificheButton();
    }

    @Then("pagina Piattaforma  Notifiche persona fisica viene visualizzata correttamente")
    public void paginaPiattaformaNotificheDestinatarioVieneVisualizzataCorrettamente() {
        HeaderFRSection headerFRSection = new HeaderFRSection(this.driver);
        headerFRSection.waitLoadHeaderDESection();

        if (!CookieConfig.isCookieEnabled()) {
            CookiesSection cookiesSection = new CookiesSection(this.driver);
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPage();
        if (notifichePFPage.verificaPresenzaCodiceIunTextField()) {
            logger.info("text field codice iun presente");
        } else {
            logger.info("text field codice iun non presente");
            Assert.fail("text field codice iun non presente");
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("Si visualizza correttamente la Pagina Notifiche persona fisica")
    public void siVisualizzaCorrettamenteLaPaginaNotificheDestinatario() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.siVisualizzaPaginaNotifichePersonaFisica();
    }

    @And("Nella Pagina Notifiche persona fisica si visualizzano correttamente i filtri di ricerca")
    public void nellaPaginaNotificheDestinatarioSiVisualizzanoCorrettamenteIFiltriDiRicerca() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.siVisualizzanoFiltriRicerca();
    }

    @And("Nella Pagina Notifiche persona fisica si visualizza correttamente l elenco delle notifiche")
    public void nellaPaginaNotificheDestinatarioSiVisualizzaCorrettamenteLElencoDelleNotifiche() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.siVisualizzaElencoNotifiche();
    }

    @And("Si visualizza l elenco delle notifiche relative al delegante")
    public void siVisualizzaLElencoDelleNotificheRelativeAlDelegante() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.siVisualizzaElencoNotifiche();
    }

    @And("Si seleziona il nome del delegante nell elenco")
    public void siSelezionaIlNomeDelDeleganteNellElenco() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.clickNomeDelegante();
    }

    @Then("Si visualizzano tutte le notifiche del delegante selezionato")
    public void siVisualizzanoTutteLeNotificheDelDeleganteSelezionato() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.siVisualizzaElencoNotifiche();
    }

    @Then("Si visualizzano correttamente le notifiche in elenco paginato")
    public void siVisualizzanoCorrettamenteLeNotificheInElencoPaginato() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        int numeroRigheNotifiche = notifichePFPage.siVisualizzaNotifichePresenti();

        if (numeroRigheNotifiche != 0) {
            logger.info("Si visualizza correttamente l'elenco notifiche");
        } else {
            logger.error("NON visualizza correttamente l'elenco notifiche");
            Assert.fail("NON visualizza correttamente l'elenco notifiche");
        }
    }

    @And("Si visualizzano le notifiche dalla piu recente")
    public void siVisualizzanoLeNotificheDallaPiuRecente() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        List<WebElement> dateNotifiche = notifichePFPage.getDateNotifiche();

        if (dateNotifiche.size() != 0) {
            boolean result = notifichePFPage.controllaNotifiche(dateNotifiche);
            if (result) {
                logger.info("Le date sono ordinate correttamente");
            } else {
                logger.error("Le date NON sono ordinate correttamente");
                Assert.fail("Le date NON sono ordinate correttamente");
            }
        } else {
            logger.error("Non sono presenti notifiche con date");
            Assert.fail("Non sono presenti notifiche con date");
        }
    }

    @And("Si aggiorna la paginazione utilizzando le frecce")
    public void siAggiornaLaPaginazioneUtilizzandoLeFrecce() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.clickPaginaSuccessiva();
    }

    @And("Si visualizza correttamente una pagina diversa dalla precedente")
    public void siVisualizzaCorrettamenteUnaPaginaDiversaDallaPrecedente() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadPaginaDifferente();
    }

    @And("Ci si posiziona su una pagina differente attraverso i numeri")
    public void ciSiPosizionaSuUnaPaginaDifferenteAttraversoINumeri() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.siSceglieUnaPaginaDiversaConNumero();
    }

    @And("Si modifica il numero di notifiche visualizzate scegliendo un valore diverso da quello di default")
    public void siModificaIlNumeroDiNotificheVisualizzateScegliendoUnValoreDiversoDaQuelloDiDefault() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.modificaNumeroNotifichePagina();
    }

    @And("Ci si posiziona su una pagina differente da quella di default e si aumenta il numero di modifiche visualizzate")
    public void ciSiPosizionaSuUnaPaginaDifferenteDaQuellaDiDefaultESiAumentaIlNumeroDiModificheVisualizzate() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.numeroDiversoPagine();
    }

    @And("Si visualizza un numero di pagine visualizzate uguale a quello selezionato")
    public void siVisualizzaUnNumeroDiPagineVisualizzateUgualeAQuelloSelezionato() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int numeroRigheNotifiche = notifichePFPage.conteggioNotifiche();

        if (numeroRigheNotifiche == 20) {
            logger.info("Si visualizzano venti notifiche in elenco");
        } else {
            logger.error("NON si visualizzano venti notifiche in elenco");
            Assert.fail("NON si visualizzano venti notifiche in elenco");
        }
    }

    @When("La persona fisica clicca sulla notifica restituita")
    public void ilDestinatarioCliccaSullaNotificaRestituita() {
        logger.info("Si clicca sulla notifica");
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.selezionaNotifica();
    }

    @And("Si visualizza correttamente la section Dettaglio Notifica persona fisica")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotificaDestinatario() {
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        dettaglioNotificaSection.waitLoadDettaglioNotificaDESection();
    }

    @Then("Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica persona fisica, e si controlla che il download sia avvenuto {string}")
    public void siSelezionanoIFileAttestazioniOpponibiliDaScaricareAllInternoDellaNotificaDestinatarioESiControllaCheIlDownloadSiaAvvenuto(String dpFile) {
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(this.driver);
        int numeroLinkAttestazioniOpponibile = dettaglioNotificaSection.getLinkAttestazioniOpponibili();
        DownloadFile downloadFile = new DownloadFile(this.driver);
        DataPopulation dataPopulation = new DataPopulation();
        Map<String, Object> datiNotifica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        String workingDirectory = System.getProperty("user.dir");
        boolean headless = System.getProperty("headless").equalsIgnoreCase("true");
        File pathCartella = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/destinatario/personaFisica");
        if (!downloadFile.controlloEsistenzaCartella(pathCartella)) {
            pathCartella.mkdirs();
        }
        for (int i = 0; i < numeroLinkAttestazioniOpponibile; i++) {
            dettaglioNotificaSection.clickLinkAttestazioniOpponibile(i);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String urlFileAttestazioneOppponubile = downloadFile.getUrl("https://webapi.test.notifichedigitali.it/delivery-push/" + datiNotifica.get("codiceIUN").toString() + "/legal-facts/");
            if (headless && urlFileAttestazioneOppponubile.isEmpty()) {
                String testoLink = dettaglioNotificaSection.getTextLinkAttestazioniOpponibili(i);
                logger.error("Non è stato recuperato url per il download per il link: " + testoLink);
                Assert.fail("Non è stato recuperato url per il download per il link: " + testoLink);
            }
            File file = new File(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/destinatario/notificaN" + i + ".pdf");
            downloadFile.download(urlFileAttestazioneOppponubile, file, headless);
            if (!headless) {
                dettaglioNotificaSection.goBack();
            }
        }
        downloadFile.controlloDownload(workingDirectory + "/src/test/resources/dataPopulation/downloadFileNotifica/destinatario", numeroLinkAttestazioniOpponibile);
    }

    @And("Si clicca sul opzione Vedi Dettaglio")
    public void siCliccaSulOpzioneVediDettaglio() {
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(driver);
        dettaglioNotificaSection.selezioneVediDettaglioButton();
    }

    @And("Si visualizza correttamente la Pagina Notifiche persona fisica delegante {string}")
    public void siVisualizzaCorrettamenteLaPaginaNotifichePersonaFisicaDelegante(String dpFile) {
        DataPopulation dataPopulation = new DataPopulation();
        Map<String, Object> personaFisicaDelgante = dataPopulation.readDataPopulation(dpFile + ".yaml");
        String nome = personaFisicaDelgante.get("name").toString();
        String cognome = personaFisicaDelgante.get("familyName").toString();

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPageDelegante(nome, cognome);
    }

    @And("Nella pagina Piattaforma Notifiche PF si recupera un codice IUN valido")
    public void nellaPaginaPiattaformaNotificheSiRecuperaUnCodiceIUNValido() {
        logger.info("Si recupera un codice IUN valido");

        List<String> codiciIun = piattaformaNotifichePage.getCodiceIunPresentiPF();
        this.personaFisica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        String codiceIun = this.personaFisica.get("codiceIUN").toString();
        if (codiciIun.contains(codiceIun)) {
            piattaformaNotifichePage.inserimentoCodiceIUN(codiceIun);
        } else {
            piattaformaNotifichePage.inserimentoCodiceIUN(codiciIun.get(0));
            this.personaFisica.put("codiceIUN", codiciIun.get(0));
            dataPopulation.writeDataPopulation("datiNotifica.yaml", this.personaFisica);
        }
    }
}



