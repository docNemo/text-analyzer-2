package com.training.lexer.command.implementations;

import com.training.lexer.command.ICommand;
import com.training.lexer.tokenbuilder.ITokenBuilder;

public class CreateTokenWhileWithNextChar implements ICommand {
    @Override
    public void execute(char character, ITokenBuilder builder) {
        builder.setPostponeBuffer(String.valueOf(character));
        builder.setNameToken("WHILE");
    }
}
