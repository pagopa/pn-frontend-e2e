package it.frontend.e2e.framework.web.binder;

import it.frontend.e2e.framework.core.binder.Binder;
import it.frontend.e2e.framework.core.binder.BinderInvocationHandler;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.WebSelector;

public class WebBinder extends Binder<WebSelector, WebLocation, WebPresentationElement> {

    public WebBinder(BinderInvocationHandler invocationHandler) {
        super(invocationHandler);
    }

}
