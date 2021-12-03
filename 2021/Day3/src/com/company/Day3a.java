package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3a {

    public static void main(String[] args) {
        var fileInput = readFile();
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        for(int i = 0; i < fileInput.get(0).length(); i++) {
            int finalI = i;
            var start1 = fileInput.stream().filter(line -> line.charAt(finalI) == '1').collect(Collectors.toList());
            gamma.append((start1.size() > fileInput.size()/2) ? '1' : '0');
            epsilon.append((start1.size() > fileInput.size()/2) ? '0' : '1');
        }

        System.out.println("Gamma: " + Integer.parseInt(gamma.toString(), 2));
        System.out.println("Epsilon: " + Integer.parseInt(epsilon.toString(), 2));
        System.out.println("Output: " + Integer.parseInt(gamma.toString(), 2) * Integer.parseInt(epsilon.toString(), 2));


    }

    private static List<String> readFile () {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            List<String> fileInput = new ArrayList<>();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                fileInput.add(line);
            }
            return fileInput;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
