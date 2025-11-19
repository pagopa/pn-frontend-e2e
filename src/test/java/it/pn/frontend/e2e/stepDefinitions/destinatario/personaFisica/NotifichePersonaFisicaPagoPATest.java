package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.common.DettaglioNotificaSection;
import it.pn.frontend.e2e.config.DataPopulationConfig;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.AccediAPiattaformaNotifichePage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.HeaderPFSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.LeTueDelegheSection;
import it.pn.frontend.e2e.utility.CookieConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.DownloadFile;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class NotifichePersonaFisicaPagoPATest extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(NotifichePersonaFisicaPagoPATest.class);

    private Map<String, Object> personaFisica = new HashMap<>();

    @Autowired
    private CookieConfig cookieConfig;
    @Autowired
    private WebDriverConfig webDriverConfig;

    @Autowired
    private DataPopulation dataPopulation;

    private PiattaformaNotifichePage piattaformaNotifichePage;

    private CookiesSection cookiesSection;

    private HeaderPFSection headerPFSection;

    private DownloadFile downloadFile;

    private DettaglioNotificaSection dettaglioNotificaSection;

    private NotifichePFPage notifichePFPage;

    private AccediAPiattaformaNotifichePage accediAPiattaformaNotifichePage;

    private DettaglioNotificaSection dettaglioNotifica;

    private DestinatarioPage destinatarioPage;

    private LeTueDelegheSection leTueDelegheSection;

    private WebTool webTool;

    @Autowired
    private DataPopulationConfig dataPopulationConfig;

    @PostConstruct
    public void init() {
        logger.info("INIT TEST...: ");
        webTool = new WebTool(driver);
        destinatarioPage = new DestinatarioPage(driver);
        notifichePFPage = new NotifichePFPage(driver);
        piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        cookiesSection = new CookiesSection(driver);
        headerPFSection = new HeaderPFSection(driver);
        downloadFile = new DownloadFile(driver);
        dettaglioNotificaSection = new DettaglioNotificaSection(driver);
        accediAPiattaformaNotifichePage = new AccediAPiattaformaNotifichePage(driver);
        dettaglioNotifica = new DettaglioNotificaSection(driver);

    }

    @When("Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone Notifiche")
    public void nellaPiattaformaDestinatarioCliccareSulBottoneNotifiche() {
        notifichePFPage.clickNotificheButton();
    }

    @Then("pagina Piattaforma  Notifiche persona fisica viene visualizzata correttamente")
    public void paginaPiattaformaNotificheDestinatarioVieneVisualizzataCorrettamente() {
        headerPFSection.waitLoadHeaderDESection();
        if (!cookieConfig.isCookieEnabled()) {
            if (cookiesSection.waitLoadCookiesPage()) {
                cookiesSection.selezionaAccettaTuttiButton();
            }
        }

        notifichePFPage.waitLoadNotificheDEPage();
        if (notifichePFPage.verificaPresenzaCodiceIunTextField()) {
            logger.info("text field codice iun presente");
        } else {
            logger.info("text field codice iun non presente");
            Assertions.fail("text field codice iun non presente");
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("Si visualizza correttamente la Pagina Notifiche persona fisica")
    public void siVisualizzaCorrettamenteLaPaginaNotificheDestinatario() {
        notifichePFPage.siVisualizzaPaginaNotifichePersonaFisica();
    }

    @And("Nella Pagina Notifiche persona fisica si visualizzano correttamente i filtri di ricerca")
    public void nellaPaginaNotificheDestinatarioSiVisualizzanoCorrettamenteIFiltriDiRicerca() {
        notifichePFPage.siVisualizzanoFiltriRicerca();
    }

    @And("Nella Pagina Notifiche persona fisica si visualizza correttamente l elenco delle notifiche")
    public void nellaPaginaNotificheDestinatarioSiVisualizzaCorrettamenteLElencoDelleNotifiche() {
        notifichePFPage.siVisualizzaElencoNotifiche();
    }

    @And("Si visualizza l elenco delle notifiche relative al delegante")
    public void siVisualizzaLElencoDelleNotificheRelativeAlDelegante() {
        notifichePFPage.siVisualizzaElencoNotifiche();
    }

    @And("Si visualizza l elenco delle notifiche dell impresa")
    public void siVisualizzaLElencoDelleNotificheDellImpresa() {
        notifichePFPage.siVisualizzaElencoNotifiche();
    }

    @And("Si seleziona il nome del delegante nell elenco")
    public void siSelezionaIlNomeDelDeleganteNellElenco() {
        notifichePFPage.clickNomeDelegante();
    }

    @Then("Si visualizzano correttamente le notifiche in elenco paginato")
    public void siVisualizzanoCorrettamenteLeNotificheInElencoPaginato() {
        int numeroRigheNotifiche = notifichePFPage.siVisualizzaNotifichePresenti();

        if (numeroRigheNotifiche != 0) {
            logger.info("Si visualizza correttamente l'elenco notifiche");
        } else {
            Assertions.fail("NON visualizza correttamente l'elenco notifiche");
        }
    }

    @And("Si visualizzano le notifiche dalla piu recente")
    public void siVisualizzanoLeNotificheDallaPiuRecente() {
        driver.navigate().refresh();
        List<WebElement> dateNotifiche = notifichePFPage.getDateNotifiche();
        if (!dateNotifiche.isEmpty()) {
            boolean result = notifichePFPage.controllaNotifiche(dateNotifiche);
            if (result) {
                logger.info("Le date sono ordinate correttamente");
            } else {
                Assertions.fail("Le date NON sono ordinate correttamente");
            }
        } else {
            Assertions.fail("Non sono presenti notifiche con date");
        }
    }

    @And("Si aggiorna la paginazione utilizzando le frecce")
    public void siAggiornaLaPaginazioneUtilizzandoLeFrecce() {
        notifichePFPage.clickPaginaSuccessiva();
    }

    @And("Si visualizza correttamente una pagina diversa dalla precedente")
    public void siVisualizzaCorrettamenteUnaPaginaDiversaDallaPrecedente() {
        notifichePFPage.waitLoadPaginaDifferente();
    }

    @And("Si visualizza correttamente la prossima pagina")
    public void siVisualizzaCorrettamenteLaSecondaPagina() {
        notifichePFPage.waitLoadSecondaPagina();
    }

    @And("Ci si posiziona su una pagina differente attraverso i numeri e si applica filtro")
    public void ciSiPosizionaSuUnaPaginaDifferenteAttraversoINumeriESiApplicaFiltro() {
        notifichePFPage.siSceglieUnaPaginaDiversaConNumeroESiFiltra(dataPopulationConfig.getPersonaGiuridica().getCodiceIUN());
    }

    @And("Si modifica il numero di notifiche visualizzate scegliendo un valore diverso da quello di default")
    public void siModificaIlNumeroDiNotificheVisualizzateScegliendoUnValoreDiversoDaQuelloDiDefault() {
        notifichePFPage.modificaNumeroNotifichePagina();
    }

    @And("Ci si posiziona su una pagina differente da quella di default e si aumenta il numero di modifiche visualizzate")
    public void ciSiPosizionaSuUnaPaginaDifferenteDaQuellaDiDefaultESiAumentaIlNumeroDiModificheVisualizzate() {
        notifichePFPage.numeroDiversoPagine();
    }

    @And("Si visualizza un numero di pagine visualizzate uguale a quello selezionato")
    public void siVisualizzaUnNumeroDiPagineVisualizzateUgualeAQuelloSelezionato() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int numeroRigheNotifiche = notifichePFPage.conteggioNotifiche();

        if (numeroRigheNotifiche == 20) {
            logger.info("Si visualizzano venti notifiche in elenco");
        } else {
            Assertions.fail("NON si visualizzano venti notifiche in elenco");
        }
    }

    @When("La persona fisica clicca sulla notifica restituita")
    public void ilDestinatarioCliccaSullaNotificaRestituita() {
        logger.info("Si clicca sulla notifica");
        piattaformaNotifichePage.selezionaPrimaNotifica();
    }

    @And("Si visualizza correttamente la section Dettaglio Notifica persona fisica")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotificaDestinatario() {
        dettaglioNotificaSection.waitLoadDettaglioNotificaDESection();
    }

    @And("Si controlla sezione Pagamento se notifica prevede il pagamento")
    public void siControllaSezionePagamentoSeNotificaPrevedeIlPagamento() {
        boolean sezionePagamentoIsDisplayed = accediAPiattaformaNotifichePage.sezionePagamentoDisplayed();
        if (!sezionePagamentoIsDisplayed) {
            logger.info("La notifica non prevede il pagamento ");
        } else {
            logger.info("La notifica prevede il pagamento");
            boolean radioBoxPresent = accediAPiattaformaNotifichePage.isRadioBoxPresent();
            if (radioBoxPresent) {
                accediAPiattaformaNotifichePage.clickRadioBoxButton();
            }
            boolean titoloPagamentoIsDisplayed = accediAPiattaformaNotifichePage.titoloDiPagamentoDisplayed();
            if (titoloPagamentoIsDisplayed) {
                logger.info("Sezione titolo di pagamento è visualizzato");
            } else {
                Assertions.fail("Sezione titolo di pagamento non è visualizzato");
            }
            boolean codiceAvvisoIsDisplayed = accediAPiattaformaNotifichePage.codiceAvvisoDisplayed();
            if (codiceAvvisoIsDisplayed) {
                logger.info("Sezione codice avviso è visualizzato");
            } else {
                Assertions.fail("Sezione codice avviso non è visualizzato");
            }
            boolean modelloF24IsDisplayed = accediAPiattaformaNotifichePage.modelloF24Displayed();
            if (modelloF24IsDisplayed) {
                logger.info("Sezione scarica modello F24 è visualizzato");
            } else {
                Assertions.fail("Sezione scarica modello F24 non è visualizzato");
            }
            boolean scaricaAvvisoDisplayed = accediAPiattaformaNotifichePage.scaricaAvvisoDisplayed();
            if (scaricaAvvisoDisplayed) {
                logger.info("Sezione scarica avviso è visualizzato");
            } else {
                Assertions.fail("Sezione scarica avviso non è visualizzato");
            }
            boolean pagaAvvisoDisplayed = accediAPiattaformaNotifichePage.pagaAvvisoDisplayed();
            if (pagaAvvisoDisplayed) {
                logger.info("Sezione paga avviso è visualizzato");
            } else {
                Assertions.fail("Sezione paga avviso non è visualizzato");
            }
        }
    }

    @Then("Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica persona fisica, e si controlla che il download sia avvenuto {string}")
    public void siSelezionanoIFileAttestazioniOpponibiliDaScaricareAllInternoDellaNotificaDestinatarioESiControllaCheIlDownloadSiaAvvenuto(String dpFile) {
        int numeroLinkAttestazioniOpponibile = dettaglioNotificaSection.getLinkAttestazioniOpponibili();
        String workingDirectory = System.getProperty("user.dir");
        boolean headless = webDriverConfig.getHeadless().equalsIgnoreCase("true");
        File pathCartella = new File(workingDirectory + "/src/test/resources/dataPopulation/downloads");
        if (!downloadFile.controlloEsistenzaCartella(pathCartella)) {
            pathCartella.mkdirs();
        }
        for (int i = 0; i < numeroLinkAttestazioniOpponibile; i++) {
            dettaglioNotificaSection.clickLinkAttestazioniOpponibile(i);
            webTool.waitTime(5);
            String urlFileAttestazioneOppponibile = downloadFile.getUrl("https://webapi.test.notifichedigitali.it/delivery-push/" + dataPopulationConfig.getDatiNotifica().getCodiceIUN() + "/legal-facts/");
            if (headless && urlFileAttestazioneOppponibile.isEmpty()) {
                String testoLink = dettaglioNotificaSection.getTextLinkAttestazioniOpponibili(i);
                Assertions.fail("Non è stato recuperato url per il download per il link: " + testoLink);
            }
            File file = new File(workingDirectory + "/src/test/resources/dataPopulation/downloads/notificaN" + i + ".pdf");
            downloadFile.download(urlFileAttestazioneOppponibile, file, headless);
            if (!headless) {
                dettaglioNotificaSection.goBack();
            }
        }
        downloadFile.controlloDownload(workingDirectory + "/src/test/resources/dataPopulation/downloads", numeroLinkAttestazioniOpponibile);
    }

    @And("Nella sezione Dettaglio Notifiche si clicca sulla opzione Vedi Più Dettagli")
    public void siCliccaSulOpzioneVediDettaglio() {
        dettaglioNotificaSection.selezioneVediDettaglioButton();
    }

    @And("Si visualizza correttamente la Pagina Notifiche persona fisica delegante")
    public void siVisualizzaCorrettamenteLaPaginaNotifichePersonaFisicaDelegante() {
        notifichePFPage.waitLoadNotificheDEPageDelegante(dataPopulationConfig.getPersonaFisica().getName(), dataPopulationConfig.getPersonaFisica().getFamilyName());
    }

    @And("Nella pagina Piattaforma Notifiche PF si recupera un codice IUN valido")
    public void nellaPaginaPiattaformaNotificheSiRecuperaUnCodiceIUNValido() {
        logger.info("Si recupera un codice IUN valido");
        List<String> codiciIun = piattaformaNotifichePage.getCodiceIunPresentiPF();
        String codiceIun = dataPopulationConfig.getDatiNotifica().getCodiceIUN();
        if (codiciIun.contains(codiceIun)) {
            piattaformaNotifichePage.inserimentoCodiceIUN(codiceIun);
        } else {
            piattaformaNotifichePage.inserimentoCodiceIUN(codiciIun.get(0));
            dataPopulationConfig.getDatiNotifica().setCodiceIUN(codiciIun.get(0));
        }
    }

    @And("Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN {string}")
    public void nellaPaginaPiattaformaNotificheSiRecuperaUnCodiceIUNValido(String codiceIun) {
        logger.info("Si recupera un codice IUN valido");
        piattaformaNotifichePage.inserimentoCodiceIUN(codiceIun);
    }

    @And("Si Controlla la paginazione di default")
    public void siControllaLaPaginazioneDiDefault() {
        logger.info("controllo paginazione di default in pagina notifiche");
        piattaformaNotifichePage.checkDefaultPagination();
    }

    @And("Si controlla la pagina delle notifiche delegati di {string}")
    public void siControllaLaPaginaDelleNotificheDelegatiDi(String nomeDelegante) {
        piattaformaNotifichePage.checkPaginaNotificheDelegante(nomeDelegante);
    }

    @And("Si clicca sulla notifica del delegante")
    public void siCliccaSullaNotificaDelDelegante() {
        piattaformaNotifichePage.selezionaPrimaNotifica();
    }

    @And("Si controlla il dettaglio della notifica")
    public void siControllaIlDettaglioDellaNotifica() {
        dettaglioNotifica.waitLoadDettaglioNotificaDESection();
    }

    @And("Si controlla che nel portale del destinatario la notifica sia {string} e si chiude la scheda")
    public void siControllaCheNelPortaleDelDestinatarioLaNotificaSiaESiChiudeLaScheda(String statoNotifica) {
        logger.info("Si controlla che anche nel portale del destinatario la notifica sia in stato " + statoNotifica + " e si chiude la scheda");
        piattaformaNotifichePage.selezionaPrimaNotifica();
        piattaformaNotifichePage.verificaPresenzaStato(statoNotifica);
        webTool.closeTab();
    }

    @And("Si verifica che gli allegati denominati {string} non sono scaricabili")
    public void siVerificaCheGliAllegatiDenominatiNonSonoScaricabili(String descrizioneAllegato) {
        logger.info("Si controlla che non sia possibile scaricare gli allegati");
        piattaformaNotifichePage.checkAllegatoScaricabile(descrizioneAllegato);
    }

    @And("Si verifica che gli AAR non sono scaricabili")
    public void siVerificaCheGliAARNonSonoScaricabili() {
        logger.info("Si controlla che non sia possibile scaricare gli AAR");
        piattaformaNotifichePage.checkAARScaricabili();
    }

    @And("Si verifica che le attestazioni opponibili a terzi non siano scaricabili")
    public void siVerificaCheLeAttestazioniOpponibiliATerziNonSianoScaricabili() {
        logger.info("Si controlla che non sia possibile scaricare le attestazioni opponibili a terzi");
        piattaformaNotifichePage.checkAttestazioniOpponibiliATerziScaricabili();
    }

    @And("Si verifica che non sia possibile scaricare le ricevute PEC")
    public void siVerificaCheNonSiaPossibileScaricareLeRicevutePEC() {
        logger.info("Si controlla che non sia possibile scaricare le ricevute PEC");
        piattaformaNotifichePage.checkRicevutePECScaricabili();
    }

    @And("Si controlla che il testo sia nel box pagamento {string}")
    public void siControllaTestoSiaNelBoxPagamento(String xpath) {
        boolean isPresent = dettaglioNotifica.isFieldDisplayed(By.xpath(xpath));
        if (!isPresent) {
            Assertions.fail("L'elemento Paga non esiste");
        }
    }

    @And("Si controlla che il testo non sia nel box pagamento {string}")
    public void siControllaTestoNonSiaNelBoxPagamento(String xpathString) {
        By xpath = By.xpath(xpathString);
        boolean isNotPresent = dettaglioNotifica.isFieldNotDisplayed(xpath);
        if (!isNotPresent) {
            Assertions.fail("L'elemento esiste");
        }
    }

    @And("Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca")
    public void nellaPaginaPiattaformaNotificheVisualizzanoCorrettamenteIFiltriDiRicerca() {
        piattaformaNotifichePage.siVisualizzaCorrettamenteIlCodiceIUNField();
        piattaformaNotifichePage.siVisualizzaCorrettamenteLaDataInzioField();
        piattaformaNotifichePage.siVisualizzaCorrettamenteLaDataFineField();
    }

    @Then("Si visualizza correttamente la section Dettaglio Notifica annullata")
    public void siVisualizzaCorrettamenteLaSectionDettaglioNotificaAnnullata() {
        dettaglioNotificaSection.waitLoadDettaglioNotificaAnnullataDESection();
    }

    @And("Si seleziona un avviso pagopa")
    public void siSelezionaUnAvvisoPagopa() {
        dettaglioNotifica.selezioneAvvisoPagoPa();
    }

    @Then("Si clicca sul bottone scarica F24")
    public void siCliccaSulBottoneScaricaF24() {
        logger.info("Si clicca sul bottone per scaricare il modello F24, viene aperto il file");
        notifichePFPage.clickScaricaF24Button();
    }

    @Then("Si clicca sul bottone scarica avviso PagoPA")
    public void siCliccaSulBottoneScaricaAvvisoPagoPA() {
        logger.info("Si clicca sul bottone per scaricare l'avviso PagoPA, viene aperto il file");
        notifichePFPage.clickScaricaAvvisoPagoPAButton();
    }

    @And("Si controlla di aver aperto il file F24")
    public void siControllaDiAverApertoIlFileF24() {
        logger.info("Si controlla di aver aperto correttamente il file F24");
        notifichePFPage.checkFileF24IsOpen();
    }

    @And("Si controlla di aver aperto l'avviso PagoPa")
    public void siControllaDiAverApertoLAvvisoPagoPa() {
        logger.info("Si controlla di aver aperto correttamente l'avviso PagoPa");
        notifichePFPage.checkAvvisoPagoPaIsOpen();
    }

    @And("Si torna alla pagina precedente")
    public void siTornaAllaPaginaPrecedente() {
        logger.info("Si torna alla pagina precedente");
        driver.navigate().back();
    }

    @And("Si controlla non sia presente il bottone paga")
    public void siControllaNonSiaPresenteIlBottonePaga() {
        logger.info("Si controlla che il bottone per il pagamento non sia visibile all'interno del dettaglio della notifica");
        if (accediAPiattaformaNotifichePage.checkButtonPagaIsDisplayed()) {
            Assertions.fail("Il bottone per il pagamento è visibile all'interno del dettaglio della notifica");
        } else {
            logger.error("Il bottone per il pagamento non è visibile all'interno del dettaglio della notifica");
        }
    }

    @And("Si controlla se la sezione pagamento visualizzata correttamente")
    public void siControllaSeLaSezionePagamentoVisualizzataCorrettamente() {
        accediAPiattaformaNotifichePage.siVisualizzaSezionePagamento();
    }

    @And("Si controlla che costi di notifica inclusi non presente")
    public void siControllaCostiDiNotifica() {
        if (!accediAPiattaformaNotifichePage.siControllaCostiDiNotifica()) {
            logger.info("Costi di notifica non inclusi");
        } else {
            Assertions.fail("Costi di notifica inclusi");
        }
    }

    @And("Cliccare sul bottone Paga")
    public void cliccaBottonePaga() {
        webTool.waitTime(5);
        accediAPiattaformaNotifichePage.cliccaPaga();
    }

    @Then("Si inserisce i dati di pagamento e procede con il pagamento {string}")
    public void siInserisceIDatiDiPagamento(String email) throws InterruptedException {
        logger.info("Si inserisce i dati di pagamento");
        accediAPiattaformaNotifichePage.inserireDatiPagamento(email);
        accediAPiattaformaNotifichePage.checkoutPagamento();
    }

    @And("Si verifica che visualizzato lo stato Pagato")
    public void siVisualizzaStatoPagato() {
        accediAPiattaformaNotifichePage.siVisualizzaStatoPagato();
    }

    @And("Verifica nome ente mittente {string}")
    public void verificaNomeEnteMittente(String nomeEnte) {
        Map<String, String> infoNotifiche = dettaglioNotificaSection.recuperoInfoNotificheDestinatario();
        Assertions.assertTrue(infoNotifiche.get("mittente").equalsIgnoreCase(nomeEnte));
    }

    @And("Verifica Presenza Codici Avviso PagoPa {int} e ModelloF24 {int}")
    public void verificaPresenzaCodiciAvvisoEF24(int numeroAttesoCodiciAvviso, int numeroAttesoF24) {
        notifichePFPage.verificaPresenzaCodiciAvvisoEF24(numeroAttesoCodiciAvviso, numeroAttesoF24);
    }

    @And("Verifica Sezione Pagamenti")
    public void verificaSezionePagamenti() {
        notifichePFPage.verificaSezionePagamenti();
    }


    @And("Verifica testo {string}")
    public void verificaTesto(String test0) {
        notifichePFPage.verificaTesto(test0);
    }

    @When("Nella pagina Piattaforma Notifiche persona fisica si accede alla notifica con codice IUN {string}")
    public void portalePFVaiANotifica(String codiceIUN) {
        String env = webDriverConfig.getEnvironment();
        this.driver.get("https://cittadini." + env + ".notifichedigitali.it/notifiche/" + codiceIUN + "/dettaglio");
    }

    @When("Nella pagina Piattaforma Notifiche persona fisica si accede alla notifica con codice IUN specifico")
    public void portalePFVaiANotifica(Map<String, String> codiciIUN) {
        String env = webDriverConfig.getEnvironment();
        if (codiciIUN.containsKey("dev") || codiciIUN.containsKey("test") || codiciIUN.containsKey("uat")) {
            this.driver.get("https://cittadini." + env + ".notifichedigitali.it/notifiche/" + codiciIUN.get(env) + "/dettaglio");
            webTool.waitTime(5);
        }
        else
            Assertions.fail("Nessuna chiave valida per gli ambienti di esecuzione!");
    }

    @And("Nella pagina Piattaforma Notifiche mittente si accede alla notifica con codice IUN specifico")
    public void nellaPaginaPiattaformaNotificheMittenteSiAccedeAllaNotificaConCodiceIUNSpecifico(Map<String, String> codiciIUN) {
        String env = webDriverConfig.getEnvironment();
        if (codiciIUN.containsKey("dev") || codiciIUN.containsKey("test") || codiciIUN.containsKey("uat")) {
            this.driver.get("https://selfcare." + env + ".notifichedigitali.it/dashboard/" + codiciIUN.get(env) + "/dettaglio");
            webTool.waitTime(5);
        }
        else
            Assertions.fail("Nessuna chiave valida per gli ambienti di esecuzione!");
    }
}



