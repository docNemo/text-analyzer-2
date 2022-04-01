package com.training;

import com.training.exceptions.AppException;
import com.training.exceptions.CloseException;
import com.training.formatter.Formatter;
import com.training.formatter.IFormatter;
import com.training.io.file.FileReaderChar;
import com.training.io.file.FileWriterChar;
import com.training.lexer.ILexer;
import com.training.lexer.Lexer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public final static String LINE_SEPARATOR = System.lineSeparator();

    public static void main(String[] args) {
        final String pathToInputFile = args[0];
        final String pathToOutputFile = args[1];
        try (
                FileInputStream inputStream = new FileInputStream(pathToInputFile);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader fileReader = new BufferedReader(inputStreamReader);
                FileReaderChar readerChar = new FileReaderChar(fileReader);

                FileOutputStream outputStream = new FileOutputStream(pathToOutputFile);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter fileWriter = new BufferedWriter(outputStreamWriter);
                FileWriterChar fileWriterChar = new FileWriterChar(fileWriter)

        ) {
            ILexer lexer = new Lexer(readerChar);
            IFormatter formatter = new Formatter();
            formatter.format(lexer, fileWriterChar);

        } catch (CloseException e) {
            logger.error("Error during closing stream. {} ", e.toString());
            throw new AppException("Error during closing stream", e);
        } catch (IOException e) {
            logger.error("Error creating reader and writer. {} ", e.toString());
            throw new AppException("Error creating reader and writer", e);
        }

    }
}
