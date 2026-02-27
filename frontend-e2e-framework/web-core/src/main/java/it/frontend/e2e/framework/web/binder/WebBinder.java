package it.frontend.e2e.framework.web.binder;

import it.frontend.e2e.framework.core.binder.AbstractBinder;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.WebSelector;

import java.lang.reflect.InvocationHandler;

public class WebBinder extends AbstractBinder<WebSelector, WebLocation, WebPresentationElement> {

    public WebBinder(InvocationHandler invocationHandler) {
        super(invocationHandler);
    }

}
