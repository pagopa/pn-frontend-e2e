package it.frontend.e2e.framework.web.model;


import it.frontend.e2e.framework.core.model.AbstractPresentationElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class WebPresentationElement extends AbstractPresentationElement<XPathSelector, WebLocation> {
    private String tag;
    private String text;
    private Map<String, String> attributes;
    private List<WebPresentationElement> children;

    public WebPresentationElement(XPathSelector selector, WebLocation location) {
        super(selector, location);
    }
}
