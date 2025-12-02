package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputLoader {
    private static String INPUT_FOLDER = "inputs/";
    
    private InputLoader() {}

    /**
     * Reads the input for a specific day and returns it as a List of Strings.
     * Useful for puzzles where line-by-line processing is needed.
     */
    public static List<String> loadLines(int day) {
        var path = getPath(day);

        try {
            return Files.readAllLines(path);
        } catch(IOException e) {
            throw new RuntimeException("Could not read input file: " + path, e);
        }
    }

    /**
     * Reads the whole input as a single String.
     * Useful for puzzles that are one long sequence or require custom splitting.
     */
    public static String loadString(int day) {
        var path = getPath(day);

        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("Could not read input file: " + path, e);
        }
    }

    private static Path getPath(int day) {
        var fileName = String.format("day%02d.txt", day);
        return Path.of(INPUT_FOLDER + fileName);
    }
    
}