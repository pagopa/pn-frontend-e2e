package it.pn.frontend.e2e.config;

import it.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;
import it.frontend.e2e.framework.web.WebPresentationGateway;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.adapter.model.BrowserSettings;
import it.frontend.e2e.framework.web.adapter.selenium.SeleniumApiAdapter;
import it.frontend.e2e.framework.web.binder.WebBinder;
import it.frontend.e2e.framework.web.capability.handler.ClickableCapabilityHandler;
import it.frontend.e2e.framework.web.capability.handler.GettableCapabilityHandler;
import it.frontend.e2e.framework.web.capability.handler.ReadableCapabilityHandler;
import it.frontend.e2e.framework.web.capability.handler.WritableCapabilityHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@TestConfiguration
public class FrameworkConfig {

    @Value("${selenium.browser:chrome}")
    private String browser;

    @Value("${selenium.headless:false}")
    private boolean headless;

    @Value("${selenium.arguments:}")
    private List<String> browserArguments;

    @Bean
    public IWebPresentationApiAdapter webPresentationApiAdapter() {
        BrowserSettings settings = BrowserSettings.of(browser, headless, browserArguments);
        return new SeleniumApiAdapter(settings);
    }

    @Bean
    public List<ICapabilityHandler> capabilityHandlers(IWebPresentationApiAdapter adapter) {
        return List.of(
                new ClickableCapabilityHandler(adapter),
                new GettableCapabilityHandler(),
                new ReadableCapabilityHandler(adapter),
                new WritableCapabilityHandler(adapter)
        );
    }

    @Bean
    public WebBinder webPresentationBinder(List<ICapabilityHandler> handlers) {
        return new WebBinder(handlers);
    }

    @Bean
    public WebPresentationGateway webPresentationGateway(IWebPresentationApiAdapter api, WebBinder binder) {
        return new WebPresentationGateway(api, binder);
    }
}
