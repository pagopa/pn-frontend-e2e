package it.pn.frontend.e2e.framework.annotation.processor.rule;

import it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.validator.result.ValidationResult;
import it.pn.frontend.e2e.framework.core.meta.Descriptor;

import javax.annotation.processing.Messager;

public interface DescriptorRule {
    void apply(Descriptor descriptor, Messager messager, ValidationResult result);
}
