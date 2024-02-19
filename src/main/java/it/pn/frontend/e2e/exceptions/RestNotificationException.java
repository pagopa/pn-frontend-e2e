package it.pn.frontend.e2e.exceptions;

public class RestNotificationException extends RuntimeException {
    public RestNotificationException(String message) {
        super(message);
    }

    public RestNotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
