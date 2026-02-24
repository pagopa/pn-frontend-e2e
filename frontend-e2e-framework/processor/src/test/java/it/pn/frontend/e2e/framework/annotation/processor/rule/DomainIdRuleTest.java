package it.pn.frontend.e2e.framework.annotation.processor.rule;

import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.validator.result.ValidationResult;
import it.pn.frontend.e2e.framework.core.meta.Descriptor;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class DomainIdRuleTest {

    private final DomainIdRule rule = new DomainIdRule();

    @Test
    void shouldReportErrorWhenDomainIdIsNull() {
        Descriptor d = new Descriptor();
        d.domainId = null;

        Messager messager = mock(Messager.class);
        ValidationResult result = new ValidationResult();

        rule.apply(d, messager, result);

        verify(messager).printMessage(
                eq(Diagnostic.Kind.ERROR),
                contains("domainId is missing")
        );
        assert(result.hasErrors());
    }

    @Test
    void shouldReportErrorWhenDomainIdIsBlank() {
        Descriptor d = new Descriptor();
        d.domainId = "   ";

        Messager messager = mock(Messager.class);
        ValidationResult result = new ValidationResult();

        rule.apply(d, messager, result);

        verify(messager).printMessage(
                eq(Diagnostic.Kind.ERROR),
                anyString()
        );
        assert(result.hasErrors());
    }

    @Test
    void shouldPassWhenDomainIdIsValid() {
        Descriptor d = new Descriptor();
        d.domainId = "payments";

        Messager messager = mock(Messager.class);
        ValidationResult result = new ValidationResult();

        rule.apply(d, messager, result);

        verifyNoInteractions(messager);
        assert(!result.hasErrors());
    }
}
