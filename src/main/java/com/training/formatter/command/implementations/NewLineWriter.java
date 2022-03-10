package com.training.formatter.command.implementations;

import com.training.formatter.command.ICommandFormatter;
import com.training.formatter.context.IContextFormatter;
import com.training.lexer.token.IToken;

public class NewLineWriter implements ICommandFormatter {
    @Override
    public void execute(IToken token, IContextFormatter context) {
        if (token.getLexeme().length() >= 3) {
            context.writeString("\n\n");
        } else if (token.getLexeme().length() == 2) {
            context.writeString("\n");
        }
    }
}
