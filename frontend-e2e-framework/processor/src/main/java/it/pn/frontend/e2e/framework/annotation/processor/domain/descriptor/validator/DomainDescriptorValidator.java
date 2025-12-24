package it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.validator;

import it.pn.frontend.e2e.framework.core.domain.descriptor.DomainDescriptor;

import javax.annotation.processing.Messager;

public interface DomainDescriptorValidator {
    void validate(DomainDescriptor descriptor, Messager messager);
}
