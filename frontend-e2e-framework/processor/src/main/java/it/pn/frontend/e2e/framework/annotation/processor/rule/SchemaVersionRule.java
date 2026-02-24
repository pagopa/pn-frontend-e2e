package it.pn.frontend.e2e.framework.annotation.processor.rule;

import it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.validator.result.ValidationResult;
import it.pn.frontend.e2e.framework.core.meta.Descriptor;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public final class SchemaVersionRule implements DescriptorRule {

    private final int supportedVersion;

    public SchemaVersionRule(int supportedVersion) {
        this.supportedVersion = supportedVersion;
    }

    @Override
    public void apply(Descriptor d, Messager messager, ValidationResult result) {
        if (d.schemaVersion != supportedVersion) {
            messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Unsupported schemaVersion " + d.schemaVersion +
                            " in domain " + d.domainId
            );
            result.error();
        }
    }
}
