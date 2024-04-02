package it.pn.frontend.e2e.stepDefinitions.common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.api.personaFisica.RecuperoOTPRecapiti;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class RecapitiTest {

    private final WebDriver driver = Hooks.driver;
    private final BackgroundTest backgroundTest = new BackgroundTest();
    private final Logger logger = LoggerFactory.getLogger("RecapitiTest");
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
    public void nellaPaginaITuoiRecapitiSiPremeSulBottoneDelNumeroDiCellulareDiCortesia(String CTA) {
        logger.info("Si preme sul bottone " + CTA + " del numero di cellulare di cortesia");
        recapitiDestinatarioPage.clickSuBottoneCellulareDiCortesia(CTA);
    }

    @And("Nella pagina I Tuoi Recapiti si preme sul bottone {string} dell'email di cortesia")
    public void nellaPaginaITuoiRecapitiSiPremeSulBottoneDellEmailDiCortesia(String CTA) {
        logger.info("Si preme sul bottone " + CTA + " dell'email di cortesia");
        recapitiDestinatarioPage.clickSuBottoneEmailDiCortesia(CTA);
    }

    @Then("Nella pagina I Tuoi Recapiti si controlla che il numero di cellulare non sia presente")
    public void nellaPaginITuoiRecapitiSiControllaCheIlNumeroDiCellulareNonSiaPresente() {
        logger.info("Si controlla che il numero di cellulare di cortesia sia stato eliminato");
        recapitiDestinatarioPage.checkNumeroDiCellulareNonPresente();
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che ci sia già una Email e si inserisce {string}")
    public void nellaPaginaITuoiRecapitiSiControllaCheCiSiaGiaUnaEmail(String email) {
        logger.info("Si controlla che che ci sia già una Email e se ne inserisce una");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();
        if (!recapitiDestinatarioPage.verificaMailAssociata()) {
            backgroundTest.aggiuntaEmailPF();
        } else if (recapitiDestinatarioPage.controlloEmailAssociata(email)) {
            iTuoiRecapitiPage.eliminaEmailEsistente();
            if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi e-mail")) {
                recapitiDestinatarioPage.clickConfermaButtonEliminaPopUp();
            } else {
                recapitiDestinatarioPage.clickSuChiudiPopUp();
                recapitiDestinatarioPage.eliminaNuovaEmail();
                iTuoiRecapitiPage.eliminaEmailEsistente();
                recapitiDestinatarioPage.waitLoadPopUpElimina();
                recapitiDestinatarioPage.clickConfermaButtonEliminaPopUp();
            }
            backgroundTest.aggiuntaEmailPF();
        }
        WebTool.waitTime(10);
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
        if (!errorMessageRead.contains("Indirizzo e-mail non valido") && !errorMessageRead.contains("Scrivi massimo 254 caratteri")) {
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

    @And("Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request dell'email {string} e viene inserito")
    public void nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPTramiteChiamataRequestDellEmailEVieneInserito(String email) {

        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);

        String startUrl = "http://localhost:8887/";
        String url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + email;
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results) {
            String OTP = recuperoOTPRecapiti.getResponseBody();
            iTuoiRecapitiPage.sendOTP(OTP);
            recapitiDestinatarioPage.confermaButtonClickPopUp();
            if (recapitiDestinatarioPage.waitMessaggioErrore()) {
                logger.error("Il codice OTP inserito è sbagliato");
                Assert.fail("Il codice OTP inserito è sbagliato");
            }
        } else {
            String variabileAmbiente = System.getProperty("environment");
            if (variabileAmbiente.equalsIgnoreCase("test")) {
                startUrl = "http://internal-pn-ec-Appli-L4ZIDSL1OIWQ-1000421895.eu-south-1.elb.amazonaws.com:8080/";
            } else if (variabileAmbiente.equalsIgnoreCase("dev")) {
                startUrl = "http://internal-ecsa-20230409091221502000000003-2047636771.eu-south-1.elb.amazonaws.com:8080/";
            }
            url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + email;
            results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
            if (results) {
                String OTP = recuperoOTPRecapiti.getResponseBody();
                iTuoiRecapitiPage.sendOTP(OTP);
                recapitiDestinatarioPage.confermaButtonClickPopUp();
                if (recapitiDestinatarioPage.waitMessaggioErrore()) {
                    logger.error("Il codice OTP inserito è sbagliato");
                    Assert.fail("Il codice OTP inserito è sbagliato");
                }
            } else {
                logger.error("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
                Assert.fail("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
            }
        }
    }


}
