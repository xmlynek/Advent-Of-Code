package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day12 {
    Map<String, Set<String>> roads;

    public Day12(String filename) throws IOException {
        this.roads = new HashMap<>();
        Files.readAllLines(Path.of(filename)).forEach(line -> {
            var pos = line.split("-");
            for(int i = 0; i < pos.length; i++)
                addEntry(pos[i], pos[(i+1)%pos.length]);
        });
    }

    private void addEntry(String key, String value) {
        if(key.equals("end") || value.equals("start")) return;
        if(roads.containsKey(key))
            roads.get(key).add(value);
        else
            roads.put(key, new HashSet<>(Set.of(value)));
    }

    public int day12a() {
        return process(false);
    }

    public int day12b() {
        return process(true);
    }

    private int process(boolean day12b) {

        Stack<List<String>> stack = new Stack<>();
        stack.push(List.of("start"));
        AtomicInteger count = new AtomicInteger(0);

        while(!stack.isEmpty()) {
            var currList = stack.pop();
            var currKey = currList.get(currList.size()-1);
            var neighbours = roads.get(currKey);

            neighbours.forEach(neighbour -> {
                if(neighbour.equals("end")){
                    count.getAndIncrement();
                } else if (neighbour.equals(neighbour.toLowerCase()) && currList.contains(neighbour)) {
                    if(!currList.contains("TAGGED") && day12b){
                        var copy = new ArrayList<>(List.copyOf(currList));
                        copy.set(0, "TAGGED");
                        copy.add(neighbour);
                        stack.push(copy);
                    }
                } else {
                    var copy = new ArrayList<>(List.copyOf(currList));
                    copy.add(neighbour);
                    stack.push(copy);
                }
            });
        }
        return count.get();
    }
}
