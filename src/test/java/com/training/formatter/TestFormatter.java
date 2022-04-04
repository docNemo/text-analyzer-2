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
    private Formatter formatter;
    private IReader reader;
    private ILexer lexer;
    private IWriter writer;
    private StringBuilder stringInWriter;
    private final String pathToLexerConfig = "src/main/java/com/training/lexer/lexer.yaml";
    private final String pathToFormatterConfig = "src/main/java/com/training/formatter/formatter.yaml";

    @BeforeEach
    public void createFormatter() {
        reader = new StringReaderChar("");
        stringInWriter = new StringBuilder();
        writer = new StringWriterChar(stringInWriter);
        formatter = new Formatter(pathToFormatterConfig);
    }

    @Test
    void test() {
        String input = """    
                /* asd{}a;\\
                   sdd */
                        asd;
                        //hhgffgh
                      
                        asdasd;asdasd = "sadasd";//asdasdasd
                      
                          asdas{asdas;asd;asdsad{asd;//asdasd}}
                          }}""";
        reader = new StringReaderChar(input);
        String expected = """
                /* asd{}a;\\
                   sdd */
                asd;
                //hhgffgh
                      
                asdasd;
                asdasd = "sadasd";//asdasdasd
                      
                asdas {
                    asdas;
                    asd;
                    asdsad {
                        asd;//asdasd}}
                    }
                }""";

        lexer = new Lexer(reader, pathToLexerConfig);
        formatter = new Formatter(pathToFormatterConfig);

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testIndent() {
        String input = """
                /**/
                    asd;
                        a;""";
        reader = new StringReaderChar(input);
        String expected = """
                /**/
                asd;
                a;""";

        lexer = new Lexer(reader, pathToLexerConfig);
        formatter = new Formatter(pathToFormatterConfig);

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }


//    @Test
//    void testFormat() {
//        String input = """
//                asd;                asd;
//                qew
//                {
//                qwer;asdasdas;}
//
//                qkjfj;
//                }
//                /* asd{}a;\\""";
//        reader = new StringReaderChar(input);
//        String expected = """
//                asd;
//                asd;
//                qew {
//                    qwer;
//                    asdasdas;
//                }
//
//                qkjfj;
//                }
//                /* asd{}a;\\""";
//
//        lexer = new Lexer(reader, pathToLexerConfig);
//        formatter = new Formatter(pathToFormatterConfig);
//
//        formatter.format(lexer, writer);
//        String actual = stringInWriter.toString();
//        assertEquals(expected, actual);
//    }

    @Test
    void testOpenBraceAfterChar() {
        String input = """
                asd{""";
        reader = new StringReaderChar(input);
        String expected = """
                asd {""";
        lexer = new Lexer(reader, pathToLexerConfig);
        formatter = new Formatter(pathToFormatterConfig);

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testOpenBraceOnNewLine() {
        String input = """
                asd;{""";
        reader = new StringReaderChar(input);
        String expected = """
                asd;
                {""";
        lexer = new Lexer(reader, pathToLexerConfig);
        formatter = new Formatter(pathToFormatterConfig);

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testTwoOpenBraces() {
        String input = """
                {asd;{""";
        reader = new StringReaderChar(input);
        String expected = """
                {
                    asd;
                    {""";
        lexer = new Lexer(reader, pathToLexerConfig);
        formatter = new Formatter(pathToFormatterConfig);

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testCloseBrace() {
        String input = """
                asd}""";
        reader = new StringReaderChar(input);
        String expected = """
                asd
                }""";
        lexer = new Lexer(reader, pathToLexerConfig);
        formatter = new Formatter(pathToFormatterConfig);

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testStringLiteral() {
        String input = """
                asd;"asdasd    {} () \\*asda *\\" asdf""";
        reader = new StringReaderChar(input);
        String expected = """
                asd;
                "asdasd    {} () \\*asda *\\" asdf""";
        lexer = new Lexer(reader, pathToLexerConfig);
        formatter = new Formatter(pathToFormatterConfig);

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testTwoNewLines() {
        String input = """
                }
                                
                asd;
                                
                //ghjgffgh
                asd""";
        reader = new StringReaderChar(input);
        String expected = """
                }
                                
                asd;
                                
                //ghjgffgh
                asd""";

        lexer = new Lexer(reader, pathToLexerConfig);
        formatter = new Formatter(pathToFormatterConfig);

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testBrackets() {
        String input = """
                (
                ad,
                asddas
                )""";
        reader = new StringReaderChar(input);
        String expected = """
                (ad, asddas)""";

        lexer = new Lexer(reader, pathToLexerConfig);
        formatter = new Formatter(pathToFormatterConfig);

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testBracketsOnNewLine() {
        String input = """
                {(
                ad,
                asddas
                )""";
        reader = new StringReaderChar(input);
        String expected = """
                {
                    (ad, asddas)""";

        lexer = new Lexer(reader, pathToLexerConfig);
        formatter = new Formatter(pathToFormatterConfig);

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testBracketsInText() {
        String input = """
                asd()sAas
                                
                AADda () asdas""";
        reader = new StringReaderChar(input);
        String expected = """
                asd () sAas AADda () asdas""";

        lexer = new Lexer(reader, pathToLexerConfig);
        formatter = new Formatter(pathToFormatterConfig);

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testFor() {
        String input = """
                asd for asd""";
        reader = new StringReaderChar(input);
        String expected = """
                asd for asd""";

        lexer = new Lexer(reader, pathToLexerConfig);
        formatter = new Formatter(pathToFormatterConfig);

        formatter.format(lexer, writer);
        String actual = stringInWriter.toString();
        assertEquals(expected, actual);
    }

}
