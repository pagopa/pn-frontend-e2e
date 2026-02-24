package it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.validator;

import it.pn.frontend.e2e.framework.core.meta.Descriptor;

import javax.annotation.processing.Messager;

public interface DomainDescriptorValidator {
    void validate(Descriptor descriptor, Messager messager);
}
