package it.frontend.e2e.framework.web.binder;

import it.frontend.e2e.framework.core.binder.Binder;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.WebSelector;

import java.util.List;

public class WebBinder extends Binder<WebSelector, WebLocation, WebPresentationElement> {

    public WebBinder(List<ICapabilityHandler> handlers) {
        super(handlers);
    }

}
