package com.training.lexer;

import com.training.lexer.token.IToken;

public interface ILexer {
    boolean hasNextToken();
    IToken getToken();
}
