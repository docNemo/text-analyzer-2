package com.training.lexer.command.implementations;

import com.training.lexer.CharAnalyzer;
import com.training.lexer.command.ICommand;
import com.training.lexer.tokenbuilder.ITokenBuilder;

public class CreateToken implements ICommand {
    @Override
    public void execute(char character, ITokenBuilder builder) { // { } ; "
        builder.setNameToken(CharAnalyzer.analyse(character));
        builder.appendToLexeme(character);
    }
}
