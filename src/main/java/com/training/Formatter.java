package com.training;

public class Formatter {
    private static final byte NUM_SPACES = 4;
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
                case '\n' -> {
                    //ignore
                }
                case ' ' -> {
                    if (wasWord) {
                        wasWord = false;
                    }
                }
                case '{' -> {
                    writeOpeningBrace(newLine, nestingLevel);
                    newLine = true;
                    nestingLevel++;
                }
                case '}' -> {
                    writeClosingBrace(newLine, nestingLevel);
                    nestingLevel--;
                    newLine = true;
                }
                case ';' -> {
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
            writer.writeChar(' ');
            size_indent--;
        }
    }

    public void writeOpeningBrace(boolean newLine, int nestingLevel) throws WriteException {
        writer.writeChar(' ');
        if (newLine) {
            writeIndent(nestingLevel);
        }
        writer.writeChar('{');
        writer.writeChar('\n');
    }

    public void writeClosingBrace(boolean newLine, int nestingLevel) throws WriteException {
        if (!newLine) {
            writer.writeChar('\n');
        }
        writeIndent(nestingLevel - 1);
        writer.writeChar('}');
        writer.writeChar('\n');
    }

    public void writeSemicolon() throws WriteException {
        writer.writeChar(';');
        writer.writeChar('\n');
    }

    public void writeCommonChar(boolean newLine, boolean wasWord, int nestingLevel, char character) throws WriteException {
        if (newLine) {
            writeIndent(nestingLevel);
        } else if (!wasWord) {
            writer.writeChar(' ');
        }
        writer.writeChar(character);
    }
}
