package it.pn.frontend.e2e.framework.annotation.processor.rule;

import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.validator.result.ValidationResult;
import it.pn.frontend.e2e.framework.core.domain.descriptor.DomainDescriptor;

import javax.annotation.processing.Messager;

public interface DescriptorRule {
    void apply(DomainDescriptor descriptor, Messager messager, ValidationResult result);
}
