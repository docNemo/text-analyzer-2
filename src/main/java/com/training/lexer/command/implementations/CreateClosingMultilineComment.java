package com.training.lexer.command.implementations;

import com.training.lexer.command.ICommand;
import com.training.lexer.tokenbuilder.ITokenBuilder;

public class CreateClosingMultilineComment implements ICommand {
    @Override
    public void execute(char character, ITokenBuilder builder) {
        if (builder.lengthLexeme() == 0) {
            builder.appendToLexeme('*');
            builder.appendToLexeme(character);
            builder.setNameToken("CLOSING_MULTILINE_COMMENT");
        } else {
            builder.setPostponeBuffer("*/");
        }
    }
}
