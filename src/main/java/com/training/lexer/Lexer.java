package com.training.lexer;

import com.training.io.IReader;
import com.training.lexer.token.IToken;
import com.training.lexer.token.Token;

public class Lexer implements ILexer {
    private static final char SPACE = ' ';
    private static final char ASTERISK = '*';
    private static final char OPENING_BRACE = '{';
    private static final char CLOSING_BRACE = '}';
    private static final char SEMICOLON = ';';
    private static final char NEW_LINE = '\n';
    private static final char SLASH = '/';
    private static final String COMMENT_TOKEN = "COMMENT";
    private static final String MULTILINE_COMMENT_TOKEN = "MULTILINE_COMMENT";
    private static final String COMMON_TOKEN = "COMMON";


    private final IReader reader;
    private IToken readedToken;
    private IToken extraToken; //maybe after reading common lexeme

    public Lexer(IReader reader) {
        this.reader = reader;
        readedToken = readToken();
    }

    @Override
    public boolean hasNextToken() {
        return readedToken != null;
    }

    @Override
    public IToken getToken() {
        IToken returnableToken = readedToken;

        if (extraToken != null) {
            readedToken = extraToken;
            extraToken = null;
        } else {
            readedToken = readToken();
        }

        return returnableToken;
    }

    IToken readToken() {
        IToken nextToken = null;
        boolean readyToken = false;

        while (!readyToken && reader.hasChar()) {
            char character = reader.readChar();

            nextToken = switch (character) {
                case SPACE, NEW_LINE -> null;
                case OPENING_BRACE, CLOSING_BRACE, SEMICOLON ->  {
                    readyToken = true;
                    yield new Token(analyse(character), String.valueOf(character));
                }
                case SLASH -> {
                    readyToken = true;
                    if (reader.hasChar()) {
                        char charAfterSlash = reader.readChar();

                        yield switch (charAfterSlash) {
                            case SLASH -> new Token(COMMENT_TOKEN, character + readComment());
                            case ASTERISK -> new Token(MULTILINE_COMMENT_TOKEN, "/*" + readMultilineComment());
                            case NEW_LINE, SPACE -> new Token(COMMON_TOKEN, String.valueOf(character));
                            default -> new Token(COMMON_TOKEN,
                                    character + String.valueOf(charAfterSlash) + readCommon());
                        };
                    } else {
                        yield new Token(COMMON_TOKEN, String.valueOf(character));
                    }
                }
                default -> {
                    readyToken = true;
                    yield new Token(COMMON_TOKEN, character + readCommon());
                }
            };
        }

        return nextToken;
    }

    String readCommon() {
        StringBuilder commonLexeme = new StringBuilder();

        while (reader.hasChar()) {
            char currentChar = reader.readChar();
            if (analyse(currentChar).equals("COMMON_CHAR")) {
                commonLexeme.append(currentChar);
            } else if (currentChar != SPACE && currentChar != NEW_LINE) {
                extraToken = new Token(analyse(currentChar), String.valueOf(currentChar));
                return commonLexeme.toString();
            }
        }
        return commonLexeme.toString();
    }

    String readComment() {
        char currentChar;
        StringBuilder commentLine = new StringBuilder();

        while (reader.hasChar()) {
            currentChar = reader.readChar();
            if (currentChar != '\n') {
                commentLine.append(currentChar);
            } else {
                return commentLine.toString();
            }
        }
        return commentLine.toString();
    }

    String readMultilineComment() {
        StringBuilder comment = new StringBuilder();

        while (
                !comment.substring(comment.length() - 2).equals("*/")
                && reader.hasChar()
        ) {
            comment.append(reader.readChar());
        }
        return comment.toString();
    }

    public String analyse(char character) {
        return switch (character) {
            case NEW_LINE -> "NEW_LINE";
            case SPACE -> "SPACE";
            case OPENING_BRACE -> "OPENING_BRACE";
            case CLOSING_BRACE -> "CLOSING_BRACE";
            case SEMICOLON -> "SEMICOLON";
            default -> "COMMON_CHAR";
        };
    }
}
