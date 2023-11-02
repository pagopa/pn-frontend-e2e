package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class InserimentoOTPSbagliato {
    private static final Logger logger = LoggerFactory.getLogger("InserimentoOTPSbagliato");
    private final WebDriver driver = Hooks.driver;

    private final RecapitiDestinatarioPage recapitiDestinatarioPage = new RecapitiDestinatarioPage(this.driver);

    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;

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
        String url = "https://webapi.test.notifichedigitali.it/address-book/v1/digital-address";
        recapitiDestinatarioPage.waitLoadPopUp();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (verificaChiamataEmail(url)){
            logger.info("La chiamata per inviare l'otp è stata effettuata");
        }else {
            logger.error("La chiamata per inviare l'otp NON è stata effettuata");
            Assert.fail("La chiamata per inviare l'otp NON è stata effettuata");
        }
    }

    private boolean verificaChiamataEmail(String url) {
        for (NetWorkInfo info: netWorkInfos) {
            if (info.getRequestUrl().contains(url) && info.getResponseStatus().equals("200")){
                return true;
            }
        }
        return false;
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

    @And("Nella pagina I Tuoi Recapiti si inserisce la PEC errata {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLaPECErrata(String emailPec) {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.insertEmailPEC(emailPec);
    }

    @Then("Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di pec errata")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlMessaggioDiPecErrata() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        String errorMessageRead = iTuoiRecapitiPage.getPecErrorMessage();
        Assert.assertEquals("messagio di errore letto : '" + errorMessageRead + "' non è uguale a : Indirizzo PEC non valido", "Indirizzo PEC non valido", errorMessageRead);
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che il tasto conferma sia bloccato")
    public void nellaPaginaITuoiRecapitiSiControllaCheIlTastoConfermaSiaBloccato() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        Assert.assertTrue("il buttone Conferma non è disabilitato",iTuoiRecapitiPage.verificaButtoneConfermaDisabilitato());
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce l'email errata {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLEmailErrata(String emailErrata) {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.insertEmail(emailErrata);
    }

    @Then("Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio email errata")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlMessaggioEmailErrata() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        String errorMessageRead = iTuoiRecapitiPage.getEmailErrorMessage();
        Assert.assertEquals("messagio di errore letto : '" + errorMessageRead + "' non è uguale a : Indirizzo e-mail non valido", "Indirizzo e-mail non valido", errorMessageRead);
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che il tasto avvisami via email sia bloccato")
    public void nellaPaginaITuoiRecapitiSiControllaCheIlTastoAvvisamiViaEmailSiaBloccato() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        Assert.assertTrue("il buttone avvisami via email non è disabilitato",iTuoiRecapitiPage.avvisamiViaEmailIsDisabled());
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce l'email del PF {string} e clicca sul bottone avvisami via email")
    public void nellaPaginaITuoiRecapitiSiInserisceLEmailDelPFECliccaSulBottoneAvvisamiViaEmail(String dpFile) {
        logger.info("Si inserisce la email");

        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> personaFisica = dataPopulation.readDataPopulation(dpFile +".yaml");
        String email = personaFisica.get("email").toString();

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.insertEmail(email);
        iTuoiRecapitiPage.clickAvvisamiViaEmail();
    }

    @And("Si visualizza correttamente il pop-up e si clicca su conferma")
    public void siVisualizzaCorrettamenteIlPopUpESiCliccaSuConferma() {
        logger.info("click popup conferma email");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        Assert.assertFalse("il popup Conferma email non si visualizza",iTuoiRecapitiPage.verificaPopupConfirmaEmail());

        iTuoiRecapitiPage.clickHoCapitoCheckBoxPopup();
        iTuoiRecapitiPage.confirmaEmailPopup();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il numero di telefono del PF {string} e clicca sul bottone avvisami via SMS")
    public void nellaPaginaITuoiRecapitiSiInserisceIlNumeroDiTelefonoDelPF(String dpFile) {

        logger.info("Si inserisce il numero di telefono PF");

        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> personaFisica = dataPopulation.readDataPopulation(dpFile +".yaml");
        String phoneNumber = personaFisica.get("telefono").toString();

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.insertTelephoneNumber(phoneNumber);
        iTuoiRecapitiPage.clickAvvisamiViaSMS();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il numero di telefono errato {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceIlNumeroDiTelefonoErrato(String numeroErrato) {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.insertTelephoneNumber(numeroErrato);
         }

    @Then("Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di numero di telefono errato")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlMessaggioDiNumeroDiTelefonoErrato() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        String errorMessageRead = iTuoiRecapitiPage.getPhoneErrorMessage();
        Assert.assertEquals("messagio di errore letto : '" + errorMessageRead + "' non è uguale a : Numero di cellulare non valido", "Numero di cellulare non valido", errorMessageRead);

    }

    @And("Nella pagina I Tuoi Recapiti si controlla che il tasto avvisami via sms sia bloccato")
    public void nellaPaginaITuoiRecapitiSiControllaCheIlTastoAvvisamiViaSmsSiaBloccato() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        Assert.assertTrue("il buttone avvisami via SMS non è disabilitato",iTuoiRecapitiPage.avvisamiViaSMSIsDisabled());
    }
}
