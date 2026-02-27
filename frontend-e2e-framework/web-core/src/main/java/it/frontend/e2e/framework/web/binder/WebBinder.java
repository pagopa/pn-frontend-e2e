package it.frontend.e2e.framework.web.binder;

import it.frontend.e2e.framework.core.binder.AbstractBinder;
import it.frontend.e2e.framework.core.capability.core.Gettable;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;
import it.frontend.e2e.framework.web.capability.dispatcher.WebCapabilityDispatcher;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.WebSelector;

import java.lang.reflect.InvocationHandler;
import java.util.List;

public class WebBinder extends AbstractBinder<WebSelector, WebLocation, WebPresentationElement> {

    @Override
    protected <T extends Gettable<WebSelector, WebLocation, WebPresentationElement>> InvocationHandler getInvocationHandler(Class<T> aClass) {
        List<ICapabilityHandler> handlers = initializeHandlers();
        WebCapabilityDispatcher dispatcher = new WebCapabilityDispatcher(handlers);

        return new WebBinderInvocationHandler(dispatcher);
    }

    private List<ICapabilityHandler> initializeHandlers() {
        return List.of();
    }
}
