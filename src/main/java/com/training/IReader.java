package com.training;

public interface IReader {
    boolean hasChar() throws ReaderException;
    char readChar() throws ReaderException;
}
