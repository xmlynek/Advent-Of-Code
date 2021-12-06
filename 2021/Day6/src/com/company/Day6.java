package com.company;

import java.util.*;

public class Day6 {
    private final long[] lanternFish = new long[9];

    public Day6(List<String> fileInput) {
        Arrays.stream(fileInput.get(0).split(",")).mapToInt(Integer::parseInt).forEach(item -> lanternFish[item]++);
    }

    public Long day6a() {
        return day6(80);
    }

    public Long day6b() {
        return day6(256);
    }

    public Long day6(int iterations) {
        for (int day = 1; day <= iterations; day++) {
            long zeros = lanternFish[0];
            System.arraycopy(lanternFish, 1, lanternFish, 0, lanternFish.length - 1);
            lanternFish[8] = zeros;
            lanternFish[6] += zeros;
        }
        return Arrays.stream(lanternFish).sum();
    }
}