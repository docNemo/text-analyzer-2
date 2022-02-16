package com.training.closable;

import com.training.exceptions.CloseException;

public interface IClosable extends AutoCloseable{
    @Override
    void close() throws CloseException;
}
