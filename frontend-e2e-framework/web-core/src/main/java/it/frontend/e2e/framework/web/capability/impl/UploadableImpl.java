package it.frontend.e2e.framework.web.capability.impl;

import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.capability.core.Uploadable;

public class UploadableImpl extends AbstractCapabilityImpl implements Uploadable {

    public UploadableImpl(IWebPresentationApiAdapter adapter) {
        super(adapter);
    }

    @Override
    public void upload(String absolutePath) {
        //adapter.sendText(xPathSelector.get(), absolutePath);
        adapter.sendFile(xPathSelector.get(), absolutePath);
    }
}