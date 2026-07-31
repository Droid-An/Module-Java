package com.prep.exercise_3_1_3_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * File reader
 */
public class FileReaderService {

    /**
     * Demo method to show how errors are handled
     */
    public static void main(String[] args) {
        List<Object> files = Arrays.asList(
                "prep/src/main/resources/dummyData.txt",
                null,
                "prep/src/main/resources/notExisting.txt",
                "prep/src/main/resources/emptyFile.txt"
        );
        FileReaderService fileReaderService = new FileReaderService();
        for (Object file : files) {
            try {
                System.out.println(fileReaderService.readFile((String) file));
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }

    /**
     * Read file using BufferedReader and return full content.
     *
     * @param filename Name of the file to read
     * @return File content
     * @throws InvalidFileFormatException when file is empty
     * @throws NullPointerException
     * @throws RuntimeException
     */
    public String readFile(String filename) {
        Objects.requireNonNull(filename, "Filename must not be null");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String content = readAllLinesWithStream(reader);
            if (content.isEmpty()) {
                throw new InvalidFileFormatException("Empty files are invalid");
            }
            return content;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + filename, e);
        } catch (IOException e) {
            throw new RuntimeException("Problem reading file: " + filename, e);
        }
    }

    /**
     * Helper to read file in lines using Collectors
     *
     * @param reader Buffered reader with file
     * @return File content
     */
    public String readAllLinesWithStream(BufferedReader reader) {
        return reader.lines()
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
