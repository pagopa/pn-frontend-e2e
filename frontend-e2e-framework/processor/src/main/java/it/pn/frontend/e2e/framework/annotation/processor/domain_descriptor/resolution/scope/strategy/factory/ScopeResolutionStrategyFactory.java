package it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.resolution.scope.strategy.factory;

import it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.resolution.scope.strategy.ScopeResolutionStrategy;
import it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.resolution.scope.strategy.subtypesOf.SubtypesOfResolutionStrategy;
import it.pn.frontend.e2e.framework.core.meta.model.Scope;

public final class ScopeResolutionStrategyFactory {

    public ScopeResolutionStrategy forScope(Scope scope) {

        if (scope.subtypesOf != null) {
            return new SubtypesOfResolutionStrategy();
        }

        throw new IllegalArgumentException(
                "Unsupported scope definition"
        );
    }
}

