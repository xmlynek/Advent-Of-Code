package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.stream.IntStream.rangeClosed;


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
        return rangeClosed(0, Arrays.stream(values).max().getAsInt())
                .mapToLong(target -> Arrays.stream(values).
                        mapToLong(i -> function.apply(target, i))
                        .sum()).min().orElse(0);
    }

    private long recursiveAccumulate(long startValue) {
        if(startValue > 0)
            return startValue + recursiveAccumulate(startValue-1);
        return 0;
    }
}
