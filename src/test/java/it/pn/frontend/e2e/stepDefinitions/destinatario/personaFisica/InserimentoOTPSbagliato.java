package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class InserimentoOTPSbagliato {
    private static final Logger logger = LoggerFactory.getLogger("InserimentoOTPSbagliato");
    private final WebDriver driver = Hooks.driver;

    private final RecapitiDestinatarioPage recapitiDestinatarioPage = new RecapitiDestinatarioPage(this.driver);

    @When("Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti")
    public void ITuoiRecapitiButtonClick(){
        logger.info("Si cerca di cliccare il bottone I Tuoi Recapiti");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(driver);
        iTuoiRecapitiPage.ITuoiRecapitiButtonClick();
    }

    @And("Si visualizza correttamente la pagina I Tuoi Recapiti")
    public void siVisualizzaCorrettamenteLaPaginaITuoiRecapiti() {
        logger.info("Si controlla che si visualizza correttamente la pagina I Tuoi Recapiti");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce la PEC del persona fisica {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLaPECDelDestinatario(String dpFile) {
        logger.info("Si inserisce la email PEC");

        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> personaFisica = dataPopulation.readDataPopulation(dpFile +".yaml");
        String emailPEC = personaFisica.get("emailPec").toString();

        recapitiDestinatarioPage.insertEmailPEC(emailPEC);
    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone conferma")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneConferma() {
        logger.info("Si cerca di cliccare sul bottone conferma");

        recapitiDestinatarioPage.confermaButtonClick();
    }

    @And("Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP() {
        logger.info("Si visualizza correttamente il pop-up di inserimento OTP");

        recapitiDestinatarioPage.waitLoadPopUp();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceOTPSbagliato(String otp) {
        logger.info("Si inserisce l'otp sbagliato");

        recapitiDestinatarioPage.sendOTP(otp);
    }

    @And("Si visualizza correttamente il messaggio di errore")
    public void siVisualizzaCorrettamenteIlMessaggioDiErrore() {
        logger.info("Si controlla che il messaggio di errore sia visibile");

        recapitiDestinatarioPage.waitMessaggioErrore();
    }

    @And("Cliccare sul bottone Annulla")
    public void cliccareSulBottoneAnnulla() {
        logger.info("Si clicca sul bottone Annulla");

        recapitiDestinatarioPage.annullaButtonClick();
    }


    @And("Nella pagina I Tuoi Recapiti clicca sul bottone conferma")
    public void nellaPaginaITuoiRecapitiCliccaSulBottoneConferma() {
        logger.info("Si cerca di cliccare sul bottone conferma");

        recapitiDestinatarioPage.confermaButtonClickPopUp();
    }
}
