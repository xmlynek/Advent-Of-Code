package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day14 {

    private final Map<String, Long> pairs = new TreeMap<>();
    private final Map<String, Character> pairInsertion = new HashMap<>();
    private final Map<Character, Long> charCounter = new LinkedHashMap<>();
    private final List<String> fileInput;

    public Day14(String filename) throws IOException {
        this.fileInput = Files.readAllLines(Path.of(filename));
    }

    private void init() {
        pairs.clear();
        pairInsertion.clear();
        charCounter.clear();

        String inputLine = fileInput.get(0);
        for(int i = 0; i < inputLine.length()-1; i++) {
            updateCounter(pairs, inputLine.substring(i, i+2), 1L);
            updateCounter(charCounter, inputLine.charAt(i), 1L);
        }
        updateCounter(charCounter, inputLine.charAt(inputLine.length()-1), 1L);

        fileInput.stream().skip(2).forEach(line -> {
            var splitValues = line.split(" -> ");
            pairInsertion.put(splitValues[0], splitValues[1].charAt(0));
        });
    }

    public long day14a() {
        init();
        return proceedCalculation(10);
    }

    public long day14b() {
        init();
        return proceedCalculation(40);
    }

    private long proceedCalculation(int steps) {
        for(int i = 0; i < steps; i++) {
            Map<String, Long> newPairs = new TreeMap<>();
            for(var pair : pairs.entrySet()) {
                if(pair.getValue() != 0) {
                    updateCounter(charCounter, pairInsertion.get(pair.getKey()), pair.getValue());
                    updateCounter(newPairs, ""+pair.getKey().charAt(0) + pairInsertion.get(pair.getKey()), pair.getValue());
                    updateCounter(newPairs, ""+pairInsertion.get(pair.getKey()) + pair.getKey().charAt(1), pair.getValue());
                }
            }
            pairs.clear();
            pairs.putAll(newPairs);
        }
        return (charCounter.values().stream().max(Comparator.comparing(Long::valueOf)).get()
                - charCounter.values().stream().min(Comparator.comparing(Long::valueOf)).get());
    }

    private <T> void updateCounter(Map<T, Long> map, T key, long value) {
        if(map.putIfAbsent(key, value) == null)
            return;
        map.put(key, map.get(key) + value);
    }
}
