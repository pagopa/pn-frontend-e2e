package it.frontend.e2e.framework.web.capability.impl;

import it.frontend.e2e.framework.web.capability.core.Gettable;
import it.frontend.e2e.framework.web.model.WebPresentationElement;

import java.util.Optional;

public class GettableImpl implements Gettable {
    @Override
    public Optional<WebPresentationElement> get() {
        return Optional.empty();
    }
}
