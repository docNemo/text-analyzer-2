package com.training.io.file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.training.exceptions.AppException;
import com.training.exceptions.CloseException;
import com.training.exceptions.ReaderException;
import org.junit.jupiter.api.Test;

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

        } catch (CloseException e) {
            throw new AppException("Error during closing stream of reader in te", e);
        } catch (IOException e) {
            throw new ReaderException("Error while reading file in test 1 of file reader", e);
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

        } catch (CloseException e) {
            throw new AppException("Error during closing stream of reader in te", e);
        } catch (IOException e) {
            throw new ReaderException("Error while reading file in test 2 of file reader", e);
        }

        assertEquals(expected, actual.toString());
    }
}
