package it.frontend.e2e.framework.web.adapter.model;


import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.frontend.e2e.framework.web.adapter.model.locator.WebLocation;
import it.frontend.e2e.framework.web.adapter.model.selector.WebSelector;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class WebPresentationElement extends AbstractPresentationElement<WebSelector, WebLocation> {
    private String tag;
    private String text;
    private Map<String, String> attributes;
    private List<WebPresentationElement> children;

    public WebPresentationElement(WebSelector selector, WebLocation location) {
        super(selector, location);
    }
}
