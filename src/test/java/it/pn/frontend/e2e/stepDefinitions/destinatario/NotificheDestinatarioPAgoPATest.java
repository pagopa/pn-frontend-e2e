package it.pn.frontend.e2e.stepDefinitions.destinatario;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.NotificheDEPage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.DettaglioNotificaDESection;
import it.pn.frontend.e2e.section.destinatario.HeaderDESection;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaSection;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotificheDestinatarioPAgoPATest {

    private static final Logger logger = LoggerFactory.getLogger("NotificheDestinatarioTest");

    private final WebDriver driver = Hooks.driver;

    @When("Nella pagina Piattaforma Notifiche Destinatario si clicca sul bottone Notifiche")
    public void nella_piattaforma_destinatario_cliccare_sul_bottone_notifiche() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.clickNotificheButton();
    }

    @Then("pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente")
    public void paginaPiattaformaNotificheDestinatarioVieneVisualizzataCorrettamente() {
            HeaderDESection headerDESection = new HeaderDESection(this.driver);
            headerDESection.waitLoadHeaderDESection();

            CookiesSection cookiesSection = new CookiesSection(this.driver);
            if (cookiesSection.waitLoadCookiesPage()){
                cookiesSection.selezionaAccettaTuttiButton();
            }

            NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
            notificheDEPage.waitLoadNotificheDEPage();
            if (notificheDEPage.verificaPresenzaCodiceIumTextField()) {
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

    @And("Si visualizza correttamente la Pagina Notifiche Destinatario")
    public void siVisualizzaCorrettamenteLaPaginaNotificheDestinatario() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.siVisualizzaPaginaNotificheDestinatario();
    }

    @And("Nella Pagina Notifiche Destinatario si visualizzano correttamente i filtri di ricerca")
    public void nellaPaginaNotificheDestinatarioSiVisualizzanoCorrettamenteIFiltriDiRicerca() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.siVisualizzanoFiltriRicerca();
    }

    @And("Nella Pagina Notifiche Destinatario si visualizza correttamente l elenco delle notifiche")
    public void nellaPaginaNotificheDestinatarioSiVisualizzaCorrettamenteLElencoDelleNotifiche() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.siVisualizzaElencoNotifiche();
    }

    @And("Si visualizza l elenco delle notifiche relative al delegante")
    public void siVisualizzaLElencoDelleNotificheRelativeAlDelegante() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.siVisualizzaElencoNotifiche();
    }

    @And("Si seleziona il nome del delegante nell elenco")
    public void siSelezionaIlNomeDelDeleganteNellElenco() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.clickNomeDelegante();
    }

    @Then("Si visualizzano tutte le notifiche del delegante selezionato")
    public void siVisualizzanoTutteLeNotificheDelDeleganteSelezionato() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.siVisualizzaElencoNotifiche();
    }

    @Then("Si visualizzano correttamente le notifiche in elenco paginato")
    public void siVisualizzanoCorrettamenteLeNotificheInElencoPaginato() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        int numeroRigheNotifiche = notificheDEPage.siVisualizzaNotifichePresenti();

        if (numeroRigheNotifiche !=0){
            logger.info("Si visualizza correttamente l'elenco notifica");
        } else {
            logger.error("NON visualizza correttamente l'elenco notifica");
            Assert.fail("NON visualizza correttamente l'elenco notifica");
        }
    }

    @And("Si visualizzano le notifiche dalla piu recente")
    public void siVisualizzanoLeNotificheDallaPiuRecente() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        List<WebElement> dateNotifiche = notificheDEPage.getDateNotifiche();

        if(dateNotifiche.size() != 0){
            boolean result = notificheDEPage.controllaNotifiche(dateNotifiche);
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
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.clickSullaFreccia();
    }

    @And("Si visualizza correttamente una pagina diversa dalla precedente")
    public void siVisualizzaCorrettamenteUnaPaginaDiversaDallaPrecedente() {
            NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
            notificheDEPage.waitLoadPaginaDifferente();
    }

    @And("Ci si posiziona su una pagina differente attraverso i numeri")
    public void ciSiPosizionaSuUnaPaginaDifferenteAttraversoINumeri() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.siSceglieUnaPaginaDiversaConNumero();
    }

    @And("Si modifica il numero di notifiche visualizzate scegliendo un valore diverso da quello di default")
    public void siModificaIlNumeroDiNotificheVisualizzateScegliendoUnValoreDiversoDaQuelloDiDefault() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.modificaNumeroNotifichePagina();
    }

    @And("Ci si posiziona su una pagina differente da quella di default e si aumenta il numero di modifiche visualizzate")
    public void ciSiPosizionaSuUnaPaginaDifferenteDaQuellaDiDefaultESiAumentaIlNumeroDiModificheVisualizzate() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.numeroDiversoPagine();
    }

    @And("Si visualizza un numero di pagine visualizzate uguale a quello selezionato")
    public void siVisualizzaUnNumeroDiPagineVisualizzateUgualeAQuelloSelezionato() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int numeroRigheNotifiche = notificheDEPage.conteggioNotifiche();

        if (numeroRigheNotifiche == 20) {
            logger.info("Si visualizzano venti notifiche in elenco");
        } else {
            logger.error("NON si visualizzano venti notifiche in elenco");
            Assert.fail("NON si visualizzano venti notifiche in elenco");
        }
    }

    @When("Il destinatario clicca sulla notifica restituita")
    public void ilDestinatarioCliccaSullaNotificaRestituita() {
        logger.info("Si clicca sulla notifica");
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.selezionaNotifica();
    }

    @And("Si visualizza correttamente la section Dettaglio Notifica destinatario")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotificaDestinatario() {
        DettaglioNotificaDESection dettaglioNotificaDESection = new DettaglioNotificaDESection(this.driver);
        dettaglioNotificaDESection.waitLoadDettaglioNotificaDESection();
    }

    @Then("Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica destinatario, e si controlla che il download sia avvenuto")
    public void siSelezionanoIFileAttestazioniOpponibiliDaScaricareAllInternoDellaNotificaDestinatarioESiControllaCheIlDownloadSiaAvvenuto() {
        DettaglioNotificaDESection dettaglioNotificaDESection = new DettaglioNotificaDESection(this.driver);
        dettaglioNotificaDESection.downloandFileAttestazioni("/src/test/resources/download/destinatario");
        dettaglioNotificaDESection.controlloDownload();
    }

    @And("Si clicca sul opzione Vedi Dettaglio")
    public void siCliccaSulOpzioneVediDettaglio() {
        DettaglioNotificaSection dettaglioNotificaSection = new DettaglioNotificaSection(driver);
        dettaglioNotificaSection.selezioneVediDettaglioButton();
    }
}



