package com.training.io.file;

import com.training.IClosable;
import com.training.exceptions.CloseException;
import com.training.exceptions.ReaderException;
import com.training.io.IReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileReaderChar implements IReader, IClosable {
    BufferedReader reader;

    public FileReaderChar(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public boolean hasChar() throws ReaderException {
        try {
            return reader.ready();
        } catch (IOException e) {
            throw new ReaderException("error checking read readiness", e);
        }
    }

    @Override
    public char readChar() throws ReaderException {
        try {
            return (char) reader.read();
        } catch (IOException e) {
            throw new ReaderException("Error when reading a character", e);
        }
    }

    @Override
    public void close() throws CloseException {
        if (reader == null)
            return;
        try {
            reader.close();
        } catch (IOException e) {
            throw new CloseException("Error when closing reader", e);
        } finally {
            reader = null;
        }
    }
}
