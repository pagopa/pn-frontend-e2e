package it.pn.frontend.e2e.presentation.test;

import it.pn.frontend.e2e.presentation.core.adapter.IPresentationApiAdapter;
import it.pn.frontend.e2e.presentation.core.binder.IPresentationBinder;
import it.pn.frontend.e2e.presentation.core.binder.PresentationBinder;
import it.pn.frontend.e2e.presentation.core.capability.dispatcher.CapabilityDispatcher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PresentationFlowIntegrationTest {

    @Test
    void shouldInterceptMethodThroughProxyAndCapability() {

        // GIVEN
        TestPresentationApiAdapter adapter = new TestPresentationApiAdapter();
        TestCapabilityHandler handler = new TestCapabilityHandler();

        CapabilityDispatcher dispatcher =
                new CapabilityDispatcher(List.of(handler));

        PresentationBinder<IPresentationApiAdapter> binder =
                new PresentationBinder<>(adapter, dispatcher);

        TestPresentationApiAdapter api =
                binder.bind(TestPresentationApiAdapter.class);

        // WHEN
        String result = api.sayHello("Mario");

        // THEN
        assertEquals("Hello Mario", result);

        assertNotNull(handler.interceptedMethod);
        assertEquals("sayHello", handler.interceptedMethod.getName());
        assertEquals("Mario", handler.interceptedArgs[0]);
    }
}

