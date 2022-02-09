package com.training;

public class Lexer {
    private static final char SPACE = ' ';
    private static final char OPENING_BRACE = '{';
    private static final char CLOSING_BRACE = '}';
    private static final char SEMICOLON = ';';
    private static final char NEW_LINE = '\n';

    public String[] analyse(char character) {
        String[] lexeme = new String[2];
        lexeme[1] = Character.toString(character);
        switch (character) {
            case NEW_LINE -> {
                lexeme[0] = "NEW_LINE";
            }
            case SPACE -> {
                lexeme[0] = "SPACE";
            }
            case OPENING_BRACE -> {
                lexeme[0] = "OPENING_BRACE";
            }
            case CLOSING_BRACE -> {
                lexeme[0] = "CLOSING_BRACE";
            }
            case SEMICOLON -> {
                lexeme[0] = "SEMICOLON";
            }
            default -> {
                lexeme[0] = "COMMON_CHAR";
            }
        }
        return lexeme;
    }
}
