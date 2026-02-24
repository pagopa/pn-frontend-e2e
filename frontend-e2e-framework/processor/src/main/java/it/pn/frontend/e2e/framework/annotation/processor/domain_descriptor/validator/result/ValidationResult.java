package it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.validator.result;

public final class ValidationResult {

    private boolean hasErrors = false;

    public void error() {
        hasErrors = true;
    }

    public boolean hasErrors() {
        return hasErrors;
    }
}

