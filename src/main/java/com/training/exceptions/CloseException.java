package com.training.exceptions;

public class CloseException extends Exception {
    public CloseException(final String message) {
        super(message);
    }

    public CloseException(final Throwable cause) {
        super(cause);
    }

    public CloseException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
