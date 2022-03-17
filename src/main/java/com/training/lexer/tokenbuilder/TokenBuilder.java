package com.training.lexer.tokenbuilder;

import com.training.lexer.token.IToken;
import com.training.lexer.token.Token;

public class TokenBuilder implements ITokenBuilder {
    private String nameToken;
    private StringBuilder lexeme;
    private String postponeBuffer;

    public TokenBuilder() {
        nameToken = "";
        lexeme = new StringBuilder();
        postponeBuffer = "";
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
    public String getPostponeBuffer() {
        return postponeBuffer;
    }

    @Override
    public void setPostponeBuffer(String postponeStr) {
        this.postponeBuffer = postponeStr;
    }

    @Override
    public void setEmptyPostponeBuffer(){
        this.postponeBuffer = "";
    }

    public void newLexeme() {
        nameToken = "";
        lexeme = new StringBuilder();
    }

    @Override
    public void deleteLastChar() {
        this.lexeme.deleteCharAt(lexeme.length() - 1);
    }

    @Override
    public int lengthLexeme() {
        return lexeme.length();
    }

    @Override
    public IToken getToken() {
        return new Token(nameToken, lexeme.toString());
    }

}
