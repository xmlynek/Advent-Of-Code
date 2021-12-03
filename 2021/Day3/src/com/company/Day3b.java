package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Day3b {
    public static void main(String[] args) {
        var fileList = readFile();
        System.out.println(makeOperation(fileList, fileList.get(0).length(), OperationType.CO2) *
                makeOperation(fileList, fileList.get(0).length(), OperationType.OXYGEN));
    }

    private static Integer makeOperation(List<String> inputList, int iterationLength, OperationType operationType) {
        var list = List.copyOf(inputList);

        for(int i = 0; i < iterationLength && list.size() > 1; i++) {
            int finalI = i;
            var start1 = list.stream().filter(line -> line.charAt(finalI) == '1').collect(Collectors.toList());
            var start0 = list.stream().filter(line -> line.charAt(finalI) == '0').collect(Collectors.toList());

            if(operationType == OperationType.CO2)
                list = (start0.size() <= start1.size()) ? start0 : start1;
            else if(operationType == OperationType.OXYGEN)
                list = (start1.size() >= start0.size()) ? start1 : start0;
        }
        return Integer.parseInt(list.get(0), 2);
    }

    private static List<String> readFile () {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            List<String> fileInput = new ArrayList<>();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                fileInput.add(line);
            }
            return fileInput;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
