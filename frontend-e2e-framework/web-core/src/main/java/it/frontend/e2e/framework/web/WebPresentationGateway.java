package it.frontend.e2e.framework.web;

import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.binder.WebPresentationBinder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class WebPresentationGateway {
    @Delegate
    private final IWebPresentationApiAdapter adapter;

    @Delegate
    private final WebPresentationBinder binder;
}
