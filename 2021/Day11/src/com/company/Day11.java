package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;

public class Day11 {
    private final Integer lineSize;
    private final List<Integer> flashedIndexes;
    private int flashCount = 0;
    private int synchronizedRound = 0;
    private final List<Integer> squidList;

    public Day11(String filename) throws IOException {
        List<String> fileInput = Files.readAllLines(Path.of(filename));

        this.squidList = new ArrayList<>();
        this.lineSize = fileInput.size();
        this.flashedIndexes =  new ArrayList<>();

        fileInput.forEach(line -> range(0, line.length())
                .forEach(i -> squidList.add(Integer.parseInt(String.valueOf(line.charAt(i))))));
        proceedCalculation(1000);
    }

    public int day11a() {
        return flashCount;
    }

    public int day11b() {
        return synchronizedRound;
    }

    private void proceedCalculation(int iterations) {
        for(int i = 1; i <= iterations; i++) {
            flashedIndexes.clear();

            for(int index = 0; index < squidList.size(); index++) {
                if(squidList.set(index, squidList.get(index)+1) >= 9)
                    flashIndex(index);
            }
            for(var index : flashedIndexes)
                squidList.set(index, 0);
            if(i <= 100)
                flashCount += flashedIndexes.size();

            if(flashedIndexes.size() == squidList.size()){
                synchronizedRound = i;
                return;
            }
        }
    }

    private void flashIndex(int index) {
        if(index < 0 || index >= squidList.size() || flashedIndexes.contains(index))
            return;
        flashedIndexes.add(index);

        incUpper(index);
        incLower(index);
        incLeft(index);
        incRight(index);
    }

    private void incRight(int index) {
        if((index + 1) % lineSize != 0)
            if(squidList.set(index + 1, squidList.get(index + 1)+1) >= 9 && !flashedIndexes.contains(index +1))
                flashIndex(index + 1);
    }

    private void incLeft(int index) {
        if(index % lineSize - 1 >= 0)
            if(squidList.set(index - 1, squidList.get(index - 1)+1) >= 9 && !flashedIndexes.contains(index - 1))
                flashIndex(index -1);
    }

    private void incUpper(int index) {
        if(index - lineSize >= 0) {
            if (squidList.set(index - lineSize, squidList.get(index - lineSize) + 1) >= 9 && !flashedIndexes.contains(index - lineSize))
                flashIndex(index - lineSize);

            incLeft(index - lineSize);
            incRight(index - lineSize);
        }
    }

    private void incLower(int index) {
        if(index + lineSize < squidList.size()){
            if (squidList.set(index + lineSize, squidList.get(index + lineSize) + 1) >= 9 && !flashedIndexes.contains(index + lineSize))
                flashIndex(index + lineSize);

            incLeft(index + lineSize);
            incRight(index + lineSize);
        }
    }
}
