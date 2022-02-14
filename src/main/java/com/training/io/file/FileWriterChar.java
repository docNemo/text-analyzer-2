package com.training.io.file;

import com.training.IClosable;
import com.training.exceptions.CloseException;
import com.training.io.IWriter;
import com.training.exceptions.WriteException;
import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriterChar implements IWriter, IClosable {
    private BufferedWriter writer;

    public FileWriterChar(BufferedWriter writer) {
        this.writer = writer;
    }

    @Override
    public void writeChar(char ch) throws WriteException {
        try {
            writer.write(ch);
        } catch (IOException e) {
            throw new WriteException("Error when writing character", e);
        }
    }

    @Override
    public void writeString(String str) throws WriteException {
        try {
            writer.write(str);
        } catch (IOException e) {
            throw new WriteException("Error when writing character", e);
        }
    }

    @Override
    public void close() throws CloseException {
        if (writer == null)
            return;
        try {
            writer.close();
        } catch (IOException e) {
            throw new CloseException("Error when closing reader", e);
        } finally {
            writer = null;
        }
    }
}
