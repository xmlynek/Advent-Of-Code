package test;

import com.company.Day6;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    private Day6 day6Example;
    private Day6 day6;

    @BeforeEach
    void setDay6() throws IOException{
        this.day6Example = new Day6(Files.readAllLines(Path.of("inputExample.txt")));
        this.day6 = new Day6(Files.readAllLines(Path.of("input.txt")));
    }

    @Test
    void day6aTest() {
        assertEquals(5934L, day6Example.day6a());
        assertEquals(353274L, day6.day6a());
    }

    @Test
    void day6bTest() {
        assertEquals(26984457539L, day6Example.day6b());
        assertEquals(1609314870967L, day6.day6b());
    }
}