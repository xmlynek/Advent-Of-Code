package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static java.util.stream.IntStream.range;

public class Day9 {
    private final List<Integer> inputList;
    private final int lineSize;
    private final Map<Integer, Boolean> searchMap;
    private final List<Integer> basinLengths;

    public Day9(String filename) throws IOException {
        this.inputList = new ArrayList<>();
        Files.readAllLines(Path.of(filename))
                .forEach(line -> range(0, line.length())
                        .forEach(i -> inputList.add(Integer.parseInt(String.valueOf(line.charAt(i))))));

        this.lineSize = inputList.size() / Files.readAllLines(Path.of(filename)).size();
        this.basinLengths = new ArrayList<>();

        this.searchMap = new HashMap<>();
        range(0, inputList.size()).forEach(value -> searchMap.put(value, Boolean.FALSE));
    }

    public int day9a() {
        AtomicInteger riskSum = new AtomicInteger();
        processLavaTubes(i -> riskSum.addAndGet(inputList.get(i) + 1));
        return riskSum.get();
    }

    public int day9b() {
        processLavaTubes(i -> {
            List<Integer> outputList = new ArrayList<>();
            calculateBasin(i, outputList);
            basinLengths.add(outputList.size());
        });
        return basinLengths.stream().sorted().skip(basinLengths.size()-3).reduce(1, (integer, integer2) -> integer*integer2);
    }

    private void processLavaTubes(Consumer<Integer> consumer) {
        for(int i = 0; i < inputList.size(); i++) {
            boolean equal = true;
            int min = inputList.get(i);

            List<Integer> list = new ArrayList<>();
            if(i + lineSize < inputList.size())
                list.add(inputList.get(i+lineSize));
            if(i - lineSize >= 0)
                list.add(inputList.get(i-lineSize));
            if(i - 1 >= 0)
                list.add(inputList.get(i-1));
            if(i + 1 < inputList.size())
                list.add(inputList.get(i+1));

            for(var item : list) {
                if(!item.equals(inputList.get(i)))
                    equal = false;
                if(item < min)
                    min = item;
            }
            if(min == inputList.get(i) && !equal){
                consumer.accept(i);
            }
        }
    }

    private void calculateBasin(int index, List<Integer> outputList) {
        if(searchMap.get(index).equals(true) || inputList.get(index) == 9)
            return ;

        searchMap.put(index, true);
        outputList.add(index);

        if(index - lineSize >= 0)
            calculateBasin(index - lineSize, outputList);
        if(index + lineSize < inputList.size())
            calculateBasin( index + lineSize, outputList);
        if((index % lineSize - 1) >= 0)
            calculateBasin( index-1, outputList);
        if(index % lineSize + 1 < lineSize)
            calculateBasin(index+1, outputList);
    }
}
