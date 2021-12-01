package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1b {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            int count = 0;
            Deque<String> window1 = new ArrayDeque<>();
            String line;

            while((line = br.readLine()) != null){
                if(window1.isEmpty() || window1.size() < 3){
                    window1.addLast(line);
                    continue;
                }
                Deque<String> window2 = new ArrayDeque<>(window1);
                window2.pop();
                window2.addLast(line);
                if(sumOfWindow(window2) > sumOfWindow(window1))
                    count++;
                window1 = window2;
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int sumOfWindow(Deque<String> deque) {
        AtomicInteger sum = new AtomicInteger();
        deque.forEach(str -> {
            try {
                sum.addAndGet(Integer.parseInt(str));
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
        });
        return sum.get();
    }
}
