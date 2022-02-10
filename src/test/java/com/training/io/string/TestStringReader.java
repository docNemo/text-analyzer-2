package com.training.io.string;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.training.io.string.StringReaderChar;

import org.junit.jupiter.api.Test;

public class TestStringReader {

    @Test
    public void testStringReaderHasChar() {
        String expected = "asd; asd; asdasd { asdas asda; }";
        StringReaderChar stringReaderChar = new StringReaderChar(expected);
        assertTrue(stringReaderChar.hasChar());

        expected = "";
        stringReaderChar = new StringReaderChar(expected);
        assertFalse(stringReaderChar.hasChar());
    }

    @Test
    public void testStringReaderReadChar() {
        String expected = "a d";
        StringReaderChar stringReaderChar = new StringReaderChar(expected);
        int indexChar = 0;
        while (stringReaderChar.hasChar()) {
            assertEquals(
                    expected.charAt(indexChar),
                    stringReaderChar.readChar()
            );
            indexChar++;
        }
    }
    @Test
    public void testStringReader() {
        String expected = "asd; asd; asdasd { asdas asda; }";
        StringReaderChar stringReaderChar = new StringReaderChar(expected);

        StringBuilder actual = new StringBuilder();
        while (stringReaderChar.hasChar()) {
            actual.append(stringReaderChar.readChar());
        }
        assertEquals(expected, actual.toString());
    }
}
