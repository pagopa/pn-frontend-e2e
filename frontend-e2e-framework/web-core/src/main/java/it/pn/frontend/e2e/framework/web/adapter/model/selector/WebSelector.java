package it.pn.frontend.e2e.framework.web.adapter.model.selector;

import it.pn.frontend.e2e.framework.core.adapter.model.selector.Selector;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WebSelector implements Selector {
    private final String value;

    @Override
    public String getSelector() {
        return value;
    }
}
