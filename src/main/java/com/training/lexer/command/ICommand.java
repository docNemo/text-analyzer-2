package com.training.lexer.command;

import com.training.lexer.tokenbuilder.ITokenBuilder;

public interface ICommand {
    void execute(char character, ITokenBuilder builder);
}
