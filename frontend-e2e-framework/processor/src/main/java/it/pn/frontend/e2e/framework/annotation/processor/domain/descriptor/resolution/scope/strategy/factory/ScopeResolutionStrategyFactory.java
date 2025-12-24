package it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.resolution.scope.strategy.factory;

import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.resolution.scope.strategy.ScopeResolutionStrategy;
import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.resolution.scope.strategy.subtypesOf.SubtypesOfResolutionStrategy;
import it.pn.frontend.e2e.framework.core.domain.descriptor.ScopeDescriptor;

public final class ScopeResolutionStrategyFactory {

    public ScopeResolutionStrategy forScope(ScopeDescriptor scope) {

        if (scope.subtypesOf != null) {
            return new SubtypesOfResolutionStrategy();
        }

        throw new IllegalArgumentException(
                "Unsupported scope definition"
        );
    }
}

