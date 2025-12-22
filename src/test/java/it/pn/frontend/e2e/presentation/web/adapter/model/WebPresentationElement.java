package it.pn.frontend.e2e.presentation.web.adapter.model;

import it.pn.frontend.e2e.presentation.core.adapter.model.PresentationElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class WebPresentationElement extends PresentationElement {
    private String tag;
    private String text;
    private Map<String, String> attributes;
    private List<WebPresentationElement> children;
}
