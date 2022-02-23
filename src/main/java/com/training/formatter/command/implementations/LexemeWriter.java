package com.training.formatter.command.implementations;

import com.training.formatter.command.ICommandFormatter;
import com.training.io.IWriter;
import com.training.lexer.token.IToken;
import com.training.lexer.tokenbuilder.ITokenBuilder;

public class LexemeWriter implements ICommandFormatter {
    @Override
    public void execute(IToken token, String indent, IWriter writer) {
        writer.writeString(indent + token.getLexeme());
    }
}
