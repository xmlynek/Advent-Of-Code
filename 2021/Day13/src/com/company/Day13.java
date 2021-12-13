package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day13 {
    private final Set<Coords> coordsList = new HashSet<>();

    public Day13(String filename) throws IOException {
        Files.readAllLines(Path.of(filename))
                .forEach(line -> {
            if(line.isEmpty()) return;
            else if(line.split("fold along ").length != 1) {
                var parse = line.split("fold along ")[1].split("=");
                foldTable(parse[0], Integer.parseInt(parse[1]));
                System.out.println(coordsList.size());
            }
            else {
                var values = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                coordsList.add(new Coords(values[0], values[1]));
            }
        });
        printTable();
    }

    private Coords getActualTableSize() {
        return new Coords(coordsList.stream().max(Comparator.comparingInt(Coords::getX)).get().getX()+1,
                coordsList.stream().max(Comparator.comparingInt(Coords::getY)).get().getY()+1);
    }

    private void printTable() {
        var tableSize = getActualTableSize();
        for (int i = 0; i < tableSize.getY(); i++) {
            for (int j = 0; j < tableSize.getX(); j++){
                System.out.print(coordsList.contains(new Coords(j,i)) ? "#" : " ");
            }
            System.out.println();
        }
    }

    private void foldTable(String foldByType, int value) {
        Set<Coords> newCoordsList = new HashSet<>();
        if(foldByType.equals("x")) {
            coordsList.forEach(coords -> newCoordsList.add(coords.getX() > value ?
                    new Coords(value - Math.abs(value - coords.getX()), coords.getY()) : coords));
        } else if(foldByType.equals("y")) {
            coordsList.forEach(coords -> newCoordsList.add(coords.getY() > value ?
                    new Coords(coords.getX(),value - Math.abs(value - coords.getY())) : coords));
        }
        coordsList.clear();
        coordsList.addAll(newCoordsList);
    }
}
