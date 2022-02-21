package com.training.io.file;

import com.training.closable.IClosable;
import com.training.exceptions.CloseException;
import com.training.exceptions.ReaderException;
import com.training.io.IReader;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReaderChar implements IReader, IClosable {
    private BufferedReader reader;
    private int codeChar;

    public FileReaderChar(BufferedReader reader) {

        this.reader = reader;
        codeChar = readSymbol();
    }

    @Override
    public boolean hasChar() throws ReaderException {
        return codeChar != -1;
    }

    @Override
    public char readChar() throws ReaderException {
        try {
            int returnableChar = codeChar;
            codeChar = reader.read();
            return (char) returnableChar;
        } catch (IOException e) {
            throw new ReaderException("Error when reading a character", e);
        }
    }

    @Override
    public void close() throws CloseException {
        if (reader == null) {
            return;
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new CloseException("Error when closing reader", e);
        } finally {
            reader = null;
        }
    }

    private int readSymbol() throws ReaderException {
        try {
            return reader.read();
        } catch (IOException e) {
            throw new ReaderException("Can't read symbol", e);
        }
    }
}
