package com.training.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.training.io.IReader;
import com.training.io.string.StringReaderChar;
import com.training.lexer.token.IToken;
import org.junit.jupiter.api.Test;

public class LexerTest {

    private IReader reader;
    private Lexer lexer;
    private final String pathToLexerConfig = "src/main/java/com/training/lexer/lexer.yaml";

    @Test
    void testOneLineComment() {
        reader = new StringReaderChar("// f\n");
        lexer = new Lexer(reader, pathToLexerConfig);

        IToken actual = lexer.getToken();

        assertEquals("onelinecomment", actual.getName());
        assertEquals("//", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("space", actual.getName());
        assertEquals(" ", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("char", actual.getName());
        assertEquals("f", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("newline", actual.getName());
        assertEquals("\n", actual.getLexeme());
    }

    @Test
    void testStringLiteral() {
        reader = new StringReaderChar("\"/ \"\r\n");
        lexer = new Lexer(reader, pathToLexerConfig);

        IToken actual = lexer.getToken();

        assertEquals("quotemark", actual.getName());
        assertEquals("\"", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("char", actual.getName());
        assertEquals("/", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("space", actual.getName());
        assertEquals(" ", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("quotemark", actual.getName());
        assertEquals("\"", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("slashr", actual.getName());
        assertEquals("\r", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("newline", actual.getName());
        assertEquals("\n", actual.getLexeme());
    }

    @Test
    void testFor() {
        reader = new StringReaderChar("for df ");
        lexer = new Lexer(reader, pathToLexerConfig);

        IToken actual = lexer.getToken();

        assertEquals("for", actual.getName());
        assertEquals("for", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("space", actual.getName());
        assertEquals(" ", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("char", actual.getName());
        assertEquals("d", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("char", actual.getName());
        assertEquals("f", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("space", actual.getName());
        assertEquals(" ", actual.getLexeme());
    }

    @Test
    void testNotFor() {
        reader = new StringReaderChar("fors df ");
        lexer = new Lexer(reader, pathToLexerConfig);

        IToken actual = lexer.getToken();

        assertEquals("char", actual.getName());
        assertEquals("fors", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("space", actual.getName());
        assertEquals(" ", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("char", actual.getName());
        assertEquals("d", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("char", actual.getName());
        assertEquals("f", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("space", actual.getName());
        assertEquals(" ", actual.getLexeme());
    }

    @Test
    void testBlock() {
        reader = new StringReaderChar("{for(){a;}}");
        lexer = new Lexer(reader, pathToLexerConfig);

        IToken actual = lexer.getToken();

        assertEquals("openbrace", actual.getName());
        assertEquals("{", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("for", actual.getName());
        assertEquals("for", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("openbracket", actual.getName());
        assertEquals("(", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("closebracket", actual.getName());
        assertEquals(")", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("openbrace", actual.getName());
        assertEquals("{", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("char", actual.getName());
        assertEquals("a", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("semicolon", actual.getName());
        assertEquals(";", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("closebrace", actual.getName());
        assertEquals("}", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("closebrace", actual.getName());
        assertEquals("}", actual.getLexeme());
    }
}
