package it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.resolution.scope.strategy;

import it.pn.frontend.e2e.framework.core.domain.descriptor.DomainDescriptor;
import it.pn.frontend.e2e.framework.core.domain.descriptor.ScopeDescriptor;

import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Set;

public interface ScopeResolutionStrategy {
    Set<TypeElement> resolve(
            ScopeDescriptor scope,
            DomainDescriptor domain,
            RoundEnvironment roundEnv,
            Types types,
            Elements elements,
            Messager messager
    );
}

