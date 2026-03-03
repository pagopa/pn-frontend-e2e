package it.pn.frontend.e2e.config;

import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;
import it.frontend.e2e.framework.web.WebPresentationGateway;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.adapter.impl.SeleniumApiAdapter;
import it.frontend.e2e.framework.web.binder.WebBinder;
import it.frontend.e2e.framework.web.capability.dispatcher.handler.ClickableCapabilityHandler;
import it.frontend.e2e.framework.web.capability.dispatcher.handler.GettableCapabilityHandler;
import it.frontend.e2e.framework.web.capability.dispatcher.handler.ReadableCapabilityHandler;
import it.frontend.e2e.framework.web.capability.dispatcher.handler.WritableCapabilityHandler;
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
                new GettableCapabilityHandler(adapter),
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
