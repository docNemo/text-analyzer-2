package com.training.exceptions;

public class CouldNotCreateCommandRepository extends RuntimeException {
    public CouldNotCreateCommandRepository(final String message, final Throwable cause) {
        super(message, cause);
    }
}
