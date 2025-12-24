package it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.validator;


import it.pn.frontend.e2e.framework.annotation.processor.rule.DescriptorRule;
import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.validator.result.ValidationResult;
import it.pn.frontend.e2e.framework.core.domain.descriptor.DomainDescriptor;

import javax.annotation.processing.Messager;
import java.util.List;

public final class CompositeDomainDescriptorValidator implements DomainDescriptorValidator {

    private final List<DescriptorRule> rules;

    public CompositeDomainDescriptorValidator(List<DescriptorRule> rules) {
        this.rules = List.copyOf(rules); // immutabile
    }

    @Override
    public void validate(DomainDescriptor descriptor, Messager messager) {
        ValidationResult result = new ValidationResult();
        for (DescriptorRule rule : rules) {
            rule.apply(descriptor, messager, result);
        }
        if (result.hasErrors()) {
            throw new IllegalStateException("Descriptor validation failed");
        }
    }

}
