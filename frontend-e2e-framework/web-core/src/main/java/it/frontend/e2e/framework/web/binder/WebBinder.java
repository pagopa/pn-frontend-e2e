package it.frontend.e2e.framework.web.binder;

import it.frontend.e2e.framework.core.binder.DefaultBinder;
import it.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;

import java.util.List;

public class WebBinder extends DefaultBinder {
    public WebBinder(List<ICapabilityHandler> handlers) {
        super(handlers);
    }
}
