package com.training;

import com.training.fileIO.FileReaderChar;
import com.training.fileIO.FileWriterChar;
import com.training.stringIO.StringReaderChar;
import com.training.stringIO.StringWriterChar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try (
                FileInputStream javaFileInputStream = new FileInputStream("src/asd.txt");
                InputStreamReader javaInputStreamReader = new InputStreamReader(javaFileInputStream);
                BufferedReader javaFileReader = new BufferedReader(javaInputStreamReader);
                FileReaderChar readerChar = new FileReaderChar(javaFileReader);

                FileOutputStream javaFileOutputStream = new FileOutputStream("src/asd2.txt");
                OutputStreamWriter javaOutputStreamReader = new OutputStreamWriter(javaFileOutputStream);
                BufferedWriter javaFileWriter = new BufferedWriter(javaOutputStreamReader);
                FileWriterChar fileWriterChar = new FileWriterChar(javaFileWriter);

        ) {
                while(readerChar.hasChar()) {
                    fileWriterChar.writeChar(readerChar.readChar());
                }

        } catch (Exception e) {

        }

    }
}
