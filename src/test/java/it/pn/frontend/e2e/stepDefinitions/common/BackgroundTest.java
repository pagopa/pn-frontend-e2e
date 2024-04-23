package it.pn.frontend.e2e.stepDefinitions.common;

import io.cucumber.messages.types.Hook;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.DeleghePagoPATest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.LoginPersonaFisicaPagoPA;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.RecapitiPersonaFisicaTest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.*;
import it.pn.frontend.e2e.stepDefinitions.mittente.NotificaMittentePagoPATest;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class BackgroundTest {

    private final WebDriver driver = Hooks.driver;
    private final String nomeFileDatiNotifica = "datiNotifica";
    private final String nomeFilePersonaFisica = "personaFisica";
    private final String nomeFilePG = "personaGiuridica";
    private final String nomeFileNuovaDelega = "nuova_delega";
    private final String nomeFileNuovaDelegaPG = "nuovaDelegaPG";
    private final String mittente = "mittente";
    private final DeleghePagoPATest deleghePagoPATest = new DeleghePagoPATest();

    private final RecapitiPersonaFisicaTest recapitiPersonaFisicaTest = new RecapitiPersonaFisicaTest();
    private final RecapitiPGPagoPaTest recapitiPGTest = new RecapitiPGPagoPaTest();
    private final LoginPGPagoPATest loginPGPagoPATest = new LoginPGPagoPATest();
    private final LoginPersonaFisicaPagoPA personaFisicaPagoPA = new LoginPersonaFisicaPagoPA();
    private final DeleghePGPagoPATest deleghePGPagoPATest = new DeleghePGPagoPATest();
    private final DisserviziAppPGTest disserviziAppPGTest = new DisserviziAppPGTest();
    private final HelpdeskTest helpdeskTest = new HelpdeskTest();
    private final NotifichePGPagoPATest notifichePGPagoPATest = new NotifichePGPagoPATest();
    private final RecapitiTest recapitiTest = new RecapitiTest();
    private Map<String, String> datiPersonaFisica;
    private final RecapitiDestinatarioPage recapitiDestinatarioPage = new RecapitiDestinatarioPage(driver);
    private final ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(driver);

    public BackgroundTest(){
        datiPersonaFisica = new HashMap<>();
        datiPersonaFisica.put("nome", "Lucrezia");
        datiPersonaFisica.put("cognome", "Borgia");
        datiPersonaFisica.put("codiceFiscale", "BRGLRZ80D58H501Q");
        datiPersonaFisica.put("ente", "Comune di Verona");
        datiPersonaFisica.put("ragioneSociale", "Lucrezia Borgia");
    }


    public void invioNotificaErrorePec() {
        NotificaMittentePagoPATest notificaMittentePagoPATest = new NotificaMittentePagoPATest();
        notificaMittentePagoPATest.nellaPaginaPiattaformaNotificheSiRecuperaLUltimoNumeroProtocollo();
        notificaMittentePagoPATest.nellaPaginaPiattaformaNotificheCliccareSulBottoneInviaUnaNuovaNotifica();
        notificaMittentePagoPATest.siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionInformazioniPreliminari();
        notificaMittentePagoPATest.nellaSectionInformazioniPreliminariInserireIDatiDellaNotificaSenzaPagamento(nomeFileDatiNotifica);
        notificaMittentePagoPATest.cliccareSuContinua();
        notificaMittentePagoPATest.siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionDestinatario();
        notificaMittentePagoPATest.nellaSectionDestinatarioInserireNomeCognomeECodiceFiscaleDaDestinatario(nomeFilePersonaFisica);
        notificaMittentePagoPATest.nellaSectionDestinatarioCliccareSuAggiungiIndirizzoFisicoCompilareIDatiDelDestinatario(nomeFilePersonaFisica);
        notificaMittentePagoPATest.nellaSectionDestinatarioCliccareSuAggiungiDomicilioDigitaleCompilareIDatiDellaPersonaFisica(nomeFilePersonaFisica);
        notificaMittentePagoPATest.cliccareSuContinua();
        notificaMittentePagoPATest.siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionAllegati();
        notificaMittentePagoPATest.nellaSectionAllegatiCaricareLAttoEInserireIlNomeAtto(nomeFileDatiNotifica);
        notificaMittentePagoPATest.nellaSectionAllegatiCliccareSulBottoneInvia();
        notificaMittentePagoPATest.siVisualizzaCorrettamenteLaFraseLaNotificaEStataCorrettamenteCreata();
        notificaMittentePagoPATest.cliccareSulBottoneVaiAlleNotifiche();
        notificaMittentePagoPATest.siVisualizzaCorrettamenteLaPaginaPiattaformaNotifiche();
        notificaMittentePagoPATest.siVerificaCheLaNotificaVieneCreataCorrettamente(nomeFileDatiNotifica);
    }

    public void aggiuntaNuovaDelegaPF() {
        deleghePagoPATest.visualizzaDelegheSection();
        deleghePagoPATest.nellaSezioneDelegheClickSulBottoneAggiungiNuovaDelega();
        deleghePagoPATest.siVisualizzaCorrettamenteLaPaginaNuovaDelega();
        deleghePagoPATest.nellaSezioneLeTueDelegheInserireIDati(datiPersonaFisica);
        deleghePagoPATest.nellaSezioneLeTueDelegheVerificareCheLaDataSiaCorretta();
        deleghePagoPATest.nellaSezioneLeTueDelegheSalvareIlCodiceVerificaAllInternoDelFile(nomeFileNuovaDelega);
        deleghePagoPATest.nellaSezioneLeTueDelegheClickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe();
        deleghePagoPATest.nellaSezioneDelegheSiVisualizzaLaDelegaInStatoDiAttesaDiConferma();
    }


    public void loginPF(String nomeFileLogin) {
        personaFisicaPagoPA.loginPortaleDelegatoTramiteRequestMethod(nomeFileLogin);
        personaFisicaPagoPA.homePageDestinatarioVieneVisualizzataCorrettamente();
        deleghePagoPATest.waitDelegheButton();
        deleghePagoPATest.visualizzaDelegheSection();
    }

    public void loginPGDeleghe(String nomeFileLogin) {
        loginPGPagoPATest.loginPortalePersonaGiuridicaTramiteTokenExchange(nomeFileLogin);
        notifichePGPagoPATest.nellaPaginaPiattaformaNotifichePersonaGiuridicaClickSulBottoneDeleghe();
        notifichePGPagoPATest.visualizzaDelegheSection();
    }

    public void accettazioneDelegaPG() {
        deleghePagoPATest.siSceglieOpzioneAccetta();
        deleghePagoPATest.siInserisceIlCodiceDelegaNelPopUp(nomeFileNuovaDelegaPG);
        deleghePagoPATest.siCliccaSulBottoneAccetta();
        deleghePGPagoPATest.siCliccaSulBottoneConfermaGruppo();

    }


    public void loginPFRecapiti(String nomeFileLogin) {
        personaFisicaPagoPA.loginPortaleDelegatoTramiteRequestMethod(nomeFileLogin);
        personaFisicaPagoPA.homePageDestinatarioVieneVisualizzataCorrettamente();
    }

    public void getStatoDellaPiattaformaPage() {
        disserviziAppPGTest.nellaDashboardPersonaGiuridicaCliccaSuDisserviziApp();
        disserviziAppPGTest.siVisualizzaCorrettamenteLaPaginaDelloStatoDellaPiattaforma();
        disserviziAppPGTest.siVisualizzanoCorrettamenteIDatiSulloStatoDellaPiattaforma();
        disserviziAppPGTest.siVisualizzaStoricoDisservizi();
    }

    public void getHelpdeskMonitoraggioPiattaforma() {
        helpdeskTest.siVisualizzaCorrettamenteHomeMonitoraggio();
    }

    public void logoutPF() {
        personaFisicaPagoPA.logoutDaPortaleDestinatario();
    }

    public void accettazioneDelegaPF() {
        deleghePagoPATest.waitDelegheButton();
        deleghePagoPATest.siSceglieOpzioneAccetta();
        deleghePagoPATest.siInserisceIlCodiceDelegaNelPopUp(nomeFileNuovaDelega);
        deleghePagoPATest.siCliccaSulBottoneAccetta();
        deleghePagoPATest.siControllaCheLaDelegaHaLoStatoAttiva(nomeFilePersonaFisica);
    }

    public void accettaDelegaPF() {
        deleghePagoPATest.waitDelegheButton();
        deleghePagoPATest.siSceglieOpzioneAccetta();
        deleghePagoPATest.siInserisceIlCodiceDelegaNelPopUp();
        deleghePagoPATest.siCliccaSulBottoneAccetta();
        deleghePagoPATest.siControllaCheLaDelegaHaLoStatoAttiva(nomeFilePersonaFisica);
    }


    public void aggiuntaEmailPF() {
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceLEmailDelPFECliccaSulBottoneAvvisamiViaEmail(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.siVisualizzaCorrettamenteIlPopUpESiCliccaSuConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaLOTPDellaEmailTramiteRequestMethod(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceLOTPRicevutoViaEmail(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiControllaCheLaEmailSiaPresente();
    }

    public void aggiungiPECPF() {
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceLaPECDelDestinatario(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiCliccaSulBottoneConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPTramiteChiamataRequest(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTP(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiControllaCheLaPecSiaStataInseritaCorrettamente();
    }

    public void aggiungiNuovaPECPF() {
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceUnaNuovaPECDellaPersonaFisica(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiCliccaSulBottoneConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPDellaNuovaPECTramiteChiamataRequest(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTP(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVerificaCheLaPecSiaStataModificata(nomeFilePersonaFisica);
        logoutPF();
        loginPFRecapiti(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaPiattaformaNotifichePersonaFisicaSiCliccaSulBottoneITuoiRecapiti();
        recapitiPersonaFisicaTest.siVisualizzaCorrettamenteLaPaginaITuoiRecapiti();
    }


    public void accettazioneDelegaSceltaGruppo(boolean withGroup) {
        deleghePagoPATest.siSceglieOpzioneAccetta();
        deleghePGPagoPATest.siInserisceIlCodiceDellaDelegaACaricoDellImpresaNellaModale();
        deleghePGPagoPATest.nellaSezioneDelegheSiCliccaSulBottoneConfermaCodice();
        if (!withGroup) {
            deleghePGPagoPATest.siAssegnaUnGruppoAllaDelega();
        }
        deleghePGPagoPATest.siCliccaSulBottoneConfermaGruppo();
    }

    public void checkDelegaSceltaGruppoEInserimentoCodiceErrata() {
        deleghePagoPATest.siSceglieOpzioneAccetta();
        deleghePGPagoPATest.siInserisceIlCodiceDellaDelegaACaricoDellImpresaNellaModaleErrata();
        deleghePGPagoPATest.siCliccaSulBottoneAccettaDelega();
        deleghePGPagoPATest.siCliccaSulBottoneConfermaGruppoErrato();
        deleghePGPagoPATest.siCliccaSulBottoneAssegnaAUnGruppo();
        deleghePGPagoPATest.nonSiAssegnaUnGruppoAllaDelegaCheLoRichiede();
        deleghePGPagoPATest.siCliccaSulBottoneIndietroInAssegnazioneGruppo();
        deleghePGPagoPATest.checkErroreInInserimentoCodiceErrato();
        deleghePGPagoPATest.siCliccaSulBottoneIndietroInInserimentoCodiceVerifica();
    }


    public void accettazioneDelegaConGruppo() {
        deleghePagoPATest.siSceglieOpzioneAccetta();
        deleghePGPagoPATest.siInserisceIlCodiceDellaDelegaACaricoDellImpresaNellaModale();
        deleghePGPagoPATest.nellaSezioneDelegheSiCliccaSulBottoneConfermaCodice();
        deleghePGPagoPATest.siAssegnaUnGruppoAllaDelega();
        deleghePGPagoPATest.siCliccaSulBottoneConfermaGruppo();
        deleghePGPagoPATest.siControllaCheLaDelegaPGALoStatoAttiva("Convivio Spa");
    }

    public void aggiuntaNuovaDelegaDellImpresaPG() {
        deleghePGPagoPATest.nellaPaginaDelegheSiCliccaSuDelegatiDallImpresa();
        deleghePGPagoPATest.nellaSezioneDelegatiDellImpresaClickSulBottoneAggiungiNuovaDelega();
        deleghePGPagoPATest.siVisualizzaLaSezioneLeTueDeleghePersonaGiuridica();
        deleghePGPagoPATest.nellaSezioneLeTueDeleghePersonaGiuridicaInserireIDati(nomeFileNuovaDelegaPG);
        deleghePGPagoPATest.nellaSezioneLeTueDeleghePersonaGiuridicaSalvareIlCodiceVerificaAllInternoDelFile(nomeFileNuovaDelegaPG);
        deleghePGPagoPATest.nellaSezioneLeTueDeleghePersonaGiuridicaVerificareCheLaDataSiaCorretta();
        deleghePGPagoPATest.nellaSezioneLeTueDeleghePersonaGiuridicaclickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe();
        deleghePGPagoPATest.nellaSezioneDelegatiDallImpresaSiVisualizzaLaDelegaInStatoDiAttesaDiConferma();
    }

    public void aggiuntaNuovaEmail() {
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceLaNuovaEmailDelPFECliccaSulBottoneAvvisamiViaEmail("personaFisica");
        recapitiPersonaFisicaTest.siVisualizzaCorrettamenteIlPopUpESiCliccaSuConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaLOTPDellaNuovaEmailTramiteRequestMethod("personaFisica");
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceLOTPRicevutoViaEmail("personaFisica");
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiControllaCheLaEmailSiaStataModificata();
    }

    public void logoutPG() {
        loginPGPagoPATest.logoutDaPortalePersonaGiuridica();
    }

    public void loginPGRecapiti(String nomeFilePG) {
        loginPGPagoPATest.loginPortalePersonaGiuridicaTramiteTokenExchange("personaGiuridica");
        NotifichePGPagoPATest notifichePGPagoPATest = new NotifichePGPagoPATest();
        notifichePGPagoPATest.siRecuperaBearerToken("personaGiuridica");
    }

    public void aggiungiPECPG() {
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceLaPECDelDestinatario(nomeFilePG);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiCliccaSulBottoneConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPTramiteChiamataRequest(nomeFilePG);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTP(nomeFilePG);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiControllaCheLaPecSiaStataInseritaCorrettamente();
    }

    public void aggiungiPecSezioneGiaAssociati() {
        recapitiPersonaFisicaTest.nellaSezioneAltriRecapitiSiSelezionaLEnte(mittente);
        recapitiPersonaFisicaTest.nellaSezioneAltriRecapitiSiSelezionaIlTipoDiIndirizzo();
        recapitiPersonaFisicaTest.nellaSezioneAltriRecapitiSiInserisceLaPECAggiuntivaDePersonaFisica("pec@pec.pagopa.it");
        recapitiPersonaFisicaTest.nellaSezioneAltriRecapitiSiCliccaSulBottoneAssocia();
        recapitiPersonaFisicaTest.nellaSezioneAltriRecapitiSiCliccaSulBottoneConfermaPerInserireUnRecapito();
    }

    public void revocaDelegaPG(String ragioneSociale) {
        deleghePGPagoPATest.siControllaCheNonSiaPresenteUnaDelegaConStessoNomePersonaGiuridica(ragioneSociale);
        deleghePagoPATest.siControllaCheNonCiSiaPiuUnaDelega();
    }

    public void rifiutoDelegaACaricoDellImpresa(String dpFile) {
        deleghePGPagoPATest.nellaPaginaDelegheSezioneDelegheAcaricoDellImpresaSiCliccaSulMenuDellaDelega(dpFile);
        deleghePGPagoPATest.nellaSezioneDelegheSiCliccaSulBottoneRifiuta();
        deleghePGPagoPATest.siCliccaSulBottoneRifiutaDelega();
        deleghePGPagoPATest.siControllaCheLaDelegaNonSiPiuPresenteInElenco();
    }

    public void aggiuntaEmailDiCortesia(String email) {
        recapitiTest.siInserisceLEmailDiCortesiaESiCliccaSulBottoneAvvisamiViaEmail(email);
        recapitiTest.siVisualizzaIlPopUpDisclaimerSiCliccaLaCheckboxEIlBottoneConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPTramiteChiamataRequestDellEmailEVieneInserito(email);
        recapitiTest.siControllaCheLEmailInseritaSiaPresente();
    }

    public void aggiuntaPEC(String emailPEC){
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiControllaCheNonCiSiaGiaUnaPec();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceLEmailPerLaPECDelDestinatario(emailPEC);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiCliccaSulBottoneConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPTramiteChiamataRequestDellEmailEVieneInserito(emailPEC);
    }

    public void aggiuntaPECConControlli(String emailPEC) {
        if (recapitiDestinatarioPage.checkFieldInputPEC()) {
            aggiuntaPEC(emailPEC);
        } else if (recapitiDestinatarioPage.siControllaPresenzaPEC()) {
            iTuoiRecapitiPage.eliminaPECEsistente();
            if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi PEC")) {
                recapitiDestinatarioPage.clickConfermaButtonEliminaPopUp();
            } else {
                recapitiDestinatarioPage.clickSuChiudiPopUp();
                recapitiDestinatarioPage.eliminaNuovaEmail();
                iTuoiRecapitiPage.eliminaPECEsistente();
                recapitiDestinatarioPage.waitLoadPopUpElimina();
                recapitiDestinatarioPage.clickConfermaButtonEliminaPopUp();
            }
            aggiuntaPEC(emailPEC);
        }
        WebTool.waitTime(10);
    }

    public void aggiuntaEmailDiCortesiaConControlli(String emailDiCortesia) {
        if (!recapitiDestinatarioPage.verificaMailAssociata()) {
            aggiuntaEmailDiCortesia(emailDiCortesia);
        } else if (recapitiDestinatarioPage.controlloEmailAssociata(emailDiCortesia)) {
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
            aggiuntaEmailDiCortesia(emailDiCortesia);
        }
        WebTool.waitTime(10);
    }
}
