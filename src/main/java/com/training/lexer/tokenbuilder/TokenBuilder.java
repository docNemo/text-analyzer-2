package com.training.lexer.tokenbuilder;

import com.training.lexer.token.IToken;
import com.training.lexer.token.Token;

public class TokenBuilder implements ITokenBuilder {
    private String nameToken;
    private StringBuilder lexeme = new StringBuilder();
    private Character nextChar;

    @Override
    public void setNameToken(String nameToken) {
        this.nameToken = nameToken;
    }

    @Override
    public void appendToLexeme(char character) {
        lexeme.append(character);
    }

    @Override
    public Character getNextChar() {
        return nextChar;
    }

    @Override
    public void setNextChar(Character nextChar) {
        this.nextChar = nextChar;
    }

    @Override
    public IToken getToken() {
        return new Token(nameToken, lexeme.toString());
    }

}
