package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.api.personaGiuridica.CreazioneDelega;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.DeleghePGPagoPAPage;
import it.pn.frontend.e2e.section.destinatario.personaFisica.LeTueDelegheSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.AggiungiDelegaPGSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.DelegatiImpresaSection;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DeleghePGPagoPATest {
    private final Logger logger = LoggerFactory.getLogger("DeleghePGPagoPATest");
    private final WebDriver driver = Hooks.driver;
    private final DeleghePGPagoPAPage deleghePGPagoPAPage = new DeleghePGPagoPAPage(this.driver);
    private final DelegatiImpresaSection delegatiImpresaSection = new DelegatiImpresaSection(this.driver);
    private final AggiungiDelegaPGSection aggiungiDelegaPGSection = new AggiungiDelegaPGSection(this.driver);
    private final DataPopulation dataPopulation = new DataPopulation();
    private Map<String, Object> datiDelega = new HashMap<>();
    Map<String, Object> datiPersonaFisica = new HashMap<>();



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

    @And("Nella sezione Aggiungi Delega  persona giuridica click sul bottone Invia richiesta e sul bottone torna alle deleghe")
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
    }

    @And("Si controlla che non sia presente una delega con stesso nome {string} persona giuridica")
    public void siControllaCheNonSiaPresenteUnaDelegaConStessoNomePersonaGiuridica(String nomeFile) {
        logger.info("Si controlla che non ci sia una delega con lo stesso nome");

        this.datiDelega = dataPopulation.readDataPopulation(nomeFile + ".yaml");

        String ragioneSociale = datiDelega.get("ragioneSociale").toString();

        if (deleghePGPagoPAPage.cercaEsistenzaDelegaPG(ragioneSociale)) {
            logger.info("Delega con lo stesso nome trovata");
            deleghePGPagoPAPage.clickRevocaMenuButtonPG(ragioneSociale);
            delegatiImpresaSection.waitPopUpRevoca(ragioneSociale);
            delegatiImpresaSection.clickRevocaButton();
        } else {
            logger.info("Delega con lo stesso nome NON trovata");
        }
    }

    @And("Nella sezione Le Tue Deleghe inserire una data con formato errato e andecedente alla data")
    public void nellaSezioneLeTueDelegheInserireUnaDataConFormatoErratoEAndecedenteAllaData() {
        logger.info("Si inserisce una data errata e antecedente");

        aggiungiDelegaPGSection.clearInputData();
        aggiungiDelegaPGSection.insertDataErrata();
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

    @And("Si vede correttamente l'elenco delle deleghe")
    public void siVedeCorrettamenteLElencoDelleDeleghe() {
        logger.info("Si controlla che si visualizzi l'elenco delle deleghe");

        deleghePGPagoPAPage.verificaPresenzaElencoDeleghe();
    }

    @And("Nella pagina Deleghe sezione Deleghe a Carico dell impresa si inserisce il codice fiscale del delegante {string}")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiInserisceIlCodiceFiscaleDelDelegante(String dpFile) {
        logger.info("Si inserisce il codice fiscale del delegante");

        this.datiPersonaFisica = this.dataPopulation.readDataPopulation(dpFile + ".yaml");

        deleghePGPagoPAPage.insertCFDelegante(this.datiPersonaFisica.get("codiceFiscale").toString());
    }

    @And("Nella pagina Deleghe sezione Deleghe a Carico dell impresa si clicca su bottone Filtra")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiCliccaSuBottoneFiltra() {
        logger.info("Si clicca filtra button");

        this.deleghePGPagoPAPage.clickFiltraButton();

    }

    @And("Nella pagina Deleghe sezione Deleghe a Carico dell impresa si controlla che venga restituita la delega con il codice fiscale inserito {string}")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiControllaCheVengaRestituitaLaDelegaConIlCodiceFiscaleInserito(String dpFile) {
        this.datiDelega = this.dataPopulation.readDataPopulation(dpFile + ".yaml");
        String delegante = this.datiDelega.get("ragioneSociale").toString();
        if (deleghePGPagoPAPage.controlloDelegaRestituita(delegante)) {
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

    @Then("Si clicca sul bottone revoca")
    public void siCliccaSulBottoneRevoca() {
        logger.info("Si clicca sul bottone rifiuta all'interno del pop-up");
        delegatiImpresaSection.clickRevocaButton();
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

    @And("Si assegna un gruppo alla delega")
    public void siAssegnaUnGruppoAllaDelega() {
        logger.info("Si assegna un gruppo alla delega");

        deleghePGPagoPAPage.waitLoadPopUpGruppo();
        deleghePGPagoPAPage.clickAssegnaGruppoRadioButton();
        deleghePGPagoPAPage.waitLoadPopUpGruppo();
        deleghePGPagoPAPage.clickGruppoField();
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

    @And("^Si controlla che la delega PG ha lo stato Attiva (.*)$")
    public void siControllaCheLaDelegaPGALoStatoAttiva(String ragioneSociale) {
        this.logger.info("Si controlla che lo stato della delega sia attivo");

        this.deleghePGPagoPAPage.controlloStatoAttiva(ragioneSociale);
    }

    @And("Non si assegna un gruppo alla delega")
    public void nonSiAssegnaUnGruppoAllaDelega() {
        logger.info("Si clicca sul bottone non assegna gruppo");

        deleghePGPagoPAPage.clickNonAssegnaGruppo();
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

        deleghePGPagoPAPage.aggionamentoPagina();

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
        deleghePGPagoPAPage.clickGruppoField();
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

    @And("Si controlla che la delega a cambiato gruppo")
    public void siControllaCheLaDelegaACambiatoStato() {
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

/*
    @And("Nella sezione Deleghe si verifica sia presente una delega per PG {string}")
    public void nellaSezioneDelegheSiVerificaSiaPresenteUnaDelegaPG(String dpFile) {
        logger.info("Si verifica sia presente una delega per PG");

        String ragioneSociale = dataPopulation.readDataPopulation(dpFile + ".yaml").get("ragioneSociale").toString();
        if (!deleghePGPagoPAPage.cercaEsistenzaDelegaPG(ragioneSociale)) {
            aggiuntaDelegaConChiamata(dpFile);
        }
    }*/

    public void aggiuntaDelegaConChiamata(String dpFile) {
        CreazioneDelega creazioneDelega = new CreazioneDelega();
        String variabileAmbiente = System.getProperty("environment");
        String url = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/mandate/api/v1/mandate";
        creazioneDelega.setUrl(url);
        String body = creazioneBodyChiamata(dpFile);
        if (!body.isEmpty()) {
            logger.info("Il body della chiamata è: " + body);
            creazioneDelega.setBody(body);
        } else {
            logger.error("Non è stato possibile leggere il file json del body");
            Assert.fail("Non è stato possibile leggere il file json del body");
        }
        String bearerToken = dataPopulation.readDataPopulation("personaGiuridica" + ".yaml").get("bearerToken").toString();
        creazioneDelega.setAuthorizationToken(bearerToken);
        boolean results = creazioneDelega.runCreazioneDelegheApi();
        if (!results) {
            logger.error("La chiamata ha risposto con questo codice: " + creazioneDelega.getResponseCode());
            Assert.fail("La chiamata ha risposto con questo codice: " + creazioneDelega.getResponseCode());
        }


        deleghePGPagoPAPage.aggionamentoPagina();
        deleghePGPagoPAPage.waitLoadDeleghePage();

    }


    private String creazioneBodyChiamata(String dpFile) {
        String body;
        String ragioneSocialeDelegante = dataPopulation.readDataPopulation("nuovaDelegaPG.yaml").get("ragioneSociale").toString();
        String ragioneSocialeDelegato = dataPopulation.readDataPopulation(dpFile + ".yaml").get("ragioneSociale").toString();
        String codiceFiscaleDelegante = dataPopulation.readDataPopulation("nuovaDelegaPG.yaml").get("codiceFiscale").toString();
        String codiceFiscaleDelegato = dataPopulation.readDataPopulation(dpFile + ".yaml").get("codiceFiscale").toString();
        try {
            String pathIniziale = System.getProperty("user.dir");
            String text = Files.readString(Paths.get(pathIniziale + "/src/test/resources/dataPopulation/bodyChiamataDeleghe.json"));
            JSONObject object = new JSONObject(text);
            // ---------------------//
            Map<String, Object> delegante = new HashMap<>();
            delegante.put("displayName", ragioneSocialeDelegante);
            delegante.put("companyName", ragioneSocialeDelegante);
            delegante.put("fiscalCode", codiceFiscaleDelegante);
            delegante.put("person", false);
            // ---------------------//
            Map<String, Object> delegato = new HashMap<>();
            delegato.put("displayName", ragioneSocialeDelegato);
            delegato.put("companyName", ragioneSocialeDelegato);
            delegato.put("fiscalCode", codiceFiscaleDelegato);
            delegato.put("person", false);
            // ---------------------//
            LocalDate date = LocalDate.now();
            object.put("delegator", delegante);
            object.put("delegate", delegato);
            object.put("datefrom", date);
            object.put("dateto", date.plusDays(1));
            body = object.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return body;
    }

    @And("Nella sezione Deleghe si verifica sia presente una delega accettata per PG {string}")
    public void nellaSezioneDelegheSiVerificaSiaPresenteUnaDelegaAccettataPerPG(String dpFile) {
        BackgroundTest backgroundTest = new BackgroundTest();

        String ragioneSociale = this.dataPopulation.readDataPopulation(dpFile + ".yaml").get("ragioneSociale").toString();
        LeTueDelegheSection deleghePage = new LeTueDelegheSection(this.driver);
        if (!deleghePGPagoPAPage.cercaEsistenzaDelegaPG(ragioneSociale)) {

            aggiuntaDelegaConChiamata(dpFile);
            backgroundTest.accettazioneDelegaSenzaGruppo();
        } else if (deleghePage.controlloPresenzaBottoneAccetta()) {
            backgroundTest.accettazioneDelegaSenzaGruppo();
        }
    }

    @And("Nella pagina Deleghe sezione Deleghe a carico dell'impresa si controlla la presenza di una delega per PG {string}")
    public void nellaPaginaDelegheSezioneDelegheACaricoDellImpresaSiControllaLaPresenzaDiUnaDelegaPerPG(String dpFile) {
        logger.info("Si controlla che ci sia una delega");

        String ragioneSociale = dataPopulation.readDataPopulation(dpFile + ".yaml").get("ragioneSociale").toString();
        if (!deleghePGPagoPAPage.cercaEsistenzaDelegaPG(ragioneSociale)) {
            aggiuntaDelegaConChiamata(dpFile);
        }
    }

    @And("Si clicca sul bottone conferma delega")
    public void siCliccaSulBottoneConfermaDelega() {
        logger.info("Si clicca su conferma del pop-up");

        deleghePGPagoPAPage.clickBottoneConfermaDelega();
        if (this.deleghePGPagoPAPage.verificaEsistenzaErroreCodiceSbagliato()) {
            logger.error("il codice inserito è sbagliato");
            Assert.fail("il codice inserito è sbagliato");
        }
    }

    @And("Si ripristina lo stato iniziale delle deleghe a carico dell impresa {string}")
    public void siRipristinaLoStatoInizialeDelleDelegheACaricoDellImpresa(String dpFile) {
        BackgroundTest backgroundTest = new BackgroundTest();

        backgroundTest.rifiutoDelegaACaricoDellImpresa(dpFile);
    }

    @And("Si accetta la delega con un gruppo")
    public void siAccettaLaDelegaConUnGruppo() {
        BackgroundTest backgroundTest = new BackgroundTest();
        backgroundTest.accettazioneDelegaConGruppo();
    }

    @And("Si inserisce il codice della delega a carico dell impresa nella modale")
    public void siInserisceIlCodiceDellaDelegaACaricoDellImpresaNellaModale() {
        String codeVerification = deleghePGPagoPAPage.getCodiceVerificaDelegaACaricoDellImpresaAPI();
        logger.info(codeVerification);
        deleghePGPagoPAPage.inserimentoCodiceDelegaACaricoDellImpresaAPI(codeVerification);
    }

    @And("Accetta la delega a carico dell impresa ed assegna gruppo")
    public void accettaLaDelegaACaricoDellImpresaEdAssegnaGruppo() {
        BackgroundTest backgroundTest = new BackgroundTest();
        backgroundTest.accettaDelegaACaricoDellImpresaEdAssegnaGruppo();
    }
}
