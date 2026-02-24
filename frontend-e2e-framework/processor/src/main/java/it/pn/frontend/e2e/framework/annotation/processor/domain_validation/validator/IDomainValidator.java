package it.pn.frontend.e2e.framework.annotation.processor.domain_validation.validator;

import it.pn.frontend.e2e.framework.core.meta.Descriptor;

import javax.annotation.processing.Messager;

public interface IDomainValidator {
    void validate(Descriptor descriptor, Messager messager);
}
