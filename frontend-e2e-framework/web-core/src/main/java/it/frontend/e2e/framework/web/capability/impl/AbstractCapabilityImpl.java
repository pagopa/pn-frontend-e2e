package it.frontend.e2e.framework.web.capability.impl;

import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.adapter.decorator.WebAdapterLoggingDecorator;

public abstract class AbstractCapabilityImpl {
    protected final IWebPresentationApiAdapter adapter;

    public AbstractCapabilityImpl(IWebPresentationApiAdapter adapter) {
        this.adapter = new WebAdapterLoggingDecorator(adapter);
    }

}
