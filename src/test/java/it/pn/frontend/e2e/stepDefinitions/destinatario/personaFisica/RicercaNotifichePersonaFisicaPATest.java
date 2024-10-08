package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.NotificheDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.destinatario.personaFisica.HeaderPFSection;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.DataPopulation;
import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RicercaNotifichePersonaFisicaPATest {
    private static final Logger logger = LoggerFactory.getLogger("RicercaNotifichePersonaFisicaTest");
    private final WebDriver driver = Hooks.driver;
    private final NotificationSingleton notificationSingleton = NotificationSingleton.getInstance();
    private final PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
    private final DestinatarioPage destinatarioPage = new DestinatarioPage(this.driver);
    private Map<String, Object> datiNotifica = new HashMap<>();
    private Map<String, Object> datiNotificaNonValidoPF;

    @When("Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheDestinatario() {
        logger.info("Verifica visualizzazione piattaforma notifiche persona fisica");
        HeaderPFSection headerPFSection = new HeaderPFSection(this.driver);
        headerPFSection.waitLoadHeaderDESection();

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPage();
    }

    @And("Collegarsi a link con codice {int}")
    public void collegarsiLink(int code) {
        switch (code) {
            case 19 -> {
                this.driver.get("https://cittadini.test.notifichedigitali.it/auth/login/error?errorCode=19");
            }
            case 20 -> {
                this.driver.get("https://cittadini.test.notifichedigitali.it/auth/login/error?errorCode=20");
            }
            case 21 -> {
                this.driver.get("https://cittadini.test.notifichedigitali.it/auth/login/error?errorCode=21");
            }
            case 22 -> {
                this.driver.get("https://cittadini.test.notifichedigitali.it/auth/login/error?errorCode=22");
            }
            case 23 -> {
                this.driver.get("https://cittadini.test.notifichedigitali.it/auth/login/error?errorCode=23");
            }
            case 25 -> {
                this.driver.get("https://cittadini.test.notifichedigitali.it/auth/login/error?errorCode=25");
            }
            case 30 -> {
                this.driver.get("https://cittadini.test.notifichedigitali.it/auth/login/error?errorCode=30");
            }
            case 1001 -> {
                this.driver.get("https://cittadini.test.notifichedigitali.it/auth/login/error?errorCode=1001");
            }
        }
    }

    @And("Si visualizza correttamente il messaggio di errore {int}")
    public void siVisualizzaMessaggioErroreConCodice(int code) {
        piattaformaNotifichePage.checkMessaggioErroreConCodice(code);
    }

    @And("Nella pagina Piattaforma Notifiche persona fisica inserire il codice IUN {string}")
    public void nellaPaginaPiattaformaNotificheDestinatarioInserireIlCodiceIUN(String IUN) throws InterruptedException {
        logger.info("Si inserisce il codice IUN");
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadPage();

        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        notificheDestinatarioPage.inserisciCodiceIUN(IUN);
    }

    @And("Cliccare sul bottone Filtra persona fisica")
    public void cliccareSulBottoneFiltra() {
        logger.info("Si clicca sul tasto filtra");
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.selectFiltraButton();
    }

    @Then("Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN della notifica {string}")
    public void nellaPaginaPiattaformaNotificheDestinatarioVengoRestituiteTutteLeNotificheConIlCodiceIUNDellaNotifica(String dpDatiNotifica) {
        logger.info("Si verificano i risultati restituiti");
        HeaderPFSection headerPFSection = new HeaderPFSection(this.driver);
        headerPFSection.waitLoadHeaderDESection();

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPage();

        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotifica = dataPopulation.readDataPopulation(dpDatiNotifica + ".yaml");
        String codiceIUNInserito = datiNotifica.get("codiceIUN").toString();

        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        boolean result = notificheDestinatarioPage.verificaCodiceIUN(codiceIUNInserito);
        if (result) {
            logger.info("Il risultato é coerente con il codice IUN inserito");
        } else {
            logger.error("Il risultato NON é coerente con il codice IUN inserito");
            Assert.fail("Il risultato NON é coerente con il coodice IUN inserito");
        }
    }

    @Then("Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN {string}")
    public void nellaPaginaPiattaformaNotificheDestinatarioVengoRestituiteTutteLeNotificheConIlCodiceIUN(String IUN) {
        logger.info("Si verificano i risultati restituiti");
        HeaderPFSection headerPFSection = new HeaderPFSection(this.driver);
        headerPFSection.waitLoadHeaderDESection();

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPage();

        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        boolean result = notificheDestinatarioPage.verificaCodiceIUN(IUN);
        if (result) {
            logger.info("Il risultato é coerente con il codice IUN inserito");
        } else {
            logger.error("Il risultato NON é coerente con il codice IUN inserito");
            Assert.fail("Il risultato NON é coerente con il coodice IUN inserito");
        }
    }

    @And("Nella pagina Piattaforma Notifiche mittente inserire un arco temporale")
    public void nellaPaginaPiattaformaNotificheMittenteInserireUnaDataDaDAAA(Map<String, Integer> date) {
        logger.info("Si inserisce l'arco temporale su cui effettuare la ricerca ");
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        LocalDate dataFine = LocalDate.of(date.get("annoA"), date.get("meseA"), date.get("giornoA"));
        LocalDate dataInizio = LocalDate.of(date.get("annoDa"), date.get("meseDa"), date.get("giornoDa"));
        String dataDA = notifichePFPage.controlloDateInserite(dataInizio.toString());
        String dataA = notifichePFPage.controlloDateInserite(dataFine.toString());
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        piattaformaNotifichePage.inserimentoArcoTemporale(dataDA, dataA);
    }

    @And("Nella pagina Piattaforma Notifiche mittente inserire un arco temporale di maggiore di 120 giorni")
    public void nellaPaginaPiattaformaNotificheMittenteInserireUnaDataDaDAAA120Giorni() {
        logger.info("Si inserisce l'arco temporale su cui effettuare la ricerca 120 giorni ");

        logger.info("Scenario " +Hooks.getScenario());

        LocalDate dateNow = LocalDate.now();
        LocalDate dateA = dateNow.minusDays(150);

        LocalDate dateDa = dateA.minusDays(30);

        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);
        String dataa = piattaformaNotifichePage.conversioneFormatoDate(dateA.toString());
        String datada = piattaformaNotifichePage.conversioneFormatoDate(dateDa.toString());
        logger.info("ARCO TEMPORRALE SETTATO: "+dataa +" - "+datada);
        piattaformaNotifichePage.inserimentoArcoTemporale(dataa, datada);
    }

    @And("Se i risultati sono contenuti in più pagine persona fisica è possibile effettuare il cambio pagina")
    public void seIRisultatiSonoContenutiInPiuPagineDestinatarioEPossibileEffettuareIlCambioPagina() {
        logger.info("Se i risultati sono contenuti in più pagine è possibile effettuare il cambio pagina");
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        if (piattaformaNotifichePage.verificaEsistenzaEPassaggioPagina()) {
            logger.info("Bottone pagina 2 trovato e cliccato");
            HeaderPFSection headerPFSection = new HeaderPFSection(this.driver);
            headerPFSection.waitLoadHeaderDESection();
            notifichePFPage.waitLoadNotificheDEPage();
        } else {
            logger.info("Bottone pagina 2 non trovato non effettuato il passaggio di pagina");
        }
    }

    @And("Cliccare sul bottone Rimuovi filtri persona fisica")
    public void cliccareSulBottoneRimuoviFiltriPersonaFisica() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.clickRimuoviFiltriButton();
    }

    @And("Nella pagina Piattaforma Notifiche persona fisica inserire il codice IUN non valido da dati notifica {string}")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaInserireIlCodiceIunNonValidoDaDatiNotifica(String datiNotificaNonValidoPF) throws InterruptedException {
        logger.info("Si inserisce il codice IUN non valido");
        DataPopulation dataPopulation = new DataPopulation();
        this.datiNotificaNonValidoPF = dataPopulation.readDataPopulation(datiNotificaNonValidoPF + ".yaml");
        NotificheDestinatarioPage notificheDestinatarioPage = new NotificheDestinatarioPage(this.driver);
        notificheDestinatarioPage.inserisciCodiceIUN(this.datiNotificaNonValidoPF.get("codiceIUN").toString());
    }

    @Then("Nella pagina Piattaforma Notifiche persona fisica viene visualizzato un messaggio in rosso di errore sotto il campo errato e il rettangolo diventa rosso e il tasto Filtra è disattivo")
    public void NellaPaginaPiattaformaNotifichePersonaFisicaVieneVisualizzatoUnMessaggioInRossoDiErroreSottoIlCampoErratoEIlRettangoloDiventaRossoEIlTastoFiltraEDisattivo() {

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);

        boolean isErrorMessageDisplayed = notifichePFPage.isErrorMessageDisplayed();

        if (isErrorMessageDisplayed) {
            logger.info("il messaggio di errore é visualizzato");
        } else {
            logger.error("il messaggio di errore non é visualizzato");
            Assert.fail("il messaggio di errore non é visualizzato");
        }

        boolean isTextBoxInValid = notifichePFPage.isTextBoxInvalid();

        if (isTextBoxInValid) {
            logger.info("IUN text box non é valido");
        } else {
            logger.error("IUN text box non é passato allo stato non valido");
            Assert.fail("IUN text box non é passato allo stato non valido");

        }

        notifichePFPage.clickFiltraButton();
        boolean isErrorMessageStillDisplayed = notifichePFPage.isErrorMessageDisplayed();
        if (isErrorMessageStillDisplayed) {
            logger.info("Il bottone Filtra é dissativato");
        } else {
            logger.error("Il bottone Filtra é attivo");
            Assert.fail("Il bottone Filtra é attivo");
        }
    }

    @And("Si clicca su pagina diversa dalla prima")
    public void siCliccaSupaginaDiversaDallaPrima() {
        logger.info("si clicca su una pagina diversa dalla prima");
        piattaformaNotifichePage.clickPagina(3);
    }

    @And("Si verifica che visualizza la prima pagina")
    public void siVerificaCheVisualizzaLaPrimaPagina() {
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.firstPageDisplayed();
    }

    @Then("Vengono visualizzate correttamente le notifiche comprese nell'arco temporale inserito")
    public void vengonoVisualizzateCorrettamenteLeNotificheCompreseNellArcoTemporaleInserito() {
        HeaderPFSection headerPFSection = new HeaderPFSection(this.driver);
        headerPFSection.waitLoadHeaderDESection();

        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitLoadNotificheDEPage();
        boolean result = notifichePFPage.getListData();
        if (result) {
            logger.info("Il risultato é coerente con le date inserite");
        } else {
            logger.error("Il risultato NON é coerente con le date inserite");
            Assert.fail("Il risultato NON é coerente con le date inserite");
        }
    }

    @Then("Il rettangolo del campo errato diventa rosso")
    public void ilRettangoloDelCampoErratoDiventaRosso() {
        logger.info("Si controlla che almeno un campo data sia in stato invalido");
        destinatarioPage.isDateBoxInvalid();
    }

    @And("Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN {string}")
    public void nellaPaginaPiattaformaNotificheDestinatarioSiEffettuaLaRicercaPerCodiceIUN(String codiceIUN) {
        logger.info("Si cerca una notifica tramite IUN: " + codiceIUN);
        PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        piattaformaNotifichePage.inserimentoCodiceIUN(codiceIUN);
        piattaformaNotifichePage.selectFiltraNotificaButtonDestinatario();
    }

    @And("Si seleziona la notifica destinatario")
    public void siSelezionaLaNotificaDestinatario() {
        BackgroundTest backgroundTest = new BackgroundTest();
        String iun = notificationSingleton.getIun(Hooks.scenario);
        backgroundTest.siFiltraLaTabellaDelleNotificheDelDestinatarioPerIUN(iun);
    }
}

