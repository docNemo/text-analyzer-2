package com.training.lexer.tokenbuilder;

import com.training.lexer.token.IToken;

public interface ITokenBuilder {
    void setNameToken(String nameToken);
    void appendToLexeme(char character);
    String getPostponeBuffer();
    void setPostponeBuffer(String postponeStr);
    void setEmptyPostponeBuffer();
    void newLexeme();
    void deleteLastChar();
    int lengthLexeme();
    IToken getToken();
}
