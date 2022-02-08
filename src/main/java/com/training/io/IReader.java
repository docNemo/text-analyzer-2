package com.training.io;

import com.training.exceptions.ReaderException;

public interface IReader {
    boolean hasChar() throws ReaderException;
    char readChar() throws ReaderException;
}
