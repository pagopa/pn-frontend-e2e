package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.model.delegate.DelegatePG;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPG;
import it.pn.frontend.e2e.model.delegate.DelegateResponsePG;
import it.pn.frontend.e2e.model.singleton.MandateSingleton;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.DeleghePGPagoPAPage;
import it.pn.frontend.e2e.rest.RestDelegation;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.AggiungiDelegaPGSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.DelegatiImpresaSection;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.DeleghePagoPATest;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeleghePGPagoPATest {
    private final Logger logger = LoggerFactory.getLogger("DeleghePGPagoPATest");
    private final WebDriver driver = Hooks.driver;
    private final DeleghePGPagoPAPage deleghePGPagoPAPage = new DeleghePGPagoPAPage(this.driver);
    private final DeleghePagoPATest deleghePagoPATest = new DeleghePagoPATest();
    private final DelegatiImpresaSection delegatiImpresaSection = new DelegatiImpresaSection(this.driver);
    private final AggiungiDelegaPGSection aggiungiDelegaPGSection = new AggiungiDelegaPGSection(this.driver);
    private final DataPopulation dataPopulation = new DataPopulation();
    private Map<String, Object> datiDelega = new HashMap<>();
    Map<String, Object> datiPersonaFisica = new HashMap<>();
    private final MandateSingleton mandateSingleton = MandateSingleton.getInstance();

    private final RestDelegation restDelegation = RestDelegation.getInstance();

    private LoginPGPagoPATest loginPGPagoPaTest = new LoginPGPagoPATest();

    @And("Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa")
    public void siVisualizzaLaPaginaDeleghe() {
        logger.info("Si controlla che si visualizza la pagina Deleghe");

        deleghePGPagoPAPage.waitLoadDeleghePage();
    }

    @And("Nella pagina Deleghe si clicca su Delegati dall impresa")
    public void nellaPaginaDelegheSiCliccaSuDelegatiDallImpresa() {
        logger.info("Si clicca sul delegati dell'impresa");
        deleghePGPagoPAPage.clickDelegatiImpresa();
    }

    @And("Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa")
    public void siVisualizzaCorrettamenteLaPaginaDelegheSezioneDelegheDellImpresa() {
        logger.info("Si controlla che la sezione Deleghe dell'impresa");

        delegatiImpresaSection.waitLoadDelegatiImpresaPage();
    }

    @And("Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega")
    public void nellaSezioneDelegatiDellImpresaClickSulBottoneAggiungiNuovaDelega() {
        logger.info("Nella sezione Deleghe dell'impresa si clicca su aggiungi una nuova delega");

        delegatiImpresaSection.clickAggiungiDelegaButton();
    }

    @And("Si visualizza la sezione Aggiungi Delega persona giuridica")
    public void siVisualizzaLaSezioneLeTueDeleghePersonaGiuridica() {
        logger.info("Si controllo che si visualizza la sezione Aggiungi delega");

        aggiungiDelegaPGSection.waitLoadAggiungiDelegaPage();
    }

    @And("Nella sezione Aggiungi Delega persona giuridica inserire i dati {string}")
    public void nellaSezioneLeTueDeleghePersonaGiuridicaInserireIDati(String dpFile) {
        logger.info("Si aggiungono tutti i dati del delegato");

        this.datiDelega = dataPopulation.readDataPopulation(dpFile + ".yaml");

        aggiungiDelegaPGSection.selectPersonaGiuridicaRadioButton();
        aggiungiDelegaPGSection.insertRagioneSociale(this.datiDelega.get("ragioneSociale").toString());
        aggiungiDelegaPGSection.inserireCF(this.datiDelega.get("codiceFiscale").toString());
        aggiungiDelegaPGSection.selectSoloEntiSelezionati();
        aggiungiDelegaPGSection.waitLoadAggiungiDelegaPage();
        aggiungiDelegaPGSection.selezionaUnEnte(datiDelega.get("ente").toString());
    }

    @And("Nella sezione Aggiungi Delega persona giuridica inserire i dati")
    public void nellaSezioneLeTueDeleghePersonaGiuridicaInserireIDati(Map<String, String> personaGiuridica) {
        logger.info("Si aggiungono tutti i dati del delegato");
        aggiungiDelegaPGSection.selectPersonaGiuridicaRadioButton();
        aggiungiDelegaPGSection.insertRagioneSociale(personaGiuridica.get("ragioneSociale"));
        aggiungiDelegaPGSection.inserireCF(personaGiuridica.get("codiceFiscale"));
        aggiungiDelegaPGSection.selectSoloEntiSelezionati();
        aggiungiDelegaPGSection.waitLoadAggiungiDelegaPage();
        aggiungiDelegaPGSection.selezionaUnEnte(personaGiuridica.get("ente"));
    }

    @And("Nella sezione Aggiungi Delega persona giuridica verificare che la data sia corretta")
    public void nellaSezioneLeTueDeleghePersonaGiuridicaVerificareCheLaDataSiaCorretta() {
        logger.info("Si controlla che la data di fine delega sia corretta");

        if (aggiungiDelegaPGSection.verificareCheLaDataSiaCorretta()) {
            logger.info("La data di fine delega è corretta");
        } else {
            logger.error("La data di fine delega non è corretta");
            Assert.fail("La data di fine delega non è corretta");
        }
    }

    @And("Nella sezione Aggiungi Delega persona giuridica salvare il codice verifica all'interno del file {string}")
    public void nellaSezioneLeTueDeleghePersonaGiuridicaSalvareIlCodiceVerificaAllInternoDelFile(String dpFile) {
        logger.info("Si salva il codice della delega nel file " + dpFile);

        this.datiDelega = dataPopulation.readDataPopulation(dpFile + ".yaml");

        String codiceDelega = aggiungiDelegaPGSection.salvataggioCodiceVerifica();
        this.datiDelega.put("codiceDelega", codiceDelega);

        dataPopulation.writeDataPopulation(dpFile + ".yaml", this.datiDelega);
    }

    @And("Nella sezione Aggiungi Delega persona giuridica click sul bottone Invia richiesta e sul bottone torna alle deleghe")
    public void nellaSezioneLeTueDeleghePersonaGiuridicaclickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe() {
        logger.info("Si clicca sul bottone invia richiesta");

        aggiungiDelegaPGSection.clickSulBottoneInviaRichiesta();
    }

    @And("Nella sezione Delegati dall impresa si visualizza la delega in stato di attesa di conferma")
    public void nellaSezioneDelegatiDallImpresaSiVisualizzaLaDelegaInStatoDiAttesaDiConferma() {
        logger.info("Si controlla che la delega sia in stato attesa di conferma");

        this.datiDelega = dataPopulation.readDataPopulation("nuovaDelegaPG.yaml");

        delegatiImpresaSection.waitLoadDelegatiImpresaPage();
        delegatiImpresaSection.controlloEsistenzaDelega(this.datiDelega.get("ragioneSociale").toString());
        delegatiImpresaSection.clickMenuDelega(this.datiDelega.get("ragioneSociale").toString());
        delegatiImpresaSection.esistenzaRevocaButton();
    }

    @And("Si controlla che non sia presente una delega con stesso nome persona giuridica {string}")
    public void siControllaCheNonSiaPresenteUnaDelegaConStessoNomePersonaGiuridica(String ragioneSociale) {
        logger.info("Si controlla che non ci sia una delega con lo stesso nome");

        if (deleghePGPagoPAPage.cercaEsistenzaDelegaPG(ragioneSociale)) {
            logger.info("Delega con lo stesso nome trovata");
            deleghePGPagoPAPage.clickRevocaMenuButtonPG(ragioneSociale);
            delegatiImpresaSection.waitPopUpRevoca(ragioneSociale);
            delegatiImpresaSection.clickRevocaButton();
        } else {
            logger.info("Delega con lo stesso nome NON trovata");
        }
    }

    @And("Nella sezione Le Tue Deleghe inserire una data con formato errato e antecedente alla data")
    public void nellaSezioneLeTueDelegheInserireUnaDataConFormatoErratoEAntecedenteAllaData() {
        logger.info("Si inserisce una data errata e antecedente");

        aggiungiDelegaPGSection.clearInputData();
        aggiungiDelegaPGSection.insertDataErrata();
    }

    @And("Nella sezione Le Tue Deleghe inserire una data")
    public void nellaSezioneLeTueDelegheInserireUnaData() {
        logger.info("Si inserisce una data");

        aggiungiDelegaPGSection.clearDateField();
        aggiungiDelegaPGSection.insertDataCorretta();
    }

    @And("Nella sezione Le Tue Deleghe si visualizza il messaggio di errore data errata")
    public void nellaSezioneLeTueDelegheSiVisualizzaIlMessaggioDiErroreDataErrata() {
        logger.info("Si controlla che si veda il messaggio di errore");

        Assert.assertEquals("il messaggio di errore previsto: Data errata è diverso dell'attuale " + aggiungiDelegaPGSection.waitMessaggioErroreData(), "Data errata", aggiungiDelegaPGSection.waitMessaggioErroreData());
        logger.info("il messaggio di errore 'Data errata' è presente");
    }

    @And("Nella pagina Deleghe si clicca su Deleghe a carico dell impresa")
    public void nellaPaginaDelegheSiCliccaSuDelegheACaricoDellImpresa() {
        logger.info("Si clicca sul tab Deleghe a carico dell'impresa");

        deleghePGPagoPAPage.clickSuDelegheCaricoDellImpresa();
    }

    @And("Nella pagina Deleghe sezione Deleghe a Carico dell impresa si inserisce il codice fiscale del delegante {string}")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiInserisceIlCodiceFiscaleDelDelegante(String codiceFiscale) {
        logger.info("Si inserisce il codice fiscale del delegante");
        deleghePGPagoPAPage.insertCFDelegante(codiceFiscale);
    }

    @And("Nella pagina Deleghe sezione Deleghe a Carico dell impresa si clicca su bottone Filtra")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiCliccaSuBottoneFiltra() {
        logger.info("Si clicca filtra button");

        this.deleghePGPagoPAPage.clickFiltraButton();

    }

    @And("Nella pagina Deleghe sezione Deleghe a Carico dell impresa si controlla che venga restituita la delega con il codice fiscale inserito {string}")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiControllaCheVengaRestituitaLaDelegaConIlCodiceFiscaleInserito(String ragioneSociale) {

        if (deleghePGPagoPAPage.controlloDelegaRestituita(ragioneSociale)) {
            this.logger.info("La delega restituita è corretta");
        } else {
            this.logger.error("La delega restituita NON è corretta");
            Assert.fail("La delega restituita NON è corretta");
        }
    }

    @And("Nella pagina Deleghe sezione Deleghe dell impresa  si verifica sia presente una delega")
    public void nellaPaginaDelegheSezioneDelegheDellImpresaSiVerificaSiaPresenteUnaDelega() {
        logger.info("Si controlla che ci sia almeno una delega");
        BackgroundTest backgroundTest = new BackgroundTest();

        if (!this.delegatiImpresaSection.siVisualizzaUnaDelega()) {
            backgroundTest.aggiuntaNuovaDelegaDellImpresaPG();
        }
    }

    @And("Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega {string}")
    public void nellaPaginaDelegheSezioneDelegheAcaricoDellImpresaSiCliccaSulMenuDellaDelega(String dpFile) {
        logger.info("Si clicca sul menu delle delega");

        this.datiDelega = dataPopulation.readDataPopulation(dpFile + ".yaml");
        this.delegatiImpresaSection.clickMenuDelega(datiDelega.get("ragioneSociale").toString());
    }

    @And("Nella pagina Deleghe sezione Deleghe dell impresa si clicca sul menu della delega {string}")
    public void nellaPaginaDelegheSezioneDelegheDellImpresaSiCliccaSulMenuDellaDelega(String dpFile) {
        logger.info("Si clicca sul menu delle delega");

        this.datiDelega = dataPopulation.readDataPopulation(dpFile + ".yaml");
        this.delegatiImpresaSection.controlloEsistenzaDelega(datiDelega.get("ragioneSociale").toString());
    }

    @And("Nella pagina Deleghe sezione Deleghe dell impresa si sceglie l'opzione mostra codice")
    public void nellaPaginaDelegheSezioneDelegheDellImpresaSiSceglieLOpzioneMostraCodice() {
        logger.info("Si sceglie l'opzione mostra codice");

        delegatiImpresaSection.clickMostraCodice();
    }

    @And("Nella sezione Deleghe persona giuridica si sceglie l'opzione revoca")
    public void nellaSezioneDeleghePersonaGiuridicaSiSceglieLOpzioneRevoca() {
        logger.info("Si clicca sull'opzione revoca del menu");

        delegatiImpresaSection.clickRevocaMenuButtonPG();
    }


    @And("Nella sezione Deleghe sezione Deleghe dell'impresa si controlla che non sia più presente la delega {string}")
    public void nellaSezioneDelegheSezioneDelegheDellImpresaSiControllaCheNonSiaPiuPresenteLaDelega(String dpFile) {
        logger.info("Si controlla che la delega non sia più in elenco");

        this.datiDelega = this.dataPopulation.readDataPopulation(dpFile + ".yaml");
        delegatiImpresaSection.waitLoadDelegatiImpresaPage();
        delegatiImpresaSection.waitLoadingSpinner();
        if (!deleghePGPagoPAPage.cercaEsistenzaDelegaPG(this.datiDelega.get("ragioneSociale").toString())) {
            logger.info("La delega è stata revocata correttamente");
        } else {
            logger.error("La delega NON è stata revocata correttamente");
            Assert.fail("La delega NON è stata revocata correttamente");
        }

    }

    @And("Nella sezione Deleghe si clicca sul bottone conferma codice")
    public void nellaSezioneDelegheSiCliccaSulBottoneConfermaCodice() {
        logger.info("Si clicca sul bottone accetta delega");

        deleghePGPagoPAPage.clickConfirmCodeButton();
    }

    @And("Si assegna un gruppo alla delega {string}")
    public void siAssegnaUnGruppoAllaDelega(String gruppo) {
        logger.info("Si assegna un gruppo alla delega");

        deleghePGPagoPAPage.waitLoadPopUpGruppo();
        deleghePGPagoPAPage.clickAssegnaGruppoRadioButton();
        deleghePGPagoPAPage.waitLoadPopUpGruppo();
        deleghePGPagoPAPage.clickGruppoField(gruppo);
    }

    @And("Si clicca sul bottone conferma gruppo")
    public void siCliccaSulBottoneConfermaGruppo() {
        logger.info("Si seleziona il bottone conferma");

        deleghePGPagoPAPage.clickBottoneConferma();
        if (this.deleghePGPagoPAPage.verificaEsistenzaErroreCodiceSbagliato()) {
            logger.error("il codice inserito è sbagliato");
            Assert.fail("il codice inserito è sbagliato");
        }
    }

    @And("Si controlla che la delega PG ha lo stato Attiva {string}")
    public void siControllaCheLaDelegaPGALoStatoAttiva(String ragioneSociale) {
        logger.info("Si controlla che lo stato della delega sia attivo");

        deleghePGPagoPAPage.controlloStatoAttiva(ragioneSociale);
    }

    @And("Si clicca sul bottone conferma gruppo errato")
    public void siCliccaSulBottoneConfermaGruppoErrato() {
        logger.info("Si seleziona il bottone conferma");

        deleghePGPagoPAPage.clickBottoneConferma();
        deleghePGPagoPAPage.verificaEsistenzaErroreCodiceSbagliato();
    }

    @And("Nella sezione Deleghe si clicca sul bottone rifiuta")
    public void nellaSezioneDelegheSiCliccaSulBottoneRifiuta() {
        logger.info("Si clicca su l'opzione rifiuta");

        deleghePGPagoPAPage.clickOpzioneRifiuta();
    }

    @And("Si clicca sul bottone rifiuta delega")
    public void siCliccaSulBottoneRifiutaDelega() {
        logger.info("Si clicca su bottone rifiuta del pop-up");

        deleghePGPagoPAPage.waitLoadPopUpRevoca();
        deleghePGPagoPAPage.clickBottoneRifiuta();
    }

    @And("Si controlla che la delega non si più presente in elenco")
    public void siControllaCheLaDelegaNonSiPiuPresenteInElenco() {
        logger.info("Si controlla che la delega sia stata rifiutata");

        this.datiDelega = this.dataPopulation.readDataPopulation("personaGiuridica.yaml");

        deleghePGPagoPAPage.aggiornamentoPagina();

        if (!deleghePGPagoPAPage.cercaEsistenzaDelegaPG(this.datiDelega.get("ragioneSociale").toString())) {
            logger.info("La delega è stata rifiutata correttamente");
        } else {
            logger.error("La delega NON è stata rifiutata correttamente");
            Assert.fail("La delega NON è stata rifiutata correttamente");
        }
    }

    @And("Nella sezione Deleghe si clicca sul bottone modifica")
    public void nellaSezioneDelegheSiCliccaSulBottoneModifica() {
        logger.info("Si clicca sull'opzione modifica");

        deleghePGPagoPAPage.clickOpzioneModifica();
    }

    @And("Si clicca sul bottone assegna a un gruppo")
    public void siCliccaSulBottoneAssegnaAUnGruppo() {
        logger.info("Si clicca sul bottone assegna un gruppo");

        deleghePGPagoPAPage.waitLoadPopUpModifica();
        deleghePGPagoPAPage.clickAssegnaGruppoRadioButton();
    }

    @And("Si selezione il gruppo della delega")
    public void siSelezioneIlGruppoDellaDelega() {
        logger.info("Si seleziona un il gruppo di delega");

        deleghePGPagoPAPage.waitLoadPopUpModifica();
        deleghePGPagoPAPage.clickGruppoField("Test gruppi");
    }

    @And("Si clicca su conferma in assegnazione gruppo")
    public void siCliccaSuConfermaInAssegnazioneGruppo() {
        logger.info("Si clicca su conferma del pop-up");

        deleghePGPagoPAPage.clickBottoneConferma();
        if (this.deleghePGPagoPAPage.verificaEsistenzaErroreCodiceSbagliato()) {
            logger.error("il codice inserito è sbagliato");
            Assert.fail("il codice inserito è sbagliato");
        }
    }

    @And("Si controlla che la delega ha cambiato gruppo")
    public void siControllaCheLaDelegaHaCambiatoStato() {
        logger.info("Si controlla che la delega abbia il gruppo");

        this.datiDelega = dataPopulation.readDataPopulation("personaGiuridica_1.yaml");

        if (deleghePGPagoPAPage.verificaPresenzaGruppo(this.datiDelega.get("ragioneSociale").toString())) {
            logger.info("La delega ha un gruppo");
        } else {
            logger.error("La delega NON ha un gruppo");
            Assert.fail("La delega NON ha un gruppo");
        }
    }

    @And("Si clicca sul bottone non assegna a un gruppo")
    public void siCliccaSulBottoneNonAssegnaAUnGruppo() {
        logger.info("Si clicca sul bottone non assegna a un gruppo");

        deleghePGPagoPAPage.waitLoadPopUpModifica();
        deleghePGPagoPAPage.clickNonAssegnaGruppo();
    }

    @And("Si controlla che la delega non abbia più il gruppo")
    public void siControllaCheLaDelegaNonAbbiaPiuIlGruppo() {
        logger.info("Si verifica che non abbia più il gruppo");

        this.datiDelega = dataPopulation.readDataPopulation("personaGiuridica.yaml");

        deleghePGPagoPAPage.waitLoadDeleghePage();

        if (!deleghePGPagoPAPage.verificaPresenzaGruppo(this.datiDelega.get("ragioneSociale").toString())) {
            logger.info("La delega non ha più gruppo");
        } else {
            logger.error("La delega ha ancora il gruppo");
            Assert.fail("La delega ha ancora il gruppo");
        }

    }

    @Then("Si clicca sul bottone annulla")
    public void siCliccaSulBottoneAnnulla() {
        logger.info("Si clicca sul bottone annulla");

        delegatiImpresaSection.clickAnnulla();
    }

    @And("Nella pagina Deleghe sezione Deleghe a Carico dell impresa si inserisce il gruppo del delegante")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiInserisceIlGruppoDelDelegante() {
        logger.info("Si inserisce il gruppo del delegante");

        deleghePGPagoPAPage.inserireGruppoDelegante();
    }

    @And("Creo in background una delega per persona giuridica")
    public void creoInBackgroundUnaDelegaPerPersonaGiuridica(Map<String, String> personaGiuridica) {
        logger.info("Si controlla che ci sia una delega");
        String dateto = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DelegatePG delegatePG = DelegatePG.builder()
                .companyName(personaGiuridica.get("companyName"))
                .displayName(personaGiuridica.get("displayName"))
                .fiscalCode(personaGiuridica.get("fiscalCode"))
                .person(Boolean.parseBoolean(personaGiuridica.get("person")))
                .build();
        DelegateRequestPG delegateRequestPG = DelegateRequestPG.builder()
                .dateto(dateto)
                .delegate(delegatePG)
                .visibilityIds(new ArrayList<>())
                .verificationCode("12345")
                .build();
        String tokenExchange = loginPGPagoPaTest.getTokenExchangePGFromFile(personaGiuridica.get("accessoCome"));
        DelegateResponsePG response = restDelegation.addDelegationPG(delegateRequestPG, tokenExchange);
        mandateSingleton.setScenarioMandateId(Hooks.getScenario(),response.getMandateId());
        mandateSingleton.setScenarioVerificationCode(mandateSingleton.getMandateId(Hooks.getScenario()),response.getVerificationCode());
        driver.navigate().refresh();
    }

    @And("Si clicca sul bottone accetta delega dopo aver inserito il codice di verifica")
    public void siCliccaSulBottoneAccettaDelegaDopoAverInseritoIlCodiceDiVerifica() {
        logger.info("Si clicca su conferma del pop-up");

        deleghePGPagoPAPage.clickBottoneConfermaDelega();
        if (this.deleghePGPagoPAPage.verificaEsistenzaErroreCodiceSbagliato()) {
            logger.error("il codice inserito è sbagliato");
            Assert.fail("il codice inserito è sbagliato");
        }
    }

    public void siCliccaSulBottoneAccettaDelega() {
        logger.info("Si clicca su conferma del pop-up");

        deleghePGPagoPAPage.clickBottoneConfermaDelega();
    }

    @And("Si accetta la delega senza gruppo")
    public void siAccettaLaDelegaSenzaGruppo() {
        BackgroundTest backgroundTest = new BackgroundTest();
        backgroundTest.accettazioneDelegaSceltaGruppo(false,null);
    }

    @And("Si inserisce il codice della delega a carico dell impresa nella modale")
    public void siInserisceIlCodiceDellaDelegaACaricoDellImpresaNellaModale() {
        String verificationCode = mandateSingleton.getVerificationCode(mandateSingleton.getMandateId(Hooks.getScenario()));
        logger.info(verificationCode);
        deleghePGPagoPAPage.inserimentoCodiceDelegaACaricoDellImpresaAPI(verificationCode);
    }

    @And("Si ripristina lo stato iniziale delle deleghe dall impresa {string}")
    public void siRipristinaLoStatoInizialeDelleDelegheDallImpresa(String ragioneSociale) {
        BackgroundTest backgroundTest = new BackgroundTest();
        backgroundTest.revocaDelegaPG(ragioneSociale);
    }

    @And("Si accetta la delega con gruppo {string}")
    public void siAccettaLaDelegaGruppo(String gruppo) {
        BackgroundTest backgroundTest = new BackgroundTest();
        backgroundTest.accettazioneDelegaSceltaGruppo(true,gruppo);
    }

    public void siInserisceIlCodiceDellaDelegaACaricoDellImpresaNellaModaleErrata() {

        deleghePGPagoPAPage.inserimentoCodiceDelegaACaricoDellImpresaAPI("00000");

    }

    public void nonSiAssegnaUnGruppoAllaDelegaCheLoRichiede() {
        deleghePGPagoPAPage.checkErroreInSelezioneGruppo();
    }

    @And("Si emula accettazione della delega con gruppo con errore")
    public void siEmulaAccettazioneDellaDelegaConGruppoConErrore() {
        BackgroundTest backgroundTest = new BackgroundTest();

        backgroundTest.checkDelegaSceltaGruppoEInserimentoCodiceErrata();
    }

    public void checkErroreInInserimentoCodiceErrato() {
        deleghePGPagoPAPage.checkErroreInInserimentoCodice();
    }

    public void siCliccaSulBottoneIndietroInInserimentoCodiceVerifica() {
        deleghePGPagoPAPage.clickIndietroInInserimentoCodiceVerifica();
    }

    public void siCliccaSulBottoneIndietroInAssegnazioneGruppo() {
        deleghePGPagoPAPage.clickButtonIndietroInAssegnazioneGruppo();
    }

    @And("Non si inserisce il codice OTP e l invito della delega non è più presente")
    public void nonSiInserisceIlCodiceOTPELInvitoDellaDelegaNonèPiùPresente() {
        WebTool.waitTime(61 * 15);
        driver.navigate().refresh();
        deleghePGPagoPAPage.waitLoadDeleghePage();
    }

    @And("Si inserisce un codice della delega a carico dell impresa errato nella modale")
    public void siInserisceUnCodiceDellaDelegaACaricoDellImpresaErratoNellaModale() {
        logger.info("Si inserisce un codice della delega errato nella modale");
        deleghePagoPATest.siSceglieOpzioneAccetta();
        String verificationCode = "00000";
        deleghePGPagoPAPage.inserimentoCodiceDelegaACaricoDellImpresaAPI(verificationCode);
        deleghePGPagoPAPage.clickConfirmCodeButton();
        deleghePGPagoPAPage.clickBottoneConferma();
    }

    @Then("Le textbox che contengono le cifre del codice delega diventano rosse")
    public void leTextboxCheContengonoLeCifreDelCodiceDelegaDiventanoRosse() {
        logger.info("Si visualizza alert di errore in pagina");
        deleghePGPagoPAPage.checkAlertWrongDelegationCode();
        deleghePGPagoPAPage.clickButtonIndietroDaAssegnaGruppo();
        deleghePGPagoPAPage.checkTextboxCodiceSonoRosse();
        deleghePGPagoPAPage.clickButtonIndietroCloseModale();
    }

    @And("Nella pagina Deleghe sezione Deleghe a Carico dell impresa si controlla che ci sia una delega con la ragione sociale inserita {string}")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiControllaCheCiSiaUnaDelegaConLaRagioneSocialeInserita(String codFiscale) {
        if (deleghePGPagoPAPage.controlloDelegaRestituita(codFiscale)) {
            this.logger.info("La delega restituita è corretta");
        } else {
            this.logger.error("La delega restituita NON è corretta");
            Assert.fail("La delega restituita NON è corretta");
        }
    }

    @And("Si revoca delega come delegante con api")
    public void siRevocaDelegaComeDelegantConApi() {

        loginPGPagoPaTest.getTokenExchangePGFromFile("delegante");
        String mandateId = mandateSingleton.getMandateId(Hooks.getScenario());
        restDelegation.revokeDelegation(mandateId);

    }

    @And("Si controlla la tabella deleghe a carico dell impresa")
    public void siControllaLaTabellaDelegheACaricoDellImpresa() {

        deleghePGPagoPAPage.checkTabellaDelegheACaricoDellImpresa();
    }

    @And("Si controlla la tabella delegati dall impresa")
    public void siControllaLaTabellaDelegatiDallImpresa() {
        delegatiImpresaSection.checkTabellaDelegheDellImpresa();
    }

    @And("Nella sezione Delegati dall impresa si visualizza correttamente una delega in stato di attesa di conferma {string}")
    public void nellaSezioneDelegatiDallImpresaSiVisualizzaCorrettamenteUnaDelegaInStatoDiAttesaConferma(String ragioneSociale) {
        logger.info("Si controlla che la delega sia in stato attesa di conferma");

        delegatiImpresaSection.waitLoadDelegatiImpresaPage();
        delegatiImpresaSection.controlloEsistenzaDelega(ragioneSociale);
    }
}
