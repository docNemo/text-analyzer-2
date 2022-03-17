package com.training.lexer.command.implementations;

import com.training.lexer.command.ICommand;
import com.training.lexer.tokenbuilder.ITokenBuilder;

public class CreateTokenCommonAddedAsteriskWithNextChar implements ICommand {
    @Override
    public void execute(char character, ITokenBuilder builder) {
        builder.appendToLexeme('*');
        builder.setPostponeBuffer(String.valueOf(character));
    }
}
