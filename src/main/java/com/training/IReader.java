package com.training;

import java.io.IOException;

public interface IReader {
    boolean hasChar() throws ReaderException;
    char readChar() throws ReaderException;
}
