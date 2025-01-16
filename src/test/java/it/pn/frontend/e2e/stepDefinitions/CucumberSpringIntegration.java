package it.pn.frontend.e2e.stepDefinitions;

import io.cucumber.spring.CucumberContextConfiguration;
import it.pn.frontend.e2e.api.mittente.AccettazioneRichiestaNotifica;
import it.pn.frontend.e2e.api.personaFisica.RecuperoOTPRecapiti;
import it.pn.frontend.e2e.config.*;
import it.pn.frontend.e2e.listeners.LoggerStartupListener;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.model.delegate.*;
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
import it.pn.frontend.e2e.utility.CookieConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.NotificationBuilder;
import it.pn.frontend.e2e.utility.WebTool;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
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
        NotificationSingleton.class,
        RestNotification.class,
        NotificationBuilder.class,
        MandateSingleton.class,
        RestContact.class,
        CustomHttpClient.class,
        BasicCookieStore.class,
        AccettazioneRichiestaNotifica.class,
        RecuperoOTPRecapiti.class,
        BackgroundTest.class,
        RestDelegation.class,
        WebTool.class,
        WebdriverScopeConfig.class,
        WebDriverManager.class,
        DataPopulationConfig.class

})
@EnableScheduling
@EnableConfigurationProperties
public class CucumberSpringIntegration {


}

