package it.pn.frontend.e2e.stepDefinitions;

import io.cucumber.spring.CucumberContextConfiguration;
import it.pn.frontend.e2e.config.BearerTokenConfig;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.listeners.LoggerStartupListener;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
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
        DataPopulation.class,
        BearerTokenConfig.class
})
@EnableScheduling
@EnableConfigurationProperties
public class CucumberSpringIntegration {


}

