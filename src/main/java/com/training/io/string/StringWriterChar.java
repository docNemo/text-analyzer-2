package com.training.io.string;

import com.training.exceptions.WriteException;
import com.training.io.IWriter;

public class StringWriterChar implements IWriter {
    private StringBuilder stringForWrite;

    public StringWriterChar(StringBuilder stringForWrite) {
        this.stringForWrite = stringForWrite;
    }

    @Override
    public void writeChar(char ch) {
        stringForWrite.append(ch);
    }

    @Override
    public void writeString(String str) throws WriteException {
        stringForWrite.append(str);
    }
}
