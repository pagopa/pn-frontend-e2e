package it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.resolution.scope.strategy;

import it.pn.frontend.e2e.framework.core.meta.model.Scope;

import javax.lang.model.element.ElementKind;
import java.util.Set;

public abstract class AbstractScopeResolutionStrategy implements ScopeResolutionStrategy {

    protected Set<ElementKind> acceptedKinds(Scope scope) {
        if (scope.accepts == null || scope.accepts.isEmpty()) {
            return Set.of(ElementKind.INTERFACE); // default
        }
        return scope.accepts;
    }

    protected boolean includeTarget(Scope scope) {
        if(scope.includeTarget == null) return false;
        return scope.includeTarget;
    }
}

