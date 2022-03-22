package com.training.lexer.tokenbuilder;

import com.training.lexer.token.IToken;

public interface ITokenBuilder {
    void setNameToken(String nameToken);
    void appendToLexeme(char character);
    StringBuilder getPostponeBuffer();
    void appendPostponeBuffer(char character);
    void setEmptyPostponeBuffer();
    void newLexeme();
    IToken getToken();
}
