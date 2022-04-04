package com.training.exceptions;

public class CouldNotCreateStateTransitions extends RuntimeException {
    public CouldNotCreateStateTransitions(final String message, final Throwable cause) {
        super(message, cause);
    }
}
