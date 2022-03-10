package com.training.formatter.command;

import com.training.formatter.context.IContextFormatter;
import com.training.lexer.token.IToken;

public interface ICommandFormatter {
    void execute(IToken token, IContextFormatter context);
}
