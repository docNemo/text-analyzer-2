package com.training.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.training.io.IReader;
import com.training.io.IWriter;
import com.training.io.string.StringReaderChar;
import com.training.io.string.StringWriterChar;
import com.training.lexer.ILexer;
import com.training.lexer.Lexer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFormatter {
    Formatter formatter;
    IReader reader;
    ILexer lexer;
    IWriter writer;
    StringBuilder stringInWriter;

    @BeforeEach
    public void createFormatter() {
        reader = new StringReaderChar("");
        stringInWriter = new StringBuilder();
        writer = new StringWriterChar(stringInWriter);
        lexer = new Lexer(reader);
        formatter = new Formatter();
    }

//    @Test
//    public void testWriteIndent1() {
//        String extended = "";
//        int nestinglevel = -1;
//        formatter.writeIndent(nestinglevel);
//        String actual = stringInWriter.toString();
//        assertEquals(extended, actual);
//    }
//
//    @Test
//    public void testWriteIndent2() {
//        String extended = "        ";
//        int nestinglevel = 2;
//        formatter.writeIndent(nestinglevel);
//        String actual = stringInWriter.toString();
//        assertEquals(extended, actual);
//    }
//
//    @Test
//    public void testWriteOpeningBrace1() {
//        boolean newLine = false;
//        boolean wasWord = false;
//        int nestinglevel = 1;
//        formatter.writeOpeningBrace(newLine, wasWord, nestinglevel, "{");
//
//        String expected = "{\n";
//        String actual = stringInWriter.toString();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testWriteOpeningBrace2() {
//        boolean newLine = true;
//        boolean wasWord = false;
//        int nestinglevel = 1;
//        String expected = "    {\n";
//
//        formatter.writeOpeningBrace(newLine, wasWord, nestinglevel, "{");
//        String actual = stringInWriter.toString();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testWriteOpeningBrace3() {
//        boolean newLine = true;
//        boolean wasWord = true;
//        int nestinglevel = 1;
//        String expected = "    {\n";
//
//        formatter.writeOpeningBrace(newLine, wasWord, nestinglevel, "{");
//        String actual = stringInWriter.toString();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testWriteOpeningBrace4() {
//        boolean newLine = false;
//        boolean wasWord = true;
//        int nestinglevel = 1;
//        String expected = " {\n";
//
//        formatter.writeOpeningBrace(newLine, wasWord, nestinglevel, "{");
//        String actual = stringInWriter.toString();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testWriteClosingBrace1() {
//        boolean newLine = false;
//        int nestingLevel = 2;
//        formatter.writeClosingBrace(newLine, nestingLevel, "}");
//        String expected = "\n    }\n";
//        String actual = stringInWriter.toString();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testWriteClosingBrace2() {
//        boolean newLine = true;
//        int nestingLevel = 1;
//        formatter.writeClosingBrace(newLine, nestingLevel, "}");
//        String expected = "}\n";
//        String actual = stringInWriter.toString();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testWriteSemicolon() {
//        formatter.writeSemicolon(";");
//        String expected = ";\n";
//        String actual = stringInWriter.toString();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testWriteCommonChar1() {
//        boolean newLine = false;
//        boolean wasWord = false;
//        int nestingLevel = 1;
//        String testString = "fads";
//
//        formatter.writeCommonChar(newLine, wasWord, nestingLevel, testString);
//        String expected = " " + testString;
//        String actual = stringInWriter.toString();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testWriteCommonChar2() {
//        boolean newLine = false;
//        boolean wasWord = true;
//        int nestingLevel = 1;
//        String testString = "fads";
//
//        formatter.writeCommonChar(newLine, wasWord, nestingLevel, testString);
//        String expected = testString;
//        String actual = stringInWriter.toString();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testWriteCommonChar3() {
//        boolean newLine = true;
//        boolean wasWord = true;
//        int nestingLevel = 1;
//        String testString = "fads";
//
//        formatter.writeCommonChar(newLine, wasWord, nestingLevel, testString);
//        String expected = "    " + testString;
//        String actual = stringInWriter.toString();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testWriteCommonChar4() {
//        boolean newLine = true;
//        boolean wasWord = false;
//        int nestingLevel = 1;
//        String testString = "fads";
//
//        formatter.writeCommonChar(newLine, wasWord, nestingLevel, testString);
//        String expected = "    " + testString;
//        String actual = stringInWriter.toString();
//        assertEquals(expected, actual);
//    }

    @Test
    public void testFormat() {
        String input = """
                asd;                asd;
                qew
                {
                qwer;
                asdasdas;}
                                
                qkjfj;""";
        reader = new StringReaderChar(input);
        String expected = """
                asd;
                asd;
                qew {
                    qwer;
                    asdasdas;
                }
                
                qkjfj;""";

        lexer = new Lexer(reader);
        formatter = new Formatter();

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }
}
