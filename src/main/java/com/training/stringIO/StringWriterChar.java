package com.training.stringIO;

import com.training.IWriter;

public class StringWriterChar implements IWriter, AutoCloseable {
    private StringBuilder stringForWrite;

    public StringWriterChar() {
        stringForWrite = new StringBuilder();
    }

    @Override
    public void writeChar(char ch) {
        stringForWrite.append(ch);
    }

    public String getRecordedString() {
        return stringForWrite.toString();
    }

    @Override
    public void close() throws Exception {

    }
}
