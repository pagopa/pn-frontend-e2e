package it.pn.frontend.e2e.framework.annotation.processor.rule;

import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.validator.result.ValidationResult;
import it.pn.frontend.e2e.framework.core.meta.Descriptor;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public final class DomainIdRule implements DescriptorRule {

    @Override
    public void apply(Descriptor d, Messager messager, ValidationResult result) {
        if (d.domainId == null || d.domainId.isBlank()) {
            messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "domainId is missing in domain descriptor"
            );
            result.error();
        }
    }
}
