package com.training;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.training.stringIO.StringReaderChar;
import com.training.stringIO.StringWriterChar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFormatter {
    Formatter formatter;
    IWriter writer;
    StringBuilder stringInWriter;

    @BeforeEach
    public void createFormatter() {
        IReader reader = new StringReaderChar("");
        stringInWriter = new StringBuilder();
        writer = new StringWriterChar(stringInWriter);
        formatter = new Formatter(reader, writer);
    }

    @Test
    public void testWriteIndent() {
        String extended = "";
        int nestinglevel = -1;
        formatter.writeIndent(nestinglevel);
        String actual = stringInWriter.toString();
        assertEquals(extended, actual);

        extended = "        ";
        nestinglevel = 2;
        formatter.writeIndent(nestinglevel);
        actual = stringInWriter.toString();
        assertEquals(extended, actual);
    }

    @Test
    public void testWriteOpeningBrace() {
        boolean newLine = false;
        boolean wasWord = false;
        int nestinglevel = 1;
        formatter.writeOpeningBrace(newLine, wasWord, nestinglevel);

        String expected = "{\n";
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);

        newLine = true;
        createFormatter();
        expected = "    {\n";
        formatter.writeOpeningBrace(newLine, wasWord, nestinglevel);
        actual = stringInWriter.toString();
        assertEquals(expected, actual);

        wasWord = true;
        createFormatter();
        expected = "    {\n";
        formatter.writeOpeningBrace(newLine, wasWord, nestinglevel);
        actual = stringInWriter.toString();
        assertEquals(expected, actual);

        newLine = false;
        createFormatter();
        expected = " {\n";
        formatter.writeOpeningBrace(newLine, wasWord, nestinglevel);
        actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testWriteClosingBrace() {
        boolean newLine = false;
        int nestingLevel = 2;
        formatter.writeClosingBrace(newLine, nestingLevel);
        String expected = "\n    }\n";
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);

        newLine = true;
        nestingLevel = 1;
        createFormatter();
        formatter.writeClosingBrace(newLine, nestingLevel);
        expected = "}\n";
        actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testWriteSemicolon() {
        formatter.writeSemicolon();
        String expected = ";\n";
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testWriteCommonChar() {
        boolean newLine = false;
        boolean wasWord = false;
        int nestingLevel = 1;
        char character = 'f';

        formatter.writeCommonChar(newLine, wasWord, nestingLevel, character);
        String expected = " " + character;
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);

        wasWord = true;
        createFormatter();
        formatter.writeCommonChar(newLine, wasWord, nestingLevel, character);
        expected = Character.toString(character);
        actual = stringInWriter.toString();
        assertEquals(expected, actual);

        newLine = true;
        createFormatter();
        formatter.writeCommonChar(newLine, wasWord, nestingLevel, character);
        expected = "    " + character;
        actual = stringInWriter.toString();
        assertEquals(expected, actual);

        wasWord = false;
        createFormatter();
        formatter.writeCommonChar(newLine, wasWord, nestingLevel, character);
        expected = "    " + character;
        actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }
}
