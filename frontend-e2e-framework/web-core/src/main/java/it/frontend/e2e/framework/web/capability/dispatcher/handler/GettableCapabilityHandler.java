package it.frontend.e2e.framework.web.capability.dispatcher.handler;

import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.capability.core.Gettable;

import java.lang.reflect.Method;

public class GettableCapabilityHandler extends AbstractWebCapabilityHandler<Gettable> {

    public GettableCapabilityHandler(IWebPresentationApiAdapter adapter) {
        super(adapter);
    }

    @Override
    public <T> T handle(Method method, Object[] args, String selector) {
        return null;
    }
}
