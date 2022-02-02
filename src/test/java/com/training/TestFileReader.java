package com.training;

import com.training.fileIO.FileReaderChar;
import com.training.fileIO.FileReaderException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestFileReader {

    @Test
    public void testFileReader() {
        String expected = """
            as; asd
            asddsa {} ()""";
        String pathToFile = "src/test/resources/testFileReader1.txt";
        StringBuilder actual = new StringBuilder();

        try (
                FileInputStream fileInputStream = new FileInputStream(pathToFile);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                FileReaderChar reader = new FileReaderChar(bufferedReader)
        ) {
            while (reader.hasChar()) {
                actual.append(reader.readChar());
            }

        } catch (IOException e) {
            throw new FileReaderException("Error while reading file in test 1 of file reader" , e);
        }
        assertEquals(expected, actual.toString());

        pathToFile = "src/test/resources/testFileReader2.txt";
        expected = "";
        actual = new StringBuilder();

        try (
                FileInputStream fileInputStream = new FileInputStream(pathToFile);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                FileReaderChar reader = new FileReaderChar(bufferedReader)
        ) {
            while (reader.hasChar()) {
                actual.append(reader.readChar());
            }

        } catch (IOException e) {
            throw new FileReaderException("Error while reading file in test 2 of file reader" , e);
        }

        assertEquals(expected, actual.toString());
    }
}
