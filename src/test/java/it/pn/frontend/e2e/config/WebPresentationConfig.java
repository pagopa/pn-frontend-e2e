package it.pn.frontend.e2e.config;

import io.cucumber.spring.ScenarioScope;
import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.adapter.impl.SeleniumApiAdapter;
import it.frontend.e2e.framework.web.binder.WebBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebPresentationConfig {

    @Bean
    @ScenarioScope
    public IWebPresentationApiAdapter webPresentationApiAdapter() {
        return new SeleniumApiAdapter();
    }

    @Bean
    @ScenarioScope
    public ICapabilityDispatcher capabilityDispatcher() {
        List<ICapabilityHandler> handlers = new ArrayList<>();
        return null;
    }

    @Bean
    @ScenarioScope
    public WebBinder webPresentationBinder(
            IWebPresentationApiAdapter adapter,
            ICapabilityDispatcher dispatcher
    ) {
        return null;
    }
}
