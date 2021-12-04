package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main {

    public static void main(String[] args) throws IOException {
        var fileInput = Files.readAllLines(Path.of("input.txt"));

        Day4 day4 = new Day4(fileInput);

        System.out.println("Result of the first winning table: " + day4.day4a());
        System.out.println("Result of the last winning table: " + day4.day4b());
    }
}
