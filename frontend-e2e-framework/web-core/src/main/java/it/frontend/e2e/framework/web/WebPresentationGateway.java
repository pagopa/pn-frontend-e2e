package it.frontend.e2e.framework.web;

import it.frontend.e2e.framework.core.IPresentationGateway;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.binder.WebBinder;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.XPathSelector;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class WebPresentationGateway implements IPresentationGateway<XPathSelector, WebLocation, WebPresentationElement> {
    @Delegate
    private final IWebPresentationApiAdapter adapter;

    @Delegate
    private final WebBinder binder;
}
