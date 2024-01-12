package it.pn.frontend.e2e.exceptions;

public class CustomHttpException extends RuntimeException {
    public CustomHttpException(String message) {
        super(message);
    }

    public CustomHttpException(String message, Throwable cause) {
        super(message, cause);
    }
}
