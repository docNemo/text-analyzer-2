package com.training.formatter.command;

import com.training.io.IWriter;
import com.training.lexer.token.IToken;

public interface ICommandFormatter {
    void execute(IToken token, String indent, IWriter writer);
}
