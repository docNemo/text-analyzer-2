package com.training.io.string;

import com.training.io.IReader;

public class StringReaderChar implements IReader {
    private String stringForRead;
    private int indexForRead;

    public StringReaderChar(String stringForRead) {
        this.stringForRead = stringForRead;
        this.indexForRead = 0;
    }

    @Override

    public boolean hasChar() {
        return indexForRead != stringForRead.length();
    }

    @Override
    public char readChar() {
            char returnableChar = stringForRead.charAt(indexForRead);
            indexForRead++;
            return returnableChar;
    }


}
