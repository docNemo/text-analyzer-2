package com.training.formatter;

import com.training.lexer.Lexeme;
import com.training.lexer.Lexer;
import com.training.exceptions.ReaderException;
import com.training.exceptions.UnexpectedLexemeException;
import com.training.exceptions.WriteException;
import com.training.io.IReader;
import com.training.io.IWriter;

public class Formatter implements IFormatter {
    private static final byte NUM_SPACES = 4;
    private static final char SPACE = ' ';
    private static final String SPACE_TOKEN = "SPACE";
    private static final char OPENING_BRACE = '{';
    private static final String OPENING_BRACE_TOKEN = "OPENING_BRACE";
    private static final char CLOSING_BRACE = '}';
    private static final String CLOSING_BRACE_TOKEN = "CLOSING_BRACE";
    private static final char SEMICOLON = ';';
    private static final String SEMICOLON_TOKEN = "SEMICOLON";
    private static final char NEW_LINE = '\n';
    private static final String NEW_LINE_TOKEN = "NEW_LINE";
    private static final String COMMON_CHAR_TOKEN = "COMMON_CHAR";

    private final IReader reader;
    private final IWriter writer;

    public Formatter(IReader reader, IWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void format() throws ReaderException, WriteException {
        boolean wasWord = false;
        boolean newLine = true;
        int nestingLevel = 0;

        Lexer lexer = new Lexer();

        while (reader.hasChar()) {
            char character = reader.readChar();

            Lexeme lexeme = lexer.analyse(character);
            switch (lexeme.getName()) {
                case NEW_LINE_TOKEN -> {
                    //ignore
                }
                case SPACE_TOKEN -> {
                    if (wasWord) {
                        wasWord = false;
                    }
                }
                case OPENING_BRACE_TOKEN -> {
                    writeOpeningBrace(newLine, wasWord, nestingLevel);
                    newLine = true;
                    nestingLevel++;
                }
                case CLOSING_BRACE_TOKEN -> {
                    writeClosingBrace(newLine, nestingLevel);
                    nestingLevel--;
                    newLine = true;
                }
                case SEMICOLON_TOKEN -> {
                    writeSemicolon();
                    newLine = true;
                }
                case COMMON_CHAR_TOKEN -> {
                    writeCommonChar(newLine, wasWord, nestingLevel, lexeme.getLexeme());
                    newLine = false;
                    wasWord = true;
                }
                default -> throw new UnexpectedLexemeException("Unexpected lexeme: " + lexeme);
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

    void writeOpeningBrace(boolean newLine, boolean wasWord, int nestingLevel) throws WriteException {
        if (newLine) {
            writeIndent(nestingLevel);
        } else if (wasWord) {
            writer.writeChar(SPACE);
        }
        writer.writeChar(OPENING_BRACE);
        writer.writeChar(NEW_LINE);
    }

    void writeClosingBrace(boolean newLine, int nestingLevel) throws WriteException {
        if (!newLine) {
            writer.writeChar(NEW_LINE);
        }
        writeIndent(nestingLevel - 1);
        writer.writeChar(CLOSING_BRACE);
        writer.writeChar(NEW_LINE);
    }

    void writeSemicolon() throws WriteException {
        writer.writeChar(SEMICOLON);
        writer.writeChar(NEW_LINE);
    }

    void writeCommonChar(boolean newLine, boolean wasWord, int nestingLevel, char character) throws WriteException {
        if (newLine) {
            writeIndent(nestingLevel);
        } else if (!wasWord) {
            writer.writeChar(SPACE);
        }
        writer.writeChar(character);
    }
}
