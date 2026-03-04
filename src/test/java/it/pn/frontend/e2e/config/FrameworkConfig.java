package it.pn.frontend.e2e.config;

import it.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;
import it.frontend.e2e.framework.web.WebPresentationGateway;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.adapter.impl.SeleniumApiAdapter;
import it.frontend.e2e.framework.web.binder.WebBinder;
import it.frontend.e2e.framework.web.capability.handler.ClickableCapabilityHandler;
import it.frontend.e2e.framework.web.capability.handler.GettableCapabilityHandler;
import it.frontend.e2e.framework.web.capability.handler.ReadableCapabilityHandler;
import it.frontend.e2e.framework.web.capability.handler.WritableCapabilityHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FrameworkConfig {

    @Bean
    public IWebPresentationApiAdapter webPresentationApiAdapter() {
        return new SeleniumApiAdapter();
    }

    @Bean
    public List<ICapabilityHandler> capabilityHandlers(IWebPresentationApiAdapter adapter) {
        return List.of(
                new ClickableCapabilityHandler(adapter),
                new GettableCapabilityHandler(),
                new ReadableCapabilityHandler(),
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
