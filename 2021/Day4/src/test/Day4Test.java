package test;

import com.company.Day4;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    private final Day4 day4Example;
    private final Day4 day4Input;

    Day4Test() throws IOException {
        this.day4Example = new Day4(Files.readAllLines(Path.of("inputExample.txt")));
        this.day4Input = new Day4(Files.readAllLines(Path.of("input.txt")));
    }

    @Test
    void day4a() {
        assertEquals(4512, day4Example.day4a());
        assertEquals(64084, day4Input.day4a());
    }

    @Test
    void day4b() {
        assertEquals(1924, day4Example.day4b());
        assertEquals(12833, day4Input.day4b());
    }
}