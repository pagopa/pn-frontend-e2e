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

    @And("Si inserisce i dati personali {string}")
    public void siInserisceIDatiPersonali(String dpFile) throws InterruptedException {
        DataPopulation dataPopulation = new DataPopulation();
        Map<String, Object> utentePG = dataPopulation.readDataPopulation(dpFile + ".yaml");
        String codiceFiscale = utentePG.get("codiceFiscale").toString();
        String name = utentePG.get("name").toString();
        String surname = utentePG.get("familyName").toString();
        String email = utentePG.get("mail").toString();
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

    @And("Si clicca sul bottone annula nel popup")
    public void siCliccaSulBottoneAnnulaNelPopup(){
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
    public void siModificaIlCampoEmailEConfermaEmail(String dpFile){
        DataPopulation dataPopulation = new DataPopulation();
        Map<String, Object> utentePG = dataPopulation.readDataPopulation(dpFile + ".yaml");
        String emailNew = utentePG.get("newMail").toString();
        utentiPGPage.inserisciNuovoEmail(emailNew);
    }

    @And("Nella pagina riepilogativa utenti si clicca sul bottone conferma")
    public void nellaPaginaRiepilogativaUtentiSiCliccaSulBottoneConferma(){
        utentiPGPage.clickConfirm();
    }

    @And("Si visualizza correttamente nuovo email {string}")
    public void siVisualizzaCorrettamenteNuovoEmail(String dpFile) {
        DataPopulation dataPopulation = new DataPopulation();
        Map<String, Object> utentePG = dataPopulation.readDataPopulation(dpFile + ".yaml");
        String emailNew = utentePG.get("newMail").toString();
        utentiPGPage.checkNewEmail(emailNew);
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
    public void siApreDettaglioDellUtenteAppenaCreato(String dpFile){
        DataPopulation dataPopulation = new DataPopulation();
        Map<String, Object> utentePG = dataPopulation.readDataPopulation(dpFile + ".yaml");
        String name = utentePG.get("name").toString().toLowerCase();
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

    @And("Nella pagina utenti si effettua la login tramite credenziali {string}")
    public void nellaPaginaUtentiSiEffettuaLaLoginTramiteCredenziali(String dpFile){
        this.datiPersonaGiuridica = this.dataPopulation.readDataPopulation(dpFile + ".yaml");
        String nome = this.datiPersonaGiuridica.get("user").toString();
        String pwd = this.datiPersonaGiuridica.get("pwd").toString();
        utentiPGPage.loginUtenti(nome,pwd);
    }
}
