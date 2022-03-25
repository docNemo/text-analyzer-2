package com.training.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.training.io.IReader;
import com.training.io.string.StringReaderChar;
import com.training.lexer.token.IToken;
import org.junit.jupiter.api.Test;

public class LexerTest {

    private IReader reader;
    private Lexer lexer;


    @Test
    void test123123() {
        reader = new StringReaderChar("// f\n");
        lexer = new Lexer(reader);

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
    void test112323123() {
        reader = new StringReaderChar("\"/ \"\n");
        lexer = new Lexer(reader);

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

        assertEquals("newline", actual.getName());
        assertEquals("\n", actual.getLexeme());
    }

    @Test
    void testFor() {
        reader = new StringReaderChar("for df ");
        lexer = new Lexer(reader);

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
        lexer = new Lexer(reader);

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
}
