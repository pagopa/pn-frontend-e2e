package it.pn.frontend.e2e.stepDefinitions.common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class RecapitiTest extends BasePage {


    private final Logger logger = LoggerFactory.getLogger(RecapitiTest.class);

    private final String PEC = "PEC";
    private final String emailDiCortesia = "email di cortesia";
    private final String ELIMINA = "Elimina";

    public String OTP;

    private RecapitiDestinatarioPage recapitiDestinatarioPage;

    private ITuoiRecapitiPage iTuoiRecapitiPage;
    private WebTool webTool;

    @Autowired
    private WebDriverConfig webDriverConfig;

    @Autowired
    @Lazy
    private BackgroundTest backgroundTest;


    PiattaformaNotifichePage piattaformaNotifichePage;

    @PostConstruct
    public void init() {
        logger.info("INIT TEST...: ");
        webTool = new WebTool(driver);
        recapitiDestinatarioPage = new RecapitiDestinatarioPage(driver);
        iTuoiRecapitiPage = new ITuoiRecapitiPage(driver);
        piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
        backgroundTest.setRecapitiTest(this);

    }

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

    @And("Nella pagina I Tuoi Recapiti si preme sul bottone Disattiva dell'app IO")
    public void nellaPaginaITuoiRecapitiSiPremeSulBottoneDisattivaDiIO() {
        logger.info("si preme sul bottone Disattiva dell'app IO");
        recapitiDestinatarioPage.clickSuBottoneDisattivaIO();
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
        if (PEC.equalsIgnoreCase(tipoContatto)) {
            backgroundTest.checkPECEsistentePerEliminazioneEInserimento(indirizzoMail);
        } else if (emailDiCortesia.equalsIgnoreCase(tipoContatto)) {
            backgroundTest.checkEmailDiCortesiaPerEliminazioneEInserimento(indirizzoMail);
        } else {
            Assertions.fail("Errore nella scrittura del tipo di contatto da controllare e inserire");
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
            Assertions.fail("messaggio di errore letto : '" + errorMessageRead + "' non è uguale a : Indirizzo e-mail non valido o Scrivi massimo 254 caratteri");
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
            Assertions.fail("Email non è stata inserita correttamente");
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
        if (recapitiDestinatarioPage.verificaMailAssociata()) {
            logger.info("Email di cortesia trovata, si procede con l'eliminazione");
            recapitiDestinatarioPage.clickSuBottoneEmailDiCortesia(ELIMINA);
            recapitiDestinatarioPage.confermaButtonEliminaClick();
        }
    }

    @And("Si inserisce il codice OTP errato {string} per tre volte e si controlla il messaggio di errore")
    public void siInserisceIlCodiceOTPErratoPerTreVolteESiControllaIlMessaggioDiErrore(String OTP) {
        logger.info("Si inserisce un codice OTP errato per 3 volte e si controlla il messaggio di errore");
        backgroundTest.inserimentoOTPErratoTreVolteEControlloMessaggio(OTP);
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che non ci sia un numero di cellulare di cortesia impostato")
    public void nellaPaginaITuoiRecapitiSiControllaCheNonCiSiaUnNumeroDiCellulareDiCortesiaImpostato() {
        logger.info("Si controlla la presenza di un numero di cellulare di cortesia");
        if (recapitiDestinatarioPage.verificaNumeroDiCellulareAssociato()) {
            logger.info("Numero di cellulare di cortesia trovato, si procede con l'eliminazione");
            recapitiDestinatarioPage.clickSuBottoneCellulareDiCortesia(ELIMINA);
            recapitiDestinatarioPage.confermaButtonEliminaClick();
        }
    }

    @And("Si clicca su 'Attiva SEND su IO'")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneAttivaSENDSuIO() {
        String variabileAmbiente = webDriverConfig.getEnvironment();
        if (variabileAmbiente.equalsIgnoreCase("uat")) {
            logger.info("Si clicca su 'Attiva SEND su IO'");
            recapitiDestinatarioPage.clickSuBottoneAttivaSENDSuIO();
        }
        logger.info("Si è su ambiente {} e feature IO non è attiva, si prosegue con il test", variabileAmbiente);
    }

    @And("Si clicca su 'Collega SEND su IO'")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneCollegaSENDSuIO() {
        String variabileAmbiente = webDriverConfig.getEnvironment();
        if (variabileAmbiente.equalsIgnoreCase("uat")) {
            logger.info("Si clicca su 'Collega SEND su IO'");
            iTuoiRecapitiPage.clickCollegaSENDSuIO();
        }
        logger.info("Si è su ambiente {} e feature IO non è attiva, si prosegue con il test", variabileAmbiente);
    }

    @And("Si visualizza correttamente il banner di recapito di cortesia mancante")
    public void siVisualizzaCorrettamenteIlBannerDiDomicilioMancante() {
        logger.info("Si visualizza correttamente il banner di recapito di cortesia mancante");
        recapitiDestinatarioPage.checkBannerRecapitoCortesiaMancante();
    }

    @And("Si visualizza correttamente il banner di email mancante")
    public void siVisualizzaIlBannerDiEmailMancante() {
        logger.info("Si visualizza correttamente il banner di email mancante");
        recapitiDestinatarioPage.checkPresenzaBannerEmailMancante();
    }

    @And("Non si visualizza correttamente il banner di email mancante")
    public void nonSiVisualizzaIlBannerDiEmailMancante() {
        logger.info("Non si visualizza correttamente il banner di email mancante");
        recapitiDestinatarioPage.checkAssenzaBannerEmailMancante();
    }

    @And("Si visualizza correttamente il banner di PEC in validazione {string}")
    public void siVisualizzaCorrettamenteIlBannerDiPECInValidazione(String ente) {
        logger.info("Si visualizza correttamente il banner di PEC in validazione");
        recapitiDestinatarioPage.checkBannerPECInValidazione(ente);
    }

    @Then("Si visualizza correttamente la pagina di avvenuta attivazione del Domicilio Digitale")
    public void siVisualizzaCorrettamentePaginaAttivazioneDomicilioDigitale() {
        iTuoiRecapitiPage.waitLoadAttivazioneDomicilioDigitalePage();
    }

    @Then("Verifica pop-up Impossibile disattivare il Domicilio Digitale")
    public void verificaImpossibileDisattivareIlDomicilioDigitale() {
        iTuoiRecapitiPage.checkImpossibileDisattivareDomicilioDigitale();
    }

    @And("Si chiude pop-up Impossibile disattivare il Domicilio Digitale")
    public void siChiudeImpossibileDisattivareIlDomicilioDigitale() {
        iTuoiRecapitiPage.siChiudeImpossibileDisattivareDomicilioDigitale();
    }

    @And("Click Notifiche")
    public void clickNotifiche() {
        iTuoiRecapitiPage.clickNotifiche();
    }

    @And("Click Le Tue Notifiche")
    public void clickLeTueNotifiche() {
        iTuoiRecapitiPage.clickLeTueNotifiche();
    }

    @And("Click Bottone Inizia nel Banner")
    public void clickBottoneIniziaNelBanner() {
        iTuoiRecapitiPage.clickBottoneIniziaNelBanner();
    }

    @And("Click Annulla Servizio Notifiche Digitali")
    public void clickAnnullaServizioNotificheDigitali() {
        iTuoiRecapitiPage.clickAnnullaServizioNotificheDigitali();
    }

    @And("Click I Tuoi Dati")
    public void clickITuoiDati() {
        iTuoiRecapitiPage.clickITuoiDati();
    }

    @And("Seleziona Numero di pagine")
    public void selezionaNumeroDiPagine() {
        piattaformaNotifichePage.buttonRighePagine();
        piattaformaNotifichePage.selezionaPage50();
    }

    @And("Click Continua")
    public void clickContinua() {
        iTuoiRecapitiPage.clickContinua();
    }

    @And("Click Aggiungi email")
    public void clickAggiungiEmail() {
        recapitiDestinatarioPage.clickAvvisamiViaEmail();
    }

    @And("Verifica  Indirizzo email non valido")
    public void verificaIndirizzoEmailNonValido() {
        recapitiDestinatarioPage.verificaIndirizzoEmailNonValido();
    }

    @And("Click Continua Tab Inserisci un recapito")
    public void clickContinuaTabInserisciUnRecapito() {
        recapitiDestinatarioPage.clickContinuaTabInserisciUnRecapito();
    }

    @And("Click Ok ho capito Recapiti")
    public void clickOkHoCapitoRecapiti() {
        recapitiDestinatarioPage.clickScollegaSENDDaIONelPopUpAggiungiITuoiRecapitiEImportante();
    }
    @And("Click Ok ho capito Recapiti Pop-up")
    public void clickOkHoCapitoRecapitiPopUp() {
        recapitiDestinatarioPage.clickOkHoCapitoRecapitiPopUp();
    }

    @And("Verifica presenza Campo obbligatorio")
    public void verificaPresenzaCampoObbligatorio() {
        recapitiDestinatarioPage.verificaPresenzaCampoObbligatorio();
    }

    @And("Spuntare checkbox privacy")
    public void spuntareCheckboxPrivacy() {
        recapitiDestinatarioPage.spuntareCheckboxPrivacy();
    }

    @And("Verifica  Indirizzo pec non valido")
    public void verificaIndirizzoPecNonValido() {
        recapitiDestinatarioPage.verificaIndirizzoPecNonValido();
    }

    @And("Verifica Scomparsa Banner Inizia")
    public void verificaScomparsaBannerInizia() {
        recapitiDestinatarioPage.verificaScomparsaBannerInizia();
    }

    @And("Verifica abilitazione campo email")
    public void verificaAbilitazioneCampoEmail() {
        recapitiDestinatarioPage.verificaAbilitazioneCampoEmail();
    }

    @And("Verifica campo obbligatorio Ente e Tipologia")
    public void verificaCampoObbligatorioEnteETipologia() {
        recapitiDestinatarioPage.verificaCampoObbligatorioEnteETipologia();
    }

    @And("Click Continua senza collegare IO")
    public void clickContinuaSenzaCollegareIO() {
        recapitiDestinatarioPage.clickContinuaSenzaCollegareIO();
    }

    @And("Click Scollega SEND da IO in Attiva domicilio digitale su SEND")
    public void clickScollegaSENDDaIOInAttivaDomicilioDigitaleSuSEND() {
        recapitiDestinatarioPage.clickScollegaSENDDaIOInAttivaDomicilioDigitaleSuSEND();
    }

    @And("Click Scollega SEND da IO nel Pop-up Aggiungi i tuoi recapiti e importante")
    public void clickScollegaSENDDaIONelPopUpAggiungiITuoiRecapitiEImportante() {
        recapitiDestinatarioPage.clickScollegaSENDDaIONelPopUpAggiungiITuoiRecapitiEImportante();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce {string} con caratteri speciali per la persona {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceConCaratteriSpecialiPerLaPersona(String tipo, String persona) {

        int tentativi = 0;
        int maxTentativi = 30;
        String indirizzoGenerato;

        while (tentativi < maxTentativi) {
            // Genera indirizzo non valido in base al tipo richiesto
            indirizzoGenerato = tipo.equalsIgnoreCase("pec") ?
                    recapitiDestinatarioPage.generateInvalidAddress("pec") :
                    recapitiDestinatarioPage.generateInvalidAddress("email");
            //Possibilita in futuro, distinguere se è per persona fisica o giuridica e per email o pec
            recapitiDestinatarioPage.cancellaTesto();
            recapitiDestinatarioPage.insertEmailPEC(indirizzoGenerato);


            webTool.waitTime(2);

            boolean erroreVisibile = recapitiDestinatarioPage.verificaIndirizzoPecModificatoNonValido(tipo);

            // Se NON c’è errore → indirizzo accettato → interrompi
            if (!erroreVisibile) {
                Assertions.fail("Il sistema ha accettato un indirizzo non valido: " + indirizzoGenerato);
                return;
            }
            tentativi++;
        }
        Assertions.assertTrue(true, "Tutti gli indirizzi sono stati correttamente segnalati come non validi");


    }

    @And("Si inserisce {string} con Caratteri Speciali Personalizza il tuo domicilio digitale per ente mittente")
    public void siInserisceConCaratteriSpecialiPersonalizzaIlTuoDomicilioDigitalePerEnteMittente(String tipo) {
        int tentativi = 0;
        int maxTentativi = 30;
        String indirizzoGenerato;

        while (tentativi < maxTentativi) {
            // Genera indirizzo non valido in base al tipo richiesto
            indirizzoGenerato = tipo.equalsIgnoreCase("pec") ?
                    recapitiDestinatarioPage.generateInvalidAddress("pec") :
                    recapitiDestinatarioPage.generateInvalidAddress("email");

                recapitiDestinatarioPage.inserisciPecInPersonalizzaIlTuoDomicilioDigitalePerEnteCaratteriSpeciali(indirizzoGenerato);

            // Verifica se il sistema NON ha segnalato errore (quindi lo considera valido)
            webTool.waitTime(2);

            boolean erroreVisibile = recapitiDestinatarioPage.verificaIndirizzoPecPersonalizzaIlTuoDomicilioPerEnteMittenteNonValido(tipo);

            // Se NON c’è errore → indirizzo accettato → interrompi
            if (!erroreVisibile) {
                Assertions.fail("Il sistema ha accettato un indirizzo non valido: " + indirizzoGenerato);
                return;
            }
            tentativi++;
        }
        Assertions.assertTrue(true, "Tutti gli indirizzi sono stati correttamente segnalati come non validi");
    }

    @And("Verifica Banner Personalizza il tuo domicilio digitale per ente mittente {string}")
    public void verificaBannerPersonalizzaIlTuoDomicilioDigitalePerEnteMittente(String testBanner) {
        recapitiDestinatarioPage.verificaBannerPersonalizzaIlTuoDomicilioDigitalePerEnteMittente(testBanner);

    }
}
