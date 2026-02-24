package it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.validator.factory;

import it.pn.frontend.e2e.framework.annotation.processor.rule.ConstraintsRule;
import it.pn.frontend.e2e.framework.annotation.processor.rule.DomainIdRule;
import it.pn.frontend.e2e.framework.annotation.processor.rule.SchemaVersionRule;
import it.pn.frontend.e2e.framework.annotation.processor.rule.ScopesRule;
import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.validator.CompositeDomainDescriptorValidator;
import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.validator.DomainDescriptorValidator;

import javax.tools.Diagnostic;
import java.util.List;

public final class DefaultDomainDescriptorValidatorFactory
        implements DomainDescriptorValidatorFactory {

    @Override
    public DomainDescriptorValidator forSchema(int schemaVersion) {
        return switch (schemaVersion) {
            case 1 -> v1();
            default -> unsupported(schemaVersion);
        };
    }

    private DomainDescriptorValidator v1() {
        return new CompositeDomainDescriptorValidator(List.of(
                new SchemaVersionRule(1),
                new DomainIdRule(),
                new ScopesRule(),
                new ConstraintsRule()
        ));
    }

    private DomainDescriptorValidator unsupported(int schemaVersion) {
        return (d, messager) -> messager.printMessage(
                Diagnostic.Kind.ERROR,
                "Unsupported schemaVersion " + schemaVersion +
                        " in domain " + d.domainId
        );
    }
}
