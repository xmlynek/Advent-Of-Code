package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day13 {

    private final List<Coords> coordsList = new ArrayList<>();
    private char[][] table;
    private final List<FoldInstruction> foldInstructions = new ArrayList<>();
    private int rows;
    private int cols;

    public Day13(String filename) throws IOException {
        var lines = Files.readAllLines(Path.of(filename));
        AtomicInteger xSize = new AtomicInteger();
        AtomicInteger ySize = new AtomicInteger();

        lines.forEach(line -> {
            if(line.isEmpty()) return;
            else if(line.split("fold along ").length != 1) {
                var parse = line.split("fold along ")[1].split("=");
                foldInstructions.add(new FoldInstruction(parse[0], Integer.parseInt(parse[1])));
            }
            else {
                var values = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                var coords = new Coords(values[0], values[1]);
                if(coords.getX() > xSize.get())
                    xSize.set(coords.getX());
                if(coords.getY() > ySize.get())
                    ySize.set(coords.getY());
                coordsList.add(coords);
            }
        });
        rows = ySize.get()+1;
        cols = xSize.get()+1;
        initTable(cols, rows);

        foldInstructions.forEach(foldInstruction -> foldTable(foldInstruction.getFoldType(), foldInstruction.getValue()));
        printTable();
    }

    private void initTable(int x, int y) {
        table = new char[y][x];
        for (var coords : coordsList)
            table[coords.getY()][coords.getX()] = '#';
        for(int i = 0; i < table.length; i++)
            for(int j = 0; j < table[i].length; j++)
                table[i][j] = table[i][j] == '#' ? '#' : '.';
    }

    private void printTable() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++)
                System.out.print(table[i][j]);
            System.out.println();
        }
    }

    private void foldTable(FoldInstruction.FoldType foldType, int value) {
        if(foldType.equals(FoldInstruction.FoldType.FOLD_X)) {
            for(int row = 0; row < this.rows; row++){
                for(int col = 1; col < this.cols - value; col++) {
                    table[row][value-col] = table[row][value-col] == '#' ? '#' : table[row][value+col];
                }
            }
            cols = value;
        } else if(foldType.equals(FoldInstruction.FoldType.FOLD_Y)) {
            for(int row = 1; row < this.rows - value; row++){
                for(int col = 0; col < this.cols; col++) {
                    table[value-row][col] = table[value-row][col] == '#' ? '#' : table[value+row][col];
                }
            }
            rows = value;
        }
        System.out.println(countDots());
    }

    private int countDots() {
        int sum = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++) {
                if(table[i][j] == '#')
                    sum++;
            }
        }
        return sum;
    }
}
