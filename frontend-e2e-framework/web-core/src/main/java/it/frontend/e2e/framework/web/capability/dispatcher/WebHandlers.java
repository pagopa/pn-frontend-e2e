package it.frontend.e2e.framework.web.capability.dispatcher;

import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;
import it.frontend.e2e.framework.web.capability.dispatcher.handler.GettableCapabilityHandler;
import it.frontend.e2e.framework.web.capability.dispatcher.handler.ReadableCapabilityHandler;
import it.frontend.e2e.framework.web.capability.dispatcher.handler.WritableCapabilityHandler;

import java.util.List;

public class WebHandlers {
    public static List<ICapabilityHandler> getDefault() {
        return List.of(
                new GettableCapabilityHandler(),
                new ReadableCapabilityHandler(),
                new WritableCapabilityHandler()
        );
    }
}
