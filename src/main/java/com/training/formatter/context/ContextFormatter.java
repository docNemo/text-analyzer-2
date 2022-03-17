package com.training.formatter.context;

import com.training.io.IWriter;

public class ContextFormatter implements IContextFormatter {
    private static final byte NUM_SPACES = 4;

    private final IWriter writer;
    private int nestingLevel;

    public ContextFormatter(IWriter writer) {
        this.writer = writer;
        this.nestingLevel = 0;
    }

    @Override
    public void incrementIndent() {
        nestingLevel++;
    }

    @Override
    public void decrementIndent() {
        if (nestingLevel > 0) {
            nestingLevel--;
        }
    }

    @Override
    public void writeIndent() {
        writer.writeString(" ".repeat(nestingLevel * NUM_SPACES));
    }

    @Override
    public void writeChar(char ch) {
        writer.writeChar(ch);
    }

    @Override
    public void writeString(String str) {
        writer.writeString(str);
    }
}
