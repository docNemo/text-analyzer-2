package com.training;

public class ReaderException extends RuntimeException{
    public ReaderException(final String message) {
        super(message);
    }

    public ReaderException(final Throwable cause) {
        super(cause);
    }

    public ReaderException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
