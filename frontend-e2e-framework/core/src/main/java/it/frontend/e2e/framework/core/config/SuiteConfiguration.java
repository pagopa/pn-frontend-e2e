package it.frontend.e2e.framework.core.config;

import it.frontend.e2e.framework.core.adapter.IPresentationApiAdapter;
import it.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;
import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.core.model.location.Location;
import it.frontend.e2e.framework.core.model.selector.Selector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SuiteConfiguration<
        S extends Selector,
        L extends Location,
        E extends AbstractPresentationElement<S, L>,
        A extends IPresentationApiAdapter<S,L,E>
        > {
    private final List<ICapabilityHandler> capabilityHandlers;
    private final List<A> presentationApiAdapters;
}
