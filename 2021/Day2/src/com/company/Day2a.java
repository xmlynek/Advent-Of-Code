package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2a {

    public static void main(String[] args) {
        int depth = 0, hPos = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                String msg[] = line.split(" ");
                int nValue = Integer.parseInt(msg[1]);
                switch (msg[0]) {
                    case "forward":
                        hPos += nValue;
                        break;
                    case "down":
                        depth += nValue;
                        break;
                    case "up":
                        depth -= nValue;
                        break;
                }
            }
            System.out.println(depth * hPos);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
