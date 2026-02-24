package it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.resolution.scope.strategy.filter;

import javax.lang.model.element.TypeElement;
import java.util.Set;

public interface ElementFilter {
    boolean accepts(TypeElement element);
}

