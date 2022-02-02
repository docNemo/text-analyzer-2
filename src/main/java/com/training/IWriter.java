package com.training;

import com.training.Exceptions.WriteException;

public interface IWriter {
    void writeChar(char ch) throws WriteException;
}
