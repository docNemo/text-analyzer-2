package com.training.fileIO;

import com.training.IWriter;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriterChar implements IWriter, AutoCloseable {
    private BufferedWriter writer;

    public FileWriterChar(BufferedWriter writer) {
        this.writer = writer;
    }

    @Override
    public void writeChar(char ch) throws IOException {
        char[] chars = {ch};
        writer.write(Character.toString(ch));
    }

    @Override
    public void close() throws Exception {
        writer = null;
    }
}
