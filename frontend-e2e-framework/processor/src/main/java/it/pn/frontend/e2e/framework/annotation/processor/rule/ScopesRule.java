package it.pn.frontend.e2e.framework.annotation.processor.rule;

import it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.validator.result.ValidationResult;
import it.pn.frontend.e2e.framework.core.meta.Descriptor;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public final class ScopesRule implements DescriptorRule {

    @Override
    public void apply(Descriptor d, Messager messager, ValidationResult result) {
        if (d.scopes == null || d.scopes.isEmpty()) {
            messager.printMessage(
                    Diagnostic.Kind.WARNING,
                    "No scopes defined for domain " + d.domainId
            );
        }
    }
}
