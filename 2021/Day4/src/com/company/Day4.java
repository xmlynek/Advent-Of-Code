package com.company;

import java.util.ArrayList;
import java.util.List;

public class Day4 {
    private final List<PlayTable> playTableList = new ArrayList<>();
    private final List<Integer> bingoTips = new ArrayList<>();

    public Day4(List<String> fileInput) {
        String[] inputs = fileInput.get(0).split(",");
        for (String input : inputs)
            if (!input.isEmpty())
                bingoTips.add(Integer.parseInt(input));

        List<String> tempList = new ArrayList<>();

        // Init playTableList
        for(int i = 2; i < fileInput.size(); i++){
            String line = fileInput.get(i);
            if(line.isEmpty()){
                playTableList.add(new PlayTable(tempList));
                tempList.clear();
                continue;
            }
            tempList.add(line);
        }
        playTableList.add(new PlayTable(tempList));
    }

    public Integer day4a() {
        for (Integer bingoTip : bingoTips) {
            for (var playTable: playTableList) {
                try {
                    playTable.guess(bingoTip);
                } catch (GameWonException e) {
                    return playTable.getResultOfTable() *bingoTip;
                }
            }
        }
        return 0;
    }

    public Integer day4b() {
        for (Integer bingoTip : bingoTips) {
            for (var playTable: playTableList) {
                try {
                    playTable.guess(bingoTip);
                } catch (GameWonException e) {
                    if (countWinnerTables() == playTableList.size()) {
                        return playTable.getResultOfTable() * bingoTip;
                    }
                }
            }
        }
        return 0;
    }

    private long countWinnerTables() {
        return playTableList.stream().filter(PlayTable::isWinner).count();
    }

}
