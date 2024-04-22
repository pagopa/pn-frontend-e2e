package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.RecapitiPGPage;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

public class RecapitiPGPagoPaTest {
    private final Logger logger = LoggerFactory.getLogger("RecapitiPGPagoPaTest");
    private final WebDriver driver = Hooks.driver;
    DataPopulation dataPopulation = new DataPopulation();
    private final RecapitiPGPage recapitiPGPage = new RecapitiPGPage(this.driver);
    private final RecapitiDestinatarioPage recapitiDestinatarioPage = new RecapitiDestinatarioPage(this.driver);

    @And("Si visualizza correttamente la pagina Recapiti persona giuridica")
    public void siVisualizzaRecapitiPagePersonaGiuridca(){
        logger.info("Si visualizza correttamente la pagina Recapiti persona giuridica");

        recapitiPGPage.waitLoadRecapitiPage();
    }

    @And("Nella pagina Recapiti si inserisce la PEC del persona giuridica {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLaPECDelPersonaGiuridica(String personaGiuridica) {
        logger.info("Si cerca di inserire la email pec");

        Map<String, Object> datiPG = dataPopulation.readDataPopulation(personaGiuridica+".yaml");
        recapitiDestinatarioPage.insertEmailPEC(datiPG.get("emailPec").toString());
    }


    @And("Nella pagina Recapiti persona giuridica si inserisce una PEC sbagliata {string}")
    public void nellaPaginaITuoiRecapitiPersonaGiuridicaSiInserisceUnaPECSbagliata(String emailPec) {
        logger.info("Si cerca di inserire la email pec sbagliata");

        recapitiDestinatarioPage.insertEmailPEC(emailPec);
    }

    @Then("Nella pagina Recapiti persona giuridica si visualizza correttamente il messaggio di errore pec sbagliata")
    public void nellaPaginaITuoiRecapitiPersonaGiuridicaSiVisualizzaCorrettamenteIlMessaggioDiErrorePecSbagliata() {
        logger.info("Si controlla che si vede il messaggio di errore");

        recapitiDestinatarioPage.getPecErrorMessage();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce l'email del PG {string} e clicca sul bottone avvisami via email")
    public void nellaPaginaITuoiRecapitiSiInserisceLEmailDelPGECliccaSulBottoneAvvisamiViaEmail(String personaGiuridica) {
        logger.info("Si inserisce l'email del PG e si clicca sul bottone avvisami via email");

        Map<String, Object> datiPG = dataPopulation.readDataPopulation(personaGiuridica+".yaml");
        recapitiDestinatarioPage.insertEmail(datiPG.get("emailPec").toString());
        recapitiDestinatarioPage.clickAvvisami();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il numero di telefono del PG {string} e clicca sul bottone avvisami via SMS")
    public void nellaPaginaITuoiRecapitiSiInserisceIlNumeroDiTelefonoDelPGECliccaSulBottoneAvvisamiViaSMS(String personaGiuridica) {
        logger.info("Si inserisce l'email del PG e clicca sul bottone avvisami via numero telefonico");

        Map<String, Object> datiPG = dataPopulation.readDataPopulation(personaGiuridica+".yaml");
        recapitiDestinatarioPage.insertPhone(datiPG.get("cellulare").toString());
        recapitiDestinatarioPage.clickAvvisamiSMS();
    }

    @Then("Si visualizzano correttamente tutti gli elementi della sezione altri recapiti")
    public void siVisualizzanoCorrettamenteTuttiGliElementiDellaSezioneAltriRecapiti() {
        logger.info("Si visualizzano correttamente tutti gli elementi della sezione altri recapiti");
        WebTool.waitTime(10);
        this.driver.navigate().refresh();
        recapitiDestinatarioPage.visualizzazioneCampiSezioneAltriRecapiti();
    }

    @And("Nella pagina I Tuoi Recapiti di PG, si controlla che ci sia già una pec")
    public void nellaPaginaITuoiRecapitiDiPgSiControllaCheCiSiaGiaUnaPec() {
        logger.info("Si controlla la presenza di una pec");
        String pec = dataPopulation.readDataPopulation("personaGiuridica.yaml").get("emailPec").toString();
        BackgroundTest backgroundTest = new BackgroundTest();
        if (!recapitiDestinatarioPage.siVisualizzaPecInserita()) {
            backgroundTest.aggiungiPECPG();
        } else if (!recapitiDestinatarioPage.siControllaPECModificata(pec)) {
            recapitiDestinatarioPage.eliminaPecEsistente();
            backgroundTest.aggiungiPECPG();
        }
    }

    @And("Si clicca su elimina email")
    public void siCliccaSuEliminaEmail() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);

        iTuoiRecapitiPage.eliminaEmailEsistente();
    }

    @And("Si annulla eliminazione email")
    public void siAnnullaEliminazioneEmail() {
        recapitiDestinatarioPage.checkButtonAnnullaEliminazioneInPopUp();
        recapitiDestinatarioPage.clickButtonAnnullaEliminazioneInPopUp();
    }

    @And("Si conferma {string} nel pop up")
    public void siConfermaEliminazioneNelPopUp(String contattoCortesia) {
        if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase(contattoCortesia)) {
            recapitiDestinatarioPage.confermaButtonEliminaClick();
        }
    }

    @And("Si controlla presenza email precedentemente salvata {string}")
    public void siControllaPresenzaEmailPrecedentementeSalvata(String email) {
        recapitiDestinatarioPage.checkEmailPrecedentementeSalvata(email);
    }

    @And("Nella sezione altri recapiti si inserisce un recapito")
    public void nellaSezioneAltriRecapitiSiInserisceUnRecapito(){
        BackgroundTest backgroundTest = new BackgroundTest();
        backgroundTest.aggiungiPecSezioneGiaAssociati();
    }

    @And("Nella pagina I Tuoi Recapiti si visualizza il pop up di disclaimer")
    public void nellaPaginaITuoiRecapitiSiVisualizzaIlPopUpDiDisclaimer() {
        logger.info("Si controlla il disclaimer per il cambio dell'email di cortesia");
        recapitiDestinatarioPage.checkDisclaimer();
    }

      @And("Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione altri recapiti persona giuridica {string}")
      public void siVisualizzaAltriRecapitiPagePersonaGiuridca(String textboxId){
    recapitiDestinatarioPage.visualizzazioneSezioneAltriRecapitiPG(textboxId);
}
    @And("Nella sezione altri recapiti si seleziona il tipo di indirizzo PG scegliendo {string}")
    public void selezionaIlTipoDiIndirizzo(String tipoIndirizzo){
        logger.info("Si seleziona il tipo di indirizzo digitale");
        if (tipoIndirizzo.equalsIgnoreCase("PEC"))
            recapitiDestinatarioPage.selezionaTipoPec();
        else if(tipoIndirizzo.equalsIgnoreCase("Email")){
            recapitiDestinatarioPage.selezionaTipoEmail();
        }else{
            recapitiDestinatarioPage.selezionaTipoCelulare();
        }
    }

    @And("Nella sezione altri recapiti si inserisce la email aggiuntiva {string}")
    public void siInserisceEmailAggiuntiva(String email){
        recapitiDestinatarioPage.insertEmailAggiuntiva(email);
    }

    @And("Nella sezione altri recapiti si inserisce la PEC aggiuntiva {string}")
    public void siInseriscePECAggiuntiva(String pec){
        recapitiDestinatarioPage.insertPECAggiuntiva(pec);
    }

    @And("Nella sezione altri recapiti si inserisce la Email aggiuntiva {string}")
    public void siInserisceAggiuntiva(String email){
        recapitiDestinatarioPage.insertPECAggiuntiva(email);
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce un PEC maggiore di {int} caratteri")
    public void nellaPaginaITuoiRecapitiSiInserisceUnPECMaggioreDiCaratteri(int numeroCaratteri) {
        StringBuilder email = new StringBuilder("test");
        email.append("a".repeat(Math.max(0, numeroCaratteri)));
        recapitiDestinatarioPage.insertPECAggiuntiva(email.toString());
    }

    @And("Nella sezione altri recapiti si visualizza il messaggio di errore {string}")
    public void nellaSezioneAltriRecapitiSiVisualizzaIlMessagioDiErrore(String check){
        recapitiDestinatarioPage.checkMessaggioDiErrore(check);
        logger.info("Si visualizza il messaggio di errore");
    }


    @And("Nella sezione altri recapiti si cancella email da textbox {string}")
    public void nellaSezioneAltriRecapitiSiCancellaEmailDaTextbox(String check){
        recapitiDestinatarioPage.clearMailbox(check);
    }

    @And("Nella sezione altri recapiti si clicca sul bottone conferma di popup")
    public void nellaSezioneAltriRecapitiSiCliccaConfermaPopup(){
        recapitiDestinatarioPage.clickConfermaPopupOTP();
    }

    @And("Nella sezione altri recapiti si clicca sul bottone annulla di popup")
    public void nellaSezioneAltriRecapitiSiCliccaAnnullaPopup(){
        recapitiDestinatarioPage.clickAnnullaPopupOTP();
    }
    @And("Nella sezione altri recapiti si visualizza correttamente il messaggio di errore di popup")
    public void nellaSezioneAltriRecapitiSiVisualizzaMessagioDiErrorePopup(){
       if (!recapitiDestinatarioPage.waitErrorMessagePopupOTP()){
           logger.error("Il messaggio di errore OTP popup non è visibile");
           Assert.fail("Il messaggio di errore OTP popup non è visibile");
       }
    }

}