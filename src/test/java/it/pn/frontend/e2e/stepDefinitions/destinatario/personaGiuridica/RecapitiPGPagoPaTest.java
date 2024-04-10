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

    @And("Si conferma eliminazione nel pop up")
    public void siConfermaEliminazioneNelPopUp() {
        if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi e-mail")) {
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
}
