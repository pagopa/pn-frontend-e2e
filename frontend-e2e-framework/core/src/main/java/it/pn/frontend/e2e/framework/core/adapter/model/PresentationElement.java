package it.pn.frontend.e2e.framework.core.adapter.model;

import it.pn.frontend.e2e.framework.core.model.locator.Locator;
import it.pn.frontend.e2e.framework.core.model.locator.LocatorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PresentationElement {

    private Locator<? extends LocatorType> locator;
    private String elementType;
    private String name;
    private boolean displayed;
    private boolean enabled;
    private String text;

    public PresentationElement(Locator<? extends LocatorType> locator, String elementType, String name) {
        this.locator = locator;
        this.elementType = elementType;
        this.name = name;
        this.displayed = false;
        this.enabled = false;
    }
}
