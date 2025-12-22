package it.pn.frontend.e2e.presentation.web;

import it.pn.frontend.e2e.presentation.web.adapter.IWebPresentationApiAdapter;
import it.pn.frontend.e2e.presentation.web.binder.IWebPresentationBinder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class WebPresentationGateway {
    @Delegate
    private final IWebPresentationApiAdapter adapter;

    @Delegate
    private final IWebPresentationBinder binder;
}
