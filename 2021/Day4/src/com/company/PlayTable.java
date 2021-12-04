package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayTable {
    private static int TABLE_INDEX = 0;
    private final int tableNumber;
    private final List<Field> fieldList = new ArrayList<>();
    private final int tableSize;
    private boolean winner = false;

    public PlayTable(List<String> inputList) {
        tableSize = inputList.size();
        inputList.forEach(line -> {
            String[] inputs = line.split(" ");
            for (String input : inputs) {
                if (input.isEmpty()) continue;
                fieldList.add(new Field(Integer.parseInt(input)));
            }
        });
        tableNumber = TABLE_INDEX++;
    }

    public void guess(Integer value) throws GameWonException{
        var fieldOptional = fieldList.stream().filter(field -> field.getValue().equals(value)).findFirst();
        if(fieldOptional.isPresent()) {
            fieldOptional.get().setGuessed(true);
            checkWinCondition(fieldList.indexOf(fieldOptional.get()));
        }
    }

    private void checkWinCondition(int index) throws GameWonException {
        if(!checkColumns(index) && !checkRows(index))
            return;
        this.winner = true;
        throw new GameWonException();
    }

    private boolean checkColumns(int index) {
        int colStart = index % tableSize;
        for(int i = colStart; i < fieldList.size(); i += tableSize)
            if(!fieldList.get(i).isGuessed())
                return false;
        return true;
    }

    private boolean checkRows(int index) {
        int rowStart = (index/tableSize)*tableSize;
        for(int i = rowStart; i < rowStart + tableSize; i++)
            if(!fieldList.get(i).isGuessed())
                return false;
        return true;
    }

    public Integer getResultOfTable() {
        AtomicInteger sumOfUnmarkedFields = new AtomicInteger();
        fieldList.stream().filter(field -> !field.isGuessed()).forEach(field -> sumOfUnmarkedFields.addAndGet(field.getValue()));
        return sumOfUnmarkedFields.get();
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isWinner() {
        return winner;
    }
}
