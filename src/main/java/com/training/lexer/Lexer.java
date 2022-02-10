package com.training.lexer;

public class Lexer {
    private static final char SPACE = ' ';
    private static final char OPENING_BRACE = '{';
    private static final char CLOSING_BRACE = '}';
    private static final char SEMICOLON = ';';
    private static final char NEW_LINE = '\n';

    public Lexeme analyse(char character) {
        String lexemeName = switch (character) {
            case NEW_LINE ->"NEW_LINE";
            case SPACE -> "SPACE";
            case OPENING_BRACE -> "OPENING_BRACE";
            case CLOSING_BRACE -> "CLOSING_BRACE";
            case SEMICOLON -> "SEMICOLON";
            default -> "COMMON_CHAR";
        };
        return new Lexeme(lexemeName, character);
    }
}
