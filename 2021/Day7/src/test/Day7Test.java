package test;

import com.company.Day7;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {

    private final Day7 day7Example;
    private final Day7 day7;

    public Day7Test() throws IOException {
        this.day7Example = new Day7(Files.readAllLines(Path.of("inputExample.txt")));
        this.day7 = new Day7(Files.readAllLines(Path.of("input.txt")));
    }

    @Test
    void day7aTest() {
        assertEquals(37, day7Example.day7a());
        assertEquals(342534, day7.day7a());
    }

    @Test
    void day7bTest() {
        assertEquals(168, day7Example.day7b());
        assertEquals(94004208, day7.day7b());
    }
}