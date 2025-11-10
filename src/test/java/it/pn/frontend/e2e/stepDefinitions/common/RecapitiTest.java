package it.pn.frontend.e2e.stepDefinitions.common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.RecapitiPGPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

public class RecapitiTest extends BasePage {


    private final Logger logger = LoggerFactory.getLogger(RecapitiTest.class);

    private final String PEC = "PEC";
    private final String emailDiCortesia = "email di cortesia";
    private final String ELIMINA = "Elimina";

    public String OTP;

    private RecapitiDestinatarioPage recapitiDestinatarioPage;

    private ITuoiRecapitiPage iTuoiRecapitiPage;
    private RecapitiPGPage recapitiPGPage;
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
        recapitiPGPage = new RecapitiPGPage(driver);
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

    @And("Si visualizza correttamente il banner di recapito di cortesia mancante con DD attivato")
    public void siVisualizzaCorrettamenteIlBannerDiDomicilioMancanteDDAttivato() {
        logger.info("Si visualizza correttamente il banner di recapito di cortesia mancante con DD attivato");
        recapitiDestinatarioPage.checkBannerRecapitoCortesiaMancanteDDAttivato();
    }

    @And("Non si visualizza correttamente il banner di recapito di cortesia mancante con DD attivato")
    public void nonSiVisualizzaCorrettamenteIlBannerDiDomicilioMancanteDDAttivato() {
        logger.info("Non si visualizza correttamente il banner di recapito di cortesia mancante con DD attivato");
        recapitiDestinatarioPage.checkAssenzaBannerRecapitoCortesiaMancanteDDAttivato();
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

    @And("Verifica della presenza della modale Importanza aggiunta contatti")
    public void verificaPresenzaModaleImportanzaAggiuntaContatti() {
        recapitiDestinatarioPage.verificaPresenzaModaleImportanzaAggiuntaContatti();
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

    @And("Verifica Indirizzi {string} Non Validi Con Caratteri Speciali per {string}")
    public void verificaIndirizziNonValidiConCaratteriSpeciali(String tipo, String contesto) {
        // Ottengo la lista di tutti gli indirizzi non validi (uno per ogni carattere speciale)
        List<String> indirizziInvalidi = recapitiDestinatarioPage.generateInvalidAddress(tipo);
        List<String> indirizziAccettati = new ArrayList<>();

        for (String indirizzo : indirizziInvalidi) {
            // Inserisce l’indirizzo nel campo corretto in base al contesto
            switch (contesto.toLowerCase()) {
                case "persona":
                    recapitiDestinatarioPage.cancellaTesto();
                    recapitiDestinatarioPage.insertEmailPEC(indirizzo);
                    break;

                case "ente":
                    recapitiDestinatarioPage.inserisciPecInPersonalizzaIlTuoDomicilioDigitalePerEnteCaratteriSpeciali(indirizzo);
                    break;

                case "homepage":
                    if ("pec".equalsIgnoreCase(tipo)) {
                        recapitiDestinatarioPage.insertPEC(indirizzo);
                    } else {
                        recapitiDestinatarioPage.insertEmail(indirizzo);
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Contesto non supportato: " + contesto);
            }
            webTool.waitTime(2);

            // Verifica se compare il messaggio di errore in base al contesto
            boolean erroreVisibile;
            switch (contesto.toLowerCase()) {
                case "persona":
                    erroreVisibile = recapitiDestinatarioPage.verificaIndirizzoPecModificatoNonValido(tipo);
                    break;
                case "ente":
                    erroreVisibile = recapitiDestinatarioPage.verificaIndirizzoPecPersonalizzaIlTuoDomicilioPerEnteMittenteNonValido(tipo);
                    break;
                case "homepage":
                    erroreVisibile = recapitiDestinatarioPage.verificaIndirizzoEmailPecNonValido(tipo);
                    break;
                default:
                    throw new IllegalArgumentException("Contesto non supportato: " + contesto);
            }

            // Se non compare l'errore → l'indirizzo è stato accettato per sbaglio
            if (!erroreVisibile) {
                indirizziAccettati.add(indirizzo);
                logger.error("Il sistema ha accettato un indirizzo non valido: {}", indirizzo);
            } else {
                logger.info("Indirizzo non valido correttamente rifiutato: {}", indirizzo);
            }
        }
        // Asserzione finale: il sistema non deve accettare nessun indirizzo non valido
        Assertions.assertTrue(indirizziAccettati.isEmpty(),
                "Il sistema ha accettato i seguenti indirizzi non validi: " + indirizziAccettati);
    }

    @And("Verifica Banner Personalizza il tuo domicilio digitale per ente mittente {string}")
    public void verificaBannerPersonalizzaIlTuoDomicilioDigitalePerEnteMittente(String testBanner) {
        recapitiDestinatarioPage.verificaBannerPersonalizzaIlTuoDomicilioDigitalePerEnteMittente(testBanner);

    }

    @And("Click su bottone Disattiva per il recapito mail")
    public void clickSuBottoneDisattivaPerIlRecapitoMail() {
        recapitiDestinatarioPage.clickBottoneDisattivaInSezioneEmailDiCortesia();
    }

    @Then("Verifica pop-up Non è possibile disattivare l'email")
    public void verificaNonEPossibileDisattivareEmail() {
        iTuoiRecapitiPage.checkNonEPossibileDisattivareEmail();
    }

    @And("Si chiude pop-up Non è possibile disattivare l'email")
    public void siChiudePopUpNonÈPossibileDisattivareLEmail() {
        iTuoiRecapitiPage.siChiudeNonEPossibileDisattivareEmail();
    }

    @And("Si visualizza correttamente il banner di Domicilio Digitale non attivato")
    public void siVisualizzaCorrettamenteIlBannerDiDomicilioDigitaleNonAttivato() {
        recapitiDestinatarioPage.checkBannerDomicilioDigitaleNonAttivato();
    }

    @And("Click su Disattiva Personalizzati per Ente e Annulla")
    public void clickSuDisattivaPersonalizzatiPerEnte() {
        recapitiDestinatarioPage.clickSuDisattivaPersonalizzatiPerEnteEAnnulla();
    }

    @And("Non si visualizzano correttamente i pulsanti modifica, elimina e non è possibile modificare l'email")
    public void nonSiVisualizzanoCorrettamenteGliElementiPostModifica() {
        logger.info("Si controlla che non si visualizzano correttamente i pulsanti modifica, elimina e che non è possibile modificare l'email");
        iTuoiRecapitiPage.checkAssenzaPostModifica();
    }

    @And("Verifica della pagina Attiva domicilio digitale su SEND per PF")
    public void verificaDellaPaginaAttivaDomicilioDigitaleSuSENDPF() {
        iTuoiRecapitiPage.verificaDellaPaginaAttivaDomicilioDigitaleSuSEND();
    }

    @And("Verifica della pagina Attiva domicilio digitale su SEND per PG")
    public void verificaDellaPaginaAttivaDomicilioDigitaleSuSENDPG() {
        recapitiPGPage.verificaDellaPaginaAttivaDomicilioDigitaleSuSEND();
    }

    @And("Verifica della pagina La tua email per ricevere avvisi sulle notifiche SEND")
    public void verificaDellaPaginaLaTuaEmailPerRicevereAvvisiSulleNotificheSEND() {
        recapitiDestinatarioPage.verificaDellaPaginaLaTuaEmailPerRicevereAvvisiSulleNotificheSEND();
    }

    @And("Verifica della pagina Stai attivando il tuo domicilio digitale su SEND")
    public void verificaDellaPaginaStaiAttivandoIlTuoDomicilioDigitaleSuSEND() {
        recapitiDestinatarioPage.verificaDellaPaginaStaiAttivandoIlTuoDomicilioDigitaleSuSEND();
    }

    @And("Verifica della TYP Hai attivato il tuo domicilio digitale su SEND")
    public void verificaDellaTYPHaiAttivatoIlTuoDomicilioDigitaleSuSEND() {
        recapitiDestinatarioPage.verificaDellaTYPHaiAttivatoIlTuoDomicilioDigitaleSuSEND();
    }

    @And("Verifica della pagina La email aziendale per ricevere avvisi sulle notifiche SEND")
    public void verificaDellaPaginaLaEmailAziendalePerRicevereAvvisiSulleNotificheSEND() {
        recapitiPGPage.verificaDellaPaginaLaEmailAziendalePerRicevereAvvisiSulleNotificheSEND();
    }

    @And("Verifica della presenza della modale Importanza aggiunta contatti aziendali")
    public void verificaDellaPresenzaDellaModaleImportanzaAggiuntaContattiAziendali() {
        recapitiPGPage.verificaPresenzaModaleImportanzaAggiuntaContattiAziendali();
    }

    @And("Verifica della pagina Stai attivando il domicilio digitale su SEND per PG")
    public void verificaDellaPaginaStaiAttivandoIlDomicilioDigitaleSuSENDPG() {
        recapitiPGPage.verificaDellaPaginaStaiAttivandoIlDomicilioDigitaleSuSEND();
    }

    @And("Verifica della TYP Hai attivato il domicilio digitale su SEND per PG")
    public void verificaDellaTYPHaiAttivatoIlDomicilioDigitaleSuSENDPG() {
        recapitiPGPage.verificaDellaTYPHaiAttivatoIlDomicilioDigitaleSuSEND();
    }

    @And("Verifica della pagina Gestisci il tuo domicilio digitale")
    public void verificaDellaPaginaGestisciIlTuoDomicilioDigitale() {
        iTuoiRecapitiPage.GestisciIlDomicilioDigitale();
    }

    @And("Verifica della pagina Gestisci il domicilio digitale per PG")
    public void verificaDellaPaginaGestisciIlDomicilioDigitalePerPG() {
        recapitiPGPage.verificaDellaPaginaGestisciIlDomicilioDigitalePerPG();
    }

    @And("Verifica della pagina Personalizza il tuo domicilio digitale per ente mittente per PF")
    public void verificaDellaPaginaPersonalizzaIlTuoDomicilioDigitalePerEnteMittentePF() {
        iTuoiRecapitiPage.verificaDellaPaginaPersonalizzaIlTuoDomicilioDigitalePerEnteMittente();
    }

    @And("Verifica della pagina Personalizza il tuo domicilio digitale per ente mittente per PG")
    public void verificaDellaPaginaPersonalizzaIlTuoDomicilioDigitalePerEnteMittentePG() {
        recapitiPGPage.verificaDellaPaginaPersonalizzaIlTuoDomicilioDigitalePerEnteMittente();
    }

    @And("Verifica della TYP Trasferimento del domicilio digitale in corso per PF")
    public void verificaDellaTYPTrasferimentoDelDomicilioDigitaleInCorsoPF() {
        iTuoiRecapitiPage.verificaDellaTYPTrasferimentoDelDomicilioDigitaleInCorso();
    }

    @And("Verifica della TYP Hai trasferito il tuo domicilio digitale su SEND")
    public void verificaDellaTYPHaiTrasferitoIlTuoDomicilioDigitaleSuSEND() {
        iTuoiRecapitiPage.verificaDellaTYPHaiTrasferitoIlTuoDomicilioDigitaleSuSEND();
    }

    @And("Verifica della TYP Hai trasferito il domicilio digitale su SEND per PG")
    public void verificaDellaTYPHaiTrasferitoIlDomicilioDigitaleSuSENDPerPG() {
        recapitiPGPage.verificaDellaTYPHaiTrasferitoIlDomicilioDigitaleSuSEND();
    }

    @And("Verifica della pagina Hai aggiornato il tuo domicilio digitale per PF")
    public void verificaDellaPaginaHaiAggiornatoIlTuoDomicilioDigitalePF() {
        iTuoiRecapitiPage.verificaDellaPaginaHaiAggiornatoIlTuoDomicilioDigitale();
    }

    @And("Verifica della pagina Hai aggiornato il tuo domicilio digitale per PG")
    public void verificaDellaPaginaHaiAggiornatoIlTuoDomicilioDigitalePG() {
        recapitiPGPage.verificaDellaPaginaHaiAggiornatoIlTuoDomicilioDigitale();
    }

    @And("Verifica della pagina Usa una PEC come domicilio digitale per PF")
    public void verificaDellaPaginaUsaUnaPECComeDomicilioDigitalePF() {
        iTuoiRecapitiPage.verificaDellaPaginaUsaUnaPECComeDomicilioDigitale();
    }

    @And("Verifica della pagina Usa una PEC come domicilio digitale per PG")
    public void verificaDellaPaginaUsaUnaPECComeDomicilioDigitalePG() {
        recapitiPGPage.verificaDellaPaginaUsaUnaPECComeDomicilioDigitalePG();
    }
}
