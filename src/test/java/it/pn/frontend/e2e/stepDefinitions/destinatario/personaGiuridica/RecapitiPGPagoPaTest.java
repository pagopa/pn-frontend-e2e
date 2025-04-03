package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.config.DataPopulationConfig;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.RecapitiPGPage;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class RecapitiPGPagoPaTest extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("RecapitiPGPagoPaTest");


    @Autowired
    private  DataPopulation dataPopulation;

    private RecapitiPGPage recapitiPGPage;

    private RecapitiDestinatarioPage recapitiDestinatarioPage;

    private ITuoiRecapitiPage iTuoiRecapitiPage;

    @Autowired
    @Lazy
    private BackgroundTest backgroundTest;

    private  WebTool webTool;
    @Autowired
    private DataPopulationConfig dataPopulationConfig;

    @PostConstruct
    public void init(){
        logger.info("INIT TEST...: ");
        webTool = new WebTool(driver);
        recapitiPGPage = new RecapitiPGPage(driver);
        recapitiDestinatarioPage = new RecapitiDestinatarioPage(driver);
        iTuoiRecapitiPage = new ITuoiRecapitiPage(driver);
    }


    @And("Si visualizza correttamente la pagina Recapiti persona giuridica")
    public void siVisualizzaRecapitiPagePersonaGiuridica(){
        logger.info("Si visualizza correttamente la pagina Recapiti persona giuridica");

        recapitiPGPage.waitLoadRecapitiPage();
    }

    @And("Nella pagina Recapiti si inserisce la PEC del persona giuridica")
    public void nellaPaginaITuoiRecapitiSiInserisceLaPECDelPersonaGiuridica() {
        logger.info("Si cerca di inserire la email pec");
        recapitiDestinatarioPage.insertEmailPEC(dataPopulationConfig.getPersonaGiuridica().getEmailPec());
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

    @And("Nella pagina I Tuoi Recapiti si inserisce l'email del PG e clicca sul bottone avvisami via email")
    public void nellaPaginaITuoiRecapitiSiInserisceLEmailDelPGECliccaSulBottoneAvvisamiViaEmail() {
        logger.info("Si inserisce l'email del PG e si clicca sul bottone avvisami via email");
        recapitiDestinatarioPage.insertEmail(dataPopulationConfig.getPersonaGiuridica().getEmailPec());
        recapitiDestinatarioPage.clickAvvisami();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il numero di telefono del PG e clicca sul bottone avvisami via SMS")
    public void nellaPaginaITuoiRecapitiSiInserisceIlNumeroDiTelefonoDelPGECliccaSulBottoneAvvisamiViaSMS() {
        logger.info("Si inserisce l'email del PG e clicca sul bottone avvisami via numero telefonico");
        //personaGiuridica
        recapitiDestinatarioPage.insertPhone(dataPopulationConfig.getPersonaGiuridica().getCellulare());
        recapitiDestinatarioPage.clickAvvisamiSMS();
    }

    @Then("Si visualizzano correttamente tutti gli elementi della sezione altri recapiti")
    public void siVisualizzanoCorrettamenteTuttiGliElementiDellaSezioneAltriRecapiti() {
        logger.info("Si visualizzano correttamente tutti gli elementi della sezione altri recapiti");
        webTool.waitTime(10);
        driver.navigate().refresh();
        recapitiDestinatarioPage.visualizzazioneCampiSezioneAltriRecapiti();
    }

    @And("Nella pagina I Tuoi Recapiti di PG, si controlla che ci sia già una pec")
    public void nellaPaginaITuoiRecapitiDiPgSiControllaCheCiSiaGiaUnaPec() {
        logger.info("Si controlla la presenza di una pec");
        String pec = dataPopulationConfig.getPersonaGiuridica().getEmailPec();
        if (!recapitiDestinatarioPage.siVisualizzaPecInserita()) {
            backgroundTest.aggiungiPECPG();
        } else if (!recapitiDestinatarioPage.siControllaPECModificata(pec)) {
            recapitiDestinatarioPage.eliminaPecEsistente();
            backgroundTest.aggiungiPECPG();
        }
    }

    @And("Si clicca su elimina email")
    public void siCliccaSuEliminaEmail() {
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
        backgroundTest.aggiungiPecSezioneGiaAssociati();
    }

    @And("Nella pagina I Tuoi Recapiti si visualizza il pop up di disclaimer")
    public void nellaPaginaITuoiRecapitiSiVisualizzaIlPopUpDiDisclaimer() {
        logger.info("Si controlla il disclaimer per il cambio dell'email di cortesia");
        recapitiDestinatarioPage.confermaButtonEliminaClick();
    }

      @And("Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione altri recapiti persona giuridica {string}")
      public void siVisualizzaAltriRecapitiPagePersonaGiuridca(String textboxId){
    recapitiDestinatarioPage.visualizzazioneSezioneAltriRecapitiPG(textboxId);
}
    @And("Nella sezione altri recapiti si seleziona il tipo di indirizzo PG scegliendo {string}")
    public void selezionaIlTipoDiIndirizzo(String tipoIndirizzo){
        logger.info("Si seleziona il tipo di indirizzo digitale");
        if (tipoIndirizzo.equalsIgnoreCase("pec"))
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
           Assertions.fail("Il messaggio di errore OTP popup non è visibile");
       }
    }

    @When("Click Inizia")
    public void clickInizia() {
        recapitiDestinatarioPage.clickInizia();
    }

    @And("Click Attiva")
    public void clickAttiva() {
        recapitiDestinatarioPage.clickAttiva();
    }

    @And("Click Non ora")
    public void clickNonOra() {
        recapitiDestinatarioPage.clickNonOra();
    }

    @And("Click Lo Faro piu tardi")
    public void clickLoFaroPiuTardi() {
        recapitiDestinatarioPage.clickLoFaroPiuTardiOrConfermaModificaRecapito();
    }

    @And("Click Torna ai tuoi recapiti")
    public void clickTornaAiTuoiRecapiti() {
        recapitiDestinatarioPage.clickTornaAiTuoiRecapiti();
    }

    @Then("Verifica Attivazione Domicilio digitale")
    public void verificaAttivazioneDomicilioDigitaleDellaTuaImpresa() {
        recapitiDestinatarioPage.verificaAttivazioneDomicilioDigitaleDellaTuaImpresa();
    }

    @And("Click Annulla")
    public void clickAnnulla() {
        recapitiDestinatarioPage.clickAnnulla();
    }

    @Then("Verifica Da Attivare Domicilio digitale")
    public void verificaDaAttivareDomicilioDigitale() {
        recapitiDestinatarioPage.verificaDaAttivareDomicilioDigitale();
    }

    @And("Click Bottone {string}")
    public void cliccaBottone(String testo) {
        recapitiDestinatarioPage.cliccaBottone(testo);
    }

    @And("Click Bottone Esci PG")
    public void cliccaBottoneEsciPG() {
        recapitiDestinatarioPage.cliccaBottoneEsciPG();
    }

    @And("Verifica Pagina {string}")
    public void verificaPagina(String testo) {
        recapitiDestinatarioPage.verificaPagina(testo);
    }

    @And("Click Inserisci Email Pop-Up")
    public void clickInserisciEmailPopUp() {
        recapitiDestinatarioPage.clickInserisciEmailPopUp();
    }

    @When("Click Modifica Email")
    public void clickModificaEmail() {
        recapitiDestinatarioPage.clickSuModifica();
    }

    @And("Si inserisce la nuova Email del PG e clicca su Conferma")
    public void siInserisceLaNuovaEmailDelPGECliccaSuConferma() {
        iTuoiRecapitiPage.cancellaTesto();
        iTuoiRecapitiPage.insertEmail(dataPopulationConfig.getPersonaGiuridica().getEmail());
        iTuoiRecapitiPage.clickConfermaEmail();
    }

    @And("Click Insirisci Pec")
    public void clickInsirisciPec() {
        iTuoiRecapitiPage.clickInsirisciPec();
    }

    @And("Click Bottone Notifiche dell Impresa")
    public void clickBottoneNotificheDellImpresa() {
        iTuoiRecapitiPage.clickBottoneNotificheDellImpresa();
    }

    @And("Click Bottone Conferma per modifica PEC")
    public void clickBottoneConfermaPerModificaPEC() {
        iTuoiRecapitiPage.clickBottoneConfermaPerModificaPEC();
    }

    @And("Click Bottone Conferma in Trasferisci il domicilio digitale su una PEC")
    public void clickBottoneConfermaInTrasferisciIlDomicilioDigitaleSuUnaPEC() {
        recapitiDestinatarioPage.clickConferma();
    }


    @Then("Verifica Da Attivare Email")
    public void verificaDaAttivareEmail() {
        recapitiDestinatarioPage.verificaDaAttivareEmail();
    }

    @And("Click Bottone Conferma email")
    public void clickBottoneConfermaEmail() {
        recapitiDestinatarioPage.clickSuConfermaElimina();
    }

    @And("Click Menu Ente Mittente Inserimento ente {string}")
    public void clickMenuEnteMittenteInseriemntoEnte(String ente) {
        recapitiDestinatarioPage.clickMenuEnteMittenteInseriemntoEnte(ente);
    }
    @And("Click Menu Ente Mittente Inserimento ente")
    public void clickMenuEnteMittenteInseriemntoEnte() {
        recapitiDestinatarioPage.clickMenuEnteMittenteInseriemntoEnte();
    }

    @And("Inserisci Pec in Personalizza il tuo domicilio digitale per ente {string}")
    public void inserisciPecInPersonalizzaIlTuoDomicilioDigitalePerEnte(String pecOrEmail) {
        recapitiDestinatarioPage.inserisciPecInPersonalizzaIlTuoDomicilioDigitalePerEnte(pecOrEmail);
    }

    @And("Verifica ed Elimina personalizzati per ente")
    public void verificaEdEliminaPersonalizzatiPerEnte() {
        recapitiDestinatarioPage.verificaEdEliminaPersonalizzatiPerEnte();
    }

    @And("Click Modifica personalizzati per ente")
    public void clickModificaPersonalizzatiPerEnte() {
        recapitiDestinatarioPage.clickModificaPersonalizzatiPerEnte();
    }
    @And("Click Modifica personalizzati per ente OFF")
    public void clickModificaPersonalizzatiPerEnteOff() {
        recapitiDestinatarioPage.clickModificaPersonalizzatiPerEnteOFF();
    }

    @And("Click Elimina personalizzati per ente")
    public void clickEliminaPersonalizzatiPerEnte() {
        recapitiDestinatarioPage.clickEliminaPersonalizzatiPerEnte();
    }


    @And("Modifica Pec personalizzati per Ente e conferma {string}")
    public void modificaPecPersonalizzatiPerEnteEConferma(String pec) {
        recapitiDestinatarioPage.modificaPecPersonalizzatiPerEnteEConferma(pec);
    }

    @And("Seleziona Tipologia {string}")
    public void selezionaTipologia(String tipologia) {
        recapitiDestinatarioPage.selezionaTipologia(tipologia);
    }

    @And("Verifica presenza messaggio")
    public void verificaPresenzaMessaggio() {
        recapitiDestinatarioPage.verificaPresenzaMessaggio();
    }

    @And("Verifica Assenza Sezione Personalizzati Per Ente")
    public void verificaAssenzaSezionePersonalizzatiPerEnte() {
        recapitiDestinatarioPage.verificaAssenzaSezionePersonalizzatiPerEnte();
    }

    @And("Click Bottone Conferma Personalizza il tuo domicilio digitale per ente")
    public void clickBottoneConfermaPersonalizzaIlTuoDomicilioDigitalePerEnte() {
        recapitiDestinatarioPage.clickConferma();
    }

    @When("Click Bottone Disattiva In domicilio digitale {string}")
    public void clickBottoneDisattivaInDomicilioDigitale(String testo) {
        recapitiDestinatarioPage.clickBottoneDisattivaInDomicilioDigitale(testo);
    }

    @And("Verifica e Disattiva Personalizzati per Ente")
    public void verificaEDisattivaPersonalizzatiPerEnte() {
        recapitiDestinatarioPage.verificaEDisattivaPersonalizzatiPerEnte();
    }

    @And("Click Bottone Conferma Modifica Recapito")
    public void clickBottoneConfermaModificaRecapito() {
        recapitiDestinatarioPage.clickLoFaroPiuTardiOrConfermaModificaRecapito();
    }

    @And("Verifica e Disattiva domicilio digitale")
    public void verificaEDisattivaDomicilioDigitale() {
        recapitiDestinatarioPage.verificaEDisattivaDomicilioDigitale();
    }

    @And("Verifica e Disattiva app IO")
    public void verificaEDisattivaAppIO() {
        recapitiDestinatarioPage.verificaEDisattivaAppIO();

    }

    @And("Verifica e Disattiva email")
    public void verificaEDisattivaEmail() {
        recapitiDestinatarioPage.verificaEDisattivaEmail();
    }


    @And("Verifica e Disattiva cellulare")
    public void verificaEDisattivaCellulare() {
        recapitiDestinatarioPage.verificaEDisattivaCellulare();
    }

    @When("Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale")
    public void clickBottoneIndietroTrasferisciPersonalizzaIlDomicilioDigitale() {
        recapitiDestinatarioPage.clickBottoneIndietroTrasferisciPersonalizzaIlDomicilioDigitale();
    }

//    @And("Verifica e Disattiva {string}")
//    public void verificaAndOrDisattiva(String testo) {
//        recapitiDestinatarioPage.verificaAndOrDisattiva(testo);
//    }

}