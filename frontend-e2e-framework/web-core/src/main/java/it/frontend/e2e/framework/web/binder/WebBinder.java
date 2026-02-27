package it.frontend.e2e.framework.web.binder;

import it.frontend.e2e.framework.core.binder.AbstractBinder;
import it.frontend.e2e.framework.core.capability.Gettable;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.frontend.e2e.framework.web.model.WebSelector;

import java.lang.reflect.InvocationHandler;

public class WebBinder extends AbstractBinder<WebSelector, WebLocation, WebPresentationElement> {

    @Override
    protected <T extends Gettable<WebSelector, WebLocation, WebPresentationElement>> InvocationHandler getInvocationHandler(Class<T> aClass) {
        return null;
    }
}
