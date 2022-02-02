package com.training.fileIO;

import com.training.IReader;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReaderChar implements IReader, AutoCloseable {
    BufferedReader reader;

    public FileReaderChar(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public boolean hasChar() throws IOException {
        return reader.ready();
    }

    @Override
    public char readChar() throws IOException {
        char[] buf = new char[1];
        reader.read(buf);
        return buf[0];
    }

    @Override
    public void close() {
        reader = null;
    }
}
