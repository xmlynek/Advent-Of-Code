package test;

import com.company.Day9;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    private final Day9 day9Example = new Day9("inputExample.txt");
    private final Day9 day9 = new Day9("input.txt");


    Day9Test() throws IOException {
    }

    @Test
    void day9aTest() {
        assertEquals(15, day9Example.day9a());
        assertEquals(502, day9.day9a());
    }

    @Test
    void day9bTest() {
        assertEquals(1134, day9Example.day9b());
        assertEquals(1330560, day9.day9b());
    }
}