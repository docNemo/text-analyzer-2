package com.training.exceptions;

public class UnexpectedLexemeException extends RuntimeException {
    public UnexpectedLexemeException(final String message) {
        super(message);
    }

    public UnexpectedLexemeException(final Throwable cause) {
        super(cause);
    }

    public UnexpectedLexemeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
