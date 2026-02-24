package it.pn.frontend.e2e.config;


import io.cucumber.spring.ScenarioScope;
import it.pn.frontend.e2e.model.SharedContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    @ScenarioScope
    public SharedContext getSharedContext() {
        return new SharedContext();
    }
}
