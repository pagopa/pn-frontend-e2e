package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.GruppiPGPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.Map;

@Slf4j
public class GruppiPGPagoPATest {

    private final WebDriver driver = Hooks.driver;
    private final GruppiPGPage gruppiPGPage = new GruppiPGPage(this.driver);

    @When("Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone Gruppi")
    public void nellaPaginaPiattaformaNotifichePersonaGiuridicaSiCliccaSulBottoneGruppi() {
        log.info("Si clicca sul bottone Gruppi");
        gruppiPGPage.clickGruppiButton();
    }

    @And("Nella pagina gruppi si effettua la login tramite credenziali")
    public void nellaPaginaUtentiSiEffettuaLaLoginTramiteCredenziali(Map<String, String> nuovoUtente) {
        log.info("Si effettua la login nella pagina di reindirizzamento dei gruppi");
        String nome = nuovoUtente.get("user");
        String pwd = nuovoUtente.get("pwd");
        gruppiPGPage.loginGruppi(nome, pwd);
    }

    @And("Si visualizza correttamente la pagina gruppi")
    public void siVisualizzaCorrettamenteLaPaginaGruppi() {
        log.info("Si attende il corretto caricamento della pagina gruppi");
        gruppiPGPage.waitLoadGruppiPage();
    }

    @And("Si clicca sul bottone Crea gruppo")
    public void siCliccaSulBottoneCreaGruppo() {
        log.info("Si clicca sul bottone crea gruppo");
        gruppiPGPage.clickButtonCreaGruppo();
    }

    @And("Si visualizza correttamente la pagina crea gruppo")
    public void siVisualizzaCorrettamenteLaPaginaCreaGruppo() {
        log.info("Si attende il corretto caricamento della pagina crea nuovo gruppo");
        gruppiPGPage.waitLoadCreaGruppoPage();
    }

    @Then("Si compilano i campi per la creazione di un nuovo gruppo")
    public void siCompilanoICampiPerLaCreazioneDiUnNuovoGruppo(Map<String, String> gruppo) {
        log.info("Si compilano i campi per la creazione di un nuovo gruppo");
        gruppiPGPage.creazioneNuovoGruppo(gruppo);
    }

    @And("Si controlla che il bottone conferma sia ablitato e lo si clicca")
    public void siControllaCheIlBottoneConfermaSiaAbilitatoELoSiClicca() {
        log.info("Si controlla che il bottone conferma sia abilitato e lo si clicca");
        gruppiPGPage.checkButtonConfermaAndClick();
    }

    @And("Si visualizza correttamente la pagina di riepilogo del gruppo {string}")
    public void siVisualizzaCorrettamenteLaPaginaDiRiepilogoDelGruppo(String nomeGruppo) {
        log.info("Si verifica che la pagina di riepilogo del gruppo " + nomeGruppo + " creato sia visualizzata correttamente");
        gruppiPGPage.waitLoadGruppoCreatoPage(nomeGruppo);
    }

    @And("Si ritorna alla pagina principale della sezione gruppi")
    public void siRitornaAllaPaginaPrincipaleDellaSezioneGruppi() {
        log.info("Si ritorna alla pagina principale");
        gruppiPGPage.clickGruppiButtonSchedaGruppi();
    }

    @And("Si clicca sul bottone {string}")
    public void siCliccaSulBottone(String azioneSuGruppi) {
        log.info("Si clicca sul bottone " + azioneSuGruppi);
        gruppiPGPage.clickBottonePaginaDettaglioGruppo(azioneSuGruppi);
    }

    @And("Si controlla che i campi del gruppo sono modificabili")
    public void siControllaCheICampiDelGruppoSonoModificabili() {
        log.info("Si controlla che i campi siano modificabili");
        gruppiPGPage.checkCampiModificabili();
    }

    @And("Si modifica il campo {string} inserendo {string}")
    public void siModificaIlCampoInserendo(String campo, String modifica) {
        log.info("Si modifica il campo " + campo + " inserendo " + modifica);
        gruppiPGPage.siModificaUnCampoDelGruppo(campo, modifica);
    }

    @Then("Si visualizza il popup di conferma con la scritta {string}")
    public void siVisualizzaIlPopupDiConfermaConLaScritta(String testoPopUp) {
        log.info("Si controlla venga visualizzato il popup di conferma con la scritta " + testoPopUp);
        gruppiPGPage.checkPopUpConfermaModifica(testoPopUp);
    }

    @And("Si controlla che le modifiche siano state salvate")
    public void siControllaCheLeModificheSianoStateSalvate() {
        log.info("Si controlla che siano state salvate correttamente le modifiche");
        gruppiPGPage.checkModificheSalvate();
    }

    @And("Si verifica che venga mostrato correttamente il popup di sospensione")
    public void siVerificaCheVengaMostratoCorrettamenteIlPopupDiSospensione() {
        log.info("Si controlla che il popup per la sospensione del gruppo sia mostrato in maniera corretta");
        gruppiPGPage.checkPopUpSospensioneGruppo();
    }

    @And("Nella pagina di descrizione del gruppo si visualizza la voce sospeso")
    public void nellaPaginaDiDescrizioneDelGruppoSiVisualizzaLaVoceSospeso() {
        log.info("Si controlla sia presente la voce sospeso nella pagina di descrizione del gruppo");
        gruppiPGPage.checkVoceSospeso();
    }

    @And("Si verifica che venga mostrato correttamente il popup di riattivazione")
    public void siVerificaCheVengaMostratoCorrettamenteIlPopupDiRiattivazione() {
        log.info("Si verifica che il pop up di riattivazione del  gruppo venga mostrato correttamente");
        gruppiPGPage.checkPopUpRiattivazioneGruppo();
    }

    @And("Si verifica che venga mostrato correttamente il popup di duplicazione")
    public void siVerificaCheVengaMostratoCorrettamenteIlPopupDiDuplicazione() {
        log.info("Si verifica che il pop up di duplicazione del  gruppo venga mostrato correttamente");
        gruppiPGPage.checkPopUpDuplicazioneGruppo();
    }

    @And("Si verifica che la pagina di duplicazione del gruppo si visualizza correttamente")
    public void siVerificaCheLaPaginaDiDuplicazioneDelGruppoSiVisualizzaCorrettamente() {
        log.info("Si verifica che la pagina di duplicazione del gruppo si visualizza correttamente");
        gruppiPGPage.waitLoadDuplicazionePage();
    }

    @And("Si verifica che venga mostrato correttamente il popup di eliminazione")
    public void siVerificaCheVengaMostratoCorrettamenteIlPopupDiEliminazione() {
        log.info("Si verifica che il pop up di eliminazione del  gruppo venga mostrato correttamente");
        gruppiPGPage.checkPopUpEliminazioneGruppo();
    }
}
