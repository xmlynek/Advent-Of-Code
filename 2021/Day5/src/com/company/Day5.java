package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day5 {
    private final List<Coords> coords;
    private final int[][] diagram;

    public Day5(List<String> fileInput) {
        coords =  new ArrayList<>();
        AtomicInteger maxCoordValue = new AtomicInteger();

        fileInput.forEach(line -> {
            int[] parsedValues = Arrays.stream(line.split("( -> )|,")).mapToInt(Integer::parseInt).toArray();
            Arrays.stream(parsedValues).max().ifPresent(value -> {
                if(value > maxCoordValue.get())
                    maxCoordValue.set(value);
            });
            coords.add(new Coords(parsedValues[0], parsedValues[1], parsedValues[2], parsedValues[3]));
        });
        diagram = new int[maxCoordValue.get()+1][maxCoordValue.get()+1];
    }

    public Integer day5a() {
        for (Coords coord : coords) {
            if(isHorizontal(coord))
                for(int i = Math.min(coord.getX1(), coord.getX2()); i <= Math.max(coord.getX1() ,coord.getX2()); i++)
                    diagram[coord.getY1()][i]++;

            else if(isVertical(coord))
                for(int i = Math.min(coord.getY1(), coord.getY2()); i <= Math.max(coord.getY1() ,coord.getY2()); i++)
                    diagram[i][coord.getX1()]++;
        }
        return getFinalDiagramCount();
    }

    public Integer day5b() {
        day5a();
        for (Coords coord : coords) {
            if (isDiagonal(coord)){
            // up left to down right
                if((coord.getX2() > coord.getX1() && coord.getY2() > coord.getY1()) ||
                        (coord.getX1() > coord.getX2() && coord.getY1() > coord.getY2())){
                    for(int col = Math.min(coord.getX1(), coord.getX2()),
                        row = Math.min(coord.getY1(), coord.getY2()); row <= Math.max(coord.getY2(), coord.getY1()); col++, row++){
                        diagram[row][col]++;
                    }
                }
                // up right to down left
                else if((coord.getX2() < coord.getX1() && coord.getY2() > coord.getY1()) ||
                        (coord.getX2() > coord.getX1() && coord.getY1() > coord.getY2())){
                    for(int col = Math.max(coord.getX1(), coord.getX2()),
                        row = Math.min(coord.getY1(), coord.getY2()); row <= Math.max(coord.getY2(), coord.getY1()); col--, row++){
                        diagram[row][col]++;
                    }
                }
            }
        }
        return getFinalDiagramCount();
    }

    private Integer getFinalDiagramCount() {
        int count = 0;
        for (int[] ints : diagram)
            for (int value : ints)
                if (value > 1)
                    count++;
        return count;
    }

    private boolean isDiagonal(Coords coords) {
        return (coords.getX1() + coords.getX2() + coords.getY1() + coords.getY2()) % 2 == 0;
    }

    private boolean isHorizontal(Coords coords) {
        return coords.getY1() == coords.getY2();
    }

    private boolean isVertical(Coords coords) {
        return coords.getX1() == coords.getX2();
    }
}
