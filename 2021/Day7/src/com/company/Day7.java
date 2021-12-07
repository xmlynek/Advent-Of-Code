package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;


public class Day7 {
    private final int[] values;

    public Day7(List<String> fileInput) {
        this.values = Stream.of(fileInput.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public long day7a() {
        return calculate((value1, value2) -> (long) Math.abs(value1 - value2));
    }

    public long day7b() {
        return calculate((value1, value2) -> recursiveAccumulate(Math.abs(value1 - value2)));
    }

    private long calculate(BiFunction<Integer, Integer, Long> function) {
        long min = Long.MAX_VALUE;
        int max = Arrays.stream(values).max().getAsInt();

        for(int i = 0; i <= max; i++) {
            long sum = 0;
            for(var item : values) {
                sum += function.apply(i, item);
            }
            if(sum < min)
                min = sum;
        }
        return min;
    }

    private long recursiveAccumulate(long startValue) {
        if(startValue > 0)
            return startValue + recursiveAccumulate(startValue-1);
        return 0;
    }


}
