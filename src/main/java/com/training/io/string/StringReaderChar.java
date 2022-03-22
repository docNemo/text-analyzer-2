package com.training.io.string;

import com.training.exceptions.ReaderException;
import com.training.io.IReader;

public class StringReaderChar implements IReader {
    private final String stringForRead;
    private int indexForRead;

    public StringReaderChar(String stringForRead) {
        this.stringForRead = stringForRead;
        this.indexForRead = 0;
    }

    @Override

    public boolean hasChar() {
        return indexForRead < stringForRead.length();
    }

    @Override
    public char readChar() {
        try {
            return stringForRead.charAt(indexForRead++);
        } catch (IndexOutOfBoundsException e) {
            throw new ReaderException("Char at " + indexForRead + " can not read", e);
        }
    }


}
