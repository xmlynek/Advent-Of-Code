package test;

import com.company.Day10;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    private final Day10 day10Example = new Day10("inputExample.txt");
    private final Day10 day10 = new Day10("input.txt");

    Day10Test() throws IOException {

    }

    @Test
    void day10aTest() {
        assertEquals(26397L, day10Example.day10a());
        assertEquals(319233L, day10.day10a());
    }

    @Test
    void day10bTest() {
        assertEquals(288957L, day10Example.day10b());
        assertEquals(1118976874L, day10.day10b());
    }
}