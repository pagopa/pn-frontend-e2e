package it.pn.frontend.e2e.stepDefinitions.common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecapitiTest {

    private final String PEC = "PEC";
    private final String emailDiCortesia = "email di cortesia";
    private final String ELIMINA = "Elimina";
    private final Logger logger = LoggerFactory.getLogger("RecapitiTest");
    private final WebDriver driver = Hooks.driver;
    public static String OTP;
    private final RecapitiDestinatarioPage recapitiDestinatarioPage = new RecapitiDestinatarioPage(this.driver);
    private final ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);

    @And("Nella pagina I Tuoi Recapiti si inserisce il numero di telefono {string} e si clicca sul bottone avvisami via SMS")
    public void nellaPaginaITuoiRecapitiSiInserisceIlNumeroDiTelefonoESiCliccaSulBottoneAvvisamiViaSMS(String cellulare) {
        logger.info("Si inserisce l'email del PG e clicca sul bottone avvisami via numero telefonico");
        recapitiDestinatarioPage.insertPhone(cellulare);
        recapitiDestinatarioPage.clickAvvisamiSMS();
    }

    @And("Si annulla eliminazione numero di cellulare")
    public void siAnnullaEliminazioneNUmeroDiCellulare() {
        recapitiDestinatarioPage.checkButtonAnnullaEliminazioneInPopUp();
        recapitiDestinatarioPage.clickButtonAnnullaEliminazioneInPopUp();
    }

    @And("Nella pagina I Tuoi Recapiti si visualizza correttamente il numero di cellulare {string}")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlNumeroDiCellulare(String cellulare) {
        logger.info("Si controlla la presenza e correttezza del numero di cellulare inserito");
        recapitiDestinatarioPage.checkNumeroDiCellulareCorretto(cellulare);
    }

    @And("Nella pagina I Tuoi Recapiti si preme sul bottone {string} del numero di cellulare di cortesia")
    public void nellaPaginaITuoiRecapitiSiPremeSulBottoneDelNumeroDiCellulareDiCortesia(String actionButton) {
        logger.info("Si preme sul bottone " + actionButton + " del numero di cellulare di cortesia");
        recapitiDestinatarioPage.clickSuBottoneCellulareDiCortesia(actionButton);
    }

    @And("Nella pagina I Tuoi Recapiti si preme sul bottone {string} dell'email di cortesia")
    public void nellaPaginaITuoiRecapitiSiPremeSulBottoneDellEmailDiCortesia(String actionButton) {
        logger.info("Si preme sul bottone " + actionButton + " dell'email di cortesia");
        recapitiDestinatarioPage.clickSuBottoneEmailDiCortesia(actionButton);
    }

    @Then("Nella pagina I Tuoi Recapiti si controlla che il numero di cellulare non sia presente")
    public void nellaPaginITuoiRecapitiSiControllaCheIlNumeroDiCellulareNonSiaPresente() {
        logger.info("Si controlla che il numero di cellulare di cortesia sia stato eliminato");
        recapitiDestinatarioPage.checkNumeroDiCellulareNonPresente();
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che non ci sia già una {string} e si inserisce {string}")
    public void nellaPaginaITuoiRecapitiSiControllaCheCiSiaGiaUnaPECESiInserisce(String tipoContatto, String indirizzoMail) {
        logger.info("Si controlla che non ci sia già una " + tipoContatto + " e se ne inserisce una");
        BackgroundTest backgroundTest = new BackgroundTest();
        if (PEC.equalsIgnoreCase(tipoContatto)){
            backgroundTest.checkPECEsistentePerEliminazioneEInserimento(indirizzoMail);
        } else if (emailDiCortesia.equalsIgnoreCase(tipoContatto)){
            backgroundTest.checkEmailDiCortesiaPerEliminazioneEInserimento(indirizzoMail);
        } else {
            logger.error("Errore nella scrittura del tipo di contatto da controllare e inserire");
            Assert.fail("Errore nella scrittura del tipo di contatto da controllare e inserire");
        }
    }

    @Then("Si visualizza il campo email modificabile")
    public void siVisualizzaIlCampoEmailModificabile() {
        logger.info("Si visualizza il campo email modificabile");
        recapitiDestinatarioPage.checkCampoEmailModificabile();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce l'email errata {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLEmailErrata(String emailErrata) {
        recapitiDestinatarioPage.insertEmail(emailErrata);
    }

    @Then("Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio email errata")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlMessaggioEmailErrata() {
        String errorMessageRead = recapitiDestinatarioPage.getEmailErrorMessage();
        if (!errorMessageRead.contains("Indirizzo email non valido") && !errorMessageRead.contains("Scrivi massimo 254 caratteri")) {
            Assert.fail("messaggio di errore letto : '" + errorMessageRead + "' non è uguale a : Indirizzo e-mail non valido o Scrivi massimo 254 caratteri");
        }
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce un email maggiore di {int} caratteri")
    public void nellaPaginaITuoiRecapitiSiInserisceUnEmailMaggioreDiCaratteri(int numeroCaratteri) {
        String email = "test";
        for (int i = 0; i < numeroCaratteri; i++) {
            email += "a";
        }
        recapitiDestinatarioPage.insertEmail(email);
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce un email aggiuntiva maggiore di {int} caratteri")
    public void nellaPaginaITuoiRecapitiSiInserisceUnEmailAggiuntivaMaggioreDiCaratteri(int numeroCaratteri) {
        String email = "test";
        for (int i = 0; i < numeroCaratteri; i++) {
            email += "a";
        }
        recapitiDestinatarioPage.insertEmailAggiuntiva(email);
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce la nuova email {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLaNuovaEmailProvaTestIt(String email) {
        logger.info("Si inserisce la nuova mail e si clicca sul bottone avvisami via email");
        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();
        iTuoiRecapitiPage.cancellaTesto();
        iTuoiRecapitiPage.insertEmail(email);
    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone conferma del disclaimer")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneConfermaDelDisclaimer() {
        logger.info("Si clicca sul bottone di conferma del disclaimer");
        recapitiDestinatarioPage.clickHoCapitoCheckBoxPopup();
        recapitiDestinatarioPage.confermaEmailPopup();
    }

    @And("Si inserisce l'email di cortesia {string} e si clicca sul bottone avvisami via email")
    public void siInserisceLEmailDiCortesiaESiCliccaSulBottoneAvvisamiViaEmail(String email) {
        logger.info("Si inserisce l'email");
        recapitiDestinatarioPage.insertEmail(email);
        recapitiDestinatarioPage.clickAvvisamiViaEmail();
    }

    @And("Si visualizza il pop-up disclaimer si clicca la checkbox e il bottone conferma")
    public void siVisualizzaIlPopUpDisclaimerSiCliccaLaCheckboxEIlBottoneConferma() {
        logger.info("Si visualizza il disclaimer, clicco la checkbox e il bottone conferma");
        recapitiDestinatarioPage.clickHoCapitoCheckBoxPopup();
        recapitiDestinatarioPage.confermaEmailPopup();
    }

    @Then("Si controlla che l'Email inserita sia presente")
    public void siControllaCheLEmailInseritaSiaPresente() {
        logger.info("Si controlla che la Email sia stata inserita correttamente");
        if (!recapitiDestinatarioPage.verificaMailAssociata()) {
            logger.error("Email non è stata inserita correttamente");
            Assert.fail("Email non è stata inserita correttamente");
        }
    }

    @Then("Si visualizza correttamente il messaggio di errore dei tre tentativi")
    public void siVisualizzaCorrettamenteIlMessaggioDiErroreDeiTreTentativi() {
        logger.info("Si verifica la visualizzazione del corretto messaggio di errore");
        recapitiDestinatarioPage.checkMessaggioErroreTreTentativiOTPSbagliato();
        recapitiDestinatarioPage.annullaButtonClick();
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che non ci sia una email di cortesia impostata")
    public void nellaPaginaITuoiRecapitiSiControllaCheNonCiSiaUnaEmailDiCortesiaImpostata() {
        logger.info("Si controlla la presenza di una email di cortesia");
        if(recapitiDestinatarioPage.verificaMailAssociata()){
            logger.info("Email di cortesia trovata, si procede con l'eliminazione");
            recapitiDestinatarioPage.clickSuBottoneEmailDiCortesia(ELIMINA);
            recapitiDestinatarioPage.confermaButtonEliminaClick();
        }
    }

    @And("Si inserisce il codice OTP errato {string} per tre volte e si controlla il messaggio di errore")
    public void siInserisceIlCodiceOTPErratoPerTreVolteESiControllaIlMessaggioDiErrore(String OTP) {
        logger.info("Si inserisce un codice OTP errato per 3 volte e si controlla il messaggio di errore");
        BackgroundTest backgroundTest = new BackgroundTest();
        backgroundTest.inserimentoOTPErratoTreVolteEControlloMessaggio(OTP);
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che non ci sia un numero di cellulare di cortesia impostato")
    public void nellaPaginaITuoiRecapitiSiControllaCheNonCiSiaUnNumeroDiCellulareDiCortesiaImpostato() {
        logger.info("Si controlla la presenza di un numero di cellulare di cortesia");
        if(recapitiDestinatarioPage.verificaNumeroDiCellulareAssociato()) {
            logger.info("Numero di cellulare di cortesia trovato, si procede con l'eliminazione");
            recapitiDestinatarioPage.clickSuBottoneCellulareDiCortesia(ELIMINA);
            recapitiDestinatarioPage.confermaButtonEliminaClick();
        }
    }
}
