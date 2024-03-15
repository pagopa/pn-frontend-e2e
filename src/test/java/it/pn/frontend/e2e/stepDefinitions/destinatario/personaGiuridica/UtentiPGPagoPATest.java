package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.UtentiPGPage;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class UtentiPGPagoPATest {
    private final Logger logger = LoggerFactory.getLogger("UtentiPGPagoPATest");
    private final WebDriver driver = Hooks.driver;

    private final UtentiPGPage utentiPGPage = new UtentiPGPage(this.driver);
    private Map<String, Object> datiPersonaGiuridica = new HashMap<>();
    private final DataPopulation dataPopulation = new DataPopulation();


    @And("Si visualizza correttamente la pagina utenti")
    public void siVisualizzaCorrettamenteLaPAginaUtenti() {
        utentiPGPage.waitLoadUtentiPage();
    }

    @And("Si clicca sul bottone aggiungi utente")
    public void siCliccaSulBottoneAggiungiUtente(){
        utentiPGPage.clickAddUserButton();
    }

    @And("Si visualizza correttamente la pagina aggiungi nuovo utente")
    public void siVisualizzaCorrettamenteLaPaginaAggiungiNuovoUtente(){
        utentiPGPage.waitLoadAggiungiUtentePage();
    }

    @And("Si inserisce i dati personali")
    public void siInserisceIDatiPersonali(Map<String, String> nuovoUtente) throws InterruptedException {
        String codiceFiscale = nuovoUtente.get("codiceFiscale");
        String name = nuovoUtente.get("name");
        String surname = nuovoUtente.get("familyName");
        String email = nuovoUtente.get("email");
        utentiPGPage.insertData(codiceFiscale,name,surname,email);
    }

    @And("Si clicca su seleziona il prodotto {string}")
    public void siCliccaSuSelezionaIlProdotto(String product){
        utentiPGPage.selectProduct(product);
    }

    @And("Si clicca su opzione amministratore e clicca bottone continua")
    public void siCliccaSuOpzioneAmministratoreECliccaBottoneContinua(){
        utentiPGPage.selectRole();
    }

    @And("Si clicca sul bottone annulla nel popup")
    public void siCliccaSulBottoneAnnullaNelPopup(){
        utentiPGPage.clickRejectPopup();
    }

    @And("Si clicca sul bottone continua e assegna e si visualizza messaggio di successo")
    public void siCliccaSulBottoneContinuaEAssegna(){
        utentiPGPage.clickContinueAndAssign();
        utentiPGPage.waitSuccessMessage();
    }

    @And("Si visualizza correttamente la pagina riepilogativa")
    public void siVisualizzaCorrettamenteLaPaginaRiepilogativa(){
        utentiPGPage.waitLoadRecapPage();
    }

    @And("Nella pagina riepilogativa utenti si clicca sul bottone modifica")
    public void nellaPaginaRiepilogativaUtentiSiCliccaSulBottoneModifica(){
        utentiPGPage.clickModifyButton();
        logger.info("Si verifica che campo email e conferma email sono attivi");
        utentiPGPage.checkEmailBoxActive();
    }

    @And("Si modifica il campo email e conferma email {string}")
    public void siModificaIlCampoEmailEConfermaEmail(String mail){;
        utentiPGPage.inserisciNuovoEmail(mail);
    }

    @And("Nella pagina riepilogativa utenti si clicca sul bottone conferma")
    public void nellaPaginaRiepilogativaUtentiSiCliccaSulBottoneConferma(){
        utentiPGPage.clickConfirm();
    }

    @And("Si visualizza correttamente nuovo email {string}")
    public void siVisualizzaCorrettamenteNuovoEmail(String mail) {
        utentiPGPage.checkNewEmail(mail);
    }

    @And("Nella pagina riepilogativa utenti si clicca sul bottone rimuovi")
    public void nellaPaginaRiepilogativaUtentiSiCliccaSulBottoneRimuovi(){
        utentiPGPage.clickRemoveButton();
        utentiPGPage.checkRemoveUserPopup();
    }

    @And("Nella pagina riepilogativa utenti si clicca sul bottone annula")
    public void nellaPaginaRiepilogativaUtentiSiCliccaSulBottoneAnnula(){
        utentiPGPage.clickRejectPopup();
    }

    @And("Si clicca sul bottone rimuovi dell popup")
    public void siCliccaSulBottoneRimuoviDellPopup(){
        utentiPGPage.clickRemoveRoleButton();
        utentiPGPage.checkUserDeletedMEssage();
    }

    @And("Si apre dettaglio dell utente appena creato {string}")
    public void siApreDettaglioDellUtenteAppenaCreato(String nameUtente){
        String name = nameUtente.toLowerCase();
        utentiPGPage.getUserDetailsPage(name);
    }
    @When("Nella Pagina Notifiche persona giuridica si clicca su utenti")
    public void nellaPaginaNotifichePersonaGiuridicaSiCliccaSuUtenti() {
        utentiPGPage.clickSezioneUtenti();

    }

    @And("Nella Pagina riepilogativa si clicca su utenti")
    public void nellaPaginaRiepilogativaSiCliccaSuUtenti(){
        utentiPGPage.clickSezioneUtentiDaRiepilogo();
    }

    @And("Nella pagina utenti si effettua la login tramite credenziali")
    public void nellaPaginaUtentiSiEffettuaLaLoginTramiteCredenziali(Map<String, String> nuovoUtente){
        String nome = nuovoUtente.get("user");
        String pwd = nuovoUtente.get("pwd");
        utentiPGPage.loginUtenti(nome,pwd);
    }
}
