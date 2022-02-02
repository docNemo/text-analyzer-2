package com.training.fileIO;

import com.training.IWriter;
import com.training.Exceptions.WriteException;
import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriterChar implements IWriter, AutoCloseable {
    private BufferedWriter writer;

    public FileWriterChar(BufferedWriter writer) {
        this.writer = writer;
    }

    @Override
    public void writeChar(char ch) throws WriteException {
        try {
            writer.write(Character.toString(ch));
        } catch (IOException e) {
            throw new WriteException("Error when writing character", e);
        }
    }

    @Override
    public void close() {
        writer = null;
    }
}
