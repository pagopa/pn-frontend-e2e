package it.pn.frontend.e2e.presentation.config;

import io.cucumber.spring.ScenarioScope;
import it.pn.frontend.e2e.presentation.IPresentationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PresentationConfig {

    @Bean
    @ScenarioScope
    public IPresentationHandler getPresentationHandler() {
        return null;
    }
}
