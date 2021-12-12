package test;

import com.company.Day12;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    private final Day12 day12Example = new Day12("inputExample.txt");
    private final Day12 day12 = new Day12("input.txt");

    Day12Test() throws IOException {
    }

    @Test
    void day12a() {
        assertEquals(19, day12Example.day12a());
        assertEquals(5212, day12.day12a());
    }

    @Test
    void day12b() {
        assertEquals(103, day12Example.day12b());
        assertEquals(134862, day12.day12b());
    }
}