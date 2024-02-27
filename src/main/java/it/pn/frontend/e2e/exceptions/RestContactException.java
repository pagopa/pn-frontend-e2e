package it.pn.frontend.e2e.exceptions;

public class RestContactException extends RuntimeException {
    public RestContactException(String message) {
        super(message);
    }

    public RestContactException(String message, Throwable cause) {
        super(message, cause);
    }

}
