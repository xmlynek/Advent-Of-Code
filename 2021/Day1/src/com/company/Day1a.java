package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1a {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            int count = 0;
            int prev = Integer.parseInt(br.readLine());
            String line;
            while((line = br.readLine()) != null){
                int curr = Integer.parseInt(line);
                if(curr > prev)
                    count++;
                prev = curr;
            }
            System.out.println(count);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
