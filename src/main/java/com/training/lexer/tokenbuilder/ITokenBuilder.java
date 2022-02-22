package com.training.lexer.tokenbuilder;

import com.training.lexer.token.IToken;

public interface ITokenBuilder {
    void setNameToken(String nameToken);
    void appendToLexeme(char character);
    char getNextChar();
    void setNextChar(char nextChar);
    void setNextToken(String name, String lexeme);
    IToken getNextToken();
    void deleteLastChar();
    int lengthLexeme();
    IToken getToken();
}
