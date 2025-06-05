package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.DataPopulationConfig;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.model.delegate.DelegatePF;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPF;
import it.pn.frontend.e2e.model.delegate.DelegateResponsePF;
import it.pn.frontend.e2e.model.singleton.MandateSingleton;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.DeleghePage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.rest.RestDelegation;
import it.pn.frontend.e2e.section.destinatario.personaFisica.LeTueDelegheSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.PopUpRevocaDelegaSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.DelegatiImpresaSection;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DeleghePagoPATest extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("DeleghePagoPATest");


    @Autowired
    private DataPopulation dataPopulation;

    private LeTueDelegheSection leTueDelegheSection;

    private PopUpRevocaDelegaSection popUpRevocaDelegaSection;

    private DeleghePage deleghePage;

    @Autowired
    private LoginPersonaFisicaPagoPA loginPersonaFisicaPagoPA;

    private DestinatarioPage destinatarioPage;

    private NotifichePFPage notifichePFPage;

    private DelegatiImpresaSection delegatiImpresaSection;

    @Autowired
    @Lazy
    private BackgroundTest backgroundTest;

    @Autowired
    private MandateSingleton mandateSingleton;

    @Autowired
    private RestDelegation restDelegation;

    private WebTool webTool;

    private Map<String, Object> deleghe = new HashMap<>();
    @Autowired
    private DataPopulationConfig dataPopulationConfig;

    @Autowired
    private HooksNew hooksNew;


    @Setter
    private String codiceVerifica;

    @PostConstruct
    public void init() {
        logger.info("INIT TEST...: ");
        webTool = new WebTool(driver);
        leTueDelegheSection = new LeTueDelegheSection(driver);
        popUpRevocaDelegaSection = new PopUpRevocaDelegaSection(driver);
        deleghePage = new DeleghePage(driver);
        deleghePage = new DeleghePage(driver);
        destinatarioPage = new DestinatarioPage(driver);
        notifichePFPage = new NotifichePFPage(driver);
        delegatiImpresaSection = new DelegatiImpresaSection(driver);
    }


    @When("Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe")
    public void waitDelegheButton() {
        logger.info("Si clicca sul bottone deleghe");
        notifichePFPage.waitESelectDelegheButton();
    }

    @And("Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe")
    public void visualizzaDelegheSection() {
        logger.info("Si visualizza la sezione deleghe");

        deleghePage.waitDeleghePage();
    }

    @And("Nella sezione Deleghe click sul bottone aggiungi nuova delega")
    public void nellaSezioneDelegheClickSulBottoneAggiungiNuovaDelega() {
        logger.info("Click sul bottone aggiungi nuova delega");
        delegatiImpresaSection.verificaRemoveMenuDelega();
        deleghePage.clickAggiungiDelegaButton();
    }

    @And("Nella sezione Deleghe click sul bottone aggiungi nuova delega PF")
    public void nellaSezioneDelegheClickSulBottoneAggiungiNuovaDelegaPF() {
        logger.info("Click sul bottone aggiungi nuova delega");
        deleghePage.clickAggiungiDelegaButton();
    }


    @And("Nella sezione Le Tue Deleghe inserire i dati")
    public void nellaSezioneLeTueDelegheInserireIDati(Map<String, String> personaFisica) {
        logger.info("Nella sezione Le Tue Deleghe inserire i dati");

        String nome = personaFisica.get("nome");
        String cognome = personaFisica.get("cognome");
        String codiceFiscale = personaFisica.get("codiceFiscale");
        String ente = personaFisica.get("ente");
        logger.info("*-*-*-*-*-* ente: " + ente);

        leTueDelegheSection.selectPersonaFisicaRadioButton();

        leTueDelegheSection.insertNomeCognome(nome, cognome);
        leTueDelegheSection.inserireCF(codiceFiscale);
        leTueDelegheSection.selectSoloEntiSelezionati();
        leTueDelegheSection.waitLoadPage();
        leTueDelegheSection.selezionaUnEnte(ente);

    }

    @And("Nella sezione Le Tue Deleghe verificare che la data sia corretta")
    public void nellaSezioneLeTueDelegheVerificareCheLaDataSiaCorretta() {
        logger.info("Si controlla che la data visualizzata sia corretta");

        if (leTueDelegheSection.verificareCheLaDataSiaCorretta()) {
            logger.info("La data inserita è corretta");
        } else {
            logger.error("La data inserita non è corretta");
            Assertions.fail("La data inserita non è corretta");
        }
    }

    @And("Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file")
    public void nellaSezioneLeTueDelegheSalvareIlCodiceVerificaAllInternoDelFile() {
        logger.info("Si salva il codice deleghe nel file SharedSteps -> NuovaDelega");
        //NUOVA DELEGA
        String codiceVerifica = leTueDelegheSection.salvataggioCodiceVerifica();

        logger.info("CodiceVerifica Creazione delega: " + codiceVerifica);
        dataPopulationConfig.getNuovaDelega().setCodiceDelega(codiceVerifica);

    }

    @And("Nella sezione Le Tue Deleghe salvare il codice verifica")
    public void nellaSezioneLeTueDelegheSalvareIlCodiceVerifica() {
        this.codiceVerifica = leTueDelegheSection.salvataggioCodiceVerifica();
    }

    @And("Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe")
    public void nellaSezioneLeTueDelegheClickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe() {

        leTueDelegheSection.clickSulBottoneInviaRichiesta();
    }

    @And("Nella sezione Deleghe si visualizza la delega in stato di attesa di conferma")
    public void nellaSezioneDelegheSiVisualizzaLaDelegaInStatoDiAttesaDiConferma() {

        deleghePage.waitDeleghePage();
        deleghePage.controlloCreazioneDelega();
    }

    @And("Nella sezione Deleghe si clicca sul menu della delega")
    public void nellaSezioneDelegheSiCliccaSulMenuDellaDelega(Map<String, String> personaFisica) {
        logger.info("Nella sezione Deleghe si clicca sul menu della delega");
        String fullName = personaFisica.get("nome") + " " + personaFisica.get("cognome");
        deleghePage.clickMenuDelegante(fullName);
    }

    @And("Nella sezione Deleghe si clicca sul menu dei delegati")
    public void nellaSezioneDelegheSiCliccaSulMenuDeliDelegati(Map<String, String> personaFisica) {
        logger.info("Nella sezione Deleghe si clicca sul menu dei delegati");
        String fullName = personaFisica.get("nome") + " " + personaFisica.get("cognome");
        deleghePage.clickMenuDelegato(fullName);
    }

    @And("Nella sezione Deleghe si sceglie l'opzione mostra codice")
    public void nellaSezioneDelegheSiSceglieLOpzioneMostraCodice() {
        logger.info("Si clicca su mostra codice");
        this.deleghePage.siSceglieOpzioneMostraCodice();
    }

    @Then("Si clicca sul bottone chiudi")
    public void siCliccaSulBottoneChiudi() {
        logger.info("Si clicca sul bottone chiudi");
        deleghePage.siCliccaSulBottoneChiudi();
    }

    @And("Nella sezione Deleghe si sceglie l'opzione revoca")
    public void nellaSezioneDelegheSiSceglieLOpzioneRevoca() {
        logger.info("Si clicca sulla opzione revoca delega");
        deleghePage.clickOpzioneRevoca();
    }

    @Then("Si conferma l'azione scegliendo revoca la delega")
    public void siConfermaLAzioneScegliendoRevocaLaDelega() {
        popUpRevocaDelegaSection.clickRevocaLaDelega();
    }

    @And("Nella sezione Le Tue Deleghe click sul bottone Invia richiesta")
    public void nellaSezioneLeTueDelegheClickSulBottoneInviaRichiesta() {
        logger.info("Si clicca sul bottone  invia richiesta");
        leTueDelegheSection.clickInviaRichiesta();
    }

    @And("Nella sezione Le Tue Deleghe si visualizza il messaggio di errore")
    public void nellaSezioneLeTueDelegheSiVisualizzaIlMessaggioDiErrore() {
        logger.info("Si visualizza il messaggio di errore");
        String actualErrorMessage = leTueDelegheSection.messaggioDiErrore().toLowerCase();
        String expectedErrorMessage = "errore inserimento delega";
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Messeggio di Errore non è uguale a Codice fiscale non valido");
    }

    @And("Nella sezione Le Tue Deleghe si visualizza il messaggio di errore delega gia aggiunta")
    public void nellaSezioneLeTueDelegheSiVisualizzaIlMessaggioDiErroreDelegaGiaAggiunta() {
        logger.info("Si visualizza il messaggio di errore delega gia aggiunta");
        leTueDelegheSection.messaggioDiErroreDelegaPresente();
    }

    @And("Si verifica sia presente una delega nella sezione Deleghe a Tuo Carico")
    public void siVerificaSiaPresenteUnaDelegaNellaSezioneDelegheATuoCarico(Map<String, String> datiPersonaFisica) {
        logger.info("Si verifica sia presente una delega nella sezione Deleghe a Tuo Carico");

        String nome = datiPersonaFisica.get("nome");
        String cognome = datiPersonaFisica.get("cognome");

        deleghePage.vaiInFondoAllaPagina();
        boolean esistenzaDelega = deleghePage.siVisualizzaUnaDelegaConNomeDelegato(nome, cognome);
        String stato = "";
        if (esistenzaDelega && !leTueDelegheSection.controlloPresenzaBottoneAccetta()) {
            stato = deleghePage.vericaStatoDelega();
        }
        String PF = "personaFisica";
        if (!esistenzaDelega) {
            // backgroundTest = new BackgroundTest();
            backgroundTest.loginPF(PF);
            backgroundTest.aggiuntaNuovaDelegaPF();
            backgroundTest.logoutPF();
            backgroundTest.loginPF("delegatoPF");
        }

        if (esistenzaDelega && stato.equalsIgnoreCase("Attiva")) {
            nellaPaginaDelegheSiCliccaSulMenuDellaDelega(datiPersonaFisica);
            nellaPaginaDelegheSiSceglieOpzioneRifiuta();
            siCliccaSulBottoneRifiutaAllInternoDelPopUp();
            siControllaCheLaDelegaNonSiaPiuPresenteNellaLista(PF);

            // backgroundTest = new BackgroundTest();
            backgroundTest.loginPF(PF);
            backgroundTest.aggiuntaNuovaDelegaPF();
            backgroundTest.logoutPF();
            backgroundTest.loginPF("delegatoPF");
        }
    }

    @And("Si sceglie opzione accetta")
    public void siSceglieOpzioneAccetta() {
        logger.info("Si sceglie l'opzione accetta");
        leTueDelegheSection.clickOpzioneAccetta();
    }

    @And("Si inserisce il codice delega nel pop-up OTP")
    public void inserisceCodiceOTPDelega() {
        leTueDelegheSection.waitPopUpLoad();
        leTueDelegheSection.inserireCodiceDelega(codiceVerifica);
    }

    @And("Si inserisce il codice delega nel pop-up {string}")
    public void siInserisceIlCodiceDelegaNelPopUp(String nomeConfig) {
        //TODO è richiamtao in divese parti del codice il nomeFile passatogli sono:
        // nomeFileNuovaDelegaPG -> nuovaDelegaPG, nomeFileNuovaDelega -> nuova_delega, personaFisica, nuova_delega, nuova_delega

        logger.info("Si inserisce il codice per accettare la delega");
        leTueDelegheSection.waitPopUpLoad();
        leTueDelegheSection.inserireCodiceDelega(getCodiceDelega(nomeConfig));
    }

    @And("Si inserisce il codice errato delega nel pop-up {string}")
    public void siInserisceIlCodiceErratoDelegaNelPopUp(String codice) {
        logger.info("Si inserisce il codice per accettare la delega");
        leTueDelegheSection.waitPopUpLoad();
        leTueDelegheSection.inserireCodiceDelega(codice);
    }

    @And("Si inserisce il codice delega nel pop-up OTP {string}")
    public void siInserisceIlCodiceDelegaNelPopUpOTP(String data) {
        logger.info("Si inserisce il codice delega nel pop-up OTP");

        String verificationCode = "";
        leTueDelegheSection.waitPopUpLoad();
        if (data.equalsIgnoreCase("errato")) {
            verificationCode = "54321";
        } else {
            verificationCode = mandateSingleton.getVerificationCode(mandateSingleton.getMandateId(hooksNew.getScenario()));
        }
        leTueDelegheSection.inserireCodiceDelega(verificationCode);
    }

    /**
     * Crea in background una delega per persona fisica
     *
     * @param personaFisica Example:
     *                      | accessoCome | tipoDiAccesso (personaFisica, delegatoPF)|
     *                      | displayName | Nome delegato |
     *                      | firstName | Nome |
     *                      | lastName | Cognome |
     *                      | fiscalCode | Codice fiscale |
     *                      | person | true (se persona fisica o impresa) |
     */

    @When("Creo in background una delega per persona fisica")
    public void creaInBackgroundUnaDelegaPerPersonaFisica(Map<String, String> personaFisica){

        //logica elimina delega
        logger.info("Verifico se esiste una delega");
        delegatiImpresaSection.verificaRemoveMenuDelega(personaFisica.get("displayName"), StringUtils.isEmpty(personaFisica.get("DelegheCarico")) ? null : personaFisica.get("DelegheCarico"));

        logger.info("Si controlla che ci sia una delega");


        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DelegatePF delegatePF = new DelegatePF();
        delegatePF.setFiscalCode(personaFisica.get("fiscalCode"));
        delegatePF.setDisplayName(personaFisica.get("displayName"));
        delegatePF.setFirstName(personaFisica.get("firstName"));
        delegatePF.setLastName(personaFisica.get("lastName"));
        delegatePF.setPerson(Boolean.parseBoolean(personaFisica.get("person")));
        /**
         DelegatePF delegatePF = DelegatePF.builder()
         .displayName(personaFisica.get("displayName"))
         .firstName(personaFisica.get("firstName"))
         .lastName(personaFisica.get("lastName"))
         .fiscalCode(personaFisica.get("fiscalCode"))
         .person(Boolean.parseBoolean(personaFisica.get("person"))).build();
         **/

        DelegateRequestPF delegateRequestPF = dataPopulationConfig.getDelegateRequestPF();
        delegateRequestPF.setDelegate(delegatePF);
/**
 DelegateRequestPF delegateRequestPF = DelegateRequestPF.builder()
 .dateto(date)
 .delegate(delegatePF)
 .visibilityIds(new ArrayList<String>())
 .verificationCode("12345")
 .build();
 **/


        String tokenExchange = loginPersonaFisicaPagoPA.getTokenExchangePFFromFile(personaFisica.get("accessoCome"));
        int attempt = 0;
        int maxAttempts = 7;
        DelegateResponsePF response = null;
        while (attempt <= maxAttempts) {

            response = restDelegation.addDelegationPF(delegateRequestPF, tokenExchange);

            if (response!= null && response.getVerificationCode()!= null && !response.getVerificationCode().isEmpty()) {
                logger.info("Inizio controllo notifica fino a stato accettata");

                mandateSingleton.setScenarioMandateId(hooksNew.getScenario(), response.getMandateId());
                mandateSingleton.setScenarioVerificationCode(mandateSingleton.getMandateId(hooksNew.getScenario()), response.getVerificationCode());
                driver.navigate().refresh();
                return;
            }
            else {
                logger.warn("Tentativo #{} di attesa risposta. Riprovo...", attempt);
                webTool.waitTime(3);
                attempt++;
            }
        }
        logger.error("Errore nella response DelegateResponsePF per PF dopo {} tentativi", maxAttempts);
        Assertions.fail("Errore nella response DelegateResponsePF per PF dopo " + maxAttempts + " tentativi");
        webTool.waitTime(3);
    }


    @When("Creo in background una delega per persona fisica 1")
    public void creaInBackgroundUnaDelegaPerPersonaFisica1() {

        logger.info("PERSONA " + dataPopulationConfig.getDelegatePF().getDisplayName());
        logger.info("PERSONA " + dataPopulationConfig.getDelegatePF().getFiscalCode());
        logger.info("DelegateRequestPF " + dataPopulationConfig.getDelegateRequestPF().getVerificationCode());
    }

    @And("Si clicca sul bottone Accetta")
    public void siCliccaSulBottoneAccetta() {
        logger.info("Nel pop-up si clicca sul bottone accetta");

        leTueDelegheSection.clickAccettaButton();
        if (leTueDelegheSection.verificaEsistenzaErroreCodiceSbagliato()) {
            Assertions.assertEquals(
                    "Il codice è sbagliato", leTueDelegheSection.getTextCodiceSbagliato(), "Il codice inserito è sbagliato");
        }
    }

    @And("Si vefifica il messaggio di codice sbagliato")
    public void siVerificaIlMessaggioDiCodiceSbagliato() {
        if (leTueDelegheSection.verificaEsistenzaErroreCodiceSbagliato()) {
            logger.info("Il messaggio di codice sbagliato è visualizzata");
        } else {
            logger.error("Il messaggio di codice sbagliato non è visualizzata");
            Assertions.fail("Il messaggio di codice sbagliato non è visualizzata");
        }

    }

    @And("Si clicca sul bottone indietro popup")
    public void siCliccaSulBottoneIndietroPopUp() {
        leTueDelegheSection.clickIndietroButton();
    }


    @And("Si controlla che la delega ha lo stato Attiva {string}")
    public void siControllaCheLaDelegaHaLoStatoAttiva(String dpFile) {
        logger.info("Si controlla che la delega abbia lo stato Attiva");
        leTueDelegheSection.controlloStatoAttiva(dataPopulationConfig.getPersonaFisica().getName(), dataPopulationConfig.getPersonaFisica().getFamilyName());
    }

    @And("Si controlla che la delega ha lo stato Attiva")
    public void siControllaCheLaDelegaHaLoStatoAttiva(Map<String, String> data) {
        logger.info("Si controlla che la delega abbia lo stato Attiva");
        leTueDelegheSection.controlloStatoAttiva(data.get("firstName"), data.get("lastName"));
    }

    @And("Si controlla che la delega è ancora presente")
    public void siControllaCheLaDelegaèAncoraPresente() {
        logger.info("Si controlla che la delega abbia lo stato Attiva");
        //personaFisica
        leTueDelegheSection.controlloEsistenzaDelega(dataPopulationConfig.getPersonaFisica().getName(), dataPopulationConfig.getPersonaFisica().getFamilyName());


    }

    @And("Nella sezione Deleghe si visualizza il titolo")
    public void siVisualizzaIlTitolo() {

        if (leTueDelegheSection.siVisualizzaIlTitolo()) {
            logger.info("Il titolo della sezione Deleghe si visualizza correttamente");
        } else {
            logger.error("Il titolo della sezione Deleghe NON si visualizza correttamente");
            Assertions.fail("Il titolo della sezione Deleghe NON si visualizza correttamente");
        }
    }

    @And("Nella sezione Deleghe si visualizza il sottotitolo")
    public void siVisualizzaIlSottotitolo() {
        if (leTueDelegheSection.siVisualizzaIlSottotitolo()) {
            logger.info("Il sottotitolo della sezione Deleghe si visualizza correttamente");
        } else {
            logger.error("Il sottotitolo della sezione Deleghe NON si visualizza correttamente");
            Assertions.fail("Il sottotitolo della sezione Deleghe NON si visualizza correttamente");
        }
    }

    @And("Nella sezione Deleghe si visualizza il bottone aggiungi una delega")
    public void siVisualizzaIlBottoneAggiungiUnaDelega() {
        if (leTueDelegheSection.siVisualizzaIlBottoneAggiungiUnaDelega()) {
            logger.info("Il bottone aggiungi delega si visualizza correttamente");
        } else {
            logger.error("Il bottone aggiungi delega NON si visualizza correttamente");
            Assertions.fail("Il bottone aggiungi delega NON si visualizza correttamente");
        }
    }

    @And("Nella sezione Deleghe si visualizzano tutti i campi dell'elenco dei delegati")
    public void siVisualizzanoTuttiICampiDellElencoDeiDelegati() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (leTueDelegheSection.siVisualizzaIlNomeDelegato()) {
            logger.info("Si visualizza correttamente il nome del delegato");
        } else {
            logger.error("NON si visualizza correttamente il nome del delegato");
            Assertions.fail("NON si visualizza correttamente il nome del delegato");
        }

        if (leTueDelegheSection.siVisualizzaDataInizioDelega()) {
            logger.info("Si visualizza correttamente la data di inizio della delega");
        } else {
            logger.error("NON si visualizza correttamente la data di inizio della delega");
            Assertions.fail("NON Si visualizza correttamente la data di inizio della delega");
        }

        if (leTueDelegheSection.siVisualizzaDataFinoDelega()) {
            logger.info("Si visualizza correttamente la data di fine delle deleghe");
        } else {
            logger.error("NON si visualizza correttamente la data di fine delle deleghe");
            Assertions.fail("NON si visualizza correttamente la data di fine delle deleghe");
        }

        if (leTueDelegheSection.siVisualizzaPermessiDelega()) {
            logger.info("Si visualizza correttamente il permesso della delega");
        } else {
            logger.error("NON si visualizza correttamente il permesso della delega");
            Assertions.fail("NON si visualizza correttamente il permesso della delega");
        }
    }


    @And("Si controlla che non sia presente una delega con stesso nome")
    public void siControllaCheNonSiaPresenteUnaDelegaConStessoNome(Map<String, String> personaFisica) {
        logger.info("Si controlla che non ci sia una delega con lo stesso nome");

        String nome = personaFisica.get("nome");
        String cognome = personaFisica.get("cognome");

        if (deleghePage.cercaEsistenzaDelega(nome, cognome)) {
            logger.info("Delega con lo stesso nome trovata");
            deleghePage.clickRevocaButtonOnMenu(nome, cognome);
            popUpRevocaDelegaSection.waitLoadPopUpRevocaDelegaSection();
            popUpRevocaDelegaSection.clickRevocaLaDelega();
        } else {
            logger.info("Delega con lo stesso nome NON trovata");
        }
    }

    @And("Nella pagina Deleghe si clicca sul menu della delega a tuo carico")
    public void nellaPaginaDelegheSiCliccaSulMenuDellaDelega(Map<String, String> personaFisica) {
        logger.info("Si clicca sul menu delle delega");
        String nome = personaFisica.get("nome");
        String cognome = personaFisica.get("cognome");
        deleghePage.clickMenuPerRifiuto(nome, cognome);
    }

    @And("Nella pagina Deleghe si sceglie opzione rifiuta")
    public void nellaPaginaDelegheSiSceglieOpzioneRifiuta() {
        logger.info("Si sceglie l'opzione rifiuta");
        deleghePage.clickRifiuta();
    }

    @And("Si clicca sul bottone rifiuta all'interno del pop-up")
    public void siCliccaSulBottoneRifiutaAllInternoDelPopUp() {
        logger.info("Si clicca sul bottone rifiuta nel pop-up");
        deleghePage.clickRifiutaPopUp();
    }

    @And("Si clicca sul bottone annulla all'interno del pop-up")
    public void siCliccaSulBottoneAnnullaAllInternoDelPopUp() {
        logger.info("Si clicca sul bottone annulla nel pop-up");
        deleghePage.clickAnnullaPopUp();
    }

    @And("Si controlla che la delega non sia più presente nella lista {string}")
    public void siControllaCheLaDelegaNonSiaPiuPresenteNellaLista(String dpFile) {
        logger.info("Si controlla che la delega non sia più presente nella lista");
        //personaFisica
        deleghePage.waitLoadingSpinner();
        if (!deleghePage.verificaEsistenzaDelega(dataPopulationConfig.getPersonaFisica().getName(), dataPopulationConfig.getPersonaFisica().getFamilyName())) {
            logger.info("La delega non è più presente nella lista");
        } else {
            logger.error("La delega è ancora presente in lista");
            Assertions.fail("La delega è ancora presente in lista");
        }
    }

    @And("Nella sezione Deleghe si verifica sia presente una delega")
    public void nellaSezioneDelegheSiVerificaSiaPresenteUnaDelega(Map<String, String> personaFisica) {
        logger.info("Si controlla la presenza di una delega");

        String nome = personaFisica.get("nome");
        String cognome = personaFisica.get("cognome");

        if (!deleghePage.siVisualizzaUnaDelegaConNome(nome, cognome)) {
            webTool.waitTime(5);
            backgroundTest.aggiuntaNuovaDelegaPF();
        }
    }

    @And("Nella sezione Deleghe si verifica sia presente una delega accettata")
    public void nellaSezioneDelegheSiVerificaSiaPresenteUnaDelegaAccettata() {
        logger.info("Si controlla che ci sia una delega accettata");
        //this.deleghe = this.dataPopulation.readDataPopulation("personaFisica.yaml")
        if (!this.deleghePage.siVisualizzaUnaDelegaConNomeDelegato(dataPopulationConfig.getPersonaFisica().getName(), dataPopulationConfig.getPersonaFisica().getFamilyName())) {
            logger.info("accettazioneDelegaPF richiamata dentro IF");
            backgroundTest.loginPF("personaFisica");
            backgroundTest.aggiuntaNuovaDelegaPF();
            backgroundTest.logoutPF();
            backgroundTest.loginPF("delegatoPF");
            backgroundTest.accettazioneDelegaPF();
        } else if (leTueDelegheSection.controlloPresenzaBottoneAccetta()) {
            logger.info("accettazioneDelegaPF richiamata dentro ELSE IF");
            backgroundTest.accettazioneDelegaPF();
        }
        notifichePFPage.clickNotificheButton();
        notifichePFPage.clickLeTueNotificheButton();
    }

    @And("Si verifica sia presente una delega da rifiutare nella sezione Deleghe a Tuo Carico")
    public void siVerificaSiaPresenteUnaDelegaDaRifiutareNellaSezioneDelegheATuoCarico(Map<String, String> personaFisica) {
        logger.info("Si controlla che ci sia almeno una delega");

        String nome = personaFisica.get("nome");
        String cognome = personaFisica.get("cognome");
        deleghePage.vaiInFondoAllaPagina();
        boolean esistenzaDelega = this.deleghePage.siVisualizzaUnaDelegaConNomeDelegato(nome, cognome);
        if (!esistenzaDelega) {
            backgroundTest.loginPF("personaFisica");
            backgroundTest.aggiuntaNuovaDelegaPF();
            backgroundTest.logoutPF();
            backgroundTest.loginPF("delegatoPF");
        }
    }

    @Then("Si controlla che non ci sia più una delega")
    public void siControllaCheNonCiSiaPiuUnaDelega() {
        logger.info("Si controlla che non sia più presente una delega");
        webTool.waitTime(6);
        //this.deleghe = dataPopulation.readDataPopulation("delegatoPF.yaml")
        String nome = dataPopulationConfig.getDelegatePF().getFirstName();
        String cognome = dataPopulationConfig.getDelegatePF().getLastName();
        if (!deleghePage.siVisualizzaUnaDelegaConNome(nome, cognome)) {
            logger.info("La delega è stata revocata correttamente");
        } else {
            logger.error("La delega è ancora presente in lista");
            Assertions.fail("La delega è ancora presente in lista");
        }
    }

    @Then("Si controlla che non ci sia più una delega e si chiude la pagina in parallelo")
    public void siControllaCheNonCiSiaPiuUnaDelegaESiChiudePaginaInParallelo() {
        logger.info("Si controlla che non sia più presente una delega");
        webTool.waitTime(6);
        //this.deleghe = dataPopulation.readDataPopulation("delegatoPF.yaml")
        String nome = dataPopulationConfig.getDelegatePF().getFirstName();
        String cognome = dataPopulationConfig.getDelegatePF().getLastName();
        if (!deleghePage.siVisualizzaUnaDelegaConNome(nome, cognome)) {
            logger.info("La delega è stata revocata correttamente");
        } else {
            logger.error("La delega è ancora presente in lista");
            Assertions.fail("La delega è ancora presente in lista");
        }
        webTool.closeTab();
    }

    @And("Si annulla azione revoca")
    public void siAnnullaAzioneRevoca() {
        deleghePage.clickAnnullaRevoca();
    }

    @And("Si visualizza correttamente la pagina nuova delega")
    public void siVisualizzaCorrettamenteLaPaginaNuovaDelega() {
        logger.info("Si visualizza la sezione Le Tue Deleghe");
        leTueDelegheSection.waitNuovaDelegaSection();
    }

    @And("Si visualizza correttamente la modale mostra codice")
    public void siVisualizzaCorrettamenteLaModaleMostraCodice() {
        deleghePage.checkModaleMostraCodice();
    }

    @And("Si verifica che presente un indicatore numerico in corrispondenza della voce di menù Deleghe")
    public void siVerificaIndicatoreNumericoMenuDeleghe() {
        logger.info("Si controlla indicatore numerico");
        leTueDelegheSection.checkIndicatoreNumerico();
    }

    /**
     * @param tipoVisualizzazioneNotifica i valori possibili sono: Tutti gli enti o Solo enti selezionati
     */
    @Then("Nella sezione della nuova delega si sceglie la visualizzazione delle notifiche da parte di: {string}")
    public void nellaSezioneSiSceglieLaVisualizzazioneDelleNotificheDaParteDi(String tipoVisualizzazioneNotifica) {
        logger.info("Si sceglie la visualizzazione delle notifiche");
        if (tipoVisualizzazioneNotifica.equalsIgnoreCase("Tutti gli enti")) {
            destinatarioPage.clickTuttiGliEnti();
        } else {
            destinatarioPage.clickSoloEntiSelezionati();
        }
    }

    @And("Si verifica che nell'elenco degli enti sono presenti solamente enti radice")
    public void siVerificaCheNellElencoDegliEntiSonoPresentiSolamenteEntiRadice(List<String> enti) {
        logger.info("Si verifica che nell'elenco degli enti sono presenti solamente enti radice");
        destinatarioPage.clickListaEnti();
        destinatarioPage.controlloEntiRadice(enti);
    }

    @And("Nella pagina Piattaforma Notifiche persona fisica si clicca sulle notifiche di {string}")
    public void nellaPaginaPiattaformaNotifichePersonaFisicaSiCliccaSulleNotificheDi(String personaFisica) {
        logger.info("Nella pagina Piattaforma Notifiche della persona fisica nel menu laterale si clicca sulla voce notifiche di " + personaFisica);
        deleghePage.clickDelegheDelDelegante(personaFisica);
    }

    private String getCodiceDelega(String nomeConfig) {
        String codiceDelega;
//        nomeFileNuovaDelegaPG -> nuovaDelegaPG, nomeFileNuovaDelega -> nuova_delega, personaFisica, nuova_delega, nuova_delega
        switch (nomeConfig) {
            case "nuovaDelegaPG" -> {
                logger.info("Codice nuovaDelegaPG: " + dataPopulationConfig.getNuovaDelegaPg().getCodiceDelega());
                codiceDelega = dataPopulationConfig.getNuovaDelegaPg().getCodiceDelega();
            }
            case "nuova_delega" -> {
                codiceDelega = dataPopulationConfig.getNuovaDelega().getCodiceDelega();
                logger.info("Codice nuova_delega: " + codiceDelega);
            }
            case "personaFisica" -> {
                //TODO Non viene Utilizzato
                codiceDelega = "";
            }

            default -> {
                logger.error("Nessun nome corrisponde");
                throw new RuntimeException("ERRORE Nessun nome corrisponde");
            }
        }
        return codiceDelega;
    }

    @And("Inserisci credenziali Delegante")
    public void inserisciCredenzialiDelegante(Map<String, String> destinatario) {
        logger.info("Inserisci credenziali Delegato");
        leTueDelegheSection.inserisciCredenzialiDelegato(destinatario);
    }

    @And("Seleziona PG radio button portale {string}")
    public void selezionaPGRadioButtonPortale(String portale) {
        deleghePage.selezionaPGRadioButton(portale);
    }

    @And("Inserire Codice Fiscale {string}")
    public void inserireCodiceFiscale(String codiceFiscale) {
        leTueDelegheSection.inserireCF(codiceFiscale);
    }

    @Then("Verifica messaggio errore Deleghe {string}")
    public void verificaMessaggioErroreDeleghe(String messaggio) {
        deleghePage.verificaMessaggioErroreDeleghe(messaggio);
    }
}
