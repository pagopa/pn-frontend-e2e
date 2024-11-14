package it.pn.frontend.e2e.stepDefinitions;

import io.cucumber.spring.CucumberContextConfiguration;
import it.pn.frontend.e2e.api.mittente.AccettazioneRichiestaNotifica;
import it.pn.frontend.e2e.api.personaFisica.RecuperoOTPRecapiti;
import it.pn.frontend.e2e.common.*;
import it.pn.frontend.e2e.config.*;
import it.pn.frontend.e2e.listeners.LoggerStartupListener;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.listeners.WebDriverFactory;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPF;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPG;
import it.pn.frontend.e2e.model.delegate.DelegateResponsePF;
import it.pn.frontend.e2e.model.delegate.DelegateResponsePG;
import it.pn.frontend.e2e.model.singleton.MandateSingleton;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.*;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.*;
import it.pn.frontend.e2e.pages.mittente.*;
import it.pn.frontend.e2e.rest.RestContact;
import it.pn.frontend.e2e.rest.RestDelegation;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.HeaderPFSection;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.AccediAreaRiservataPGPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.destinatario.personaFisica.LeTueDelegheSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.PopUpRevocaDelegaSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.AggiungiDelegaPGSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.DelegatiImpresaSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.HeaderPGSection;
import it.pn.frontend.e2e.section.mittente.*;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.LoginPersonaFisicaPagoPA;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.LoginPGPagoPATest;
import it.pn.frontend.e2e.stepDefinitions.mittente.NotificaMittentePagoPATest;
import it.pn.frontend.e2e.section.mittente.AllegatiPASection;
import it.pn.frontend.e2e.section.mittente.DestinatarioPASection;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaMittenteSection;
import it.pn.frontend.e2e.section.mittente.InformazioniPreliminariPASection;
import it.pn.frontend.e2e.utility.*;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@CucumberContextConfiguration
@SpringBootTest(classes = {
        CookieConfig.class,
        LoggerStartupListener.class,
        NetWorkInfo.class,
        WebDriverConfig.class,
        BearerTokenConfig.class,
        UserPasswordConfig.class,
        DataPopulation.class,
       // HeaderPGSection.class,
        //AccediAreaRiservataPGPage.class,
       // DisserviziAppPage.class,
        //PiattaformaNotifichePGPAPage.class,
        //DownloadFile.class,
       // AccediAPiattaformaNotifichePage.class,
        //DisserviziAppPAPage.class,
        // PiattaformaNotifichePage.class,
        //DettaglioNotificaMittenteSection.class,
        //AllegatiPASection.class,
        // DestinatarioPASection.class,
        //InformazioniPreliminariPASection.class,
        //NotificaMittentePagoPATest.class,
        //LoginPersonaFisicaPagoPA.class,
        //ScegliSpidPAPage.class,
        //LoginPAPage.class,
        //CookiesSection.class,
        //AreaRiservataPAPage.class,
        //NotifichePFPage.class,
        //HeaderPFSection.class,
        // SelezionaImpresaPage.class,
        // ScegliSpidPFPage.class,
        // LoginSpidPFPage.class,
        // ConfermaDatiSpidPFPage.class,
        // HomePagePG.class,
        // ScegliSpidPGPage.class,
        // LoginPGPagoPAPage.class,
        // AutorizzaInvioDatiPGPage.class,
        // DestinatarioPage.class,
        // HeaderPASection.class,
        // PreAccediAreaRiservataPAPage.class,
        // AutorizziInvioDatiPAPage.class,
        // SelezionaEntePAPage.class,
        //AcccediAreaRiservataPAPage.class,
        //DettaglioNotificaSection.class,
        // RecapitiDestinatarioPage.class,
        // ITuoiRecapitiPage.class,
        // LoginPGPagoPATest.class,
        //DeleghePGPagoPAPage.class,
        // DelegatiImpresaSection.class,
        // AggiungiDelegaPGSection.class,
        //LeTueDelegheSection.class,
        //PopUpRevocaDelegaSection.class,
        // DeleghePage.class,
        // NotificheDestinatarioPage.class,
        // HelpdeskPage.class,
        // ComeVuoiAccederePage.class,
        // DashboardPage.class,
        NotificationSingleton.class,
        // SuccessPASection.class,
        //LoginPersonaFisicaPagoPA.class,
        RestNotification.class,
        NotificationBuilder.class,
        MandateSingleton.class,
        RestContact.class,
        CustomHttpClient.class,
        //InvioNotifichePAPage.class,
        BasicCookieStore.class,
        AccettazioneRichiestaNotifica.class,
        RecuperoOTPRecapiti.class,
        BackgroundTest.class,
        //GeneraApiKeySection.class,
        RestDelegation.class,
        WebTool.class,
        // GruppiPGPage.class,
        //ApiKeyPAPage.class,
        WebdriverScopeConfig.class,
        WebDriverManager.class,
        WebDriverFactory.class


})
@EnableScheduling
@EnableConfigurationProperties
public class CucumberSpringIntegration {


}

