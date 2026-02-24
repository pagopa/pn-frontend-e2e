package it.pn.frontend.e2e.config;

import io.cucumber.spring.ScenarioScope;


import it.pn.frontend.e2e.framework.core.capability.dispatcher.CapabilityDispatcher;
import it.pn.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.pn.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;
import it.pn.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.pn.frontend.e2e.framework.web.adapter.selenium.SeleniumApiAdapter;
import it.pn.frontend.e2e.framework.web.binder.WebPresentationBinder;
import it.pn.frontend.e2e.framework.web.binder.invocation_handler.context.WebInvocationContext;
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
    public ICapabilityDispatcher<WebInvocationContext> capabilityDispatcher() {
        List<ICapabilityHandler<WebInvocationContext>> handlers = new ArrayList<>();
        return new CapabilityDispatcher<>(handlers);
    }

    @Bean
    @ScenarioScope
    public WebPresentationBinder webPresentationBinder(
            IWebPresentationApiAdapter adapter,
            ICapabilityDispatcher<WebInvocationContext> dispatcher
    ) {
        return new WebPresentationBinder(adapter, dispatcher);
    }
}
