package com.training.exceptions;

public class AppException extends RuntimeException {
    public AppException(final String message) {
        super(message);
    }

    public AppException(final Throwable cause) {
        super(cause);
    }

    public AppException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
