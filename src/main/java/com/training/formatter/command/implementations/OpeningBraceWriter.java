package com.training.formatter.command.implementations;

import com.training.formatter.command.ICommandFormatter;
import com.training.io.IWriter;
import com.training.lexer.token.IToken;

public class OpeningBraceWriter implements ICommandFormatter {
    @Override
    public void execute(IToken token, String indent, IWriter writer) {
        writer.writeChar(' ');
        writer.writeString(token.getLexeme());
        writer.writeChar('\n');
    }
}
