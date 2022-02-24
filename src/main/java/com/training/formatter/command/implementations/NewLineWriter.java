package com.training.formatter.command.implementations;

import com.training.formatter.command.ICommandFormatter;
import com.training.io.IWriter;
import com.training.lexer.token.IToken;

public class NewLineWriter implements ICommandFormatter {
    @Override
    public void execute(IToken token, String indent, IWriter writer) {
        if (token.getLexeme().length() >= 3) {
            writer.writeString("\n\n");
        } else if (token.getLexeme().length() == 2) {
            writer.writeString("\n");
        }
    }
}
