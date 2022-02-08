package com.training.io.string;

import com.training.io.IReader;

public class StringReaderChar implements IReader {
    private String stringForRead;

    public StringReaderChar(String stringForRead) {
        this.stringForRead = stringForRead;
    }

    @Override
    public boolean hasChar() {
        return !stringForRead.isEmpty();
    }

    @Override
    public char readChar() {
        char returnableChar = stringForRead.charAt(0);
        stringForRead = stringForRead.substring(1);
        return returnableChar;
    }
}
