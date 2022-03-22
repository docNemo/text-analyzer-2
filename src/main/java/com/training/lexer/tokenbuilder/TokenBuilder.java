package com.training.lexer.tokenbuilder;

import com.training.lexer.token.IToken;
import com.training.lexer.token.Token;

public class TokenBuilder implements ITokenBuilder {
    private String nameToken;
    private StringBuilder lexeme;
    private StringBuilder postponeBuffer;

    public TokenBuilder() {
        nameToken = "";
        lexeme = new StringBuilder();
        postponeBuffer = new StringBuilder();
    }

    @Override
    public void setNameToken(String nameToken) {
        this.nameToken = nameToken;
    }

    @Override
    public void appendToLexeme(char character) {
        this.lexeme.append(character);
    }

    @Override
    public StringBuilder getPostponeBuffer() {
        return postponeBuffer;
    }

    @Override
    public void appendPostponeBuffer(char character) {
        this.postponeBuffer.append(character);
    }

    @Override
    public void setEmptyPostponeBuffer(){
        this.postponeBuffer = new StringBuilder();
    }

    @Override
    public void newLexeme() {
        nameToken = "";
        lexeme = new StringBuilder();
    }

    @Override
    public IToken getToken() {
        return new Token(nameToken, lexeme.toString());
    }

}
