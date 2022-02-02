package com.training;

import com.training.Exceptions.ReaderException;

public interface IReader {
    boolean hasChar() throws ReaderException;
    char readChar() throws ReaderException;
}
