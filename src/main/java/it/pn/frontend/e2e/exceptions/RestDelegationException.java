package it.pn.frontend.e2e.exceptions;

public class RestDelegationException extends RuntimeException {
    public RestDelegationException(String message) {
        super(message);
    }

    public RestDelegationException(String message, Throwable cause) {
        super(message, cause);
    }
}
