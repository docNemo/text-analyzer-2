package com.training.lexer.command.implementations;

import com.training.lexer.command.ICommand;
import com.training.lexer.tokenbuilder.ITokenBuilder;

public class CreateTokenSemicolon implements ICommand {
    @Override
    public void execute(char character, ITokenBuilder builder) {
        builder.setNameToken("SEMICOLON");
        builder.appendToLexeme(character);
    }
}
