package it.pn.frontend.e2e.stepDefinitions;

import io.cucumber.spring.CucumberContextConfiguration;
import it.pn.frontend.e2e.common.HelpdeskPage;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.config.BearerTokenConfig;
import it.pn.frontend.e2e.config.UserPasswordConfig;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.listeners.LoggerStartupListener;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.AccediAreaRiservataPGPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.HeaderPGSection;
import it.pn.frontend.e2e.section.mittente.AllegatiPASection;
import it.pn.frontend.e2e.section.mittente.DestinatarioPASection;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaMittenteSection;
import it.pn.frontend.e2e.section.mittente.InformazioniPreliminariPASection;
import it.pn.frontend.e2e.utility.CookieConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
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
        HelpdeskPage.class,
        HeaderPGSection.class,
        AccediAreaRiservataPGPage.class,
        RecapitiDestinatarioPage.class,
        ITuoiRecapitiPage.class,
        PiattaformaNotifichePage.class,
        AllegatiPASection.class,
        DestinatarioPASection.class,
        DettaglioNotificaMittenteSection.class,
        InformazioniPreliminariPASection.class

})
@EnableScheduling
@EnableConfigurationProperties
public class CucumberSpringIntegration {


}

