package it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.resolution.scope.strategy;

import it.pn.frontend.e2e.framework.core.domain.descriptor.ScopeDescriptor;

import javax.lang.model.element.ElementKind;
import java.util.Set;

public abstract class AbstractScopeResolutionStrategy implements ScopeResolutionStrategy {

    protected Set<ElementKind> acceptedKinds(ScopeDescriptor scope) {
        if (scope.accepts == null || scope.accepts.isEmpty()) {
            return Set.of(ElementKind.INTERFACE); // default
        }
        return scope.accepts;
    }

    protected boolean includeTarget(ScopeDescriptor scope) {
        if(scope.includeTarget == null) return false;
        return scope.includeTarget;
    }
}

