package com.training.lexer;

public class CharAnalyzer {
    private static final char SPACE = ' ';
    private static final char ASTERISK = '*';
    private static final char OPENING_BRACE = '{';
    private static final char CLOSING_BRACE = '}';
    private static final char SEMICOLON = ';';
    private static final char NEW_LINE = '\n';
    private static final char SLASH = '/';
    private static final char DOUBLE_QUOTE = '"';

    public static String analyse(char character) {
        return switch (character) {
            case NEW_LINE -> "NEW_LINE";
            case SPACE -> "SPACE";
            case OPENING_BRACE -> "OPENING_BRACE";
            case CLOSING_BRACE -> "CLOSING_BRACE";
            case SEMICOLON -> "SEMICOLON";
            case ASTERISK -> "ASTERISK";
            case SLASH -> "SLASH";
            case DOUBLE_QUOTE -> "DOUBLE_QUOTE";
            default -> "COMMON_CHAR";
        };
    }
}
