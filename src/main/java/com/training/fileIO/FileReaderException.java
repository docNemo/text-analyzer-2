package com.training.fileIO;

public class FileReaderException extends RuntimeException{
    public  FileReaderException(final String message) {
        super(message);
    }

    public  FileReaderException(final Throwable cause) {
        super(cause);
    }

    public  FileReaderException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
