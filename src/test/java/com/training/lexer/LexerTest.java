package com.training.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.training.io.IReader;
import com.training.io.string.StringReaderChar;
import com.training.lexer.token.IToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LexerTest {

    private IReader reader;
    private Lexer lexer;

    @BeforeEach
    void setUp() {
        reader = mock(IReader.class);
        lexer = new Lexer(reader);
    }

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
}
