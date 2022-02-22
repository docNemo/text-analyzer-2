package com.training.lexer.tokenbuilder;

import com.training.lexer.token.IToken;
import com.training.lexer.token.Token;

public class TokenBuilder implements ITokenBuilder {
    private String nameToken;
    private StringBuilder lexeme = new StringBuilder();
    private IToken nextToken;
    private char nextChar;

    @Override
    public void setNameToken(String nameToken) {
        this.nameToken = nameToken;
    }

    @Override
    public void appendToLexeme(char character) {
        lexeme.append(character);
    }

    @Override
    public char getNextChar() {
        return nextChar;
    }

    @Override
    public void setNextChar(char nextChar) {
        this.nextChar = nextChar;
    }

    @Override
    public void setNextToken(String name, String lexeme) {
        this.nextToken = new Token(name, lexeme);
    }

    @Override
    public IToken getNextToken() {
        return nextToken;
    }

    @Override
    public void deleteLastChar() {
        lexeme.deleteCharAt(lexeme.length() - 1);
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
