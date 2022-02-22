package com.training.lexer.tokenbuilder;

import com.training.lexer.token.IToken;

public interface ITokenBuilder {
    void setNameToken(String nameToken);
    void appendToLexeme(char character);
    Character getNextChar();
    void setNextChar(Character nextChar);
    IToken getToken();
}
