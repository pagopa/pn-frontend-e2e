package it.pn.frontend.e2e.stepDefinitions.common;

import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.DeleghePagoPATest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.LoginPersonaFisicaPagoPA;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.NotifichePersonaFisicaPAgoPATest;
import it.pn.frontend.e2e.stepDefinitions.mittente.NotificaMittentePagoPATest;

public class BackgroundTest {
    private final String nomeFileDatiNotifica = "datiNotifica";
    private final String nomeFilePersonaFisica = "personaFisica";

    private final String nomeFileNuovaDelega = "nuova_delega";

    private DeleghePagoPATest deleghePagoPATest = new DeleghePagoPATest();

    private LoginPersonaFisicaPagoPA personaFisicaPagoPA = new LoginPersonaFisicaPagoPA();

    public void invioNotificaErrorePec(){

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

    public void aggiuntaNuovaDelegaPF(){
         deleghePagoPATest.nellaSezioneDelegheClickSulBottoneAggiungiNuovaDelega();
         deleghePagoPATest.siVisualizzaLaSezioneLeTueDeleghe();
         deleghePagoPATest.nellaSezioneLeTueDelegheInserireIDati(nomeFileNuovaDelega);
         deleghePagoPATest.nellaSezioneLeTueDelegheVerificareCheLaDataSiaCorretta();
         deleghePagoPATest.nellaSezioneLeTueDelegheSalvareIlCodiceVerificaAllInternoDelFile(nomeFileNuovaDelega);
         deleghePagoPATest.nellaSezioneLeTueDelegheClickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe();
         deleghePagoPATest.nellaSezioneDelegheSiVisualizzaLaDelegaInStatoDiAttesaDiConferma();
    }
    public void loginPF(String nomeFileLogin){
        personaFisicaPagoPA.loginPortaleDelegatoTramiteRequestMethod(nomeFileLogin);
        personaFisicaPagoPA.home_page_destinatario_viene_visualizzata_correttamente();
        deleghePagoPATest.wait_deleghe_Button();
        deleghePagoPATest.visualizzaDelegheSection();
    }

    public void logoutPF(){
        personaFisicaPagoPA.logout_da_portale_destinatario();
    }
}
