package com.training;

import com.training.Exceptions.ReaderException;
import com.training.Exceptions.WriteException;

public class Formatter {
    private static final byte NUM_SPACES = 4;
    private static final char SPACE = ' ';
    private static final char OPENING_BRACE = '{';
    private static final char CLOSING_BRACE = '}';
    private static final char SEMICOLON = ';';
    private static final char NEW_LINE = '\n';

    IReader reader;
    IWriter writer;

    public Formatter(IReader reader, IWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void format() throws ReaderException, WriteException {
        boolean wasWord = false;
        boolean newLine = true;
        int nestingLevel = 0;

        while (reader.hasChar()) {
            char character = reader.readChar();

            switch (character) {
                case NEW_LINE -> {
                    //ignore
                }
                case SPACE -> {
                    if (wasWord) {
                        wasWord = false;
                    }
                }
                case OPENING_BRACE -> {
                    writeOpeningBrace(newLine, wasWord, nestingLevel);
                    newLine = true;
                    nestingLevel++;
                }
                case CLOSING_BRACE -> {
                    writeClosingBrace(newLine, nestingLevel);
                    nestingLevel--;
                    newLine = true;
                }
                case SEMICOLON -> {
                    writeSemicolon();
                    newLine = true;
                }
                default -> {
                    writeCommonChar(newLine, wasWord, nestingLevel, character);
                    newLine = false;
                    wasWord = true;
                }
            }
        }
    }

    public void writeIndent(int nestingLevel) throws WriteException {
        int size_indent = NUM_SPACES * nestingLevel;
        while (size_indent > 0) {
            writer.writeChar(SPACE);
            size_indent--;
        }
    }

    public void writeOpeningBrace(boolean newLine, boolean wasWord, int nestingLevel) throws WriteException {
        if (wasWord) {
            writer.writeChar(SPACE);
        }
        if (newLine) {
            writeIndent(nestingLevel);
        }
        writer.writeChar(OPENING_BRACE);
        writer.writeChar(NEW_LINE);
    }

    public void writeClosingBrace(boolean newLine, int nestingLevel) throws WriteException {
        if (!newLine) {
            writer.writeChar(NEW_LINE);
        }
        writeIndent(nestingLevel - 1);
        writer.writeChar(CLOSING_BRACE);
        writer.writeChar(NEW_LINE);
    }

    public void writeSemicolon() throws WriteException {
        writer.writeChar(SEMICOLON);
        writer.writeChar(NEW_LINE);
    }

    public void writeCommonChar(boolean newLine, boolean wasWord, int nestingLevel, char character) throws WriteException {
        if (newLine) {
            writeIndent(nestingLevel);
        } else if (!wasWord) {
            writer.writeChar(SPACE);
        }
        writer.writeChar(character);
    }
}
