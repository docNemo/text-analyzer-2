package com.training.formatter.command.implementations;

import com.training.formatter.command.ICommandFormatter;
import com.training.io.IWriter;
import com.training.lexer.token.IToken;

public class ClosingBraceWriter implements ICommandFormatter {
    @Override
    public void execute(IToken token, String indent, IWriter writer) {
        writer.writeChar('\n');
        writer.writeString(token.getLexeme());
        writer.writeChar('\n');
    }
}
