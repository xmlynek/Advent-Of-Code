package test;

import com.company.Day5;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    private final Day5 day5Example;
    private final Day5 day5;

    public Day5Test() throws IOException {
        this.day5Example = new Day5(Files.readAllLines(Path.of("inputExample.txt")));
        this.day5 = new Day5(Files.readAllLines(Path.of("input.txt")));
    }

    @Test
    void day5aTest() {
        assertEquals(5, day5Example.day5a());
        assertEquals(7674, day5.day5a());
    }

    @Test
    void day5bTest() {
        assertEquals(12, day5Example.day5b());
        assertEquals(20898, day5.day5b());
    }
}