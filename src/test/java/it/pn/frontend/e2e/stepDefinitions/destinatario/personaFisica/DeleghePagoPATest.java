package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.model.delegate.DelegatePF;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPF;
import it.pn.frontend.e2e.model.delegate.DelegateResponsePF;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.DeleghePage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.rest.RestDelegation;
import it.pn.frontend.e2e.section.destinatario.personaFisica.LeTueDelegheSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.PopUpRevocaDelegaSection;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Slf4j
public class DeleghePagoPATest {

    private final WebDriver driver = Hooks.driver;
    Map<String, Object> deleghe = new HashMap<>();

    private final LeTueDelegheSection leTueDelegheSection = new LeTueDelegheSection(this.driver);
    private final PopUpRevocaDelegaSection popUpRevocaDelegaSection = new PopUpRevocaDelegaSection(this.driver);
    private final DataPopulation dataPopulation = new DataPopulation();
    private final DeleghePage deleghePage = new DeleghePage(this.driver);
    private final LoginPersonaFisicaPagoPA loginPersonaFisicaPagoPA = new LoginPersonaFisicaPagoPA();
    private final DestinatarioPage destinatarioPage = new DestinatarioPage(this.driver);


    private final RestDelegation restDelegation = RestDelegation.getInstance();

    @When("Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe")
    public void waitDelegheButton() {
        log.info("Si clicca sul bottone deleghe");
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        notifichePFPage.waitESelectDelegheButton();
    }

    @And("Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe")
    public void visualizzaDelegheSection() {
        log.info("Si visualizza la sezione deleghe");

        deleghePage.waitDeleghePage();
    }

    @And("Nella sezione Deleghe click sul bottone aggiungi nuova delega")
    public void nellaSezioneDelegheClickSulBottoneAggiungiNuovaDelega() {
        log.info("Click sul bottone aggiungi nuova delega");

        deleghePage.clickAggiungiDelegaButton();
    }


    @And("Nella sezione Le Tue Deleghe inserire i dati")
    public void nellaSezioneLeTueDelegheInserireIDati(Map<String, String> personaFisica) {
        log.info("Nella sezione Le Tue Deleghe inserire i dati");

        String nome = personaFisica.get("nome");
        String cognome = personaFisica.get("cognome");
        String codiceFiscale = personaFisica.get("codiceFiscale");
        String ente = personaFisica.get("ente");


        leTueDelegheSection.selectPersonaFisicaRadioButton();

        leTueDelegheSection.insertNomeCognome(nome, cognome);
        leTueDelegheSection.inserireCF(codiceFiscale);
        leTueDelegheSection.selectSoloEntiSelezionati();
        leTueDelegheSection.waitLoadPage();
        leTueDelegheSection.selezionaUnEnte(ente);

    }

    @And("Nella sezione Le Tue Deleghe verificare che la data sia corretta")
    public void nellaSezioneLeTueDelegheVerificareCheLaDataSiaCorretta() {
        log.info("Si controlla che la data visualizzata sia corretta");


        if (this.leTueDelegheSection.verificareCheLaDataSiaCorretta()) {
            log.info("La data inserita è corretta");
        } else {
            log.error("La data inserita non è corretta");
            Assert.fail("La data inserita non è corretta");
        }
    }

    @And("Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file {string}")
    public void nellaSezioneLeTueDelegheSalvareIlCodiceVerificaAllInternoDelFile(String dpFile) {
        log.info("Si salva il codice deleghe nel file " + dpFile);

        deleghe = dataPopulation.readDataPopulation(dpFile + ".yaml");


        String codiceVerifica = this.leTueDelegheSection.salvataggioCodiceVerifica();
        deleghe.put("codiceDelega", codiceVerifica);
        dataPopulation.writeDataPopulation(dpFile + ".yaml", deleghe);
    }

    @And("Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe")
    public void nellaSezioneLeTueDelegheClickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe() {

        this.leTueDelegheSection.clickSulBottoneInviaRichiesta();
    }

    @And("Nella sezione Deleghe si visualizza la delega in stato di attesa di conferma")
    public void nellaSezioneDelegheSiVisualizzaLaDelegaInStatoDiAttesaDiConferma() {

        deleghePage.waitDeleghePage();
        deleghePage.controlloCreazioneDelega();
    }

    @And("Nella sezione Deleghe si clicca sul menu della delega")
    public void nellaSezioneDelegheSiCliccaSulMenuDellaDelega(Map<String, String> personaFisica) {
        log.info("Si clicca sul menu della delega");
        String fullName = personaFisica.get("nome") + " " + personaFisica.get("cognome");
        deleghePage.clickMenuDelegato(fullName);
    }

    @And("Nella sezione Deleghe si sceglie l'opzione mostra codice")
    public void nellaSezioneDelegheSiSceglieLOpzioneMostraCodice() {
        log.info("Si clicca su mostra codice");
        this.deleghePage.siSceglieOpzioneMostraCodice();
    }

    @Then("Si clicca sul bottone chiudi")
    public void siCliccaSulBottoneChiudi() {
        log.info("Si clicca sul bottone chiudi");

        deleghePage.siCliccaSulBottoneChiudi();
    }

    @And("Nella sezione Deleghe si sceglie l'opzione revoca")
    public void nellaSezioneDelegheSiSceglieLOpzioneRevoca() {
        log.info("Si clicca sulla opzione revoca delega");

        deleghePage.clickOpzioneRevoca();
    }

    @Then("Si conferma l'azione scegliendo revoca la delega")
    public void siConfermaLAzioneScegliendoRevocaLaDelega() {
        popUpRevocaDelegaSection.clickRevocaLaDelega();
    }

    @And("Nella sezione Le Tue Deleghe click sul bottone Invia richiesta")
    public void nellaSezioneLeTueDelegheClickSulBottoneInviaRichiesta() {
        log.info("Si clicca sul bottone  invia richiesta");
        leTueDelegheSection.clickInviaRichiesta();
    }

    @And("Nella sezione Le Tue Deleghe si visualizza il messaggio di errore")
    public void nellaSezioneLeTueDelegheSiVisualizzaIlMessaggioDiErrore() {
        log.info("Si visualizza il messaggio di errore");
        String actualErrorMessage = this.leTueDelegheSection.messaggioDiErrore().toLowerCase();
        String expectedErrorMessage = "errore inserimento delega";
        Assert.assertEquals("Messeggio di Errore non è uguale a Codice fiscale non valido", expectedErrorMessage, actualErrorMessage);
    }

    @And("Nella sezione Le Tue Deleghe si visualizza il messaggio di errore delega gia aggiunta")
    public void nellaSezioneLeTueDelegheSiVisualizzaIlMessaggioDiErroreDelegaGiaAggiunta() {
        log.info("Si visualizza il messaggio di errore delega gia aggiunta");
        this.leTueDelegheSection.messaggioDiErroreDelegaPresente();
    }

    @And("Si verifica sia presente una delega nella sezione Deleghe a Tuo Carico")
    public void siVerificaSiaPresenteUnaDelegaNellaSezioneDelegheATuoCarico(Map<String, String> datiPersonaFisica) {
        log.info("Si controlla che ci sia almeno una delega");

        String nome = datiPersonaFisica.get("nome");
        String cognome = datiPersonaFisica.get("cognome");

        BackgroundTest backgroundTest = new BackgroundTest();
        deleghePage.vaiInFondoAllaPagina();
        boolean esistenzaDelega = deleghePage.siVisualizzaUnaDelegaConNomeDelegato(nome, cognome);
        String stato = "";
        if (esistenzaDelega && !this.leTueDelegheSection.controlloPresenzaBottoneAccetta()) {
            stato = deleghePage.vericaStatoDelega();
        }
        String PF = "personaFisica";
        if (!esistenzaDelega) {
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

            backgroundTest.loginPF(PF);
            backgroundTest.aggiuntaNuovaDelegaPF();
            backgroundTest.logoutPF();
            backgroundTest.loginPF("delegatoPF");
        }
    }

    @And("Si sceglie opzione accetta")
    public void siSceglieOpzioneAccetta() {
        log.info("Si sceglie l'opzione accetta");
        this.leTueDelegheSection.clickOpzioneAccetta();
    }

    @And("Si inserisce il codice delega nel pop-up {string}")
    public void siInserisceIlCodiceDelegaNelPopUp(String dpFile) {
        log.info("Si inserisce il codice per accettare la delega");
        this.leTueDelegheSection.waitPopUpLoad();
        Map<String, Object> destinatari = dataPopulation.readDataPopulation(dpFile + ".yaml");
        this.leTueDelegheSection.inserireCodiceDelega(destinatari.get("codiceDelega").toString());
    }

    @And("Si inserisce il codice errato delega nel pop-up {string}")
    public void siInserisceIlCodiceErratoDelegaNelPopUp(String codice) {
        log.info("Si inserisce il codice per accettare la delega");
        this.leTueDelegheSection.waitPopUpLoad();
        this.leTueDelegheSection.inserireCodiceDelega(codice);
    }

    @And("Si inserisce il codice delega nel pop-up OTP {string}")
    public void siInserisceIlCodiceDelegaNelPopUpOTP(String data) {
        log.info("Si inserisce il codice per accettare la delega");
        String verificationCode = "";
        leTueDelegheSection.waitPopUpLoad();
        if (data.equalsIgnoreCase("corretto")) {
            verificationCode = System.getProperty("verificationCode");
        }else {
            verificationCode = "54321";
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
    public void creaInBackgroundUnaDelegaPerPersonaFisica(Map<String, String> personaFisica) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DelegatePF delegatePF = DelegatePF.builder()
                .displayName(personaFisica.get("displayName"))
                .firstName(personaFisica.get("firstName"))
                .lastName(personaFisica.get("lastName"))
                .fiscalCode(personaFisica.get("fiscalCode"))
                .person(Boolean.parseBoolean(personaFisica.get("person"))).build();
        DelegateRequestPF delegateRequestPF = DelegateRequestPF.builder()
                .dateto(date)
                .delegate(delegatePF)
                .visibilityIds(new ArrayList<String>())
                .verificationCode("12345")
                .build();
        String tokenExchange = loginPersonaFisicaPagoPA.getTokenExchangePFFromFile(personaFisica.get("accessoCome"));
        DelegateResponsePF response = restDelegation.addDelegationPF(delegateRequestPF, tokenExchange);
        if (response != null) {
            System.setProperty("mandateId", response.getMandateId());
            System.setProperty("verificationCode", response.getVerificationCode());
        }
        driver.navigate().refresh();
    }

    @And("Si clicca sul bottone Accetta")
    public void siCliccaSulBottoneAccetta() {
        log.info("Nel pop-up si clicca sul bottone accetta");

        this.leTueDelegheSection.clickAccettaButton();
        if (this.leTueDelegheSection.verificaEsistenzaErroreCodiceSbagliato()) {
            Assert.assertEquals("Il codice inserito è sbagliato",
                    "Il codice è sbagliato", this.leTueDelegheSection.getTextCodiceSbagliato());
        }
    }

    @And("Si vefifica il messaggio di codice sbagliato")
    public void siVerificaIlMessaggioDiCodiceSbagliato(){
        if (leTueDelegheSection.verificaEsistenzaErroreCodiceSbagliato()) {
            log.info("Il messaggio di codice sbagliato è visualizzata");
        }else{
            log.error("Il messaggio di codice sbagliato non è visualizzata");
            Assert.fail("Il messaggio di codice sbagliato non è visualizzata");
        }

    }
    @And("Si clicca sul bottone indietro popup")
    public void siCliccaSulBottoneIndietroPopUp(){
        leTueDelegheSection.clickIndietroButton();
    }


    @And("Si controlla che la delega ha lo stato Attiva {string}")
    public void siControllaCheLaDelegaHaLoStatoAttiva(String dpFile) {
        log.info("Si controlla che la delega abbia lo stato Attiva");
        deleghe = dataPopulation.readDataPopulation(dpFile + ".yaml");
        leTueDelegheSection.controlloStatoAttiva(deleghe.get("name").toString(), deleghe.get("familyName").toString());
    }

    @And("Si controlla che la delega ha lo stato Attiva")
    public void siControllaCheLaDelegaHaLoStatoAttiva(Map<String,String> data) {
        log.info("Si controlla che la delega abbia lo stato Attiva");
        leTueDelegheSection.controlloStatoAttiva(data.get("firstName"), data.get("lastName"));
    }

    @And("Si controlla che la delega è ancora presente {string}")
    public void siControllaCheLaDelegaèAncoraPresente(String dpFile) {
        log.info("Si controlla che la delega abbia lo stato Attiva");
        this.deleghe = this.dataPopulation.readDataPopulation(dpFile + ".yaml");
        this.leTueDelegheSection.controlloEsistenzaDelega(deleghe.get("name").toString(), deleghe.get("familyName").toString());
    }

    @And("Nella sezione Deleghe si visualizza il titolo")
    public void siVisualizzaIlTitolo() {

        if (this.leTueDelegheSection.siVisualizzaIlTitolo()) {
            log.info("Il titolo della sezione Deleghe si visualizza correttamente");
        } else {
            log.error("Il titolo della sezione Deleghe NON si visualizza correttamente");
            Assert.fail("Il titolo della sezione Deleghe NON si visualizza correttamente");
        }
    }

    @And("Nella sezione Deleghe si visualizza il sottotitolo")
    public void siVisualizzaIlSottotitolo() {
        if (this.leTueDelegheSection.siVisualizzaIlSottotitolo()) {
            log.info("Il sottotitolo della sezione Deleghe si visualizza correttamente");
        } else {
            log.error("Il sottotitolo della sezione Deleghe NON si visualizza correttamente");
            Assert.fail("Il sottotitolo della sezione Deleghe NON si visualizza correttamente");
        }
    }

    @And("Nella sezione Deleghe si visualizza il bottone aggiungi una delega")
    public void siVisualizzaIlBottoneAggiungiUnaDelega() {
        if (this.leTueDelegheSection.siVisualizzaIlBottoneAggiungiUnaDelega()) {
            log.info("Il bottone aggiungi delega si visualizza correttamente");
        } else {
            log.error("Il bottone aggiungi delega NON si visualizza correttamente");
            Assert.fail("Il bottone aggiungi delega NON si visualizza correttamente");
        }
    }

    @And("Nella sezione Deleghe si visualizzano tutti i campi dell'elenco dei delegati")
    public void siVisualizzanoTuttiICampiDellElencoDeiDelegati() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (this.leTueDelegheSection.siVisualizzaIlNomeDelegato()) {
            log.info("Si visualizza correttamente il nome del delegato");
        } else {
            log.error("NON si visualizza correttamente il nome del delegato");
            Assert.fail("NON si visualizza correttamente il nome del delegato");
        }

        if (this.leTueDelegheSection.siVisualizzaDataInizioDelega()) {
            log.info("Si visualizza correttamente la data di inizio della delega");
        } else {
            log.error("NON si visualizza correttamente la data di inizio della delega");
            Assert.fail("NON Si visualizza correttamente la data di inizio della delega");
        }

        if (this.leTueDelegheSection.siVisualizzaDataFinoDelega()) {
            log.info("Si visualizza correttamente la data di fine delle deleghe");
        } else {
            log.error("NON si visualizza correttamente la data di fine delle deleghe");
            Assert.fail("NON si visualizza correttamente la data di fine delle deleghe");
        }

        if (this.leTueDelegheSection.siVisualizzaPermessiDelega()) {
            log.info("Si visualizza correttamente il permesso della delega");
        } else {
            log.error("NON si visualizza correttamente il permesso della delega");
            Assert.fail("NON si visualizza correttamente il permesso della delega");
        }
    }


    @And("Si controlla che non sia presente una delega con stesso nome")
    public void siControllaCheNonSiaPresenteUnaDelegaConStessoNome(Map<String, String> personaFisica) {
        log.info("Si controlla che non ci sia una delega con lo stesso nome");

        String nome = personaFisica.get("nome");
        String cognome = personaFisica.get("cognome");

        if (deleghePage.cercaEsistenzaDelega(nome, cognome)) {
            log.info("Delega con lo stesso nome trovata");
            deleghePage.clickRevocaButtonOnMenu(nome, cognome);
            this.popUpRevocaDelegaSection.waitLoadPopUpRevocaDelegaSection();
            this.popUpRevocaDelegaSection.clickRevocaLaDelega();
        } else {
            log.info("Delega con lo stesso nome NON trovata");
        }
    }

    @And("Nella pagina Deleghe si clicca sul menu della delega a tuo carico")
    public void nellaPaginaDelegheSiCliccaSulMenuDellaDelega(Map<String,String> personaFisica) {
        log.info("Si clicca sul menu delle delega");
        String nome = personaFisica.get("nome");
        String cognome = personaFisica.get("cognome");
        deleghePage.clickMenuPerRifiuto(nome, cognome);
    }

    @And("Nella pagina Deleghe si sceglie opzione rifiuta")
    public void nellaPaginaDelegheSiSceglieOpzioneRifiuta() {
        log.info("Si sceglie l'opzione rifiuta");
        deleghePage.clickRifiuta();
    }

    @And("Si clicca sul bottone rifiuta all'interno del pop-up")
    public void siCliccaSulBottoneRifiutaAllInternoDelPopUp() {
        log.info("Si clicca sul bottone rifiuta nel pop-up");
        deleghePage.clickRifiutaPopUp();
    }

    @And("Si clicca sul bottone annulla all'interno del pop-up")
    public void siCliccaSulBottoneAnnullaAllInternoDelPopUp() {
        log.info("Si clicca sul bottone annulla nel pop-up");
        deleghePage.clickAnnullaPopUp();
    }

    @And("Si controlla che la delega non sia più presente nella lista {string}")
    public void siControllaCheLaDelegaNonSiaPiuPresenteNellaLista(String dpFile) {
        log.info("Si controlla che la delega non sia più presente nella lista");
        this.deleghe = dataPopulation.readDataPopulation(dpFile + ".yaml");
        deleghePage.waitLoadingSpinner();
        if (!deleghePage.verificaEsistenzaDelega(this.deleghe.get("name").toString(), this.deleghe.get("familyName").toString())) {
            log.info("La delega non è più presente nella lista");
        } else {
            log.error("La delega è ancora presente in lista");
            Assert.fail("La delega è ancora presente in lista");
        }
    }

    @And("Nella sezione Deleghe si verifica sia presente una delega")
    public void nellaSezioneDelegheSiVerificaSiaPresenteUnaDelega(Map<String, String> personaFisica) {
        log.info("Si controlla la presenza di una delega");

        String nome = personaFisica.get("nome");
        String cognome = personaFisica.get("cognome");
        BackgroundTest backgroundTest = new BackgroundTest();

        if (!this.deleghePage.siVisualizzaUnaDelegaConNome(nome, cognome)) {
            backgroundTest.aggiuntaNuovaDelegaPF();
        }
    }

    @And("Nella sezione Deleghe si verifica sia presente una delega accettata")
    public void nellaSezioneDelegheSiVerificaSiaPresenteUnaDelegaAccettata() {
        log.info("Si controlla che ci sia una delega accettata");
        this.deleghe = this.dataPopulation.readDataPopulation("personaFisica.yaml");
        BackgroundTest backgroundTest = new BackgroundTest();
        NotifichePFPage notifichePFPage = new NotifichePFPage(this.driver);
        if (!this.deleghePage.siVisualizzaUnaDelegaConNomeDelegato(this.deleghe.get("name").toString(), this.deleghe.get("familyName").toString())) {
            backgroundTest.loginPF("personaFisica");
            backgroundTest.aggiuntaNuovaDelegaPF();
            backgroundTest.logoutPF();
            backgroundTest.loginPF("delegatoPF");
            backgroundTest.accettazioneDelegaPF();
        } else if (this.leTueDelegheSection.controlloPresenzaBottoneAccetta()) {
            backgroundTest.accettazioneDelegaPF();
        }
        notifichePFPage.clickNotificheButton();
        notifichePFPage.clickLeTueNotificheButton();
    }

    @And("Si verifica sia presente una delega da rifiutare nella sezione Deleghe a Tuo Carico")
    public void siVerificaSiaPresenteUnaDelegaDaRifiutareNellaSezioneDelegheATuoCarico(Map<String,String> personaFisica) {
        log.info("Si controlla che ci sia almeno una delega");

        String nome = personaFisica.get("nome");
        String cognome = personaFisica.get("cognome");
        BackgroundTest backgroundTest = new BackgroundTest();
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
        log.info("Si controlla che non sia più presente una delega");
        WebTool.waitTime(6);
        this.deleghe = dataPopulation.readDataPopulation("delegatoPF.yaml");
        String nome = this.deleghe.get("name").toString();
        String cognome = this.deleghe.get("familyName").toString();
        if (!deleghePage.siVisualizzaUnaDelegaConNome(nome, cognome)) {
            log.info("La delega è stata revocata correttamente");
        } else {
            log.error("La delega è ancora presente in lista");
            Assert.fail("La delega è ancora presente in lista");
        }
    }
    @And("Si annulla azione revoca")
    public void siAnnullaAzioneRevoca() {
        deleghePage.clickAnnullaRevoca();
    }

    @And("Si visualizza correttamente la pagina nuova delega")
    public void siVisualizzaCorrettamenteLaPaginaNuovaDelega() {
        log.info("Si visualizza la sezione Le Tue Deleghe");
        leTueDelegheSection.waitNuovaDelegaSection();
    }

    @And("Si visualizza correttamente la modale mostra codice")
    public void siVisualizzaCorrettamenteLaModaleMostraCodice() {
        deleghePage.checkModaleMostraCodice();
    }

    @And("Si verifica che presente un indicatore numerico in corrispondenza della voce di menù Deleghe")
    public void siVerificaIndicatoreNumericoMenuDeleghe(){
        log.info("Si controlla indicatore numerico");
       leTueDelegheSection.checkIndicatoreNumerico();
    }

    @And("Nella pagina Piattaforma Notifiche persona fisica si clicca sulle notifiche di {string}")
    public void nellaPaginaPiattaformaNotifichePersonaFisicaSiCliccaSulleNotificheDi(String personaFisica) {
        log.info("Nella pagina Piattaforma Notifiche della persona fisica nel menu laterale si clicca sulla voce notifiche di " + personaFisica);
        deleghePage.clickDelegheDelDelegante(personaFisica);
    }
}
