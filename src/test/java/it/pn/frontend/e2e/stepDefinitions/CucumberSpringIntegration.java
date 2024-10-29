package it.pn.frontend.e2e.stepDefinitions;

import io.cucumber.spring.CucumberContextConfiguration;
import it.pn.frontend.e2e.common.DettaglioNotificaSection;
import it.pn.frontend.e2e.common.HelpdeskPage;
import it.pn.frontend.e2e.common.NotificheDestinatarioPage;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.config.BearerTokenConfig;
import it.pn.frontend.e2e.config.UserPasswordConfig;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.listeners.LoggerStartupListener;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.*;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.*;
import it.pn.frontend.e2e.pages.mittente.*;
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
import it.pn.frontend.e2e.utility.CookieConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.DownloadFile;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;


@CucumberContextConfiguration
@SpringBootTest(classes = {
        CookieConfig.class,
        LoggerStartupListener.class,
        NetWorkInfo.class,
        WebDriverConfig.class,
        BearerTokenConfig.class,
        UserPasswordConfig.class,
        DataPopulation.class,
        HeaderPGSection.class,
        AccediAreaRiservataPGPage.class,
        DisserviziAppPage.class,
        PiattaformaNotifichePGPAPage.class,
        DownloadFile.class,
        AccediAPiattaformaNotifichePage.class,
        DisserviziAppPAPage.class,
        PiattaformaNotifichePage.class,
        DettaglioNotificaMittenteSection.class,
        AllegatiPASection.class,
        DestinatarioPASection.class,
        InformazioniPreliminariPASection.class,
        NotificaMittentePagoPATest.class,
        LoginPersonaFisicaPagoPA.class,
        ScegliSpidPAPage.class,
        LoginPAPage.class,
        CookiesSection.class,
        AreaRiservataPAPage.class,
        NotifichePFPage.class,
        HeaderPFSection.class,
        SelezionaImpresaPage.class,
        ScegliSpidPFPage.class,
        LoginSpidPFPage.class,
        ConfermaDatiSpidPFPage.class,
        HomePagePG.class,
        ScegliSpidPGPage.class,
        LoginPGPagoPAPage.class,
        AutorizzaInvioDatiPGPage.class,
        DestinatarioPage.class,
        HeaderPASection.class,
        PreAccediAreaRiservataPAPage.class,
        AutorizziInvioDatiPAPage.class,
        SelezionaEntePAPage.class,
        AcccediAreaRiservataPAPage.class,
        DettaglioNotificaSection.class,
        HelpdeskPage.class,
        RecapitiDestinatarioPage.class,
        ITuoiRecapitiPage.class,
        LoginPGPagoPATest.class,
        DeleghePGPagoPAPage.class,
        DelegatiImpresaSection.class,
        AggiungiDelegaPGSection.class,
        LeTueDelegheSection.class,
        PopUpRevocaDelegaSection.class,
        DeleghePage.class,
        NotificheDestinatarioPage.class,
        BackgroundTest.class

})
@EnableScheduling
@EnableConfigurationProperties
public class CucumberSpringIntegration {


}

