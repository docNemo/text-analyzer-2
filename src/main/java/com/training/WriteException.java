package com.training;

public class WriteException extends RuntimeException {

    public WriteException(final String message) {
        super(message);
    }

    public WriteException(final Throwable cause) { super(cause); }

    public WriteException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
