package com.training.formatter.command.implementations;

import com.training.formatter.command.ICommandFormatter;
import com.training.formatter.context.IContextFormatter;
import com.training.lexer.token.IToken;

public class OpeningBraceWriter implements ICommandFormatter {
    @Override
    public void execute(IToken token, IContextFormatter context) {
        context.writeString(token.getLexeme());
        context.incrementIndent();
    }
}
