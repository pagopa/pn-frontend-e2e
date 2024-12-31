package it.pn.frontend.e2e.stepDefinitions.common;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.DeleghePagoPATest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.LoginPersonaFisicaPagoPA;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.RecapitiPersonaFisicaTest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.DeleghePGPagoPATest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.DisserviziAppPGTest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.LoginPGPagoPATest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.NotifichePGPagoPATest;
import it.pn.frontend.e2e.stepDefinitions.mittente.NotificaMittentePagoPATest;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BackgroundTest  extends BasePage {

    private final String nomeFileDatiNotifica = "datiNotifica";
    private final String nomeFilePersonaFisica = "personaFisica";
    private final String nomeFilePG = "personaGiuridica";
    private final String nomeFileNuovaDelega = "nuova_delega";
    private final String nomeFileNuovaDelegaPG = "nuovaDelegaPG";
    private final String mittente = "mittente";
    private final Map<String, String> datiPersonaFisica;

    @Autowired
    @Lazy
    @Setter
    private DeleghePagoPATest deleghePagoPATest;
    @Autowired
    @Lazy
    @Setter
    private  RecapitiPersonaFisicaTest recapitiPersonaFisicaTest;
    @Autowired
    @Lazy
    @Setter
    private LoginPGPagoPATest loginPGPagoPATest;
    @Autowired
    @Lazy
    @Setter
    private LoginPersonaFisicaPagoPA personaFisicaPagoPA;
    @Autowired
    @Lazy
    @Setter
    private DeleghePGPagoPATest deleghePGPagoPATest;
    @Autowired
    @Lazy
    @Setter
    private  DisserviziAppPGTest disserviziAppPGTest ;
    @Autowired
    @Lazy
    @Setter
    private  HelpdeskTest helpdeskTest;
    @Autowired
    @Lazy
    @Setter
    private NotifichePGPagoPATest notifichePGPagoPATest;
    @Autowired
    @Lazy
    @Setter
    private RecapitiTest recapitiTest;

    @Autowired
    @Lazy
    @Setter
    private NotificaMittentePagoPATest notificaMittentePagoPATest;
    private  RecapitiDestinatarioPage recapitiDestinatarioPage ;
    private  ITuoiRecapitiPage iTuoiRecapitiPage;
    @Setter
    @Getter
    private  PiattaformaNotifichePage piattaformaNotifichePage;

    @Setter
    @Getter
    private HooksNew hooksNew;

    private  WebTool webTool;

    public BackgroundTest() {
        datiPersonaFisica = new HashMap<>();
        datiPersonaFisica.put("nome", "Lucrezia");
        datiPersonaFisica.put("cognome", "Borgia");
        datiPersonaFisica.put("codiceFiscale", "BRGLRZ80D58H501Q");
        datiPersonaFisica.put("ente", "Comune di Verona");
        datiPersonaFisica.put("ragioneSociale", "Lucrezia Borgia");
    }


    @PostConstruct
    public void init(){
        webTool = new WebTool(driver);
        recapitiDestinatarioPage = new RecapitiDestinatarioPage(driver);
        iTuoiRecapitiPage = new ITuoiRecapitiPage(driver);
        piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
    }



    public void invioNotificaErrorePec() {

        notificaMittentePagoPATest.nellaPaginaPiattaformaNotificheSiRecuperaLUltimoNumeroProtocollo();
        notificaMittentePagoPATest.nellaPaginaPiattaformaNotificheCliccareSulBottoneInviaUnaNuovaNotifica();
        notificaMittentePagoPATest.siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionInformazioniPreliminari();
        notificaMittentePagoPATest.nellaSectionInformazioniPreliminariInserireIDatiDellaNotificaSenzaPagamento();
        notificaMittentePagoPATest.cliccareSuContinua();
        notificaMittentePagoPATest.siVisualizzaCorrettamenteLaPaginaPiattaformaNotificheSectionDestinatario();
        notificaMittentePagoPATest.nellaSectionDestinatarioInserireNomeCognomeECodiceFiscaleDaDestinatario(nomeFilePersonaFisica);
        notificaMittentePagoPATest.nellaSectionDestinatarioCliccareSuAggiungiIndirizzoFisicoCompilareIDatiDelDestinatario(nomeFilePersonaFisica,0);
        notificaMittentePagoPATest.nellaSectionDestinatarioCliccareSuAggiungiDomicilioDigitaleCompilareIDatiDellaPersonaFisica();
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
        deleghePagoPATest.nellaSezioneLeTueDelegheSalvareIlCodiceVerificaAllInternoDelFile();
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
        deleghePagoPATest.siInserisceIlCodiceDelegaNelPopUp("personaFisica");
        deleghePagoPATest.siCliccaSulBottoneAccetta();
        deleghePagoPATest.siControllaCheLaDelegaHaLoStatoAttiva(nomeFilePersonaFisica);
    }


    public void aggiuntaEmailPF() {
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceLEmailDelPFECliccaSulBottoneAvvisamiViaEmail(nomeFilePersonaFisica);
        //recapitiPersonaFisicaTest.siVisualizzaCorrettamenteIlPopUpESiCliccaSuConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaLOTPDellaEmailTramiteRequestMethod(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceLOTPRicevutoViaEmail();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiControllaCheLaEmailSiaPresente();
    }

    public void aggiungiPECPF() {
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceUnaNuovaPECDellaPersonaFisica("prova@pec.it");
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiCliccaSulBottoneConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPDellaNuovaEmailPECTramiteChiamataRequest("prova@pec.it");
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiControllaCheLaPecSiaStataInseritaCorrettamente();
    }

    public void aggiungiNuovaPECPF() {
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceUnaNuovaPECDellaPersonaFisica("prova@pec.it");
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiCliccaSulBottoneConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPDellaNuovaPECTramiteChiamataRequest();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTP(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVerificaCheLaPecSiaStataModificata(nomeFilePersonaFisica);
        logoutPF();
        loginPFRecapiti(nomeFilePersonaFisica);
        recapitiPersonaFisicaTest.nellaPaginaPiattaformaNotifichePersonaFisicaSiCliccaSulBottoneITuoiRecapiti();
        recapitiPersonaFisicaTest.siVisualizzaCorrettamenteLaPaginaITuoiRecapiti();
    }


    public void accettazioneDelegaSceltaGruppo(boolean withGroup, String gruppo) {
        deleghePagoPATest.siSceglieOpzioneAccetta();
        deleghePGPagoPATest.siInserisceIlCodiceDellaDelegaACaricoDellImpresaNellaModale();
        deleghePGPagoPATest.nellaSezioneDelegheSiCliccaSulBottoneConfermaCodice();
        if (withGroup) {
            deleghePGPagoPATest.siAssegnaUnGruppoAllaDelega(gruppo);
        }
        deleghePGPagoPATest.siCliccaSulBottoneConfermaGruppo();
    }

    public void accettazioneDelegaSceltaGruppoPF(boolean withGroup, String gruppo) {
        deleghePagoPATest.siSceglieOpzioneAccetta();
        deleghePGPagoPATest.siInserisceIlCodiceDellaDelegaACaricoDellImpresaNellaModale();
        deleghePGPagoPATest.nellaSezioneDelegheSiCliccaSulBottoneConfermaCodice();
        if (withGroup) {
            deleghePGPagoPATest.siAssegnaUnGruppoAllaDelega(gruppo);
        }
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


    public void aggiuntaNuovaDelegaDellImpresaPG() {
        deleghePGPagoPATest.nellaPaginaDelegheSiCliccaSuDelegatiDallImpresa();
        deleghePGPagoPATest.nellaSezioneDelegatiDellImpresaClickSulBottoneAggiungiNuovaDelega();
        deleghePGPagoPATest.siVisualizzaLaSezioneLeTueDeleghePersonaGiuridica();
        deleghePGPagoPATest.nellaSezioneLeTueDeleghePersonaGiuridicaInserireIDati();
        deleghePGPagoPATest.nellaSezioneLeTueDeleghePersonaGiuridicaSalvareIlCodiceVerificaAllInternoDelFile();
        deleghePGPagoPATest.nellaSezioneLeTueDeleghePersonaGiuridicaVerificareCheLaDataSiaCorretta();
        deleghePGPagoPATest.nellaSezioneLeTueDeleghePersonaGiuridicaclickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe();
        deleghePGPagoPATest.nellaSezioneDelegatiDallImpresaSiVisualizzaLaDelegaInStatoDiAttesaDiConferma();
    }

    public void logoutPG() {
        loginPGPagoPATest.logoutDaPortalePersonaGiuridica();
    }


    public void aggiungiPECPG() {
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceLaPECDelDestinatario("pec@pec.pagopa.it");
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiCliccaSulBottoneConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPTramiteChiamataRequest(nomeFilePG);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTP(nomeFilePG);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiControllaCheLaPecSiaStataInseritaCorrettamente();
    }

    public void aggiungiPecSezioneGiaAssociati() {
        recapitiPersonaFisicaTest.nellaSezioneAltriRecapitiSiSelezionaLEnte();
        recapitiPersonaFisicaTest.nellaSezioneAltriRecapitiSiSelezionaIlTipoDiIndirizzo();
        recapitiPersonaFisicaTest.nellaSezioneAltriRecapitiSiInserisceLaPECAggiuntivaDePersonaFisica("pec@pec.pagopa.it");
        recapitiPersonaFisicaTest.nellaSezioneAltriRecapitiSiCliccaSulBottoneAssocia();
        recapitiPersonaFisicaTest.nellaSezioneAltriRecapitiSiCliccaSulBottoneConfermaPerInserireUnRecapito();
    }

    public void revocaDelegaPG(String ragioneSociale) {
        deleghePGPagoPATest.siControllaCheNonSiaPresenteUnaDelegaConStessoNomePersonaGiuridica(ragioneSociale);
        deleghePagoPATest.siControllaCheNonCiSiaPiuUnaDelega();
    }

    public void creazioneDisservizio() {
        helpdeskTest.loginHelpdeskConUtenteTest();
        helpdeskTest.siVisualizzaCorrettamenteHomeHelpdesk();
        helpdeskTest.clickSuCardMonitoraggioPiattaforma();
        helpdeskTest.siVisualizzaCorrettamenteHomeMonitoraggio();
        helpdeskTest.siCreaIlDisservizio();
        helpdeskTest.siVerificaLaCreazioneDelDisservizio();
    }

    public void risoluzioneDisservizio() {
        helpdeskTest.loginHelpdeskConUtenteTest();
        helpdeskTest.siVisualizzaCorrettamenteHomeHelpdesk();
        helpdeskTest.clickSuCardMonitoraggioPiattaforma();
        helpdeskTest.siVisualizzaCorrettamenteHomeMonitoraggio();
        helpdeskTest.siRisolveIlDisservizio();
        helpdeskTest.siRisolveIlDisservizio();
        helpdeskTest.siVerificaLaCreazioneDelDisservizio();
    }

    public void aggiuntaEmailDiCortesia(String email) {
        recapitiTest.siInserisceLEmailDiCortesiaESiCliccaSulBottoneAvvisamiViaEmail(email);
       // recapitiTest.siVisualizzaIlPopUpDisclaimerSiCliccaLaCheckboxEIlBottoneConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPTramiteChiamataRequestDellEmailEVieneInserito(email);
        recapitiTest.siControllaCheLEmailInseritaSiaPresente();
    }

    public void siEliminaPecEsistenteEAltriRecapitiAssociati() {
        recapitiDestinatarioPage.clickSuEliminaPec();
        webTool.waitTime(3);
        if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi PEC")) {
            recapitiDestinatarioPage.clickSuConfermaElimina();
        } else {
            recapitiDestinatarioPage.clickSuChiudiPopUp();
            recapitiDestinatarioPage.eliminaNuovaPec();
            recapitiDestinatarioPage.clickSuEliminaPec();
            recapitiDestinatarioPage.waitLoadPopUpElimina();
            recapitiDestinatarioPage.clickSuConfermaElimina();
        }
    }

    public void siInserisceUnaPECConCampoInputVisibile(String emailPEC) {
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceLEmailPerLaPECDelDestinatario(emailPEC);
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiCliccaSulBottoneConferma();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPTramiteChiamataRequestDellEmailEVieneInserito(emailPEC);
    }

    public void checkPECEsistentePerEliminazioneEInserimento(String emailPEC) {
        if (!recapitiDestinatarioPage.verificaPecAssociata()) {
            siInserisceUnaPECConCampoInputVisibile(emailPEC);
        } else if (recapitiDestinatarioPage.siControllaPresenzaPEC()) {
            recapitiDestinatarioPage.clickSuEliminaPec();
            if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi PEC")) {
                recapitiDestinatarioPage.clickSuConfermaElimina();
            } else {
                recapitiDestinatarioPage.clickSuChiudiPopUp();
                recapitiDestinatarioPage.eliminaNuovaEmail();
                recapitiDestinatarioPage.clickSuEliminaPec();
                recapitiDestinatarioPage.waitLoadPopUpElimina();
                recapitiDestinatarioPage.clickSuConfermaElimina();
            }
            siInserisceUnaPECConCampoInputVisibile(emailPEC);
        }
        webTool.waitTime(10);
    }

    public void checkEmailDiCortesiaPerEliminazioneEInserimento(String emailDiCortesia) {
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
        webTool.waitTime(10);
    }

    public void inserimentoOTPErratoTreVolteEControlloMessaggio(String OTP) {
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP();
        recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiSiInserisceOTPSbagliato(OTP);
        for(int i = 0; i < 2; i++){
            recapitiPersonaFisicaTest.nellaPaginaITuoiRecapitiCliccaSulBottoneConferma();
        }
        recapitiTest.siVisualizzaCorrettamenteIlMessaggioDiErroreDeiTreTentativi();
    }

    public void siFiltraLaTabellaDelleNotifichePerIUNDestinatario(String iun) {
        piattaformaNotifichePage.inserimentoCodiceIUN(iun);
        piattaformaNotifichePage.selectFiltraNotificaButtonDestinatario();
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.clickSuNotifica();
    }

    public void siFiltraLaTabellaDelleNotificheDelDestinatarioPerIUN(String iun) {
        piattaformaNotifichePage.inserimentoCodiceIUN(iun);
        piattaformaNotifichePage.selectFiltraNotificaButtonDestinatario();
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.clickSuNotifica();
    }

    public void siFiltraLaTabellaDelleNotifichePerIUNMittente(String iun) {
        piattaformaNotifichePage.inserimentoCodiceIUN(iun);
        piattaformaNotifichePage.selectFiltraNotificaButtonMittente();
        webTool.waitTime(1);
        piattaformaNotifichePage.setHooksNew(hooksNew);
        piattaformaNotifichePage.clickSuNotifica();
    }
}
