package it.pn.frontend.e2e.config;

import it.frontend.e2e.framework.web.WebPresentationGateway;
import it.frontend.e2e.framework.web.adapter.model.BrowserSettings;
import it.frontend.e2e.framework.web.adapter.selenium.SeleniumApiAdapter;
import it.frontend.e2e.framework.web.config.WebSuiteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@TestConfiguration
public class FrameworkConfig {

    @Value("${channel.web.browser:chrome}")
    private String browser;

    @Value("${channel.web.headless:false}")
    private boolean headless;

    @Value("${channel.web.arguments:}")
    private List<String> browserArguments;

    @Bean
    public WebPresentationGateway webPresentationGateway() {
        BrowserSettings settings = BrowserSettings.of(browser, headless, browserArguments);

        return WebSuiteBuilder.builder()
                .withAdapter(() -> new SeleniumApiAdapter(settings))
                .build();
    }
}
