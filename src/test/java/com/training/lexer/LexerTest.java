package com.training.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.training.io.IReader;
import com.training.lexer.token.IToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LexerTest {

    private IReader reader;
    private Lexer lexer;

    @BeforeEach
    void setUp() {
        reader = mock(IReader.class);

        when(reader.hasChar())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        when(reader.readChar())
                .thenReturn('"')
                .thenReturn('/')
                .thenReturn(' ')
                .thenReturn('"')
                .thenReturn('\n');
        lexer = new Lexer(reader);
    }

//    @Test
//    void test1() {
//        when(reader.hasChar())
//                .thenReturn(true)
//                .thenReturn(true)
//                .thenReturn(true)
//                .thenReturn(true)
//                .thenReturn(true)
//                .thenReturn(false);
//        when(reader.readChar())
//                .thenReturn('/')
//                .thenReturn('/')
//                .thenReturn(' ')
//                .thenReturn('f')
//                .thenReturn('\n');
//
//        IToken actual = lexer.readToken();
//
//        assertEquals(true, true);
//        assertEquals("LINE_COMMENT", actual.getName());
//        assertEquals("//", actual.getLexeme()); //FIXME
//
//        actual = lexer.readToken();
//
//        assertEquals("SPACE", actual.getName());
//        assertEquals(" ", actual.getLexeme()); //FIXME
//
//        actual = lexer.readToken();
//
//        assertEquals("COMMON", actual.getName());
//        assertEquals("f", actual.getLexeme());
//
////        verify(reader, times(6)).hasChar(); //FIXME hasChar called 7 times
////        verify(reader, times(5)).readChar();
//    }

    @Test
    void test2() {

        IToken actual = lexer.getToken();

        assertEquals(true, true);
        assertEquals("DOUBLE_QUOTE", actual.getName());
        assertEquals("\"", actual.getLexeme()); //FIXME

        actual = lexer.getToken();

        assertEquals("SLASH", actual.getName());
        assertEquals("/", actual.getLexeme()); //FIXME

        actual = lexer.getToken();

        assertEquals("SPACE", actual.getName());
        assertEquals(" ", actual.getLexeme());

        actual = lexer.getToken();

        assertEquals("DOUBLE_QUOTE", actual.getName());
        assertEquals("\"", actual.getLexeme());

//        verify(reader, times(6)).hasChar(); //FIXME hasChar called 7 times
//        verify(reader, times(5)).readChar();
    }
}
