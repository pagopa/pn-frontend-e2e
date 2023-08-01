package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.utility.DownloadFile;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.DettaglioNotificaFRSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.HeadeFRSection;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaSection;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NotifichePersonaFisicaPAgoPATest {

    private static final Logger logger = LoggerFactory.getLogger("NotifichePersonaFisicaTest");

    private final WebDriver driver = Hooks.driver;

    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;

    @When("Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone Notifiche")
    public void nella_piattaforma_destinatario_cliccare_sul_bottone_notifiche() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.clickNotificheButton();
    }

    @Then("pagina Piattaforma  Notifiche persona fisica viene visualizzata correttamente")
    public void paginaPiattaformaNotificheDestinatarioVieneVisualizzataCorrettamente() {
            HeadeFRSection headeFRSection = new HeadeFRSection(this.driver);
            headeFRSection.waitLoadHeaderDESection();

            CookiesSection cookiesSection = new CookiesSection(this.driver);
            if (cookiesSection.waitLoadCookiesPage()){
                cookiesSection.selezionaAccettaTuttiButton();
            }

            NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
            notifichePFPage.waitLoadNotificheDEPage();
            if (notifichePFPage.verificaPresenzaCodiceIumTextField()) {
                logger.info("text field codice ium presente");
            } else {
                logger.info("text field codice ium non presente");
                Assert.fail("text field codice ium non presente");
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

        if (numeroRigheNotifiche !=0){
            logger.info("Si visualizza correttamente l'elenco notifica");
        } else {
            logger.error("NON visualizza correttamente l'elenco notifica");
            Assert.fail("NON visualizza correttamente l'elenco notifica");
        }
    }

    @And("Si visualizzano le notifiche dalla piu recente")
    public void siVisualizzanoLeNotificheDallaPiuRecente() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        List<WebElement> dateNotifiche = notifichePFPage.getDateNotifiche();

        if(dateNotifiche.size() != 0){
            boolean result = notifichePFPage.controllaNotifiche(dateNotifiche);
            if (result){
                logger.info("Le date sono ordinate correttamente");
            }else {
                logger.error("Le date NON sono ordinate correttamente");
                Assert.fail("Le date NON sono ordinate correttamente");
            }
        }else {
            logger.error("Non sono presenti notifiche con date");
            Assert.fail("Non sono presenti notifiche con date");
        }
    }

    @And("Si aggiorna la paginazione utilizzando le frecce")
    public void siAggiornaLaPaginazioneUtilizzandoLeFrecce() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.clickSullaFreccia();
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
            TimeUnit.SECONDS.sleep(4);
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

    @When("Il persona fisica clicca sulla notifica restituita")
    public void ilDestinatarioCliccaSullaNotificaRestituita() {
        logger.info("Si clicca sulla notifica");
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.selezionaNotifica();
    }

    @And("Si visualizza correttamente la section Dettaglio Notifica persona fisica")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotificaDestinatario() {
        DettaglioNotificaFRSection dettaglioNotificaFRSection = new DettaglioNotificaFRSection(this.driver);
        dettaglioNotificaFRSection.waitLoadDettaglioNotificaDESection();
    }

    @Then("Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica persona fisica, e si controlla che il download sia avvenuto")
    public void siSelezionanoIFileAttestazioniOpponibiliDaScaricareAllInternoDellaNotificaDestinatarioESiControllaCheIlDownloadSiaAvvenuto() {
        DettaglioNotificaFRSection dettaglioNotificaFRSection = new DettaglioNotificaFRSection(this.driver);
        int numeroLinkAttestazioniOpponibile = dettaglioNotificaFRSection.getLinkAttestazioniOpponubili();
        DownloadFile downloadFile = new DownloadFile();
        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> datiNotifica = dataPopulation.readDataPopulation("datiNotifica.yaml");
        String workingDirectory = System.getProperty("user.dir");

        for (int i = 0; i <numeroLinkAttestazioniOpponibile ; i++) {
            dettaglioNotificaFRSection.clickLinkAttestazionipponibile(i);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String urlFileAttestazioneOppponubile = getUrl("https://webapi.test.notifichedigitali.it/delivery-push/"+datiNotifica.get("codiceIUN").toString()+"/legal-facts/");
            File file = new File(workingDirectory+"/src/test/resources/dataPopulation/downloadFileNotifica/destinatario/notificaN"+i+".pdf");
            downloadFile.download(urlFileAttestazioneOppponubile,file);
        }
        downloadFile.controlloDownload(workingDirectory+"/src/test/resources/dataPopulation/downloadFileNotifica/destinatario",numeroLinkAttestazioniOpponibile);
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
                logger.info("url",url);
            }
        }
        return url;

    }

    @And("Si clicca sul opzione Vedi Dettaglio")
    public void siCliccaSulOpzioneVediDettaglio() {
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(driver);
        dettaglioNotificaSection.selezioneVediDettaglioButton();
    }
}



