package com.training.lexer;

import com.training.lexer.token.IToken;
import com.training.lexer.token.Token;

public interface ILexer {
    boolean hasNextToken();
    IToken getToken();
}
