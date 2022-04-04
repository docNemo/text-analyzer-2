package com.training.exceptions;

public class CouldNotCreateCommand extends RuntimeException {
    public CouldNotCreateCommand(final String message, final Throwable cause) {
        super(message, cause);
    }
}
