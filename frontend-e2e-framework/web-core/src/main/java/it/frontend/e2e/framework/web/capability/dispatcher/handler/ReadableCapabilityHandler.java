package it.frontend.e2e.framework.web.capability.dispatcher.handler;

import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.capability.core.Readable;

import java.lang.reflect.Method;

public class ReadableCapabilityHandler extends AbstractWebCapabilityHandler<Readable> {

    public ReadableCapabilityHandler(IWebPresentationApiAdapter adapter) {
        super(adapter);
    }

    @Override
    public <T> T handle(Method method, Object[] args, String selector) {
        return null;
    }
}
