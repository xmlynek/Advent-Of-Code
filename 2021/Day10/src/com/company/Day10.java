package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Day10 {
    private final Map<Character, Integer> errorPoints = Map.of(')', 3, ']', 57, '}', 1197, '>', 25137);
    private final Map<Character, Character> bracketPairs = Map.of('(', ')', '[', ']', '{', '}', '<', '>');
    private final Map<Character, Integer> bracketValues = Map.of('(', 1, '[', 2, '{', 3, '<', 4);
    AtomicLong sumOfInvalidPoints = new AtomicLong(0L);
    List<Long> bracketScores = new ArrayList<>();

    public Day10(String filename) throws IOException {
        var fileInput = Files.readAllLines(Path.of(filename));

       proceedSyntaxScoring(fileInput);
    }

    private void proceedSyntaxScoring(List<String> lines) {
        lines.forEach(line -> {
            Stack<Character> brackets = new Stack<>();
            boolean corrupted = false;

            for(int i = 0; i < line.length(); i++)  {
                if(bracketPairs.containsValue(line.charAt(i))) {
                    if(bracketPairs.get(brackets.pop()) != line.charAt(i)) {
                        corrupted = true;
                        sumOfInvalidPoints.addAndGet(errorPoints.get(line.charAt(i)));
                        break;
                    }
                } else brackets.push(line.charAt(i));
            }
            if(!corrupted) {
                long sum = 0;
                while(!brackets.isEmpty()) {
                    sum *= 5;
                    sum += bracketValues.get(brackets.pop());
                }
                bracketScores.add(sum);
            }
        });
    }

    public long day10a() {
        return this.sumOfInvalidPoints.get();
    }

    public long day10b() {
        return bracketScores.stream().sorted().collect(Collectors.toList()).get(bracketScores.size() / 2);
    }
}
