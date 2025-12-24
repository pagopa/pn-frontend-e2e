package it.pn.frontend.e2e.framework.annotation.processor.validation;

import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.validator.CompositeDomainDescriptorValidator;
import it.pn.frontend.e2e.framework.annotation.processor.rule.ConstraintsRule;
import it.pn.frontend.e2e.framework.annotation.processor.rule.DomainIdRule;
import it.pn.frontend.e2e.framework.annotation.processor.rule.ScopesRule;
import it.pn.frontend.e2e.framework.core.domain.descriptor.DomainDescriptor;
import org.junit.jupiter.api.Test;

import javax.annotation.processing.Messager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class CompositeDomainDescriptorValidatorTest {

    @Test
    void shouldThrowExceptionWhenAnyRuleReportsError() {
        DomainDescriptor d = new DomainDescriptor();
        d.domainId = null;
        d.schemaVersion = 1;

        CompositeDomainDescriptorValidator validator =
                new CompositeDomainDescriptorValidator(List.of(
                        new DomainIdRule()
                ));

        Messager messager = mock(Messager.class);

        assertThrows(IllegalStateException.class,
                () -> validator.validate(d, messager));
    }

    @Test
    void shouldPassWhenOnlyWarningsOrNotesOccur() {
        DomainDescriptor d = new DomainDescriptor();
        d.domainId = "test";
        d.schemaVersion = 1;
        d.scopes = null; // warning
        d.constraints = null; // note

        CompositeDomainDescriptorValidator validator =
                new CompositeDomainDescriptorValidator(List.of(
                        new ScopesRule(),
                        new ConstraintsRule()
                ));

        Messager messager = mock(Messager.class);

        // NON deve lanciare
        validator.validate(d, messager);
    }
}
