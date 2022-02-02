package com.training.fileIO;

import com.training.Exceptions.ReaderException;
import com.training.IReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileReaderChar implements IReader, AutoCloseable {
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
            char[] buf = new char[1];
            reader.read(buf);
            return buf[0];
        } catch (IOException e) {
            throw new ReaderException("Error when reading a character", e);
        }
    }

    @Override
    public void close() throws RuntimeException {
        if (reader == null)
            return;
        try {
            reader.close();
        } catch (IOException e) {
            throw new ReaderException("Error when closing reader", e);
        } finally {
            reader = null;
        }
    }
}
