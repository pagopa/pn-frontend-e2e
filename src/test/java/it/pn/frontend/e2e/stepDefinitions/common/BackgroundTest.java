package it.pn.frontend.e2e.stepDefinitions.common;

import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.DeleghePagoPATest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.LoginPersonaFisicaPagoPA;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.RecapitiPersonaFisicaTest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.DeleghePGPagoPATest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.DisserviziAppPGTest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.LoginPGPagoPATest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.NotifichePGPagoPATest;
import it.pn.frontend.e2e.stepDefinitions.mittente.NotificaMittentePagoPATest;

public class BackgroundTest {
    private final String nomeFileDatiNotifica = "datiNotifica";
    private final String nomeFilePersonaFisica = "personaFisica";

    private final String nomeFileNuovaDelega = "nuova_delega";

    private final String nomeFileNuovaDelegaPG = "nuovaDelegaPG";

    private final DeleghePagoPATest deleghePagoPATest = new DeleghePagoPATest();

    private final RecapitiPersonaFisicaTest recapitiPersonaFisicaTest = new RecapitiPersonaFisicaTest();

    private final LoginPGPagoPATest loginPGPagoPATest = new LoginPGPagoPATest();

    private final LoginPersonaFisicaPagoPA personaFisicaPagoPA = new LoginPersonaFisicaPagoPA();

    private final DeleghePGPagoPATest deleghePGPagoPATest = new DeleghePGPagoPATest();

    private final DisserviziAppPGTest disserviziAppPGTest = new DisserviziAppPGTest();

    private final HelpdeskTest helpdeskTest = new HelpdeskTest();

    private final NotifichePGPagoPATest notifichePGPagoPATest = new NotifichePGPagoPATest();

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
        deleghePagoPATest.siVisualizzaLaSezioneLeTueDeleghe();
        deleghePagoPATest.nellaSezioneLeTueDelegheInserireIDati(nomeFileNuovaDelega);
        deleghePagoPATest.nellaSezioneLeTueDelegheVerificareCheLaDataSiaCorretta();
        deleghePagoPATest.nellaSezioneLeTueDelegheSalvareIlCodiceVerificaAllInternoDelFile(nomeFileNuovaDelega);
        deleghePagoPATest.nellaSezioneLeTueDelegheClickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe();
        deleghePagoPATest.nellaSezioneDelegheSiVisualizzaLaDelegaInStatoDiAttesaDiConferma();
    }

    public void loginPF(String nomeFileLogin) {
        personaFisicaPagoPA.loginPortaleDelegatoTramiteRequestMethod(nomeFileLogin);
        personaFisicaPagoPA.home_page_destinatario_viene_visualizzata_correttamente();
        deleghePagoPATest.wait_deleghe_Button();
        deleghePagoPATest.visualizzaDelegheSection();
    }

    public void loginPFRecapiti(String nomeFileLogin) {
        personaFisicaPagoPA.loginPortaleDelegatoTramiteRequestMethod(nomeFileLogin);
        personaFisicaPagoPA.home_page_destinatario_viene_visualizzata_correttamente();
        recapitiPersonaFisicaTest.ITuoiRecapitiButtonClick();
        recapitiPersonaFisicaTest.siVisualizzaCorrettamenteLaPaginaITuoiRecapiti();
    }

    public void loginPG(String nomeFileLogin) {
        loginPGPagoPATest.login_page_persona_giuridica_viene_visualizzata(nomeFileLogin);
    }

    public void getStatoDellaPiattaformaPage() {
        disserviziAppPGTest.nellaDashboardPersonaGiuridicaCliccaSuDisserviziApp();
        disserviziAppPGTest.siVisualizzaCorrettamenteLaPaginaDelloStatoDellaPiattaforma();
        disserviziAppPGTest.siVisualizzanoCorrettamenteIDatiSulloStatoDellaPiattaforma();
        disserviziAppPGTest.siVisualizzaStoricoDisservizi();
    }

    public void getHelpdeskMonitoraggioPiattaforma(String nomeFileLogin) {
        helpdeskTest.loginHelpdeskConUtenteTest(nomeFileLogin);
        helpdeskTest.siVisualizzaCorrettamenteHomeHelpdesk();
        helpdeskTest.clickSuCardMonitoraggioPiattaforma();
        helpdeskTest.siVisualizzaCorrettamenteHomeMonitoraggio();
    }

    public void logoutPF() {
        personaFisicaPagoPA.logout_da_portale_destinatario();
    }

    public void accettazioneDelegaPF() {
        deleghePagoPATest.wait_deleghe_Button();
        deleghePagoPATest.siSceglieOpzioneAccetta();
        deleghePagoPATest.siInserisceIlCodiceDelegaNelPopUp(nomeFileNuovaDelega);
        deleghePagoPATest.siCliccaSulBottoneAccetta();
        deleghePagoPATest.siControllaCheLaDelegaALoStatoAttiva(nomeFilePersonaFisica);
    }

    public void aggiungaDelegaPG() {
        deleghePGPagoPATest.nellaSezioneDelegatiDellImpresaClickSulBottoneAggiungiNuovaDelega();
        deleghePGPagoPATest.siVisualizzaLaSezioneLeTueDeleghePersonaGiuridica();
        deleghePGPagoPATest.nellaSezioneLeTueDeleghePersonaGiuridicaInserireIDati(nomeFileNuovaDelegaPG);
        deleghePGPagoPATest.nellaSezioneLeTueDeleghePersonaGiuridicaSalvareIlCodiceVerificaAllInternoDelFile(nomeFileNuovaDelegaPG);
        deleghePGPagoPATest.nellaSezioneLeTueDeleghePersonaGiuridicaclickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe();
        deleghePGPagoPATest.nellaSezioneDelegatiDallImpresaSiVisualizzaLaDelegaInStatoDiAttesaDiConferma();
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
        logoutPF();
        loginPFRecapiti(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.ITuoiRecapitiButtonClick();
        recapitiPersonaFisicaTest.siVisualizzaCorrettamenteLaPaginaITuoiRecapiti();
    }

    public void aggiungiNuovaPECPF() {
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceUnaNuovaPECDellaPersonaFisica(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiCliccaSulBottoneConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPDellaNuovaPECTramiteChiamataRequest(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTP(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVerificaCheLaPecSiaStataModificata(nomeFilePersonaFisica);
        logoutPF();
        loginPF(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.ITuoiRecapitiButtonClick();
        recapitiPersonaFisicaTest.siVisualizzaCorrettamenteLaPaginaITuoiRecapiti();
    }

    public void loginPGTokenExchange() {
        loginPGPagoPATest.loginPortalePersonaGiuridicaTramiteTokenExchange("personaGiuridica");
        NotifichePGPagoPATest notifichePGPagoPATest = new NotifichePGPagoPATest();
        notifichePGPagoPATest.siRecuperaBearerToken("personaGiuridica");
        loginPGPagoPATest.logoutDaPortalePersonaGiuridica();
        loginPGPagoPATest.loginPortalePersonaGiuridicaTramiteTokenExchange("delegatoPG");
        notifichePGPagoPATest.siVisualizzaCorrettamenteLaPaginaNotifichePersonaGiuridica("delegatoPG");
        notifichePGPagoPATest.nellaPaginaPiattaformaNotifichePersonaGiuridicaClickSulBottoneDeleghe();
        deleghePGPagoPATest.siVisualizzaLaPaginaDeleghe();
    }

    public void accettazioneDelegaSenzaGruppo() {
        deleghePagoPATest.siSceglieOpzioneAccetta();
        deleghePagoPATest.siInserisceIlCodiceDelegaNelPopUp("nuovaDelegaPG");
        deleghePGPagoPATest.nellaSezioneDelegheSiCliccaSulBottoneAccetta();
        deleghePGPagoPATest.nonSiAssegnaUnGruppoAllaDelega();
        deleghePGPagoPATest.siCliccaSulBottoneConferma();
        deleghePGPagoPATest.siControllaCheLaDelegaPGALoStatoAttiva("personaGiuridica");
    }

    public void aggiuntaNuovaDelegaPG() {
        deleghePGPagoPATest.nellaSezioneDelegatiDellImpresaClickSulBottoneAggiungiNuovaDelega();
        deleghePGPagoPATest.siVisualizzaLaSezioneLeTueDeleghePersonaGiuridica();
        deleghePGPagoPATest.nellaSezioneLeTueDeleghePersonaGiuridicaInserireIDati("nuovaDelegaPG");
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
}
