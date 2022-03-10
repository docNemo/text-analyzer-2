package com.training.formatter.context;

public interface IContextFormatter {
    void incrementIndent();
    void decrementIndent();
    void writeIndent();
    void writeChar(char ch);
    void writeString(String str);
}
