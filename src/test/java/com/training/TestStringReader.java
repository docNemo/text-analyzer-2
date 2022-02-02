package com.training;

import com.training.stringIO.StringReaderChar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
