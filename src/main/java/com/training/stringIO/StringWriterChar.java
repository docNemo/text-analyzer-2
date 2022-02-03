package com.training.stringIO;

import com.training.IWriter;

public class StringWriterChar implements IWriter {
    private StringBuilder stringForWrite;

    public StringWriterChar(StringBuilder stringForWrite) {
        this.stringForWrite = stringForWrite;
    }

    @Override
    public void writeChar(char ch) {
        stringForWrite.append(ch);
    }
}
