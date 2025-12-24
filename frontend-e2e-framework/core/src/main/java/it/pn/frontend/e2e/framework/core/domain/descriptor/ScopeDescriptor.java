package it.pn.frontend.e2e.framework.core.domain.descriptor;

import javax.lang.model.element.ElementKind;
import java.util.Set;

public final class ScopeDescriptor {
    public String subtypesOf;
    public String annotatedWith;
    public Set<ElementKind> accepts;
    public Boolean includeTarget;
}

