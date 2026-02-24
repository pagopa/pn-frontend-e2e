package it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.validator.factory;

import it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.validator.DomainDescriptorValidator;

public interface DomainDescriptorValidatorFactory {
    DomainDescriptorValidator forSchema(int schemaVersion);
}
