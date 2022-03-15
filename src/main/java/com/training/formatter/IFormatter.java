package com.training.formatter;

import com.training.io.IWriter;
import com.training.lexer.ILexer;

public interface IFormatter {
    void format(ILexer lexer, IWriter writer);
}
