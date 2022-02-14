package com.training.formatter;

import com.training.lexer.ILexer;
import com.training.lexer.token.IToken;
import com.training.exceptions.ReaderException;
import com.training.exceptions.UnexpectedLexemeException;
import com.training.exceptions.WriteException;
import com.training.io.IWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Formatter implements IFormatter {

    private static final byte NUM_SPACES = 4;
    private static final char SPACE = ' ';
    private static final String OPENING_BRACE_TOKEN = "OPENING_BRACE";
    private static final String CLOSING_BRACE_TOKEN = "CLOSING_BRACE";
    private static final String SEMICOLON_TOKEN = "SEMICOLON";
    private static final char NEW_LINE = '\n';
    private static final String COMMENT_TOKEN = "COMMENT";
    private static final String MULTILINE_COMMENT_TOKEN = "MULTILINE_COMMENT";
    private static final String COMMON_TOKEN = "COMMON";

    private final ILexer lexer;
    private final IWriter writer;

    public Formatter(ILexer lexer, IWriter writer) {
        this.lexer = lexer;
        this.writer = writer;
    }

    public void format() throws ReaderException, WriteException {
        boolean wasWord = false;
        boolean newLine = true;
        int nestingLevel = 0;

        while (lexer.hasNextToken()) {
            IToken token = lexer.getToken();

            switch (token.getName()) {
                case OPENING_BRACE_TOKEN -> {
                    writeOpeningBrace(newLine, wasWord, nestingLevel, token.getLexeme());
                    newLine = true;
                    nestingLevel++;
                }
                case CLOSING_BRACE_TOKEN -> {
                    writeClosingBrace(newLine, nestingLevel, token.getLexeme());
                    nestingLevel--;
                    newLine = true;
                }
                case SEMICOLON_TOKEN -> {
                    writeSemicolon(token.getLexeme());
                    newLine = true;
                }
                case COMMENT_TOKEN, MULTILINE_COMMENT_TOKEN -> {
                    writer.writeString(token.getLexeme());
                }
                case COMMON_TOKEN -> {
                    writeCommonChar(newLine, wasWord, nestingLevel, token.getLexeme());
                    newLine = false;
                    wasWord = true;
                }
                default -> {
                    throw new UnexpectedLexemeException("Unexpected lexeme: " + token);
                }
            }
        }
    }

    void writeIndent(int nestingLevel) throws WriteException {
        int size_indent = NUM_SPACES * nestingLevel;
        while (size_indent > 0) {
            writer.writeChar(SPACE);
            size_indent--;
        }
    }

    void writeOpeningBrace(boolean newLine, boolean wasWord, int nestingLevel, String lexeme) throws WriteException {
        if (newLine) {
            writeIndent(nestingLevel);
        } else if (wasWord) {
            writer.writeChar(SPACE);
        }
        writer.writeString(lexeme);
        writer.writeChar(NEW_LINE);
    }

    void writeClosingBrace(boolean newLine, int nestingLevel, String lexeme) throws WriteException {
        if (!newLine) {
            writer.writeChar(NEW_LINE);
        }
        writeIndent(nestingLevel - 1);
        writer.writeString(lexeme);
        writer.writeChar(NEW_LINE);
    }

    void writeSemicolon(String lexeme) throws WriteException {
        writer.writeString(lexeme);
        writer.writeChar(NEW_LINE);
    }

    void writeCommonChar(boolean newLine, boolean wasWord, int nestingLevel, String lexeme) throws WriteException {
        if (newLine) {
            writeIndent(nestingLevel);
        } else if (!wasWord) {
            writer.writeChar(SPACE);
        }
        writer.writeString(lexeme);
    }
}
