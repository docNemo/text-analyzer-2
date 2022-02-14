package com.training.io;

import com.training.exceptions.WriteException;

public interface IWriter {
    void writeChar(char ch) throws WriteException;
    void writeString(String str) throws WriteException;
}
