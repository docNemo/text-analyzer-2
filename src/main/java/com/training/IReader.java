package com.training;

import java.io.IOException;

public interface IReader {
    boolean hasChar() throws IOException;
    char readChar() throws IOException;
}
