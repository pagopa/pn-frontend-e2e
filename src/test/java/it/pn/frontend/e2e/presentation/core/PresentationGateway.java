package it.pn.frontend.e2e.presentation.core;

import it.pn.frontend.e2e.presentation.core.adapter.IPresentationApiAdapter;
import it.pn.frontend.e2e.presentation.core.binder.IPresentationBinder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class PresentationGateway {
    @Delegate(types = IPresentationApiAdapter.class)
    private final IPresentationApiAdapter adapter;

    @Delegate(types = IPresentationBinder.class)
    private final IPresentationBinder binder;
}
