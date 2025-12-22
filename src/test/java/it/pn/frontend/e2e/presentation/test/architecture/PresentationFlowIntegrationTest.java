package it.pn.frontend.e2e.presentation.test.architecture;


import it.pn.frontend.e2e.presentation.core.capability.dispatcher.CapabilityDispatcher;
import it.pn.frontend.e2e.presentation.test.architecture.web.TestCapability;
import it.pn.frontend.e2e.presentation.test.architecture.web.TestCapabilityHandler;
import it.pn.frontend.e2e.presentation.test.architecture.web.TestWebPresentationApiAdapter;
import it.pn.frontend.e2e.presentation.web.binder.WebPresentationBinder;
import it.pn.frontend.e2e.presentation.web.binder.invocation_handler.context.WebInvocationContext;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PresentationFlowIntegrationTest {

    @Test
    void webShouldInterceptMethodThroughProxyAndCapability() {

        // GIVEN
        TestWebPresentationApiAdapter adapter = new TestWebPresentationApiAdapter();
        TestCapabilityHandler handler = new TestCapabilityHandler();

        CapabilityDispatcher<WebInvocationContext> dispatcher =
                new CapabilityDispatcher<>(List.of(handler));

        WebPresentationBinder binder =
                new WebPresentationBinder(adapter, dispatcher);

        TestCapability capability =
                binder.bind(TestCapability.class);

        // WHEN
        String result = capability.sayHello("Mario");

        // THEN
        assertEquals("Hello Mario", result);

        assertNotNull(handler.interceptedMethod);
        assertEquals("sayHello", handler.interceptedMethod.getName());
        assertEquals("Mario", handler.interceptedArgs[0]);
    }
}

